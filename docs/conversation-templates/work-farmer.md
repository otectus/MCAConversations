# Work talk with a farmer

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.farmer.crop_failing.active.respond`](#conversations-scene-work-farmer-crop-failing-active-respond)
- [`conversations.scene.work.farmer.crop_failing.blocked.respond`](#conversations-scene-work-farmer-crop-failing-blocked-respond)
- [`conversations.scene.work.farmer.crop_failing.failed.respond`](#conversations-scene-work-farmer-crop-failing-failed-respond)
- [`conversations.scene.work.farmer.crop_failing.succeeded.respond`](#conversations-scene-work-farmer-crop-failing-succeeded-respond)
- [`conversations.scene.work.farmer.followup`](#conversations-scene-work-farmer-followup)
- [`conversations.scene.work.farmer.pest_pressure.active.respond`](#conversations-scene-work-farmer-pest-pressure-active-respond)
- [`conversations.scene.work.farmer.pest_pressure.succeeded.respond`](#conversations-scene-work-farmer-pest-pressure-succeeded-respond)
- [`conversations.scene.work.farmer.price_dispute.blocked.respond`](#conversations-scene-work-farmer-price-dispute-blocked-respond)
- [`conversations.scene.work.farmer.price_dispute.succeeded.respond`](#conversations-scene-work-farmer-price-dispute-succeeded-respond)
- [`conversations.topic.work.farmer.craft.respond`](#conversations-topic-work-farmer-craft-respond)
- [`conversations.topic.work.farmer.followup`](#conversations-topic-work-farmer-followup)
- [`conversations.topic.work.farmer.future.respond`](#conversations-topic-work-farmer-future-respond)
- [`conversations.topic.work.farmer.respond`](#conversations-topic-work-farmer-respond)
- [`conversations.topic.work.farmer.risk.respond`](#conversations-topic-work-farmer-risk-respond)
- [`conversations.topic.work.farmer.task.respond`](#conversations-topic-work-farmer-task-respond)
- [`conversations.topic.work.farmer.village.respond`](#conversations-topic-work-farmer-village-respond)

---

## `conversations.scene.work.farmer.crop_failing.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.farmer.crop_failing.active` — e.g. "%2$s in %3$s has stopped getting worse, which is not the same as getting better, but I will take it for now."


```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.farmer.crop_failing.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.active.respond   [17 chars]
    en  The field, again.
    >>  ............................................
    pt  O campo, de novo.
    >>  ............................................
```


### Button `ask_odds` — "What are the odds it comes back?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.farmer.crop_failing.active` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.farmer.crop_failing.active.ask_odds` — accepted phrasings: "what are the odds it comes back"; "what are its chances"; "do you think it will recover"
  - the message must contain one of: `odds`, `chances`, `recover`
  - scored words: `odds`(1.8), `chances`(1.8), `recover`(1.8), `comes`(0.8), `back`(0.8), `think`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.active.respond.ask_odds
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.crop_failing.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.active.respond.ask_odds   [32 chars]
    en  What are the odds it comes back?
    >>  ............................................
    pt  Que chance ele tem de se recuperar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.farmer.crop_health`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.farmer.crop_failing"}
- Then opens: `conversations.scene.work.farmer.followup`
- …where the player's next choices will be: "What's the hardest part of the work?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.active.guessed
WHO    VILLAGER — what the player reads after pressing "What are the odds it comes back?"
       spoken on: conversations.scene.work.farmer.crop_failing.active.respond, button `ask_odds`
       leaves the player on: conversations.scene.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.crop_failing.active.guessed`: the villager explains. Subject `work.farmer.crop_health`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.active.guessed/1   [144 chars]
    en  Even, if the weather is kind. Worse if it is not. I have stopped putting a number on %2$s because the number was only ever me talking to myself.
    >>  ............................................
    pt  Meio a meio, se o tempo ajudar. Pior, se não. Parei de dar número a %2$s porque o número era só eu falando comigo mesma.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.active.guessed/2   [119 chars]
    en  Ask me in nine days. That is when %2$s either sets seed or does not, and no amount of standing over it moves that date.
    >>  ............................................
    pt  Me pergunte em nove dias. É quando %2$s vai granar ou não, e ficar em cima não muda essa data.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.active.guessed/3   [117 chars]
    en  Better than a week ago, which is the only comparison I trust. I have been wrong about %2$s twice this season already.
    >>  ............................................
    pt  Melhor que na semana passada, que é a única comparação em que confio. Já errei sobre %2$s duas vezes nesta estação.
    >>  ............................................
```


### Button `glad` — "I'm glad it's holding."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.farmer.crop_failing.active` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.farmer.crop_failing.active.glad` — accepted phrasings: "im glad its holding"; "glad it is holding on"; "good to hear it has stopped getting worse"
  - the message must contain one of: `glad`, `holding`, `getting`
  - scored words: `glad`(1.8), `holding`(1.8), `getting`(1.8), `good`(0.8), `hear`(0.8), `stopped`(0.8), `worse`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.active.respond.glad
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.crop_failing.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.active.respond.glad   [22 chars]
    en  I'm glad it's holding.
    >>  ............................................
    pt  Fico feliz que esteja aguentando.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.farmer.crop_failing.glad`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `work.farmer.crop_health`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.farmer.crop_failing"}
- Then opens: `conversations.scene.work.farmer.followup`
- …where the player's next choices will be: "What's the hardest part of the work?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.active.shared
WHO    VILLAGER — what the player reads after pressing "I'm glad it's holding."
       spoken on: conversations.scene.work.farmer.crop_failing.active.respond, button `glad`
       leaves the player on: conversations.scene.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.crop_failing.active.shared`: the villager accepts. Subject `work.farmer.crop_health`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.active.shared/1   [80 chars]
    en  So am I, and I am trying not to say so out loud too often in case %2$s hears me.
    >>  ............................................
    pt  Eu também, e estou tentando não dizer isso alto demais, para %2$s não me ouvir.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.active.shared/2   [88 chars]
    en  It is a strange thing to be glad about. A field that has stopped dying. But there it is.
    >>  ............................................
    pt  É uma coisa estranha de comemorar. Um campo que parou de morrer. Mas é o que temos.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.active.shared/3   [100 chars]
    en  Say that to me again in nine days and I will believe you. Today I am just walking %2$s and counting.
    >>  ............................................
    pt  Me diga isso de novo em nove dias e eu acredito. Hoje só estou andando por %2$s e contando.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · answers the beat(s) `work.farmer.crop_failing.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.crop_failing.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.active.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você trabalhar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.scene.work.farmer.crop_failing.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.left`: the villager accepts. Subject `work.farmer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.farmer.crop_failing.blocked.respond / leave; conversations.scene.work.farmer.crop_failing.failed.respond / leave; conversations.scene.work.farmer.crop_failing.succeeded.respond / leave; conversations.scene.work.farmer.followup / leave; conversations.scene.work.farmer.pest_pressure.active.respond / leave; conversations.scene.work.farmer.pest_pressure.succeeded.respond / leave; conversations.scene.work.farmer.price_dispute.blocked.respond / leave; conversations.scene.work.farmer.price_dispute.succeeded.respond / leave …and 7 more
```

```text
  dialogue.conversations.work.prof.farmer.leave/1   [45 chars]
    en  The row's not going anywhere. But aye, go on.
    >>  ............................................
    pt  A fileira não vai a lugar nenhum. Mas é, pode ir.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.leave/2   [39 chars]
    en  Mind the furrows on your way out, %1$s.
    >>  ............................................
    pt  Cuidado com os sulcos na saída, %1$s.
    >>  ............................................
```

---


## `conversations.scene.work.farmer.crop_failing.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.farmer.crop_failing.blocked` — e.g. "%2$s is going yellow at the tips in %3$s and I have known why for four days. %4$s. Knowing has not helped."


```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.farmer.crop_failing.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.blocked.respond   [10 chars]
    en  The field.
    >>  ............................................
    pt  O campo.
    >>  ............................................
```


### Button `ask_cause` — "How long has that been happening?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.farmer.crop_failing.blocked` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.farmer.crop_failing.blocked.ask_cause` — accepted phrasings: "how long has that been happening"; "how long has it been like that"; "when did that start"
  - the message must contain one of: `long`, `start`, `started`
  - scored words: `long`(1.8), `start`(1.8), `started`(1.8), `been`(0.8), `happening`(0.8), `like`(0.8), `when`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.blocked.respond.ask_cause
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.crop_failing.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.blocked.respond.ask_cause   [33 chars]
    en  How long has that been happening?
    >>  ............................................
    pt  Há quanto tempo isso está acontecendo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.farmer.crop_health`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.farmer.crop_failing"}
- Then opens: `conversations.scene.work.farmer.followup`
- …where the player's next choices will be: "What's the hardest part of the work?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.blocked.explained
WHO    VILLAGER — what the player reads after pressing "How long has that been happening?"
       spoken on: conversations.scene.work.farmer.crop_failing.blocked.respond, button `ask_cause`
       leaves the player on: conversations.scene.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.crop_failing.blocked.explained`: the villager explains. Subject `work.farmer.crop_health`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.blocked.explained/1   [132 chars]
    en  Longer than I said. I have been telling myself it was the weather since spring, and %2$s has been telling me otherwise since spring.
    >>  ............................................
    pt  Mais tempo do que eu disse. Venho me convencendo de que era o clima desde a primavera, e %2$s vem me dizendo o contrário desde a primavera.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.blocked.explained/2   [150 chars]
    en  Three weeks that I will admit to. My father farmed %2$s for thirty years and I have had it four, so you can imagine how eager I am to say it out loud.
    >>  ............................................
    pt  Três semanas, é o que eu admito. Meu pai cultivou %2$s por trinta anos e eu tenho ele há quatro, então imagine a minha vontade de dizer isso em voz alta.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.blocked.explained/3   [104 chars]
    en  Since the thaw. I walked %2$s every morning hoping to be wrong, which is a very slow way of being right.
    >>  ............................................
    pt  Desde o degelo. Percorri %2$s toda manhã esperando estar enganada, o que é um jeito muito lento de estar certa.
    >>  ............................................
```


### Button `offer_bonemeal` — "I can bring you bone meal for it."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.farmer.crop_failing.blocked` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.farmer.crop_failing.blocked.offer_bonemeal` — accepted phrasings: "i can bring you bone meal for it"; "let me bring you bone meal"; "i will fetch bone meal"
  - the message must contain one of: `bone`, `meal`
  - scored words: `bone`(1.8), `meal`(1.8), `bring`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.blocked.respond.offer_bonemeal
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.crop_failing.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.blocked.respond.offer_bonemeal   [33 chars]
    en  I can bring you bone meal for it.
    >>  ............................................
    pt  Posso trazer farinha de osso para isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.farmer.crop_failing.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +3, warmth +2  _(recorded under topic `work.farmer.crop_health`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.crop_failing", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.farmer.crop_failing", "obligation": "commitment:work.farmer.bring_bonemeal"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.farmer.bring_bonemeal"}
- Then opens: `conversations.scene.work.farmer.followup`
- …where the player's next choices will be: "What's the hardest part of the work?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I can bring you bone meal for it."
       spoken on: conversations.scene.work.farmer.crop_failing.blocked.respond, button `offer_bonemeal`
       leaves the player on: conversations.scene.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.crop_failing.blocked.accepted`: the villager accepts. Subject `work.farmer.crop_health`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.blocked.accepted/1   [143 chars]
    en  Then I will hold off turning %2$s under until you are back. That is a real thing you have just done, and I am not going to pretend it is small.
    >>  ............................................
    pt  Então vou adiar revirar %2$s até você voltar. Isso que você acabou de fazer é coisa séria, e não vou fingir que é pouco.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.blocked.accepted/2   [118 chars]
    en  You will. Right. I had got as far as deciding to lose %2$s, so you have caught me a day before I did something stupid.
    >>  ............................................
    pt  Vai mesmo. Certo. Eu já tinha chegado à decisão de perder %2$s, então você me pegou um dia antes de eu fazer uma bobagem.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.blocked.accepted/3   [103 chars]
    en  I will take that, and I will take it gladly. %2$s gets one more chance and then I stop arguing with it.
    >>  ............................................
    pt  Aceito, e aceito com gosto. %2$s ganha mais uma chance e depois eu paro de discutir com ele.
    >>  ............................................
