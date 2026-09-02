# Work talk with a cultist

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.cultist.empty_vigil.failed.respond`](#conversations-scene-work-cultist-empty-vigil-failed-respond)
- [`conversations.scene.work.cultist.followup`](#conversations-scene-work-cultist-followup)
- [`conversations.scene.work.cultist.unanswered_question.active.respond`](#conversations-scene-work-cultist-unanswered-question-active-respond)
- [`conversations.scene.work.cultist.unanswered_question.succeeded.respond`](#conversations-scene-work-cultist-unanswered-question-succeeded-respond)
- [`conversations.scene.work.cultist.village_suspicion.blocked.respond`](#conversations-scene-work-cultist-village-suspicion-blocked-respond)
- [`conversations.scene.work.cultist.village_suspicion.succeeded.respond`](#conversations-scene-work-cultist-village-suspicion-succeeded-respond)
- [`conversations.topic.work.cultist.craft.respond`](#conversations-topic-work-cultist-craft-respond)
- [`conversations.topic.work.cultist.followup`](#conversations-topic-work-cultist-followup)
- [`conversations.topic.work.cultist.future.respond`](#conversations-topic-work-cultist-future-respond)
- [`conversations.topic.work.cultist.respond`](#conversations-topic-work-cultist-respond)
- [`conversations.topic.work.cultist.risk.respond`](#conversations-topic-work-cultist-risk-respond)
- [`conversations.topic.work.cultist.task.respond`](#conversations-topic-work-cultist-task-respond)
- [`conversations.topic.work.cultist.village.respond`](#conversations-topic-work-cultist-village-respond)

---

## `conversations.scene.work.cultist.empty_vigil.failed.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.cultist.empty_vigil.failed` — e.g. "I kept %2$s and nothing came of it, and I have told exactly one person that, and now two."


```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.empty_vigil.failed.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.cultist.empty_vigil.failed.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.cultist.empty_vigil.failed.respond   [10 chars]
    en  The vigil.
    >>  ............................................
    pt  A vigília.
    >>  ............................................
```


### Button `ask_what_it_means` — "What do you make of that?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.cultist.empty_vigil.failed` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cultist.empty_vigil.failed.ask_what_it_means` — accepted phrasings: "what do you make of that"; "what do you make of that"; "how do you read that outcome"
  - the message must contain one of: `make`, `read`, `outcome`
  - scored words: `make`(1.8), `read`(1.8), `outcome`(1.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.empty_vigil.failed.respond.ask_what_it_means
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cultist.empty_vigil.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cultist.empty_vigil.failed.respond.ask_what_it_means   [25 chars]
    en  What do you make of that?
    >>  ............................................
    pt  O que você conclui disso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.cultist.vigil`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.cultist.empty_vigil"}
- Then opens: `conversations.scene.work.cultist.followup`
- …where the player's next choices will be: "What's the hardest part of being misread?" | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.empty_vigil.failed.answered
WHO    VILLAGER — what the player reads after pressing "What do you make of that?"
       spoken on: conversations.scene.work.cultist.empty_vigil.failed.respond, button `ask_what_it_means`
       leaves the player on: conversations.scene.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.empty_vigil.failed.answered`: the villager explains. Subject `work.cultist.vigil`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cultist.empty_vigil.failed.answered/1   [101 chars]
    en  Three things, and they contradict each other, and I am refusing to pick one until the winter is over.
    >>  ............................................
    pt  Três coisas, e elas se contradizem, e me recuso a escolher uma antes do inverno acabar.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.empty_vigil.failed.answered/2   [103 chars]
    en  That I was owed nothing. Which I already knew and had apparently been quietly assuming was a formality.
    >>  ............................................
    pt  Que eu não tinha nada a receber. O que eu já sabia e, aparentemente, vinha assumindo em silêncio como formalidade.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.empty_vigil.failed.answered/3   [130 chars]
    en  The honest answer is that a night can be spent well and still produce nothing, and I would rather hold that than invent a meaning.
    >>  ............................................
    pt  A resposta honesta é que uma noite pode ser bem gasta e ainda assim não produzir nada, e prefiro segurar isso a inventar um sentido.
    >>  ............................................
```


