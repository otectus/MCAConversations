# Work talk with a florist

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.florist.failed_bed.blocked.respond`](#conversations-scene-work-florist-failed-bed-blocked-respond)
- [`conversations.scene.work.florist.failed_bed.succeeded.respond`](#conversations-scene-work-florist-failed-bed-succeeded-respond)
- [`conversations.scene.work.florist.followup`](#conversations-scene-work-florist-followup)
- [`conversations.scene.work.florist.funeral_order.active.respond`](#conversations-scene-work-florist-funeral-order-active-respond)
- [`conversations.scene.work.florist.funeral_order.succeeded.respond`](#conversations-scene-work-florist-funeral-order-succeeded-respond)
- [`conversations.scene.work.florist.stubborn_variety.active.respond`](#conversations-scene-work-florist-stubborn-variety-active-respond)
- [`conversations.scene.work.florist.stubborn_variety.succeeded.respond`](#conversations-scene-work-florist-stubborn-variety-succeeded-respond)
- [`conversations.topic.work.florist.craft.respond`](#conversations-topic-work-florist-craft-respond)
- [`conversations.topic.work.florist.followup`](#conversations-topic-work-florist-followup)
- [`conversations.topic.work.florist.future.respond`](#conversations-topic-work-florist-future-respond)
- [`conversations.topic.work.florist.respond`](#conversations-topic-work-florist-respond)
- [`conversations.topic.work.florist.risk.respond`](#conversations-topic-work-florist-risk-respond)
- [`conversations.topic.work.florist.task.respond`](#conversations-topic-work-florist-task-respond)
- [`conversations.topic.work.florist.village.respond`](#conversations-topic-work-florist-village-respond)

---

## `conversations.scene.work.florist.failed_bed.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.florist.failed_bed.blocked` — e.g. "%2$s has gone, all of it, to %3$s, and it was eight months of work standing in a row."


```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.failed_bed.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.florist.failed_bed.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.florist.failed_bed.blocked.respond   [9 chars]
    en  The beds.
    >>  ............................................
    pt  Os canteiros.
    >>  ............................................
```


### Button `ask_if_recoverable` — "Can the bed be brought back?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.florist.failed_bed.blocked` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.florist.failed_bed.blocked.ask_if_recoverable` — accepted phrasings: "can the bed be brought back"; "can the bed be brought back"; "is the ground recoverable"
  - the message must contain one of: `bed`, `ground`, `recoverable`
  - scored words: `bed`(1.8), `ground`(1.8), `recoverable`(1.8), `brought`(0.8), `back`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.failed_bed.blocked.respond.ask_if_recoverable
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.florist.failed_bed.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.florist.failed_bed.blocked.respond.ask_if_recoverable   [28 chars]
    en  Can the bed be brought back?
    >>  ............................................
    pt  Dá para recuperar o canteiro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.florist.the_beds`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.florist.failed_bed"}
- Then opens: `conversations.scene.work.florist.followup`
- …where the player's next choices will be: "What's the hardest part of a bloom that fails?" | "I'll leave you to the beds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.failed_bed.blocked.explained
WHO    VILLAGER — what the player reads after pressing "Can the bed be brought back?"
       spoken on: conversations.scene.work.florist.failed_bed.blocked.respond, button `ask_if_recoverable`
       leaves the player on: conversations.scene.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.failed_bed.blocked.explained`: the villager explains. Subject `work.florist.the_beds`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.florist.failed_bed.blocked.explained/1   [111 chars]
    en  The soil, yes, with a season of feeding. %2$s itself is a year gone, and a year is a year whatever you feed it.
    >>  ............................................
    pt  O solo, sim, com uma estação de adubo. %2$s em si é um ano perdido, e um ano é um ano por mais que você aduba.
    >>  ............................................
  dialogue.conversations.scene.work.florist.failed_bed.blocked.explained/2   [140 chars]
    en  I can replant. What I cannot do is make anything old, and half of what I sell is old, because a plant with four years in it looks different.
    >>  ............................................
    pt  Posso replantar. O que eu não consigo é fazer algo ficar velho, e metade do que eu vendo é velho, porque uma planta com quatro anos parece diferente.
    >>  ............................................
  dialogue.conversations.scene.work.florist.failed_bed.blocked.explained/3   [122 chars]
    en  It depends what killed it. If it was the frost I lose a season; if the soil has turned I lose that ground for three years.
    >>  ............................................
    pt  Depende do que matou. Se foi a geada, perco uma estação; se o solo virou, perco aquele terreno por três anos.
    >>  ............................................
```


### Button `offer_bonemeal` — "I'll bring you bone meal."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.florist.failed_bed.blocked` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.florist.failed_bed.blocked.offer_bonemeal` — accepted phrasings: "ill bring you bone meal"; "i can bring you bone meal"; "let me fetch bone meal for that"
  - the message must contain one of: `meal`, `bone`
  - scored words: `meal`(1.8), `bone`(1.8), `ill`(0.8), `bring`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.failed_bed.blocked.respond.offer_bonemeal
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.florist.failed_bed.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.florist.failed_bed.blocked.respond.offer_bonemeal   [25 chars]
    en  I'll bring you bone meal.
    >>  ............................................
    pt  Vou te trazer farinha de osso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.florist.bed.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.florist.the_beds`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.failed_bed", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.florist.failed_bed", "obligation": "commitment:work.florist.bring_bonemeal"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.florist.bring_bonemeal"}
- Then opens: `conversations.scene.work.florist.followup`
- …where the player's next choices will be: "What's the hardest part of a bloom that fails?" | "I'll leave you to the beds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.failed_bed.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you bone meal."
       spoken on: conversations.scene.work.florist.failed_bed.blocked.respond, button `offer_bonemeal`
       leaves the player on: conversations.scene.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.failed_bed.blocked.accepted`: the villager accepts. Subject `work.florist.the_beds`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.florist.failed_bed.blocked.accepted/1   [92 chars]
    en  Then %2$s gets fed this week and I can put in the autumn stock instead of writing apologies.
    >>  ............................................
    pt  Então %2$s recebe adubo esta semana e eu posso plantar o estoque do outono em vez de escrever desculpas.
    >>  ............................................
  dialogue.conversations.scene.work.florist.failed_bed.blocked.accepted/2   [109 chars]
    en  That saves me a season, which in this trade is most of a year. I do not think people know that about flowers.
    >>  ............................................
    pt  Isso me poupa uma estação, que neste ofício é quase um ano. Acho que as pessoas não sabem disso sobre flores.
    >>  ............................................
  dialogue.conversations.scene.work.florist.failed_bed.blocked.accepted/3   [110 chars]
    en  Yes. And come in the autumn and take whatever is best out of that bed, and I will not be argued with about it.
    >>  ............................................
    pt  Sim. E venha no outono e leve o que houver de melhor naquele canteiro, e não vou aceitar discussão.
    >>  ............................................
```


### Button `advise_telling_the_orders` — "Warn the autumn orders now."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.florist.failed_bed.blocked` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.florist.failed_bed.blocked.advise_telling_the_orders` — accepted phrasings: "warn the autumn orders now"; "warn the autumn orders now"; "tell the customers early about the loss"
  - the message must contain one of: `orders`, `customers`, `warn`
  - scored words: `orders`(1.8), `customers`(1.8), `warn`(1.8), `autumn`(0.8), `now`(0.8), `tell`(0.8), `early`(0.8), `loss`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.failed_bed.blocked.respond.advise_telling_the_orders
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.florist.failed_bed.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.florist.failed_bed.blocked.respond.advise_telling_the_orders   [27 chars]
    en  Warn the autumn orders now.
    >>  ............................................
    pt  Avise já as encomendas do outono.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.florist.the_beds`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.florist.failed_bed"}
- Then opens: `conversations.scene.work.florist.followup`
- …where the player's next choices will be: "What's the hardest part of a bloom that fails?" | "I'll leave you to the beds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.failed_bed.blocked.conceded
WHO    VILLAGER — what the player reads after pressing "Warn the autumn orders now."
       spoken on: conversations.scene.work.florist.failed_bed.blocked.respond, button `advise_telling_the_orders`
       leaves the player on: conversations.scene.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.failed_bed.blocked.conceded`: the villager accepts. Subject `work.florist.the_beds`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.florist.failed_bed.blocked.conceded/1   [120 chars]
    en  Two of those orders are weddings. Told now, they can plan; told in the autumn, I have ruined a day that cannot be moved.
    >>  ............................................
    pt  Duas dessas encomendas são casamentos. Avisados agora, podem se planejar; avisados no outono, eu estraguei um dia que não se remarca.
    >>  ............................................
  dialogue.conversations.scene.work.florist.failed_bed.blocked.conceded/2   [117 chars]
    en  You are right and it is four difficult conversations, and I have been finding gardening to do instead of having them.
    >>  ............................................
    pt  Você tem razão e são quatro conversas difíceis, e eu venho arranjando jardinagem para fazer em vez de tê-las.
    >>  ............................................
  dialogue.conversations.scene.work.florist.failed_bed.blocked.conceded/3   [130 chars]
    en  I will go round this week. And I will offer them what I do have rather than only what I have lost, because one of those is a plan.
    >>  ............................................
    pt  Vou passar nas casas esta semana. E vou oferecer o que eu tenho em vez de só o que perdi, porque uma dessas coisas é um plano.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the beds."

*stance family `exit` · tone `plain` · answers the beat(s) `work.florist.failed_bed.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.failed_bed.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.florist.failed_bed.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.florist.failed_bed.blocked.respond.leave   [34 chars]
    en  I'll let you get back to the beds.
    >>  ............................................
    pt  Vou deixar você voltar aos canteiros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the beds."
       spoken on: conversations.scene.work.florist.failed_bed.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.left`: the villager accepts. Subject `work.florist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.florist.failed_bed.succeeded.respond / leave; conversations.scene.work.florist.followup / leave; conversations.scene.work.florist.funeral_order.active.respond / leave; conversations.scene.work.florist.funeral_order.succeeded.respond / leave; conversations.scene.work.florist.stubborn_variety.active.respond / leave; conversations.scene.work.florist.stubborn_variety.succeeded.respond / leave; conversations.topic.work.florist.craft.respond / leave; conversations.topic.work.florist.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.florist.leave/1   [56 chars]
    en  The bees have opinions about my timekeeping. Off you go.
    >>  ............................................
    pt  As abelhas têm opinião sobre minha pontualidade. Pode ir.
    >>  ............................................
  dialogue.conversations.work.prof.florist.leave/2   [52 chars]
    en  Mind the row on the left, %1$s, it's just been sown.
    >>  ............................................
    pt  Cuidado com a fileira da esquerda, %1$s, foi semeada agora.
    >>  ............................................
```

---


## `conversations.scene.work.florist.failed_bed.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.florist.failed_bed.succeeded` — e.g. "%2$s is planted and coming through. Not what it was, and coming through, and I will take that."


```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.failed_bed.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.florist.failed_bed.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.florist.failed_bed.succeeded.respond   [16 chars]
    en  The beds, since.
    >>  ............................................
    pt  Os canteiros, depois disso.
    >>  ............................................
```


### Button `ask_about_the_conversations` — "How did the four conversations go?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.florist.failed_bed.succeeded` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.florist.failed_bed.succeeded.ask_about_the_conversations` — accepted phrasings: "how did the four conversations go"; "how did the four conversations go"; "what did the customers say"
  - the message must contain one of: `conversations`, `customers`
  - scored words: `conversations`(1.8), `customers`(1.8), `four`(0.8), `say`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.failed_bed.succeeded.respond.ask_about_the_conversations
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.florist.failed_bed.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.florist.failed_bed.succeeded.respond.ask_about_the_conversations   [34 chars]
    en  How did the four conversations go?
    >>  ............................................
    pt  Como foram as quatro conversas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.florist.the_beds`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.florist.failed_bed"}