```


### Button `advise_fallow` — "Let it lie fallow a year. The ground is telling you something."

*stance family `candor` · tone `plain` · outcome `resisted` · answers the beat(s) `work.farmer.crop_failing.blocked` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.farmer.crop_failing.blocked.advise_fallow` — accepted phrasings: "let it lie fallow a year the ground is telling you something"; "let it lie fallow"; "rest the ground a year"; "leave the field fallow"
  - the message must contain one of: `fallow`, `rest`, `descansar`
  - scored words: `fallow`(1.8), `rest`(1.8), `descansar`(1.8), `lie`(0.8), `year`(0.8), `telling`(0.8), `something`(0.8), `leave`(0.8), `field`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.blocked.respond.advise_fallow
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.crop_failing.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.blocked.respond.advise_fallow   [62 chars]
    en  Let it lie fallow a year. The ground is telling you something.
    >>  ............................................
    pt  Deixe descansar um ano. A terra está te dizendo algo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2, tension +1  _(recorded under topic `work.farmer.crop_health`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.farmer.crop_failing"}
- Then opens: `conversations.scene.work.farmer.followup`
- …where the player's next choices will be: "What's the hardest part of the work?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.blocked.resisted
WHO    VILLAGER — what the player reads after pressing "Let it lie fallow a year. The ground is telling you something."
       spoken on: conversations.scene.work.farmer.crop_failing.blocked.respond, button `advise_fallow`
       leaves the player on: conversations.scene.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.crop_failing.blocked.resisted`: the villager resists. Subject `work.farmer.crop_health`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.blocked.resisted/1   [145 chars]
    en  It is telling me something, yes. It is telling me it wants a year I do not have. A fallow %2$s is a winter of buying bread instead of selling it.
    >>  ............................................
    pt  Está me dizendo algo, sim. Está dizendo que quer um ano que eu não tenho. Deixar %2$s em pousio é um inverno comprando pão em vez de vender.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.blocked.resisted/2   [126 chars]
    en  You are not wrong and it is still not advice I can take. Fallow is what people say when the field is not the one feeding them.
    >>  ............................................
    pt  Você não está errada, e mesmo assim não é conselho que eu possa seguir. Pousio é o que se diz quando o campo não é o que alimenta a gente.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.blocked.resisted/3   [170 chars]
    en  I know. I have known since the thaw. Say it to me again after harvest and I might be able to hear it — right now %2$s is the difference between a lean year and a bad one.
    >>  ............................................
    pt  Eu sei. Sei desde o degelo. Me diga isso de novo depois da colheita e talvez eu consiga ouvir — agora %2$s é a diferença entre um ano magro e um ano ruim.
    >>  ............................................
```


### Button `no_advice` — "Ground is beyond me. I'd be guessing."

*stance family `restraint` · tone `plain` · outcome `qualified` · answers the beat(s) `work.farmer.crop_failing.blocked` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.farmer.crop_failing.blocked.no_advice` — accepted phrasings: "ground is beyond me id be guessing"; "ground is beyond me id be guessing"; "im guessing if i say anything about soil"
  - the message must contain one of: `beyond`, `guessing`, `soil`
  - scored words: `beyond`(1.8), `guessing`(1.8), `soil`(1.8), `say`(0.8), `anything`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.blocked.respond.no_advice
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.crop_failing.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.blocked.respond.no_advice   [37 chars]
    en  Ground is beyond me. I'd be guessing.
    >>  ............................................
    pt  De terra eu não entendo. Eu estaria chutando.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +1  _(recorded under topic `work.farmer.crop_health`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.farmer.crop_failing"}
- Then opens: `conversations.scene.work.farmer.followup`
- …where the player's next choices will be: "What's the hardest part of the work?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.blocked.excused
WHO    VILLAGER — what the player reads after pressing "Ground is beyond me. I'd be guessing."
       spoken on: conversations.scene.work.farmer.crop_failing.blocked.respond, button `no_advice`
       leaves the player on: conversations.scene.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.crop_failing.blocked.excused`: the villager qualifys. Subject `work.farmer.crop_health`, polarity `mixed`, permits followup, outcome `qualified`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.blocked.excused/1   [110 chars]
    en  Nor does half the village, and it has never stopped them. I would rather stand here with somebody who says so.
    >>  ............................................
    pt  Nem metade da vila entende, e isso nunca os impediu. Prefiro ficar aqui com alguém que admite.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.blocked.excused/2   [88 chars]
    en  That is an honest answer and I have had four dishonest ones this week. Thank you for it.
    >>  ............................................
    pt  Essa é uma resposta honesta, e eu recebi quatro desonestas esta semana. Obrigada por ela.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.blocked.excused/3   [73 chars]
    en  Good. Then you will not tell me to lime it. Everyone tells me to lime it.
    >>  ............................................
    pt  Ótimo. Então você não vai me mandar passar cal. Todo mundo me manda passar cal.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · answers the beat(s) `work.farmer.crop_failing.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.crop_failing.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.blocked.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você trabalhar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.scene.work.farmer.crop_failing.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.left`: the villager accepts. Subject `work.farmer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.farmer.crop_failing.active.respond / leave; conversations.scene.work.farmer.crop_failing.failed.respond / leave; conversations.scene.work.farmer.crop_failing.succeeded.respond / leave; conversations.scene.work.farmer.followup / leave; conversations.scene.work.farmer.pest_pressure.active.respond / leave; conversations.scene.work.farmer.pest_pressure.succeeded.respond / leave; conversations.scene.work.farmer.price_dispute.blocked.respond / leave; conversations.scene.work.farmer.price_dispute.succeeded.respond / leave …and 7 more
```

> Written out in full under **`conversations.scene.work.farmer.crop_failing.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.farmer.crop_failing.failed.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.farmer.crop_failing.failed` — e.g. "I turned %2$s under last week. There was no point pretending %3$s was going to give me anything."


```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.failed.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.farmer.crop_failing.failed.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.failed.respond   [20 chars]
    en  It didn't come back.
    >>  ............................................
    pt  Não se recuperou.
    >>  ............................................
```


### Button `sit_with_it` — "That's a year's work. I'm sorry."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.farmer.crop_failing.failed` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.farmer.crop_failing.failed.sit_with_it` — accepted phrasings: "thats a years work im sorry"; "that is a whole year of work"; "im sorry that is a real loss"
  - the message must contain one of: `sorry`, `year`, `loss`
  - scored words: `sorry`(1.8), `year`(1.8), `loss`(1.8), `thats`(0.8), `years`(0.8), `work`(0.8), `whole`(0.8), `real`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.failed.respond.sit_with_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.crop_failing.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.failed.respond.sit_with_it   [32 chars]
    en  That's a year's work. I'm sorry.
    >>  ............................................
    pt  É um ano de trabalho. Sinto muito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.farmer.crop_failing.mourn`, budget `standard`, replay policy `once`
- Does: disposition — warmth +3, trust +2  _(recorded under topic `work.farmer.crop_health`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.farmer.crop_failing"}
- Then opens: `conversations.scene.work.farmer.followup`
- …where the player's next choices will be: "What's the hardest part of the work?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.failed.received
WHO    VILLAGER — what the player reads after pressing "That's a year's work. I'm sorry."
       spoken on: conversations.scene.work.farmer.crop_failing.failed.respond, button `sit_with_it`
       leaves the player on: conversations.scene.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.crop_failing.failed.received`: the villager qualifys. Subject `work.farmer.crop_health`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.failed.received/1   [128 chars]
    en  It is, and thank you for calling it that. Everyone says there is always next year, which is true and is not what I needed today.
    >>  ............................................
    pt  É mesmo, e obrigada por chamar assim. Todo mundo diz que sempre tem o ano que vem, o que é verdade e não era o que eu precisava hoje.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.failed.received/2   [83 chars]
    en  A year and a half, if you count the winter I spent planning it. But yes. Thank you.
    >>  ............................................
    pt  Um ano e meio, se contar o inverno que passei planejando. Mas sim. Obrigada.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.failed.received/3   [131 chars]
    en  I have not said that out loud yet. I have been calling it a setback since Tuesday. Standing in %2$s it did not feel like a setback.
    >>  ............................................
    pt  Ainda não tinha dito isso em voz alta. Venho chamando de contratempo desde terça. Parada em %2$s não parecia contratempo.
    >>  ............................................
```


### Button `ask_lesson` — "What would you do differently?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.farmer.crop_failing.failed` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.farmer.crop_failing.failed.ask_lesson` — accepted phrasings: "what would you do differently"; "what would you change"; "what did it teach you"
  - the message must contain one of: `differently`, `change`, `teach`
  - scored words: `differently`(1.8), `change`(1.8), `teach`(1.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.failed.respond.ask_lesson
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.crop_failing.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.failed.respond.ask_lesson   [30 chars]
    en  What would you do differently?
    >>  ............................................
    pt  O que você faria diferente?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2, familiarity +1  _(recorded under topic `work.farmer.crop_health`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.farmer.crop_failing"}
- Then opens: `conversations.scene.work.farmer.followup`
- …where the player's next choices will be: "What's the hardest part of the work?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.failed.learned
WHO    VILLAGER — what the player reads after pressing "What would you do differently?"
       spoken on: conversations.scene.work.farmer.crop_failing.failed.respond, button `ask_lesson`
       leaves the player on: conversations.scene.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.crop_failing.failed.learned`: the villager explains. Subject `work.farmer.crop_health`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.failed.learned/1   [120 chars]
    en  Listen to %2$s in the first week instead of the fourth. I knew in the first week. I spent three more deciding I did not.
    >>  ............................................
    pt  Ouvir %2$s na primeira semana em vez de na quarta. Eu sabia na primeira semana. Passei mais três decidindo que não sabia.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.failed.learned/2   [111 chars]
    en  Sow less of it and more beans. I have said that after every bad year and planted the same thing every good one.
    >>  ............................................
    pt  Plantar menos disso e mais feijão. Digo isso depois de todo ano ruim e planto a mesma coisa em todo ano bom.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.failed.learned/3   [98 chars]
    en  Ask somebody sooner. Not for the answer — I had the answer — for the company while I looked at it.
    >>  ............................................
    pt  Perguntar a alguém antes. Não pela resposta — eu tinha a resposta — pela companhia enquanto eu olhava para aquilo.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · answers the beat(s) `work.farmer.crop_failing.failed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.failed.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.crop_failing.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.failed.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você trabalhar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.scene.work.farmer.crop_failing.failed.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.left`: the villager accepts. Subject `work.farmer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.farmer.crop_failing.active.respond / leave; conversations.scene.work.farmer.crop_failing.blocked.respond / leave; conversations.scene.work.farmer.crop_failing.succeeded.respond / leave; conversations.scene.work.farmer.followup / leave; conversations.scene.work.farmer.pest_pressure.active.respond / leave; conversations.scene.work.farmer.pest_pressure.succeeded.respond / leave; conversations.scene.work.farmer.price_dispute.blocked.respond / leave; conversations.scene.work.farmer.price_dispute.succeeded.respond / leave …and 7 more
```

> Written out in full under **`conversations.scene.work.farmer.crop_failing.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.farmer.crop_failing.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.farmer.crop_failing.succeeded` — e.g. "%2$s came back. Not the far end of %3$s — that is gone — but the rest of it, and the rest of it is a year."


```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.farmer.crop_failing.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.succeeded.respond   [13 chars]
    en  It came back.
    >>  ............................................
    pt  Se recuperou.
    >>  ............................................
```


### Button `credit_her` — "You kept it alive. That was you."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.farmer.crop_failing.succeeded` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.farmer.crop_failing.succeeded.credit_her` — accepted phrasings: "you kept it alive that was you"; "you did that"; "that was your work not luck"
  - the message must contain one of: `you`, `your`
  - scored words: `you`(1.8), `your`(1.8), `kept`(0.8), `alive`(0.8), `work`(0.8), `luck`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.succeeded.respond.credit_her
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.crop_failing.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.succeeded.respond.credit_her   [32 chars]
    en  You kept it alive. That was you.
    >>  ............................................
    pt  Você o manteve vivo. Isso foi você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.farmer.crop_failing.credit`, budget `standard`, replay policy `once`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.farmer.crop_health`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.farmer.crop_failing"}
- Then opens: `conversations.scene.work.farmer.followup`
- …where the player's next choices will be: "What's the hardest part of the work?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.succeeded.credited
WHO    VILLAGER — what the player reads after pressing "You kept it alive. That was you."
       spoken on: conversations.scene.work.farmer.crop_failing.succeeded.respond, button `credit_her`
       leaves the player on: conversations.scene.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.crop_failing.succeeded.credited`: the villager qualifys. Subject `work.farmer.crop_health`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.succeeded.credited/1   [123 chars]
    en  Me and the weather, and the weather did not have to help. I will take my share and leave the rest to whoever arranges rain.
    >>  ............................................
    pt  Eu e o tempo, e o tempo não era obrigado a ajudar. Fico com a minha parte e deixo o resto para quem organiza a chuva.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.succeeded.credited/2   [117 chars]
    en  It was me being stubborn in the right direction for once. Ask me next year and I may have picked the wrong direction.
    >>  ............................................
    pt  Foi eu ser teimosa na direção certa, pela primeira vez. Me pergunte no ano que vem e talvez eu tenha escolhido a direção errada.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.succeeded.credited/3   [97 chars]
    en  I will allow it. Not the whole of it — %2$s decided a good deal on its own — but I will allow it.
    >>  ............................................
    pt  Aceito. Não tudo — %2$s decidiu boa parte sozinho — mas aceito.
    >>  ............................................
```


### Button `ask_next_year` — "Will you plant it again next year?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.farmer.crop_failing.succeeded` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.farmer.crop_failing.succeeded.ask_next_year` — accepted phrasings: "will you plant it again next year"; "will you sow it again"; "same crop next season"
  - the message must contain one of: `again`, `next`, `season`
  - scored words: `again`(1.8), `next`(1.8), `season`(1.8), `plant`(0.8), `year`(0.8), `sow`(0.8), `same`(0.8), `crop`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.succeeded.respond.ask_next_year
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.crop_failing.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.succeeded.respond.ask_next_year   [34 chars]
    en  Will you plant it again next year?
    >>  ............................................
    pt  Vai plantar de novo no ano que vem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.farmer.crop_health`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.farmer.crop_failing"}
- Then opens: `conversations.scene.work.farmer.followup`
- …where the player's next choices will be: "What's the hardest part of the work?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.succeeded.planned
WHO    VILLAGER — what the player reads after pressing "Will you plant it again next year?"
       spoken on: conversations.scene.work.farmer.crop_failing.succeeded.respond, button `ask_next_year`
       leaves the player on: conversations.scene.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.crop_failing.succeeded.planned`: the villager explains. Subject `work.farmer.crop_health`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.succeeded.planned/1   [122 chars]
    en  Not in the far end of %2$s. That corner has told me twice now and I am done arguing with it. Beans there, and we will see.
    >>  ............................................
    pt  Na ponta de lá de %2$s não. Aquele canto já me avisou duas vezes e eu parei de discutir. Feijão ali, e a gente vê.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.succeeded.planned/2   [78 chars]
    en  The same, and I will hate every morning of it until it is up. That is farming.
    >>  ............................................
    pt  O mesmo, e vou detestar cada manhã até nascer. Isso é a lavoura.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.crop_failing.succeeded.planned/3   [92 chars]
    en  I have been standing at %2$s working that out for a week. Probably. Probably is what I have.
    >>  ............................................
    pt  Faz uma semana que fico parada em %2$s pensando nisso. Provavelmente. Provavelmente é o que eu tenho.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · answers the beat(s) `work.farmer.crop_failing.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.crop_failing.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.crop_failing.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.crop_failing.succeeded.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você trabalhar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.scene.work.farmer.crop_failing.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.left`: the villager accepts. Subject `work.farmer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.farmer.crop_failing.active.respond / leave; conversations.scene.work.farmer.crop_failing.blocked.respond / leave; conversations.scene.work.farmer.crop_failing.failed.respond / leave; conversations.scene.work.farmer.followup / leave; conversations.scene.work.farmer.pest_pressure.active.respond / leave; conversations.scene.work.farmer.pest_pressure.succeeded.respond / leave; conversations.scene.work.farmer.price_dispute.blocked.respond / leave; conversations.scene.work.farmer.price_dispute.succeeded.respond / leave …and 7 more
```

> Written out in full under **`conversations.scene.work.farmer.crop_failing.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.farmer.followup`

**Reached from 18 route(s):** `conversations.scene.work.farmer.crop_failing.active.respond` / `ask_odds`; `conversations.scene.work.farmer.crop_failing.active.respond` / `glad`; `conversations.scene.work.farmer.crop_failing.blocked.respond` / `ask_cause`; `conversations.scene.work.farmer.crop_failing.blocked.respond` / `offer_bonemeal`; `conversations.scene.work.farmer.crop_failing.blocked.respond` / `advise_fallow`; `conversations.scene.work.farmer.crop_failing.blocked.respond` / `no_advice`; `conversations.scene.work.farmer.crop_failing.failed.respond` / `sit_with_it`; `conversations.scene.work.farmer.crop_failing.failed.respond` / `ask_lesson`; `conversations.scene.work.farmer.crop_failing.succeeded.respond` / `credit_her`; `conversations.scene.work.farmer.crop_failing.succeeded.respond` / `ask_next_year`; `conversations.scene.work.farmer.pest_pressure.active.respond` / `urge_fence`; `conversations.scene.work.farmer.pest_pressure.active.respond` / `ask_tolerance` …and 6 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.farmer.crop_failing.active.guessed` — e.g. "Even, if the weather is kind. Worse if it is not. I have stopped putting a number on %2$s because the number was only ever me talking to myself."
- `conversations.scene.work.farmer.crop_failing.active.shared` — e.g. "So am I, and I am trying not to say so out loud too often in case %2$s hears me."
- `conversations.scene.work.farmer.crop_failing.blocked.accepted` — e.g. "Then I will hold off turning %2$s under until you are back. That is a real thing you have just done, and I am not going to pretend it is small."
- `conversations.scene.work.farmer.crop_failing.blocked.excused` — e.g. "Nor does half the village, and it has never stopped them. I would rather stand here with somebody who says so."
- `conversations.scene.work.farmer.crop_failing.blocked.explained` — e.g. "Longer than I said. I have been telling myself it was the weather since spring, and %2$s has been telling me otherwise since spring."
- `conversations.scene.work.farmer.crop_failing.blocked.resisted` — e.g. "It is telling me something, yes. It is telling me it wants a year I do not have. A fallow %2$s is a winter of buying bread instead of selling it."
- `conversations.scene.work.farmer.crop_failing.failed.learned` — e.g. "Listen to %2$s in the first week instead of the fourth. I knew in the first week. I spent three more deciding I did not."
- `conversations.scene.work.farmer.crop_failing.failed.received` — e.g. "It is, and thank you for calling it that. Everyone says there is always next year, which is true and is not what I needed today."
- `conversations.scene.work.farmer.crop_failing.succeeded.credited` — e.g. "Me and the weather, and the weather did not have to help. I will take my share and leave the rest to whoever arranges rain."
- `conversations.scene.work.farmer.crop_failing.succeeded.planned` — e.g. "Not in the far end of %2$s. That corner has told me twice now and I am done arguing with it. Beans there, and we will see."
- `conversations.scene.work.farmer.pest_pressure.active.conceded` — e.g. "Yes. Yes, all right. I have known that since the first week and I have been doing the cheap thing every week since. %2$s gets a fence."
- `conversations.scene.work.farmer.pest_pressure.active.weighed` — e.g. "A tithe. Always a tithe — %2$s were here before the field was. It is when it goes past a tithe that I stop being philosophical about it."
- `conversations.scene.work.farmer.pest_pressure.succeeded.counted` — e.g. "Less than the crop it will save in one year, which is the sum I should have done in the first week. I did it on the third day of building instead."
- `conversations.scene.work.farmer.pest_pressure.succeeded.wry` — e.g. "Say it. Go on. I have been saying it to myself with a hammer in my hand for two days and I would rather hear it from somebody else."
- …and 4 more pools


```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.farmer.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.farmer.followup   [14 chars]
    en  Anything else?
    >>  ............................................
    pt  Mais alguma coisa?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of the work?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.farmer.*` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.farmer.followup.ask_more` — accepted phrasings: "whats the hardest part of the work"; "what is the hardest part of it"; "whats the worst part of the job"
  - the message must contain one of: `hardest`, `part`
  - scored words: `hardest`(1.8), `part`(1.8), `whats`(0.8), `work`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.followup.ask_more   [36 chars]
    en  What's the hardest part of the work?
    >>  ............................................
    pt  Qual é a parte mais difícil do trabalho?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.farmer.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.farmer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What happens if the harvest fails?" | "Good luck with the season."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of the work?"
       spoken on: conversations.scene.work.farmer.followup, button `ask_more`
       leaves the player on: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.hard`: the villager explains. Subject `work.farmer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.farmer.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.farmer.hard/1   [96 chars]
    en  A morning a day, all summer. Multiply that out and you'll see why I'm short with people in July.
    >>  ............................................
    pt  Uma manhã por dia, o verão inteiro. Some tudo e você vê por que eu sou seco com as pessoas em julho.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.hard/2   [88 chars]
    en  A row a week if I let them. That's bread nobody eats, %1$s, and nobody counts it either.
    >>  ............................................
    pt  Uma fileira por semana se eu deixar. É pão que ninguém come, %1$s, e ninguém conta também.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.farmer.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.followup.leave   [28 chars]
    en  I'll let you get back to it.
    >>  ............................................
    pt  Vou deixar você voltar ao trabalho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.scene.work.farmer.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.left`: the villager accepts. Subject `work.farmer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.farmer.crop_failing.active.respond / leave; conversations.scene.work.farmer.crop_failing.blocked.respond / leave; conversations.scene.work.farmer.crop_failing.failed.respond / leave; conversations.scene.work.farmer.crop_failing.succeeded.respond / leave; conversations.scene.work.farmer.pest_pressure.active.respond / leave; conversations.scene.work.farmer.pest_pressure.succeeded.respond / leave; conversations.scene.work.farmer.price_dispute.blocked.respond / leave; conversations.scene.work.farmer.price_dispute.succeeded.respond / leave …and 7 more
```

> Written out in full under **`conversations.scene.work.farmer.crop_failing.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.farmer.pest_pressure.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.farmer.pest_pressure.active` — e.g. "%2$s in %3$s again. Not enough to ruin me, enough to be there every morning, which is its own kind of grinding."


```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.pest_pressure.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.farmer.pest_pressure.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.farmer.pest_pressure.active.respond   [16 chars]
    en  About the field.
    >>  ............................................
    pt  Sobre o campo.
    >>  ............................................
```


### Button `urge_fence` — "Fence it properly. Once, and be done."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.farmer.pest_pressure.active` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.farmer.pest_pressure.active.urge_fence` — accepted phrasings: "fence it properly once and be done"; "build a proper fence"; "fence it once and for all"
  - the message must contain one of: `fence`, `fencing`, `cerque`
  - scored words: `fence`(1.8), `fencing`(1.8), `cerque`(1.8), `properly`(0.8), `once`(0.8), `done`(0.8), `build`(0.8), `proper`(0.8), `all`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.pest_pressure.active.respond.urge_fence
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.pest_pressure.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.pest_pressure.active.respond.urge_fence   [37 chars]
    en  Fence it properly. Once, and be done.
    >>  ............................................
    pt  Cerque direito. De uma vez, e acabou.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2, familiarity +1  _(recorded under topic `work.farmer.pests`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.pest_pressure", "state": "succeeded"}
- Does: `conversations_thread` = {"op": "open", "template": "work.farmer.pest_pressure"}
- Then opens: `conversations.scene.work.farmer.followup`
- …where the player's next choices will be: "What's the hardest part of the work?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.pest_pressure.active.conceded
WHO    VILLAGER — what the player reads after pressing "Fence it properly. Once, and be done."
       spoken on: conversations.scene.work.farmer.pest_pressure.active.respond, button `urge_fence`
       leaves the player on: conversations.scene.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.pest_pressure.active.conceded`: the villager accepts. Subject `work.farmer.pests`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.pest_pressure.active.conceded/1   [134 chars]
    en  Yes. Yes, all right. I have known that since the first week and I have been doing the cheap thing every week since. %2$s gets a fence.
    >>  ............................................
    pt  Sim. Sim, está bem. Eu sei disso desde a primeira semana e venho fazendo o barato toda semana desde então. %2$s vai ganhar cerca.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.pest_pressure.active.conceded/2   [93 chars]
    en  You are the third person to say it and the first one I have not argued with. Fine. This week.
    >>  ............................................
    pt  Você é a terceira pessoa a dizer isso e a primeira com quem eu não discuti. Pronto. Esta semana.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.pest_pressure.active.conceded/3   [129 chars]
    en  It is two days of work I do not want to spend and forty mornings I do not want to keep spending. Put like that it decides itself.
    >>  ............................................
    pt  São dois dias de trabalho que eu não quero gastar e quarenta manhãs que eu não quero continuar gastando. Assim posto, decide sozinho.
    >>  ............................................
```


### Button `ask_tolerance` — "How much of it can you live with?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.farmer.pest_pressure.active` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.farmer.pest_pressure.active.ask_tolerance` — accepted phrasings: "how much of it can you live with"; "how much can you tolerate"; "where is the line for you"
  - the message must contain one of: `live`, `tolerate`, `line`
  - scored words: `live`(1.8), `tolerate`(1.8), `line`(1.8), `much`(0.8), `where`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.pest_pressure.active.respond.ask_tolerance
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.pest_pressure.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.pest_pressure.active.respond.ask_tolerance   [33 chars]
    en  How much of it can you live with?
    >>  ............................................
    pt  Com quanto disso você consegue conviver?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.farmer.pests`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.farmer.pest_pressure"}
- Then opens: `conversations.scene.work.farmer.followup`
- …where the player's next choices will be: "What's the hardest part of the work?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.pest_pressure.active.weighed
WHO    VILLAGER — what the player reads after pressing "How much of it can you live with?"
       spoken on: conversations.scene.work.farmer.pest_pressure.active.respond, button `ask_tolerance`
       leaves the player on: conversations.scene.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.pest_pressure.active.weighed`: the villager explains. Subject `work.farmer.pests`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.pest_pressure.active.weighed/1   [136 chars]
    en  A tithe. Always a tithe — %2$s were here before the field was. It is when it goes past a tithe that I stop being philosophical about it.
    >>  ............................................
    pt  Um dízimo. Sempre um dízimo — %2$s estavam aqui antes do campo. É quando passa do dízimo que eu deixo de ser filosófica.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.pest_pressure.active.weighed/2   [128 chars]
    en  More than most farmers, which I get told about. I would rather feed %2$s than spend my life at war with the edge of my own land.
    >>  ............................................
    pt  Mais que a maioria dos lavradores, e me falam disso. Prefiro alimentar %2$s a passar a vida em guerra com a borda da minha própria terra.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.pest_pressure.active.weighed/3   [92 chars]
    en  Less than I did before I had a bad year. That is the honest answer and I am not proud of it.
    >>  ............................................
    pt  Menos do que antes de eu ter um ano ruim. Essa é a resposta honesta e não me orgulho dela.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · answers the beat(s) `work.farmer.pest_pressure.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.pest_pressure.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.pest_pressure.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.pest_pressure.active.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você trabalhar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.scene.work.farmer.pest_pressure.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.left`: the villager accepts. Subject `work.farmer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.farmer.crop_failing.active.respond / leave; conversations.scene.work.farmer.crop_failing.blocked.respond / leave; conversations.scene.work.farmer.crop_failing.failed.respond / leave; conversations.scene.work.farmer.crop_failing.succeeded.respond / leave; conversations.scene.work.farmer.followup / leave; conversations.scene.work.farmer.pest_pressure.succeeded.respond / leave; conversations.scene.work.farmer.price_dispute.blocked.respond / leave; conversations.scene.work.farmer.price_dispute.succeeded.respond / leave …and 7 more
```

> Written out in full under **`conversations.scene.work.farmer.crop_failing.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.farmer.pest_pressure.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.farmer.pest_pressure.succeeded` — e.g. "The fence is up along %3$s. Two days of my life and I have not seen %2$s inside it since, and I am quietly furious about how simple that was."


```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.pest_pressure.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.farmer.pest_pressure.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.farmer.pest_pressure.succeeded.respond   [18 chars]
    en  The fence went up.
    >>  ............................................
    pt  A cerca subiu.
    >>  ............................................
```


### Button `tease` — "Two days, after forty mornings."

*stance family `humor` · tone `playful` · outcome `appreciated` · answers the beat(s) `work.farmer.pest_pressure.succeeded` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.farmer.pest_pressure.succeeded.tease` — accepted phrasings: "two days after forty mornings"; "only two days then"; "forty mornings and it took two days"
  - the message must contain one of: `days`, `mornings`
  - scored words: `days`(1.8), `mornings`(1.8), `two`(0.8), `after`(0.8), `forty`(0.8), `only`(0.8), `took`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.pest_pressure.succeeded.respond.tease
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.pest_pressure.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.pest_pressure.succeeded.respond.tease   [31 chars]
    en  Two days, after forty mornings.
    >>  ............................................
    pt  Dois dias, depois de quarenta manhãs.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2  _(recorded under topic `work.farmer.pests`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.farmer.pest_pressure"}
- Then opens: `conversations.scene.work.farmer.followup`
- …where the player's next choices will be: "What's the hardest part of the work?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.pest_pressure.succeeded.wry
WHO    VILLAGER — what the player reads after pressing "Two days, after forty mornings."
       spoken on: conversations.scene.work.farmer.pest_pressure.succeeded.respond, button `tease`
       leaves the player on: conversations.scene.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.pest_pressure.succeeded.wry`: the villager accepts. Subject `work.farmer.pests`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.pest_pressure.succeeded.wry/1   [131 chars]
    en  Say it. Go on. I have been saying it to myself with a hammer in my hand for two days and I would rather hear it from somebody else.
    >>  ............................................
    pt  Fale. Pode falar. Passei dois dias dizendo isso a mim mesma com um martelo na mão e prefiro ouvir de outra pessoa.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.pest_pressure.succeeded.wry/2   [97 chars]
    en  Do not. I know. I am going to be told this by my own memory every spring for the rest of my life.
    >>  ............................................
    pt  Não diga. Eu sei. Minha própria memória vai me lembrar disso toda primavera pelo resto da vida.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.pest_pressure.succeeded.wry/3   [67 chars]
    en  Forty-one, but who is counting. I was, obviously. That is the joke.
    >>  ............................................
    pt  Quarenta e uma, mas quem está contando. Eu estava, claro. É essa a piada.
    >>  ............................................
```


### Button `ask_cost` — "What did the timber cost you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.farmer.pest_pressure.succeeded` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.farmer.pest_pressure.succeeded.ask_cost` — accepted phrasings: "what did the timber cost you"; "what did the wood cost"; "was the timber dear"
  - the message must contain one of: `timber`, `wood`, `cost`
  - scored words: `timber`(1.8), `wood`(1.8), `cost`(1.8), `dear`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.pest_pressure.succeeded.respond.ask_cost
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.pest_pressure.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.pest_pressure.succeeded.respond.ask_cost   [29 chars]
    en  What did the timber cost you?
    >>  ............................................
    pt  Quanto lhe custou a madeira?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.farmer.pests`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.farmer.pest_pressure"}
- Then opens: `conversations.scene.work.farmer.followup`
- …where the player's next choices will be: "What's the hardest part of the work?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.pest_pressure.succeeded.counted
WHO    VILLAGER — what the player reads after pressing "What did the timber cost you?"
       spoken on: conversations.scene.work.farmer.pest_pressure.succeeded.respond, button `ask_cost`
       leaves the player on: conversations.scene.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.pest_pressure.succeeded.counted`: the villager explains. Subject `work.farmer.pests`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.pest_pressure.succeeded.counted/1   [146 chars]
    en  Less than the crop it will save in one year, which is the sum I should have done in the first week. I did it on the third day of building instead.
    >>  ............................................
    pt  Menos do que a lavoura que ela vai salvar em um ano, que é a conta que eu deveria ter feito na primeira semana. Fiz no terceiro dia de obra.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.pest_pressure.succeeded.counted/2   [176 chars]
    en  Half of it came off the windbreak I lost in the storm, so cheaper than it should have been. %2$s is fenced with the remains of its own shelter, which I find funny at odd hours.
    >>  ............................................
    pt  Metade veio do quebra-vento que perdi na tempestade, então saiu mais barato do que devia. %2$s está cercado com os restos do próprio abrigo, o que me diverte em horas estranhas.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.pest_pressure.succeeded.counted/3   [109 chars]
    en  Enough to notice. Not enough to have been the reason I waited, which is what makes waiting harder to explain.
    >>  ............................................
    pt  O bastante para notar. Não o bastante para ter sido o motivo da demora, o que torna a demora mais difícil de explicar.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · answers the beat(s) `work.farmer.pest_pressure.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.pest_pressure.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.pest_pressure.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.pest_pressure.succeeded.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você trabalhar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.scene.work.farmer.pest_pressure.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.left`: the villager accepts. Subject `work.farmer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.farmer.crop_failing.active.respond / leave; conversations.scene.work.farmer.crop_failing.blocked.respond / leave; conversations.scene.work.farmer.crop_failing.failed.respond / leave; conversations.scene.work.farmer.crop_failing.succeeded.respond / leave; conversations.scene.work.farmer.followup / leave; conversations.scene.work.farmer.pest_pressure.active.respond / leave; conversations.scene.work.farmer.price_dispute.blocked.respond / leave; conversations.scene.work.farmer.price_dispute.succeeded.respond / leave …and 7 more
```

> Written out in full under **`conversations.scene.work.farmer.crop_failing.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.farmer.price_dispute.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.farmer.price_dispute.blocked` — e.g. "%3$s has offered me the same for %2$s as last year, and last year was not a hard year. I have not said yes and I have not said no."


```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.price_dispute.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.farmer.price_dispute.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.farmer.price_dispute.blocked.respond   [16 chars]
    en  About the price.
    >>  ............................................
    pt  Sobre o preço.
    >>  ............................................
```


### Button `urge_ask` — "Ask for more. The worst they say is no."

*stance family `candor` · tone `plain` · outcome `accepted` · answers the beat(s) `work.farmer.price_dispute.blocked` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.farmer.price_dispute.blocked.urge_ask` — accepted phrasings: "ask for more the worst they say is no"; "you should ask for more"; "name a higher price"
  - the message must contain one of: `more`, `higher`, `ask`
  - scored words: `more`(1.8), `higher`(1.8), `ask`(1.8), `worst`(0.8), `say`(0.8), `should`(0.8), `name`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.price_dispute.blocked.respond.urge_ask
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.price_dispute.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.price_dispute.blocked.respond.urge_ask   [39 chars]
    en  Ask for more. The worst they say is no.
    >>  ............................................
    pt  Peça mais. O pior que dizem é não.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.farmer.trade_prices`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.price_dispute", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.farmer.price_dispute"}
- Then opens: `conversations.scene.work.farmer.followup`
- …where the player's next choices will be: "What's the hardest part of the work?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.price_dispute.blocked.resolved_to
WHO    VILLAGER — what the player reads after pressing "Ask for more. The worst they say is no."
       spoken on: conversations.scene.work.farmer.price_dispute.blocked.respond, button `urge_ask`
       leaves the player on: conversations.scene.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.price_dispute.blocked.resolved_to`: the villager accepts. Subject `work.farmer.trade_prices`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.price_dispute.blocked.resolved_to/1   [165 chars]
    en  The worst they say is no and then buy elsewhere for a season and come back. That is what I have been afraid of. But you are right, and I will ask %2$s on market day.
    >>  ............................................
    pt  O pior que dizem é não, e aí compram de outro por uma estação e voltam. É disso que eu tinha medo. Mas você tem razão, e vou falar com %2$s no dia de feira.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.price_dispute.blocked.resolved_to/2   [106 chars]
    en  Said plainly like that it sounds obvious. It has not been obvious from inside my own head for a fortnight.
    >>  ............................................
    pt  Dito assim, parece óbvio. De dentro da minha cabeça não parecia óbvio há quinze dias.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.price_dispute.blocked.resolved_to/3   [111 chars]
    en  Right. Market day. I will write the number down first so I cannot talk myself down while %2$s is looking at me.
    >>  ............................................
    pt  Certo. Dia de feira. Vou anotar o número antes, para não me convencer a baixar enquanto %2$s me encara.
    >>  ............................................
```


### Button `ask_worth` — "What do you think it's actually worth?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.farmer.price_dispute.blocked` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.farmer.price_dispute.blocked.ask_worth` — accepted phrasings: "what do you think its actually worth"; "what is it worth"; "what would be a fair price"
  - the message must contain one of: `worth`, `fair`, `price`
  - scored words: `worth`(1.8), `fair`(1.8), `price`(1.8), `think`(0.8), `its`(0.8), `actually`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.price_dispute.blocked.respond.ask_worth
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.price_dispute.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.price_dispute.blocked.respond.ask_worth   [38 chars]
    en  What do you think it's actually worth?
    >>  ............................................
    pt  Quanto você acha que vale de verdade?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.farmer.trade_prices`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.farmer.price_dispute"}
- Then opens: `conversations.scene.work.farmer.followup`
- …where the player's next choices will be: "What's the hardest part of the work?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.price_dispute.blocked.priced
WHO    VILLAGER — what the player reads after pressing "What do you think it's actually worth?"
       spoken on: conversations.scene.work.farmer.price_dispute.blocked.respond, button `ask_worth`
       leaves the player on: conversations.scene.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.price_dispute.blocked.priced`: the villager explains. Subject `work.farmer.trade_prices`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.price_dispute.blocked.priced/1   [141 chars]
    en  A third more than the offer, and I can show you the arithmetic. The arithmetic is not the hard part; saying the number to somebody's face is.
    >>  ............................................
    pt  Um terço a mais que a oferta, e posso te mostrar a conta. A conta não é a parte difícil; dizer o número na cara de alguém é.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.price_dispute.blocked.priced/2   [110 chars]
    en  Enough that I would not have to sell the last of %2$s in spring at whatever price spring feels like giving me.
    >>  ............................................
    pt  O bastante para eu não ter de vender o resto de %2$s na primavera pelo preço que a primavera resolver me dar.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.price_dispute.blocked.priced/3   [135 chars]
    en  More than last year and less than I would like. I do not want to be greedy about %2$s. I want to stop subsidising other people's bread.
    >>  ............................................
    pt  Mais que ano passado e menos do que eu gostaria. Não quero ser gananciosa com %2$s. Quero parar de bancar o pão dos outros.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · answers the beat(s) `work.farmer.price_dispute.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.price_dispute.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.price_dispute.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.price_dispute.blocked.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você trabalhar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.scene.work.farmer.price_dispute.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.left`: the villager accepts. Subject `work.farmer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.farmer.crop_failing.active.respond / leave; conversations.scene.work.farmer.crop_failing.blocked.respond / leave; conversations.scene.work.farmer.crop_failing.failed.respond / leave; conversations.scene.work.farmer.crop_failing.succeeded.respond / leave; conversations.scene.work.farmer.followup / leave; conversations.scene.work.farmer.pest_pressure.active.respond / leave; conversations.scene.work.farmer.pest_pressure.succeeded.respond / leave; conversations.scene.work.farmer.price_dispute.succeeded.respond / leave …and 7 more
```

> Written out in full under **`conversations.scene.work.farmer.crop_failing.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.farmer.price_dispute.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.farmer.price_dispute.succeeded` — e.g. "I asked %3$s. I got most of it — not all, but most — and %2$s is sold at a price I can say out loud without wincing."


```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.price_dispute.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.farmer.price_dispute.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.farmer.price_dispute.succeeded.respond   [16 chars]
    en  You asked, then.
    >>  ............................................
    pt  Então você pediu.
    >>  ............................................
```


### Button `well_done` — "That took more nerve than the asking looked like."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.farmer.price_dispute.succeeded` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.farmer.price_dispute.succeeded.well_done` — accepted phrasings: "that took more nerve than the asking looked like"; "that took nerve"; "well done for asking"
  - the message must contain one of: `nerve`, `courage`, `done`
  - scored words: `nerve`(1.8), `courage`(1.8), `done`(1.8), `took`(0.8), `more`(0.8), `than`(0.8), `asking`(0.8), `looked`(0.8), `like`(0.8), `well`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.price_dispute.succeeded.respond.well_done
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.price_dispute.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.price_dispute.succeeded.respond.well_done   [49 chars]
    en  That took more nerve than the asking looked like.
    >>  ............................................
    pt  Isso exigiu mais coragem do que parecia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.farmer.price_dispute.praise`, budget `standard`, replay policy `once`
- Does: disposition — warmth +2, respect +2  _(recorded under topic `work.farmer.trade_prices`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.farmer.price_dispute"}
- Then opens: `conversations.scene.work.farmer.followup`
- …where the player's next choices will be: "What's the hardest part of the work?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.price_dispute.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "That took more nerve than the asking looked like."
       spoken on: conversations.scene.work.farmer.price_dispute.succeeded.respond, button `well_done`
       leaves the player on: conversations.scene.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.price_dispute.succeeded.acknowledged`: the villager accepts. Subject `work.farmer.trade_prices`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.price_dispute.succeeded.acknowledged/1   [88 chars]
    en  It did. I rehearsed it to a fencepost. Do not tell anyone I rehearsed it to a fencepost.
    >>  ............................................
    pt  Exigiu. Ensaiei para um mourão de cerca. Não conte a ninguém que ensaiei para um mourão de cerca.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.price_dispute.succeeded.acknowledged/2   [105 chars]
    en  More than I would admit to most people. Thank you for noticing that it was the asking and not the number.
    >>  ............................................
    pt  Mais do que eu admitiria para a maioria. Obrigada por perceber que o difícil era pedir, não o número.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.price_dispute.succeeded.acknowledged/3   [91 chars]
    en  I nearly did not. I got halfway there and turned round once, and then I turned round again.
    >>  ............................................
    pt  Quase não fui. Cheguei na metade do caminho e voltei uma vez, e depois voltei de novo.
    >>  ............................................
```


### Button `ask_what_changed` — "What did they say when you named it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.farmer.price_dispute.succeeded` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.farmer.price_dispute.succeeded.ask_what_changed` — accepted phrasings: "what did they say when you named it"; "what did they say"; "how did they take it"
  - the message must contain one of: `say`, `said`, `take`
  - scored words: `say`(1.8), `said`(1.8), `take`(1.8), `when`(0.8), `named`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.price_dispute.succeeded.respond.ask_what_changed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.price_dispute.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.price_dispute.succeeded.respond.ask_what_changed   [36 chars]
    en  What did they say when you named it?
    >>  ............................................
    pt  O que disseram quando você falou o número?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.farmer.trade_prices`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.farmer.price_dispute"}
- Then opens: `conversations.scene.work.farmer.followup`
- …where the player's next choices will be: "What's the hardest part of the work?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.price_dispute.succeeded.recounted
WHO    VILLAGER — what the player reads after pressing "What did they say when you named it?"
       spoken on: conversations.scene.work.farmer.price_dispute.succeeded.respond, button `ask_what_changed`
       leaves the player on: conversations.scene.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.price_dispute.succeeded.recounted`: the villager reminisces. Subject `work.farmer.trade_prices`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.farmer.price_dispute.succeeded.recounted/1   [136 chars]
    en  Nothing, for long enough that I nearly took it back. Then %2$s said 'about time' and wrote it down, and I had to go and sit in the cart.
    >>  ............................................
    pt  Nada, por tempo suficiente para eu quase voltar atrás. Aí %2$s disse 'já não era sem tempo' e anotou, e eu tive de ir sentar na carroça.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.price_dispute.succeeded.recounted/2   [134 chars]
    en  %2$s said the price of everything else had moved and mine had not, in a tone that suggested this had been obvious to everybody but me.
    >>  ............................................
    pt  %2$s disse que o preço de tudo tinha mexido e o meu não, num tom que sugeria que isso era óbvio para todo mundo menos para mim.
    >>  ............................................
  dialogue.conversations.scene.work.farmer.price_dispute.succeeded.recounted/3   [148 chars]
    en  Haggled a little, out of habit I think. We landed close to my number. %2$s asked why I had waited two years to say it, and I did not have an answer.
    >>  ............................................
    pt  Pechinchou um pouco, por hábito, acho. Paramos perto do meu número. %2$s perguntou por que eu tinha esperado dois anos para falar, e eu não tinha resposta.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · answers the beat(s) `work.farmer.price_dispute.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.farmer.price_dispute.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.farmer.price_dispute.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.farmer.price_dispute.succeeded.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você trabalhar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.scene.work.farmer.price_dispute.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.left`: the villager accepts. Subject `work.farmer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.farmer.crop_failing.active.respond / leave; conversations.scene.work.farmer.crop_failing.blocked.respond / leave; conversations.scene.work.farmer.crop_failing.failed.respond / leave; conversations.scene.work.farmer.crop_failing.succeeded.respond / leave; conversations.scene.work.farmer.followup / leave; conversations.scene.work.farmer.pest_pressure.active.respond / leave; conversations.scene.work.farmer.pest_pressure.succeeded.respond / leave; conversations.scene.work.farmer.price_dispute.blocked.respond / leave …and 7 more
```

> Written out in full under **`conversations.scene.work.farmer.crop_failing.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.farmer.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.farmer.craft` — e.g. "My grandmother taught me to read soil with my hands. I still do it and I still feel foolish."


```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.farmer.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.farmer.craft.respond   [26 chars]
    en  That's how it got learned.
    >>  ............................................
    pt  Foi assim que se aprendeu.
    >>  ............................................
```


### Button `ask_teacher` — "What else did she teach you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.farmer.craft` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.farmer.craft.ask_teacher` — accepted phrasings: "what else did she teach you"
  - the message must contain one of: `else`, `taught`, `grandmother`
  - scored words: `else`(1.0), `taught`(1.5), `grandmother`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.craft.respond.ask_teacher
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.craft.respond.ask_teacher   [28 chars]
    en  What else did she teach you?
    >>  ............................................
    pt  O que mais ela te ensinou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.farmer.craft.ask_teacher`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.farmer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What happens if the harvest fails?" | "Good luck with the season."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.craft.ask_teacher
WHO    VILLAGER — what the player reads after pressing "What else did she teach you?"
       spoken on: conversations.topic.work.farmer.craft.respond, button `ask_teacher`
       leaves the player on: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.craft.ask_teacher`: the villager explains. Subject `work.farmer.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.farmer.craft.ask_teacher/1   [76 chars]
    en  To leave one row unweeded. She never said why and I've never dared find out.
    >>  ............................................
    pt  A deixar uma fileira sem capinar. Ela nunca disse por quê e eu nunca ousei descobrir.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.craft.ask_teacher/2   [54 chars]
    en  That a field forgives you once. Not twice, %1$s. Once.
    >>  ............................................
    pt  Que um campo te perdoa uma vez. Não duas, %1$s. Uma.
    >>  ............................................
```


### Button `admire` — "That's a skill, reading dirt."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.farmer.craft` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.farmer.craft.admire` — accepted phrasings: "that's a skill, reading dirt"
  - the message must contain one of: `skill`, `reading`, `dirt`
  - scored words: `skill`(1.5), `reading`(1.2), `dirt`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.craft.respond.admire   [29 chars]
    en  That's a skill, reading dirt.
    >>  ............................................
    pt  É uma habilidade, ler terra.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.farmer.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.farmer.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.farmer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What happens if the harvest fails?" | "Good luck with the season."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.craft.admire
WHO    VILLAGER — what the player reads after pressing "That's a skill, reading dirt."
       spoken on: conversations.topic.work.farmer.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.craft.admire`: the villager accepts. Subject `work.farmer.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.farmer.craft.admire/1   [66 chars]
    en  It's a skill nobody puts on a sign. I'll take it being called one.
    >>  ............................................
    pt  É uma habilidade que ninguém põe numa placa. Aceito ser chamada assim.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.craft.admire/2   [62 chars]
    en  Reading dirt. Ha. Say it like that again and I'll believe you.
    >>  ............................................
    pt  Ler terra. Ha. Diga assim de novo e eu acredito.
    >>  ............................................
```


### Button `ask_still_wrong` — "Do you ever get it wrong now?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.farmer.craft` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.farmer.craft.ask_still_wrong` — accepted phrasings: "do you ever get it wrong now"
  - the message must contain one of: `wrong`, `mistakes`
  - scored words: `wrong`(1.5), `mistakes`(1.5), `ever`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.craft.respond.ask_still_wrong
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.craft.respond.ask_still_wrong   [29 chars]
    en  Do you ever get it wrong now?
    >>  ............................................
    pt  Você ainda erra hoje em dia?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.farmer.craft.ask_still_wrong`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.farmer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What happens if the harvest fails?" | "Good luck with the season."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.craft.ask_still_wrong
WHO    VILLAGER — what the player reads after pressing "Do you ever get it wrong now?"
       spoken on: conversations.topic.work.farmer.craft.respond, button `ask_still_wrong`
       leaves the player on: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.craft.ask_still_wrong`: the villager explains. Subject `work.farmer.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.farmer.craft.ask_still_wrong/1   [72 chars]
    en  Every spring. The difference is I get it wrong earlier now, which helps.
    >>  ............................................
    pt  Toda primavera. A diferença é que agora eu erro mais cedo, o que ajuda.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.craft.ask_still_wrong/2   [59 chars]
    en  Twice this year. Once badly. I'll not tell you which field.
    >>  ............................................
    pt  Duas vezes este ano. Uma feio. Não vou dizer qual campo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the row."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.farmer.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.craft.respond.leave   [33 chars]
    en  I'll let you get back to the row.
    >>  ............................................
    pt  Vou deixar você voltar pra fileira.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the row."
       spoken on: conversations.topic.work.farmer.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.left`: the villager accepts. Subject `work.farmer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.farmer.crop_failing.active.respond / leave; conversations.scene.work.farmer.crop_failing.blocked.respond / leave; conversations.scene.work.farmer.crop_failing.failed.respond / leave; conversations.scene.work.farmer.crop_failing.succeeded.respond / leave; conversations.scene.work.farmer.followup / leave; conversations.scene.work.farmer.pest_pressure.active.respond / leave; conversations.scene.work.farmer.pest_pressure.succeeded.respond / leave; conversations.scene.work.farmer.price_dispute.blocked.respond / leave …and 7 more
```

> Written out in full under **`conversations.scene.work.farmer.crop_failing.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.farmer.followup`

**Reached from 20 route(s):** `conversations.scene.work.farmer.followup` / `ask_more`; `conversations.topic.work.farmer.craft.respond` / `ask_teacher`; `conversations.topic.work.farmer.craft.respond` / `admire`; `conversations.topic.work.farmer.craft.respond` / `ask_still_wrong`; `conversations.topic.work.farmer.future.respond` / `ask_what_stops`; `conversations.topic.work.farmer.future.respond` / `encourage`; `conversations.topic.work.farmer.future.respond` / `ask_father`; `conversations.topic.work.farmer.respond` / `ask_hard`; `conversations.topic.work.farmer.respond` / `value`; `conversations.topic.work.farmer.respond` / `challenge`; `conversations.topic.work.farmer.respond` / `challenge`; `conversations.topic.work.farmer.risk.respond` / `ask_often` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.farmer.challenge.landed` — e.g. "Anyone can. Then they come back in August wondering where the wheat went."
- `conversations.work.prof.farmer.challenge.stung` — e.g. "...Say that to the field, not to me. It's been arguing with me since March."
- `conversations.work.prof.farmer.craft.admire` — e.g. "It's a skill nobody puts on a sign. I'll take it being called one."
- `conversations.work.prof.farmer.craft.ask_still_wrong` — e.g. "Every spring. The difference is I get it wrong earlier now, which helps."
- `conversations.work.prof.farmer.craft.ask_teacher` — e.g. "To leave one row unweeded. She never said why and I've never dared find out."
- `conversations.work.prof.farmer.future.ask_father` — e.g. "Better at this than me and worse at everything else. I've the same balance."
- `conversations.work.prof.farmer.future.ask_what_stops` — e.g. "The fences. The market. The arguing. It's a closed sort of question, %1$s."
- `conversations.work.prof.farmer.future.encourage` — e.g. "...Would it. Huh. That arithmetic has never once been offered to me."
- `conversations.work.prof.farmer.hard` — e.g. "A morning a day, all summer. Multiply that out and you'll see why I'm short with people in July."
- `conversations.work.prof.farmer.risk.ask_often` — e.g. "Twice in twenty years. Which sounds rare until you've lived through one."
- `conversations.work.prof.farmer.risk.ask_prepare` — e.g. "Plant two kinds and hope they don't both fail. That's the whole of the strategy."
- `conversations.work.prof.farmer.risk.sympathise` — e.g. "...It is. Nobody puts it that way. They say 'good luck with the harvest'."
- `conversations.work.prof.farmer.task.ask_finish` — e.g. "Half of it. The other half will still be there tomorrow, patiently."
- `conversations.work.prof.farmer.task.ask_worst` — e.g. "Not by a distance. The worst one I do in the dark so nobody asks about it."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.farmer.followup   [47 chars]
    en  That's most of what there is to say about dirt.
    >>  ............................................
    pt  É quase tudo que dá pra dizer sobre terra.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.farmer.challenge.landed`, `work.farmer.challenge.stung`, `work.farmer.craft.admire`, `work.farmer.craft.ask_still_wrong`, `work.farmer.craft.ask_teacher`, `work.farmer.future.ask_father`, `work.farmer.future.ask_what_stops`, `work.farmer.future.encourage`, `work.farmer.hard`, `work.farmer.risk.ask_often`, `work.farmer.risk.ask_prepare`, `work.farmer.risk.sympathise`, `work.farmer.task.ask_finish`, `work.farmer.task.ask_worst`, `work.farmer.task.offer_hands`, `work.farmer.value`, `work.farmer.village.ask_price`, `work.farmer.village.ask_who`, `work.farmer.village.say_thanks` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.farmer.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `dirt`
  - scored words: `thought`(1.2), `way`(0.6), `dirt`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.farmer.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.farmer.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.farmer.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.farmer.thanks`: the villager accepts. Subject `work.farmer.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.farmer.thanks/1   [76 chars]
    en  Almost nobody does. Then they buy the bread and think it grew in the basket.
    >>  ............................................
    pt  Quase ninguém pensa. Aí compram o pão achando que nasceu na cesta.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.thanks/2   [67 chars]
    en  Nobody does until they've knelt in it, %1$s. Now you have, sort of.
    >>  ............................................
    pt  Ninguém pensa até ter ajoelhado nela, %1$s. Agora você meio que ajoelhou.
    >>  ............................................
```


### Button `ask_more` — "What happens if the harvest fails?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.farmer.challenge.landed`, `work.farmer.challenge.stung`, `work.farmer.craft.admire`, `work.farmer.craft.ask_still_wrong`, `work.farmer.craft.ask_teacher`, `work.farmer.future.ask_father`, `work.farmer.future.ask_what_stops`, `work.farmer.future.encourage`, `work.farmer.hard`, `work.farmer.risk.ask_often`, `work.farmer.risk.ask_prepare`, `work.farmer.risk.sympathise`, `work.farmer.task.ask_finish`, `work.farmer.task.ask_worst`, `work.farmer.task.offer_hands`, `work.farmer.value`, `work.farmer.village.ask_price`, `work.farmer.village.ask_who`, `work.farmer.village.say_thanks` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.farmer.more` — accepted phrasings: "what happens if the harvest fails"
  - the message must contain one of: `harvest`, `fails`, `winter`
  - scored words: `harvest`(1.5), `fails`(1.5), `winter`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.followup.ask_more   [34 chars]
    en  What happens if the harvest fails?
    >>  ............................................
    pt  O que acontece se a colheita falhar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.farmer.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.more
WHO    VILLAGER — what the player reads after pressing "What happens if the harvest fails?"
       spoken on: conversations.topic.work.farmer.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.farmer.more`: the villager discloses. Subject `work.farmer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.farmer.more/1   [80 chars]
    en  Then we eat the seed corn and pray twice as hard the year after. It's been done.
    >>  ............................................
    pt  Aí a gente come a semente e reza o dobro no ano seguinte. Já aconteceu.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.more/2   [84 chars]
    en  Then the village learns what a winter really is. I've seen it once. Once was plenty.
    >>  ............................................
    pt  Aí o vilarejo aprende o que é um inverno de verdade. Vi uma vez. Uma bastou.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.farmer.more/1
    en  Then we eat the seed corn and hope. I've done it once and I'd rather not describe that winter.
    >>  ............................................
    pt  Aí a gente come a semente e espera. Já fiz uma vez e prefiro não descrever aquele inverno.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner takes a tenth of the field every third year, and I count it every third year.
    >>  ............................................
    pt  O canto baixo leva um décimo do campo a cada três anos, e eu conto a cada três anos.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.farmer.more/1
    en  Then we eat the seed corn. It's an old answer to an old problem and it still works.
    >>  ............................................
    pt  Aí a gente come a semente. É uma resposta antiga a um problema antigo e ainda funciona.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner floods. It has always flooded. A ditch would fix it and there's no rush on ditches.
    >>  ............................................
    pt  O canto baixo alaga. Sempre alagou. Uma vala resolveria e não há pressa com valas.
    >>  ............................................
  confident.dialogue.conversations.work.prof.farmer.more/1
    en  Then we eat the seed corn and pray twice as hard the year after. It's been done.
    >>  ............................................
    pt  Aí a gente come a semente e reza o dobro no ano seguinte. Já aconteceu.
    >>  ............................................
  confident.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner floods every third year. I've asked for a ditch three times.
    >>  ............................................
    pt  O canto baixo alaga a cada três anos. Pedi uma vala três vezes.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.farmer.more/1
    en  Then we eat the seed corn and pray twice as hard the year after. It's been done.
    >>  ............................................
    pt  Aí a gente come a semente e reza o dobro no ano seguinte. Já aconteceu.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner floods every third year. I've asked for a ditch three times.
    >>  ............................................
    pt  O canto baixo alaga a cada três anos. Pedi uma vala três vezes.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.farmer.more/1
    en  Then we eat the seed corn, and the neighbours find reasons to bring things round. They always do.
    >>  ............................................
    pt  Aí a gente come a semente, e os vizinhos arranjam motivos pra trazer coisas. Sempre arranjam.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner floods. Come at the wet end of spring and you'll see exactly what I mean.
    >>  ............................................
    pt  O canto baixo alaga. Venha no fim molhado da primavera e você vai ver exatamente.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.farmer.more/1
    en  Then we eat the seed corn, and the neighbours find reasons to bring things round. They always do.
    >>  ............................................
    pt  Aí a gente come a semente, e os vizinhos arranjam motivos pra trazer coisas. Sempre arranjam.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner floods. Come at the wet end of spring and you'll see exactly what I mean.
    >>  ............................................
    pt  O canto baixo alaga. Venha no fim molhado da primavera e você vai ver exatamente.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.farmer.more/1
    en  Then we eat the seed corn, and the neighbours find reasons to bring things round. They always do.
    >>  ............................................
    pt  Aí a gente come a semente, e os vizinhos arranjam motivos pra trazer coisas. Sempre arranjam.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner floods. Come at the wet end of spring and you'll see exactly what I mean.
    >>  ............................................
    pt  O canto baixo alaga. Venha no fim molhado da primavera e você vai ver exatamente.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.farmer.more/1
    en  Then we eat the seed corn and hope. I've done it once and I'd rather not describe that winter.
    >>  ............................................
    pt  Aí a gente come a semente e espera. Já fiz uma vez e prefiro não descrever aquele inverno.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner takes a tenth of the field every third year, and I count it every third year.
    >>  ............................................
    pt  O canto baixo leva um décimo do campo a cada três anos, e eu conto a cada três anos.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.farmer.more/1
    en  Then we eat the seed corn and pray twice as hard the year after. It's been done.
    >>  ............................................
    pt  Aí a gente come a semente e reza o dobro no ano seguinte. Já aconteceu.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner floods every third year. I've asked for a ditch three times.
    >>  ............................................
    pt  O canto baixo alaga a cada três anos. Pedi uma vala três vezes.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.farmer.more/1
    en  Then we eat the seed corn and pray twice as hard the year after. It's been done.
    >>  ............................................
    pt  Aí a gente come a semente e reza o dobro no ano seguinte. Já aconteceu.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner floods every third year. I've asked for a ditch three times.
    >>  ............................................
    pt  O canto baixo alaga a cada três anos. Pedi uma vala três vezes.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.farmer.more/1
    en  Then we eat the seed corn. It's been done twice in my lifetime and once in my father's.
    >>  ............................................
    pt  Aí a gente come a semente. Aconteceu duas vezes na minha vida e uma na do meu pai.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner. Every third year, same fortnight, and nobody has ever wanted to hear about it.
    >>  ............................................
    pt  O canto baixo. A cada três anos, mesma quinzena, e ninguém nunca quis ouvir sobre isso.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.farmer.more/1
    en  Then we eat the seed corn. It's an old answer to an old problem and it still works.
    >>  ............................................
    pt  Aí a gente come a semente. É uma resposta antiga a um problema antigo e ainda funciona.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner floods. It has always flooded. A ditch would fix it and there's no rush on ditches.
    >>  ............................................
    pt  O canto baixo alaga. Sempre alagou. Uma vala resolveria e não há pressa com valas.
    >>  ............................................
  odd.dialogue.conversations.work.prof.farmer.more/1
    en  Then we eat the seed corn. It's been done twice in my lifetime and once in my father's.
    >>  ............................................
    pt  Aí a gente come a semente. Aconteceu duas vezes na minha vida e uma na do meu pai.
    >>  ............................................
  odd.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner. Every third year, same fortnight, and nobody has ever wanted to hear about it.
    >>  ............................................
    pt  O canto baixo. A cada três anos, mesma quinzena, e ninguém nunca quis ouvir sobre isso.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.farmer.more/1
    en  Then we eat the seed corn. It's an old answer to an old problem and it still works.
    >>  ............................................
    pt  Aí a gente come a semente. É uma resposta antiga a um problema antigo e ainda funciona.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner floods. It has always flooded. A ditch would fix it and there's no rush on ditches.
    >>  ............................................
    pt  O canto baixo alaga. Sempre alagou. Uma vala resolveria e não há pressa com valas.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.farmer.more/1
    en  We eat the seed corn and pray twice as hard! It has been done and I'd rather not do it again.
    >>  ............................................
    pt  A gente come a semente e reza o dobro! Já aconteceu e eu preferia não repetir.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner floods every third year. Every third year! You could set a calendar by it.
    >>  ............................................
    pt  O canto baixo alaga a cada três anos. A cada três anos! Dá pra acertar o calendário.
    >>  ............................................
  playful.dialogue.conversations.work.prof.farmer.more/1
    en  We eat the seed corn and pray twice as hard! It has been done and I'd rather not do it again.
    >>  ............................................
    pt  A gente come a semente e reza o dobro! Já aconteceu e eu preferia não repetir.
    >>  ............................................
  playful.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner floods every third year. Every third year! You could set a calendar by it.
    >>  ............................................
    pt  O canto baixo alaga a cada três anos. A cada três anos! Dá pra acertar o calendário.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.farmer.more/1
    en  Then we eat the seed corn. It's an old answer to an old problem and it still works.
    >>  ............................................
    pt  Aí a gente come a semente. É uma resposta antiga a um problema antigo e ainda funciona.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner floods. It has always flooded. A ditch would fix it and there's no rush on ditches.
    >>  ............................................
    pt  O canto baixo alaga. Sempre alagou. Uma vala resolveria e não há pressa com valas.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.farmer.more/1
    en  Then we eat the seed corn and hope. I've done it once and I'd rather not describe that winter.
    >>  ............................................
    pt  Aí a gente come a semente e espera. Já fiz uma vez e prefiro não descrever aquele inverno.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner takes a tenth of the field every third year, and I count it every third year.
    >>  ............................................
    pt  O canto baixo leva um décimo do campo a cada três anos, e eu conto a cada três anos.
    >>  ............................................
  shy.dialogue.conversations.work.prof.farmer.more/1
    en  Then we eat the seed corn. It's been done twice in my lifetime and once in my father's.
    >>  ............................................
    pt  Aí a gente come a semente. Aconteceu duas vezes na minha vida e uma na do meu pai.
    >>  ............................................
  shy.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner. Every third year, same fortnight, and nobody has ever wanted to hear about it.
    >>  ............................................
    pt  O canto baixo. A cada três anos, mesma quinzena, e ninguém nunca quis ouvir sobre isso.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.farmer.more/1
    en  We eat the seed corn and pray twice as hard! It has been done and I'd rather not do it again.
    >>  ............................................
    pt  A gente come a semente e reza o dobro! Já aconteceu e eu preferia não repetir.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner floods every third year. Every third year! You could set a calendar by it.
    >>  ............................................
    pt  O canto baixo alaga a cada três anos. A cada três anos! Dá pra acertar o calendário.
    >>  ............................................
  witty.dialogue.conversations.work.prof.farmer.more/1
    en  We eat the seed corn and pray twice as hard! It has been done and I'd rather not do it again.
    >>  ............................................
    pt  A gente come a semente e reza o dobro! Já aconteceu e eu preferia não repetir.
    >>  ............................................
  witty.dialogue.conversations.work.prof.farmer.more/2
    en  The low corner floods every third year. Every third year! You could set a calendar by it.
    >>  ............................................
    pt  O canto baixo alaga a cada três anos. A cada três anos! Dá pra acertar o calendário.
    >>  ............................................
```

</details>


### Button `leave` — "Good luck with the season."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.farmer.challenge.landed`, `work.farmer.challenge.stung`, `work.farmer.craft.admire`, `work.farmer.craft.ask_still_wrong`, `work.farmer.craft.ask_teacher`, `work.farmer.future.ask_father`, `work.farmer.future.ask_what_stops`, `work.farmer.future.encourage`, `work.farmer.hard`, `work.farmer.risk.ask_often`, `work.farmer.risk.ask_prepare`, `work.farmer.risk.sympathise`, `work.farmer.task.ask_finish`, `work.farmer.task.ask_worst`, `work.farmer.task.offer_hands`, `work.farmer.value`, `work.farmer.village.ask_price`, `work.farmer.village.ask_who`, `work.farmer.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.followup.leave   [26 chars]
    en  Good luck with the season.
    >>  ............................................
    pt  Boa sorte com a estação.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.leave
WHO    VILLAGER — what the player reads after pressing "Good luck with the season."
       spoken on: conversations.topic.work.farmer.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.left`: the villager accepts. Subject `work.farmer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.farmer.crop_failing.active.respond / leave; conversations.scene.work.farmer.crop_failing.blocked.respond / leave; conversations.scene.work.farmer.crop_failing.failed.respond / leave; conversations.scene.work.farmer.crop_failing.succeeded.respond / leave; conversations.scene.work.farmer.followup / leave; conversations.scene.work.farmer.pest_pressure.active.respond / leave; conversations.scene.work.farmer.pest_pressure.succeeded.respond / leave; conversations.scene.work.farmer.price_dispute.blocked.respond / leave …and 7 more
```

> Written out in full under **`conversations.scene.work.farmer.crop_failing.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.farmer.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.farmer.future` — e.g. "I'd like one season where I only farm. No fences, no market, no arguing. Just the field."


```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.farmer.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.farmer.future.respond   [25 chars]
    en  That's the far end of it.
    >>  ............................................
    pt  É o extremo da coisa.
    >>  ............................................
```


### Button `ask_what_stops` — "What's in the way of it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.farmer.future` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.farmer.future.ask_what_stops` — accepted phrasings: "what's in the way of it"
  - the message must contain one of: `prevents`, `obstacle`
  - scored words: `way`(0.8), `prevents`(1.5), `obstacle`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.future.respond.ask_what_stops
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.future.respond.ask_what_stops   [24 chars]
    en  What's in the way of it?
    >>  ............................................
    pt  O que está no caminho?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.farmer.future.ask_what_stops`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.farmer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What happens if the harvest fails?" | "Good luck with the season."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.future.ask_what_stops
WHO    VILLAGER — what the player reads after pressing "What's in the way of it?"
       spoken on: conversations.topic.work.farmer.future.respond, button `ask_what_stops`
       leaves the player on: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.future.ask_what_stops`: the villager explains. Subject `work.farmer.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.farmer.future.ask_what_stops/1   [74 chars]
    en  The fences. The market. The arguing. It's a closed sort of question, %1$s.
    >>  ............................................
    pt  As cercas. O mercado. As discussões. É uma pergunta meio fechada, %1$s.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.future.ask_what_stops/2   [61 chars]
    en  Nothing, some years. That's the part I don't like to look at.
    >>  ............................................
    pt  Nada, em alguns anos. É essa a parte que eu não gosto de olhar.
    >>  ............................................
```


### Button `encourage` — "Take the season. The village will manage."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.farmer.future` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.farmer.future.encourage` — accepted phrasings: "take the season. the village will manage"
  - the message must contain one of: `season`, `manage`
  - scored words: `season`(1.2), `manage`(1.5), `take`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.future.respond.encourage   [41 chars]
    en  Take the season. The village will manage.
    >>  ............................................
    pt  Tire a estação. O vilarejo se vira.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.farmer.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.farmer.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.farmer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What happens if the harvest fails?" | "Good luck with the season."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.future.encourage
WHO    VILLAGER — what the player reads after pressing "Take the season. The village will manage."
       spoken on: conversations.topic.work.farmer.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.future.encourage`: the villager accepts. Subject `work.farmer.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.farmer.future.encourage/1   [68 chars]
    en  ...Would it. Huh. That arithmetic has never once been offered to me.
    >>  ............................................
    pt  ...Será que se vira. Huh. Ninguém nunca me ofereceu essa conta.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.future.encourage/2   [77 chars]
    en  The village would manage and I'd be unbearable about it afterwards. Tempting.
    >>  ............................................
    pt  O vilarejo se viraria e eu ficaria insuportável depois. Tentador.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it. I've never let myself do that arithmetic in case it came out yes.
    >>  ............................................
    pt  ...Será. Nunca me deixei fazer essa conta, caso desse sim.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  The village would manage without me. That's the part I'd have to sit with.
    >>  ............................................
    pt  O vilarejo se viraria sem mim. É essa parte que eu teria que aguentar.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it. Forty harvests and nobody has offered me that arithmetic.
    >>  ............................................
    pt  ...Será. Quarenta colheitas e ninguém me ofereceu essa conta.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  The village would manage. It managed before me and it will after; that's the comfort.
    >>  ............................................
    pt  O vilarejo se viraria. Se virou antes de mim e vai se virar depois; é o consolo.
    >>  ............................................
  confident.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it. Huh. That arithmetic has never been offered to me.
    >>  ............................................
    pt  ...Será. Hm. Nunca me ofereceram essa conta.
    >>  ............................................
  confident.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  The village would manage and I'd be unbearable about it afterwards. Tempting.
    >>  ............................................
    pt  O vilarejo se viraria e eu seria insuportável depois. Tentador.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it. Huh. That arithmetic has never been offered to me.
    >>  ............................................
    pt  ...Será. Hm. Nunca me ofereceram essa conta.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  The village would manage and I'd be unbearable about it afterwards. Tempting.
    >>  ............................................
    pt  O vilarejo se viraria e eu seria insuportável depois. Tentador.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it, %1$s. That arithmetic has never been offered to me before.
    >>  ............................................
    pt  ...Será, %1$s. Nunca me ofereceram essa conta antes.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  The village would manage. I'd be unbearable about it afterwards and you'd hear it all.
    >>  ............................................
    pt  O vilarejo se viraria. Eu seria insuportável depois e você ouviria tudo.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it, %1$s. That arithmetic has never been offered to me before.
    >>  ............................................
    pt  ...Será, %1$s. Nunca me ofereceram essa conta antes.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  The village would manage. I'd be unbearable about it afterwards and you'd hear it all.
    >>  ............................................
    pt  O vilarejo se viraria. Eu seria insuportável depois e você ouviria tudo.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it, %1$s. That arithmetic has never been offered to me before.
    >>  ............................................
    pt  ...Será, %1$s. Nunca me ofereceram essa conta antes.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  The village would manage. I'd be unbearable about it afterwards and you'd hear it all.
    >>  ............................................
    pt  O vilarejo se viraria. Eu seria insuportável depois e você ouviria tudo.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it. I've never let myself do that arithmetic in case it came out yes.
    >>  ............................................
    pt  ...Será. Nunca me deixei fazer essa conta, caso desse sim.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  The village would manage without me. That's the part I'd have to sit with.
    >>  ............................................
    pt  O vilarejo se viraria sem mim. É essa parte que eu teria que aguentar.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it. Huh. That arithmetic has never been offered to me.
    >>  ............................................
    pt  ...Será. Hm. Nunca me ofereceram essa conta.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  The village would manage and I'd be unbearable about it afterwards. Tempting.
    >>  ............................................
    pt  O vilarejo se viraria e eu seria insuportável depois. Tentador.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it. Huh. That arithmetic has never been offered to me.
    >>  ............................................
    pt  ...Será. Hm. Nunca me ofereceram essa conta.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  The village would manage and I'd be unbearable about it afterwards. Tempting.
    >>  ............................................
    pt  O vilarejo se viraria e eu seria insuportável depois. Tentador.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it. Nobody's done that sum for me.
    >>  ............................................
    pt  ...Será. Ninguém fez essa conta pra mim.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  They'd manage. I'd be unbearable after.
    >>  ............................................
    pt  Eles se virariam. Eu seria insuportável depois.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it. Forty harvests and nobody has offered me that arithmetic.
    >>  ............................................
    pt  ...Será. Quarenta colheitas e ninguém me ofereceu essa conta.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  The village would manage. It managed before me and it will after; that's the comfort.
    >>  ............................................
    pt  O vilarejo se viraria. Se virou antes de mim e vai se virar depois; é o consolo.
    >>  ............................................
  odd.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it. Nobody's done that sum for me.
    >>  ............................................
    pt  ...Será. Ninguém fez essa conta pra mim.
    >>  ............................................
  odd.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  They'd manage. I'd be unbearable after.
    >>  ............................................
    pt  Eles se virariam. Eu seria insuportável depois.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it. Forty harvests and nobody has offered me that arithmetic.
    >>  ............................................
    pt  ...Será. Quarenta colheitas e ninguém me ofereceu essa conta.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  The village would manage. It managed before me and it will after; that's the comfort.
    >>  ............................................
    pt  O vilarejo se viraria. Se virou antes de mim e vai se virar depois; é o consolo.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it! Nobody has ever done that arithmetic in front of me. Not once.
    >>  ............................................
    pt  ...Será! Ninguém nunca fez essa conta na minha frente. Nem uma vez.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  The village would manage, and I'd be insufferable about it afterwards. Tempting.
    >>  ............................................
    pt  O vilarejo se viraria, e eu seria insuportável depois. Tentador.
    >>  ............................................
  playful.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it! Nobody has ever done that arithmetic in front of me. Not once.
    >>  ............................................
    pt  ...Será! Ninguém nunca fez essa conta na minha frente. Nem uma vez.
    >>  ............................................
  playful.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  The village would manage, and I'd be insufferable about it afterwards. Tempting.
    >>  ............................................
    pt  O vilarejo se viraria, e eu seria insuportável depois. Tentador.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it. Forty harvests and nobody has offered me that arithmetic.
    >>  ............................................
    pt  ...Será. Quarenta colheitas e ninguém me ofereceu essa conta.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  The village would manage. It managed before me and it will after; that's the comfort.
    >>  ............................................
    pt  O vilarejo se viraria. Se virou antes de mim e vai se virar depois; é o consolo.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it. I've never let myself do that arithmetic in case it came out yes.
    >>  ............................................
    pt  ...Será. Nunca me deixei fazer essa conta, caso desse sim.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  The village would manage without me. That's the part I'd have to sit with.
    >>  ............................................
    pt  O vilarejo se viraria sem mim. É essa parte que eu teria que aguentar.
    >>  ............................................
  shy.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it. Nobody's done that sum for me.
    >>  ............................................
    pt  ...Será. Ninguém fez essa conta pra mim.
    >>  ............................................
  shy.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  They'd manage. I'd be unbearable after.
    >>  ............................................
    pt  Eles se virariam. Eu seria insuportável depois.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it! Nobody has ever done that arithmetic in front of me. Not once.
    >>  ............................................
    pt  ...Será! Ninguém nunca fez essa conta na minha frente. Nem uma vez.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  The village would manage, and I'd be insufferable about it afterwards. Tempting.
    >>  ............................................
    pt  O vilarejo se viraria, e eu seria insuportável depois. Tentador.
    >>  ............................................
  witty.dialogue.conversations.work.prof.farmer.future.encourage/1
    en  ...Would it! Nobody has ever done that arithmetic in front of me. Not once.
    >>  ............................................
    pt  ...Será! Ninguém nunca fez essa conta na minha frente. Nem uma vez.
    >>  ............................................
  witty.dialogue.conversations.work.prof.farmer.future.encourage/2
    en  The village would manage, and I'd be insufferable about it afterwards. Tempting.
    >>  ............................................
    pt  O vilarejo se viraria, e eu seria insuportável depois. Tentador.
    >>  ............................................
```

</details>


### Button `ask_father` — "What sort of farmer was he?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.farmer.future` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.farmer.future.ask_father` — accepted phrasings: "what sort of farmer was he"
  - the message must contain one of: `sort`, `farmer`
  - scored words: `sort`(1.2), `farmer`(1.5), `was`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.future.respond.ask_father
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.future.respond.ask_father   [27 chars]
    en  What sort of farmer was he?
    >>  ............................................
    pt  Que tipo de agricultor ele era?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.farmer.future.ask_father`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.farmer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What happens if the harvest fails?" | "Good luck with the season."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.future.ask_father
WHO    VILLAGER — what the player reads after pressing "What sort of farmer was he?"
       spoken on: conversations.topic.work.farmer.future.respond, button `ask_father`
       leaves the player on: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.future.ask_father`: the villager explains. Subject `work.farmer.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.farmer.future.ask_father/1   [75 chars]
    en  Better at this than me and worse at everything else. I've the same balance.
    >>  ............................................
    pt  Melhor nisso que eu e pior em todo o resto. Eu tenho o mesmo equilíbrio.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.future.ask_father/2   [77 chars]
    en  Quiet. He talked to the field and not to us, and I've caught myself doing it.
    >>  ............................................
    pt  Quieto. Falava com o campo e não com a gente, e eu já me peguei fazendo isso.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the row."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.farmer.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.future.respond.leave   [33 chars]
    en  I'll let you get back to the row.
    >>  ............................................
    pt  Vou deixar você voltar pra fileira.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the row."
       spoken on: conversations.topic.work.farmer.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.left`: the villager accepts. Subject `work.farmer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.farmer.crop_failing.active.respond / leave; conversations.scene.work.farmer.crop_failing.blocked.respond / leave; conversations.scene.work.farmer.crop_failing.failed.respond / leave; conversations.scene.work.farmer.crop_failing.succeeded.respond / leave; conversations.scene.work.farmer.followup / leave; conversations.scene.work.farmer.pest_pressure.active.respond / leave; conversations.scene.work.farmer.pest_pressure.succeeded.respond / leave; conversations.scene.work.farmer.price_dispute.blocked.respond / leave …and 7 more
```

> Written out in full under **`conversations.scene.work.farmer.crop_failing.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.farmer.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.farmer` — e.g. "Farming's honest: you plant, you pray, you pull weeds. The weeds always negotiate hardest."


```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.farmer.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.farmer.respond   [34 chars]
    en  That's the trade, when it behaves.
    >>  ............................................
    pt  É o ofício, quando ele colabora.
    >>  ............................................
```


### Button `ask_hard` — "What do the weeds actually cost you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.farmer.identity` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.farmer.hard` — accepted phrasings: "what do the weeds actually cost you"
  - the message must contain one of: `weeds`, `cost`, `worst`
  - scored words: `weeds`(1.5), `cost`(1.2), `worst`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.respond.ask_hard   [36 chars]
    en  What do the weeds actually cost you?
    >>  ............................................
    pt  Quanto as ervas daninhas custam de verdade?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.farmer.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.farmer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What happens if the harvest fails?" | "Good luck with the season."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.hard
WHO    VILLAGER — what the player reads after pressing "What do the weeds actually cost you?"
       spoken on: conversations.topic.work.farmer.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.hard`: the villager explains. Subject `work.farmer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.farmer.followup / ask_more
```

> Written out in full under **`conversations.scene.work.farmer.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "The village eats because you get up first."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.farmer.identity` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.farmer.value` — accepted phrasings: "the village eats because you get up first"
  - the message must contain one of: `eats`, `bread`, `feed`
  - scored words: `eats`(1.5), `bread`(1.2), `feed`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.respond.value   [42 chars]
    en  The village eats because you get up first.
    >>  ............................................
    pt  O vilarejo come porque você levanta primeiro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.farmer.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.farmer.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.farmer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What happens if the harvest fails?" | "Good luck with the season."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.value
WHO    VILLAGER — what the player reads after pressing "The village eats because you get up first."
       spoken on: conversations.topic.work.farmer.respond, button `value`
       leaves the player on: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.value`: the villager accepts. Subject `work.farmer.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.farmer.value/1   [77 chars]
    en  Somebody does. It may as well be the one who likes the smell of turned earth.
    >>  ............................................
    pt  Alguém tem que levantar. Que seja quem gosta do cheiro de terra revirada.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.value/2   [68 chars]
    en  That's the deal, aye. I get the mud and everyone else gets the loaf.
    >>  ............................................
    pt  É o acordo, sim. Eu fico com a lama e todo mundo fica com o pão.
    >>  ............................................
```


### Button `challenge` — "Anyone can put a seed in the ground."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.farmer.identity` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.farmer.challenge` — accepted phrasings: "anyone can put a seed in the ground"
  - the message must contain one of: `seed`, `ground`
  - scored words: `seed`(1.5), `ground`(1.2), `anyone`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.respond.challenge   [36 chars]
    en  Anyone can put a seed in the ground.
    >>  ............................................
    pt  Qualquer um planta uma semente.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.farmer.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.farmer.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.farmer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What happens if the harvest fails?" | "Good luck with the season."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.challenge.landed
WHO    VILLAGER — what the player reads after pressing "Anyone can put a seed in the ground."
       spoken on: conversations.topic.work.farmer.respond, button `challenge`
       leaves the player on: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.challenge.landed`: the villager resists. Subject `work.farmer.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.farmer.challenge.landed/1   [73 chars]
    en  Anyone can. Then they come back in August wondering where the wheat went.
    >>  ............................................
    pt  Qualquer um planta. Aí voltam em agosto se perguntando cadê o trigo.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.challenge.landed/2   [59 chars]
    en  True enough. Putting it in is the easy quarter of it, %1$s.
    >>  ............................................
    pt  Verdade. Plantar é o quarto fácil da coisa, %1$s.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.farmer.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.farmer.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.farmer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What happens if the harvest fails?" | "Good luck with the season."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.challenge.stung
WHO    VILLAGER — what the player reads after pressing "Anyone can put a seed in the ground."
       spoken on: conversations.topic.work.farmer.respond, button `challenge`
       leaves the player on: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.challenge.stung`: the villager resists. Subject `work.farmer.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.farmer.challenge.stung/1   [75 chars]
    en  ...Say that to the field, not to me. It's been arguing with me since March.
    >>  ............................................
    pt  ...Diga isso pro campo, não pra mim. Ele discute comigo desde março.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.challenge.stung/2   [64 chars]
    en  Anyone can. Nobody does. That difference is my whole life, %1$s.
    >>  ............................................
    pt  Qualquer um planta. Ninguém planta. Essa diferença é minha vida inteira, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the row."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.farmer.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.respond.leave   [33 chars]
    en  I'll let you get back to the row.
    >>  ............................................
    pt  Vou deixar você voltar pra fileira.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the row."
       spoken on: conversations.topic.work.farmer.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.left`: the villager accepts. Subject `work.farmer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.farmer.crop_failing.active.respond / leave; conversations.scene.work.farmer.crop_failing.blocked.respond / leave; conversations.scene.work.farmer.crop_failing.failed.respond / leave; conversations.scene.work.farmer.crop_failing.succeeded.respond / leave; conversations.scene.work.farmer.followup / leave; conversations.scene.work.farmer.pest_pressure.active.respond / leave; conversations.scene.work.farmer.pest_pressure.succeeded.respond / leave; conversations.scene.work.farmer.price_dispute.blocked.respond / leave …and 7 more
```

> Written out in full under **`conversations.scene.work.farmer.crop_failing.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.farmer.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.farmer.risk` — e.g. "One bad fortnight in August and the whole year's a loss. That's the arithmetic of it."


```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.farmer.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.farmer.risk.respond   [24 chars]
    en  That's what's out there.
    >>  ............................................
    pt  É isso que existe por aí.
    >>  ............................................
```


### Button `ask_often` — "How often does it actually happen?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.farmer.risk` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.farmer.risk.ask_often` — accepted phrasings: "how often does it actually happen"
  - the message must contain one of: `often`, `happen`, `frequently`
  - scored words: `often`(1.5), `happen`(1.0), `frequently`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.risk.respond.ask_often
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.risk.respond.ask_often   [34 chars]
    en  How often does it actually happen?
    >>  ............................................
    pt  Com que frequência isso acontece de verdade?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.farmer.risk.ask_often`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.farmer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What happens if the harvest fails?" | "Good luck with the season."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.risk.ask_often
WHO    VILLAGER — what the player reads after pressing "How often does it actually happen?"
       spoken on: conversations.topic.work.farmer.risk.respond, button `ask_often`
       leaves the player on: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.risk.ask_often`: the villager explains. Subject `work.farmer.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.farmer.risk.ask_often/1   [72 chars]
    en  Twice in twenty years. Which sounds rare until you've lived through one.
    >>  ............................................
    pt  Duas vezes em vinte anos. Parece raro até você viver uma.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.risk.ask_often/2   [65 chars]
    en  Often enough that I count the days in August and pretend I'm not.
    >>  ............................................
    pt  Frequente o bastante pra eu contar os dias de agosto fingindo que não conto.
    >>  ............................................
```


### Button `sympathise` — "That's a lot to carry every August."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.farmer.risk` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.farmer.risk.sympathise` — accepted phrasings: "that's a lot to carry every august"
  - the message must contain one of: `carry`, `august`, `weight`
  - scored words: `carry`(1.5), `august`(1.2), `weight`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.risk.respond.sympathise   [35 chars]
    en  That's a lot to carry every August.
    >>  ............................................
    pt  É muita coisa pra carregar todo agosto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.farmer.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.farmer.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.farmer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What happens if the harvest fails?" | "Good luck with the season."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "That's a lot to carry every August."
       spoken on: conversations.topic.work.farmer.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.risk.sympathise`: the villager accepts. Subject `work.farmer.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.farmer.risk.sympathise/1   [73 chars]
    en  ...It is. Nobody puts it that way. They say 'good luck with the harvest'.
    >>  ............................................
    pt  ...É. Ninguém coloca assim. Dizem 'boa sorte na colheita'.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.risk.sympathise/2   [76 chars]
    en  Every August, aye. And every August I forget by October, which is the trick.
    >>  ............................................
    pt  Todo agosto, sim. E todo outubro eu esqueço, que é o truque.
    >>  ............................................
```


### Button `ask_prepare` — "Can you do anything about it beforehand?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.farmer.risk` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.farmer.risk.ask_prepare` — accepted phrasings: "can you do anything about it beforehand"
  - the message must contain one of: `beforehand`, `prepare`, `prevent`
  - scored words: `beforehand`(1.5), `prepare`(1.5), `prevent`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.risk.respond.ask_prepare
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.risk.respond.ask_prepare   [40 chars]
    en  Can you do anything about it beforehand?
    >>  ............................................
    pt  Dá pra fazer alguma coisa antes?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.farmer.risk.ask_prepare`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.farmer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What happens if the harvest fails?" | "Good luck with the season."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.risk.ask_prepare
WHO    VILLAGER — what the player reads after pressing "Can you do anything about it beforehand?"
       spoken on: conversations.topic.work.farmer.risk.respond, button `ask_prepare`
       leaves the player on: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.risk.ask_prepare`: the villager explains. Subject `work.farmer.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.farmer.risk.ask_prepare/1   [80 chars]
    en  Plant two kinds and hope they don't both fail. That's the whole of the strategy.
    >>  ............................................
    pt  Plantar dois tipos e torcer pra que os dois não falhem. É toda a estratégia.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.risk.ask_prepare/2   [57 chars]
    en  Watch. That's it. Watch, and move fast the day it starts.
    >>  ............................................
    pt  Observar. É isso. Observar, e agir rápido no dia em que começa.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the row."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.farmer.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.risk.respond.leave   [33 chars]
    en  I'll let you get back to the row.
    >>  ............................................
    pt  Vou deixar você voltar pra fileira.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the row."
       spoken on: conversations.topic.work.farmer.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.left`: the villager accepts. Subject `work.farmer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.farmer.crop_failing.active.respond / leave; conversations.scene.work.farmer.crop_failing.blocked.respond / leave; conversations.scene.work.farmer.crop_failing.failed.respond / leave; conversations.scene.work.farmer.crop_failing.succeeded.respond / leave; conversations.scene.work.farmer.followup / leave; conversations.scene.work.farmer.pest_pressure.active.respond / leave; conversations.scene.work.farmer.pest_pressure.succeeded.respond / leave; conversations.scene.work.farmer.price_dispute.blocked.respond / leave …and 7 more
```

> Written out in full under **`conversations.scene.work.farmer.crop_failing.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.farmer.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.farmer.task` — e.g. "Second field today. It's all thistle and opinions down there."


```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.farmer.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.farmer.task.respond   [21 chars]
    en  That's today, anyway.
    >>  ............................................
    pt  É o de hoje, enfim.
    >>  ............................................
```


### Button `ask_finish` — "Will you get it done before dark?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.farmer.task` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.farmer.task.ask_finish` — accepted phrasings: "will you get it done before dark"
  - the message must contain one of: `dark`, `finish`
  - scored words: `dark`(1.2), `finish`(1.5), `today`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.task.respond.ask_finish
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.task.respond.ask_finish   [33 chars]
    en  Will you get it done before dark?
    >>  ............................................
    pt  Você termina antes de escurecer?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.farmer.task.ask_finish`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.farmer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What happens if the harvest fails?" | "Good luck with the season."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.task.ask_finish
WHO    VILLAGER — what the player reads after pressing "Will you get it done before dark?"
       spoken on: conversations.topic.work.farmer.task.respond, button `ask_finish`
       leaves the player on: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.task.ask_finish`: the villager explains. Subject `work.farmer.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.farmer.task.ask_finish/1   [67 chars]
    en  Half of it. The other half will still be there tomorrow, patiently.
    >>  ............................................
    pt  Metade. A outra metade vai continuar lá amanhã, pacientemente.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.task.ask_finish/2   [44 chars]
    en  No. That's not the same as not trying, mind.
    >>  ............................................
    pt  Não. Mas isso não é o mesmo que não tentar.
    >>  ............................................
```


### Button `offer_hands` — "I've two hands going spare."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.farmer.task` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.farmer.task.offer_hands` — accepted phrasings: "i've two hands going spare"
  - the message must contain one of: `hands`, `spare`
  - scored words: `hands`(1.5), `spare`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.task.respond.offer_hands   [27 chars]
    en  I've two hands going spare.
    >>  ............................................
    pt  Tenho duas mãos sobrando.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.farmer.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.farmer.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.farmer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What happens if the harvest fails?" | "Good luck with the season."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I've two hands going spare."
       spoken on: conversations.topic.work.farmer.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.task.offer_hands`: the villager accepts. Subject `work.farmer.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.farmer.task.offer_hands/1   [62 chars]
    en  ...Have you. Right — the far end, and don't be gentle with it.
    >>  ............................................
    pt  ...Tem é? Certo — a ponta lá do fundo, e não seja delicado com ela.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.task.offer_hands/2   [71 chars]
    en  Then take the other side of the post and we'll be done by supper, %1$s.
    >>  ............................................
    pt  Então segure o outro lado do poste e a gente termina antes do jantar, %1$s.
    >>  ............................................
```


### Button `ask_worst` — "Is that the worst job on the list?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.farmer.task` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.farmer.task.ask_worst` — accepted phrasings: "is that the worst job on the list"
  - the message must contain one of: `worst`, `list`
  - scored words: `worst`(1.5), `list`(1.2), `job`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.task.respond.ask_worst
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.task.respond.ask_worst   [34 chars]
    en  Is that the worst job on the list?
    >>  ............................................
    pt  Esse é o pior trabalho da lista?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.farmer.task.ask_worst`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.farmer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What happens if the harvest fails?" | "Good luck with the season."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.task.ask_worst
WHO    VILLAGER — what the player reads after pressing "Is that the worst job on the list?"
       spoken on: conversations.topic.work.farmer.task.respond, button `ask_worst`
       leaves the player on: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.task.ask_worst`: the villager explains. Subject `work.farmer.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.farmer.task.ask_worst/1   [74 chars]
    en  Not by a distance. The worst one I do in the dark so nobody asks about it.
    >>  ............................................
    pt  Nem de longe. O pior eu faço no escuro pra ninguém perguntar.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.task.ask_worst/2   [66 chars]
    en  It's the one I do first, which tells you something about the rest.
    >>  ............................................
    pt  É o que eu faço primeiro, o que diz alguma coisa sobre o resto.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the row."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.farmer.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.task.respond.leave   [33 chars]
    en  I'll let you get back to the row.
    >>  ............................................
    pt  Vou deixar você voltar pra fileira.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the row."
       spoken on: conversations.topic.work.farmer.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.left`: the villager accepts. Subject `work.farmer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.farmer.crop_failing.active.respond / leave; conversations.scene.work.farmer.crop_failing.blocked.respond / leave; conversations.scene.work.farmer.crop_failing.failed.respond / leave; conversations.scene.work.farmer.crop_failing.succeeded.respond / leave; conversations.scene.work.farmer.followup / leave; conversations.scene.work.farmer.pest_pressure.active.respond / leave; conversations.scene.work.farmer.pest_pressure.succeeded.respond / leave; conversations.scene.work.farmer.price_dispute.blocked.respond / leave …and 7 more
```

> Written out in full under **`conversations.scene.work.farmer.crop_failing.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.farmer.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.farmer.village` — e.g. "Every loaf in that bakery started in my second field. Not one person has mentioned it."


```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.farmer.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.farmer.village.respond   [29 chars]
    en  That's what the village gets.
    >>  ............................................
    pt  É o que o vilarejo recebe.
    >>  ............................................
```


### Button `ask_who` — "Who'd notice first if you gave it up?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.farmer.village` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.farmer.village.ask_who` — accepted phrasings: "who'd notice first if you gave it up"
  - the message must contain one of: `notice`, `gave`
  - scored words: `notice`(1.5), `gave`(1.2), `first`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.village.respond.ask_who
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.village.respond.ask_who   [37 chars]
    en  Who'd notice first if you gave it up?
    >>  ............................................
    pt  Quem notaria primeiro se você largasse?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.farmer.village.ask_who`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.farmer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What happens if the harvest fails?" | "Good luck with the season."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.village.ask_who
WHO    VILLAGER — what the player reads after pressing "Who'd notice first if you gave it up?"
       spoken on: conversations.topic.work.farmer.village.respond, button `ask_who`
       leaves the player on: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.village.ask_who`: the villager explains. Subject `work.farmer.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.farmer.village.ask_who/1   [75 chars]
    en  The baker. Within four days. Then everyone else, loudly, by the week's end.
    >>  ............................................
    pt  A padeira. Em quatro dias. Aí todo mundo, aos gritos, até o fim da semana.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.village.ask_who/2   [64 chars]
    en  Nobody, for a fortnight. Then everybody at once, which is worse.
    >>  ............................................
    pt  Ninguém, por uma quinzena. Aí todo mundo de uma vez, que é pior.
    >>  ............................................
```


### Button `say_thanks` — "Then somebody should mention it. I will."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.farmer.village` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.farmer.village.say_thanks` — accepted phrasings: "then somebody should mention it. i will"
  - the message must contain one of: `mention`, `somebody`, `thanks`
  - scored words: `mention`(1.5), `somebody`(1.0), `thanks`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.village.respond.say_thanks   [40 chars]
    en  Then somebody should mention it. I will.
    >>  ............................................
    pt  Então alguém devia mencionar. Eu menciono.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.farmer.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.farmer.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.farmer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What happens if the harvest fails?" | "Good luck with the season."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Then somebody should mention it. I will."
       spoken on: conversations.topic.work.farmer.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.village.say_thanks`: the villager accepts. Subject `work.farmer.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.farmer.village.say_thanks/1   [81 chars]
    en  ...Right. Well. Twenty years at this and that is the first time it has been said.
    >>  ............................................
    pt  ...Certo. Bom. É a primeira vez e eu faço isso há vinte anos.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.village.say_thanks/2   [70 chars]
    en  Mention it to the baker, %1$s. She'll deny it and then bring me bread.
    >>  ............................................
    pt  Mencione à padeira, %1$s. Ela vai negar e depois vai me trazer pão.
    >>  ............................................
```


### Button `ask_price` — "Do they pay you what it's worth?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.farmer.village` · offered only once the villager has actually said `work:farmer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.farmer.village.ask_price` — accepted phrasings: "do they pay you what it's worth"
  - the message must contain one of: `pay`, `worth`, `price`
  - scored words: `pay`(1.5), `worth`(1.2), `price`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.village.respond.ask_price
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.village.respond.ask_price   [32 chars]
    en  Do they pay you what it's worth?
    >>  ............................................
    pt  Eles pagam o que vale?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.farmer.village.ask_price`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.farmer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What happens if the harvest fails?" | "Good luck with the season."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.village.ask_price
WHO    VILLAGER — what the player reads after pressing "Do they pay you what it's worth?"
       spoken on: conversations.topic.work.farmer.village.respond, button `ask_price`
       leaves the player on: conversations.topic.work.farmer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.village.ask_price`: the villager explains. Subject `work.farmer.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:farmer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.farmer.village.ask_price/1   [78 chars]
    en  No. They pay what a village can pay, which is a different and fairer question.
    >>  ............................................
    pt  Não. Pagam o que um vilarejo pode pagar, que é outra pergunta e mais justa.
    >>  ............................................
  dialogue.conversations.work.prof.farmer.village.ask_price/2   [69 chars]
    en  Enough to do it again next year. That's the only figure that matters.
    >>  ............................................
    pt  O bastante pra fazer de novo ano que vem. É o único número que importa.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the row."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.farmer.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.farmer.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.farmer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.farmer.village.respond.leave   [33 chars]
    en  I'll let you get back to the row.
    >>  ............................................
    pt  Vou deixar você voltar pra fileira.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.farmer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the row."
       spoken on: conversations.topic.work.farmer.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.farmer.left`: the villager accepts. Subject `work.farmer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.farmer.crop_failing.active.respond / leave; conversations.scene.work.farmer.crop_failing.blocked.respond / leave; conversations.scene.work.farmer.crop_failing.failed.respond / leave; conversations.scene.work.farmer.crop_failing.succeeded.respond / leave; conversations.scene.work.farmer.followup / leave; conversations.scene.work.farmer.pest_pressure.active.respond / leave; conversations.scene.work.farmer.pest_pressure.succeeded.respond / leave; conversations.scene.work.farmer.price_dispute.blocked.respond / leave …and 7 more
```

> Written out in full under **`conversations.scene.work.farmer.crop_failing.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

