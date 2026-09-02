# Topic: player

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `player` |
| Opened from | question `conversations.cat.personal`, button `player` |
| Depth class (its heart budget) | `quick` |
| Returns to | `conversations.cat.personal` |
| Ages that can reach it | teen, adult |
| Stance families it must offer | `curiosity`, `self_disclosure`, `dismissal`, `exit` |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.personal`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.personal.player
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.personal
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.personal.player   [23 chars]
    en  What do you make of me?
    >>  ............................................
    pt  O que você acha de mim?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.scene.player.followup`](#conversations-scene-player-followup)
- [`conversations.scene.player.the_day_i_revised_it.respond`](#conversations-scene-player-the-day-i-revised-it-respond)
- [`conversations.scene.player.what_i_say_about_you.respond`](#conversations-scene-player-what-i-say-about-you-respond)
- [`conversations.topic.player.more.respond`](#conversations-topic-player-more-respond)
- [`conversations.topic.player.open.respond`](#conversations-topic-player-open-respond)

---

## `conversations.scene.player.followup`

**Reached from 4 route(s):** `conversations.scene.player.the_day_i_revised_it.respond` / `ask_which_day`; `conversations.scene.player.the_day_i_revised_it.respond` / `say_it_matters_to_hear`; `conversations.scene.player.what_i_say_about_you.respond` / `ask_what_version`; `conversations.scene.player.what_i_say_about_you.respond` / `thank_them_for_the_correcting`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.player.the_day_i_revised_it.acknowledged` — e.g. "Then I am glad I said it. I have been sitting on it for a season out of pure stubbornness."
- `conversations.scene.player.the_day_i_revised_it.told` — e.g. "You will not remember it, which is exactly why it counted. You did the decent thing with no audience worth having."
- `conversations.scene.player.what_i_say_about_you.acknowledged` — e.g. "Correcting, not defending. If the unkind version were true I would have let it stand."
- `conversations.scene.player.what_i_say_about_you.reported` — e.g. "That you are generous and in a hurry. Half of them think the hurry means you are hiding something."


```text
POOL   dialogue key: dialogue.conversations.scene.player.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.player.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.player.followup   [20 chars]
    en  Want the rest of it?
    >>  ............................................
    pt  Quer o resto?
    >>  ............................................
```


### Button `leave` — "Enough of you for one day."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:player.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.player.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.player.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.player.followup.leave   [26 chars]
    en  Enough of you for one day.
    >>  ............................................
    pt  Já chega de você por hoje.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.player.leaving
WHO    VILLAGER — what the player reads after pressing "Enough of you for one day."
       spoken on: conversations.scene.player.followup, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `player.scene.leaving`: the villager accepts. Subject `player.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.player.the_day_i_revised_it.respond / leave; conversations.scene.player.what_i_say_about_you.respond / leave; conversations.topic.player.more.respond / leave; conversations.topic.player.open.respond / leave
```

```text
  dialogue.conversations.scene.player.leaving/1   [48 chars]
    en  That is my reading of you, for what it is worth.
    >>  ............................................
    pt  É a minha leitura de você, pelo que valha.
    >>  ............................................
  dialogue.conversations.scene.player.leaving/2   [31 chars]
    en  You asked. I answered honestly.
    >>  ............................................
    pt  Você perguntou. Eu respondi com honestidade.
    >>  ............................................
  dialogue.conversations.scene.player.leaving/3   [54 chars]
    en  Ask me again in a season and see whether it has moved.
    >>  ............................................
    pt  Me pergunte de novo daqui a uma estação e veja se mudou.
    >>  ............................................
```

---


## `conversations.scene.player.the_day_i_revised_it.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `player`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.player.the_day_i_revised_it` — e.g. "There was one afternoon that did it, and you have no idea which one, and I have never told you."


```text
POOL   dialogue key: dialogue.conversations.scene.player.the_day_i_revised_it.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.player.the_day_i_revised_it.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.player.the_day_i_revised_it.respond   [19 chars]
    en  The day it changed.
    >>  ............................................
    pt  O dia em que mudou.
    >>  ............................................
```


### Button `ask_which_day` — "Which afternoon was it?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `player.the_day_i_revised_it.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.player.the_day_i_revised_it.ask_which_day` — accepted phrasings: "which afternoon was it"; "which afternoon was it"; "tell me which day it was"
  - the message must contain one of: `afternoon`, `day`
  - scored words: `afternoon`(1.8), `day`(1.8), `which`(0.8), `tell`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.player.the_day_i_revised_it.respond.ask_which_day
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.player.the_day_i_revised_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.player.the_day_i_revised_it.respond.ask_which_day   [23 chars]
    en  Which afternoon was it?
    >>  ............................................
    pt  Que tarde foi essa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `topic.player.revision.asked`, budget `standard`, replay policy `once`
- Does: disposition — familiarity +3, trust +2  _(recorded under topic `player.revision`)_
- Does: session `turn`
- Then opens: `conversations.scene.player.followup`
- …where the player's next choices will be: "Enough of you for one day."

```text
POOL   dialogue key: dialogue.conversations.scene.player.the_day_i_revised_it.told
WHO    VILLAGER — what the player reads after pressing "Which afternoon was it?"
       spoken on: conversations.scene.player.the_day_i_revised_it.respond, button `ask_which_day`
       leaves the player on: conversations.scene.player.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `player.the_day_i_revised_it.open.told`: the villager reminisces. Subject `player.revision`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:player` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.player.the_day_i_revised_it.told/1   [114 chars]
    en  You will not remember it, which is exactly why it counted. You did the decent thing with no audience worth having.
    >>  ............................................
    pt  Você não vai lembrar, e é exatamente por isso que contou. Fez a coisa decente sem plateia que valesse a pena.
    >>  ............................................
  dialogue.conversations.scene.player.the_day_i_revised_it.told/2   [108 chars]
    en  A wet afternoon, and you waited for somebody slower than you, and you did not make a performance of waiting.
    >>  ............................................
    pt  Uma tarde chuvosa, e você esperou alguém mais lento que você, e não fez cena de estar esperando.
    >>  ............................................
  dialogue.conversations.scene.player.the_day_i_revised_it.told/3   [99 chars]
    en  You told me a small truth that made you look worse. Nobody does that to a person they are managing.
    >>  ............................................
    pt  Você me contou uma verdade pequena que te deixou pior na foto. Ninguém faz isso com quem está manipulando.
    >>  ............................................
```


### Button `say_it_matters_to_hear` — "It means something, hearing that."

*stance family `self_disclosure` · tone `gentle` · outcome `appreciated` · answers the beat(s) `player.the_day_i_revised_it.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.player.the_day_i_revised_it.say_it_matters_to_hear` — accepted phrasings: "it means something hearing that"; "it means something hearing that"; "that means a great deal to me"
  - the message must contain one of: `means`, `hearing`
  - scored words: `means`(1.8), `hearing`(1.8), `something`(0.8), `great`(0.8), `deal`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.player.the_day_i_revised_it.respond.say_it_matters_to_hear
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.player.the_day_i_revised_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.player.the_day_i_revised_it.respond.say_it_matters_to_hear   [33 chars]
    en  It means something, hearing that.
    >>  ............................................
    pt  Significa algo, ouvir isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `topic.player.revision.received`, budget `standard`, replay policy `once`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `player.revision`)_
- Does: session `turn`
- Then opens: `conversations.scene.player.followup`
- …where the player's next choices will be: "Enough of you for one day."

```text
POOL   dialogue key: dialogue.conversations.scene.player.the_day_i_revised_it.acknowledged
WHO    VILLAGER — what the player reads after pressing "It means something, hearing that."
       spoken on: conversations.scene.player.the_day_i_revised_it.respond, button `say_it_matters_to_hear`
       leaves the player on: conversations.scene.player.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `player.the_day_i_revised_it.open.acknowledged`: the villager accepts. Subject `player.revision`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:player` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.player.the_day_i_revised_it.acknowledged/1   [90 chars]
    en  Then I am glad I said it. I have been sitting on it for a season out of pure stubbornness.
    >>  ............................................
    pt  Então fico contente de ter dito. Fiquei sentada em cima disso uma estação inteira por pura teimosia.
    >>  ............................................
  dialogue.conversations.scene.player.the_day_i_revised_it.acknowledged/2   [94 chars]
    en  Good. People ought to be told the day somebody decided in their favour. Almost nobody ever is.
    >>  ............................................
    pt  Bom. As pessoas deviam saber o dia em que alguém decidiu a favor delas. Quase ninguém sabe.
    >>  ............................................
  dialogue.conversations.scene.player.the_day_i_revised_it.acknowledged/3   [93 chars]
    en  It cost me nothing and I put it off for months, which tells you more about me than about you.
    >>  ............................................
    pt  Não me custou nada e eu adiei por meses, o que diz mais sobre mim do que sobre você.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `player.the_day_i_revised_it.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.player.the_day_i_revised_it.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.player.the_day_i_revised_it.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.player.the_day_i_revised_it.respond.leave   [12 chars]
    en  Fair enough.
    >>  ............................................
    pt  Justo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.player.leaving
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.scene.player.the_day_i_revised_it.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `player.scene.leaving`: the villager accepts. Subject `player.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.player.followup / leave; conversations.scene.player.what_i_say_about_you.respond / leave; conversations.topic.player.more.respond / leave; conversations.topic.player.open.respond / leave
```

> Written out in full under **`conversations.scene.player.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.player.what_i_say_about_you.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `player`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.player.what_i_say_about_you` — e.g. "You get talked about here, and I am usually the one doing the talking, and you come out of it well."


```text
POOL   dialogue key: dialogue.conversations.scene.player.what_i_say_about_you.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.player.what_i_say_about_you.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.player.what_i_say_about_you.respond   [21 chars]
    en  What you tell others.
    >>  ............................................
    pt  O que você diz aos outros.
    >>  ............................................
```


### Button `ask_what_version` — "What version is going round?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `player.what_i_say_about_you.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.player.what_i_say_about_you.ask_what_version` — accepted phrasings: "what version is going round"; "what version is going round"; "what do people say about me"
  - the message must contain one of: `version`, `people`
  - scored words: `version`(1.8), `people`(1.8), `going`(0.8), `round`(0.8), `say`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.player.what_i_say_about_you.respond.ask_what_version
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.player.what_i_say_about_you.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.player.what_i_say_about_you.respond.ask_what_version   [28 chars]
    en  What version is going round?
    >>  ............................................
    pt  Que versão está circulando?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `player.reputation_here`)_
- Does: session `turn`
- Then opens: `conversations.scene.player.followup`
- …where the player's next choices will be: "Enough of you for one day."

```text
POOL   dialogue key: dialogue.conversations.scene.player.what_i_say_about_you.reported
WHO    VILLAGER — what the player reads after pressing "What version is going round?"
       spoken on: conversations.scene.player.what_i_say_about_you.respond, button `ask_what_version`
       leaves the player on: conversations.scene.player.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `player.what_i_say_about_you.open.reported`: the villager explains. Subject `player.reputation_here`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:player` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.player.what_i_say_about_you.reported/1   [98 chars]
    en  That you are generous and in a hurry. Half of them think the hurry means you are hiding something.
    >>  ............................................
    pt  Que você é generoso e apressado. Metade deles acha que a pressa quer dizer que você esconde algo.
    >>  ............................................
  dialogue.conversations.scene.player.what_i_say_about_you.reported/2   [89 chars]
    en  That you can be relied on and cannot be read, and people find the second half unsettling.
    >>  ............................................
    pt  Que dá para contar com você e que não dá para te ler, e a segunda metade incomoda as pessoas.
    >>  ............................................
  dialogue.conversations.scene.player.what_i_say_about_you.reported/3   [102 chars]
    en  A kind one, mostly. There is one household that thinks otherwise and I have stopped arguing with them.
    >>  ............................................
    pt  Uma versão gentil, na maior parte. Tem uma casa que pensa diferente e eu parei de discutir com eles.
    >>  ............................................
```


### Button `thank_them_for_the_correcting` — "You've been defending me, then."

*stance family `self_disclosure` · tone `gentle` · outcome `appreciated` · answers the beat(s) `player.what_i_say_about_you.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.player.what_i_say_about_you.thank_them_for_the_correcting` — accepted phrasings: "youve been defending me then"; "you have been defending me then"; "you spoke up for me"
  - the message must contain one of: `defending`, `spoke`
  - scored words: `defending`(1.8), `spoke`(1.8), `youve`(0.8), `been`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.player.what_i_say_about_you.respond.thank_them_for_the_correcting
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.player.what_i_say_about_you.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.player.what_i_say_about_you.respond.thank_them_for_the_correcting   [31 chars]
    en  You've been defending me, then.
    >>  ............................................
    pt  Você tem me defendido, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `topic.player.reputation.thanked`, budget `standard`, replay policy `once`
- Does: disposition — warmth +3, trust +2  _(recorded under topic `player.reputation_here`)_
- Does: session `turn`
- Then opens: `conversations.scene.player.followup`
- …where the player's next choices will be: "Enough of you for one day."

```text
POOL   dialogue key: dialogue.conversations.scene.player.what_i_say_about_you.acknowledged
WHO    VILLAGER — what the player reads after pressing "You've been defending me, then."
       spoken on: conversations.scene.player.what_i_say_about_you.respond, button `thank_them_for_the_correcting`
       leaves the player on: conversations.scene.player.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `player.what_i_say_about_you.open.acknowledged`: the villager accepts. Subject `player.reputation_here`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:player` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.player.what_i_say_about_you.acknowledged/1   [85 chars]
    en  Correcting, not defending. If the unkind version were true I would have let it stand.
    >>  ............................................
    pt  Corrigindo, não defendendo. Se a versão cruel fosse verdade eu teria deixado passar.
    >>  ............................................
  dialogue.conversations.scene.player.what_i_say_about_you.acknowledged/2   [91 chars]
    en  Somebody has to. A reputation left alone in a village this size goes bad in about a season.
    >>  ............................................
    pt  Alguém tem que fazer. Uma reputação largada numa vila deste tamanho azeda em uma estação.
    >>  ............................................
  dialogue.conversations.scene.player.what_i_say_about_you.acknowledged/3   [90 chars]
    en  It is not charity. I have to live with these people and I would rather they were accurate.
    >>  ............................................
    pt  Não é caridade. Eu tenho que conviver com essa gente e prefiro que estejam corretos.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `player.what_i_say_about_you.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.player.what_i_say_about_you.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.player.what_i_say_about_you.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.player.what_i_say_about_you.respond.leave   [12 chars]
    en  Fair enough.
    >>  ............................................
    pt  Justo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.player.leaving
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.scene.player.what_i_say_about_you.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `player.scene.leaving`: the villager accepts. Subject `player.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.player.followup / leave; conversations.scene.player.the_day_i_revised_it.respond / leave; conversations.topic.player.more.respond / leave; conversations.topic.player.open.respond / leave
```

> Written out in full under **`conversations.scene.player.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.player.more.respond`

**Reached from 2 route(s):** `conversations.topic.player.open.respond` / `ask_what_changed_it`; `conversations.topic.player.open.respond` / `admit_the_impression`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.player.open.explained` — e.g. "You came back. That is genuinely most of it. People who mean well and leave are the same as people who did nothing."
- `conversations.player.open.warmed` — e.g. "That answer is the reason the reading changed, if you want the short version."


```text
POOL   dialogue key: dialogue.conversations.topic.player.more.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.player.more.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.player.more.respond   [8 chars]
    en  And now?
    >>  ............................................
    pt  E agora?
    >>  ............................................
```


### Button `ask_the_worst_of_it` — "Give me the unflattering half."

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `player.open.explained`, `player.open.warmed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.player.more.ask_the_worst_of_it` — accepted phrasings: "give me the unflattering half"; "give me the unflattering half"; "tell me the bad part too"
  - the message must contain one of: `unflattering`, `bad`
  - scored words: `unflattering`(1.8), `bad`(1.8), `give`(0.8), `half`(0.8), `tell`(0.8), `part`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.player.more.respond.ask_the_worst_of_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.player.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.player.more.respond.ask_the_worst_of_it   [30 chars]
    en  Give me the unflattering half.
    >>  ............................................
    pt  Me dê a metade nada lisonjeira.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `topic.player.asked_for_candour`, budget `standard`, replay policy `once`
- Does: disposition — respect +3, familiarity +2  _(recorded under topic `player.current_reading`)_
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.player.more.levelled
WHO    VILLAGER — what the player reads after pressing "Give me the unflattering half."
       spoken on: conversations.topic.player.more.respond, button `ask_the_worst_of_it`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `player.more.levelled`: the villager explains. Subject `player.current_reading`, polarity `mixed`, ends conversation, outcome `engaged`.
NOTE   this is the line that establishes `topic:player` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.player.more.levelled/1   [114 chars]
    en  You take on other people's problems faster than you put down your own, and one day that arithmetic will catch you.
    >>  ............................................
    pt  Você assume os problemas dos outros mais rápido do que larga os seus, e um dia essa conta te alcança.
    >>  ............................................
  dialogue.conversations.player.more.levelled/2   [114 chars]
    en  You are hard to say no to, and you have not yet noticed that this is a thing you do rather than a thing about you.
    >>  ............................................
    pt  É difícil te dizer não, e você ainda não notou que isso é algo que você faz, e não algo sobre você.
    >>  ............................................
  dialogue.conversations.player.more.levelled/3   [111 chars]
    en  You listen well and you answer almost nothing about yourself, and after a year that starts to feel like a wall.
    >>  ............................................
    pt  Você escuta bem e quase não responde nada sobre si, e depois de um ano isso começa a parecer um muro.
    >>  ............................................
```


### Button `return_the_reading` — "I'd notice that gap too."

*stance family `self_disclosure` · tone `gentle` · outcome `appreciated` · answers the beat(s) `player.open.explained`, `player.open.warmed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.player.more.return_the_reading` — accepted phrasings: "id notice that gap too"; "i would notice that gap too"; "the gap would be on my side as well"
  - the message must contain one of: `notice`, `gap`
  - scored words: `notice`(1.8), `gap`(1.8), `side`(0.8), `well`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.player.more.respond.return_the_reading
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.player.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.player.more.respond.return_the_reading   [24 chars]
    en  I'd notice that gap too.
    >>  ............................................
    pt  Eu também notaria essa falta.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `topic.player.returned`, budget `standard`, replay policy `once`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `player.current_reading`)_
- Does: session `end`
- Does: `conversations_claim` = {"op": "record", "type": "notices_absence", "value": "flag:true", "source": "conversations.topic.player.more.respond/return_the_reading"}
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.player.more.met
WHO    VILLAGER — what the player reads after pressing "I'd notice that gap too."
       spoken on: conversations.topic.player.more.respond, button `return_the_reading`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `player.more.met`: the villager accepts. Subject `player.current_reading`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   this is the line that establishes `topic:player` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.player.more.met/1   [82 chars]
    en  Good. That is the sort of thing people feel for years and never once say out loud.
    >>  ............................................
    pt  Que bom. É o tipo de coisa que as pessoas sentem por anos e nunca dizem em voz alta.
    >>  ............................................
  dialogue.conversations.player.more.met/2   [68 chars]
    en  Then we are square, and I shall stop pretending I only tolerate you.
    >>  ............................................
    pt  Então estamos quites, e eu paro de fingir que só te tolero.
    >>  ............................................
  dialogue.conversations.player.more.met/3   [84 chars]
    en  I was hoping you would say something and I had entirely accepted that you might not.
    >>  ............................................
    pt  Eu esperava que você dissesse algo e já tinha aceitado por completo que talvez não dissesse.
    >>  ............................................
```


### Button `end_the_appraisal` — "Keep the appraisal to yourself."

*stance family `dismissal` · tone `blunt` · outcome `conversation_ended` · answers the beat(s) `player.open.explained`, `player.open.warmed`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.player.more.end_the_appraisal` — accepted phrasings: "keep the appraisal to yourself"; "keep the appraisal to yourself"; "i would rather skip the appraisal"
  - the message must contain one of: `appraisal`, `skip`
  - scored words: `appraisal`(1.8), `skip`(1.8), `keep`(0.8), `yourself`(0.8), `rather`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.player.more.respond.end_the_appraisal
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.player.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.player.more.respond.end_the_appraisal   [31 chars]
    en  Keep the appraisal to yourself.
    >>  ............................................
    pt  Guarde a avaliação para você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `topic.player.cut_short`, budget `standard`, replay policy `once`
- Does: disposition — warmth -2, tension +2  _(recorded under topic `player.current_reading`)_
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.player.more.closed
WHO    VILLAGER — what the player reads after pressing "Keep the appraisal to yourself."
       spoken on: conversations.topic.player.more.respond, button `end_the_appraisal`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `player.more.closed`: the villager deflects. Subject `player.current_reading`, polarity `negative`, ends conversation, outcome `conversation_ended`.
NOTE   this is the line that establishes `topic:player` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.player.more.closed/1   [73 chars]
    en  Then I shall stop, and I shall note that you asked me the question first.
    >>  ............................................
    pt  Então eu paro, e registro que foi você quem fez a pergunta primeiro.
    >>  ............................................
  dialogue.conversations.player.more.closed/2   [93 chars]
    en  Understood. It is an uncomfortable thing to hear and I was going to say the kind half anyway.
    >>  ............................................
    pt  Entendido. É desconfortável de ouvir e eu ia dizer a metade gentil de qualquer forma.
    >>  ............................................
  dialogue.conversations.player.more.closed/3   [34 chars]
    en  Right. Consider the ledger closed.
    >>  ............................................
    pt  Certo. Considere o balanço encerrado.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `player.open.explained`, `player.open.warmed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.player.more.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.player.more.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.player.more.respond.leave   [12 chars]
    en  Fair enough.
    >>  ............................................
    pt  Justo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.player.leaving
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.topic.player.more.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `player.scene.leaving`: the villager accepts. Subject `player.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.player.followup / leave; conversations.scene.player.the_day_i_revised_it.respond / leave; conversations.scene.player.what_i_say_about_you.respond / leave; conversations.topic.player.open.respond / leave
```

> Written out in full under **`conversations.scene.player.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.player.open.respond`

**Reached from 1 route(s):** `conversations.cat.personal` / `player`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.player.open` — e.g. "Honestly? I assumed you were passing through. Nearly everybody who arrives with that much kit is."


```text
POOL   dialogue key: dialogue.conversations.topic.player.open.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.player.open.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.player.open.respond   [26 chars]
    en  What you thought at first.
    >>  ............................................
    pt  O que você achou no começo.
    >>  ............................................
```


### Button `ask_what_changed_it` — "What shifted your reading?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `player.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.player.open.ask_what_changed_it` — accepted phrasings: "what shifted your reading"; "what shifted your reading"; "what changed your mind about me"
  - the message must contain one of: `shifted`, `reading`, `changed`
  - scored words: `shifted`(1.8), `reading`(1.8), `changed`(1.8)

```text
POOL   dialogue key: dialogue.conversations.topic.player.open.respond.ask_what_changed_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.player.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.player.open.respond.ask_what_changed_it   [26 chars]
    en  What shifted your reading?
    >>  ............................................
    pt  O que mudou a sua leitura?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `player.first_impression`)_
- Does: session `turn`
- Then opens: `conversations.topic.player.more.respond`
- …where the player's next choices will be: "Give me the unflattering half." | "I'd notice that gap too." | "Keep the appraisal to yourself." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.player.open.explained
WHO    VILLAGER — what the player reads after pressing "What shifted your reading?"
       spoken on: conversations.topic.player.open.respond, button `ask_what_changed_it`
       leaves the player on: conversations.topic.player.more.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `player.open.explained`: the villager explains. Subject `player.first_impression`, polarity `positive`, invites followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:player` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, self_disclosure, dismissal, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.player.open.explained/1   [115 chars]
    en  You came back. That is genuinely most of it. People who mean well and leave are the same as people who did nothing.
    >>  ............................................
    pt  Você voltou. É genuinamente a maior parte. Quem tem boa intenção e vai embora dá no mesmo que quem não fez nada.
    >>  ............................................
  dialogue.conversations.player.open.explained/2   [94 chars]
    en  You did a small thing nobody was watching, and I happened to be watching, and that settled it.
    >>  ............................................
    pt  Você fez uma coisa pequena que ninguém estava vendo, e eu por acaso estava, e aquilo resolveu.
    >>  ............................................
  dialogue.conversations.player.open.explained/3   [103 chars]
    en  You took a correction without arguing. I have known men in this village forty years who cannot do that.
    >>  ............................................
    pt  Você aceitou uma correção sem discutir. Conheço homens desta vila há quarenta anos que não conseguem.
    >>  ............................................
```


### Button `admit_the_impression` — "I'd have thought the same of me."

*stance family `self_disclosure` · tone `plain` · outcome `appreciated` · answers the beat(s) `player.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.player.open.admit_the_impression` — accepted phrasings: "id have thought the same of me"; "i would have thought the same of me"; "that is a fair first impression"
  - the message must contain one of: `same`, `impression`
  - scored words: `same`(1.8), `impression`(1.8), `thought`(0.8), `fair`(0.8), `first`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.player.open.respond.admit_the_impression
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.player.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.player.open.respond.admit_the_impression   [32 chars]
    en  I'd have thought the same of me.
    >>  ............................................
    pt  Eu teria pensado o mesmo de mim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2, warmth +1  _(recorded under topic `player.first_impression`)_
- Does: session `turn`
- Does: `conversations_claim` = {"op": "record", "type": "takes_correction_well", "value": "flag:true", "source": "conversations.topic.player.open.respond/admit_the_impression"}
- Then opens: `conversations.topic.player.more.respond`
- …where the player's next choices will be: "Give me the unflattering half." | "I'd notice that gap too." | "Keep the appraisal to yourself." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.player.open.warmed
WHO    VILLAGER — what the player reads after pressing "I'd have thought the same of me."
       spoken on: conversations.topic.player.open.respond, button `admit_the_impression`
       leaves the player on: conversations.topic.player.more.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `player.open.warmed`: the villager accepts. Subject `player.first_impression`, polarity `positive`, invites followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:player` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, self_disclosure, dismissal, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.player.open.warmed/1   [77 chars]
    en  That answer is the reason the reading changed, if you want the short version.
    >>  ............................................
    pt  Essa resposta é o motivo de a leitura ter mudado, se você quiser a versão curta.
    >>  ............................................
  dialogue.conversations.player.open.warmed/2   [96 chars]
    en  Nearly everybody argues with the first impression. You are the third in my life to just take it.
    >>  ............................................
    pt  Quase todo mundo discute com a primeira impressão. Você é a terceira pessoa na minha vida a só aceitar.
    >>  ............................................
  dialogue.conversations.player.open.warmed/3   [104 chars]
    en  See, that is exactly the thing. You keep declining the chance to be offended and it keeps working on me.
    >>  ............................................
    pt  Viu, é exatamente isso. Você continua recusando a chance de se ofender e isso continua funcionando comigo.
    >>  ............................................
```


### Button `refuse_the_verdict` — "You judged me early, then."

*stance family `dismissal` · tone `blunt` · outcome `conversation_ended` · answers the beat(s) `player.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.player.open.refuse_the_verdict` — accepted phrasings: "you judged me early then"; "you judged me early then"; "you made up your mind quickly"
  - the message must contain one of: `judged`, `quickly`
  - scored words: `judged`(1.8), `quickly`(1.8), `early`(0.8), `made`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.player.open.respond.refuse_the_verdict
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.player.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.player.open.respond.refuse_the_verdict   [26 chars]
    en  You judged me early, then.
    >>  ............................................
    pt  Você me julgou cedo, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `topic.player.dismissed`, budget `standard`, replay policy `once`
- Does: disposition — warmth -2, tension +3  _(recorded under topic `player.first_impression`)_
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.player.open.closed
WHO    VILLAGER — what the player reads after pressing "You judged me early, then."
       spoken on: conversations.topic.player.open.respond, button `refuse_the_verdict`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `player.open.closed`: the villager qualifys. Subject `player.first_impression`, polarity `negative`, ends conversation, outcome `conversation_ended`.
NOTE   this is the line that establishes `topic:player` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.player.open.closed/1   [97 chars]
    en  I did. Everybody does. The difference is that I told you the early verdict instead of keeping it.
    >>  ............................................
    pt  Julguei. Todo mundo julga. A diferença é que eu te contei o veredito inicial em vez de guardar.
    >>  ............................................
  dialogue.conversations.player.open.closed/2   [84 chars]
    en  Yes. And I changed it, which is the part of the story you have just decided to skip.
    >>  ............................................
    pt  Sim. E mudei, que é a parte da história que você acabou de decidir pular.
    >>  ............................................
  dialogue.conversations.player.open.closed/3   [61 chars]
    en  Right. I shall keep my readings to myself from here on, then.
    >>  ............................................
    pt  Certo. Guardo minhas leituras para mim daqui em diante, então.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `player.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.player.open.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.player.open.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.player.open.respond.leave   [12 chars]
    en  Fair enough.
    >>  ............................................
    pt  Justo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.personal`
- …where the player's next choices will be: "Tell me about your life." | "What do you dream about?" | "What are you afraid of?" | "What are you hoping for?" | "How do you really feel about me?" | "Do you have any regrets?" | "Tell me a secret." | "What do you enjoy?" | "What matters to you?" | "What do you make of me?" | "Where are you from?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.player.leaving
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.topic.player.open.respond, button `leave`
       leaves the player on: conversations.cat.personal
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `player.scene.leaving`: the villager accepts. Subject `player.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.player.followup / leave; conversations.scene.player.the_day_i_revised_it.respond / leave; conversations.scene.player.what_i_say_about_you.respond / leave; conversations.topic.player.more.respond / leave
```

> Written out in full under **`conversations.scene.player.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

