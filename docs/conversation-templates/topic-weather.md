# Topic: weather

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `weather` |
| Opened from | question `conversations.cat.chitchat`, button `weather` |
| Depth class (its heart budget) | `quick` |
| Returns to | `conversations.cat.chitchat` |
| Ages that can reach it | toddler, child, teen, adult |
| Stance families it must offer | `empathy`, `practical_help`, `humor`, `dismissal`, `exit` |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.chitchat`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.chitchat.weather
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.chitchat
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.chitchat.weather   [33 chars]
    en  What do you make of this weather?
    >>  ............................................
    pt  O que você acha desse tempo?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.scene.weather.followup`](#conversations-scene-weather-followup)
- [`conversations.scene.weather.the_long_dry.respond`](#conversations-scene-weather-the-long-dry-respond)
- [`conversations.scene.weather.working_in_the_wet.respond`](#conversations-scene-weather-working-in-the-wet-respond)
- [`conversations.topic.weather.mild.followup`](#conversations-topic-weather-mild-followup)
- [`conversations.topic.weather.mild.respond`](#conversations-topic-weather-mild-respond)
- [`conversations.topic.weather.storm.followup`](#conversations-topic-weather-storm-followup)
- [`conversations.topic.weather.storm.respond`](#conversations-topic-weather-storm-respond)
- [`conversations.topic.weather.toddler.respond`](#conversations-topic-weather-toddler-respond)

---

## `conversations.scene.weather.followup`

**Reached from 4 route(s):** `conversations.scene.weather.the_long_dry.respond` / `ask_about_the_well`; `conversations.scene.weather.the_long_dry.respond` / `enjoy_it`; `conversations.scene.weather.working_in_the_wet.respond` / `ask_what_it_stops`; `conversations.scene.weather.working_in_the_wet.respond` / `commiserate`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.weather.the_long_dry.answered` — e.g. "A hand's width since the weekend. That is ordinary for four days and it stops being ordinary at about nine."
- `conversations.scene.weather.the_long_dry.softened` — e.g. "I will. I have eaten outside three evenings running and I have every intention of making it four."
- `conversations.scene.weather.working_in_the_wet.agreed` — e.g. "They have. And the fields need it, which is the sentence everybody says on the fourth day through their teeth."
- `conversations.scene.weather.working_in_the_wet.explained` — e.g. "Anything with a lid off. Half my week is things that must not get wet and the other half is carrying them indoors."


```text
POOL   dialogue key: dialogue.conversations.scene.weather.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.weather.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.weather.followup   [32 chars]
    en  Anything else about the weather?
    >>  ............................................
    pt  Mais alguma coisa sobre o tempo?
    >>  ............................................
```


### Button `leave` — "I'll let the sky get on with it."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:weather.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.weather.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.weather.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.weather.followup.leave   [32 chars]
    en  I'll let the sky get on with it.
    >>  ............................................
    pt  Vou deixar o céu se virar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.weather.leaving
WHO    VILLAGER — what the player reads after pressing "I'll let the sky get on with it."
       spoken on: conversations.scene.weather.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.scene.leaving`: the villager accepts. Subject `weather.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.weather.the_long_dry.respond / leave; conversations.scene.weather.working_in_the_wet.respond / leave
```

```text
  dialogue.conversations.scene.weather.leaving/1   [34 chars]
    en  Aye. Get out in it while it holds.
    >>  ............................................
    pt  Isso. Aproveite enquanto dura.
    >>  ............................................
  dialogue.conversations.scene.weather.leaving/2   [50 chars]
    en  Right you are. It will do what it does either way.
    >>  ............................................
    pt  Pois é. Vai fazer o que quiser de qualquer jeito.
    >>  ............................................
  dialogue.conversations.scene.weather.leaving/3   [40 chars]
    en  Mind how you go. It has a look about it.
    >>  ............................................
    pt  Vá com cuidado. O céu está com uma cara estranha.
    >>  ............................................
```

---


## `conversations.scene.weather.the_long_dry.respond`

**Reached from 1 route(s):** `conversations.cat.chitchat` / `weather`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.weather.the_long_dry` — e.g. "Lovely, and everybody says so, and the people who watch water have gone quiet about it."


```text
POOL   dialogue key: dialogue.conversations.scene.weather.the_long_dry.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.weather.the_long_dry.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.weather.the_long_dry.respond   [17 chars]
    en  This dry stretch.
    >>  ............................................
    pt  Esta estiagem.
    >>  ............................................
```


### Button `ask_about_the_well` — "Is the well dropping?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `weather.the_long_dry.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.weather.the_long_dry.ask_about_the_well` — accepted phrasings: "is the well dropping"; "is the well dropping"; "how is the water holding up"
  - the message must contain one of: `well`, `water`
  - scored words: `well`(1.8), `water`(1.8), `dropping`(0.8), `holding`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.weather.the_long_dry.respond.ask_about_the_well
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.weather.the_long_dry.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.weather.the_long_dry.respond.ask_about_the_well   [21 chars]
    en  Is the well dropping?
    >>  ............................................
    pt  O poço está baixando?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `weather.the_dry_spell`)_
- Does: session `turn`
- Then opens: `conversations.scene.weather.followup`
- …where the player's next choices will be: "I'll let the sky get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.weather.the_long_dry.answered
WHO    VILLAGER — what the player reads after pressing "Is the well dropping?"
       spoken on: conversations.scene.weather.the_long_dry.respond, button `ask_about_the_well`
       leaves the player on: conversations.scene.weather.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.the_long_dry.open.answered`: the villager reports. Subject `weather.the_dry_spell`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:weather` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.weather.the_long_dry.answered/1   [107 chars]
    en  A hand's width since the weekend. That is ordinary for four days and it stops being ordinary at about nine.
    >>  ............................................
    pt  Um palmo desde o fim de semana. É comum em quatro dias e deixa de ser comum lá pelo nono.
    >>  ............................................
  dialogue.conversations.scene.weather.the_long_dry.answered/2   [113 chars]
    en  It is. Nobody has said anything yet because saying it makes it a problem, and a problem needs somebody to own it.
    >>  ............................................
    pt  Está. Ninguém disse nada ainda porque dizer transforma em problema, e problema precisa de um dono.
    >>  ............................................
  dialogue.conversations.scene.weather.the_long_dry.answered/3   [103 chars]
    en  I mark the stone every morning. Eleven years of marks and this is the fourth-fastest I have seen it go.
    >>  ............................................
    pt  Marco a pedra toda manhã. Onze anos de marcas e esta é a quarta descida mais rápida que eu já vi.
    >>  ............................................
```


### Button `enjoy_it` — "Take the good days while they're here."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `weather.the_long_dry.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.weather.the_long_dry.enjoy_it` — accepted phrasings: "take the good days while theyre here"; "take the good days while they are here"; "enjoy the clear days"
  - the message must contain one of: `good`, `enjoy`, `clear`
  - scored words: `good`(1.8), `enjoy`(1.8), `clear`(1.8), `take`(0.8), `days`(0.8), `while`(0.8), `theyre`(0.8), `here`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.weather.the_long_dry.respond.enjoy_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.weather.the_long_dry.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.weather.the_long_dry.respond.enjoy_it   [38 chars]
    en  Take the good days while they're here.
    >>  ............................................
    pt  Aproveite os dias bons enquanto duram.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2  _(recorded under topic `weather.the_dry_spell`)_
- Does: session `turn`
- Then opens: `conversations.scene.weather.followup`
- …where the player's next choices will be: "I'll let the sky get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.weather.the_long_dry.softened
WHO    VILLAGER — what the player reads after pressing "Take the good days while they're here."
       spoken on: conversations.scene.weather.the_long_dry.respond, button `enjoy_it`
       leaves the player on: conversations.scene.weather.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.the_long_dry.open.softened`: the villager accepts. Subject `weather.the_dry_spell`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:weather` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.weather.the_long_dry.softened/1   [97 chars]
    en  I will. I have eaten outside three evenings running and I have every intention of making it four.
    >>  ............................................
    pt  Vou. Já jantei do lado de fora três noites seguidas e pretendo firmemente fazer a quarta.
    >>  ............................................
  dialogue.conversations.scene.weather.the_long_dry.softened/2   [125 chars]
    en  That is the right instruction and I am poor at following it, because I am always half doing arithmetic about the next season.
    >>  ............................................
    pt  É a instrução certa e eu sou ruim em seguir, porque estou sempre meio fazendo contas sobre a próxima estação.
    >>  ............................................
  dialogue.conversations.scene.weather.the_long_dry.softened/3   [134 chars]
    en  The light in the evening this week has been worth the whole summer. I would say that out loud more often if it did not sound peculiar.
    >>  ............................................
    pt  A luz no fim da tarde esta semana valeu o verão inteiro. Eu diria isso em voz alta mais vezes se não soasse esquisito.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `weather.the_long_dry.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.weather.the_long_dry.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.weather.the_long_dry.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.weather.the_long_dry.respond.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.scene.weather.leaving
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.scene.weather.the_long_dry.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.scene.leaving`: the villager accepts. Subject `weather.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.weather.followup / leave; conversations.scene.weather.working_in_the_wet.respond / leave
```

> Written out in full under **`conversations.scene.weather.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.weather.working_in_the_wet.respond`

**Reached from 1 route(s):** `conversations.cat.chitchat` / `weather`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.weather.working_in_the_wet` — e.g. "Everything takes twice as long in this and I have stopped pretending otherwise."


```text
POOL   dialogue key: dialogue.conversations.scene.weather.working_in_the_wet.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.weather.working_in_the_wet.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.weather.working_in_the_wet.respond   [12 chars]
    en  The weather.
    >>  ............................................
    pt  O tempo.
    >>  ............................................
```


### Button `ask_what_it_stops` — "What does it stop you doing?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `weather.working_in_the_wet.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.weather.working_in_the_wet.ask_what_it_stops` — accepted phrasings: "what does it stop you doing"; "what does it stop you doing"; "which jobs does the rain hold up"
  - the message must contain one of: `jobs`, `doing`, `rain`
  - scored words: `jobs`(1.8), `doing`(1.8), `rain`(1.8), `does`(0.8), `stop`(0.8), `which`(0.8), `hold`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.weather.working_in_the_wet.respond.ask_what_it_stops
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.weather.working_in_the_wet.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.weather.working_in_the_wet.respond.ask_what_it_stops   [28 chars]
    en  What does it stop you doing?
    >>  ............................................
    pt  O que isso te impede de fazer?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `weather.working_in_it`)_
- Does: session `turn`
- Then opens: `conversations.scene.weather.followup`
- …where the player's next choices will be: "I'll let the sky get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.weather.working_in_the_wet.explained
WHO    VILLAGER — what the player reads after pressing "What does it stop you doing?"
       spoken on: conversations.scene.weather.working_in_the_wet.respond, button `ask_what_it_stops`
       leaves the player on: conversations.scene.weather.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.working_in_the_wet.open.explained`: the villager explains. Subject `weather.working_in_it`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:weather` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.weather.working_in_the_wet.explained/1   [114 chars]
    en  Anything with a lid off. Half my week is things that must not get wet and the other half is carrying them indoors.
    >>  ............................................
    pt  Qualquer coisa sem tampa. Metade da minha semana são coisas que não podem molhar e a outra metade é carregar tudo para dentro.
    >>  ............................................
  dialogue.conversations.scene.weather.working_in_the_wet.explained/2   [107 chars]
    en  Nothing outright. It just moves every job an hour later and there are only so many hours to move them into.
    >>  ............................................
    pt  Nada de vez. Só empurra cada tarefa uma hora para depois, e só existe um tanto de horas para onde empurrar.
    >>  ............................................
  dialogue.conversations.scene.weather.working_in_the_wet.explained/3   [133 chars]
    en  The walking, mostly. Everything is further in the rain, and I have never been able to explain that to anybody who works under a roof.
    >>  ............................................
    pt  A caminhada, principalmente. Tudo fica mais longe na chuva, e eu nunca consegui explicar isso a quem trabalha sob um teto.
    >>  ............................................
```


### Button `commiserate` — "It's been a grim few days."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `weather.working_in_the_wet.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.weather.working_in_the_wet.commiserate` — accepted phrasings: "its been a grim few days"; "it has been a grim few days"; "these days have been miserable"
  - the message must contain one of: `grim`, `miserable`, `days`
  - scored words: `grim`(1.8), `miserable`(1.8), `days`(1.8), `its`(0.8), `been`(0.8), `few`(0.8), `these`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.weather.working_in_the_wet.respond.commiserate
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.weather.working_in_the_wet.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.weather.working_in_the_wet.respond.commiserate   [26 chars]
    en  It's been a grim few days.
    >>  ............................................
    pt  Foram dias sombrios.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2  _(recorded under topic `weather.working_in_it`)_
- Does: session `turn`
- Then opens: `conversations.scene.weather.followup`
- …where the player's next choices will be: "I'll let the sky get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.weather.working_in_the_wet.agreed
WHO    VILLAGER — what the player reads after pressing "It's been a grim few days."
       spoken on: conversations.scene.weather.working_in_the_wet.respond, button `commiserate`
       leaves the player on: conversations.scene.weather.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.working_in_the_wet.open.agreed`: the villager accepts. Subject `weather.working_in_it`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:weather` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.weather.working_in_the_wet.agreed/1   [110 chars]
    en  They have. And the fields need it, which is the sentence everybody says on the fourth day through their teeth.
    >>  ............................................
    pt  Foram. E os campos precisam, que é a frase que todo mundo diz no quarto dia entre os dentes.
    >>  ............................................
  dialogue.conversations.scene.weather.working_in_the_wet.agreed/2   [69 chars]
    en  Grim is the word. Not bad — grim. Bad weather has an end you can see.
    >>  ............................................
    pt  Sombrio é a palavra. Não ruim — sombrio. Tempo ruim tem um fim que dá para ver.
    >>  ............................................
  dialogue.conversations.scene.weather.working_in_the_wet.agreed/3   [103 chars]
    en  It will break on the day I have nothing planned. It always does, and I have stopped trying to trick it.
    >>  ............................................
    pt  Vai abrir no dia em que eu não tiver nada planejado. Sempre abre, e eu parei de tentar enganar isso.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `weather.working_in_the_wet.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.weather.working_in_the_wet.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.weather.working_in_the_wet.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.weather.working_in_the_wet.respond.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.scene.weather.leaving
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.scene.weather.working_in_the_wet.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.scene.leaving`: the villager accepts. Subject `weather.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.weather.followup / leave; conversations.scene.weather.the_long_dry.respond / leave
```

> Written out in full under **`conversations.scene.weather.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.weather.mild.followup`

**Reached from 7 route(s):** `conversations.topic.weather.mild.respond` / `enjoy`; `conversations.topic.weather.mild.respond` / `enjoy`; `conversations.topic.weather.mild.respond` / `talk_crops`; `conversations.topic.weather.mild.respond` / `talk_crops`; `conversations.topic.weather.mild.respond` / `grumble`; `conversations.topic.weather.mild.respond` / `grumble`; `conversations.topic.weather.mild.respond` / `grumble`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.weather.enjoy.high` — e.g. "Isn't it! I've been finding excuses to be outdoors since breakfast."
- `conversations.weather.mild.enjoy` — e.g. "It is. You have to say so out loud or the good days go by unremarked."
- `conversations.weather.mild.grumble.flat` — e.g. "Would you? It's the first decent day in a fortnight, %1$s."
- `conversations.weather.mild.grumble.landed` — e.g. "Ha! Finally, someone else who isn't delighted by it."
- `conversations.weather.mild.grumble.polite` — e.g. "Everyone wants a different sky. That's why nobody's ever happy."
- `conversations.weather.mild.talk_crops` — e.g. "Every bit of it. Another week like this and the barley's made."
- `conversations.weather.talk_crops.clear` — e.g. "Not a cloud. %2$s like this is what you pray for and then immediately worry about."


```text
POOL   dialogue key: dialogue.conversations.topic.weather.mild.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.weather.mild.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.weather.mild.followup   [18 chars]
    en  Weather's weather.
    >>  ............................................
    pt  Tempo é tempo.
    >>  ............................................
```


### Button `agree` — "You're right about that."

*stance family `encouragement` · tone `plain` · answers the beat(s) `weather.enjoy.high.to.weather.mild`, `weather.mild.enjoy.to.weather.mild`, `weather.mild.grumble.flat.to.weather.mild`, `weather.mild.grumble.landed.to.weather.mild`, `weather.mild.grumble.polite.to.weather.mild`, `weather.mild.talk_crops.to.weather.mild`, `weather.talk_crops.clear.to.weather.mild`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `weather.mild.agree` — accepted phrasings: "you are right about that"; "that is true"; "quite right"
  - the message must contain one of: `right`, `true`, `correct`
  - scored words: `right`(1.5), `true`(1.2), `correct`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.weather.mild.followup.agree
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.weather.mild.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.weather.mild.followup.agree   [24 chars]
    en  You're right about that.
    >>  ............................................
    pt  Você tem razão nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `weather.mild.agree`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `weather.mild.agree`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.weather.mild.agree
WHO    VILLAGER — what the player reads after pressing "You're right about that."
       spoken on: conversations.topic.weather.mild.followup, button `agree`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.mild.agree.terminal`: the villager accepts. Subject `weather.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.weather.mild.agree/1   [66 chars]
    en  Course I am. I've been reading this sky since before you got here.
    >>  ............................................
    pt  Claro que tenho. Leio esse céu desde antes de você chegar.
    >>  ............................................
  dialogue.conversations.weather.mild.agree/2   [65 chars]
    en  It's nice to be agreed with. Doesn't happen much around the well.
    >>  ............................................
    pt  É bom concordarem comigo. Não acontece muito perto do poço.
    >>  ............................................
  dialogue.conversations.weather.mild.agree/3   [47 chars]
    en  Aye. We'll be proved wrong by evening, but aye.
    >>  ............................................
    pt  É. Vamos ser desmentidos até a noite, mas é.
    >>  ............................................
```


### Button `ask_plans` — "Doing anything with the day?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `weather.enjoy.high.to.weather.mild`, `weather.mild.enjoy.to.weather.mild`, `weather.mild.grumble.flat.to.weather.mild`, `weather.mild.grumble.landed.to.weather.mild`, `weather.mild.grumble.polite.to.weather.mild`, `weather.mild.talk_crops.to.weather.mild`, `weather.talk_crops.clear.to.weather.mild`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `weather.mild.ask_plans` — accepted phrasings: "doing anything with the day"; "any plans today"; "what are your plans"
  - the message must contain one of: `plans`, `doing`
  - scored words: `plans`(1.5), `doing`(1.0), `day`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.weather.mild.followup.ask_plans
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.weather.mild.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.weather.mild.followup.ask_plans   [28 chars]
    en  Doing anything with the day?
    >>  ............................................
    pt  Vai fazer algo com o dia?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `weather.mild.ask_plans`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.weather.mild.ask_plans
WHO    VILLAGER — what the player reads after pressing "Doing anything with the day?"
       spoken on: conversations.topic.weather.mild.followup, button `ask_plans`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.mild.ask_plans.terminal`: the villager accepts. Subject `weather.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.weather.mild.ask_plans/1   [67 chars]
    en  Work, then more work, then sitting outside pretending I planned to.
    >>  ............................................
    pt  Trabalho, mais trabalho, e depois sentar lá fora fingindo que planejei.
    >>  ............................................
  dialogue.conversations.weather.mild.ask_plans/2   [59 chars]
    en  Nothing grand. That's rather the luxury of a day like this.
    >>  ............................................
    pt  Nada grandioso. É esse o luxo de um dia desses.
    >>  ............................................
  dialogue.conversations.weather.mild.ask_plans/3   [62 chars]
    en  Getting the outside jobs done before the sky changes its mind.
    >>  ............................................
    pt  Terminar o serviço externo antes de o céu mudar de ideia.
    >>  ............................................
```


### Button `cut_short` — "It's only weather. Anyway."

*stance family `exit` · tone `blunt` · answers the beat(s) `weather.enjoy.high.to.weather.mild`, `weather.mild.enjoy.to.weather.mild`, `weather.mild.grumble.flat.to.weather.mild`, `weather.mild.grumble.landed.to.weather.mild`, `weather.mild.grumble.polite.to.weather.mild`, `weather.mild.talk_crops.to.weather.mild`, `weather.talk_crops.clear.to.weather.mild` · **this is the graceful way out of the node***

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `weather.mild.cut_short` — accepted phrasings: "it is only weather"; "anyway, moving on"; "this is boring"
  - the message must contain one of: `only`, `anyway`, `boring`
  - scored words: `only`(1.2), `weather`(1.2), `anyway`(1.2), `boring`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.weather.mild.followup.cut_short
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.weather.mild.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.weather.mild.followup.cut_short   [26 chars]
    en  It's only weather. Anyway.
    >>  ............................................
    pt  É só o tempo. Enfim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `weather.mild.cut_short`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `weather.mild.cut_short`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.weather.mild.cut_short
WHO    VILLAGER — what the player reads after pressing "It's only weather. Anyway."
       spoken on: conversations.topic.weather.mild.followup, button `cut_short`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.mild.cut_short.terminal`: the villager accepts. Subject `weather.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.weather.mild.cut_short/1   [52 chars]
    en  ...It is only weather, aye. You brought it up, %1$s.
    >>  ............................................
    pt  ...É só o tempo, é. Você que puxou o assunto, %1$s.
    >>  ............................................
  dialogue.conversations.weather.mild.cut_short/2   [47 chars]
    en  Right. I'll stop boring you with the sky, then.
    >>  ............................................
    pt  Certo. Paro de te entediar com o céu, então.
    >>  ............................................
  dialogue.conversations.weather.mild.cut_short/3   [15 chars]
    en  Mm. Off you go.
    >>  ............................................
    pt  Hm. Pode ir.
    >>  ............................................
```


### Button `leave` — "I'll get on."

*stance family `exit` · tone `plain` · answers the beat(s) `weather.enjoy.high.to.weather.mild`, `weather.mild.enjoy.to.weather.mild`, `weather.mild.grumble.flat.to.weather.mild`, `weather.mild.grumble.landed.to.weather.mild`, `weather.mild.grumble.polite.to.weather.mild`, `weather.mild.talk_crops.to.weather.mild`, `weather.talk_crops.clear.to.weather.mild` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.weather.mild.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.weather.mild.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.weather.mild.followup.leave   [12 chars]
    en  I'll get on.
    >>  ............................................
    pt  Vou seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.weather.mild.leave
WHO    VILLAGER — what the player reads after pressing "I'll get on."
       spoken on: conversations.topic.weather.mild.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.mild.leave.terminal`: the villager accepts. Subject `weather.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.weather.mild.respond / leave
```

```text
  dialogue.conversations.weather.mild.leave/1   [34 chars]
    en  Aye. Get out in it while it holds.
    >>  ............................................
    pt  Tá. Aproveite enquanto dura.
    >>  ............................................
  dialogue.conversations.weather.mild.leave/2   [43 chars]
    en  Go on. It'll be raining by supper, mark me.
    >>  ............................................
    pt  Pode ir. Vai estar chovendo no jantar, pode marcar.
    >>  ............................................
  dialogue.conversations.weather.mild.leave/3   [18 chars]
    en  Away you go, %1$s.
    >>  ............................................
    pt  Pode seguir, %1$s.
    >>  ............................................
```

---


## `conversations.topic.weather.mild.respond`

**Reached from 2 route(s):** `conversations.cat.chitchat` / `weather`; `conversations.cat.chitchat` / `weather`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.weather.clear` — e.g. "Not a cloud up there. Days like this you forgive the place its winters."
- `conversations.weather.rain` — e.g. "This rain? The crops love it even if my boots don't. We needed it, honestly."


```text
POOL   dialogue key: dialogue.conversations.topic.weather.mild.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.weather.mild.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.weather.mild.respond   [25 chars]
    en  That's the sky we've got.
    >>  ............................................
    pt  É esse o céu que temos.
    >>  ............................................
```


### Button `enjoy` — "It's a good one, isn't it."

*stance family `encouragement` · tone `plain` · answers the beat(s) `weather.clear.to.weather.mild`, `weather.rain.to.weather.mild`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `weather.mild.enjoy` — accepted phrasings: "it is a good one"; "lovely day"; "it is nice out"
  - the message must contain one of: `lovely`, `nice`, `good`
  - scored words: `good`(0.8), `lovely`(1.5), `nice`(1.2), `one`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.weather.mild.respond.enjoy
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.weather.mild.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.weather.mild.respond.enjoy   [26 chars]
    en  It's a good one, isn't it.
    >>  ............................................
    pt  Está bom, né.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the mood is `happy`
- Does: **hearts +1** — decision id `weather.mild.enjoy`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `weather.mild.enjoy`)_
- Then opens: `conversations.topic.weather.mild.followup`
- …where the player's next choices will be: "You're right about that." | "Doing anything with the day?" | "It's only weather. Anyway." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.weather.enjoy.high
WHO    VILLAGER — what the player reads after pressing "It's a good one, isn't it."
       spoken on: conversations.topic.weather.mild.respond, button `enjoy`
       leaves the player on: conversations.topic.weather.mild.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.enjoy.high.to.weather.mild`: the villager accepts. Subject `weather.mild`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.weather.enjoy.high/1   [67 chars]
    en  Isn't it! I've been finding excuses to be outdoors since breakfast.
    >>  ............................................
    pt  Não é! Venho inventando desculpas para ficar do lado de fora desde o café.
    >>  ............................................
  dialogue.conversations.weather.enjoy.high/2   [74 chars]
    en  A day like this and I forget every complaint I had about this place, %1$s.
    >>  ............................................
    pt  Num dia desses eu esqueço todas as reclamações que tinha deste lugar, %1$s.
    >>  ............................................
  dialogue.conversations.weather.enjoy.high/3   [82 chars]
    en  It is. Days like this are the argument for staying, and I'm having it with myself.
    >>  ............................................
    pt  É. Dias assim são o argumento para ficar, e eu estou discutindo isso comigo mesmo.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the mood is `happy`  _(chance -2000)_
- Does: **hearts +1** — decision id `weather.mild.enjoy`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `weather.mild.enjoy`)_
- Then opens: `conversations.topic.weather.mild.followup`
- …where the player's next choices will be: "You're right about that." | "Doing anything with the day?" | "It's only weather. Anyway." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.weather.mild.enjoy
WHO    VILLAGER — what the player reads after pressing "It's a good one, isn't it."
       spoken on: conversations.topic.weather.mild.respond, button `enjoy`
       leaves the player on: conversations.topic.weather.mild.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.mild.enjoy.to.weather.mild`: the villager accepts. Subject `weather.mild`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.weather.mild.enjoy/1   [69 chars]
    en  It is. You have to say so out loud or the good days go by unremarked.
    >>  ............................................
    pt  Está. Você tem que dizer em voz alta senão os dias bons passam sem comentário.
    >>  ............................................
  dialogue.conversations.weather.mild.enjoy/2   [69 chars]
    en  Aye. I'll be complaining about the heat by noon, but right now — aye.
    >>  ............................................
    pt  É. Vou reclamar do calor ao meio-dia, mas agora — é.
    >>  ............................................
  dialogue.conversations.weather.mild.enjoy/3   [77 chars]
    en  Good of you to notice it. The sky mostly gets mentioned when it's against us.
    >>  ............................................
    pt  Bom você reparar. O céu só é mencionado quando está contra a gente.
    >>  ............................................
```


### Button `talk_crops` — "Is it any use to the fields?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `weather.clear.to.weather.mild`, `weather.rain.to.weather.mild`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `weather.mild.talk_crops` — accepted phrasings: "is it any use to the fields"; "good for the crops"; "will it help the harvest"
  - the message must contain one of: `fields`, `crops`, `harvest`
  - scored words: `fields`(1.5), `crops`(1.5), `harvest`(1.2), `use`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.weather.mild.respond.talk_crops
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.weather.mild.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.weather.mild.respond.talk_crops   [28 chars]
    en  Is it any use to the fields?
    >>  ............................................
    pt  Serve para as plantações?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the sky is `clear`
- Fires when: RULED OUT when the `world` feature is OFF  _(chance -2000)_
- Does: disposition — respect +3  _(recorded under topic `weather.mild.talk_crops`)_
- Then opens: `conversations.topic.weather.mild.followup`
- …where the player's next choices will be: "You're right about that." | "Doing anything with the day?" | "It's only weather. Anyway." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.weather.talk_crops.clear
WHO    VILLAGER — what the player reads after pressing "Is it any use to the fields?"
       spoken on: conversations.topic.weather.mild.respond, button `talk_crops`
       leaves the player on: conversations.topic.weather.mild.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional) · %2$s = weather
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.talk_crops.clear.to.weather.mild`: the villager accepts. Subject `weather.mild`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.weather.talk_crops.clear/1   [82 chars]
    en  Not a cloud. %2$s like this is what you pray for and then immediately worry about.
    >>  ............................................
    pt  Nem uma nuvem. %2$s assim é o que a gente reza para ter e depois passa a temer.
    >>  ............................................
  dialogue.conversations.weather.talk_crops.clear/2   [96 chars]
    en  It's fine growing weather, %1$s. Three more days of it and I'll start complaining about drought.
    >>  ............................................
    pt  É bom tempo de plantio, %1$s. Mais três dias assim e eu começo a reclamar da seca.
    >>  ............................................
  dialogue.conversations.weather.talk_crops.clear/3   [73 chars]
    en  Clear sky, no wind, everything doing what it should. Suspicious, frankly.
    >>  ............................................
    pt  Céu limpo, sem vento, tudo fazendo o que deve. Suspeito, francamente.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the sky is `clear`  _(chance -2000)_
- Does: disposition — respect +3  _(recorded under topic `weather.mild.talk_crops`)_
- Then opens: `conversations.topic.weather.mild.followup`
- …where the player's next choices will be: "You're right about that." | "Doing anything with the day?" | "It's only weather. Anyway." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.weather.mild.talk_crops
WHO    VILLAGER — what the player reads after pressing "Is it any use to the fields?"
       spoken on: conversations.topic.weather.mild.respond, button `talk_crops`
       leaves the player on: conversations.topic.weather.mild.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.mild.talk_crops.to.weather.mild`: the villager accepts. Subject `weather.mild`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.weather.mild.talk_crops/1   [62 chars]
    en  Every bit of it. Another week like this and the barley's made.
    >>  ............................................
    pt  Cada pedacinho. Mais uma semana assim e a cevada está feita.
    >>  ............................................
  dialogue.conversations.weather.mild.talk_crops/2   [62 chars]
    en  Ask the farmer — they'll talk your ear off. Short answer: yes.
    >>  ............................................
    pt  Pergunte a quem planta — vão falar pelos cotovelos. Resposta curta: sim.
    >>  ............................................
  dialogue.conversations.weather.mild.talk_crops/3   [67 chars]
    en  It'll do. We wanted rain a fortnight ago; now we want exactly this.
    >>  ............................................
    pt  Serve. Queríamos chuva há duas semanas; agora queremos exatamente isso.
    >>  ............................................
```


### Button `grumble` — "I could do with less of it, personally."

*stance family `respectful_disagreement` · tone `plain` · answers the beat(s) `weather.clear.to.weather.mild`, `weather.rain.to.weather.mild`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `weather.mild.grumble` — accepted phrasings: "i could do with less of it"; "i have had enough of it"; "i am tired of it"
  - the message must contain one of: `less`, `enough`, `tired`
  - scored words: `less`(1.5), `could`(0.5), `enough`(1.2), `tired`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.weather.mild.respond.grumble
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.weather.mild.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.weather.mild.respond.grumble   [39 chars]
    en  I could do with less of it, personally.
    >>  ............................................
    pt  Eu preferia menos disso, pessoalmente.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `gloomy`, `sensitive`, `anxious`, `crabby`, `introverted`, `confident`, `greedy`, `crabby`, `peaceful`
- Does: **hearts +1** — decision id `weather.mild.grumble`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — familiarity +2  _(recorded under topic `weather.mild.grumble`)_
- Then opens: `conversations.topic.weather.mild.followup`
- …where the player's next choices will be: "You're right about that." | "Doing anything with the day?" | "It's only weather. Anyway." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.weather.mild.grumble.landed
WHO    VILLAGER — what the player reads after pressing "I could do with less of it, personally."
       spoken on: conversations.topic.weather.mild.respond, button `grumble`
       leaves the player on: conversations.topic.weather.mild.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.mild.grumble.landed.to.weather.mild`: the villager accepts. Subject `weather.mild`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.weather.mild.grumble.landed/1   [52 chars]
    en  Ha! Finally, someone else who isn't delighted by it.
    >>  ............................................
    pt  Rá! Enfim, mais alguém que não está encantado com isso.
    >>  ............................................
  dialogue.conversations.weather.mild.grumble.landed/2   [49 chars]
    en  Aye, it's relentless. Nobody's allowed to say so.
    >>  ............................................
    pt  É, é implacável. Ninguém pode dizer isso.
    >>  ............................................
  dialogue.conversations.weather.mild.grumble.landed/3   [70 chars]
    en  Say that in the square and they'll look at you like you kicked a lamb.
    >>  ............................................
    pt  Diga isso na praça e vão olhar como se você tivesse chutado um cordeiro.
    >>  ............................................
```


**Outcome 2 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `playful`, `peppy`, `upbeat`, `odd`, `relaxed`, `extroverted`, `flirty`
- Does: **hearts -1** — decision id `weather.mild.grumble`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -2  _(recorded under topic `weather.mild.grumble`)_
- Then opens: `conversations.topic.weather.mild.followup`
- …where the player's next choices will be: "You're right about that." | "Doing anything with the day?" | "It's only weather. Anyway." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.weather.mild.grumble.flat
WHO    VILLAGER — what the player reads after pressing "I could do with less of it, personally."
       spoken on: conversations.topic.weather.mild.respond, button `grumble`
       leaves the player on: conversations.topic.weather.mild.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.mild.grumble.flat.to.weather.mild`: the villager accepts. Subject `weather.mild`, polarity `negative`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.weather.mild.grumble.flat/1   [58 chars]
    en  Would you? It's the first decent day in a fortnight, %1$s.
    >>  ............................................
    pt  Preferia? É o primeiro dia decente em duas semanas, %1$s.
    >>  ............................................
  dialogue.conversations.weather.mild.grumble.flat/2   [55 chars]
    en  There's no pleasing some people. It's a lovely morning.
    >>  ............................................
    pt  Não tem como agradar certas pessoas. É uma manhã linda.
    >>  ............................................
  dialogue.conversations.weather.mild.grumble.flat/3   [59 chars]
    en  Mm. And now I'm looking at it differently. Thanks for that.
    >>  ............................................
    pt  Hm. E agora estou olhando diferente. Obrigado por isso.
    >>  ............................................
```


**Outcome 3 of 3** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `gloomy`, `sensitive`, `anxious`, `crabby`, `introverted`, `confident`, `greedy`, `crabby`, `peaceful`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `playful`, `peppy`, `upbeat`, `odd`, `relaxed`, `extroverted`, `flirty`  _(chance -2000)_
- Does: disposition — familiarity +1  _(recorded under topic `weather.mild.grumble`)_
- Then opens: `conversations.topic.weather.mild.followup`
- …where the player's next choices will be: "You're right about that." | "Doing anything with the day?" | "It's only weather. Anyway." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.weather.mild.grumble.polite
WHO    VILLAGER — what the player reads after pressing "I could do with less of it, personally."
       spoken on: conversations.topic.weather.mild.respond, button `grumble`
       leaves the player on: conversations.topic.weather.mild.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.mild.grumble.polite.to.weather.mild`: the villager accepts. Subject `weather.mild`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.weather.mild.grumble.polite/1   [63 chars]
    en  Everyone wants a different sky. That's why nobody's ever happy.
    >>  ............................................
    pt  Cada um quer um céu diferente. Por isso ninguém nunca está feliz.
    >>  ............................................
  dialogue.conversations.weather.mild.grumble.polite/2   [31 chars]
    en  Fair. It won't last either way.
    >>  ............................................
    pt  Justo. Não vai durar de todo jeito.
    >>  ............................................
  dialogue.conversations.weather.mild.grumble.polite/3   [31 chars]
    en  You and half the village, then.
    >>  ............................................
    pt  Você e metade da vila, então.
    >>  ............................................
```


### Button `leave` — "I'll make the most of it. Bye."

*stance family `exit` · tone `plain` · answers the beat(s) `weather.clear.to.weather.mild`, `weather.rain.to.weather.mild` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.weather.mild.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.weather.mild.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.weather.mild.respond.leave   [30 chars]
    en  I'll make the most of it. Bye.
    >>  ............................................
    pt  Vou aproveitar. Tchau.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.weather.mild.leave
WHO    VILLAGER — what the player reads after pressing "I'll make the most of it. Bye."
       spoken on: conversations.topic.weather.mild.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.mild.leave.terminal`: the villager accepts. Subject `weather.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.weather.mild.followup / leave
```

> Written out in full under **`conversations.topic.weather.mild.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.weather.storm.followup`

**Reached from 3 route(s):** `conversations.topic.weather.storm.respond` / `concern`; `conversations.topic.weather.storm.respond` / `practical`; `conversations.topic.weather.storm.respond` / `dismiss`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.weather.storm.concern` — e.g. "...I'm alright. Wet, but alright. Kind of you to check on a day like this."
- `conversations.weather.storm.dismiss` — e.g. "Tell that to the roof. And the animals. And my nerves."
- `conversations.weather.storm.practical` — e.g. "The washing, if you're offering. And the small hens panic at thunder."


```text
POOL   dialogue key: dialogue.conversations.topic.weather.storm.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.weather.storm.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.weather.storm.followup   [38 chars]
    en  It'll blow itself out. They always do.
    >>  ............................................
    pt  Vai passar. Sempre passa.
    >>  ............................................
```


### Button `offer_shelter` — "Come sit it out with me."

*stance family `practical_help` · tone `gentle` · answers the beat(s) `weather.storm.concern.to.weather.storm`, `weather.storm.dismiss.to.weather.storm`, `weather.storm.practical.to.weather.storm`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `weather.storm.offer_shelter` — accepted phrasings: "come sit it out with me"; "come inside with me"; "take shelter with me"
  - the message must contain one of: `sit`, `shelter`, `inside`, `come`
  - scored words: `come`(1.2), `sit`(1.5), `inside`(1.2), `shelter`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.weather.storm.followup.offer_shelter
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.weather.storm.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.weather.storm.followup.offer_shelter   [24 chars]
    en  Come sit it out with me.
    >>  ............................................
    pt  Vem esperar passar comigo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `weather.storm.offer_shelter`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +2  _(recorded under topic `weather.storm.offer_shelter`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.weather.storm.offer_shelter
WHO    VILLAGER — what the player reads after pressing "Come sit it out with me."
       spoken on: conversations.topic.weather.storm.followup, button `offer_shelter`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.storm.offer_shelter.terminal`: the villager accepts. Subject `weather.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.weather.storm.offer_shelter/1   [71 chars]
    en  ...I will, actually. Thank you. It's a long storm to sit through alone.
    >>  ............................................
    pt  ...Vou aceitar, na verdade. Obrigado. É uma tempestade longa para passar sozinho.
    >>  ............................................
  dialogue.conversations.weather.storm.offer_shelter/2   [68 chars]
    en  Somewhere dry and somebody in it. That's the whole of what I wanted.
    >>  ............................................
    pt  Um lugar seco e alguém nele. Era só isso que eu queria.
    >>  ............................................
  dialogue.conversations.weather.storm.offer_shelter/3   [55 chars]
    en  Kind of you, %1$s. I'll bring what's left of the bread.
    >>  ............................................
    pt  Gentil da sua parte, %1$s. Levo o que sobrou do pão.
    >>  ............................................
```


### Button `stay_awhile` — "I'll wait it out here with you."

*stance family `restraint` · tone `gentle` · answers the beat(s) `weather.storm.concern.to.weather.storm`, `weather.storm.dismiss.to.weather.storm`, `weather.storm.practical.to.weather.storm`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `weather.storm.stay_awhile` — accepted phrasings: "i will wait it out here with you"; "i will stay with you"; "i will wait here"
  - the message must contain one of: `wait`, `stay`, `here`
  - scored words: `wait`(1.5), `here`(1.0), `stay`(1.2), `with`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.weather.storm.followup.stay_awhile
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.weather.storm.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.weather.storm.followup.stay_awhile   [31 chars]
    en  I'll wait it out here with you.
    >>  ............................................
    pt  Vou esperar passar aqui com você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `weather.storm.stay_awhile`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `weather.storm.stay_awhile`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.weather.storm.stay_awhile
WHO    VILLAGER — what the player reads after pressing "I'll wait it out here with you."
       spoken on: conversations.topic.weather.storm.followup, button `stay_awhile`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.storm.stay_awhile.terminal`: the villager accepts. Subject `weather.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.weather.storm.stay_awhile/1   [58 chars]
    en  You'd wait it out here? Alright. It's better with company.
    >>  ............................................
    pt  Você esperaria aqui? Tudo bem. É melhor com companhia.
    >>  ............................................
  dialogue.conversations.weather.storm.stay_awhile/2   [48 chars]
    en  Suit yourself — but you'll be as soaked as I am.
    >>  ............................................
    pt  Como quiser — mas vai ficar tão encharcado quanto eu.
    >>  ............................................
  dialogue.conversations.weather.storm.stay_awhile/3   [65 chars]
    en  That's decent of you. Storms are long when you're counting alone.
    >>  ............................................
    pt  Isso é decente da sua parte. Tempestades são longas quando se conta sozinho.
    >>  ............................................
```


### Button `joke` — "Good weather for staying in bed."

*stance family `humor` · tone `playful` · answers the beat(s) `weather.storm.concern.to.weather.storm`, `weather.storm.dismiss.to.weather.storm`, `weather.storm.practical.to.weather.storm`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `weather.storm.joke` — accepted phrasings: "good weather for staying in bed"; "perfect sleeping weather"; "weather for bed"
  - the message must contain one of: `bed`, `staying`, `sleeping`
  - scored words: `bed`(1.5), `staying`(1.2), `sleeping`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.weather.storm.followup.joke
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.weather.storm.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.weather.storm.followup.joke   [32 chars]
    en  Good weather for staying in bed.
    >>  ............................................
    pt  Bom tempo para ficar na cama.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `playful`, `peppy`, `upbeat`, `odd`, `relaxed`, `extroverted`, `flirty`
- Does: **hearts +1** — decision id `weather.storm.joke`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `weather.storm.joke`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.weather.storm.joke.landed
WHO    VILLAGER — what the player reads after pressing "Good weather for staying in bed."
       spoken on: conversations.topic.weather.storm.followup, button `joke`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.storm.joke.landed.terminal`: the villager accepts. Subject `weather.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.weather.storm.joke.landed/1   [58 chars]
    en  Ha! It is. Best weather there is, if you've nowhere to be.
    >>  ............................................
    pt  Rá! É mesmo. O melhor tempo que existe, se você não tem onde estar.
    >>  ............................................
  dialogue.conversations.weather.storm.joke.landed/2   [61 chars]
    en  Now there's a plan. Wake me when the sky's finished shouting.
    >>  ............................................
    pt  Aí está um plano. Me acorda quando o céu parar de gritar.
    >>  ............................................
  dialogue.conversations.weather.storm.joke.landed/3   [50 chars]
    en  You've got the right idea and the wrong job, %1$s.
    >>  ............................................
    pt  Você teve a ideia certa e tem o trabalho errado, %1$s.
    >>  ............................................
```


**Outcome 2 of 3** — base weight `0`

- Fires when: weighted +100 when the personality is `gloomy`, `sensitive`, `anxious`, `crabby`, `introverted`
- Does: **hearts -1** — decision id `weather.storm.joke`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — tension +3  _(recorded under topic `weather.storm.joke`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.weather.storm.joke.flat
WHO    VILLAGER — what the player reads after pressing "Good weather for staying in bed."
       spoken on: conversations.topic.weather.storm.followup, button `joke`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.storm.joke.flat.terminal`: the villager accepts. Subject `weather.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.weather.storm.joke.flat/1   [62 chars]
    en  Some of us can't. The animals don't care what the sky's doing.
    >>  ............................................
    pt  Alguns de nós não podem. Os animais não ligam para o que o céu está fazendo.
    >>  ............................................
  dialogue.conversations.weather.storm.joke.flat/2   [49 chars]
    en  Mm. I'd rather it stopped than be funny about it.
    >>  ............................................
    pt  Hm. Eu preferia que parasse a fazer piada disso.
    >>  ............................................
  dialogue.conversations.weather.storm.joke.flat/3   [41 chars]
    en  ...Right. Very restful for you, I'm sure.
    >>  ............................................
    pt  ...Certo. Muito descansado para você, imagino.
    >>  ............................................
```


**Outcome 3 of 3** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `playful`, `peppy`, `upbeat`, `odd`, `relaxed`, `extroverted`, `flirty`  _(chance -2000)_
- Fires when: RULED OUT when the personality is `gloomy`, `sensitive`, `anxious`, `crabby`, `introverted`  _(chance -2000)_
- Does: disposition — warmth +1  _(recorded under topic `weather.storm.joke`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.weather.storm.joke.polite
WHO    VILLAGER — what the player reads after pressing "Good weather for staying in bed."
       spoken on: conversations.topic.weather.storm.followup, button `joke`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.storm.joke.polite.terminal`: the villager accepts. Subject `weather.talk`, polarity `positive`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.weather.storm.joke.polite/1   [32 chars]
    en  It would be, if the roof agreed.
    >>  ............................................
    pt  Seria, se o telhado concordasse.
    >>  ............................................
  dialogue.conversations.weather.storm.joke.polite/2   [33 chars]
    en  Aye, there's that. Small mercies.
    >>  ............................................
    pt  É, tem isso. Pequenas graças.
    >>  ............................................
  dialogue.conversations.weather.storm.joke.polite/3   [52 chars]
    en  If only. There's always something needs doing in it.
    >>  ............................................
    pt  Quem dera. Sempre tem algo para fazer nisso.
    >>  ............................................
```


### Button `leave` — "Stay dry."

*stance family `exit` · tone `plain` · answers the beat(s) `weather.storm.concern.to.weather.storm`, `weather.storm.dismiss.to.weather.storm`, `weather.storm.practical.to.weather.storm` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.weather.storm.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.weather.storm.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.weather.storm.followup.leave   [9 chars]
    en  Stay dry.
    >>  ............................................
    pt  Fica seco.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.weather.storm.leave
WHO    VILLAGER — what the player reads after pressing "Stay dry."
       spoken on: conversations.topic.weather.storm.followup, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.storm.leave.terminal`: the villager accepts. Subject `weather.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.weather.storm.respond / leave
```

```text
  dialogue.conversations.weather.storm.leave/1   [59 chars]
    en  Aye — you get in too. Mind the road, it floods by the gate.
    >>  ............................................
    pt  É — entre você também. Cuidado com a estrada, alaga perto do portão.
    >>  ............................................
  dialogue.conversations.weather.storm.leave/2   [54 chars]
    en  Go on. Don't stand under the big elm, whatever you do.
    >>  ............................................
    pt  Pode ir. Não fique embaixo do olmo grande, de jeito nenhum.
    >>  ............................................
  dialogue.conversations.weather.storm.leave/3   [53 chars]
    en  Right. Dry socks and a door between you and it, %1$s.
    >>  ............................................
    pt  Certo. Meias secas e uma porta entre você e isso, %1$s.
    >>  ............................................
```

---


## `conversations.topic.weather.storm.respond`

**Reached from 1 route(s):** `conversations.cat.chitchat` / `weather`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.weather.storm` — e.g. "Wild sky today. I've got the shutters latched and a candle ready. Best stay in if you can, %1$s."


```text
POOL   dialogue key: dialogue.conversations.topic.weather.storm.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.weather.storm.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.weather.storm.respond   [17 chars]
    en  Wild sky, anyway.
    >>  ............................................
    pt  Céu bravo, enfim.
    >>  ............................................
```


### Button `concern` — "Are you alright out in this?"

*stance family `empathy` · tone `gentle` · answers the beat(s) `weather.storm.to.weather.storm`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `weather.storm.concern` — accepted phrasings: "are you alright out in this"; "are you okay in this"; "are you safe out here"
  - the message must contain one of: `alright`, `okay`, `safe`
  - scored words: `alright`(1.5), `okay`(1.2), `out`(0.5), `safe`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.weather.storm.respond.concern
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.weather.storm.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.weather.storm.respond.concern   [28 chars]
    en  Are you alright out in this?
    >>  ............................................
    pt  Você está bem nisso aí fora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `weather.storm.concern`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3, trust +1  _(recorded under topic `weather.storm.concern`)_
- Then opens: `conversations.topic.weather.storm.followup`
- …where the player's next choices will be: "Come sit it out with me." | "I'll wait it out here with you." | "Good weather for staying in bed." | "Stay dry."

```text
POOL   dialogue key: dialogue.conversations.weather.storm.concern
WHO    VILLAGER — what the player reads after pressing "Are you alright out in this?"
       spoken on: conversations.topic.weather.storm.respond, button `concern`
       leaves the player on: conversations.topic.weather.storm.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.storm.concern.to.weather.storm`: the villager accepts. Subject `weather.storm`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.weather.storm.concern/1   [74 chars]
    en  ...I'm alright. Wet, but alright. Kind of you to check on a day like this.
    >>  ............................................
    pt  ...Estou bem. Molhado, mas bem. Gentil da sua parte perguntar num dia desses.
    >>  ............................................
  dialogue.conversations.weather.storm.concern/2   [57 chars]
    en  Better for being asked, %1$s. Most folk run past in this.
    >>  ............................................
    pt  Melhor por ter sido perguntado, %1$s. A maioria passa correndo nisso.
    >>  ............................................
  dialogue.conversations.weather.storm.concern/3   [74 chars]
    en  I've been out in worse. Still — it's nice not to be invisible in the rain.
    >>  ............................................
    pt  Já enfrentei pior. Mesmo assim — é bom não ser invisível na chuva.
    >>  ............................................
```


### Button `practical` — "Anything need bringing in?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `weather.storm.to.weather.storm`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `weather.storm.practical` — accepted phrasings: "anything need bringing in"; "anything to bring inside"; "need anything secured"
  - the message must contain one of: `bringing`, `inside`, `secure`, `need`
  - scored words: `bringing`(1.5), `inside`(1.2), `need`(0.6), `secure`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.weather.storm.respond.practical
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.weather.storm.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.weather.storm.respond.practical   [26 chars]
    en  Anything need bringing in?
    >>  ............................................
    pt  Precisa recolher alguma coisa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `weather.storm.practical`)_
- Then opens: `conversations.topic.weather.storm.followup`
- …where the player's next choices will be: "Come sit it out with me." | "I'll wait it out here with you." | "Good weather for staying in bed." | "Stay dry."

```text
POOL   dialogue key: dialogue.conversations.weather.storm.practical
WHO    VILLAGER — what the player reads after pressing "Anything need bringing in?"
       spoken on: conversations.topic.weather.storm.respond, button `practical`
       leaves the player on: conversations.topic.weather.storm.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.storm.practical.to.weather.storm`: the villager accepts. Subject `weather.storm`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.weather.storm.practical/1   [69 chars]
    en  The washing, if you're offering. And the small hens panic at thunder.
    >>  ............................................
    pt  A roupa, se você estiver oferecendo. E as galinhas pequenas surtam com trovão.
    >>  ............................................
  dialogue.conversations.weather.storm.practical/2   [64 chars]
    en  Shutters on the north side. They rattle loose every single time.
    >>  ............................................
    pt  As venezianas do lado norte. Soltam todas as vezes.
    >>  ............................................
  dialogue.conversations.weather.storm.practical/3   [56 chars]
    en  Now that you mention it — yes. Two hands would halve it.
    >>  ............................................
    pt  Agora que você falou — sim. Duas mãos cortariam pela metade.
    >>  ............................................
```


### Button `dismiss` — "It's only a bit of thunder."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `weather.storm.to.weather.storm`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `weather.storm.dismiss` — accepted phrasings: "it is only thunder"; "just a bit of thunder"; "only a bit of weather"
  - the message must contain one of: `only`, `thunder`, `bit`, `just`
  - scored words: `only`(1.2), `thunder`(1.2), `bit`(1.0), `just`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.weather.storm.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.weather.storm.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.weather.storm.respond.dismiss   [27 chars]
    en  It's only a bit of thunder.
    >>  ............................................
    pt  É só um trovãozinho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `weather.storm.dismiss`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -2, tension +4  _(recorded under topic `weather.storm.dismiss`)_
- Then opens: `conversations.topic.weather.storm.followup`
- …where the player's next choices will be: "Come sit it out with me." | "I'll wait it out here with you." | "Good weather for staying in bed." | "Stay dry."

```text
POOL   dialogue key: dialogue.conversations.weather.storm.dismiss
WHO    VILLAGER — what the player reads after pressing "It's only a bit of thunder."
       spoken on: conversations.topic.weather.storm.respond, button `dismiss`
       leaves the player on: conversations.topic.weather.storm.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.storm.dismiss.to.weather.storm`: the villager resists. Subject `weather.storm`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.weather.storm.dismiss/1   [54 chars]
    en  Tell that to the roof. And the animals. And my nerves.
    >>  ............................................
    pt  Diga isso para o telhado. E para os animais. E para os meus nervos.
    >>  ............................................
  dialogue.conversations.weather.storm.dismiss/2   [65 chars]
    en  A bit of thunder took the mill's roof off two winters back, %1$s.
    >>  ............................................
    pt  Um trovãozinho arrancou o telhado do moinho dois invernos atrás, %1$s.
    >>  ............................................
  dialogue.conversations.weather.storm.dismiss/3   [58 chars]
    en  Easy to say from under a hood. Go and stand in it a while.
    >>  ............................................
    pt  Fácil dizer debaixo de um capuz. Vá ficar nele um pouco.
    >>  ............................................
```


### Button `leave` — "Get inside. I'll go."

*stance family `exit` · tone `plain` · answers the beat(s) `weather.storm.to.weather.storm` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.weather.storm.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.weather.storm.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.weather.storm.respond.leave   [20 chars]
    en  Get inside. I'll go.
    >>  ............................................
    pt  Entra. Vou indo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.weather.storm.leave
WHO    VILLAGER — what the player reads after pressing "Get inside. I'll go."
       spoken on: conversations.topic.weather.storm.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.storm.leave.terminal`: the villager accepts. Subject `weather.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.topic.weather.storm.followup / leave
```

> Written out in full under **`conversations.topic.weather.storm.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.weather.toddler.respond`

**Reached from 1 route(s):** `conversations.cat.chitchat` / `weather`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.weather.toddler` — e.g. "Sky water! I like jumping in the puddles after."


```text
POOL   dialogue key: dialogue.conversations.topic.weather.toddler.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.weather.toddler.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.weather.toddler.respond   [21 chars]
    en  That's the sky today!
    >>  ............................................
    pt  É esse o céu de hoje!
    >>  ............................................
```


### Button `play_along` — "You're right — I can see it too."

*stance family `encouragement` · tone `playful` · answers the beat(s) `weather.toddler.to.weather.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `weather.toddler.play_along` — accepted phrasings: "you are right i can see it too"; "i can see it too"; "you are right about the sky"
  - the message must contain one of: `see`, `right`
  - scored words: `see`(1.4), `right`(1.2), `too`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.weather.toddler.respond.play_along
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.weather.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.weather.toddler.respond.play_along   [32 chars]
    en  You're right — I can see it too.
    >>  ............................................
    pt  Você tem razão — eu também vejo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `weather.young.play_along`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `weather.young.play_along`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.weather.toddler.play_along
WHO    VILLAGER — what the player reads after pressing "You're right — I can see it too."
       spoken on: conversations.topic.weather.toddler.respond, button `play_along`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.toddler.play_along.terminal`: the villager accepts. Subject `weather.talk`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.weather.toddler.play_along/1   [47 chars]
    en  YES! You SEE it! Grown-ups never look properly.
    >>  ............................................
    pt  SIM! Você VÊ! Adulto nunca olha direito.
    >>  ............................................
  dialogue.conversations.weather.toddler.play_along/2   [52 chars]
    en  See? SEE? I told everyone and nobody listened to me.
    >>  ............................................
    pt  Viu? VIU? Eu falei para todo mundo e ninguém me escutou.
    >>  ............................................
  dialogue.conversations.weather.toddler.play_along/3   [59 chars]
    en  I knew you'd get it. You're the only one who gets it, %1$s.
    >>  ............................................
    pt  Eu sabia que você ia entender. Você é o único que entende, %1$s.
    >>  ............................................
```


### Button `ask` — "What's your favourite kind of sky?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `weather.toddler.to.weather.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `weather.toddler.ask` — accepted phrasings: "what is your favourite kind of sky"; "which sky is best"; "favourite weather"
  - the message must contain one of: `favourite`, `favorite`, `best`
  - scored words: `favourite`(1.5), `favorite`(1.5), `best`(1.2), `kind`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.weather.toddler.respond.ask
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.weather.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.weather.toddler.respond.ask   [34 chars]
    en  What's your favourite kind of sky?
    >>  ............................................
    pt  Qual seu tipo de céu favorito?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +1  _(recorded under topic `weather.young.ask`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.weather.toddler.ask
WHO    VILLAGER — what the player reads after pressing "What's your favourite kind of sky?"
       spoken on: conversations.topic.weather.toddler.respond, button `ask`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.toddler.ask.terminal`: the villager asks. Subject `weather.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.weather.toddler.ask/1   [49 chars]
    en  The one with the pink bits at the end of the day.
    >>  ............................................
    pt  Aquele com as partes rosas no fim do dia.
    >>  ............................................
  dialogue.conversations.weather.toddler.ask/2   [44 chars]
    en  Puddle sky! Best sky. You get puddles after.
    >>  ............................................
    pt  Céu de poça! O melhor céu. Depois tem poça.
    >>  ............................................
  dialogue.conversations.weather.toddler.ask/3   [35 chars]
    en  Snow sky. It goes all quiet before.
    >>  ............................................
    pt  Céu de neve. Fica tudo quieto antes.
    >>  ............................................
```


### Button `dismiss` — "It's just clouds."

*stance family `dismissal` · tone `blunt` · answers the beat(s) `weather.toddler.to.weather.toddler`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `weather.toddler.dismiss` — accepted phrasings: "they are just clouds"; "it is only clouds"; "just clouds"
  - the message must contain one of: `clouds`, `just`, `only`
  - scored words: `just`(1.0), `clouds`(1.5), `only`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.weather.toddler.respond.dismiss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.weather.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.weather.toddler.respond.dismiss   [17 chars]
    en  It's just clouds.
    >>  ............................................
    pt  São só nuvens.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `weather.young.dismiss`, budget `quick`, replay policy `daily_repeat`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `weather.young.dismiss`)_
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.weather.toddler.dismiss
WHO    VILLAGER — what the player reads after pressing "It's just clouds."
       spoken on: conversations.topic.weather.toddler.respond, button `dismiss`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.toddler.dismiss.terminal`: the villager dismisss. Subject `weather.talk`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.weather.toddler.dismiss/1   [24 chars]
    en  They're not JUST clouds.
    >>  ............................................
    pt  Não são SÓ nuvens.
    >>  ............................................
  dialogue.conversations.weather.toddler.dismiss/2   [28 chars]
    en  You're not looking properly.
    >>  ............................................
    pt  Você não está olhando direito.
    >>  ............................................
  dialogue.conversations.weather.toddler.dismiss/3   [29 chars]
    en  ...Okay. But I'm still right.
    >>  ............................................
    pt  ...Tá. Mas eu continuo certo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds. Don't say that, %1$s.
    >>  ............................................
    pt  NÃO são só nuvens. Não diga isso, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.weather.toddler.dismiss/2
    en  ...I like them. That's all.
    >>  ............................................
    pt  ...Eu gosto delas. Só isso.
    >>  ............................................
  anxious.dialogue.conversations.weather.toddler.dismiss/3
    en  ...Fine. I'll watch them by myself.
    >>  ............................................
    pt  ...Tudo bem. Vou olhar sozinho.
    >>  ............................................
  athletic.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds. Give them a minute and they'll be something.
    >>  ............................................
    pt  NÃO são só nuvens. Dê um minuto e elas viram algo.
    >>  ............................................
  athletic.dialogue.conversations.weather.toddler.dismiss/2
    en  ...They change. That's the whole of why I watch them.
    >>  ............................................
    pt  ...Elas mudam. É toda a razão de eu olhar.
    >>  ............................................
  athletic.dialogue.conversations.weather.toddler.dismiss/3
    en  ...All right. They'll still be there tomorrow.
    >>  ............................................
    pt  ...Está bem. Vão estar lá amanhã.
    >>  ............................................
  confident.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds.
    >>  ............................................
    pt  NÃO são só nuvens.
    >>  ............................................
  confident.dialogue.conversations.weather.toddler.dismiss/2
    en  They've shapes. You have to look up for a while.
    >>  ............................................
    pt  Elas têm formas. Você tem que olhar pra cima um tempo.
    >>  ............................................
  confident.dialogue.conversations.weather.toddler.dismiss/3
    en  ...Fine. Don't look, then.
    >>  ............................................
    pt  ...Tudo bem. Então não olhe.
    >>  ............................................
  crabby.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds.
    >>  ............................................
    pt  NÃO são só nuvens.
    >>  ............................................
  crabby.dialogue.conversations.weather.toddler.dismiss/2
    en  They've shapes. You have to look up for a while.
    >>  ............................................
    pt  Elas têm formas. Você tem que olhar pra cima um tempo.
    >>  ............................................
  crabby.dialogue.conversations.weather.toddler.dismiss/3
    en  ...Fine. Don't look, then.
    >>  ............................................
    pt  ...Tudo bem. Então não olhe.
    >>  ............................................
  extroverted.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds, %1$s. Come and lie down and look properly.
    >>  ............................................
    pt  NÃO são só nuvens, %1$s. Venha deitar e olhar direito.
    >>  ............................................
  extroverted.dialogue.conversations.weather.toddler.dismiss/2
    en  I'll show you the good one. It's over the mill and it's shaped like a boot.
    >>  ............................................
    pt  Vou te mostrar a boa. Está sobre o moinho e tem formato de bota.
    >>  ............................................
  extroverted.dialogue.conversations.weather.toddler.dismiss/3
    en  ...Fine. But you're missing the best one.
    >>  ............................................
    pt  ...Tudo bem. Mas você está perdendo a melhor.
    >>  ............................................
  flirty.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds, %1$s. Come and lie down and look properly.
    >>  ............................................
    pt  NÃO são só nuvens, %1$s. Venha deitar e olhar direito.
    >>  ............................................
  flirty.dialogue.conversations.weather.toddler.dismiss/2
    en  I'll show you the good one. It's over the mill and it's shaped like a boot.
    >>  ............................................
    pt  Vou te mostrar a boa. Está sobre o moinho e tem formato de bota.
    >>  ............................................
  flirty.dialogue.conversations.weather.toddler.dismiss/3
    en  ...Fine. But you're missing the best one.
    >>  ............................................
    pt  ...Tudo bem. Mas você está perdendo a melhor.
    >>  ............................................
  friendly.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds, %1$s. Come and lie down and look properly.
    >>  ............................................
    pt  NÃO são só nuvens, %1$s. Venha deitar e olhar direito.
    >>  ............................................
  friendly.dialogue.conversations.weather.toddler.dismiss/2
    en  I'll show you the good one. It's over the mill and it's shaped like a boot.
    >>  ............................................
    pt  Vou te mostrar a boa. Está sobre o moinho e tem formato de bota.
    >>  ............................................
  friendly.dialogue.conversations.weather.toddler.dismiss/3
    en  ...Fine. But you're missing the best one.
    >>  ............................................
    pt  ...Tudo bem. Mas você está perdendo a melhor.
    >>  ............................................
  gloomy.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds. Don't say that, %1$s.
    >>  ............................................
    pt  NÃO são só nuvens. Não diga isso, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.weather.toddler.dismiss/2
    en  ...I like them. That's all.
    >>  ............................................
    pt  ...Eu gosto delas. Só isso.
    >>  ............................................
  gloomy.dialogue.conversations.weather.toddler.dismiss/3
    en  ...Fine. I'll watch them by myself.
    >>  ............................................
    pt  ...Tudo bem. Vou olhar sozinho.
    >>  ............................................
  greedy.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds.
    >>  ............................................
    pt  NÃO são só nuvens.
    >>  ............................................
  greedy.dialogue.conversations.weather.toddler.dismiss/2
    en  They've shapes. You have to look up for a while.
    >>  ............................................
    pt  Elas têm formas. Você tem que olhar pra cima um tempo.
    >>  ............................................
  greedy.dialogue.conversations.weather.toddler.dismiss/3
    en  ...Fine. Don't look, then.
    >>  ............................................
    pt  ...Tudo bem. Então não olhe.
    >>  ............................................
  grumpy.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds.
    >>  ............................................
    pt  NÃO são só nuvens.
    >>  ............................................
  grumpy.dialogue.conversations.weather.toddler.dismiss/2
    en  They've shapes. You have to look up for a while.
    >>  ............................................
    pt  Elas têm formas. Você tem que olhar pra cima um tempo.
    >>  ............................................
  grumpy.dialogue.conversations.weather.toddler.dismiss/3
    en  ...Fine. Don't look, then.
    >>  ............................................
    pt  ...Tudo bem. Então não olhe.
    >>  ............................................
  introverted.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds.
    >>  ............................................
    pt  NÃO são só nuvens.
    >>  ............................................
  introverted.dialogue.conversations.weather.toddler.dismiss/2
    en  ...That one's a fish.
    >>  ............................................
    pt  ...Aquela é um peixe.
    >>  ............................................
  introverted.dialogue.conversations.weather.toddler.dismiss/3
    en  ...I like watching them.
    >>  ............................................
    pt  ...Eu gosto de olhar pra elas.
    >>  ............................................
  lazy.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds. Give them a minute and they'll be something.
    >>  ............................................
    pt  NÃO são só nuvens. Dê um minuto e elas viram algo.
    >>  ............................................
  lazy.dialogue.conversations.weather.toddler.dismiss/2
    en  ...They change. That's the whole of why I watch them.
    >>  ............................................
    pt  ...Elas mudam. É toda a razão de eu olhar.
    >>  ............................................
  lazy.dialogue.conversations.weather.toddler.dismiss/3
    en  ...All right. They'll still be there tomorrow.
    >>  ............................................
    pt  ...Está bem. Vão estar lá amanhã.
    >>  ............................................
  odd.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds.
    >>  ............................................
    pt  NÃO são só nuvens.
    >>  ............................................
  odd.dialogue.conversations.weather.toddler.dismiss/2
    en  ...That one's a fish.
    >>  ............................................
    pt  ...Aquela é um peixe.
    >>  ............................................
  odd.dialogue.conversations.weather.toddler.dismiss/3
    en  ...I like watching them.
    >>  ............................................
    pt  ...Eu gosto de olhar pra elas.
    >>  ............................................
  peaceful.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds. Give them a minute and they'll be something.
    >>  ............................................
    pt  NÃO são só nuvens. Dê um minuto e elas viram algo.
    >>  ............................................
  peaceful.dialogue.conversations.weather.toddler.dismiss/2
    en  ...They change. That's the whole of why I watch them.
    >>  ............................................
    pt  ...Elas mudam. É toda a razão de eu olhar.
    >>  ............................................
  peaceful.dialogue.conversations.weather.toddler.dismiss/3
    en  ...All right. They'll still be there tomorrow.
    >>  ............................................
    pt  ...Está bem. Vão estar lá amanhã.
    >>  ............................................
  peppy.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds! There's a whole horse in that one!
    >>  ............................................
    pt  NÃO são só nuvens! Tem um cavalo inteiro naquela ali!
    >>  ............................................
  peppy.dialogue.conversations.weather.toddler.dismiss/2
    en  Look UP. Go on. That's not just a cloud and you know it.
    >>  ............................................
    pt  Olhe pra CIMA. Vai. Aquilo não é só uma nuvem e você sabe.
    >>  ............................................
  peppy.dialogue.conversations.weather.toddler.dismiss/3
    en  ...Ha! You'll see it in a minute. Everyone does eventually.
    >>  ............................................
    pt  ...Ha! Você vai ver num minuto. Todo mundo vê uma hora.
    >>  ............................................
  playful.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds! There's a whole horse in that one!
    >>  ............................................
    pt  NÃO são só nuvens! Tem um cavalo inteiro naquela ali!
    >>  ............................................
  playful.dialogue.conversations.weather.toddler.dismiss/2
    en  Look UP. Go on. That's not just a cloud and you know it.
    >>  ............................................
    pt  Olhe pra CIMA. Vai. Aquilo não é só uma nuvem e você sabe.
    >>  ............................................
  playful.dialogue.conversations.weather.toddler.dismiss/3
    en  ...Ha! You'll see it in a minute. Everyone does eventually.
    >>  ............................................
    pt  ...Ha! Você vai ver num minuto. Todo mundo vê uma hora.
    >>  ............................................
  relaxed.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds. Give them a minute and they'll be something.
    >>  ............................................
    pt  NÃO são só nuvens. Dê um minuto e elas viram algo.
    >>  ............................................
  relaxed.dialogue.conversations.weather.toddler.dismiss/2
    en  ...They change. That's the whole of why I watch them.
    >>  ............................................
    pt  ...Elas mudam. É toda a razão de eu olhar.
    >>  ............................................
  relaxed.dialogue.conversations.weather.toddler.dismiss/3
    en  ...All right. They'll still be there tomorrow.
    >>  ............................................
    pt  ...Está bem. Vão estar lá amanhã.
    >>  ............................................
  sensitive.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds. Don't say that, %1$s.
    >>  ............................................
    pt  NÃO são só nuvens. Não diga isso, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.weather.toddler.dismiss/2
    en  ...I like them. That's all.
    >>  ............................................
    pt  ...Eu gosto delas. Só isso.
    >>  ............................................
  sensitive.dialogue.conversations.weather.toddler.dismiss/3
    en  ...Fine. I'll watch them by myself.
    >>  ............................................
    pt  ...Tudo bem. Vou olhar sozinho.
    >>  ............................................
  shy.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds.
    >>  ............................................
    pt  NÃO são só nuvens.
    >>  ............................................
  shy.dialogue.conversations.weather.toddler.dismiss/2
    en  ...That one's a fish.
    >>  ............................................
    pt  ...Aquela é um peixe.
    >>  ............................................
  shy.dialogue.conversations.weather.toddler.dismiss/3
    en  ...I like watching them.
    >>  ............................................
    pt  ...Eu gosto de olhar pra elas.
    >>  ............................................
  upbeat.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds! There's a whole horse in that one!
    >>  ............................................
    pt  NÃO são só nuvens! Tem um cavalo inteiro naquela ali!
    >>  ............................................
  upbeat.dialogue.conversations.weather.toddler.dismiss/2
    en  Look UP. Go on. That's not just a cloud and you know it.
    >>  ............................................
    pt  Olhe pra CIMA. Vai. Aquilo não é só uma nuvem e você sabe.
    >>  ............................................
  upbeat.dialogue.conversations.weather.toddler.dismiss/3
    en  ...Ha! You'll see it in a minute. Everyone does eventually.
    >>  ............................................
    pt  ...Ha! Você vai ver num minuto. Todo mundo vê uma hora.
    >>  ............................................
  witty.dialogue.conversations.weather.toddler.dismiss/1
    en  They're not JUST clouds! There's a whole horse in that one!
    >>  ............................................
    pt  NÃO são só nuvens! Tem um cavalo inteiro naquela ali!
    >>  ............................................
  witty.dialogue.conversations.weather.toddler.dismiss/2
    en  Look UP. Go on. That's not just a cloud and you know it.
    >>  ............................................
    pt  Olhe pra CIMA. Vai. Aquilo não é só uma nuvem e você sabe.
    >>  ............................................
  witty.dialogue.conversations.weather.toddler.dismiss/3
    en  ...Ha! You'll see it in a minute. Everyone does eventually.
    >>  ............................................
    pt  ...Ha! Você vai ver num minuto. Todo mundo vê uma hora.
    >>  ............................................
```

</details>


### Button `leave` — "Go on, before it rains."

*stance family `exit` · tone `plain` · answers the beat(s) `weather.toddler.to.weather.toddler` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.weather.toddler.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.weather.toddler.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.weather.toddler.respond.leave   [23 chars]
    en  Go on, before it rains.
    >>  ............................................
    pt  Vai, antes que chova.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.chitchat`
- …where the player's next choices will be: "How's your day actually going?" | "What's good to eat around here?" | "What do you make of this weather?" | "How's the season treating you?" | "How do your days go?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.weather.toddler.leave
WHO    VILLAGER — what the player reads after pressing "Go on, before it rains."
       spoken on: conversations.topic.weather.toddler.respond, button `leave`
       leaves the player on: conversations.cat.chitchat
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `weather.toddler.leave.terminal`: the villager accepts. Subject `weather.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.weather.toddler.leave/1   [39 chars]
    en  Bye! I'm going to go look at them more.
    >>  ............................................
    pt  Tchau! Vou olhar mais um pouco.
    >>  ............................................
  dialogue.conversations.weather.toddler.leave/2   [9 chars]
    en  Okay bye!
    >>  ............................................
    pt  Tá, tchau!
    >>  ............................................
  dialogue.conversations.weather.toddler.leave/3   [14 chars]
    en  See you, %1$s!
    >>  ............................................
    pt  Até mais, %1$s!
    >>  ............................................
```

---

