# Topic: neighbour

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.

## What this conversation is

| | |
|---|---|
| Topic id | `neighbour` |
| Opened from | question `conversations.cat.village`, button `neighbour` |
| Depth class (its heart budget) | `standard` |
| Returns to | `conversations.cat.village` |
| Ages that can reach it | child, teen, adult |
| Stance families it must offer | `curiosity`, `candor`, `restraint`, `dismissal`, `exit` |
| Narrative arc | `neighbour`, max stage 2 |
| Typable in chat mode | yes |

### The way in

The button that opens this whole conversation sits on `conversations.cat.village`, which is written out in **00-hub.md**. Rewrite the outcomes behind it there, once. The button's own wording is repeated here because it is the first thing a player reads about this subject.

```text
POOL   dialogue key: dialogue.conversations.cat.village.neighbour
WHO    PLAYER — the button that opens this whole conversation
       on the node: conversations.cat.village
ARGS   none — button labels take no substitutions
SIZE   1 line in this pool
NOTE   If you change it, change the same key in 00-hub*.md too — it is one key, shown in two places.
```

```text
  dialogue.conversations.cat.village.neighbour   [29 chars]
    en  Is there anyone on your mind?
    >>  ............................................
    pt  Tem alguém na sua cabeça?
    >>  ............................................
```

---


## Nodes in this file

