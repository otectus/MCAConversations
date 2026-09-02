# Work talk with a scribe

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.scribe.failing_eyes.active.respond`](#conversations-scene-work-scribe-failing-eyes-active-respond)
- [`conversations.scene.work.scribe.failing_eyes.succeeded.respond`](#conversations-scene-work-scribe-failing-eyes-succeeded-respond)
- [`conversations.scene.work.scribe.followup`](#conversations-scene-work-scribe-followup)
- [`conversations.scene.work.scribe.inherited_error.blocked.respond`](#conversations-scene-work-scribe-inherited-error-blocked-respond)
- [`conversations.scene.work.scribe.inherited_error.succeeded.respond`](#conversations-scene-work-scribe-inherited-error-succeeded-respond)
- [`conversations.scene.work.scribe.text_she_doubts.active.respond`](#conversations-scene-work-scribe-text-she-doubts-active-respond)
- [`conversations.scene.work.scribe.text_she_doubts.succeeded.respond`](#conversations-scene-work-scribe-text-she-doubts-succeeded-respond)
- [`conversations.topic.work.scribe.craft.respond`](#conversations-topic-work-scribe-craft-respond)
- [`conversations.topic.work.scribe.followup`](#conversations-topic-work-scribe-followup)
- [`conversations.topic.work.scribe.future.respond`](#conversations-topic-work-scribe-future-respond)
- [`conversations.topic.work.scribe.respond`](#conversations-topic-work-scribe-respond)
- [`conversations.topic.work.scribe.risk.respond`](#conversations-topic-work-scribe-risk-respond)
- [`conversations.topic.work.scribe.task.respond`](#conversations-topic-work-scribe-task-respond)
- [`conversations.topic.work.scribe.village.respond`](#conversations-topic-work-scribe-village-respond)

---

## `conversations.scene.work.scribe.failing_eyes.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.scribe.failing_eyes.active` — e.g. "I have %2$s, and I have been working shorter days and telling nobody, which is a plan with a very short life in it."


```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.failing_eyes.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.scribe.failing_eyes.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.scribe.failing_eyes.active.respond   [16 chars]
    en  The work itself.
    >>  ............................................
    pt  O trabalho em si.
    >>  ............................................
```


### Button `ask_about_the_plan` — "What's your plan for it?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.scribe.failing_eyes.active` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.scribe.failing_eyes.active.ask_about_the_plan` — accepted phrasings: "whats your plan for it"; "what is your plan for that"; "how are you planning around it"
  - the message must contain one of: `plan`, `planning`
  - scored words: `plan`(1.8), `planning`(1.8), `whats`(0.8), `around`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.failing_eyes.active.respond.ask_about_the_plan
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.scribe.failing_eyes.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.scribe.failing_eyes.active.respond.ask_about_the_plan   [24 chars]
    en  What's your plan for it?
    >>  ............................................
    pt  Qual é o seu plano?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3  _(recorded under topic `work.scribe.the_page`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.scribe.failing_eyes"}
- Then opens: `conversations.scene.work.scribe.followup`
- …where the player's next choices will be: "What's the hardest part of copying a page?" | "I'll leave you to the page."

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.failing_eyes.active.explained
WHO    VILLAGER — what the player reads after pressing "What's your plan for it?"
       spoken on: conversations.scene.work.scribe.failing_eyes.active.respond, button `ask_about_the_plan`
       leaves the player on: conversations.scene.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.failing_eyes.active.explained`: the villager explains. Subject `work.scribe.the_page`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.scribe.failing_eyes.active.explained/1   [113 chars]
    en  Copy the important books first, in the good hours, and let the register wait. It has waited ninety years already.
    >>  ............................................
    pt  Copiar os livros importantes primeiro, nas boas horas, e deixar o registro esperar. Ele já esperou noventa anos.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.failing_eyes.active.explained/2   [133 chars]
    en  Teach somebody. That is the real plan and it is the one I have been putting off for two years for reasons I do not like about myself.
    >>  ............................................
    pt  Ensinar alguém. É o plano de verdade e é o que eu venho adiando há dois anos por motivos de que eu não gosto a meu respeito.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.failing_eyes.active.explained/3   [151 chars]
    en  Bigger letters and shorter lines. It looks like a child's book and it will be readable in a century, which is more than my best work at thirty will be.
    >>  ............................................
    pt  Letras maiores e linhas mais curtas. Parece livro de criança e vai estar legível em um século, o que é mais do que o meu melhor trabalho aos trinta vai estar.
    >>  ............................................
```


### Button `urge_teaching` — "Take an apprentice now."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.scribe.failing_eyes.active` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.scribe.failing_eyes.active.urge_teaching` — accepted phrasings: "take an apprentice now"; "take an apprentice now"; "start teaching somebody this year"
  - the message must contain one of: `apprentice`, `teaching`
  - scored words: `apprentice`(1.8), `teaching`(1.8), `take`(0.8), `now`(0.8), `start`(0.8), `somebody`(0.8), `year`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.failing_eyes.active.respond.urge_teaching
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.scribe.failing_eyes.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.scribe.failing_eyes.active.respond.urge_teaching   [23 chars]
    en  Take an apprentice now.
    >>  ............................................
    pt  Pegue uma aprendiz agora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.scribe.eyes.urged`, budget `standard`, replay policy `once`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.scribe.the_page`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.scribe.failing_eyes"}
- Then opens: `conversations.scene.work.scribe.followup`
- …where the player's next choices will be: "What's the hardest part of copying a page?" | "I'll leave you to the page."

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.failing_eyes.active.accepted
WHO    VILLAGER — what the player reads after pressing "Take an apprentice now."
       spoken on: conversations.scene.work.scribe.failing_eyes.active.respond, button `urge_teaching`
       leaves the player on: conversations.scene.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.failing_eyes.active.accepted`: the villager accepts. Subject `work.scribe.the_page`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.scribe.failing_eyes.active.accepted/1   [134 chars]
    en  While I can still see well enough to correct them. That is the argument and it has a date on it, which is why I have been avoiding it.
    >>  ............................................
    pt  Enquanto eu ainda enxergo o bastante para corrigir. É o argumento, e ele tem data marcada, e por isso eu venho evitando.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.failing_eyes.active.accepted/2   [132 chars]
    en  Yes. And I have to teach them the marking and the marginal note and the refusing to improve, and those take longer than the letters.
    >>  ............................................
    pt  Sim. E eu preciso ensinar a marcação, a nota de margem e a recusa a melhorar o texto, e isso leva mais tempo que as letras.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.failing_eyes.active.accepted/3   [162 chars]
    en  There is a child in this village who reads for pleasure and has no trade. I have known that for a year and said nothing, out of something I would rather not name.
    >>  ............................................
    pt  Tem uma criança nesta vila que lê por prazer e não tem ofício. Eu sei disso há um ano e não disse nada, por algo que eu prefiro não nomear.
    >>  ............................................
```


### Button `acknowledge_the_loss` — "Nine hours to three is a real loss."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.scribe.failing_eyes.active` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.scribe.failing_eyes.active.acknowledge_the_loss` — accepted phrasings: "nine hours to three is a real loss"; "nine hours to three is a real loss"; "losing those hours is hard"
  - the message must contain one of: `hours`, `loss`, `losing`
  - scored words: `hours`(1.8), `loss`(1.8), `losing`(1.8), `nine`(0.8), `three`(0.8), `real`(0.8), `those`(0.8), `hard`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.failing_eyes.active.respond.acknowledge_the_loss
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.scribe.failing_eyes.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.scribe.failing_eyes.active.respond.acknowledge_the_loss   [35 chars]
    en  Nine hours to three is a real loss.
    >>  ............................................
    pt  De nove horas para três é uma perda real.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +4, trust +1  _(recorded under topic `work.scribe.the_page`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.scribe.failing_eyes"}
- Then opens: `conversations.scene.work.scribe.followup`
- …where the player's next choices will be: "What's the hardest part of copying a page?" | "I'll leave you to the page."

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.failing_eyes.active.steadied
WHO    VILLAGER — what the player reads after pressing "Nine hours to three is a real loss."
       spoken on: conversations.scene.work.scribe.failing_eyes.active.respond, button `acknowledge_the_loss`
       leaves the player on: conversations.scene.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.failing_eyes.active.steadied`: the villager accepts. Subject `work.scribe.the_page`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.scribe.failing_eyes.active.steadied/1   [98 chars]
    en  It is, and I have been very reasonable about it in public and much less so at four in the morning.
    >>  ............................................
    pt  É, e eu venho sendo muito razoável a respeito em público e muito menos às quatro da manhã.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.failing_eyes.active.steadied/2   [120 chars]
    en  Thank you. Everybody says at least you can still read. I can. It is not the same as being the person who writes it down.
    >>  ............................................
    pt  Obrigada. Todo mundo diz que pelo menos eu ainda leio. Eu leio. Não é a mesma coisa que ser a pessoa que escreve.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.failing_eyes.active.steadied/3   [129 chars]
    en  The strange part is that the three hours are better. Whatever this is has taken the quantity and left the quality entirely alone.
    >>  ............................................
    pt  O estranho é que as três horas são melhores. Seja lá o que for isso, levou a quantidade e deixou a qualidade inteiramente em paz.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the page."

*stance family `exit` · tone `plain` · answers the beat(s) `work.scribe.failing_eyes.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.failing_eyes.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.scribe.failing_eyes.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.scribe.failing_eyes.active.respond.leave   [34 chars]
    en  I'll let you get back to the page.
    >>  ............................................
    pt  Vou deixar você voltar à página.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the page."
       spoken on: conversations.scene.work.scribe.failing_eyes.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.left`: the villager accepts. Subject `work.scribe.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.scribe.failing_eyes.succeeded.respond / leave; conversations.scene.work.scribe.followup / leave; conversations.scene.work.scribe.inherited_error.blocked.respond / leave; conversations.scene.work.scribe.inherited_error.succeeded.respond / leave; conversations.scene.work.scribe.text_she_doubts.active.respond / leave; conversations.scene.work.scribe.text_she_doubts.succeeded.respond / leave; conversations.topic.work.scribe.craft.respond / leave; conversations.topic.work.scribe.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.scribe.leave/1   [42 chars]
    en  The ink is drying as we speak. Off you go.
    >>  ............................................
    pt  A tinta está secando enquanto falamos. Pode ir.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.leave/2   [47 chars]
    en  Quietly, %1$s. And don't breathe on the vellum.
    >>  ............................................
    pt  Em silêncio, %1$s. E não sopre no pergaminho.
    >>  ............................................
```

---


## `conversations.scene.work.scribe.failing_eyes.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.scribe.failing_eyes.succeeded` — e.g. "She has copied forty pages and made eleven errors, and she has marked all eleven herself, which is the whole of the trade."


```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.failing_eyes.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.scribe.failing_eyes.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.scribe.failing_eyes.succeeded.respond   [16 chars]
    en  Your apprentice.
    >>  ............................................
    pt  Sua aprendiz.
    >>  ............................................
```


### Button `note_the_order` — "Teaching the note first was clever."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.scribe.failing_eyes.succeeded` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.scribe.failing_eyes.succeeded.note_the_order` — accepted phrasings: "teaching the note first was clever"; "teaching the note first was clever"; "starting with the marginal note was wise"
  - the message must contain one of: `note`, `clever`, `wise`
  - scored words: `note`(1.8), `clever`(1.8), `wise`(1.8), `teaching`(0.8), `first`(0.8), `starting`(0.8), `marginal`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.failing_eyes.succeeded.respond.note_the_order
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.scribe.failing_eyes.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.scribe.failing_eyes.succeeded.respond.note_the_order   [35 chars]
    en  Teaching the note first was clever.
    >>  ............................................
    pt  Ensinar a nota primeiro foi esperto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.scribe.the_page`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.scribe.failing_eyes"}
- Then opens: `conversations.scene.work.scribe.followup`
- …where the player's next choices will be: "What's the hardest part of copying a page?" | "I'll leave you to the page."

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.failing_eyes.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Teaching the note first was clever."
       spoken on: conversations.scene.work.scribe.failing_eyes.succeeded.respond, button `note_the_order`
       leaves the player on: conversations.scene.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.failing_eyes.succeeded.acknowledged`: the villager accepts. Subject `work.scribe.the_page`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.scribe.failing_eyes.succeeded.acknowledged/1   [127 chars]
    en  Anybody can be taught to form letters. What takes years is the habit of doubting yourself in writing, where somebody can check.
    >>  ............................................
    pt  Qualquer um aprende a formar letras. O que leva anos é o hábito de duvidar de si por escrito, onde alguém pode conferir.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.failing_eyes.succeeded.acknowledged/2   [139 chars]
    en  Thank you. My own teacher did it the other way round and I did not learn the marking until I was thirty-one and had already spoiled a book.
    >>  ............................................
    pt  Obrigada. Minha própria mestra fez ao contrário e eu só aprendi a marcação aos trinta e um, depois de já ter estragado um livro.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.failing_eyes.succeeded.acknowledged/3   [135 chars]
    en  She will be better than me. I have been careful to say that out loud to her, in those words, because I was never told it and I noticed.
    >>  ............................................
    pt  Ela vai ser melhor que eu. Tomei o cuidado de dizer isso a ela em voz alta, com essas palavras, porque a mim nunca disseram e eu reparei.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the page."

