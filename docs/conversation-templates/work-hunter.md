# Work talk with a hunter

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.hunter.emptied_wood.blocked.respond`](#conversations-scene-work-hunter-emptied-wood-blocked-respond)
- [`conversations.scene.work.hunter.emptied_wood.succeeded.respond`](#conversations-scene-work-hunter-emptied-wood-succeeded-respond)
- [`conversations.scene.work.hunter.followup`](#conversations-scene-work-hunter-followup)
- [`conversations.scene.work.hunter.lost_track.failed.respond`](#conversations-scene-work-hunter-lost-track-failed-respond)
- [`conversations.scene.work.hunter.lost_track.remembered.respond`](#conversations-scene-work-hunter-lost-track-remembered-respond)
- [`conversations.scene.work.hunter.robbed_line.blocked.respond`](#conversations-scene-work-hunter-robbed-line-blocked-respond)
- [`conversations.scene.work.hunter.robbed_line.succeeded.respond`](#conversations-scene-work-hunter-robbed-line-succeeded-respond)
- [`conversations.topic.work.hunter.craft.respond`](#conversations-topic-work-hunter-craft-respond)
- [`conversations.topic.work.hunter.followup`](#conversations-topic-work-hunter-followup)
- [`conversations.topic.work.hunter.future.respond`](#conversations-topic-work-hunter-future-respond)
- [`conversations.topic.work.hunter.respond`](#conversations-topic-work-hunter-respond)
- [`conversations.topic.work.hunter.risk.respond`](#conversations-topic-work-hunter-risk-respond)
- [`conversations.topic.work.hunter.task.respond`](#conversations-topic-work-hunter-task-respond)
- [`conversations.topic.work.hunter.village.respond`](#conversations-topic-work-hunter-village-respond)

---

## `conversations.scene.work.hunter.emptied_wood.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.hunter.emptied_wood.blocked` — e.g. "%2$s has %3$s and I can see it in the tracks — half what there was two years ago, in the same mud, at the same hour."


```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.emptied_wood.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.hunter.emptied_wood.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.hunter.emptied_wood.blocked.respond   [9 chars]
    en  The wood.
    >>  ............................................
    pt  O bosque.
    >>  ............................................
```


### Button `ask_how_she_counts` — "How do you count what's left?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter.emptied_wood.blocked` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter.emptied_wood.blocked.ask_how_she_counts` — accepted phrasings: "how do you count whats left"; "how do you count what is left"; "how do you know the numbers"
  - the message must contain one of: `count`, `numbers`
  - scored words: `count`(1.8), `numbers`(1.8), `whats`(0.8), `left`(0.8), `know`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.emptied_wood.blocked.respond.ask_how_she_counts
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter.emptied_wood.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter.emptied_wood.blocked.respond.ask_how_she_counts   [29 chars]
    en  How do you count what's left?
    >>  ............................................
    pt  Como você conta o que restou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.hunter.what_the_wood_can_spare`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.hunter.emptied_wood"}
- Then opens: `conversations.scene.work.hunter.followup`
- …where the player's next choices will be: "What's the hardest part of a long track?" | "I'll leave you to the track."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.emptied_wood.blocked.explained
WHO    VILLAGER — what the player reads after pressing "How do you count what's left?"
       spoken on: conversations.scene.work.hunter.emptied_wood.blocked.respond, button `ask_how_she_counts`
       leaves the player on: conversations.scene.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.emptied_wood.blocked.explained`: the villager explains. Subject `work.hunter.what_the_wood_can_spare`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter.emptied_wood.blocked.explained/1   [131 chars]
    en  The same four crossings on %2$s, the morning after rain, once a fortnight. Prints in fresh mud are the only honest census there is.
    >>  ............................................
    pt  As mesmas quatro travessias em %2$s, na manhã seguinte à chuva, a cada duas semanas. Pegada em lama fresca é o único censo honesto que existe.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.emptied_wood.blocked.explained/2   [123 chars]
    en  By what I stop seeing. Young ones first, then the big males, then nothing but the wary old females who will not breed well.
    >>  ............................................
    pt  Pelo que eu paro de ver. Os filhotes primeiro, depois os machos grandes, depois nada além das fêmeas velhas e arredias que não vão parir bem.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.emptied_wood.blocked.explained/3   [111 chars]
    en  I write it down. Eleven years of numbers in a book, and the book is why I know this is not simply a bad season.
    >>  ............................................
    pt  Eu anoto. Onze anos de números num caderno, e é o caderno que me diz que isto não é simplesmente uma estação ruim.
    >>  ............................................
```


### Button `advise_resting_it` — "Rest the wood for a year."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.hunter.emptied_wood.blocked` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter.emptied_wood.blocked.advise_resting_it` — accepted phrasings: "rest the wood for a year"; "rest the wood for a year"; "leave that ground alone a season"
  - the message must contain one of: `rest`, `ground`, `leave`
  - scored words: `rest`(1.8), `ground`(1.8), `leave`(1.8), `wood`(0.8), `year`(0.8), `alone`(0.8), `season`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.emptied_wood.blocked.respond.advise_resting_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter.emptied_wood.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter.emptied_wood.blocked.respond.advise_resting_it   [25 chars]
    en  Rest the wood for a year.
    >>  ............................................
    pt  Deixe o bosque descansar um ano.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.hunter.wood.rested`, budget `standard`, replay policy `once`
- Does: disposition — respect +4, trust +1  _(recorded under topic `work.hunter.what_the_wood_can_spare`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.hunter.emptied_wood"}
- Then opens: `conversations.scene.work.hunter.followup`
- …where the player's next choices will be: "What's the hardest part of a long track?" | "I'll leave you to the track."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.emptied_wood.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "Rest the wood for a year."
       spoken on: conversations.scene.work.hunter.emptied_wood.blocked.respond, button `advise_resting_it`
       leaves the player on: conversations.scene.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.emptied_wood.blocked.accepted`: the villager accepts. Subject `work.hunter.what_the_wood_can_spare`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter.emptied_wood.blocked.accepted/1   [113 chars]
    en  A year off %2$s means a year of walking twice as far for half as much, and it is still obviously the right thing.
    >>  ............................................
    pt  Um ano longe de %2$s significa um ano andando o dobro por metade, e ainda assim é obviamente o certo.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.emptied_wood.blocked.accepted/2   [132 chars]
    en  Yes. And I will have to tell the village why there is less meat, which is a conversation about arithmetic that nobody wants to have.
    >>  ............................................
    pt  Sim. E vou ter que explicar à vila por que tem menos carne, uma conversa sobre aritmética que ninguém quer ter.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.emptied_wood.blocked.accepted/3   [121 chars]
    en  I have been waiting for somebody to say it so that I could stop arguing with myself. That is a poor reason and it worked.
    >>  ............................................
    pt  Eu estava esperando alguém dizer para eu poder parar de discutir comigo mesma. É um motivo ruim e funcionou.
    >>  ............................................
```


### Button `acknowledge_the_admission` — "Admitting your share is hard."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.hunter.emptied_wood.blocked` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter.emptied_wood.blocked.acknowledge_the_admission` — accepted phrasings: "admitting your share is hard"; "admitting your share of it is hard"; "owning your part in it took something"
  - the message must contain one of: `admitting`, `share`, `owning`
  - scored words: `admitting`(1.8), `share`(1.8), `owning`(1.8), `hard`(0.8), `part`(0.8), `took`(0.8), `something`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.emptied_wood.blocked.respond.acknowledge_the_admission
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter.emptied_wood.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter.emptied_wood.blocked.respond.acknowledge_the_admission   [29 chars]
    en  Admitting your share is hard.
    >>  ............................................
    pt  Admitir a sua parte é difícil.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, trust +1  _(recorded under topic `work.hunter.what_the_wood_can_spare`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.hunter.emptied_wood"}
- Then opens: `conversations.scene.work.hunter.followup`
- …where the player's next choices will be: "What's the hardest part of a long track?" | "I'll leave you to the track."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.emptied_wood.blocked.steadied
WHO    VILLAGER — what the player reads after pressing "Admitting your share is hard."
       spoken on: conversations.scene.work.hunter.emptied_wood.blocked.respond, button `acknowledge_the_admission`
       leaves the player on: conversations.scene.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.emptied_wood.blocked.steadied`: the villager accepts. Subject `work.hunter.what_the_wood_can_spare`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter.emptied_wood.blocked.steadied/1   [123 chars]
    en  It is easier than the alternative, which is a wood with nothing in it and a story about why that was somebody else's doing.
    >>  ............................................
    pt  É mais fácil que a alternativa, que é um bosque vazio e uma história sobre isso ter sido obra de outra pessoa.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.emptied_wood.blocked.steadied/2   [104 chars]
    en  Thank you. Every hunter I have known who blamed the weather ended up with no ground left to blame it on.
    >>  ............................................
    pt  Obrigada. Todo caçador que conheci que culpou o tempo acabou sem terreno nenhum para culpar.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.emptied_wood.blocked.steadied/3   [135 chars]
    en  I would rather be the person who took four too many than the person who cannot count. The first is a mistake and the second is a habit.
    >>  ............................................
    pt  Prefiro ser a pessoa que tirou quatro a mais do que a pessoa que não sabe contar. A primeira é um erro e a segunda é um hábito.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the wood."

*stance family `exit` · tone `plain` · answers the beat(s) `work.hunter.emptied_wood.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.emptied_wood.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter.emptied_wood.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter.emptied_wood.blocked.respond.leave   [34 chars]
    en  I'll let you get back to the wood.
    >>  ............................................
    pt  Vou deixar você voltar ao bosque.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the wood."
       spoken on: conversations.scene.work.hunter.emptied_wood.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.left`: the villager accepts. Subject `work.hunter.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter.emptied_wood.succeeded.respond / leave; conversations.scene.work.hunter.followup / leave; conversations.scene.work.hunter.lost_track.failed.respond / leave; conversations.scene.work.hunter.lost_track.remembered.respond / leave; conversations.scene.work.hunter.robbed_line.blocked.respond / leave; conversations.scene.work.hunter.robbed_line.succeeded.respond / leave; conversations.topic.work.hunter.craft.respond / leave; conversations.topic.work.hunter.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.hunter.leave/1   [39 chars]
    en  They'll be gone by evening. Off you go.
    >>  ............................................
    pt  Vão sumir até de noite. Pode ir.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.leave/2   [38 chars]
    en  Walk on the stone, %1$s, not the moss.
    >>  ............................................
    pt  Ande na pedra, %1$s, não no musgo.
    >>  ............................................
```

---


## `conversations.scene.work.hunter.emptied_wood.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.hunter.emptied_wood.succeeded` — e.g. "I rested %2$s for a full year. There were young at three of the four crossings this spring."


```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.emptied_wood.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.hunter.emptied_wood.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.hunter.emptied_wood.succeeded.respond   [16 chars]
    en  The wood, since.
    >>  ............................................
    pt  O bosque, depois disso.
    >>  ............................................
```


