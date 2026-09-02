# Topic: season

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `season` |
| Opened from | question `conversations.cat.chitchat`, button `season` |
| Depth class (its heart budget) | `quick` |
| Returns to | `conversations.cat.chitchat` |
| Ages that can reach it | toddler, child, teen, adult |
| Stance families it must offer | `curiosity`, `self_disclosure`, `encouragement`, `dismissal`, `humor`, `exit` |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.chitchat`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.chitchat.season
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.chitchat
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.chitchat.season   [30 chars]
    en  How's the season treating you?
    >>  ............................................
    pt  Como a estação está te tratando?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.scene.season.deep_winter.respond`](#conversations-scene-season-deep-winter-respond)
- [`conversations.scene.season.followup`](#conversations-scene-season-followup)
- [`conversations.scene.season.the_turn.respond`](#conversations-scene-season-the-turn-respond)
- [`conversations.topic.season.holiday.followup`](#conversations-topic-season-holiday-followup)
- [`conversations.topic.season.holiday.respond`](#conversations-topic-season-holiday-respond)
- [`conversations.topic.season.toddler.respond`](#conversations-topic-season-toddler-respond)
- [`conversations.topic.season.turn.followup`](#conversations-topic-season-turn-followup)
- [`conversations.topic.season.turn.respond`](#conversations-topic-season-turn-respond)

---

## `conversations.scene.season.deep_winter.respond`

**Reached from 1 route(s):** `conversations.cat.chitchat` / `season`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.season.deep_winter` — e.g. "Halfway. The second half is always shorter than it feels and I say that to myself most mornings."


```text
POOL   dialogue key: dialogue.conversations.scene.season.deep_winter.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.season.deep_winter.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.season.deep_winter.respond   [12 chars]
    en  This winter.
    >>  ............................................
    pt  Este inverno.
    >>  ............................................
```


### Button `ask_about_the_stores` — "Will the stores hold?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `season.deep_winter.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.season.deep_winter.ask_about_the_stores` — accepted phrasings: "will the stores hold"; "will the stores hold"; "is there plenty put by"
  - the message must contain one of: `stores`, `plenty`, `hold`
  - scored words: `stores`(1.8), `plenty`(1.8), `hold`(1.8), `put`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.season.deep_winter.respond.ask_about_the_stores
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.season.deep_winter.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.season.deep_winter.respond.ask_about_the_stores   [21 chars]
    en  Will the stores hold?
    >>  ............................................
    pt  Os estoques vão durar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `season.winter`)_
- Does: session `turn`
- Then opens: `conversations.scene.season.followup`
- …where the player's next choices will be: "That's the year, then."

```text
POOL   dialogue key: dialogue.conversations.scene.season.deep_winter.answered
WHO    VILLAGER — what the player reads after pressing "Will the stores hold?"
       spoken on: conversations.scene.season.deep_winter.respond, button `ask_about_the_stores`
       leaves the player on: conversations.scene.season.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.deep_winter.open.answered`: the villager reports. Subject `season.winter`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:season` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.season.deep_winter.answered/1   [119 chars]
    en  For me, yes, with about three weeks to spare. For two houses at the top of the lane, no, and everybody knows which two.
    >>  ............................................
    pt  Para mim, sim, com umas três semanas de folga. Para duas casas no fim da viela, não, e todo mundo sabe quais duas.
    >>  ............................................
  dialogue.conversations.scene.season.deep_winter.answered/2   [121 chars]
    en  They will if the thaw comes when it usually does. That is the whole of the plan and it has held nine years out of eleven.
    >>  ............................................
    pt  Vão, se o degelo vier quando costuma vir. É o plano inteiro e funcionou nove anos em onze.
    >>  ............................................
  dialogue.conversations.scene.season.deep_winter.answered/3   [110 chars]
    en  I count them on the first of every month. It is a grim little ritual and it has never once been a wasted hour.
    >>  ............................................
    pt  Eu conto no primeiro dia de cada mês. É um ritualzinho sombrio e nunca foi uma hora desperdiçada.
    >>  ............................................
```


### Button `offer_to_help` — "Tell me if anybody runs short."

*stance family `practical_help` · tone `gentle` · outcome `appreciated` · answers the beat(s) `season.deep_winter.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.season.deep_winter.offer_to_help` — accepted phrasings: "tell me if anybody runs short"; "tell me if anybody runs short"; "let me know if a house runs out"
  - the message must contain one of: `short`, `house`, `runs`
  - scored words: `short`(1.8), `house`(1.8), `runs`(1.8), `tell`(0.8), `anybody`(0.8), `let`(0.8), `know`(0.8), `out`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.season.deep_winter.respond.offer_to_help
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.season.deep_winter.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.season.deep_winter.respond.offer_to_help   [30 chars]
    en  Tell me if anybody runs short.
    >>  ............................................
    pt  Me avise se alguém ficar sem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `topic.season.winter.offered`, budget `standard`, replay policy `once`
- Does: disposition — warmth +3, trust +2  _(recorded under topic `season.winter`)_
- Does: session `turn`
- Then opens: `conversations.scene.season.followup`
- …where the player's next choices will be: "That's the year, then."

```text
POOL   dialogue key: dialogue.conversations.scene.season.deep_winter.accepted
WHO    VILLAGER — what the player reads after pressing "Tell me if anybody runs short."
       spoken on: conversations.scene.season.deep_winter.respond, button `offer_to_help`
       leaves the player on: conversations.scene.season.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.deep_winter.open.accepted`: the villager accepts. Subject `season.winter`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:season` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.season.deep_winter.accepted/1   [112 chars]
    en  I will, and I will tell you before they ask, because by the time a house asks it has been short for a fortnight.
    >>  ............................................
    pt  Vou, e vou avisar antes de pedirem, porque quando uma casa pede já está sem há duas semanas.
    >>  ............................................
  dialogue.conversations.scene.season.deep_winter.accepted/2   [111 chars]
    en  That is a useful thing to be told and a rare one. Most offers arrive after the thaw, when they are a sentiment.
    >>  ............................................
    pt  É uma coisa útil de se ouvir e é rara. Quase toda oferta chega depois do degelo, quando já é só sentimento.
    >>  ............................................
  dialogue.conversations.scene.season.deep_winter.accepted/3   [100 chars]
    en  Understood. And I will not name the house to you unless they say I may, which I hope you would want.
    >>  ............................................
    pt  Entendido. E não vou dizer a você qual casa a menos que eles permitam, o que eu espero que você prefira.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `season.deep_winter.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.season.deep_winter.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.season.deep_winter.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.season.deep_winter.respond.leave   [12 chars]
    en  Fair enough.
    >>  ............................................
    pt  Justo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.season.leaving
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.scene.season.deep_winter.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.scene.leaving`: the villager accepts. Subject `season.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.season.followup / leave; conversations.scene.season.the_turn.respond / leave
```

```text
  dialogue.conversations.scene.season.leaving/1   [24 chars]
    en  It will do what it does.
    >>  ............................................
    pt  Vai fazer o que quiser.
    >>  ............................................
  dialogue.conversations.scene.season.leaving/2   [37 chars]
    en  Right. Another one along soon enough.
    >>  ............................................
    pt  Certo. Logo vem outra.
    >>  ............................................
  dialogue.conversations.scene.season.leaving/3   [23 chars]
    en  Aye. Mind the evenings.
    >>  ............................................
    pt  Pois é. Cuidado com as noites.
    >>  ............................................
```

---


## `conversations.scene.season.followup`

**Reached from 4 route(s):** `conversations.scene.season.deep_winter.respond` / `ask_about_the_stores`; `conversations.scene.season.deep_winter.respond` / `offer_to_help`; `conversations.scene.season.the_turn.respond` / `ask_about_last_year`; `conversations.scene.season.the_turn.respond` / `note_the_light`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.season.deep_winter.accepted` — e.g. "I will, and I will tell you before they ask, because by the time a house asks it has been short for a fortnight."
- `conversations.scene.season.deep_winter.answered` — e.g. "For me, yes, with about three weeks to spare. For two houses at the top of the lane, no, and everybody knows which two."
- `conversations.scene.season.the_turn.agreed` — e.g. "It is, and it lasts about eleven days, and I have never once managed to be unbusy for all eleven."
- `conversations.scene.season.the_turn.explained` — e.g. "Last year it hung on until the frost and then went in a week. This one is doing it properly, in order, which is rarer than it sounds."


```text
POOL   dialogue key: dialogue.conversations.scene.season.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.season.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.season.followup   [31 chars]
    en  Anything else about the season?
    >>  ............................................
    pt  Mais alguma coisa sobre a estação?
    >>  ............................................
```


### Button `leave` — "That's the year, then."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:season.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.season.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.season.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.season.followup.leave   [22 chars]
    en  That's the year, then.
    >>  ............................................
    pt  É o ano, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.season.leaving
WHO    VILLAGER — what the player reads after pressing "That's the year, then."
       spoken on: conversations.scene.season.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.scene.leaving`: the villager accepts. Subject `season.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.season.deep_winter.respond / leave; conversations.scene.season.the_turn.respond / leave
```

> Written out in full under **`conversations.scene.season.deep_winter.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.season.the_turn.respond`

**Reached from 1 route(s):** `conversations.cat.chitchat` / `season`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.season.the_turn` — e.g. "Early. Everything is a week ahead of last year and I have written it down, because nobody ever remembers what last year did."


```text
POOL   dialogue key: dialogue.conversations.scene.season.the_turn.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.season.the_turn.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.season.the_turn.respond   [12 chars]
    en  This autumn.
    >>  ............................................
    pt  Este outono.
    >>  ............................................
```


### Button `ask_about_last_year` — "How does it compare to last year?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `season.the_turn.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.season.the_turn.ask_about_last_year` — accepted phrasings: "how does it compare to last year"; "how does it compare to last year"; "was last year different"
  - the message must contain one of: `compare`, `last`, `year`
  - scored words: `compare`(1.8), `last`(1.8), `year`(1.8), `does`(0.8), `different`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.season.the_turn.respond.ask_about_last_year
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.season.the_turn.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.season.the_turn.respond.ask_about_last_year   [33 chars]
    en  How does it compare to last year?
    >>  ............................................
    pt  Como se compara ao ano passado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `season.autumn`)_
- Does: session `turn`
- Then opens: `conversations.scene.season.followup`
- …where the player's next choices will be: "That's the year, then."

```text
POOL   dialogue key: dialogue.conversations.scene.season.the_turn.explained
WHO    VILLAGER — what the player reads after pressing "How does it compare to last year?"
       spoken on: conversations.scene.season.the_turn.respond, button `ask_about_last_year`
       leaves the player on: conversations.scene.season.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.the_turn.open.explained`: the villager explains. Subject `season.autumn`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:season` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.season.the_turn.explained/1   [133 chars]
    en  Last year it hung on until the frost and then went in a week. This one is doing it properly, in order, which is rarer than it sounds.
    >>  ............................................
    pt  Ano passado se arrastou até a geada e depois foi embora numa semana. Este está fazendo direito, na ordem, o que é mais raro do que parece.
    >>  ............................................
  dialogue.conversations.scene.season.the_turn.explained/2   [105 chars]
    en  Warmer, and warmer in autumn is not the gift it sounds like. Things keep growing that should be stopping.
    >>  ............................................
    pt  Mais quente, e mais quente no outono não é o presente que parece. As coisas continuam crescendo quando deviam parar.
    >>  ............................................
  dialogue.conversations.scene.season.the_turn.explained/3   [109 chars]
    en  I have four years written down. Two were like this and two were not, which is exactly as useful as it sounds.
    >>  ............................................
    pt  Tenho quatro anos anotados. Dois foram assim e dois não, o que é exatamente tão útil quanto soa.
    >>  ............................................
```


### Button `note_the_light` — "The light is lovely just now."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `season.the_turn.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.season.the_turn.note_the_light` — accepted phrasings: "the light is lovely just now"; "the light is lovely just now"; "the evening light has been beautiful"
  - the message must contain one of: `light`, `lovely`, `beautiful`
  - scored words: `light`(1.8), `lovely`(1.8), `beautiful`(1.8), `now`(0.8), `evening`(0.8), `been`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.season.the_turn.respond.note_the_light
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.season.the_turn.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.season.the_turn.respond.note_the_light   [29 chars]
    en  The light is lovely just now.
    >>  ............................................
    pt  A luz está linda agora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2  _(recorded under topic `season.autumn`)_
- Does: session `turn`
- Then opens: `conversations.scene.season.followup`
- …where the player's next choices will be: "That's the year, then."

```text
POOL   dialogue key: dialogue.conversations.scene.season.the_turn.agreed
WHO    VILLAGER — what the player reads after pressing "The light is lovely just now."
       spoken on: conversations.scene.season.the_turn.respond, button `note_the_light`
       leaves the player on: conversations.scene.season.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.the_turn.open.agreed`: the villager accepts. Subject `season.autumn`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:season` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.season.the_turn.agreed/1   [97 chars]
    en  It is, and it lasts about eleven days, and I have never once managed to be unbusy for all eleven.
    >>  ............................................
    pt  Está, e dura uns onze dias, e eu nunca consegui estar desocupada nos onze.
    >>  ............................................
  dialogue.conversations.scene.season.the_turn.agreed/2   [90 chars]
    en  That is the part of autumn nobody puts in the almanac. Everything in there is about grain.
    >>  ............................................
    pt  É a parte do outono que ninguém põe no almanaque. Lá dentro é tudo sobre grão.
    >>  ............................................
  dialogue.conversations.scene.season.the_turn.agreed/3   [105 chars]
    en  I stood in the lane for a while yesterday doing nothing at all. At my age that is practically a festival.
    >>  ............................................
    pt  Fiquei parada na viela um tempo ontem sem fazer nada. Na minha idade isso é praticamente uma festa.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `season.the_turn.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.season.the_turn.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.season.the_turn.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.season.the_turn.respond.leave   [12 chars]
    en  Fair enough.
    >>  ............................................
    pt  Justo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.season.leaving
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.scene.season.the_turn.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.scene.leaving`: the villager accepts. Subject `season.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.season.deep_winter.respond / leave; conversations.scene.season.followup / leave
```

> Written out in full under **`conversations.scene.season.deep_winter.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.season.holiday.followup`

**Reached from 3 route(s):** `conversations.topic.season.holiday.respond` / `ask_tradition`; `conversations.topic.season.holiday.respond` / `accept`; `conversations.topic.season.holiday.respond` / `decline`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.season.holiday.accept` — e.g. "...I will. That's the first time anyone's asked to be counted in."
- `conversations.season.holiday.ask_tradition` — e.g. "Same as we always have. Garlands, too much food, and someone crying by midnight."
- `conversations.season.holiday.decline` — e.g. "Fair enough. It'll come round again — they always do."


```text
POOL   dialogue key: dialogue.conversations.topic.season.holiday.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.season.holiday.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.season.holiday.followup   [23 chars]
    en  That's the day, anyway.
    >>  ............................................
    pt  É esse o dia, enfim.
    >>  ............................................
```


### Button `share_tradition` — "We did something similar where I'm from."

*stance family `self_disclosure` · tone `plain` · answers the beat(s) `season.holiday.accept.to.season.holiday`, `season.holiday.ask_tradition.to.season.holiday`, `season.holiday.decline.to.season.holiday`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `season.holiday.share_tradition` — accepted phrasings: "we did something similar where i am from"; "we had something similar back home"; "similar where i grew up"
  - the message must contain one of: `similar`, `home`, `from`
  - scored words: `similar`(1.5), `where`(0.8), `from`(0.6), `home`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.season.holiday.followup.share_tradition
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.season.holiday.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.season.holiday.followup.share_tradition   [40 chars]
    en  We did something similar where I'm from.
    >>  ............................................
    pt  A gente fazia algo parecido de onde eu venho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `season.holiday.share_tradition`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — familiarity +3, warmth +2  _(recorded under topic `season.holiday.share_tradition`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.season.holiday.share_tradition
WHO    VILLAGER — what the player reads after pressing "We did something similar where I'm from."
       spoken on: conversations.topic.season.holiday.followup, button `share_tradition`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.holiday.share_tradition.terminal`: the villager accepts. Subject `season.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.season.holiday.share_tradition/1   [65 chars]
    en  Did you? Tell me — I collect other people's versions of this day.
    >>  ............................................
    pt  Faziam? Me conta — eu coleciono as versões dos outros deste dia.
    >>  ............................................
  dialogue.conversations.season.holiday.share_tradition/2   [72 chars]
    en  Everywhere does it slightly wrong except here, obviously. Go on, though.
    >>  ............................................
    pt  Todo lugar faz um pouco errado menos aqui, obviamente. Mas conta.
    >>  ............................................
  dialogue.conversations.season.holiday.share_tradition/3   [69 chars]
    en  That's the good thing about the festivals. Everyone brings their own.
    >>  ............................................
    pt  É essa a graça dos festivais. Cada um traz o seu.
    >>  ............................................
```


### Button `compliment` — "The village does this well."

*stance family `encouragement` · tone `plain` · answers the beat(s) `season.holiday.accept.to.season.holiday`, `season.holiday.ask_tradition.to.season.holiday`, `season.holiday.decline.to.season.holiday`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `season.holiday.compliment` — accepted phrasings: "the village does this well"; "you do this beautifully"; "it is lovely here today"
  - the message must contain one of: `village`, `well`, `lovely`, `beautifully`
  - scored words: `village`(1.2), `well`(1.2), `lovely`(1.2), `beautifully`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.season.holiday.followup.compliment
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.season.holiday.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.season.holiday.followup.compliment   [27 chars]
    en  The village does this well.
    >>  ............................................
    pt  A vila faz isso bem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `season.holiday.compliment`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `season.holiday.compliment`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.season.holiday.compliment
WHO    VILLAGER — what the player reads after pressing "The village does this well."
       spoken on: conversations.topic.season.holiday.followup, button `compliment`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.holiday.compliment.terminal`: the villager accepts. Subject `season.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.season.holiday.compliment/1   [67 chars]
    en  We do, don't we. One day a season where nobody's counting the cost.
    >>  ............................................
    pt  A gente faz, né. Um dia por estação em que ninguém conta o custo.
    >>  ............................................
  dialogue.conversations.season.holiday.compliment/2   [67 chars]
    en  It's the one thing this village agrees on. Enjoy it while it lasts.
    >>  ............................................
    pt  É a única coisa em que esta vila concorda. Aproveite enquanto dura.
    >>  ............................................
  dialogue.conversations.season.holiday.compliment/3   [54 chars]
    en  Kind of you to say. We work at it more than we let on.
    >>  ............................................
    pt  Gentil da sua parte dizer. A gente se esforça mais do que demonstra.
    >>  ............................................
```


### Button `grumble` — "It's a lot of fuss, though."

*stance family `respectful_disagreement` · tone `plain` · answers the beat(s) `season.holiday.accept.to.season.holiday`, `season.holiday.ask_tradition.to.season.holiday`, `season.holiday.decline.to.season.holiday`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `season.holiday.grumble` — accepted phrasings: "it is a lot of fuss"; "too much fuss"; "a lot of bother for one day"
  - the message must contain one of: `fuss`, `bother`
  - scored words: `fuss`(1.5), `bother`(1.2), `much`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.season.holiday.followup.grumble
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.season.holiday.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.season.holiday.followup.grumble   [27 chars]
    en  It's a lot of fuss, though.
    >>  ............................................
    pt  Mas é muito estardalhaço.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `gloomy`, `sensitive`, `anxious`, `crabby`, `introverted`, `confident`, `greedy`, `crabby`, `peaceful`
- Does: **hearts +1** — decision id `season.holiday.grumble`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — familiarity +2  _(recorded under topic `season.holiday.grumble`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.season.holiday.grumble.landed
WHO    VILLAGER — what the player reads after pressing "It's a lot of fuss, though."
       spoken on: conversations.topic.season.holiday.followup, button `grumble`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.holiday.grumble.landed.terminal`: the villager accepts. Subject `season.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.season.holiday.grumble.landed/1   [61 chars]
    en  It IS. Someone had to say it. Garlands don't hang themselves.
    >>  ............................................
    pt  É MESMO. Alguém tinha que dizer. Guirlanda não se pendura sozinha.
    >>  ............................................
  dialogue.conversations.season.holiday.grumble.landed/2   [64 chars]
    en  Ha! Every year I swear I'll do less and every year there's more.
    >>  ............................................
    pt  Rá! Todo ano eu juro que vou fazer menos e todo ano tem mais.
    >>  ............................................
  dialogue.conversations.season.holiday.grumble.landed/3   [55 chars]
    en  A great deal of fuss for one evening. Worth it. Barely.
    >>  ............................................
    pt  Muito estardalhaço para uma noite. Vale a pena. Por pouco.
    >>  ............................................
```


**Outcome 2 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `playful`, `peppy`, `upbeat`, `odd`, `relaxed`, `extroverted`, `flirty`, `friendly`, `peaceful`
- Does: **hearts -1** — decision id `season.holiday.grumble`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `season.holiday.grumble`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.season.holiday.grumble.flat
WHO    VILLAGER — what the player reads after pressing "It's a lot of fuss, though."
       spoken on: conversations.topic.season.holiday.followup, button `grumble`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.holiday.grumble.flat.terminal`: the villager accepts. Subject `season.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.season.holiday.grumble.flat/1   [47 chars]
    en  ...It's the one good day, %1$s. Let us have it.
    >>  ............................................
    pt  ...É o único dia bom, %1$s. Deixa a gente ter.
    >>  ............................................
  dialogue.conversations.season.holiday.grumble.flat/2   [45 chars]
    en  Fuss is the point. That's what a festival is.
    >>  ............................................
    pt  O estardalhaço é o ponto. É isso que é um festival.
    >>  ............................................
  dialogue.conversations.season.holiday.grumble.flat/3   [31 chars]
    en  Mm. You needn't come, you know.
    >>  ............................................
    pt  Hm. Você não precisa vir, sabia.
    >>  ............................................
```


**Outcome 3 of 3** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `gloomy`, `sensitive`, `anxious`, `crabby`, `introverted`, `confident`, `greedy`, `crabby`, `peaceful`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `playful`, `peppy`, `upbeat`, `odd`, `relaxed`, `extroverted`, `flirty`, `friendly`, `peaceful`  _(chance -2000)_
- Does: disposition — familiarity +1  _(recorded under topic `season.holiday.grumble`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.season.holiday.grumble.polite
WHO    VILLAGER — what the player reads after pressing "It's a lot of fuss, though."
       spoken on: conversations.topic.season.holiday.followup, button `grumble`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.holiday.grumble.polite.terminal`: the villager accepts. Subject `season.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.season.holiday.grumble.polite/1   [27 chars]
    en  It is. Pleasant fuss, mind.
    >>  ............................................
    pt  É. Estardalhaço agradável, veja bem.
    >>  ............................................
  dialogue.conversations.season.holiday.grumble.polite/2   [52 chars]
    en  Just so, but the alternative is an ordinary Tuesday.
    >>  ............................................
    pt  Pois é, mas a alternativa é uma terça comum.
    >>  ............................................
  dialogue.conversations.season.holiday.grumble.polite/3   [48 chars]
    en  A fair bit, yes. Somebody has to do the hanging.
    >>  ............................................
    pt  Bastante, sim. Alguém tem que pendurar.
    >>  ............................................
```


### Button `leave` — "Enjoy the day."

*stance family `exit` · tone `plain` · answers the beat(s) `season.holiday.accept.to.season.holiday`, `season.holiday.ask_tradition.to.season.holiday`, `season.holiday.decline.to.season.holiday` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.season.holiday.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.season.holiday.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.season.holiday.followup.leave   [14 chars]
    en  Enjoy the day.
    >>  ............................................
    pt  Aproveite o dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.season.holiday.leave
WHO    VILLAGER — what the player reads after pressing "Enjoy the day."
       spoken on: conversations.topic.season.holiday.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.holiday.leave.terminal`: the villager accepts. Subject `season.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.season.holiday.respond / leave
```

```text
  dialogue.conversations.season.holiday.leave/1   [45 chars]
    en  Aye — there's a great deal to do before dusk.
    >>  ............................................
    pt  É — tem muito a fazer antes do anoitecer.
    >>  ............................................
  dialogue.conversations.season.holiday.leave/2   [56 chars]
    en  Go on. Come by the square later if you change your mind.
    >>  ............................................
    pt  Pode ir. Passe na praça depois se mudar de ideia.
    >>  ............................................
  dialogue.conversations.season.holiday.leave/3   [38 chars]
    en  Right you are. Happy day to you, %1$s.
    >>  ............................................
    pt  Isso mesmo. Bom dia de festa para você, %1$s.
    >>  ............................................
```

---


## `conversations.topic.season.holiday.respond`

**Reached from 4 route(s):** `conversations.cat.chitchat` / `season`; `conversations.cat.chitchat` / `season`; `conversations.cat.chitchat` / `season`; `conversations.cat.chitchat` / `season`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.season.holiday.harvest_festival` — e.g. "Harvest festival! Barns full, tables fuller. Sit with us tonight, %1$s — nobody eats alone this evening."
- `conversations.season.holiday.midsummer` — e.g. "Midsummer! Longest day of the year — there'll be a bonfire in the square tonight, and I'll not miss it."
- `conversations.season.holiday.midwinter` — e.g. "Midwinter's night — the year's darkest. We light every candle we've got and dare the dark to outlast us."
- `conversations.season.holiday.spring_bloom` — e.g. "You picked the spring bloom to visit! The whole village is out with flowers in their hair. Even I've got a sprig."


```text
POOL   dialogue key: dialogue.conversations.topic.season.holiday.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.season.holiday.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.season.holiday.respond   [47 chars]
    en  It comes round every year, and I still like it.
    >>  ............................................
    pt  Vem todo ano, e eu ainda gosto.
    >>  ............................................
```


### Button `ask_tradition` — "What do you do for it?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `season.holiday.harvest_festival.to.season.holiday`, `season.holiday.midsummer.to.season.holiday`, `season.holiday.midwinter.to.season.holiday`, `season.holiday.spring_bloom.to.season.holiday`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `season.holiday.ask_tradition` — accepted phrasings: "what do you do for it"; "how do you celebrate"; "what is the tradition"; "how do you mark it"
  - the message must contain one of: `celebrate`, `tradition`, `usually`, `do`, `mark`
  - scored words: `do`(0.5), `celebrate`(1.5), `tradition`(1.5), `usually`(0.8), `mark`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.season.holiday.respond.ask_tradition
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.season.holiday.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.season.holiday.respond.ask_tradition   [22 chars]
    en  What do you do for it?
    >>  ............................................
    pt  O que você faz nesse dia?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, warmth +1  _(recorded under topic `season.holiday.ask_tradition`)_
- Then opens: `conversations.topic.season.holiday.followup`
- …where the player's next choices will be: "We did something similar where I'm from." | "The village does this well." | "It's a lot of fuss, though." | "Enjoy the day."

```text
POOL   dialogue key: dialogue.conversations.season.holiday.ask_tradition
WHO    VILLAGER — what the player reads after pressing "What do you do for it?"
       spoken on: conversations.topic.season.holiday.respond, button `ask_tradition`
       leaves the player on: conversations.topic.season.holiday.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.holiday.ask_tradition.to.season.holiday`: the villager accepts. Subject `season.holiday`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.holiday.ask_tradition/1   [80 chars]
    en  Same as we always have. Garlands, too much food, and someone crying by midnight.
    >>  ............................................
    pt  O mesmo de sempre. Guirlandas, comida demais, e alguém chorando até a meia-noite.
    >>  ............................................
  dialogue.conversations.season.holiday.ask_tradition/2   [74 chars]
    en  The old way, mostly. My mother's way, if I'm honest, and hers before that.
    >>  ............................................
    pt  Do jeito antigo, principalmente. Do jeito da minha mãe, para ser sincero, e da mãe dela antes.
    >>  ............................................
  dialogue.conversations.season.holiday.ask_tradition/3   [81 chars]
    en  You stand in the square until someone hands you a drink. It's a robust tradition.
    >>  ............................................
    pt  Você fica na praça até alguém te dar uma bebida. É uma tradição robusta.
    >>  ............................................
```


### Button `accept` — "Save me a place."

*stance family `restraint` · tone `plain` · answers the beat(s) `season.holiday.harvest_festival.to.season.holiday`, `season.holiday.midsummer.to.season.holiday`, `season.holiday.midwinter.to.season.holiday`, `season.holiday.spring_bloom.to.season.holiday`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `season.holiday.accept` — accepted phrasings: "save me a place"; "i will come"; "count me in"; "i will join you"
  - the message must contain one of: `save`, `place`, `join`, `come`
  - scored words: `save`(1.5), `place`(1.2), `come`(1.0), `join`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.season.holiday.respond.accept
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.season.holiday.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.season.holiday.respond.accept   [16 chars]
    en  Save me a place.
    >>  ............................................
    pt  Guarda um lugar para mim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `season.holiday.accept`, budget `quick`, replay policy `once_per_day`
- Does: disposition — warmth +4, trust +1  _(recorded under topic `season.holiday.accept`)_
- Then opens: `conversations.topic.season.holiday.followup`
- …where the player's next choices will be: "We did something similar where I'm from." | "The village does this well." | "It's a lot of fuss, though." | "Enjoy the day."

```text
POOL   dialogue key: dialogue.conversations.season.holiday.accept
WHO    VILLAGER — what the player reads after pressing "Save me a place."
       spoken on: conversations.topic.season.holiday.respond, button `accept`
       leaves the player on: conversations.topic.season.holiday.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.holiday.accept.to.season.holiday`: the villager accepts. Subject `season.holiday`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.holiday.accept/1   [65 chars]
    en  ...I will. That's the first time anyone's asked to be counted in.
    >>  ............................................
    pt  ...Vou guardar. É a primeira vez que alguém pede para ser incluído.
    >>  ............................................
  dialogue.conversations.season.holiday.accept/2   [63 chars]
    en  A place saved, then. Don't be late — the good bread goes early.
    >>  ............................................
    pt  Um lugar guardado, então. Não se atrase — o pão bom acaba cedo.
    >>  ............................................
  dialogue.conversations.season.holiday.accept/3   [68 chars]
    en  Done. And you'll be introduced to everyone, so brace yourself, %1$s.
    >>  ............................................
    pt  Feito. E você vai ser apresentado a todo mundo, então se prepare, %1$s.
    >>  ............................................
```


### Button `decline` — "I can't make it, but enjoy it."

*stance family `candor` · tone `gentle` · answers the beat(s) `season.holiday.harvest_festival.to.season.holiday`, `season.holiday.midsummer.to.season.holiday`, `season.holiday.midwinter.to.season.holiday`, `season.holiday.spring_bloom.to.season.holiday`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `season.holiday.decline` — accepted phrasings: "i cannot make it"; "i am busy that day"; "i cannot come but enjoy it"
  - the message must contain one of: `cannot`, `busy`, `enjoy`
  - scored words: `cannot`(1.5), `enjoy`(1.0), `make`(0.6), `busy`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.season.holiday.respond.decline
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.season.holiday.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.season.holiday.respond.decline   [30 chars]
    en  I can't make it, but enjoy it.
    >>  ............................................
    pt  Não vou conseguir ir, mas aproveite.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +1  _(recorded under topic `season.holiday.decline`)_
- Then opens: `conversations.topic.season.holiday.followup`
- …where the player's next choices will be: "We did something similar where I'm from." | "The village does this well." | "It's a lot of fuss, though." | "Enjoy the day."

```text
POOL   dialogue key: dialogue.conversations.season.holiday.decline
WHO    VILLAGER — what the player reads after pressing "I can't make it, but enjoy it."
       spoken on: conversations.topic.season.holiday.respond, button `decline`
       leaves the player on: conversations.topic.season.holiday.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.holiday.decline.to.season.holiday`: the villager accepts. Subject `season.holiday`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.holiday.decline/1   [53 chars]
    en  Fair enough. It'll come round again — they always do.
    >>  ............................................
    pt  Justo. Vai voltar — sempre volta.
    >>  ............................................
  dialogue.conversations.season.holiday.decline/2   [60 chars]
    en  Another year, then. I'll save you a story instead of a seat.
    >>  ............................................
    pt  Outro ano, então. Guardo uma história em vez de um lugar.
    >>  ............................................
  dialogue.conversations.season.holiday.decline/3   [60 chars]
    en  No matter. It's the asking that counts more than the coming.
    >>  ............................................
    pt  Não tem problema. Perguntar conta mais do que vir.
    >>  ............................................
```


### Button `leave` — "I'll let you get to it."

*stance family `exit` · tone `plain` · answers the beat(s) `season.holiday.harvest_festival.to.season.holiday`, `season.holiday.midsummer.to.season.holiday`, `season.holiday.midwinter.to.season.holiday`, `season.holiday.spring_bloom.to.season.holiday` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.season.holiday.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.season.holiday.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.season.holiday.respond.leave   [23 chars]
    en  I'll let you get to it.
    >>  ............................................
    pt  Vou deixar você ir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.season.holiday.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get to it."
       spoken on: conversations.topic.season.holiday.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.holiday.leave.terminal`: the villager accepts. Subject `season.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.season.holiday.followup / leave
```

> Written out in full under **`conversations.topic.season.holiday.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.season.toddler.respond`

**Reached from 1 route(s):** `conversations.cat.chitchat` / `season`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.season.toddler` — e.g. "I like the season where the flowers come. Is it that one?"


```text
POOL   dialogue key: dialogue.conversations.topic.season.toddler.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.season.toddler.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.season.toddler.respond   [24 chars]
    en  That's my favourite bit!
    >>  ............................................
    pt  Essa é minha parte favorita!
    >>  ............................................
```


### Button `play_along` — "It's the best one, you're right."

*stance family `encouragement` · tone `playful` · answers the beat(s) `season.toddler.to.season.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `season.toddler.play_along` — accepted phrasings: "it is the best one"; "you are right, it is the best"; "i agree it is the best"
  - the message must contain one of: `best`, `right`, `agree`
  - scored words: `best`(1.5), `right`(1.0), `agree`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.season.toddler.respond.play_along
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.season.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.season.toddler.respond.play_along   [32 chars]
    en  It's the best one, you're right.
    >>  ............................................
    pt  É a melhor, você tem razão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `season.young.play_along`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `season.young.play_along`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.season.toddler.play_along
WHO    VILLAGER — what the player reads after pressing "It's the best one, you're right."
       spoken on: conversations.topic.season.toddler.respond, button `play_along`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.toddler.play_along.terminal`: the villager accepts. Subject `season.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.season.toddler.play_along/1   [24 chars]
    en  It IS! I've been saying!
    >>  ............................................
    pt  É MESMO! Eu venho dizendo!
    >>  ............................................
  dialogue.conversations.season.toddler.play_along/2   [31 chars]
    en  Finally someone agrees with me.
    >>  ............................................
    pt  Enfim alguém concorda comigo.
    >>  ............................................
  dialogue.conversations.season.toddler.play_along/3   [19 chars]
    en  Told you. TOLD you.
    >>  ............................................
    pt  Eu falei. EU FALEI.
    >>  ............................................
```


### Button `ask` — "What do you like about it?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `season.toddler.to.season.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `season.toddler.ask` — accepted phrasings: "what do you like about it"; "why do you like it"; "what is good about it"
  - the message must contain one of: `like`, `why`, `about`
  - scored words: `like`(1.2), `about`(0.5), `why`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.season.toddler.respond.ask
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.season.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.season.toddler.respond.ask   [26 chars]
    en  What do you like about it?
    >>  ............................................
    pt  Do que você gosta nela?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +1  _(recorded under topic `season.young.ask`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.season.toddler.ask
WHO    VILLAGER — what the player reads after pressing "What do you like about it?"
       spoken on: conversations.topic.season.toddler.respond, button `ask`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.toddler.ask.terminal`: the villager asks. Subject `season.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.season.toddler.ask/1   [54 chars]
    en  Everything! But mostly the smell. It smells different.
    >>  ............................................
    pt  Tudo! Mas principalmente o cheiro. Cheira diferente.
    >>  ............................................
  dialogue.conversations.season.toddler.ask/2   [45 chars]
    en  You get to do the thing! You know. The thing.
    >>  ............................................
    pt  Dá para fazer a coisa! Você sabe. A coisa.
    >>  ............................................
  dialogue.conversations.season.toddler.ask/3   [56 chars]
    en  The food's better and nobody makes me go to bed on time.
    >>  ............................................
    pt  A comida é melhor e ninguém me faz dormir na hora.
    >>  ............................................
```


### Button `dismiss` — "They're all much the same."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `season.toddler.to.season.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `season.toddler.dismiss` — accepted phrasings: "they are all much the same"; "they are all alike"; "all the same really"
  - the message must contain one of: `same`, `alike`
  - scored words: `same`(1.5), `much`(0.8), `alike`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.season.toddler.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.season.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.season.toddler.respond.dismiss   [26 chars]
    en  They're all much the same.
    >>  ............................................
    pt  São todas parecidas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `season.young.dismiss`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `season.young.dismiss`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.season.toddler.dismiss
WHO    VILLAGER — what the player reads after pressing "They're all much the same."
       spoken on: conversations.topic.season.toddler.respond, button `dismiss`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.toddler.dismiss.terminal`: the villager dismisss. Subject `season.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.season.toddler.dismiss/1   [12 chars]
    en  They're NOT.
    >>  ............................................
    pt  NÃO são.
    >>  ............................................
  dialogue.conversations.season.toddler.dismiss/2   [33 chars]
    en  That's what grown-ups always say.
    >>  ............................................
    pt  É o que os adultos sempre dizem.
    >>  ............................................
  dialogue.conversations.season.toddler.dismiss/3   [31 chars]
    en  ...Okay. But this one's better.
    >>  ............................................
    pt  ...Tá. Mas essa é melhor.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT. Don't say that, %1$s.
    >>  ............................................
    pt  NÃO são. Não diga isso, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.season.toddler.dismiss/2
    en  ...They are special. To me they are.
    >>  ............................................
    pt  ...São especiais. Pra mim são.
    >>  ............................................
  anxious.dialogue.conversations.season.toddler.dismiss/3
    en  ...Fine. I won't show you the next one.
    >>  ............................................
    pt  ...Tudo bem. Não te mostro a próxima.
    >>  ............................................
  athletic.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT. You'll see, when it changes.
    >>  ............................................
    pt  NÃO são. Você vai ver, quando mudar.
    >>  ............................................
  athletic.dialogue.conversations.season.toddler.dismiss/2
    en  ...They are, in their way. You have to wait for it.
    >>  ............................................
    pt  ...São, do jeito delas. Você tem que esperar.
    >>  ............................................
  athletic.dialogue.conversations.season.toddler.dismiss/3
    en  ...All right. Look again in a month.
    >>  ............................................
    pt  ...Está bem. Olhe de novo em um mês.
    >>  ............................................
  confident.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT.
    >>  ............................................
    pt  NÃO são.
    >>  ............................................
  confident.dialogue.conversations.season.toddler.dismiss/2
    en  They are so. I looked.
    >>  ............................................
    pt  São sim. Eu olhei.
    >>  ............................................
  confident.dialogue.conversations.season.toddler.dismiss/3
    en  ...I know what I saw.
    >>  ............................................
    pt  ...Eu sei o que eu vi.
    >>  ............................................
  crabby.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT.
    >>  ............................................
    pt  NÃO são.
    >>  ............................................
  crabby.dialogue.conversations.season.toddler.dismiss/2
    en  They are so. I looked.
    >>  ............................................
    pt  São sim. Eu olhei.
    >>  ............................................
  crabby.dialogue.conversations.season.toddler.dismiss/3
    en  ...I know what I saw.
    >>  ............................................
    pt  ...Eu sei o que eu vi.
    >>  ............................................
  extroverted.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT. Come and look with me, %1$s.
    >>  ............................................
    pt  NÃO são. Venha olhar comigo, %1$s.
    >>  ............................................
  extroverted.dialogue.conversations.season.toddler.dismiss/2
    en  They are! I wanted to show you and now you've said that.
    >>  ............................................
    pt  São sim! Eu queria te mostrar e agora você disse isso.
    >>  ............................................
  extroverted.dialogue.conversations.season.toddler.dismiss/3
    en  ...Fine. But you'd like them if you looked.
    >>  ............................................
    pt  ...Tudo bem. Mas você ia gostar se olhasse.
    >>  ............................................
  flirty.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT. Come and look with me, %1$s.
    >>  ............................................
    pt  NÃO são. Venha olhar comigo, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.season.toddler.dismiss/2
    en  They are! I wanted to show you and now you've said that.
    >>  ............................................
    pt  São sim! Eu queria te mostrar e agora você disse isso.
    >>  ............................................
  flirty.dialogue.conversations.season.toddler.dismiss/3
    en  ...Fine. But you'd like them if you looked.
    >>  ............................................
    pt  ...Tudo bem. Mas você ia gostar se olhasse.
    >>  ............................................
  friendly.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT. Come and look with me, %1$s.
    >>  ............................................
    pt  NÃO são. Venha olhar comigo, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.season.toddler.dismiss/2
    en  They are! I wanted to show you and now you've said that.
    >>  ............................................
    pt  São sim! Eu queria te mostrar e agora você disse isso.
    >>  ............................................
  friendly.dialogue.conversations.season.toddler.dismiss/3
    en  ...Fine. But you'd like them if you looked.
    >>  ............................................
    pt  ...Tudo bem. Mas você ia gostar se olhasse.
    >>  ............................................
  gloomy.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT. Don't say that, %1$s.
    >>  ............................................
    pt  NÃO são. Não diga isso, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.season.toddler.dismiss/2
    en  ...They are special. To me they are.
    >>  ............................................
    pt  ...São especiais. Pra mim são.
    >>  ............................................
  gloomy.dialogue.conversations.season.toddler.dismiss/3
    en  ...Fine. I won't show you the next one.
    >>  ............................................
    pt  ...Tudo bem. Não te mostro a próxima.
    >>  ............................................
  greedy.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT.
    >>  ............................................
    pt  NÃO são.
    >>  ............................................
  greedy.dialogue.conversations.season.toddler.dismiss/2
    en  They are so. I looked.
    >>  ............................................
    pt  São sim. Eu olhei.
    >>  ............................................
  greedy.dialogue.conversations.season.toddler.dismiss/3
    en  ...I know what I saw.
    >>  ............................................
    pt  ...Eu sei o que eu vi.
    >>  ............................................
  grumpy.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT.
    >>  ............................................
    pt  NÃO são.
    >>  ............................................
  grumpy.dialogue.conversations.season.toddler.dismiss/2
    en  They are so. I looked.
    >>  ............................................
    pt  São sim. Eu olhei.
    >>  ............................................
  grumpy.dialogue.conversations.season.toddler.dismiss/3
    en  ...I know what I saw.
    >>  ............................................
    pt  ...Eu sei o que eu vi.
    >>  ............................................
  introverted.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT.
    >>  ............................................
    pt  NÃO são.
    >>  ............................................
  introverted.dialogue.conversations.season.toddler.dismiss/2
    en  ...They are, though.
    >>  ............................................
    pt  ...Mas são.
    >>  ............................................
  introverted.dialogue.conversations.season.toddler.dismiss/3
    en  ...I like them.
    >>  ............................................
    pt  ...Eu gosto delas.
    >>  ............................................
  lazy.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT. You'll see, when it changes.
    >>  ............................................
    pt  NÃO são. Você vai ver, quando mudar.
    >>  ............................................
  lazy.dialogue.conversations.season.toddler.dismiss/2
    en  ...They are, in their way. You have to wait for it.
    >>  ............................................
    pt  ...São, do jeito delas. Você tem que esperar.
    >>  ............................................
  lazy.dialogue.conversations.season.toddler.dismiss/3
    en  ...All right. Look again in a month.
    >>  ............................................
    pt  ...Está bem. Olhe de novo em um mês.
    >>  ............................................
  odd.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT.
    >>  ............................................
    pt  NÃO são.
    >>  ............................................
  odd.dialogue.conversations.season.toddler.dismiss/2
    en  ...They are, though.
    >>  ............................................
    pt  ...Mas são.
    >>  ............................................
  odd.dialogue.conversations.season.toddler.dismiss/3
    en  ...I like them.
    >>  ............................................
    pt  ...Eu gosto delas.
    >>  ............................................
  peaceful.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT. You'll see, when it changes.
    >>  ............................................
    pt  NÃO são. Você vai ver, quando mudar.
    >>  ............................................
  peaceful.dialogue.conversations.season.toddler.dismiss/2
    en  ...They are, in their way. You have to wait for it.
    >>  ............................................
    pt  ...São, do jeito delas. Você tem que esperar.
    >>  ............................................
  peaceful.dialogue.conversations.season.toddler.dismiss/3
    en  ...All right. Look again in a month.
    >>  ............................................
    pt  ...Está bem. Olhe de novo em um mês.
    >>  ............................................
  peppy.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT! They're the best ones and I've counted.
    >>  ............................................
    pt  NÃO são! São as melhores e eu contei.
    >>  ............................................
  peppy.dialogue.conversations.season.toddler.dismiss/2
    en  They ARE special. You're just not looking properly!
    >>  ............................................
    pt  São especiais SIM. Você é que não está olhando direito!
    >>  ............................................
  peppy.dialogue.conversations.season.toddler.dismiss/3
    en  ...I'll show you tomorrow and then you'll see.
    >>  ............................................
    pt  ...Eu te mostro amanhã e aí você vai ver.
    >>  ............................................
  playful.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT! They're the best ones and I've counted.
    >>  ............................................
    pt  NÃO são! São as melhores e eu contei.
    >>  ............................................
  playful.dialogue.conversations.season.toddler.dismiss/2
    en  They ARE special. You're just not looking properly!
    >>  ............................................
    pt  São especiais SIM. Você é que não está olhando direito!
    >>  ............................................
  playful.dialogue.conversations.season.toddler.dismiss/3
    en  ...I'll show you tomorrow and then you'll see.
    >>  ............................................
    pt  ...Eu te mostro amanhã e aí você vai ver.
    >>  ............................................
  relaxed.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT. You'll see, when it changes.
    >>  ............................................
    pt  NÃO são. Você vai ver, quando mudar.
    >>  ............................................
  relaxed.dialogue.conversations.season.toddler.dismiss/2
    en  ...They are, in their way. You have to wait for it.
    >>  ............................................
    pt  ...São, do jeito delas. Você tem que esperar.
    >>  ............................................
  relaxed.dialogue.conversations.season.toddler.dismiss/3
    en  ...All right. Look again in a month.
    >>  ............................................
    pt  ...Está bem. Olhe de novo em um mês.
    >>  ............................................
  sensitive.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT. Don't say that, %1$s.
    >>  ............................................
    pt  NÃO são. Não diga isso, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.season.toddler.dismiss/2
    en  ...They are special. To me they are.
    >>  ............................................
    pt  ...São especiais. Pra mim são.
    >>  ............................................
  sensitive.dialogue.conversations.season.toddler.dismiss/3
    en  ...Fine. I won't show you the next one.
    >>  ............................................
    pt  ...Tudo bem. Não te mostro a próxima.
    >>  ............................................
  shy.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT.
    >>  ............................................
    pt  NÃO são.
    >>  ............................................
  shy.dialogue.conversations.season.toddler.dismiss/2
    en  ...They are, though.
    >>  ............................................
    pt  ...Mas são.
    >>  ............................................
  shy.dialogue.conversations.season.toddler.dismiss/3
    en  ...I like them.
    >>  ............................................
    pt  ...Eu gosto delas.
    >>  ............................................
  upbeat.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT! They're the best ones and I've counted.
    >>  ............................................
    pt  NÃO são! São as melhores e eu contei.
    >>  ............................................
  upbeat.dialogue.conversations.season.toddler.dismiss/2
    en  They ARE special. You're just not looking properly!
    >>  ............................................
    pt  São especiais SIM. Você é que não está olhando direito!
    >>  ............................................
  upbeat.dialogue.conversations.season.toddler.dismiss/3
    en  ...I'll show you tomorrow and then you'll see.
    >>  ............................................
    pt  ...Eu te mostro amanhã e aí você vai ver.
    >>  ............................................
  witty.dialogue.conversations.season.toddler.dismiss/1
    en  They're NOT! They're the best ones and I've counted.
    >>  ............................................
    pt  NÃO são! São as melhores e eu contei.
    >>  ............................................
  witty.dialogue.conversations.season.toddler.dismiss/2
    en  They ARE special. You're just not looking properly!
    >>  ............................................
    pt  São especiais SIM. Você é que não está olhando direito!
    >>  ............................................
  witty.dialogue.conversations.season.toddler.dismiss/3
    en  ...I'll show you tomorrow and then you'll see.
    >>  ............................................
    pt  ...Eu te mostro amanhã e aí você vai ver.
    >>  ............................................
```

</details>


### Button `leave` — "Off you go."

*stance family `exit` · tone `plain` · answers the beat(s) `season.toddler.to.season.toddler` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.season.toddler.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.season.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.season.toddler.respond.leave   [11 chars]
    en  Off you go.
    >>  ............................................
    pt  Pode ir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.season.toddler.leave
WHO    VILLAGER — what the player reads after pressing "Off you go."
       spoken on: conversations.topic.season.toddler.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.toddler.leave.terminal`: the villager accepts. Subject `season.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.season.toddler.leave/1   [4 chars]
    en  Bye!
    >>  ............................................
    pt  Tchau!
    >>  ............................................
  dialogue.conversations.season.toddler.leave/2   [15 chars]
    en  Okay bye, %1$s!
    >>  ............................................
    pt  Tá, tchau, %1$s!
    >>  ............................................
  dialogue.conversations.season.toddler.leave/3   [31 chars]
    en  See you when it's the next one!
    >>  ............................................
    pt  Até a próxima estação!
    >>  ............................................
```

---


## `conversations.topic.season.turn.followup`

**Reached from 7 route(s):** `conversations.topic.season.turn.respond` / `agree`; `conversations.topic.season.turn.respond` / `agree`; `conversations.topic.season.turn.respond` / `ask_favourite`; `conversations.topic.season.turn.respond` / `ask_favourite`; `conversations.topic.season.turn.respond` / `complain`; `conversations.topic.season.turn.respond` / `complain`; `conversations.topic.season.turn.respond` / `complain`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.season.agree.passive` — e.g. "Suppose so. It turns whether I notice it or not, which is rather restful."
- `conversations.season.ask_favourite.plain` — e.g. "Favourite? None of them, particularly. It's just the ordinary middle of the year."
- `conversations.season.turn.agree` — e.g. "Does it? Huh. I'll take it. That's the first useful thing anyone's said about the weather."
- `conversations.season.turn.ask_favourite` — e.g. "The one just turning, usually. I'm easily persuaded by a change."
- `conversations.season.turn.complain.flat` — e.g. "Already? It's barely started, %1$s."
- `conversations.season.turn.complain.landed` — e.g. "Aye! It goes on too long every single year and nobody admits it."
- `conversations.season.turn.complain.polite` — e.g. "You and everyone. It'll turn soon enough."


```text
POOL   dialogue key: dialogue.conversations.topic.season.turn.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.season.turn.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.season.turn.followup   [24 chars]
    en  Same wheel it always is.
    >>  ............................................
    pt  A mesma roda de sempre.
    >>  ............................................
```


### Button `share_plan` — "Here's what I'm doing with it."

*stance family `self_disclosure` · tone `plain` · answers the beat(s) `season.agree.passive.to.season.turn`, `season.ask_favourite.plain.to.season.turn`, `season.turn.agree.to.season.turn`, `season.turn.ask_favourite.to.season.turn`, `season.turn.complain.flat.to.season.turn`, `season.turn.complain.landed.to.season.turn`, `season.turn.complain.polite.to.season.turn`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `season.turn.share_plan` — accepted phrasings: "here is what i am doing with it"; "my plan for it"; "this is what i am doing"
  - the message must contain one of: `plan`, `doing`, `mine`
  - scored words: `doing`(1.2), `plan`(1.5), `mine`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.season.turn.followup.share_plan
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.season.turn.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.season.turn.followup.share_plan   [30 chars]
    en  Here's what I'm doing with it.
    >>  ............................................
    pt  Aqui está o que vou fazer com ela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `season.turn.share_plan`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — familiarity +3, warmth +1  _(recorded under topic `season.turn.share_plan`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.season.turn.share_plan
WHO    VILLAGER — what the player reads after pressing "Here's what I'm doing with it."
       spoken on: conversations.topic.season.turn.followup, button `share_plan`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.turn.share_plan.terminal`: the villager accepts. Subject `season.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.season.turn.share_plan/1   [58 chars]
    en  Oh, tell me — I like knowing what people do with a season.
    >>  ............................................
    pt  Ah, me conta — gosto de saber o que as pessoas fazem com uma estação.
    >>  ............................................
  dialogue.conversations.season.turn.share_plan/2   [55 chars]
    en  That's more of a plan than I've got. Mine is 'keep up'.
    >>  ............................................
    pt  Isso é mais plano do que eu tenho. O meu é 'acompanhar'.
    >>  ............................................
  dialogue.conversations.season.turn.share_plan/3   [60 chars]
    en  Good. A season with a plan in it goes faster and hurts less.
    >>  ............................................
    pt  Bom. Uma estação com um plano passa mais rápido e dói menos.
    >>  ............................................
```


### Button `look_forward` — "There's a lot to look forward to."

*stance family `encouragement` · tone `plain` · answers the beat(s) `season.agree.passive.to.season.turn`, `season.ask_favourite.plain.to.season.turn`, `season.turn.agree.to.season.turn`, `season.turn.ask_favourite.to.season.turn`, `season.turn.complain.flat.to.season.turn`, `season.turn.complain.landed.to.season.turn`, `season.turn.complain.polite.to.season.turn`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `season.turn.look_forward` — accepted phrasings: "a lot to look forward to"; "good things ahead"; "much to look forward to"
  - the message must contain one of: `forward`, `ahead`, `coming`
  - scored words: `forward`(1.5), `ahead`(1.2), `coming`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.season.turn.followup.look_forward
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.season.turn.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.season.turn.followup.look_forward   [33 chars]
    en  There's a lot to look forward to.
    >>  ............................................
    pt  Tem muita coisa boa pela frente.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `season.turn.look_forward`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `season.turn.look_forward`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.season.turn.look_forward
WHO    VILLAGER — what the player reads after pressing "There's a lot to look forward to."
       spoken on: conversations.topic.season.turn.followup, button `look_forward`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.turn.look_forward.terminal`: the villager accepts. Subject `season.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.season.turn.look_forward/1   [54 chars]
    en  There is, when you list it out like that. I forget to.
    >>  ............................................
    pt  Tem, quando você lista assim. Eu esqueço de listar.
    >>  ............................................
  dialogue.conversations.season.turn.look_forward/2   [75 chars]
    en  True enough. I'd stopped counting the good things. Thanks for the reminder.
    >>  ............................................
    pt  Bem verdade. Eu tinha parado de contar as coisas boas. Obrigado pelo lembrete.
    >>  ............................................
  dialogue.conversations.season.turn.look_forward/3   [58 chars]
    en  You're a more hopeful sort than I am, %1$s. It's catching.
    >>  ............................................
    pt  Você é mais esperançoso que eu, %1$s. É contagioso.
    >>  ............................................
```


### Button `dismiss` — "It's all the same to me."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `season.agree.passive.to.season.turn`, `season.ask_favourite.plain.to.season.turn`, `season.turn.agree.to.season.turn`, `season.turn.ask_favourite.to.season.turn`, `season.turn.complain.flat.to.season.turn`, `season.turn.complain.landed.to.season.turn`, `season.turn.complain.polite.to.season.turn`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `season.turn.dismiss` — accepted phrasings: "it is all the same to me"; "they are all the same"; "does not matter to me"
  - the message must contain one of: `same`, `matter`
  - scored words: `same`(1.5), `all`(0.5), `matter`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.season.turn.followup.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.season.turn.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.season.turn.followup.dismiss   [24 chars]
    en  It's all the same to me.
    >>  ............................................
    pt  Para mim tanto faz.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `season.turn.dismiss`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `season.turn.dismiss`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.season.turn.dismiss
WHO    VILLAGER — what the player reads after pressing "It's all the same to me."
       spoken on: conversations.topic.season.turn.followup, button `dismiss`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.turn.dismiss.terminal`: the villager dismisss. Subject `season.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.season.turn.dismiss/1   [23 chars]
    en  ...Is it. Then why ask.
    >>  ............................................
    pt  ...Ah é. Então por que perguntar.
    >>  ............................................
  dialogue.conversations.season.turn.dismiss/2   [66 chars]
    en  All the same. Right. I'll spare you the rest of my thoughts on it.
    >>  ............................................
    pt  Tanto faz. Certo. Poupo você do resto dos meus pensamentos.
    >>  ............................................
  dialogue.conversations.season.turn.dismiss/3   [47 chars]
    en  Mm. It isn't, but I'll not argue about weather.
    >>  ............................................
    pt  Hm. Não é, mas não vou discutir sobre o tempo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.season.turn.dismiss/1
    en  ...Then why ask me, %1$s. I'd been looking forward to answering.
    >>  ............................................
    pt  ...Então por que me perguntar, %1$s? Eu estava ansioso pra responder.
    >>  ............................................
  anxious.dialogue.conversations.season.turn.dismiss/2
    en  It isn't the same. But I'll not argue about a season.
    >>  ............................................
    pt  Não é igual. Mas eu não vou discutir por uma estação.
    >>  ............................................
  anxious.dialogue.conversations.season.turn.dismiss/3
    en  ...Right. I'll keep the noticing to myself.
    >>  ............................................
    pt  ...Certo. Vou guardar o reparar pra mim.
    >>  ............................................
  athletic.dialogue.conversations.season.turn.dismiss/1
    en  Is it. Then why ask. It's never quite the same twice, mind.
    >>  ............................................
    pt  É mesmo? Então por que perguntar? Mas nunca é bem igual duas vezes.
    >>  ............................................
  athletic.dialogue.conversations.season.turn.dismiss/2
    en  ...Aye, near enough. The differences are small and they're there.
    >>  ............................................
    pt  ...É, mais ou menos. As diferenças são pequenas e estão lá.
    >>  ............................................
  athletic.dialogue.conversations.season.turn.dismiss/3
    en  Right you are. It'll turn whether we discuss it or not.
    >>  ............................................
    pt  Você tem razão. Vai virar, discutindo ou não.
    >>  ............................................
  confident.dialogue.conversations.season.turn.dismiss/1
    en  Is it. Then why ask.
    >>  ............................................
    pt  É mesmo? Então por que perguntar?
    >>  ............................................
  confident.dialogue.conversations.season.turn.dismiss/2
    en  Right. You asked me and you'd already decided.
    >>  ............................................
    pt  Certo. Você perguntou e já tinha decidido.
    >>  ............................................
  confident.dialogue.conversations.season.turn.dismiss/3
    en  ...I'll save the answer next time.
    >>  ............................................
    pt  ...Da próxima eu guardo a resposta.
    >>  ............................................
  crabby.dialogue.conversations.season.turn.dismiss/1
    en  Is it. Then why ask.
    >>  ............................................
    pt  É mesmo? Então por que perguntar?
    >>  ............................................
  crabby.dialogue.conversations.season.turn.dismiss/2
    en  Right. You asked me and you'd already decided.
    >>  ............................................
    pt  Certo. Você perguntou e já tinha decidido.
    >>  ............................................
  crabby.dialogue.conversations.season.turn.dismiss/3
    en  ...I'll save the answer next time.
    >>  ............................................
    pt  ...Da próxima eu guardo a resposta.
    >>  ............................................
  extroverted.dialogue.conversations.season.turn.dismiss/1
    en  ...Is it. Then why ask me, %1$s?
    >>  ............................................
    pt  ...É mesmo? Então por que me perguntar, %1$s?
    >>  ............................................
  extroverted.dialogue.conversations.season.turn.dismiss/2
    en  I'd have shown you what's different, if you'd wanted it.
    >>  ............................................
    pt  Eu teria te mostrado o que muda, se você quisesse.
    >>  ............................................
  extroverted.dialogue.conversations.season.turn.dismiss/3
    en  ...Right. I'll not go on about the season, then.
    >>  ............................................
    pt  ...Certo. Então não falo mais da estação.
    >>  ............................................
  flirty.dialogue.conversations.season.turn.dismiss/1
    en  ...Is it. Then why ask me, %1$s?
    >>  ............................................
    pt  ...É mesmo? Então por que me perguntar, %1$s?
    >>  ............................................
  flirty.dialogue.conversations.season.turn.dismiss/2
    en  I'd have shown you what's different, if you'd wanted it.
    >>  ............................................
    pt  Eu teria te mostrado o que muda, se você quisesse.
    >>  ............................................
  flirty.dialogue.conversations.season.turn.dismiss/3
    en  ...Right. I'll not go on about the season, then.
    >>  ............................................
    pt  ...Certo. Então não falo mais da estação.
    >>  ............................................
  friendly.dialogue.conversations.season.turn.dismiss/1
    en  ...Is it. Then why ask me, %1$s?
    >>  ............................................
    pt  ...É mesmo? Então por que me perguntar, %1$s?
    >>  ............................................
  friendly.dialogue.conversations.season.turn.dismiss/2
    en  I'd have shown you what's different, if you'd wanted it.
    >>  ............................................
    pt  Eu teria te mostrado o que muda, se você quisesse.
    >>  ............................................
  friendly.dialogue.conversations.season.turn.dismiss/3
    en  ...Right. I'll not go on about the season, then.
    >>  ............................................
    pt  ...Certo. Então não falo mais da estação.
    >>  ............................................
  gloomy.dialogue.conversations.season.turn.dismiss/1
    en  ...Then why ask me, %1$s. I'd been looking forward to answering.
    >>  ............................................
    pt  ...Então por que me perguntar, %1$s? Eu estava ansioso pra responder.
    >>  ............................................
  gloomy.dialogue.conversations.season.turn.dismiss/2
    en  It isn't the same. But I'll not argue about a season.
    >>  ............................................
    pt  Não é igual. Mas eu não vou discutir por uma estação.
    >>  ............................................
  gloomy.dialogue.conversations.season.turn.dismiss/3
    en  ...Right. I'll keep the noticing to myself.
    >>  ............................................
    pt  ...Certo. Vou guardar o reparar pra mim.
    >>  ............................................
  greedy.dialogue.conversations.season.turn.dismiss/1
    en  Is it. Then why ask.
    >>  ............................................
    pt  É mesmo? Então por que perguntar?
    >>  ............................................
  greedy.dialogue.conversations.season.turn.dismiss/2
    en  Right. You asked me and you'd already decided.
    >>  ............................................
    pt  Certo. Você perguntou e já tinha decidido.
    >>  ............................................
  greedy.dialogue.conversations.season.turn.dismiss/3
    en  ...I'll save the answer next time.
    >>  ............................................
    pt  ...Da próxima eu guardo a resposta.
    >>  ............................................
  grumpy.dialogue.conversations.season.turn.dismiss/1
    en  Is it. Then why ask.
    >>  ............................................
    pt  É mesmo? Então por que perguntar?
    >>  ............................................
  grumpy.dialogue.conversations.season.turn.dismiss/2
    en  Right. You asked me and you'd already decided.
    >>  ............................................
    pt  Certo. Você perguntou e já tinha decidido.
    >>  ............................................
  grumpy.dialogue.conversations.season.turn.dismiss/3
    en  ...I'll save the answer next time.
    >>  ............................................
    pt  ...Da próxima eu guardo a resposta.
    >>  ............................................
  introverted.dialogue.conversations.season.turn.dismiss/1
    en  ...Is it. Then why ask.
    >>  ............................................
    pt  ...É mesmo? Então por que perguntar?
    >>  ............................................
  introverted.dialogue.conversations.season.turn.dismiss/2
    en  It isn't the same, actually.
    >>  ............................................
    pt  Na verdade não é igual.
    >>  ............................................
  introverted.dialogue.conversations.season.turn.dismiss/3
    en  ...Right. Never mind.
    >>  ............................................
    pt  ...Certo. Deixa pra lá.
    >>  ............................................
  lazy.dialogue.conversations.season.turn.dismiss/1
    en  Is it. Then why ask. It's never quite the same twice, mind.
    >>  ............................................
    pt  É mesmo? Então por que perguntar? Mas nunca é bem igual duas vezes.
    >>  ............................................
  lazy.dialogue.conversations.season.turn.dismiss/2
    en  ...Aye, near enough. The differences are small and they're there.
    >>  ............................................
    pt  ...É, mais ou menos. As diferenças são pequenas e estão lá.
    >>  ............................................
  lazy.dialogue.conversations.season.turn.dismiss/3
    en  Right you are. It'll turn whether we discuss it or not.
    >>  ............................................
    pt  Você tem razão. Vai virar, discutindo ou não.
    >>  ............................................
  odd.dialogue.conversations.season.turn.dismiss/1
    en  ...Is it. Then why ask.
    >>  ............................................
    pt  ...É mesmo? Então por que perguntar?
    >>  ............................................
  odd.dialogue.conversations.season.turn.dismiss/2
    en  It isn't the same, actually.
    >>  ............................................
    pt  Na verdade não é igual.
    >>  ............................................
  odd.dialogue.conversations.season.turn.dismiss/3
    en  ...Right. Never mind.
    >>  ............................................
    pt  ...Certo. Deixa pra lá.
    >>  ............................................
  peaceful.dialogue.conversations.season.turn.dismiss/1
    en  Is it. Then why ask. It's never quite the same twice, mind.
    >>  ............................................
    pt  É mesmo? Então por que perguntar? Mas nunca é bem igual duas vezes.
    >>  ............................................
  peaceful.dialogue.conversations.season.turn.dismiss/2
    en  ...Aye, near enough. The differences are small and they're there.
    >>  ............................................
    pt  ...É, mais ou menos. As diferenças são pequenas e estão lá.
    >>  ............................................
  peaceful.dialogue.conversations.season.turn.dismiss/3
    en  Right you are. It'll turn whether we discuss it or not.
    >>  ............................................
    pt  Você tem razão. Vai virar, discutindo ou não.
    >>  ............................................
  peppy.dialogue.conversations.season.turn.dismiss/1
    en  Is it! Then why ask, %1$s. You've saved us both a conversation.
    >>  ............................................
    pt  É mesmo! Então por que perguntar, %1$s. Você poupou uma conversa aos dois.
    >>  ............................................
  peppy.dialogue.conversations.season.turn.dismiss/2
    en  Right, well. Nothing changes and I'm delighted to have confirmed it.
    >>  ............................................
    pt  Certo, bom. Nada muda e eu estou encantado de ter confirmado.
    >>  ............................................
  peppy.dialogue.conversations.season.turn.dismiss/3
    en  ...Ha. Same as always. Except it isn't, but never mind.
    >>  ............................................
    pt  ...Ha. Igual sempre. Só que não é, mas deixa pra lá.
    >>  ............................................
  playful.dialogue.conversations.season.turn.dismiss/1
    en  Is it! Then why ask, %1$s. You've saved us both a conversation.
    >>  ............................................
    pt  É mesmo! Então por que perguntar, %1$s. Você poupou uma conversa aos dois.
    >>  ............................................
  playful.dialogue.conversations.season.turn.dismiss/2
    en  Right, well. Nothing changes and I'm delighted to have confirmed it.
    >>  ............................................
    pt  Certo, bom. Nada muda e eu estou encantado de ter confirmado.
    >>  ............................................
  playful.dialogue.conversations.season.turn.dismiss/3
    en  ...Ha. Same as always. Except it isn't, but never mind.
    >>  ............................................
    pt  ...Ha. Igual sempre. Só que não é, mas deixa pra lá.
    >>  ............................................
  relaxed.dialogue.conversations.season.turn.dismiss/1
    en  Is it. Then why ask. It's never quite the same twice, mind.
    >>  ............................................
    pt  É mesmo? Então por que perguntar? Mas nunca é bem igual duas vezes.
    >>  ............................................
  relaxed.dialogue.conversations.season.turn.dismiss/2
    en  ...Aye, near enough. The differences are small and they're there.
    >>  ............................................
    pt  ...É, mais ou menos. As diferenças são pequenas e estão lá.
    >>  ............................................
  relaxed.dialogue.conversations.season.turn.dismiss/3
    en  Right you are. It'll turn whether we discuss it or not.
    >>  ............................................
    pt  Você tem razão. Vai virar, discutindo ou não.
    >>  ............................................
  sensitive.dialogue.conversations.season.turn.dismiss/1
    en  ...Then why ask me, %1$s. I'd been looking forward to answering.
    >>  ............................................
    pt  ...Então por que me perguntar, %1$s? Eu estava ansioso pra responder.
    >>  ............................................
  sensitive.dialogue.conversations.season.turn.dismiss/2
    en  It isn't the same. But I'll not argue about a season.
    >>  ............................................
    pt  Não é igual. Mas eu não vou discutir por uma estação.
    >>  ............................................
  sensitive.dialogue.conversations.season.turn.dismiss/3
    en  ...Right. I'll keep the noticing to myself.
    >>  ............................................
    pt  ...Certo. Vou guardar o reparar pra mim.
    >>  ............................................
  shy.dialogue.conversations.season.turn.dismiss/1
    en  ...Is it. Then why ask.
    >>  ............................................
    pt  ...É mesmo? Então por que perguntar?
    >>  ............................................
  shy.dialogue.conversations.season.turn.dismiss/2
    en  It isn't the same, actually.
    >>  ............................................
    pt  Na verdade não é igual.
    >>  ............................................
  shy.dialogue.conversations.season.turn.dismiss/3
    en  ...Right. Never mind.
    >>  ............................................
    pt  ...Certo. Deixa pra lá.
    >>  ............................................
  upbeat.dialogue.conversations.season.turn.dismiss/1
    en  Is it! Then why ask, %1$s. You've saved us both a conversation.
    >>  ............................................
    pt  É mesmo! Então por que perguntar, %1$s. Você poupou uma conversa aos dois.
    >>  ............................................
  upbeat.dialogue.conversations.season.turn.dismiss/2
    en  Right, well. Nothing changes and I'm delighted to have confirmed it.
    >>  ............................................
    pt  Certo, bom. Nada muda e eu estou encantado de ter confirmado.
    >>  ............................................
  upbeat.dialogue.conversations.season.turn.dismiss/3
    en  ...Ha. Same as always. Except it isn't, but never mind.
    >>  ............................................
    pt  ...Ha. Igual sempre. Só que não é, mas deixa pra lá.
    >>  ............................................
  witty.dialogue.conversations.season.turn.dismiss/1
    en  Is it! Then why ask, %1$s. You've saved us both a conversation.
    >>  ............................................
    pt  É mesmo! Então por que perguntar, %1$s. Você poupou uma conversa aos dois.
    >>  ............................................
  witty.dialogue.conversations.season.turn.dismiss/2
    en  Right, well. Nothing changes and I'm delighted to have confirmed it.
    >>  ............................................
    pt  Certo, bom. Nada muda e eu estou encantado de ter confirmado.
    >>  ............................................
  witty.dialogue.conversations.season.turn.dismiss/3
    en  ...Ha. Same as always. Except it isn't, but never mind.
    >>  ............................................
    pt  ...Ha. Igual sempre. Só que não é, mas deixa pra lá.
    >>  ............................................
```

</details>


### Button `leave` — "I'll leave you to the weather."

*stance family `exit` · tone `plain` · answers the beat(s) `season.agree.passive.to.season.turn`, `season.ask_favourite.plain.to.season.turn`, `season.turn.agree.to.season.turn`, `season.turn.ask_favourite.to.season.turn`, `season.turn.complain.flat.to.season.turn`, `season.turn.complain.landed.to.season.turn`, `season.turn.complain.polite.to.season.turn` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.season.turn.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.season.turn.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.season.turn.followup.leave   [30 chars]
    en  I'll leave you to the weather.
    >>  ............................................
    pt  Vou deixar você com o tempo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.season.turn.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the weather."
       spoken on: conversations.topic.season.turn.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.turn.leave.terminal`: the villager accepts. Subject `season.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.season.turn.respond / leave
```

```text
  dialogue.conversations.season.turn.leave/1   [58 chars]
    en  Quite, the light goes early this time of year. Off you go.
    >>  ............................................
    pt  Exato, escurece cedo nesta época. Pode ir.
    >>  ............................................
  dialogue.conversations.season.turn.leave/2   [35 chars]
    en  Right you are. Mind the road, %1$s.
    >>  ............................................
    pt  Isso mesmo. Cuidado com a estrada, %1$s.
    >>  ............................................
  dialogue.conversations.season.turn.leave/3   [45 chars]
    en  Go on. The year'll still be turning tomorrow.
    >>  ............................................
    pt  Pode ir. O ano ainda vai estar virando amanhã.
    >>  ............................................
```

---


## `conversations.topic.season.turn.respond`

**Reached from 6 route(s):** `conversations.cat.chitchat` / `season`; `conversations.cat.chitchat` / `season`; `conversations.cat.chitchat` / `season`; `conversations.cat.chitchat` / `season`; `conversations.cat.chitchat` / `season`; `conversations.cat.chitchat` / `season`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.season.again` — e.g. "We did the seasons already. The wheel hasn't turned since breakfast."
- `conversations.season.any` — e.g. "Season's turning like it always does. I just keep pace with it and try not to grumble too loud."
- `conversations.season.autumn` — e.g. "Autumn already. The leaves are going gold and I've the harvest and the woodpile both on my mind."
- `conversations.season.spring` — e.g. "Spring suits me. Everything's waking up — green on the fields, lambs in the pen, mud on every doorstep."
- `conversations.season.summer` — e.g. "High summer — long days, short tempers by the forge, and cold well-water that's worth its weight in gold."
- `conversations.season.winter` — e.g. "Winter's here, and it doesn't ask permission. I keep the fire fed and my complaints to myself. Mostly."


```text
POOL   dialogue key: dialogue.conversations.topic.season.turn.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.season.turn.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.season.turn.respond   [42 chars]
    en  The year turns whether we watch it or not.
    >>  ............................................
    pt  O ano vira, a gente olhando ou não.
    >>  ............................................
```


### Button `agree` — "It suits you, this time of year."

*stance family `encouragement` · tone `plain` · answers the beat(s) `season.again.to.season.turn`, `season.any.to.season.turn`, `season.autumn.to.season.turn`, `season.spring.to.season.turn`, `season.summer.to.season.turn`, `season.winter.to.season.turn`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `season.turn.agree` — accepted phrasings: "it suits you"; "this season fits you"; "it suits you, this time of year"
  - the message must contain one of: `suits`, `fits`
  - scored words: `suits`(1.5), `fits`(1.5), `you`(0.3)

```text
POOL   dialogue key: dialogue.conversations.topic.season.turn.respond.agree
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.season.turn.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.season.turn.respond.agree   [32 chars]
    en  It suits you, this time of year.
    >>  ............................................
    pt  Combina com você, esta época do ano.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the mood is `passive`
- Does: **hearts +1** — decision id `season.turn.agree`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `season.turn.agree`)_
- Then opens: `conversations.topic.season.turn.followup`
- …where the player's next choices will be: "Here's what I'm doing with it." | "There's a lot to look forward to." | "It's all the same to me." | "I'll leave you to the weather."

```text
POOL   dialogue key: dialogue.conversations.season.agree.passive
WHO    VILLAGER — what the player reads after pressing "It suits you, this time of year."
       spoken on: conversations.topic.season.turn.respond, button `agree`
       leaves the player on: conversations.topic.season.turn.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.agree.passive.to.season.turn`: the villager accepts. Subject `season.turn`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.agree.passive/1   [73 chars]
    en  Suppose so. It turns whether I notice it or not, which is rather restful.
    >>  ............................................
    pt  Deve ser. Vira quer eu repare ou não, o que é bastante repousante.
    >>  ............................................
  dialogue.conversations.season.agree.passive/2   [89 chars]
    en  It comes round. It always comes round. There's a comfort in the ordinariness of it, %1$s.
    >>  ............................................
    pt  Ela volta. Sempre volta. Tem um conforto nessa banalidade, %1$s.
    >>  ............................................
  dialogue.conversations.season.agree.passive/3   [82 chars]
    en  Quite. Nothing much to say about it, and that's rather the point of a good season.
    >>  ............................................
    pt  Exato. Não tem muito o que dizer, e é meio que esse o ponto de uma boa estação.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the mood is `passive`  _(chance -2000)_
- Does: **hearts +1** — decision id `season.turn.agree`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `season.turn.agree`)_
- Then opens: `conversations.topic.season.turn.followup`
- …where the player's next choices will be: "Here's what I'm doing with it." | "There's a lot to look forward to." | "It's all the same to me." | "I'll leave you to the weather."

```text
POOL   dialogue key: dialogue.conversations.season.turn.agree
WHO    VILLAGER — what the player reads after pressing "It suits you, this time of year."
       spoken on: conversations.topic.season.turn.respond, button `agree`
       leaves the player on: conversations.topic.season.turn.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.turn.agree.to.season.turn`: the villager accepts. Subject `season.turn`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.turn.agree/1   [90 chars]
    en  Does it? Huh. I'll take it. That's the first useful thing anyone's said about the weather.
    >>  ............................................
    pt  É mesmo? Hm. Eu aceito. É a primeira coisa útil que alguém disse sobre o tempo.
    >>  ............................................
  dialogue.conversations.season.turn.agree/2   [49 chars]
    en  It's the one I complain about least, so probably.
    >>  ............................................
    pt  É a que eu menos reclamo, então provavelmente.
    >>  ............................................
  dialogue.conversations.season.turn.agree/3   [59 chars]
    en  True enough, maybe. Something about it fits the way I work.
    >>  ............................................
    pt  Bem verdade, talvez. Tem algo nela que combina com o meu jeito de trabalhar.
    >>  ............................................
```


### Button `ask_favourite` — "Which season's yours?"

*stance family `curiosity` · tone `playful` · answers the beat(s) `season.again.to.season.turn`, `season.any.to.season.turn`, `season.autumn.to.season.turn`, `season.spring.to.season.turn`, `season.summer.to.season.turn`, `season.winter.to.season.turn`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `season.turn.ask_favourite` — accepted phrasings: "which season is yours"; "what is your favourite season"; "which do you prefer"
  - the message must contain one of: `favourite`, `favorite`, `which`, `prefer`
  - scored words: `favourite`(1.5), `favorite`(1.5), `which`(1.0), `prefer`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.season.turn.respond.ask_favourite
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.season.turn.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.season.turn.respond.ask_favourite   [21 chars]
    en  Which season's yours?
    >>  ............................................
    pt  Qual estação é a sua?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the festival is `none`
- Fires when: RULED OUT when the `holidays` feature is OFF  _(chance -2000)_
- Does: disposition — familiarity +3, warmth +1  _(recorded under topic `season.turn.ask_favourite`)_
- Then opens: `conversations.topic.season.turn.followup`
- …where the player's next choices will be: "Here's what I'm doing with it." | "There's a lot to look forward to." | "It's all the same to me." | "I'll leave you to the weather."

```text
POOL   dialogue key: dialogue.conversations.season.ask_favourite.plain
WHO    VILLAGER — what the player reads after pressing "Which season's yours?"
       spoken on: conversations.topic.season.turn.respond, button `ask_favourite`
       leaves the player on: conversations.topic.season.turn.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.ask_favourite.plain.to.season.turn`: the villager accepts. Subject `season.turn`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.ask_favourite.plain/1   [81 chars]
    en  Favourite? None of them, particularly. It's just the ordinary middle of the year.
    >>  ............................................
    pt  Favorita? Nenhuma, especialmente. É só o meio comum do ano.
    >>  ............................................
  dialogue.conversations.season.ask_favourite.plain/2   [78 chars]
    en  There's no festival to point at, %1$s. Just the days going by, which suits me.
    >>  ............................................
    pt  Não tem festa para apontar, %1$s. Só os dias passando, o que me serve bem.
    >>  ............................................
  dialogue.conversations.season.ask_favourite.plain/3   [62 chars]
    en  The ones with nothing happening in them. Those are underrated.
    >>  ............................................
    pt  As em que não acontece nada. Essas são subestimadas.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the festival is `none`  _(chance -2000)_
- Does: disposition — familiarity +3, warmth +1  _(recorded under topic `season.turn.ask_favourite`)_
- Then opens: `conversations.topic.season.turn.followup`
- …where the player's next choices will be: "Here's what I'm doing with it." | "There's a lot to look forward to." | "It's all the same to me." | "I'll leave you to the weather."

```text
POOL   dialogue key: dialogue.conversations.season.turn.ask_favourite
WHO    VILLAGER — what the player reads after pressing "Which season's yours?"
       spoken on: conversations.topic.season.turn.respond, button `ask_favourite`
       leaves the player on: conversations.topic.season.turn.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.turn.ask_favourite.to.season.turn`: the villager accepts. Subject `season.turn`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.turn.ask_favourite/1   [64 chars]
    en  The one just turning, usually. I'm easily persuaded by a change.
    >>  ............................................
    pt  A que está virando, geralmente. Sou facilmente convencido por uma mudança.
    >>  ............................................
  dialogue.conversations.season.turn.ask_favourite/2   [57 chars]
    en  Autumn. Everything ripe at once and then the quiet after.
    >>  ............................................
    pt  Outono. Tudo maduro de uma vez e depois o silêncio.
    >>  ............................................
  dialogue.conversations.season.turn.ask_favourite/3   [55 chars]
    en  Whichever one isn't currently happening, if I'm honest.
    >>  ............................................
    pt  A que não estiver acontecendo, para ser sincero.
    >>  ............................................
```


### Button `complain` — "I'll be glad when it's over."

*stance family `respectful_disagreement` · tone `plain` · answers the beat(s) `season.again.to.season.turn`, `season.any.to.season.turn`, `season.autumn.to.season.turn`, `season.spring.to.season.turn`, `season.summer.to.season.turn`, `season.winter.to.season.turn`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `season.turn.complain` — accepted phrasings: "i will be glad when it is over"; "i am sick of this season"; "i am done with it"
  - the message must contain one of: `over`, `sick`, `glad`, `done`
  - scored words: `over`(1.5), `glad`(1.0), `sick`(1.5), `done`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.season.turn.respond.complain
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.season.turn.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.season.turn.respond.complain   [28 chars]
    en  I'll be glad when it's over.
    >>  ............................................
    pt  Vou ficar feliz quando acabar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `gloomy`, `sensitive`, `anxious`, `crabby`, `introverted`, `confident`, `greedy`, `crabby`, `peaceful`
- Does: **hearts +1** — decision id `season.turn.complain`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — familiarity +2  _(recorded under topic `season.turn.complain`)_
- Then opens: `conversations.topic.season.turn.followup`
- …where the player's next choices will be: "Here's what I'm doing with it." | "There's a lot to look forward to." | "It's all the same to me." | "I'll leave you to the weather."

```text
POOL   dialogue key: dialogue.conversations.season.turn.complain.landed
WHO    VILLAGER — what the player reads after pressing "I'll be glad when it's over."
       spoken on: conversations.topic.season.turn.respond, button `complain`
       leaves the player on: conversations.topic.season.turn.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.turn.complain.landed.to.season.turn`: the villager accepts. Subject `season.turn`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.turn.complain.landed/1   [64 chars]
    en  Aye! It goes on too long every single year and nobody admits it.
    >>  ............................................
    pt  É! Dura demais todo santo ano e ninguém admite.
    >>  ............................................
  dialogue.conversations.season.turn.complain.landed/2   [65 chars]
    en  Thank you. Everyone else is being relentlessly cheerful about it.
    >>  ............................................
    pt  Obrigado. Todo mundo está sendo implacavelmente alegre sobre isso.
    >>  ............................................
  dialogue.conversations.season.turn.complain.landed/3   [43 chars]
    en  Right there with you. Roll on the next one.
    >>  ............................................
    pt  Estou com você. Que venha a próxima.
    >>  ............................................
```


**Outcome 2 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `playful`, `peppy`, `upbeat`, `odd`, `relaxed`, `extroverted`, `flirty`
- Does: **hearts -1** — decision id `season.turn.complain`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -2  _(recorded under topic `season.turn.complain`)_
- Then opens: `conversations.topic.season.turn.followup`
- …where the player's next choices will be: "Here's what I'm doing with it." | "There's a lot to look forward to." | "It's all the same to me." | "I'll leave you to the weather."

```text
POOL   dialogue key: dialogue.conversations.season.turn.complain.flat
WHO    VILLAGER — what the player reads after pressing "I'll be glad when it's over."
       spoken on: conversations.topic.season.turn.respond, button `complain`
       leaves the player on: conversations.topic.season.turn.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.turn.complain.flat.to.season.turn`: the villager accepts. Subject `season.turn`, polarity `negative`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.turn.complain.flat/1   [35 chars]
    en  Already? It's barely started, %1$s.
    >>  ............................................
    pt  Já? Mal começou, %1$s.
    >>  ............................................
  dialogue.conversations.season.turn.complain.flat/2   [48 chars]
    en  There's good in it if you look. I'd rather look.
    >>  ............................................
    pt  Tem coisa boa nela se você olhar. Prefiro olhar.
    >>  ............................................
  dialogue.conversations.season.turn.complain.flat/3   [55 chars]
    en  Mm. Wishing a season away wishes the year away with it.
    >>  ............................................
    pt  Hm. Desejar que uma estação passe faz o ano passar junto.
    >>  ............................................
```


**Outcome 3 of 3** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `gloomy`, `sensitive`, `anxious`, `crabby`, `introverted`, `confident`, `greedy`, `crabby`, `peaceful`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `playful`, `peppy`, `upbeat`, `odd`, `relaxed`, `extroverted`, `flirty`  _(chance -2000)_
- Does: disposition — familiarity +1  _(recorded under topic `season.turn.complain`)_
- Then opens: `conversations.topic.season.turn.followup`
- …where the player's next choices will be: "Here's what I'm doing with it." | "There's a lot to look forward to." | "It's all the same to me." | "I'll leave you to the weather."

```text
POOL   dialogue key: dialogue.conversations.season.turn.complain.polite
WHO    VILLAGER — what the player reads after pressing "I'll be glad when it's over."
       spoken on: conversations.topic.season.turn.respond, button `complain`
       leaves the player on: conversations.topic.season.turn.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.turn.complain.polite.to.season.turn`: the villager accepts. Subject `season.turn`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.season.turn.complain.polite/1   [41 chars]
    en  You and everyone. It'll turn soon enough.
    >>  ............................................
    pt  Você e todo mundo. Vai virar logo.
    >>  ............................................
  dialogue.conversations.season.turn.complain.polite/2   [49 chars]
    en  It has its moments. Few of them, but it has them.
    >>  ............................................
    pt  Tem seus momentos. Poucos, mas tem.
    >>  ............................................
  dialogue.conversations.season.turn.complain.polite/3   [43 chars]
    en  So it is, it drags. They all do by the end.
    >>  ............................................
    pt  É assim, se arrasta. Todas se arrastam no fim.
    >>  ............................................
```


### Button `leave` — "I'll get on before the light goes."

*stance family `exit` · tone `plain` · answers the beat(s) `season.again.to.season.turn`, `season.any.to.season.turn`, `season.autumn.to.season.turn`, `season.spring.to.season.turn`, `season.summer.to.season.turn`, `season.winter.to.season.turn` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.season.turn.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.season.turn.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.season.turn.respond.leave   [34 chars]
    en  I'll get on before the light goes.
    >>  ............................................
    pt  Vou seguir antes de escurecer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.season.turn.leave
WHO    VILLAGER — what the player reads after pressing "I'll get on before the light goes."
       spoken on: conversations.topic.season.turn.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `season.turn.leave.terminal`: the villager accepts. Subject `season.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.season.turn.followup / leave
```

> Written out in full under **`conversations.topic.season.turn.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