*stance family `exit` · tone `plain` · answers the beat(s) `work.scribe.failing_eyes.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.failing_eyes.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.scribe.failing_eyes.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.scribe.failing_eyes.succeeded.respond.leave   [34 chars]
    en  I'll let you get back to the page.
    >>  ............................................
    pt  Vou deixar você voltar à página.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the page."
       spoken on: conversations.scene.work.scribe.failing_eyes.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.left`: the villager accepts. Subject `work.scribe.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.scribe.failing_eyes.active.respond / leave; conversations.scene.work.scribe.followup / leave; conversations.scene.work.scribe.inherited_error.blocked.respond / leave; conversations.scene.work.scribe.inherited_error.succeeded.respond / leave; conversations.scene.work.scribe.text_she_doubts.active.respond / leave; conversations.scene.work.scribe.text_she_doubts.succeeded.respond / leave; conversations.topic.work.scribe.craft.respond / leave; conversations.topic.work.scribe.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.scribe.failing_eyes.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.scribe.followup`

**Reached from 11 route(s):** `conversations.scene.work.scribe.failing_eyes.active.respond` / `ask_about_the_plan`; `conversations.scene.work.scribe.failing_eyes.active.respond` / `urge_teaching`; `conversations.scene.work.scribe.failing_eyes.active.respond` / `acknowledge_the_loss`; `conversations.scene.work.scribe.failing_eyes.succeeded.respond` / `note_the_order`; `conversations.scene.work.scribe.inherited_error.blocked.respond` / `ask_what_she_should_do`; `conversations.scene.work.scribe.inherited_error.blocked.respond` / `offer_paper`; `conversations.scene.work.scribe.inherited_error.blocked.respond` / `back_the_margin`; `conversations.scene.work.scribe.inherited_error.succeeded.respond` / `ask_about_the_argument`; `conversations.scene.work.scribe.text_she_doubts.active.respond` / `ask_what_she_does_with_doubt`; `conversations.scene.work.scribe.text_she_doubts.active.respond` / `back_the_faithful_copy`; `conversations.scene.work.scribe.text_she_doubts.succeeded.respond` / `note_the_discipline`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.scribe.failing_eyes.active.accepted` — e.g. "While I can still see well enough to correct them. That is the argument and it has a date on it, which is why I have been avoiding it."
- `conversations.scene.work.scribe.failing_eyes.active.explained` — e.g. "Copy the important books first, in the good hours, and let the register wait. It has waited ninety years already."
- `conversations.scene.work.scribe.failing_eyes.active.steadied` — e.g. "It is, and I have been very reasonable about it in public and much less so at four in the morning."
- `conversations.scene.work.scribe.failing_eyes.succeeded.acknowledged` — e.g. "Anybody can be taught to form letters. What takes years is the habit of doubting yourself in writing, where somebody can check."
- `conversations.scene.work.scribe.inherited_error.blocked.accepted` — e.g. "Then %2$s gets a note bound in behind it, saying what I found and how, and signed, so that the next person can disagree with me by name."
- `conversations.scene.work.scribe.inherited_error.blocked.explained` — e.g. "Copy it as it stands and mark %2$s in the margin. That is the only one of the three that leaves the next reader free to decide."
- `conversations.scene.work.scribe.inherited_error.blocked.steadied` — e.g. "That is the rule and it is the one I have to talk myself into every single time, because helping is such a pleasant feeling."
- `conversations.scene.work.scribe.inherited_error.succeeded.answered` — e.g. "That the older spelling was the right one, and he may be correct, and now there are two notes and the reader has both."
- `conversations.scene.work.scribe.text_she_doubts.active.explained` — e.g. "Into the margin, signed and dated, where it is clearly mine and clearly not his. That is the only honest place for it."
- `conversations.scene.work.scribe.text_she_doubts.active.steadied` — e.g. "Faithfully, and with my name at the end so that anybody who wants to blame somebody for the copy can blame the right person."
- `conversations.scene.work.scribe.text_she_doubts.succeeded.acknowledged` — e.g. "It is only rare because disagreement usually happens out loud, where it costs nothing to be unfair, and I had four days and a margin."


```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.scribe.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.scribe.followup   [27 chars]
    en  Was there another question?
    >>  ............................................
    pt  Tinha outra pergunta?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of copying a page?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.scribe.*` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.scribe.followup.ask_more` — accepted phrasings: "whats the hardest part of copying a page"; "what is the hardest part of copying a page"; "hardest thing about copying"
  - the message must contain one of: `hardest`, `copying`
  - scored words: `hardest`(1.8), `copying`(1.8), `whats`(0.8), `part`(0.8), `page`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.scribe.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.scribe.followup.ask_more   [42 chars]
    en  What's the hardest part of copying a page?
    >>  ............................................
    pt  Qual é a parte mais difícil de copiar uma página?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.scribe.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.scribe.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the oldest thing you've saved?" | "Steady hand."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of copying a page?"
       spoken on: conversations.scene.work.scribe.followup, button `ask_more`
       leaves the player on: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.hard`: the villager explains. Subject `work.scribe.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.scribe.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.scribe.hard/1   [77 chars]
    en  I mean the binding is not always leather and the ink is not always ink, %1$s.
    >>  ............................................
    pt  Quero dizer que a encadernação nem sempre é couro e a tinta nem sempre é tinta, %1$s.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.hard/2   [71 chars]
    en  I mean I have a scar on my thumb from a page. I'd rather not elaborate.
    >>  ............................................
    pt  Quero dizer que tenho uma cicatriz no polegar por causa de uma página. Prefiro não detalhar.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the page."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.scribe.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.scribe.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.scribe.followup.leave   [27 chars]
    en  I'll leave you to the page.
    >>  ............................................
    pt  Vou deixar você com a página.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the page."
       spoken on: conversations.scene.work.scribe.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.left`: the villager accepts. Subject `work.scribe.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.scribe.failing_eyes.active.respond / leave; conversations.scene.work.scribe.failing_eyes.succeeded.respond / leave; conversations.scene.work.scribe.inherited_error.blocked.respond / leave; conversations.scene.work.scribe.inherited_error.succeeded.respond / leave; conversations.scene.work.scribe.text_she_doubts.active.respond / leave; conversations.scene.work.scribe.text_she_doubts.succeeded.respond / leave; conversations.topic.work.scribe.craft.respond / leave; conversations.topic.work.scribe.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.scribe.failing_eyes.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.scribe.inherited_error.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.scribe.inherited_error.blocked` — e.g. "There is %2$s in %3$s, and it has been faithfully copied forward by everybody since, including me, twice."


```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.inherited_error.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.scribe.inherited_error.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.scribe.inherited_error.blocked.respond   [11 chars]
    en  The copies.
    >>  ............................................
    pt  As cópias.
    >>  ............................................
```


### Button `ask_what_she_should_do` — "What are your options?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.scribe.inherited_error.blocked` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.scribe.inherited_error.blocked.ask_what_she_should_do` — accepted phrasings: "what are your options"; "what are your options here"; "what can a copyist do about that"
  - the message must contain one of: `options`, `copyist`
  - scored words: `options`(1.8), `copyist`(1.8), `here`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.inherited_error.blocked.respond.ask_what_she_should_do
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.scribe.inherited_error.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.scribe.inherited_error.blocked.respond.ask_what_she_should_do   [22 chars]
    en  What are your options?
    >>  ............................................
    pt  Quais são suas opções?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.scribe.errors`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.scribe.inherited_error"}
- Then opens: `conversations.scene.work.scribe.followup`
- …where the player's next choices will be: "What's the hardest part of copying a page?" | "I'll leave you to the page."

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.inherited_error.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What are your options?"
       spoken on: conversations.scene.work.scribe.inherited_error.blocked.respond, button `ask_what_she_should_do`
       leaves the player on: conversations.scene.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.inherited_error.blocked.explained`: the villager explains. Subject `work.scribe.errors`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.scribe.inherited_error.blocked.explained/1   [127 chars]
    en  Copy it as it stands and mark %2$s in the margin. That is the only one of the three that leaves the next reader free to decide.
    >>  ............................................
    pt  Copiar como está e marcar %2$s na margem. É a única das três que deixa o próximo leitor livre para decidir.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.inherited_error.blocked.explained/2   [108 chars]
    en  Correct it silently, which is what a helpful person does and what destroys an archive over four generations.
    >>  ............................................
    pt  Corrigir em silêncio, que é o que uma pessoa prestativa faz e o que destrói um arquivo em quatro gerações.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.inherited_error.blocked.explained/3   [123 chars]
    en  Write a separate note and bind it in. It is ugly, it doubles the work, and in two hundred years it will be the useful page.
    >>  ............................................
    pt  Escrever uma nota separada e encadernar junto. É feio, dobra o trabalho, e em duzentos anos vai ser a página útil.
    >>  ............................................
```


### Button `offer_paper` — "I'll bring you paper for the archive."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.scribe.inherited_error.blocked` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.scribe.inherited_error.blocked.offer_paper` — accepted phrasings: "ill bring you paper for the archive"; "i can bring you paper for the archive"; "let me fetch paper for the binding"
  - the message must contain one of: `paper`
  - scored words: `paper`(1.8), `ill`(0.8), `bring`(0.8), `archive`(0.8), `let`(0.8), `fetch`(0.8), `binding`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.inherited_error.blocked.respond.offer_paper
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.scribe.inherited_error.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.scribe.inherited_error.blocked.respond.offer_paper   [37 chars]
    en  I'll bring you paper for the archive.
    >>  ............................................
    pt  Vou trazer papel para o arquivo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.scribe.error.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.scribe.errors`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.inherited_error", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.scribe.inherited_error", "obligation": "commitment:work.scribe.bring_paper"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.scribe.bring_paper"}
- Then opens: `conversations.scene.work.scribe.followup`
- …where the player's next choices will be: "What's the hardest part of copying a page?" | "I'll leave you to the page."

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.inherited_error.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you paper for the archive."
       spoken on: conversations.scene.work.scribe.inherited_error.blocked.respond, button `offer_paper`
       leaves the player on: conversations.scene.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.inherited_error.blocked.accepted`: the villager accepts. Subject `work.scribe.errors`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.scribe.inherited_error.blocked.accepted/1   [136 chars]
    en  Then %2$s gets a note bound in behind it, saying what I found and how, and signed, so that the next person can disagree with me by name.
    >>  ............................................
    pt  Então %2$s ganha uma nota encadernada atrás, dizendo o que eu encontrei e como, e assinada, para a próxima pessoa poder discordar de mim pelo nome.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.inherited_error.blocked.accepted/2   [126 chars]
    en  Bound in rather than loose. A loose note is lost in one generation, and a lost note is worse than none because it was written.
    >>  ............................................
    pt  Encadernada, não solta. Uma nota solta se perde em uma geração, e nota perdida é pior que nenhuma porque foi escrita.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.inherited_error.blocked.accepted/3   [135 chars]
    en  Yes. And I will copy the note twice, into both books, because a correction that exists in one place is a correction waiting to be lost.
    >>  ............................................
    pt  Sim. E vou copiar a nota duas vezes, nos dois livros, porque uma correção que existe em um lugar só é uma correção esperando para se perder.
    >>  ............................................