- [`conversations.arc.neighbour.resume.followup`](#conversations-arc-neighbour-resume-followup)
- [`conversations.arc.neighbour.resume.respond`](#conversations-arc-neighbour-resume-respond)
- [`conversations.scene.neighbour.followup`](#conversations-scene-neighbour-followup)
- [`conversations.scene.neighbour.the_unthanked_kindness.respond`](#conversations-scene-neighbour-the-unthanked-kindness-respond)
- [`conversations.scene.neighbour.two_doors_down.respond`](#conversations-scene-neighbour-two-doors-down-respond)
- [`conversations.topic.neighbour.dispute`](#conversations-topic-neighbour-dispute)
- [`conversations.topic.neighbour.family`](#conversations-topic-neighbour-family)
- [`conversations.topic.neighbour.followup`](#conversations-topic-neighbour-followup)
- [`conversations.topic.neighbour.more`](#conversations-topic-neighbour-more)
- [`conversations.topic.neighbour.needs`](#conversations-topic-neighbour-needs)
- [`conversations.topic.neighbour.none.respond`](#conversations-topic-neighbour-none-respond)
- [`conversations.topic.neighbour.privacy`](#conversations-topic-neighbour-privacy)
- [`conversations.topic.neighbour.respond`](#conversations-topic-neighbour-respond)

---

## `conversations.arc.neighbour.resume.followup`

**Reached from 3 route(s):** `conversations.arc.neighbour.resume.respond` / `say_nothing`; `conversations.arc.neighbour.resume.respond` / `ask_how_they_are`; `conversations.arc.neighbour.resume.respond` / `ill_keep_going`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.neighbour.resume.ask_how_they_are` — e.g. "Better than the week before. That's the most anyone can say about anyone."
- `conversations.neighbour.resume.ill_keep_going` — e.g. "Then you'll be the second person in this village who ever did twice."
- `conversations.neighbour.resume.say_nothing` — e.g. "That's how it should be done and it's how almost nobody does it."


```text
POOL   dialogue key: dialogue.conversations.arc.neighbour.resume.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.neighbour.resume.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.neighbour.resume.followup   [29 chars]
    en  And that's where we leave it.
    >>  ............................................
    pt  E é aí que a gente para.
    >>  ............................................
```


### Button `thank_you_for_telling` — "Thank you for keeping me in it."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `neighbour.resume.say_nothing`, `neighbour.resume.ask_how_they_are`, `neighbour.resume.ill_keep_going`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.resume.thank_you_for_telling` — accepted phrasings: "thank you for keeping me in it"; "thanks for keeping me informed"; "i am glad you told me how it went"
  - the message must contain one of: `keeping`
  - scored words: `errand`(0.3), `keeping`(1.2), `telling`(0.6)

```text
POOL   dialogue key: dialogue.conversations.arc.neighbour.resume.followup.thank_you_for_telling
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.neighbour.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.neighbour.resume.followup.thank_you_for_telling   [31 chars]
    en  Thank you for keeping me in it.
    >>  ............................................
    pt  Obrigado por me manter por dentro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `neighbour.resume.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — familiarity +1, warmth +1  _(recorded under topic `neighbour.resume.thank_you_for_telling`)_
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.resume.thank_you_for_telling
WHO    VILLAGER — what the player reads after pressing "Thank you for keeping me in it."
       spoken on: conversations.arc.neighbour.resume.followup, button `thank_you_for_telling`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.resume.thank_you_for_telling`: the villager accepts. Subject `neighbour.needs`, polarity `positive`, ends conversation, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.resume.thank_you_for_telling/1   [72 chars]
    en  You asked. People who ask get told; it isn't more complicated than that.
    >>  ............................................
    pt  Você perguntou. Quem pergunta é informado; não é mais complicado que isso.
    >>  ............................................
  dialogue.conversations.neighbour.resume.thank_you_for_telling/2   [76 chars]
    en  It costs me nothing and it seems to be worth something. I'll go on doing it.
    >>  ............................................
    pt  Não me custa nada e parece valer algo. Vou continuar fazendo.
    >>  ............................................
  dialogue.conversations.neighbour.resume.thank_you_for_telling/3   [74 chars]
    en  That's the second time you've thanked me for a thing I'd have done anyway.
    >>  ............................................
    pt  É a segunda vez que você me agradece por algo que eu faria de qualquer jeito.
    >>  ............................................
```


### Button `leave_it_with_you` — "I'll leave it with you."

*stance family `restraint` · tone `plain` · outcome `accepted` · answers the beat(s) `neighbour.resume.say_nothing`, `neighbour.resume.ask_how_they_are`, `neighbour.resume.ill_keep_going`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.resume.leave_it_with_you` — accepted phrasings: "i will leave it with you"; "that is yours to handle"; "i will let you carry it from here"
  - the message must contain one of: `yours`
  - scored words: `errand`(0.3), `leave`(0.6), `with`(0.3), `yours`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.neighbour.resume.followup.leave_it_with_you
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.neighbour.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.neighbour.resume.followup.leave_it_with_you   [23 chars]
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
POOL   dialogue key: dialogue.conversations.neighbour.resume.leave_it_with_you
WHO    VILLAGER — what the player reads after pressing "I'll leave it with you."
       spoken on: conversations.arc.neighbour.resume.followup, button `leave_it_with_you`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.resume.leave_it_with_you`: the villager accepts. Subject `neighbour.needs`, polarity `neutral`, ends conversation, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.resume.leave_it_with_you/1   [49 chars]
    en  Do. I'll say if it changes, and I'll say plainly.
    >>  ............................................
    pt  Deixe. Eu aviso se mudar, e aviso sem rodeios.
    >>  ............................................
  dialogue.conversations.neighbour.resume.leave_it_with_you/2   [73 chars]
    en  Right. It's mine to carry and it's lighter for having been said out loud.
    >>  ............................................
    pt  Certo. É meu pra carregar e está mais leve por ter sido dito em voz alta.
    >>  ............................................
  dialogue.conversations.neighbour.resume.leave_it_with_you/3   [74 chars]
    en  Then it's mine again. That's how it should be, and thank you for the loan.
    >>  ............................................
    pt  Então volta a ser meu. É como deve ser, e obrigado pelo empréstimo.
    >>  ............................................
```


### Button `leave` — "I'll get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `neighbour.resume.say_nothing`, `neighbour.resume.ask_how_they_are`, `neighbour.resume.ill_keep_going` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.neighbour.resume.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.neighbour.resume.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.neighbour.resume.followup.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.neighbour.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll get on."
       spoken on: conversations.arc.neighbour.resume.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.resume.leave`: the villager accepts. Subject `neighbour.needs`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.arc.neighbour.resume.respond / leave
```

```text
  dialogue.conversations.neighbour.resume.leave/1   [5 chars]
    en  Good.
    >>  ............................................
    pt  Bom.
    >>  ............................................
  dialogue.conversations.neighbour.resume.leave/2   [16 chars]
    en  Until next time.
    >>  ............................................
    pt  Até a próxima.
    >>  ............................................
  dialogue.conversations.neighbour.resume.leave/3   [14 chars]
    en  Mind the road.
    >>  ............................................
    pt  Cuidado na estrada.
    >>  ............................................
```

---


## `conversations.arc.neighbour.resume.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `neighbour`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.neighbour.resume` — e.g. "They mentioned somebody had been. They didn't say who and they didn't have to."


```text
POOL   dialogue key: dialogue.conversations.arc.neighbour.resume.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.arc.neighbour.resume.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.arc.neighbour.resume.respond   [24 chars]
    en  So that's how that went.
    >>  ............................................
    pt  Então foi assim que ficou.
    >>  ............................................
```


### Button `say_nothing` — "Best they never know it was me."

*stance family `restraint` · tone `plain` · outcome `appreciated` · answers the beat(s) `neighbour.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.resume.say_nothing` — accepted phrasings: "best they never know it was me"; "do not tell them it was me"; "keep my name out of it"
  - scored words: `know`(0.4), `never`(0.6)

```text
POOL   dialogue key: dialogue.conversations.arc.neighbour.resume.respond.say_nothing
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.neighbour.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.neighbour.resume.respond.say_nothing   [31 chars]
    en  Best they never know it was me.
    >>  ............................................
    pt  Melhor que nunca saibam que fui eu.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `neighbour.resume.quiet`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `neighbour.resume.say_nothing`)_
- Does: session `turn`
- Then opens: `conversations.arc.neighbour.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.neighbour.resume.say_nothing
WHO    VILLAGER — what the player reads after pressing "Best they never know it was me."
       spoken on: conversations.arc.neighbour.resume.respond, button `say_nothing`
       leaves the player on: conversations.arc.neighbour.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.resume.say_nothing`: the villager accepts. Subject `neighbour.needs`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.resume.say_nothing/1   [64 chars]
    en  That's how it should be done and it's how almost nobody does it.
    >>  ............................................
    pt  É assim que se deve fazer e é como quase ninguém faz.
    >>  ............................................
  dialogue.conversations.neighbour.resume.say_nothing/2   [76 chars]
    en  Then they won't. I've kept quieter things than that for people I liked less.
    >>  ............................................
    pt  Então não vão saber. Já guardei coisas mais silenciosas por gente de quem gostei menos.
    >>  ............................................
  dialogue.conversations.neighbour.resume.say_nothing/3   [58 chars]
    en  They'll guess. They'll be wrong about who, and that'll do.
    >>  ............................................
    pt  Vão adivinhar. Vão errar sobre quem, e está bom assim.
    >>  ............................................
```


### Button `ask_how_they_are` — "How are they, in themselves?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `neighbour.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.resume.ask_how_they_are` — accepted phrasings: "how are they in themselves"; "how are they keeping"; "how are they doing really"
  - the message must contain one of: `themselves`
  - scored words: `how`(0.3), `themselves`(1.2)

```text
POOL   dialogue key: dialogue.conversations.arc.neighbour.resume.respond.ask_how_they_are
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.neighbour.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.neighbour.resume.respond.ask_how_they_are   [28 chars]
    en  How are they, in themselves?
    >>  ............................................
    pt  Como eles estão, neles mesmos?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.arc.neighbour.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.neighbour.resume.ask_how_they_are
WHO    VILLAGER — what the player reads after pressing "How are they, in themselves?"
       spoken on: conversations.arc.neighbour.resume.respond, button `ask_how_they_are`
       leaves the player on: conversations.arc.neighbour.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.resume.ask_how_they_are`: the villager discloses. Subject `neighbour.needs`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.resume.ask_how_they_are/1   [73 chars]
    en  Better than the week before. That's the most anyone can say about anyone.
    >>  ............................................
    pt  Melhor que na semana anterior. É o máximo que se pode dizer de alguém.
    >>  ............................................
  dialogue.conversations.neighbour.resume.ask_how_they_are/2   [70 chars]
    en  Proud and cold, in that order. The cold will pass and the proud won't.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem. O frio passa e o orgulho não.
    >>  ............................................
  dialogue.conversations.neighbour.resume.ask_how_they_are/3   [68 chars]
    en  Asking that puts you ahead of everyone who asked about the firewood.
    >>  ............................................
    pt  Perguntar isso te põe à frente de todos que perguntaram sobre a lenha.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before. I've learned to count in weeks, which tells you something.
    >>  ............................................
    pt  Melhor que na semana anterior. Aprendi a contar em semanas, o que já diz algo.
    >>  ............................................
  anxious.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order, and I don't know which to be sorrier about.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem, e não sei de qual ter mais pena.
    >>  ............................................
  anxious.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  Asking that puts you ahead of everyone else, and I wish it didn't.
    >>  ............................................
    pt  Perguntar isso te põe à frente de todos, e eu queria que não pusesse.
    >>  ............................................
  athletic.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before. After sixty years that's the only honest measure left.
    >>  ............................................
    pt  Melhor que na semana anterior. Com sessenta anos é a única medida honesta que sobra.
    >>  ............................................
  athletic.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order. I've seen pride outlast worse winters than this.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem. Vi orgulho durar mais que invernos piores.
    >>  ............................................
  athletic.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  Asking that puts you ahead of everyone who asked about the firewood. It always does.
    >>  ............................................
    pt  Perguntar isso te põe à frente de quem perguntou da lenha. Sempre põe.
    >>  ............................................
  confident.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before. That's the most anyone can say about anyone.
    >>  ............................................
    pt  Melhor que na semana anterior. É o máximo que se pode dizer de alguém.
    >>  ............................................
  confident.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order. The cold will pass and the proud won't.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem. O frio passa e o orgulho não.
    >>  ............................................
  confident.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  Asking that puts you ahead of everyone who asked about the firewood.
    >>  ............................................
    pt  Perguntar isso te põe à frente de todos que perguntaram sobre a lenha.
    >>  ............................................
  crabby.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before. That's the most anyone can say about anyone.
    >>  ............................................
    pt  Melhor que na semana anterior. É o máximo que se pode dizer de alguém.
    >>  ............................................
  crabby.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order. The cold will pass and the proud won't.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem. O frio passa e o orgulho não.
    >>  ............................................
  crabby.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  Asking that puts you ahead of everyone who asked about the firewood.
    >>  ............................................
    pt  Perguntar isso te põe à frente de todos que perguntaram sobre a lenha.
    >>  ............................................
  extroverted.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before, %1$s. That's the most anyone can say about anyone.
    >>  ............................................
    pt  Melhor que na semana anterior, %1$s. É o máximo que se pode dizer de alguém.
    >>  ............................................
  extroverted.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order. The cold will pass and the proud won't.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem. O frio passa e o orgulho não.
    >>  ............................................
  extroverted.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  Asking that puts you ahead of everyone who asked about the firewood, and I noticed.
    >>  ............................................
    pt  Perguntar isso te põe à frente de quem perguntou da lenha, e eu notei.
    >>  ............................................
  flirty.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before, %1$s. That's the most anyone can say about anyone.
    >>  ............................................
    pt  Melhor que na semana anterior, %1$s. É o máximo que se pode dizer de alguém.
    >>  ............................................
  flirty.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order. The cold will pass and the proud won't.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem. O frio passa e o orgulho não.
    >>  ............................................
  flirty.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  Asking that puts you ahead of everyone who asked about the firewood, and I noticed.
    >>  ............................................
    pt  Perguntar isso te põe à frente de quem perguntou da lenha, e eu notei.
    >>  ............................................
  friendly.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before, %1$s. That's the most anyone can say about anyone.
    >>  ............................................
    pt  Melhor que na semana anterior, %1$s. É o máximo que se pode dizer de alguém.
    >>  ............................................
  friendly.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order. The cold will pass and the proud won't.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem. O frio passa e o orgulho não.
    >>  ............................................
  friendly.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  Asking that puts you ahead of everyone who asked about the firewood, and I noticed.
    >>  ............................................
    pt  Perguntar isso te põe à frente de quem perguntou da lenha, e eu notei.
    >>  ............................................
  gloomy.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before. I've learned to count in weeks, which tells you something.
    >>  ............................................
    pt  Melhor que na semana anterior. Aprendi a contar em semanas, o que já diz algo.
    >>  ............................................
  gloomy.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order, and I don't know which to be sorrier about.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem, e não sei de qual ter mais pena.
    >>  ............................................
  gloomy.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  Asking that puts you ahead of everyone else, and I wish it didn't.
    >>  ............................................
    pt  Perguntar isso te põe à frente de todos, e eu queria que não pusesse.
    >>  ............................................
  greedy.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before. That's the most anyone can say about anyone.
    >>  ............................................
    pt  Melhor que na semana anterior. É o máximo que se pode dizer de alguém.
    >>  ............................................
  greedy.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order. The cold will pass and the proud won't.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem. O frio passa e o orgulho não.
    >>  ............................................
  greedy.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  Asking that puts you ahead of everyone who asked about the firewood.
    >>  ............................................
    pt  Perguntar isso te põe à frente de todos que perguntaram sobre a lenha.
    >>  ............................................
  grumpy.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before. That's the most anyone can say about anyone.
    >>  ............................................
    pt  Melhor que na semana anterior. É o máximo que se pode dizer de alguém.
    >>  ............................................
  grumpy.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order. The cold will pass and the proud won't.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem. O frio passa e o orgulho não.
    >>  ............................................
  grumpy.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  Asking that puts you ahead of everyone who asked about the firewood.
    >>  ............................................
    pt  Perguntar isso te põe à frente de todos que perguntaram sobre a lenha.
    >>  ............................................
  introverted.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before.
    >>  ............................................
    pt  Melhor que na semana anterior.
    >>  ............................................
  introverted.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem.
    >>  ............................................
  introverted.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  You asked after them. Nobody else did.
    >>  ............................................
    pt  Você perguntou por eles. Mais ninguém.
    >>  ............................................
  lazy.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before. After sixty years that's the only honest measure left.
    >>  ............................................
    pt  Melhor que na semana anterior. Com sessenta anos é a única medida honesta que sobra.
    >>  ............................................
  lazy.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order. I've seen pride outlast worse winters than this.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem. Vi orgulho durar mais que invernos piores.
    >>  ............................................
  lazy.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  Asking that puts you ahead of everyone who asked about the firewood. It always does.
    >>  ............................................
    pt  Perguntar isso te põe à frente de quem perguntou da lenha. Sempre põe.
    >>  ............................................
  odd.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before.
    >>  ............................................
    pt  Melhor que na semana anterior.
    >>  ............................................
  odd.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem.
    >>  ............................................
  odd.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  You asked after them. Nobody else did.
    >>  ............................................
    pt  Você perguntou por eles. Mais ninguém.
    >>  ............................................
  peaceful.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before. After sixty years that's the only honest measure left.
    >>  ............................................
    pt  Melhor que na semana anterior. Com sessenta anos é a única medida honesta que sobra.
    >>  ............................................
  peaceful.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order. I've seen pride outlast worse winters than this.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem. Vi orgulho durar mais que invernos piores.
    >>  ............................................
  peaceful.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  Asking that puts you ahead of everyone who asked about the firewood. It always does.
    >>  ............................................
    pt  Perguntar isso te põe à frente de quem perguntou da lenha. Sempre põe.
    >>  ............................................
  peppy.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before! Which is the most anyone can say about anyone, really.
    >>  ............................................
    pt  Melhor que na semana anterior! O que é o máximo que se pode dizer de alguém.
    >>  ............................................
  peppy.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order. The cold will pass; the proud is permanent.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem. O frio passa; o orgulho é permanente.
    >>  ............................................
  peppy.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  Asking that puts you ahead of everyone who asked about the firewood. Everyone.
    >>  ............................................
    pt  Perguntar isso te põe à frente de todos que perguntaram da lenha. Todos.
    >>  ............................................
  playful.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before! Which is the most anyone can say about anyone, really.
    >>  ............................................
    pt  Melhor que na semana anterior! O que é o máximo que se pode dizer de alguém.
    >>  ............................................
  playful.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order. The cold will pass; the proud is permanent.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem. O frio passa; o orgulho é permanente.
    >>  ............................................
  playful.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  Asking that puts you ahead of everyone who asked about the firewood. Everyone.
    >>  ............................................
    pt  Perguntar isso te põe à frente de todos que perguntaram da lenha. Todos.
    >>  ............................................
  relaxed.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before. After sixty years that's the only honest measure left.
    >>  ............................................
    pt  Melhor que na semana anterior. Com sessenta anos é a única medida honesta que sobra.
    >>  ............................................
  relaxed.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order. I've seen pride outlast worse winters than this.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem. Vi orgulho durar mais que invernos piores.
    >>  ............................................
  relaxed.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  Asking that puts you ahead of everyone who asked about the firewood. It always does.
    >>  ............................................
    pt  Perguntar isso te põe à frente de quem perguntou da lenha. Sempre põe.
    >>  ............................................
  sensitive.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before. I've learned to count in weeks, which tells you something.
    >>  ............................................
    pt  Melhor que na semana anterior. Aprendi a contar em semanas, o que já diz algo.
    >>  ............................................
  sensitive.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order, and I don't know which to be sorrier about.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem, e não sei de qual ter mais pena.
    >>  ............................................
  sensitive.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  Asking that puts you ahead of everyone else, and I wish it didn't.
    >>  ............................................
    pt  Perguntar isso te põe à frente de todos, e eu queria que não pusesse.
    >>  ............................................
  shy.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before.
    >>  ............................................
    pt  Melhor que na semana anterior.
    >>  ............................................
  shy.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem.
    >>  ............................................
  shy.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  You asked after them. Nobody else did.
    >>  ............................................
    pt  Você perguntou por eles. Mais ninguém.
    >>  ............................................
  upbeat.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before! Which is the most anyone can say about anyone, really.
    >>  ............................................
    pt  Melhor que na semana anterior! O que é o máximo que se pode dizer de alguém.
    >>  ............................................
  upbeat.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order. The cold will pass; the proud is permanent.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem. O frio passa; o orgulho é permanente.
    >>  ............................................
  upbeat.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  Asking that puts you ahead of everyone who asked about the firewood. Everyone.
    >>  ............................................
    pt  Perguntar isso te põe à frente de todos que perguntaram da lenha. Todos.
    >>  ............................................
  witty.dialogue.conversations.neighbour.resume.ask_how_they_are/1
    en  Better than the week before! Which is the most anyone can say about anyone, really.
    >>  ............................................
    pt  Melhor que na semana anterior! O que é o máximo que se pode dizer de alguém.
    >>  ............................................
  witty.dialogue.conversations.neighbour.resume.ask_how_they_are/2
    en  Proud and cold, in that order. The cold will pass; the proud is permanent.
    >>  ............................................
    pt  Orgulhosos e com frio, nessa ordem. O frio passa; o orgulho é permanente.
    >>  ............................................
  witty.dialogue.conversations.neighbour.resume.ask_how_they_are/3
    en  Asking that puts you ahead of everyone who asked about the firewood. Everyone.
    >>  ............................................
    pt  Perguntar isso te põe à frente de todos que perguntaram da lenha. Todos.
    >>  ............................................
```

</details>


### Button `ill_keep_going` — "I'll keep looking in on them."

*stance family `practical_help` · tone `plain` · outcome `appreciated` · answers the beat(s) `neighbour.resume.opener`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.resume.ill_keep_going` — accepted phrasings: "i will keep looking in on them"; "i will call again"; "i will check on them again"
  - the message must contain one of: `looking`
  - scored words: `keep`(0.5), `looking`(1.0)

```text
POOL   dialogue key: dialogue.conversations.arc.neighbour.resume.respond.ill_keep_going
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.neighbour.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.neighbour.resume.respond.ill_keep_going   [29 chars]
    en  I'll keep looking in on them.
    >>  ............................................
    pt  Vou continuar dando uma passada lá.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `neighbour.resume.again`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `neighbour.resume.ill_keep_going`)_
- Does: session `turn`
- Then opens: `conversations.arc.neighbour.resume.followup`
- …where the player's next choices will be: "Thank you for keeping me in it." | "I'll leave it with you." | "I'll get on."

```text
POOL   dialogue key: dialogue.conversations.neighbour.resume.ill_keep_going
WHO    VILLAGER — what the player reads after pressing "I'll keep looking in on them."
       spoken on: conversations.arc.neighbour.resume.respond, button `ill_keep_going`
       leaves the player on: conversations.arc.neighbour.resume.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.resume.ill_keep_going`: the villager accepts. Subject `neighbour.needs`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.resume.ill_keep_going/1   [68 chars]
    en  Then you'll be the second person in this village who ever did twice.
    >>  ............................................
    pt  Então você será a segunda pessoa deste vilarejo que fez duas vezes.
    >>  ............................................
  dialogue.conversations.neighbour.resume.ill_keep_going/2   [80 chars]
    en  That's the part that counts. Anybody can do a thing once and feel good about it.
    >>  ............................................
    pt  É essa a parte que conta. Qualquer um faz uma vez e se sente bem.
    >>  ............................................
  dialogue.conversations.neighbour.resume.ill_keep_going/3   [81 chars]
    en  Careful. They'll start expecting it, and expecting is how people survive winters.
    >>  ............................................
    pt  Cuidado. Vão começar a esperar, e esperar é como se atravessa o inverno.
    >>  ............................................
```


### Button `leave` — "I'll get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `neighbour.resume.opener` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.arc.neighbour.resume.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.arc.neighbour.resume.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.arc.neighbour.resume.respond.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.neighbour.resume.leave
WHO    VILLAGER — what the player reads after pressing "I'll get on."
       spoken on: conversations.arc.neighbour.resume.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.resume.leave`: the villager accepts. Subject `neighbour.needs`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.arc.neighbour.resume.followup / leave
```

> Written out in full under **`conversations.arc.neighbour.resume.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.neighbour.followup`

**Reached from 4 route(s):** `conversations.scene.neighbour.the_unthanked_kindness.respond` / `ask_who`; `conversations.scene.neighbour.the_unthanked_kindness.respond` / `say_that_is_the_village`; `conversations.scene.neighbour.two_doors_down.respond` / `advise_staying_out`; `conversations.scene.neighbour.two_doors_down.respond` / `ask_what_it_is_really_about`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.neighbour.the_unthanked_kindness.agreed` — e.g. "It is, and it happens about four times a week and nobody counts, and the arguments happen once a season and everybody does."
- `conversations.scene.neighbour.the_unthanked_kindness.answered` — e.g. "I do, and they have gone to some trouble not to be known, so I am going to respect the trouble."
- `conversations.scene.neighbour.two_doors_down.declined_to_say` — e.g. "I have a fair idea and I am going to keep it, because a guess repeated becomes the official version by Thursday."
- `conversations.scene.neighbour.two_doors_down.steadied` — e.g. "That is the plan and it costs me something with both of them, and it will cost me less than the alternative."


```text
POOL   dialogue key: dialogue.conversations.scene.neighbour.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.neighbour.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.neighbour.followup   [25 chars]
    en  Anything else about them?
    >>  ............................................
    pt  Mais alguma coisa sobre eles?
    >>  ............................................
```


### Button `leave` — "That's the neighbours, then."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:neighbour.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.neighbour.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.neighbour.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.neighbour.followup.leave   [28 chars]
    en  That's the neighbours, then.
    >>  ............................................
    pt  São os vizinhos, então.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.scene.neighbour.leaving
WHO    VILLAGER — what the player reads after pressing "That's the neighbours, then."
       spoken on: conversations.scene.neighbour.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.scene.leaving`: the villager accepts. Subject `neighbour.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.neighbour.the_unthanked_kindness.respond / leave; conversations.scene.neighbour.two_doors_down.respond / leave
```

```text
  dialogue.conversations.scene.neighbour.leaving/1   [39 chars]
    en  They will sort it out or they will not.
    >>  ............................................
    pt  Vão resolver ou não vão.
    >>  ............................................
  dialogue.conversations.scene.neighbour.leaving/2   [32 chars]
    en  Right. That is the lane for you.
    >>  ............................................
    pt  Certo. É a viela.
    >>  ............................................
  dialogue.conversations.scene.neighbour.leaving/3   [13 chars]
    en  It will keep.
    >>  ............................................
    pt  Isso pode esperar.
    >>  ............................................
```

---


## `conversations.scene.neighbour.the_unthanked_kindness.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `neighbour`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.neighbour.the_unthanked_kindness` — e.g. "Somebody has been leaving firewood at a door and has told nobody, and I have decided to let them get away with it."


```text
POOL   dialogue key: dialogue.conversations.scene.neighbour.the_unthanked_kindness.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.neighbour.the_unthanked_kindness.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.neighbour.the_unthanked_kindness.respond   [26 chars]
    en  Something a neighbour did.
    >>  ............................................
    pt  Algo que um vizinho fez.
    >>  ............................................
```


### Button `ask_who` — "Do you know who it was?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `neighbour.the_unthanked_kindness.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.neighbour.the_unthanked_kindness.ask_who` — accepted phrasings: "do you know who it was"; "do you know who it was"; "who did that"
  - the message must contain one of: `who`
  - scored words: `who`(1.8), `know`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.neighbour.the_unthanked_kindness.respond.ask_who
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.neighbour.the_unthanked_kindness.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.neighbour.the_unthanked_kindness.respond.ask_who   [23 chars]
    en  Do you know who it was?
    >>  ............................................
    pt  Você sabe quem foi?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `neighbour.small_good`)_
- Does: session `turn`
- Then opens: `conversations.scene.neighbour.followup`
- …where the player's next choices will be: "That's the neighbours, then."

```text
POOL   dialogue key: dialogue.conversations.scene.neighbour.the_unthanked_kindness.answered
WHO    VILLAGER — what the player reads after pressing "Do you know who it was?"
       spoken on: conversations.scene.neighbour.the_unthanked_kindness.respond, button `ask_who`
       leaves the player on: conversations.scene.neighbour.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.the_unthanked_kindness.open.answered`: the villager deflects. Subject `neighbour.small_good`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:neighbour` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.neighbour.the_unthanked_kindness.answered/1   [95 chars]
    en  I do, and they have gone to some trouble not to be known, so I am going to respect the trouble.
    >>  ............................................
    pt  Sei, e essa pessoa se deu ao trabalho de não ser identificada, então eu vou respeitar o trabalho.
    >>  ............................................
  dialogue.conversations.scene.neighbour.the_unthanked_kindness.answered/2   [97 chars]
    en  I have a suspicion and one boot print, which is more evidence than I usually have about anything.
    >>  ............................................
    pt  Tenho uma suspeita e uma pegada de bota, o que é mais prova do que eu costumo ter sobre qualquer coisa.
    >>  ............................................
  dialogue.conversations.scene.neighbour.the_unthanked_kindness.answered/3   [101 chars]
    en  It does not matter. That is the whole point of doing it that way and I would spoil it by naming them.
    >>  ............................................
    pt  Não importa. É esse o objetivo de fazer assim, e eu estragaria nomeando.
    >>  ............................................
```


### Button `say_that_is_the_village` — "That's what a village is for."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `neighbour.the_unthanked_kindness.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.neighbour.the_unthanked_kindness.say_that_is_the_village` — accepted phrasings: "thats what a village is for"; "that is what a village is for"; "that is the point of living close"
  - the message must contain one of: `village`, `close`, `point`
  - scored words: `village`(1.8), `close`(1.8), `point`(1.8), `thats`(0.8), `living`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.neighbour.the_unthanked_kindness.respond.say_that_is_the_village
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.neighbour.the_unthanked_kindness.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.neighbour.the_unthanked_kindness.respond.say_that_is_the_village   [29 chars]
    en  That's what a village is for.
    >>  ............................................
    pt  É para isso que serve uma vila.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `neighbour.small_good`)_
- Does: session `turn`
- Then opens: `conversations.scene.neighbour.followup`
- …where the player's next choices will be: "That's the neighbours, then."

```text
POOL   dialogue key: dialogue.conversations.scene.neighbour.the_unthanked_kindness.agreed
WHO    VILLAGER — what the player reads after pressing "That's what a village is for."
       spoken on: conversations.scene.neighbour.the_unthanked_kindness.respond, button `say_that_is_the_village`
       leaves the player on: conversations.scene.neighbour.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.the_unthanked_kindness.open.agreed`: the villager accepts. Subject `neighbour.small_good`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:neighbour` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.neighbour.the_unthanked_kindness.agreed/1   [123 chars]
    en  It is, and it happens about four times a week and nobody counts, and the arguments happen once a season and everybody does.
    >>  ............................................
    pt  É, e acontece umas quatro vezes por semana e ninguém conta, e as brigas acontecem uma vez por estação e todo mundo conta.
    >>  ............................................
  dialogue.conversations.scene.neighbour.the_unthanked_kindness.agreed/2   [115 chars]
    en  Yes. If you wrote down only the good ones you would get a very strange and much more accurate history of this lane.
    >>  ............................................
    pt  Sim. Se você anotasse só as boas, teria uma história muito estranha e bem mais exata desta viela.
    >>  ............................................
  dialogue.conversations.scene.neighbour.the_unthanked_kindness.agreed/3   [101 chars]
    en  Thank you for saying it out loud. That sort of thing needs saying occasionally or it stops happening.
    >>  ............................................
    pt  Obrigada por dizer em voz alta. Esse tipo de coisa precisa ser dito de vez em quando ou para de acontecer.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `neighbour.the_unthanked_kindness.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.neighbour.the_unthanked_kindness.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.neighbour.the_unthanked_kindness.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.neighbour.the_unthanked_kindness.respond.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.scene.neighbour.leaving
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.scene.neighbour.the_unthanked_kindness.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.scene.leaving`: the villager accepts. Subject `neighbour.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.neighbour.followup / leave; conversations.scene.neighbour.two_doors_down.respond / leave
```

> Written out in full under **`conversations.scene.neighbour.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.neighbour.two_doors_down.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `neighbour`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.neighbour.two_doors_down` — e.g. "Two of them have stopped speaking over something neither will name, and the lane has quietly picked sides."


```text
POOL   dialogue key: dialogue.conversations.scene.neighbour.two_doors_down.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.neighbour.two_doors_down.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.neighbour.two_doors_down.respond   [19 chars]
    en  The two doors down.
    >>  ............................................
    pt  Os dois da porta ao lado.
    >>  ............................................
```


### Button `advise_staying_out` — "Keep refusing to take a side."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `neighbour.two_doors_down.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.neighbour.two_doors_down.advise_staying_out` — accepted phrasings: "keep refusing to take a side"; "keep refusing to take a side"; "stay out of the argument"
  - the message must contain one of: `refusing`, `side`, `argument`
  - scored words: `refusing`(1.8), `side`(1.8), `argument`(1.8), `keep`(0.8), `take`(0.8), `stay`(0.8), `out`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.neighbour.two_doors_down.respond.advise_staying_out
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.neighbour.two_doors_down.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.neighbour.two_doors_down.respond.advise_staying_out   [29 chars]
    en  Keep refusing to take a side.
    >>  ............................................
    pt  Continue recusando tomar partido.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, trust +1  _(recorded under topic `neighbour.dispute`)_
- Does: session `turn`
- Then opens: `conversations.scene.neighbour.followup`
- …where the player's next choices will be: "That's the neighbours, then."

```text
POOL   dialogue key: dialogue.conversations.scene.neighbour.two_doors_down.steadied
WHO    VILLAGER — what the player reads after pressing "Keep refusing to take a side."
       spoken on: conversations.scene.neighbour.two_doors_down.respond, button `advise_staying_out`
       leaves the player on: conversations.scene.neighbour.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.two_doors_down.open.steadied`: the villager accepts. Subject `neighbour.dispute`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `topic:neighbour` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.neighbour.two_doors_down.steadied/1   [108 chars]
    en  That is the plan and it costs me something with both of them, and it will cost me less than the alternative.
    >>  ............................................
    pt  É o plano e me custa alguma coisa com os dois, e vai custar menos que a alternativa.
    >>  ............................................
  dialogue.conversations.scene.neighbour.two_doors_down.steadied/2   [107 chars]
    en  Yes. The person who agrees with both is trusted by neither the day they find out, and they always find out.
    >>  ............................................
    pt  Sim. Quem concorda com os dois não é confiado por nenhum no dia em que descobrem, e eles sempre descobrem.
    >>  ............................................
  dialogue.conversations.scene.neighbour.two_doors_down.steadied/3   [100 chars]
    en  I shall keep saying I do not know, which happens to be true, and which nobody in this lane believes.
    >>  ............................................
    pt  Vou continuar dizendo que não sei, o que por acaso é verdade, e em que ninguém nesta viela acredita.
    >>  ............................................
```


### Button `ask_what_it_is_really_about` — "What's it really about?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `neighbour.two_doors_down.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.neighbour.two_doors_down.ask_what_it_is_really_about` — accepted phrasings: "whats it really about"; "what is it really about"; "what lies under the fence"
  - the message must contain one of: `really`, `under`
  - scored words: `really`(1.8), `under`(1.8), `whats`(0.8), `lies`(0.8), `fence`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.neighbour.two_doors_down.respond.ask_what_it_is_really_about
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.neighbour.two_doors_down.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.neighbour.two_doors_down.respond.ask_what_it_is_really_about   [23 chars]
    en  What's it really about?
    >>  ............................................
    pt  Sobre o que é de verdade?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `neighbour.dispute`)_
- Does: session `turn`
- Then opens: `conversations.scene.neighbour.followup`
- …where the player's next choices will be: "That's the neighbours, then."

```text
POOL   dialogue key: dialogue.conversations.scene.neighbour.two_doors_down.declined_to_say
WHO    VILLAGER — what the player reads after pressing "What's it really about?"
       spoken on: conversations.scene.neighbour.two_doors_down.respond, button `ask_what_it_is_really_about`
       leaves the player on: conversations.scene.neighbour.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.two_doors_down.open.declined_to_say`: the villager deflects. Subject `neighbour.dispute`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `topic:neighbour` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a teen/adult
```

```text
  dialogue.conversations.scene.neighbour.two_doors_down.declined_to_say/1   [112 chars]
    en  I have a fair idea and I am going to keep it, because a guess repeated becomes the official version by Thursday.
    >>  ............................................
    pt  Tenho uma boa ideia e vou guardar, porque um palpite repetido vira a versão oficial até quinta.
    >>  ............................................
  dialogue.conversations.scene.neighbour.two_doors_down.declined_to_say/2   [100 chars]
    en  Something from before I lived here. That is as much as I will say and it is more than I should have.
    >>  ............................................
    pt  Alguma coisa de antes de eu morar aqui. É tudo o que eu vou dizer e já é mais do que eu deveria.
    >>  ............................................
  dialogue.conversations.scene.neighbour.two_doors_down.declined_to_say/3   [103 chars]
    en  If either of them wants it known they will say it. Me saying it for them is how a fence becomes a feud.
    >>  ............................................
    pt  Se um dos dois quiser que se saiba, vai dizer. Eu dizer por eles é como uma cerca vira uma rixa.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `neighbour.two_doors_down.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.neighbour.two_doors_down.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.neighbour.two_doors_down.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.neighbour.two_doors_down.respond.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.scene.neighbour.leaving
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.scene.neighbour.two_doors_down.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.scene.leaving`: the villager accepts. Subject `neighbour.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   the same pool is also spoken at: conversations.scene.neighbour.followup / leave; conversations.scene.neighbour.the_unthanked_kindness.respond / leave
```

> Written out in full under **`conversations.scene.neighbour.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.neighbour.dispute`

**Reached from 1 route(s):** `conversations.topic.neighbour.more` / `you_two`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.neighbour.more.you_two` — e.g. "We did. There was a winter we didn't, and neither of us has explained it since."


```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.dispute
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.neighbour.dispute
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.neighbour.dispute   [33 chars]
    en  That's where the two of us stand.
    >>  ............................................
    pt  É aí que nós dois estamos.
    >>  ............................................
```


### Button `want_it_mended` — "Would you want it mended?"

*stance family `practical_help` · tone `gentle` · outcome `engaged` · answers the beat(s) `neighbour.more.you_two`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.dispute.mend` — accepted phrasings: "would you want it mended"; "do you want to fix it"; "would you patch it up if you could"
  - the message must contain one of: `mended`, `mend`
  - scored words: `mended`(1.5), `mend`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.dispute.want_it_mended
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.dispute
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.dispute.want_it_mended   [25 chars]
    en  Would you want it mended?
    >>  ............................................
    pt  Você gostaria de consertar isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `neighbour.dispute.mend`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +1  _(recorded under topic `neighbour.dispute.mend`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.dispute.mend
WHO    VILLAGER — what the player reads after pressing "Would you want it mended?"
       spoken on: conversations.topic.neighbour.dispute, button `want_it_mended`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.dispute.mend`: the villager qualifys. Subject `neighbour.dispute`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.dispute.mend/1   [82 chars]
    en  ...I would. I've not said that out loud before and I'd thank you not to repeat it.
    >>  ............................................
    pt  ...Eu gostaria. Nunca disse isso em voz alta e agradeço se não repetir.
    >>  ............................................
  dialogue.conversations.neighbour.dispute.mend/2   [79 chars]
    en  Wanting it isn't the difficulty. Neither of us knows how to start the sentence.
    >>  ............................................
    pt  Querer não é a dificuldade. Nenhum de nós sabe como começar a frase.
    >>  ............................................
  dialogue.conversations.neighbour.dispute.mend/3   [67 chars]
    en  No. And I've turned that answer over enough times to be sure of it.
    >>  ............................................
    pt  Não. E já revirei essa resposta o bastante pra ter certeza.
    >>  ............................................
```


### Button `between_you` — "That's between the two of you."

*stance family `restraint` · tone `plain` · outcome `appreciated` · answers the beat(s) `neighbour.more.you_two`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.dispute.between` — accepted phrasings: "that is between the two of you"; "that is not my business"; "i will stay out of it"
  - the message must contain one of: `between`
  - scored words: `between`(1.5), `two`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.dispute.between_you
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.dispute
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.dispute.between_you   [30 chars]
    en  That's between the two of you.
    >>  ............................................
    pt  Isso é entre vocês dois.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2  _(recorded under topic `neighbour.dispute.between`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.dispute.between
WHO    VILLAGER — what the player reads after pressing "That's between the two of you."
       spoken on: conversations.topic.neighbour.dispute, button `between_you`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.dispute.between`: the villager accepts. Subject `neighbour.dispute`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.dispute.between/1   [72 chars]
    en  It is, and I'm glad somebody said so before offering to carry a message.
    >>  ............................................
    pt  É, e ainda bem que alguém disse isso antes de oferecer levar recado.
    >>  ............................................
  dialogue.conversations.neighbour.dispute.between/2   [78 chars]
    en  Just so. Half the village has tried to be the bridge and made the water wider.
    >>  ............................................
    pt  Pois é. Metade do vilarejo tentou ser ponte e só alargou o rio.
    >>  ............................................
  dialogue.conversations.neighbour.dispute.between/3   [72 chars]
    en  Thank you. I told you because you asked, not because I wanted it solved.
    >>  ............................................
    pt  Obrigado. Eu contei porque você perguntou, não porque queria resolver.
    >>  ............................................
```


### Button `leave` — "Understood."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `neighbour.more.you_two` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.dispute.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.dispute
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.dispute.leave   [11 chars]
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
POOL   dialogue key: dialogue.conversations.neighbour.dispute.leave
WHO    VILLAGER — what the player reads after pressing "Understood."
       spoken on: conversations.topic.neighbour.dispute, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.dispute.leave`: the villager accepts. Subject `neighbour.dispute`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.dispute.leave/1   [9 chars]
    en  So it is.
    >>  ............................................
    pt  É assim mesmo.
    >>  ............................................
  dialogue.conversations.neighbour.dispute.leave/2   [6 chars]
    en  Right.
    >>  ............................................
    pt  Certo.
    >>  ............................................
  dialogue.conversations.neighbour.dispute.leave/3   [11 chars]
    en  Off you go.
    >>  ............................................
    pt  Pode ir.
    >>  ............................................
```

---


## `conversations.topic.neighbour.family`

**Reached from 1 route(s):** `conversations.topic.neighbour.more` / `their_family`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.neighbour.more.their_family` — e.g. "A sister, two doors along, and they don't speak. That's the whole map of it."


```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.family
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.neighbour.family
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.neighbour.family   [24 chars]
    en  That's the family of it.
    >>  ............................................
    pt  É essa a família disso.
    >>  ............................................
```


### Button `hard_to_live_beside` — "That's a hard thing to live beside."

*stance family `empathy` · tone `gentle` · outcome `accepted` · answers the beat(s) `neighbour.more.their_family`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.family.hard` — accepted phrasings: "that is a hard thing to live beside"; "that must be hard to live with"; "that cannot be easy so close"
  - the message must contain one of: `beside`
  - scored words: `beside`(1.2), `live`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.family.hard_to_live_beside
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.family
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.family.hard_to_live_beside   [35 chars]
    en  That's a hard thing to live beside.
    >>  ............................................
    pt  É difícil viver ao lado disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `neighbour.family.hard`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `neighbour.family.hard`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.family.hard
WHO    VILLAGER — what the player reads after pressing "That's a hard thing to live beside."
       spoken on: conversations.topic.neighbour.family, button `hard_to_live_beside`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.family.hard`: the villager accepts. Subject `neighbour.family`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.family.hard/1   [63 chars]
    en  It is, and we've all got used to walking the long way round it.
    >>  ............................................
    pt  É, e todos nos acostumamos a dar a volta por fora.
    >>  ............................................
  dialogue.conversations.neighbour.family.hard/2   [70 chars]
    en  True enough. Two doors is no distance at all when you're not speaking.
    >>  ............................................
    pt  Bem verdade. Duas portas não é distância nenhuma quando não se fala.
    >>  ............................................
  dialogue.conversations.neighbour.family.hard/3   [58 chars]
    en  You'd think it would wear off. Twelve years and it hasn't.
    >>  ............................................
    pt  Você pensaria que passa com o tempo. Doze anos e não passou.
    >>  ............................................
```


### Button `common_enough` — "That's true of most villages, I'd think."

*stance family `candor` · tone `plain` · outcome `qualified` · answers the beat(s) `neighbour.more.their_family`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.family.common` — accepted phrasings: "that is true of most villages"; "that happens everywhere"; "every village is like that"
  - the message must contain one of: `villages`, `everywhere`
  - scored words: `villages`(1.5), `everywhere`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.family.common_enough
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.family
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.family.common_enough   [40 chars]
    en  That's true of most villages, I'd think.
    >>  ............................................
    pt  Isso vale pra quase todo vilarejo, eu acho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +1  _(recorded under topic `neighbour.family.common`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.family.common
WHO    VILLAGER — what the player reads after pressing "That's true of most villages, I'd think."
       spoken on: conversations.topic.neighbour.family, button `common_enough`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.family.common`: the villager qualifys. Subject `neighbour.family`, polarity `neutral`, permits followup, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.family.common/1   [72 chars]
    en  It is. Doesn't make it lighter, but it does stop me feeling singled out.
    >>  ............................................
    pt  É. Não deixa mais leve, mas para de me fazer sentir escolhido a dedo.
    >>  ............................................
  dialogue.conversations.neighbour.family.common/2   [72 chars]
    en  True. Every village is four families disagreeing politely for a century.
    >>  ............................................
    pt  Verdade. Todo vilarejo é quatro famílias discordando educadamente por um século.
    >>  ............................................
  dialogue.conversations.neighbour.family.common/3   [72 chars]
    en  Perhaps. I've only lived in the one, so I've nothing to hold it against.
    >>  ............................................
    pt  Talvez. Só morei neste, então não tenho com o que comparar.
    >>  ............................................
```


### Button `leave` — "I'll leave it there."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `neighbour.more.their_family` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.family.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.family
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.family.leave   [20 chars]
    en  I'll leave it there.
    >>  ............................................
    pt  Vou parar por aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.family.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave it there."
       spoken on: conversations.topic.neighbour.family, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.family.leave`: the villager accepts. Subject `neighbour.family`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.family.leave/1   [6 chars]
    en  Quite.
    >>  ............................................
    pt  Exato.
    >>  ............................................
  dialogue.conversations.neighbour.family.leave/2   [21 chars]
    en  That'll do for today.
    >>  ............................................
    pt  Por hoje está bom.
    >>  ............................................
  dialogue.conversations.neighbour.family.leave/3   [12 chars]
    en  Go on, %1$s.
    >>  ............................................
    pt  Vá lá, %1$s.
    >>  ............................................
```

---


## `conversations.topic.neighbour.followup`

**Reached from 3 route(s):** `conversations.topic.neighbour.respond` / `ask_more`; `conversations.topic.neighbour.respond` / `defend_them`; `conversations.topic.neighbour.respond` / `encourage_more`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.neighbour.ask_more` — e.g. "Really? Stubborn. Generous with the wrong things and careful with the right ones. Like most of us."
- `conversations.neighbour.defend_them` — e.g. "...They have, haven't they. I'd got into the habit of the other story. Thank you for the correction."
- `conversations.neighbour.encourage_more` — e.g. "...I could. I'm not going to. You've a hungry look about you and I don't like where this goes."


```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.neighbour.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.neighbour.followup   [40 chars]
    en  That's the whole of what I know, anyway.
    >>  ............................................
    pt  É tudo o que eu sei, enfim.
    >>  ............................................
```


### Button `ask_history` — "How long have you known them?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `neighbour.ask_more.to.neighbour`, `neighbour.defend_them.to.neighbour`, `neighbour.encourage_more.to.neighbour`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.ask_history` — accepted phrasings: "how long have you known them"; "how long have you known each other"; "known them long"
  - the message must contain one of: `long`, `known`
  - scored words: `long`(1.5), `known`(1.3)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.followup.ask_history
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.followup.ask_history   [29 chars]
    en  How long have you known them?
    >>  ............................................
    pt  Há quanto tempo você o conhece?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +4  _(recorded under topic `neighbour.ask_history`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.ask_history
WHO    VILLAGER — what the player reads after pressing "How long have you known them?"
       spoken on: conversations.topic.neighbour.followup, button `ask_history`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.ask_history.terminal`: the villager accepts. Subject `neighbour.history`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.neighbour.ask_history/1   [84 chars]
    en  Longer than I've known most things. You learn a person slowly in a place this small.
    >>  ............................................
    pt  Mais tempo do que conheço a maioria das coisas. Num lugar pequeno a gente aprende a pessoa devagar.
    >>  ............................................
  dialogue.conversations.neighbour.ask_history/2   [88 chars]
    en  Since before either of us had any sense, %1$s. That's a long apprenticeship in somebody.
    >>  ............................................
    pt  Desde antes de qualquer um de nós ter juízo, %1$s. É um longo aprendizado em alguém.
    >>  ............................................
  dialogue.conversations.neighbour.ask_history/3   [86 chars]
    en  Years. Which is why I'm careful about how I speak of them, and why I sometimes am not.
    >>  ............................................
    pt  Anos. É por isso que eu tomo cuidado ao falar dele, e por isso que às vezes não tomo.
    >>  ............................................
```


### Button `be_honest` — "You don't sound sure about them."

*stance family `candor` · tone `gentle` · answers the beat(s) `neighbour.ask_more.to.neighbour`, `neighbour.defend_them.to.neighbour`, `neighbour.encourage_more.to.neighbour`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.be_honest` — accepted phrasings: "you do not sound sure about them"; "you are not sure about them"; "you sound unsure"
  - the message must contain one of: `sure`, `sound`
  - scored words: `sure`(1.5), `sound`(1.1)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.followup.be_honest
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.followup.be_honest   [32 chars]
    en  You don't sound sure about them.
    >>  ............................................
    pt  Você não parece ter certeza sobre ele.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `neighbour.be_honest`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, trust +2  _(recorded under topic `neighbour.be_honest`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.be_honest
WHO    VILLAGER — what the player reads after pressing "You don't sound sure about them."
       spoken on: conversations.topic.neighbour.followup, button `be_honest`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.be_honest.terminal`: the villager accepts. Subject `neighbour.defence`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.neighbour.be_honest/1   [87 chars]
    en  ...I'm not. I've been telling it as though I'd decided, and I haven't. You caught that.
    >>  ............................................
    pt  ...Não tenho. Eu vinha contando como se tivesse decidido, e não decidi. Você percebeu.
    >>  ............................................
  dialogue.conversations.neighbour.be_honest/2   [80 chars]
    en  No, I'm not sure. I'd rather be uncertain out loud than certain and wrong, %1$s.
    >>  ............................................
    pt  Não, não tenho certeza. Prefiro ser incerto em voz alta a certo e errado, %1$s.
    >>  ............................................
  dialogue.conversations.neighbour.be_honest/3   [79 chars]
    en  Hm. You listen to the gaps as well as the words. That's an uncomfortable skill.
    >>  ............................................
    pt  Hm. Você escuta os vãos além das palavras. É uma habilidade desconfortável.
    >>  ............................................
```


### Button `let_it_lie` — "Let's leave them be."

*stance family `restraint` · tone `plain` · answers the beat(s) `neighbour.ask_more.to.neighbour`, `neighbour.defend_them.to.neighbour`, `neighbour.encourage_more.to.neighbour`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.let_it_lie` — accepted phrasings: "let us leave them be"; "leave them be"; "we should leave them alone"
  - the message must contain one of: `leave`
  - scored words: `leave`(1.5), `be`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.followup.let_it_lie
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.followup.let_it_lie   [20 chars]
    en  Let's leave them be.
    >>  ............................................
    pt  Vamos deixar ele em paz.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `neighbour.let_it_lie`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, tension -3  _(recorded under topic `neighbour.let_it_lie`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.let_it_lie
WHO    VILLAGER — what the player reads after pressing "Let's leave them be."
       spoken on: conversations.topic.neighbour.followup, button `let_it_lie`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.let_it_lie.terminal`: the villager accepts. Subject `neighbour.boundary`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.neighbour.let_it_lie/1   [68 chars]
    en  Let's. They'd do the same for me, most likely, and I'd want them to.
    >>  ............................................
    pt  Vamos. Ele faria o mesmo por mim, provavelmente, e eu ia querer que fizesse.
    >>  ............................................
  dialogue.conversations.neighbour.let_it_lie/2   [67 chars]
    en  Just so. Enough. Whatever they are, they've to live here too, %1$s.
    >>  ............................................
    pt  Pois é. Chega. Seja o que for, ele também tem que morar aqui, %1$s.
    >>  ............................................
  dialogue.conversations.neighbour.let_it_lie/3   [54 chars]
    en  Right. We'll let them be a person rather than a story.
    >>  ............................................
    pt  Certo. Vamos deixar ele ser uma pessoa em vez de uma história.
    >>  ............................................
```


### Button `ask_about_them` — "What else can you tell me about them?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `neighbour.ask_more.to.neighbour`, `neighbour.defend_them.to.neighbour`, `neighbour.encourage_more.to.neighbour`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.followup.ask_about_them` — accepted phrasings: "what else can you tell me about them"; "is there more to them"; "what else is there about them"
  - the message must contain one of: `else`
  - scored words: `else`(1.2), `them`(0.4), `more`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.followup.ask_about_them
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.followup.ask_about_them   [37 chars]
    en  What else can you tell me about them?
    >>  ............................................
    pt  O que mais você pode me contar sobre eles?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.neighbour.more`
- …where the player's next choices will be: "Have they family here?" | "Do the two of you get on?" | "Is there anything they need?" | "Should you be telling me this?" | "That's enough about them."

```text
POOL   dialogue key: dialogue.conversations.neighbour.more
WHO    VILLAGER — what the player reads after pressing "What else can you tell me about them?"
       spoken on: conversations.topic.neighbour.followup, button `ask_about_them`
       leaves the player on: conversations.topic.neighbour.more
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.more.open`: the villager invites. Subject `neighbour.talk`, polarity `neutral`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, practical_help, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.more/1   [67 chars]
    en  There's more to them than the one story, if you've the time for it.
    >>  ............................................
    pt  Tem mais neles do que uma história só, se você tiver tempo.
    >>  ............................................
  dialogue.conversations.neighbour.more/2   [76 chars]
    en  Ask me, then. I'd rather you had it from me than from whoever's at the well.
    >>  ............................................
    pt  Então me pergunte. Prefiro que você ouça de mim do que de quem está no poço.
    >>  ............................................
  dialogue.conversations.neighbour.more/3   [70 chars]
    en  Go on. I'll tell you what's mine to tell and I'll stop where it stops.
    >>  ............................................
    pt  Vá em frente. Conto o que é meu pra contar e paro onde parar.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.neighbour.more/1
    en  There's more to them than the one story. It seems unfair to leave them at the one.
    >>  ............................................
    pt  Tem mais neles do que uma história só. Parece injusto deixá-los só nela.
    >>  ............................................
  anxious.dialogue.conversations.neighbour.more/2
    en  Ask me. I'd rather you had it from someone who's fond of them, and I am.
    >>  ............................................
    pt  Me pergunte. Prefiro que ouça de alguém que gosta deles, e eu gosto.
    >>  ............................................
  anxious.dialogue.conversations.neighbour.more/3
    en  Go on. I'll tell you what's mine to tell, and I'll be careful with the rest.
    >>  ............................................
    pt  Vá em frente. Conto o que é meu pra contar, e com o resto vou ter cuidado.
    >>  ............................................
  athletic.dialogue.conversations.neighbour.more/1
    en  There's more to them than the one story. There always is, given thirty years of looking.
    >>  ............................................
    pt  Tem mais neles do que uma história só. Sempre tem, com trinta anos de observação.
    >>  ............................................
  athletic.dialogue.conversations.neighbour.more/2
    en  Ask me. I've watched them longer than most and I'll not embroider it.
    >>  ............................................
    pt  Me pergunte. Observei mais tempo que a maioria e não vou bordar nada.
    >>  ............................................
  athletic.dialogue.conversations.neighbour.more/3
    en  Go on. I'll tell you what's mine to tell. The rest keeps, the way it always has.
    >>  ............................................
    pt  Vá em frente. Conto o que é meu. O resto espera, como sempre esperou.
    >>  ............................................
  confident.dialogue.conversations.neighbour.more/1
    en  There's more to them than the one story, if you've the time.
    >>  ............................................
    pt  Tem mais neles do que uma história só, se você tiver tempo.
    >>  ............................................
  confident.dialogue.conversations.neighbour.more/2
    en  Ask me, then. I'd rather you had it from me than from the well.
    >>  ............................................
    pt  Então me pergunte. Prefiro que ouça de mim do que no poço.
    >>  ............................................
  confident.dialogue.conversations.neighbour.more/3
    en  Go on. I'll tell you what's mine to tell and stop where it stops.
    >>  ............................................
    pt  Vá em frente. Conto o que é meu pra contar e paro onde parar.
    >>  ............................................
  crabby.dialogue.conversations.neighbour.more/1
    en  There's more to them than the one story, if you've the time.
    >>  ............................................
    pt  Tem mais neles do que uma história só, se você tiver tempo.
    >>  ............................................
  crabby.dialogue.conversations.neighbour.more/2
    en  Ask me, then. I'd rather you had it from me than from the well.
    >>  ............................................
    pt  Então me pergunte. Prefiro que ouça de mim do que no poço.
    >>  ............................................
  crabby.dialogue.conversations.neighbour.more/3
    en  Go on. I'll tell you what's mine to tell and stop where it stops.
    >>  ............................................
    pt  Vá em frente. Conto o que é meu pra contar e paro onde parar.
    >>  ............................................
  extroverted.dialogue.conversations.neighbour.more/1
    en  There's more to them than the one story, %1$s, and you're the sort who'd want it.
    >>  ............................................
    pt  Tem mais neles do que uma história só, %1$s, e você é do tipo que ia querer saber.
    >>  ............................................
  extroverted.dialogue.conversations.neighbour.more/2
    en  Ask me. I'd rather you had it from me than from someone who doesn't like them.
    >>  ............................................
    pt  Me pergunte. Prefiro que ouça de mim do que de alguém que não gosta deles.
    >>  ............................................
  extroverted.dialogue.conversations.neighbour.more/3
    en  Go on. I'll tell you what's mine to tell — and I'd not offer that to everyone.
    >>  ............................................
    pt  Vá em frente. Conto o que é meu pra contar — e não ofereço isso a qualquer um.
    >>  ............................................
  flirty.dialogue.conversations.neighbour.more/1
    en  There's more to them than the one story, %1$s, and you're the sort who'd want it.
    >>  ............................................
    pt  Tem mais neles do que uma história só, %1$s, e você é do tipo que ia querer saber.
    >>  ............................................
  flirty.dialogue.conversations.neighbour.more/2
    en  Ask me. I'd rather you had it from me than from someone who doesn't like them.
    >>  ............................................
    pt  Me pergunte. Prefiro que ouça de mim do que de alguém que não gosta deles.
    >>  ............................................
  flirty.dialogue.conversations.neighbour.more/3
    en  Go on. I'll tell you what's mine to tell — and I'd not offer that to everyone.
    >>  ............................................
    pt  Vá em frente. Conto o que é meu pra contar — e não ofereço isso a qualquer um.
    >>  ............................................
  friendly.dialogue.conversations.neighbour.more/1
    en  There's more to them than the one story, %1$s, and you're the sort who'd want it.
    >>  ............................................
    pt  Tem mais neles do que uma história só, %1$s, e você é do tipo que ia querer saber.
    >>  ............................................
  friendly.dialogue.conversations.neighbour.more/2
    en  Ask me. I'd rather you had it from me than from someone who doesn't like them.
    >>  ............................................
    pt  Me pergunte. Prefiro que ouça de mim do que de alguém que não gosta deles.
    >>  ............................................
  friendly.dialogue.conversations.neighbour.more/3
    en  Go on. I'll tell you what's mine to tell — and I'd not offer that to everyone.
    >>  ............................................
    pt  Vá em frente. Conto o que é meu pra contar — e não ofereço isso a qualquer um.
    >>  ............................................
  gloomy.dialogue.conversations.neighbour.more/1
    en  There's more to them than the one story. It seems unfair to leave them at the one.
    >>  ............................................
    pt  Tem mais neles do que uma história só. Parece injusto deixá-los só nela.
    >>  ............................................
  gloomy.dialogue.conversations.neighbour.more/2
    en  Ask me. I'd rather you had it from someone who's fond of them, and I am.
    >>  ............................................
    pt  Me pergunte. Prefiro que ouça de alguém que gosta deles, e eu gosto.
    >>  ............................................
  gloomy.dialogue.conversations.neighbour.more/3
    en  Go on. I'll tell you what's mine to tell, and I'll be careful with the rest.
    >>  ............................................
    pt  Vá em frente. Conto o que é meu pra contar, e com o resto vou ter cuidado.
    >>  ............................................
  greedy.dialogue.conversations.neighbour.more/1
    en  There's more to them than the one story, if you've the time.
    >>  ............................................
    pt  Tem mais neles do que uma história só, se você tiver tempo.
    >>  ............................................
  greedy.dialogue.conversations.neighbour.more/2
    en  Ask me, then. I'd rather you had it from me than from the well.
    >>  ............................................
    pt  Então me pergunte. Prefiro que ouça de mim do que no poço.
    >>  ............................................
  greedy.dialogue.conversations.neighbour.more/3
    en  Go on. I'll tell you what's mine to tell and stop where it stops.
    >>  ............................................
    pt  Vá em frente. Conto o que é meu pra contar e paro onde parar.
    >>  ............................................
  grumpy.dialogue.conversations.neighbour.more/1
    en  There's more to them than the one story, if you've the time.
    >>  ............................................
    pt  Tem mais neles do que uma história só, se você tiver tempo.
    >>  ............................................
  grumpy.dialogue.conversations.neighbour.more/2
    en  Ask me, then. I'd rather you had it from me than from the well.
    >>  ............................................
    pt  Então me pergunte. Prefiro que ouça de mim do que no poço.
    >>  ............................................
  grumpy.dialogue.conversations.neighbour.more/3
    en  Go on. I'll tell you what's mine to tell and stop where it stops.
    >>  ............................................
    pt  Vá em frente. Conto o que é meu pra contar e paro onde parar.
    >>  ............................................
  introverted.dialogue.conversations.neighbour.more/1
    en  There's more to them than the one story.
    >>  ............................................
    pt  Tem mais neles do que uma história só.
    >>  ............................................
  introverted.dialogue.conversations.neighbour.more/2
    en  Ask, then.
    >>  ............................................
    pt  Pergunte, então.
    >>  ............................................
  introverted.dialogue.conversations.neighbour.more/3
    en  Go on. I'll stop where it stops.
    >>  ............................................
    pt  Vá em frente. Paro onde parar.
    >>  ............................................
  lazy.dialogue.conversations.neighbour.more/1
    en  There's more to them than the one story. There always is, given thirty years of looking.
    >>  ............................................
    pt  Tem mais neles do que uma história só. Sempre tem, com trinta anos de observação.
    >>  ............................................
  lazy.dialogue.conversations.neighbour.more/2
    en  Ask me. I've watched them longer than most and I'll not embroider it.
    >>  ............................................
    pt  Me pergunte. Observei mais tempo que a maioria e não vou bordar nada.
    >>  ............................................
  lazy.dialogue.conversations.neighbour.more/3
    en  Go on. I'll tell you what's mine to tell. The rest keeps, the way it always has.
    >>  ............................................
    pt  Vá em frente. Conto o que é meu. O resto espera, como sempre esperou.
    >>  ............................................
  odd.dialogue.conversations.neighbour.more/1
    en  There's more to them than the one story.
    >>  ............................................
    pt  Tem mais neles do que uma história só.
    >>  ............................................
  odd.dialogue.conversations.neighbour.more/2
    en  Ask, then.
    >>  ............................................
    pt  Pergunte, então.
    >>  ............................................
  odd.dialogue.conversations.neighbour.more/3
    en  Go on. I'll stop where it stops.
    >>  ............................................
    pt  Vá em frente. Paro onde parar.
    >>  ............................................
  peaceful.dialogue.conversations.neighbour.more/1
    en  There's more to them than the one story. There always is, given thirty years of looking.
    >>  ............................................
    pt  Tem mais neles do que uma história só. Sempre tem, com trinta anos de observação.
    >>  ............................................
  peaceful.dialogue.conversations.neighbour.more/2
    en  Ask me. I've watched them longer than most and I'll not embroider it.
    >>  ............................................
    pt  Me pergunte. Observei mais tempo que a maioria e não vou bordar nada.
    >>  ............................................
  peaceful.dialogue.conversations.neighbour.more/3
    en  Go on. I'll tell you what's mine to tell. The rest keeps, the way it always has.
    >>  ............................................
    pt  Vá em frente. Conto o que é meu. O resto espera, como sempre esperou.
    >>  ............................................
  peppy.dialogue.conversations.neighbour.more/1
    en  There's a great deal more to them than the one story, and I've the time if you have!
    >>  ............................................
    pt  Tem muito mais neles do que uma história só, e eu tenho tempo se você tiver!
    >>  ............................................
  peppy.dialogue.conversations.neighbour.more/2
    en  Ask me! I'd much rather you had it from me than from whoever's holding court at the well.
    >>  ............................................
    pt  Pergunte! Prefiro muito mais que ouça de mim do que de quem preside o poço.
    >>  ............................................
  peppy.dialogue.conversations.neighbour.more/3
    en  Go on. I'll tell you everything that's mine to tell, and I'll enjoy the telling.
    >>  ............................................
    pt  Vá em frente. Conto tudo que é meu pra contar, e vou gostar de contar.
    >>  ............................................
  playful.dialogue.conversations.neighbour.more/1
    en  There's a great deal more to them than the one story, and I've the time if you have!
    >>  ............................................
    pt  Tem muito mais neles do que uma história só, e eu tenho tempo se você tiver!
    >>  ............................................
  playful.dialogue.conversations.neighbour.more/2
    en  Ask me! I'd much rather you had it from me than from whoever's holding court at the well.
    >>  ............................................
    pt  Pergunte! Prefiro muito mais que ouça de mim do que de quem preside o poço.
    >>  ............................................
  playful.dialogue.conversations.neighbour.more/3
    en  Go on. I'll tell you everything that's mine to tell, and I'll enjoy the telling.
    >>  ............................................
    pt  Vá em frente. Conto tudo que é meu pra contar, e vou gostar de contar.
    >>  ............................................
  relaxed.dialogue.conversations.neighbour.more/1
    en  There's more to them than the one story. There always is, given thirty years of looking.
    >>  ............................................
    pt  Tem mais neles do que uma história só. Sempre tem, com trinta anos de observação.
    >>  ............................................
  relaxed.dialogue.conversations.neighbour.more/2
    en  Ask me. I've watched them longer than most and I'll not embroider it.
    >>  ............................................
    pt  Me pergunte. Observei mais tempo que a maioria e não vou bordar nada.
    >>  ............................................
  relaxed.dialogue.conversations.neighbour.more/3
    en  Go on. I'll tell you what's mine to tell. The rest keeps, the way it always has.
    >>  ............................................
    pt  Vá em frente. Conto o que é meu. O resto espera, como sempre esperou.
    >>  ............................................
  sensitive.dialogue.conversations.neighbour.more/1
    en  There's more to them than the one story. It seems unfair to leave them at the one.
    >>  ............................................
    pt  Tem mais neles do que uma história só. Parece injusto deixá-los só nela.
    >>  ............................................
  sensitive.dialogue.conversations.neighbour.more/2
    en  Ask me. I'd rather you had it from someone who's fond of them, and I am.
    >>  ............................................
    pt  Me pergunte. Prefiro que ouça de alguém que gosta deles, e eu gosto.
    >>  ............................................
  sensitive.dialogue.conversations.neighbour.more/3
    en  Go on. I'll tell you what's mine to tell, and I'll be careful with the rest.
    >>  ............................................
    pt  Vá em frente. Conto o que é meu pra contar, e com o resto vou ter cuidado.
    >>  ............................................
  shy.dialogue.conversations.neighbour.more/1
    en  There's more to them than the one story.
    >>  ............................................
    pt  Tem mais neles do que uma história só.
    >>  ............................................
  shy.dialogue.conversations.neighbour.more/2
    en  Ask, then.
    >>  ............................................
    pt  Pergunte, então.
    >>  ............................................
  shy.dialogue.conversations.neighbour.more/3
    en  Go on. I'll stop where it stops.
    >>  ............................................
    pt  Vá em frente. Paro onde parar.
    >>  ............................................
  upbeat.dialogue.conversations.neighbour.more/1
    en  There's a great deal more to them than the one story, and I've the time if you have!
    >>  ............................................
    pt  Tem muito mais neles do que uma história só, e eu tenho tempo se você tiver!
    >>  ............................................
  upbeat.dialogue.conversations.neighbour.more/2
    en  Ask me! I'd much rather you had it from me than from whoever's holding court at the well.
    >>  ............................................
    pt  Pergunte! Prefiro muito mais que ouça de mim do que de quem preside o poço.
    >>  ............................................
  upbeat.dialogue.conversations.neighbour.more/3
    en  Go on. I'll tell you everything that's mine to tell, and I'll enjoy the telling.
    >>  ............................................
    pt  Vá em frente. Conto tudo que é meu pra contar, e vou gostar de contar.
    >>  ............................................
  witty.dialogue.conversations.neighbour.more/1
    en  There's a great deal more to them than the one story, and I've the time if you have!
    >>  ............................................
    pt  Tem muito mais neles do que uma história só, e eu tenho tempo se você tiver!
    >>  ............................................
  witty.dialogue.conversations.neighbour.more/2
    en  Ask me! I'd much rather you had it from me than from whoever's holding court at the well.
    >>  ............................................
    pt  Pergunte! Prefiro muito mais que ouça de mim do que de quem preside o poço.
    >>  ............................................
  witty.dialogue.conversations.neighbour.more/3
    en  Go on. I'll tell you everything that's mine to tell, and I'll enjoy the telling.
    >>  ............................................
    pt  Vá em frente. Conto tudo que é meu pra contar, e vou gostar de contar.
    >>  ............................................
```

</details>


### Button `leave` — "That'll do."

*stance family `exit` · tone `plain` · answers the beat(s) `neighbour.ask_more.to.neighbour`, `neighbour.defend_them.to.neighbour`, `neighbour.encourage_more.to.neighbour` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.followup.leave   [11 chars]
    en  That'll do.
    >>  ............................................
    pt  Já serve.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.followup_leave
WHO    VILLAGER — what the player reads after pressing "That'll do."
       spoken on: conversations.topic.neighbour.followup, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.followup_leave.terminal`: the villager accepts. Subject `neighbour.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.neighbour.followup_leave/1   [47 chars]
    en  It is. That's enough about anybody for one day.
    >>  ............................................
    pt  É sim. Já chega de falar de qualquer um por hoje.
    >>  ............................................
  dialogue.conversations.neighbour.followup_leave/2   [37 chars]
    en  Right you are, %1$s. Mind how you go.
    >>  ............................................
    pt  Isso mesmo, %1$s. Se cuida.
    >>  ............................................
  dialogue.conversations.neighbour.followup_leave/3   [43 chars]
    en  Go on, then. And thank you for not pushing.
    >>  ............................................
    pt  Vai lá. E obrigado por não insistir.
    >>  ............................................
```

---


## `conversations.topic.neighbour.more`

**Reached from 1 route(s):** `conversations.topic.neighbour.followup` / `ask_about_them`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.neighbour.more` — e.g. "There's more to them than the one story, if you've the time for it."


```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.more
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.neighbour.more
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.neighbour.more   [49 chars]
    en  Ask, then, and I'll tell you what's mine to tell.
    >>  ............................................
    pt  Pergunte, então, e eu conto o que é meu pra contar.
    >>  ............................................
```


### Button `their_family` — "Have they family here?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `neighbour.more.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.more.their_family` — accepted phrasings: "have they family here"; "do they have relatives here"; "any kin of theirs about"
  - the message must contain one of: `relatives`, `kin`
  - scored words: `relatives`(1.5), `kin`(1.2), `family`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.more.their_family
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.more
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.more.their_family   [22 chars]
    en  Have they family here?
    >>  ............................................
    pt  Eles têm família aqui?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.neighbour.family`
- …where the player's next choices will be: "That's a hard thing to live beside." | "That's true of most villages, I'd think." | "I'll leave it there."

```text
POOL   dialogue key: dialogue.conversations.neighbour.more.their_family
WHO    VILLAGER — what the player reads after pressing "Have they family here?"
       spoken on: conversations.topic.neighbour.more, button `their_family`
       leaves the player on: conversations.topic.neighbour.family
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.more.their_family`: the villager reports. Subject `neighbour.talk`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: empathy, candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.more.their_family/1   [76 chars]
    en  A sister, two doors along, and they don't speak. That's the whole map of it.
    >>  ............................................
    pt  Uma irmã, duas portas adiante, e não se falam. É o mapa inteiro.
    >>  ............................................
  dialogue.conversations.neighbour.more.their_family/2   [66 chars]
    en  None left here. That's why they take the long way past the graves.
    >>  ............................................
    pt  Não sobrou ninguém aqui. Por isso dão a volta longa pelas sepulturas.
    >>  ............................................
  dialogue.conversations.neighbour.more.their_family/3   [75 chars]
    en  Half the lane, if you go back far enough. Most of us are somebody's cousin.
    >>  ............................................
    pt  Metade da rua, se você voltar o bastante. Quase todos somos primos de alguém.
    >>  ............................................
```


### Button `you_two` — "Do the two of you get on?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `neighbour.more.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.more.you_two` — accepted phrasings: "do the two of you get on"; "do you get along with them"; "are you and they on good terms"
  - the message must contain one of: `along`, `terms`
  - scored words: `along`(1.5), `terms`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.more.you_two
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.more
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.more.you_two   [25 chars]
    en  Do the two of you get on?
    >>  ............................................
    pt  Vocês dois se dão bem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.neighbour.dispute`
- …where the player's next choices will be: "Would you want it mended?" | "That's between the two of you." | "Understood."

```text
POOL   dialogue key: dialogue.conversations.neighbour.more.you_two
WHO    VILLAGER — what the player reads after pressing "Do the two of you get on?"
       spoken on: conversations.topic.neighbour.more, button `you_two`
       leaves the player on: conversations.topic.neighbour.dispute
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.more.you_two`: the villager discloses. Subject `neighbour.talk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, restraint, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.more.you_two/1   [79 chars]
    en  We did. There was a winter we didn't, and neither of us has explained it since.
    >>  ............................................
    pt  Nos dávamos. Houve um inverno em que não, e nenhum de nós explicou desde então.
    >>  ............................................
  dialogue.conversations.neighbour.more.you_two/2   [75 chars]
    en  Well enough. We disagree about the fence and agree about everything harder.
    >>  ............................................
    pt  Bem o bastante. Discordamos sobre a cerca e concordamos no que é mais difícil.
    >>  ............................................
  dialogue.conversations.neighbour.more.you_two/3   [76 chars]
    en  No. And I'll not dress it up: they wronged me and it hasn't been made right.
    >>  ............................................
    pt  Não. E não vou enfeitar: me prejudicaram e não foi consertado.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.neighbour.more.you_two/1
    en  We did. There was a winter we didn't, and I still don't know which of us stopped first.
    >>  ............................................
    pt  Nos dávamos. Houve um inverno em que não, e ainda não sei quem parou primeiro.
    >>  ............................................
  anxious.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough. The fence is easier to argue about than the thing underneath it.
    >>  ............................................
    pt  Bem o bastante. A cerca é mais fácil de discutir do que o que está embaixo dela.
    >>  ............................................
  anxious.dialogue.conversations.neighbour.more.you_two/3
    en  No. They wronged me, and saying it plainly still costs me something.
    >>  ............................................
    pt  Não. Me prejudicaram, e dizer isso sem rodeios ainda me custa algo.
    >>  ............................................
  athletic.dialogue.conversations.neighbour.more.you_two/1
    en  We did. There was a winter we didn't. Twelve years on, neither of us has explained it.
    >>  ............................................
    pt  Nos dávamos. Houve um inverno em que não. Doze anos depois, ninguém explicou.
    >>  ............................................
  athletic.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough. We've disagreed about that fence since before either of us owned it.
    >>  ............................................
    pt  Bem o bastante. Discordamos daquela cerca desde antes de qualquer um dos dois ser dono.
    >>  ............................................
  athletic.dialogue.conversations.neighbour.more.you_two/3
    en  No. They wronged me, and I've had long enough to decide I'm not wrong about that.
    >>  ............................................
    pt  Não. Me prejudicaram, e tive tempo o bastante pra decidir que não estou errado nisso.
    >>  ............................................
  confident.dialogue.conversations.neighbour.more.you_two/1
    en  We did. There was a winter we didn't, and neither of us has explained it since.
    >>  ............................................
    pt  Nos dávamos. Houve um inverno em que não, e nenhum de nós explicou desde então.
    >>  ............................................
  confident.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough. We disagree about the fence and agree about everything harder.
    >>  ............................................
    pt  Bem o bastante. Discordamos da cerca e concordamos no que é mais difícil.
    >>  ............................................
  confident.dialogue.conversations.neighbour.more.you_two/3
    en  No. They wronged me and it hasn't been made right.
    >>  ............................................
    pt  Não. Me prejudicaram e não foi consertado.
    >>  ............................................
  crabby.dialogue.conversations.neighbour.more.you_two/1
    en  We did. There was a winter we didn't, and neither of us has explained it since.
    >>  ............................................
    pt  Nos dávamos. Houve um inverno em que não, e nenhum de nós explicou desde então.
    >>  ............................................
  crabby.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough. We disagree about the fence and agree about everything harder.
    >>  ............................................
    pt  Bem o bastante. Discordamos da cerca e concordamos no que é mais difícil.
    >>  ............................................
  crabby.dialogue.conversations.neighbour.more.you_two/3
    en  No. They wronged me and it hasn't been made right.
    >>  ............................................
    pt  Não. Me prejudicaram e não foi consertado.
    >>  ............................................
  extroverted.dialogue.conversations.neighbour.more.you_two/1
    en  We did. There was a winter we didn't, and I've missed them the whole time since.
    >>  ............................................
    pt  Nos dávamos. Houve um inverno em que não, e senti falta deles esse tempo todo.
    >>  ............................................
  extroverted.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough. We fall out about the fence and we'd still sit up with one another.
    >>  ............................................
    pt  Bem o bastante. Brigamos pela cerca e ainda passaríamos a noite um com o outro.
    >>  ............................................
  extroverted.dialogue.conversations.neighbour.more.you_two/3
    en  No, %1$s. They wronged me. I'd rather you knew that from me than found it out.
    >>  ............................................
    pt  Não, %1$s. Me prejudicaram. Prefiro que saiba por mim a que descubra.
    >>  ............................................
  flirty.dialogue.conversations.neighbour.more.you_two/1
    en  We did. There was a winter we didn't, and I've missed them the whole time since.
    >>  ............................................
    pt  Nos dávamos. Houve um inverno em que não, e senti falta deles esse tempo todo.
    >>  ............................................
  flirty.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough. We fall out about the fence and we'd still sit up with one another.
    >>  ............................................
    pt  Bem o bastante. Brigamos pela cerca e ainda passaríamos a noite um com o outro.
    >>  ............................................
  flirty.dialogue.conversations.neighbour.more.you_two/3
    en  No, %1$s. They wronged me. I'd rather you knew that from me than found it out.
    >>  ............................................
    pt  Não, %1$s. Me prejudicaram. Prefiro que saiba por mim a que descubra.
    >>  ............................................
  friendly.dialogue.conversations.neighbour.more.you_two/1
    en  We did. There was a winter we didn't, and I've missed them the whole time since.
    >>  ............................................
    pt  Nos dávamos. Houve um inverno em que não, e senti falta deles esse tempo todo.
    >>  ............................................
  friendly.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough. We fall out about the fence and we'd still sit up with one another.
    >>  ............................................
    pt  Bem o bastante. Brigamos pela cerca e ainda passaríamos a noite um com o outro.
    >>  ............................................
  friendly.dialogue.conversations.neighbour.more.you_two/3
    en  No, %1$s. They wronged me. I'd rather you knew that from me than found it out.
    >>  ............................................
    pt  Não, %1$s. Me prejudicaram. Prefiro que saiba por mim a que descubra.
    >>  ............................................
  gloomy.dialogue.conversations.neighbour.more.you_two/1
    en  We did. There was a winter we didn't, and I still don't know which of us stopped first.
    >>  ............................................
    pt  Nos dávamos. Houve um inverno em que não, e ainda não sei quem parou primeiro.
    >>  ............................................
  gloomy.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough. The fence is easier to argue about than the thing underneath it.
    >>  ............................................
    pt  Bem o bastante. A cerca é mais fácil de discutir do que o que está embaixo dela.
    >>  ............................................
  gloomy.dialogue.conversations.neighbour.more.you_two/3
    en  No. They wronged me, and saying it plainly still costs me something.
    >>  ............................................
    pt  Não. Me prejudicaram, e dizer isso sem rodeios ainda me custa algo.
    >>  ............................................
  greedy.dialogue.conversations.neighbour.more.you_two/1
    en  We did. There was a winter we didn't, and neither of us has explained it since.
    >>  ............................................
    pt  Nos dávamos. Houve um inverno em que não, e nenhum de nós explicou desde então.
    >>  ............................................
  greedy.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough. We disagree about the fence and agree about everything harder.
    >>  ............................................
    pt  Bem o bastante. Discordamos da cerca e concordamos no que é mais difícil.
    >>  ............................................
  greedy.dialogue.conversations.neighbour.more.you_two/3
    en  No. They wronged me and it hasn't been made right.
    >>  ............................................
    pt  Não. Me prejudicaram e não foi consertado.
    >>  ............................................
  grumpy.dialogue.conversations.neighbour.more.you_two/1
    en  We did. There was a winter we didn't, and neither of us has explained it since.
    >>  ............................................
    pt  Nos dávamos. Houve um inverno em que não, e nenhum de nós explicou desde então.
    >>  ............................................
  grumpy.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough. We disagree about the fence and agree about everything harder.
    >>  ............................................
    pt  Bem o bastante. Discordamos da cerca e concordamos no que é mais difícil.
    >>  ............................................
  grumpy.dialogue.conversations.neighbour.more.you_two/3
    en  No. They wronged me and it hasn't been made right.
    >>  ............................................
    pt  Não. Me prejudicaram e não foi consertado.
    >>  ............................................
  introverted.dialogue.conversations.neighbour.more.you_two/1
    en  We did. There was a winter we didn't.
    >>  ............................................
    pt  Nos dávamos. Houve um inverno em que não.
    >>  ............................................
  introverted.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough.
    >>  ............................................
    pt  Bem o bastante.
    >>  ............................................
  introverted.dialogue.conversations.neighbour.more.you_two/3
    en  No. They wronged me.
    >>  ............................................
    pt  Não. Me prejudicaram.
    >>  ............................................
  lazy.dialogue.conversations.neighbour.more.you_two/1
    en  We did. There was a winter we didn't. Twelve years on, neither of us has explained it.
    >>  ............................................
    pt  Nos dávamos. Houve um inverno em que não. Doze anos depois, ninguém explicou.
    >>  ............................................
  lazy.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough. We've disagreed about that fence since before either of us owned it.
    >>  ............................................
    pt  Bem o bastante. Discordamos daquela cerca desde antes de qualquer um dos dois ser dono.
    >>  ............................................
  lazy.dialogue.conversations.neighbour.more.you_two/3
    en  No. They wronged me, and I've had long enough to decide I'm not wrong about that.
    >>  ............................................
    pt  Não. Me prejudicaram, e tive tempo o bastante pra decidir que não estou errado nisso.
    >>  ............................................
  odd.dialogue.conversations.neighbour.more.you_two/1
    en  We did. There was a winter we didn't.
    >>  ............................................
    pt  Nos dávamos. Houve um inverno em que não.
    >>  ............................................
  odd.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough.
    >>  ............................................
    pt  Bem o bastante.
    >>  ............................................
  odd.dialogue.conversations.neighbour.more.you_two/3
    en  No. They wronged me.
    >>  ............................................
    pt  Não. Me prejudicaram.
    >>  ............................................
  peaceful.dialogue.conversations.neighbour.more.you_two/1
    en  We did. There was a winter we didn't. Twelve years on, neither of us has explained it.
    >>  ............................................
    pt  Nos dávamos. Houve um inverno em que não. Doze anos depois, ninguém explicou.
    >>  ............................................
  peaceful.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough. We've disagreed about that fence since before either of us owned it.
    >>  ............................................
    pt  Bem o bastante. Discordamos daquela cerca desde antes de qualquer um dos dois ser dono.
    >>  ............................................
  peaceful.dialogue.conversations.neighbour.more.you_two/3
    en  No. They wronged me, and I've had long enough to decide I'm not wrong about that.
    >>  ............................................
    pt  Não. Me prejudicaram, e tive tempo o bastante pra decidir que não estou errado nisso.
    >>  ............................................
  peppy.dialogue.conversations.neighbour.more.you_two/1
    en  We did! There was one winter we didn't, and it has never once been discussed since.
    >>  ............................................
    pt  Nos dávamos! Houve um inverno em que não, e nunca mais se falou disso.
    >>  ............................................
  peppy.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough! We disagree about the fence and agree about everything that matters.
    >>  ............................................
    pt  Bem o bastante! Discordamos da cerca e concordamos no que importa.
    >>  ............................................
  peppy.dialogue.conversations.neighbour.more.you_two/3
    en  No. They wronged me, and I've been magnificently civil about it ever since.
    >>  ............................................
    pt  Não. Me prejudicaram, e desde então tenho sido magnificamente civilizado.
    >>  ............................................
  playful.dialogue.conversations.neighbour.more.you_two/1
    en  We did! There was one winter we didn't, and it has never once been discussed since.
    >>  ............................................
    pt  Nos dávamos! Houve um inverno em que não, e nunca mais se falou disso.
    >>  ............................................
  playful.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough! We disagree about the fence and agree about everything that matters.
    >>  ............................................
    pt  Bem o bastante! Discordamos da cerca e concordamos no que importa.
    >>  ............................................
  playful.dialogue.conversations.neighbour.more.you_two/3
    en  No. They wronged me, and I've been magnificently civil about it ever since.
    >>  ............................................
    pt  Não. Me prejudicaram, e desde então tenho sido magnificamente civilizado.
    >>  ............................................
  relaxed.dialogue.conversations.neighbour.more.you_two/1
    en  We did. There was a winter we didn't. Twelve years on, neither of us has explained it.
    >>  ............................................
    pt  Nos dávamos. Houve um inverno em que não. Doze anos depois, ninguém explicou.
    >>  ............................................
  relaxed.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough. We've disagreed about that fence since before either of us owned it.
    >>  ............................................
    pt  Bem o bastante. Discordamos daquela cerca desde antes de qualquer um dos dois ser dono.
    >>  ............................................
  relaxed.dialogue.conversations.neighbour.more.you_two/3
    en  No. They wronged me, and I've had long enough to decide I'm not wrong about that.
    >>  ............................................
    pt  Não. Me prejudicaram, e tive tempo o bastante pra decidir que não estou errado nisso.
    >>  ............................................
  sensitive.dialogue.conversations.neighbour.more.you_two/1
    en  We did. There was a winter we didn't, and I still don't know which of us stopped first.
    >>  ............................................
    pt  Nos dávamos. Houve um inverno em que não, e ainda não sei quem parou primeiro.
    >>  ............................................
  sensitive.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough. The fence is easier to argue about than the thing underneath it.
    >>  ............................................
    pt  Bem o bastante. A cerca é mais fácil de discutir do que o que está embaixo dela.
    >>  ............................................
  sensitive.dialogue.conversations.neighbour.more.you_two/3
    en  No. They wronged me, and saying it plainly still costs me something.
    >>  ............................................
    pt  Não. Me prejudicaram, e dizer isso sem rodeios ainda me custa algo.
    >>  ............................................
  shy.dialogue.conversations.neighbour.more.you_two/1
    en  We did. There was a winter we didn't.
    >>  ............................................
    pt  Nos dávamos. Houve um inverno em que não.
    >>  ............................................
  shy.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough.
    >>  ............................................
    pt  Bem o bastante.
    >>  ............................................
  shy.dialogue.conversations.neighbour.more.you_two/3
    en  No. They wronged me.
    >>  ............................................
    pt  Não. Me prejudicaram.
    >>  ............................................
  upbeat.dialogue.conversations.neighbour.more.you_two/1
    en  We did! There was one winter we didn't, and it has never once been discussed since.
    >>  ............................................
    pt  Nos dávamos! Houve um inverno em que não, e nunca mais se falou disso.
    >>  ............................................
  upbeat.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough! We disagree about the fence and agree about everything that matters.
    >>  ............................................
    pt  Bem o bastante! Discordamos da cerca e concordamos no que importa.
    >>  ............................................
  upbeat.dialogue.conversations.neighbour.more.you_two/3
    en  No. They wronged me, and I've been magnificently civil about it ever since.
    >>  ............................................
    pt  Não. Me prejudicaram, e desde então tenho sido magnificamente civilizado.
    >>  ............................................
  witty.dialogue.conversations.neighbour.more.you_two/1
    en  We did! There was one winter we didn't, and it has never once been discussed since.
    >>  ............................................
    pt  Nos dávamos! Houve um inverno em que não, e nunca mais se falou disso.
    >>  ............................................
  witty.dialogue.conversations.neighbour.more.you_two/2
    en  Well enough! We disagree about the fence and agree about everything that matters.
    >>  ............................................
    pt  Bem o bastante! Discordamos da cerca e concordamos no que importa.
    >>  ............................................
  witty.dialogue.conversations.neighbour.more.you_two/3
    en  No. They wronged me, and I've been magnificently civil about it ever since.
    >>  ............................................
    pt  Não. Me prejudicaram, e desde então tenho sido magnificamente civilizado.
    >>  ............................................
```

</details>


### Button `they_need` — "Is there anything they need?"

*stance family `practical_help` · tone `plain` · outcome `engaged` · answers the beat(s) `neighbour.more.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.more.they_need` — accepted phrasings: "is there anything they need"; "do they want for anything"; "are they short of anything"
  - the message must contain one of: `short`
  - scored words: `want`(0.5), `short`(1.0), `they`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.more.they_need
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.more
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.more.they_need   [28 chars]
    en  Is there anything they need?
    >>  ............................................
    pt  Eles precisam de alguma coisa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.neighbour.needs`
- …where the player's next choices will be: "I'll go round." | "Tell them I offered." | "Right."

```text
POOL   dialogue key: dialogue.conversations.neighbour.more.they_need
WHO    VILLAGER — what the player reads after pressing "Is there anything they need?"
       spoken on: conversations.topic.neighbour.more, button `they_need`
       leaves the player on: conversations.topic.neighbour.needs
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.more.they_need`: the villager reports. Subject `neighbour.talk`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.more.they_need/1   [70 chars]
    en  Firewood, and they'd sooner freeze than ask. That's the shape of them.
    >>  ............................................
    pt  Lenha, e prefeririam congelar a pedir. É esse o formato deles.
    >>  ............................................
  dialogue.conversations.neighbour.more.they_need/2   [62 chars]
    en  Nothing they'd admit to. Company, if you want my honest guess.
    >>  ............................................
    pt  Nada que admitissem. Companhia, se quer meu palpite honesto.
    >>  ............................................
  dialogue.conversations.neighbour.more.they_need/3   [69 chars]
    en  Hands, for two days in the spring. Everyone means to and nobody goes.
    >>  ............................................
    pt  Mãos, por dois dias na primavera. Todos pretendem e ninguém vai.
    >>  ............................................
```


### Button `should_you` — "Should you be telling me this?"

*stance family `restraint` · tone `plain` · outcome `qualified` · answers the beat(s) `neighbour.more.open`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.more.should_you` — accepted phrasings: "should you be telling me this"; "is this yours to tell"; "ought you to be saying this"
  - the message must contain one of: `telling`
  - scored words: `telling`(1.2), `should`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.more.should_you
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.more
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.more.should_you   [30 chars]
    en  Should you be telling me this?
    >>  ............................................
    pt  Você deveria estar me contando isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `turn`
- Then opens: `conversations.topic.neighbour.privacy`
- …where the player's next choices will be: "It stays with me." | "I'd rather hear it from them." | "Fair enough."

```text
POOL   dialogue key: dialogue.conversations.neighbour.more.should_you
WHO    VILLAGER — what the player reads after pressing "Should you be telling me this?"
       spoken on: conversations.topic.neighbour.more, button `should_you`
       leaves the player on: conversations.topic.neighbour.privacy
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.more.should_you`: the villager qualifys. Subject `neighbour.talk`, polarity `neutral`, guarded, outcome `qualified`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.more.should_you/1   [72 chars]
    en  Probably not. I'll stop there, and you'll not repeat the part I've said.
    >>  ............................................
    pt  Provavelmente não. Vou parar aqui, e você não repete a parte que eu disse.
    >>  ............................................
  dialogue.conversations.neighbour.more.should_you/2   [81 chars]
    en  It's known. There's a difference between a secret and a thing nobody says loudly.
    >>  ............................................
    pt  É sabido. Tem diferença entre um segredo e algo que ninguém diz alto.
    >>  ............................................
  dialogue.conversations.neighbour.more.should_you/3   [70 chars]
    en  I asked myself that halfway through. Thank you for asking it out loud.
    >>  ............................................
    pt  Eu me perguntei isso no meio. Obrigado por perguntar em voz alta.
    >>  ............................................
```


### Button `leave` — "That's enough about them."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `neighbour.more.open` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.more.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.more
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.more.leave   [25 chars]
    en  That's enough about them.
    >>  ............................................
    pt  Já chega sobre eles.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.more.leave
WHO    VILLAGER — what the player reads after pressing "That's enough about them."
       spoken on: conversations.topic.neighbour.more, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.more.leave`: the villager accepts. Subject `neighbour.talk`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.more.leave/1   [19 chars]
    en  True enough, it is.
    >>  ............................................
    pt  Bem verdade, chega.
    >>  ............................................
  dialogue.conversations.neighbour.more.leave/2   [12 chars]
    en  Away you go.
    >>  ............................................
    pt  Pode seguir.
    >>  ............................................
  dialogue.conversations.neighbour.more.leave/3   [22 chars]
    en  Until next time, %1$s.
    >>  ............................................
    pt  Até a próxima, %1$s.
    >>  ............................................
```

---


## `conversations.topic.neighbour.needs`

**Reached from 1 route(s):** `conversations.topic.neighbour.more` / `they_need`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.neighbour.more.they_need` — e.g. "Firewood, and they'd sooner freeze than ask. That's the shape of them."


```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.needs
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.neighbour.needs
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.neighbour.needs   [30 chars]
    en  That's what I'd guess, anyway.
    >>  ............................................
    pt  É o que eu chutaria, de todo jeito.
    >>  ............................................
```


### Button `ill_go` — "I'll go round."

*stance family `practical_help` · tone `plain` · outcome `appreciated` · answers the beat(s) `neighbour.more.they_need`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.needs.go` — accepted phrasings: "i will go round"; "i will call on them"; "i will take it to them myself"
  - the message must contain one of: `round`
  - scored words: `round`(1.0), `go`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.needs.ill_go
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.needs
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.needs.ill_go   [14 chars]
    en  I'll go round.
    >>  ............................................
    pt  Eu passo lá.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `neighbour.needs.go`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3, warmth +2  _(recorded under topic `neighbour.needs.go`)_
- Does: arc `neighbour` — advance
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.needs.go
WHO    VILLAGER — what the player reads after pressing "I'll go round."
       spoken on: conversations.topic.neighbour.needs, button `ill_go`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.needs.go`: the villager accepts. Subject `neighbour.needs`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.needs.go/1   [70 chars]
    en  Then you'll be the first, and they'll pretend they didn't need you to.
    >>  ............................................
    pt  Então você será o primeiro, e eles vão fingir que não precisavam.
    >>  ............................................
  dialogue.conversations.neighbour.needs.go/2   [64 chars]
    en  Go in the morning. They're proudest after dark, for some reason.
    >>  ............................................
    pt  Vá de manhã. Ficam mais orgulhosos depois do escuro, sei lá por quê.
    >>  ............................................
  dialogue.conversations.neighbour.needs.go/3   [61 chars]
    en  Good. Don't announce it — put it by the door and say nothing.
    >>  ............................................
    pt  Bom. Não anuncie — deixe na porta e não diga nada.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first, and they'll pretend they didn't need it, and they did.
    >>  ............................................
    pt  Então você será o primeiro, e vão fingir que não precisavam, e precisavam.
    >>  ............................................
  anxious.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. After dark they've had all day to decide they deserve nothing.
    >>  ............................................
    pt  Vá de manhã. Depois do escuro já tiveram o dia todo pra decidir que não merecem nada.
    >>  ............................................
  anxious.dialogue.conversations.neighbour.needs.go/3
    en  Good. Don't announce it. Being helped in front of somebody is its own small wound.
    >>  ............................................
    pt  Bom. Não anuncie. Ser ajudado na frente de alguém é uma pequena ferida própria.
    >>  ............................................
  athletic.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first. I've watched that door for years and nobody has gone to it.
    >>  ............................................
    pt  Então você será o primeiro. Vigio aquela porta há anos e ninguém foi lá.
    >>  ............................................
  athletic.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. Forty years here teaches you when a proud house is at its worst.
    >>  ............................................
    pt  Vá de manhã. Quarenta anos aqui ensinam quando uma casa orgulhosa está pior.
    >>  ............................................
  athletic.dialogue.conversations.neighbour.needs.go/3
    en  Good. Don't announce it. The best help this village ever did was done at dawn.
    >>  ............................................
    pt  Bom. Não anuncie. A melhor ajuda deste vilarejo sempre foi feita ao amanhecer.
    >>  ............................................
  confident.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first, and they'll pretend they didn't need you to.
    >>  ............................................
    pt  Então você será o primeiro, e vão fingir que não precisavam.
    >>  ............................................
  confident.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. They're proudest after dark, for some reason.
    >>  ............................................
    pt  Vá de manhã. Ficam mais orgulhosos depois do escuro, sei lá por quê.
    >>  ............................................
  confident.dialogue.conversations.neighbour.needs.go/3
    en  Good. Don't announce it — put it by the door and say nothing.
    >>  ............................................
    pt  Bom. Não anuncie — deixe na porta e não diga nada.
    >>  ............................................
  crabby.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first, and they'll pretend they didn't need you to.
    >>  ............................................
    pt  Então você será o primeiro, e vão fingir que não precisavam.
    >>  ............................................
  crabby.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. They're proudest after dark, for some reason.
    >>  ............................................
    pt  Vá de manhã. Ficam mais orgulhosos depois do escuro, sei lá por quê.
    >>  ............................................
  crabby.dialogue.conversations.neighbour.needs.go/3
    en  Good. Don't announce it — put it by the door and say nothing.
    >>  ............................................
    pt  Bom. Não anuncie — deixe na porta e não diga nada.
    >>  ............................................
  extroverted.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first, %1$s, and they'll pretend they didn't need you to.
    >>  ............................................
    pt  Então você será o primeiro, %1$s, e vão fingir que não precisavam.
    >>  ............................................
  extroverted.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. They're proudest after dark, and pride makes a poor host.
    >>  ............................................
    pt  Vá de manhã. Ficam mais orgulhosos depois do escuro, e orgulho é anfitrião ruim.
    >>  ............................................
  extroverted.dialogue.conversations.neighbour.needs.go/3
    en  Good. Don't announce it. Put it by the door and let them keep their dignity.
    >>  ............................................
    pt  Bom. Não anuncie. Deixe na porta e deixe eles com a dignidade.
    >>  ............................................
  flirty.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first, %1$s, and they'll pretend they didn't need you to.
    >>  ............................................
    pt  Então você será o primeiro, %1$s, e vão fingir que não precisavam.
    >>  ............................................
  flirty.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. They're proudest after dark, and pride makes a poor host.
    >>  ............................................
    pt  Vá de manhã. Ficam mais orgulhosos depois do escuro, e orgulho é anfitrião ruim.
    >>  ............................................
  flirty.dialogue.conversations.neighbour.needs.go/3
    en  Good. Don't announce it. Put it by the door and let them keep their dignity.
    >>  ............................................
    pt  Bom. Não anuncie. Deixe na porta e deixe eles com a dignidade.
    >>  ............................................
  friendly.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first, %1$s, and they'll pretend they didn't need you to.
    >>  ............................................
    pt  Então você será o primeiro, %1$s, e vão fingir que não precisavam.
    >>  ............................................
  friendly.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. They're proudest after dark, and pride makes a poor host.
    >>  ............................................
    pt  Vá de manhã. Ficam mais orgulhosos depois do escuro, e orgulho é anfitrião ruim.
    >>  ............................................
  friendly.dialogue.conversations.neighbour.needs.go/3
    en  Good. Don't announce it. Put it by the door and let them keep their dignity.
    >>  ............................................
    pt  Bom. Não anuncie. Deixe na porta e deixe eles com a dignidade.
    >>  ............................................
  gloomy.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first, and they'll pretend they didn't need it, and they did.
    >>  ............................................
    pt  Então você será o primeiro, e vão fingir que não precisavam, e precisavam.
    >>  ............................................
  gloomy.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. After dark they've had all day to decide they deserve nothing.
    >>  ............................................
    pt  Vá de manhã. Depois do escuro já tiveram o dia todo pra decidir que não merecem nada.
    >>  ............................................
  gloomy.dialogue.conversations.neighbour.needs.go/3
    en  Good. Don't announce it. Being helped in front of somebody is its own small wound.
    >>  ............................................
    pt  Bom. Não anuncie. Ser ajudado na frente de alguém é uma pequena ferida própria.
    >>  ............................................
  greedy.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first, and they'll pretend they didn't need you to.
    >>  ............................................
    pt  Então você será o primeiro, e vão fingir que não precisavam.
    >>  ............................................
  greedy.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. They're proudest after dark, for some reason.
    >>  ............................................
    pt  Vá de manhã. Ficam mais orgulhosos depois do escuro, sei lá por quê.
    >>  ............................................
  greedy.dialogue.conversations.neighbour.needs.go/3
    en  Good. Don't announce it — put it by the door and say nothing.
    >>  ............................................
    pt  Bom. Não anuncie — deixe na porta e não diga nada.
    >>  ............................................
  grumpy.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first, and they'll pretend they didn't need you to.
    >>  ............................................
    pt  Então você será o primeiro, e vão fingir que não precisavam.
    >>  ............................................
  grumpy.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. They're proudest after dark, for some reason.
    >>  ............................................
    pt  Vá de manhã. Ficam mais orgulhosos depois do escuro, sei lá por quê.
    >>  ............................................
  grumpy.dialogue.conversations.neighbour.needs.go/3
    en  Good. Don't announce it — put it by the door and say nothing.
    >>  ............................................
    pt  Bom. Não anuncie — deixe na porta e não diga nada.
    >>  ............................................
  introverted.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first.
    >>  ............................................
    pt  Então você será o primeiro.
    >>  ............................................
  introverted.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. Not after dark.
    >>  ............................................
    pt  Vá de manhã. Não depois do escuro.
    >>  ............................................
  introverted.dialogue.conversations.neighbour.needs.go/3
    en  Don't announce it. By the door.
    >>  ............................................
    pt  Não anuncie. Na porta.
    >>  ............................................
  lazy.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first. I've watched that door for years and nobody has gone to it.
    >>  ............................................
    pt  Então você será o primeiro. Vigio aquela porta há anos e ninguém foi lá.
    >>  ............................................
  lazy.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. Forty years here teaches you when a proud house is at its worst.
    >>  ............................................
    pt  Vá de manhã. Quarenta anos aqui ensinam quando uma casa orgulhosa está pior.
    >>  ............................................
  lazy.dialogue.conversations.neighbour.needs.go/3
    en  Good. Don't announce it. The best help this village ever did was done at dawn.
    >>  ............................................
    pt  Bom. Não anuncie. A melhor ajuda deste vilarejo sempre foi feita ao amanhecer.
    >>  ............................................
  odd.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first.
    >>  ............................................
    pt  Então você será o primeiro.
    >>  ............................................
  odd.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. Not after dark.
    >>  ............................................
    pt  Vá de manhã. Não depois do escuro.
    >>  ............................................
  odd.dialogue.conversations.neighbour.needs.go/3
    en  Don't announce it. By the door.
    >>  ............................................
    pt  Não anuncie. Na porta.
    >>  ............................................
  peaceful.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first. I've watched that door for years and nobody has gone to it.
    >>  ............................................
    pt  Então você será o primeiro. Vigio aquela porta há anos e ninguém foi lá.
    >>  ............................................
  peaceful.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. Forty years here teaches you when a proud house is at its worst.
    >>  ............................................
    pt  Vá de manhã. Quarenta anos aqui ensinam quando uma casa orgulhosa está pior.
    >>  ............................................
  peaceful.dialogue.conversations.neighbour.needs.go/3
    en  Good. Don't announce it. The best help this village ever did was done at dawn.
    >>  ............................................
    pt  Bom. Não anuncie. A melhor ajuda deste vilarejo sempre foi feita ao amanhecer.
    >>  ............................................
  peppy.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first! And they'll pretend they never needed you to. Watch.
    >>  ............................................
    pt  Então você será o primeiro! E vão fingir que nunca precisaram. Observe.
    >>  ............................................
  peppy.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. They're proudest after dark, for reasons nobody has explained.
    >>  ............................................
    pt  Vá de manhã. Ficam mais orgulhosos depois do escuro, por razões que ninguém explicou.
    >>  ............................................
  peppy.dialogue.conversations.neighbour.needs.go/3
    en  Good. Don't announce it — leave it by the door and say absolutely nothing.
    >>  ............................................
    pt  Bom. Não anuncie — deixe na porta e não diga absolutamente nada.
    >>  ............................................
  playful.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first! And they'll pretend they never needed you to. Watch.
    >>  ............................................
    pt  Então você será o primeiro! E vão fingir que nunca precisaram. Observe.
    >>  ............................................
  playful.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. They're proudest after dark, for reasons nobody has explained.
    >>  ............................................
    pt  Vá de manhã. Ficam mais orgulhosos depois do escuro, por razões que ninguém explicou.
    >>  ............................................
  playful.dialogue.conversations.neighbour.needs.go/3
    en  Good. Don't announce it — leave it by the door and say absolutely nothing.
    >>  ............................................
    pt  Bom. Não anuncie — deixe na porta e não diga absolutamente nada.
    >>  ............................................
  relaxed.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first. I've watched that door for years and nobody has gone to it.
    >>  ............................................
    pt  Então você será o primeiro. Vigio aquela porta há anos e ninguém foi lá.
    >>  ............................................
  relaxed.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. Forty years here teaches you when a proud house is at its worst.
    >>  ............................................
    pt  Vá de manhã. Quarenta anos aqui ensinam quando uma casa orgulhosa está pior.
    >>  ............................................
  relaxed.dialogue.conversations.neighbour.needs.go/3
    en  Good. Don't announce it. The best help this village ever did was done at dawn.
    >>  ............................................
    pt  Bom. Não anuncie. A melhor ajuda deste vilarejo sempre foi feita ao amanhecer.
    >>  ............................................
  sensitive.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first, and they'll pretend they didn't need it, and they did.
    >>  ............................................
    pt  Então você será o primeiro, e vão fingir que não precisavam, e precisavam.
    >>  ............................................
  sensitive.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. After dark they've had all day to decide they deserve nothing.
    >>  ............................................
    pt  Vá de manhã. Depois do escuro já tiveram o dia todo pra decidir que não merecem nada.
    >>  ............................................
  sensitive.dialogue.conversations.neighbour.needs.go/3
    en  Good. Don't announce it. Being helped in front of somebody is its own small wound.
    >>  ............................................
    pt  Bom. Não anuncie. Ser ajudado na frente de alguém é uma pequena ferida própria.
    >>  ............................................
  shy.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first.
    >>  ............................................
    pt  Então você será o primeiro.
    >>  ............................................
  shy.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. Not after dark.
    >>  ............................................
    pt  Vá de manhã. Não depois do escuro.
    >>  ............................................
  shy.dialogue.conversations.neighbour.needs.go/3
    en  Don't announce it. By the door.
    >>  ............................................
    pt  Não anuncie. Na porta.
    >>  ............................................
  upbeat.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first! And they'll pretend they never needed you to. Watch.
    >>  ............................................
    pt  Então você será o primeiro! E vão fingir que nunca precisaram. Observe.
    >>  ............................................
  upbeat.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. They're proudest after dark, for reasons nobody has explained.
    >>  ............................................
    pt  Vá de manhã. Ficam mais orgulhosos depois do escuro, por razões que ninguém explicou.
    >>  ............................................
  upbeat.dialogue.conversations.neighbour.needs.go/3
    en  Good. Don't announce it — leave it by the door and say absolutely nothing.
    >>  ............................................
    pt  Bom. Não anuncie — deixe na porta e não diga absolutamente nada.
    >>  ............................................
  witty.dialogue.conversations.neighbour.needs.go/1
    en  Then you'll be the first! And they'll pretend they never needed you to. Watch.
    >>  ............................................
    pt  Então você será o primeiro! E vão fingir que nunca precisaram. Observe.
    >>  ............................................
  witty.dialogue.conversations.neighbour.needs.go/2
    en  Go in the morning. They're proudest after dark, for reasons nobody has explained.
    >>  ............................................
    pt  Vá de manhã. Ficam mais orgulhosos depois do escuro, por razões que ninguém explicou.
    >>  ............................................
  witty.dialogue.conversations.neighbour.needs.go/3
    en  Good. Don't announce it — leave it by the door and say absolutely nothing.
    >>  ............................................
    pt  Bom. Não anuncie — deixe na porta e não diga absolutamente nada.
    >>  ............................................
```

</details>


### Button `tell_them_i_offered` — "Tell them I offered."

*stance family `candor` · tone `plain` · outcome `accepted` · answers the beat(s) `neighbour.more.they_need`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.needs.offered` — accepted phrasings: "tell them i offered"; "let them know i offered"; "pass on that i am willing"
  - the message must contain one of: `offered`
  - scored words: `offered`(1.5), `tell`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.needs.tell_them_i_offered
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.needs
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.needs.tell_them_i_offered   [20 chars]
    en  Tell them I offered.
    >>  ............................................
    pt  Diga a eles que eu ofereci.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `neighbour.needs.offered`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +1  _(recorded under topic `neighbour.needs.offered`)_
- Does: arc `neighbour` — advance
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.needs.offered
WHO    VILLAGER — what the player reads after pressing "Tell them I offered."
       spoken on: conversations.topic.neighbour.needs, button `tell_them_i_offered`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.needs.offered`: the villager accepts. Subject `neighbour.needs`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.needs.offered/1   [74 chars]
    en  I will. Coming from me it'll get past the pride, which is the trick of it.
    >>  ............................................
    pt  Vou dizer. Vindo de mim passa pelo orgulho, que é o truque.
    >>  ............................................
  dialogue.conversations.neighbour.needs.offered/2   [70 chars]
    en  That's the wiser way round. They'd refuse you and accept me saying it.
    >>  ............................................
    pt  É o jeito mais sábio. Recusariam você e aceitariam eu dizendo.
    >>  ............................................
  dialogue.conversations.neighbour.needs.offered/3   [67 chars]
    en  Consider it said. Don't expect thanks — expect a nod in six months.
    >>  ............................................
    pt  Considere dito. Não espere agradecimento — espere um aceno em seis meses.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.neighbour.needs.offered/1
    en  I will. Coming from me it gets past the pride, and I know how much pride is left them.
    >>  ............................................
    pt  Vou dizer. Vindo de mim passa pelo orgulho, e sei quanto orgulho lhes resta.
    >>  ............................................
  anxious.dialogue.conversations.neighbour.needs.offered/2
    en  That's the wiser way round. They'd refuse you, and refusing costs them something.
    >>  ............................................
    pt  É o jeito mais sábio. Recusariam você, e recusar lhes custa algo.
    >>  ............................................
  anxious.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. Don't expect thanks; expecting thanks would spoil what you've done.
    >>  ............................................
    pt  Considere dito. Não espere agradecimento; esperar estragaria o que você fez.
    >>  ............................................
  athletic.dialogue.conversations.neighbour.needs.offered/1
    en  I will. Coming from me it gets past the pride; I've been doing that for thirty years.
    >>  ............................................
    pt  Vou dizer. Vindo de mim passa pelo orgulho; faço isso há trinta anos.
    >>  ............................................
  athletic.dialogue.conversations.neighbour.needs.offered/2
    en  That's the wiser way round. I've carried a dozen offers that way and lost none of them.
    >>  ............................................
    pt  É o jeito mais sábio. Já levei uma dúzia de ofertas assim e não perdi nenhuma.
    >>  ............................................
  athletic.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. Don't expect thanks. A nod in six months is how thanks is done here.
    >>  ............................................
    pt  Considere dito. Não espere agradecimento. Um aceno em seis meses é como se agradece aqui.
    >>  ............................................
  confident.dialogue.conversations.neighbour.needs.offered/1
    en  I will. Coming from me it'll get past the pride, which is the trick of it.
    >>  ............................................
    pt  Vou dizer. Vindo de mim passa pelo orgulho, que é o truque.
    >>  ............................................
  confident.dialogue.conversations.neighbour.needs.offered/2
    en  That's the wiser way round. They'd refuse you and accept me saying it.
    >>  ............................................
    pt  É o jeito mais sábio. Recusariam você e aceitariam eu dizendo.
    >>  ............................................
  confident.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. Don't expect thanks — expect a nod in six months.
    >>  ............................................
    pt  Considere dito. Não espere agradecimento — espere um aceno em seis meses.
    >>  ............................................
  crabby.dialogue.conversations.neighbour.needs.offered/1
    en  I will. Coming from me it'll get past the pride, which is the trick of it.
    >>  ............................................
    pt  Vou dizer. Vindo de mim passa pelo orgulho, que é o truque.
    >>  ............................................
  crabby.dialogue.conversations.neighbour.needs.offered/2
    en  That's the wiser way round. They'd refuse you and accept me saying it.
    >>  ............................................
    pt  É o jeito mais sábio. Recusariam você e aceitariam eu dizendo.
    >>  ............................................
  crabby.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. Don't expect thanks — expect a nod in six months.
    >>  ............................................
    pt  Considere dito. Não espere agradecimento — espere um aceno em seis meses.
    >>  ............................................
  extroverted.dialogue.conversations.neighbour.needs.offered/1
    en  I will, %1$s. Coming from me it gets past the pride, which is the trick of it.
    >>  ............................................
    pt  Vou dizer, %1$s. Vindo de mim passa pelo orgulho, que é o truque.
    >>  ............................................
  extroverted.dialogue.conversations.neighbour.needs.offered/2
    en  That's the wiser way round, and I'd not have thought of it if you hadn't asked.
    >>  ............................................
    pt  É o jeito mais sábio, e eu não teria pensado se você não tivesse perguntado.
    >>  ............................................
  extroverted.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. Don't expect thanks. Expect a nod in six months and be glad of it.
    >>  ............................................
    pt  Considere dito. Não espere agradecimento. Espere um aceno em seis meses e se contente.
    >>  ............................................
  flirty.dialogue.conversations.neighbour.needs.offered/1
    en  I will, %1$s. Coming from me it gets past the pride, which is the trick of it.
    >>  ............................................
    pt  Vou dizer, %1$s. Vindo de mim passa pelo orgulho, que é o truque.
    >>  ............................................
  flirty.dialogue.conversations.neighbour.needs.offered/2
    en  That's the wiser way round, and I'd not have thought of it if you hadn't asked.
    >>  ............................................
    pt  É o jeito mais sábio, e eu não teria pensado se você não tivesse perguntado.
    >>  ............................................
  flirty.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. Don't expect thanks. Expect a nod in six months and be glad of it.
    >>  ............................................
    pt  Considere dito. Não espere agradecimento. Espere um aceno em seis meses e se contente.
    >>  ............................................
  friendly.dialogue.conversations.neighbour.needs.offered/1
    en  I will, %1$s. Coming from me it gets past the pride, which is the trick of it.
    >>  ............................................
    pt  Vou dizer, %1$s. Vindo de mim passa pelo orgulho, que é o truque.
    >>  ............................................
  friendly.dialogue.conversations.neighbour.needs.offered/2
    en  That's the wiser way round, and I'd not have thought of it if you hadn't asked.
    >>  ............................................
    pt  É o jeito mais sábio, e eu não teria pensado se você não tivesse perguntado.
    >>  ............................................
  friendly.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. Don't expect thanks. Expect a nod in six months and be glad of it.
    >>  ............................................
    pt  Considere dito. Não espere agradecimento. Espere um aceno em seis meses e se contente.
    >>  ............................................
  gloomy.dialogue.conversations.neighbour.needs.offered/1
    en  I will. Coming from me it gets past the pride, and I know how much pride is left them.
    >>  ............................................
    pt  Vou dizer. Vindo de mim passa pelo orgulho, e sei quanto orgulho lhes resta.
    >>  ............................................
  gloomy.dialogue.conversations.neighbour.needs.offered/2
    en  That's the wiser way round. They'd refuse you, and refusing costs them something.
    >>  ............................................
    pt  É o jeito mais sábio. Recusariam você, e recusar lhes custa algo.
    >>  ............................................
  gloomy.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. Don't expect thanks; expecting thanks would spoil what you've done.
    >>  ............................................
    pt  Considere dito. Não espere agradecimento; esperar estragaria o que você fez.
    >>  ............................................
  greedy.dialogue.conversations.neighbour.needs.offered/1
    en  I will. Coming from me it'll get past the pride, which is the trick of it.
    >>  ............................................
    pt  Vou dizer. Vindo de mim passa pelo orgulho, que é o truque.
    >>  ............................................
  greedy.dialogue.conversations.neighbour.needs.offered/2
    en  That's the wiser way round. They'd refuse you and accept me saying it.
    >>  ............................................
    pt  É o jeito mais sábio. Recusariam você e aceitariam eu dizendo.
    >>  ............................................
  greedy.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. Don't expect thanks — expect a nod in six months.
    >>  ............................................
    pt  Considere dito. Não espere agradecimento — espere um aceno em seis meses.
    >>  ............................................
  grumpy.dialogue.conversations.neighbour.needs.offered/1
    en  I will. Coming from me it'll get past the pride, which is the trick of it.
    >>  ............................................
    pt  Vou dizer. Vindo de mim passa pelo orgulho, que é o truque.
    >>  ............................................
  grumpy.dialogue.conversations.neighbour.needs.offered/2
    en  That's the wiser way round. They'd refuse you and accept me saying it.
    >>  ............................................
    pt  É o jeito mais sábio. Recusariam você e aceitariam eu dizendo.
    >>  ............................................
  grumpy.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. Don't expect thanks — expect a nod in six months.
    >>  ............................................
    pt  Considere dito. Não espere agradecimento — espere um aceno em seis meses.
    >>  ............................................
  introverted.dialogue.conversations.neighbour.needs.offered/1
    en  I will. It gets past the pride, coming from me.
    >>  ............................................
    pt  Vou dizer. Passa pelo orgulho, vindo de mim.
    >>  ............................................
  introverted.dialogue.conversations.neighbour.needs.offered/2
    en  Wiser that way. They'd refuse you.
    >>  ............................................
    pt  Mais sábio assim. Recusariam você.
    >>  ............................................
  introverted.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. A nod, in six months.
    >>  ............................................
    pt  Considere dito. Um aceno, em seis meses.
    >>  ............................................
  lazy.dialogue.conversations.neighbour.needs.offered/1
    en  I will. Coming from me it gets past the pride; I've been doing that for thirty years.
    >>  ............................................
    pt  Vou dizer. Vindo de mim passa pelo orgulho; faço isso há trinta anos.
    >>  ............................................
  lazy.dialogue.conversations.neighbour.needs.offered/2
    en  That's the wiser way round. I've carried a dozen offers that way and lost none of them.
    >>  ............................................
    pt  É o jeito mais sábio. Já levei uma dúzia de ofertas assim e não perdi nenhuma.
    >>  ............................................
  lazy.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. Don't expect thanks. A nod in six months is how thanks is done here.
    >>  ............................................
    pt  Considere dito. Não espere agradecimento. Um aceno em seis meses é como se agradece aqui.
    >>  ............................................
  odd.dialogue.conversations.neighbour.needs.offered/1
    en  I will. It gets past the pride, coming from me.
    >>  ............................................
    pt  Vou dizer. Passa pelo orgulho, vindo de mim.
    >>  ............................................
  odd.dialogue.conversations.neighbour.needs.offered/2
    en  Wiser that way. They'd refuse you.
    >>  ............................................
    pt  Mais sábio assim. Recusariam você.
    >>  ............................................
  odd.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. A nod, in six months.
    >>  ............................................
    pt  Considere dito. Um aceno, em seis meses.
    >>  ............................................
  peaceful.dialogue.conversations.neighbour.needs.offered/1
    en  I will. Coming from me it gets past the pride; I've been doing that for thirty years.
    >>  ............................................
    pt  Vou dizer. Vindo de mim passa pelo orgulho; faço isso há trinta anos.
    >>  ............................................
  peaceful.dialogue.conversations.neighbour.needs.offered/2
    en  That's the wiser way round. I've carried a dozen offers that way and lost none of them.
    >>  ............................................
    pt  É o jeito mais sábio. Já levei uma dúzia de ofertas assim e não perdi nenhuma.
    >>  ............................................
  peaceful.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. Don't expect thanks. A nod in six months is how thanks is done here.
    >>  ............................................
    pt  Considere dito. Não espere agradecimento. Um aceno em seis meses é como se agradece aqui.
    >>  ............................................
  peppy.dialogue.conversations.neighbour.needs.offered/1
    en  I will! Coming from me it gets past the pride, which is the entire trick of it.
    >>  ............................................
    pt  Vou dizer! Vindo de mim passa pelo orgulho, que é o truque inteiro.
    >>  ............................................
  peppy.dialogue.conversations.neighbour.needs.offered/2
    en  That's the wiser way round. They'd refuse you flat and accept me saying it.
    >>  ............................................
    pt  É o jeito mais sábio. Recusariam você na hora e aceitariam eu dizendo.
    >>  ............................................
  peppy.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. Don't expect thanks — expect a nod, in about six months.
    >>  ............................................
    pt  Considere dito. Não espere agradecimento — espere um aceno, em uns seis meses.
    >>  ............................................
  playful.dialogue.conversations.neighbour.needs.offered/1
    en  I will! Coming from me it gets past the pride, which is the entire trick of it.
    >>  ............................................
    pt  Vou dizer! Vindo de mim passa pelo orgulho, que é o truque inteiro.
    >>  ............................................
  playful.dialogue.conversations.neighbour.needs.offered/2
    en  That's the wiser way round. They'd refuse you flat and accept me saying it.
    >>  ............................................
    pt  É o jeito mais sábio. Recusariam você na hora e aceitariam eu dizendo.
    >>  ............................................
  playful.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. Don't expect thanks — expect a nod, in about six months.
    >>  ............................................
    pt  Considere dito. Não espere agradecimento — espere um aceno, em uns seis meses.
    >>  ............................................
  relaxed.dialogue.conversations.neighbour.needs.offered/1
    en  I will. Coming from me it gets past the pride; I've been doing that for thirty years.
    >>  ............................................
    pt  Vou dizer. Vindo de mim passa pelo orgulho; faço isso há trinta anos.
    >>  ............................................
  relaxed.dialogue.conversations.neighbour.needs.offered/2
    en  That's the wiser way round. I've carried a dozen offers that way and lost none of them.
    >>  ............................................
    pt  É o jeito mais sábio. Já levei uma dúzia de ofertas assim e não perdi nenhuma.
    >>  ............................................
  relaxed.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. Don't expect thanks. A nod in six months is how thanks is done here.
    >>  ............................................
    pt  Considere dito. Não espere agradecimento. Um aceno em seis meses é como se agradece aqui.
    >>  ............................................
  sensitive.dialogue.conversations.neighbour.needs.offered/1
    en  I will. Coming from me it gets past the pride, and I know how much pride is left them.
    >>  ............................................
    pt  Vou dizer. Vindo de mim passa pelo orgulho, e sei quanto orgulho lhes resta.
    >>  ............................................
  sensitive.dialogue.conversations.neighbour.needs.offered/2
    en  That's the wiser way round. They'd refuse you, and refusing costs them something.
    >>  ............................................
    pt  É o jeito mais sábio. Recusariam você, e recusar lhes custa algo.
    >>  ............................................
  sensitive.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. Don't expect thanks; expecting thanks would spoil what you've done.
    >>  ............................................
    pt  Considere dito. Não espere agradecimento; esperar estragaria o que você fez.
    >>  ............................................
  shy.dialogue.conversations.neighbour.needs.offered/1
    en  I will. It gets past the pride, coming from me.
    >>  ............................................
    pt  Vou dizer. Passa pelo orgulho, vindo de mim.
    >>  ............................................
  shy.dialogue.conversations.neighbour.needs.offered/2
    en  Wiser that way. They'd refuse you.
    >>  ............................................
    pt  Mais sábio assim. Recusariam você.
    >>  ............................................
  shy.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. A nod, in six months.
    >>  ............................................
    pt  Considere dito. Um aceno, em seis meses.
    >>  ............................................
  upbeat.dialogue.conversations.neighbour.needs.offered/1
    en  I will! Coming from me it gets past the pride, which is the entire trick of it.
    >>  ............................................
    pt  Vou dizer! Vindo de mim passa pelo orgulho, que é o truque inteiro.
    >>  ............................................
  upbeat.dialogue.conversations.neighbour.needs.offered/2
    en  That's the wiser way round. They'd refuse you flat and accept me saying it.
    >>  ............................................
    pt  É o jeito mais sábio. Recusariam você na hora e aceitariam eu dizendo.
    >>  ............................................
  upbeat.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. Don't expect thanks — expect a nod, in about six months.
    >>  ............................................
    pt  Considere dito. Não espere agradecimento — espere um aceno, em uns seis meses.
    >>  ............................................
  witty.dialogue.conversations.neighbour.needs.offered/1
    en  I will! Coming from me it gets past the pride, which is the entire trick of it.
    >>  ............................................
    pt  Vou dizer! Vindo de mim passa pelo orgulho, que é o truque inteiro.
    >>  ............................................
  witty.dialogue.conversations.neighbour.needs.offered/2
    en  That's the wiser way round. They'd refuse you flat and accept me saying it.
    >>  ............................................
    pt  É o jeito mais sábio. Recusariam você na hora e aceitariam eu dizendo.
    >>  ............................................
  witty.dialogue.conversations.neighbour.needs.offered/3
    en  Consider it said. Don't expect thanks — expect a nod, in about six months.
    >>  ............................................
    pt  Considere dito. Não espere agradecimento — espere um aceno, em uns seis meses.
    >>  ............................................
```

</details>


### Button `leave` — "Right."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `neighbour.more.they_need` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.needs.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.needs
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.needs.leave   [6 chars]
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
POOL   dialogue key: dialogue.conversations.neighbour.needs.leave
WHO    VILLAGER — what the player reads after pressing "Right."
       spoken on: conversations.topic.neighbour.needs, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.needs.leave`: the villager accepts. Subject `neighbour.needs`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.needs.leave/1   [12 chars]
    en  True enough.
    >>  ............................................
    pt  Bem verdade.
    >>  ............................................
  dialogue.conversations.neighbour.needs.leave/2   [5 chars]
    en  Good.
    >>  ............................................
    pt  Bom.
    >>  ............................................
  dialogue.conversations.neighbour.needs.leave/3   [16 chars]
    en  Mind how you go.
    >>  ............................................
    pt  Olhe por onde anda.
    >>  ............................................
```

---


## `conversations.topic.neighbour.none.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `neighbour`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.neighbour.none` — e.g. "Nobody in particular. It's been a settled sort of week and I'll not jinx it."


```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.none.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.neighbour.none.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.neighbour.none.respond   [31 chars]
    en  Nobody comes to mind, honestly.
    >>  ............................................
    pt  Não me vem ninguém, sinceramente.
    >>  ............................................
```


### Button `ask_anyway` — "Nobody at all?"

*stance family `curiosity` · tone `plain` · answers the beat(s) `neighbour.none.to.neighbour.none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.ask_anyway` — accepted phrasings: "nobody at all"; "nobody at all then"; "really nobody"
  - the message must contain one of: `nobody`
  - scored words: `nobody`(1.5), `all`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.none.respond.ask_anyway
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.none.respond.ask_anyway   [14 chars]
    en  Nobody at all?
    >>  ............................................
    pt  Ninguém mesmo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `neighbour.ask_anyway`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.ask_anyway
WHO    VILLAGER — what the player reads after pressing "Nobody at all?"
       spoken on: conversations.topic.neighbour.none.respond, button `ask_anyway`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.ask_anyway.terminal`: the villager accepts. Subject `neighbour.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.neighbour.ask_anyway/1   [81 chars]
    en  Nobody worth naming. There's always somebody worth naming, so this is remarkable.
    >>  ............................................
    pt  Ninguém que valha nomear. Sempre tem alguém que vale nomear, então isso é notável.
    >>  ............................................
  dialogue.conversations.neighbour.ask_anyway/2   [86 chars]
    en  Not one, %1$s. I've checked. I checked twice, actually, which tells you about my week.
    >>  ............................................
    pt  Nem um, %1$s. Eu conferi. Conferi duas vezes, aliás, o que diz muito da minha semana.
    >>  ............................................
  dialogue.conversations.neighbour.ask_anyway/3   [87 chars]
    en  No. Everyone's just quietly getting on with it. I don't know what to do with the peace.
    >>  ............................................
    pt  Não. Todo mundo tocando a vida em silêncio. Não sei o que fazer com a paz.
    >>  ............................................
```


### Button `glad` — "That sounds like a good week."

*stance family `encouragement` · tone `plain` · answers the beat(s) `neighbour.none.to.neighbour.none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.glad` — accepted phrasings: "that sounds like a good week"; "sounds like a good week"; "a good week then"
  - the message must contain one of: `week`, `good`
  - scored words: `week`(1.5), `good`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.none.respond.glad
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.none.respond.glad   [29 chars]
    en  That sounds like a good week.
    >>  ............................................
    pt  Parece uma boa semana.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `neighbour.glad`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +3  _(recorded under topic `neighbour.glad`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.glad
WHO    VILLAGER — what the player reads after pressing "That sounds like a good week."
       spoken on: conversations.topic.neighbour.none.respond, button `glad`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.glad.terminal`: the villager accepts. Subject `neighbour.fondness`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.neighbour.glad/1   [92 chars]
    en  It has been, at that. Nobody's fallen out with anybody. I'd forgotten it could be like this.
    >>  ............................................
    pt  Foi mesmo. Ninguém brigou com ninguém. Eu tinha esquecido que podia ser assim.
    >>  ............................................
  dialogue.conversations.neighbour.glad/2   [76 chars]
    en  A good week. Aye. Say it quietly, %1$s, in case somebody hears and fixes it.
    >>  ............................................
    pt  Uma boa semana. É. Fale baixo, %1$s, vai que alguém ouve e conserta.
    >>  ............................................
  dialogue.conversations.neighbour.glad/3   [63 chars]
    en  It is. Ordinary weeks get no credit and they're most of a life.
    >>  ............................................
    pt  É. Semana comum não recebe crédito e é a maior parte de uma vida.
    >>  ............................................
```


### Button `press` — "Someone must have done something."

*stance family `boundary_push` · tone `blunt` · answers the beat(s) `neighbour.none.to.neighbour.none`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.press` — accepted phrasings: "someone must have done something"; "somebody must have done something"; "surely someone did something"
  - the message must contain one of: `must`
  - scored words: `must`(1.5), `someone`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.none.respond.press
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.none.respond.press   [33 chars]
    en  Someone must have done something.
    >>  ............................................
    pt  Alguém deve ter feito alguma coisa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `neighbour.press`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — tension +3, respect -2  _(recorded under topic `neighbour.press`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.press
WHO    VILLAGER — what the player reads after pressing "Someone must have done something."
       spoken on: conversations.topic.neighbour.none.respond, button `press`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.press.terminal`: the villager resists. Subject `neighbour.boundary`, polarity `negative`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.neighbour.press/1   [86 chars]
    en  Somebody must have. That's the attitude that starts things, and I'll not help with it.
    >>  ............................................
    pt  Alguém deve ter. É essa a atitude que começa as coisas, e eu não vou ajudar.
    >>  ............................................
  dialogue.conversations.neighbour.press/2   [60 chars]
    en  Nobody has. Wanting there to be is a different matter, %1$s.
    >>  ............................................
    pt  Ninguém fez. Querer que tenha feito é outro assunto, %1$s.
    >>  ............................................
  dialogue.conversations.neighbour.press/3   [76 chars]
    en  No. And asking twice doesn't make one appear, however much you'd like it to.
    >>  ............................................
    pt  Não. E perguntar duas vezes não faz aparecer, por mais que você queira.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · answers the beat(s) `neighbour.none.to.neighbour.none` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.none.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.none.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.none.respond.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.neighbour.none_leave
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.topic.neighbour.none.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.none_leave.terminal`: the villager accepts. Subject `neighbour.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.neighbour.none_leave/1   [65 chars]
    en  Quite. Come back after market day, there'll be something by then.
    >>  ............................................
    pt  Exato. Volte depois do dia de feira, aí vai ter alguma coisa.
    >>  ............................................
  dialogue.conversations.neighbour.none_leave/2   [20 chars]
    en  Right you are, %1$s.
    >>  ............................................
    pt  Isso mesmo, %1$s.
    >>  ............................................
  dialogue.conversations.neighbour.none_leave/3   [43 chars]
    en  Off you go. Enjoy the quiet while it lasts.
    >>  ............................................
    pt  Pode ir. Aproveite o sossego enquanto dura.
    >>  ............................................
```

---


## `conversations.topic.neighbour.privacy`

**Reached from 1 route(s):** `conversations.topic.neighbour.more` / `should_you`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.neighbour.more.should_you` — e.g. "Probably not. I'll stop there, and you'll not repeat the part I've said."


```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.privacy
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.neighbour.privacy
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.neighbour.privacy   [28 chars]
    en  So that's where the line is.
    >>  ............................................
    pt  Então é aí que está o limite.
    >>  ............................................
```


### Button `stays_with_me` — "It stays with me."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `neighbour.more.should_you`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.privacy.stays` — accepted phrasings: "it stays with me"; "it is safe with me"; "i will not repeat it"
  - the message must contain one of: `stays`, `repeat`
  - scored words: `stays`(1.5), `repeat`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.privacy.stays_with_me
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.privacy
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.privacy.stays_with_me   [17 chars]
    en  It stays with me.
    >>  ............................................
    pt  Fica comigo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `neighbour.privacy.stays`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3, warmth +2  _(recorded under topic `neighbour.privacy.stays`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.privacy.stays
WHO    VILLAGER — what the player reads after pressing "It stays with me."
       spoken on: conversations.topic.neighbour.privacy, button `stays_with_me`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.privacy.stays`: the villager accepts. Subject `neighbour.privacy`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.privacy.stays/1   [74 chars]
    en  Then I'll go on telling you things, which is a rarer offer than it sounds.
    >>  ............................................
    pt  Então eu continuo te contando coisas, uma oferta mais rara do que parece.
    >>  ............................................
  dialogue.conversations.neighbour.privacy.stays/2   [58 chars]
    en  Good. I'll know soon enough if it didn't, and so will you.
    >>  ............................................
    pt  Bom. Vou saber logo se não ficou, e você também.
    >>  ............................................
  dialogue.conversations.neighbour.privacy.stays/3   [75 chars]
    en  Thank you. That's the whole of what I wanted and I'd not have asked for it.
    >>  ............................................
    pt  Obrigado. É tudo que eu queria e não teria pedido.
    >>  ............................................
```


### Button `rather_hear_it_from_them` — "I'd rather hear it from them."

*stance family `restraint` · tone `plain` · outcome `appreciated` · answers the beat(s) `neighbour.more.should_you`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.privacy.from_them` — accepted phrasings: "i would rather hear it from them"; "i will ask them myself"; "they should tell me themselves"
  - the message must contain one of: `themselves`
  - scored words: `them`(0.4), `themselves`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.privacy.rather_hear_it_from_them
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.privacy
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.privacy.rather_hear_it_from_them   [29 chars]
    en  I'd rather hear it from them.
    >>  ............................................
    pt  Prefiro ouvir deles.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `neighbour.privacy.from_them`)_
- Does: session `turn`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.privacy.from_them
WHO    VILLAGER — what the player reads after pressing "I'd rather hear it from them."
       spoken on: conversations.topic.neighbour.privacy, button `rather_hear_it_from_them`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.privacy.from_them`: the villager accepts. Subject `neighbour.privacy`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.privacy.from_them/1   [68 chars]
    en  That's the right answer and I'm a little ashamed you had to give it.
    >>  ............................................
    pt  É a resposta certa e me envergonha um pouco que você tenha que dá-la.
    >>  ............................................
  dialogue.conversations.neighbour.privacy.from_them/2   [72 chars]
    en  Then go and ask. They'll tell it better and they'll be glad to be asked.
    >>  ............................................
    pt  Então vá perguntar. Vão contar melhor e vão gostar de ser perguntados.
    >>  ............................................
  dialogue.conversations.neighbour.privacy.from_them/3   [78 chars]
    en  It is. I'd been enjoying knowing something, and that's a poor reason to speak.
    >>  ............................................
    pt  É sim. Eu estava gostando de saber algo, e é um motivo ruim pra falar.
    >>  ............................................
```


### Button `leave` — "Fair enough."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `neighbour.more.should_you` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.privacy.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.privacy
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.privacy.leave   [12 chars]
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
POOL   dialogue key: dialogue.conversations.neighbour.privacy.leave
WHO    VILLAGER — what the player reads after pressing "Fair enough."
       spoken on: conversations.topic.neighbour.privacy, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.privacy.leave`: the villager accepts. Subject `neighbour.privacy`, polarity `neutral`, ends conversation, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.neighbour.privacy.leave/1   [14 chars]
    en  So I've found.
    >>  ............................................
    pt  Foi o que eu vi.
    >>  ............................................
  dialogue.conversations.neighbour.privacy.leave/2   [10 chars]
    en  Safe home.
    >>  ............................................
    pt  Volte bem.
    >>  ............................................
  dialogue.conversations.neighbour.privacy.leave/3   [6 chars]
    en  Go on.
    >>  ............................................
    pt  Vá lá.
    >>  ............................................
```

---


## `conversations.topic.neighbour.respond`

**Reached from 1 route(s):** `conversations.cat.village` / `neighbour`


```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.neighbour.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.neighbour.respond   [37 chars]
    en  That's who's been on my mind, anyway.
    >>  ............................................
    pt  É nele que eu ando pensando, enfim.
    >>  ............................................
```


### Button `ask_more` — "What are they like, really?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.ask_more` — accepted phrasings: "what are they like really"; "what are they actually like"; "what sort of person are they"
  - the message must contain one of: `like`, `really`
  - scored words: `like`(1.4), `really`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.respond.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.respond.ask_more   [27 chars]
    en  What are they like, really?
    >>  ............................................
    pt  Como ele é, de verdade?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, warmth +1  _(recorded under topic `neighbour.ask_more`)_
- Then opens: `conversations.topic.neighbour.followup`
- …where the player's next choices will be: "How long have you known them?" | "You don't sound sure about them." | "Let's leave them be." | "What else can you tell me about them?" | "That'll do."

```text
POOL   dialogue key: dialogue.conversations.neighbour.ask_more
WHO    VILLAGER — what the player reads after pressing "What are they like, really?"
       spoken on: conversations.topic.neighbour.respond, button `ask_more`
       leaves the player on: conversations.topic.neighbour.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.ask_more.to.neighbour`: the villager explains. Subject `neighbour.history`, polarity `positive`, invites followup, outcome `engaged`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.neighbour.ask_more/1   [98 chars]
    en  Really? Stubborn. Generous with the wrong things and careful with the right ones. Like most of us.
    >>  ............................................
    pt  De verdade? Teimoso. Generoso com as coisas erradas e cuidadoso com as certas. Como quase todos.
    >>  ............................................
  dialogue.conversations.neighbour.ask_more/2   [86 chars]
    en  Better than they let on and worse than they think, %1$s. That covers nearly everybody.
    >>  ............................................
    pt  Melhor do que demonstra e pior do que pensa, %1$s. Isso cobre quase todo mundo.
    >>  ............................................
  dialogue.conversations.neighbour.ask_more/3   [97 chars]
    en  Hard to say. I've known them fifteen years and I'd still not swear to what they'd do in a corner.
    >>  ............................................
    pt  Difícil dizer. Conheço há quinze anos e ainda não juraria o que faria encurralado.
    >>  ............................................
```


### Button `defend_them` — "They've always been decent to me."

*stance family `respectful_disagreement` · tone `plain` · outcome `accepted` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.defend_them` — accepted phrasings: "they have always been decent to me"; "they have been decent to me"; "they are decent enough"
  - the message must contain one of: `decent`
  - scored words: `decent`(1.6), `always`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.respond.defend_them
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.respond.defend_them   [33 chars]
    en  They've always been decent to me.
    >>  ............................................
    pt  Ele sempre foi decente comigo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `neighbour.defend_them`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, trust +2  _(recorded under topic `neighbour.defend_them`)_
- Then opens: `conversations.topic.neighbour.followup`
- …where the player's next choices will be: "How long have you known them?" | "You don't sound sure about them." | "Let's leave them be." | "What else can you tell me about them?" | "That'll do."

```text
POOL   dialogue key: dialogue.conversations.neighbour.defend_them
WHO    VILLAGER — what the player reads after pressing "They've always been decent to me."
       spoken on: conversations.topic.neighbour.respond, button `defend_them`
       leaves the player on: conversations.topic.neighbour.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.defend_them.to.neighbour`: the villager accepts. Subject `neighbour.defence`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.neighbour.defend_them/1   [100 chars]
    en  ...They have, haven't they. I'd got into the habit of the other story. Thank you for the correction.
    >>  ............................................
    pt  ...Foi mesmo, né. Eu tinha entrado no hábito da outra história. Obrigado pela correção.
    >>  ............................................
  dialogue.conversations.neighbour.defend_them/2   [90 chars]
    en  That's true, and I'd forgotten it was true. It's easier to remember the one bad afternoon.
    >>  ............................................
    pt  É verdade, e eu tinha esquecido que era. É mais fácil lembrar da única tarde ruim.
    >>  ............................................
  dialogue.conversations.neighbour.defend_them/3   [83 chars]
    en  So I've found. They are decent. I'd let the telling run away from the person, %1$s.
    >>  ............................................
    pt  Foi o que eu vi. Ele é decente. Eu deixei a fofoca fugir da pessoa, %1$s.
    >>  ............................................
```


### Button `not_my_business` — "That's theirs to tell, not yours."

*stance family `restraint` · tone `plain` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.not_my_business` — accepted phrasings: "that is theirs to tell not yours"; "that is theirs to tell"; "not yours to tell"
  - the message must contain one of: `theirs`
  - scored words: `theirs`(1.6), `tell`(1.1)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.respond.not_my_business
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.respond.not_my_business   [33 chars]
    en  That's theirs to tell, not yours.
    >>  ............................................
    pt  Isso é dele para contar, não seu.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `neighbour.not_my_business`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +5, tension -2  _(recorded under topic `neighbour.not_my_business`)_
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.not_my_business
WHO    VILLAGER — what the player reads after pressing "That's theirs to tell, not yours."
       spoken on: conversations.topic.neighbour.respond, button `not_my_business`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.not_my_business.terminal`: the villager accepts. Subject `neighbour.boundary`, polarity `mixed`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.neighbour.not_my_business/1   [87 chars]
    en  ...You're right. It's theirs. I've been carrying it about like it was mine to hand out.
    >>  ............................................
    pt  ...Você tem razão. É dele. Eu venho carregando isso como se fosse meu para distribuir.
    >>  ............................................
  dialogue.conversations.neighbour.not_my_business/2   [82 chars]
    en  That's the correct answer and I didn't want to hear it, which is how I know, %1$s.
    >>  ............................................
    pt  É a resposta certa e eu não queria ouvir, que é como eu sei, %1$s.
    >>  ............................................
  dialogue.conversations.neighbour.not_my_business/3   [65 chars]
    en  Hm. Fair. I'll say no more, and I'll mean it for at least a week.
    >>  ............................................
    pt  Hm. Justo. Não digo mais nada, e vou manter isso por pelo menos uma semana.
    >>  ............................................
```


### Button `encourage_more` — "Go on — what else do you know?"

*stance family `encouragement` · tone `plain` · outcome `accepted` · answers the beat(s) `*`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `neighbour.encourage_more` — accepted phrasings: "go on what else do you know"; "what else do you know"; "tell me what else you know"
  - the message must contain one of: `else`
  - scored words: `else`(1.5), `know`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.respond.encourage_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.respond.encourage_more   [30 chars]
    en  Go on — what else do you know?
    >>  ............................................
    pt  Vai — o que mais você sabe?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts -1** — decision id `neighbour.encourage_more`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -3, tension +3  _(recorded under topic `neighbour.encourage_more`)_
- Then opens: `conversations.topic.neighbour.followup`
- …where the player's next choices will be: "How long have you known them?" | "You don't sound sure about them." | "Let's leave them be." | "What else can you tell me about them?" | "That'll do."

```text
POOL   dialogue key: dialogue.conversations.neighbour.encourage_more
WHO    VILLAGER — what the player reads after pressing "Go on — what else do you know?"
       spoken on: conversations.topic.neighbour.respond, button `encourage_more`
       leaves the player on: conversations.topic.neighbour.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.encourage_more.to.neighbour`: the villager accepts. Subject `neighbour.fondness`, polarity `negative`, permits followup, outcome `accepted`.
NOTE   the buttons that answer it may take almost any stance (13 families), so it must not close the subject down
```

```text
  dialogue.conversations.neighbour.encourage_more/1   [94 chars]
    en  ...I could. I'm not going to. You've a hungry look about you and I don't like where this goes.
    >>  ............................................
    pt  ...Eu poderia. Não vou. Você está com cara de faminto e eu não gosto do rumo disso.
    >>  ............................................
  dialogue.conversations.neighbour.encourage_more/2   [87 chars]
    en  That's the wrong question, %1$s, and I've asked it myself often enough to recognise it.
    >>  ............................................
    pt  É a pergunta errada, %1$s, e eu já fiz ela vezes suficientes para reconhecer.
    >>  ............................................
  dialogue.conversations.neighbour.encourage_more/3   [66 chars]
    en  No. What I've said is already a sentence more than I'd have liked.
    >>  ............................................
    pt  Não. O que eu já disse é uma frase a mais do que eu gostaria.
    >>  ............................................
```


### Button `leave` — "I'll not pry."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.neighbour.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.neighbour.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.neighbour.respond.leave   [13 chars]
    en  I'll not pry.
    >>  ............................................
    pt  Não vou bisbilhotar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.village`
- …where the player's next choices will be: "What's it like living here?" | "What do you make of your neighbors?" | "Any rumors going around?" | "What do people think of me around here?" | "Is there anyone on your mind?" | "Anywhere here you're fond of?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.neighbour.leave
WHO    VILLAGER — what the player reads after pressing "I'll not pry."
       spoken on: conversations.topic.neighbour.respond, button `leave`
       leaves the player on: conversations.cat.village
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `neighbour.leave.terminal`: the villager accepts. Subject `neighbour.talk`, polarity `neutral`, ends conversation, outcome `None`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
```

```text
  dialogue.conversations.neighbour.leave/1   [57 chars]
    en  Good. There's little enough privacy in a place this size.
    >>  ............................................
    pt  Bom. Já tem pouca privacidade num lugar deste tamanho.
    >>  ............................................
  dialogue.conversations.neighbour.leave/2   [20 chars]
    en  Right you are, %1$s.
    >>  ............................................
    pt  Isso mesmo, %1$s.
    >>  ............................................
  dialogue.conversations.neighbour.leave/3   [52 chars]
    en  So I've found. Off you go, and mind what you repeat.
    >>  ............................................
    pt  Foi o que eu vi. Pode ir, e cuidado com o que você repete.
    >>  ............................................
```

---