### Button `sit_with_her` — "That sounds lonely."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.cultist.empty_vigil.failed` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cultist.empty_vigil.failed.sit_with_her` — accepted phrasings: "that sounds lonely"; "that sounds lonely"; "it sounds like a lonely night"
  - the message must contain one of: `lonely`, `sounds`
  - scored words: `lonely`(1.8), `sounds`(1.8), `like`(0.8), `night`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.empty_vigil.failed.respond.sit_with_her
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cultist.empty_vigil.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cultist.empty_vigil.failed.respond.sit_with_her   [19 chars]
    en  That sounds lonely.
    >>  ............................................
    pt  Isso parece solitário.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +4, trust +2  _(recorded under topic `work.cultist.vigil`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.cultist.empty_vigil"}
- Then opens: `conversations.scene.work.cultist.followup`
- …where the player's next choices will be: "What's the hardest part of being misread?" | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.empty_vigil.failed.softened
WHO    VILLAGER — what the player reads after pressing "That sounds lonely."
       spoken on: conversations.scene.work.cultist.empty_vigil.failed.respond, button `sit_with_her`
       leaves the player on: conversations.scene.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.empty_vigil.failed.softened`: the villager accepts. Subject `work.cultist.vigil`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cultist.empty_vigil.failed.softened/1   [96 chars]
    en  It was. I had prepared for frightening and got lonely instead, and I was not ready for the swap.
    >>  ............................................
    pt  Foi. Eu tinha me preparado para assustador e recebi solitário no lugar, e não estava pronta para a troca.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.empty_vigil.failed.softened/2   [103 chars]
    en  Yes. And I would do it again, which is the part I find hardest to explain to anybody, including myself.
    >>  ............................................
    pt  Sim. E eu faria de novo, que é a parte mais difícil de explicar a qualquer um, inclusive a mim.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.empty_vigil.failed.softened/3   [98 chars]
    en  Thank you for saying so rather than asking what I was waiting for. Everybody else asks that first.
    >>  ............................................
    pt  Obrigada por dizer isso em vez de perguntar o que eu esperava. Todo mundo pergunta isso primeiro.
    >>  ............................................
```


### Button `leave` — "I'll leave you to your reading."

*stance family `exit` · tone `plain` · answers the beat(s) `work.cultist.empty_vigil.failed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.empty_vigil.failed.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cultist.empty_vigil.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cultist.empty_vigil.failed.respond.leave   [31 chars]
    en  I'll leave you to your reading.
    >>  ............................................
    pt  Vou deixar você com sua leitura.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to your reading."
       spoken on: conversations.scene.work.cultist.empty_vigil.failed.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.left`: the villager accepts. Subject `work.cultist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cultist.followup / leave; conversations.scene.work.cultist.unanswered_question.active.respond / leave; conversations.scene.work.cultist.unanswered_question.succeeded.respond / leave; conversations.scene.work.cultist.village_suspicion.blocked.respond / leave; conversations.scene.work.cultist.village_suspicion.succeeded.respond / leave; conversations.topic.work.cultist.craft.respond / leave; conversations.topic.work.cultist.followup / leave; conversations.topic.work.cultist.future.respond / leave …and 4 more
```

```text
  dialogue.conversations.work.prof.cultist.leave/1   [33 chars]
    en  Reading. Yes. That is what it is.
    >>  ............................................
    pt  Ler. Sim. É isso mesmo.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.leave/2   [64 chars]
    en  Take a pamphlet on your way out, %1$s. Everyone does eventually.
    >>  ............................................
    pt  Leve um panfleto na saída, %1$s. Todo mundo acaba levando.
    >>  ............................................
```

---


## `conversations.scene.work.cultist.followup`

**Reached from 9 route(s):** `conversations.scene.work.cultist.empty_vigil.failed.respond` / `ask_what_it_means`; `conversations.scene.work.cultist.empty_vigil.failed.respond` / `sit_with_her`; `conversations.scene.work.cultist.unanswered_question.active.respond` / `encourage_asking`; `conversations.scene.work.cultist.unanswered_question.active.respond` / `ask_the_question`; `conversations.scene.work.cultist.unanswered_question.succeeded.respond` / `note_persistence`; `conversations.scene.work.cultist.village_suspicion.blocked.respond` / `ask_what_they_fear`; `conversations.scene.work.cultist.village_suspicion.blocked.respond` / `say_it_is_unfair`; `conversations.scene.work.cultist.village_suspicion.blocked.respond` / `suggest_explaining`; `conversations.scene.work.cultist.village_suspicion.succeeded.respond` / `ask_what_changed`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.cultist.empty_vigil.failed.answered` — e.g. "Three things, and they contradict each other, and I am refusing to pick one until the winter is over."
- `conversations.scene.work.cultist.empty_vigil.failed.softened` — e.g. "It was. I had prepared for frightening and got lonely instead, and I was not ready for the swap."
- `conversations.scene.work.cultist.unanswered_question.active.declined_politely` — e.g. "I would rather not put it in your head. It is the sort of question that stays, and you did not come here for that."
- `conversations.scene.work.cultist.unanswered_question.active.resolved` — e.g. "I will. Quietly, and to one person at a time, which is slower and much harder to refuse."
- `conversations.scene.work.cultist.unanswered_question.succeeded.acknowledged` — e.g. "Or stubbornness. The two look identical from outside and I have given up trying to tell which one I have."
- `conversations.scene.work.cultist.village_suspicion.blocked.explained` — e.g. "Something enormous. That is the trouble with a rumour — it never says what, so it can never be answered."
- `conversations.scene.work.cultist.village_suspicion.blocked.resisted` — e.g. "I tried that. An explanation given to a frightened room becomes a confession by the time it reaches the next room."
- `conversations.scene.work.cultist.village_suspicion.blocked.steadied` — e.g. "It is, and you are the first person in a season to say so where somebody might hear you."
- `conversations.scene.work.cultist.village_suspicion.succeeded.explained` — e.g. "Time, and me being boring in public on purpose for eleven months. Nothing noble. Just attendance."


```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.cultist.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.cultist.followup   [14 chars]
    en  Is there more?
    >>  ............................................
    pt  Tem mais?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of being misread?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.cultist.*` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cultist.followup.ask_more` — accepted phrasings: "whats the hardest part of being misread"; "what is the hardest part of being misread"; "hardest thing about people misreading you"
  - the message must contain one of: `hardest`, `misread`
  - scored words: `hardest`(1.8), `misread`(1.8), `whats`(0.8), `part`(0.8), `being`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cultist.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cultist.followup.ask_more   [41 chars]
    en  What's the hardest part of being misread?
    >>  ............................................
    pt  Qual é a parte mais difícil de ser mal interpretada?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cultist.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cultist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What is it you're actually waiting for?" | "Enjoy the reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of being misread?"
       spoken on: conversations.scene.work.cultist.followup, button `ask_more`
       leaves the player on: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.hard`: the villager explains. Subject `work.cultist.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.cultist.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.cultist.hard/1   [61 chars]
    en  That we're odd, and that the chanting carries. Both are fair.
    >>  ............................................
    pt  Que somos esquisitos, e que o cântico se ouve longe. Os dois são justos.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.hard/2   [73 chars]
    en  They think what people think about anything they aren't invited to, %1$s.
    >>  ............................................
    pt  Pensam o que se pensa de qualquer coisa pra qual não fomos convidados, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll leave you to it."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.cultist.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cultist.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cultist.followup.leave   [21 chars]
    en  I'll leave you to it.
    >>  ............................................
    pt  Vou deixar você nisso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to it."
       spoken on: conversations.scene.work.cultist.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.left`: the villager accepts. Subject `work.cultist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cultist.empty_vigil.failed.respond / leave; conversations.scene.work.cultist.unanswered_question.active.respond / leave; conversations.scene.work.cultist.unanswered_question.succeeded.respond / leave; conversations.scene.work.cultist.village_suspicion.blocked.respond / leave; conversations.scene.work.cultist.village_suspicion.succeeded.respond / leave; conversations.topic.work.cultist.craft.respond / leave; conversations.topic.work.cultist.followup / leave; conversations.topic.work.cultist.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.cultist.empty_vigil.failed.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.cultist.unanswered_question.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.cultist.unanswered_question.active` — e.g. "There is %2$s in the text and when I raise it, the room changes subject. Every time."


```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.unanswered_question.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.cultist.unanswered_question.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.cultist.unanswered_question.active.respond   [13 chars]
    en  Your reading.
    >>  ............................................
    pt  Sua leitura.
    >>  ............................................
```


### Button `encourage_asking` — "Keep asking it anyway."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cultist.unanswered_question.active` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cultist.unanswered_question.active.encourage_asking` — accepted phrasings: "keep asking it anyway"; "keep asking it anyway"; "ask the question again next time"
  - the message must contain one of: `asking`, `question`, `again`
  - scored words: `asking`(1.8), `question`(1.8), `again`(1.8), `keep`(0.8), `anyway`(0.8), `ask`(0.8), `next`(0.8), `time`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.unanswered_question.active.respond.encourage_asking
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cultist.unanswered_question.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cultist.unanswered_question.active.respond.encourage_asking   [22 chars]
    en  Keep asking it anyway.
    >>  ............................................
    pt  Continue perguntando mesmo assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, trust +1  _(recorded under topic `work.cultist.doubt`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.cultist.unanswered_question"}
- Then opens: `conversations.scene.work.cultist.followup`
- …where the player's next choices will be: "What's the hardest part of being misread?" | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.unanswered_question.active.resolved
WHO    VILLAGER — what the player reads after pressing "Keep asking it anyway."
       spoken on: conversations.scene.work.cultist.unanswered_question.active.respond, button `encourage_asking`
       leaves the player on: conversations.scene.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.unanswered_question.active.resolved`: the villager accepts. Subject `work.cultist.doubt`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cultist.unanswered_question.active.resolved/1   [88 chars]
    en  I will. Quietly, and to one person at a time, which is slower and much harder to refuse.
    >>  ............................................
    pt  Vou. Em voz baixa, e a uma pessoa por vez, o que é mais lento e muito mais difícil de recusar.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.unanswered_question.active.resolved/2   [132 chars]
    en  Yes. A question that is only asked once is a mood. A question asked eleven times is a problem, and problems eventually get answered.
    >>  ............................................
    pt  Sim. Uma pergunta feita só uma vez é um humor. Uma pergunta feita onze vezes é um problema, e problemas acabam sendo respondidos.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.unanswered_question.active.resolved/3   [92 chars]
    en  You are giving me permission I should not have needed, and I am taking it anyway. Thank you.
    >>  ............................................
    pt  Você está me dando uma permissão de que eu não deveria precisar, e eu vou aceitar mesmo assim. Obrigada.
    >>  ............................................
```


### Button `ask_the_question` — "What's the question itself?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cultist.unanswered_question.active` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cultist.unanswered_question.active.ask_the_question` — accepted phrasings: "whats the question itself"; "what is the question itself"; "tell me the actual question"
  - the message must contain one of: `itself`, `actual`, `question`
  - scored words: `itself`(1.8), `actual`(1.8), `question`(1.8), `whats`(0.8), `tell`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.unanswered_question.active.respond.ask_the_question
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cultist.unanswered_question.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cultist.unanswered_question.active.respond.ask_the_question   [27 chars]
    en  What's the question itself?
    >>  ............................................
    pt  Qual é a pergunta em si?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cultist.doubt`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.cultist.unanswered_question"}
- Then opens: `conversations.scene.work.cultist.followup`
- …where the player's next choices will be: "What's the hardest part of being misread?" | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.unanswered_question.active.declined_politely
WHO    VILLAGER — what the player reads after pressing "What's the question itself?"
       spoken on: conversations.scene.work.cultist.unanswered_question.active.respond, button `ask_the_question`
       leaves the player on: conversations.scene.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.unanswered_question.active.declined_politely`: the villager deflects. Subject `work.cultist.doubt`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cultist.unanswered_question.active.declined_politely/1   [114 chars]
    en  I would rather not put it in your head. It is the sort of question that stays, and you did not come here for that.
    >>  ............................................
    pt  Prefiro não colocar isso na sua cabeça. É o tipo de pergunta que fica, e você não veio aqui para isso.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.unanswered_question.active.declined_politely/2   [90 chars]
    en  Ask me in a year. If I still have it by then it will have earned the right to be repeated.
    >>  ............................................
    pt  Me pergunte daqui a um ano. Se eu ainda tiver essa pergunta, ela terá ganhado o direito de ser repetida.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.unanswered_question.active.declined_politely/3   [114 chars]
    en  It is four words long and it is not interesting out of context, which is the safest true thing I can say about it.
    >>  ............................................
    pt  Tem quatro palavras e não é interessante fora de contexto, que é a coisa verdadeira mais segura que eu sei dizer.
    >>  ............................................
```


### Button `leave` — "I'll leave you to your reading."

*stance family `exit` · tone `plain` · answers the beat(s) `work.cultist.unanswered_question.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.unanswered_question.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cultist.unanswered_question.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cultist.unanswered_question.active.respond.leave   [31 chars]
    en  I'll leave you to your reading.
    >>  ............................................
    pt  Vou deixar você com sua leitura.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to your reading."
       spoken on: conversations.scene.work.cultist.unanswered_question.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.left`: the villager accepts. Subject `work.cultist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cultist.empty_vigil.failed.respond / leave; conversations.scene.work.cultist.followup / leave; conversations.scene.work.cultist.unanswered_question.succeeded.respond / leave; conversations.scene.work.cultist.village_suspicion.blocked.respond / leave; conversations.scene.work.cultist.village_suspicion.succeeded.respond / leave; conversations.topic.work.cultist.craft.respond / leave; conversations.topic.work.cultist.followup / leave; conversations.topic.work.cultist.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.cultist.empty_vigil.failed.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.cultist.unanswered_question.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.cultist.unanswered_question.succeeded` — e.g. "Somebody finally answered it. Badly, and out of irritation, and it was still the most useful hour of my year."


```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.unanswered_question.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.cultist.unanswered_question.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.cultist.unanswered_question.succeeded.respond   [14 chars]
    en  That question.
    >>  ............................................
    pt  Aquela pergunta.
    >>  ............................................
```


### Button `note_persistence` — "Eleven times is persistence."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.cultist.unanswered_question.succeeded` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cultist.unanswered_question.succeeded.note_persistence` — accepted phrasings: "eleven times is persistence"; "eleven times is real persistence"; "that is persistence"
  - the message must contain one of: `persistence`, `eleven`
  - scored words: `persistence`(1.8), `eleven`(1.8), `times`(0.8), `real`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.unanswered_question.succeeded.respond.note_persistence
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cultist.unanswered_question.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cultist.unanswered_question.succeeded.respond.note_persistence   [28 chars]
    en  Eleven times is persistence.
    >>  ............................................
    pt  Onze vezes é persistência.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.cultist.doubt`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.cultist.unanswered_question"}
- Then opens: `conversations.scene.work.cultist.followup`
- …where the player's next choices will be: "What's the hardest part of being misread?" | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.unanswered_question.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Eleven times is persistence."
       spoken on: conversations.scene.work.cultist.unanswered_question.succeeded.respond, button `note_persistence`
       leaves the player on: conversations.scene.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.unanswered_question.succeeded.acknowledged`: the villager accepts. Subject `work.cultist.doubt`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cultist.unanswered_question.succeeded.acknowledged/1   [105 chars]
    en  Or stubbornness. The two look identical from outside and I have given up trying to tell which one I have.
    >>  ............................................
    pt  Ou teimosia. As duas parecem idênticas de fora e eu desisti de tentar saber qual das duas eu tenho.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.unanswered_question.succeeded.acknowledged/2   [111 chars]
    en  Thank you. It is the only virtue I am confident of, and it is the cheap one, because it costs nothing but time.
    >>  ............................................
    pt  Obrigada. É a única virtude de que tenho certeza, e é a barata, porque só custa tempo.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.unanswered_question.succeeded.acknowledged/3   [116 chars]
    en  I nearly stopped at eight. I want that in the record, because the version where I never wavered is not the true one.
    >>  ............................................
    pt  Quase parei na oitava. Quero isso registrado, porque a versão em que eu nunca vacilei não é a verdadeira.
    >>  ............................................
```


### Button `leave` — "I'll leave you to your reading."

*stance family `exit` · tone `plain` · answers the beat(s) `work.cultist.unanswered_question.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.unanswered_question.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cultist.unanswered_question.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cultist.unanswered_question.succeeded.respond.leave   [31 chars]
    en  I'll leave you to your reading.
    >>  ............................................
    pt  Vou deixar você com sua leitura.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to your reading."
       spoken on: conversations.scene.work.cultist.unanswered_question.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.left`: the villager accepts. Subject `work.cultist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cultist.empty_vigil.failed.respond / leave; conversations.scene.work.cultist.followup / leave; conversations.scene.work.cultist.unanswered_question.active.respond / leave; conversations.scene.work.cultist.village_suspicion.blocked.respond / leave; conversations.scene.work.cultist.village_suspicion.succeeded.respond / leave; conversations.topic.work.cultist.craft.respond / leave; conversations.topic.work.cultist.followup / leave; conversations.topic.work.cultist.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.cultist.empty_vigil.failed.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.cultist.village_suspicion.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.cultist.village_suspicion.blocked` — e.g. "There was %3$s again. At %2$s, in daylight, where everybody could watch it happen."


```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.village_suspicion.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.cultist.village_suspicion.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.cultist.village_suspicion.blocked.respond   [12 chars]
    en  The village.
    >>  ............................................
    pt  A vila.
    >>  ............................................
```


### Button `ask_what_they_fear` — "What do they think you're doing?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cultist.village_suspicion.blocked` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cultist.village_suspicion.blocked.ask_what_they_fear` — accepted phrasings: "what do they think youre doing"; "what do they think you are doing"; "what are people afraid of exactly"
  - the message must contain one of: `think`, `afraid`, `people`
  - scored words: `think`(1.8), `afraid`(1.8), `people`(1.8), `youre`(0.8), `doing`(0.8), `exactly`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.village_suspicion.blocked.respond.ask_what_they_fear
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cultist.village_suspicion.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cultist.village_suspicion.blocked.respond.ask_what_they_fear   [32 chars]
    en  What do they think you're doing?
    >>  ............................................
    pt  O que acham que você faz?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cultist.suspicion`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.cultist.village_suspicion"}
- Then opens: `conversations.scene.work.cultist.followup`
- …where the player's next choices will be: "What's the hardest part of being misread?" | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.village_suspicion.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What do they think you're doing?"
       spoken on: conversations.scene.work.cultist.village_suspicion.blocked.respond, button `ask_what_they_fear`
       leaves the player on: conversations.scene.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.village_suspicion.blocked.explained`: the villager explains. Subject `work.cultist.suspicion`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cultist.village_suspicion.blocked.explained/1   [104 chars]
    en  Something enormous. That is the trouble with a rumour — it never says what, so it can never be answered.
    >>  ............................................
    pt  Algo enorme. É esse o problema de um boato — ele nunca diz o quê, então nunca pode ser respondido.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.village_suspicion.blocked.explained/2   [139 chars]
    en  Reading, mostly. I read old things in a language they cannot check, and everything unverifiable becomes whatever the reader feared already.
    >>  ............................................
    pt  Leitura, na maior parte. Leio coisas antigas numa língua que eles não podem conferir, e tudo que não se verifica vira o que o ouvinte já temia.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.village_suspicion.blocked.explained/3   [118 chars]
    en  If one of them asked me plainly, I would tell them plainly, and the conversation would take four minutes. Nobody asks.
    >>  ............................................
    pt  Se um deles perguntasse com todas as letras, eu responderia com todas as letras, e a conversa levaria quatro minutos. Ninguém pergunta.
    >>  ............................................
```


### Button `say_it_is_unfair` — "That's unfair to you."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.cultist.village_suspicion.blocked` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cultist.village_suspicion.blocked.say_it_is_unfair` — accepted phrasings: "thats unfair to you"; "that is unfair to you"; "you deserve better than that treatment"
  - the message must contain one of: `unfair`, `deserve`, `treatment`
  - scored words: `unfair`(1.8), `deserve`(1.8), `treatment`(1.8), `thats`(0.8), `better`(0.8), `than`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.village_suspicion.blocked.respond.say_it_is_unfair
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cultist.village_suspicion.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cultist.village_suspicion.blocked.respond.say_it_is_unfair   [21 chars]
    en  That's unfair to you.
    >>  ............................................
    pt  Isso é injusto com você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.cultist.suspicion.stood_up`, budget `standard`, replay policy `once`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `work.cultist.suspicion`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.cultist.village_suspicion"}
- Then opens: `conversations.scene.work.cultist.followup`
- …where the player's next choices will be: "What's the hardest part of being misread?" | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.village_suspicion.blocked.steadied
WHO    VILLAGER — what the player reads after pressing "That's unfair to you."
       spoken on: conversations.scene.work.cultist.village_suspicion.blocked.respond, button `say_it_is_unfair`
       leaves the player on: conversations.scene.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.village_suspicion.blocked.steadied`: the villager accepts. Subject `work.cultist.suspicion`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cultist.village_suspicion.blocked.steadied/1   [88 chars]
    en  It is, and you are the first person in a season to say so where somebody might hear you.
    >>  ............................................
    pt  É, e você é a primeira pessoa em uma estação a dizer isso onde alguém podia ouvir.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.village_suspicion.blocked.steadied/2   [121 chars]
    en  Thank you. I had decided that being fair about it myself was the whole job, and it turns out it is easier when it is not.
    >>  ............................................
    pt  Obrigada. Eu tinha decidido que ser justa sozinha era o trabalho inteiro, e acontece que é mais fácil quando não é.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.village_suspicion.blocked.steadied/3   [134 chars]
    en  I am careful with that word. Unfair means somebody chose. I would rather believe they are only frightened, because frightened can end.
    >>  ............................................
    pt  Sou cuidadosa com essa palavra. Injusto quer dizer que alguém escolheu. Prefiro acreditar que só estão com medo, porque medo pode acabar.
    >>  ............................................
```


### Button `suggest_explaining` — "Explain it to them openly."

*stance family `candor` · tone `plain` · outcome `resisted` · answers the beat(s) `work.cultist.village_suspicion.blocked` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cultist.village_suspicion.blocked.suggest_explaining` — accepted phrasings: "explain it to them openly"; "explain it to them openly"; "tell the village what you actually study"
  - the message must contain one of: `explain`, `openly`, `study`
  - scored words: `explain`(1.8), `openly`(1.8), `study`(1.8), `tell`(0.8), `village`(0.8), `actually`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.village_suspicion.blocked.respond.suggest_explaining
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cultist.village_suspicion.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cultist.village_suspicion.blocked.respond.suggest_explaining   [26 chars]
    en  Explain it to them openly.
    >>  ............................................
    pt  Explique abertamente a eles.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +1  _(recorded under topic `work.cultist.suspicion`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.cultist.village_suspicion"}
- Then opens: `conversations.scene.work.cultist.followup`
- …where the player's next choices will be: "What's the hardest part of being misread?" | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.village_suspicion.blocked.resisted
WHO    VILLAGER — what the player reads after pressing "Explain it to them openly."
       spoken on: conversations.scene.work.cultist.village_suspicion.blocked.respond, button `suggest_explaining`
       leaves the player on: conversations.scene.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.village_suspicion.blocked.resisted`: the villager resists. Subject `work.cultist.suspicion`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cultist.village_suspicion.blocked.resisted/1   [114 chars]
    en  I tried that. An explanation given to a frightened room becomes a confession by the time it reaches the next room.
    >>  ............................................
    pt  Já tentei. Uma explicação dada a uma sala assustada vira confissão até chegar à sala seguinte.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.village_suspicion.blocked.resisted/2   [125 chars]
    en  The difficulty is that half of it is genuinely private. I will not trade my privacy for their comfort and call that fairness.
    >>  ............................................
    pt  A dificuldade é que metade disso é genuinamente privado. Não vou trocar minha privacidade pelo conforto deles e chamar isso de justiça.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.village_suspicion.blocked.resisted/3   [120 chars]
    en  Openly to whom? There is no meeting. There is only forty separate people, each of whom would need it said to them alone.
    >>  ............................................
    pt  Abertamente para quem? Não existe reunião. Existem quarenta pessoas separadas, e para cada uma seria preciso dizer a sós.
    >>  ............................................
```


### Button `leave` — "I'll leave you to your reading."

*stance family `exit` · tone `plain` · answers the beat(s) `work.cultist.village_suspicion.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.village_suspicion.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cultist.village_suspicion.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cultist.village_suspicion.blocked.respond.leave   [31 chars]
    en  I'll leave you to your reading.
    >>  ............................................
    pt  Vou deixar você com sua leitura.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to your reading."
       spoken on: conversations.scene.work.cultist.village_suspicion.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.left`: the villager accepts. Subject `work.cultist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cultist.empty_vigil.failed.respond / leave; conversations.scene.work.cultist.followup / leave; conversations.scene.work.cultist.unanswered_question.active.respond / leave; conversations.scene.work.cultist.unanswered_question.succeeded.respond / leave; conversations.scene.work.cultist.village_suspicion.succeeded.respond / leave; conversations.topic.work.cultist.craft.respond / leave; conversations.topic.work.cultist.followup / leave; conversations.topic.work.cultist.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.cultist.empty_vigil.failed.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.cultist.village_suspicion.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.cultist.village_suspicion.succeeded` — e.g. "Somebody spoke to me at %2$s last week. About the weather. It was the best conversation I have had in a year."


```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.village_suspicion.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.cultist.village_suspicion.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.cultist.village_suspicion.succeeded.respond   [19 chars]
    en  Things at the well.
    >>  ............................................
    pt  As coisas no poço.
    >>  ............................................
```


### Button `ask_what_changed` — "What changed their minds?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.cultist.village_suspicion.succeeded` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.cultist.village_suspicion.succeeded.ask_what_changed` — accepted phrasings: "what changed their minds"; "what changed their minds"; "why did people ease off"
  - the message must contain one of: `changed`, `eased`, `why`
  - scored words: `changed`(1.8), `eased`(1.8), `why`(1.8), `their`(0.8), `minds`(0.8), `people`(0.8), `ease`(0.8), `off`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.village_suspicion.succeeded.respond.ask_what_changed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cultist.village_suspicion.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cultist.village_suspicion.succeeded.respond.ask_what_changed   [25 chars]
    en  What changed their minds?
    >>  ............................................
    pt  O que mudou a cabeça deles?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cultist.suspicion`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.cultist.village_suspicion"}
- Then opens: `conversations.scene.work.cultist.followup`
- …where the player's next choices will be: "What's the hardest part of being misread?" | "I'll leave you to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.village_suspicion.succeeded.explained
WHO    VILLAGER — what the player reads after pressing "What changed their minds?"
       spoken on: conversations.scene.work.cultist.village_suspicion.succeeded.respond, button `ask_what_changed`
       leaves the player on: conversations.scene.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.village_suspicion.succeeded.explained`: the villager explains. Subject `work.cultist.suspicion`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.cultist.village_suspicion.succeeded.explained/1   [97 chars]
    en  Time, and me being boring in public on purpose for eleven months. Nothing noble. Just attendance.
    >>  ............................................
    pt  Tempo, e eu sendo chata em público de propósito por onze meses. Nada nobre. Só presença.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.village_suspicion.succeeded.explained/2   [103 chars]
    en  One woman's roof leaked and I was the person standing there with a ladder. That was the whole reversal.
    >>  ............................................
    pt  O telhado de uma mulher vazou e eu era a pessoa ali com uma escada. Foi essa a virada inteira.
    >>  ............................................
  dialogue.conversations.scene.work.cultist.village_suspicion.succeeded.explained/3   [124 chars]
    en  I stopped trying to be understood and started being reliably present, and it turns out those are not the same errand at all.
    >>  ............................................
    pt  Parei de tentar ser compreendida e passei a estar confiavelmente presente, e acontece que não são a mesma tarefa.
    >>  ............................................
```


### Button `leave` — "I'll leave you to your reading."

*stance family `exit` · tone `plain` · answers the beat(s) `work.cultist.village_suspicion.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.cultist.village_suspicion.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.cultist.village_suspicion.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.cultist.village_suspicion.succeeded.respond.leave   [31 chars]
    en  I'll leave you to your reading.
    >>  ............................................
    pt  Vou deixar você com sua leitura.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to your reading."
       spoken on: conversations.scene.work.cultist.village_suspicion.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.left`: the villager accepts. Subject `work.cultist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cultist.empty_vigil.failed.respond / leave; conversations.scene.work.cultist.followup / leave; conversations.scene.work.cultist.unanswered_question.active.respond / leave; conversations.scene.work.cultist.unanswered_question.succeeded.respond / leave; conversations.scene.work.cultist.village_suspicion.blocked.respond / leave; conversations.topic.work.cultist.craft.respond / leave; conversations.topic.work.cultist.followup / leave; conversations.topic.work.cultist.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.cultist.empty_vigil.failed.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cultist.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cultist.craft` — e.g. "It's memory, mostly. Nothing we hold is written anywhere a fire could reach, so it lives in heads."


```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cultist.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cultist.craft.respond   [25 chars]
    en  That's how it is carried.
    >>  ............................................
    pt  É assim que se carrega.
    >>  ............................................
```


### Button `ask_tested` — "How did she test you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cultist.craft` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cultist.craft.ask_tested` — accepted phrasings: "how did she test you"
  - the message must contain one of: `tested`, `test`
  - scored words: `tested`(1.5), `test`(1.5), `she`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.craft.respond.ask_tested
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.craft.respond.ask_tested   [21 chars]
    en  How did she test you?
    >>  ............................................
    pt  Como ela te testou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cultist.craft.ask_tested`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cultist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What is it you're actually waiting for?" | "Enjoy the reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.craft.ask_tested
WHO    VILLAGER — what the player reads after pressing "How did she test you?"
       spoken on: conversations.topic.work.cultist.craft.respond, button `ask_tested`
       leaves the player on: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.craft.ask_tested`: the villager explains. Subject `work.cultist.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cultist.craft.ask_tested/1   [99 chars]
    en  She told me falsehoods and waited to see whether I'd repeat them outside. I did, once, at nineteen.
    >>  ............................................
    pt  Ela me contava falsidades e esperava pra ver se eu repetia fora. Eu repeti, uma vez, aos dezenove.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.craft.ask_tested/2   [91 chars]
    en  By giving me things to keep and seeing which ones came back to her from other mouths, %1$s.
    >>  ............................................
    pt  Me dando coisas pra guardar e vendo quais voltavam a ela por outras bocas, %1$s.
    >>  ............................................
```


### Button `admire` — "Holding something only in memory takes real discipline."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cultist.craft` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cultist.craft.admire` — accepted phrasings: "holding something only in memory takes real discipline"
  - the message must contain one of: `memory`, `discipline`, `holding`
  - scored words: `memory`(1.5), `discipline`(1.5), `holding`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.craft.respond.admire   [55 chars]
    en  Holding something only in memory takes real discipline.
    >>  ............................................
    pt  Guardar algo só na memória exige disciplina real.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.cultist.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.cultist.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cultist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What is it you're actually waiting for?" | "Enjoy the reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.craft.admire
WHO    VILLAGER — what the player reads after pressing "Holding something only in memory takes real discipline."
       spoken on: conversations.topic.work.cultist.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.craft.admire`: the villager accepts. Subject `work.cultist.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cultist.craft.admire/1   [80 chars]
    en  It takes a person who can be boring for decades. That is the actual requirement.
    >>  ............................................
    pt  Exige uma pessoa que consiga ser entediante por décadas. É esse o requisito real.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.craft.admire/2   [81 chars]
    en  It also means that when I go, some of it goes, %1$s, and we've never solved that.
    >>  ............................................
    pt  Também significa que quando eu for, parte vai junto, %1$s, e nunca resolvemos isso.
    >>  ............................................
```


### Button `ask_nineteen` — "What happened when you repeated it at nineteen?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cultist.craft` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cultist.craft.ask_nineteen` — accepted phrasings: "what happened when you repeated it at nineteen"
  - the message must contain one of: `nineteen`, `repeated`, `punishment`
  - scored words: `nineteen`(1.5), `repeated`(1.2), `punishment`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.craft.respond.ask_nineteen
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.craft.respond.ask_nineteen   [47 chars]
    en  What happened when you repeated it at nineteen?
    >>  ............................................
    pt  O que aconteceu quando você repetiu aos dezenove?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cultist.craft.ask_nineteen`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cultist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What is it you're actually waiting for?" | "Enjoy the reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.craft.ask_nineteen
WHO    VILLAGER — what the player reads after pressing "What happened when you repeated it at nineteen?"
       spoken on: conversations.topic.work.cultist.craft.respond, button `ask_nineteen`
       leaves the player on: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.craft.ask_nineteen`: the villager explains. Subject `work.cultist.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cultist.craft.ask_nineteen/1   [86 chars]
    en  She let me find out for myself that it had been false. That was the entire punishment.
    >>  ............................................
    pt  Ela me deixou descobrir sozinho que era falso. Foi toda a punição.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.craft.ask_nineteen/2   [74 chars]
    en  Nothing. She said nothing for a month, and the month was the lesson, %1$s.
    >>  ............................................
    pt  Nada. Ela não disse nada por um mês, e o mês foi a lição, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to... reading."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cultist.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.craft.respond.leave   [36 chars]
    en  I'll let you get back to... reading.
    >>  ............................................
    pt  Vou deixar você voltar a... ler.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to... reading."
       spoken on: conversations.topic.work.cultist.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.left`: the villager accepts. Subject `work.cultist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cultist.empty_vigil.failed.respond / leave; conversations.scene.work.cultist.followup / leave; conversations.scene.work.cultist.unanswered_question.active.respond / leave; conversations.scene.work.cultist.unanswered_question.succeeded.respond / leave; conversations.scene.work.cultist.village_suspicion.blocked.respond / leave; conversations.scene.work.cultist.village_suspicion.succeeded.respond / leave; conversations.topic.work.cultist.followup / leave; conversations.topic.work.cultist.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.cultist.empty_vigil.failed.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cultist.followup`

**Reached from 20 route(s):** `conversations.scene.work.cultist.followup` / `ask_more`; `conversations.topic.work.cultist.craft.respond` / `ask_tested`; `conversations.topic.work.cultist.craft.respond` / `admire`; `conversations.topic.work.cultist.craft.respond` / `ask_nineteen`; `conversations.topic.work.cultist.future.respond` / `ask_three`; `conversations.topic.work.cultist.future.respond` / `encourage`; `conversations.topic.work.cultist.future.respond` / `ask_none`; `conversations.topic.work.cultist.respond` / `ask_hard`; `conversations.topic.work.cultist.respond` / `value`; `conversations.topic.work.cultist.respond` / `challenge`; `conversations.topic.work.cultist.respond` / `challenge`; `conversations.topic.work.cultist.risk.respond` / `ask_wrong` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cultist.challenge.landed` — e.g. "Some days I don't. Then something answers, and I do again. It's exhausting."
- `conversations.work.prof.cultist.challenge.stung` — e.g. "...You've been to two meetings and read none of the pamphlet."
- `conversations.work.prof.cultist.craft.admire` — e.g. "It takes a person who can be boring for decades. That is the actual requirement."
- `conversations.work.prof.cultist.craft.ask_nineteen` — e.g. "She let me find out for myself that it had been false. That was the entire punishment."
- `conversations.work.prof.cultist.craft.ask_tested` — e.g. "She told me falsehoods and waited to see whether I'd repeat them outside. I did, once, at nineteen."
- `conversations.work.prof.cultist.future.ask_none` — e.g. "Then it ends with me, and ninety years of names end with me, and I'd rather not finish that sentence."
- `conversations.work.prof.cultist.future.ask_three` — e.g. "One talked. One wanted it for the standing. One was perfect and left for the city."
- `conversations.work.prof.cultist.future.encourage` — e.g. "...It is, isn't it. I've spent years arguing for respect when I wanted something smaller."
- `conversations.work.prof.cultist.hard` — e.g. "That we're odd, and that the chanting carries. Both are fair."
- `conversations.work.prof.cultist.risk.ask_before` — e.g. "Stones, in my grandmother's time, in a different valley. I'll leave the account there."
- `conversations.work.prof.cultist.risk.ask_wrong` — e.g. "Then I kept a light burning and hurt nobody. I've decided that's a survivable answer."
- `conversations.work.prof.cultist.risk.sympathise` — e.g. "...It is. And I can't complain about it, because careful is better than what came before careful."
- `conversations.work.prof.cultist.task.ask_lamp` — e.g. "Once, before me. They walked. Nobody in this order has let it happen twice."
- `conversations.work.prof.cultist.task.ask_lines` — e.g. "Not something I'd recite to a stranger. Ask me again when you're not a stranger."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cultist.followup   [27 chars]
    en  That's the book club, then.
    >>  ............................................
    pt  É o clube do livro, então.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cultist.challenge.landed`, `work.cultist.challenge.stung`, `work.cultist.craft.admire`, `work.cultist.craft.ask_nineteen`, `work.cultist.craft.ask_tested`, `work.cultist.future.ask_none`, `work.cultist.future.ask_three`, `work.cultist.future.encourage`, `work.cultist.hard`, `work.cultist.risk.ask_before`, `work.cultist.risk.ask_wrong`, `work.cultist.risk.sympathise`, `work.cultist.task.ask_lamp`, `work.cultist.task.ask_lines`, `work.cultist.task.offer_hands`, `work.cultist.value`, `work.cultist.village.ask_rest`, `work.cultist.village.ask_two`, `work.cultist.village.say_thanks` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.cultist.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `insulting`, `restful`
  - scored words: `thought`(1.2), `insulting`(1.5), `restful`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.cultist.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.cultist.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.cultist.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.cultist.thanks`: the villager accepts. Subject `work.cultist.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cultist.thanks/1   [71 chars]
    en  Nobody thinks about us at all, which is restful and slightly insulting.
    >>  ............................................
    pt  Ninguém pensa na gente, o que é descansado e levemente ofensivo.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.thanks/2   [75 chars]
    en  You'd be the first this year to think about it rather than around it, %1$s.
    >>  ............................................
    pt  Você seria o primeiro do ano a pensar nisso em vez de desviar, %1$s.
    >>  ............................................
```


### Button `ask_more` — "What is it you're actually waiting for?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cultist.challenge.landed`, `work.cultist.challenge.stung`, `work.cultist.craft.admire`, `work.cultist.craft.ask_nineteen`, `work.cultist.craft.ask_tested`, `work.cultist.future.ask_none`, `work.cultist.future.ask_three`, `work.cultist.future.encourage`, `work.cultist.hard`, `work.cultist.risk.ask_before`, `work.cultist.risk.ask_wrong`, `work.cultist.risk.sympathise`, `work.cultist.task.ask_lamp`, `work.cultist.task.ask_lines`, `work.cultist.task.offer_hands`, `work.cultist.value`, `work.cultist.village.ask_rest`, `work.cultist.village.ask_two`, `work.cultist.village.say_thanks` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.cultist.more` — accepted phrasings: "what is it you're actually waiting for"
  - the message must contain one of: `waiting`, `expecting`
  - scored words: `waiting`(1.5), `expecting`(1.5), `actually`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.followup.ask_more   [39 chars]
    en  What is it you're actually waiting for?
    >>  ............................................
    pt  O que vocês estão realmente esperando?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.cultist.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.more
WHO    VILLAGER — what the player reads after pressing "What is it you're actually waiting for?"
       spoken on: conversations.topic.work.cultist.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.cultist.more`: the villager discloses. Subject `work.cultist.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cultist.more/1   [58 chars]
    en  An answer. We've had noises. An answer would be different.
    >>  ............................................
    pt  Uma resposta. Tivemos ruídos. Uma resposta seria diferente.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.more/2   [66 chars]
    en  ...I'd have to know you better. Ask me when the moon's thin, %1$s.
    >>  ............................................
    pt  ...Eu teria que te conhecer melhor. Me pergunte quando a lua estiver fina, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.cultist.more/1
    en  An answer. Forty years of keeping a lamp and I would like one answer, %1$s.
    >>  ............................................
    pt  Uma resposta. Quarenta anos cuidando de uma lamparina e eu queria uma resposta, %1$s.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.cultist.more/2
    en  If I pass nobody, ninety years of names end with me. I would rather not finish that sentence.
    >>  ............................................
    pt  Se ninguém passar, noventa anos de nomes acabam comigo. Prefiro não terminar essa frase.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.cultist.more/1
    en  An answer, one day. The order has waited ninety years; it can wait a little longer.
    >>  ............................................
    pt  Uma resposta, um dia. A ordem esperou noventa anos; pode esperar mais um pouco.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.cultist.more/2
    en  A successor, in time. Three so far. There'll be a fourth, and I'm not in a hurry about it.
    >>  ............................................
    pt  Um sucessor, com o tempo. Três até agora. Vai haver um quarto, e eu não tenho pressa.
    >>  ............................................
  confident.dialogue.conversations.work.prof.cultist.more/1
    en  An answer. We've had noises. An answer would be different.
    >>  ............................................
    pt  Uma resposta. Já tivemos ruídos. Uma resposta seria diferente.
    >>  ............................................
  confident.dialogue.conversations.work.prof.cultist.more/2
    en  Somebody to take the forty lines. I've tested three and passed none of them.
    >>  ............................................
    pt  Alguém pra receber as quarenta linhas. Testei três e nenhum passou.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.cultist.more/1
    en  An answer. We've had noises. An answer would be different.
    >>  ............................................
    pt  Uma resposta. Já tivemos ruídos. Uma resposta seria diferente.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.cultist.more/2
    en  Somebody to take the forty lines. I've tested three and passed none of them.
    >>  ............................................
    pt  Alguém pra receber as quarenta linhas. Testei três e nenhum passou.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.cultist.more/1
    en  An answer. Noises we've had. Sit with me on a lamp night and you'll hear what I mean.
    >>  ............................................
    pt  Uma resposta. Ruídos a gente já teve. Fique comigo numa noite de lamparina e você vai entender.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.cultist.more/2
    en  Somebody to take the lines. I'd not press it on anyone — it has to be wanted.
    >>  ............................................
    pt  Alguém pra receber as linhas. Eu não empurraria em ninguém — tem que ser querido.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.cultist.more/1
    en  An answer. Noises we've had. Sit with me on a lamp night and you'll hear what I mean.
    >>  ............................................
    pt  Uma resposta. Ruídos a gente já teve. Fique comigo numa noite de lamparina e você vai entender.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.cultist.more/2
    en  Somebody to take the lines. I'd not press it on anyone — it has to be wanted.
    >>  ............................................
    pt  Alguém pra receber as linhas. Eu não empurraria em ninguém — tem que ser querido.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.cultist.more/1
    en  An answer. Noises we've had. Sit with me on a lamp night and you'll hear what I mean.
    >>  ............................................
    pt  Uma resposta. Ruídos a gente já teve. Fique comigo numa noite de lamparina e você vai entender.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.cultist.more/2
    en  Somebody to take the lines. I'd not press it on anyone — it has to be wanted.
    >>  ............................................
    pt  Alguém pra receber as linhas. Eu não empurraria em ninguém — tem que ser querido.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.cultist.more/1
    en  An answer. Forty years of keeping a lamp and I would like one answer, %1$s.
    >>  ............................................
    pt  Uma resposta. Quarenta anos cuidando de uma lamparina e eu queria uma resposta, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.cultist.more/2
    en  If I pass nobody, ninety years of names end with me. I would rather not finish that sentence.
    >>  ............................................
    pt  Se ninguém passar, noventa anos de nomes acabam comigo. Prefiro não terminar essa frase.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.cultist.more/1
    en  An answer. We've had noises. An answer would be different.
    >>  ............................................
    pt  Uma resposta. Já tivemos ruídos. Uma resposta seria diferente.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.cultist.more/2
    en  Somebody to take the forty lines. I've tested three and passed none of them.
    >>  ............................................
    pt  Alguém pra receber as quarenta linhas. Testei três e nenhum passou.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.cultist.more/1
    en  An answer. We've had noises. An answer would be different.
    >>  ............................................
    pt  Uma resposta. Já tivemos ruídos. Uma resposta seria diferente.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.cultist.more/2
    en  Somebody to take the forty lines. I've tested three and passed none of them.
    >>  ............................................
    pt  Alguém pra receber as quarenta linhas. Testei três e nenhum passou.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.cultist.more/1
    en  An answer. That's all. Noises are not answers.
    >>  ............................................
    pt  Uma resposta. Só isso. Ruídos não são respostas.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.cultist.more/2
    en  A successor. One talked, one wanted the standing, and one was perfect and left for the city.
    >>  ............................................
    pt  Um sucessor. Um falou, um queria o prestígio, e um era perfeito e foi pra cidade.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.cultist.more/1
    en  An answer, one day. The order has waited ninety years; it can wait a little longer.
    >>  ............................................
    pt  Uma resposta, um dia. A ordem esperou noventa anos; pode esperar mais um pouco.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.cultist.more/2
    en  A successor, in time. Three so far. There'll be a fourth, and I'm not in a hurry about it.
    >>  ............................................
    pt  Um sucessor, com o tempo. Três até agora. Vai haver um quarto, e eu não tenho pressa.
    >>  ............................................
  odd.dialogue.conversations.work.prof.cultist.more/1
    en  An answer. That's all. Noises are not answers.
    >>  ............................................
    pt  Uma resposta. Só isso. Ruídos não são respostas.
    >>  ............................................
  odd.dialogue.conversations.work.prof.cultist.more/2
    en  A successor. One talked, one wanted the standing, and one was perfect and left for the city.
    >>  ............................................
    pt  Um sucessor. Um falou, um queria o prestígio, e um era perfeito e foi pra cidade.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.cultist.more/1
    en  An answer, one day. The order has waited ninety years; it can wait a little longer.
    >>  ............................................
    pt  Uma resposta, um dia. A ordem esperou noventa anos; pode esperar mais um pouco.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.cultist.more/2
    en  A successor, in time. Three so far. There'll be a fourth, and I'm not in a hurry about it.
    >>  ............................................
    pt  Um sucessor, com o tempo. Três até agora. Vai haver um quarto, e eu não tenho pressa.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.cultist.more/1
    en  An answer! We've had noises for years. An answer would be a novelty.
    >>  ............................................
    pt  Uma resposta! Temos ruídos há anos. Uma resposta seria novidade.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.cultist.more/2
    en  A successor. Three candidates, three failures, and one of them very nearly worked.
    >>  ............................................
    pt  Um sucessor. Três candidatos, três falhas, e um quase deu certo.
    >>  ............................................
  playful.dialogue.conversations.work.prof.cultist.more/1
    en  An answer! We've had noises for years. An answer would be a novelty.
    >>  ............................................
    pt  Uma resposta! Temos ruídos há anos. Uma resposta seria novidade.
    >>  ............................................
  playful.dialogue.conversations.work.prof.cultist.more/2
    en  A successor. Three candidates, three failures, and one of them very nearly worked.
    >>  ............................................
    pt  Um sucessor. Três candidatos, três falhas, e um quase deu certo.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.cultist.more/1
    en  An answer, one day. The order has waited ninety years; it can wait a little longer.
    >>  ............................................
    pt  Uma resposta, um dia. A ordem esperou noventa anos; pode esperar mais um pouco.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.cultist.more/2
    en  A successor, in time. Three so far. There'll be a fourth, and I'm not in a hurry about it.
    >>  ............................................
    pt  Um sucessor, com o tempo. Três até agora. Vai haver um quarto, e eu não tenho pressa.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.cultist.more/1
    en  An answer. Forty years of keeping a lamp and I would like one answer, %1$s.
    >>  ............................................
    pt  Uma resposta. Quarenta anos cuidando de uma lamparina e eu queria uma resposta, %1$s.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.cultist.more/2
    en  If I pass nobody, ninety years of names end with me. I would rather not finish that sentence.
    >>  ............................................
    pt  Se ninguém passar, noventa anos de nomes acabam comigo. Prefiro não terminar essa frase.
    >>  ............................................
  shy.dialogue.conversations.work.prof.cultist.more/1
    en  An answer. That's all. Noises are not answers.
    >>  ............................................
    pt  Uma resposta. Só isso. Ruídos não são respostas.
    >>  ............................................
  shy.dialogue.conversations.work.prof.cultist.more/2
    en  A successor. One talked, one wanted the standing, and one was perfect and left for the city.
    >>  ............................................
    pt  Um sucessor. Um falou, um queria o prestígio, e um era perfeito e foi pra cidade.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.cultist.more/1
    en  An answer! We've had noises for years. An answer would be a novelty.
    >>  ............................................
    pt  Uma resposta! Temos ruídos há anos. Uma resposta seria novidade.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.cultist.more/2
    en  A successor. Three candidates, three failures, and one of them very nearly worked.
    >>  ............................................
    pt  Um sucessor. Três candidatos, três falhas, e um quase deu certo.
    >>  ............................................
  witty.dialogue.conversations.work.prof.cultist.more/1
    en  An answer! We've had noises for years. An answer would be a novelty.
    >>  ............................................
    pt  Uma resposta! Temos ruídos há anos. Uma resposta seria novidade.
    >>  ............................................
  witty.dialogue.conversations.work.prof.cultist.more/2
    en  A successor. Three candidates, three failures, and one of them very nearly worked.
    >>  ............................................
    pt  Um sucessor. Três candidatos, três falhas, e um quase deu certo.
    >>  ............................................
```

</details>


### Button `leave` — "Enjoy the reading."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cultist.challenge.landed`, `work.cultist.challenge.stung`, `work.cultist.craft.admire`, `work.cultist.craft.ask_nineteen`, `work.cultist.craft.ask_tested`, `work.cultist.future.ask_none`, `work.cultist.future.ask_three`, `work.cultist.future.encourage`, `work.cultist.hard`, `work.cultist.risk.ask_before`, `work.cultist.risk.ask_wrong`, `work.cultist.risk.sympathise`, `work.cultist.task.ask_lamp`, `work.cultist.task.ask_lines`, `work.cultist.task.offer_hands`, `work.cultist.value`, `work.cultist.village.ask_rest`, `work.cultist.village.ask_two`, `work.cultist.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.followup.leave   [18 chars]
    en  Enjoy the reading.
    >>  ............................................
    pt  Boa leitura.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.leave
WHO    VILLAGER — what the player reads after pressing "Enjoy the reading."
       spoken on: conversations.topic.work.cultist.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.left`: the villager accepts. Subject `work.cultist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cultist.empty_vigil.failed.respond / leave; conversations.scene.work.cultist.followup / leave; conversations.scene.work.cultist.unanswered_question.active.respond / leave; conversations.scene.work.cultist.unanswered_question.succeeded.respond / leave; conversations.scene.work.cultist.village_suspicion.blocked.respond / leave; conversations.scene.work.cultist.village_suspicion.succeeded.respond / leave; conversations.topic.work.cultist.craft.respond / leave; conversations.topic.work.cultist.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.cultist.empty_vigil.failed.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cultist.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cultist.future` — e.g. "Somebody has to take the forty lines. I've tested three people and passed none of them."


```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cultist.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cultist.future.respond   [19 chars]
    en  That's what's left.
    >>  ............................................
    pt  É o que resta.
    >>  ............................................
```


### Button `ask_three` — "Why did the three fail?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cultist.future` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cultist.future.ask_three` — accepted phrasings: "why did the three fail"
  - the message must contain one of: `three`, `fail`, `candidates`
  - scored words: `three`(1.5), `fail`(1.2), `candidates`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.future.respond.ask_three
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.future.respond.ask_three   [23 chars]
    en  Why did the three fail?
    >>  ............................................
    pt  Por que os três falharam?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cultist.future.ask_three`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cultist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What is it you're actually waiting for?" | "Enjoy the reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.future.ask_three
WHO    VILLAGER — what the player reads after pressing "Why did the three fail?"
       spoken on: conversations.topic.work.cultist.future.respond, button `ask_three`
       leaves the player on: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.future.ask_three`: the villager explains. Subject `work.cultist.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cultist.future.ask_three/1   [82 chars]
    en  One talked. One wanted it for the standing. One was perfect and left for the city.
    >>  ............................................
    pt  Um falou. Um queria pelo prestígio. Um era perfeito e foi pra cidade.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.future.ask_three/2   [90 chars]
    en  Two were unsuited and one was suited, %1$s, and the suited one is the failure that stings.
    >>  ............................................
    pt  Dois não serviam e um servia, %1$s, e o que servia é a falha que dói.
    >>  ............................................
```


### Button `encourage` — "Ordinary is a fair thing to want."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cultist.future` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cultist.future.encourage` — accepted phrasings: "ordinary is a fair thing to want"
  - the message must contain one of: `ordinary`, `fair`
  - scored words: `ordinary`(1.5), `fair`(1.2), `want`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.future.respond.encourage   [33 chars]
    en  Ordinary is a fair thing to want.
    >>  ............................................
    pt  Comum é uma coisa justa de querer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.cultist.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.cultist.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.cultist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What is it you're actually waiting for?" | "Enjoy the reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.future.encourage
WHO    VILLAGER — what the player reads after pressing "Ordinary is a fair thing to want."
       spoken on: conversations.topic.work.cultist.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.future.encourage`: the villager accepts. Subject `work.cultist.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cultist.future.encourage/1   [89 chars]
    en  ...It is, isn't it. I've spent years arguing for respect when I wanted something smaller.
    >>  ............................................
    pt  ...É, não é. Passei anos pedindo respeito quando eu queria algo menor.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.future.encourage/2   [75 chars]
    en  Nobody has told me that's allowed. I'd assumed I had to ask for more, %1$s.
    >>  ............................................
    pt  Ninguém me disse que isso é permitido. Eu supunha que tinha que pedir mais, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is, isn't it. I asked for respect because asking for less felt like defeat.
    >>  ............................................
    pt  ...É mesmo, não é. Pedi respeito porque pedir menos parecia derrota.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody has told me that's allowed, and hearing it makes my throat tight.
    >>  ............................................
    pt  Ninguém me disse que é permitido, e ouvir isso me aperta a garganta.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is. Years of arguing for respect when the smaller thing was always there.
    >>  ............................................
    pt  ...É. Anos exigindo respeito quando a coisa menor sempre esteve ali.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody has told me that's allowed. At my age you stop expecting to be told.
    >>  ............................................
    pt  Ninguém me disse que é permitido. Na minha idade você para de esperar ouvir.
    >>  ............................................
  confident.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is, isn't it. I've spent years arguing for respect when I wanted something smaller.
    >>  ............................................
    pt  ...É mesmo, não é. Passei anos exigindo respeito quando queria algo menor.
    >>  ............................................
  confident.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody has told me that's allowed. I'd assumed I had to ask for more.
    >>  ............................................
    pt  Ninguém me disse que isso é permitido. Eu presumia que devia pedir mais.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is, isn't it. I've spent years arguing for respect when I wanted something smaller.
    >>  ............................................
    pt  ...É mesmo, não é. Passei anos exigindo respeito quando queria algo menor.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody has told me that's allowed. I'd assumed I had to ask for more.
    >>  ............................................
    pt  Ninguém me disse que isso é permitido. Eu presumia que devia pedir mais.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is, isn't it, %1$s. Years of arguing for respect when I wanted something smaller.
    >>  ............................................
    pt  ...É mesmo, não é, %1$s. Anos exigindo respeito quando eu queria algo menor.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody has told me that's allowed. I'd assumed I had to ask for more, %1$s.
    >>  ............................................
    pt  Ninguém me disse que é permitido. Eu presumia que devia pedir mais, %1$s.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is, isn't it, %1$s. Years of arguing for respect when I wanted something smaller.
    >>  ............................................
    pt  ...É mesmo, não é, %1$s. Anos exigindo respeito quando eu queria algo menor.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody has told me that's allowed. I'd assumed I had to ask for more, %1$s.
    >>  ............................................
    pt  Ninguém me disse que é permitido. Eu presumia que devia pedir mais, %1$s.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is, isn't it, %1$s. Years of arguing for respect when I wanted something smaller.
    >>  ............................................
    pt  ...É mesmo, não é, %1$s. Anos exigindo respeito quando eu queria algo menor.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody has told me that's allowed. I'd assumed I had to ask for more, %1$s.
    >>  ............................................
    pt  Ninguém me disse que é permitido. Eu presumia que devia pedir mais, %1$s.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is, isn't it. I asked for respect because asking for less felt like defeat.
    >>  ............................................
    pt  ...É mesmo, não é. Pedi respeito porque pedir menos parecia derrota.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody has told me that's allowed, and hearing it makes my throat tight.
    >>  ............................................
    pt  Ninguém me disse que é permitido, e ouvir isso me aperta a garganta.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is, isn't it. I've spent years arguing for respect when I wanted something smaller.
    >>  ............................................
    pt  ...É mesmo, não é. Passei anos exigindo respeito quando queria algo menor.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody has told me that's allowed. I'd assumed I had to ask for more.
    >>  ............................................
    pt  Ninguém me disse que isso é permitido. Eu presumia que devia pedir mais.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is, isn't it. I've spent years arguing for respect when I wanted something smaller.
    >>  ............................................
    pt  ...É mesmo, não é. Passei anos exigindo respeito quando queria algo menor.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody has told me that's allowed. I'd assumed I had to ask for more.
    >>  ............................................
    pt  Ninguém me disse que isso é permitido. Eu presumia que devia pedir mais.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is. Something smaller, all along.
    >>  ............................................
    pt  ...É. Algo menor, esse tempo todo.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody said that was allowed.
    >>  ............................................
    pt  Ninguém disse que isso era permitido.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is. Years of arguing for respect when the smaller thing was always there.
    >>  ............................................
    pt  ...É. Anos exigindo respeito quando a coisa menor sempre esteve ali.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody has told me that's allowed. At my age you stop expecting to be told.
    >>  ............................................
    pt  Ninguém me disse que é permitido. Na minha idade você para de esperar ouvir.
    >>  ............................................
  odd.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is. Something smaller, all along.
    >>  ............................................
    pt  ...É. Algo menor, esse tempo todo.
    >>  ............................................
  odd.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody said that was allowed.
    >>  ............................................
    pt  Ninguém disse que isso era permitido.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is. Years of arguing for respect when the smaller thing was always there.
    >>  ............................................
    pt  ...É. Anos exigindo respeito quando a coisa menor sempre esteve ali.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody has told me that's allowed. At my age you stop expecting to be told.
    >>  ............................................
    pt  Ninguém me disse que é permitido. Na minha idade você para de esperar ouvir.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is, isn't it! Years of demanding respect when I wanted something much smaller.
    >>  ............................................
    pt  ...É mesmo, não é! Anos exigindo respeito quando eu queria algo bem menor.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody has told me that's allowed. I assumed the price of asking was asking for more.
    >>  ............................................
    pt  Ninguém me disse que é permitido. Achei que o preço de pedir era pedir mais.
    >>  ............................................
  playful.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is, isn't it! Years of demanding respect when I wanted something much smaller.
    >>  ............................................
    pt  ...É mesmo, não é! Anos exigindo respeito quando eu queria algo bem menor.
    >>  ............................................
  playful.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody has told me that's allowed. I assumed the price of asking was asking for more.
    >>  ............................................
    pt  Ninguém me disse que é permitido. Achei que o preço de pedir era pedir mais.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is. Years of arguing for respect when the smaller thing was always there.
    >>  ............................................
    pt  ...É. Anos exigindo respeito quando a coisa menor sempre esteve ali.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody has told me that's allowed. At my age you stop expecting to be told.
    >>  ............................................
    pt  Ninguém me disse que é permitido. Na minha idade você para de esperar ouvir.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is, isn't it. I asked for respect because asking for less felt like defeat.
    >>  ............................................
    pt  ...É mesmo, não é. Pedi respeito porque pedir menos parecia derrota.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody has told me that's allowed, and hearing it makes my throat tight.
    >>  ............................................
    pt  Ninguém me disse que é permitido, e ouvir isso me aperta a garganta.
    >>  ............................................
  shy.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is. Something smaller, all along.
    >>  ............................................
    pt  ...É. Algo menor, esse tempo todo.
    >>  ............................................
  shy.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody said that was allowed.
    >>  ............................................
    pt  Ninguém disse que isso era permitido.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is, isn't it! Years of demanding respect when I wanted something much smaller.
    >>  ............................................
    pt  ...É mesmo, não é! Anos exigindo respeito quando eu queria algo bem menor.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody has told me that's allowed. I assumed the price of asking was asking for more.
    >>  ............................................
    pt  Ninguém me disse que é permitido. Achei que o preço de pedir era pedir mais.
    >>  ............................................
  witty.dialogue.conversations.work.prof.cultist.future.encourage/1
    en  ...It is, isn't it! Years of demanding respect when I wanted something much smaller.
    >>  ............................................
    pt  ...É mesmo, não é! Anos exigindo respeito quando eu queria algo bem menor.
    >>  ............................................
  witty.dialogue.conversations.work.prof.cultist.future.encourage/2
    en  Nobody has told me that's allowed. I assumed the price of asking was asking for more.
    >>  ............................................
    pt  Ninguém me disse que é permitido. Achei que o preço de pedir era pedir mais.
    >>  ............................................
```

</details>


### Button `ask_none` — "What happens if you pass nobody?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cultist.future` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cultist.future.ask_none` — accepted phrasings: "what happens if you pass nobody"
  - the message must contain one of: `nobody`, `passes`, `ends`
  - scored words: `nobody`(1.2), `passes`(1.5), `ends`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.future.respond.ask_none
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.future.respond.ask_none   [32 chars]
    en  What happens if you pass nobody?
    >>  ............................................
    pt  O que acontece se ninguém passar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cultist.future.ask_none`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cultist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What is it you're actually waiting for?" | "Enjoy the reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.future.ask_none
WHO    VILLAGER — what the player reads after pressing "What happens if you pass nobody?"
       spoken on: conversations.topic.work.cultist.future.respond, button `ask_none`
       leaves the player on: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.future.ask_none`: the villager explains. Subject `work.cultist.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cultist.future.ask_none/1   [101 chars]
    en  Then it ends with me, and ninety years of names end with me, and I'd rather not finish that sentence.
    >>  ............................................
    pt  Então acaba comigo, e noventa anos de nomes acabam comigo, e eu prefiro não terminar essa frase.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.future.ask_none/2   [101 chars]
    en  I've written the lines down once, sealed, against that day. It's forbidden and I did it anyway, %1$s.
    >>  ............................................
    pt  Escrevi as linhas uma vez, lacrado, contra esse dia. É proibido e eu fiz assim mesmo, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to... reading."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cultist.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.future.respond.leave   [36 chars]
    en  I'll let you get back to... reading.
    >>  ............................................
    pt  Vou deixar você voltar a... ler.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to... reading."
       spoken on: conversations.topic.work.cultist.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.left`: the villager accepts. Subject `work.cultist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cultist.empty_vigil.failed.respond / leave; conversations.scene.work.cultist.followup / leave; conversations.scene.work.cultist.unanswered_question.active.respond / leave; conversations.scene.work.cultist.unanswered_question.succeeded.respond / leave; conversations.scene.work.cultist.village_suspicion.blocked.respond / leave; conversations.scene.work.cultist.village_suspicion.succeeded.respond / leave; conversations.topic.work.cultist.craft.respond / leave; conversations.topic.work.cultist.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.cultist.empty_vigil.failed.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cultist.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cultist` — e.g. "We are a BOOK CLUB. A perfectly ordinary book club. The chanting is... enthusiasm for literature."


```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cultist.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cultist.respond   [27 chars]
    en  So. A book club. As I said.
    >>  ............................................
    pt  Então. Um clube do livro. Como eu disse.
    >>  ............................................
```


### Button `ask_hard` — "What do the neighbours actually think?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cultist.identity` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cultist.hard` — accepted phrasings: "what do the neighbours actually think"
  - the message must contain one of: `neighbours`, `suspicion`
  - scored words: `neighbours`(1.5), `think`(0.8), `suspicion`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.respond.ask_hard   [38 chars]
    en  What do the neighbours actually think?
    >>  ............................................
    pt  O que os vizinhos realmente acham?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.cultist.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cultist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What is it you're actually waiting for?" | "Enjoy the reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.hard
WHO    VILLAGER — what the player reads after pressing "What do the neighbours actually think?"
       spoken on: conversations.topic.work.cultist.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.hard`: the villager explains. Subject `work.cultist.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cultist.followup / ask_more
```

> Written out in full under **`conversations.scene.work.cultist.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "You've never actually harmed anyone here."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cultist.identity` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cultist.value` — accepted phrasings: "you've never actually harmed anyone here"
  - the message must contain one of: `harmed`, `hurt`
  - scored words: `harmed`(1.5), `hurt`(1.2), `nobody`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.respond.value   [41 chars]
    en  You've never actually harmed anyone here.
    >>  ............................................
    pt  Você nunca machucou ninguém aqui de verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.cultist.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.cultist.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cultist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What is it you're actually waiting for?" | "Enjoy the reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.value
WHO    VILLAGER — what the player reads after pressing "You've never actually harmed anyone here."
       spoken on: conversations.topic.work.cultist.respond, button `value`
       leaves the player on: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.value`: the villager accepts. Subject `work.cultist.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cultist.value/1   [64 chars]
    en  ...No. And nobody has ever said that out loud before. Thank you.
    >>  ............................................
    pt  ...Não. E ninguém nunca disse isso em voz alta antes. Obrigado.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.value/2   [84 chars]
    en  Not once. It's a low bar and I'd rather be judged by a higher one, but I'll take it.
    >>  ............................................
    pt  Nenhuma vez. É um critério baixo e eu preferia um mais alto, mas eu aceito.
    >>  ............................................
```


### Button `challenge` — "You don't believe a word of it."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.cultist.identity` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cultist.challenge` — accepted phrasings: "you don't believe a word of it"
  - the message must contain one of: `believe`, `word`, `faith`
  - scored words: `believe`(1.5), `word`(1.0), `faith`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.respond.challenge   [31 chars]
    en  You don't believe a word of it.
    >>  ............................................
    pt  Você não acredita numa palavra disso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.cultist.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.cultist.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cultist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What is it you're actually waiting for?" | "Enjoy the reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.challenge.landed
WHO    VILLAGER — what the player reads after pressing "You don't believe a word of it."
       spoken on: conversations.topic.work.cultist.respond, button `challenge`
       leaves the player on: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.challenge.landed`: the villager resists. Subject `work.cultist.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cultist.challenge.landed/1   [75 chars]
    en  Some days I don't. Then something answers, and I do again. It's exhausting.
    >>  ............................................
    pt  Alguns dias eu não acredito. Aí algo responde, e eu acredito de novo. É exaustivo.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.challenge.landed/2   [57 chars]
    en  That would be simpler, %1$s. I'd sleep better as a fraud.
    >>  ............................................
    pt  Seria mais simples, %1$s. Eu dormiria melhor sendo uma fraude.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.cultist.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.cultist.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cultist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What is it you're actually waiting for?" | "Enjoy the reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.challenge.stung
WHO    VILLAGER — what the player reads after pressing "You don't believe a word of it."
       spoken on: conversations.topic.work.cultist.respond, button `challenge`
       leaves the player on: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.challenge.stung`: the villager resists. Subject `work.cultist.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cultist.challenge.stung/1   [61 chars]
    en  ...You've been to two meetings and read none of the pamphlet.
    >>  ............................................
    pt  ...Você foi a duas reuniões e não leu nada do panfleto.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.challenge.stung/2   [48 chars]
    en  Not a word. Right. And yet here you are, asking.
    >>  ............................................
    pt  Nem uma palavra. Certo. E mesmo assim está aqui, perguntando.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to... reading."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cultist.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.respond.leave   [36 chars]
    en  I'll let you get back to... reading.
    >>  ............................................
    pt  Vou deixar você voltar a... ler.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to... reading."
       spoken on: conversations.topic.work.cultist.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.left`: the villager accepts. Subject `work.cultist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cultist.empty_vigil.failed.respond / leave; conversations.scene.work.cultist.followup / leave; conversations.scene.work.cultist.unanswered_question.active.respond / leave; conversations.scene.work.cultist.unanswered_question.succeeded.respond / leave; conversations.scene.work.cultist.village_suspicion.blocked.respond / leave; conversations.scene.work.cultist.village_suspicion.succeeded.respond / leave; conversations.topic.work.cultist.craft.respond / leave; conversations.topic.work.cultist.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.cultist.empty_vigil.failed.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cultist.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cultist.risk` — e.g. "People here are polite to my face and careful behind it. I've made my peace with the careful part."


```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cultist.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cultist.risk.respond   [19 chars]
    en  That's what I hold.
    >>  ............................................
    pt  É o que eu carrego.
    >>  ............................................
```


### Button `ask_wrong` — "What if you are wrong about it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cultist.risk` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cultist.risk.ask_wrong` — accepted phrasings: "what if you are wrong about it"
  - the message must contain one of: `wrong`, `doubt`, `mistaken`
  - scored words: `wrong`(1.5), `doubt`(1.2), `mistaken`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.risk.respond.ask_wrong
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.risk.respond.ask_wrong   [31 chars]
    en  What if you are wrong about it?
    >>  ............................................
    pt  E se você estiver errado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cultist.risk.ask_wrong`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cultist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What is it you're actually waiting for?" | "Enjoy the reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.risk.ask_wrong
WHO    VILLAGER — what the player reads after pressing "What if you are wrong about it?"
       spoken on: conversations.topic.work.cultist.risk.respond, button `ask_wrong`
       leaves the player on: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.risk.ask_wrong`: the villager explains. Subject `work.cultist.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cultist.risk.ask_wrong/1   [85 chars]
    en  Then I kept a light burning and hurt nobody. I've decided that's a survivable answer.
    >>  ............................................
    pt  Então eu mantive uma luz acesa e não machuquei ninguém. Decidi que é uma resposta suportável.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.risk.ask_wrong/2   [92 chars]
    en  Then the forty lines are forty names remembered, %1$s, and remembering names is not nothing.
    >>  ............................................
    pt  Então as quarenta linhas são quarenta nomes lembrados, %1$s, e lembrar nomes não é nada.
    >>  ............................................
```


### Button `sympathise` — "Being treated carefully every day must be tiring."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cultist.risk` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cultist.risk.sympathise` — accepted phrasings: "being treated carefully every day must be tiring"
  - the message must contain one of: `carefully`, `tiring`, `treated`
  - scored words: `carefully`(1.5), `tiring`(1.5), `treated`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.risk.respond.sympathise   [49 chars]
    en  Being treated carefully every day must be tiring.
    >>  ............................................
    pt  Ser tratado com cautela todo dia deve cansar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.cultist.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.cultist.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cultist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What is it you're actually waiting for?" | "Enjoy the reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "Being treated carefully every day must be tiring."
       spoken on: conversations.topic.work.cultist.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.risk.sympathise`: the villager accepts. Subject `work.cultist.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cultist.risk.sympathise/1   [97 chars]
    en  ...It is. And I can't complain about it, because careful is better than what came before careful.
    >>  ............................................
    pt  ...Cansa. E eu não posso reclamar, porque cautela é melhor que o que veio antes da cautela.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.risk.sympathise/2   [88 chars]
    en  No one calls it tiring. They call it my choice, %1$s, which it is, and it's also tiring.
    >>  ............................................
    pt  Ninguém chama de cansativo. Chamam de minha escolha, %1$s, que é, e também cansa.
    >>  ............................................
```


### Button `ask_before` — "What came before careful?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cultist.risk` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cultist.risk.ask_before` — accepted phrasings: "what came before careful"
  - the message must contain one of: `before`, `came`, `history`
  - scored words: `before`(1.2), `came`(1.0), `history`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.risk.respond.ask_before
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.risk.respond.ask_before   [25 chars]
    en  What came before careful?
    >>  ............................................
    pt  O que veio antes da cautela?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cultist.risk.ask_before`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cultist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What is it you're actually waiting for?" | "Enjoy the reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.risk.ask_before
WHO    VILLAGER — what the player reads after pressing "What came before careful?"
       spoken on: conversations.topic.work.cultist.risk.respond, button `ask_before`
       leaves the player on: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.risk.ask_before`: the villager explains. Subject `work.cultist.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cultist.risk.ask_before/1   [86 chars]
    en  Stones, in my grandmother's time, in a different valley. I'll leave the account there.
    >>  ............................................
    pt  Pedras, no tempo da minha avó, num vale diferente. Vou deixar o relato aí.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.risk.ask_before/2   [74 chars]
    en  A fire that wasn't a lamp. That's all I'll say standing in the open, %1$s.
    >>  ............................................
    pt  Um fogo que não era lamparina. É tudo que eu digo a céu aberto, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to... reading."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cultist.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.risk.respond.leave   [36 chars]
    en  I'll let you get back to... reading.
    >>  ............................................
    pt  Vou deixar você voltar a... ler.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to... reading."
       spoken on: conversations.topic.work.cultist.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.left`: the villager accepts. Subject `work.cultist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cultist.empty_vigil.failed.respond / leave; conversations.scene.work.cultist.followup / leave; conversations.scene.work.cultist.unanswered_question.active.respond / leave; conversations.scene.work.cultist.unanswered_question.succeeded.respond / leave; conversations.scene.work.cultist.village_suspicion.blocked.respond / leave; conversations.scene.work.cultist.village_suspicion.succeeded.respond / leave; conversations.topic.work.cultist.craft.respond / leave; conversations.topic.work.cultist.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.cultist.empty_vigil.failed.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cultist.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cultist.task` — e.g. "Copying. The same forty lines, by hand, until the hand does it without me. That's the discipline."


```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cultist.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cultist.task.respond   [28 chars]
    en  That's the observance today.
    >>  ............................................
    pt  É a observância hoje.
    >>  ............................................
```


### Button `ask_lines` — "What are the forty lines?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cultist.task` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cultist.task.ask_lines` — accepted phrasings: "what are the forty lines"
  - the message must contain one of: `lines`, `forty`, `copying`
  - scored words: `lines`(1.5), `forty`(1.2), `copying`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.task.respond.ask_lines
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.task.respond.ask_lines   [25 chars]
    en  What are the forty lines?
    >>  ............................................
    pt  Quais são as quarenta linhas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cultist.task.ask_lines`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cultist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What is it you're actually waiting for?" | "Enjoy the reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.task.ask_lines
WHO    VILLAGER — what the player reads after pressing "What are the forty lines?"
       spoken on: conversations.topic.work.cultist.task.respond, button `ask_lines`
       leaves the player on: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.task.ask_lines`: the villager explains. Subject `work.cultist.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cultist.task.ask_lines/1   [80 chars]
    en  Not something I'd recite to a stranger. Ask me again when you're not a stranger.
    >>  ............................................
    pt  Não é algo que eu recitaria a um estranho. Pergunte de novo quando não for estranho.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.task.ask_lines/2   [93 chars]
    en  A list of names, mostly. Older than anyone can account for, %1$s, and that's the point of it.
    >>  ............................................
    pt  Uma lista de nomes, principalmente. Mais velha do que qualquer um explica, %1$s, e é essa a questão.
    >>  ............................................
```


### Button `offer_hands` — "I could fetch fire if the lamp went out."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.cultist.task` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cultist.task.offer_hands` — accepted phrasings: "i could fetch fire if the lamp went out"
  - the message must contain one of: `fire`, `fetch`, `lamp`
  - scored words: `fire`(1.5), `fetch`(1.2), `lamp`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.task.respond.offer_hands   [40 chars]
    en  I could fetch fire if the lamp went out.
    >>  ............................................
    pt  Eu podia buscar fogo se a lamparina apagasse.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.cultist.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.cultist.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cultist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What is it you're actually waiting for?" | "Enjoy the reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I could fetch fire if the lamp went out."
       spoken on: conversations.topic.work.cultist.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.task.offer_hands`: the villager accepts. Subject `work.cultist.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cultist.task.offer_hands/1   [95 chars]
    en  ...You could, and you'd be the first outsider ever to. I'd have to think about what that means.
    >>  ............................................
    pt  ...Podia, e seria o primeiro de fora a fazer. Eu teria que pensar no que isso significa.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.task.offer_hands/2   [82 chars]
    en  It's four hours each way in the dark, %1$s. Say that again knowing the four hours.
    >>  ............................................
    pt  São quatro horas de ida e volta no escuro, %1$s. Diga de novo sabendo das quatro horas.
    >>  ............................................
```


### Button `ask_lamp` — "Has the lamp ever gone out?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cultist.task` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cultist.task.ask_lamp` — accepted phrasings: "has the lamp ever gone out"
  - the message must contain one of: `lamp`, `extinguished`
  - scored words: `lamp`(1.5), `out`(0.5), `extinguished`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.task.respond.ask_lamp
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.task.respond.ask_lamp   [27 chars]
    en  Has the lamp ever gone out?
    >>  ............................................
    pt  A lamparina já apagou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cultist.task.ask_lamp`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cultist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What is it you're actually waiting for?" | "Enjoy the reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.task.ask_lamp
WHO    VILLAGER — what the player reads after pressing "Has the lamp ever gone out?"
       spoken on: conversations.topic.work.cultist.task.respond, button `ask_lamp`
       leaves the player on: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.task.ask_lamp`: the villager explains. Subject `work.cultist.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cultist.task.ask_lamp/1   [75 chars]
    en  Once, before me. They walked. Nobody in this order has let it happen twice.
    >>  ............................................
    pt  Uma vez, antes de mim. Eles caminharam. Ninguém desta ordem deixou acontecer duas vezes.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.task.ask_lamp/2   [89 chars]
    en  Not in my keeping, and I've kept it eleven years, %1$s, and I intend to keep saying that.
    >>  ............................................
    pt  Não sob minha guarda, e eu guardo há onze anos, %1$s, e pretendo continuar dizendo isso.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to... reading."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cultist.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.task.respond.leave   [36 chars]
    en  I'll let you get back to... reading.
    >>  ............................................
    pt  Vou deixar você voltar a... ler.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to... reading."
       spoken on: conversations.topic.work.cultist.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.left`: the villager accepts. Subject `work.cultist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cultist.empty_vigil.failed.respond / leave; conversations.scene.work.cultist.followup / leave; conversations.scene.work.cultist.unanswered_question.active.respond / leave; conversations.scene.work.cultist.unanswered_question.succeeded.respond / leave; conversations.scene.work.cultist.village_suspicion.blocked.respond / leave; conversations.scene.work.cultist.village_suspicion.succeeded.respond / leave; conversations.topic.work.cultist.craft.respond / leave; conversations.topic.work.cultist.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.cultist.empty_vigil.failed.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.cultist.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.cultist.village` — e.g. "I keep the names of everyone who's died here for ninety years. The church keeps dates; I keep the rest."


```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.cultist.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.cultist.village.respond   [24 chars]
    en  That's what I do for it.
    >>  ............................................
    pt  É o que eu faço por aqui.
    >>  ............................................
```


### Button `ask_rest` — "What's 'the rest'?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cultist.village` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cultist.village.ask_rest` — accepted phrasings: "what's 'the rest'"
  - the message must contain one of: `rest`, `names`, `remember`
  - scored words: `rest`(1.5), `names`(1.0), `remember`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.village.respond.ask_rest
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.village.respond.ask_rest   [18 chars]
    en  What's 'the rest'?
    >>  ............................................
    pt  O que é 'o resto'?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cultist.village.ask_rest`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cultist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What is it you're actually waiting for?" | "Enjoy the reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.village.ask_rest
WHO    VILLAGER — what the player reads after pressing "What's 'the rest'?"
       spoken on: conversations.topic.work.cultist.village.respond, button `ask_rest`
       leaves the player on: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.village.ask_rest`: the villager explains. Subject `work.cultist.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cultist.village.ask_rest/1   [81 chars]
    en  What they were like. A date tells you nothing. I can tell you which of them sang.
    >>  ............................................
    pt  Como eles eram. Uma data não diz nada. Eu sei dizer quais deles cantavam.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.village.ask_rest/2   [85 chars]
    en  How they were at the end, and who was there, %1$s. The ledger has no column for that.
    >>  ............................................
    pt  Como estavam no fim, e quem estava lá, %1$s. O registro não tem coluna pra isso.
    >>  ............................................
```


### Button `say_thanks` — "Burning it for the eleven who don't come is decent of you."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.cultist.village` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cultist.village.say_thanks` — accepted phrasings: "burning it for the eleven who don't come is decent of you"
  - the message must contain one of: `eleven`, `burning`, `decent`
  - scored words: `eleven`(1.5), `burning`(1.2), `decent`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.village.respond.say_thanks   [58 chars]
    en  Burning it for the eleven who don't come is decent of you.
    >>  ............................................
    pt  Mantê-la acesa pelas onze que não vêm é decente da sua parte.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.cultist.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.cultist.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cultist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What is it you're actually waiting for?" | "Enjoy the reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Burning it for the eleven who don't come is decent of you."
       spoken on: conversations.topic.work.cultist.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.village.say_thanks`: the villager accepts. Subject `work.cultist.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cultist.village.say_thanks/1   [94 chars]
    en  ...It's the only part I'd defend without any doctrine attached to it. Thank you for seeing it.
    >>  ............................................
    pt  ...É a única parte que eu defenderia sem nenhuma doutrina junto. Obrigado por ver.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.village.say_thanks/2   [84 chars]
    en  They'd be furious if they knew, %1$s, which is rather the joke of my whole position.
    >>  ............................................
    pt  Eles ficariam furiosos se soubessem, %1$s, que é meio que a piada da minha posição.
    >>  ............................................
```


### Button `ask_two` — "Which two families come?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.cultist.village` · offered only once the villager has actually said `work:cultist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.cultist.village.ask_two` — accepted phrasings: "which two families come"
  - the message must contain one of: `families`, `two`
  - scored words: `families`(1.5), `two`(1.0), `come`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.village.respond.ask_two
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.village.respond.ask_two   [24 chars]
    en  Which two families come?
    >>  ............................................
    pt  Quais duas famílias vêm?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.cultist.village.ask_two`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.cultist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What is it you're actually waiting for?" | "Enjoy the reading."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.village.ask_two
WHO    VILLAGER — what the player reads after pressing "Which two families come?"
       spoken on: conversations.topic.work.cultist.village.respond, button `ask_two`
       leaves the player on: conversations.topic.work.cultist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.village.ask_two`: the villager explains. Subject `work.cultist.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:cultist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.cultist.village.ask_two/1   [75 chars]
    en  That I'll not say. They'd be treated differently tomorrow and they know it.
    >>  ............................................
    pt  Isso eu não digo. Seriam tratadas diferente amanhã e elas sabem.
    >>  ............................................
  dialogue.conversations.work.prof.cultist.village.ask_two/2   [82 chars]
    en  Both of them lost somebody in the same winter, %1$s. That's the whole explanation.
    >>  ............................................
    pt  As duas perderam alguém no mesmo inverno, %1$s. É toda a explicação.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to... reading."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.cultist.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.cultist.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.cultist.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.cultist.village.respond.leave   [36 chars]
    en  I'll let you get back to... reading.
    >>  ............................................
    pt  Vou deixar você voltar a... ler.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.cultist.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to... reading."
       spoken on: conversations.topic.work.cultist.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.cultist.left`: the villager accepts. Subject `work.cultist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.cultist.empty_vigil.failed.respond / leave; conversations.scene.work.cultist.followup / leave; conversations.scene.work.cultist.unanswered_question.active.respond / leave; conversations.scene.work.cultist.unanswered_question.succeeded.respond / leave; conversations.scene.work.cultist.village_suspicion.blocked.respond / leave; conversations.scene.work.cultist.village_suspicion.succeeded.respond / leave; conversations.topic.work.cultist.craft.respond / leave; conversations.topic.work.cultist.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.cultist.empty_vigil.failed.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