```


### Button `back_the_margin` — "Copy it as it stands and mark it."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.scribe.inherited_error.blocked` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.scribe.inherited_error.blocked.back_the_margin` — accepted phrasings: "copy it as it stands and mark it"; "copy it as it stands and mark it"; "leave the text and note the doubt"
  - the message must contain one of: `stands`, `mark`, `doubt`
  - scored words: `stands`(1.8), `mark`(1.8), `doubt`(1.8), `copy`(0.8), `leave`(0.8), `text`(0.8), `note`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.inherited_error.blocked.respond.back_the_margin
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.scribe.inherited_error.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.scribe.inherited_error.blocked.respond.back_the_margin   [33 chars]
    en  Copy it as it stands and mark it.
    >>  ............................................
    pt  Copie como está e marque.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4, trust +1  _(recorded under topic `work.scribe.errors`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.scribe.inherited_error"}
- Then opens: `conversations.scene.work.scribe.followup`
- …where the player's next choices will be: "What's the hardest part of copying a page?" | "I'll leave you to the page."

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.inherited_error.blocked.steadied
WHO    VILLAGER — what the player reads after pressing "Copy it as it stands and mark it."
       spoken on: conversations.scene.work.scribe.inherited_error.blocked.respond, button `back_the_margin`
       leaves the player on: conversations.scene.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.inherited_error.blocked.steadied`: the villager accepts. Subject `work.scribe.errors`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.scribe.inherited_error.blocked.steadied/1   [124 chars]
    en  That is the rule and it is the one I have to talk myself into every single time, because helping is such a pleasant feeling.
    >>  ............................................
    pt  É a regra, e é a que eu preciso me convencer a seguir toda vez, porque ajudar é uma sensação muito agradável.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.inherited_error.blocked.steadied/2   [146 chars]
    en  Yes. A copyist who improves a text has replaced it with herself, and everybody afterwards is reading her and thinking they are reading the source.
    >>  ............................................
    pt  Sim. Uma copista que melhora um texto o substituiu por si mesma, e todo mundo depois está lendo ela achando que lê a fonte.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.inherited_error.blocked.steadied/3   [154 chars]
    en  Thank you. It is the answer I knew and I wanted to hear somebody outside the trade say it, because inside the trade it sounds like an excuse for laziness.
    >>  ............................................
    pt  Obrigada. É a resposta que eu já sabia e eu queria ouvir alguém de fora do ofício dizer, porque dentro do ofício soa como desculpa para preguiça.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the page."

*stance family `exit` · tone `plain` · answers the beat(s) `work.scribe.inherited_error.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.inherited_error.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.scribe.inherited_error.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.scribe.inherited_error.blocked.respond.leave   [34 chars]
    en  I'll let you get back to the page.
    >>  ............................................
    pt  Vou deixar você voltar à página.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the page."
       spoken on: conversations.scene.work.scribe.inherited_error.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.left`: the villager accepts. Subject `work.scribe.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.scribe.failing_eyes.active.respond / leave; conversations.scene.work.scribe.failing_eyes.succeeded.respond / leave; conversations.scene.work.scribe.followup / leave; conversations.scene.work.scribe.inherited_error.succeeded.respond / leave; conversations.scene.work.scribe.text_she_doubts.active.respond / leave; conversations.scene.work.scribe.text_she_doubts.succeeded.respond / leave; conversations.topic.work.scribe.craft.respond / leave; conversations.topic.work.scribe.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.scribe.failing_eyes.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.scribe.inherited_error.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.scribe.inherited_error.succeeded` — e.g. "%2$s carries the note now, bound in, in both books, and the text above it is exactly as wrong as it always was."


```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.inherited_error.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.scribe.inherited_error.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.scribe.inherited_error.succeeded.respond   [18 chars]
    en  The copies, since.
    >>  ............................................
    pt  As cópias, depois disso.
    >>  ............................................
```


### Button `ask_about_the_argument` — "What did they argue?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.scribe.inherited_error.succeeded` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.scribe.inherited_error.succeeded.ask_about_the_argument` — accepted phrasings: "what did they argue"; "what did they argue with you about"; "what was their objection"
  - the message must contain one of: `argue`, `objection`
  - scored words: `argue`(1.8), `objection`(1.8), `their`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.inherited_error.succeeded.respond.ask_about_the_argument
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.scribe.inherited_error.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.scribe.inherited_error.succeeded.respond.ask_about_the_argument   [20 chars]
    en  What did they argue?
    >>  ............................................
    pt  O que argumentaram?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.scribe.errors`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.scribe.inherited_error"}
- Then opens: `conversations.scene.work.scribe.followup`
- …where the player's next choices will be: "What's the hardest part of copying a page?" | "I'll leave you to the page."

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.inherited_error.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "What did they argue?"
       spoken on: conversations.scene.work.scribe.inherited_error.succeeded.respond, button `ask_about_the_argument`
       leaves the player on: conversations.scene.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.inherited_error.succeeded.answered`: the villager explains. Subject `work.scribe.errors`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.scribe.inherited_error.succeeded.answered/1   [118 chars]
    en  That the older spelling was the right one, and he may be correct, and now there are two notes and the reader has both.
    >>  ............................................
    pt  Que a grafia mais antiga era a certa, e ele pode ter razão, e agora há duas notas e o leitor tem as duas.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.inherited_error.succeeded.answered/2   [146 chars]
    en  He was cross for about a minute and then genuinely interested, which is the most common shape an argument takes when the evidence is on the table.
    >>  ............................................
    pt  Ficou bravo por um minuto e depois genuinamente interessado, que é o formato mais comum de uma discussão quando a prova está na mesa.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.inherited_error.succeeded.answered/3   [157 chars]
    en  He knew something I did not. His grandmother's family used the other spelling, and that is exactly the sort of thing an archive cannot hold and a person can.
    >>  ............................................
    pt  Ele sabia algo que eu não sabia. A família da avó dele usava a outra grafia, e é exatamente o tipo de coisa que um arquivo não guarda e uma pessoa guarda.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the page."

*stance family `exit` · tone `plain` · answers the beat(s) `work.scribe.inherited_error.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.inherited_error.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.scribe.inherited_error.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.scribe.inherited_error.succeeded.respond.leave   [34 chars]
    en  I'll let you get back to the page.
    >>  ............................................
    pt  Vou deixar você voltar à página.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the page."
       spoken on: conversations.scene.work.scribe.inherited_error.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.left`: the villager accepts. Subject `work.scribe.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.scribe.failing_eyes.active.respond / leave; conversations.scene.work.scribe.failing_eyes.succeeded.respond / leave; conversations.scene.work.scribe.followup / leave; conversations.scene.work.scribe.inherited_error.blocked.respond / leave; conversations.scene.work.scribe.text_she_doubts.active.respond / leave; conversations.scene.work.scribe.text_she_doubts.succeeded.respond / leave; conversations.topic.work.scribe.craft.respond / leave; conversations.topic.work.scribe.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.scribe.failing_eyes.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.scribe.text_she_doubts.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.scribe.text_she_doubts.active` — e.g. "I am copying %2$s that I do not believe a word of, and my job is to write it out exactly."


```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.text_she_doubts.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.scribe.text_she_doubts.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.scribe.text_she_doubts.active.respond   [24 chars]
    en  The text you're copying.
    >>  ............................................
    pt  O texto que você copia.
    >>  ............................................
```


### Button `ask_what_she_does_with_doubt` — "Where does your doubt go?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.scribe.text_she_doubts.active` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.scribe.text_she_doubts.active.ask_what_she_does_with_doubt` — accepted phrasings: "where does your doubt go"; "where does your doubt go"; "what happens to your own view of it"
  - the message must contain one of: `doubt`, `view`
  - scored words: `doubt`(1.8), `view`(1.8), `where`(0.8), `does`(0.8), `happens`(0.8), `own`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.text_she_doubts.active.respond.ask_what_she_does_with_doubt
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.scribe.text_she_doubts.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.scribe.text_she_doubts.active.respond.ask_what_she_does_with_doubt   [25 chars]
    en  Where does your doubt go?
    >>  ............................................
    pt  Para onde vai a sua dúvida?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.scribe.a_text_i_doubt`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.scribe.text_she_doubts"}
- Then opens: `conversations.scene.work.scribe.followup`
- …where the player's next choices will be: "What's the hardest part of copying a page?" | "I'll leave you to the page."

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.text_she_doubts.active.explained
WHO    VILLAGER — what the player reads after pressing "Where does your doubt go?"
       spoken on: conversations.scene.work.scribe.text_she_doubts.active.respond, button `ask_what_she_does_with_doubt`
       leaves the player on: conversations.scene.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.text_she_doubts.active.explained`: the villager explains. Subject `work.scribe.a_text_i_doubt`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.scribe.text_she_doubts.active.explained/1   [118 chars]
    en  Into the margin, signed and dated, where it is clearly mine and clearly not his. That is the only honest place for it.
    >>  ............................................
    pt  Para a margem, assinada e datada, onde é claramente minha e claramente não dele. É o único lugar honesto.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.text_she_doubts.active.explained/2   [107 chars]
    en  Nowhere, in the text. A reader in two hundred years is entitled to his account, not to mine of his account.
    >>  ............................................
    pt  Para lugar nenhum, no texto. Um leitor daqui a duzentos anos tem direito ao relato dele, não ao meu sobre o relato dele.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.text_she_doubts.active.explained/3   [148 chars]
    en  I write what I would need to be persuaded. Not that he is wrong — what evidence would change my mind. It is a better note and it is harder to write.
    >>  ............................................
    pt  Escrevo o que eu precisaria para ser convencida. Não que ele esteja errado — que prova mudaria minha cabeça. É uma nota melhor e é mais difícil de escrever.
    >>  ............................................
```


### Button `back_the_faithful_copy` — "Copy it faithfully anyway."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.scribe.text_she_doubts.active` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.scribe.text_she_doubts.active.back_the_faithful_copy` — accepted phrasings: "copy it faithfully anyway"; "copy it faithfully anyway"; "keep the copy faithful to his words"
  - the message must contain one of: `faithfully`, `faithful`
  - scored words: `faithfully`(1.8), `faithful`(1.8), `copy`(0.8), `anyway`(0.8), `keep`(0.8), `his`(0.8), `words`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.text_she_doubts.active.respond.back_the_faithful_copy
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.scribe.text_she_doubts.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.scribe.text_she_doubts.active.respond.back_the_faithful_copy   [26 chars]
    en  Copy it faithfully anyway.
    >>  ............................................
    pt  Copie fielmente mesmo assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4, trust +1  _(recorded under topic `work.scribe.a_text_i_doubt`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.scribe.text_she_doubts"}
- Then opens: `conversations.scene.work.scribe.followup`
- …where the player's next choices will be: "What's the hardest part of copying a page?" | "I'll leave you to the page."

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.text_she_doubts.active.steadied
WHO    VILLAGER — what the player reads after pressing "Copy it faithfully anyway."
       spoken on: conversations.scene.work.scribe.text_she_doubts.active.respond, button `back_the_faithful_copy`
       leaves the player on: conversations.scene.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.text_she_doubts.active.steadied`: the villager accepts. Subject `work.scribe.a_text_i_doubt`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.scribe.text_she_doubts.active.steadied/1   [124 chars]
    en  Faithfully, and with my name at the end so that anybody who wants to blame somebody for the copy can blame the right person.
    >>  ............................................
    pt  Fielmente, e com meu nome no fim, para que quem quiser culpar alguém pela cópia culpe a pessoa certa.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.text_she_doubts.active.steadied/2   [132 chars]
    en  Yes. If I am right about him, the record will show it eventually without my help. If I am wrong, I will have kept the only evidence.
    >>  ............................................
    pt  Sim. Se eu estiver certa sobre ele, o registro vai mostrar isso sem a minha ajuda. Se eu estiver errada, terei guardado a única prova.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.text_she_doubts.active.steadied/3   [106 chars]
    en  It is the whole discipline. A copyist who only copies what she believes has produced a book about herself.
    >>  ............................................
    pt  É a disciplina inteira. Uma copista que só copia aquilo em que acredita produziu um livro sobre si mesma.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the page."

