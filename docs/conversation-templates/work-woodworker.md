# Work talk with a woodworker

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.woodworker.bad_joint.active.respond`](#conversations-scene-work-woodworker-bad-joint-active-respond)
- [`conversations.scene.work.woodworker.bad_joint.succeeded.respond`](#conversations-scene-work-woodworker-bad-joint-succeeded-respond)
- [`conversations.scene.work.woodworker.followup`](#conversations-scene-work-woodworker-followup)
- [`conversations.scene.work.woodworker.teachers_chair.succeeded.respond`](#conversations-scene-work-woodworker-teachers-chair-succeeded-respond)
- [`conversations.scene.work.woodworker.warped_piece.blocked.respond`](#conversations-scene-work-woodworker-warped-piece-blocked-respond)
- [`conversations.scene.work.woodworker.warped_piece.succeeded.respond`](#conversations-scene-work-woodworker-warped-piece-succeeded-respond)
- [`conversations.topic.work.woodworker.craft.respond`](#conversations-topic-work-woodworker-craft-respond)
- [`conversations.topic.work.woodworker.followup`](#conversations-topic-work-woodworker-followup)
- [`conversations.topic.work.woodworker.future.respond`](#conversations-topic-work-woodworker-future-respond)
- [`conversations.topic.work.woodworker.respond`](#conversations-topic-work-woodworker-respond)
- [`conversations.topic.work.woodworker.risk.respond`](#conversations-topic-work-woodworker-risk-respond)
- [`conversations.topic.work.woodworker.task.respond`](#conversations-topic-work-woodworker-task-respond)
- [`conversations.topic.work.woodworker.village.respond`](#conversations-topic-work-woodworker-village-respond)

---

## `conversations.scene.work.woodworker.bad_joint.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.woodworker.bad_joint.active` — e.g. "The customer wants %2$s because it is quicker and cheaper, and they are right about both and wrong about everything after that."


```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.bad_joint.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.woodworker.bad_joint.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.woodworker.bad_joint.active.respond   [15 chars]
    en  The commission.
    >>  ............................................
    pt  A encomenda.
    >>  ............................................
```


### Button `ask_how_to_persuade` — "How would you persuade them?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.woodworker.bad_joint.active` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.woodworker.bad_joint.active.ask_how_to_persuade` — accepted phrasings: "how would you persuade them"; "how would you persuade them"; "what would convince the customer"
  - the message must contain one of: `persuade`, `convince`
  - scored words: `persuade`(1.8), `convince`(1.8), `customer`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.bad_joint.active.respond.ask_how_to_persuade
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.woodworker.bad_joint.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.woodworker.bad_joint.active.respond.ask_how_to_persuade   [28 chars]
    en  How would you persuade them?
    >>  ............................................
    pt  Como você os convenceria?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.woodworker.joints`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.woodworker.bad_joint"}
- Then opens: `conversations.scene.work.woodworker.followup`
- …where the player's next choices will be: "What's the hardest part of a board that warps?" | "I'll leave you to the timber."

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.bad_joint.active.explained
WHO    VILLAGER — what the player reads after pressing "How would you persuade them?"
       spoken on: conversations.scene.work.woodworker.bad_joint.active.respond, button `ask_how_to_persuade`
       leaves the player on: conversations.scene.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.bad_joint.active.explained`: the villager explains. Subject `work.woodworker.joints`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.woodworker.bad_joint.active.explained/1   [107 chars]
    en  Show them the wall. It is the only argument I have that does not require them to take my word for anything.
    >>  ............................................
    pt  Mostrar a parede. É o único argumento que eu tenho que não exige que acreditem na minha palavra.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.bad_joint.active.explained/2   [146 chars]
    en  Price it over ten years instead of over one. Said that way it stops being about craftsmanship and becomes about money, which everybody can follow.
    >>  ............................................
    pt  Cotar por dez anos em vez de por um. Dito assim, deixa de ser sobre ofício e vira sobre dinheiro, que todo mundo acompanha.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.bad_joint.active.explained/3   [120 chars]
    en  I would not, honestly. I would offer both, price both, and let them choose, and then make whichever they chose properly.
    >>  ............................................
    pt  Eu não convenceria, sinceramente. Ofereceria os dois, cotaria os dois, deixaria escolher, e faria direito o que escolhessem.
    >>  ............................................
```