- Then opens: `conversations.scene.work.florist.followup`
- …where the player's next choices will be: "What's the hardest part of a bloom that fails?" | "I'll leave you to the beds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.failed_bed.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "How did the four conversations go?"
       spoken on: conversations.scene.work.florist.failed_bed.succeeded.respond, button `ask_about_the_conversations`
       leaves the player on: conversations.scene.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.failed_bed.succeeded.answered`: the villager explains. Subject `work.florist.the_beds`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.florist.failed_bed.succeeded.answered/1   [104 chars]
    en  Better than the eleven days of dreading them. That is always the ratio and I have never once learned it.
    >>  ............................................
    pt  Melhores que os onze dias temendo elas. É sempre essa a proporção e eu nunca aprendo.
    >>  ............................................
  dialogue.conversations.scene.work.florist.failed_bed.succeeded.answered/2   [118 chars]
    en  One woman cried, which I had not prepared for, and it turned out to be about her mother rather than about the flowers.
    >>  ............................................
    pt  Uma mulher chorou, coisa para a qual eu não estava preparada, e no fim era sobre a mãe dela e não sobre as flores.
    >>  ............................................
  dialogue.conversations.scene.work.florist.failed_bed.succeeded.answered/3   [105 chars]
    en  They were kinder to me than I was going to be to myself, and I walked home feeling foolish in a good way.
    >>  ............................................
    pt  Foram mais gentis comigo do que eu ia ser comigo mesma, e voltei para casa me sentindo tola de um jeito bom.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the beds."

*stance family `exit` · tone `plain` · answers the beat(s) `work.florist.failed_bed.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.failed_bed.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.florist.failed_bed.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.florist.failed_bed.succeeded.respond.leave   [34 chars]
    en  I'll let you get back to the beds.
    >>  ............................................
    pt  Vou deixar você voltar aos canteiros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the beds."
       spoken on: conversations.scene.work.florist.failed_bed.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.left`: the villager accepts. Subject `work.florist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.florist.failed_bed.blocked.respond / leave; conversations.scene.work.florist.followup / leave; conversations.scene.work.florist.funeral_order.active.respond / leave; conversations.scene.work.florist.funeral_order.succeeded.respond / leave; conversations.scene.work.florist.stubborn_variety.active.respond / leave; conversations.scene.work.florist.stubborn_variety.succeeded.respond / leave; conversations.topic.work.florist.craft.respond / leave; conversations.topic.work.florist.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.florist.failed_bed.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.florist.followup`

**Reached from 10 route(s):** `conversations.scene.work.florist.failed_bed.blocked.respond` / `ask_if_recoverable`; `conversations.scene.work.florist.failed_bed.blocked.respond` / `offer_bonemeal`; `conversations.scene.work.florist.failed_bed.blocked.respond` / `advise_telling_the_orders`; `conversations.scene.work.florist.failed_bed.succeeded.respond` / `ask_about_the_conversations`; `conversations.scene.work.florist.funeral_order.active.respond` / `ask_how_she_decides`; `conversations.scene.work.florist.funeral_order.active.respond` / `back_the_small_one`; `conversations.scene.work.florist.funeral_order.succeeded.respond` / `note_the_asking`; `conversations.scene.work.florist.stubborn_variety.active.respond` / `ask_why_persist`; `conversations.scene.work.florist.stubborn_variety.active.respond` / `encourage_the_attempt`; `conversations.scene.work.florist.stubborn_variety.succeeded.respond` / `celebrate_it`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.florist.failed_bed.blocked.accepted` — e.g. "Then %2$s gets fed this week and I can put in the autumn stock instead of writing apologies."
- `conversations.scene.work.florist.failed_bed.blocked.conceded` — e.g. "Two of those orders are weddings. Told now, they can plan; told in the autumn, I have ruined a day that cannot be moved."
- `conversations.scene.work.florist.failed_bed.blocked.explained` — e.g. "The soil, yes, with a season of feeding. %2$s itself is a year gone, and a year is a year whatever you feed it."
- `conversations.scene.work.florist.failed_bed.succeeded.answered` — e.g. "Better than the eleven days of dreading them. That is always the ratio and I have never once learned it."
- `conversations.scene.work.florist.funeral_order.active.explained` — e.g. "I ask what was in her garden. Not what they want — what she grew. It is a different question and people can always answer it."
- `conversations.scene.work.florist.funeral_order.active.resolved` — e.g. "Then I will charge them for the small one, which is less, and spend twice as long on it, which they will never know."
- `conversations.scene.work.florist.funeral_order.succeeded.acknowledged` — e.g. "It is the only part of the trade that cannot be taught with a trowel, and it is the part I get thanked for."
- `conversations.scene.work.florist.stubborn_variety.active.accepted` — e.g. "Four more, then. I will try shade this time, which everybody tells me is wrong, and everybody has been telling me that for four years."
- `conversations.scene.work.florist.stubborn_variety.active.explained` — e.g. "Because I have learned something new about soil every year I have failed, and none of that would have happened growing what works."
- `conversations.scene.work.florist.stubborn_variety.succeeded.acknowledged` — e.g. "I sat down next to it for an hour, which is exactly what I do at the quarry and is apparently what I do when I am happy."


```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.florist.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.florist.followup   [4 chars]
    en  And?
    >>  ............................................
    pt  E então?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of a bloom that fails?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.florist.*` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.florist.followup.ask_more` — accepted phrasings: "whats the hardest part of a bloom that fails"; "what is the hardest part of a bloom that fails"; "hardest thing about a failed bloom"
  - the message must contain one of: `hardest`, `bloom`
  - scored words: `hardest`(1.8), `bloom`(1.8), `whats`(0.8), `part`(0.8), `fails`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.florist.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.florist.followup.ask_more   [46 chars]
    en  What's the hardest part of a bloom that fails?
    >>  ............................................
    pt  Qual é a parte mais difícil de uma flor que não vinga?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.florist.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.florist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which flower is hardest to grow?" | "Good growing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of a bloom that fails?"
       spoken on: conversations.scene.work.florist.followup, button `ask_more`
       leaves the player on: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.hard`: the villager explains. Subject `work.florist.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.florist.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.florist.hard/1   [88 chars]
    en  A funeral for someone I liked. You have to keep your hands steady and your face working.
    >>  ............................................
    pt  Um funeral de alguém de quem eu gostava. Você tem que manter as mãos firmes e o rosto funcionando.
    >>  ............................................
  dialogue.conversations.work.prof.florist.hard/2   [79 chars]
    en  The apology ones, %1$s. People tell you what they did while you tie the ribbon.
    >>  ............................................
    pt  Os de pedido de desculpas, %1$s. As pessoas contam o que fizeram enquanto você amarra a fita.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the beds."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.florist.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.florist.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.florist.followup.leave   [27 chars]
    en  I'll leave you to the beds.
    >>  ............................................
    pt  Vou deixar você com os canteiros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the beds."
       spoken on: conversations.scene.work.florist.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.left`: the villager accepts. Subject `work.florist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.florist.failed_bed.blocked.respond / leave; conversations.scene.work.florist.failed_bed.succeeded.respond / leave; conversations.scene.work.florist.funeral_order.active.respond / leave; conversations.scene.work.florist.funeral_order.succeeded.respond / leave; conversations.scene.work.florist.stubborn_variety.active.respond / leave; conversations.scene.work.florist.stubborn_variety.succeeded.respond / leave; conversations.topic.work.florist.craft.respond / leave; conversations.topic.work.florist.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.florist.failed_bed.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.florist.funeral_order.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.florist.funeral_order.active` — e.g. "I have %2$s on Thursday and the family cannot tell me what they want, which is entirely reasonable and leaves me guessing."


```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.funeral_order.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.florist.funeral_order.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.florist.funeral_order.active.respond   [10 chars]
    en  The order.
    >>  ............................................
    pt  A encomenda.
    >>  ............................................
```