*stance family `exit` · tone `plain` · answers the beat(s) `work.scribe.text_she_doubts.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.text_she_doubts.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.scribe.text_she_doubts.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.scribe.text_she_doubts.active.respond.leave   [34 chars]
    en  I'll let you get back to the page.
    >>  ............................................
    pt  Vou deixar você voltar à página.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the page."
       spoken on: conversations.scene.work.scribe.text_she_doubts.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.left`: the villager accepts. Subject `work.scribe.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.scribe.failing_eyes.active.respond / leave; conversations.scene.work.scribe.failing_eyes.succeeded.respond / leave; conversations.scene.work.scribe.followup / leave; conversations.scene.work.scribe.inherited_error.blocked.respond / leave; conversations.scene.work.scribe.inherited_error.succeeded.respond / leave; conversations.scene.work.scribe.text_she_doubts.succeeded.respond / leave; conversations.topic.work.scribe.craft.respond / leave; conversations.topic.work.scribe.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.scribe.failing_eyes.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.scribe.text_she_doubts.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.scribe.text_she_doubts.succeeded` — e.g. "%2$s is copied, word for word, with four lines of mine in the margin saying what would persuade me."


```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.text_she_doubts.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.scribe.text_she_doubts.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.scribe.text_she_doubts.succeeded.respond   [13 chars]
    en  That account.
    >>  ............................................
    pt  Aquele relato.
    >>  ............................................
```


### Button `note_the_discipline` — "A fair disagreement is a rare thing."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.scribe.text_she_doubts.succeeded` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.scribe.text_she_doubts.succeeded.note_the_discipline` — accepted phrasings: "a fair disagreement is a rare thing"; "a fair disagreement is a rare thing"; "disagreeing fairly is rare"
  - the message must contain one of: `disagreement`, `disagreeing`, `fair`
  - scored words: `disagreement`(1.8), `disagreeing`(1.8), `fair`(1.8), `rare`(0.8), `thing`(0.8), `fairly`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.text_she_doubts.succeeded.respond.note_the_discipline
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.scribe.text_she_doubts.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.scribe.text_she_doubts.succeeded.respond.note_the_discipline   [36 chars]
    en  A fair disagreement is a rare thing.
    >>  ............................................
    pt  Uma discordância justa é coisa rara.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.scribe.a_text_i_doubt`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.scribe.text_she_doubts"}
- Then opens: `conversations.scene.work.scribe.followup`
- …where the player's next choices will be: "What's the hardest part of copying a page?" | "I'll leave you to the page."

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.text_she_doubts.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "A fair disagreement is a rare thing."
       spoken on: conversations.scene.work.scribe.text_she_doubts.succeeded.respond, button `note_the_discipline`
       leaves the player on: conversations.scene.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.text_she_doubts.succeeded.acknowledged`: the villager accepts. Subject `work.scribe.a_text_i_doubt`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.scribe.text_she_doubts.succeeded.acknowledged/1   [133 chars]
    en  It is only rare because disagreement usually happens out loud, where it costs nothing to be unfair, and I had four days and a margin.
    >>  ............................................
    pt  Só é rara porque a discordância costuma acontecer em voz alta, onde ser injusto não custa nada, e eu tinha quatro dias e uma margem.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.text_she_doubts.succeeded.acknowledged/2   [145 chars]
    en  Thank you. Writing down what would change my mind took three of those four days, and it changed my mind about two other things while I was at it.
    >>  ............................................
    pt  Obrigada. Escrever o que mudaria minha cabeça levou três desses quatro dias, e mudou minha cabeça sobre outras duas coisas de quebra.
    >>  ............................................
  dialogue.conversations.scene.work.scribe.text_she_doubts.succeeded.acknowledged/3   [112 chars]
    en  The trick is that you have to mean it. A note that lists impossible evidence is a sneer with better handwriting.
    >>  ............................................
    pt  O truque é que você tem que estar falando sério. Uma nota que lista provas impossíveis é um deboche com letra melhor.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the page."

*stance family `exit` · tone `plain` · answers the beat(s) `work.scribe.text_she_doubts.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.scribe.text_she_doubts.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.scribe.text_she_doubts.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.scribe.text_she_doubts.succeeded.respond.leave   [34 chars]
    en  I'll let you get back to the page.
    >>  ............................................
    pt  Vou deixar você voltar à página.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the page."
       spoken on: conversations.scene.work.scribe.text_she_doubts.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.left`: the villager accepts. Subject `work.scribe.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.scribe.failing_eyes.active.respond / leave; conversations.scene.work.scribe.failing_eyes.succeeded.respond / leave; conversations.scene.work.scribe.followup / leave; conversations.scene.work.scribe.inherited_error.blocked.respond / leave; conversations.scene.work.scribe.inherited_error.succeeded.respond / leave; conversations.scene.work.scribe.text_she_doubts.active.respond / leave; conversations.topic.work.scribe.craft.respond / leave; conversations.topic.work.scribe.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.scribe.failing_eyes.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.scribe.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.scribe.craft` — e.g. "Copying is the easy half. The hard half is telling a first-hand account from a well-told second-hand one."


```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.scribe.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.scribe.craft.respond   [26 chars]
    en  That's the training of it.
    >>  ............................................
    pt  É esse o treino.
    >>  ............................................
```


### Button `ask_firsthand` — "How do you tell the difference?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.scribe.craft` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.scribe.craft.ask_firsthand` — accepted phrasings: "how do you tell the difference"
  - the message must contain one of: `firsthand`, `difference`, `account`
  - scored words: `firsthand`(1.5), `difference`(1.2), `account`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.craft.respond.ask_firsthand
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.craft.respond.ask_firsthand   [31 chars]
    en  How do you tell the difference?
    >>  ............................................
    pt  Como você distingue?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.scribe.craft.ask_firsthand`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.scribe.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the oldest thing you've saved?" | "Steady hand."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.craft.ask_firsthand
WHO    VILLAGER — what the player reads after pressing "How do you tell the difference?"
       spoken on: conversations.topic.work.scribe.craft.respond, button `ask_firsthand`
       leaves the player on: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.craft.ask_firsthand`: the villager explains. Subject `work.scribe.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.scribe.craft.ask_firsthand/1   [108 chars]
    en  A first-hand account gets the weather right and the meaning wrong. A second-hand one is the other way about.
    >>  ............................................
    pt  Um relato em primeira mão acerta o clima e erra o significado. Um em segunda é o contrário.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.craft.ask_firsthand/2   [85 chars]
    en  The dull details, %1$s. Nobody inventing a story remembers that their boots were wet.
    >>  ............................................
    pt  Os detalhes chatos, %1$s. Ninguém inventando uma história lembra que as botas estavam molhadas.
    >>  ............................................
```


### Button `admire` — "Burning ten of eleven sounds cruel and probably wasn't."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.scribe.craft` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.scribe.craft.admire` — accepted phrasings: "burning ten of eleven sounds cruel and probably wasn't"
  - the message must contain one of: `burning`, `cruel`, `eleven`
  - scored words: `burning`(1.5), `cruel`(1.2), `eleven`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.craft.respond.admire   [55 chars]
    en  Burning ten of eleven sounds cruel and probably wasn't.
    >>  ............................................
    pt  Queimar dez de onze soa cruel e provavelmente não era.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.scribe.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.scribe.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.scribe.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the oldest thing you've saved?" | "Steady hand."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.craft.admire
WHO    VILLAGER — what the player reads after pressing "Burning ten of eleven sounds cruel and probably wasn't."
       spoken on: conversations.topic.work.scribe.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.craft.admire`: the villager accepts. Subject `work.scribe.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.scribe.craft.admire/1   [100 chars]
    en  It was both. He was cruel and he was right, and I've never worked out how to teach it any other way.
    >>  ............................................
    pt  Era as duas coisas. Ele era cruel e tinha razão, e eu nunca descobri outro jeito de ensinar.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.craft.admire/2   [99 chars]
    en  The eleventh was the only one worth keeping and I knew it before he did, %1$s. That was the lesson.
    >>  ............................................
    pt  A décima primeira era a única que valia e eu soube antes dele, %1$s. Foi essa a lição.
    >>  ............................................
```


### Button `ask_page` — "What was on the page?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.scribe.craft` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.scribe.craft.ask_page` — accepted phrasings: "what was on the page"
  - the message must contain one of: `page`, `copied`, `contained`
  - scored words: `page`(1.5), `copied`(1.2), `contained`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.craft.respond.ask_page
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.craft.respond.ask_page   [21 chars]
    en  What was on the page?
    >>  ............................................
    pt  O que tinha na página?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.scribe.craft.ask_page`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.scribe.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the oldest thing you've saved?" | "Steady hand."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.craft.ask_page
WHO    VILLAGER — what the player reads after pressing "What was on the page?"
       spoken on: conversations.topic.work.scribe.craft.respond, button `ask_page`
       leaves the player on: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.craft.ask_page`: the villager explains. Subject `work.scribe.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.scribe.craft.ask_page/1   [103 chars]
    en  A list of provisions. Deliberately dull, so that I couldn't be carried along by it and had to be exact.
    >>  ............................................
    pt  Uma lista de mantimentos. Deliberadamente chata, pra eu não me deixar levar e ter que ser exato.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.craft.ask_page/2   [94 chars]
    en  Nothing. That was the point, %1$s. He'd have ruined me by starting with something interesting.
    >>  ............................................
    pt  Nada. Era essa a questão, %1$s. Ele teria me arruinado começando com algo interessante.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the page."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.scribe.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.craft.respond.leave   [34 chars]
    en  I'll let you get back to the page.
    >>  ............................................
    pt  Vou deixar você voltar pra página.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the page."
       spoken on: conversations.topic.work.scribe.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.left`: the villager accepts. Subject `work.scribe.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.scribe.failing_eyes.active.respond / leave; conversations.scene.work.scribe.failing_eyes.succeeded.respond / leave; conversations.scene.work.scribe.followup / leave; conversations.scene.work.scribe.inherited_error.blocked.respond / leave; conversations.scene.work.scribe.inherited_error.succeeded.respond / leave; conversations.scene.work.scribe.text_she_doubts.active.respond / leave; conversations.scene.work.scribe.text_she_doubts.succeeded.respond / leave; conversations.topic.work.scribe.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.scribe.failing_eyes.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.scribe.followup`

**Reached from 20 route(s):** `conversations.scene.work.scribe.followup` / `ask_more`; `conversations.topic.work.scribe.craft.respond` / `ask_firsthand`; `conversations.topic.work.scribe.craft.respond` / `admire`; `conversations.topic.work.scribe.craft.respond` / `ask_page`; `conversations.topic.work.scribe.future.respond` / `ask_second_copy`; `conversations.topic.work.scribe.future.respond` / `encourage`; `conversations.topic.work.scribe.future.respond` / `ask_eleven`; `conversations.topic.work.scribe.respond` / `ask_hard`; `conversations.topic.work.scribe.respond` / `value`; `conversations.topic.work.scribe.respond` / `challenge`; `conversations.topic.work.scribe.respond` / `challenge`; `conversations.topic.work.scribe.risk.respond` / `ask_burned` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.scribe.challenge.landed` — e.g. "Some of them, yes. Copying accurately is worth more than understanding badly."
- `conversations.work.prof.scribe.challenge.stung` — e.g. "...I read four hands and speak two dead tongues. Try one."
- `conversations.work.prof.scribe.craft.admire` — e.g. "It was both. He was cruel and he was right, and I've never worked out how to teach it any other way."
- `conversations.work.prof.scribe.craft.ask_firsthand` — e.g. "A first-hand account gets the weather right and the meaning wrong. A second-hand one is the other way about."
- `conversations.work.prof.scribe.craft.ask_page` — e.g. "A list of provisions. Deliberately dull, so that I couldn't be carried along by it and had to be exact."
- `conversations.work.prof.scribe.future.ask_eleven` — e.g. "Dates, mostly. Two are about a name and one of those two matters more than the other ten together."
- `conversations.work.prof.scribe.future.ask_second_copy` — e.g. "Four valleys away, with a scribe I've written to twice and never met. She's agreed and I've not sent it."
- `conversations.work.prof.scribe.future.encourage` — e.g. "...The same as no copy. That's exact and it's unpleasant and I'll have it on the road this month."
- `conversations.work.prof.scribe.hard` — e.g. "I mean the binding is not always leather and the ink is not always ink, %1$s."
- `conversations.work.prof.scribe.risk.ask_burned` — e.g. "Anyone certain of something. That's a larger group than you'd think and it changes membership every generation."
- `conversations.work.prof.scribe.risk.ask_wrong` — e.g. "Once, a number, and it was in circulation for four years before somebody's grandmother contradicted it."
- `conversations.work.prof.scribe.risk.sympathise` — e.g. "...It is, and I've known it for six years, and I have spent those six years copying instead of asking."
- `conversations.work.prof.scribe.task.ask_believe` — e.g. "My belief isn't in the copy. That's the discipline and it's the only thing that makes the copy worth having."
- `conversations.work.prof.scribe.task.ask_who` — e.g. "Because one of the two was written by somebody who wanted to have been there. That's the commonest fault."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.scribe.followup   [32 chars]
    en  That's the desk and its dangers.
    >>  ............................................
    pt  É a escrivaninha e os perigos dela.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.scribe.challenge.landed`, `work.scribe.challenge.stung`, `work.scribe.craft.admire`, `work.scribe.craft.ask_firsthand`, `work.scribe.craft.ask_page`, `work.scribe.future.ask_eleven`, `work.scribe.future.ask_second_copy`, `work.scribe.future.encourage`, `work.scribe.hard`, `work.scribe.risk.ask_burned`, `work.scribe.risk.ask_wrong`, `work.scribe.risk.sympathise`, `work.scribe.task.ask_believe`, `work.scribe.task.ask_who`, `work.scribe.task.offer_hands`, `work.scribe.value`, `work.scribe.village.ask_librarian`, `work.scribe.village.ask_two_hundred`, `work.scribe.village.say_thanks` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.scribe.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `emergency`
  - scored words: `thought`(1.2), `emergency`(1.5), `quiet`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.scribe.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.scribe.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.scribe.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.scribe.thanks`: the villager accepts. Subject `work.scribe.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.scribe.thanks/1   [55 chars]
    en  Few visitors do. They see a quiet man and a small room.
    >>  ............................................
    pt  Poucos visitantes pensam. Veem um homem quieto e uma sala pequena.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.thanks/2   [74 chars]
    en  It is a slow, unglamorous emergency, %1$s. That is a hard thing to convey.
    >>  ............................................
    pt  É uma emergência lenta e sem glamour, %1$s. É difícil de transmitir.
    >>  ............................................
```


