# Topic: place

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `place` |
| Opened from | question `conversations.cat.village`, button `place` |
| Depth class (its heart budget) | `quick` |
| Returns to | `conversations.cat.village` |
| Ages that can reach it | teen, adult |
| Stance families it must offer | `curiosity`, `encouragement`, `dismissal`, `exit` |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.village`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.village.place
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.village
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.village.place   [29 chars]
    en  Anywhere here you're fond of?
    >>  ............................................
    pt  Tem algum lugar daqui de que você goste?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.scene.place.followup`](#conversations-scene-place-followup)
- [`conversations.scene.place.the_place_this_season.respond`](#conversations-scene-place-the-place-this-season-respond)
- [`conversations.scene.place.the_village_at_this_hour.respond`](#conversations-scene-place-the-village-at-this-hour-respond)
- [`conversations.topic.place.more.respond`](#conversations-topic-place-more-respond)
- [`conversations.topic.place.open.respond`](#conversations-topic-place-open-respond)

---

## `conversations.scene.place.followup`

**Reached from 4 route(s):** `conversations.scene.place.the_place_this_season.respond` / `ask_the_true_season`; `conversations.scene.place.the_place_this_season.respond` / `say_you_will_stay_to_see`; `conversations.scene.place.the_village_at_this_hour.respond` / `ask_where_to_stand`; `conversations.scene.place.the_village_at_this_hour.respond` / `agree_it_is_worth_seeing`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.place.the_place_this_season.answered` — e.g. "The wet one. You learn who fetches for whom in about a fortnight and you never unlearn it."
- `conversations.scene.place.the_place_this_season.counted_on` — e.g. "Then you will see the real thing, and I shall be interested to hear whether you still like us."
- `conversations.scene.place.the_village_at_this_hour.directed` — e.g. "The rise on the far side. Go now and you will get the last of the light on the roofs."
- `conversations.scene.place.the_village_at_this_hour.shared` — e.g. "Good. You are the first person in a season to stand still for it rather than walk on through."


```text
POOL   dialogue key: dialogue.conversations.scene.place.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.place.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.place.followup   [30 chars]
    en  Shall I go on about the place?
    >>  ............................................
    pt  Sigo falando do lugar?
    >>  ............................................
```


### Button `leave` — "Go and see it for yourself."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:place.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.place.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.place.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.place.followup.leave   [27 chars]
    en  Go and see it for yourself.
    >>  ............................................
    pt  Vá ver com seus próprios olhos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.place.leaving
WHO    VILLAGER — what the player reads after pressing "Go and see it for yourself."
       spoken on: conversations.scene.place.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `place.scene.leaving`: the villager accepts. Subject `place.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.place.the_place_this_season.respond / leave; conversations.scene.place.the_village_at_this_hour.respond / leave; conversations.topic.place.more.respond / leave; conversations.topic.place.open.respond / leave
```

```text
  dialogue.conversations.scene.place.leaving/1   [42 chars]
    en  You will find your own corner soon enough.
    >>  ............................................
    pt  Você vai achar o seu canto logo.
    >>  ............................................
  dialogue.conversations.scene.place.leaving/2   [62 chars]
    en  It is a small place. There is not much of it left to show you.
    >>  ............................................
    pt  É um lugar pequeno. Não sobrou muita coisa para te mostrar.
    >>  ............................................
  dialogue.conversations.scene.place.leaving/3   [27 chars]
    en  Go and look at it sometime.
    >>  ............................................
    pt  Vá dar uma olhada algum dia.
    >>  ............................................
```

---


## `conversations.scene.place.the_place_this_season.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `place`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.place.the_place_this_season` — e.g. "The village changes shape this time of year. Ground that is a path in winter is somebody's crop by now."


```text
POOL   dialogue key: dialogue.conversations.scene.place.the_place_this_season.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.place.the_place_this_season.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.place.the_place_this_season.respond   [23 chars]
    en  The place, this season.
    >>  ............................................
    pt  O lugar, nesta estação.
    >>  ............................................
```


### Button `ask_the_true_season` — "Which season shows it truly?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `place.the_place_this_season.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.place.the_place_this_season.ask_the_true_season` — accepted phrasings: "which season shows it truly"; "which season shows it truly"; "which season shows the real place"
  - the message must contain one of: `season`, `shows`
  - scored words: `season`(1.8), `shows`(1.8), `which`(0.8), `truly`(0.8), `real`(0.8), `place`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.place.the_place_this_season.respond.ask_the_true_season
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.place.the_place_this_season.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.place.the_place_this_season.respond.ask_the_true_season   [28 chars]
    en  Which season shows it truly?
    >>  ............................................
    pt  Que estação mostra a verdade?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `place.this_season`)_
- Does: session `turn`
- Then opens: `conversations.scene.place.followup`
- …where the player's next choices will be: "Go and see it for yourself."

```text
POOL   dialogue key: dialogue.conversations.scene.place.the_place_this_season.answered
WHO    VILLAGER — what the player reads after pressing "Which season shows it truly?"
       spoken on: conversations.scene.place.the_place_this_season.respond, button `ask_the_true_season`
       leaves the player on: conversations.scene.place.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `place.the_place_this_season.open.answered`: the villager explains. Subject `place.this_season`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:place` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.place.the_place_this_season.answered/1   [90 chars]
    en  The wet one. You learn who fetches for whom in about a fortnight and you never unlearn it.
    >>  ............................................
    pt  A chuvosa. Você aprende quem busca coisas para quem em umas duas semanas e nunca mais desaprende.
    >>  ............................................
  dialogue.conversations.scene.place.the_place_this_season.answered/2   [93 chars]
    en  The hungry end of winter. Everything a village is gets decided in about nine days round then.
    >>  ............................................
    pt  O fim faminto do inverno. Tudo o que uma vila é se decide em uns nove dias por ali.
    >>  ............................................
  dialogue.conversations.scene.place.the_place_this_season.answered/3   [93 chars]
    en  The one nobody visits in. A place shows itself when it has stopped expecting anybody to look.
    >>  ............................................
    pt  Aquela em que ninguém visita. Um lugar se mostra quando parou de esperar que alguém olhe.
    >>  ............................................
```


### Button `say_you_will_stay_to_see` — "I'll be here for that one too."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `place.the_place_this_season.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.place.the_place_this_season.say_you_will_stay_to_see` — accepted phrasings: "ill be here for that one too"; "i will be here for that one too"; "i intend to stay through it"
  - the message must contain one of: `here`, `stay`
  - scored words: `here`(1.8), `stay`(1.8), `ill`(0.8), `one`(0.8), `too`(0.8), `intend`(0.8), `through`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.place.the_place_this_season.respond.say_you_will_stay_to_see
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.place.the_place_this_season.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.place.the_place_this_season.respond.say_you_will_stay_to_see   [30 chars]
    en  I'll be here for that one too.
    >>  ............................................
    pt  Estarei aqui nessa também.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `topic.place.season.promised`, budget `quick`, replay policy `once`
- Does: disposition — warmth +3, trust +1  _(recorded under topic `place.this_season`)_
- Does: session `turn`
- Then opens: `conversations.scene.place.followup`
- …where the player's next choices will be: "Go and see it for yourself."

```text
POOL   dialogue key: dialogue.conversations.scene.place.the_place_this_season.counted_on
WHO    VILLAGER — what the player reads after pressing "I'll be here for that one too."
       spoken on: conversations.scene.place.the_place_this_season.respond, button `say_you_will_stay_to_see`
       leaves the player on: conversations.scene.place.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `place.the_place_this_season.open.counted_on`: the villager accepts. Subject `place.this_season`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:place` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.place.the_place_this_season.counted_on/1   [94 chars]
    en  Then you will see the real thing, and I shall be interested to hear whether you still like us.
    >>  ............................................
    pt  Então você vai ver a coisa de verdade, e eu vou ter curiosidade de saber se ainda gosta da gente.
    >>  ............................................
  dialogue.conversations.scene.place.the_place_this_season.counted_on/2   [85 chars]
    en  Say that again in the wet months and I shall believe it. People mean it every summer.
    >>  ............................................
    pt  Repita isso nos meses chuvosos e eu acredito. As pessoas falam sério todo verão.
    >>  ............................................
  dialogue.conversations.scene.place.the_place_this_season.counted_on/3   [95 chars]
    en  Good. Anybody can love a village in this weather. Staying for the other kind is the whole test.
    >>  ............................................
    pt  Bom. Qualquer um ama uma vila com este tempo. Ficar para o outro tipo é o teste inteiro.
    >>  ............................................
```


### Button `leave` — "I'll go and look."

*stance family `exit` · tone `plain` · answers the beat(s) `place.the_place_this_season.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.place.the_place_this_season.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.place.the_place_this_season.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.place.the_place_this_season.respond.leave   [17 chars]
    en  I'll go and look.
    >>  ............................................
    pt  Vou dar uma olhada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.place.leaving
WHO    VILLAGER — what the player reads after pressing "I'll go and look."
       spoken on: conversations.scene.place.the_place_this_season.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `place.scene.leaving`: the villager accepts. Subject `place.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.place.followup / leave; conversations.scene.place.the_village_at_this_hour.respond / leave; conversations.topic.place.more.respond / leave; conversations.topic.place.open.respond / leave
```

> Written out in full under **`conversations.scene.place.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.place.the_village_at_this_hour.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `place`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.place.the_village_at_this_hour` — e.g. "This is the hour the village is at its best and there is almost never anybody about to see it."


```text
POOL   dialogue key: dialogue.conversations.scene.place.the_village_at_this_hour.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.place.the_village_at_this_hour.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.place.the_village_at_this_hour.respond   [25 chars]
    en  The village at this hour.
    >>  ............................................
    pt  A vila a esta hora.
    >>  ............................................
```


### Button `ask_where_to_stand` — "Where's the best view of it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `place.the_village_at_this_hour.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.place.the_village_at_this_hour.ask_where_to_stand` — accepted phrasings: "wheres the best view of it"; "where is the best view of it"; "where should i stand to see it"
  - the message must contain one of: `view`, `stand`
  - scored words: `view`(1.8), `stand`(1.8), `wheres`(0.8), `best`(0.8), `where`(0.8), `should`(0.8), `see`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.place.the_village_at_this_hour.respond.ask_where_to_stand
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.place.the_village_at_this_hour.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.place.the_village_at_this_hour.respond.ask_where_to_stand   [28 chars]
    en  Where's the best view of it?
    >>  ............................................
    pt  De onde se vê melhor?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `place.at_this_hour`)_
- Does: session `turn`
- Then opens: `conversations.scene.place.followup`
- …where the player's next choices will be: "Go and see it for yourself."

```text
POOL   dialogue key: dialogue.conversations.scene.place.the_village_at_this_hour.directed
WHO    VILLAGER — what the player reads after pressing "Where's the best view of it?"
       spoken on: conversations.scene.place.the_village_at_this_hour.respond, button `ask_where_to_stand`
       leaves the player on: conversations.scene.place.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `place.the_village_at_this_hour.open.directed`: the villager explains. Subject `place.at_this_hour`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:place` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.place.the_village_at_this_hour.directed/1   [85 chars]
    en  The rise on the far side. Go now and you will get the last of the light on the roofs.
    >>  ............................................
    pt  A elevação do outro lado. Vá agora e você pega a última luz nos telhados.
    >>  ............................................
  dialogue.conversations.scene.place.the_village_at_this_hour.directed/2   [105 chars]
    en  Anywhere with your back to the workshops. The village looks entirely different without its tools in shot.
    >>  ............................................
    pt  Qualquer lugar de costas para as oficinas. A vila fica outra sem as ferramentas no enquadramento.
    >>  ............................................
  dialogue.conversations.scene.place.the_village_at_this_hour.directed/3   [97 chars]
    en  Sit on the wall by the water. Everybody walks past it and nobody sits on it, which is their loss.
    >>  ............................................
    pt  Sente no muro perto da água. Todo mundo passa por ele e ninguém senta, o que é perda deles.
    >>  ............................................
```


### Button `agree_it_is_worth_seeing` — "It's worth stopping for."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `place.the_village_at_this_hour.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.place.the_village_at_this_hour.agree_it_is_worth_seeing` — accepted phrasings: "its worth stopping for"; "it is worth stopping for"; "worth looking at properly"
  - the message must contain one of: `worth`, `looking`
  - scored words: `worth`(1.8), `looking`(1.8), `its`(0.8), `stopping`(0.8), `properly`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.place.the_village_at_this_hour.respond.agree_it_is_worth_seeing
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.place.the_village_at_this_hour.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.place.the_village_at_this_hour.respond.agree_it_is_worth_seeing   [24 chars]
    en  It's worth stopping for.
    >>  ............................................
    pt  Vale a pena parar para ver.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `topic.place.hour.shared`, budget `quick`, replay policy `once`
- Does: disposition — warmth +2  _(recorded under topic `place.at_this_hour`)_
- Does: session `turn`
- Then opens: `conversations.scene.place.followup`
- …where the player's next choices will be: "Go and see it for yourself."

```text
POOL   dialogue key: dialogue.conversations.scene.place.the_village_at_this_hour.shared
WHO    VILLAGER — what the player reads after pressing "It's worth stopping for."
       spoken on: conversations.scene.place.the_village_at_this_hour.respond, button `agree_it_is_worth_seeing`
       leaves the player on: conversations.scene.place.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `place.the_village_at_this_hour.open.shared`: the villager accepts. Subject `place.at_this_hour`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:place` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.place.the_village_at_this_hour.shared/1   [93 chars]
    en  Good. You are the first person in a season to stand still for it rather than walk on through.
    >>  ............................................
    pt  Bom. Você é a primeira pessoa numa estação a parar por causa disso em vez de seguir reto.
    >>  ............................................
  dialogue.conversations.scene.place.the_village_at_this_hour.shared/2   [77 chars]
    en  It is, and it costs nothing, and I have never once regretted the ten minutes.
    >>  ............................................
    pt  Vale, e não custa nada, e eu nunca me arrependi dos dez minutos.
    >>  ............................................
  dialogue.conversations.scene.place.the_village_at_this_hour.shared/3   [91 chars]
    en  That is why I stop here. I am glad of the company for it, which is not a thing I say often.
    >>  ............................................
    pt  É por isso que eu paro aqui. Fico contente com a companhia, o que não é coisa que eu diga sempre.
    >>  ............................................
```


### Button `leave` — "I'll go and look."

*stance family `exit` · tone `plain` · answers the beat(s) `place.the_village_at_this_hour.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.place.the_village_at_this_hour.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.place.the_village_at_this_hour.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.place.the_village_at_this_hour.respond.leave   [17 chars]
    en  I'll go and look.
    >>  ............................................
    pt  Vou dar uma olhada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.place.leaving
WHO    VILLAGER — what the player reads after pressing "I'll go and look."
       spoken on: conversations.scene.place.the_village_at_this_hour.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `place.scene.leaving`: the villager accepts. Subject `place.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.place.followup / leave; conversations.scene.place.the_place_this_season.respond / leave; conversations.topic.place.more.respond / leave; conversations.topic.place.open.respond / leave
```

> Written out in full under **`conversations.scene.place.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.place.more.respond`

**Reached from 2 route(s):** `conversations.topic.place.open.respond` / `ask_where_it_is`; `conversations.topic.place.open.respond` / `say_you_will_go`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.place.open.agreed` — e.g. "It does, and the moment somebody builds on it the village will be measurably worse and nobody will be able to say why."
- `conversations.place.open.shown` — e.g. "Past the last roof and left, where the ground drops. You would walk over it and never notice."


```text
POOL   dialogue key: dialogue.conversations.topic.place.more.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.place.more.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.place.more.respond   [27 chars]
    en  And one you keep away from.
    >>  ............................................
    pt  E um do qual você se afasta.
    >>  ............................................
```


### Button `ask_what_happened_there` — "What happened there?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `place.open.shown`, `place.open.agreed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.place.more.ask_what_happened_there` — accepted phrasings: "what happened there"; "what happened there"; "why do you go around it"
  - the message must contain one of: `happened`, `around`
  - scored words: `happened`(1.8), `around`(1.8), `why`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.place.more.respond.ask_what_happened_there
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.place.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.place.more.respond.ask_what_happened_there   [20 chars]
    en  What happened there?
    >>  ............................................
    pt  O que aconteceu ali?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `topic.place.asked_after`, budget `quick`, replay policy `once`
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `place.the_avoided_one`)_
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.place.more.told
WHO    VILLAGER — what the player reads after pressing "What happened there?"
       spoken on: conversations.topic.place.more.respond, button `ask_what_happened_there`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `place.more.told`: the villager explains. Subject `place.the_avoided_one`, polarity `mixed`, ends conversation, outcome `engaged`.
NOTE   this is the line that establishes `topic:place` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.place.more.told/1   [99 chars]
    en  A conversation I handled badly. The other person has forgiven me and the ground apparently has not.
    >>  ............................................
    pt  Uma conversa que eu conduzi mal. A outra pessoa me perdoou e o chão, pelo visto, não.
    >>  ............................................
  dialogue.conversations.place.more.told/2   [99 chars]
    en  Somebody used to wait for me there. They stopped, and the waiting spot kept the habit without them.
    >>  ............................................
    pt  Alguém costumava me esperar ali. Parou, e o ponto de espera manteve o hábito sem essa pessoa.
    >>  ............................................
  dialogue.conversations.place.more.told/3   [96 chars]
    en  Nothing worth the drama. I simply cannot stand in it without hearing what I heard the last time.
    >>  ............................................
    pt  Nada que valha o drama. Eu só não consigo ficar ali sem ouvir o que eu ouvi da última vez.
    >>  ............................................
```


### Button `say_the_long_way_is_fine` — "The long way round is allowed."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `place.open.shown`, `place.open.agreed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.place.more.say_the_long_way_is_fine` — accepted phrasings: "the long way round is allowed"; "the long way round is allowed"; "taking the long way is fine"
  - the message must contain one of: `long`, `round`
  - scored words: `long`(1.8), `round`(1.8), `way`(0.8), `allowed`(0.8), `taking`(0.8), `fine`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.place.more.respond.say_the_long_way_is_fine
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.place.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.place.more.respond.say_the_long_way_is_fine   [30 chars]
    en  The long way round is allowed.
    >>  ............................................
    pt  Fazer a volta é permitido.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `place.the_avoided_one`)_
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.place.more.eased
WHO    VILLAGER — what the player reads after pressing "The long way round is allowed."
       spoken on: conversations.topic.place.more.respond, button `say_the_long_way_is_fine`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `place.more.eased`: the villager accepts. Subject `place.the_avoided_one`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   this is the line that establishes `topic:place` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.place.more.eased/1   [98 chars]
    en  Thank you. Everybody else tells me to walk through it once and be done, as though that ever works.
    >>  ............................................
    pt  Obrigada. Todo mundo me diz para atravessar uma vez e acabar com isso, como se isso funcionasse.
    >>  ............................................
  dialogue.conversations.place.more.eased/2   [88 chars]
    en  It adds two minutes. I have decided that is a reasonable rent to pay on a bad afternoon.
    >>  ............................................
    pt  Acrescenta dois minutos. Decidi que é um aluguel razoável a pagar por uma tarde ruim.
    >>  ............................................
  dialogue.conversations.place.more.eased/3   [93 chars]
    en  One year I shall walk through it and think nothing. Until then the long way is doing no harm.
    >>  ............................................
    pt  Um ano eu vou atravessar e não sentir nada. Até lá a volta não faz mal a ninguém.
    >>  ............................................
```


### Button `call_it_silly` — "Ground can't hold a grudge."

*stance family `dismissal` · tone `blunt` · outcome `conversation_ended` · answers the beat(s) `place.open.shown`, `place.open.agreed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.place.more.call_it_silly` — accepted phrasings: "ground cant hold a grudge"; "ground cannot hold a grudge"; "the ground did nothing to you"
  - the message must contain one of: `ground`, `grudge`
  - scored words: `ground`(1.8), `grudge`(1.8), `cant`(0.8), `hold`(0.8), `cannot`(0.8), `nothing`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.place.more.respond.call_it_silly
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.place.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.place.more.respond.call_it_silly   [27 chars]
    en  Ground can't hold a grudge.
    >>  ............................................
    pt  Chão não guarda rancor.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `topic.place.cut_short`, budget `quick`, replay policy `once`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `place.the_avoided_one`)_
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.place.more.closed
WHO    VILLAGER — what the player reads after pressing "Ground can't hold a grudge."
       spoken on: conversations.topic.place.more.respond, button `call_it_silly`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `place.more.closed`: the villager deflects. Subject `place.the_avoided_one`, polarity `negative`, ends conversation, outcome `conversation_ended`.
NOTE   this is the line that establishes `topic:place` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.place.more.closed/1   [92 chars]
    en  It cannot. I am the one holding it and I am aware of that, and it has still been four years.
    >>  ............................................
    pt  Não guarda. Quem guarda sou eu e eu tenho consciência disso, e mesmo assim já são quatro anos.
    >>  ............................................
  dialogue.conversations.place.more.closed/2   [82 chars]
    en  You are right and it changes nothing, which is the awkward part about being right.
    >>  ............................................
    pt  Você tem razão e isso não muda nada, que é a parte incômoda de ter razão.
    >>  ............................................
  dialogue.conversations.place.more.closed/3   [40 chars]
    en  Right. I shall keep my routes to myself.
    >>  ............................................
    pt  Certo. Guardo meus caminhos para mim.
    >>  ............................................
```


### Button `leave` — "I'll go and look."

*stance family `exit` · tone `plain` · answers the beat(s) `place.open.shown`, `place.open.agreed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.place.more.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.place.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.place.more.respond.leave   [17 chars]
    en  I'll go and look.
    >>  ............................................
    pt  Vou dar uma olhada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.place.leaving
WHO    VILLAGER — what the player reads after pressing "I'll go and look."
       spoken on: conversations.topic.place.more.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `place.scene.leaving`: the villager accepts. Subject `place.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.place.followup / leave; conversations.scene.place.the_place_this_season.respond / leave; conversations.scene.place.the_village_at_this_hour.respond / leave; conversations.topic.place.open.respond / leave
```

> Written out in full under **`conversations.scene.place.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.place.open.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `place`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.place.open` — e.g. "There is one corner of this village I would defend in an argument, and it is not a building."


```text
POOL   dialogue key: dialogue.conversations.topic.place.open.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.place.open.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.place.open.respond   [24 chars]
    en  A corner you're fond of.
    >>  ............................................
    pt  Um canto de que você gosta.
    >>  ............................................
```


### Button `ask_where_it_is` — "Where is this corner?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `place.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.place.open.ask_where_it_is` — accepted phrasings: "where is this corner"; "where is this corner"; "which spot do you mean"
  - the message must contain one of: `corner`, `spot`
  - scored words: `corner`(1.8), `spot`(1.8), `where`(0.8), `which`(0.8), `mean`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.place.open.respond.ask_where_it_is
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.place.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.place.open.respond.ask_where_it_is   [21 chars]
    en  Where is this corner?
    >>  ............................................
    pt  Onde fica esse canto?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `place.the_fond_one`)_
- Does: session `turn`
- Then opens: `conversations.topic.place.more.respond`
- …where the player's next choices will be: "What happened there?" | "The long way round is allowed." | "Ground can't hold a grudge." | "I'll go and look."

```text
POOL   dialogue key: dialogue.conversations.place.open.shown
WHO    VILLAGER — what the player reads after pressing "Where is this corner?"
       spoken on: conversations.topic.place.open.respond, button `ask_where_it_is`
       leaves the player on: conversations.topic.place.more.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `place.open.shown`: the villager explains. Subject `place.the_fond_one`, polarity `positive`, invites followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:place` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, dismissal, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.place.open.shown/1   [93 chars]
    en  Past the last roof and left, where the ground drops. You would walk over it and never notice.
    >>  ............................................
    pt  Depois do último telhado e à esquerda, onde o chão desce. Você passaria por cima e nunca notaria.
    >>  ............................................
  dialogue.conversations.place.open.shown/2   [100 chars]
    en  Between two walls that were meant to meet and never did. The gap is exactly the width of one person.
    >>  ............................................
    pt  Entre dois muros que deviam se encontrar e nunca se encontraram. A fresta tem exatamente a largura de uma pessoa.
    >>  ............................................
  dialogue.conversations.place.open.shown/3   [100 chars]
    en  Where the path bends before the water. Stand there and you cannot hear a single door in the village.
    >>  ............................................
    pt  Onde o caminho faz a curva antes da água. Fique lá e você não escuta uma porta sequer da vila.
    >>  ............................................
```


### Button `say_you_will_go` — "Every village needs one of those."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `place.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.place.open.say_you_will_go` — accepted phrasings: "every village needs one of those"; "every village needs one of those"; "everywhere needs a place like that"
  - the message must contain one of: `village`, `needs`
  - scored words: `village`(1.8), `needs`(1.8), `every`(0.8), `one`(0.8), `those`(0.8), `everywhere`(0.8), `place`(0.8), `like`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.place.open.respond.say_you_will_go
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.place.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.place.open.respond.say_you_will_go   [33 chars]
    en  Every village needs one of those.
    >>  ............................................
    pt  Toda vila precisa de um desses.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2  _(recorded under topic `place.the_fond_one`)_
- Does: session `turn`
- Then opens: `conversations.topic.place.more.respond`
- …where the player's next choices will be: "What happened there?" | "The long way round is allowed." | "Ground can't hold a grudge." | "I'll go and look."

```text
POOL   dialogue key: dialogue.conversations.place.open.agreed
WHO    VILLAGER — what the player reads after pressing "Every village needs one of those."
       spoken on: conversations.topic.place.open.respond, button `say_you_will_go`
       leaves the player on: conversations.topic.place.more.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `place.open.agreed`: the villager accepts. Subject `place.the_fond_one`, polarity `positive`, invites followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:place` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, encouragement, dismissal, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.place.open.agreed/1   [118 chars]
    en  It does, and the moment somebody builds on it the village will be measurably worse and nobody will be able to say why.
    >>  ............................................
    pt  Precisa, e no momento em que alguém construir ali a vila vai ficar mensuravelmente pior e ninguém vai saber dizer por quê.
    >>  ............................................
  dialogue.conversations.place.open.agreed/2   [85 chars]
    en  That is my position exactly, and I have lost the argument about it twice at the well.
    >>  ............................................
    pt  É exatamente a minha posição, e eu já perdi essa discussão duas vezes no poço.
    >>  ............................................
  dialogue.conversations.place.open.agreed/3   [94 chars]
    en  Nearly everywhere has one. The trouble is that whoever owns the ground rarely knows it is one.
    >>  ............................................
    pt  Quase todo lugar tem um. O problema é que quem é dono do terreno raramente sabe que é um.
    >>  ............................................
```


### Button `shrug_at_the_ground` — "It's a patch of dirt."

*stance family `dismissal` · tone `blunt` · outcome `conversation_ended` · answers the beat(s) `place.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.place.open.shrug_at_the_ground` — accepted phrasings: "its a patch of dirt"; "it is a patch of dirt"; "that is only dirt and grass"
  - the message must contain one of: `dirt`, `grass`
  - scored words: `dirt`(1.8), `grass`(1.8), `its`(0.8), `patch`(0.8), `only`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.place.open.respond.shrug_at_the_ground
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.place.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.place.open.respond.shrug_at_the_ground   [21 chars]
    en  It's a patch of dirt.
    >>  ............................................
    pt  É um pedaço de terra.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `topic.place.dismissed`, budget `quick`, replay policy `once`
- Does: disposition — warmth -2, tension +1  _(recorded under topic `place.the_fond_one`)_
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.place.open.closed
WHO    VILLAGER — what the player reads after pressing "It's a patch of dirt."
       spoken on: conversations.topic.place.open.respond, button `shrug_at_the_ground`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `place.open.closed`: the villager qualifys. Subject `place.the_fond_one`, polarity `negative`, ends conversation, outcome `conversation_ended`.
NOTE   this is the line that establishes `topic:place` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.place.open.closed/1   [75 chars]
    en  It is. So is everywhere else, and I have chosen to have a favourite anyway.
    >>  ............................................
    pt  É. Como todo o resto, e eu escolhi ter um favorito mesmo assim.
    >>  ............................................
  dialogue.conversations.place.open.closed/2   [109 chars]
    en  Very likely. It has been my patch of dirt for eleven years, which is longer than most friendships hereabouts.
    >>  ............................................
    pt  Muito provável. É o meu pedaço de terra há onze anos, mais tempo que a maioria das amizades por aqui.
    >>  ............................................
  dialogue.conversations.place.open.closed/3   [49 chars]
    en  Right. I shall stop pointing at the ground, then.
    >>  ............................................
    pt  Certo. Paro de apontar para o chão, então.
    >>  ............................................
```


### Button `leave` — "I'll go and look."

*stance family `exit` · tone `plain` · answers the beat(s) `place.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.place.open.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.place.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.place.open.respond.leave   [17 chars]
    en  I'll go and look.
    >>  ............................................
    pt  Vou dar uma olhada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.place.leaving
WHO    VILLAGER — what the player reads after pressing "I'll go and look."
       spoken on: conversations.topic.place.open.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `place.scene.leaving`: the villager accepts. Subject `place.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.place.followup / leave; conversations.scene.place.the_place_this_season.respond / leave; conversations.scene.place.the_village_at_this_hour.respond / leave; conversations.topic.place.more.respond / leave
```

> Written out in full under **`conversations.scene.place.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