### Button `back_offering_both` — "Offer both and price them honestly."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.woodworker.bad_joint.active` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.woodworker.bad_joint.active.back_offering_both` — accepted phrasings: "offer both and price them honestly"; "offer both and price them honestly"; "let them choose with the real prices"
  - the message must contain one of: `both`, `price`, `choose`
  - scored words: `both`(1.8), `price`(1.8), `choose`(1.8), `offer`(0.8), `honestly`(0.8), `let`(0.8), `real`(0.8), `prices`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.bad_joint.active.respond.back_offering_both
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.woodworker.bad_joint.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.woodworker.bad_joint.active.respond.back_offering_both   [35 chars]
    en  Offer both and price them honestly.
    >>  ............................................
    pt  Ofereça as duas e cote com honestidade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, trust +1  _(recorded under topic `work.woodworker.joints`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.woodworker.bad_joint"}
- Then opens: `conversations.scene.work.woodworker.followup`
- …where the player's next choices will be: "What's the hardest part of a board that warps?" | "I'll leave you to the timber."

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.bad_joint.active.accepted
WHO    VILLAGER — what the player reads after pressing "Offer both and price them honestly."
       spoken on: conversations.scene.work.woodworker.bad_joint.active.respond, button `back_offering_both`
       leaves the player on: conversations.scene.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.bad_joint.active.accepted`: the villager accepts. Subject `work.woodworker.joints`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.woodworker.bad_joint.active.accepted/1   [134 chars]
    en  Both, with the years written next to the prices. Then it is their decision with their information, which is the only fair arrangement.
    >>  ............................................
    pt  As duas, com os anos escritos ao lado dos preços. Aí é decisão deles com informação deles, que é o único arranjo justo.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.bad_joint.active.accepted/2   [134 chars]
    en  Yes. And if they take the cheap one I will make it as well as a cheap joint can be made, and I will not sulk about it in the workshop.
    >>  ............................................
    pt  Sim. E se levarem a barata, eu vou fazer tão bem quanto uma junta barata pode ser feita, e não vou ficar emburrada na oficina.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.bad_joint.active.accepted/3   [122 chars]
    en  That is what my teacher did and I had forgotten. She never argued with anybody. She just put two prices on the same board.
    >>  ............................................
    pt  Foi o que minha mestra fazia e eu tinha esquecido. Ela nunca discutia com ninguém. Só punha dois preços na mesma tábua.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the bench."

*stance family `exit` · tone `plain` · answers the beat(s) `work.woodworker.bad_joint.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.bad_joint.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.woodworker.bad_joint.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.woodworker.bad_joint.active.respond.leave   [35 chars]
    en  I'll let you get back to the bench.
    >>  ............................................
    pt  Vou deixar você voltar à bancada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the bench."
       spoken on: conversations.scene.work.woodworker.bad_joint.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.left`: the villager accepts. Subject `work.woodworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.woodworker.bad_joint.succeeded.respond / leave; conversations.scene.work.woodworker.followup / leave; conversations.scene.work.woodworker.teachers_chair.succeeded.respond / leave; conversations.scene.work.woodworker.warped_piece.blocked.respond / leave; conversations.scene.work.woodworker.warped_piece.succeeded.respond / leave; conversations.topic.work.woodworker.craft.respond / leave; conversations.topic.work.woodworker.followup / leave; conversations.topic.work.woodworker.future.respond / leave …and 4 more
```

```text
  dialogue.conversations.work.prof.woodworker.leave/1   [38 chars]
    en  The glue waits for nobody. Off you go.
    >>  ............................................
    pt  A cola não espera ninguém. Pode ir.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.leave/2   [42 chars]
    en  Mind the shavings, %1$s, they're slippery.
    >>  ............................................
    pt  Cuidado com as aparas, %1$s, escorregam.
    >>  ............................................
```

---


## `conversations.scene.work.woodworker.bad_joint.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.woodworker.bad_joint.succeeded` — e.g. "They took the expensive one. I put the years next to the prices and they read it and changed their minds in about four seconds."


```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.bad_joint.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.woodworker.bad_joint.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.woodworker.bad_joint.succeeded.respond   [16 chars]
    en  That commission.
    >>  ............................................
    pt  Aquela encomenda.
    >>  ............................................
```


### Button `note_the_chalk` — "Two prices did more than arguing."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.woodworker.bad_joint.succeeded` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.woodworker.bad_joint.succeeded.note_the_chalk` — accepted phrasings: "two prices did more than arguing"; "two prices did more than arguing"; "the chalk worked better than argument"
  - the message must contain one of: `prices`, `chalk`, `arguing`
  - scored words: `prices`(1.8), `chalk`(1.8), `arguing`(1.8), `two`(0.8), `more`(0.8), `than`(0.8), `worked`(0.8), `better`(0.8), `argument`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.bad_joint.succeeded.respond.note_the_chalk
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.woodworker.bad_joint.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.woodworker.bad_joint.succeeded.respond.note_the_chalk   [33 chars]
    en  Two prices did more than arguing.
    >>  ............................................
    pt  Dois preços fizeram mais que discutir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2, respect +3  _(recorded under topic `work.woodworker.joints`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.woodworker.bad_joint"}
- Then opens: `conversations.scene.work.woodworker.followup`
- …where the player's next choices will be: "What's the hardest part of a board that warps?" | "I'll leave you to the timber."

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.bad_joint.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Two prices did more than arguing."
       spoken on: conversations.scene.work.woodworker.bad_joint.succeeded.respond, button `note_the_chalk`
       leaves the player on: conversations.scene.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.bad_joint.succeeded.acknowledged`: the villager accepts. Subject `work.woodworker.joints`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.woodworker.bad_joint.succeeded.acknowledged/1   [113 chars]
    en  Arguing puts a person on the other side of the bench. Two prices puts them next to you looking at the same board.
    >>  ............................................
    pt  Discutir põe a pessoa do outro lado da bancada. Dois preços põem ela ao seu lado olhando a mesma tábua.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.bad_joint.succeeded.acknowledged/2   [141 chars]
    en  Thank you. I had been treating it as a question of whether they respected the craft, and it was a question about their money and their years.
    >>  ............................................
    pt  Obrigada. Eu vinha tratando como questão de respeitarem o ofício, e era questão do dinheiro deles e dos anos deles.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.bad_joint.succeeded.acknowledged/3   [112 chars]
    en  It also means I have to be honest about the cheap joint being genuinely cheaper, which I was not, quite, before.
    >>  ............................................
    pt  Também significa que eu preciso ser honesta sobre a junta barata ser genuinamente mais barata, coisa que eu não era, bem, antes.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the bench."

*stance family `exit` · tone `plain` · answers the beat(s) `work.woodworker.bad_joint.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.bad_joint.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.woodworker.bad_joint.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.woodworker.bad_joint.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the bench.
    >>  ............................................
    pt  Vou deixar você voltar à bancada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the bench."
       spoken on: conversations.scene.work.woodworker.bad_joint.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.left`: the villager accepts. Subject `work.woodworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.woodworker.bad_joint.active.respond / leave; conversations.scene.work.woodworker.followup / leave; conversations.scene.work.woodworker.teachers_chair.succeeded.respond / leave; conversations.scene.work.woodworker.warped_piece.blocked.respond / leave; conversations.scene.work.woodworker.warped_piece.succeeded.respond / leave; conversations.topic.work.woodworker.craft.respond / leave; conversations.topic.work.woodworker.followup / leave; conversations.topic.work.woodworker.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.woodworker.bad_joint.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.woodworker.followup`

**Reached from 9 route(s):** `conversations.scene.work.woodworker.bad_joint.active.respond` / `ask_how_to_persuade`; `conversations.scene.work.woodworker.bad_joint.active.respond` / `back_offering_both`; `conversations.scene.work.woodworker.bad_joint.succeeded.respond` / `note_the_chalk`; `conversations.scene.work.woodworker.teachers_chair.succeeded.respond` / `ask_about_the_teacher`; `conversations.scene.work.woodworker.teachers_chair.succeeded.respond` / `note_the_forty_years`; `conversations.scene.work.woodworker.warped_piece.blocked.respond` / `ask_if_it_matters`; `conversations.scene.work.woodworker.warped_piece.blocked.respond` / `offer_planks`; `conversations.scene.work.woodworker.warped_piece.blocked.respond` / `advise_telling_them`; `conversations.scene.work.woodworker.warped_piece.succeeded.respond` / `ask_about_the_wall`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.woodworker.bad_joint.active.accepted` — e.g. "Both, with the years written next to the prices. Then it is their decision with their information, which is the only fair arrangement."
- `conversations.scene.work.woodworker.bad_joint.active.explained` — e.g. "Show them the wall. It is the only argument I have that does not require them to take my word for anything."
- `conversations.scene.work.woodworker.bad_joint.succeeded.acknowledged` — e.g. "Arguing puts a person on the other side of the bench. Two prices puts them next to you looking at the same board."
- `conversations.scene.work.woodworker.teachers_chair.succeeded.acknowledged` — e.g. "It is, and she would have said forty was disappointing, and she would have been half serious."
- `conversations.scene.work.woodworker.teachers_chair.succeeded.answered` — e.g. "Silent, mostly. She would take the plane out of my hand, do four strokes, hand it back, and walk off. It took a year to work out that this was teaching."
- `conversations.scene.work.woodworker.warped_piece.blocked.accepted` — e.g. "Then I remake %2$s and take the old one back, and nobody in that house has to be told anything at all."
- `conversations.scene.work.woodworker.warped_piece.blocked.explained` — e.g. "Not this year. In four years %2$s will rock on the floor and they will think it was always like that, and it was not."
- `conversations.scene.work.woodworker.warped_piece.blocked.resolved` — e.g. "They have no idea anything is wrong. Telling them is entirely my doing and it is the only version I can live with."
- `conversations.scene.work.woodworker.warped_piece.succeeded.explained` — e.g. "Because a lesson you cannot see goes soft in about two years, and a warped board on a wall stays exactly as warped as it was."


```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.woodworker.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.woodworker.followup   [22 chars]
    en  Anything more from me?
    >>  ............................................
    pt  Mais alguma coisa de mim?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of a board that warps?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.woodworker.*` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.woodworker.followup.ask_more` — accepted phrasings: "whats the hardest part of a board that warps"; "what is the hardest part of a board that warps"; "hardest thing about a warping board"
  - the message must contain one of: `hardest`, `board`
  - scored words: `hardest`(1.8), `board`(1.8), `whats`(0.8), `part`(0.8), `warps`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.woodworker.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.woodworker.followup.ask_more   [46 chars]
    en  What's the hardest part of a board that warps?
    >>  ............................................
    pt  Qual é a parte mais difícil de uma tábua que empena?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.woodworker.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.woodworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Can you really tell the tree?" | "Straight grain."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of a board that warps?"
       spoken on: conversations.scene.work.woodworker.followup, button `ask_more`
       leaves the player on: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.hard`: the villager explains. Subject `work.woodworker.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.woodworker.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.woodworker.hard/1   [76 chars]
    en  Anything with a curve. Wood wants to be straight and remembers being a tree.
    >>  ............................................
    pt  Qualquer coisa com curva. Madeira quer ser reta e lembra de ter sido árvore.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.hard/2   [86 chars]
    en  A cradle, %1$s. Not the joinery — the deadline. Those arrive whether I'm ready or not.
    >>  ............................................
    pt  Um berço, %1$s. Não a marcenaria — o prazo. Esses chegam esteja eu pronto ou não.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the timber."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.woodworker.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.woodworker.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.woodworker.followup.leave   [29 chars]
    en  I'll leave you to the timber.
    >>  ............................................
    pt  Vou deixar você com a madeira.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the timber."
       spoken on: conversations.scene.work.woodworker.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.left`: the villager accepts. Subject `work.woodworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.woodworker.bad_joint.active.respond / leave; conversations.scene.work.woodworker.bad_joint.succeeded.respond / leave; conversations.scene.work.woodworker.teachers_chair.succeeded.respond / leave; conversations.scene.work.woodworker.warped_piece.blocked.respond / leave; conversations.scene.work.woodworker.warped_piece.succeeded.respond / leave; conversations.topic.work.woodworker.craft.respond / leave; conversations.topic.work.woodworker.followup / leave; conversations.topic.work.woodworker.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.woodworker.bad_joint.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.woodworker.teachers_chair.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.woodworker.teachers_chair.succeeded` — e.g. "%2$s came in for mending last week. My teacher made it, forty years ago, and I knew her joints before I turned it over."


```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.teachers_chair.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.woodworker.teachers_chair.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.woodworker.teachers_chair.succeeded.respond   [28 chars]
    en  That old piece on the bench.
    >>  ............................................
    pt  Aquela peça velha na bancada.
    >>  ............................................
```


### Button `ask_about_the_teacher` — "What was she like to learn from?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.woodworker.teachers_chair.succeeded` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.woodworker.teachers_chair.succeeded.ask_about_the_teacher` — accepted phrasings: "what was she like to learn from"; "what was she like to learn from"; "what kind of teacher was she"
  - the message must contain one of: `learn`, `teacher`
  - scored words: `learn`(1.8), `teacher`(1.8), `she`(0.8), `like`(0.8), `from`(0.8), `kind`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.teachers_chair.succeeded.respond.ask_about_the_teacher
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.woodworker.teachers_chair.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.woodworker.teachers_chair.succeeded.respond.ask_about_the_teacher   [32 chars]
    en  What was she like to learn from?
    >>  ............................................
    pt  Como era aprender com ela?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, warmth +1  _(recorded under topic `work.woodworker.the_workshop`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.woodworker.teachers_chair"}
- Then opens: `conversations.scene.work.woodworker.followup`
- …where the player's next choices will be: "What's the hardest part of a board that warps?" | "I'll leave you to the timber."

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.teachers_chair.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "What was she like to learn from?"
       spoken on: conversations.scene.work.woodworker.teachers_chair.succeeded.respond, button `ask_about_the_teacher`
       leaves the player on: conversations.scene.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.teachers_chair.succeeded.answered`: the villager explains. Subject `work.woodworker.the_workshop`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.woodworker.teachers_chair.succeeded.answered/1   [152 chars]
    en  Silent, mostly. She would take the plane out of my hand, do four strokes, hand it back, and walk off. It took a year to work out that this was teaching.
    >>  ............................................
    pt  Calada, na maior parte. Tirava a plaina da minha mão, dava quatro passadas, devolvia e ia embora. Levei um ano para entender que aquilo era ensinar.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.teachers_chair.succeeded.answered/2   [102 chars]
    en  Honest about what she did not know, which I did not appreciate at nineteen and think about weekly now.
    >>  ............................................
    pt  Honesta sobre o que não sabia, coisa que eu não valorizei aos dezenove e em que penso toda semana agora.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.teachers_chair.succeeded.answered/3   [140 chars]
    en  She told me she was unsure about that joint, out loud, forty years ago, and then made it anyway because there was no better way at the time.
    >>  ............................................
    pt  Ela me disse que tinha dúvida sobre aquela junta, em voz alta, quarenta anos atrás, e fez assim mesmo porque não havia jeito melhor na época.
    >>  ............................................
```


### Button `note_the_forty_years` — "Forty years is a good run."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.woodworker.teachers_chair.succeeded` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.woodworker.teachers_chair.succeeded.note_the_forty_years` — accepted phrasings: "forty years is a good run"; "forty years is a good run"; "lasting forty years says plenty"
  - the message must contain one of: `forty`, `lasting`
  - scored words: `forty`(1.8), `lasting`(1.8), `years`(0.8), `good`(0.8), `run`(0.8), `says`(0.8), `plenty`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.teachers_chair.succeeded.respond.note_the_forty_years
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.woodworker.teachers_chair.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.woodworker.teachers_chair.succeeded.respond.note_the_forty_years   [26 chars]
    en  Forty years is a good run.
    >>  ............................................
    pt  Quarenta anos é uma boa marca.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +2  _(recorded under topic `work.woodworker.the_workshop`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.woodworker.teachers_chair"}
- Then opens: `conversations.scene.work.woodworker.followup`
- …where the player's next choices will be: "What's the hardest part of a board that warps?" | "I'll leave you to the timber."

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.teachers_chair.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Forty years is a good run."
       spoken on: conversations.scene.work.woodworker.teachers_chair.succeeded.respond, button `note_the_forty_years`
       leaves the player on: conversations.scene.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.teachers_chair.succeeded.acknowledged`: the villager accepts. Subject `work.woodworker.the_workshop`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.woodworker.teachers_chair.succeeded.acknowledged/1   [93 chars]
    en  It is, and she would have said forty was disappointing, and she would have been half serious.
    >>  ............................................
    pt  É, e ela teria dito que quarenta era decepcionante, e estaria meio falando sério.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.teachers_chair.succeeded.acknowledged/2   [144 chars]
    en  I am going to mend it the way she would have if she had known what I know. That is either respect or arrogance and I have decided it is respect.
    >>  ............................................
    pt  Vou consertar do jeito que ela faria se soubesse o que eu sei. Isso é respeito ou arrogância, e eu decidi que é respeito.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.teachers_chair.succeeded.acknowledged/3   [118 chars]
    en  Thank you. The family have no idea any of this. They want a chair that works by Friday, and they are going to get one.
    >>  ............................................
    pt  Obrigada. A família não faz ideia de nada disso. Querem uma cadeira que funcione até sexta, e vão ter.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the bench."

*stance family `exit` · tone `plain` · answers the beat(s) `work.woodworker.teachers_chair.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.teachers_chair.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.woodworker.teachers_chair.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.woodworker.teachers_chair.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the bench.
    >>  ............................................
    pt  Vou deixar você voltar à bancada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the bench."
       spoken on: conversations.scene.work.woodworker.teachers_chair.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.left`: the villager accepts. Subject `work.woodworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.woodworker.bad_joint.active.respond / leave; conversations.scene.work.woodworker.bad_joint.succeeded.respond / leave; conversations.scene.work.woodworker.followup / leave; conversations.scene.work.woodworker.warped_piece.blocked.respond / leave; conversations.scene.work.woodworker.warped_piece.succeeded.respond / leave; conversations.topic.work.woodworker.craft.respond / leave; conversations.topic.work.woodworker.followup / leave; conversations.topic.work.woodworker.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.woodworker.bad_joint.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.woodworker.warped_piece.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.woodworker.warped_piece.blocked` — e.g. "%2$s I finished in the autumn has cupped, because of %3$s, and it is sitting in somebody's kitchen looking wrong."


```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.warped_piece.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.woodworker.warped_piece.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.woodworker.warped_piece.blocked.respond   [21 chars]
    en  The piece that moved.
    >>  ............................................
    pt  A peça que se mexeu.
    >>  ............................................
```


### Button `ask_if_it_matters` — "Would anyone else notice?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.woodworker.warped_piece.blocked` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.woodworker.warped_piece.blocked.ask_if_it_matters` — accepted phrasings: "would anyone else notice"; "would anyone else notice that"; "does that much movement matter"
  - the message must contain one of: `notice`, `movement`
  - scored words: `notice`(1.8), `movement`(1.8), `anyone`(0.8), `else`(0.8), `does`(0.8), `much`(0.8), `matter`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.warped_piece.blocked.respond.ask_if_it_matters
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.woodworker.warped_piece.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.woodworker.warped_piece.blocked.respond.ask_if_it_matters   [25 chars]
    en  Would anyone else notice?
    >>  ............................................
    pt  Alguém mais notaria?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.woodworker.seasoning`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.woodworker.warped_piece"}
- Then opens: `conversations.scene.work.woodworker.followup`
- …where the player's next choices will be: "What's the hardest part of a board that warps?" | "I'll leave you to the timber."

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.warped_piece.blocked.explained
WHO    VILLAGER — what the player reads after pressing "Would anyone else notice?"
       spoken on: conversations.scene.work.woodworker.warped_piece.blocked.respond, button `ask_if_it_matters`
       leaves the player on: conversations.scene.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.warped_piece.blocked.explained`: the villager explains. Subject `work.woodworker.seasoning`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.woodworker.warped_piece.blocked.explained/1   [117 chars]
    en  Not this year. In four years %2$s will rock on the floor and they will think it was always like that, and it was not.
    >>  ............................................
    pt  Este ano, não. Em quatro anos %2$s vai balançar no chão e vão achar que sempre foi assim, e não era.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.warped_piece.blocked.explained/2   [145 chars]
    en  They notice without noticing. A door that has moved catches once a day, and after a year that is four hundred small irritations they cannot name.
    >>  ............................................
    pt  Notam sem notar. Uma porta que se moveu prende uma vez por dia, e depois de um ano são quatrocentas pequenas irritações que não sabem nomear.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.warped_piece.blocked.explained/3   [99 chars]
    en  The honest answer is that I would notice, every time I visited that house, for the rest of my life.
    >>  ............................................
    pt  A resposta honesta é que eu notaria, toda vez que visitasse aquela casa, pelo resto da vida.
    >>  ............................................
```


### Button `offer_planks` — "I'll bring you seasoned planks."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.woodworker.warped_piece.blocked` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.woodworker.warped_piece.blocked.offer_planks` — accepted phrasings: "ill bring you seasoned planks"; "i can bring you seasoned planks"; "let me fetch planks for that"
  - the message must contain one of: `planks`, `seasoned`
  - scored words: `planks`(1.8), `seasoned`(1.8), `ill`(0.8), `bring`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.warped_piece.blocked.respond.offer_planks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.woodworker.warped_piece.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.woodworker.warped_piece.blocked.respond.offer_planks   [31 chars]
    en  I'll bring you seasoned planks.
    >>  ............................................
    pt  Vou trazer tábuas curadas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.woodworker.warp.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.woodworker.seasoning`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.warped_piece", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.woodworker.warped_piece", "obligation": "commitment:work.woodworker.bring_planks"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.woodworker.bring_planks"}
- Then opens: `conversations.scene.work.woodworker.followup`
- …where the player's next choices will be: "What's the hardest part of a board that warps?" | "I'll leave you to the timber."

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.warped_piece.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you seasoned planks."
       spoken on: conversations.scene.work.woodworker.warped_piece.blocked.respond, button `offer_planks`
       leaves the player on: conversations.scene.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.warped_piece.blocked.accepted`: the villager accepts. Subject `work.woodworker.seasoning`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.woodworker.warped_piece.blocked.accepted/1   [102 chars]
    en  Then I remake %2$s and take the old one back, and nobody in that house has to be told anything at all.
    >>  ............................................
    pt  Então eu refaço %2$s e levo a antiga de volta, e ninguém naquela casa precisa ser informado de nada.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.warped_piece.blocked.accepted/2   [125 chars]
    en  Bring quarter-sawn if you can see the end grain. It moves half as much and it costs twice as much and it is worth four times.
    >>  ............................................
    pt  Traga serrada em quartos, se você conseguir ver o topo da fibra. Move metade, custa o dobro e vale quatro vezes.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.warped_piece.blocked.accepted/3   [125 chars]
    en  Yes. And I will keep the warped one on the wall of the workshop, where I keep the others, in the order they taught me things.
    >>  ............................................
    pt  Sim. E vou guardar a empenada na parede da oficina, onde ficam as outras, na ordem em que me ensinaram coisas.
    >>  ............................................
```


### Button `advise_telling_them` — "Tell them and offer to remake it."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.woodworker.warped_piece.blocked` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.woodworker.warped_piece.blocked.advise_telling_them` — accepted phrasings: "tell them and offer to remake it"; "tell them and offer to remake it"; "own it with the customer and replace it"
  - the message must contain one of: `remake`, `replace`, `customer`
  - scored words: `remake`(1.8), `replace`(1.8), `customer`(1.8), `tell`(0.8), `offer`(0.8), `own`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.warped_piece.blocked.respond.advise_telling_them
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.woodworker.warped_piece.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.woodworker.warped_piece.blocked.respond.advise_telling_them   [33 chars]
    en  Tell them and offer to remake it.
    >>  ............................................
    pt  Conte e ofereça refazer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4  _(recorded under topic `work.woodworker.seasoning`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.woodworker.warped_piece"}
- Then opens: `conversations.scene.work.woodworker.followup`
- …where the player's next choices will be: "What's the hardest part of a board that warps?" | "I'll leave you to the timber."

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.warped_piece.blocked.resolved
WHO    VILLAGER — what the player reads after pressing "Tell them and offer to remake it."
       spoken on: conversations.scene.work.woodworker.warped_piece.blocked.respond, button `advise_telling_them`
       leaves the player on: conversations.scene.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.warped_piece.blocked.resolved`: the villager accepts. Subject `work.woodworker.seasoning`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.woodworker.warped_piece.blocked.resolved/1   [114 chars]
    en  They have no idea anything is wrong. Telling them is entirely my doing and it is the only version I can live with.
    >>  ............................................
    pt  Eles não fazem ideia de que há algo errado. Contar é inteiramente obra minha e é a única versão em que eu consigo viver.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.warped_piece.blocked.resolved/2   [129 chars]
    en  Yes. And I will say it is the timber rather than my judgement, which is true, and I will not say which of those chose the timber.
    >>  ............................................
    pt  Sim. E vou dizer que é a madeira e não o meu julgamento, o que é verdade, e não vou dizer qual dos dois escolheu a madeira.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.warped_piece.blocked.resolved/3   [133 chars]
    en  It costs me a week and the price of the boards. It buys me being the person who came back, and that is not purchasable any other way.
    >>  ............................................
    pt  Custa-me uma semana e o preço das tábuas. Compra-me ser a pessoa que voltou, e isso não se compra de outro jeito.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the bench."

*stance family `exit` · tone `plain` · answers the beat(s) `work.woodworker.warped_piece.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.warped_piece.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.woodworker.warped_piece.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.woodworker.warped_piece.blocked.respond.leave   [35 chars]
    en  I'll let you get back to the bench.
    >>  ............................................
    pt  Vou deixar você voltar à bancada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the bench."
       spoken on: conversations.scene.work.woodworker.warped_piece.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.left`: the villager accepts. Subject `work.woodworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.woodworker.bad_joint.active.respond / leave; conversations.scene.work.woodworker.bad_joint.succeeded.respond / leave; conversations.scene.work.woodworker.followup / leave; conversations.scene.work.woodworker.teachers_chair.succeeded.respond / leave; conversations.scene.work.woodworker.warped_piece.succeeded.respond / leave; conversations.topic.work.woodworker.craft.respond / leave; conversations.topic.work.woodworker.followup / leave; conversations.topic.work.woodworker.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.woodworker.bad_joint.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.woodworker.warped_piece.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.woodworker.warped_piece.succeeded` — e.g. "%2$s is remade in quarter-sawn oak and it will still be flat when the house has a new roof."


```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.warped_piece.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.woodworker.warped_piece.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.woodworker.warped_piece.succeeded.respond   [11 chars]
    en  That piece.
    >>  ............................................
    pt  Aquela peça.
    >>  ............................................
```


### Button `ask_about_the_wall` — "Why keep the failures on the wall?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.woodworker.warped_piece.succeeded` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.woodworker.warped_piece.succeeded.ask_about_the_wall` — accepted phrasings: "why keep the failures on the wall"; "why keep the failures on the wall"; "what is the wall of failures for"
  - the message must contain one of: `failures`, `wall`
  - scored words: `failures`(1.8), `wall`(1.8), `why`(0.8), `keep`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.warped_piece.succeeded.respond.ask_about_the_wall
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.woodworker.warped_piece.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.woodworker.warped_piece.succeeded.respond.ask_about_the_wall   [34 chars]
    en  Why keep the failures on the wall?
    >>  ............................................
    pt  Por que guardar os fracassos na parede?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.woodworker.seasoning`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.woodworker.warped_piece"}
- Then opens: `conversations.scene.work.woodworker.followup`
- …where the player's next choices will be: "What's the hardest part of a board that warps?" | "I'll leave you to the timber."

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.warped_piece.succeeded.explained
WHO    VILLAGER — what the player reads after pressing "Why keep the failures on the wall?"
       spoken on: conversations.scene.work.woodworker.warped_piece.succeeded.respond, button `ask_about_the_wall`
       leaves the player on: conversations.scene.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.warped_piece.succeeded.explained`: the villager explains. Subject `work.woodworker.seasoning`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.woodworker.warped_piece.succeeded.explained/1   [125 chars]
    en  Because a lesson you cannot see goes soft in about two years, and a warped board on a wall stays exactly as warped as it was.
    >>  ............................................
    pt  Porque uma lição que você não vê amolece em uns dois anos, e uma tábua empenada numa parede fica exatamente tão empenada quanto era.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.warped_piece.succeeded.explained/2   [143 chars]
    en  My teacher had one. I thought it was morbid at nineteen and I understood it at thirty-one, and now I show it to apprentices on their first day.
    >>  ............................................
    pt  Minha mestra tinha uma. Aos dezenove eu achava mórbido e aos trinta e um eu entendi, e agora eu mostro aos aprendizes no primeiro dia.
    >>  ............................................
  dialogue.conversations.scene.work.woodworker.warped_piece.succeeded.explained/3   [142 chars]
    en  Everything on that wall was good work with one wrong decision in it. That is the useful kind of failure and it is the only kind worth keeping.
    >>  ............................................
    pt  Tudo naquela parede era bom trabalho com uma decisão errada dentro. É o tipo útil de fracasso e é o único que vale guardar.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the bench."

*stance family `exit` · tone `plain` · answers the beat(s) `work.woodworker.warped_piece.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.woodworker.warped_piece.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.woodworker.warped_piece.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.woodworker.warped_piece.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the bench.
    >>  ............................................
    pt  Vou deixar você voltar à bancada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the bench."
       spoken on: conversations.scene.work.woodworker.warped_piece.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.left`: the villager accepts. Subject `work.woodworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.woodworker.bad_joint.active.respond / leave; conversations.scene.work.woodworker.bad_joint.succeeded.respond / leave; conversations.scene.work.woodworker.followup / leave; conversations.scene.work.woodworker.teachers_chair.succeeded.respond / leave; conversations.scene.work.woodworker.warped_piece.blocked.respond / leave; conversations.topic.work.woodworker.craft.respond / leave; conversations.topic.work.woodworker.followup / leave; conversations.topic.work.woodworker.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.woodworker.bad_joint.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.woodworker.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.woodworker.craft` — e.g. "Wood moves after you've finished with it. The whole trade is guessing which way, years ahead."


```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.woodworker.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.woodworker.craft.respond   [29 chars]
    en  That's what it comes down to.
    >>  ............................................
    pt  É nisso que dá.
    >>  ............................................
```


### Button `ask_moves` — "How do you guess which way?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.woodworker.craft` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.woodworker.craft.ask_moves` — accepted phrasings: "how do you guess which way"
  - the message must contain one of: `guess`, `grain`, `moves`
  - scored words: `guess`(1.2), `grain`(1.5), `moves`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.craft.respond.ask_moves
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.craft.respond.ask_moves   [27 chars]
    en  How do you guess which way?
    >>  ............................................
    pt  Como você adivinha pra que lado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.woodworker.craft.ask_moves`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.woodworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Can you really tell the tree?" | "Straight grain."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.craft.ask_moves
WHO    VILLAGER — what the player reads after pressing "How do you guess which way?"
       spoken on: conversations.topic.work.woodworker.craft.respond, button `ask_moves`
       leaves the player on: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.craft.ask_moves`: the villager explains. Subject `work.woodworker.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.woodworker.craft.ask_moves/1   [88 chars]
    en  Read the end grain and assume it will do the thing you'd least like. It's usually right.
    >>  ............................................
    pt  Leia a fibra da ponta e suponha que vai fazer o que você menos quer. Costuma acertar.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.craft.ask_moves/2   [84 chars]
    en  You put the heart side where it can't pull the joint apart, %1$s. That's most of it.
    >>  ............................................
    pt  Você põe o lado do cerne onde ele não abre o encaixe, %1$s. É quase tudo.
    >>  ............................................
```


### Button `admire` — "Guessing years ahead is a strange thing to be good at."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.woodworker.craft` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.woodworker.craft.admire` — accepted phrasings: "guessing years ahead is a strange thing to be good at"
  - the message must contain one of: `guessing`, `ahead`
  - scored words: `guessing`(1.5), `ahead`(1.2), `years`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.craft.respond.admire   [54 chars]
    en  Guessing years ahead is a strange thing to be good at.
    >>  ............................................
    pt  Adivinhar anos à frente é uma coisa estranha de se ser bom.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.woodworker.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.woodworker.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.woodworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Can you really tell the tree?" | "Straight grain."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.craft.admire
WHO    VILLAGER — what the player reads after pressing "Guessing years ahead is a strange thing to be good at."
       spoken on: conversations.topic.work.woodworker.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.craft.admire`: the villager accepts. Subject `work.woodworker.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.woodworker.craft.admire/1   [74 chars]
    en  It's the whole reason old furniture exists and new furniture doesn't last.
    >>  ............................................
    pt  É a razão inteira de móvel velho existir e móvel novo não durar.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.craft.admire/2   [74 chars]
    en  Nobody notices when I get it right. They only ever see the failures, %1$s.
    >>  ............................................
    pt  Ninguém repara quando eu acerto. Só veem as falhas, %1$s.
    >>  ............................................
```


### Button `ask_knuckles` — "Did the knuckle-rapping teach you anything?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.woodworker.craft` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.woodworker.craft.ask_knuckles` — accepted phrasings: "did the knuckle-rapping teach you anything"
  - the message must contain one of: `knuckles`, `teach`, `master`
  - scored words: `knuckles`(1.5), `teach`(1.0), `master`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.craft.respond.ask_knuckles
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.craft.respond.ask_knuckles   [43 chars]
    en  Did the knuckle-rapping teach you anything?
    >>  ............................................
    pt  As pancadas nos dedos te ensinaram algo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.woodworker.craft.ask_knuckles`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.woodworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Can you really tell the tree?" | "Straight grain."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.craft.ask_knuckles
WHO    VILLAGER — what the player reads after pressing "Did the knuckle-rapping teach you anything?"
       spoken on: conversations.topic.work.woodworker.craft.respond, button `ask_knuckles`
       leaves the player on: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.craft.ask_knuckles`: the villager explains. Subject `work.woodworker.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.woodworker.craft.ask_knuckles/1   [93 chars]
    en  A thin shaving and a lasting resentment. I've kept the first and mostly let go of the second.
    >>  ............................................
    pt  Uma lasca fina e um ressentimento duradouro. Guardei o primeiro e quase larguei o segundo.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.craft.ask_knuckles/2   [98 chars]
    en  It taught me to listen to the plane instead of watching it, %1$s. She was right and she was awful.
    >>  ............................................
    pt  Me ensinou a escutar a plaina em vez de olhar, %1$s. Ela tinha razão e era terrível.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the bench."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.woodworker.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.craft.respond.leave   [35 chars]
    en  I'll let you get back to the bench.
    >>  ............................................
    pt  Vou deixar você voltar pra bancada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the bench."
       spoken on: conversations.topic.work.woodworker.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.left`: the villager accepts. Subject `work.woodworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.woodworker.bad_joint.active.respond / leave; conversations.scene.work.woodworker.bad_joint.succeeded.respond / leave; conversations.scene.work.woodworker.followup / leave; conversations.scene.work.woodworker.teachers_chair.succeeded.respond / leave; conversations.scene.work.woodworker.warped_piece.blocked.respond / leave; conversations.scene.work.woodworker.warped_piece.succeeded.respond / leave; conversations.topic.work.woodworker.followup / leave; conversations.topic.work.woodworker.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.woodworker.bad_joint.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.woodworker.followup`

**Reached from 20 route(s):** `conversations.scene.work.woodworker.followup` / `ask_more`; `conversations.topic.work.woodworker.craft.respond` / `ask_moves`; `conversations.topic.work.woodworker.craft.respond` / `admire`; `conversations.topic.work.woodworker.craft.respond` / `ask_knuckles`; `conversations.topic.work.woodworker.future.respond` / `ask_stair`; `conversations.topic.work.woodworker.future.respond` / `encourage`; `conversations.topic.work.woodworker.future.respond` / `ask_three_doors`; `conversations.topic.work.woodworker.respond` / `ask_hard`; `conversations.topic.work.woodworker.respond` / `value`; `conversations.topic.work.woodworker.respond` / `challenge`; `conversations.topic.work.woodworker.respond` / `challenge`; `conversations.topic.work.woodworker.risk.respond` / `ask_beam` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.woodworker.challenge.landed` — e.g. "It's mostly not cutting them. Choosing which, and leaving the rest, is the trade."
- `conversations.work.prof.woodworker.challenge.stung` — e.g. "...I've a beam upstairs I chose nine years before I cut it."
- `conversations.work.prof.woodworker.craft.admire` — e.g. "It's the whole reason old furniture exists and new furniture doesn't last."
- `conversations.work.prof.woodworker.craft.ask_knuckles` — e.g. "A thin shaving and a lasting resentment. I've kept the first and mostly let go of the second."
- `conversations.work.prof.woodworker.craft.ask_moves` — e.g. "Read the end grain and assume it will do the thing you'd least like. It's usually right."
- `conversations.work.prof.woodworker.future.ask_stair` — e.g. "The mill has a ladder and a woman of sixty climbing it. That's where it would go."
- `conversations.work.prof.woodworker.future.ask_three_doors` — e.g. "No nails. Not one, in any of them. I've taken one apart in my head a hundred times."
- `conversations.work.prof.woodworker.future.encourage` — e.g. "...Call it a commission. That's how you get a thing built in this place, isn't it."
- `conversations.work.prof.woodworker.hard` — e.g. "Anything with a curve. Wood wants to be straight and remembers being a tree."
- `conversations.work.prof.woodworker.risk.ask_beam` — e.g. "Because I'd look up. And once you've looked up you spend the evening doing arithmetic."
- `conversations.work.prof.woodworker.risk.ask_saw` — e.g. "I was finishing a piece at dusk because a customer was waiting. That's the whole story."
- `conversations.work.prof.woodworker.risk.sympathise` — e.g. "...It is, and it was my fault, and being able to say both is what took the longest."
- `conversations.work.prof.woodworker.task.ask_chairs` — e.g. "They twisted over a winter. Two came back and one I saw in a barn, being used as a barn thing."
- `conversations.work.prof.woodworker.task.ask_eleventh` — e.g. "Because by then your hand is tired and your eye has agreed to be satisfied. That's when frames fail."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.woodworker.followup   [31 chars]
    en  That's timber, and the waiting.
    >>  ............................................
    pt  É madeira, e a espera.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.woodworker.challenge.landed`, `work.woodworker.challenge.stung`, `work.woodworker.craft.admire`, `work.woodworker.craft.ask_knuckles`, `work.woodworker.craft.ask_moves`, `work.woodworker.future.ask_stair`, `work.woodworker.future.ask_three_doors`, `work.woodworker.future.encourage`, `work.woodworker.hard`, `work.woodworker.risk.ask_beam`, `work.woodworker.risk.ask_saw`, `work.woodworker.risk.sympathise`, `work.woodworker.task.ask_chairs`, `work.woodworker.task.ask_eleventh`, `work.woodworker.task.offer_hands`, `work.woodworker.value`, `work.woodworker.village.ask_cradle`, `work.woodworker.village.ask_doors`, `work.woodworker.village.say_thanks` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.woodworker.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `beam`
  - scored words: `thought`(1.2), `beam`(1.5), `ruin`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.woodworker.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.woodworker.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.woodworker.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.woodworker.thanks`: the villager accepts. Subject `work.woodworker.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.woodworker.thanks/1   [63 chars]
    en  Nobody looks up at a beam. That's how you know it's a good one.
    >>  ............................................
    pt  Ninguém olha pra cima pra uma viga. É assim que você sabe que ela é boa.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.thanks/2   [85 chars]
    en  Wood takes a lifetime to grow and an afternoon to ruin, %1$s. I try to remember that.
    >>  ............................................
    pt  Madeira leva uma vida pra crescer e uma tarde pra estragar, %1$s. Tento lembrar disso.
    >>  ............................................
```


### Button `ask_more` — "Can you really tell the tree?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.woodworker.challenge.landed`, `work.woodworker.challenge.stung`, `work.woodworker.craft.admire`, `work.woodworker.craft.ask_knuckles`, `work.woodworker.craft.ask_moves`, `work.woodworker.future.ask_stair`, `work.woodworker.future.ask_three_doors`, `work.woodworker.future.encourage`, `work.woodworker.hard`, `work.woodworker.risk.ask_beam`, `work.woodworker.risk.ask_saw`, `work.woodworker.risk.sympathise`, `work.woodworker.task.ask_chairs`, `work.woodworker.task.ask_eleventh`, `work.woodworker.task.offer_hands`, `work.woodworker.value`, `work.woodworker.village.ask_cradle`, `work.woodworker.village.ask_doors`, `work.woodworker.village.say_thanks` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.woodworker.more` — accepted phrasings: "can you really tell the tree"
  - the message must contain one of: `tell`, `tree`, `grain`
  - scored words: `tell`(1.0), `tree`(1.5), `grain`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.followup.ask_more   [29 chars]
    en  Can you really tell the tree?
    >>  ............................................
    pt  Você consegue mesmo dizer qual árvore era?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.woodworker.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.more
WHO    VILLAGER — what the player reads after pressing "Can you really tell the tree?"
       spoken on: conversations.topic.work.woodworker.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.woodworker.more`: the villager discloses. Subject `work.woodworker.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.woodworker.more/1   [92 chars]
    en  Every time. The tavern bar was a spiteful old oak on the north slope. I remember felling it.
    >>  ............................................
    pt  Sempre. O balcão da taverna era um carvalho velho e rancoroso na encosta norte. Lembro de derrubar.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.more/2   [81 chars]
    en  Most of the time. Some pieces keep their secrets, and I respect them more for it.
    >>  ............................................
    pt  Quase sempre. Algumas peças guardam segredo, e eu as respeito mais por isso.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time. I remember felling the tavern oak and I've never been entirely comfortable about it.
    >>  ............................................
    pt  Toda vez. Lembro de derrubar o carvalho da taverna e nunca fiquei totalmente à vontade com isso.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase. Nobody needs one, so wanting it feels like something I have to apologise for.
    >>  ............................................
    pt  Uma escada. Ninguém precisa, então querer parece algo pelo que eu tenho que pedir desculpa.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time. That oak took two years to season and about four hundred to grow.
    >>  ............................................
    pt  Toda vez. Aquele carvalho levou dois anos pra curar e uns quatrocentos pra crescer.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase, one day. Timber waits two years; a staircase can wait a few more.
    >>  ............................................
    pt  Uma escada, um dia. A madeira espera dois anos; uma escada pode esperar mais alguns.
    >>  ............................................
  confident.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time. The tavern bar was a spiteful old oak on the north slope. I remember felling it.
    >>  ............................................
    pt  Toda vez. O balcão da taverna era um carvalho velho e rancoroso da encosta norte. Lembro de derrubar.
    >>  ............................................
  confident.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase. A proper turned one. Nobody here needs one and I want to make one anyway.
    >>  ............................................
    pt  Uma escada. De verdade, torneada. Ninguém aqui precisa e eu quero fazer mesmo assim.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time. The tavern bar was a spiteful old oak on the north slope. I remember felling it.
    >>  ............................................
    pt  Toda vez. O balcão da taverna era um carvalho velho e rancoroso da encosta norte. Lembro de derrubar.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase. A proper turned one. Nobody here needs one and I want to make one anyway.
    >>  ............................................
    pt  Uma escada. De verdade, torneada. Ninguém aqui precisa e eu quero fazer mesmo assim.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time. Ask me about the tavern bar and I'll tell you about the tree and the afternoon it came down.
    >>  ............................................
    pt  Toda vez. Me pergunte do balcão da taverna e eu conto da árvore e da tarde em que caiu.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase for the mill. There's a woman of sixty climbing a ladder in there every day.
    >>  ............................................
    pt  Uma escada pro moinho. Tem uma mulher de sessenta subindo uma escada de mão lá todo dia.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time. Ask me about the tavern bar and I'll tell you about the tree and the afternoon it came down.
    >>  ............................................
    pt  Toda vez. Me pergunte do balcão da taverna e eu conto da árvore e da tarde em que caiu.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase for the mill. There's a woman of sixty climbing a ladder in there every day.
    >>  ............................................
    pt  Uma escada pro moinho. Tem uma mulher de sessenta subindo uma escada de mão lá todo dia.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time. Ask me about the tavern bar and I'll tell you about the tree and the afternoon it came down.
    >>  ............................................
    pt  Toda vez. Me pergunte do balcão da taverna e eu conto da árvore e da tarde em que caiu.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase for the mill. There's a woman of sixty climbing a ladder in there every day.
    >>  ............................................
    pt  Uma escada pro moinho. Tem uma mulher de sessenta subindo uma escada de mão lá todo dia.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time. I remember felling the tavern oak and I've never been entirely comfortable about it.
    >>  ............................................
    pt  Toda vez. Lembro de derrubar o carvalho da taverna e nunca fiquei totalmente à vontade com isso.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase. Nobody needs one, so wanting it feels like something I have to apologise for.
    >>  ............................................
    pt  Uma escada. Ninguém precisa, então querer parece algo pelo que eu tenho que pedir desculpa.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time. The tavern bar was a spiteful old oak on the north slope. I remember felling it.
    >>  ............................................
    pt  Toda vez. O balcão da taverna era um carvalho velho e rancoroso da encosta norte. Lembro de derrubar.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase. A proper turned one. Nobody here needs one and I want to make one anyway.
    >>  ............................................
    pt  Uma escada. De verdade, torneada. Ninguém aqui precisa e eu quero fazer mesmo assim.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time. The tavern bar was a spiteful old oak on the north slope. I remember felling it.
    >>  ............................................
    pt  Toda vez. O balcão da taverna era um carvalho velho e rancoroso da encosta norte. Lembro de derrubar.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase. A proper turned one. Nobody here needs one and I want to make one anyway.
    >>  ............................................
    pt  Uma escada. De verdade, torneada. Ninguém aqui precisa e eu quero fazer mesmo assim.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time. That bar was a north-slope oak, and it fought the whole way down.
    >>  ............................................
    pt  Toda vez. Aquele balcão era um carvalho da encosta norte, e brigou o caminho todo.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase. Turned properly. It's the one thing I've wanted to make and never been paid to.
    >>  ............................................
    pt  Uma escada. Torneada direito. É a única coisa que eu quis fazer e nunca fui pago pra fazer.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time. That oak took two years to season and about four hundred to grow.
    >>  ............................................
    pt  Toda vez. Aquele carvalho levou dois anos pra curar e uns quatrocentos pra crescer.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase, one day. Timber waits two years; a staircase can wait a few more.
    >>  ............................................
    pt  Uma escada, um dia. A madeira espera dois anos; uma escada pode esperar mais alguns.
    >>  ............................................
  odd.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time. That bar was a north-slope oak, and it fought the whole way down.
    >>  ............................................
    pt  Toda vez. Aquele balcão era um carvalho da encosta norte, e brigou o caminho todo.
    >>  ............................................
  odd.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase. Turned properly. It's the one thing I've wanted to make and never been paid to.
    >>  ............................................
    pt  Uma escada. Torneada direito. É a única coisa que eu quis fazer e nunca fui pago pra fazer.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time. That oak took two years to season and about four hundred to grow.
    >>  ............................................
    pt  Toda vez. Aquele carvalho levou dois anos pra curar e uns quatrocentos pra crescer.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase, one day. Timber waits two years; a staircase can wait a few more.
    >>  ............................................
    pt  Uma escada, um dia. A madeira espera dois anos; uma escada pode esperar mais alguns.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time! The tavern bar was a spiteful old oak on the north slope and I have not forgiven it.
    >>  ............................................
    pt  Toda vez! O balcão da taverna era um carvalho velho e rancoroso da encosta norte e eu não perdoei.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase. A proper turned one. Nobody needs it, which is exactly why I want it.
    >>  ............................................
    pt  Uma escada. De verdade, torneada. Ninguém precisa, e é exatamente por isso que eu quero.
    >>  ............................................
  playful.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time! The tavern bar was a spiteful old oak on the north slope and I have not forgiven it.
    >>  ............................................
    pt  Toda vez! O balcão da taverna era um carvalho velho e rancoroso da encosta norte e eu não perdoei.
    >>  ............................................
  playful.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase. A proper turned one. Nobody needs it, which is exactly why I want it.
    >>  ............................................
    pt  Uma escada. De verdade, torneada. Ninguém precisa, e é exatamente por isso que eu quero.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time. That oak took two years to season and about four hundred to grow.
    >>  ............................................
    pt  Toda vez. Aquele carvalho levou dois anos pra curar e uns quatrocentos pra crescer.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase, one day. Timber waits two years; a staircase can wait a few more.
    >>  ............................................
    pt  Uma escada, um dia. A madeira espera dois anos; uma escada pode esperar mais alguns.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time. I remember felling the tavern oak and I've never been entirely comfortable about it.
    >>  ............................................
    pt  Toda vez. Lembro de derrubar o carvalho da taverna e nunca fiquei totalmente à vontade com isso.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase. Nobody needs one, so wanting it feels like something I have to apologise for.
    >>  ............................................
    pt  Uma escada. Ninguém precisa, então querer parece algo pelo que eu tenho que pedir desculpa.
    >>  ............................................
  shy.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time. That bar was a north-slope oak, and it fought the whole way down.
    >>  ............................................
    pt  Toda vez. Aquele balcão era um carvalho da encosta norte, e brigou o caminho todo.
    >>  ............................................
  shy.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase. Turned properly. It's the one thing I've wanted to make and never been paid to.
    >>  ............................................
    pt  Uma escada. Torneada direito. É a única coisa que eu quis fazer e nunca fui pago pra fazer.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time! The tavern bar was a spiteful old oak on the north slope and I have not forgiven it.
    >>  ............................................
    pt  Toda vez! O balcão da taverna era um carvalho velho e rancoroso da encosta norte e eu não perdoei.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase. A proper turned one. Nobody needs it, which is exactly why I want it.
    >>  ............................................
    pt  Uma escada. De verdade, torneada. Ninguém precisa, e é exatamente por isso que eu quero.
    >>  ............................................
  witty.dialogue.conversations.work.prof.woodworker.more/1
    en  Every time! The tavern bar was a spiteful old oak on the north slope and I have not forgiven it.
    >>  ............................................
    pt  Toda vez! O balcão da taverna era um carvalho velho e rancoroso da encosta norte e eu não perdoei.
    >>  ............................................
  witty.dialogue.conversations.work.prof.woodworker.more/2
    en  A staircase. A proper turned one. Nobody needs it, which is exactly why I want it.
    >>  ............................................
    pt  Uma escada. De verdade, torneada. Ninguém precisa, e é exatamente por isso que eu quero.
    >>  ............................................
```

</details>


### Button `leave` — "Straight grain."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.woodworker.challenge.landed`, `work.woodworker.challenge.stung`, `work.woodworker.craft.admire`, `work.woodworker.craft.ask_knuckles`, `work.woodworker.craft.ask_moves`, `work.woodworker.future.ask_stair`, `work.woodworker.future.ask_three_doors`, `work.woodworker.future.encourage`, `work.woodworker.hard`, `work.woodworker.risk.ask_beam`, `work.woodworker.risk.ask_saw`, `work.woodworker.risk.sympathise`, `work.woodworker.task.ask_chairs`, `work.woodworker.task.ask_eleventh`, `work.woodworker.task.offer_hands`, `work.woodworker.value`, `work.woodworker.village.ask_cradle`, `work.woodworker.village.ask_doors`, `work.woodworker.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.followup.leave   [15 chars]
    en  Straight grain.
    >>  ............................................
    pt  Veio reto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.leave
WHO    VILLAGER — what the player reads after pressing "Straight grain."
       spoken on: conversations.topic.work.woodworker.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.left`: the villager accepts. Subject `work.woodworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.woodworker.bad_joint.active.respond / leave; conversations.scene.work.woodworker.bad_joint.succeeded.respond / leave; conversations.scene.work.woodworker.followup / leave; conversations.scene.work.woodworker.teachers_chair.succeeded.respond / leave; conversations.scene.work.woodworker.warped_piece.blocked.respond / leave; conversations.scene.work.woodworker.warped_piece.succeeded.respond / leave; conversations.topic.work.woodworker.craft.respond / leave; conversations.topic.work.woodworker.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.woodworker.bad_joint.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.woodworker.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.woodworker.future` — e.g. "A staircase. A proper turned one. Nobody here needs one and I want to make one anyway."


```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.woodworker.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.woodworker.future.respond   [33 chars]
    en  That's what's ahead of the bench.
    >>  ............................................
    pt  É o que está à frente da bancada.
    >>  ............................................
```


### Button `ask_stair` — "Where would a staircase even go?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.woodworker.future` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.woodworker.future.ask_stair` — accepted phrasings: "where would a staircase even go"
  - the message must contain one of: `staircase`, `stair`
  - scored words: `staircase`(1.5), `stair`(1.5), `where`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.future.respond.ask_stair
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.future.respond.ask_stair   [32 chars]
    en  Where would a staircase even go?
    >>  ............................................
    pt  Onde uma escada iria?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.woodworker.future.ask_stair`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.woodworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Can you really tell the tree?" | "Straight grain."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.future.ask_stair
WHO    VILLAGER — what the player reads after pressing "Where would a staircase even go?"
       spoken on: conversations.topic.work.woodworker.future.respond, button `ask_stair`
       leaves the player on: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.future.ask_stair`: the villager explains. Subject `work.woodworker.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.woodworker.future.ask_stair/1   [81 chars]
    en  The mill has a ladder and a woman of sixty climbing it. That's where it would go.
    >>  ............................................
    pt  O moinho tem uma escada de mão e uma mulher de sessenta subindo. É pra lá que iria.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.future.ask_stair/2   [88 chars]
    en  Nowhere. That's what makes it the thing I want rather than the thing I'm owed for, %1$s.
    >>  ............................................
    pt  Lugar nenhum. É isso que faz dela o que eu quero e não o que me pagam, %1$s.
    >>  ............................................
```


### Button `encourage` — "Then build it for the mill and call it a commission."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.woodworker.future` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.woodworker.future.encourage` — accepted phrasings: "then build it for the mill and call it a commission"
  - the message must contain one of: `mill`, `commission`
  - scored words: `mill`(1.5), `commission`(1.5), `build`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.future.respond.encourage   [52 chars]
    en  Then build it for the mill and call it a commission.
    >>  ............................................
    pt  Então faça pro moinho e chame de encomenda.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.woodworker.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.woodworker.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.woodworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Can you really tell the tree?" | "Straight grain."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.future.encourage
WHO    VILLAGER — what the player reads after pressing "Then build it for the mill and call it a commission."
       spoken on: conversations.topic.work.woodworker.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.future.encourage`: the villager accepts. Subject `work.woodworker.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.woodworker.future.encourage/1   [82 chars]
    en  ...Call it a commission. That's how you get a thing built in this place, isn't it.
    >>  ............................................
    pt  ...Chamar de encomenda. É assim que se constrói algo aqui, não é.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.future.encourage/2   [89 chars]
    en  She'd never ask and she'd use it every day, %1$s. That settles the argument I was having.
    >>  ............................................
    pt  Ela nunca pediria e usaria todo dia, %1$s. Isso encerra a discussão que eu estava tendo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission. Then it isn't me deciding somebody needs a gift.
    >>  ............................................
    pt  ...Chame de encomenda. Aí não sou eu decidindo que alguém precisa de presente.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask and she'd use it every day. I'd like to have made her something.
    >>  ............................................
    pt  Ela nunca pediria e usaria todo dia. Eu gostaria de ter feito algo pra ela.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission. Thirty years and that's still how a thing gets built here.
    >>  ............................................
    pt  ...Chame de encomenda. Trinta anos e ainda é assim que se constrói algo aqui.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask and she'd use it every day. Those are the only two things worth making.
    >>  ............................................
    pt  Ela nunca pediria e usaria todo dia. São as únicas duas coisas que valem fazer.
    >>  ............................................
  confident.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission. That's how you get a thing built in this place, isn't it.
    >>  ............................................
    pt  ...Chame de encomenda. É assim que se constrói algo neste lugar, não é.
    >>  ............................................
  confident.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask and she'd use it every day. That settles the argument I was having.
    >>  ............................................
    pt  Ela nunca pediria e usaria todo dia. Isso encerra a discussão que eu tinha.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission. That's how you get a thing built in this place, isn't it.
    >>  ............................................
    pt  ...Chame de encomenda. É assim que se constrói algo neste lugar, não é.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask and she'd use it every day. That settles the argument I was having.
    >>  ............................................
    pt  Ela nunca pediria e usaria todo dia. Isso encerra a discussão que eu tinha.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission, %1$s. That's how a thing gets built in this place.
    >>  ............................................
    pt  ...Chame de encomenda, %1$s. É assim que se constrói algo neste lugar.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask and she'd use it every day. That settles the argument I was having.
    >>  ............................................
    pt  Ela nunca pediria e usaria todo dia. Isso encerra a discussão que eu tinha.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission, %1$s. That's how a thing gets built in this place.
    >>  ............................................
    pt  ...Chame de encomenda, %1$s. É assim que se constrói algo neste lugar.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask and she'd use it every day. That settles the argument I was having.
    >>  ............................................
    pt  Ela nunca pediria e usaria todo dia. Isso encerra a discussão que eu tinha.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission, %1$s. That's how a thing gets built in this place.
    >>  ............................................
    pt  ...Chame de encomenda, %1$s. É assim que se constrói algo neste lugar.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask and she'd use it every day. That settles the argument I was having.
    >>  ............................................
    pt  Ela nunca pediria e usaria todo dia. Isso encerra a discussão que eu tinha.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission. Then it isn't me deciding somebody needs a gift.
    >>  ............................................
    pt  ...Chame de encomenda. Aí não sou eu decidindo que alguém precisa de presente.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask and she'd use it every day. I'd like to have made her something.
    >>  ............................................
    pt  Ela nunca pediria e usaria todo dia. Eu gostaria de ter feito algo pra ela.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission. That's how you get a thing built in this place, isn't it.
    >>  ............................................
    pt  ...Chame de encomenda. É assim que se constrói algo neste lugar, não é.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask and she'd use it every day. That settles the argument I was having.
    >>  ............................................
    pt  Ela nunca pediria e usaria todo dia. Isso encerra a discussão que eu tinha.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission. That's how you get a thing built in this place, isn't it.
    >>  ............................................
    pt  ...Chame de encomenda. É assim que se constrói algo neste lugar, não é.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask and she'd use it every day. That settles the argument I was having.
    >>  ............................................
    pt  Ela nunca pediria e usaria todo dia. Isso encerra a discussão que eu tinha.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission. That's how things get built here.
    >>  ............................................
    pt  ...Chame de encomenda. É assim que as coisas se constroem aqui.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask. She'd use it daily.
    >>  ............................................
    pt  Ela nunca pediria. Usaria todo dia.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission. Thirty years and that's still how a thing gets built here.
    >>  ............................................
    pt  ...Chame de encomenda. Trinta anos e ainda é assim que se constrói algo aqui.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask and she'd use it every day. Those are the only two things worth making.
    >>  ............................................
    pt  Ela nunca pediria e usaria todo dia. São as únicas duas coisas que valem fazer.
    >>  ............................................
  odd.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission. That's how things get built here.
    >>  ............................................
    pt  ...Chame de encomenda. É assim que as coisas se constroem aqui.
    >>  ............................................
  odd.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask. She'd use it daily.
    >>  ............................................
    pt  Ela nunca pediria. Usaria todo dia.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission. Thirty years and that's still how a thing gets built here.
    >>  ............................................
    pt  ...Chame de encomenda. Trinta anos e ainda é assim que se constrói algo aqui.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask and she'd use it every day. Those are the only two things worth making.
    >>  ............................................
    pt  Ela nunca pediria e usaria todo dia. São as únicas duas coisas que valem fazer.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission! That's how anything gets built in this place, isn't it.
    >>  ............................................
    pt  ...Chame de encomenda! É assim que qualquer coisa se constrói aqui, não é.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask and she'd use it every day. That settles the argument I was having.
    >>  ............................................
    pt  Ela nunca pediria e usaria todo dia. Isso encerra a discussão que eu tinha.
    >>  ............................................
  playful.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission! That's how anything gets built in this place, isn't it.
    >>  ............................................
    pt  ...Chame de encomenda! É assim que qualquer coisa se constrói aqui, não é.
    >>  ............................................
  playful.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask and she'd use it every day. That settles the argument I was having.
    >>  ............................................
    pt  Ela nunca pediria e usaria todo dia. Isso encerra a discussão que eu tinha.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission. Thirty years and that's still how a thing gets built here.
    >>  ............................................
    pt  ...Chame de encomenda. Trinta anos e ainda é assim que se constrói algo aqui.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask and she'd use it every day. Those are the only two things worth making.
    >>  ............................................
    pt  Ela nunca pediria e usaria todo dia. São as únicas duas coisas que valem fazer.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission. Then it isn't me deciding somebody needs a gift.
    >>  ............................................
    pt  ...Chame de encomenda. Aí não sou eu decidindo que alguém precisa de presente.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask and she'd use it every day. I'd like to have made her something.
    >>  ............................................
    pt  Ela nunca pediria e usaria todo dia. Eu gostaria de ter feito algo pra ela.
    >>  ............................................
  shy.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission. That's how things get built here.
    >>  ............................................
    pt  ...Chame de encomenda. É assim que as coisas se constroem aqui.
    >>  ............................................
  shy.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask. She'd use it daily.
    >>  ............................................
    pt  Ela nunca pediria. Usaria todo dia.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission! That's how anything gets built in this place, isn't it.
    >>  ............................................
    pt  ...Chame de encomenda! É assim que qualquer coisa se constrói aqui, não é.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask and she'd use it every day. That settles the argument I was having.
    >>  ............................................
    pt  Ela nunca pediria e usaria todo dia. Isso encerra a discussão que eu tinha.
    >>  ............................................
  witty.dialogue.conversations.work.prof.woodworker.future.encourage/1
    en  ...Call it a commission! That's how anything gets built in this place, isn't it.
    >>  ............................................
    pt  ...Chame de encomenda! É assim que qualquer coisa se constrói aqui, não é.
    >>  ............................................
  witty.dialogue.conversations.work.prof.woodworker.future.encourage/2
    en  She'd never ask and she'd use it every day. That settles the argument I was having.
    >>  ............................................
    pt  Ela nunca pediria e usaria todo dia. Isso encerra a discussão que eu tinha.
    >>  ............................................
```

</details>


### Button `ask_three_doors` — "What's different about the three doors?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.woodworker.future` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.woodworker.future.ask_three_doors` — accepted phrasings: "what's different about the three doors"
  - the message must contain one of: `doors`, `different`, `older`
  - scored words: `doors`(1.2), `different`(1.2), `older`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.future.respond.ask_three_doors
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.future.respond.ask_three_doors   [39 chars]
    en  What's different about the three doors?
    >>  ............................................
    pt  O que as três portas têm de diferente?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.woodworker.future.ask_three_doors`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.woodworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Can you really tell the tree?" | "Straight grain."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.future.ask_three_doors
WHO    VILLAGER — what the player reads after pressing "What's different about the three doors?"
       spoken on: conversations.topic.work.woodworker.future.respond, button `ask_three_doors`
       leaves the player on: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.future.ask_three_doors`: the villager explains. Subject `work.woodworker.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.woodworker.future.ask_three_doors/1   [83 chars]
    en  No nails. Not one, in any of them. I've taken one apart in my head a hundred times.
    >>  ............................................
    pt  Sem pregos. Nem um, em nenhuma delas. Já desmontei uma na cabeça umas cem vezes.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.future.ask_three_doors/2   [83 chars]
    en  They've never dropped on the hinge in ninety years, %1$s, and I can't tell you why.
    >>  ............................................
    pt  Nunca cederam na dobradiça em noventa anos, %1$s, e eu não sei dizer por quê.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the bench."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.woodworker.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.future.respond.leave   [35 chars]
    en  I'll let you get back to the bench.
    >>  ............................................
    pt  Vou deixar você voltar pra bancada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the bench."
       spoken on: conversations.topic.work.woodworker.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.left`: the villager accepts. Subject `work.woodworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.woodworker.bad_joint.active.respond / leave; conversations.scene.work.woodworker.bad_joint.succeeded.respond / leave; conversations.scene.work.woodworker.followup / leave; conversations.scene.work.woodworker.teachers_chair.succeeded.respond / leave; conversations.scene.work.woodworker.warped_piece.blocked.respond / leave; conversations.scene.work.woodworker.warped_piece.succeeded.respond / leave; conversations.topic.work.woodworker.craft.respond / leave; conversations.topic.work.woodworker.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.woodworker.bad_joint.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.woodworker.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.woodworker` — e.g. "Trees give a lifetime of patience in every plank. I try to build things worthy of the wait."


```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.woodworker.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.woodworker.respond   [35 chars]
    en  That's the grain and what I owe it.
    >>  ............................................
    pt  É o veio da madeira e o que eu devo a ele.
    >>  ............................................
```


### Button `ask_hard` — "What's a difficult piece?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.woodworker.identity` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.woodworker.hard` — accepted phrasings: "what's a difficult piece"
  - the message must contain one of: `difficult`, `piece`, `curve`
  - scored words: `difficult`(1.5), `piece`(1.2), `curve`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.respond.ask_hard   [25 chars]
    en  What's a difficult piece?
    >>  ............................................
    pt  O que é uma peça difícil?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.woodworker.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.woodworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Can you really tell the tree?" | "Straight grain."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.hard
WHO    VILLAGER — what the player reads after pressing "What's a difficult piece?"
       spoken on: conversations.topic.work.woodworker.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.hard`: the villager explains. Subject `work.woodworker.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.woodworker.followup / ask_more
```

> Written out in full under **`conversations.scene.work.woodworker.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "Families live inside what you build."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.woodworker.identity` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.woodworker.value` — accepted phrasings: "families live inside what you build"
  - the message must contain one of: `families`, `inside`
  - scored words: `families`(1.5), `inside`(1.5), `build`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.respond.value   [36 chars]
    en  Families live inside what you build.
    >>  ............................................
    pt  Famílias moram dentro do que você constrói.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.woodworker.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.woodworker.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.woodworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Can you really tell the tree?" | "Straight grain."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.value
WHO    VILLAGER — what the player reads after pressing "Families live inside what you build."
       spoken on: conversations.topic.work.woodworker.respond, button `value`
       leaves the player on: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.value`: the villager accepts. Subject `work.woodworker.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.woodworker.value/1   [91 chars]
    en  They do. That's a heavier sentence than you meant it to be and I'll be carrying it all day.
    >>  ............................................
    pt  Moram. É uma frase mais pesada do que você quis dizer e eu vou carregar ela o dia todo.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.value/2   [63 chars]
    en  Aye. Beams, doors, the table they argue across. All of it mine.
    >>  ............................................
    pt  É. Vigas, portas, a mesa em que discutem. Tudo meu.
    >>  ............................................
```


### Button `challenge` — "It's just cutting up trees."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.woodworker.identity` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.woodworker.challenge` — accepted phrasings: "it's just cutting up trees"
  - the message must contain one of: `cutting`, `trees`
  - scored words: `cutting`(1.5), `trees`(1.2), `just`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.respond.challenge   [27 chars]
    en  It's just cutting up trees.
    >>  ............................................
    pt  É só cortar árvore.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.woodworker.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.woodworker.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.woodworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Can you really tell the tree?" | "Straight grain."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.challenge.landed
WHO    VILLAGER — what the player reads after pressing "It's just cutting up trees."
       spoken on: conversations.topic.work.woodworker.respond, button `challenge`
       leaves the player on: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.challenge.landed`: the villager resists. Subject `work.woodworker.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.woodworker.challenge.landed/1   [81 chars]
    en  It's mostly not cutting them. Choosing which, and leaving the rest, is the trade.
    >>  ............................................
    pt  É principalmente não cortar. Escolher qual, e deixar o resto, é o ofício.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.challenge.landed/2   [68 chars]
    en  Cutting up trees. Aye, and a violin is just cat gut and a box, %1$s.
    >>  ............................................
    pt  Cortar árvore. É, e um violino é só tripa de gato e uma caixa, %1$s.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.woodworker.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.woodworker.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.woodworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Can you really tell the tree?" | "Straight grain."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.challenge.stung
WHO    VILLAGER — what the player reads after pressing "It's just cutting up trees."
       spoken on: conversations.topic.work.woodworker.respond, button `challenge`
       leaves the player on: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.challenge.stung`: the villager resists. Subject `work.woodworker.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.woodworker.challenge.stung/1   [59 chars]
    en  ...I've a beam upstairs I chose nine years before I cut it.
    >>  ............................................
    pt  ...Tenho uma viga lá em cima que eu escolhi nove anos antes de cortar.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.challenge.stung/2   [66 chars]
    en  Just cutting. Right. Sleep under a roof somebody 'just cut', then.
    >>  ............................................
    pt  Só cortar. Certo. Durma sob um telhado que alguém 'só cortou', então.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the bench."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.woodworker.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.respond.leave   [35 chars]
    en  I'll let you get back to the bench.
    >>  ............................................
    pt  Vou deixar você voltar pra bancada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the bench."
       spoken on: conversations.topic.work.woodworker.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.left`: the villager accepts. Subject `work.woodworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.woodworker.bad_joint.active.respond / leave; conversations.scene.work.woodworker.bad_joint.succeeded.respond / leave; conversations.scene.work.woodworker.followup / leave; conversations.scene.work.woodworker.teachers_chair.succeeded.respond / leave; conversations.scene.work.woodworker.warped_piece.blocked.respond / leave; conversations.scene.work.woodworker.warped_piece.succeeded.respond / leave; conversations.topic.work.woodworker.craft.respond / leave; conversations.topic.work.woodworker.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.woodworker.bad_joint.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.woodworker.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.woodworker.risk` — e.g. "A roof beam I cut holds up a room with a bed in it. I know which room and I do not walk past it."


```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.woodworker.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.woodworker.risk.respond   [30 chars]
    en  That's what's under the bench.
    >>  ............................................
    pt  É o que está sob a bancada.
    >>  ............................................
```


### Button `ask_beam` — "Why don't you walk past it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.woodworker.risk` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.woodworker.risk.ask_beam` — accepted phrasings: "why don't you walk past it"
  - the message must contain one of: `beam`, `past`, `avoid`
  - scored words: `beam`(1.5), `past`(1.0), `avoid`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.risk.respond.ask_beam
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.risk.respond.ask_beam   [27 chars]
    en  Why don't you walk past it?
    >>  ............................................
    pt  Por que você não passa por lá?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.woodworker.risk.ask_beam`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.woodworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Can you really tell the tree?" | "Straight grain."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.risk.ask_beam
WHO    VILLAGER — what the player reads after pressing "Why don't you walk past it?"
       spoken on: conversations.topic.work.woodworker.risk.respond, button `ask_beam`
       leaves the player on: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.risk.ask_beam`: the villager explains. Subject `work.woodworker.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.woodworker.risk.ask_beam/1   [86 chars]
    en  Because I'd look up. And once you've looked up you spend the evening doing arithmetic.
    >>  ............................................
    pt  Porque eu olharia pra cima. E depois de olhar você passa a noite fazendo contas.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.risk.ask_beam/2   [92 chars]
    en  Because I checked it four times when I set it, %1$s, and a fifth check would mean something.
    >>  ............................................
    pt  Porque eu conferi quatro vezes quando assentei, %1$s, e uma quinta significaria algo.
    >>  ............................................
```


### Button `sympathise` — "Two fingers is a hard way to be reminded."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.woodworker.risk` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.woodworker.risk.sympathise` — accepted phrasings: "two fingers is a hard way to be reminded"
  - the message must contain one of: `fingers`, `reminded`
  - scored words: `fingers`(1.5), `reminded`(1.2), `hard`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.risk.respond.sympathise   [41 chars]
    en  Two fingers is a hard way to be reminded.
    >>  ............................................
    pt  Dois dedos é um jeito duro de ser lembrado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.woodworker.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.woodworker.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.woodworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Can you really tell the tree?" | "Straight grain."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "Two fingers is a hard way to be reminded."
       spoken on: conversations.topic.work.woodworker.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.risk.sympathise`: the villager accepts. Subject `work.woodworker.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.woodworker.risk.sympathise/1   [83 chars]
    en  ...It is, and it was my fault, and being able to say both is what took the longest.
    >>  ............................................
    pt  ...É, e a culpa foi minha, e conseguir dizer as duas coisas foi o que levou mais tempo.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.risk.sympathise/2   [97 chars]
    en  It's the cheapest lesson I've had, %1$s, and it cost two fingers. Think about the expensive ones.
    >>  ............................................
    pt  É a lição mais barata que eu tive, %1$s, e custou dois dedos. Pense nas caras.
    >>  ............................................
```


### Button `ask_saw` — "What went wrong with the saw?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.woodworker.risk` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.woodworker.risk.ask_saw` — accepted phrasings: "what went wrong with the saw"
  - the message must contain one of: `saw`, `accident`
  - scored words: `saw`(1.5), `wrong`(0.8), `accident`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.risk.respond.ask_saw
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.risk.respond.ask_saw   [29 chars]
    en  What went wrong with the saw?
    >>  ............................................
    pt  O que deu errado com a serra?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.woodworker.risk.ask_saw`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.woodworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Can you really tell the tree?" | "Straight grain."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.risk.ask_saw
WHO    VILLAGER — what the player reads after pressing "What went wrong with the saw?"
       spoken on: conversations.topic.work.woodworker.risk.respond, button `ask_saw`
       leaves the player on: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.risk.ask_saw`: the villager explains. Subject `work.woodworker.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.woodworker.risk.ask_saw/1   [87 chars]
    en  I was finishing a piece at dusk because a customer was waiting. That's the whole story.
    >>  ............................................
    pt  Eu estava terminando uma peça ao anoitecer porque um cliente esperava. É toda a história.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.risk.ask_saw/2   [83 chars]
    en  Nothing went wrong with the saw. Everything went wrong with the hour I chose, %1$s.
    >>  ............................................
    pt  Nada deu errado com a serra. Tudo deu errado com a hora que eu escolhi, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the bench."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.woodworker.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.risk.respond.leave   [35 chars]
    en  I'll let you get back to the bench.
    >>  ............................................
    pt  Vou deixar você voltar pra bancada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the bench."
       spoken on: conversations.topic.work.woodworker.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.left`: the villager accepts. Subject `work.woodworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.woodworker.bad_joint.active.respond / leave; conversations.scene.work.woodworker.bad_joint.succeeded.respond / leave; conversations.scene.work.woodworker.followup / leave; conversations.scene.work.woodworker.teachers_chair.succeeded.respond / leave; conversations.scene.work.woodworker.warped_piece.blocked.respond / leave; conversations.scene.work.woodworker.warped_piece.succeeded.respond / leave; conversations.topic.work.woodworker.craft.respond / leave; conversations.topic.work.woodworker.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.woodworker.bad_joint.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.woodworker.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.woodworker.task` — e.g. "Joints. Eleven of them for a frame, and the eleventh has to fit as well as the first did."


```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.woodworker.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.woodworker.task.respond   [23 chars]
    en  That's the bench today.
    >>  ............................................
    pt  É a bancada hoje.
    >>  ............................................
```


### Button `ask_eleventh` — "Why is the eleventh the hard one?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.woodworker.task` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.woodworker.task.ask_eleventh` — accepted phrasings: "why is the eleventh the hard one"
  - the message must contain one of: `eleventh`, `joints`
  - scored words: `eleventh`(1.5), `joints`(1.2), `hard`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.task.respond.ask_eleventh
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.task.respond.ask_eleventh   [33 chars]
    en  Why is the eleventh the hard one?
    >>  ............................................
    pt  Por que o décimo primeiro é o difícil?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.woodworker.task.ask_eleventh`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.woodworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Can you really tell the tree?" | "Straight grain."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.task.ask_eleventh
WHO    VILLAGER — what the player reads after pressing "Why is the eleventh the hard one?"
       spoken on: conversations.topic.work.woodworker.task.respond, button `ask_eleventh`
       leaves the player on: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.task.ask_eleventh`: the villager explains. Subject `work.woodworker.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.woodworker.task.ask_eleventh/1   [100 chars]
    en  Because by then your hand is tired and your eye has agreed to be satisfied. That's when frames fail.
    >>  ............................................
    pt  Porque a essa altura a mão cansou e o olho concordou em se satisfazer. É aí que as armações falham.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.task.ask_eleventh/2   [87 chars]
    en  Because the first ten decided the shape and the eleventh has to obey all of them, %1$s.
    >>  ............................................
    pt  Porque os dez primeiros decidiram a forma e o décimo primeiro tem que obedecer todos, %1$s.
    >>  ............................................
```


### Button `offer_hands` — "I could stack the seasoning stock."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.woodworker.task` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.woodworker.task.offer_hands` — accepted phrasings: "i could stack the seasoning stock"
  - the message must contain one of: `stack`, `seasoning`, `timber`
  - scored words: `stack`(1.5), `seasoning`(1.5), `timber`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.task.respond.offer_hands   [34 chars]
    en  I could stack the seasoning stock.
    >>  ............................................
    pt  Eu podia empilhar a madeira em cura.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.woodworker.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.woodworker.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.woodworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Can you really tell the tree?" | "Straight grain."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I could stack the seasoning stock."
       spoken on: conversations.topic.work.woodworker.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.task.offer_hands`: the villager accepts. Subject `work.woodworker.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.woodworker.task.offer_hands/1   [99 chars]
    en  ...You could. Sticks between every layer, air all the way through, and nothing touching the ground.
    >>  ............................................
    pt  ...Podia. Sarrafo entre cada camada, ar passando por tudo, e nada tocando o chão.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.task.offer_hands/2   [81 chars]
    en  Do it wrong and you've made firewood out of two years of patience, %1$s. Careful.
    >>  ............................................
    pt  Faça errado e você fez lenha de dois anos de paciência, %1$s. Cuidado.
    >>  ............................................
```


### Button `ask_chairs` — "What happened to the three chairs?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.woodworker.task` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.woodworker.task.ask_chairs` — accepted phrasings: "what happened to the three chairs"
  - the message must contain one of: `chairs`, `three`, `ruined`
  - scored words: `chairs`(1.5), `three`(1.0), `ruined`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.task.respond.ask_chairs
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.task.respond.ask_chairs   [34 chars]
    en  What happened to the three chairs?
    >>  ............................................
    pt  O que aconteceu com as três cadeiras?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.woodworker.task.ask_chairs`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.woodworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Can you really tell the tree?" | "Straight grain."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.task.ask_chairs
WHO    VILLAGER — what the player reads after pressing "What happened to the three chairs?"
       spoken on: conversations.topic.work.woodworker.task.respond, button `ask_chairs`
       leaves the player on: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.task.ask_chairs`: the villager explains. Subject `work.woodworker.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.woodworker.task.ask_chairs/1   [94 chars]
    en  They twisted over a winter. Two came back and one I saw in a barn, being used as a barn thing.
    >>  ............................................
    pt  Empenaram num inverno. Duas voltaram e uma eu vi num celeiro, servindo de coisa de celeiro.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.task.ask_chairs/2   [74 chars]
    en  I bought them back and burned them, %1$s, which was expensive and correct.
    >>  ............................................
    pt  Comprei de volta e queimei, %1$s, o que foi caro e correto.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the bench."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.woodworker.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.task.respond.leave   [35 chars]
    en  I'll let you get back to the bench.
    >>  ............................................
    pt  Vou deixar você voltar pra bancada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the bench."
       spoken on: conversations.topic.work.woodworker.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.left`: the villager accepts. Subject `work.woodworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.woodworker.bad_joint.active.respond / leave; conversations.scene.work.woodworker.bad_joint.succeeded.respond / leave; conversations.scene.work.woodworker.followup / leave; conversations.scene.work.woodworker.teachers_chair.succeeded.respond / leave; conversations.scene.work.woodworker.warped_piece.blocked.respond / leave; conversations.scene.work.woodworker.warped_piece.succeeded.respond / leave; conversations.topic.work.woodworker.craft.respond / leave; conversations.topic.work.woodworker.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.woodworker.bad_joint.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.woodworker.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.woodworker.village` — e.g. "Every roof, every door, every table people eat at. I don't sign them and I know all of them."


```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.woodworker.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.woodworker.village.respond   [27 chars]
    en  That's what I've left here.
    >>  ............................................
    pt  É o que eu deixei aqui.
    >>  ............................................
```


### Button `ask_cradle` — "Nine infants in the same cradle?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.woodworker.village` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.woodworker.village.ask_cradle` — accepted phrasings: "nine infants in the same cradle"
  - the message must contain one of: `cradle`, `nine`, `infants`
  - scored words: `cradle`(1.5), `nine`(1.2), `infants`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.village.respond.ask_cradle
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.village.respond.ask_cradle   [32 chars]
    en  Nine infants in the same cradle?
    >>  ............................................
    pt  Nove bebês no mesmo berço?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.woodworker.village.ask_cradle`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.woodworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Can you really tell the tree?" | "Straight grain."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.village.ask_cradle
WHO    VILLAGER — what the player reads after pressing "Nine infants in the same cradle?"
       spoken on: conversations.topic.work.woodworker.village.respond, button `ask_cradle`
       leaves the player on: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.village.ask_cradle`: the villager explains. Subject `work.woodworker.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.woodworker.village.ask_cradle/1   [70 chars]
    en  Nine. I rehang the ropes every second year and no one has asked me to.
    >>  ............................................
    pt  Nove. Eu troco as cordas a cada dois anos e ninguém me pediu.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.village.ask_cradle/2   [93 chars]
    en  Nine, and the fourth family brought it back for mending with an apology, %1$s. I nearly wept.
    >>  ............................................
    pt  Nove, e a quarta família trouxe pra consertar com um pedido de desculpa, %1$s. Eu quase chorei.
    >>  ............................................
```


### Button `say_thanks` — "Not signing them doesn't mean nobody knows."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.woodworker.village` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.woodworker.village.say_thanks` — accepted phrasings: "not signing them doesn't mean nobody knows"
  - the message must contain one of: `signing`, `knows`, `unsigned`
  - scored words: `signing`(1.5), `knows`(1.0), `unsigned`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.village.respond.say_thanks   [43 chars]
    en  Not signing them doesn't mean nobody knows.
    >>  ............................................
    pt  Não assinar não significa que ninguém sabe.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.woodworker.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.woodworker.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.woodworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Can you really tell the tree?" | "Straight grain."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Not signing them doesn't mean nobody knows."
       spoken on: conversations.topic.work.woodworker.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.village.say_thanks`: the villager accepts. Subject `work.woodworker.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.woodworker.village.say_thanks/1   [90 chars]
    en  ...Somebody knowing is a different thing from somebody saying. You've done the second one.
    >>  ............................................
    pt  ...Alguém saber é diferente de alguém dizer. Você fez a segunda coisa.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.village.say_thanks/2   [81 chars]
    en  I'd assumed the not-signing meant the not-knowing followed, %1$s. Apparently not.
    >>  ............................................
    pt  Eu supunha que não assinar levava a não saber, %1$s. Aparentemente não.
    >>  ............................................
```


### Button `ask_doors` — "How many doors in this place are yours?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.woodworker.village` · offered only once the villager has actually said `work:woodworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.woodworker.village.ask_doors` — accepted phrasings: "how many doors in this place are yours"
  - the message must contain one of: `doors`, `count`
  - scored words: `doors`(1.5), `many`(0.8), `count`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.village.respond.ask_doors
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.village.respond.ask_doors   [39 chars]
    en  How many doors in this place are yours?
    >>  ............................................
    pt  Quantas portas daqui são suas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.woodworker.village.ask_doors`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.woodworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Can you really tell the tree?" | "Straight grain."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.village.ask_doors
WHO    VILLAGER — what the player reads after pressing "How many doors in this place are yours?"
       spoken on: conversations.topic.work.woodworker.village.respond, button `ask_doors`
       leaves the player on: conversations.topic.work.woodworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.village.ask_doors`: the villager explains. Subject `work.woodworker.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:woodworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.woodworker.village.ask_doors/1   [96 chars]
    en  Thirty-one of thirty-four. The other three are older than me and better made, and it bothers me.
    >>  ............................................
    pt  Trinta e uma de trinta e quatro. As outras três são mais velhas e melhores, e isso me incomoda.
    >>  ............................................
  dialogue.conversations.work.prof.woodworker.village.ask_doors/2   [75 chars]
    en  All but three. I could tell you which three from the sound they make, %1$s.
    >>  ............................................
    pt  Todas menos três. Eu saberia dizer quais três pelo som que fazem, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the bench."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.woodworker.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.woodworker.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.woodworker.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.woodworker.village.respond.leave   [35 chars]
    en  I'll let you get back to the bench.
    >>  ............................................
    pt  Vou deixar você voltar pra bancada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.woodworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the bench."
       spoken on: conversations.topic.work.woodworker.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.woodworker.left`: the villager accepts. Subject `work.woodworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.woodworker.bad_joint.active.respond / leave; conversations.scene.work.woodworker.bad_joint.succeeded.respond / leave; conversations.scene.work.woodworker.followup / leave; conversations.scene.work.woodworker.teachers_chair.succeeded.respond / leave; conversations.scene.work.woodworker.warped_piece.blocked.respond / leave; conversations.scene.work.woodworker.warped_piece.succeeded.respond / leave; conversations.topic.work.woodworker.craft.respond / leave; conversations.topic.work.woodworker.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.woodworker.bad_joint.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