### Button `ask_more` — "What's the oldest thing you've saved?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.scribe.challenge.landed`, `work.scribe.challenge.stung`, `work.scribe.craft.admire`, `work.scribe.craft.ask_firsthand`, `work.scribe.craft.ask_page`, `work.scribe.future.ask_eleven`, `work.scribe.future.ask_second_copy`, `work.scribe.future.encourage`, `work.scribe.hard`, `work.scribe.risk.ask_burned`, `work.scribe.risk.ask_wrong`, `work.scribe.risk.sympathise`, `work.scribe.task.ask_believe`, `work.scribe.task.ask_who`, `work.scribe.task.offer_hands`, `work.scribe.value`, `work.scribe.village.ask_librarian`, `work.scribe.village.ask_two_hundred`, `work.scribe.village.say_thanks` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.scribe.more` — accepted phrasings: "what's the oldest thing you've saved"
  - the message must contain one of: `oldest`, `saved`, `recovered`
  - scored words: `oldest`(1.5), `saved`(1.2), `recovered`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.followup.ask_more   [37 chars]
    en  What's the oldest thing you've saved?
    >>  ............................................
    pt  Qual a coisa mais antiga que você salvou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.scribe.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.more
WHO    VILLAGER — what the player reads after pressing "What's the oldest thing you've saved?"
       spoken on: conversations.topic.work.scribe.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.scribe.more`: the villager discloses. Subject `work.scribe.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.scribe.more/1   [90 chars]
    en  Nine lines about a river that no longer exists. They took me a winter and I'd do it again.
    >>  ............................................
    pt  Nove linhas sobre um rio que não existe mais. Levaram um inverno e eu faria de novo.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.more/2   [102 chars]
    en  A name. One name, from a page that crumbled as I finished. Somebody is remembered because I was quick.
    >>  ............................................
    pt  Um nome. Um nome só, de uma página que se desfez quando eu terminei. Alguém é lembrado porque eu fui rápido.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a vanished river. Somebody stood beside it once and wrote it down, and now there's me.
    >>  ............................................
    pt  Nove linhas sobre um rio sumido. Alguém esteve ao lado dele e anotou, e agora tem eu.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.scribe.more/2
    en  A stone room. Some of what I hold would be burned if the wrong person read it, and my roof is thatch.
    >>  ............................................
    pt  Um cômodo de pedra. Parte do que eu guardo seria queimada se a pessoa errada lesse, e meu telhado é palha.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a vanished river. A winter to copy nine lines is the correct amount of time.
    >>  ............................................
    pt  Nove linhas sobre um rio sumido. Um inverno pra copiar nove linhas é o tempo correto.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.scribe.more/2
    en  A stone room and a second copy. It'll take a season to arrange and I've had six years to start.
    >>  ............................................
    pt  Um cômodo de pedra e uma segunda cópia. Leva uma estação pra arranjar e eu tive seis anos pra começar.
    >>  ............................................
  confident.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a river that no longer exists. They took me a winter and I'd do it again.
    >>  ............................................
    pt  Nove linhas sobre um rio que não existe mais. Levaram um inverno e eu faria de novo.
    >>  ............................................
  confident.dialogue.conversations.work.prof.scribe.more/2
    en  A stone room, and a second copy of everything kept somewhere else. That is the whole plan.
    >>  ............................................
    pt  Um cômodo de pedra, e uma segunda cópia de tudo guardada em outro lugar. É todo o plano.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a river that no longer exists. They took me a winter and I'd do it again.
    >>  ............................................
    pt  Nove linhas sobre um rio que não existe mais. Levaram um inverno e eu faria de novo.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.scribe.more/2
    en  A stone room, and a second copy of everything kept somewhere else. That is the whole plan.
    >>  ............................................
    pt  Um cômodo de pedra, e uma segunda cópia de tudo guardada em outro lugar. É todo o plano.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a vanished river. The librarian argued with me about two of them for a year.
    >>  ............................................
    pt  Nove linhas sobre um rio sumido. O bibliotecário discutiu comigo sobre duas delas por um ano.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.scribe.more/2
    en  A second copy, four valleys away, with a scribe I've written to twice and never met.
    >>  ............................................
    pt  Uma segunda cópia, a quatro vales, com uma escriba pra quem eu escrevi duas vezes e nunca conheci.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a vanished river. The librarian argued with me about two of them for a year.
    >>  ............................................
    pt  Nove linhas sobre um rio sumido. O bibliotecário discutiu comigo sobre duas delas por um ano.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.scribe.more/2
    en  A second copy, four valleys away, with a scribe I've written to twice and never met.
    >>  ............................................
    pt  Uma segunda cópia, a quatro vales, com uma escriba pra quem eu escrevi duas vezes e nunca conheci.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a vanished river. The librarian argued with me about two of them for a year.
    >>  ............................................
    pt  Nove linhas sobre um rio sumido. O bibliotecário discutiu comigo sobre duas delas por um ano.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.scribe.more/2
    en  A second copy, four valleys away, with a scribe I've written to twice and never met.
    >>  ............................................
    pt  Uma segunda cópia, a quatro vales, com uma escriba pra quem eu escrevi duas vezes e nunca conheci.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a vanished river. Somebody stood beside it once and wrote it down, and now there's me.
    >>  ............................................
    pt  Nove linhas sobre um rio sumido. Alguém esteve ao lado dele e anotou, e agora tem eu.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.scribe.more/2
    en  A stone room. Some of what I hold would be burned if the wrong person read it, and my roof is thatch.
    >>  ............................................
    pt  Um cômodo de pedra. Parte do que eu guardo seria queimada se a pessoa errada lesse, e meu telhado é palha.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a river that no longer exists. They took me a winter and I'd do it again.
    >>  ............................................
    pt  Nove linhas sobre um rio que não existe mais. Levaram um inverno e eu faria de novo.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.scribe.more/2
    en  A stone room, and a second copy of everything kept somewhere else. That is the whole plan.
    >>  ............................................
    pt  Um cômodo de pedra, e uma segunda cópia de tudo guardada em outro lugar. É todo o plano.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a river that no longer exists. They took me a winter and I'd do it again.
    >>  ............................................
    pt  Nove linhas sobre um rio que não existe mais. Levaram um inverno e eu faria de novo.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.scribe.more/2
    en  A stone room, and a second copy of everything kept somewhere else. That is the whole plan.
    >>  ............................................
    pt  Um cômodo de pedra, e uma segunda cópia de tudo guardada em outro lugar. É todo o plano.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a river that no longer exists. A winter's work, and worth every day of it.
    >>  ............................................
    pt  Nove linhas sobre um rio que não existe mais. Trabalho de um inverno, e valeu cada dia.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.scribe.more/2
    en  A stone room. Anywhere the fire and the damp can't both reach at once.
    >>  ............................................
    pt  Um cômodo de pedra. Qualquer lugar que o fogo e a umidade não alcancem juntos.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a vanished river. A winter to copy nine lines is the correct amount of time.
    >>  ............................................
    pt  Nove linhas sobre um rio sumido. Um inverno pra copiar nove linhas é o tempo correto.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.scribe.more/2
    en  A stone room and a second copy. It'll take a season to arrange and I've had six years to start.
    >>  ............................................
    pt  Um cômodo de pedra e uma segunda cópia. Leva uma estação pra arranjar e eu tive seis anos pra começar.
    >>  ............................................
  odd.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a river that no longer exists. A winter's work, and worth every day of it.
    >>  ............................................
    pt  Nove linhas sobre um rio que não existe mais. Trabalho de um inverno, e valeu cada dia.
    >>  ............................................
  odd.dialogue.conversations.work.prof.scribe.more/2
    en  A stone room. Anywhere the fire and the damp can't both reach at once.
    >>  ............................................
    pt  Um cômodo de pedra. Qualquer lugar que o fogo e a umidade não alcancem juntos.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a vanished river. A winter to copy nine lines is the correct amount of time.
    >>  ............................................
    pt  Nove linhas sobre um rio sumido. Um inverno pra copiar nove linhas é o tempo correto.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.scribe.more/2
    en  A stone room and a second copy. It'll take a season to arrange and I've had six years to start.
    >>  ............................................
    pt  Um cômodo de pedra e uma segunda cópia. Leva uma estação pra arranjar e eu tive seis anos pra começar.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a river that no longer exists! A whole winter. Best winter I've had.
    >>  ............................................
    pt  Nove linhas sobre um rio que não existe mais! Um inverno inteiro. Melhor inverno que eu tive.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.scribe.more/2
    en  A stone room. My roof is thatch and my life's work is paper, which is a joke I'd rather not finish.
    >>  ............................................
    pt  Um cômodo de pedra. Meu telhado é palha e minha obra é papel, uma piada que eu prefiro não terminar.
    >>  ............................................
  playful.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a river that no longer exists! A whole winter. Best winter I've had.
    >>  ............................................
    pt  Nove linhas sobre um rio que não existe mais! Um inverno inteiro. Melhor inverno que eu tive.
    >>  ............................................
  playful.dialogue.conversations.work.prof.scribe.more/2
    en  A stone room. My roof is thatch and my life's work is paper, which is a joke I'd rather not finish.
    >>  ............................................
    pt  Um cômodo de pedra. Meu telhado é palha e minha obra é papel, uma piada que eu prefiro não terminar.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a vanished river. A winter to copy nine lines is the correct amount of time.
    >>  ............................................
    pt  Nove linhas sobre um rio sumido. Um inverno pra copiar nove linhas é o tempo correto.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.scribe.more/2
    en  A stone room and a second copy. It'll take a season to arrange and I've had six years to start.
    >>  ............................................
    pt  Um cômodo de pedra e uma segunda cópia. Leva uma estação pra arranjar e eu tive seis anos pra começar.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a vanished river. Somebody stood beside it once and wrote it down, and now there's me.
    >>  ............................................
    pt  Nove linhas sobre um rio sumido. Alguém esteve ao lado dele e anotou, e agora tem eu.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.scribe.more/2
    en  A stone room. Some of what I hold would be burned if the wrong person read it, and my roof is thatch.
    >>  ............................................
    pt  Um cômodo de pedra. Parte do que eu guardo seria queimada se a pessoa errada lesse, e meu telhado é palha.
    >>  ............................................
  shy.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a river that no longer exists. A winter's work, and worth every day of it.
    >>  ............................................
    pt  Nove linhas sobre um rio que não existe mais. Trabalho de um inverno, e valeu cada dia.
    >>  ............................................
  shy.dialogue.conversations.work.prof.scribe.more/2
    en  A stone room. Anywhere the fire and the damp can't both reach at once.
    >>  ............................................
    pt  Um cômodo de pedra. Qualquer lugar que o fogo e a umidade não alcancem juntos.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a river that no longer exists! A whole winter. Best winter I've had.
    >>  ............................................
    pt  Nove linhas sobre um rio que não existe mais! Um inverno inteiro. Melhor inverno que eu tive.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.scribe.more/2
    en  A stone room. My roof is thatch and my life's work is paper, which is a joke I'd rather not finish.
    >>  ............................................
    pt  Um cômodo de pedra. Meu telhado é palha e minha obra é papel, uma piada que eu prefiro não terminar.
    >>  ............................................
  witty.dialogue.conversations.work.prof.scribe.more/1
    en  Nine lines about a river that no longer exists! A whole winter. Best winter I've had.
    >>  ............................................
    pt  Nove linhas sobre um rio que não existe mais! Um inverno inteiro. Melhor inverno que eu tive.
    >>  ............................................
  witty.dialogue.conversations.work.prof.scribe.more/2
    en  A stone room. My roof is thatch and my life's work is paper, which is a joke I'd rather not finish.
    >>  ............................................
    pt  Um cômodo de pedra. Meu telhado é palha e minha obra é papel, uma piada que eu prefiro não terminar.
    >>  ............................................