### Button `ask_about_the_village` — "How did the village take it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter.emptied_wood.succeeded` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter.emptied_wood.succeeded.ask_about_the_village` — accepted phrasings: "how did the village take it"; "how did the village take it"; "what did people say about less meat"
  - the message must contain one of: `village`, `people`, `meat`
  - scored words: `village`(1.8), `people`(1.8), `meat`(1.8), `take`(0.8), `say`(0.8), `less`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.emptied_wood.succeeded.respond.ask_about_the_village
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter.emptied_wood.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter.emptied_wood.succeeded.respond.ask_about_the_village   [28 chars]
    en  How did the village take it?
    >>  ............................................
    pt  Como a vila reagiu?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter.what_the_wood_can_spare`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.hunter.emptied_wood"}
- Then opens: `conversations.scene.work.hunter.followup`
- …where the player's next choices will be: "What's the hardest part of a long track?" | "I'll leave you to the track."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.emptied_wood.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "How did the village take it?"
       spoken on: conversations.scene.work.hunter.emptied_wood.succeeded.respond, button `ask_about_the_village`
       leaves the player on: conversations.scene.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.emptied_wood.succeeded.answered`: the villager explains. Subject `work.hunter.what_the_wood_can_spare`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter.emptied_wood.succeeded.answered/1   [108 chars]
    en  Badly for a season and then they forgot they had ever objected, which is the ordinary shape of these things.
    >>  ............................................
    pt  Mal por uma estação e depois esqueceram que tinham reclamado, que é o formato comum dessas coisas.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.emptied_wood.succeeded.answered/2   [127 chars]
    en  I showed two of them the book. One of them argued with the numbers and then came back a month later having counted for himself.
    >>  ............................................
    pt  Mostrei o caderno a dois deles. Um discutiu com os números e depois voltou um mês depois tendo contado ele mesmo.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.emptied_wood.succeeded.answered/3   [124 chars]
    en  The butcher backed me, which mattered more than anything I said. People believe a second trade before they believe your own.
    >>  ............................................
    pt  A açougueira me apoiou, o que pesou mais do que qualquer coisa que eu disse. As pessoas acreditam em um segundo ofício antes de acreditar no seu.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the wood."

*stance family `exit` · tone `plain` · answers the beat(s) `work.hunter.emptied_wood.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.emptied_wood.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter.emptied_wood.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter.emptied_wood.succeeded.respond.leave   [34 chars]
    en  I'll let you get back to the wood.
    >>  ............................................
    pt  Vou deixar você voltar ao bosque.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the wood."
       spoken on: conversations.scene.work.hunter.emptied_wood.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.left`: the villager accepts. Subject `work.hunter.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter.emptied_wood.blocked.respond / leave; conversations.scene.work.hunter.followup / leave; conversations.scene.work.hunter.lost_track.failed.respond / leave; conversations.scene.work.hunter.lost_track.remembered.respond / leave; conversations.scene.work.hunter.robbed_line.blocked.respond / leave; conversations.scene.work.hunter.robbed_line.succeeded.respond / leave; conversations.topic.work.hunter.craft.respond / leave; conversations.topic.work.hunter.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.hunter.emptied_wood.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.hunter.followup`

**Reached from 10 route(s):** `conversations.scene.work.hunter.emptied_wood.blocked.respond` / `ask_how_she_counts`; `conversations.scene.work.hunter.emptied_wood.blocked.respond` / `advise_resting_it`; `conversations.scene.work.hunter.emptied_wood.blocked.respond` / `acknowledge_the_admission`; `conversations.scene.work.hunter.emptied_wood.succeeded.respond` / `ask_about_the_village`; `conversations.scene.work.hunter.lost_track.failed.respond` / `ask_what_went_wrong`; `conversations.scene.work.hunter.lost_track.failed.respond` / `say_it_happens`; `conversations.scene.work.hunter.lost_track.remembered.respond` / `note_the_rule`; `conversations.scene.work.hunter.robbed_line.blocked.respond` / `offer_string`; `conversations.scene.work.hunter.robbed_line.blocked.respond` / `advise_watching`; `conversations.scene.work.hunter.robbed_line.succeeded.respond` / `note_the_choice`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.hunter.emptied_wood.blocked.accepted` — e.g. "A year off %2$s means a year of walking twice as far for half as much, and it is still obviously the right thing."
- `conversations.scene.work.hunter.emptied_wood.blocked.explained` — e.g. "The same four crossings on %2$s, the morning after rain, once a fortnight. Prints in fresh mud are the only honest census there is."
- `conversations.scene.work.hunter.emptied_wood.blocked.steadied` — e.g. "It is easier than the alternative, which is a wood with nothing in it and a story about why that was somebody else's doing."
- `conversations.scene.work.hunter.emptied_wood.succeeded.answered` — e.g. "Badly for a season and then they forgot they had ever objected, which is the ordinary shape of these things."
- `conversations.scene.work.hunter.lost_track.failed.explained` — e.g. "At the water. They go along the bed and come out anywhere, and I chose the wrong bank by about forty paces."
- `conversations.scene.work.hunter.lost_track.failed.qualified` — e.g. "They do. And the ones who say so too easily are the ones who lose four a year, so I am going to keep taking it badly."
- `conversations.scene.work.hunter.lost_track.remembered.acknowledged` — e.g. "Two deer a year, near enough. It is the cheapest rule I own and I would not have written it without the bad night."
- `conversations.scene.work.hunter.robbed_line.blocked.accepted` — e.g. "Then %2$s is running again by Thursday and I can go back to the actual problem, which is who."
- `conversations.scene.work.hunter.robbed_line.blocked.considered` — e.g. "I have thought about it and been avoiding it, because once I have seen them I have to do something, and I would rather it were a stranger."
- `conversations.scene.work.hunter.robbed_line.succeeded.acknowledged` — e.g. "It was also the selfish answer. A neighbour who can feed themselves stops robbing my line, and I get to feel generous about it."


```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.hunter.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.hunter.followup   [21 chars]
    en  Anything else to say?
    >>  ............................................
    pt  Mais alguma coisa a dizer?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of a long track?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.hunter.*` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter.followup.ask_more` — accepted phrasings: "whats the hardest part of a long track"; "what is the hardest part of a long track"; "hardest thing about following a track"
  - the message must contain one of: `hardest`, `track`
  - scored words: `hardest`(1.8), `track`(1.8), `whats`(0.8), `part`(0.8), `long`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter.followup.ask_more   [40 chars]
    en  What's the hardest part of a long track?
    >>  ............................................
    pt  Qual é a parte mais difícil de um rastro longo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the woods telling you lately?" | "Good hunting."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of a long track?"
       spoken on: conversations.scene.work.hunter.followup, button `ask_more`
       leaves the player on: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.hard`: the villager explains. Subject `work.hunter.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.hunter.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.hunter.hard/1   [81 chars]
    en  Follow it. However long it takes. That's not mercy, it's the price of a bad shot.
    >>  ............................................
    pt  Sigo. O tempo que precisar. Não é misericórdia, é o preço de um tiro ruim.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.hard/2   [73 chars]
    en  I finish it, %1$s. Anything else and I'd have no business carrying a bow.
    >>  ............................................
    pt  Eu termino, %1$s. Qualquer outra coisa e eu não teria o direito de carregar um arco.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the track."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.hunter.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter.followup.leave   [28 chars]
    en  I'll leave you to the track.
    >>  ............................................
    pt  Vou deixar você com o rastro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the track."
       spoken on: conversations.scene.work.hunter.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.left`: the villager accepts. Subject `work.hunter.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter.emptied_wood.blocked.respond / leave; conversations.scene.work.hunter.emptied_wood.succeeded.respond / leave; conversations.scene.work.hunter.lost_track.failed.respond / leave; conversations.scene.work.hunter.lost_track.remembered.respond / leave; conversations.scene.work.hunter.robbed_line.blocked.respond / leave; conversations.scene.work.hunter.robbed_line.succeeded.respond / leave; conversations.topic.work.hunter.craft.respond / leave; conversations.topic.work.hunter.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.hunter.emptied_wood.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.hunter.lost_track.failed.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.hunter.lost_track.failed` — e.g. "I followed %2$s and lost it on the second afternoon, and I walked home in the dark thinking about the crossing."


```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.lost_track.failed.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.hunter.lost_track.failed.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.hunter.lost_track.failed.respond   [19 chars]
    en  The track you lost.
    >>  ............................................
    pt  O rastro que você perdeu.
    >>  ............................................
```


### Button `ask_what_went_wrong` — "Where did you lose it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter.lost_track.failed` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter.lost_track.failed.ask_what_went_wrong` — accepted phrasings: "where did you lose it"; "where did you lose it"; "which part of the trail beat you"
  - the message must contain one of: `lose`, `trail`
  - scored words: `lose`(1.8), `trail`(1.8), `where`(0.8), `which`(0.8), `part`(0.8), `beat`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.lost_track.failed.respond.ask_what_went_wrong
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter.lost_track.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter.lost_track.failed.respond.ask_what_went_wrong   [22 chars]
    en  Where did you lose it?
    >>  ............................................
    pt  Onde você perdeu?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter.the_track`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.hunter.lost_track"}
- Then opens: `conversations.scene.work.hunter.followup`
- …where the player's next choices will be: "What's the hardest part of a long track?" | "I'll leave you to the track."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.lost_track.failed.explained
WHO    VILLAGER — what the player reads after pressing "Where did you lose it?"
       spoken on: conversations.scene.work.hunter.lost_track.failed.respond, button `ask_what_went_wrong`
       leaves the player on: conversations.scene.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.lost_track.failed.explained`: the villager explains. Subject `work.hunter.the_track`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter.lost_track.failed.explained/1   [107 chars]
    en  At the water. They go along the bed and come out anywhere, and I chose the wrong bank by about forty paces.
    >>  ............................................
    pt  Na água. Eles andam pelo leito e saem em qualquer ponto, e eu escolhi a margem errada por uns quarenta passos.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.lost_track.failed.explained/2   [115 chars]
    en  I hurried the last mile because the light was going. Everything I got wrong that day, I got wrong in the last hour.
    >>  ............................................
    pt  Apressei o último quilômetro porque a luz estava indo. Tudo o que errei naquele dia, errei na última hora.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.lost_track.failed.explained/3   [119 chars]
    en  Stone. There is a run of bare rock up there and a deer crosses it in six strides and leaves you nothing at all to read.
    >>  ............................................
    pt  Pedra. Tem um trecho de rocha nua lá em cima, e um cervo atravessa em seis passadas e não deixa nada para você ler.
    >>  ............................................
```


### Button `say_it_happens` — "Even a good tracker loses one."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.hunter.lost_track.failed` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter.lost_track.failed.say_it_happens` — accepted phrasings: "even a good tracker loses one"; "even a good tracker loses one"; "the best trackers lose trails too"
  - the message must contain one of: `tracker`, `trackers`
  - scored words: `tracker`(1.8), `trackers`(1.8), `even`(0.8), `good`(0.8), `loses`(0.8), `one`(0.8), `best`(0.8), `trails`(0.8), `too`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.lost_track.failed.respond.say_it_happens
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter.lost_track.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter.lost_track.failed.respond.say_it_happens   [30 chars]
    en  Even a good tracker loses one.
    >>  ............................................
    pt  Até um bom rastreador perde um.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `work.hunter.the_track`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.hunter.lost_track"}
