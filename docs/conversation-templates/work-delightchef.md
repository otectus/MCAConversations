# Work talk with a delightchef

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.delightchef.followup`](#conversations-scene-work-delightchef-followup)
- [`conversations.scene.work.delightchef.public_failure.failed.respond`](#conversations-scene-work-delightchef-public-failure-failed-respond)
- [`conversations.scene.work.delightchef.public_failure.remembered.respond`](#conversations-scene-work-delightchef-public-failure-remembered-respond)
- [`conversations.scene.work.delightchef.short_feast.blocked.respond`](#conversations-scene-work-delightchef-short-feast-blocked-respond)
- [`conversations.scene.work.delightchef.short_feast.succeeded.respond`](#conversations-scene-work-delightchef-short-feast-succeeded-respond)
- [`conversations.scene.work.delightchef.who_eats_last.active.respond`](#conversations-scene-work-delightchef-who-eats-last-active-respond)
- [`conversations.scene.work.delightchef.who_eats_last.succeeded.respond`](#conversations-scene-work-delightchef-who-eats-last-succeeded-respond)
- [`conversations.topic.work.delightchef.craft.respond`](#conversations-topic-work-delightchef-craft-respond)
- [`conversations.topic.work.delightchef.followup`](#conversations-topic-work-delightchef-followup)
- [`conversations.topic.work.delightchef.future.respond`](#conversations-topic-work-delightchef-future-respond)
- [`conversations.topic.work.delightchef.respond`](#conversations-topic-work-delightchef-respond)
- [`conversations.topic.work.delightchef.risk.respond`](#conversations-topic-work-delightchef-risk-respond)
- [`conversations.topic.work.delightchef.task.respond`](#conversations-topic-work-delightchef-task-respond)
- [`conversations.topic.work.delightchef.village.respond`](#conversations-topic-work-delightchef-village-respond)

---

## `conversations.scene.work.delightchef.followup`

**Reached from 10 route(s):** `conversations.scene.work.delightchef.public_failure.failed.respond` / `ask_what_went_wrong`; `conversations.scene.work.delightchef.public_failure.failed.respond` / `say_one_dish`; `conversations.scene.work.delightchef.public_failure.remembered.respond` / `note_the_telling`; `conversations.scene.work.delightchef.short_feast.blocked.respond` / `ask_about_stretching`; `conversations.scene.work.delightchef.short_feast.blocked.respond` / `offer_vegetables`; `conversations.scene.work.delightchef.short_feast.blocked.respond` / `advise_saying_so`; `conversations.scene.work.delightchef.short_feast.succeeded.respond` / `ask_about_the_spare`; `conversations.scene.work.delightchef.who_eats_last.active.respond` / `ask_what_she_thinks`; `conversations.scene.work.delightchef.who_eats_last.active.respond` / `back_the_bowl`; `conversations.scene.work.delightchef.who_eats_last.succeeded.respond` / `note_the_uncovering`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.delightchef.public_failure.failed.explained` — e.g. "Too hot, too fast, because I was behind and I made the one decision every cook makes when they are behind."
- `conversations.scene.work.delightchef.public_failure.failed.qualified` — e.g. "Thirty people saw the one and about four thousand meals happened where nobody was looking. I do know that. It does not help on the night."
- `conversations.scene.work.delightchef.public_failure.remembered.acknowledged` — e.g. "It is the only thing that makes a bad afternoon worth anything, and it took me a year to be able to do it without my voice going odd."
- `conversations.scene.work.delightchef.short_feast.blocked.accepted` — e.g. "Then %2$s is fed properly and nobody at that table has to notice anything at all, which is the entire objective."
- `conversations.scene.work.delightchef.short_feast.blocked.conceded` — e.g. "Out loud, at the start, before anybody has picked up a bowl. Said then it is a fact; said after, it is an excuse."
- `conversations.scene.work.delightchef.short_feast.blocked.explained` — e.g. "Barley and time. Both are free and both taste like they cost nothing, which is the whole difficulty with the trick."
- `conversations.scene.work.delightchef.short_feast.succeeded.answered` — e.g. "It went to a house that had not come, quietly, in a covered bowl, and I have never mentioned it to anybody until now."
- `conversations.scene.work.delightchef.who_eats_last.active.explained` — e.g. "Fair is everybody eating the same food. %2$s arriving last is not a rule, so working around it is not breaking one."
- `conversations.scene.work.delightchef.who_eats_last.active.steadied` — e.g. "I will, and I will stop covering it, because covering it made it look like a secret and it is not a secret."
- `conversations.scene.work.delightchef.who_eats_last.succeeded.acknowledged` — e.g. "A hidden kindness makes the person receiving it into a problem. An open one makes it a place at the table."


```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.delightchef.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.delightchef.followup   [23 chars]
    en  Anything more you need?
    >>  ............................................
    pt  Precisa de mais alguma coisa?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of a dish for a crowd?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.delightchef.*` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightchef.followup.ask_more` — accepted phrasings: "whats the hardest part of a dish for a crowd"; "what is the hardest part of a dish for a crowd"; "hardest thing about cooking for a crowd"
  - the message must contain one of: `hardest`, `dish`
  - scored words: `hardest`(1.8), `dish`(1.8), `whats`(0.8), `part`(0.8), `crowd`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightchef.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightchef.followup.ask_more   [46 chars]
    en  What's the hardest part of a dish for a crowd?
    >>  ............................................
    pt  Qual é a parte mais difícil de um prato para muita gente?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightchef.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightchef.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the dish you're known for?" | "Good service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of a dish for a crowd?"
       spoken on: conversations.scene.work.delightchef.followup, button `ask_more`
       leaves the player on: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.hard`: the villager explains. Subject `work.delightchef.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.delightchef.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.delightchef.hard/1   [90 chars]
    en  One ingredient arriving wrong and everyone pretending it hasn't. Silence is what ruins it.
    >>  ............................................
    pt  Um ingrediente chegando errado e todo mundo fingindo que não. O silêncio é o que estraga.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.hard/2   [74 chars]
    en  Me, usually. A kitchen takes its temper from whoever holds the pass, %1$s.
    >>  ............................................
    pt  Eu, geralmente. Uma cozinha pega o temperamento de quem segura a passagem, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the pot."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.delightchef.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightchef.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightchef.followup.leave   [26 chars]
    en  I'll leave you to the pot.
    >>  ............................................
    pt  Vou deixar você com a panela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the pot."
       spoken on: conversations.scene.work.delightchef.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.left`: the villager accepts. Subject `work.delightchef.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightchef.public_failure.failed.respond / leave; conversations.scene.work.delightchef.public_failure.remembered.respond / leave; conversations.scene.work.delightchef.short_feast.blocked.respond / leave; conversations.scene.work.delightchef.short_feast.succeeded.respond / leave; conversations.scene.work.delightchef.who_eats_last.active.respond / leave; conversations.scene.work.delightchef.who_eats_last.succeeded.respond / leave; conversations.topic.work.delightchef.craft.respond / leave; conversations.topic.work.delightchef.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.delightchef.leave/1   [31 chars]
    en  Yes. Out of my kitchen, kindly.
    >>  ............................................
    pt  Sim. Fora da minha cozinha, por gentileza.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.leave/2   [39 chars]
    en  Go. And take that plate with you, %1$s.
    >>  ............................................
    pt  Vá. E leve esse prato, %1$s.
    >>  ............................................
```

---


## `conversations.scene.work.delightchef.public_failure.failed.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.delightchef.public_failure.failed` — e.g. "I served %2$s in front of thirty people and there is no version of that afternoon where I get to explain the chemistry."


```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.public_failure.failed.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.delightchef.public_failure.failed.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.delightchef.public_failure.failed.respond   [10 chars]
    en  That dish.
    >>  ............................................
    pt  Aquele prato.
    >>  ............................................
```


### Button `ask_what_went_wrong` — "What actually went wrong?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightchef.public_failure.failed` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightchef.public_failure.failed.ask_what_went_wrong` — accepted phrasings: "what actually went wrong"; "what actually went wrong with it"; "what was the cause of that"
  - the message must contain one of: `wrong`, `cause`
  - scored words: `wrong`(1.8), `cause`(1.8), `actually`(0.8), `went`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.public_failure.failed.respond.ask_what_went_wrong
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightchef.public_failure.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightchef.public_failure.failed.respond.ask_what_went_wrong   [25 chars]
    en  What actually went wrong?
    >>  ............................................
    pt  O que de fato deu errado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightchef.a_dish_that_failed`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.delightchef.public_failure"}
- Then opens: `conversations.scene.work.delightchef.followup`
- …where the player's next choices will be: "What's the hardest part of a dish for a crowd?" | "I'll leave you to the pot."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.public_failure.failed.explained
WHO    VILLAGER — what the player reads after pressing "What actually went wrong?"
       spoken on: conversations.scene.work.delightchef.public_failure.failed.respond, button `ask_what_went_wrong`
       leaves the player on: conversations.scene.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.public_failure.failed.explained`: the villager explains. Subject `work.delightchef.a_dish_that_failed`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightchef.public_failure.failed.explained/1   [106 chars]
    en  Too hot, too fast, because I was behind and I made the one decision every cook makes when they are behind.
    >>  ............................................
    pt  Quente demais, rápido demais, porque eu estava atrasada e tomei a única decisão que todo cozinheiro toma quando está atrasado.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.public_failure.failed.explained/2   [134 chars]
    en  The milk was on the turn and I could smell it and I told myself I could not. That is the honest sentence and I have said it twice now.
    >>  ............................................
    pt  O leite estava virando e eu senti o cheiro e me convenci de que não. É a frase honesta e agora eu já disse duas vezes.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.public_failure.failed.explained/3   [131 chars]
    en  A cold pot in a cold room. It is a beginner's failure and I have been cooking for twenty years, and both of those are true at once.
    >>  ............................................
    pt  Panela fria numa sala fria. É um erro de iniciante e eu cozinho há vinte anos, e as duas coisas são verdade ao mesmo tempo.
    >>  ............................................
```


### Button `say_one_dish` — "One dish isn't your record."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.delightchef.public_failure.failed` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightchef.public_failure.failed.say_one_dish` — accepted phrasings: "one dish isnt your record"; "one dish is not your whole record"; "your record is twenty years long"
  - the message must contain one of: `record`, `dish`, `years`
  - scored words: `record`(1.8), `dish`(1.8), `years`(1.8), `one`(0.8), `isnt`(0.8), `whole`(0.8), `twenty`(0.8), `long`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.public_failure.failed.respond.say_one_dish
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightchef.public_failure.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightchef.public_failure.failed.respond.say_one_dish   [27 chars]
    en  One dish isn't your record.
    >>  ............................................
    pt  Um prato não é o seu histórico.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `work.delightchef.a_dish_that_failed`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.delightchef.public_failure"}
- Then opens: `conversations.scene.work.delightchef.followup`
- …where the player's next choices will be: "What's the hardest part of a dish for a crowd?" | "I'll leave you to the pot."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.public_failure.failed.qualified
WHO    VILLAGER — what the player reads after pressing "One dish isn't your record."
       spoken on: conversations.scene.work.delightchef.public_failure.failed.respond, button `say_one_dish`
       leaves the player on: conversations.scene.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.public_failure.failed.qualified`: the villager qualifys. Subject `work.delightchef.a_dish_that_failed`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightchef.public_failure.failed.qualified/1   [137 chars]
    en  Thirty people saw the one and about four thousand meals happened where nobody was looking. I do know that. It does not help on the night.
    >>  ............................................
    pt  Trinta pessoas viram um e umas quatro mil refeições aconteceram sem ninguém olhando. Eu sei disso. Não ajuda na mesma noite.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.public_failure.failed.qualified/2   [103 chars]
    en  Thank you. What stays is not the dish. It is thirty faces deciding to be nice to me at the same moment.
    >>  ............................................
    pt  Obrigada. O que fica não é o prato. São trinta rostos decidindo ser gentis comigo no mesmo instante.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.public_failure.failed.qualified/3   [113 chars]
    en  In a year it will be a story I tell about myself. It is four days old and it is still just a thing that happened.
    >>  ............................................
    pt  Em um ano vai ser uma história que eu conto sobre mim. Tem quatro dias e ainda é só uma coisa que aconteceu.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pot."