```

</details>


### Button `leave` — "Steady hand."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.scribe.challenge.landed`, `work.scribe.challenge.stung`, `work.scribe.craft.admire`, `work.scribe.craft.ask_firsthand`, `work.scribe.craft.ask_page`, `work.scribe.future.ask_eleven`, `work.scribe.future.ask_second_copy`, `work.scribe.future.encourage`, `work.scribe.hard`, `work.scribe.risk.ask_burned`, `work.scribe.risk.ask_wrong`, `work.scribe.risk.sympathise`, `work.scribe.task.ask_believe`, `work.scribe.task.ask_who`, `work.scribe.task.offer_hands`, `work.scribe.value`, `work.scribe.village.ask_librarian`, `work.scribe.village.ask_two_hundred`, `work.scribe.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.followup.leave   [12 chars]
    en  Steady hand.
    >>  ............................................
    pt  Mão firme.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.leave
WHO    VILLAGER — what the player reads after pressing "Steady hand."
       spoken on: conversations.topic.work.scribe.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.left`: the villager accepts. Subject `work.scribe.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.scribe.failing_eyes.active.respond / leave; conversations.scene.work.scribe.failing_eyes.succeeded.respond / leave; conversations.scene.work.scribe.followup / leave; conversations.scene.work.scribe.inherited_error.blocked.respond / leave; conversations.scene.work.scribe.inherited_error.succeeded.respond / leave; conversations.scene.work.scribe.text_she_doubts.active.respond / leave; conversations.scene.work.scribe.text_she_doubts.succeeded.respond / leave; conversations.topic.work.scribe.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.scribe.failing_eyes.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.scribe.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.scribe.future` — e.g. "A stone room and a second copy of everything, kept somewhere else. That's the whole plan and it's not complicated."


```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.scribe.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.scribe.future.respond   [30 chars]
    en  That's what's left to arrange.
    >>  ............................................
    pt  É o que falta arranjar.
    >>  ............................................
```


### Button `ask_second_copy` — "Where would the second copy live?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.scribe.future` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.scribe.future.ask_second_copy` — accepted phrasings: "where would the second copy live"
  - the message must contain one of: `copy`, `second`, `elsewhere`
  - scored words: `copy`(1.5), `second`(1.2), `elsewhere`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.future.respond.ask_second_copy
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.future.respond.ask_second_copy   [33 chars]
    en  Where would the second copy live?
    >>  ............................................
    pt  Onde ficaria a segunda cópia?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.scribe.future.ask_second_copy`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.scribe.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the oldest thing you've saved?" | "Steady hand."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.future.ask_second_copy
WHO    VILLAGER — what the player reads after pressing "Where would the second copy live?"
       spoken on: conversations.topic.work.scribe.future.respond, button `ask_second_copy`
       leaves the player on: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.future.ask_second_copy`: the villager explains. Subject `work.scribe.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.scribe.future.ask_second_copy/1   [104 chars]
    en  Four valleys away, with a scribe I've written to twice and never met. She's agreed and I've not sent it.
    >>  ............................................
    pt  A quatro vales, com uma escriba pra quem eu escrevi duas vezes e nunca conheci. Ela topou e eu não enviei.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.future.ask_second_copy/2   [106 chars]
    en  Anywhere that isn't under the same roof, %1$s. That's the entire requirement and I've made it complicated.
    >>  ............................................
    pt  Em qualquer lugar que não sob o mesmo telhado, %1$s. É todo o requisito e eu complicei.
    >>  ............................................
```


### Button `encourage` — "Send it. An unsent copy is the same as no copy."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.scribe.future` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.scribe.future.encourage` — accepted phrasings: "send it. an unsent copy is the same as no copy"
  - the message must contain one of: `send`, `unsent`, `copy`
  - scored words: `send`(1.5), `unsent`(1.2), `copy`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.future.respond.encourage   [47 chars]
    en  Send it. An unsent copy is the same as no copy.
    >>  ............................................
    pt  Envie. Uma cópia não enviada é o mesmo que nenhuma.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.scribe.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.scribe.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.scribe.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the oldest thing you've saved?" | "Steady hand."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.future.encourage
WHO    VILLAGER — what the player reads after pressing "Send it. An unsent copy is the same as no copy."
       spoken on: conversations.topic.work.scribe.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.future.encourage`: the villager accepts. Subject `work.scribe.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.scribe.future.encourage/1   [97 chars]
    en  ...The same as no copy. That's exact and it's unpleasant and I'll have it on the road this month.
    >>  ............................................
    pt  ...O mesmo que nenhuma. É exato e desagradável e eu vou pôr na estrada este mês.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.future.encourage/2   [95 chars]
    en  Six years of copying so that it could sit next to the original, %1$s. Put that way it's absurd.
    >>  ............................................
    pt  Seis anos copiando pra ficar do lado do original, %1$s. Assim dito é absurdo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy. I've known that and not let myself say it.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia. Eu sabia e não me deixei dizer.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years of copying so it could sit beside the original. I'd rather not count the hours.
    >>  ............................................
    pt  Seis anos copiando pra ficar ao lado do original. Prefiro não contar as horas.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy. One fire is all it takes, and I've seen one.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia. Basta um incêndio, e eu já vi um.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years of copying so it could sit beside the original. Old habits, badly reasoned.
    >>  ............................................
    pt  Seis anos copiando pra ficar ao lado do original. Hábitos velhos, mal pensados.
    >>  ............................................
  confident.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy. That's exact and unpleasant, and I'll have it on the road this month.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia. É exato e desagradável, e vai pra estrada este mês.
    >>  ............................................
  confident.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years of copying so it could sit beside the original. Put that way it's absurd.
    >>  ............................................
    pt  Seis anos copiando pra ficar ao lado do original. Dito assim é absurdo.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy. That's exact and unpleasant, and I'll have it on the road this month.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia. É exato e desagradável, e vai pra estrada este mês.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years of copying so it could sit beside the original. Put that way it's absurd.
    >>  ............................................
    pt  Seis anos copiando pra ficar ao lado do original. Dito assim é absurdo.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy, %1$s. Exact and unpleasant. It goes on the road this month.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia, %1$s. Exato e desagradável. Vai pra estrada este mês.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years of copying so it could sit beside the original. You've made that plain.
    >>  ............................................
    pt  Seis anos copiando pra ficar ao lado do original. Você deixou isso claro.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy, %1$s. Exact and unpleasant. It goes on the road this month.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia, %1$s. Exato e desagradável. Vai pra estrada este mês.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years of copying so it could sit beside the original. You've made that plain.
    >>  ............................................
    pt  Seis anos copiando pra ficar ao lado do original. Você deixou isso claro.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy, %1$s. Exact and unpleasant. It goes on the road this month.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia, %1$s. Exato e desagradável. Vai pra estrada este mês.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years of copying so it could sit beside the original. You've made that plain.
    >>  ............................................
    pt  Seis anos copiando pra ficar ao lado do original. Você deixou isso claro.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy. I've known that and not let myself say it.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia. Eu sabia e não me deixei dizer.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years of copying so it could sit beside the original. I'd rather not count the hours.
    >>  ............................................
    pt  Seis anos copiando pra ficar ao lado do original. Prefiro não contar as horas.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy. That's exact and unpleasant, and I'll have it on the road this month.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia. É exato e desagradável, e vai pra estrada este mês.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years of copying so it could sit beside the original. Put that way it's absurd.
    >>  ............................................
    pt  Seis anos copiando pra ficar ao lado do original. Dito assim é absurdo.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy. That's exact and unpleasant, and I'll have it on the road this month.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia. É exato e desagradável, e vai pra estrada este mês.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years of copying so it could sit beside the original. Put that way it's absurd.
    >>  ............................................
    pt  Seis anos copiando pra ficar ao lado do original. Dito assim é absurdo.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy. On the road this month.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia. Pra estrada este mês.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years, so it could sit beside the original.
    >>  ............................................
    pt  Seis anos, pra ficar ao lado do original.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy. One fire is all it takes, and I've seen one.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia. Basta um incêndio, e eu já vi um.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years of copying so it could sit beside the original. Old habits, badly reasoned.
    >>  ............................................
    pt  Seis anos copiando pra ficar ao lado do original. Hábitos velhos, mal pensados.
    >>  ............................................
  odd.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy. On the road this month.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia. Pra estrada este mês.
    >>  ............................................
  odd.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years, so it could sit beside the original.
    >>  ............................................
    pt  Seis anos, pra ficar ao lado do original.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy. One fire is all it takes, and I've seen one.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia. Basta um incêndio, e eu já vi um.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years of copying so it could sit beside the original. Old habits, badly reasoned.
    >>  ............................................
    pt  Seis anos copiando pra ficar ao lado do original. Hábitos velhos, mal pensados.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy! Exact, unpleasant, and correct. It goes on the road this month.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia! Exato, desagradável e correto. Vai pra estrada este mês.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years of copying so it could sit beside the original. Absurd, put that way.
    >>  ............................................
    pt  Seis anos copiando pra ficar ao lado do original. Absurdo, dito assim.
    >>  ............................................
  playful.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy! Exact, unpleasant, and correct. It goes on the road this month.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia! Exato, desagradável e correto. Vai pra estrada este mês.
    >>  ............................................
  playful.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years of copying so it could sit beside the original. Absurd, put that way.
    >>  ............................................
    pt  Seis anos copiando pra ficar ao lado do original. Absurdo, dito assim.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy. One fire is all it takes, and I've seen one.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia. Basta um incêndio, e eu já vi um.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years of copying so it could sit beside the original. Old habits, badly reasoned.
    >>  ............................................
    pt  Seis anos copiando pra ficar ao lado do original. Hábitos velhos, mal pensados.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy. I've known that and not let myself say it.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia. Eu sabia e não me deixei dizer.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years of copying so it could sit beside the original. I'd rather not count the hours.
    >>  ............................................
    pt  Seis anos copiando pra ficar ao lado do original. Prefiro não contar as horas.
    >>  ............................................
  shy.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy. On the road this month.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia. Pra estrada este mês.
    >>  ............................................
  shy.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years, so it could sit beside the original.
    >>  ............................................
    pt  Seis anos, pra ficar ao lado do original.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy! Exact, unpleasant, and correct. It goes on the road this month.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia! Exato, desagradável e correto. Vai pra estrada este mês.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years of copying so it could sit beside the original. Absurd, put that way.
    >>  ............................................
    pt  Seis anos copiando pra ficar ao lado do original. Absurdo, dito assim.
    >>  ............................................
  witty.dialogue.conversations.work.prof.scribe.future.encourage/1
    en  ...The same as no copy! Exact, unpleasant, and correct. It goes on the road this month.
    >>  ............................................
    pt  ...O mesmo que nenhuma cópia! Exato, desagradável e correto. Vai pra estrada este mês.
    >>  ............................................
  witty.dialogue.conversations.work.prof.scribe.future.encourage/2
    en  Six years of copying so it could sit beside the original. Absurd, put that way.
    >>  ............................................
    pt  Seis anos copiando pra ficar ao lado do original. Absurdo, dito assim.
    >>  ............................................