- Then opens: `conversations.scene.work.hunter.followup`
- …where the player's next choices will be: "What's the hardest part of a long track?" | "I'll leave you to the track."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.lost_track.failed.qualified
WHO    VILLAGER — what the player reads after pressing "Even a good tracker loses one."
       spoken on: conversations.scene.work.hunter.lost_track.failed.respond, button `say_it_happens`
       leaves the player on: conversations.scene.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.lost_track.failed.qualified`: the villager qualifys. Subject `work.hunter.the_track`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter.lost_track.failed.qualified/1   [117 chars]
    en  They do. And the ones who say so too easily are the ones who lose four a year, so I am going to keep taking it badly.
    >>  ............................................
    pt  Perdem. E quem diz isso com facilidade demais é quem perde quatro por ano, então vou continuar levando a mal.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.lost_track.failed.qualified/2   [118 chars]
    en  Thank you. I will accept the comfort about the trail and not about the wound, and those are genuinely separate things.
    >>  ............................................
    pt  Obrigada. Aceito o consolo sobre o rastro e não sobre o ferimento, e são coisas genuinamente separadas.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.lost_track.failed.qualified/3   [108 chars]
    en  It is a fair thing to say. It is also how a hunter starts down the road of taking shots she should not take.
    >>  ............................................
    pt  É uma coisa justa de se dizer. Também é como uma caçadora começa a pegar o caminho dos tiros que não deveria dar.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the wood."

*stance family `exit` · tone `plain` · answers the beat(s) `work.hunter.lost_track.failed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.lost_track.failed.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter.lost_track.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter.lost_track.failed.respond.leave   [34 chars]
    en  I'll let you get back to the wood.
    >>  ............................................
    pt  Vou deixar você voltar ao bosque.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the wood."
       spoken on: conversations.scene.work.hunter.lost_track.failed.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.left`: the villager accepts. Subject `work.hunter.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter.emptied_wood.blocked.respond / leave; conversations.scene.work.hunter.emptied_wood.succeeded.respond / leave; conversations.scene.work.hunter.followup / leave; conversations.scene.work.hunter.lost_track.remembered.respond / leave; conversations.scene.work.hunter.robbed_line.blocked.respond / leave; conversations.scene.work.hunter.robbed_line.succeeded.respond / leave; conversations.topic.work.hunter.craft.respond / leave; conversations.topic.work.hunter.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.hunter.emptied_wood.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.hunter.lost_track.remembered.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.hunter.lost_track.remembered` — e.g. "I went back to the crossing four times with no bow, just to learn it, and now I could work it in the dark."


```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.lost_track.remembered.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.hunter.lost_track.remembered.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.hunter.lost_track.remembered.respond   [18 chars]
    en  That track, since.
    >>  ............................................
    pt  Aquele rastro, depois.
    >>  ............................................
```


### Button `note_the_rule` — "That rule cost you something."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.hunter.lost_track.remembered` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter.lost_track.remembered.note_the_rule` — accepted phrasings: "that rule cost you something"; "that rule cost you something real"; "the new rule has a price"
  - the message must contain one of: `rule`, `cost`, `price`
  - scored words: `rule`(1.8), `cost`(1.8), `price`(1.8), `something`(0.8), `real`(0.8), `new`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.lost_track.remembered.respond.note_the_rule
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter.lost_track.remembered.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter.lost_track.remembered.respond.note_the_rule   [29 chars]
    en  That rule cost you something.
    >>  ............................................
    pt  Essa regra te custou algo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.hunter.the_track`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.hunter.lost_track"}
- Then opens: `conversations.scene.work.hunter.followup`
- …where the player's next choices will be: "What's the hardest part of a long track?" | "I'll leave you to the track."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.lost_track.remembered.acknowledged
WHO    VILLAGER — what the player reads after pressing "That rule cost you something."
       spoken on: conversations.scene.work.hunter.lost_track.remembered.respond, button `note_the_rule`
       leaves the player on: conversations.scene.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.lost_track.remembered.acknowledged`: the villager accepts. Subject `work.hunter.the_track`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter.lost_track.remembered.acknowledged/1   [114 chars]
    en  Two deer a year, near enough. It is the cheapest rule I own and I would not have written it without the bad night.
    >>  ............................................
    pt  Dois cervos por ano, mais ou menos. É a regra mais barata que eu tenho e eu não a teria escrito sem a noite ruim.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.lost_track.remembered.acknowledged/2   [110 chars]
    en  Thank you. Rules that cost nothing are the ones people keep and then break quietly. This one I notice keeping.
    >>  ............................................
    pt  Obrigada. Regras que não custam nada são as que se cumprem e depois se quebram em silêncio. Esta eu percebo cumprindo.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.lost_track.remembered.acknowledged/3   [123 chars]
    en  I have told two younger hunters and both of them nodded and neither of them will do it until they have their own bad night.
    >>  ............................................
    pt  Contei a duas caçadoras mais novas e as duas assentiram e nenhuma vai cumprir até ter a própria noite ruim.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the wood."

*stance family `exit` · tone `plain` · answers the beat(s) `work.hunter.lost_track.remembered` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.lost_track.remembered.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter.lost_track.remembered.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter.lost_track.remembered.respond.leave   [34 chars]
    en  I'll let you get back to the wood.
    >>  ............................................
    pt  Vou deixar você voltar ao bosque.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the wood."
       spoken on: conversations.scene.work.hunter.lost_track.remembered.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.left`: the villager accepts. Subject `work.hunter.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter.emptied_wood.blocked.respond / leave; conversations.scene.work.hunter.emptied_wood.succeeded.respond / leave; conversations.scene.work.hunter.followup / leave; conversations.scene.work.hunter.lost_track.failed.respond / leave; conversations.scene.work.hunter.robbed_line.blocked.respond / leave; conversations.scene.work.hunter.robbed_line.succeeded.respond / leave; conversations.topic.work.hunter.craft.respond / leave; conversations.topic.work.hunter.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.hunter.emptied_wood.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.hunter.robbed_line.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.hunter.robbed_line.blocked` — e.g. "Somebody has been working %2$s ahead of me. Three snares empty and sprung, and the sets put back badly."


```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.robbed_line.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.hunter.robbed_line.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.hunter.robbed_line.blocked.respond   [15 chars]
    en  The snare line.
    >>  ............................................
    pt  A linha de armadilhas.
    >>  ............................................
```


### Button `offer_string` — "I'll bring you string for new snares."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.hunter.robbed_line.blocked` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter.robbed_line.blocked.offer_string` — accepted phrasings: "ill bring you string for new snares"; "i can bring you string for snares"; "let me fetch string for that"
  - the message must contain one of: `string`, `snares`
  - scored words: `string`(1.8), `snares`(1.8), `ill`(0.8), `bring`(0.8), `new`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.robbed_line.blocked.respond.offer_string
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter.robbed_line.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter.robbed_line.blocked.respond.offer_string   [37 chars]
    en  I'll bring you string for new snares.
    >>  ............................................
    pt  Vou trazer barbante para armadilhas novas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.hunter.line.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.hunter.the_long_walk`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.robbed_line", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.hunter.robbed_line", "obligation": "commitment:work.hunter.bring_string"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.hunter.bring_string"}
- Then opens: `conversations.scene.work.hunter.followup`
- …where the player's next choices will be: "What's the hardest part of a long track?" | "I'll leave you to the track."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.robbed_line.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you string for new snares."
       spoken on: conversations.scene.work.hunter.robbed_line.blocked.respond, button `offer_string`
       leaves the player on: conversations.scene.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.robbed_line.blocked.accepted`: the villager accepts. Subject `work.hunter.the_long_walk`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter.robbed_line.blocked.accepted/1   [93 chars]
    en  Then %2$s is running again by Thursday and I can go back to the actual problem, which is who.
    >>  ............................................
    pt  Então %2$s volta a funcionar até quinta e eu posso voltar ao problema de verdade, que é quem.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.robbed_line.blocked.accepted/2   [121 chars]
    en  Bring it and I will set a fresh line further up where nobody knows to look, and I will keep the old one going as a decoy.
    >>  ............................................
    pt  Traga e eu monto uma linha nova mais acima, onde ninguém pensa em procurar, e mantenho a antiga como isca.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.robbed_line.blocked.accepted/3   [105 chars]
    en  Yes. And I will owe you a hare, and I will not accept being told otherwise, so save us both the argument.
    >>  ............................................
    pt  Sim. E vou te dever uma lebre, e não vou aceitar recusa, então poupe nós dois da discussão.
    >>  ............................................
```


### Button `advise_watching` — "Sit up one morning and see who it is."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.hunter.robbed_line.blocked` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter.robbed_line.blocked.advise_watching` — accepted phrasings: "sit up one morning and see who it is"; "sit up one morning and see who it is"; "wait out one morning and watch the line"
  - the message must contain one of: `morning`, `watch`, `wait`
  - scored words: `morning`(1.8), `watch`(1.8), `wait`(1.8), `sit`(0.8), `one`(0.8), `see`(0.8), `who`(0.8), `out`(0.8), `line`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.robbed_line.blocked.respond.advise_watching
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter.robbed_line.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter.robbed_line.blocked.respond.advise_watching   [37 chars]
    en  Sit up one morning and see who it is.
    >>  ............................................
    pt  Espere uma manhã e veja quem é.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.hunter.the_long_walk`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.hunter.robbed_line"}
- Then opens: `conversations.scene.work.hunter.followup`
- …where the player's next choices will be: "What's the hardest part of a long track?" | "I'll leave you to the track."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.robbed_line.blocked.considered
WHO    VILLAGER — what the player reads after pressing "Sit up one morning and see who it is."
       spoken on: conversations.scene.work.hunter.robbed_line.blocked.respond, button `advise_watching`
       leaves the player on: conversations.scene.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.robbed_line.blocked.considered`: the villager accepts. Subject `work.hunter.the_long_walk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter.robbed_line.blocked.considered/1   [138 chars]
    en  I have thought about it and been avoiding it, because once I have seen them I have to do something, and I would rather it were a stranger.
    >>  ............................................
    pt  Já pensei e venho evitando, porque depois de ver eu vou ter que fazer algo, e eu preferia que fosse um estranho.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.robbed_line.blocked.considered/2   [126 chars]
    en  Two hours before light, in the frost, on the chance of catching a neighbour. It is the correct plan and it is a miserable one.
    >>  ............................................
    pt  Duas horas antes de clarear, na geada, na esperança de flagrar um vizinho. É o plano certo e é um plano miserável.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.robbed_line.blocked.considered/3   [129 chars]
    en  Yes. And if it is who I think it is, it is a household with four children and a bad year, and then this stops being about snares.
    >>  ............................................
    pt  Sim. E se for quem eu acho, é uma casa com quatro crianças e um ano ruim, e aí isso deixa de ser sobre armadilhas.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the wood."

*stance family `exit` · tone `plain` · answers the beat(s) `work.hunter.robbed_line.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.robbed_line.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter.robbed_line.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter.robbed_line.blocked.respond.leave   [34 chars]
    en  I'll let you get back to the wood.
    >>  ............................................
    pt  Vou deixar você voltar ao bosque.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the wood."
       spoken on: conversations.scene.work.hunter.robbed_line.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.left`: the villager accepts. Subject `work.hunter.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter.emptied_wood.blocked.respond / leave; conversations.scene.work.hunter.emptied_wood.succeeded.respond / leave; conversations.scene.work.hunter.followup / leave; conversations.scene.work.hunter.lost_track.failed.respond / leave; conversations.scene.work.hunter.lost_track.remembered.respond / leave; conversations.scene.work.hunter.robbed_line.succeeded.respond / leave; conversations.topic.work.hunter.craft.respond / leave; conversations.topic.work.hunter.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.hunter.emptied_wood.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.hunter.robbed_line.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.hunter.robbed_line.succeeded` — e.g. "I sat up and it was who I thought. I said nothing, and I have been leaving one snare set for them since."


```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.robbed_line.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.hunter.robbed_line.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.hunter.robbed_line.succeeded.respond   [10 chars]
    en  That line.
    >>  ............................................
    pt  Aquela linha.
    >>  ............................................
```