*stance family `exit` · tone `plain` · answers the beat(s) `work.delightchef.public_failure.failed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.public_failure.failed.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightchef.public_failure.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightchef.public_failure.failed.respond.leave   [33 chars]
    en  I'll let you get back to the pot.
    >>  ............................................
    pt  Vou deixar você voltar à panela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pot."
       spoken on: conversations.scene.work.delightchef.public_failure.failed.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.left`: the villager accepts. Subject `work.delightchef.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightchef.followup / leave; conversations.scene.work.delightchef.public_failure.remembered.respond / leave; conversations.scene.work.delightchef.short_feast.blocked.respond / leave; conversations.scene.work.delightchef.short_feast.succeeded.respond / leave; conversations.scene.work.delightchef.who_eats_last.active.respond / leave; conversations.scene.work.delightchef.who_eats_last.succeeded.respond / leave; conversations.topic.work.delightchef.craft.respond / leave; conversations.topic.work.delightchef.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightchef.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.delightchef.public_failure.remembered.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.delightchef.public_failure.remembered` — e.g. "I cook that dish once a season now, deliberately, on an ordinary Tuesday with nobody watching."


```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.public_failure.remembered.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.delightchef.public_failure.remembered.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.delightchef.public_failure.remembered.respond   [29 chars]
    en  That afternoon, looking back.
    >>  ............................................
    pt  Aquela tarde, olhando para trás.
    >>  ............................................
```