```

</details>


### Button `ask_eleven` — "What are the eleven disagreements about?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.scribe.future` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.scribe.future.ask_eleven` — accepted phrasings: "what are the eleven disagreements about"
  - the message must contain one of: `disagreements`, `eleven`
  - scored words: `disagreements`(1.5), `eleven`(1.2), `about`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.future.respond.ask_eleven
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.future.respond.ask_eleven   [40 chars]
    en  What are the eleven disagreements about?
    >>  ............................................
    pt  Sobre o que são as onze discordâncias?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.scribe.future.ask_eleven`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.scribe.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the oldest thing you've saved?" | "Steady hand."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.future.ask_eleven
WHO    VILLAGER — what the player reads after pressing "What are the eleven disagreements about?"
       spoken on: conversations.topic.work.scribe.future.respond, button `ask_eleven`
       leaves the player on: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.future.ask_eleven`: the villager explains. Subject `work.scribe.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.scribe.future.ask_eleven/1   [98 chars]
    en  Dates, mostly. Two are about a name and one of those two matters more than the other ten together.
    >>  ............................................
    pt  Datas, principalmente. Duas são sobre um nome e uma dessas duas importa mais que as outras dez juntas.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.future.ask_eleven/2   [103 chars]
    en  Nine are trivial. Two would change what this valley thinks it is, %1$s, and those are the two we avoid.
    >>  ............................................
    pt  Nove são triviais. Duas mudariam o que este vale pensa que é, %1$s, e são as duas que evitamos.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the page."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.scribe.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.future.respond.leave   [34 chars]
    en  I'll let you get back to the page.
    >>  ............................................
    pt  Vou deixar você voltar pra página.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the page."
       spoken on: conversations.topic.work.scribe.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.left`: the villager accepts. Subject `work.scribe.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.scribe.failing_eyes.active.respond / leave; conversations.scene.work.scribe.failing_eyes.succeeded.respond / leave; conversations.scene.work.scribe.followup / leave; conversations.scene.work.scribe.inherited_error.blocked.respond / leave; conversations.scene.work.scribe.inherited_error.succeeded.respond / leave; conversations.scene.work.scribe.text_she_doubts.active.respond / leave; conversations.scene.work.scribe.text_she_doubts.succeeded.respond / leave; conversations.topic.work.scribe.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.scribe.failing_eyes.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.scribe.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.scribe` — e.g. "I copy what the dragons leave behind — words older than kingdoms. My ink hand shakes for good reason."


```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.scribe.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.scribe.respond   [45 chars]
    en  That's the ink and the reason my hand shakes.
    >>  ............................................
    pt  É a tinta e o motivo da minha mão tremer.
    >>  ............................................
```


### Button `ask_hard` — "What do you mean, some of them bite?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.scribe.identity` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.scribe.hard` — accepted phrasings: "what do you mean, some of them bite"
  - the message must contain one of: `bite`, `dangerous`
  - scored words: `bite`(1.5), `dangerous`(1.2), `mean`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.respond.ask_hard   [36 chars]
    en  What do you mean, some of them bite?
    >>  ............................................
    pt  Como assim, alguns mordem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.scribe.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.scribe.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the oldest thing you've saved?" | "Steady hand."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.hard
WHO    VILLAGER — what the player reads after pressing "What do you mean, some of them bite?"
       spoken on: conversations.topic.work.scribe.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.hard`: the villager explains. Subject `work.scribe.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.scribe.followup / ask_more
```

> Written out in full under **`conversations.scene.work.scribe.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "Somebody has to keep these from being lost."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.scribe.identity` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.scribe.value` — accepted phrasings: "somebody has to keep these from being lost"
  - the message must contain one of: `lost`, `preserve`, `saved`
  - scored words: `lost`(1.5), `preserve`(1.5), `saved`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.respond.value   [43 chars]
    en  Somebody has to keep these from being lost.
    >>  ............................................
    pt  Alguém tem que impedir que isso se perca.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.scribe.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.scribe.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.scribe.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the oldest thing you've saved?" | "Steady hand."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.value
WHO    VILLAGER — what the player reads after pressing "Somebody has to keep these from being lost."
       spoken on: conversations.topic.work.scribe.respond, button `value`
       leaves the player on: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.value`: the villager accepts. Subject `work.scribe.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.scribe.value/1   [84 chars]
    en  Somebody does. There is nobody else within four valleys who can read the older hand.
    >>  ............................................
    pt  Alguém tem. Não há mais ninguém em quatro vales que consiga ler a caligrafia antiga.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.value/2   [74 chars]
    en  That is exactly it. A candle against forgetting, and the wind is constant.
    >>  ............................................
    pt  É exatamente isso. Uma vela contra o esquecimento, e o vento é constante.
    >>  ............................................
```


### Button `challenge` — "You're copying words you don't understand."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.scribe.identity` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.scribe.challenge` — accepted phrasings: "you're copying words you don't understand"
  - the message must contain one of: `copying`, `understand`
  - scored words: `copying`(1.5), `understand`(1.2), `words`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.respond.challenge   [42 chars]
    en  You're copying words you don't understand.
    >>  ............................................
    pt  Você está copiando palavras que não entende.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.scribe.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.scribe.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.scribe.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the oldest thing you've saved?" | "Steady hand."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.challenge.landed
WHO    VILLAGER — what the player reads after pressing "You're copying words you don't understand."
       spoken on: conversations.topic.work.scribe.respond, button `challenge`
       leaves the player on: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.challenge.landed`: the villager resists. Subject `work.scribe.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.scribe.challenge.landed/1   [77 chars]
    en  Some of them, yes. Copying accurately is worth more than understanding badly.
    >>  ............................................
    pt  Algumas, sim. Copiar com precisão vale mais que entender mal.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.challenge.landed/2   [77 chars]
    en  That's the honest state of it, %1$s. I mark what I've guessed. Most wouldn't.
    >>  ............................................
    pt  É o estado honesto da coisa, %1$s. Eu marco o que adivinhei. A maioria não marcaria.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.scribe.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.scribe.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.scribe.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the oldest thing you've saved?" | "Steady hand."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.challenge.stung
WHO    VILLAGER — what the player reads after pressing "You're copying words you don't understand."
       spoken on: conversations.topic.work.scribe.respond, button `challenge`
       leaves the player on: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.challenge.stung`: the villager resists. Subject `work.scribe.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.scribe.challenge.stung/1   [57 chars]
    en  ...I read four hands and speak two dead tongues. Try one.
    >>  ............................................
    pt  ...Eu leio quatro caligrafias e falo duas línguas mortas. Tente uma.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.challenge.stung/2   [75 chars]
    en  Don't understand. Right. Bring me a page and we'll see which of us doesn't.
    >>  ............................................
    pt  Não entendo. Certo. Me traga uma página e a gente vê qual de nós não entende.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the page."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.scribe.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.respond.leave   [34 chars]
    en  I'll let you get back to the page.
    >>  ............................................
    pt  Vou deixar você voltar pra página.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the page."
       spoken on: conversations.topic.work.scribe.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.left`: the villager accepts. Subject `work.scribe.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.scribe.failing_eyes.active.respond / leave; conversations.scene.work.scribe.failing_eyes.succeeded.respond / leave; conversations.scene.work.scribe.followup / leave; conversations.scene.work.scribe.inherited_error.blocked.respond / leave; conversations.scene.work.scribe.inherited_error.succeeded.respond / leave; conversations.scene.work.scribe.text_she_doubts.active.respond / leave; conversations.scene.work.scribe.text_she_doubts.succeeded.respond / leave; conversations.topic.work.scribe.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.scribe.failing_eyes.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.scribe.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.scribe.risk` — e.g. "An account I copy wrong becomes the true one in ninety years. That's not dramatic; that's just how it goes."


```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.scribe.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.scribe.risk.respond   [30 chars]
    en  That's the weight of the desk.
    >>  ............................................
    pt  É o peso da escrivaninha.
    >>  ............................................
```


### Button `ask_burned` — "Who would burn it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.scribe.risk` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.scribe.risk.ask_burned` — accepted phrasings: "who would burn it"
  - the message must contain one of: `burn`, `destroy`
  - scored words: `burn`(1.5), `who`(0.5), `destroy`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.risk.respond.ask_burned
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.risk.respond.ask_burned   [18 chars]
    en  Who would burn it?
    >>  ............................................
    pt  Quem queimaria?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.scribe.risk.ask_burned`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.scribe.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the oldest thing you've saved?" | "Steady hand."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.risk.ask_burned
WHO    VILLAGER — what the player reads after pressing "Who would burn it?"
       spoken on: conversations.topic.work.scribe.risk.respond, button `ask_burned`
       leaves the player on: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.risk.ask_burned`: the villager explains. Subject `work.scribe.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.scribe.risk.ask_burned/1   [111 chars]
    en  Anyone certain of something. That's a larger group than you'd think and it changes membership every generation.
    >>  ............................................
    pt  Qualquer um certo de algo. É um grupo maior do que se imagina e muda de membros a cada geração.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.risk.ask_burned/2   [83 chars]
    en  The same sort who removed the name from the second version, %1$s. They're not gone.
    >>  ............................................
    pt  O mesmo tipo que removeu o nome da segunda versão, %1$s. Eles não sumiram.
    >>  ............................................
```


### Button `sympathise` — "A thatched roof over that is a thing worth solving."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.scribe.risk` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.scribe.risk.sympathise` — accepted phrasings: "a thatched roof over that is a thing worth solving"
  - the message must contain one of: `thatched`, `roof`, `solving`
  - scored words: `thatched`(1.5), `roof`(1.2), `solving`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.risk.respond.sympathise   [51 chars]
    en  A thatched roof over that is a thing worth solving.
    >>  ............................................
    pt  Um telhado de palha sobre isso é algo que vale resolver.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.scribe.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.scribe.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.scribe.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the oldest thing you've saved?" | "Steady hand."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "A thatched roof over that is a thing worth solving."
       spoken on: conversations.topic.work.scribe.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.risk.sympathise`: the villager accepts. Subject `work.scribe.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.scribe.risk.sympathise/1   [102 chars]
    en  ...It is, and I've known it for six years, and I have spent those six years copying instead of asking.
    >>  ............................................
    pt  ...É, e eu sei há seis anos, e passei esses seis anos copiando em vez de pedir.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.risk.sympathise/2   [112 chars]
    en  The mason would build me a stone room for very little, %1$s. I've never brought myself to explain why I need it.
    >>  ............................................
    pt  O pedreiro me faria um cômodo de pedra por muito pouco, %1$s. Nunca consegui explicar por que eu preciso.
    >>  ............................................
```