### Button `note_the_choice` — "Teaching them was the better answer."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.hunter.robbed_line.succeeded` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter.robbed_line.succeeded.note_the_choice` — accepted phrasings: "teaching them was the better answer"; "teaching them was the better answer"; "showing them how was the right move"
  - the message must contain one of: `teaching`, `showing`, `better`
  - scored words: `teaching`(1.8), `showing`(1.8), `better`(1.8), `answer`(0.8), `right`(0.8), `move`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.robbed_line.succeeded.respond.note_the_choice
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter.robbed_line.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter.robbed_line.succeeded.respond.note_the_choice   [36 chars]
    en  Teaching them was the better answer.
    >>  ............................................
    pt  Ensinar foi a melhor resposta.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +4, respect +3  _(recorded under topic `work.hunter.the_long_walk`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.hunter.robbed_line"}
- Then opens: `conversations.scene.work.hunter.followup`
- …where the player's next choices will be: "What's the hardest part of a long track?" | "I'll leave you to the track."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.robbed_line.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Teaching them was the better answer."
       spoken on: conversations.scene.work.hunter.robbed_line.succeeded.respond, button `note_the_choice`
       leaves the player on: conversations.scene.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.robbed_line.succeeded.acknowledged`: the villager accepts. Subject `work.hunter.the_long_walk`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter.robbed_line.succeeded.acknowledged/1   [127 chars]
    en  It was also the selfish answer. A neighbour who can feed themselves stops robbing my line, and I get to feel generous about it.
    >>  ............................................
    pt  Também foi a resposta egoísta. Um vizinho que se alimenta sozinho para de roubar minha linha, e eu ainda me sinto generosa.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.robbed_line.succeeded.acknowledged/2   [111 chars]
    en  Thank you. The alternative was the headman and a public shaming and a family that would never look at me again.
    >>  ............................................
    pt  Obrigada. A alternativa era o chefe da vila, uma humilhação pública e uma família que nunca mais olharia para mim.
    >>  ............................................
  dialogue.conversations.scene.work.hunter.robbed_line.succeeded.acknowledged/3   [156 chars]
    en  I have thought about what my grandmother would have done. She would have set the dogs on them. I am not sure she would have been wrong, and I did it my way.
    >>  ............................................
    pt  Já pensei no que minha avó teria feito. Teria soltado os cães. Não tenho certeza de que ela estaria errada, e eu fiz do meu jeito.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the wood."

*stance family `exit` · tone `plain` · answers the beat(s) `work.hunter.robbed_line.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter.robbed_line.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter.robbed_line.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter.robbed_line.succeeded.respond.leave   [34 chars]
    en  I'll let you get back to the wood.
    >>  ............................................
    pt  Vou deixar você voltar ao bosque.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the wood."
       spoken on: conversations.scene.work.hunter.robbed_line.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.left`: the villager accepts. Subject `work.hunter.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter.emptied_wood.blocked.respond / leave; conversations.scene.work.hunter.emptied_wood.succeeded.respond / leave; conversations.scene.work.hunter.followup / leave; conversations.scene.work.hunter.lost_track.failed.respond / leave; conversations.scene.work.hunter.lost_track.remembered.respond / leave; conversations.scene.work.hunter.robbed_line.blocked.respond / leave; conversations.topic.work.hunter.craft.respond / leave; conversations.topic.work.hunter.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.hunter.emptied_wood.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.hunter.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.hunter.craft` — e.g. "Tracking is arithmetic. Depth, spacing, and what the ground was doing yesterday. Nothing mystical about it."


```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.hunter.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.hunter.craft.respond   [25 chars]
    en  That's what it really is.
    >>  ............................................
    pt  É isso que realmente é.
    >>  ............................................
```


### Button `ask_look` — "Did 'look again' actually teach you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter.craft` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter.craft.ask_look` — accepted phrasings: "did 'look again' actually teach you"
  - the message must contain one of: `again`, `taught`, `aunt`
  - scored words: `again`(1.2), `taught`(1.5), `aunt`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.craft.respond.ask_look
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.craft.respond.ask_look   [36 chars]
    en  Did 'look again' actually teach you?
    >>  ............................................
    pt  'Olhe de novo' ensinou mesmo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter.craft.ask_look`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the woods telling you lately?" | "Good hunting."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.craft.ask_look
WHO    VILLAGER — what the player reads after pressing "Did 'look again' actually teach you?"
       spoken on: conversations.topic.work.hunter.craft.respond, button `ask_look`
       leaves the player on: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.craft.ask_look`: the villager explains. Subject `work.hunter.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter.craft.ask_look/1   [89 chars]
    en  Eventually. It taught me that I'd been deciding what I saw before I'd finished seeing it.
    >>  ............................................
    pt  Por fim. Me ensinou que eu decidia o que via antes de terminar de ver.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.craft.ask_look/2   [93 chars]
    en  It taught me to distrust the first read, %1$s, which is the whole of the craft in four words.
    >>  ............................................
    pt  Me ensinou a desconfiar da primeira leitura, %1$s, que é todo o ofício em quatro palavras.
    >>  ............................................
```


### Button `admire` — "Calling it arithmetic is more honest than most would be."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.hunter.craft` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter.craft.admire` — accepted phrasings: "calling it arithmetic is more honest than most would be"
  - the message must contain one of: `arithmetic`, `honest`, `plain`
  - scored words: `arithmetic`(1.5), `honest`(1.2), `plain`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.craft.respond.admire   [56 chars]
    en  Calling it arithmetic is more honest than most would be.
    >>  ............................................
    pt  Chamar de aritmética é mais honesto do que a maioria seria.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.hunter.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.hunter.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the woods telling you lately?" | "Good hunting."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.craft.admire
WHO    VILLAGER — what the player reads after pressing "Calling it arithmetic is more honest than most would be."
       spoken on: conversations.topic.work.hunter.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.craft.admire`: the villager accepts. Subject `work.hunter.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter.craft.admire/1   [99 chars]
    en  Every hunter I've met dresses it up. It's spacing and depth and I'm not going to pretend otherwise.
    >>  ............................................
    pt  Todo caçador que eu conheci enfeita. É espaçamento e profundidade e eu não vou fingir o contrário.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.craft.admire/2   [92 chars]
    en  The mystical version gets you better prices, %1$s, and I've never been able to say it aloud.
    >>  ............................................
    pt  A versão mística rende preços melhores, %1$s, e eu nunca consegui dizer em voz alta.
    >>  ............................................
```


### Button `ask_ground` — "What was the ground doing yesterday?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter.craft` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter.craft.ask_ground` — accepted phrasings: "what was the ground doing yesterday"
  - the message must contain one of: `ground`, `yesterday`, `tracks`
  - scored words: `ground`(1.5), `yesterday`(1.2), `tracks`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.craft.respond.ask_ground
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.craft.respond.ask_ground   [36 chars]
    en  What was the ground doing yesterday?
    >>  ............................................
    pt  O que o chão fazia ontem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter.craft.ask_ground`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the woods telling you lately?" | "Good hunting."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.craft.ask_ground
WHO    VILLAGER — what the player reads after pressing "What was the ground doing yesterday?"
       spoken on: conversations.topic.work.hunter.craft.respond, button `ask_ground`
       leaves the player on: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.craft.ask_ground`: the villager explains. Subject `work.hunter.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter.craft.ask_ground/1   [90 chars]
    en  Drying, after two wet days. Which means anything I read this morning is at most a day old.
    >>  ............................................
    pt  Secando, depois de dois dias molhados. O que significa que tudo que eu li hoje tem no máximo um dia.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.craft.ask_ground/2   [87 chars]
    en  Freezing at night and thawing by nine, %1$s. That makes every track lie about its size.
    >>  ............................................
    pt  Congelando à noite e degelando às nove, %1$s. Isso faz cada pegada mentir sobre o tamanho.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the tracks."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.hunter.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.craft.respond.leave   [36 chars]
    en  I'll let you get back to the tracks.
    >>  ............................................
    pt  Vou deixar você voltar aos rastros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the tracks."
       spoken on: conversations.topic.work.hunter.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.left`: the villager accepts. Subject `work.hunter.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter.emptied_wood.blocked.respond / leave; conversations.scene.work.hunter.emptied_wood.succeeded.respond / leave; conversations.scene.work.hunter.followup / leave; conversations.scene.work.hunter.lost_track.failed.respond / leave; conversations.scene.work.hunter.lost_track.remembered.respond / leave; conversations.scene.work.hunter.robbed_line.blocked.respond / leave; conversations.scene.work.hunter.robbed_line.succeeded.respond / leave; conversations.topic.work.hunter.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.hunter.emptied_wood.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.hunter.followup`

**Reached from 20 route(s):** `conversations.scene.work.hunter.followup` / `ask_more`; `conversations.topic.work.hunter.craft.respond` / `ask_look`; `conversations.topic.work.hunter.craft.respond` / `admire`; `conversations.topic.work.hunter.craft.respond` / `ask_ground`; `conversations.topic.work.hunter.future.respond` / `ask_written`; `conversations.topic.work.hunter.future.respond` / `encourage`; `conversations.topic.work.hunter.future.respond` / `ask_knees`; `conversations.topic.work.hunter.respond` / `ask_hard`; `conversations.topic.work.hunter.respond` / `value`; `conversations.topic.work.hunter.respond` / `challenge`; `conversations.topic.work.hunter.respond` / `challenge`; `conversations.topic.work.hunter.risk.respond` / `ask_spare` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.hunter.challenge.landed` — e.g. "It is killing. The tracking is what makes it honest rather than lucky."
- `conversations.work.prof.hunter.challenge.stung` — e.g. "...And your supper walks itself onto the table, does it."
- `conversations.work.prof.hunter.craft.admire` — e.g. "Every hunter I've met dresses it up. It's spacing and depth and I'm not going to pretend otherwise."
- `conversations.work.prof.hunter.craft.ask_ground` — e.g. "Drying, after two wet days. Which means anything I read this morning is at most a day old."
- `conversations.work.prof.hunter.craft.ask_look` — e.g. "Eventually. It taught me that I'd been deciding what I saw before I'd finished seeing it."
- `conversations.work.prof.hunter.future.ask_knees` — e.g. "Five years of the full line. Ten of half of it. I've done that arithmetic more than once."
- `conversations.work.prof.hunter.future.ask_written` — e.g. "Because I can't write, and because the one time I asked, I was too embarrassed to ask twice."
- `conversations.work.prof.hunter.future.encourage` — e.g. "...He would, wouldn't he. He's mended a book for a boy and said nothing about it for a year."
- `conversations.work.prof.hunter.hard` — e.g. "Follow it. However long it takes. That's not mercy, it's the price of a bad shot."
- `conversations.work.prof.hunter.risk.ask_spare` — e.g. "I count. Every year, the same four places, the same fortnight. It's dull and it's the whole answer."
- `conversations.work.prof.hunter.risk.ask_who` — e.g. "The florist. She's up before me and she's the only one who'd notice the shed door unopened."
- `conversations.work.prof.hunter.risk.sympathise` — e.g. "...It is. I tell somebody my line before I go, and that somebody is a habit I only started last year."
- `conversations.work.prof.hunter.task.ask_four` — e.g. "In spring, no. In autumn it would mean something has moved in that shouldn't have."
- `conversations.work.prof.hunter.task.ask_worth` — e.g. "Because a snare left holding is a cruelty, and I'd not be able to look at the line again."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.hunter.followup   [30 chars]
    en  That's the woods, near enough.
    >>  ............................................
    pt  É a mata, mais ou menos.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.hunter.challenge.landed`, `work.hunter.challenge.stung`, `work.hunter.craft.admire`, `work.hunter.craft.ask_ground`, `work.hunter.craft.ask_look`, `work.hunter.future.ask_knees`, `work.hunter.future.ask_written`, `work.hunter.future.encourage`, `work.hunter.hard`, `work.hunter.risk.ask_spare`, `work.hunter.risk.ask_who`, `work.hunter.risk.sympathise`, `work.hunter.task.ask_four`, `work.hunter.task.ask_worth`, `work.hunter.task.offer_hands`, `work.hunter.value`, `work.hunter.village.ask_framed`, `work.hunter.village.ask_lean`, `work.hunter.village.say_thanks` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.hunter.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `shot`, `listened`
  - scored words: `thought`(1.2), `shot`(1.2), `listened`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.hunter.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.hunter.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.hunter.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.hunter.thanks`: the villager accepts. Subject `work.hunter.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter.thanks/1   [84 chars]
    en  Nobody does. They think the skill is the shot. The shot is the last five heartbeats.
    >>  ............................................
    pt  Ninguém pensa. Acham que a habilidade é o tiro. O tiro são as últimas cinco batidas do coração.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.thanks/2   [54 chars]
    en  You listened past the first answer, %1$s. Not many do.
    >>  ............................................
    pt  Você ouviu além da primeira resposta, %1$s. Poucos ouvem.
    >>  ............................................
```


### Button `ask_more` — "What's the woods telling you lately?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter.challenge.landed`, `work.hunter.challenge.stung`, `work.hunter.craft.admire`, `work.hunter.craft.ask_ground`, `work.hunter.craft.ask_look`, `work.hunter.future.ask_knees`, `work.hunter.future.ask_written`, `work.hunter.future.encourage`, `work.hunter.hard`, `work.hunter.risk.ask_spare`, `work.hunter.risk.ask_who`, `work.hunter.risk.sympathise`, `work.hunter.task.ask_four`, `work.hunter.task.ask_worth`, `work.hunter.task.offer_hands`, `work.hunter.value`, `work.hunter.village.ask_framed`, `work.hunter.village.ask_lean`, `work.hunter.village.say_thanks` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.hunter.more` — accepted phrasings: "what's the woods telling you lately"
  - the message must contain one of: `woods`, `lately`, `telling`
  - scored words: `woods`(1.5), `lately`(1.2), `telling`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.followup.ask_more   [36 chars]
    en  What's the woods telling you lately?
    >>  ............................................
    pt  O que a mata anda te dizendo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.hunter.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.more
WHO    VILLAGER — what the player reads after pressing "What's the woods telling you lately?"
       spoken on: conversations.topic.work.hunter.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.hunter.more`: the villager discloses. Subject `work.hunter.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter.more/1   [86 chars]
    en  Wolves higher up than they should be. Something moved them, and I'd like to know what.
    >>  ............................................
    pt  Lobos mais acima do que deviam estar. Algo os moveu, e eu queria saber o quê.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.more/2   [77 chars]
    en  A quiet year. Quiet is usually good and occasionally the worst news there is.
    >>  ............................................
    pt  Um ano quieto. Quieto costuma ser bom e de vez em quando é a pior notícia que existe.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher up than they should be. Something moved them, and that something worries me more.
    >>  ............................................
    pt  Lobos mais alto do que deveriam. Algo os moveu, e esse algo me preocupa mais.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.hunter.more/2
    en  The count has held nineteen years. If it stops holding, it'll be my doing, and I'll know.
    >>  ............................................
    pt  A conta se manteve dezenove anos. Se parar, vai ser obra minha, e eu vou saber.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher than they should be. I'll watch another season before I make anything of it.
    >>  ............................................
    pt  Lobos mais alto do que deveriam. Vou observar mais uma estação antes de concluir algo.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.hunter.more/2
    en  Nineteen years and the valley is as full as it was. Slowly is how that number is kept.
    >>  ............................................
    pt  Dezenove anos e o vale está tão cheio quanto era. Devagar é como esse número se mantém.
    >>  ............................................
  confident.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher up than they should be. Something moved them and I would like to know what.
    >>  ............................................
    pt  Lobos mais alto do que deveriam estar. Algo os moveu e eu queria saber o quê.
    >>  ............................................
  confident.dialogue.conversations.work.prof.hunter.more/2
    en  Nineteen years and the valley is as full of deer as when I started. That is the number I'd want read out.
    >>  ............................................
    pt  Dezenove anos e o vale tem tantos cervos quanto quando eu comecei. É esse o número que eu queria lido.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher up than they should be. Something moved them and I would like to know what.
    >>  ............................................
    pt  Lobos mais alto do que deveriam estar. Algo os moveu e eu queria saber o quê.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.hunter.more/2
    en  Nineteen years and the valley is as full of deer as when I started. That is the number I'd want read out.
    >>  ............................................
    pt  Dezenove anos e o vale tem tantos cervos quanto quando eu comecei. É esse o número que eu queria lido.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher up than they should be. Walk the north line with me and I'll show you the tracks.
    >>  ............................................
    pt  Lobos mais alto do que deveriam. Ande a linha norte comigo e eu te mostro as pegadas.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.hunter.more/2
    en  The valley's as full as when I started. Tell the florist — she's the only one who'd know what it cost.
    >>  ............................................
    pt  O vale está tão cheio quanto quando eu comecei. Conte à florista — só ela saberia o que custou.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher up than they should be. Walk the north line with me and I'll show you the tracks.
    >>  ............................................
    pt  Lobos mais alto do que deveriam. Ande a linha norte comigo e eu te mostro as pegadas.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.hunter.more/2
    en  The valley's as full as when I started. Tell the florist — she's the only one who'd know what it cost.
    >>  ............................................
    pt  O vale está tão cheio quanto quando eu comecei. Conte à florista — só ela saberia o que custou.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher up than they should be. Walk the north line with me and I'll show you the tracks.
    >>  ............................................
    pt  Lobos mais alto do que deveriam. Ande a linha norte comigo e eu te mostro as pegadas.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.hunter.more/2
    en  The valley's as full as when I started. Tell the florist — she's the only one who'd know what it cost.
    >>  ............................................
    pt  O vale está tão cheio quanto quando eu comecei. Conte à florista — só ela saberia o que custou.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher up than they should be. Something moved them, and that something worries me more.
    >>  ............................................
    pt  Lobos mais alto do que deveriam. Algo os moveu, e esse algo me preocupa mais.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.hunter.more/2
    en  The count has held nineteen years. If it stops holding, it'll be my doing, and I'll know.
    >>  ............................................
    pt  A conta se manteve dezenove anos. Se parar, vai ser obra minha, e eu vou saber.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher up than they should be. Something moved them and I would like to know what.
    >>  ............................................
    pt  Lobos mais alto do que deveriam estar. Algo os moveu e eu queria saber o quê.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.hunter.more/2
    en  Nineteen years and the valley is as full of deer as when I started. That is the number I'd want read out.
    >>  ............................................
    pt  Dezenove anos e o vale tem tantos cervos quanto quando eu comecei. É esse o número que eu queria lido.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher up than they should be. Something moved them and I would like to know what.
    >>  ............................................
    pt  Lobos mais alto do que deveriam estar. Algo os moveu e eu queria saber o quê.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.hunter.more/2
    en  Nineteen years and the valley is as full of deer as when I started. That is the number I'd want read out.
    >>  ............................................
    pt  Dezenove anos e o vale tem tantos cervos quanto quando eu comecei. É esse o número que eu queria lido.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher up than they should be. Same four places, same fortnight, and the count is wrong.
    >>  ............................................
    pt  Lobos mais alto do que deveriam. Mesmos quatro lugares, mesma quinzena, e a conta está errada.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.hunter.more/2
    en  Nineteen years of counting. Same four places, same fortnight, and the number has held.
    >>  ............................................
    pt  Dezenove anos contando. Mesmos quatro lugares, mesma quinzena, e o número se manteve.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher than they should be. I'll watch another season before I make anything of it.
    >>  ............................................
    pt  Lobos mais alto do que deveriam. Vou observar mais uma estação antes de concluir algo.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.hunter.more/2
    en  Nineteen years and the valley is as full as it was. Slowly is how that number is kept.
    >>  ............................................
    pt  Dezenove anos e o vale está tão cheio quanto era. Devagar é como esse número se mantém.
    >>  ............................................
  odd.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher up than they should be. Same four places, same fortnight, and the count is wrong.
    >>  ............................................
    pt  Lobos mais alto do que deveriam. Mesmos quatro lugares, mesma quinzena, e a conta está errada.
    >>  ............................................
  odd.dialogue.conversations.work.prof.hunter.more/2
    en  Nineteen years of counting. Same four places, same fortnight, and the number has held.
    >>  ............................................
    pt  Dezenove anos contando. Mesmos quatro lugares, mesma quinzena, e o número se manteve.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher than they should be. I'll watch another season before I make anything of it.
    >>  ............................................
    pt  Lobos mais alto do que deveriam. Vou observar mais uma estação antes de concluir algo.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.hunter.more/2
    en  Nineteen years and the valley is as full as it was. Slowly is how that number is kept.
    >>  ............................................
    pt  Dezenove anos e o vale está tão cheio quanto era. Devagar é como esse número se mantém.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher up than they ought to be! Something moved them and nobody else has noticed.
    >>  ............................................
    pt  Lobos mais alto do que deveriam! Algo os moveu e mais ninguém reparou.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.hunter.more/2
    en  Nineteen years and the valley's still full of deer. That's my whole boast and I'll make it twice.
    >>  ............................................
    pt  Dezenove anos e o vale continua cheio de cervos. É toda a minha vaidade e eu vou repetir.
    >>  ............................................
  playful.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher up than they ought to be! Something moved them and nobody else has noticed.
    >>  ............................................
    pt  Lobos mais alto do que deveriam! Algo os moveu e mais ninguém reparou.
    >>  ............................................
  playful.dialogue.conversations.work.prof.hunter.more/2
    en  Nineteen years and the valley's still full of deer. That's my whole boast and I'll make it twice.
    >>  ............................................
    pt  Dezenove anos e o vale continua cheio de cervos. É toda a minha vaidade e eu vou repetir.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher than they should be. I'll watch another season before I make anything of it.
    >>  ............................................
    pt  Lobos mais alto do que deveriam. Vou observar mais uma estação antes de concluir algo.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.hunter.more/2
    en  Nineteen years and the valley is as full as it was. Slowly is how that number is kept.
    >>  ............................................
    pt  Dezenove anos e o vale está tão cheio quanto era. Devagar é como esse número se mantém.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher up than they should be. Something moved them, and that something worries me more.
    >>  ............................................
    pt  Lobos mais alto do que deveriam. Algo os moveu, e esse algo me preocupa mais.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.hunter.more/2
    en  The count has held nineteen years. If it stops holding, it'll be my doing, and I'll know.
    >>  ............................................
    pt  A conta se manteve dezenove anos. Se parar, vai ser obra minha, e eu vou saber.
    >>  ............................................
  shy.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher up than they should be. Same four places, same fortnight, and the count is wrong.
    >>  ............................................
    pt  Lobos mais alto do que deveriam. Mesmos quatro lugares, mesma quinzena, e a conta está errada.
    >>  ............................................
  shy.dialogue.conversations.work.prof.hunter.more/2
    en  Nineteen years of counting. Same four places, same fortnight, and the number has held.
    >>  ............................................
    pt  Dezenove anos contando. Mesmos quatro lugares, mesma quinzena, e o número se manteve.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher up than they ought to be! Something moved them and nobody else has noticed.
    >>  ............................................
    pt  Lobos mais alto do que deveriam! Algo os moveu e mais ninguém reparou.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.hunter.more/2
    en  Nineteen years and the valley's still full of deer. That's my whole boast and I'll make it twice.
    >>  ............................................
    pt  Dezenove anos e o vale continua cheio de cervos. É toda a minha vaidade e eu vou repetir.
    >>  ............................................
  witty.dialogue.conversations.work.prof.hunter.more/1
    en  Wolves higher up than they ought to be! Something moved them and nobody else has noticed.
    >>  ............................................
    pt  Lobos mais alto do que deveriam! Algo os moveu e mais ninguém reparou.
    >>  ............................................
  witty.dialogue.conversations.work.prof.hunter.more/2
    en  Nineteen years and the valley's still full of deer. That's my whole boast and I'll make it twice.
    >>  ............................................
    pt  Dezenove anos e o vale continua cheio de cervos. É toda a minha vaidade e eu vou repetir.
    >>  ............................................
```

</details>


### Button `leave` — "Good hunting."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.hunter.challenge.landed`, `work.hunter.challenge.stung`, `work.hunter.craft.admire`, `work.hunter.craft.ask_ground`, `work.hunter.craft.ask_look`, `work.hunter.future.ask_knees`, `work.hunter.future.ask_written`, `work.hunter.future.encourage`, `work.hunter.hard`, `work.hunter.risk.ask_spare`, `work.hunter.risk.ask_who`, `work.hunter.risk.sympathise`, `work.hunter.task.ask_four`, `work.hunter.task.ask_worth`, `work.hunter.task.offer_hands`, `work.hunter.value`, `work.hunter.village.ask_framed`, `work.hunter.village.ask_lean`, `work.hunter.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.followup.leave   [13 chars]
    en  Good hunting.
    >>  ............................................
    pt  Boa caçada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.leave
WHO    VILLAGER — what the player reads after pressing "Good hunting."
       spoken on: conversations.topic.work.hunter.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.left`: the villager accepts. Subject `work.hunter.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter.emptied_wood.blocked.respond / leave; conversations.scene.work.hunter.emptied_wood.succeeded.respond / leave; conversations.scene.work.hunter.followup / leave; conversations.scene.work.hunter.lost_track.failed.respond / leave; conversations.scene.work.hunter.lost_track.remembered.respond / leave; conversations.scene.work.hunter.robbed_line.blocked.respond / leave; conversations.scene.work.hunter.robbed_line.succeeded.respond / leave; conversations.topic.work.hunter.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.hunter.emptied_wood.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.hunter.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.hunter.future` — e.g. "I want the count written down somewhere it survives me. Otherwise the next hunter starts from nothing."


```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.hunter.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.hunter.future.respond   [20 chars]
    en  That's what's ahead.
    >>  ............................................
    pt  É o que está à frente.
    >>  ............................................
```


### Button `ask_written` — "Why isn't the count written down?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter.future` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter.future.ask_written` — accepted phrasings: "why isn't the count written down"
  - the message must contain one of: `written`, `count`, `record`
  - scored words: `written`(1.5), `count`(1.0), `record`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.future.respond.ask_written
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.future.respond.ask_written   [33 chars]
    en  Why isn't the count written down?
    >>  ............................................
    pt  Por que a contagem não está escrita?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter.future.ask_written`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the woods telling you lately?" | "Good hunting."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.future.ask_written
WHO    VILLAGER — what the player reads after pressing "Why isn't the count written down?"
       spoken on: conversations.topic.work.hunter.future.respond, button `ask_written`
       leaves the player on: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.future.ask_written`: the villager explains. Subject `work.hunter.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter.future.ask_written/1   [92 chars]
    en  Because I can't write, and because the one time I asked, I was too embarrassed to ask twice.
    >>  ............................................
    pt  Porque eu não sei escrever, e porque na única vez que pedi, fiquei com vergonha de pedir de novo.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.future.ask_written/2   [103 chars]
    en  Because it's nineteen years of numbers in my head, %1$s, and I've been afraid of how small that sounds.
    >>  ............................................
    pt  Porque são dezenove anos de números na minha cabeça, %1$s, e eu tive medo de como isso soa pequeno.
    >>  ............................................
```


### Button `encourage` — "The librarian would write it and ask no questions."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.hunter.future` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter.future.encourage` — accepted phrasings: "the librarian would write it and ask no questions"
  - the message must contain one of: `librarian`, `write`
  - scored words: `librarian`(1.5), `write`(1.2), `ask`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.future.respond.encourage   [50 chars]
    en  The librarian would write it and ask no questions.
    >>  ............................................
    pt  O bibliotecário escreveria sem fazer perguntas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.hunter.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.hunter.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the woods telling you lately?" | "Good hunting."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.future.encourage
WHO    VILLAGER — what the player reads after pressing "The librarian would write it and ask no questions."
       spoken on: conversations.topic.work.hunter.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.future.encourage`: the villager accepts. Subject `work.hunter.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter.future.encourage/1   [92 chars]
    en  ...He would, wouldn't he. He's mended a book for a boy and said nothing about it for a year.
    >>  ............................................
    pt  ...Ele escreveria, não é. Ele consertou um livro pra um menino e não disse nada por um ano.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.future.encourage/2   [105 chars]
    en  Then it's a morning's talking and nineteen years survive me, %1$s. Put like that it's absurd I've waited.
    >>  ............................................
    pt  Então é uma manhã de conversa e dezenove anos me sobrevivem, %1$s. Assim posto é absurdo eu ter esperado.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would. He's kinder than he lets on and I've been too proud to ask.
    >>  ............................................
    pt  ...Ele faria. É mais gentil do que deixa ver e eu fui orgulhoso demais pra pedir.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  A morning's talking and nineteen years survive me. I've been afraid of that morning.
    >>  ............................................
    pt  Uma manhã de conversa e dezenove anos sobrevivem a mim. Tive medo dessa manhã.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would. I've watched him do quieter kindnesses than that for twenty years.
    >>  ............................................
    pt  ...Ele faria. Vi ele fazer gentilezas mais silenciosas que essa por vinte anos.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  A morning's talking and nineteen years survive me. Waiting was never the sensible half.
    >>  ............................................
    pt  Uma manhã de conversa e dezenove anos sobrevivem a mim. Esperar nunca foi o lado sensato.
    >>  ............................................
  confident.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would, wouldn't he. He mended a book for a boy and said nothing for a year.
    >>  ............................................
    pt  ...Ele faria, não faria. Ele consertou um livro pra um menino e não falou por um ano.
    >>  ............................................
  confident.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  Then it's a morning's talking and nineteen years survive me.
    >>  ............................................
    pt  Então é uma manhã de conversa e dezenove anos sobrevivem a mim.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would, wouldn't he. He mended a book for a boy and said nothing for a year.
    >>  ............................................
    pt  ...Ele faria, não faria. Ele consertou um livro pra um menino e não falou por um ano.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  Then it's a morning's talking and nineteen years survive me.
    >>  ............................................
    pt  Então é uma manhã de conversa e dezenove anos sobrevivem a mim.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would, wouldn't he, %1$s. He mended a book for a boy and told nobody.
    >>  ............................................
    pt  ...Ele faria, não faria, %1$s. Consertou um livro pra um menino e não contou a ninguém.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  A morning's talking and nineteen years survive me. Put like that, I've been foolish.
    >>  ............................................
    pt  Uma manhã de conversa e dezenove anos sobrevivem a mim. Assim, fui tolo.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would, wouldn't he, %1$s. He mended a book for a boy and told nobody.
    >>  ............................................
    pt  ...Ele faria, não faria, %1$s. Consertou um livro pra um menino e não contou a ninguém.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  A morning's talking and nineteen years survive me. Put like that, I've been foolish.
    >>  ............................................
    pt  Uma manhã de conversa e dezenove anos sobrevivem a mim. Assim, fui tolo.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would, wouldn't he, %1$s. He mended a book for a boy and told nobody.
    >>  ............................................
    pt  ...Ele faria, não faria, %1$s. Consertou um livro pra um menino e não contou a ninguém.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  A morning's talking and nineteen years survive me. Put like that, I've been foolish.
    >>  ............................................
    pt  Uma manhã de conversa e dezenove anos sobrevivem a mim. Assim, fui tolo.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would. He's kinder than he lets on and I've been too proud to ask.
    >>  ............................................
    pt  ...Ele faria. É mais gentil do que deixa ver e eu fui orgulhoso demais pra pedir.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  A morning's talking and nineteen years survive me. I've been afraid of that morning.
    >>  ............................................
    pt  Uma manhã de conversa e dezenove anos sobrevivem a mim. Tive medo dessa manhã.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would, wouldn't he. He mended a book for a boy and said nothing for a year.
    >>  ............................................
    pt  ...Ele faria, não faria. Ele consertou um livro pra um menino e não falou por um ano.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  Then it's a morning's talking and nineteen years survive me.
    >>  ............................................
    pt  Então é uma manhã de conversa e dezenove anos sobrevivem a mim.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would, wouldn't he. He mended a book for a boy and said nothing for a year.
    >>  ............................................
    pt  ...Ele faria, não faria. Ele consertou um livro pra um menino e não falou por um ano.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  Then it's a morning's talking and nineteen years survive me.
    >>  ............................................
    pt  Então é uma manhã de conversa e dezenove anos sobrevivem a mim.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would. He mended a book once and said nothing.
    >>  ............................................
    pt  ...Ele faria. Consertou um livro uma vez e não disse nada.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  A morning's talking. Nineteen years survive me.
    >>  ............................................
    pt  Uma manhã de conversa. Dezenove anos sobrevivem a mim.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would. I've watched him do quieter kindnesses than that for twenty years.
    >>  ............................................
    pt  ...Ele faria. Vi ele fazer gentilezas mais silenciosas que essa por vinte anos.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  A morning's talking and nineteen years survive me. Waiting was never the sensible half.
    >>  ............................................
    pt  Uma manhã de conversa e dezenove anos sobrevivem a mim. Esperar nunca foi o lado sensato.
    >>  ............................................
  odd.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would. He mended a book once and said nothing.
    >>  ............................................
    pt  ...Ele faria. Consertou um livro uma vez e não disse nada.
    >>  ............................................
  odd.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  A morning's talking. Nineteen years survive me.
    >>  ............................................
    pt  Uma manhã de conversa. Dezenove anos sobrevivem a mim.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would. I've watched him do quieter kindnesses than that for twenty years.
    >>  ............................................
    pt  ...Ele faria. Vi ele fazer gentilezas mais silenciosas que essa por vinte anos.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  A morning's talking and nineteen years survive me. Waiting was never the sensible half.
    >>  ............................................
    pt  Uma manhã de conversa e dezenove anos sobrevivem a mim. Esperar nunca foi o lado sensato.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would, wouldn't he! He mended a book for a boy and never said a word about it.
    >>  ............................................
    pt  ...Ele faria, não faria! Consertou um livro pra um menino e nunca disse nada.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  A morning's talking and nineteen years survive me. Put like that it's absurd I've waited.
    >>  ............................................
    pt  Uma manhã de conversa e dezenove anos sobrevivem a mim. Assim é absurdo eu ter esperado.
    >>  ............................................
  playful.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would, wouldn't he! He mended a book for a boy and never said a word about it.
    >>  ............................................
    pt  ...Ele faria, não faria! Consertou um livro pra um menino e nunca disse nada.
    >>  ............................................
  playful.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  A morning's talking and nineteen years survive me. Put like that it's absurd I've waited.
    >>  ............................................
    pt  Uma manhã de conversa e dezenove anos sobrevivem a mim. Assim é absurdo eu ter esperado.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would. I've watched him do quieter kindnesses than that for twenty years.
    >>  ............................................
    pt  ...Ele faria. Vi ele fazer gentilezas mais silenciosas que essa por vinte anos.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  A morning's talking and nineteen years survive me. Waiting was never the sensible half.
    >>  ............................................
    pt  Uma manhã de conversa e dezenove anos sobrevivem a mim. Esperar nunca foi o lado sensato.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would. He's kinder than he lets on and I've been too proud to ask.
    >>  ............................................
    pt  ...Ele faria. É mais gentil do que deixa ver e eu fui orgulhoso demais pra pedir.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  A morning's talking and nineteen years survive me. I've been afraid of that morning.
    >>  ............................................
    pt  Uma manhã de conversa e dezenove anos sobrevivem a mim. Tive medo dessa manhã.
    >>  ............................................
  shy.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would. He mended a book once and said nothing.
    >>  ............................................
    pt  ...Ele faria. Consertou um livro uma vez e não disse nada.
    >>  ............................................
  shy.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  A morning's talking. Nineteen years survive me.
    >>  ............................................
    pt  Uma manhã de conversa. Dezenove anos sobrevivem a mim.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would, wouldn't he! He mended a book for a boy and never said a word about it.
    >>  ............................................
    pt  ...Ele faria, não faria! Consertou um livro pra um menino e nunca disse nada.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  A morning's talking and nineteen years survive me. Put like that it's absurd I've waited.
    >>  ............................................
    pt  Uma manhã de conversa e dezenove anos sobrevivem a mim. Assim é absurdo eu ter esperado.
    >>  ............................................
  witty.dialogue.conversations.work.prof.hunter.future.encourage/1
    en  ...He would, wouldn't he! He mended a book for a boy and never said a word about it.
    >>  ............................................
    pt  ...Ele faria, não faria! Consertou um livro pra um menino e nunca disse nada.
    >>  ............................................
  witty.dialogue.conversations.work.prof.hunter.future.encourage/2
    en  A morning's talking and nineteen years survive me. Put like that it's absurd I've waited.
    >>  ............................................
    pt  Uma manhã de conversa e dezenove anos sobrevivem a mim. Assim é absurdo eu ter esperado.
    >>  ............................................
```

</details>


### Button `ask_knees` — "How long do the knees have?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter.future` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter.future.ask_knees` — accepted phrasings: "how long do the knees have"
  - the message must contain one of: `knees`
  - scored words: `knees`(1.5), `long`(0.8), `years`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.future.respond.ask_knees
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.future.respond.ask_knees   [27 chars]
    en  How long do the knees have?
    >>  ............................................
    pt  Quanto tempo os joelhos têm?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter.future.ask_knees`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the woods telling you lately?" | "Good hunting."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.future.ask_knees
WHO    VILLAGER — what the player reads after pressing "How long do the knees have?"
       spoken on: conversations.topic.work.hunter.future.respond, button `ask_knees`
       leaves the player on: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.future.ask_knees`: the villager explains. Subject `work.hunter.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter.future.ask_knees/1   [89 chars]
    en  Five years of the full line. Ten of half of it. I've done that arithmetic more than once.
    >>  ............................................
    pt  Cinco anos da linha inteira. Dez de metade. Já fiz essa conta mais de uma vez.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.future.ask_knees/2   [76 chars]
    en  Longer than my patience, %1$s, which is the actual thing that's running out.
    >>  ............................................
    pt  Mais que minha paciência, %1$s, que é o que está de fato acabando.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the tracks."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.hunter.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.future.respond.leave   [36 chars]
    en  I'll let you get back to the tracks.
    >>  ............................................
    pt  Vou deixar você voltar aos rastros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the tracks."
       spoken on: conversations.topic.work.hunter.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.left`: the villager accepts. Subject `work.hunter.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter.emptied_wood.blocked.respond / leave; conversations.scene.work.hunter.emptied_wood.succeeded.respond / leave; conversations.scene.work.hunter.followup / leave; conversations.scene.work.hunter.lost_track.failed.respond / leave; conversations.scene.work.hunter.lost_track.remembered.respond / leave; conversations.scene.work.hunter.robbed_line.blocked.respond / leave; conversations.scene.work.hunter.robbed_line.succeeded.respond / leave; conversations.topic.work.hunter.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.hunter.emptied_wood.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.hunter.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.hunter` — e.g. "I read the woods like the librarian reads books. Tracks don't lie. People, occasionally."


```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.hunter.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.hunter.respond   [54 chars]
    en  That's the woods and the arrangement I have with them.
    >>  ............................................
    pt  É a mata e o acordo que eu tenho com ela.
    >>  ............................................
```


### Button `ask_hard` — "What do you do about a wounded one?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter.identity` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter.hard` — accepted phrasings: "what do you do about a wounded one"
  - the message must contain one of: `wounded`, `injured`
  - scored words: `wounded`(1.5), `injured`(1.5), `follow`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.respond.ask_hard   [35 chars]
    en  What do you do about a wounded one?
    >>  ............................................
    pt  O que você faz com um ferido?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.hunter.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the woods telling you lately?" | "Good hunting."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.hard
WHO    VILLAGER — what the player reads after pressing "What do you do about a wounded one?"
       spoken on: conversations.topic.work.hunter.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.hard`: the villager explains. Subject `work.hunter.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter.followup / ask_more
```

> Written out in full under **`conversations.scene.work.hunter.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "You could take more than you do."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.hunter.identity` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter.value` — accepted phrasings: "you could take more than you do"
  - the message must contain one of: `restraint`, `take`
  - scored words: `more`(0.8), `restraint`(1.5), `take`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.respond.value   [32 chars]
    en  You could take more than you do.
    >>  ............................................
    pt  Você poderia levar mais do que leva.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.hunter.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.hunter.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the woods telling you lately?" | "Good hunting."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.value
WHO    VILLAGER — what the player reads after pressing "You could take more than you do."
       spoken on: conversations.topic.work.hunter.respond, button `value`
       leaves the player on: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.value`: the villager accepts. Subject `work.hunter.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter.value/1   [75 chars]
    en  I could. Then in six years there'd be nothing to take, and I'd be a farmer.
    >>  ............................................
    pt  Poderia. Aí em seis anos não haveria nada pra levar, e eu seria fazendeiro.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.value/2   [84 chars]
    en  That's the whole of the trade, put in one sentence. Most never notice the restraint.
    >>  ............................................
    pt  É o ofício inteiro, numa frase. Quase ninguém repara na contenção.
    >>  ............................................
```


### Button `challenge` — "It's killing, dressed up in tracking."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.hunter.identity` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter.challenge` — accepted phrasings: "it's killing, dressed up in tracking"
  - the message must contain one of: `killing`, `tracking`, `dressed`
  - scored words: `killing`(1.5), `tracking`(1.2), `dressed`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.respond.challenge   [37 chars]
    en  It's killing, dressed up in tracking.
    >>  ............................................
    pt  É matar, disfarçado de rastreamento.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.hunter.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.hunter.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the woods telling you lately?" | "Good hunting."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.challenge.landed
WHO    VILLAGER — what the player reads after pressing "It's killing, dressed up in tracking."
       spoken on: conversations.topic.work.hunter.respond, button `challenge`
       leaves the player on: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.challenge.landed`: the villager resists. Subject `work.hunter.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter.challenge.landed/1   [70 chars]
    en  It is killing. The tracking is what makes it honest rather than lucky.
    >>  ............................................
    pt  É matar. O rastreamento é o que torna honesto em vez de sortudo.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.challenge.landed/2   [72 chars]
    en  You're not wrong, %1$s. I'd think less of a hunter who argued with that.
    >>  ............................................
    pt  Você não está errado, %1$s. Eu acharia pior um caçador que discutisse isso.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.hunter.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.hunter.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the woods telling you lately?" | "Good hunting."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.challenge.stung
WHO    VILLAGER — what the player reads after pressing "It's killing, dressed up in tracking."
       spoken on: conversations.topic.work.hunter.respond, button `challenge`
       leaves the player on: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.challenge.stung`: the villager resists. Subject `work.hunter.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter.challenge.stung/1   [56 chars]
    en  ...And your supper walks itself onto the table, does it.
    >>  ............................................
    pt  ...E seu jantar vai sozinho pra mesa, é.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.challenge.stung/2   [65 chars]
    en  Dressed up. Right. Read the woods for a season and then judge me.
    >>  ............................................
    pt  Disfarçado. Certo. Leia a mata por uma estação e depois me julgue.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the tracks."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.hunter.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.respond.leave   [36 chars]
    en  I'll let you get back to the tracks.
    >>  ............................................
    pt  Vou deixar você voltar aos rastros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the tracks."
       spoken on: conversations.topic.work.hunter.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.left`: the villager accepts. Subject `work.hunter.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter.emptied_wood.blocked.respond / leave; conversations.scene.work.hunter.emptied_wood.succeeded.respond / leave; conversations.scene.work.hunter.followup / leave; conversations.scene.work.hunter.lost_track.failed.respond / leave; conversations.scene.work.hunter.lost_track.remembered.respond / leave; conversations.scene.work.hunter.robbed_line.blocked.respond / leave; conversations.scene.work.hunter.robbed_line.succeeded.respond / leave; conversations.topic.work.hunter.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.hunter.emptied_wood.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.hunter.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.hunter.risk` — e.g. "I take what the valley can spare and no more. Get that wrong for three years and there's no fourth."


```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.hunter.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.hunter.risk.respond   [23 chars]
    en  That's what's at stake.
    >>  ............................................
    pt  É o que está em jogo.
    >>  ............................................
```


### Button `ask_spare` — "How do you know what it can spare?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter.risk` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter.risk.ask_spare` — accepted phrasings: "how do you know what it can spare"
  - the message must contain one of: `spare`, `count`, `sustain`
  - scored words: `spare`(1.5), `count`(1.2), `sustain`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.risk.respond.ask_spare
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.risk.respond.ask_spare   [34 chars]
    en  How do you know what it can spare?
    >>  ............................................
    pt  Como você sabe o que ele pode dispensar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter.risk.ask_spare`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the woods telling you lately?" | "Good hunting."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.risk.ask_spare
WHO    VILLAGER — what the player reads after pressing "How do you know what it can spare?"
       spoken on: conversations.topic.work.hunter.risk.respond, button `ask_spare`
       leaves the player on: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.risk.ask_spare`: the villager explains. Subject `work.hunter.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter.risk.ask_spare/1   [99 chars]
    en  I count. Every year, the same four places, the same fortnight. It's dull and it's the whole answer.
    >>  ............................................
    pt  Eu conto. Todo ano, os mesmos quatro lugares, a mesma quinzena. É maçante e é toda a resposta.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.risk.ask_spare/2   [80 chars]
    en  By taking less than I could and watching whether next year argues with me, %1$s.
    >>  ............................................
    pt  Tirando menos do que eu poderia e vendo se o ano seguinte discorda, %1$s.
    >>  ............................................
```


### Button `sympathise` — "Three hours from anybody is a long way to be alone."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.hunter.risk` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter.risk.sympathise` — accepted phrasings: "three hours from anybody is a long way to be alone"
  - the message must contain one of: `alone`, `hours`
  - scored words: `alone`(1.5), `hours`(1.2), `far`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.risk.respond.sympathise   [51 chars]
    en  Three hours from anybody is a long way to be alone.
    >>  ............................................
    pt  Três horas de qualquer um é longe pra estar sozinho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.hunter.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.hunter.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the woods telling you lately?" | "Good hunting."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "Three hours from anybody is a long way to be alone."
       spoken on: conversations.topic.work.hunter.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.risk.sympathise`: the villager accepts. Subject `work.hunter.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter.risk.sympathise/1   [101 chars]
    en  ...It is. I tell somebody my line before I go, and that somebody is a habit I only started last year.
    >>  ............................................
    pt  ...É. Eu conto minha linha pra alguém antes de sair, e esse alguém é um hábito que eu comecei ano passado.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.risk.sympathise/2   [79 chars]
    en  It's the part of the trade nobody asks about, %1$s. They ask about the animals.
    >>  ............................................
    pt  É a parte do ofício sobre a qual ninguém pergunta, %1$s. Perguntam dos animais.
    >>  ............................................
```


### Button `ask_who` — "Who do you tell your line to?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter.risk` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter.risk.ask_who` — accepted phrasings: "who do you tell your line to"
  - the message must contain one of: `tell`, `line`, `whom`
  - scored words: `tell`(1.2), `line`(1.0), `whom`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.risk.respond.ask_who
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.risk.respond.ask_who   [29 chars]
    en  Who do you tell your line to?
    >>  ............................................
    pt  Pra quem você conta sua linha?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter.risk.ask_who`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the woods telling you lately?" | "Good hunting."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.risk.ask_who
WHO    VILLAGER — what the player reads after pressing "Who do you tell your line to?"
       spoken on: conversations.topic.work.hunter.risk.respond, button `ask_who`
       leaves the player on: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.risk.ask_who`: the villager explains. Subject `work.hunter.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter.risk.ask_who/1   [91 chars]
    en  The florist. She's up before me and she's the only one who'd notice the shed door unopened.
    >>  ............................................
    pt  À florista. Ela levanta antes de mim e é a única que repararia na porta do galpão fechada.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.risk.ask_who/2   [86 chars]
    en  Nobody, until last year. Now somebody, %1$s, and it has changed how the mornings feel.
    >>  ............................................
    pt  Ninguém, até ano passado. Agora alguém, %1$s, e isso mudou como as manhãs parecem.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the tracks."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.hunter.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.risk.respond.leave   [36 chars]
    en  I'll let you get back to the tracks.
    >>  ............................................
    pt  Vou deixar você voltar aos rastros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the tracks."
       spoken on: conversations.topic.work.hunter.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.left`: the villager accepts. Subject `work.hunter.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter.emptied_wood.blocked.respond / leave; conversations.scene.work.hunter.emptied_wood.succeeded.respond / leave; conversations.scene.work.hunter.followup / leave; conversations.scene.work.hunter.lost_track.failed.respond / leave; conversations.scene.work.hunter.lost_track.remembered.respond / leave; conversations.scene.work.hunter.robbed_line.blocked.respond / leave; conversations.scene.work.hunter.robbed_line.succeeded.respond / leave; conversations.topic.work.hunter.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.hunter.emptied_wood.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.hunter.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.hunter.task` — e.g. "Out before dawn and back with nothing. That's four days in a row and I've started counting."


```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.hunter.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.hunter.task.respond   [29 chars]
    en  That's the morning's account.
    >>  ............................................
    pt  É o balanço da manhã.
    >>  ............................................
```


### Button `ask_four` — "Four days of nothing — is that unusual?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter.task` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter.task.ask_four` — accepted phrasings: "four days of nothing — is that unusual"
  - the message must contain one of: `four`, `unusual`, `empty`
  - scored words: `four`(1.2), `unusual`(1.5), `empty`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.task.respond.ask_four
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.task.respond.ask_four   [39 chars]
    en  Four days of nothing — is that unusual?
    >>  ............................................
    pt  Quatro dias sem nada — isso é incomum?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter.task.ask_four`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the woods telling you lately?" | "Good hunting."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.task.ask_four
WHO    VILLAGER — what the player reads after pressing "Four days of nothing — is that unusual?"
       spoken on: conversations.topic.work.hunter.task.respond, button `ask_four`
       leaves the player on: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.task.ask_four`: the villager explains. Subject `work.hunter.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter.task.ask_four/1   [82 chars]
    en  In spring, no. In autumn it would mean something has moved in that shouldn't have.
    >>  ............................................
    pt  Na primavera, não. No outono significaria que algo entrou aqui que não devia.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.task.ask_four/2   [78 chars]
    en  It's the fourth day that's unusual. Three is a week; four is a question, %1$s.
    >>  ............................................
    pt  É o quarto dia que é incomum. Três é uma semana; quatro é uma pergunta, %1$s.
    >>  ............................................
```


### Button `offer_hands` — "I'll take the north half of the snares."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.hunter.task` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter.task.offer_hands` — accepted phrasings: "i'll take the north half of the snares"
  - the message must contain one of: `snares`, `half`, `north`
  - scored words: `snares`(1.5), `half`(1.5), `north`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.task.respond.offer_hands   [39 chars]
    en  I'll take the north half of the snares.
    >>  ............................................
    pt  Eu pego a metade norte das armadilhas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.hunter.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.hunter.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the woods telling you lately?" | "Good hunting."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I'll take the north half of the snares."
       spoken on: conversations.topic.work.hunter.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.task.offer_hands`: the villager accepts. Subject `work.hunter.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter.task.offer_hands/1   [79 chars]
    en  ...Take the north half. Reset anything sprung and don't touch anything holding.
    >>  ............................................
    pt  ...Pegue a metade norte. Rearme o que disparou e não toque no que estiver segurando.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.task.offer_hands/2   [96 chars]
    en  You'd halve my morning, %1$s. Count them out loud — twenty-two, and I'll know if you missed one.
    >>  ............................................
    pt  Você cortaria minha manhã pela metade, %1$s. Conte em voz alta — vinte e duas, e eu vou saber se faltou.
    >>  ............................................
```


### Button `ask_worth` — "Why walk the line if it isn't worth it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter.task` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter.task.ask_worth` — accepted phrasings: "why walk the line if it isn't worth it"
  - the message must contain one of: `worth`, `cruelty`
  - scored words: `worth`(1.2), `cruelty`(1.5), `why`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.task.respond.ask_worth
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.task.respond.ask_worth   [39 chars]
    en  Why walk the line if it isn't worth it?
    >>  ............................................
    pt  Por que andar a linha se não vale?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter.task.ask_worth`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the woods telling you lately?" | "Good hunting."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.task.ask_worth
WHO    VILLAGER — what the player reads after pressing "Why walk the line if it isn't worth it?"
       spoken on: conversations.topic.work.hunter.task.respond, button `ask_worth`
       leaves the player on: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.task.ask_worth`: the villager explains. Subject `work.hunter.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter.task.ask_worth/1   [89 chars]
    en  Because a snare left holding is a cruelty, and I'd not be able to look at the line again.
    >>  ............................................
    pt  Porque uma armadilha deixada segurando é crueldade, e eu não conseguiria olhar pra linha de novo.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.task.ask_worth/2   [85 chars]
    en  Because the day I skip is the day one is holding, %1$s. That's the entire discipline.
    >>  ............................................
    pt  Porque o dia que eu pular é o dia em que uma está segurando, %1$s. É toda a disciplina.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the tracks."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.hunter.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.task.respond.leave   [36 chars]
    en  I'll let you get back to the tracks.
    >>  ............................................
    pt  Vou deixar você voltar aos rastros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the tracks."
       spoken on: conversations.topic.work.hunter.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.left`: the villager accepts. Subject `work.hunter.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter.emptied_wood.blocked.respond / leave; conversations.scene.work.hunter.emptied_wood.succeeded.respond / leave; conversations.scene.work.hunter.followup / leave; conversations.scene.work.hunter.lost_track.failed.respond / leave; conversations.scene.work.hunter.lost_track.remembered.respond / leave; conversations.scene.work.hunter.robbed_line.blocked.respond / leave; conversations.scene.work.hunter.robbed_line.succeeded.respond / leave; conversations.topic.work.hunter.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.hunter.emptied_wood.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.hunter.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.hunter.village` — e.g. "Meat through the lean months, and I decide how lean they get. That framing is mine and nobody else's."


```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.hunter.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.hunter.village.respond   [27 chars]
    en  That's what I've done here.
    >>  ............................................
    pt  É o que eu fiz aqui.
    >>  ............................................
```


### Button `ask_lean` — "How lean do they get?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter.village` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter.village.ask_lean` — accepted phrasings: "how lean do they get"
  - the message must contain one of: `lean`, `thin`, `months`
  - scored words: `lean`(1.5), `thin`(1.2), `months`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.village.respond.ask_lean
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.village.respond.ask_lean   [21 chars]
    en  How lean do they get?
    >>  ............................................
    pt  Quão magros ficam?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter.village.ask_lean`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the woods telling you lately?" | "Good hunting."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.village.ask_lean
WHO    VILLAGER — what the player reads after pressing "How lean do they get?"
       spoken on: conversations.topic.work.hunter.village.respond, button `ask_lean`
       leaves the player on: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.village.ask_lean`: the villager explains. Subject `work.hunter.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter.village.ask_lean/1   [92 chars]
    en  Thin, not hungry. There's a line between those two and I've kept us on the right side of it.
    >>  ............................................
    pt  Magros, não famintos. Tem uma linha entre os dois e eu nos mantive do lado certo.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.village.ask_lean/2   [100 chars]
    en  That depends on February and on me, %1$s, and I take February more seriously than February takes me.
    >>  ............................................
    pt  Depende de fevereiro e de mim, %1$s, e eu levo fevereiro mais a sério do que ele me leva.
    >>  ............................................
```


### Button `say_thanks` — "A valley full of deer is the whole achievement."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.hunter.village` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter.village.say_thanks` — accepted phrasings: "a valley full of deer is the whole achievement"
  - the message must contain one of: `deer`, `achievement`, `remaining`
  - scored words: `deer`(1.5), `achievement`(1.2), `remaining`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.village.respond.say_thanks   [47 chars]
    en  A valley full of deer is the whole achievement.
    >>  ............................................
    pt  Um vale cheio de cervos é a conquista inteira.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.hunter.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.hunter.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the woods telling you lately?" | "Good hunting."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "A valley full of deer is the whole achievement."
       spoken on: conversations.topic.work.hunter.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.village.say_thanks`: the villager accepts. Subject `work.hunter.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter.village.say_thanks/1   [96 chars]
    en  ...That's it. That's the sentence. Nineteen years and you're the first to name the right number.
    >>  ............................................
    pt  ...É isso. É a frase. Dezenove anos e você é o primeiro a nomear o número certo.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.village.say_thanks/2   [98 chars]
    en  Everyone counts what I bring in, %1$s. You've counted what's left out there, and that's the trade.
    >>  ............................................
    pt  Todos contam o que eu trago, %1$s. Você contou o que ficou lá fora, e é esse o ofício.
    >>  ............................................
```


### Button `ask_framed` — "Nobody has framed it that way?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter.village` · offered only once the villager has actually said `work:hunter`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter.village.ask_framed` — accepted phrasings: "nobody has framed it that way"
  - the message must contain one of: `framed`, `understood`
  - scored words: `framed`(1.5), `way`(0.6), `understood`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.village.respond.ask_framed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.village.respond.ask_framed   [30 chars]
    en  Nobody has framed it that way?
    >>  ............................................
    pt  Ninguém colocou assim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter.village.ask_framed`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the woods telling you lately?" | "Good hunting."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.village.ask_framed
WHO    VILLAGER — what the player reads after pressing "Nobody has framed it that way?"
       spoken on: conversations.topic.work.hunter.village.respond, button `ask_framed`
       leaves the player on: conversations.topic.work.hunter.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.village.ask_framed`: the villager explains. Subject `work.hunter.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter.village.ask_framed/1   [86 chars]
    en  They frame it as bringing food in. Which is true and is the smaller half of what I do.
    >>  ............................................
    pt  Colocam como trazer comida. O que é verdade e é a metade menor do que eu faço.
    >>  ............................................
  dialogue.conversations.work.prof.hunter.village.ask_framed/2   [84 chars]
    en  They think of me as a man who kills things, %1$s. I'm a man who counts them, mostly.
    >>  ............................................
    pt  Pensam em mim como um homem que mata coisas, %1$s. Sou um homem que conta, principalmente.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the tracks."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.hunter.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter.village.respond.leave   [36 chars]
    en  I'll let you get back to the tracks.
    >>  ............................................
    pt  Vou deixar você voltar aos rastros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the tracks."
       spoken on: conversations.topic.work.hunter.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter.left`: the villager accepts. Subject `work.hunter.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter.emptied_wood.blocked.respond / leave; conversations.scene.work.hunter.emptied_wood.succeeded.respond / leave; conversations.scene.work.hunter.followup / leave; conversations.scene.work.hunter.lost_track.failed.respond / leave; conversations.scene.work.hunter.lost_track.remembered.respond / leave; conversations.scene.work.hunter.robbed_line.blocked.respond / leave; conversations.scene.work.hunter.robbed_line.succeeded.respond / leave; conversations.topic.work.hunter.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.hunter.emptied_wood.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