### Button `note_the_telling` — "Telling it makes it useful."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.delightchef.public_failure.remembered` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightchef.public_failure.remembered.note_the_telling` — accepted phrasings: "telling it makes it useful"; "telling it makes it useful"; "passing the story on helps them"
  - the message must contain one of: `telling`, `story`, `useful`
  - scored words: `telling`(1.8), `story`(1.8), `useful`(1.8), `makes`(0.8), `passing`(0.8), `helps`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.public_failure.remembered.respond.note_the_telling
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightchef.public_failure.remembered.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightchef.public_failure.remembered.respond.note_the_telling   [27 chars]
    en  Telling it makes it useful.
    >>  ............................................
    pt  Contar torna aquilo útil.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +2  _(recorded under topic `work.delightchef.a_dish_that_failed`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.delightchef.public_failure"}
- Then opens: `conversations.scene.work.delightchef.followup`
- …where the player's next choices will be: "What's the hardest part of a dish for a crowd?" | "I'll leave you to the pot."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.public_failure.remembered.acknowledged
WHO    VILLAGER — what the player reads after pressing "Telling it makes it useful."
       spoken on: conversations.scene.work.delightchef.public_failure.remembered.respond, button `note_the_telling`
       leaves the player on: conversations.scene.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.public_failure.remembered.acknowledged`: the villager accepts. Subject `work.delightchef.a_dish_that_failed`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightchef.public_failure.remembered.acknowledged/1   [133 chars]
    en  It is the only thing that makes a bad afternoon worth anything, and it took me a year to be able to do it without my voice going odd.
    >>  ............................................
    pt  É a única coisa que faz uma tarde ruim valer alguma coisa, e levei um ano para conseguir contar sem a voz sair estranha.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.public_failure.remembered.acknowledged/2   [128 chars]
    en  Thank you. Every cook in this valley has one and none of them will say so, and so every young cook thinks they are uniquely bad.
    >>  ............................................
    pt  Obrigada. Todo cozinheiro deste vale tem uma e nenhum admite, e por isso toda cozinheira jovem acha que é excepcionalmente ruim.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.public_failure.remembered.acknowledged/3   [117 chars]
    en  I tell them the smell of the milk. That is the useful detail. Everything else is just me being embarrassed in public.
    >>  ............................................
    pt  Eu conto sobre o cheiro do leite. É o detalhe útil. Todo o resto é só eu passando vergonha em público.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pot."

*stance family `exit` · tone `plain` · answers the beat(s) `work.delightchef.public_failure.remembered` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.public_failure.remembered.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightchef.public_failure.remembered.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightchef.public_failure.remembered.respond.leave   [33 chars]
    en  I'll let you get back to the pot.
    >>  ............................................
    pt  Vou deixar você voltar à panela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pot."
       spoken on: conversations.scene.work.delightchef.public_failure.remembered.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.left`: the villager accepts. Subject `work.delightchef.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightchef.followup / leave; conversations.scene.work.delightchef.public_failure.failed.respond / leave; conversations.scene.work.delightchef.short_feast.blocked.respond / leave; conversations.scene.work.delightchef.short_feast.succeeded.respond / leave; conversations.scene.work.delightchef.who_eats_last.active.respond / leave; conversations.scene.work.delightchef.who_eats_last.succeeded.respond / leave; conversations.topic.work.delightchef.craft.respond / leave; conversations.topic.work.delightchef.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightchef.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.delightchef.short_feast.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.delightchef.short_feast.blocked` — e.g. "%2$s is on Sunday and I have %3$s, and forty people are expecting to be fed properly."


```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.short_feast.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.delightchef.short_feast.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.delightchef.short_feast.blocked.respond   [10 chars]
    en  The feast.
    >>  ............................................
    pt  A festa.
    >>  ............................................
```


### Button `ask_about_stretching` — "How do you stretch a pot?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightchef.short_feast.blocked` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightchef.short_feast.blocked.ask_about_stretching` — accepted phrasings: "how do you stretch a pot"; "how do you stretch a pot"; "what do you do to make it go further"
  - the message must contain one of: `stretch`, `further`
  - scored words: `stretch`(1.8), `further`(1.8), `make`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.short_feast.blocked.respond.ask_about_stretching
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightchef.short_feast.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightchef.short_feast.blocked.respond.ask_about_stretching   [25 chars]
    en  How do you stretch a pot?
    >>  ............................................
    pt  Como se estica uma panela?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.delightchef.ingredients`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.delightchef.short_feast"}
- Then opens: `conversations.scene.work.delightchef.followup`
- …where the player's next choices will be: "What's the hardest part of a dish for a crowd?" | "I'll leave you to the pot."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.short_feast.blocked.explained
WHO    VILLAGER — what the player reads after pressing "How do you stretch a pot?"
       spoken on: conversations.scene.work.delightchef.short_feast.blocked.respond, button `ask_about_stretching`
       leaves the player on: conversations.scene.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.short_feast.blocked.explained`: the villager explains. Subject `work.delightchef.ingredients`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightchef.short_feast.blocked.explained/1   [115 chars]
    en  Barley and time. Both are free and both taste like they cost nothing, which is the whole difficulty with the trick.
    >>  ............................................
    pt  Cevada e tempo. Os dois são de graça e os dois têm gosto de não terem custado nada, e é essa a dificuldade do truque.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.short_feast.blocked.explained/2   [130 chars]
    en  You make one thing brilliant and everything else plain, so that people remember the brilliant one and forget how little there was.
    >>  ............................................
    pt  Você faz uma coisa brilhante e todo o resto simples, para as pessoas lembrarem da brilhante e esquecerem o quão pouco havia.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.short_feast.blocked.explained/3   [110 chars]
    en  Smaller bowls. It is honest and it is humiliating and it works, and I will do it before I water anything down.
    >>  ............................................
    pt  Tigelas menores. É honesto, é humilhante e funciona, e eu faço isso antes de aguar qualquer coisa.
    >>  ............................................
```


### Button `offer_vegetables` — "I'll bring carrots for the pot."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.delightchef.short_feast.blocked` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightchef.short_feast.blocked.offer_vegetables` — accepted phrasings: "ill bring carrots for the pot"; "i can bring carrots for the pot"; "let me fetch carrots for that"
  - the message must contain one of: `carrots`, `carrot`
  - scored words: `carrots`(1.8), `carrot`(1.8), `ill`(0.8), `bring`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.short_feast.blocked.respond.offer_vegetables
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightchef.short_feast.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightchef.short_feast.blocked.respond.offer_vegetables   [31 chars]
    en  I'll bring carrots for the pot.
    >>  ............................................
    pt  Vou trazer cenouras para a panela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.delightchef.feast.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +3  _(recorded under topic `work.delightchef.ingredients`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.short_feast", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.delightchef.short_feast", "obligation": "commitment:work.delightchef.bring_carrots"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.delightchef.bring_carrots"}
- Then opens: `conversations.scene.work.delightchef.followup`
- …where the player's next choices will be: "What's the hardest part of a dish for a crowd?" | "I'll leave you to the pot."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.short_feast.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring carrots for the pot."
       spoken on: conversations.scene.work.delightchef.short_feast.blocked.respond, button `offer_vegetables`
       leaves the player on: conversations.scene.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.short_feast.blocked.accepted`: the villager accepts. Subject `work.delightchef.ingredients`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightchef.short_feast.blocked.accepted/1   [112 chars]
    en  Then %2$s is fed properly and nobody at that table has to notice anything at all, which is the entire objective.
    >>  ............................................
    pt  Então %2$s come direito e ninguém naquela mesa precisa notar nada, que é o objetivo inteiro.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.short_feast.blocked.accepted/2   [115 chars]
    en  Bring them Saturday and I will have you at the top of the table, and I will refuse to discuss the seating with you.
    >>  ............................................
    pt  Traga no sábado e eu te ponho na cabeceira, e não vou discutir os lugares com você.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.short_feast.blocked.accepted/3   [128 chars]
    en  Yes. Eleven portions. I will tell nobody where they came from, because a feast where people are counting favours is not a feast.
    >>  ............................................
    pt  Sim. Onze porções. Não vou contar a ninguém de onde vieram, porque uma festa em que as pessoas contam favores não é festa.
    >>  ............................................
```


### Button `advise_saying_so` — "Tell them it's a lean year."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.delightchef.short_feast.blocked` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightchef.short_feast.blocked.advise_saying_so` — accepted phrasings: "tell them its a lean year"; "tell them it is a lean year"; "say plainly that the harvest was short"
  - the message must contain one of: `lean`, `harvest`, `plainly`
  - scored words: `lean`(1.8), `harvest`(1.8), `plainly`(1.8), `tell`(0.8), `its`(0.8), `year`(0.8), `say`(0.8), `short`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.short_feast.blocked.respond.advise_saying_so
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightchef.short_feast.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightchef.short_feast.blocked.respond.advise_saying_so   [27 chars]
    en  Tell them it's a lean year.
    >>  ............................................
    pt  Diga que é um ano magro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.delightchef.ingredients`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.delightchef.short_feast"}
- Then opens: `conversations.scene.work.delightchef.followup`
- …where the player's next choices will be: "What's the hardest part of a dish for a crowd?" | "I'll leave you to the pot."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.short_feast.blocked.conceded
WHO    VILLAGER — what the player reads after pressing "Tell them it's a lean year."
       spoken on: conversations.scene.work.delightchef.short_feast.blocked.respond, button `advise_saying_so`
       leaves the player on: conversations.scene.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.short_feast.blocked.conceded`: the villager accepts. Subject `work.delightchef.ingredients`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightchef.short_feast.blocked.conceded/1   [113 chars]
    en  Out loud, at the start, before anybody has picked up a bowl. Said then it is a fact; said after, it is an excuse.
    >>  ............................................
    pt  Em voz alta, no começo, antes de alguém pegar a tigela. Dito ali é um fato; dito depois, é desculpa.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.short_feast.blocked.conceded/2   [146 chars]
    en  You are right and I hate it, because a feast is the one afternoon a year when this village gets to pretend, and I would be the one who stopped it.
    >>  ............................................
    pt  Você tem razão e eu detesto, porque a festa é a única tarde do ano em que esta vila pode fingir, e eu seria quem acabou com isso.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.short_feast.blocked.conceded/3   [122 chars]
    en  I will say it and then I will make the barley extraordinary, and those two together are the honest version of a good year.
    >>  ............................................
    pt  Vou dizer e depois vou deixar a cevada extraordinária, e essas duas coisas juntas são a versão honesta de um ano bom.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pot."

*stance family `exit` · tone `plain` · answers the beat(s) `work.delightchef.short_feast.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.short_feast.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightchef.short_feast.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightchef.short_feast.blocked.respond.leave   [33 chars]
    en  I'll let you get back to the pot.
    >>  ............................................
    pt  Vou deixar você voltar à panela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pot."
       spoken on: conversations.scene.work.delightchef.short_feast.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.left`: the villager accepts. Subject `work.delightchef.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightchef.followup / leave; conversations.scene.work.delightchef.public_failure.failed.respond / leave; conversations.scene.work.delightchef.public_failure.remembered.respond / leave; conversations.scene.work.delightchef.short_feast.succeeded.respond / leave; conversations.scene.work.delightchef.who_eats_last.active.respond / leave; conversations.scene.work.delightchef.who_eats_last.succeeded.respond / leave; conversations.topic.work.delightchef.craft.respond / leave; conversations.topic.work.delightchef.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightchef.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.delightchef.short_feast.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.delightchef.short_feast.succeeded` — e.g. "%2$s went well. Forty-one fed, one spare portion, and I have not slept that well since the spring."


```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.short_feast.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.delightchef.short_feast.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.delightchef.short_feast.succeeded.respond   [17 chars]
    en  The feast, after.
    >>  ............................................
    pt  A festa, depois.
    >>  ............................................
```


### Button `ask_about_the_spare` — "Who got the spare portion?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.delightchef.short_feast.succeeded` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightchef.short_feast.succeeded.ask_about_the_spare` — accepted phrasings: "who got the spare portion"; "who got the spare portion"; "what happened to the extra bowl"
  - the message must contain one of: `spare`, `extra`, `portion`
  - scored words: `spare`(1.8), `extra`(1.8), `portion`(1.8), `who`(0.8), `got`(0.8), `happened`(0.8), `bowl`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.short_feast.succeeded.respond.ask_about_the_spare
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightchef.short_feast.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightchef.short_feast.succeeded.respond.ask_about_the_spare   [26 chars]
    en  Who got the spare portion?
    >>  ............................................
    pt  Quem ficou com a porção que sobrou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, warmth +1  _(recorded under topic `work.delightchef.ingredients`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.delightchef.short_feast"}
- Then opens: `conversations.scene.work.delightchef.followup`
- …where the player's next choices will be: "What's the hardest part of a dish for a crowd?" | "I'll leave you to the pot."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.short_feast.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "Who got the spare portion?"
       spoken on: conversations.scene.work.delightchef.short_feast.succeeded.respond, button `ask_about_the_spare`
       leaves the player on: conversations.scene.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.short_feast.succeeded.answered`: the villager explains. Subject `work.delightchef.ingredients`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightchef.short_feast.succeeded.answered/1   [117 chars]
    en  It went to a house that had not come, quietly, in a covered bowl, and I have never mentioned it to anybody until now.
    >>  ............................................
    pt  Foi para uma casa que não veio, em silêncio, numa tigela tampada, e eu nunca contei isso a ninguém até agora.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.short_feast.succeeded.answered/2   [106 chars]
    en  I ate it. I always cook one spare for myself and I always pretend it is a margin of error, and it is both.
    >>  ............................................
    pt  Eu comi. Sempre faço uma porção extra para mim e sempre finjo que é margem de erro, e é as duas coisas.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.short_feast.succeeded.answered/3   [126 chars]
    en  The youngest field hand, who had already eaten and was too shy to ask twice. He is nineteen and he works like a man of thirty.
    >>  ............................................
    pt  O trabalhador mais novo, que já tinha comido e era tímido demais para pedir de novo. Tem dezenove anos e trabalha como um de trinta.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pot."

*stance family `exit` · tone `plain` · answers the beat(s) `work.delightchef.short_feast.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.short_feast.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightchef.short_feast.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightchef.short_feast.succeeded.respond.leave   [33 chars]
    en  I'll let you get back to the pot.
    >>  ............................................
    pt  Vou deixar você voltar à panela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pot."
       spoken on: conversations.scene.work.delightchef.short_feast.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.left`: the villager accepts. Subject `work.delightchef.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightchef.followup / leave; conversations.scene.work.delightchef.public_failure.failed.respond / leave; conversations.scene.work.delightchef.public_failure.remembered.respond / leave; conversations.scene.work.delightchef.short_feast.blocked.respond / leave; conversations.scene.work.delightchef.who_eats_last.active.respond / leave; conversations.scene.work.delightchef.who_eats_last.succeeded.respond / leave; conversations.topic.work.delightchef.craft.respond / leave; conversations.topic.work.delightchef.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightchef.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.delightchef.who_eats_last.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.delightchef.who_eats_last.active` — e.g. "%2$s eat last at every gathering and nobody decided that, which is exactly why nobody will change it."


```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.who_eats_last.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.delightchef.who_eats_last.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.delightchef.who_eats_last.active.respond   [23 chars]
    en  The order of the table.
    >>  ............................................
    pt  A ordem da mesa.
    >>  ............................................
```


### Button `ask_what_she_thinks` — "Is it fair?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightchef.who_eats_last.active` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightchef.who_eats_last.active.ask_what_she_thinks` — accepted phrasings: "is it fair"; "is holding a bowl back fair"; "what is fair about the order"
  - the message must contain one of: `fair`, `order`
  - scored words: `fair`(1.8), `order`(1.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.who_eats_last.active.respond.ask_what_she_thinks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightchef.who_eats_last.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightchef.who_eats_last.active.respond.ask_what_she_thinks   [11 chars]
    en  Is it fair?
    >>  ............................................
    pt  É justo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightchef.the_crowd`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.delightchef.who_eats_last"}
- Then opens: `conversations.scene.work.delightchef.followup`
- …where the player's next choices will be: "What's the hardest part of a dish for a crowd?" | "I'll leave you to the pot."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.who_eats_last.active.explained
WHO    VILLAGER — what the player reads after pressing "Is it fair?"
       spoken on: conversations.scene.work.delightchef.who_eats_last.active.respond, button `ask_what_she_thinks`
       leaves the player on: conversations.scene.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.who_eats_last.active.explained`: the villager explains. Subject `work.delightchef.the_crowd`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightchef.who_eats_last.active.explained/1   [115 chars]
    en  Fair is everybody eating the same food. %2$s arriving last is not a rule, so working around it is not breaking one.
    >>  ............................................
    pt  Justo é todo mundo comer a mesma comida. %2$s chegarem por último não é regra, então contornar isso não é quebrar nenhuma.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.who_eats_last.active.explained/2   [141 chars]
    en  It is a small correction to an accident. If somebody wants to argue that the accident is fairer, I will listen and I will keep the bowl back.
    >>  ............................................
    pt  É uma correção pequena de um acaso. Se alguém quiser argumentar que o acaso é mais justo, eu escuto e continuo guardando a tigela.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.who_eats_last.active.explained/3   [127 chars]
    en  The person who asked eats first every time and has done for eleven years, and has never once thought of that as an arrangement.
    >>  ............................................
    pt  Quem perguntou come primeiro toda vez e faz isso há onze anos, e nunca pensou nisso como um arranjo.
    >>  ............................................
```


### Button `back_the_bowl` — "Keep holding the bowl back."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.delightchef.who_eats_last.active` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightchef.who_eats_last.active.back_the_bowl` — accepted phrasings: "keep holding the bowl back"; "keep holding the bowl back"; "carry on saving a portion for them"
  - the message must contain one of: `bowl`, `saving`, `portion`
  - scored words: `bowl`(1.8), `saving`(1.8), `portion`(1.8), `keep`(0.8), `carry`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.who_eats_last.active.respond.back_the_bowl
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightchef.who_eats_last.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightchef.who_eats_last.active.respond.back_the_bowl   [27 chars]
    en  Keep holding the bowl back.
    >>  ............................................
    pt  Continue guardando a tigela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.delightchef.table.backed`, budget `standard`, replay policy `once`
- Does: disposition — respect +3, warmth +3  _(recorded under topic `work.delightchef.the_crowd`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.delightchef.who_eats_last"}
- Then opens: `conversations.scene.work.delightchef.followup`
- …where the player's next choices will be: "What's the hardest part of a dish for a crowd?" | "I'll leave you to the pot."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.who_eats_last.active.steadied
WHO    VILLAGER — what the player reads after pressing "Keep holding the bowl back."
       spoken on: conversations.scene.work.delightchef.who_eats_last.active.respond, button `back_the_bowl`
       leaves the player on: conversations.scene.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.who_eats_last.active.steadied`: the villager accepts. Subject `work.delightchef.the_crowd`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightchef.who_eats_last.active.steadied/1   [107 chars]
    en  I will, and I will stop covering it, because covering it made it look like a secret and it is not a secret.
    >>  ............................................
    pt  Vou, e vou parar de tampar, porque tampar fazia aquilo parecer segredo, e não é segredo.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.who_eats_last.active.steadied/2   [98 chars]
    en  Yes. And next time somebody asks whether it is fair I am going to ask them what time they arrived.
    >>  ............................................
    pt  Sim. E da próxima vez que alguém perguntar se é justo, eu vou perguntar a que horas essa pessoa chegou.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.who_eats_last.active.steadied/3   [144 chars]
    en  Thank you. I have been doing it for a season and quietly wondering whether I was the one being unfair, which is what happens when you act alone.
    >>  ............................................
    pt  Obrigada. Faço isso há uma estação e ando em silêncio me perguntando se a injusta era eu, que é o que acontece quando se age sozinha.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pot."

*stance family `exit` · tone `plain` · answers the beat(s) `work.delightchef.who_eats_last.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.who_eats_last.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightchef.who_eats_last.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightchef.who_eats_last.active.respond.leave   [33 chars]
    en  I'll let you get back to the pot.
    >>  ............................................
    pt  Vou deixar você voltar à panela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pot."
       spoken on: conversations.scene.work.delightchef.who_eats_last.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.left`: the villager accepts. Subject `work.delightchef.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightchef.followup / leave; conversations.scene.work.delightchef.public_failure.failed.respond / leave; conversations.scene.work.delightchef.public_failure.remembered.respond / leave; conversations.scene.work.delightchef.short_feast.blocked.respond / leave; conversations.scene.work.delightchef.short_feast.succeeded.respond / leave; conversations.scene.work.delightchef.who_eats_last.succeeded.respond / leave; conversations.topic.work.delightchef.craft.respond / leave; conversations.topic.work.delightchef.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightchef.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.delightchef.who_eats_last.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.delightchef.who_eats_last.succeeded` — e.g. "The bowl sits on the end of the table now, uncovered, with %2$s written on a card, and nobody has said a word about it."


```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.who_eats_last.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.delightchef.who_eats_last.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.delightchef.who_eats_last.succeeded.respond   [17 chars]
    en  The table, since.
    >>  ............................................
    pt  A mesa, depois disso.
    >>  ............................................
```


### Button `note_the_uncovering` — "Doing it openly changed it."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.delightchef.who_eats_last.succeeded` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.delightchef.who_eats_last.succeeded.note_the_uncovering` — accepted phrasings: "doing it openly changed it"; "doing it openly changed it"; "the open bowl changed things"
  - the message must contain one of: `openly`, `open`, `changed`
  - scored words: `openly`(1.8), `open`(1.8), `changed`(1.8), `doing`(0.8), `bowl`(0.8), `things`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.who_eats_last.succeeded.respond.note_the_uncovering
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightchef.who_eats_last.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightchef.who_eats_last.succeeded.respond.note_the_uncovering   [27 chars]
    en  Doing it openly changed it.
    >>  ............................................
    pt  Fazer abertamente mudou tudo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +3  _(recorded under topic `work.delightchef.the_crowd`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.delightchef.who_eats_last"}
- Then opens: `conversations.scene.work.delightchef.followup`
- …where the player's next choices will be: "What's the hardest part of a dish for a crowd?" | "I'll leave you to the pot."

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.who_eats_last.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Doing it openly changed it."
       spoken on: conversations.scene.work.delightchef.who_eats_last.succeeded.respond, button `note_the_uncovering`
       leaves the player on: conversations.scene.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.who_eats_last.succeeded.acknowledged`: the villager accepts. Subject `work.delightchef.the_crowd`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.delightchef.who_eats_last.succeeded.acknowledged/1   [106 chars]
    en  A hidden kindness makes the person receiving it into a problem. An open one makes it a place at the table.
    >>  ............................................
    pt  Uma gentileza escondida transforma quem recebe em problema. Uma aberta transforma em lugar à mesa.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.who_eats_last.succeeded.acknowledged/2   [116 chars]
    en  Thank you. I had thought discretion was the kind part. Discretion was me being uncomfortable and calling it manners.
    >>  ............................................
    pt  Obrigada. Eu achava que discrição era a parte gentil. Discrição era eu desconfortável chamando isso de boas maneiras.
    >>  ............................................
  dialogue.conversations.scene.work.delightchef.who_eats_last.succeeded.acknowledged/3   [108 chars]
    en  The card was the whole thing. Four words on a card, and a season of quiet worrying was over in an afternoon.
    >>  ............................................
    pt  O cartão era a coisa toda. Quatro palavras num cartão, e uma estação de preocupação silenciosa acabou numa tarde.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pot."

*stance family `exit` · tone `plain` · answers the beat(s) `work.delightchef.who_eats_last.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.delightchef.who_eats_last.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.delightchef.who_eats_last.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.delightchef.who_eats_last.succeeded.respond.leave   [33 chars]
    en  I'll let you get back to the pot.
    >>  ............................................
    pt  Vou deixar você voltar à panela.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pot."
       spoken on: conversations.scene.work.delightchef.who_eats_last.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.left`: the villager accepts. Subject `work.delightchef.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightchef.followup / leave; conversations.scene.work.delightchef.public_failure.failed.respond / leave; conversations.scene.work.delightchef.public_failure.remembered.respond / leave; conversations.scene.work.delightchef.short_feast.blocked.respond / leave; conversations.scene.work.delightchef.short_feast.succeeded.respond / leave; conversations.scene.work.delightchef.who_eats_last.active.respond / leave; conversations.topic.work.delightchef.craft.respond / leave; conversations.topic.work.delightchef.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightchef.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.delightchef.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.delightchef.craft` — e.g. "Timing. Everything else can be taught in a fortnight and timing takes a decade and can't be written down."


```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.delightchef.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.delightchef.craft.respond   [28 chars]
    en  That's the whole difficulty.
    >>  ............................................
    pt  É toda a dificuldade.
    >>  ............................................
```


### Button `ask_alone` — "What's different about cooking alone?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightchef.craft` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightchef.craft.ask_alone` — accepted phrasings: "what's different about cooking alone"
  - the message must contain one of: `alone`, `different`, `solo`
  - scored words: `alone`(1.5), `different`(1.0), `solo`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.craft.respond.ask_alone
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.craft.respond.ask_alone   [37 chars]
    en  What's different about cooking alone?
    >>  ............................................
    pt  O que muda cozinhando sozinho?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightchef.craft.ask_alone`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightchef.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the dish you're known for?" | "Good service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.craft.ask_alone
WHO    VILLAGER — what the player reads after pressing "What's different about cooking alone?"
       spoken on: conversations.topic.work.delightchef.craft.respond, button `ask_alone`
       leaves the player on: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.craft.ask_alone`: the villager explains. Subject `work.delightchef.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightchef.craft.ask_alone/1   [89 chars]
    en  Nobody catches your mistake at the moment it's still a mistake. After that it's the meal.
    >>  ............................................
    pt  Ninguém pega seu erro no momento em que ainda é um erro. Depois disso é a refeição.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.craft.ask_alone/2   [97 chars]
    en  You do everything in an order you invented and nobody argues with the order, %1$s. That's a loss.
    >>  ............................................
    pt  Você faz tudo numa ordem que inventou e ninguém discute a ordem, %1$s. Isso é uma perda.
    >>  ............................................
```


### Button `admire` — "A decade for timing sounds about right, honestly."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.delightchef.craft` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightchef.craft.admire` — accepted phrasings: "a decade for timing sounds about right, honestly"
  - the message must contain one of: `decade`, `timing`
  - scored words: `decade`(1.5), `timing`(1.2), `right`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.craft.respond.admire   [49 chars]
    en  A decade for timing sounds about right, honestly.
    >>  ............................................
    pt  Uma década pro tempo parece certo, sinceramente.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.delightchef.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.delightchef.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightchef.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the dish you're known for?" | "Good service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.craft.admire
WHO    VILLAGER — what the player reads after pressing "A decade for timing sounds about right, honestly."
       spoken on: conversations.topic.work.delightchef.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.craft.admire`: the villager accepts. Subject `work.delightchef.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightchef.craft.admire/1   [80 chars]
    en  It does to anybody who's cooked. It sounds absurd to everybody who's only eaten.
    >>  ............................................
    pt  Parece pra quem já cozinhou. Soa absurdo pra quem só comeu.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.craft.admire/2   [70 chars]
    en  You're the first person to hear that number and not laugh at it, %1$s.
    >>  ............................................
    pt  Você é a primeira pessoa a ouvir esse número e não rir, %1$s.
    >>  ............................................
```


### Button `ask_nine` — "Do you miss the kitchen with nine people?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightchef.craft` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightchef.craft.ask_nine` — accepted phrasings: "do you miss the kitchen with nine people"
  - the message must contain one of: `miss`, `nine`, `kitchen`
  - scored words: `miss`(1.5), `nine`(1.2), `kitchen`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.craft.respond.ask_nine
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.craft.respond.ask_nine   [41 chars]
    en  Do you miss the kitchen with nine people?
    >>  ............................................
    pt  Você sente falta da cozinha com nove?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightchef.craft.ask_nine`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightchef.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the dish you're known for?" | "Good service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.craft.ask_nine
WHO    VILLAGER — what the player reads after pressing "Do you miss the kitchen with nine people?"
       spoken on: conversations.topic.work.delightchef.craft.respond, button `ask_nine`
       leaves the player on: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.craft.ask_nine`: the villager explains. Subject `work.delightchef.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightchef.craft.ask_nine/1   [86 chars]
    en  Every service. It was loud and unkind and I have never cooked better than I did in it.
    >>  ............................................
    pt  Todo serviço. Era barulhenta e ríspida e eu nunca cozinhei melhor do que nela.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.craft.ask_nine/2   [87 chars]
    en  I miss being corrected, %1$s. That is a strange thing to miss and I miss it constantly.
    >>  ............................................
    pt  Sinto falta de ser corrigido, %1$s. É estranho sentir falta disso e eu sinto o tempo todo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to service."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.delightchef.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.craft.respond.leave   [33 chars]
    en  I'll let you get back to service.
    >>  ............................................
    pt  Vou deixar você voltar ao serviço.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to service."
       spoken on: conversations.topic.work.delightchef.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.left`: the villager accepts. Subject `work.delightchef.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightchef.followup / leave; conversations.scene.work.delightchef.public_failure.failed.respond / leave; conversations.scene.work.delightchef.public_failure.remembered.respond / leave; conversations.scene.work.delightchef.short_feast.blocked.respond / leave; conversations.scene.work.delightchef.short_feast.succeeded.respond / leave; conversations.scene.work.delightchef.who_eats_last.active.respond / leave; conversations.scene.work.delightchef.who_eats_last.succeeded.respond / leave; conversations.topic.work.delightchef.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightchef.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.delightchef.followup`

**Reached from 20 route(s):** `conversations.scene.work.delightchef.followup` / `ask_more`; `conversations.topic.work.delightchef.craft.respond` / `ask_alone`; `conversations.topic.work.delightchef.craft.respond` / `admire`; `conversations.topic.work.delightchef.craft.respond` / `ask_nine`; `conversations.topic.work.delightchef.future.respond` / `ask_timings`; `conversations.topic.work.delightchef.future.respond` / `encourage`; `conversations.topic.work.delightchef.future.respond` / `ask_hands`; `conversations.topic.work.delightchef.respond` / `ask_hard`; `conversations.topic.work.delightchef.respond` / `value`; `conversations.topic.work.delightchef.respond` / `challenge`; `conversations.topic.work.delightchef.respond` / `challenge`; `conversations.topic.work.delightchef.risk.respond` / `ask_ill` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.delightchef.challenge.landed` — e.g. "Some of it is show. The show is how you get eleven people to move as one."
- `conversations.work.prof.delightchef.challenge.stung` — e.g. "...Come and stand at the pass for one evening and say that afterwards."
- `conversations.work.prof.delightchef.craft.admire` — e.g. "It does to anybody who's cooked. It sounds absurd to everybody who's only eaten."
- `conversations.work.prof.delightchef.craft.ask_alone` — e.g. "Nobody catches your mistake at the moment it's still a mistake. After that it's the meal."
- `conversations.work.prof.delightchef.craft.ask_nine` — e.g. "Every service. It was loud and unkind and I have never cooked better than I did in it."
- `conversations.work.prof.delightchef.future.ask_hands` — e.g. "Two have. Both wanted to cook and neither wanted to skim a stock for six hours, which is the actual offer."
- `conversations.work.prof.delightchef.future.ask_timings` — e.g. "Because the answer is always 'until it looks like that', and 'that' is a thing you have to have seen."
- `conversations.work.prof.delightchef.future.encourage` — e.g. "...Somebody can correct them. That's how I learned. I'd forgotten that's how I learned."
- `conversations.work.prof.delightchef.hard` — e.g. "One ingredient arriving wrong and everyone pretending it hasn't. Silence is what ruins it."
- `conversations.work.prof.delightchef.risk.ask_ill` — e.g. "It has happened. I cooked with a fever and it was the worst food I've ever put on a table."
- `conversations.work.prof.delightchef.risk.ask_pot` — e.g. "Once, at a wedding, and it was mushrooms, and I have not served a mushroom since."
- `conversations.work.prof.delightchef.risk.sympathise` — e.g. "...It says I couldn't bear to be the reason. Which is not the same as it being wise."
- `conversations.work.prof.delightchef.task.ask_absence` — e.g. "Instantly, and they'd blame the meat. A stock goes uncomplimented and everybody eats it."
- `conversations.work.prof.delightchef.task.ask_backwards` — e.g. "Always. The bread is the only thing that can't wait and everything else negotiates around it."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.delightchef.followup   [26 chars]
    en  That's the pass, in short.
    >>  ............................................
    pt  É a passagem, resumindo.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.delightchef.challenge.landed`, `work.delightchef.challenge.stung`, `work.delightchef.craft.admire`, `work.delightchef.craft.ask_alone`, `work.delightchef.craft.ask_nine`, `work.delightchef.future.ask_hands`, `work.delightchef.future.ask_timings`, `work.delightchef.future.encourage`, `work.delightchef.hard`, `work.delightchef.risk.ask_ill`, `work.delightchef.risk.ask_pot`, `work.delightchef.risk.sympathise`, `work.delightchef.task.ask_absence`, `work.delightchef.task.ask_backwards`, `work.delightchef.task.offer_hands`, `work.delightchef.value`, `work.delightchef.village.ask_florist`, `work.delightchef.village.ask_wake`, `work.delightchef.village.say_thanks` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.delightchef.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `shouting`
  - scored words: `thought`(1.2), `shouting`(1.5), `kitchen`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.delightchef.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.delightchef.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.delightchef.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.delightchef.thanks`: the villager accepts. Subject `work.delightchef.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightchef.thanks/1   [73 chars]
    en  Nobody outside a kitchen does. It looks like temper from the dining side.
    >>  ............................................
    pt  Ninguém de fora de uma cozinha pensa. Do lado do salão parece só temperamento.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.thanks/2   [70 chars]
    en  You listened past the shouting, %1$s. The shouting is the least of it.
    >>  ............................................
    pt  Você ouviu além da gritaria, %1$s. A gritaria é o de menos.
    >>  ............................................
```


### Button `ask_more` — "What's the dish you're known for?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightchef.challenge.landed`, `work.delightchef.challenge.stung`, `work.delightchef.craft.admire`, `work.delightchef.craft.ask_alone`, `work.delightchef.craft.ask_nine`, `work.delightchef.future.ask_hands`, `work.delightchef.future.ask_timings`, `work.delightchef.future.encourage`, `work.delightchef.hard`, `work.delightchef.risk.ask_ill`, `work.delightchef.risk.ask_pot`, `work.delightchef.risk.sympathise`, `work.delightchef.task.ask_absence`, `work.delightchef.task.ask_backwards`, `work.delightchef.task.offer_hands`, `work.delightchef.value`, `work.delightchef.village.ask_florist`, `work.delightchef.village.ask_wake`, `work.delightchef.village.say_thanks` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.delightchef.more` — accepted phrasings: "what's the dish you're known for"
  - the message must contain one of: `dish`, `known`, `signature`
  - scored words: `dish`(1.5), `known`(1.2), `signature`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.followup.ask_more   [33 chars]
    en  What's the dish you're known for?
    >>  ............................................
    pt  Qual é o prato pelo qual você é conhecido?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.delightchef.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.more
WHO    VILLAGER — what the player reads after pressing "What's the dish you're known for?"
       spoken on: conversations.topic.work.delightchef.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.delightchef.more`: the villager discloses. Subject `work.delightchef.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightchef.more/1   [79 chars]
    en  A pie nobody can reproduce because the trick is in the resting, not the recipe.
    >>  ............................................
    pt  Uma torta que ninguém reproduz porque o segredo está no descanso, não na receita.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.more/2   [91 chars]
    en  The soup. It is an insult to call it soup and I have not found a better word in nine years.
    >>  ............................................
    pt  A sopa. Chamar de sopa é um insulto e eu não achei palavra melhor em nove anos.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie nobody can copy. It's the only thing I've made that felt like mine rather than the village's.
    >>  ............................................
    pt  Uma torta que ninguém copia. É a única coisa que eu fiz que pareceu minha e não do vilarejo.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.delightchef.more/2
    en  A second cook. If I'm ill in December, December is different for everybody, and that frightens me.
    >>  ............................................
    pt  Um segundo cozinheiro. Se eu adoecer em dezembro, dezembro é outro pra todos, e isso me assusta.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie that rests properly. The resting takes a day and there is no honest way to shorten it.
    >>  ............................................
    pt  Uma torta que descansa direito. O descanso leva um dia e não há jeito honesto de encurtar.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.delightchef.more/2
    en  A second pair of hands, eventually. Kitchens fill up in their own time.
    >>  ............................................
    pt  Um segundo par de mãos, uma hora. Cozinhas se enchem no tempo delas.
    >>  ............................................
  confident.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie nobody can reproduce, because the trick is in the resting and not the recipe.
    >>  ............................................
    pt  Uma torta que ninguém reproduz, porque o truque está no descanso e não na receita.
    >>  ............................................
  confident.dialogue.conversations.work.prof.delightchef.more/2
    en  A second pair of hands. Then December is a month instead of a wager.
    >>  ............................................
    pt  Um segundo par de mãos. Aí dezembro é um mês em vez de uma aposta.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie nobody can reproduce, because the trick is in the resting and not the recipe.
    >>  ............................................
    pt  Uma torta que ninguém reproduz, porque o truque está no descanso e não na receita.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.delightchef.more/2
    en  A second pair of hands. Then December is a month instead of a wager.
    >>  ............................................
    pt  Um segundo par de mãos. Aí dezembro é um mês em vez de uma aposta.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie nobody can reproduce. Come at six one evening and I'll show you the resting, since it's you.
    >>  ............................................
    pt  Uma torta que ninguém reproduz. Venha às seis numa noite e eu mostro o descanso, já que é você.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.delightchef.more/2
    en  Somebody in the kitchen with me. Anybody. It's very quiet in there for eleven years.
    >>  ............................................
    pt  Alguém na cozinha comigo. Qualquer um. É muito quieto lá dentro há onze anos.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie nobody can reproduce. Come at six one evening and I'll show you the resting, since it's you.
    >>  ............................................
    pt  Uma torta que ninguém reproduz. Venha às seis numa noite e eu mostro o descanso, já que é você.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.delightchef.more/2
    en  Somebody in the kitchen with me. Anybody. It's very quiet in there for eleven years.
    >>  ............................................
    pt  Alguém na cozinha comigo. Qualquer um. É muito quieto lá dentro há onze anos.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie nobody can reproduce. Come at six one evening and I'll show you the resting, since it's you.
    >>  ............................................
    pt  Uma torta que ninguém reproduz. Venha às seis numa noite e eu mostro o descanso, já que é você.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.delightchef.more/2
    en  Somebody in the kitchen with me. Anybody. It's very quiet in there for eleven years.
    >>  ............................................
    pt  Alguém na cozinha comigo. Qualquer um. É muito quieto lá dentro há onze anos.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie nobody can copy. It's the only thing I've made that felt like mine rather than the village's.
    >>  ............................................
    pt  Uma torta que ninguém copia. É a única coisa que eu fiz que pareceu minha e não do vilarejo.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.delightchef.more/2
    en  A second cook. If I'm ill in December, December is different for everybody, and that frightens me.
    >>  ............................................
    pt  Um segundo cozinheiro. Se eu adoecer em dezembro, dezembro é outro pra todos, e isso me assusta.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie nobody can reproduce, because the trick is in the resting and not the recipe.
    >>  ............................................
    pt  Uma torta que ninguém reproduz, porque o truque está no descanso e não na receita.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.delightchef.more/2
    en  A second pair of hands. Then December is a month instead of a wager.
    >>  ............................................
    pt  Um segundo par de mãos. Aí dezembro é um mês em vez de uma aposta.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie nobody can reproduce, because the trick is in the resting and not the recipe.
    >>  ............................................
    pt  Uma torta que ninguém reproduz, porque o truque está no descanso e não na receita.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.delightchef.more/2
    en  A second pair of hands. Then December is a month instead of a wager.
    >>  ............................................
    pt  Um segundo par de mãos. Aí dezembro é um mês em vez de uma aposta.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie nobody can reproduce. The trick is the resting. Nobody ever asks about the resting.
    >>  ............................................
    pt  Uma torta que ninguém reproduz. O truque é o descanso. Ninguém pergunta do descanso.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.delightchef.more/2
    en  A second pair of hands, for the skimming. Six hours of it, every feast.
    >>  ............................................
    pt  Um segundo par de mãos, pra escumar. Seis horas disso, em toda festa.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie that rests properly. The resting takes a day and there is no honest way to shorten it.
    >>  ............................................
    pt  Uma torta que descansa direito. O descanso leva um dia e não há jeito honesto de encurtar.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.delightchef.more/2
    en  A second pair of hands, eventually. Kitchens fill up in their own time.
    >>  ............................................
    pt  Um segundo par de mãos, uma hora. Cozinhas se enchem no tempo delas.
    >>  ............................................
  odd.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie nobody can reproduce. The trick is the resting. Nobody ever asks about the resting.
    >>  ............................................
    pt  Uma torta que ninguém reproduz. O truque é o descanso. Ninguém pergunta do descanso.
    >>  ............................................
  odd.dialogue.conversations.work.prof.delightchef.more/2
    en  A second pair of hands, for the skimming. Six hours of it, every feast.
    >>  ............................................
    pt  Um segundo par de mãos, pra escumar. Seis horas disso, em toda festa.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie that rests properly. The resting takes a day and there is no honest way to shorten it.
    >>  ............................................
    pt  Uma torta que descansa direito. O descanso leva um dia e não há jeito honesto de encurtar.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.delightchef.more/2
    en  A second pair of hands, eventually. Kitchens fill up in their own time.
    >>  ............................................
    pt  Um segundo par de mãos, uma hora. Cozinhas se enchem no tempo delas.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie nobody can copy! The trick is the resting, and I'll never say that where the baker can hear.
    >>  ............................................
    pt  Uma torta que ninguém copia! O truque é o descanso, e eu nunca digo isso perto do padeiro.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.delightchef.more/2
    en  A second pair of hands, and then December stops being a competitive sport.
    >>  ............................................
    pt  Um segundo par de mãos, e aí dezembro deixa de ser esporte competitivo.
    >>  ............................................
  playful.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie nobody can copy! The trick is the resting, and I'll never say that where the baker can hear.
    >>  ............................................
    pt  Uma torta que ninguém copia! O truque é o descanso, e eu nunca digo isso perto do padeiro.
    >>  ............................................
  playful.dialogue.conversations.work.prof.delightchef.more/2
    en  A second pair of hands, and then December stops being a competitive sport.
    >>  ............................................
    pt  Um segundo par de mãos, e aí dezembro deixa de ser esporte competitivo.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie that rests properly. The resting takes a day and there is no honest way to shorten it.
    >>  ............................................
    pt  Uma torta que descansa direito. O descanso leva um dia e não há jeito honesto de encurtar.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.delightchef.more/2
    en  A second pair of hands, eventually. Kitchens fill up in their own time.
    >>  ............................................
    pt  Um segundo par de mãos, uma hora. Cozinhas se enchem no tempo delas.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie nobody can copy. It's the only thing I've made that felt like mine rather than the village's.
    >>  ............................................
    pt  Uma torta que ninguém copia. É a única coisa que eu fiz que pareceu minha e não do vilarejo.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.delightchef.more/2
    en  A second cook. If I'm ill in December, December is different for everybody, and that frightens me.
    >>  ............................................
    pt  Um segundo cozinheiro. Se eu adoecer em dezembro, dezembro é outro pra todos, e isso me assusta.
    >>  ............................................
  shy.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie nobody can reproduce. The trick is the resting. Nobody ever asks about the resting.
    >>  ............................................
    pt  Uma torta que ninguém reproduz. O truque é o descanso. Ninguém pergunta do descanso.
    >>  ............................................
  shy.dialogue.conversations.work.prof.delightchef.more/2
    en  A second pair of hands, for the skimming. Six hours of it, every feast.
    >>  ............................................
    pt  Um segundo par de mãos, pra escumar. Seis horas disso, em toda festa.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie nobody can copy! The trick is the resting, and I'll never say that where the baker can hear.
    >>  ............................................
    pt  Uma torta que ninguém copia! O truque é o descanso, e eu nunca digo isso perto do padeiro.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.delightchef.more/2
    en  A second pair of hands, and then December stops being a competitive sport.
    >>  ............................................
    pt  Um segundo par de mãos, e aí dezembro deixa de ser esporte competitivo.
    >>  ............................................
  witty.dialogue.conversations.work.prof.delightchef.more/1
    en  A pie nobody can copy! The trick is the resting, and I'll never say that where the baker can hear.
    >>  ............................................
    pt  Uma torta que ninguém copia! O truque é o descanso, e eu nunca digo isso perto do padeiro.
    >>  ............................................
  witty.dialogue.conversations.work.prof.delightchef.more/2
    en  A second pair of hands, and then December stops being a competitive sport.
    >>  ............................................
    pt  Um segundo par de mãos, e aí dezembro deixa de ser esporte competitivo.
    >>  ............................................
```

</details>


### Button `leave` — "Good service."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.delightchef.challenge.landed`, `work.delightchef.challenge.stung`, `work.delightchef.craft.admire`, `work.delightchef.craft.ask_alone`, `work.delightchef.craft.ask_nine`, `work.delightchef.future.ask_hands`, `work.delightchef.future.ask_timings`, `work.delightchef.future.encourage`, `work.delightchef.hard`, `work.delightchef.risk.ask_ill`, `work.delightchef.risk.ask_pot`, `work.delightchef.risk.sympathise`, `work.delightchef.task.ask_absence`, `work.delightchef.task.ask_backwards`, `work.delightchef.task.offer_hands`, `work.delightchef.value`, `work.delightchef.village.ask_florist`, `work.delightchef.village.ask_wake`, `work.delightchef.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.followup.leave   [13 chars]
    en  Good service.
    >>  ............................................
    pt  Bom serviço.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.leave
WHO    VILLAGER — what the player reads after pressing "Good service."
       spoken on: conversations.topic.work.delightchef.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.left`: the villager accepts. Subject `work.delightchef.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightchef.followup / leave; conversations.scene.work.delightchef.public_failure.failed.respond / leave; conversations.scene.work.delightchef.public_failure.remembered.respond / leave; conversations.scene.work.delightchef.short_feast.blocked.respond / leave; conversations.scene.work.delightchef.short_feast.succeeded.respond / leave; conversations.scene.work.delightchef.who_eats_last.active.respond / leave; conversations.scene.work.delightchef.who_eats_last.succeeded.respond / leave; conversations.topic.work.delightchef.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightchef.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.delightchef.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.delightchef.future` — e.g. "A second pair of hands in the kitchen. Then December is a month and not a wager."


```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.delightchef.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.delightchef.future.respond   [20 chars]
    en  That's what I'd fix.
    >>  ............................................
    pt  É o que eu resolveria.
    >>  ............................................
```


### Button `ask_timings` — "Why can't the timings be written?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightchef.future` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightchef.future.ask_timings` — accepted phrasings: "why can't the timings be written"
  - the message must contain one of: `timings`, `written`, `record`
  - scored words: `timings`(1.5), `written`(1.2), `record`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.future.respond.ask_timings
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.future.respond.ask_timings   [33 chars]
    en  Why can't the timings be written?
    >>  ............................................
    pt  Por que os tempos não podem ser escritos?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightchef.future.ask_timings`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightchef.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the dish you're known for?" | "Good service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.future.ask_timings
WHO    VILLAGER — what the player reads after pressing "Why can't the timings be written?"
       spoken on: conversations.topic.work.delightchef.future.respond, button `ask_timings`
       leaves the player on: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.future.ask_timings`: the villager explains. Subject `work.delightchef.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightchef.future.ask_timings/1   [101 chars]
    en  Because the answer is always 'until it looks like that', and 'that' is a thing you have to have seen.
    >>  ............................................
    pt  Porque a resposta é sempre 'até ficar assim', e 'assim' é algo que você precisa ter visto.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.future.ask_timings/2   [93 chars]
    en  You can write the numbers, %1$s. The numbers are wrong on a cold day and right on a warm one.
    >>  ............................................
    pt  Dá pra escrever os números, %1$s. Os números erram num dia frio e acertam num quente.
    >>  ............................................
```


### Button `encourage` — "Write the wrong numbers. Somebody can correct them."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.delightchef.future` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightchef.future.encourage` — accepted phrasings: "write the wrong numbers. somebody can correct them"
  - the message must contain one of: `write`, `correct`, `numbers`
  - scored words: `write`(1.5), `correct`(1.2), `numbers`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.future.respond.encourage   [51 chars]
    en  Write the wrong numbers. Somebody can correct them.
    >>  ............................................
    pt  Escreva os números errados. Alguém corrige.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.delightchef.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.delightchef.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.delightchef.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the dish you're known for?" | "Good service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.future.encourage
WHO    VILLAGER — what the player reads after pressing "Write the wrong numbers. Somebody can correct them."
       spoken on: conversations.topic.work.delightchef.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.future.encourage`: the villager accepts. Subject `work.delightchef.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightchef.future.encourage/1   [87 chars]
    en  ...Somebody can correct them. That's how I learned. I'd forgotten that's how I learned.
    >>  ............................................
    pt  ...Alguém corrige. Foi assim que eu aprendi. Eu tinha esquecido que foi assim.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.future.encourage/2   [99 chars]
    en  Then it's a winter of evenings and it exists, %1$s, and it can be wrong out loud instead of absent.
    >>  ............................................
    pt  Então é um inverno de noites e existe, %1$s, e pode estar errado em voz alta em vez de ausente.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them. I'd forgotten I was ever the one being corrected.
    >>  ............................................
    pt  ...Alguém pode corrigi-los. Esqueci que já fui eu o corrigido.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  A winter of evenings and it exists. Being wrong out loud is the frightening part.
    >>  ............................................
    pt  Um inverno de noites e passa a existir. Errar em voz alta é a parte assustadora.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them. That's how I learned, thirty years back.
    >>  ............................................
    pt  ...Alguém pode corrigi-los. Foi assim que aprendi, trinta anos atrás.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  A winter of evenings and it exists. I've spent winters on worse.
    >>  ............................................
    pt  Um inverno de noites e passa a existir. Já gastei invernos com coisa pior.
    >>  ............................................
  confident.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them. That's how I learned. I'd forgotten that.
    >>  ............................................
    pt  ...Alguém pode corrigi-los. Foi assim que eu aprendi. Eu tinha esquecido.
    >>  ............................................
  confident.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  Then it's a winter of evenings and it exists, and it can be wrong out loud.
    >>  ............................................
    pt  Então é um inverno de noites e passa a existir, e pode errar em voz alta.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them. That's how I learned. I'd forgotten that.
    >>  ............................................
    pt  ...Alguém pode corrigi-los. Foi assim que eu aprendi. Eu tinha esquecido.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  Then it's a winter of evenings and it exists, and it can be wrong out loud.
    >>  ............................................
    pt  Então é um inverno de noites e passa a existir, e pode errar em voz alta.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them, %1$s. That's how I learned, and I'd forgotten.
    >>  ............................................
    pt  ...Alguém pode corrigi-los, %1$s. Foi assim que aprendi, e eu tinha esquecido.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  Then it's a winter of evenings and it exists. You've made that sound possible.
    >>  ............................................
    pt  Então é um inverno de noites e passa a existir. Você fez isso soar possível.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them, %1$s. That's how I learned, and I'd forgotten.
    >>  ............................................
    pt  ...Alguém pode corrigi-los, %1$s. Foi assim que aprendi, e eu tinha esquecido.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  Then it's a winter of evenings and it exists. You've made that sound possible.
    >>  ............................................
    pt  Então é um inverno de noites e passa a existir. Você fez isso soar possível.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them, %1$s. That's how I learned, and I'd forgotten.
    >>  ............................................
    pt  ...Alguém pode corrigi-los, %1$s. Foi assim que aprendi, e eu tinha esquecido.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  Then it's a winter of evenings and it exists. You've made that sound possible.
    >>  ............................................
    pt  Então é um inverno de noites e passa a existir. Você fez isso soar possível.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them. I'd forgotten I was ever the one being corrected.
    >>  ............................................
    pt  ...Alguém pode corrigi-los. Esqueci que já fui eu o corrigido.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  A winter of evenings and it exists. Being wrong out loud is the frightening part.
    >>  ............................................
    pt  Um inverno de noites e passa a existir. Errar em voz alta é a parte assustadora.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them. That's how I learned. I'd forgotten that.
    >>  ............................................
    pt  ...Alguém pode corrigi-los. Foi assim que eu aprendi. Eu tinha esquecido.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  Then it's a winter of evenings and it exists, and it can be wrong out loud.
    >>  ............................................
    pt  Então é um inverno de noites e passa a existir, e pode errar em voz alta.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them. That's how I learned. I'd forgotten that.
    >>  ............................................
    pt  ...Alguém pode corrigi-los. Foi assim que eu aprendi. Eu tinha esquecido.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  Then it's a winter of evenings and it exists, and it can be wrong out loud.
    >>  ............................................
    pt  Então é um inverno de noites e passa a existir, e pode errar em voz alta.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them. That's how I learned.
    >>  ............................................
    pt  ...Alguém pode corrigi-los. Foi assim que aprendi.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  A winter of evenings, then. And it exists.
    >>  ............................................
    pt  Um inverno de noites, então. E passa a existir.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them. That's how I learned, thirty years back.
    >>  ............................................
    pt  ...Alguém pode corrigi-los. Foi assim que aprendi, trinta anos atrás.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  A winter of evenings and it exists. I've spent winters on worse.
    >>  ............................................
    pt  Um inverno de noites e passa a existir. Já gastei invernos com coisa pior.
    >>  ............................................
  odd.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them. That's how I learned.
    >>  ............................................
    pt  ...Alguém pode corrigi-los. Foi assim que aprendi.
    >>  ............................................
  odd.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  A winter of evenings, then. And it exists.
    >>  ............................................
    pt  Um inverno de noites, então. E passa a existir.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them. That's how I learned, thirty years back.
    >>  ............................................
    pt  ...Alguém pode corrigi-los. Foi assim que aprendi, trinta anos atrás.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  A winter of evenings and it exists. I've spent winters on worse.
    >>  ............................................
    pt  Um inverno de noites e passa a existir. Já gastei invernos com coisa pior.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them! That's how I learned. How did I forget that?
    >>  ............................................
    pt  ...Alguém pode corrigi-los! Foi assim que aprendi. Como esqueci isso?
    >>  ............................................
  peppy.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  A winter of evenings and it exists — and it can be wrong out loud instead of absent.
    >>  ............................................
    pt  Um inverno de noites e passa a existir — e pode errar em voz alta em vez de faltar.
    >>  ............................................
  playful.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them! That's how I learned. How did I forget that?
    >>  ............................................
    pt  ...Alguém pode corrigi-los! Foi assim que aprendi. Como esqueci isso?
    >>  ............................................
  playful.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  A winter of evenings and it exists — and it can be wrong out loud instead of absent.
    >>  ............................................
    pt  Um inverno de noites e passa a existir — e pode errar em voz alta em vez de faltar.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them. That's how I learned, thirty years back.
    >>  ............................................
    pt  ...Alguém pode corrigi-los. Foi assim que aprendi, trinta anos atrás.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  A winter of evenings and it exists. I've spent winters on worse.
    >>  ............................................
    pt  Um inverno de noites e passa a existir. Já gastei invernos com coisa pior.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them. I'd forgotten I was ever the one being corrected.
    >>  ............................................
    pt  ...Alguém pode corrigi-los. Esqueci que já fui eu o corrigido.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  A winter of evenings and it exists. Being wrong out loud is the frightening part.
    >>  ............................................
    pt  Um inverno de noites e passa a existir. Errar em voz alta é a parte assustadora.
    >>  ............................................
  shy.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them. That's how I learned.
    >>  ............................................
    pt  ...Alguém pode corrigi-los. Foi assim que aprendi.
    >>  ............................................
  shy.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  A winter of evenings, then. And it exists.
    >>  ............................................
    pt  Um inverno de noites, então. E passa a existir.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them! That's how I learned. How did I forget that?
    >>  ............................................
    pt  ...Alguém pode corrigi-los! Foi assim que aprendi. Como esqueci isso?
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  A winter of evenings and it exists — and it can be wrong out loud instead of absent.
    >>  ............................................
    pt  Um inverno de noites e passa a existir — e pode errar em voz alta em vez de faltar.
    >>  ............................................
  witty.dialogue.conversations.work.prof.delightchef.future.encourage/1
    en  ...Somebody can correct them! That's how I learned. How did I forget that?
    >>  ............................................
    pt  ...Alguém pode corrigi-los! Foi assim que aprendi. Como esqueci isso?
    >>  ............................................
  witty.dialogue.conversations.work.prof.delightchef.future.encourage/2
    en  A winter of evenings and it exists — and it can be wrong out loud instead of absent.
    >>  ............................................
    pt  Um inverno de noites e passa a existir — e pode errar em voz alta em vez de faltar.
    >>  ............................................
```

</details>


### Button `ask_hands` — "Has nobody wanted the second pair of hands?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightchef.future` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightchef.future.ask_hands` — accepted phrasings: "has nobody wanted the second pair of hands"
  - the message must contain one of: `hands`, `second`, `apprentice`
  - scored words: `hands`(1.5), `second`(1.2), `apprentice`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.future.respond.ask_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.future.respond.ask_hands   [43 chars]
    en  Has nobody wanted the second pair of hands?
    >>  ............................................
    pt  Ninguém quis o segundo par de mãos?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightchef.future.ask_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightchef.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the dish you're known for?" | "Good service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.future.ask_hands
WHO    VILLAGER — what the player reads after pressing "Has nobody wanted the second pair of hands?"
       spoken on: conversations.topic.work.delightchef.future.respond, button `ask_hands`
       leaves the player on: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.future.ask_hands`: the villager explains. Subject `work.delightchef.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightchef.future.ask_hands/1   [106 chars]
    en  Two have. Both wanted to cook and neither wanted to skim a stock for six hours, which is the actual offer.
    >>  ............................................
    pt  Dois quiseram. Os dois queriam cozinhar e nenhum queria escumar caldo por seis horas, que é a oferta real.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.future.ask_hands/2   [91 chars]
    en  One did, and she was good, and she went to the town four days east, %1$s. I told her to go.
    >>  ............................................
    pt  Uma quis, e era boa, e foi pra cidade a quatro dias a leste, %1$s. Eu disse pra ela ir.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to service."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.delightchef.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.future.respond.leave   [33 chars]
    en  I'll let you get back to service.
    >>  ............................................
    pt  Vou deixar você voltar ao serviço.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to service."
       spoken on: conversations.topic.work.delightchef.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.left`: the villager accepts. Subject `work.delightchef.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightchef.followup / leave; conversations.scene.work.delightchef.public_failure.failed.respond / leave; conversations.scene.work.delightchef.public_failure.remembered.respond / leave; conversations.scene.work.delightchef.short_feast.blocked.respond / leave; conversations.scene.work.delightchef.short_feast.succeeded.respond / leave; conversations.scene.work.delightchef.who_eats_last.active.respond / leave; conversations.scene.work.delightchef.who_eats_last.succeeded.respond / leave; conversations.topic.work.delightchef.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightchef.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.delightchef.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.delightchef` — e.g. "A proper kitchen is a battlefield that ends in dessert. I run mine like a loving tyrant."


```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.delightchef.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.delightchef.respond   [35 chars]
    en  That's the kitchen and who runs it.
    >>  ............................................
    pt  É a cozinha e quem manda nela.
    >>  ............................................
```


### Button `ask_hard` — "What ruins a service?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightchef.identity` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightchef.hard` — accepted phrasings: "what ruins a service"
  - the message must contain one of: `ruins`, `service`, `disaster`
  - scored words: `ruins`(1.5), `service`(1.2), `disaster`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.respond.ask_hard   [21 chars]
    en  What ruins a service?
    >>  ............................................
    pt  O que estraga um serviço?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.delightchef.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightchef.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the dish you're known for?" | "Good service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.hard
WHO    VILLAGER — what the player reads after pressing "What ruins a service?"
       spoken on: conversations.topic.work.delightchef.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.hard`: the villager explains. Subject `work.delightchef.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightchef.followup / ask_more
```

> Written out in full under **`conversations.scene.work.delightchef.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "You've made people close their eyes at a first bite."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.delightchef.identity` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightchef.value` — accepted phrasings: "you've made people close their eyes at a first bite"
  - the message must contain one of: `bite`, `eyes`, `delicious`
  - scored words: `bite`(1.5), `eyes`(1.2), `delicious`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.respond.value   [52 chars]
    en  You've made people close their eyes at a first bite.
    >>  ............................................
    pt  Você já fez gente fechar os olhos na primeira garfada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.delightchef.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.delightchef.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightchef.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the dish you're known for?" | "Good service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.value
WHO    VILLAGER — what the player reads after pressing "You've made people close their eyes at a first bite."
       spoken on: conversations.topic.work.delightchef.respond, button `value`
       leaves the player on: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.value`: the villager accepts. Subject `work.delightchef.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightchef.value/1   [77 chars]
    en  Twice with the blacksmith. I keep a private tally and I am not ashamed of it.
    >>  ............................................
    pt  Duas vezes com o ferreiro. Eu mantenho uma contagem particular e não tenho vergonha disso.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.value/2   [75 chars]
    en  That is the entire point of the trade and almost nobody says it back to me.
    >>  ............................................
    pt  É o ponto inteiro do ofício e quase ninguém me devolve isso em palavras.
    >>  ............................................
```


### Button `challenge` — "It's all show. Cooking is cooking."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.delightchef.identity` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightchef.challenge` — accepted phrasings: "it's all show. cooking is cooking"
  - the message must contain one of: `show`, `cooking`, `theatre`
  - scored words: `show`(1.5), `cooking`(1.2), `theatre`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.respond.challenge   [34 chars]
    en  It's all show. Cooking is cooking.
    >>  ............................................
    pt  É tudo espetáculo. Cozinhar é cozinhar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.delightchef.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.delightchef.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightchef.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the dish you're known for?" | "Good service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.challenge.landed
WHO    VILLAGER — what the player reads after pressing "It's all show. Cooking is cooking."
       spoken on: conversations.topic.work.delightchef.respond, button `challenge`
       leaves the player on: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.challenge.landed`: the villager resists. Subject `work.delightchef.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightchef.challenge.landed/1   [73 chars]
    en  Some of it is show. The show is how you get eleven people to move as one.
    >>  ............................................
    pt  Parte é espetáculo. O espetáculo é como você faz onze pessoas se moverem como uma.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.challenge.landed/2   [86 chars]
    en  Cooking is cooking, %1$s. Service is something else entirely, and it is the hard part.
    >>  ............................................
    pt  Cozinhar é cozinhar, %1$s. Serviço é outra coisa completamente, e é a parte difícil.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.delightchef.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.delightchef.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightchef.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the dish you're known for?" | "Good service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.challenge.stung
WHO    VILLAGER — what the player reads after pressing "It's all show. Cooking is cooking."
       spoken on: conversations.topic.work.delightchef.respond, button `challenge`
       leaves the player on: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.challenge.stung`: the villager resists. Subject `work.delightchef.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightchef.challenge.stung/1   [70 chars]
    en  ...Come and stand at the pass for one evening and say that afterwards.
    >>  ............................................
    pt  ...Venha ficar na passagem por uma noite e diga isso depois.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.challenge.stung/2   [47 chars]
    en  All show. Right. Enjoy your bread and dripping.
    >>  ............................................
    pt  Tudo espetáculo. Certo. Aproveite seu pão com banha.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to service."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.delightchef.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.respond.leave   [33 chars]
    en  I'll let you get back to service.
    >>  ............................................
    pt  Vou deixar você voltar ao serviço.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to service."
       spoken on: conversations.topic.work.delightchef.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.left`: the villager accepts. Subject `work.delightchef.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightchef.followup / leave; conversations.scene.work.delightchef.public_failure.failed.respond / leave; conversations.scene.work.delightchef.public_failure.remembered.respond / leave; conversations.scene.work.delightchef.short_feast.blocked.respond / leave; conversations.scene.work.delightchef.short_feast.succeeded.respond / leave; conversations.scene.work.delightchef.who_eats_last.active.respond / leave; conversations.scene.work.delightchef.who_eats_last.succeeded.respond / leave; conversations.topic.work.delightchef.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightchef.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.delightchef.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.delightchef.risk` — e.g. "A kitchen feeds people. Get one pot wrong and eleven households find out on the same night."


```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.delightchef.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.delightchef.risk.respond   [26 chars]
    en  That's what's on the fire.
    >>  ............................................
    pt  É o que está no fogo.
    >>  ............................................
```


### Button `ask_ill` — "What happens if you're ill in December?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightchef.risk` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightchef.risk.ask_ill` — accepted phrasings: "what happens if you're ill in december"
  - the message must contain one of: `ill`, `december`, `sick`
  - scored words: `ill`(1.5), `december`(1.2), `sick`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.risk.respond.ask_ill
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.risk.respond.ask_ill   [39 chars]
    en  What happens if you're ill in December?
    >>  ............................................
    pt  O que acontece se você adoecer em dezembro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightchef.risk.ask_ill`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightchef.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the dish you're known for?" | "Good service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.risk.ask_ill
WHO    VILLAGER — what the player reads after pressing "What happens if you're ill in December?"
       spoken on: conversations.topic.work.delightchef.risk.respond, button `ask_ill`
       leaves the player on: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.risk.ask_ill`: the villager explains. Subject `work.delightchef.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightchef.risk.ask_ill/1   [90 chars]
    en  It has happened. I cooked with a fever and it was the worst food I've ever put on a table.
    >>  ............................................
    pt  Já aconteceu. Cozinhei com febre e foi a pior comida que eu já pus numa mesa.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.risk.ask_ill/2   [87 chars]
    en  Nothing good. There's no second cook here, %1$s, which is a thing I raise every autumn.
    >>  ............................................
    pt  Nada bom. Não tem segundo cozinheiro aqui, %1$s, algo que eu levanto todo outono.
    >>  ............................................
```


### Button `sympathise` — "Cooking through a fever rather than cancelling — that says something."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.delightchef.risk` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightchef.risk.sympathise` — accepted phrasings: "cooking through a fever rather than cancelling — that says something"
  - the message must contain one of: `fever`, `cancelling`
  - scored words: `fever`(1.5), `cancelling`(1.2), `through`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.risk.respond.sympathise   [69 chars]
    en  Cooking through a fever rather than cancelling — that says something.
    >>  ............................................
    pt  Cozinhar com febre em vez de cancelar — isso diz algo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.delightchef.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.delightchef.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightchef.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the dish you're known for?" | "Good service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "Cooking through a fever rather than cancelling — that says something."
       spoken on: conversations.topic.work.delightchef.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.risk.sympathise`: the villager accepts. Subject `work.delightchef.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightchef.risk.sympathise/1   [84 chars]
    en  ...It says I couldn't bear to be the reason. Which is not the same as it being wise.
    >>  ............................................
    pt  ...Diz que eu não suportaria ser o motivo. O que não é o mesmo que ser sábio.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.risk.sympathise/2   [100 chars]
    en  It says I hadn't built anything that could survive me being unwell, %1$s. That's the honest reading.
    >>  ............................................
    pt  Diz que eu não construí nada que sobrevivesse a eu adoecer, %1$s. É a leitura honesta.
    >>  ............................................
```


### Button `ask_pot` — "Has a pot ever gone wrong?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightchef.risk` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightchef.risk.ask_pot` — accepted phrasings: "has a pot ever gone wrong"
  - the message must contain one of: `pot`, `mistake`
  - scored words: `pot`(1.5), `wrong`(0.8), `mistake`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.risk.respond.ask_pot
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.risk.respond.ask_pot   [26 chars]
    en  Has a pot ever gone wrong?
    >>  ............................................
    pt  Um caldeirão já deu errado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightchef.risk.ask_pot`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightchef.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the dish you're known for?" | "Good service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.risk.ask_pot
WHO    VILLAGER — what the player reads after pressing "Has a pot ever gone wrong?"
       spoken on: conversations.topic.work.delightchef.risk.respond, button `ask_pot`
       leaves the player on: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.risk.ask_pot`: the villager explains. Subject `work.delightchef.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightchef.risk.ask_pot/1   [81 chars]
    en  Once, at a wedding, and it was mushrooms, and I have not served a mushroom since.
    >>  ............................................
    pt  Uma vez, num casamento, e foram cogumelos, e eu não sirvo cogumelo desde então.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.risk.ask_pot/2   [93 chars]
    en  Nothing that made anybody ill. Two things that made a night worse, %1$s, and I remember both.
    >>  ............................................
    pt  Nada que adoecesse alguém. Duas coisas que pioraram uma noite, %1$s, e eu lembro das duas.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to service."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.delightchef.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.risk.respond.leave   [33 chars]
    en  I'll let you get back to service.
    >>  ............................................
    pt  Vou deixar você voltar ao serviço.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to service."
       spoken on: conversations.topic.work.delightchef.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.left`: the villager accepts. Subject `work.delightchef.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightchef.followup / leave; conversations.scene.work.delightchef.public_failure.failed.respond / leave; conversations.scene.work.delightchef.public_failure.remembered.respond / leave; conversations.scene.work.delightchef.short_feast.blocked.respond / leave; conversations.scene.work.delightchef.short_feast.succeeded.respond / leave; conversations.scene.work.delightchef.who_eats_last.active.respond / leave; conversations.scene.work.delightchef.who_eats_last.succeeded.respond / leave; conversations.topic.work.delightchef.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightchef.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.delightchef.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.delightchef.task` — e.g. "Eleven at the long table tonight and I've planned it back from the moment the bread has to come out."


```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.delightchef.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.delightchef.task.respond   [25 chars]
    en  That's tonight's kitchen.
    >>  ............................................
    pt  É a cozinha desta noite.
    >>  ............................................
```


### Button `ask_backwards` — "You plan it backwards from the bread?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightchef.task` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightchef.task.ask_backwards` — accepted phrasings: "you plan it backwards from the bread"
  - the message must contain one of: `backwards`, `bread`, `plan`
  - scored words: `backwards`(1.5), `bread`(1.2), `plan`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.task.respond.ask_backwards
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.task.respond.ask_backwards   [37 chars]
    en  You plan it backwards from the bread?
    >>  ............................................
    pt  Você planeja de trás pra frente a partir do pão?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightchef.task.ask_backwards`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightchef.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the dish you're known for?" | "Good service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.task.ask_backwards
WHO    VILLAGER — what the player reads after pressing "You plan it backwards from the bread?"
       spoken on: conversations.topic.work.delightchef.task.respond, button `ask_backwards`
       leaves the player on: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.task.ask_backwards`: the villager explains. Subject `work.delightchef.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightchef.task.ask_backwards/1   [93 chars]
    en  Always. The bread is the only thing that can't wait and everything else negotiates around it.
    >>  ............................................
    pt  Sempre. O pão é a única coisa que não espera e todo o resto negocia em volta.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.task.ask_backwards/2   [81 chars]
    en  From the bread and from the slowest eater, %1$s. Those two set the whole evening.
    >>  ............................................
    pt  A partir do pão e do comensal mais lento, %1$s. Esses dois definem a noite inteira.
    >>  ............................................
```


### Button `offer_hands` — "I can skim the stock for you."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.delightchef.task` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightchef.task.offer_hands` — accepted phrasings: "i can skim the stock for you"
  - the message must contain one of: `skim`, `stock`, `pot`
  - scored words: `skim`(1.5), `stock`(1.5), `pot`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.task.respond.offer_hands   [29 chars]
    en  I can skim the stock for you.
    >>  ............................................
    pt  Eu posso escumar o caldo pra você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.delightchef.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.delightchef.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightchef.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the dish you're known for?" | "Good service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I can skim the stock for you."
       spoken on: conversations.topic.work.delightchef.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.task.offer_hands`: the villager accepts. Subject `work.delightchef.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightchef.task.offer_hands/1   [82 chars]
    en  ...You can. Skim, never stir, and if it boils once the six hours were for nothing.
    >>  ............................................
    pt  ...Pode. Escume, nunca mexa, e se ferver uma vez as seis horas foram à toa.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.task.offer_hands/2   [92 chars]
    en  Every twenty minutes, %1$s, and put what you take off in the small bowl, not down the drain.
    >>  ............................................
    pt  A cada vinte minutos, %1$s, e ponha o que tirar na tigela pequena, não na pia.
    >>  ............................................
```


### Button `ask_absence` — "They'd notice the absence?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightchef.task` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightchef.task.ask_absence` — accepted phrasings: "they'd notice the absence"
  - the message must contain one of: `absence`, `notice`, `missing`
  - scored words: `absence`(1.5), `notice`(1.2), `missing`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.task.respond.ask_absence
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.task.respond.ask_absence   [26 chars]
    en  They'd notice the absence?
    >>  ............................................
    pt  Notariam a falta?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightchef.task.ask_absence`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightchef.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the dish you're known for?" | "Good service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.task.ask_absence
WHO    VILLAGER — what the player reads after pressing "They'd notice the absence?"
       spoken on: conversations.topic.work.delightchef.task.respond, button `ask_absence`
       leaves the player on: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.task.ask_absence`: the villager explains. Subject `work.delightchef.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightchef.task.ask_absence/1   [88 chars]
    en  Instantly, and they'd blame the meat. A stock goes uncomplimented and everybody eats it.
    >>  ............................................
    pt  Na hora, e culpariam a carne. Um caldo nunca é elogiado e todo mundo come.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.task.ask_absence/2   [100 chars]
    en  The one night I served it thin, four people asked if I was unwell, %1$s. Nobody said the word stock.
    >>  ............................................
    pt  Na única noite em que servi ralo, quatro pessoas perguntaram se eu estava mal, %1$s. Ninguém disse a palavra caldo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to service."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.delightchef.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.task.respond.leave   [33 chars]
    en  I'll let you get back to service.
    >>  ............................................
    pt  Vou deixar você voltar ao serviço.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to service."
       spoken on: conversations.topic.work.delightchef.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.left`: the villager accepts. Subject `work.delightchef.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightchef.followup / leave; conversations.scene.work.delightchef.public_failure.failed.respond / leave; conversations.scene.work.delightchef.public_failure.remembered.respond / leave; conversations.scene.work.delightchef.short_feast.blocked.respond / leave; conversations.scene.work.delightchef.short_feast.succeeded.respond / leave; conversations.scene.work.delightchef.who_eats_last.active.respond / leave; conversations.scene.work.delightchef.who_eats_last.succeeded.respond / leave; conversations.topic.work.delightchef.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightchef.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.delightchef.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.delightchef.village` — e.g. "Every wedding, every harvest supper, every wake. I've cooked for this place at both its ends, same as the florist."


```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.delightchef.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.delightchef.village.respond   [21 chars]
    en  That's my part in it.
    >>  ............................................
    pt  É a minha parte nisso.
    >>  ............................................
```


### Button `ask_wake` — "How do you cook for a wake?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightchef.village` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightchef.village.ask_wake` — accepted phrasings: "how do you cook for a wake"
  - the message must contain one of: `wake`, `funeral`
  - scored words: `wake`(1.5), `funeral`(1.2), `cook`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.village.respond.ask_wake
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.village.respond.ask_wake   [27 chars]
    en  How do you cook for a wake?
    >>  ............................................
    pt  Como se cozinha pra um velório?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightchef.village.ask_wake`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightchef.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the dish you're known for?" | "Good service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.village.ask_wake
WHO    VILLAGER — what the player reads after pressing "How do you cook for a wake?"
       spoken on: conversations.topic.work.delightchef.village.respond, button `ask_wake`
       leaves the player on: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.village.ask_wake`: the villager explains. Subject `work.delightchef.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightchef.village.ask_wake/1   [106 chars]
    en  Plainly, and enough of it, and nothing anybody has to think about. Thinking is what they're doing already.
    >>  ............................................
    pt  Simples, e em quantidade, e nada em que precisem pensar. Pensar já é o que estão fazendo.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.village.ask_wake/2   [105 chars]
    en  Whatever the person liked. I ask one family member and I ask them once, %1$s, and then I don't ask again.
    >>  ............................................
    pt  O que a pessoa gostava. Pergunto a um parente e pergunto uma vez, %1$s, e depois não pergunto mais.
    >>  ............................................
```


### Button `say_thanks` — "Eleven years of nobody eating badly is a real record."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.delightchef.village` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightchef.village.say_thanks` — accepted phrasings: "eleven years of nobody eating badly is a real record"
  - the message must contain one of: `record`, `eleven`, `badly`
  - scored words: `record`(1.5), `eleven`(1.0), `badly`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.village.respond.say_thanks   [53 chars]
    en  Eleven years of nobody eating badly is a real record.
    >>  ............................................
    pt  Onze anos sem ninguém comer mal é um recorde de verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.delightchef.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.delightchef.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightchef.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the dish you're known for?" | "Good service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Eleven years of nobody eating badly is a real record."
       spoken on: conversations.topic.work.delightchef.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.village.say_thanks`: the villager accepts. Subject `work.delightchef.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightchef.village.say_thanks/1   [88 chars]
    en  ...It is, and it's the sort of record that only exists if somebody's counting, and I am.
    >>  ............................................
    pt  ...É, e é o tipo de recorde que só existe se alguém contar, e eu conto.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.village.say_thanks/2   [89 chars]
    en  You've said 'record'. I'd been calling it 'not having failed yet', %1$s. Yours is better.
    >>  ............................................
    pt  Você disse 'recorde'. Eu vinha chamando de 'ainda não ter falhado', %1$s. O seu é melhor.
    >>  ............................................
```


### Button `ask_florist` — "You and the florist, at both ends?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.delightchef.village` · offered only once the villager has actually said `work:delightchef`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.delightchef.village.ask_florist` — accepted phrasings: "you and the florist, at both ends"
  - the message must contain one of: `florist`, `ends`
  - scored words: `florist`(1.5), `ends`(1.2), `both`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.village.respond.ask_florist
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.village.respond.ask_florist   [34 chars]
    en  You and the florist, at both ends?
    >>  ............................................
    pt  Você e a florista, nas duas pontas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.delightchef.village.ask_florist`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.delightchef.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the dish you're known for?" | "Good service."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.village.ask_florist
WHO    VILLAGER — what the player reads after pressing "You and the florist, at both ends?"
       spoken on: conversations.topic.work.delightchef.village.respond, button `ask_florist`
       leaves the player on: conversations.topic.work.delightchef.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.village.ask_florist`: the villager explains. Subject `work.delightchef.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:delightchef` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.delightchef.village.ask_florist/1   [85 chars]
    en  The same eleven years and the same fifty-one funerals. We've never once discussed it.
    >>  ............................................
    pt  Os mesmos onze anos e os mesmos cinquenta e um funerais. Nunca conversamos sobre isso.
    >>  ............................................
  dialogue.conversations.work.prof.delightchef.village.ask_florist/2   [96 chars]
    en  We arrive at the same houses on the same mornings, %1$s, and we nod, and that's the whole of it.
    >>  ............................................
    pt  A gente chega nas mesmas casas nas mesmas manhãs, %1$s, e acena, e é tudo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to service."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.delightchef.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.delightchef.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.delightchef.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.delightchef.village.respond.leave   [33 chars]
    en  I'll let you get back to service.
    >>  ............................................
    pt  Vou deixar você voltar ao serviço.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.delightchef.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to service."
       spoken on: conversations.topic.work.delightchef.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.delightchef.left`: the villager accepts. Subject `work.delightchef.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.delightchef.followup / leave; conversations.scene.work.delightchef.public_failure.failed.respond / leave; conversations.scene.work.delightchef.public_failure.remembered.respond / leave; conversations.scene.work.delightchef.short_feast.blocked.respond / leave; conversations.scene.work.delightchef.short_feast.succeeded.respond / leave; conversations.scene.work.delightchef.who_eats_last.active.respond / leave; conversations.scene.work.delightchef.who_eats_last.succeeded.respond / leave; conversations.topic.work.delightchef.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.delightchef.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