### Button `ask_wrong` — "Have you ever copied something wrong?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.scribe.risk` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.scribe.risk.ask_wrong` — accepted phrasings: "have you ever copied something wrong"
  - the message must contain one of: `copied`, `wrong`, `error`
  - scored words: `copied`(1.2), `wrong`(1.5), `error`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.risk.respond.ask_wrong
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.risk.respond.ask_wrong   [37 chars]
    en  Have you ever copied something wrong?
    >>  ............................................
    pt  Você já copiou algo errado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.scribe.risk.ask_wrong`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.scribe.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the oldest thing you've saved?" | "Steady hand."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.risk.ask_wrong
WHO    VILLAGER — what the player reads after pressing "Have you ever copied something wrong?"
       spoken on: conversations.topic.work.scribe.risk.respond, button `ask_wrong`
       leaves the player on: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.risk.ask_wrong`: the villager explains. Subject `work.scribe.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.scribe.risk.ask_wrong/1   [103 chars]
    en  Once, a number, and it was in circulation for four years before somebody's grandmother contradicted it.
    >>  ............................................
    pt  Uma vez, um número, e circulou por quatro anos até a avó de alguém contradizer.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.risk.ask_wrong/2   [85 chars]
    en  That I know of, once. That I don't know of is a question I decline to sit with, %1$s.
    >>  ............................................
    pt  Que eu saiba, uma vez. O que eu não sei é uma pergunta com a qual eu me recuso a sentar, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the page."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.scribe.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.risk.respond.leave   [34 chars]
    en  I'll let you get back to the page.
    >>  ............................................
    pt  Vou deixar você voltar pra página.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the page."
       spoken on: conversations.topic.work.scribe.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.left`: the villager accepts. Subject `work.scribe.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.scribe.failing_eyes.active.respond / leave; conversations.scene.work.scribe.failing_eyes.succeeded.respond / leave; conversations.scene.work.scribe.followup / leave; conversations.scene.work.scribe.inherited_error.blocked.respond / leave; conversations.scene.work.scribe.inherited_error.succeeded.respond / leave; conversations.scene.work.scribe.text_she_doubts.active.respond / leave; conversations.scene.work.scribe.text_she_doubts.succeeded.respond / leave; conversations.topic.work.scribe.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.scribe.failing_eyes.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.scribe.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.scribe.task` — e.g. "Copying an account of something nobody here believes happened. I copy it exactly and I add nothing."


```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.scribe.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.scribe.task.respond   [22 chars]
    en  That's the desk today.
    >>  ............................................
    pt  É a escrivaninha hoje.
    >>  ............................................
```


### Button `ask_believe` — "Do you believe it happened?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.scribe.task` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.scribe.task.ask_believe` — accepted phrasings: "do you believe it happened"
  - the message must contain one of: `believe`, `happened`, `true`
  - scored words: `believe`(1.5), `happened`(1.0), `true`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.task.respond.ask_believe
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.task.respond.ask_believe   [27 chars]
    en  Do you believe it happened?
    >>  ............................................
    pt  Você acredita que aconteceu?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.scribe.task.ask_believe`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.scribe.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the oldest thing you've saved?" | "Steady hand."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.task.ask_believe
WHO    VILLAGER — what the player reads after pressing "Do you believe it happened?"
       spoken on: conversations.topic.work.scribe.task.respond, button `ask_believe`
       leaves the player on: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.task.ask_believe`: the villager explains. Subject `work.scribe.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.scribe.task.ask_believe/1   [108 chars]
    en  My belief isn't in the copy. That's the discipline and it's the only thing that makes the copy worth having.
    >>  ............................................
    pt  Minha crença não entra na cópia. É a disciplina e é a única coisa que faz a cópia valer.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.task.ask_believe/2   [98 chars]
    en  Some of it. And I've been very careful never to mark which parts, %1$s, including in my own notes.
    >>  ............................................
    pt  Parte. E eu tomo muito cuidado pra nunca marcar quais partes, %1$s, nem nas minhas notas.
    >>  ............................................
```


### Button `offer_hands` — "I could read one version aloud while you check the other."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.scribe.task` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.scribe.task.offer_hands` — accepted phrasings: "i could read one version aloud while you check the other"
  - the message must contain one of: `aloud`, `read`, `versions`
  - scored words: `aloud`(1.5), `read`(1.0), `versions`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.task.respond.offer_hands   [57 chars]
    en  I could read one version aloud while you check the other.
    >>  ............................................
    pt  Eu podia ler uma versão em voz alta enquanto você confere a outra.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.scribe.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.scribe.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.scribe.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the oldest thing you've saved?" | "Steady hand."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I could read one version aloud while you check the other."
       spoken on: conversations.topic.work.scribe.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.task.offer_hands`: the villager accepts. Subject `work.scribe.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.scribe.task.offer_hands/1   [95 chars]
    en  ...That would halve two days. Read it flat. Don't perform it — performance changes what I hear.
    >>  ............................................
    pt  ...Isso cortaria dois dias pela metade. Leia sem emoção. Não interprete — interpretar muda o que eu ouço.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.task.offer_hands/2   [90 chars]
    en  Slowly, and say the punctuation, %1$s. The punctuation is where two versions usually part.
    >>  ............................................
    pt  Devagar, e diga a pontuação, %1$s. A pontuação é onde duas versões costumam se separar.
    >>  ............................................
```


### Button `ask_who` — "Why would they disagree about who was there?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.scribe.task` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.scribe.task.ask_who` — accepted phrasings: "why would they disagree about who was there"
  - the message must contain one of: `disagree`, `versions`
  - scored words: `disagree`(1.5), `who`(0.5), `versions`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.task.respond.ask_who
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.task.respond.ask_who   [44 chars]
    en  Why would they disagree about who was there?
    >>  ............................................
    pt  Por que discordariam sobre quem estava lá?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.scribe.task.ask_who`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.scribe.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the oldest thing you've saved?" | "Steady hand."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.task.ask_who
WHO    VILLAGER — what the player reads after pressing "Why would they disagree about who was there?"
       spoken on: conversations.topic.work.scribe.task.respond, button `ask_who`
       leaves the player on: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.task.ask_who`: the villager explains. Subject `work.scribe.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.scribe.task.ask_who/1   [105 chars]
    en  Because one of the two was written by somebody who wanted to have been there. That's the commonest fault.
    >>  ............................................
    pt  Porque uma das duas foi escrita por alguém que queria ter estado lá. É a falha mais comum.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.task.ask_who/2   [80 chars]
    en  Because a name was removed, %1$s, and I can see where, and I cannot see by whom.
    >>  ............................................
    pt  Porque um nome foi removido, %1$s, e eu vejo onde, e não vejo por quem.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the page."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.scribe.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.task.respond.leave   [34 chars]
    en  I'll let you get back to the page.
    >>  ............................................
    pt  Vou deixar você voltar pra página.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the page."
       spoken on: conversations.topic.work.scribe.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.left`: the villager accepts. Subject `work.scribe.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.scribe.failing_eyes.active.respond / leave; conversations.scene.work.scribe.failing_eyes.succeeded.respond / leave; conversations.scene.work.scribe.followup / leave; conversations.scene.work.scribe.inherited_error.blocked.respond / leave; conversations.scene.work.scribe.inherited_error.succeeded.respond / leave; conversations.scene.work.scribe.text_she_doubts.active.respond / leave; conversations.scene.work.scribe.text_she_doubts.succeeded.respond / leave; conversations.topic.work.scribe.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.scribe.failing_eyes.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.scribe.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.scribe.village` — e.g. "Nothing I do is any use to this place this year. In two hundred years it may be the only use anything was."


```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.scribe.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.scribe.village.respond   [29 chars]
    en  That's my use, such as it is.
    >>  ............................................
    pt  É a minha serventia, tal como é.
    >>  ............................................
```


### Button `ask_two_hundred` — "Two hundred years is a long time to be patient."

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.scribe.village` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.scribe.village.ask_two_hundred` — accepted phrasings: "two hundred years is a long time to be patient"
  - the message must contain one of: `hundred`, `patient`
  - scored words: `hundred`(1.5), `patient`(1.2), `years`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.village.respond.ask_two_hundred
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.village.respond.ask_two_hundred   [47 chars]
    en  Two hundred years is a long time to be patient.
    >>  ............................................
    pt  Duzentos anos é muito pra ter paciência.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.scribe.village.ask_two_hundred`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.scribe.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the oldest thing you've saved?" | "Steady hand."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.village.ask_two_hundred
WHO    VILLAGER — what the player reads after pressing "Two hundred years is a long time to be patient."
       spoken on: conversations.topic.work.scribe.village.respond, button `ask_two_hundred`
       leaves the player on: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.village.ask_two_hundred`: the villager explains. Subject `work.scribe.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.scribe.village.ask_two_hundred/1   [94 chars]
    en  It's not patience. Nobody's asking me to wait; they're just not asking me for anything at all.
    >>  ............................................
    pt  Não é paciência. Ninguém me pede pra esperar; só não me pedem nada.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.village.ask_two_hundred/2   [92 chars]
    en  It's the only scale the trade works at, %1$s, and it makes me very poor company at a supper.
    >>  ............................................
    pt  É a única escala em que o ofício funciona, %1$s, e me faz péssima companhia num jantar.
    >>  ............................................
```


### Button `say_thanks` — "Half the memory of a place is not nothing."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.scribe.village` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.scribe.village.say_thanks` — accepted phrasings: "half the memory of a place is not nothing"
  - the message must contain one of: `memory`, `half`
  - scored words: `memory`(1.5), `half`(1.0), `nothing`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.village.respond.say_thanks   [42 chars]
    en  Half the memory of a place is not nothing.
    >>  ............................................
    pt  Metade da memória de um lugar não é nada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.scribe.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.scribe.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.scribe.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the oldest thing you've saved?" | "Steady hand."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Half the memory of a place is not nothing."
       spoken on: conversations.topic.work.scribe.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.village.say_thanks`: the villager accepts. Subject `work.scribe.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.scribe.village.say_thanks/1   [84 chars]
    en  ...Half. He'd say I was the smaller half and he'd be teasing and he'd also be right.
    >>  ............................................
    pt  ...Metade. Ele diria que eu sou a metade menor e estaria brincando e também estaria certo.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.village.say_thanks/2   [93 chars]
    en  It's the half nobody misses until it's gone, %1$s, and then it's the only half anybody wants.
    >>  ............................................
    pt  É a metade de que ninguém sente falta até sumir, %1$s, e aí é a única que todos querem.
    >>  ............................................
```


### Button `ask_librarian` — "Do you and the librarian ever compare?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.scribe.village` · offered only once the villager has actually said `work:scribe`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.scribe.village.ask_librarian` — accepted phrasings: "do you and the librarian ever compare"
  - the message must contain one of: `librarian`, `compare`, `together`
  - scored words: `librarian`(1.5), `compare`(1.2), `together`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.village.respond.ask_librarian
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.village.respond.ask_librarian   [38 chars]
    en  Do you and the librarian ever compare?
    >>  ............................................
    pt  Você e o bibliotecário comparam?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.scribe.village.ask_librarian`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.scribe.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the oldest thing you've saved?" | "Steady hand."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.village.ask_librarian
WHO    VILLAGER — what the player reads after pressing "Do you and the librarian ever compare?"
       spoken on: conversations.topic.work.scribe.village.respond, button `ask_librarian`
       leaves the player on: conversations.topic.work.scribe.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.village.ask_librarian`: the villager explains. Subject `work.scribe.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:scribe` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.scribe.village.ask_librarian/1   [85 chars]
    en  Every Thursday, for two hours, and we've disagreed about eleven things in nine years.
    >>  ............................................
    pt  Toda quinta, por duas horas, e discordamos sobre onze coisas em nove anos.
    >>  ............................................
  dialogue.conversations.work.prof.scribe.village.ask_librarian/2   [94 chars]
    en  Constantly, and he's right more often than I am, %1$s, which I have never said in his hearing.
    >>  ............................................
    pt  O tempo todo, e ele acerta mais que eu, %1$s, o que eu nunca disse com ele ouvindo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the page."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.scribe.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.scribe.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.scribe.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.scribe.village.respond.leave   [34 chars]
    en  I'll let you get back to the page.
    >>  ............................................
    pt  Vou deixar você voltar pra página.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.scribe.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the page."
       spoken on: conversations.topic.work.scribe.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.scribe.left`: the villager accepts. Subject `work.scribe.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.scribe.failing_eyes.active.respond / leave; conversations.scene.work.scribe.failing_eyes.succeeded.respond / leave; conversations.scene.work.scribe.followup / leave; conversations.scene.work.scribe.inherited_error.blocked.respond / leave; conversations.scene.work.scribe.inherited_error.succeeded.respond / leave; conversations.scene.work.scribe.text_she_doubts.active.respond / leave; conversations.scene.work.scribe.text_she_doubts.succeeded.respond / leave; conversations.topic.work.scribe.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.scribe.failing_eyes.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