### Button `ask_how_she_decides` — "How do you decide, then?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.florist.funeral_order.active` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.florist.funeral_order.active.ask_how_she_decides` — accepted phrasings: "how do you decide then"; "how do you decide then"; "what do you go on when they cannot say"
  - the message must contain one of: `decide`, `go`
  - scored words: `decide`(1.8), `go`(1.8), `when`(0.8), `cannot`(0.8), `say`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.funeral_order.active.respond.ask_how_she_decides
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.florist.funeral_order.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.florist.funeral_order.active.respond.ask_how_she_decides   [24 chars]
    en  How do you decide, then?
    >>  ............................................
    pt  Então como você decide?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3  _(recorded under topic `work.florist.occasions`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.florist.funeral_order"}
- Then opens: `conversations.scene.work.florist.followup`
- …where the player's next choices will be: "What's the hardest part of a bloom that fails?" | "I'll leave you to the beds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.funeral_order.active.explained
WHO    VILLAGER — what the player reads after pressing "How do you decide, then?"
       spoken on: conversations.scene.work.florist.funeral_order.active.respond, button `ask_how_she_decides`
       leaves the player on: conversations.scene.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.funeral_order.active.explained`: the villager explains. Subject `work.florist.occasions`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.florist.funeral_order.active.explained/1   [125 chars]
    en  I ask what was in her garden. Not what they want — what she grew. It is a different question and people can always answer it.
    >>  ............................................
    pt  Pergunto o que havia no jardim dela. Não o que querem — o que ela plantava. É outra pergunta e todo mundo consegue responder.
    >>  ............................................
  dialogue.conversations.scene.work.florist.funeral_order.active.explained/2   [107 chars]
    en  I make two and take both. They point at one within a second and then apologise for being unhelpful earlier.
    >>  ............................................
    pt  Faço dois e levo os dois. Apontam para um em um segundo e depois pedem desculpa por terem sido pouco úteis antes.
    >>  ............................................
  dialogue.conversations.scene.work.florist.funeral_order.active.explained/3   [110 chars]
    en  Small and correct rather than large and safe. A large arrangement is a way of not deciding, and they can tell.
    >>  ............................................
    pt  Pequeno e certo em vez de grande e seguro. Um arranjo grande é um jeito de não decidir, e as pessoas percebem.
    >>  ............................................
```


### Button `back_the_small_one` — "Make the small correct one."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.florist.funeral_order.active` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.florist.funeral_order.active.back_the_small_one` — accepted phrasings: "make the small correct one"; "make the small correct one"; "go small and specific rather than large"
  - the message must contain one of: `small`, `specific`
  - scored words: `small`(1.8), `specific`(1.8), `make`(0.8), `correct`(0.8), `one`(0.8), `rather`(0.8), `than`(0.8), `large`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.funeral_order.active.respond.back_the_small_one
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.florist.funeral_order.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.florist.funeral_order.active.respond.back_the_small_one   [27 chars]
    en  Make the small correct one.
    >>  ............................................
    pt  Faça o pequeno e certo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.florist.order.backed`, budget `standard`, replay policy `once`
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.florist.occasions`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.florist.funeral_order"}
- Then opens: `conversations.scene.work.florist.followup`
- …where the player's next choices will be: "What's the hardest part of a bloom that fails?" | "I'll leave you to the beds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.funeral_order.active.resolved
WHO    VILLAGER — what the player reads after pressing "Make the small correct one."
       spoken on: conversations.scene.work.florist.funeral_order.active.respond, button `back_the_small_one`
       leaves the player on: conversations.scene.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.funeral_order.active.resolved`: the villager accepts. Subject `work.florist.occasions`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.florist.funeral_order.active.resolved/1   [116 chars]
    en  Then I will charge them for the small one, which is less, and spend twice as long on it, which they will never know.
    >>  ............................................
    pt  Então vou cobrar pelo pequeno, que é menos, e passar o dobro do tempo nele, coisa que eles nunca vão saber.
    >>  ............................................
  dialogue.conversations.scene.work.florist.funeral_order.active.resolved/2   [137 chars]
    en  Yes. I will go and ask about her garden tomorrow morning, which is a visit rather than a transaction, and that is the right shape for it.
    >>  ............................................
    pt  Sim. Vou perguntar sobre o jardim dela amanhã de manhã, o que é uma visita e não uma transação, e é esse o formato certo.
    >>  ............................................
  dialogue.conversations.scene.work.florist.funeral_order.active.resolved/3   [129 chars]
    en  That is what I would want. It has taken me years to trust that what I would want is a reasonable guide to what other people want.
    >>  ............................................
    pt  É o que eu ia querer. Levei anos para confiar que o que eu quero é um guia razoável do que os outros querem.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the beds."

*stance family `exit` · tone `plain` · answers the beat(s) `work.florist.funeral_order.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.funeral_order.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.florist.funeral_order.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.florist.funeral_order.active.respond.leave   [34 chars]
    en  I'll let you get back to the beds.
    >>  ............................................
    pt  Vou deixar você voltar aos canteiros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the beds."
       spoken on: conversations.scene.work.florist.funeral_order.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.left`: the villager accepts. Subject `work.florist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.florist.failed_bed.blocked.respond / leave; conversations.scene.work.florist.failed_bed.succeeded.respond / leave; conversations.scene.work.florist.followup / leave; conversations.scene.work.florist.funeral_order.succeeded.respond / leave; conversations.scene.work.florist.stubborn_variety.active.respond / leave; conversations.scene.work.florist.stubborn_variety.succeeded.respond / leave; conversations.topic.work.florist.craft.respond / leave; conversations.topic.work.florist.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.florist.failed_bed.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.florist.funeral_order.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.florist.funeral_order.succeeded` — e.g. "%2$s went well. I asked about her garden and made what was in it, and her son held it the whole way."


```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.funeral_order.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.florist.funeral_order.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.florist.funeral_order.succeeded.respond   [11 chars]
    en  That order.
    >>  ............................................
    pt  Aquela encomenda.
    >>  ............................................
```


### Button `note_the_asking` — "Asking about her garden was the whole thing."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.florist.funeral_order.succeeded` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.florist.funeral_order.succeeded.note_the_asking` — accepted phrasings: "asking about her garden was the whole thing"; "asking about her garden was the whole thing"; "the question you asked was the craft"
  - the message must contain one of: `garden`, `question`, `asking`
  - scored words: `garden`(1.8), `question`(1.8), `asking`(1.8), `her`(0.8), `whole`(0.8), `thing`(0.8), `asked`(0.8), `craft`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.funeral_order.succeeded.respond.note_the_asking
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.florist.funeral_order.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.florist.funeral_order.succeeded.respond.note_the_asking   [44 chars]
    en  Asking about her garden was the whole thing.
    >>  ............................................
    pt  Perguntar do jardim dela foi tudo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +3  _(recorded under topic `work.florist.occasions`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.florist.funeral_order"}
- Then opens: `conversations.scene.work.florist.followup`
- …where the player's next choices will be: "What's the hardest part of a bloom that fails?" | "I'll leave you to the beds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.funeral_order.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Asking about her garden was the whole thing."
       spoken on: conversations.scene.work.florist.funeral_order.succeeded.respond, button `note_the_asking`
       leaves the player on: conversations.scene.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.funeral_order.succeeded.acknowledged`: the villager accepts. Subject `work.florist.occasions`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.florist.funeral_order.succeeded.acknowledged/1   [107 chars]
    en  It is the only part of the trade that cannot be taught with a trowel, and it is the part I get thanked for.
    >>  ............................................
    pt  É a única parte do ofício que não se ensina com uma pá, e é a parte pela qual me agradecem.
    >>  ............................................
  dialogue.conversations.scene.work.florist.funeral_order.succeeded.acknowledged/2   [125 chars]
    en  Thank you. I stole the question from the woman who taught me, who stole it from her mother, and I have told nobody until now.
    >>  ............................................
    pt  Obrigada. Roubei a pergunta da mulher que me ensinou, que roubou da mãe dela, e eu não tinha contado a ninguém até agora.
    >>  ............................................
  dialogue.conversations.scene.work.florist.funeral_order.succeeded.acknowledged/3   [127 chars]
    en  People think flowers are the product. The product is that somebody was paid attention to in a week when nobody could manage it.
    >>  ............................................
    pt  As pessoas acham que a flor é o produto. O produto é que alguém recebeu atenção numa semana em que ninguém conseguia dar.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the beds."

*stance family `exit` · tone `plain` · answers the beat(s) `work.florist.funeral_order.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.funeral_order.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.florist.funeral_order.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.florist.funeral_order.succeeded.respond.leave   [34 chars]
    en  I'll let you get back to the beds.
    >>  ............................................
    pt  Vou deixar você voltar aos canteiros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the beds."
       spoken on: conversations.scene.work.florist.funeral_order.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.left`: the villager accepts. Subject `work.florist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.florist.failed_bed.blocked.respond / leave; conversations.scene.work.florist.failed_bed.succeeded.respond / leave; conversations.scene.work.florist.followup / leave; conversations.scene.work.florist.funeral_order.active.respond / leave; conversations.scene.work.florist.stubborn_variety.active.respond / leave; conversations.scene.work.florist.stubborn_variety.succeeded.respond / leave; conversations.topic.work.florist.craft.respond / leave; conversations.topic.work.florist.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.florist.failed_bed.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.florist.stubborn_variety.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.florist.stubborn_variety.active` — e.g. "I have been trying to grow %2$s for four years and I have got as far as leaves twice."


```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.stubborn_variety.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.florist.stubborn_variety.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.florist.stubborn_variety.active.respond   [24 chars]
    en  The one that won't take.
    >>  ............................................
    pt  A que não pega.
    >>  ............................................
```


### Button `ask_why_persist` — "Why keep at it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.florist.stubborn_variety.active` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.florist.stubborn_variety.active.ask_why_persist` — accepted phrasings: "why keep at it"; "why keep at it"; "what makes you keep trying with that"
  - the message must contain one of: `keep`, `trying`
  - scored words: `keep`(1.8), `trying`(1.8), `why`(0.8), `makes`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.stubborn_variety.active.respond.ask_why_persist
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.florist.stubborn_variety.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.florist.stubborn_variety.active.respond.ask_why_persist   [15 chars]
    en  Why keep at it?
    >>  ............................................
    pt  Por que insistir?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.florist.a_plant_that_wont_take`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.florist.stubborn_variety"}
- Then opens: `conversations.scene.work.florist.followup`
- …where the player's next choices will be: "What's the hardest part of a bloom that fails?" | "I'll leave you to the beds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.stubborn_variety.active.explained
WHO    VILLAGER — what the player reads after pressing "Why keep at it?"
       spoken on: conversations.scene.work.florist.stubborn_variety.active.respond, button `ask_why_persist`
       leaves the player on: conversations.scene.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.stubborn_variety.active.explained`: the villager explains. Subject `work.florist.a_plant_that_wont_take`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.florist.stubborn_variety.active.explained/1   [130 chars]
    en  Because I have learned something new about soil every year I have failed, and none of that would have happened growing what works.
    >>  ............................................
    pt  Porque aprendi algo novo sobre solo em cada ano em que fracassei, e nada disso teria acontecido cultivando o que dá certo.
    >>  ............................................
  dialogue.conversations.scene.work.florist.stubborn_variety.active.explained/2   [119 chars]
    en  %2$s grew in my grandmother's yard, forty miles north, in ground I have never seen. I would like to know what she knew.
    >>  ............................................
    pt  %2$s crescia no quintal da minha avó, a sessenta quilômetros ao norte, num terreno que eu nunca vi. Eu gostaria de saber o que ela sabia.
    >>  ............................................
  dialogue.conversations.scene.work.florist.stubborn_variety.active.explained/3   [111 chars]
    en  Everything else in the garden is for somebody. This is the only bed where I am allowed to be simply interested.
    >>  ............................................
    pt  Todo o resto do jardim é para alguém. Este é o único canteiro em que eu tenho permissão de estar simplesmente interessada.
    >>  ............................................
```


### Button `encourage_the_attempt` — "Give it another four years."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.florist.stubborn_variety.active` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.florist.stubborn_variety.active.encourage_the_attempt` — accepted phrasings: "give it another four years"; "give it another four years"; "keep the bed going another few seasons"
  - the message must contain one of: `another`, `seasons`, `years`
  - scored words: `another`(1.8), `seasons`(1.8), `years`(1.8), `give`(0.8), `four`(0.8), `bed`(0.8), `going`(0.8), `few`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.stubborn_variety.active.respond.encourage_the_attempt
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.florist.stubborn_variety.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.florist.stubborn_variety.active.respond.encourage_the_attempt   [27 chars]
    en  Give it another four years.
    >>  ............................................
    pt  Dê mais quatro anos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +2  _(recorded under topic `work.florist.a_plant_that_wont_take`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.florist.stubborn_variety"}
- Then opens: `conversations.scene.work.florist.followup`
- …where the player's next choices will be: "What's the hardest part of a bloom that fails?" | "I'll leave you to the beds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.stubborn_variety.active.accepted
WHO    VILLAGER — what the player reads after pressing "Give it another four years."
       spoken on: conversations.scene.work.florist.stubborn_variety.active.respond, button `encourage_the_attempt`
       leaves the player on: conversations.scene.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.stubborn_variety.active.accepted`: the villager accepts. Subject `work.florist.a_plant_that_wont_take`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.florist.stubborn_variety.active.accepted/1   [134 chars]
    en  Four more, then. I will try shade this time, which everybody tells me is wrong, and everybody has been telling me that for four years.
    >>  ............................................
    pt  Mais quatro, então. Vou tentar sombra desta vez, coisa que todo mundo diz que é errado, e todo mundo vem dizendo isso há quatro anos.
    >>  ............................................
  dialogue.conversations.scene.work.florist.stubborn_variety.active.accepted/2   [127 chars]
    en  Thank you. People keep suggesting I use the ground for something that sells, and they are right, and I am going to ignore them.
    >>  ............................................
    pt  Obrigada. Vivem sugerindo que eu use o terreno para algo que venda, e têm razão, e eu vou ignorar.
    >>  ............................................
  dialogue.conversations.scene.work.florist.stubborn_variety.active.accepted/3   [126 chars]
    en  That is permission I did not know I was waiting for. It is one bed out of eleven and I have felt guilty about it every spring.
    >>  ............................................
    pt  É uma permissão que eu não sabia que estava esperando. É um canteiro em onze e eu me sinto culpada por ele toda primavera.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the beds."

*stance family `exit` · tone `plain` · answers the beat(s) `work.florist.stubborn_variety.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.stubborn_variety.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.florist.stubborn_variety.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.florist.stubborn_variety.active.respond.leave   [34 chars]
    en  I'll let you get back to the beds.
    >>  ............................................
    pt  Vou deixar você voltar aos canteiros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the beds."
       spoken on: conversations.scene.work.florist.stubborn_variety.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.left`: the villager accepts. Subject `work.florist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.florist.failed_bed.blocked.respond / leave; conversations.scene.work.florist.failed_bed.succeeded.respond / leave; conversations.scene.work.florist.followup / leave; conversations.scene.work.florist.funeral_order.active.respond / leave; conversations.scene.work.florist.funeral_order.succeeded.respond / leave; conversations.scene.work.florist.stubborn_variety.succeeded.respond / leave; conversations.topic.work.florist.craft.respond / leave; conversations.topic.work.florist.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.florist.failed_bed.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.florist.stubborn_variety.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.florist.stubborn_variety.succeeded` — e.g. "%2$s flowered. Five years, and it was shade all along, and it flowered in the corner I had almost given up on."


```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.stubborn_variety.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.florist.stubborn_variety.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.florist.stubborn_variety.succeeded.respond   [11 chars]
    en  That plant.
    >>  ............................................
    pt  Aquela planta.
    >>  ............................................
```


### Button `celebrate_it` — "Five years is worth celebrating."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.florist.stubborn_variety.succeeded` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.florist.stubborn_variety.succeeded.celebrate_it` — accepted phrasings: "five years is worth celebrating"; "five years is worth celebrating"; "that deserves celebrating"
  - the message must contain one of: `celebrating`, `deserves`
  - scored words: `celebrating`(1.8), `deserves`(1.8), `five`(0.8), `years`(0.8), `worth`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.stubborn_variety.succeeded.respond.celebrate_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.florist.stubborn_variety.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.florist.stubborn_variety.succeeded.respond.celebrate_it   [32 chars]
    en  Five years is worth celebrating.
    >>  ............................................
    pt  Cinco anos merecem comemoração.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +4, respect +2  _(recorded under topic `work.florist.a_plant_that_wont_take`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.florist.stubborn_variety"}
- Then opens: `conversations.scene.work.florist.followup`
- …where the player's next choices will be: "What's the hardest part of a bloom that fails?" | "I'll leave you to the beds."

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.stubborn_variety.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Five years is worth celebrating."
       spoken on: conversations.scene.work.florist.stubborn_variety.succeeded.respond, button `celebrate_it`
       leaves the player on: conversations.scene.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.stubborn_variety.succeeded.acknowledged`: the villager celebrates. Subject `work.florist.a_plant_that_wont_take`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.florist.stubborn_variety.succeeded.acknowledged/1   [120 chars]
    en  I sat down next to it for an hour, which is exactly what I do at the quarry and is apparently what I do when I am happy.
    >>  ............................................
    pt  Sentei ao lado por uma hora, que é exatamente o que eu faço na pedreira e, aparentemente, o que eu faço quando estou feliz.
    >>  ............................................
  dialogue.conversations.scene.work.florist.stubborn_variety.succeeded.acknowledged/2   [106 chars]
    en  Thank you. Nobody in this village will understand why it matters and I have decided I do not need them to.
    >>  ............................................
    pt  Obrigada. Ninguém nesta vila vai entender por que importa e eu decidi que não preciso que entendam.
    >>  ............................................
  dialogue.conversations.scene.work.florist.stubborn_variety.succeeded.acknowledged/3   [137 chars]
    en  I am going to send one north, to the yard it came from, which will take a month and will probably die on the way. I am sending it anyway.
    >>  ............................................
    pt  Vou mandar uma para o norte, para o quintal de onde veio, o que vai levar um mês e provavelmente morrer no caminho. Vou mandar mesmo assim.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the beds."

*stance family `exit` · tone `plain` · answers the beat(s) `work.florist.stubborn_variety.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.florist.stubborn_variety.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.florist.stubborn_variety.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.florist.stubborn_variety.succeeded.respond.leave   [34 chars]
    en  I'll let you get back to the beds.
    >>  ............................................
    pt  Vou deixar você voltar aos canteiros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the beds."
       spoken on: conversations.scene.work.florist.stubborn_variety.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.left`: the villager accepts. Subject `work.florist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.florist.failed_bed.blocked.respond / leave; conversations.scene.work.florist.failed_bed.succeeded.respond / leave; conversations.scene.work.florist.followup / leave; conversations.scene.work.florist.funeral_order.active.respond / leave; conversations.scene.work.florist.funeral_order.succeeded.respond / leave; conversations.scene.work.florist.stubborn_variety.active.respond / leave; conversations.topic.work.florist.craft.respond / leave; conversations.topic.work.florist.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.florist.failed_bed.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.florist.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.florist.craft` — e.g. "Everyone thinks it's arranging. It's growing, and drainage, and knowing which bed is cold in May."


```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.florist.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.florist.craft.respond   [24 chars]
    en  That's the actual trade.
    >>  ............................................
    pt  É esse o ofício de verdade.
    >>  ............................................
```


### Button `ask_book` — "Why keep a book like that?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.florist.craft` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.florist.craft.ask_book` — accepted phrasings: "why keep a book like that"
  - the message must contain one of: `book`, `mothers`, `remember`
  - scored words: `book`(1.5), `mothers`(1.2), `remember`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.craft.respond.ask_book
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.craft.respond.ask_book   [26 chars]
    en  Why keep a book like that?
    >>  ............................................
    pt  Por que guardar um caderno assim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.florist.craft.ask_book`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.florist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which flower is hardest to grow?" | "Good growing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.craft.ask_book
WHO    VILLAGER — what the player reads after pressing "Why keep a book like that?"
       spoken on: conversations.topic.work.florist.craft.respond, button `ask_book`
       leaves the player on: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.craft.ask_book`: the villager explains. Subject `work.florist.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.florist.craft.ask_book/1   [85 chars]
    en  Because when somebody dies their children can't remember and they'd give anything to.
    >>  ............................................
    pt  Porque quando alguém morre os filhos não lembram e dariam tudo pra lembrar.
    >>  ............................................
  dialogue.conversations.work.prof.florist.craft.ask_book/2   [76 chars]
    en  Because a burial arrangement should be a message and not a decoration, %1$s.
    >>  ............................................
    pt  Porque um arranjo de enterro devia ser recado e não enfeite, %1$s.
    >>  ............................................
```


### Button `admire` — "That book is doing more than flowers."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.florist.craft` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.florist.craft.admire` — accepted phrasings: "that book is doing more than flowers"
  - the message must contain one of: `book`, `beyond`
  - scored words: `book`(1.2), `more`(0.8), `beyond`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.craft.respond.admire   [37 chars]
    en  That book is doing more than flowers.
    >>  ............................................
    pt  Esse caderno faz mais que flores.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.florist.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.florist.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.florist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which flower is hardest to grow?" | "Good growing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.craft.admire
WHO    VILLAGER — what the player reads after pressing "That book is doing more than flowers."
       spoken on: conversations.topic.work.florist.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.craft.admire`: the villager accepts. Subject `work.florist.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.florist.craft.admire/1   [81 chars]
    en  ...It's the part I'd save from a fire, and I've never told anyone that until now.
    >>  ............................................
    pt  ...É a parte que eu salvaria de um incêndio, e eu nunca contei isso a ninguém até agora.
    >>  ............................................
  dialogue.conversations.work.prof.florist.craft.admire/2   [90 chars]
    en  It's fifty-one entries and it will outlast the beds, %1$s. I've thought about who gets it.
    >>  ............................................
    pt  São cinquenta e uma entradas e vai durar mais que os canteiros, %1$s. Já pensei em quem herda.
    >>  ............................................
```


### Button `ask_beds` — "Which bed is cold in May?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.florist.craft` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.florist.craft.ask_beds` — accepted phrasings: "which bed is cold in may"
  - the message must contain one of: `bed`, `cold`
  - scored words: `bed`(1.5), `cold`(1.2), `which`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.craft.respond.ask_beds
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.craft.respond.ask_beds   [25 chars]
    en  Which bed is cold in May?
    >>  ............................................
    pt  Qual canteiro é frio em maio?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.florist.craft.ask_beds`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.florist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which flower is hardest to grow?" | "Good growing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.craft.ask_beds
WHO    VILLAGER — what the player reads after pressing "Which bed is cold in May?"
       spoken on: conversations.topic.work.florist.craft.respond, button `ask_beds`
       leaves the player on: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.craft.ask_beds`: the villager explains. Subject `work.florist.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.florist.craft.ask_beds/1   [91 chars]
    en  The one by the north wall that looks like the best of them. It has fooled every apprentice.
    >>  ............................................
    pt  O do muro norte, que parece o melhor de todos. Já enganou todo aprendiz.
    >>  ............................................
  dialogue.conversations.work.prof.florist.craft.ask_beds/2   [82 chars]
    en  The long one. It's a fortnight behind and I plan the whole spring around it, %1$s.
    >>  ............................................
    pt  O comprido. Está quinze dias atrasado e eu planejo a primavera inteira em volta dele, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the beds."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.florist.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.craft.respond.leave   [34 chars]
    en  I'll let you get back to the beds.
    >>  ............................................
    pt  Vou deixar você voltar aos canteiros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the beds."
       spoken on: conversations.topic.work.florist.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.left`: the villager accepts. Subject `work.florist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.florist.failed_bed.blocked.respond / leave; conversations.scene.work.florist.failed_bed.succeeded.respond / leave; conversations.scene.work.florist.followup / leave; conversations.scene.work.florist.funeral_order.active.respond / leave; conversations.scene.work.florist.funeral_order.succeeded.respond / leave; conversations.scene.work.florist.stubborn_variety.active.respond / leave; conversations.scene.work.florist.stubborn_variety.succeeded.respond / leave; conversations.topic.work.florist.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.florist.failed_bed.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.florist.followup`

**Reached from 20 route(s):** `conversations.scene.work.florist.followup` / `ask_more`; `conversations.topic.work.florist.craft.respond` / `ask_book`; `conversations.topic.work.florist.craft.respond` / `admire`; `conversations.topic.work.florist.craft.respond` / `ask_beds`; `conversations.topic.work.florist.future.respond` / `ask_glass`; `conversations.topic.work.florist.future.respond` / `encourage`; `conversations.topic.work.florist.future.respond` / `ask_successor`; `conversations.topic.work.florist.respond` / `ask_hard`; `conversations.topic.work.florist.respond` / `value`; `conversations.topic.work.florist.respond` / `challenge`; `conversations.topic.work.florist.respond` / `challenge`; `conversations.topic.work.florist.risk.respond` / `ask_frost` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.florist.challenge.landed` — e.g. "It is decoration. Decoration is how people say things they can't say, %1$s."
- `conversations.work.prof.florist.challenge.stung` — e.g. "...Come and watch me tie a funeral spray and say 'decoration' afterwards."
- `conversations.work.prof.florist.craft.admire` — e.g. "...It's the part I'd save from a fire, and I've never told anyone that until now."
- `conversations.work.prof.florist.craft.ask_beds` — e.g. "The one by the north wall that looks like the best of them. It has fooled every apprentice."
- `conversations.work.prof.florist.craft.ask_book` — e.g. "Because when somebody dies their children can't remember and they'd give anything to."
- `conversations.work.prof.florist.future.ask_glass` — e.g. "More than nineteen years of arrangements have earned. I've done the sum and then done it again."
- `conversations.work.prof.florist.future.ask_successor` — e.g. "Somebody who'd add to it. That's the only requirement and it rules out almost everyone."
- `conversations.work.prof.florist.future.encourage` — e.g. "...The families in the book. Fifty-one of them. I had genuinely never thought of that."
- `conversations.work.prof.florist.hard` — e.g. "A funeral for someone I liked. You have to keep your hands steady and your face working."
- `conversations.work.prof.florist.risk.ask_fifty` — e.g. "Fifty-one. The book has fifty-one entries. Those are the same number and I check that they stay the same."
- `conversations.work.prof.florist.risk.ask_frost` — e.g. "Cloth, straw and standing in the dark deciding which beds to save. That's the whole defence."
- `conversations.work.prof.florist.risk.sympathise` — e.g. "...It is. You grieve a little for everybody and you're not entitled to any of it."
- `conversations.work.prof.florist.task.ask_both` — e.g. "Separately, and with the door shut between them. It matters more than you'd think."
- `conversations.work.prof.florist.task.ask_ten` — e.g. "The stem stops drinking. You can't see it happen and you can see it three days later."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.florist.followup   [34 chars]
    en  That's the garden and its errands.
    >>  ............................................
    pt  É o jardim e os recados dele.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.florist.challenge.landed`, `work.florist.challenge.stung`, `work.florist.craft.admire`, `work.florist.craft.ask_beds`, `work.florist.craft.ask_book`, `work.florist.future.ask_glass`, `work.florist.future.ask_successor`, `work.florist.future.encourage`, `work.florist.hard`, `work.florist.risk.ask_fifty`, `work.florist.risk.ask_frost`, `work.florist.risk.sympathise`, `work.florist.task.ask_both`, `work.florist.task.ask_ten`, `work.florist.task.offer_hands`, `work.florist.value`, `work.florist.village.ask_bare`, `work.florist.village.ask_ends`, `work.florist.village.say_thanks` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.florist.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `counter`, `cry`
  - scored words: `thought`(1.2), `counter`(1.2), `cry`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.florist.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.florist.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.florist.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.florist.thanks`: the villager accepts. Subject `work.florist.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.florist.thanks/1   [74 chars]
    en  Few do. It looks like a pleasant hobby from the other side of the counter.
    >>  ............................................
    pt  Poucos pensam. Do outro lado do balcão parece um passatempo agradável.
    >>  ............................................
  dialogue.conversations.work.prof.florist.thanks/2   [65 chars]
    en  It's the only trade where people cry at you twice a season, %1$s.
    >>  ............................................
    pt  É o único ofício em que as pessoas choram na sua frente duas vezes por estação, %1$s.
    >>  ............................................
```


### Button `ask_more` — "Which flower is hardest to grow?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.florist.challenge.landed`, `work.florist.challenge.stung`, `work.florist.craft.admire`, `work.florist.craft.ask_beds`, `work.florist.craft.ask_book`, `work.florist.future.ask_glass`, `work.florist.future.ask_successor`, `work.florist.future.encourage`, `work.florist.hard`, `work.florist.risk.ask_fifty`, `work.florist.risk.ask_frost`, `work.florist.risk.sympathise`, `work.florist.task.ask_both`, `work.florist.task.ask_ten`, `work.florist.task.offer_hands`, `work.florist.value`, `work.florist.village.ask_bare`, `work.florist.village.ask_ends`, `work.florist.village.say_thanks` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.florist.more` — accepted phrasings: "which flower is hardest to grow"
  - the message must contain one of: `flower`, `grow`
  - scored words: `flower`(1.5), `grow`(1.2), `hardest`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.followup.ask_more   [32 chars]
    en  Which flower is hardest to grow?
    >>  ............................................
    pt  Qual flor é a mais difícil de cultivar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.florist.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.more
WHO    VILLAGER — what the player reads after pressing "Which flower is hardest to grow?"
       spoken on: conversations.topic.work.florist.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.florist.more`: the villager discloses. Subject `work.florist.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.florist.more/1   [81 chars]
    en  The blue one everyone wants for weddings. It hates this soil and knows I need it.
    >>  ............................................
    pt  A azul que todo mundo quer pra casamento. Ela odeia este solo e sabe que eu preciso dela.
    >>  ............................................
  dialogue.conversations.work.prof.florist.more/2   [73 chars]
    en  Anything that flowers late. One frost and a season's patience is compost.
    >>  ............................................
    pt  Qualquer uma que floresce tarde. Uma geada e a paciência de uma estação vira adubo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.florist.more/1
    en  The blue one. I've lost it two springs running and I take that harder than I should.
    >>  ............................................
    pt  A azul. Perdi duas primaveras seguidas e eu levo isso mais a sério do que deveria.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse. Then I'd stop standing in the dark in April choosing which beds to save.
    >>  ............................................
    pt  Uma estufa. Aí eu pararia de ficar no escuro em abril escolhendo quais canteiros salvar.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.florist.more/1
    en  The blue one. It'll take to the north bed eventually. Most things do, given enough years.
    >>  ............................................
    pt  A azul. Vai pegar no canteiro norte uma hora. Quase tudo pega, com anos suficientes.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse, one day. The beds have managed nineteen years without one.
    >>  ............................................
    pt  Uma estufa, um dia. Os canteiros se viraram dezenove anos sem uma.
    >>  ............................................
  confident.dialogue.conversations.work.prof.florist.more/1
    en  The blue one everyone wants for weddings. It hates this soil and it knows I need it.
    >>  ............................................
    pt  A azul que todos querem pra casamento. Ela odeia este solo e sabe que eu preciso dela.
    >>  ............................................
  confident.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse. Then February has colour by choice instead of by luck.
    >>  ............................................
    pt  Uma estufa. Aí fevereiro tem cor por escolha e não por sorte.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.florist.more/1
    en  The blue one everyone wants for weddings. It hates this soil and it knows I need it.
    >>  ............................................
    pt  A azul que todos querem pra casamento. Ela odeia este solo e sabe que eu preciso dela.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse. Then February has colour by choice instead of by luck.
    >>  ............................................
    pt  Uma estufa. Aí fevereiro tem cor por escolha e não por sorte.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.florist.more/1
    en  The blue one, for weddings. Everyone asks for it and I've never yet had to say no.
    >>  ............................................
    pt  A azul, pra casamento. Todos pedem e eu nunca precisei dizer não.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse. Fifty-one families are in my book and I've never once thought of asking them.
    >>  ............................................
    pt  Uma estufa. Cinquenta e uma famílias estão no meu caderno e eu nunca pensei em pedir a elas.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.florist.more/1
    en  The blue one, for weddings. Everyone asks for it and I've never yet had to say no.
    >>  ............................................
    pt  A azul, pra casamento. Todos pedem e eu nunca precisei dizer não.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse. Fifty-one families are in my book and I've never once thought of asking them.
    >>  ............................................
    pt  Uma estufa. Cinquenta e uma famílias estão no meu caderno e eu nunca pensei em pedir a elas.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.florist.more/1
    en  The blue one, for weddings. Everyone asks for it and I've never yet had to say no.
    >>  ............................................
    pt  A azul, pra casamento. Todos pedem e eu nunca precisei dizer não.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse. Fifty-one families are in my book and I've never once thought of asking them.
    >>  ............................................
    pt  Uma estufa. Cinquenta e uma famílias estão no meu caderno e eu nunca pensei em pedir a elas.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.florist.more/1
    en  The blue one. I've lost it two springs running and I take that harder than I should.
    >>  ............................................
    pt  A azul. Perdi duas primaveras seguidas e eu levo isso mais a sério do que deveria.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse. Then I'd stop standing in the dark in April choosing which beds to save.
    >>  ............................................
    pt  Uma estufa. Aí eu pararia de ficar no escuro em abril escolhendo quais canteiros salvar.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.florist.more/1
    en  The blue one everyone wants for weddings. It hates this soil and it knows I need it.
    >>  ............................................
    pt  A azul que todos querem pra casamento. Ela odeia este solo e sabe que eu preciso dela.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse. Then February has colour by choice instead of by luck.
    >>  ............................................
    pt  Uma estufa. Aí fevereiro tem cor por escolha e não por sorte.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.florist.more/1
    en  The blue one everyone wants for weddings. It hates this soil and it knows I need it.
    >>  ............................................
    pt  A azul que todos querem pra casamento. Ela odeia este solo e sabe que eu preciso dela.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse. Then February has colour by choice instead of by luck.
    >>  ............................................
    pt  Uma estufa. Aí fevereiro tem cor por escolha e não por sorte.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.florist.more/1
    en  The blue one. Wrong soil, wrong bed, and it's the one everybody asks for.
    >>  ............................................
    pt  A azul. Solo errado, canteiro errado, e é a que todo mundo pede.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse. The mason would build the frame; it's the glass that has stopped me for nine years.
    >>  ............................................
    pt  Uma estufa. O pedreiro faria a estrutura; é o vidro que me parou por nove anos.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.florist.more/1
    en  The blue one. It'll take to the north bed eventually. Most things do, given enough years.
    >>  ............................................
    pt  A azul. Vai pegar no canteiro norte uma hora. Quase tudo pega, com anos suficientes.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse, one day. The beds have managed nineteen years without one.
    >>  ............................................
    pt  Uma estufa, um dia. Os canteiros se viraram dezenove anos sem uma.
    >>  ............................................
  odd.dialogue.conversations.work.prof.florist.more/1
    en  The blue one. Wrong soil, wrong bed, and it's the one everybody asks for.
    >>  ............................................
    pt  A azul. Solo errado, canteiro errado, e é a que todo mundo pede.
    >>  ............................................
  odd.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse. The mason would build the frame; it's the glass that has stopped me for nine years.
    >>  ............................................
    pt  Uma estufa. O pedreiro faria a estrutura; é o vidro que me parou por nove anos.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.florist.more/1
    en  The blue one. It'll take to the north bed eventually. Most things do, given enough years.
    >>  ............................................
    pt  A azul. Vai pegar no canteiro norte uma hora. Quase tudo pega, com anos suficientes.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse, one day. The beds have managed nineteen years without one.
    >>  ............................................
    pt  Uma estufa, um dia. Os canteiros se viraram dezenove anos sem uma.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.florist.more/1
    en  The blue one! It hates this soil, it knows I need it, and it is doing this on purpose.
    >>  ............................................
    pt  A azul! Ela odeia este solo, sabe que eu preciso dela, e está fazendo de propósito.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse. Then April stops frightening me and February stops being brown.
    >>  ............................................
    pt  Uma estufa. Aí abril para de me assustar e fevereiro para de ser marrom.
    >>  ............................................
  playful.dialogue.conversations.work.prof.florist.more/1
    en  The blue one! It hates this soil, it knows I need it, and it is doing this on purpose.
    >>  ............................................
    pt  A azul! Ela odeia este solo, sabe que eu preciso dela, e está fazendo de propósito.
    >>  ............................................
  playful.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse. Then April stops frightening me and February stops being brown.
    >>  ............................................
    pt  Uma estufa. Aí abril para de me assustar e fevereiro para de ser marrom.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.florist.more/1
    en  The blue one. It'll take to the north bed eventually. Most things do, given enough years.
    >>  ............................................
    pt  A azul. Vai pegar no canteiro norte uma hora. Quase tudo pega, com anos suficientes.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse, one day. The beds have managed nineteen years without one.
    >>  ............................................
    pt  Uma estufa, um dia. Os canteiros se viraram dezenove anos sem uma.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.florist.more/1
    en  The blue one. I've lost it two springs running and I take that harder than I should.
    >>  ............................................
    pt  A azul. Perdi duas primaveras seguidas e eu levo isso mais a sério do que deveria.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse. Then I'd stop standing in the dark in April choosing which beds to save.
    >>  ............................................
    pt  Uma estufa. Aí eu pararia de ficar no escuro em abril escolhendo quais canteiros salvar.
    >>  ............................................
  shy.dialogue.conversations.work.prof.florist.more/1
    en  The blue one. Wrong soil, wrong bed, and it's the one everybody asks for.
    >>  ............................................
    pt  A azul. Solo errado, canteiro errado, e é a que todo mundo pede.
    >>  ............................................
  shy.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse. The mason would build the frame; it's the glass that has stopped me for nine years.
    >>  ............................................
    pt  Uma estufa. O pedreiro faria a estrutura; é o vidro que me parou por nove anos.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.florist.more/1
    en  The blue one! It hates this soil, it knows I need it, and it is doing this on purpose.
    >>  ............................................
    pt  A azul! Ela odeia este solo, sabe que eu preciso dela, e está fazendo de propósito.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse. Then April stops frightening me and February stops being brown.
    >>  ............................................
    pt  Uma estufa. Aí abril para de me assustar e fevereiro para de ser marrom.
    >>  ............................................
  witty.dialogue.conversations.work.prof.florist.more/1
    en  The blue one! It hates this soil, it knows I need it, and it is doing this on purpose.
    >>  ............................................
    pt  A azul! Ela odeia este solo, sabe que eu preciso dela, e está fazendo de propósito.
    >>  ............................................
  witty.dialogue.conversations.work.prof.florist.more/2
    en  A glasshouse. Then April stops frightening me and February stops being brown.
    >>  ............................................
    pt  Uma estufa. Aí abril para de me assustar e fevereiro para de ser marrom.
    >>  ............................................
```

</details>


### Button `leave` — "Good growing."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.florist.challenge.landed`, `work.florist.challenge.stung`, `work.florist.craft.admire`, `work.florist.craft.ask_beds`, `work.florist.craft.ask_book`, `work.florist.future.ask_glass`, `work.florist.future.ask_successor`, `work.florist.future.encourage`, `work.florist.hard`, `work.florist.risk.ask_fifty`, `work.florist.risk.ask_frost`, `work.florist.risk.sympathise`, `work.florist.task.ask_both`, `work.florist.task.ask_ten`, `work.florist.task.offer_hands`, `work.florist.value`, `work.florist.village.ask_bare`, `work.florist.village.ask_ends`, `work.florist.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.followup.leave   [13 chars]
    en  Good growing.
    >>  ............................................
    pt  Bom cultivo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.leave
WHO    VILLAGER — what the player reads after pressing "Good growing."
       spoken on: conversations.topic.work.florist.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.left`: the villager accepts. Subject `work.florist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.florist.failed_bed.blocked.respond / leave; conversations.scene.work.florist.failed_bed.succeeded.respond / leave; conversations.scene.work.florist.followup / leave; conversations.scene.work.florist.funeral_order.active.respond / leave; conversations.scene.work.florist.funeral_order.succeeded.respond / leave; conversations.scene.work.florist.stubborn_variety.active.respond / leave; conversations.scene.work.florist.stubborn_variety.succeeded.respond / leave; conversations.topic.work.florist.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.florist.failed_bed.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.florist.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.florist.future` — e.g. "A glasshouse. Then February has colour by choice instead of by luck, and April stops frightening me."


```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.florist.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.florist.future.respond   [22 chars]
    en  That's what I'm after.
    >>  ............................................
    pt  É o que eu quero.
    >>  ............................................
```


### Button `ask_glass` — "What would a glasshouse cost?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.florist.future` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.florist.future.ask_glass` — accepted phrasings: "what would a glasshouse cost"
  - the message must contain one of: `glasshouse`, `cost`, `glass`
  - scored words: `glasshouse`(1.5), `cost`(1.2), `glass`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.future.respond.ask_glass
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.future.respond.ask_glass   [29 chars]
    en  What would a glasshouse cost?
    >>  ............................................
    pt  Quanto custaria uma estufa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.florist.future.ask_glass`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.florist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which flower is hardest to grow?" | "Good growing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.future.ask_glass
WHO    VILLAGER — what the player reads after pressing "What would a glasshouse cost?"
       spoken on: conversations.topic.work.florist.future.respond, button `ask_glass`
       leaves the player on: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.future.ask_glass`: the villager explains. Subject `work.florist.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.florist.future.ask_glass/1   [95 chars]
    en  More than nineteen years of arrangements have earned. I've done the sum and then done it again.
    >>  ............................................
    pt  Mais do que dezenove anos de arranjos renderam. Fiz a conta e refiz.
    >>  ............................................
  dialogue.conversations.work.prof.florist.future.ask_glass/2   [88 chars]
    en  The glass is the whole of it. The mason would build the rest for a cradle's worth, %1$s.
    >>  ............................................
    pt  O vidro é tudo. O pedreiro faria o resto pelo preço de um berço, %1$s.
    >>  ............................................
```


### Button `encourage` — "Ask the families in the book to fund the glass."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.florist.future` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.florist.future.encourage` — accepted phrasings: "ask the families in the book to fund the glass"
  - the message must contain one of: `families`, `fund`, `glass`
  - scored words: `families`(1.5), `fund`(1.5), `glass`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.future.respond.encourage   [47 chars]
    en  Ask the families in the book to fund the glass.
    >>  ............................................
    pt  Peça às famílias do caderno pra pagar o vidro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.florist.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.florist.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.florist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which flower is hardest to grow?" | "Good growing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.future.encourage
WHO    VILLAGER — what the player reads after pressing "Ask the families in the book to fund the glass."
       spoken on: conversations.topic.work.florist.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.future.encourage`: the villager accepts. Subject `work.florist.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.florist.future.encourage/1   [86 chars]
    en  ...The families in the book. Fifty-one of them. I had genuinely never thought of that.
    >>  ............................................
    pt  ...As famílias do caderno. Cinquenta e uma. Eu genuinamente nunca pensei nisso.
    >>  ............................................
  dialogue.conversations.work.prof.florist.future.encourage/2   [91 chars]
    en  That's either the best idea anyone's had or a thing I could never bring myself to do, %1$s.
    >>  ............................................
    pt  Ou é a melhor ideia que alguém teve ou algo que eu nunca conseguiria fazer, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book. Fifty-one names, and I know every one of them.
    >>  ............................................
    pt  ...As famílias do livro. Cinquenta e um nomes, e eu conheço todos.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.florist.future.encourage/2
    en  That's either the best idea anyone's had or a thing I'd be too frightened to do.
    >>  ............................................
    pt  Ou é a melhor ideia que alguém teve ou algo que eu teria medo demais de fazer.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book. Fifty-one, and I've buried flowers for a third of them.
    >>  ............................................
    pt  ...As famílias do livro. Cinquenta e uma, e enterrei flores por um terço delas.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.florist.future.encourage/2
    en  That's either the best idea anyone's had or one I've been too set to consider.
    >>  ............................................
    pt  Ou é a melhor ideia que alguém teve ou uma que eu estava rígido demais pra ver.
    >>  ............................................
  confident.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book. Fifty-one of them. I'd genuinely never thought of that.
    >>  ............................................
    pt  ...As famílias do livro. Cinquenta e uma. Eu sinceramente nunca pensei nisso.
    >>  ............................................
  confident.dialogue.conversations.work.prof.florist.future.encourage/2
    en  That's either the best idea anyone has had or a thing I could never do.
    >>  ............................................
    pt  Ou é a melhor ideia que alguém teve ou é algo que eu jamais conseguiria fazer.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book. Fifty-one of them. I'd genuinely never thought of that.
    >>  ............................................
    pt  ...As famílias do livro. Cinquenta e uma. Eu sinceramente nunca pensei nisso.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.florist.future.encourage/2
    en  That's either the best idea anyone has had or a thing I could never do.
    >>  ............................................
    pt  Ou é a melhor ideia que alguém teve ou é algo que eu jamais conseguiria fazer.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book, %1$s. Fifty-one. I'd never once thought of that.
    >>  ............................................
    pt  ...As famílias do livro, %1$s. Cinquenta e uma. Nunca pensei nisso nem uma vez.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.florist.future.encourage/2
    en  That's either the best idea anyone's had or a thing I could never do, and you knew it.
    >>  ............................................
    pt  Ou é a melhor ideia que alguém teve ou algo que eu jamais faria, e você sabia.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book, %1$s. Fifty-one. I'd never once thought of that.
    >>  ............................................
    pt  ...As famílias do livro, %1$s. Cinquenta e uma. Nunca pensei nisso nem uma vez.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.florist.future.encourage/2
    en  That's either the best idea anyone's had or a thing I could never do, and you knew it.
    >>  ............................................
    pt  Ou é a melhor ideia que alguém teve ou algo que eu jamais faria, e você sabia.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book, %1$s. Fifty-one. I'd never once thought of that.
    >>  ............................................
    pt  ...As famílias do livro, %1$s. Cinquenta e uma. Nunca pensei nisso nem uma vez.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.florist.future.encourage/2
    en  That's either the best idea anyone's had or a thing I could never do, and you knew it.
    >>  ............................................
    pt  Ou é a melhor ideia que alguém teve ou algo que eu jamais faria, e você sabia.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book. Fifty-one names, and I know every one of them.
    >>  ............................................
    pt  ...As famílias do livro. Cinquenta e um nomes, e eu conheço todos.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.florist.future.encourage/2
    en  That's either the best idea anyone's had or a thing I'd be too frightened to do.
    >>  ............................................
    pt  Ou é a melhor ideia que alguém teve ou algo que eu teria medo demais de fazer.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book. Fifty-one of them. I'd genuinely never thought of that.
    >>  ............................................
    pt  ...As famílias do livro. Cinquenta e uma. Eu sinceramente nunca pensei nisso.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.florist.future.encourage/2
    en  That's either the best idea anyone has had or a thing I could never do.
    >>  ............................................
    pt  Ou é a melhor ideia que alguém teve ou é algo que eu jamais conseguiria fazer.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book. Fifty-one of them. I'd genuinely never thought of that.
    >>  ............................................
    pt  ...As famílias do livro. Cinquenta e uma. Eu sinceramente nunca pensei nisso.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.florist.future.encourage/2
    en  That's either the best idea anyone has had or a thing I could never do.
    >>  ............................................
    pt  Ou é a melhor ideia que alguém teve ou é algo que eu jamais conseguiria fazer.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book. Fifty-one.
    >>  ............................................
    pt  ...As famílias do livro. Cinquenta e uma.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.florist.future.encourage/2
    en  Best idea anyone's had, or one I could never do.
    >>  ............................................
    pt  Melhor ideia que alguém teve, ou uma que eu jamais faria.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book. Fifty-one, and I've buried flowers for a third of them.
    >>  ............................................
    pt  ...As famílias do livro. Cinquenta e uma, e enterrei flores por um terço delas.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.florist.future.encourage/2
    en  That's either the best idea anyone's had or one I've been too set to consider.
    >>  ............................................
    pt  Ou é a melhor ideia que alguém teve ou uma que eu estava rígido demais pra ver.
    >>  ............................................
  odd.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book. Fifty-one.
    >>  ............................................
    pt  ...As famílias do livro. Cinquenta e uma.
    >>  ............................................
  odd.dialogue.conversations.work.prof.florist.future.encourage/2
    en  Best idea anyone's had, or one I could never do.
    >>  ............................................
    pt  Melhor ideia que alguém teve, ou uma que eu jamais faria.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book. Fifty-one, and I've buried flowers for a third of them.
    >>  ............................................
    pt  ...As famílias do livro. Cinquenta e uma, e enterrei flores por um terço delas.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.florist.future.encourage/2
    en  That's either the best idea anyone's had or one I've been too set to consider.
    >>  ............................................
    pt  Ou é a melhor ideia que alguém teve ou uma que eu estava rígido demais pra ver.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book! Fifty-one! I had genuinely never thought of that.
    >>  ............................................
    pt  ...As famílias do livro! Cinquenta e uma! Eu sinceramente nunca pensei nisso.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.florist.future.encourage/2
    en  That's either the best idea anyone's had or a thing I could never bring myself to do.
    >>  ............................................
    pt  Ou é a melhor ideia que alguém teve ou algo que eu jamais conseguiria fazer.
    >>  ............................................
  playful.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book! Fifty-one! I had genuinely never thought of that.
    >>  ............................................
    pt  ...As famílias do livro! Cinquenta e uma! Eu sinceramente nunca pensei nisso.
    >>  ............................................
  playful.dialogue.conversations.work.prof.florist.future.encourage/2
    en  That's either the best idea anyone's had or a thing I could never bring myself to do.
    >>  ............................................
    pt  Ou é a melhor ideia que alguém teve ou algo que eu jamais conseguiria fazer.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book. Fifty-one, and I've buried flowers for a third of them.
    >>  ............................................
    pt  ...As famílias do livro. Cinquenta e uma, e enterrei flores por um terço delas.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.florist.future.encourage/2
    en  That's either the best idea anyone's had or one I've been too set to consider.
    >>  ............................................
    pt  Ou é a melhor ideia que alguém teve ou uma que eu estava rígido demais pra ver.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book. Fifty-one names, and I know every one of them.
    >>  ............................................
    pt  ...As famílias do livro. Cinquenta e um nomes, e eu conheço todos.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.florist.future.encourage/2
    en  That's either the best idea anyone's had or a thing I'd be too frightened to do.
    >>  ............................................
    pt  Ou é a melhor ideia que alguém teve ou algo que eu teria medo demais de fazer.
    >>  ............................................
  shy.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book. Fifty-one.
    >>  ............................................
    pt  ...As famílias do livro. Cinquenta e uma.
    >>  ............................................
  shy.dialogue.conversations.work.prof.florist.future.encourage/2
    en  Best idea anyone's had, or one I could never do.
    >>  ............................................
    pt  Melhor ideia que alguém teve, ou uma que eu jamais faria.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book! Fifty-one! I had genuinely never thought of that.
    >>  ............................................
    pt  ...As famílias do livro! Cinquenta e uma! Eu sinceramente nunca pensei nisso.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.florist.future.encourage/2
    en  That's either the best idea anyone's had or a thing I could never bring myself to do.
    >>  ............................................
    pt  Ou é a melhor ideia que alguém teve ou algo que eu jamais conseguiria fazer.
    >>  ............................................
  witty.dialogue.conversations.work.prof.florist.future.encourage/1
    en  ...The families in the book! Fifty-one! I had genuinely never thought of that.
    >>  ............................................
    pt  ...As famílias do livro! Cinquenta e uma! Eu sinceramente nunca pensei nisso.
    >>  ............................................
  witty.dialogue.conversations.work.prof.florist.future.encourage/2
    en  That's either the best idea anyone's had or a thing I could never bring myself to do.
    >>  ............................................
    pt  Ou é a melhor ideia que alguém teve ou algo que eu jamais conseguiria fazer.
    >>  ............................................
```

</details>


### Button `ask_successor` — "Who would you give the book to?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.florist.future` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.florist.future.ask_successor` — accepted phrasings: "who would you give the book to"
  - the message must contain one of: `book`, `successor`
  - scored words: `book`(1.2), `successor`(1.5), `give`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.future.respond.ask_successor
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.future.respond.ask_successor   [31 chars]
    en  Who would you give the book to?
    >>  ............................................
    pt  A quem você daria o caderno?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.florist.future.ask_successor`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.florist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which flower is hardest to grow?" | "Good growing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.future.ask_successor
WHO    VILLAGER — what the player reads after pressing "Who would you give the book to?"
       spoken on: conversations.topic.work.florist.future.respond, button `ask_successor`
       leaves the player on: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.future.ask_successor`: the villager explains. Subject `work.florist.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.florist.future.ask_successor/1   [87 chars]
    en  Somebody who'd add to it. That's the only requirement and it rules out almost everyone.
    >>  ............................................
    pt  Alguém que acrescentasse. É o único requisito e exclui quase todo mundo.
    >>  ............................................
  dialogue.conversations.work.prof.florist.future.ask_successor/2   [89 chars]
    en  The cleric, if she were younger. She's the only other person here who keeps things, %1$s.
    >>  ............................................
    pt  A clériga, se fosse mais nova. É a única outra pessoa daqui que guarda coisas, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the beds."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.florist.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.future.respond.leave   [34 chars]
    en  I'll let you get back to the beds.
    >>  ............................................
    pt  Vou deixar você voltar aos canteiros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the beds."
       spoken on: conversations.topic.work.florist.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.left`: the villager accepts. Subject `work.florist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.florist.failed_bed.blocked.respond / leave; conversations.scene.work.florist.failed_bed.succeeded.respond / leave; conversations.scene.work.florist.followup / leave; conversations.scene.work.florist.funeral_order.active.respond / leave; conversations.scene.work.florist.funeral_order.succeeded.respond / leave; conversations.scene.work.florist.stubborn_variety.active.respond / leave; conversations.scene.work.florist.stubborn_variety.succeeded.respond / leave; conversations.topic.work.florist.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.florist.failed_bed.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.florist.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.florist` — e.g. "Flowers are the only customers that never complain. The bees drive a hard bargain, though."


```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.florist.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.florist.respond   [38 chars]
    en  That's the stems and what they're for.
    >>  ............................................
    pt  São as hastes e pra que servem.
    >>  ............................................
```


### Button `ask_hard` — "What's the hardest arrangement to make?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.florist.identity` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.florist.hard` — accepted phrasings: "what's the hardest arrangement to make"
  - the message must contain one of: `hardest`, `arrangement`, `funeral`
  - scored words: `hardest`(1.2), `arrangement`(1.5), `funeral`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.respond.ask_hard   [39 chars]
    en  What's the hardest arrangement to make?
    >>  ............................................
    pt  Qual arranjo é o mais difícil de fazer?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.florist.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.florist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which flower is hardest to grow?" | "Good growing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest arrangement to make?"
       spoken on: conversations.topic.work.florist.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.hard`: the villager explains. Subject `work.florist.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.florist.followup / ask_more
```

> Written out in full under **`conversations.scene.work.florist.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "Every important day here passes through your hands."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.florist.identity` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.florist.value` — accepted phrasings: "every important day here passes through your hands"
  - the message must contain one of: `important`, `weddings`, `occasions`
  - scored words: `important`(1.2), `weddings`(1.5), `occasions`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.respond.value   [51 chars]
    en  Every important day here passes through your hands.
    >>  ............................................
    pt  Todo dia importante daqui passa pelas suas mãos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.florist.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.florist.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.florist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which flower is hardest to grow?" | "Good growing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.value
WHO    VILLAGER — what the player reads after pressing "Every important day here passes through your hands."
       spoken on: conversations.topic.work.florist.respond, button `value`
       leaves the player on: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.value`: the villager accepts. Subject `work.florist.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.florist.value/1   [74 chars]
    en  It does. I know who's courting before the village does, and I say nothing.
    >>  ............................................
    pt  Passa. Eu sei quem está cortejando antes do vilarejo, e não digo nada.
    >>  ............................................
  dialogue.conversations.work.prof.florist.value/2   [85 chars]
    en  Weddings, funerals, apologies. I've held all three in one week, and I remember which.
    >>  ............................................
    pt  Casamentos, funerais, desculpas. Já segurei os três numa semana, e lembro de qual foi qual.
    >>  ............................................
```


### Button `challenge` — "It's decoration, not work."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.florist.identity` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.florist.challenge` — accepted phrasings: "it's decoration, not work"
  - the message must contain one of: `decoration`, `hobby`
  - scored words: `decoration`(1.5), `hobby`(1.5), `work`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.respond.challenge   [26 chars]
    en  It's decoration, not work.
    >>  ............................................
    pt  É decoração, não trabalho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.florist.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.florist.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.florist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which flower is hardest to grow?" | "Good growing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.challenge.landed
WHO    VILLAGER — what the player reads after pressing "It's decoration, not work."
       spoken on: conversations.topic.work.florist.respond, button `challenge`
       leaves the player on: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.challenge.landed`: the villager resists. Subject `work.florist.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.florist.challenge.landed/1   [75 chars]
    en  It is decoration. Decoration is how people say things they can't say, %1$s.
    >>  ............................................
    pt  É decoração. Decoração é como as pessoas dizem o que não conseguem dizer, %1$s.
    >>  ............................................
  dialogue.conversations.work.prof.florist.challenge.landed/2   [72 chars]
    en  Call it that. Then try telling someone you're sorry with your own mouth.
    >>  ............................................
    pt  Chame assim. Aí tente pedir desculpas com a própria boca.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.florist.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.florist.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.florist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which flower is hardest to grow?" | "Good growing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.challenge.stung
WHO    VILLAGER — what the player reads after pressing "It's decoration, not work."
       spoken on: conversations.topic.work.florist.respond, button `challenge`
       leaves the player on: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.challenge.stung`: the villager resists. Subject `work.florist.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.florist.challenge.stung/1   [73 chars]
    en  ...Come and watch me tie a funeral spray and say 'decoration' afterwards.
    >>  ............................................
    pt  ...Venha me ver amarrar um arranjo de funeral e depois diga 'decoração'.
    >>  ............................................
  dialogue.conversations.work.prof.florist.challenge.stung/2   [68 chars]
    en  Not work. Right. The bees and I will manage without your assessment.
    >>  ............................................
    pt  Não é trabalho. Certo. As abelhas e eu vamos nos virar sem sua avaliação.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the beds."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.florist.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.respond.leave   [34 chars]
    en  I'll let you get back to the beds.
    >>  ............................................
    pt  Vou deixar você voltar aos canteiros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the beds."
       spoken on: conversations.topic.work.florist.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.left`: the villager accepts. Subject `work.florist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.florist.failed_bed.blocked.respond / leave; conversations.scene.work.florist.failed_bed.succeeded.respond / leave; conversations.scene.work.florist.followup / leave; conversations.scene.work.florist.funeral_order.active.respond / leave; conversations.scene.work.florist.funeral_order.succeeded.respond / leave; conversations.scene.work.florist.stubborn_variety.active.respond / leave; conversations.scene.work.florist.stubborn_variety.succeeded.respond / leave; conversations.topic.work.florist.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.florist.failed_bed.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.florist.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.florist.risk` — e.g. "A late frost takes a year in one night. I've had two and I've stopped pretending I sleep in April."


```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.florist.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.florist.risk.respond   [24 chars]
    en  That's what it costs me.
    >>  ............................................
    pt  É o que me custa.
    >>  ............................................
```


### Button `ask_frost` — "Can you do anything about a frost?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.florist.risk` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.florist.risk.ask_frost` — accepted phrasings: "can you do anything about a frost"
  - the message must contain one of: `frost`, `save`, `april`
  - scored words: `frost`(1.5), `save`(1.2), `april`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.risk.respond.ask_frost
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.risk.respond.ask_frost   [34 chars]
    en  Can you do anything about a frost?
    >>  ............................................
    pt  Dá pra fazer algo contra a geada?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.florist.risk.ask_frost`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.florist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which flower is hardest to grow?" | "Good growing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.risk.ask_frost
WHO    VILLAGER — what the player reads after pressing "Can you do anything about a frost?"
       spoken on: conversations.topic.work.florist.risk.respond, button `ask_frost`
       leaves the player on: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.risk.ask_frost`: the villager explains. Subject `work.florist.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.florist.risk.ask_frost/1   [92 chars]
    en  Cloth, straw and standing in the dark deciding which beds to save. That's the whole defence.
    >>  ............................................
    pt  Pano, palha e ficar no escuro decidindo quais canteiros salvar. É toda a defesa.
    >>  ............................................
  dialogue.conversations.work.prof.florist.risk.ask_frost/2   [86 chars]
    en  You choose. That's all it is — at two in the morning you choose which half dies, %1$s.
    >>  ............................................
    pt  Você escolhe. É só isso — às duas da manhã você escolhe qual metade morre, %1$s.
    >>  ............................................
```


### Button `sympathise` — "Being at every funeral without being family is its own weight."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.florist.risk` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.florist.risk.sympathise` — accepted phrasings: "being at every funeral without being family is its own weight"
  - the message must contain one of: `funeral`, `family`, `weight`
  - scored words: `funeral`(1.5), `family`(1.0), `weight`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.risk.respond.sympathise   [62 chars]
    en  Being at every funeral without being family is its own weight.
    >>  ............................................
    pt  Estar em todo funeral sem ser da família é um peso próprio.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.florist.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.florist.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.florist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which flower is hardest to grow?" | "Good growing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "Being at every funeral without being family is its own weight."
       spoken on: conversations.topic.work.florist.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.risk.sympathise`: the villager accepts. Subject `work.florist.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.florist.risk.sympathise/1   [81 chars]
    en  ...It is. You grieve a little for everybody and you're not entitled to any of it.
    >>  ............................................
    pt  ...É. Você lamenta um pouco por todos e não tem direito a nada disso.
    >>  ............................................
  dialogue.conversations.work.prof.florist.risk.sympathise/2   [100 chars]
    en  That has not been said to me before. I've been at fifty-one of them, %1$s, and it has not been said.
    >>  ............................................
    pt  Isso não foi dito a mim antes. Estive em cinquenta e uma, %1$s, e não foi dito.
    >>  ............................................
```


### Button `ask_fifty` — "Fifty-one funerals?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.florist.risk` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.florist.risk.ask_fifty` — accepted phrasings: "fifty-one funerals"
  - the message must contain one of: `fifty`, `funerals`
  - scored words: `fifty`(1.5), `funerals`(1.2), `many`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.risk.respond.ask_fifty
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.risk.respond.ask_fifty   [19 chars]
    en  Fifty-one funerals?
    >>  ............................................
    pt  Cinquenta e um funerais?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.florist.risk.ask_fifty`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.florist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which flower is hardest to grow?" | "Good growing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.risk.ask_fifty
WHO    VILLAGER — what the player reads after pressing "Fifty-one funerals?"
       spoken on: conversations.topic.work.florist.risk.respond, button `ask_fifty`
       leaves the player on: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.risk.ask_fifty`: the villager explains. Subject `work.florist.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.florist.risk.ask_fifty/1   [105 chars]
    en  Fifty-one. The book has fifty-one entries. Those are the same number and I check that they stay the same.
    >>  ............................................
    pt  Cinquenta e um. O caderno tem cinquenta e uma entradas. É o mesmo número e eu confiro que continue.
    >>  ............................................
  dialogue.conversations.work.prof.florist.risk.ask_fifty/2   [95 chars]
    en  In nineteen years. I could take you round the ground and tell you what each of them held, %1$s.
    >>  ............................................
    pt  Em dezenove anos. Eu poderia te levar ao cemitério e dizer o que cada um segurava, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the beds."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.florist.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.risk.respond.leave   [34 chars]
    en  I'll let you get back to the beds.
    >>  ............................................
    pt  Vou deixar você voltar aos canteiros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the beds."
       spoken on: conversations.topic.work.florist.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.left`: the villager accepts. Subject `work.florist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.florist.failed_bed.blocked.respond / leave; conversations.scene.work.florist.failed_bed.succeeded.respond / leave; conversations.scene.work.florist.followup / leave; conversations.scene.work.florist.funeral_order.active.respond / leave; conversations.scene.work.florist.funeral_order.succeeded.respond / leave; conversations.scene.work.florist.stubborn_variety.active.respond / leave; conversations.scene.work.florist.stubborn_variety.succeeded.respond / leave; conversations.topic.work.florist.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.florist.failed_bed.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.florist.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.florist.task` — e.g. "Cutting before the sun gets high. After ten they're just decorations that die in a day."


```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.florist.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.florist.task.respond   [27 chars]
    en  That's this morning's shed.
    >>  ............................................
    pt  É o galpão desta manhã.
    >>  ............................................
```


### Button `ask_both` — "How do you do both in one morning?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.florist.task` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.florist.task.ask_both` — accepted phrasings: "how do you do both in one morning"
  - the message must contain one of: `both`, `morning`, `wedding`
  - scored words: `both`(1.5), `morning`(1.0), `wedding`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.task.respond.ask_both
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.task.respond.ask_both   [34 chars]
    en  How do you do both in one morning?
    >>  ............................................
    pt  Como você faz os dois numa manhã?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.florist.task.ask_both`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.florist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which flower is hardest to grow?" | "Good growing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.task.ask_both
WHO    VILLAGER — what the player reads after pressing "How do you do both in one morning?"
       spoken on: conversations.topic.work.florist.task.respond, button `ask_both`
       leaves the player on: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.task.ask_both`: the villager explains. Subject `work.florist.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.florist.task.ask_both/1   [82 chars]
    en  Separately, and with the door shut between them. It matters more than you'd think.
    >>  ............................................
    pt  Separadamente, e com a porta fechada entre eles. Importa mais do que se imagina.
    >>  ............................................
  dialogue.conversations.work.prof.florist.task.ask_both/2   [89 chars]
    en  Carefully. The families would each be hurt to know the other was on the same bench, %1$s.
    >>  ............................................
    pt  Com cuidado. As duas famílias se magoariam sabendo que a outra estava na mesma bancada, %1$s.
    >>  ............................................
```


### Button `offer_hands` — "I can cut the early stems."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.florist.task` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.florist.task.offer_hands` — accepted phrasings: "i can cut the early stems"
  - the message must contain one of: `stems`, `cut`, `early`
  - scored words: `stems`(1.5), `cut`(1.0), `early`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.task.respond.offer_hands   [26 chars]
    en  I can cut the early stems.
    >>  ............................................
    pt  Eu posso cortar os talos cedo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.florist.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.florist.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.florist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which flower is hardest to grow?" | "Good growing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I can cut the early stems."
       spoken on: conversations.topic.work.florist.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.task.offer_hands`: the villager accepts. Subject `work.florist.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.florist.task.offer_hands/1   [90 chars]
    en  ...You can. On the slant, above a leaf pair, and into water before you take a second step.
    >>  ............................................
    pt  ...Pode. No viés, acima de um par de folhas, e na água antes de dar um segundo passo.
    >>  ............................................
  dialogue.conversations.work.prof.florist.task.offer_hands/2   [81 chars]
    en  Then take the white ones and be gentle with them, %1$s. Those are for the burial.
    >>  ............................................
    pt  Então pegue as brancas e seja delicado, %1$s. Essas são pro enterro.
    >>  ............................................
```


### Button `ask_ten` — "What changes after ten?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.florist.task` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.florist.task.ask_ten` — accepted phrasings: "what changes after ten"
  - the message must contain one of: `ten`, `changes`, `sun`
  - scored words: `ten`(1.5), `changes`(1.0), `sun`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.task.respond.ask_ten
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.task.respond.ask_ten   [23 chars]
    en  What changes after ten?
    >>  ............................................
    pt  O que muda depois das dez?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.florist.task.ask_ten`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.florist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which flower is hardest to grow?" | "Good growing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.task.ask_ten
WHO    VILLAGER — what the player reads after pressing "What changes after ten?"
       spoken on: conversations.topic.work.florist.task.respond, button `ask_ten`
       leaves the player on: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.task.ask_ten`: the villager explains. Subject `work.florist.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.florist.task.ask_ten/1   [85 chars]
    en  The stem stops drinking. You can't see it happen and you can see it three days later.
    >>  ............................................
    pt  O talo para de beber. Você não vê acontecer e vê três dias depois.
    >>  ............................................
  dialogue.conversations.work.prof.florist.task.ask_ten/2   [94 chars]
    en  Everything. A flower cut at nine lasts a week; the same flower at eleven lasts two days, %1$s.
    >>  ............................................
    pt  Tudo. Uma flor cortada às nove dura uma semana; a mesma às onze dura dois dias, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the beds."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.florist.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.task.respond.leave   [34 chars]
    en  I'll let you get back to the beds.
    >>  ............................................
    pt  Vou deixar você voltar aos canteiros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the beds."
       spoken on: conversations.topic.work.florist.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.left`: the villager accepts. Subject `work.florist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.florist.failed_bed.blocked.respond / leave; conversations.scene.work.florist.failed_bed.succeeded.respond / leave; conversations.scene.work.florist.followup / leave; conversations.scene.work.florist.funeral_order.active.respond / leave; conversations.scene.work.florist.funeral_order.succeeded.respond / leave; conversations.scene.work.florist.stubborn_variety.active.respond / leave; conversations.scene.work.florist.stubborn_variety.succeeded.respond / leave; conversations.topic.work.florist.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.florist.failed_bed.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.florist.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.florist.village` — e.g. "Every wedding and every burial in nineteen years came through my shed. Both ends of everybody."


```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.florist.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.florist.village.respond   [29 chars]
    en  That's my share of the place.
    >>  ............................................
    pt  É a minha parte do lugar.
    >>  ............................................
```


### Button `ask_ends` — "Both ends of everybody — does that sit strangely?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.florist.village` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.florist.village.ask_ends` — accepted phrasings: "both ends of everybody — does that sit strangely"
  - the message must contain one of: `ends`, `both`, `strange`
  - scored words: `ends`(1.5), `both`(1.0), `strange`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.village.respond.ask_ends
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.village.respond.ask_ends   [49 chars]
    en  Both ends of everybody — does that sit strangely?
    >>  ............................................
    pt  As duas pontas de cada um — isso é estranho?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.florist.village.ask_ends`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.florist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which flower is hardest to grow?" | "Good growing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.village.ask_ends
WHO    VILLAGER — what the player reads after pressing "Both ends of everybody — does that sit strangely?"
       spoken on: conversations.topic.work.florist.village.respond, button `ask_ends`
       leaves the player on: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.village.ask_ends`: the villager explains. Subject `work.florist.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.florist.village.ask_ends/1   [87 chars]
    en  I did a wedding and a burial for the same woman eleven years apart. That sat strangely.
    >>  ............................................
    pt  Fiz um casamento e um enterro pra mesma mulher com onze anos de diferença. Isso foi estranho.
    >>  ............................................
  dialogue.conversations.work.prof.florist.village.ask_ends/2   [96 chars]
    en  It means I know what everyone chose when they were happiest, %1$s, and what they got at the end.
    >>  ............................................
    pt  Significa que eu sei o que cada um escolheu no auge da alegria, %1$s, e o que recebeu no fim.
    >>  ............................................
```


### Button `say_thanks` — "Colour in February is a kindness people don't credit."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.florist.village` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.florist.village.say_thanks` — accepted phrasings: "colour in february is a kindness people don't credit"
  - the message must contain one of: `colour`, `february`, `kindness`
  - scored words: `colour`(1.5), `february`(1.2), `kindness`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.village.respond.say_thanks   [53 chars]
    en  Colour in February is a kindness people don't credit.
    >>  ............................................
    pt  Cor em fevereiro é uma gentileza que ninguém credita.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.florist.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.florist.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.florist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which flower is hardest to grow?" | "Good growing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Colour in February is a kindness people don't credit."
       spoken on: conversations.topic.work.florist.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.village.say_thanks`: the villager accepts. Subject `work.florist.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.florist.village.say_thanks/1   [88 chars]
    en  ...February. That's the month I'd have picked and I didn't think anyone else counted it.
    >>  ............................................
    pt  ...Fevereiro. É o mês que eu teria escolhido e eu achei que ninguém mais contava.
    >>  ............................................
  dialogue.conversations.work.prof.florist.village.say_thanks/2   [93 chars]
    en  They credit it by complaining when it's absent, %1$s, which I've decided is a form of thanks.
    >>  ............................................
    pt  Creditam reclamando quando falta, %1$s, o que eu decidi que é um tipo de agradecimento.
    >>  ............................................
```


### Button `ask_bare` — "Have the beds ever been bare?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.florist.village` · offered only once the villager has actually said `work:florist`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.florist.village.ask_bare` — accepted phrasings: "have the beds ever been bare"
  - the message must contain one of: `bare`, `empty`, `beds`
  - scored words: `bare`(1.5), `empty`(1.2), `beds`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.village.respond.ask_bare
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.village.respond.ask_bare   [29 chars]
    en  Have the beds ever been bare?
    >>  ............................................
    pt  Os canteiros já ficaram vazios?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.florist.village.ask_bare`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.florist.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which flower is hardest to grow?" | "Good growing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.village.ask_bare
WHO    VILLAGER — what the player reads after pressing "Have the beds ever been bare?"
       spoken on: conversations.topic.work.florist.village.respond, button `ask_bare`
       leaves the player on: conversations.topic.work.florist.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.village.ask_bare`: the villager explains. Subject `work.florist.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:florist` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.florist.village.ask_bare/1   [101 chars]
    en  One March, after the second frost. Four people asked me if I was unwell. Nobody asked about the beds.
    >>  ............................................
    pt  Um março, depois da segunda geada. Quatro pessoas perguntaram se eu estava mal. Ninguém perguntou dos canteiros.
    >>  ............................................
  dialogue.conversations.work.prof.florist.village.ask_bare/2   [92 chars]
    en  Once, and the complaints started on the fourth day, %1$s, which is faster than I'd expected.
    >>  ............................................
    pt  Uma vez, e as reclamações começaram no quarto dia, %1$s, mais rápido do que eu esperava.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the beds."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.florist.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.florist.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.florist.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.florist.village.respond.leave   [34 chars]
    en  I'll let you get back to the beds.
    >>  ............................................
    pt  Vou deixar você voltar aos canteiros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.florist.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the beds."
       spoken on: conversations.topic.work.florist.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.florist.left`: the villager accepts. Subject `work.florist.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.florist.failed_bed.blocked.respond / leave; conversations.scene.work.florist.failed_bed.succeeded.respond / leave; conversations.scene.work.florist.followup / leave; conversations.scene.work.florist.funeral_order.active.respond / leave; conversations.scene.work.florist.funeral_order.succeeded.respond / leave; conversations.scene.work.florist.stubborn_variety.active.respond / leave; conversations.scene.work.florist.stubborn_variety.succeeded.respond / leave; conversations.topic.work.florist.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.florist.failed_bed.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

