# Work talk with a mason

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.mason.bad_stone.blocked.respond`](#conversations-scene-work-mason-bad-stone-blocked-respond)
- [`conversations.scene.work.mason.bad_stone.succeeded.respond`](#conversations-scene-work-mason-bad-stone-succeeded-respond)
- [`conversations.scene.work.mason.followup`](#conversations-scene-work-mason-followup)
- [`conversations.scene.work.mason.quick_apprentice.active.respond`](#conversations-scene-work-mason-quick-apprentice-active-respond)
- [`conversations.scene.work.mason.quick_apprentice.succeeded.respond`](#conversations-scene-work-mason-quick-apprentice-succeeded-respond)
- [`conversations.scene.work.mason.rushed_foundation.blocked.respond`](#conversations-scene-work-mason-rushed-foundation-blocked-respond)
- [`conversations.scene.work.mason.rushed_foundation.succeeded.respond`](#conversations-scene-work-mason-rushed-foundation-succeeded-respond)
- [`conversations.topic.work.mason.craft.respond`](#conversations-topic-work-mason-craft-respond)
- [`conversations.topic.work.mason.followup`](#conversations-topic-work-mason-followup)
- [`conversations.topic.work.mason.future.respond`](#conversations-topic-work-mason-future-respond)
- [`conversations.topic.work.mason.respond`](#conversations-topic-work-mason-respond)
- [`conversations.topic.work.mason.risk.respond`](#conversations-topic-work-mason-risk-respond)
- [`conversations.topic.work.mason.task.respond`](#conversations-topic-work-mason-task-respond)
- [`conversations.topic.work.mason.village.respond`](#conversations-topic-work-mason-village-respond)

---

## `conversations.scene.work.mason.bad_stone.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.mason.bad_stone.blocked` — e.g. "%2$s. Half a cartload of it, and half a cartload is exactly the amount that tempts you to use it anyway."


```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.bad_stone.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.mason.bad_stone.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.mason.bad_stone.blocked.respond   [13 chars]
    en  The delivery.
    >>  ............................................
    pt  A entrega.
    >>  ............................................
```


### Button `ask_about_using_it` — "Could you use it somewhere it matters less?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mason.bad_stone.blocked` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mason.bad_stone.blocked.ask_about_using_it` — accepted phrasings: "could you use it somewhere it matters less"; "could you use it somewhere it matters less"; "is there a place the weaker stone would do"
  - the message must contain one of: `matters`, `weaker`, `place`
  - scored words: `matters`(1.8), `weaker`(1.8), `place`(1.8), `use`(0.8), `somewhere`(0.8), `less`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.bad_stone.blocked.respond.ask_about_using_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mason.bad_stone.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mason.bad_stone.blocked.respond.ask_about_using_it   [43 chars]
    en  Could you use it somewhere it matters less?
    >>  ............................................
    pt  Dá para usar onde importa menos?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mason.the_quarry`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.mason.bad_stone"}
- Then opens: `conversations.scene.work.mason.followup`
- …where the player's next choices will be: "What's the hardest part of laying a wall?" | "I'll leave you to the stone."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.bad_stone.blocked.explained
WHO    VILLAGER — what the player reads after pressing "Could you use it somewhere it matters less?"
       spoken on: conversations.scene.work.mason.bad_stone.blocked.respond, button `ask_about_using_it`
       leaves the player on: conversations.scene.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.bad_stone.blocked.explained`: the villager explains. Subject `work.mason.the_quarry`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mason.bad_stone.blocked.explained/1   [115 chars]
    en  Fill, yes. Facing, never. The trouble is that fill and facing look the same in a cart and only differ in ten years.
    >>  ............................................
    pt  Enchimento, sim. Face, nunca. O problema é que enchimento e face parecem iguais numa carroça e só diferem em dez anos.
    >>  ............................................
  dialogue.conversations.scene.work.mason.bad_stone.blocked.explained/2   [120 chars]
    en  There is always somewhere. That is exactly the reasoning that puts bad stone in a wall, one sensible decision at a time.
    >>  ............................................
    pt  Sempre tem um lugar. É exatamente esse raciocínio que põe pedra ruim num muro, uma decisão sensata por vez.
    >>  ............................................
  dialogue.conversations.scene.work.mason.bad_stone.blocked.explained/3   [115 chars]
    en  A garden edge, a step. Nothing that carries. I keep a low wall behind the workshop entirely out of stone I refused.
    >>  ............................................
    pt  Uma borda de horta, um degrau. Nada que sustente. Tenho um muro baixo atrás da oficina feito inteiro de pedra que recusei.
    >>  ............................................
```


### Button `offer_stone` — "I'll bring you good stone."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.mason.bad_stone.blocked` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mason.bad_stone.blocked.offer_stone` — accepted phrasings: "ill bring you good stone"; "i can bring you good stone"; "let me fetch stone for that"
  - the message must contain one of: `stone`
  - scored words: `stone`(1.8), `ill`(0.8), `bring`(0.8), `good`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.bad_stone.blocked.respond.offer_stone
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mason.bad_stone.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mason.bad_stone.blocked.respond.offer_stone   [26 chars]
    en  I'll bring you good stone.
    >>  ............................................
    pt  Vou te trazer pedra boa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.mason.stone.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.mason.the_quarry`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.bad_stone", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.mason.bad_stone", "obligation": "commitment:work.mason.bring_stone"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.mason.bring_stone"}
- Then opens: `conversations.scene.work.mason.followup`
- …where the player's next choices will be: "What's the hardest part of laying a wall?" | "I'll leave you to the stone."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.bad_stone.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you good stone."
       spoken on: conversations.scene.work.mason.bad_stone.blocked.respond, button `offer_stone`
       leaves the player on: conversations.scene.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.bad_stone.blocked.accepted`: the villager accepts. Subject `work.mason.the_quarry`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mason.bad_stone.blocked.accepted/1   [95 chars]
    en  Then the courses go up this week and I stop having the same argument with myself every morning.
    >>  ............................................
    pt  Então as fiadas sobem esta semana e eu paro de ter a mesma discussão comigo toda manhã.
    >>  ............................................
  dialogue.conversations.scene.work.mason.bad_stone.blocked.accepted/2   [92 chars]
    en  Bring it dry if you can. Wet stone splits in the first frost and the frost is six weeks out.
    >>  ............................................
    pt  Traga seca, se puder. Pedra molhada racha na primeira geada, e a geada está a seis semanas.
    >>  ............................................
  dialogue.conversations.scene.work.mason.bad_stone.blocked.accepted/3   [89 chars]
    en  Yes. And I will lay your stone at the corners, where it shows and where it works hardest.
    >>  ............................................
    pt  Sim. E vou assentar a sua pedra nos cantos, onde aparece e onde mais trabalha.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the course."

*stance family `exit` · tone `plain` · answers the beat(s) `work.mason.bad_stone.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.bad_stone.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mason.bad_stone.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mason.bad_stone.blocked.respond.leave   [36 chars]
    en  I'll let you get back to the course.
    >>  ............................................
    pt  Vou deixar você voltar à fiada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the course."
       spoken on: conversations.scene.work.mason.bad_stone.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.left`: the villager accepts. Subject `work.mason.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mason.bad_stone.succeeded.respond / leave; conversations.scene.work.mason.followup / leave; conversations.scene.work.mason.quick_apprentice.active.respond / leave; conversations.scene.work.mason.quick_apprentice.succeeded.respond / leave; conversations.scene.work.mason.rushed_foundation.blocked.respond / leave; conversations.scene.work.mason.rushed_foundation.succeeded.respond / leave; conversations.topic.work.mason.craft.respond / leave; conversations.topic.work.mason.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.mason.leave/1   [43 chars]
    en  The mortar's already going off. Off you go.
    >>  ............................................
    pt  A argamassa já está pegando. Pode ir.
    >>  ............................................
  dialogue.conversations.work.prof.mason.leave/2   [55 chars]
    en  Aye. Don't lean on that one, %1$s, it's this morning's.
    >>  ............................................
    pt  É. Não encoste nessa, %1$s, é de hoje de manhã.
    >>  ............................................
```

---


## `conversations.scene.work.mason.bad_stone.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.mason.bad_stone.succeeded` — e.g. "Six courses up and true. I ran a line along the top this morning and it did not want correcting anywhere."


```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.bad_stone.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.mason.bad_stone.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.mason.bad_stone.succeeded.respond   [9 chars]
    en  The wall.
    >>  ............................................
    pt  O muro.
    >>  ............................................
```


### Button `admire_the_wall` — "It'll outlast all of us."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.mason.bad_stone.succeeded` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mason.bad_stone.succeeded.admire_the_wall` — accepted phrasings: "itll outlast all of us"; "it will outlast all of us"; "that wall will outlast everyone here"
  - the message must contain one of: `outlast`
  - scored words: `outlast`(1.8), `itll`(0.8), `all`(0.8), `wall`(0.8), `everyone`(0.8), `here`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.bad_stone.succeeded.respond.admire_the_wall
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mason.bad_stone.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mason.bad_stone.succeeded.respond.admire_the_wall   [24 chars]
    en  It'll outlast all of us.
    >>  ............................................
    pt  Vai durar mais que todos nós.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +3  _(recorded under topic `work.mason.the_quarry`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.mason.bad_stone"}
- Then opens: `conversations.scene.work.mason.followup`
- …where the player's next choices will be: "What's the hardest part of laying a wall?" | "I'll leave you to the stone."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.bad_stone.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "It'll outlast all of us."
       spoken on: conversations.scene.work.mason.bad_stone.succeeded.respond, button `admire_the_wall`
       leaves the player on: conversations.scene.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.bad_stone.succeeded.acknowledged`: the villager accepts. Subject `work.mason.the_quarry`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mason.bad_stone.succeeded.acknowledged/1   [92 chars]
    en  That is the plan, and it is a strange plan to hold, because success means never finding out.
    >>  ............................................
    pt  É o plano, e é um plano estranho de se ter, porque sucesso significa nunca descobrir.
    >>  ............................................
  dialogue.conversations.scene.work.mason.bad_stone.succeeded.acknowledged/2   [119 chars]
    en  If it does, nobody will look at it once. A wall that lasts is invisible, and I have made my peace with being invisible.
    >>  ............................................
    pt  Se durar, ninguém vai olhar uma vez sequer. Um muro que dura é invisível, e eu fiz as pazes com ser invisível.
    >>  ............................................
  dialogue.conversations.scene.work.mason.bad_stone.succeeded.acknowledged/3   [132 chars]
    en  Thank you. My name is on nothing. There is a mason's mark on the third course from the bottom, and that is the whole of my monument.
    >>  ............................................
    pt  Obrigada. Meu nome não está em nada. Tem uma marca de pedreira na terceira fiada de baixo, e é esse o meu monumento inteiro.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the course."

*stance family `exit` · tone `plain` · answers the beat(s) `work.mason.bad_stone.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.bad_stone.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mason.bad_stone.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mason.bad_stone.succeeded.respond.leave   [36 chars]
    en  I'll let you get back to the course.
    >>  ............................................
    pt  Vou deixar você voltar à fiada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the course."
       spoken on: conversations.scene.work.mason.bad_stone.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.left`: the villager accepts. Subject `work.mason.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mason.bad_stone.blocked.respond / leave; conversations.scene.work.mason.followup / leave; conversations.scene.work.mason.quick_apprentice.active.respond / leave; conversations.scene.work.mason.quick_apprentice.succeeded.respond / leave; conversations.scene.work.mason.rushed_foundation.blocked.respond / leave; conversations.scene.work.mason.rushed_foundation.succeeded.respond / leave; conversations.topic.work.mason.craft.respond / leave; conversations.topic.work.mason.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.mason.bad_stone.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.mason.followup`

**Reached from 10 route(s):** `conversations.scene.work.mason.bad_stone.blocked.respond` / `ask_about_using_it`; `conversations.scene.work.mason.bad_stone.blocked.respond` / `offer_stone`; `conversations.scene.work.mason.bad_stone.succeeded.respond` / `admire_the_wall`; `conversations.scene.work.mason.quick_apprentice.active.respond` / `ask_how_to_teach_it`; `conversations.scene.work.mason.quick_apprentice.active.respond` / `advise_letting_them_fail`; `conversations.scene.work.mason.quick_apprentice.succeeded.respond` / `note_the_teaching`; `conversations.scene.work.mason.rushed_foundation.blocked.respond` / `ask_what_happens`; `conversations.scene.work.mason.rushed_foundation.blocked.respond` / `back_her_judgement`; `conversations.scene.work.mason.rushed_foundation.blocked.respond` / `sympathise`; `conversations.scene.work.mason.rushed_foundation.succeeded.respond` / `ask_about_the_long_view`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.mason.bad_stone.blocked.accepted` — e.g. "Then the courses go up this week and I stop having the same argument with myself every morning."
- `conversations.scene.work.mason.bad_stone.blocked.explained` — e.g. "Fill, yes. Facing, never. The trouble is that fill and facing look the same in a cart and only differ in ten years."
- `conversations.scene.work.mason.bad_stone.succeeded.acknowledged` — e.g. "That is the plan, and it is a strange plan to hold, because success means never finding out."
- `conversations.scene.work.mason.quick_apprentice.active.accepted` — e.g. "On the garden wall, where nothing depends on it. Then we take it down together in a month and I say nothing while we do."
- `conversations.scene.work.mason.quick_apprentice.active.explained` — e.g. "You cannot. You can only show somebody a failure, and failures in this trade arrive eleven years late."
- `conversations.scene.work.mason.quick_apprentice.succeeded.acknowledged` — e.g. "One gesture at a time. It took my own teacher nine years and I thought she was slow, and now I understand she was on schedule."
- `conversations.scene.work.mason.rushed_foundation.blocked.acknowledged` — e.g. "It is the position of the trade. Everything I build is judged by people who will be dead before it is tested."
- `conversations.scene.work.mason.rushed_foundation.blocked.explained` — e.g. "A crack from the corner, thin as a hair, and then a wet winter, and then %2$s is a repair that costs four times the building."
- `conversations.scene.work.mason.rushed_foundation.blocked.steadied` — e.g. "Then I lose the work and somebody worse takes it, and the wall goes up anyway. I have thought about that a great deal."
- `conversations.scene.work.mason.rushed_foundation.succeeded.answered` — e.g. "It is the best part. I am one course in a wall that a dozen people will build, and being one course is a perfectly good thing to be."


```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.mason.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.mason.followup   [24 chars]
    en  Was there anything else?
    >>  ............................................
    pt  Tinha mais alguma outra coisa?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of laying a wall?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.mason.*` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mason.followup.ask_more` — accepted phrasings: "whats the hardest part of laying a wall"; "what is the hardest part of laying a wall"; "hardest thing about laying a wall"
  - the message must contain one of: `hardest`, `wall`
  - scored words: `hardest`(1.8), `wall`(1.8), `whats`(0.8), `part`(0.8), `laying`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mason.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mason.followup.ask_more   [41 chars]
    en  What's the hardest part of laying a wall?
    >>  ............................................
    pt  Qual é a parte mais difícil de levantar um muro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mason.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mason.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which building are you proudest of?" | "Solid ground."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of laying a wall?"
       spoken on: conversations.scene.work.mason.followup, button `ask_more`
       leaves the player on: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.hard`: the villager explains. Subject `work.mason.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.mason.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.mason.hard/1   [91 chars]
    en  Whether it was laid by someone in a hurry. You can always tell, and it's always the corner.
    >>  ............................................
    pt  Se foi assentada por alguém com pressa. Dá sempre pra ver, e é sempre no canto.
    >>  ............................................
  dialogue.conversations.work.prof.mason.hard/2   [96 chars]
    en  Cracks that mean frost and cracks that mean the ground is moving. Only one of those keeps me up.
    >>  ............................................
    pt  Rachadura que quer dizer geada e rachadura que quer dizer que o chão se mexe. Só uma me tira o sono.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the stone."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.mason.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mason.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mason.followup.leave   [28 chars]
    en  I'll leave you to the stone.
    >>  ............................................
    pt  Vou deixar você com a pedra.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the stone."
       spoken on: conversations.scene.work.mason.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.left`: the villager accepts. Subject `work.mason.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mason.bad_stone.blocked.respond / leave; conversations.scene.work.mason.bad_stone.succeeded.respond / leave; conversations.scene.work.mason.quick_apprentice.active.respond / leave; conversations.scene.work.mason.quick_apprentice.succeeded.respond / leave; conversations.scene.work.mason.rushed_foundation.blocked.respond / leave; conversations.scene.work.mason.rushed_foundation.succeeded.respond / leave; conversations.topic.work.mason.craft.respond / leave; conversations.topic.work.mason.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.mason.bad_stone.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.mason.quick_apprentice.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.mason.quick_apprentice.active` — e.g. "I have %2$s who can lay twice what I can in a day and will not check a single course with a line."


```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.quick_apprentice.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.mason.quick_apprentice.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.mason.quick_apprentice.active.respond   [15 chars]
    en  The apprentice.
    >>  ............................................
    pt  O aprendiz.
    >>  ............................................
```


### Button `ask_how_to_teach_it` — "How do you teach patience?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mason.quick_apprentice.active` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mason.quick_apprentice.active.ask_how_to_teach_it` — accepted phrasings: "how do you teach patience"; "how do you teach patience"; "can patience even be taught"
  - the message must contain one of: `patience`, `teach`
  - scored words: `patience`(1.8), `teach`(1.8), `even`(0.8), `taught`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.quick_apprentice.active.respond.ask_how_to_teach_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mason.quick_apprentice.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mason.quick_apprentice.active.respond.ask_how_to_teach_it   [26 chars]
    en  How do you teach patience?
    >>  ............................................
    pt  Como se ensina paciência?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.mason.apprentices`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.mason.quick_apprentice"}
- Then opens: `conversations.scene.work.mason.followup`
- …where the player's next choices will be: "What's the hardest part of laying a wall?" | "I'll leave you to the stone."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.quick_apprentice.active.explained
WHO    VILLAGER — what the player reads after pressing "How do you teach patience?"
       spoken on: conversations.scene.work.mason.quick_apprentice.active.respond, button `ask_how_to_teach_it`
       leaves the player on: conversations.scene.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.quick_apprentice.active.explained`: the villager explains. Subject `work.mason.apprentices`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mason.quick_apprentice.active.explained/1   [102 chars]
    en  You cannot. You can only show somebody a failure, and failures in this trade arrive eleven years late.
    >>  ............................................
    pt  Não se ensina. Só dá para mostrar um fracasso a alguém, e neste ofício os fracassos chegam onze anos atrasados.
    >>  ............................................
  dialogue.conversations.scene.work.mason.quick_apprentice.active.explained/2   [114 chars]
    en  I take them to the granary and show them a crack and say who built it, and then I say she was quicker than me too.
    >>  ............................................
    pt  Levo ao celeiro, mostro uma trinca e digo quem construiu, e depois digo que ela também era mais rápida que eu.
    >>  ............................................
  dialogue.conversations.scene.work.mason.quick_apprentice.active.explained/3   [121 chars]
    en  Slowly, and by making them pull down their own good work once. It is cruel and it is the only thing that has ever worked.
    >>  ............................................
    pt  Devagar, e fazendo desmanchar o próprio bom trabalho uma vez. É cruel e é a única coisa que já funcionou.
    >>  ............................................
```


### Button `advise_letting_them_fail` — "Let them build one badly and see it."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.mason.quick_apprentice.active` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mason.quick_apprentice.active.advise_letting_them_fail` — accepted phrasings: "let them build one badly and see it"; "let them build one badly and see it"; "let the apprentice see their own error"
  - the message must contain one of: `badly`, `apprentice`, `error`
  - scored words: `badly`(1.8), `apprentice`(1.8), `error`(1.8), `let`(0.8), `build`(0.8), `one`(0.8), `see`(0.8), `their`(0.8), `own`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.quick_apprentice.active.respond.advise_letting_them_fail
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mason.quick_apprentice.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mason.quick_apprentice.active.respond.advise_letting_them_fail   [36 chars]
    en  Let them build one badly and see it.
    >>  ............................................
    pt  Deixe construir um errado e ver.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.mason.apprentices`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.mason.quick_apprentice"}
- Then opens: `conversations.scene.work.mason.followup`
- …where the player's next choices will be: "What's the hardest part of laying a wall?" | "I'll leave you to the stone."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.quick_apprentice.active.accepted
WHO    VILLAGER — what the player reads after pressing "Let them build one badly and see it."
       spoken on: conversations.scene.work.mason.quick_apprentice.active.respond, button `advise_letting_them_fail`
       leaves the player on: conversations.scene.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.quick_apprentice.active.accepted`: the villager accepts. Subject `work.mason.apprentices`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mason.quick_apprentice.active.accepted/1   [120 chars]
    en  On the garden wall, where nothing depends on it. Then we take it down together in a month and I say nothing while we do.
    >>  ............................................
    pt  No muro da horta, onde nada depende disso. Aí desmanchamos juntos daqui a um mês e eu não digo nada enquanto isso.
    >>  ............................................
  dialogue.conversations.scene.work.mason.quick_apprentice.active.accepted/2   [104 chars]
    en  Yes. I have been correcting before the error lands, which protects the wall and teaches nobody anything.
    >>  ............................................
    pt  Sim. Eu venho corrigindo antes do erro acontecer, o que protege o muro e não ensina nada a ninguém.
    >>  ............................................
  dialogue.conversations.scene.work.mason.quick_apprentice.active.accepted/3   [111 chars]
    en  That is how I learned. I have been trying to spare them it, which is affection getting in the way of the trade.
    >>  ............................................
    pt  Foi assim que eu aprendi. Ando tentando poupar deles, o que é afeto atrapalhando o ofício.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the course."

*stance family `exit` · tone `plain` · answers the beat(s) `work.mason.quick_apprentice.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.quick_apprentice.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mason.quick_apprentice.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mason.quick_apprentice.active.respond.leave   [36 chars]
    en  I'll let you get back to the course.
    >>  ............................................
    pt  Vou deixar você voltar à fiada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the course."
       spoken on: conversations.scene.work.mason.quick_apprentice.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.left`: the villager accepts. Subject `work.mason.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mason.bad_stone.blocked.respond / leave; conversations.scene.work.mason.bad_stone.succeeded.respond / leave; conversations.scene.work.mason.followup / leave; conversations.scene.work.mason.quick_apprentice.succeeded.respond / leave; conversations.scene.work.mason.rushed_foundation.blocked.respond / leave; conversations.scene.work.mason.rushed_foundation.succeeded.respond / leave; conversations.topic.work.mason.craft.respond / leave; conversations.topic.work.mason.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.mason.bad_stone.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.mason.quick_apprentice.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.mason.quick_apprentice.succeeded` — e.g. "We took the garden wall down together. %2$s did not say a word for an hour and now checks every course."


```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.quick_apprentice.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.mason.quick_apprentice.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.mason.quick_apprentice.succeeded.respond   [16 chars]
    en  Your apprentice.
    >>  ............................................
    pt  Seu aprendiz.
    >>  ............................................
```


### Button `note_the_teaching` — "That's the trade passed on."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.mason.quick_apprentice.succeeded` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mason.quick_apprentice.succeeded.note_the_teaching` — accepted phrasings: "thats the trade passed on"; "that is the trade passed on"; "you handed the craft over properly"
  - the message must contain one of: `passed`, `handed`
  - scored words: `passed`(1.8), `handed`(1.8), `thats`(0.8), `trade`(0.8), `craft`(0.8), `over`(0.8), `properly`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.quick_apprentice.succeeded.respond.note_the_teaching
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mason.quick_apprentice.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mason.quick_apprentice.succeeded.respond.note_the_teaching   [27 chars]
    en  That's the trade passed on.
    >>  ............................................
    pt  É o ofício passado adiante.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +3  _(recorded under topic `work.mason.apprentices`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.mason.quick_apprentice"}
- Then opens: `conversations.scene.work.mason.followup`
- …where the player's next choices will be: "What's the hardest part of laying a wall?" | "I'll leave you to the stone."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.quick_apprentice.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "That's the trade passed on."
       spoken on: conversations.scene.work.mason.quick_apprentice.succeeded.respond, button `note_the_teaching`
       leaves the player on: conversations.scene.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.quick_apprentice.succeeded.acknowledged`: the villager accepts. Subject `work.mason.apprentices`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mason.quick_apprentice.succeeded.acknowledged/1   [126 chars]
    en  One gesture at a time. It took my own teacher nine years and I thought she was slow, and now I understand she was on schedule.
    >>  ............................................
    pt  Um gesto por vez. Minha mestra levou nove anos e eu achava que era lenta, e agora entendo que estava no prazo.
    >>  ............................................
  dialogue.conversations.scene.work.mason.quick_apprentice.succeeded.acknowledged/2   [107 chars]
    en  Thank you. It is the only work I do that I will see finished, which is a strange consolation and I take it.
    >>  ............................................
    pt  Obrigada. É o único trabalho meu que vou ver terminado, o que é um consolo estranho e eu aceito.
    >>  ............................................
  dialogue.conversations.scene.work.mason.quick_apprentice.succeeded.acknowledged/3   [125 chars]
    en  The wall we pulled down was better than anything I built at that age. That is what made it a lesson rather than a punishment.
    >>  ............................................
    pt  O muro que desmanchamos era melhor do que qualquer coisa que eu construí naquela idade. Foi isso que fez daquilo uma lição e não um castigo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the course."

*stance family `exit` · tone `plain` · answers the beat(s) `work.mason.quick_apprentice.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.quick_apprentice.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mason.quick_apprentice.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mason.quick_apprentice.succeeded.respond.leave   [36 chars]
    en  I'll let you get back to the course.
    >>  ............................................
    pt  Vou deixar você voltar à fiada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the course."
       spoken on: conversations.scene.work.mason.quick_apprentice.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.left`: the villager accepts. Subject `work.mason.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mason.bad_stone.blocked.respond / leave; conversations.scene.work.mason.bad_stone.succeeded.respond / leave; conversations.scene.work.mason.followup / leave; conversations.scene.work.mason.quick_apprentice.active.respond / leave; conversations.scene.work.mason.rushed_foundation.blocked.respond / leave; conversations.scene.work.mason.rushed_foundation.succeeded.respond / leave; conversations.topic.work.mason.craft.respond / leave; conversations.topic.work.mason.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.mason.bad_stone.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.mason.rushed_foundation.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.mason.rushed_foundation.blocked` — e.g. "They want %2$s up by the festival and there is %3$s, and I have said so twice in front of witnesses."


```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.rushed_foundation.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.mason.rushed_foundation.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.mason.rushed_foundation.blocked.respond   [15 chars]
    en  The foundation.
    >>  ............................................
    pt  A fundação.
    >>  ............................................
```


### Button `ask_what_happens` — "What happens in four years?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mason.rushed_foundation.blocked` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mason.rushed_foundation.blocked.ask_what_happens` — accepted phrasings: "what happens in four years"; "what happens in four years"; "how does it fail later on"
  - the message must contain one of: `years`, `fail`
  - scored words: `years`(1.8), `fail`(1.8), `happens`(0.8), `four`(0.8), `does`(0.8), `later`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.rushed_foundation.blocked.respond.ask_what_happens
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mason.rushed_foundation.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mason.rushed_foundation.blocked.respond.ask_what_happens   [27 chars]
    en  What happens in four years?
    >>  ............................................
    pt  O que acontece em quatro anos?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.mason.foundations`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.mason.rushed_foundation"}
- Then opens: `conversations.scene.work.mason.followup`
- …where the player's next choices will be: "What's the hardest part of laying a wall?" | "I'll leave you to the stone."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.rushed_foundation.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What happens in four years?"
       spoken on: conversations.scene.work.mason.rushed_foundation.blocked.respond, button `ask_what_happens`
       leaves the player on: conversations.scene.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.rushed_foundation.blocked.explained`: the villager explains. Subject `work.mason.foundations`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mason.rushed_foundation.blocked.explained/1   [125 chars]
    en  A crack from the corner, thin as a hair, and then a wet winter, and then %2$s is a repair that costs four times the building.
    >>  ............................................
    pt  Uma trinca no canto, fina como um fio, depois um inverno chuvoso, e aí %2$s vira um conserto que custa quatro vezes a obra.
    >>  ............................................
  dialogue.conversations.scene.work.mason.rushed_foundation.blocked.explained/2   [105 chars]
    en  Nothing dramatic. One corner drops a finger's width. Doors stop shutting. Everybody blames the carpenter.
    >>  ............................................
    pt  Nada dramático. Um canto cede a largura de um dedo. As portas param de fechar. Todo mundo culpa o carpinteiro.
    >>  ............................................
  dialogue.conversations.scene.work.mason.rushed_foundation.blocked.explained/3   [133 chars]
    en  I will be blamed, and I will deserve some of it, because a mason who builds what she was told to build is still the one who built it.
    >>  ............................................
    pt  Vou ser culpada, e vou merecer parte, porque uma pedreira que constrói o que mandaram continua sendo quem construiu.
    >>  ............................................
```


### Button `back_her_judgement` — "Refuse to build it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.mason.rushed_foundation.blocked` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mason.rushed_foundation.blocked.back_her_judgement` — accepted phrasings: "refuse to build it that way"; "refuse to build it that way"; "hold the line on the footing"
  - the message must contain one of: `refuse`, `hold`, `footing`
  - scored words: `refuse`(1.8), `hold`(1.8), `footing`(1.8), `build`(0.8), `way`(0.8), `line`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.rushed_foundation.blocked.respond.back_her_judgement
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mason.rushed_foundation.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mason.rushed_foundation.blocked.respond.back_her_judgement   [28 chars]
    en  Refuse to build it that way.
    >>  ............................................
    pt  Recuse construir assim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.mason.foundation.backed`, budget `standard`, replay policy `once`
- Does: disposition — respect +4, trust +2  _(recorded under topic `work.mason.foundations`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.mason.rushed_foundation"}
- Then opens: `conversations.scene.work.mason.followup`
- …where the player's next choices will be: "What's the hardest part of laying a wall?" | "I'll leave you to the stone."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.rushed_foundation.blocked.steadied
WHO    VILLAGER — what the player reads after pressing "Refuse to build it that way."
       spoken on: conversations.scene.work.mason.rushed_foundation.blocked.respond, button `back_her_judgement`
       leaves the player on: conversations.scene.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.rushed_foundation.blocked.steadied`: the villager accepts. Subject `work.mason.foundations`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mason.rushed_foundation.blocked.steadied/1   [118 chars]
    en  Then I lose the work and somebody worse takes it, and the wall goes up anyway. I have thought about that a great deal.
    >>  ............................................
    pt  Aí eu perco a obra e alguém pior pega, e o muro sobe do mesmo jeito. Pensei muito nisso.
    >>  ............................................
  dialogue.conversations.scene.work.mason.rushed_foundation.blocked.steadied/2   [133 chars]
    en  I will say it a third time, in writing, and I will build it properly or I will build nothing. That is the only version I can live in.
    >>  ............................................
    pt  Vou dizer uma terceira vez, por escrito, e construo direito ou não construo nada. É a única versão em que eu consigo viver.
    >>  ............................................
  dialogue.conversations.scene.work.mason.rushed_foundation.blocked.steadied/3   [132 chars]
    en  Yes. And if they find another mason, at least the record will say I told them, and in six years the record is all anybody will have.
    >>  ............................................
    pt  Sim. E se acharem outro pedreiro, ao menos o registro vai dizer que eu avisei, e em seis anos o registro é tudo o que vai restar.
    >>  ............................................
```


### Button `sympathise` — "That's a rotten position to be in."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.mason.rushed_foundation.blocked` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mason.rushed_foundation.blocked.sympathise` — accepted phrasings: "thats a rotten position to be in"; "that is a rotten position to be in"; "you are stuck in a bad position"
  - the message must contain one of: `position`, `rotten`, `stuck`
  - scored words: `position`(1.8), `rotten`(1.8), `stuck`(1.8), `thats`(0.8), `bad`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.rushed_foundation.blocked.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mason.rushed_foundation.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mason.rushed_foundation.blocked.respond.sympathise   [34 chars]
    en  That's a rotten position to be in.
    >>  ............................................
    pt  É uma posição horrível.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `work.mason.foundations`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.mason.rushed_foundation"}
- Then opens: `conversations.scene.work.mason.followup`
- …where the player's next choices will be: "What's the hardest part of laying a wall?" | "I'll leave you to the stone."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.rushed_foundation.blocked.acknowledged
WHO    VILLAGER — what the player reads after pressing "That's a rotten position to be in."
       spoken on: conversations.scene.work.mason.rushed_foundation.blocked.respond, button `sympathise`
       leaves the player on: conversations.scene.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.rushed_foundation.blocked.acknowledged`: the villager accepts. Subject `work.mason.foundations`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mason.rushed_foundation.blocked.acknowledged/1   [109 chars]
    en  It is the position of the trade. Everything I build is judged by people who will be dead before it is tested.
    >>  ............................................
    pt  É a posição do ofício. Tudo o que eu construo é julgado por gente que vai estar morta antes de ser testado.
    >>  ............................................
  dialogue.conversations.scene.work.mason.rushed_foundation.blocked.acknowledged/2   [127 chars]
    en  Thank you. What I mind is not the argument. It is being treated as difficult for saying a thing that is simply true about soil.
    >>  ............................................
    pt  Obrigada. O que me incomoda não é a discussão. É ser tratada como difícil por dizer uma coisa que é simplesmente verdade sobre solo.
    >>  ............................................
  dialogue.conversations.scene.work.mason.rushed_foundation.blocked.acknowledged/3   [104 chars]
    en  I have been in it four times and won twice. Those are honest odds, and I go into the fifth knowing them.
    >>  ............................................
    pt  Já estive nela quatro vezes e ganhei duas. São chances honestas, e vou para a quinta sabendo disso.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the course."

*stance family `exit` · tone `plain` · answers the beat(s) `work.mason.rushed_foundation.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.rushed_foundation.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mason.rushed_foundation.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mason.rushed_foundation.blocked.respond.leave   [36 chars]
    en  I'll let you get back to the course.
    >>  ............................................
    pt  Vou deixar você voltar à fiada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the course."
       spoken on: conversations.scene.work.mason.rushed_foundation.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.left`: the villager accepts. Subject `work.mason.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mason.bad_stone.blocked.respond / leave; conversations.scene.work.mason.bad_stone.succeeded.respond / leave; conversations.scene.work.mason.followup / leave; conversations.scene.work.mason.quick_apprentice.active.respond / leave; conversations.scene.work.mason.quick_apprentice.succeeded.respond / leave; conversations.scene.work.mason.rushed_foundation.succeeded.respond / leave; conversations.topic.work.mason.craft.respond / leave; conversations.topic.work.mason.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.mason.bad_stone.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.mason.rushed_foundation.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.mason.rushed_foundation.succeeded` — e.g. "They gave me the extra fortnight. %2$s is on proper footings and it will be standing when this village has forgotten my name."


```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.rushed_foundation.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.mason.rushed_foundation.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.mason.rushed_foundation.succeeded.respond   [16 chars]
    en  That foundation.
    >>  ............................................
    pt  Aquela fundação.
    >>  ............................................
```


### Button `ask_about_the_long_view` — "Does it bother you not to see them finished?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mason.rushed_foundation.succeeded` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.mason.rushed_foundation.succeeded.ask_about_the_long_view` — accepted phrasings: "does it bother you not to see them finished"; "does it bother you not to see them finished"; "how do you feel about work you never see finished"
  - the message must contain one of: `bother`, `finished`, `feel`
  - scored words: `bother`(1.8), `finished`(1.8), `feel`(1.8), `does`(0.8), `see`(0.8), `work`(0.8), `never`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.rushed_foundation.succeeded.respond.ask_about_the_long_view
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mason.rushed_foundation.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mason.rushed_foundation.succeeded.respond.ask_about_the_long_view   [44 chars]
    en  Does it bother you not to see them finished?
    >>  ............................................
    pt  Incomoda não ver elas prontas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3  _(recorded under topic `work.mason.foundations`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.mason.rushed_foundation"}
- Then opens: `conversations.scene.work.mason.followup`
- …where the player's next choices will be: "What's the hardest part of laying a wall?" | "I'll leave you to the stone."

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.rushed_foundation.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "Does it bother you not to see them finished?"
       spoken on: conversations.scene.work.mason.rushed_foundation.succeeded.respond, button `ask_about_the_long_view`
       leaves the player on: conversations.scene.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.rushed_foundation.succeeded.answered`: the villager explains. Subject `work.mason.foundations`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.mason.rushed_foundation.succeeded.answered/1   [132 chars]
    en  It is the best part. I am one course in a wall that a dozen people will build, and being one course is a perfectly good thing to be.
    >>  ............................................
    pt  É a melhor parte. Sou uma fiada num muro que doze pessoas vão levantar, e ser uma fiada é uma coisa perfeitamente boa de se ser.
    >>  ............................................
  dialogue.conversations.scene.work.mason.rushed_foundation.succeeded.answered/2   [120 chars]
    en  My grandmother laid the church footings. I have stood on them. That is as close to an answer as I have and it is enough.
    >>  ............................................
    pt  Minha avó assentou as fundações da igreja. Já pisei nelas. É o mais perto de uma resposta que eu tenho, e basta.
    >>  ............................................
  dialogue.conversations.scene.work.mason.rushed_foundation.succeeded.answered/3   [122 chars]
    en  What bothers me is being hurried. Never the length. A hundred years is fine; a fortnight taken off the front of it is not.
    >>  ............................................
    pt  O que me incomoda é a pressa. Nunca o prazo. Cem anos está ótimo; duas semanas tiradas do começo, não.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the course."

*stance family `exit` · tone `plain` · answers the beat(s) `work.mason.rushed_foundation.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.mason.rushed_foundation.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.mason.rushed_foundation.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.mason.rushed_foundation.succeeded.respond.leave   [36 chars]
    en  I'll let you get back to the course.
    >>  ............................................
    pt  Vou deixar você voltar à fiada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the course."
       spoken on: conversations.scene.work.mason.rushed_foundation.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.left`: the villager accepts. Subject `work.mason.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mason.bad_stone.blocked.respond / leave; conversations.scene.work.mason.bad_stone.succeeded.respond / leave; conversations.scene.work.mason.followup / leave; conversations.scene.work.mason.quick_apprentice.active.respond / leave; conversations.scene.work.mason.quick_apprentice.succeeded.respond / leave; conversations.scene.work.mason.rushed_foundation.blocked.respond / leave; conversations.topic.work.mason.craft.respond / leave; conversations.topic.work.mason.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.mason.bad_stone.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.mason.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.mason.craft` — e.g. "Stone tells you where it wants to split. Twenty years and I still get it wrong twice a season."


```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.mason.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.mason.craft.respond   [30 chars]
    en  That's how it was handed down.
    >>  ............................................
    pt  Foi assim que passaram adiante.
    >>  ............................................
```


### Button `ask_year` — "Was the year of carrying worth it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mason.craft` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mason.craft.ask_year` — accepted phrasings: "was the year of carrying worth it"
  - the message must contain one of: `carrying`, `worth`, `apprenticeship`
  - scored words: `carrying`(1.5), `worth`(1.2), `apprenticeship`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.craft.respond.ask_year
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.craft.respond.ask_year   [34 chars]
    en  Was the year of carrying worth it?
    >>  ............................................
    pt  O ano carregando valeu?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mason.craft.ask_year`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mason.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which building are you proudest of?" | "Solid ground."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.craft.ask_year
WHO    VILLAGER — what the player reads after pressing "Was the year of carrying worth it?"
       spoken on: conversations.topic.work.mason.craft.respond, button `ask_year`
       leaves the player on: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.craft.ask_year`: the villager explains. Subject `work.mason.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mason.craft.ask_year/1   [71 chars]
    en  Every day of it, and I'd have punched him if you'd said so at the time.
    >>  ............................................
    pt  Cada dia dele, e eu teria batido em você se dissesse isso na época.
    >>  ............................................
  dialogue.conversations.work.prof.mason.craft.ask_year/2   [70 chars]
    en  I do the same to my own now, and they resent me exactly as much, %1$s.
    >>  ............................................
    pt  Faço o mesmo com os meus agora, e me detestam exatamente igual, %1$s.
    >>  ............................................
```


### Button `admire` — "Twenty years and you still admit to getting it wrong."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.mason.craft` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mason.craft.admire` — accepted phrasings: "twenty years and you still admit to getting it wrong"
  - the message must contain one of: `admit`, `honest`, `mistakes`
  - scored words: `admit`(1.5), `honest`(1.2), `mistakes`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.craft.respond.admire   [53 chars]
    en  Twenty years and you still admit to getting it wrong.
    >>  ............................................
    pt  Vinte anos e você ainda admite errar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.mason.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.mason.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mason.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which building are you proudest of?" | "Solid ground."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.craft.admire
WHO    VILLAGER — what the player reads after pressing "Twenty years and you still admit to getting it wrong."
       spoken on: conversations.topic.work.mason.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.craft.admire`: the villager accepts. Subject `work.mason.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mason.craft.admire/1   [77 chars]
    en  A mason who says he doesn't is building something you should not stand under.
    >>  ............................................
    pt  Um pedreiro que diz que não erra está construindo algo sob o qual você não deve ficar.
    >>  ............................................
  dialogue.conversations.work.prof.mason.craft.admire/2   [89 chars]
    en  Twice a season, and I mark each one. The marks are on the yard wall if you want to count.
    >>  ............................................
    pt  Duas por estação, e eu marco cada uma. As marcas estão na parede do pátio, se quiser contar.
    >>  ............................................
```


### Button `ask_split` — "How does stone tell you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mason.craft` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mason.craft.ask_split` — accepted phrasings: "how does stone tell you"
  - the message must contain one of: `split`, `grain`, `listen`
  - scored words: `split`(1.5), `grain`(1.2), `listen`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.craft.respond.ask_split
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.craft.respond.ask_split   [24 chars]
    en  How does stone tell you?
    >>  ............................................
    pt  Como a pedra te diz?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mason.craft.ask_split`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mason.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which building are you proudest of?" | "Solid ground."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.craft.ask_split
WHO    VILLAGER — what the player reads after pressing "How does stone tell you?"
       spoken on: conversations.topic.work.mason.craft.respond, button `ask_split`
       leaves the player on: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.craft.ask_split`: the villager explains. Subject `work.mason.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mason.craft.ask_split/1   [83 chars]
    en  Grain, sound and the way dust sits in the scratch. Mostly sound. Tap it and listen.
    >>  ............................................
    pt  Fibra, som e como a poeira assenta no risco. Principalmente som. Bata e escute.
    >>  ............................................
  dialogue.conversations.work.prof.mason.craft.ask_split/2   [87 chars]
    en  It doesn't, in words. You get a feeling and the feeling is twenty years of being wrong.
    >>  ............................................
    pt  Não diz, em palavras. Você tem uma sensação e a sensação são vinte anos de erro.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the course."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.mason.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.craft.respond.leave   [36 chars]
    en  I'll let you get back to the course.
    >>  ............................................
    pt  Vou deixar você voltar à fiada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the course."
       spoken on: conversations.topic.work.mason.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.left`: the villager accepts. Subject `work.mason.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mason.bad_stone.blocked.respond / leave; conversations.scene.work.mason.bad_stone.succeeded.respond / leave; conversations.scene.work.mason.followup / leave; conversations.scene.work.mason.quick_apprentice.active.respond / leave; conversations.scene.work.mason.quick_apprentice.succeeded.respond / leave; conversations.scene.work.mason.rushed_foundation.blocked.respond / leave; conversations.scene.work.mason.rushed_foundation.succeeded.respond / leave; conversations.topic.work.mason.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.mason.bad_stone.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.mason.followup`

**Reached from 20 route(s):** `conversations.scene.work.mason.followup` / `ask_more`; `conversations.topic.work.mason.craft.respond` / `ask_year`; `conversations.topic.work.mason.craft.respond` / `admire`; `conversations.topic.work.mason.craft.respond` / `ask_split`; `conversations.topic.work.mason.future.respond` / `ask_arch`; `conversations.topic.work.mason.future.respond` / `encourage`; `conversations.topic.work.mason.future.respond` / `ask_only`; `conversations.topic.work.mason.respond` / `ask_hard`; `conversations.topic.work.mason.respond` / `value`; `conversations.topic.work.mason.respond` / `challenge`; `conversations.topic.work.mason.respond` / `challenge`; `conversations.topic.work.mason.risk.respond` / `ask_wall` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.mason.challenge.landed` — e.g. "Stacking isn't. Choosing which stone goes where — that took me eleven years."
- `conversations.work.prof.mason.challenge.stung` — e.g. "...The wall I built at twenty is still standing. Say that again in fifty years."
- `conversations.work.prof.mason.craft.admire` — e.g. "A mason who says he doesn't is building something you should not stand under."
- `conversations.work.prof.mason.craft.ask_split` — e.g. "Grain, sound and the way dust sits in the scratch. Mostly sound. Tap it and listen."
- `conversations.work.prof.mason.craft.ask_year` — e.g. "Every day of it, and I'd have punched him if you'd said so at the time."
- `conversations.work.prof.mason.future.ask_arch` — e.g. "Because it's the one thing in the trade where the stone does the thinking and I only arrange it."
- `conversations.work.prof.mason.future.ask_only` — e.g. "Not within four valleys. I've asked. It is a slow, cold, unglamorous trade and I understand."
- `conversations.work.prof.mason.future.encourage` — e.g. "...Both. I'd been treating it as a choice and it might not be one. Thank you for that."
- `conversations.work.prof.mason.hard` — e.g. "Whether it was laid by someone in a hurry. You can always tell, and it's always the corner."
- `conversations.work.prof.mason.risk.ask_outlive` — e.g. "Both ways. The well I cut at twenty is still there and so is the crooked lintel."
- `conversations.work.prof.mason.risk.ask_wall` — e.g. "Bad footing on soft ground. Ordinary, avoidable, and it took a woman and her loom."
- `conversations.work.prof.mason.risk.sympathise` — e.g. "...It's the only way the trade gets better. Somebody has to go and look."
- `conversations.work.prof.mason.task.ask_identical` — e.g. "Because beautiful only has to satisfy me. Identical has to satisfy the next five stones."
- `conversations.work.prof.mason.task.ask_mortar` — e.g. "It gives you two years of warning and then no warning at all. That's a well shaft."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.mason.followup   [37 chars]
    en  That's stone, and the patience of it.
    >>  ............................................
    pt  É pedra, e a paciência dela.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.mason.challenge.landed`, `work.mason.challenge.stung`, `work.mason.craft.admire`, `work.mason.craft.ask_split`, `work.mason.craft.ask_year`, `work.mason.future.ask_arch`, `work.mason.future.ask_only`, `work.mason.future.encourage`, `work.mason.hard`, `work.mason.risk.ask_outlive`, `work.mason.risk.ask_wall`, `work.mason.risk.sympathise`, `work.mason.task.ask_identical`, `work.mason.task.ask_mortar`, `work.mason.task.offer_hands`, `work.mason.value`, `work.mason.village.ask_names`, `work.mason.village.ask_twice`, `work.mason.village.say_thanks` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.mason.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `compliment`, `stone`
  - scored words: `thought`(1.2), `compliment`(1.2), `stone`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.mason.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.mason.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.mason.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.mason.thanks`: the villager accepts. Subject `work.mason.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mason.thanks/1   [69 chars]
    en  Nobody looks at a wall that's working. That's the compliment, really.
    >>  ............................................
    pt  Ninguém olha pra uma parede que está funcionando. É esse o elogio, na verdade.
    >>  ............................................
  dialogue.conversations.work.prof.mason.thanks/2   [77 chars]
    en  Stone gets thought about twice: when it goes up and when it comes down, %1$s.
    >>  ............................................
    pt  Pensa-se em pedra duas vezes: quando sobe e quando cai, %1$s.
    >>  ............................................
```


### Button `ask_more` — "Which building are you proudest of?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mason.challenge.landed`, `work.mason.challenge.stung`, `work.mason.craft.admire`, `work.mason.craft.ask_split`, `work.mason.craft.ask_year`, `work.mason.future.ask_arch`, `work.mason.future.ask_only`, `work.mason.future.encourage`, `work.mason.hard`, `work.mason.risk.ask_outlive`, `work.mason.risk.ask_wall`, `work.mason.risk.sympathise`, `work.mason.task.ask_identical`, `work.mason.task.ask_mortar`, `work.mason.task.offer_hands`, `work.mason.value`, `work.mason.village.ask_names`, `work.mason.village.ask_twice`, `work.mason.village.say_thanks` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.mason.more` — accepted phrasings: "which building are you proudest of"
  - the message must contain one of: `proudest`, `building`
  - scored words: `proudest`(1.5), `building`(1.2), `best`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.followup.ask_more   [35 chars]
    en  Which building are you proudest of?
    >>  ............................................
    pt  De qual construção você tem mais orgulho?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.mason.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.more
WHO    VILLAGER — what the player reads after pressing "Which building are you proudest of?"
       spoken on: conversations.topic.work.mason.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.mason.more`: the villager discloses. Subject `work.mason.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mason.more/1   [84 chars]
    en  The well house. Nothing to look at. It has never once let water in, in thirty years.
    >>  ............................................
    pt  A casa do poço. Nada de especial. Nunca deixou entrar água, em trinta anos.
    >>  ............................................
  dialogue.conversations.work.prof.mason.more/2   [88 chars]
    en  Not a building — a corner. Third house on the square. You'd walk past it and I never do.
    >>  ............................................
    pt  Não é uma construção — é um canto. Terceira casa da praça. Você passaria batido e eu nunca passo.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.mason.more/1
    en  The well house. It has kept water out for thirty years and I still check it in heavy rain.
    >>  ............................................
    pt  A casa do poço. Manteve a água fora por trinta anos e eu ainda confiro em chuva forte.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.mason.more/2
    en  The abutment. If I don't set it, nobody within four valleys will, and I'm fifty-one.
    >>  ............................................
    pt  O encontro. Se eu não assentar, ninguém em quatro vales vai, e eu tenho cinquenta e um.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.mason.more/1
    en  The well house. Thirty years and no water. Slow work outlasts the people who complained about it.
    >>  ............................................
    pt  A casa do poço. Trinta anos e sem água. Trabalho lento sobrevive a quem reclamou dele.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.mason.more/2
    en  The abutment, and then the arch. In that order, at that pace, and neither will be hurried.
    >>  ............................................
    pt  O encontro, e depois o arco. Nessa ordem, nesse ritmo, e nenhum vai ser apressado.
    >>  ............................................
  confident.dialogue.conversations.work.prof.mason.more/1
    en  The well house. Nothing to look at. It has never once let water in, in thirty years.
    >>  ............................................
    pt  A casa do poço. Nada de bonito. Nunca deixou entrar água, em trinta anos.
    >>  ............................................
  confident.dialogue.conversations.work.prof.mason.more/2
    en  The bridge needs its second abutment and I'm the only one here who could set it. I'm fifty-one.
    >>  ............................................
    pt  A ponte precisa do segundo encontro e eu sou o único aqui que saberia assentar. Tenho cinquenta e um.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.mason.more/1
    en  The well house. Nothing to look at. It has never once let water in, in thirty years.
    >>  ............................................
    pt  A casa do poço. Nada de bonito. Nunca deixou entrar água, em trinta anos.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.mason.more/2
    en  The bridge needs its second abutment and I'm the only one here who could set it. I'm fifty-one.
    >>  ............................................
    pt  A ponte precisa do segundo encontro e eu sou o único aqui que saberia assentar. Tenho cinquenta e um.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.mason.more/1
    en  The well house. Go and look at it — you'll see nothing at all, which is exactly the point.
    >>  ............................................
    pt  A casa do poço. Vá olhar — você não vai ver nada, o que é exatamente a questão.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.mason.more/2
    en  The bridge abutment. I'd want somebody standing beside me for that one, and there's nobody.
    >>  ............................................
    pt  O encontro da ponte. Eu queria alguém ao meu lado nessa, e não tem ninguém.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.mason.more/1
    en  The well house. Go and look at it — you'll see nothing at all, which is exactly the point.
    >>  ............................................
    pt  A casa do poço. Vá olhar — você não vai ver nada, o que é exatamente a questão.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.mason.more/2
    en  The bridge abutment. I'd want somebody standing beside me for that one, and there's nobody.
    >>  ............................................
    pt  O encontro da ponte. Eu queria alguém ao meu lado nessa, e não tem ninguém.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.mason.more/1
    en  The well house. Go and look at it — you'll see nothing at all, which is exactly the point.
    >>  ............................................
    pt  A casa do poço. Vá olhar — você não vai ver nada, o que é exatamente a questão.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.mason.more/2
    en  The bridge abutment. I'd want somebody standing beside me for that one, and there's nobody.
    >>  ............................................
    pt  O encontro da ponte. Eu queria alguém ao meu lado nessa, e não tem ninguém.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.mason.more/1
    en  The well house. It has kept water out for thirty years and I still check it in heavy rain.
    >>  ............................................
    pt  A casa do poço. Manteve a água fora por trinta anos e eu ainda confiro em chuva forte.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.mason.more/2
    en  The abutment. If I don't set it, nobody within four valleys will, and I'm fifty-one.
    >>  ............................................
    pt  O encontro. Se eu não assentar, ninguém em quatro vales vai, e eu tenho cinquenta e um.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.mason.more/1
    en  The well house. Nothing to look at. It has never once let water in, in thirty years.
    >>  ............................................
    pt  A casa do poço. Nada de bonito. Nunca deixou entrar água, em trinta anos.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.mason.more/2
    en  The bridge needs its second abutment and I'm the only one here who could set it. I'm fifty-one.
    >>  ............................................
    pt  A ponte precisa do segundo encontro e eu sou o único aqui que saberia assentar. Tenho cinquenta e um.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.mason.more/1
    en  The well house. Nothing to look at. It has never once let water in, in thirty years.
    >>  ............................................
    pt  A casa do poço. Nada de bonito. Nunca deixou entrar água, em trinta anos.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.mason.more/2
    en  The bridge needs its second abutment and I'm the only one here who could set it. I'm fifty-one.
    >>  ............................................
    pt  A ponte precisa do segundo encontro e eu sou o único aqui que saberia assentar. Tenho cinquenta e um.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.mason.more/1
    en  The well house. Thirty years, no water. Nobody has ever remarked on it and that is correct.
    >>  ............................................
    pt  A casa do poço. Trinta anos, sem água. Ninguém nunca comentou e está correto.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.mason.more/2
    en  An arch, over the lane. Stone doing the thinking and me only arranging it.
    >>  ............................................
    pt  Um arco, sobre a viela. A pedra pensando e eu só arranjando.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.mason.more/1
    en  The well house. Thirty years and no water. Slow work outlasts the people who complained about it.
    >>  ............................................
    pt  A casa do poço. Trinta anos e sem água. Trabalho lento sobrevive a quem reclamou dele.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.mason.more/2
    en  The abutment, and then the arch. In that order, at that pace, and neither will be hurried.
    >>  ............................................
    pt  O encontro, e depois o arco. Nessa ordem, nesse ritmo, e nenhum vai ser apressado.
    >>  ............................................
  odd.dialogue.conversations.work.prof.mason.more/1
    en  The well house. Thirty years, no water. Nobody has ever remarked on it and that is correct.
    >>  ............................................
    pt  A casa do poço. Trinta anos, sem água. Ninguém nunca comentou e está correto.
    >>  ............................................
  odd.dialogue.conversations.work.prof.mason.more/2
    en  An arch, over the lane. Stone doing the thinking and me only arranging it.
    >>  ............................................
    pt  Um arco, sobre a viela. A pedra pensando e eu só arranjando.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.mason.more/1
    en  The well house. Thirty years and no water. Slow work outlasts the people who complained about it.
    >>  ............................................
    pt  A casa do poço. Trinta anos e sem água. Trabalho lento sobrevive a quem reclamou dele.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.mason.more/2
    en  The abutment, and then the arch. In that order, at that pace, and neither will be hurried.
    >>  ............................................
    pt  O encontro, e depois o arco. Nessa ordem, nesse ritmo, e nenhum vai ser apressado.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.mason.more/1
    en  The well house! Utterly plain and it has never let a drop in, in thirty years. That's my masterpiece.
    >>  ............................................
    pt  A casa do poço! Completamente simples e nunca deixou entrar uma gota, em trinta anos. É minha obra-prima.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.mason.more/2
    en  An arch. Nobody needs one. I want one. Those two facts have been fighting for years.
    >>  ............................................
    pt  Um arco. Ninguém precisa. Eu quero. Esses dois fatos brigam há anos.
    >>  ............................................
  playful.dialogue.conversations.work.prof.mason.more/1
    en  The well house! Utterly plain and it has never let a drop in, in thirty years. That's my masterpiece.
    >>  ............................................
    pt  A casa do poço! Completamente simples e nunca deixou entrar uma gota, em trinta anos. É minha obra-prima.
    >>  ............................................
  playful.dialogue.conversations.work.prof.mason.more/2
    en  An arch. Nobody needs one. I want one. Those two facts have been fighting for years.
    >>  ............................................
    pt  Um arco. Ninguém precisa. Eu quero. Esses dois fatos brigam há anos.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.mason.more/1
    en  The well house. Thirty years and no water. Slow work outlasts the people who complained about it.
    >>  ............................................
    pt  A casa do poço. Trinta anos e sem água. Trabalho lento sobrevive a quem reclamou dele.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.mason.more/2
    en  The abutment, and then the arch. In that order, at that pace, and neither will be hurried.
    >>  ............................................
    pt  O encontro, e depois o arco. Nessa ordem, nesse ritmo, e nenhum vai ser apressado.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.mason.more/1
    en  The well house. It has kept water out for thirty years and I still check it in heavy rain.
    >>  ............................................
    pt  A casa do poço. Manteve a água fora por trinta anos e eu ainda confiro em chuva forte.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.mason.more/2
    en  The abutment. If I don't set it, nobody within four valleys will, and I'm fifty-one.
    >>  ............................................
    pt  O encontro. Se eu não assentar, ninguém em quatro vales vai, e eu tenho cinquenta e um.
    >>  ............................................
  shy.dialogue.conversations.work.prof.mason.more/1
    en  The well house. Thirty years, no water. Nobody has ever remarked on it and that is correct.
    >>  ............................................
    pt  A casa do poço. Trinta anos, sem água. Ninguém nunca comentou e está correto.
    >>  ............................................
  shy.dialogue.conversations.work.prof.mason.more/2
    en  An arch, over the lane. Stone doing the thinking and me only arranging it.
    >>  ............................................
    pt  Um arco, sobre a viela. A pedra pensando e eu só arranjando.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.mason.more/1
    en  The well house! Utterly plain and it has never let a drop in, in thirty years. That's my masterpiece.
    >>  ............................................
    pt  A casa do poço! Completamente simples e nunca deixou entrar uma gota, em trinta anos. É minha obra-prima.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.mason.more/2
    en  An arch. Nobody needs one. I want one. Those two facts have been fighting for years.
    >>  ............................................
    pt  Um arco. Ninguém precisa. Eu quero. Esses dois fatos brigam há anos.
    >>  ............................................
  witty.dialogue.conversations.work.prof.mason.more/1
    en  The well house! Utterly plain and it has never let a drop in, in thirty years. That's my masterpiece.
    >>  ............................................
    pt  A casa do poço! Completamente simples e nunca deixou entrar uma gota, em trinta anos. É minha obra-prima.
    >>  ............................................
  witty.dialogue.conversations.work.prof.mason.more/2
    en  An arch. Nobody needs one. I want one. Those two facts have been fighting for years.
    >>  ............................................
    pt  Um arco. Ninguém precisa. Eu quero. Esses dois fatos brigam há anos.
    >>  ............................................
```

</details>


### Button `leave` — "Solid ground."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.mason.challenge.landed`, `work.mason.challenge.stung`, `work.mason.craft.admire`, `work.mason.craft.ask_split`, `work.mason.craft.ask_year`, `work.mason.future.ask_arch`, `work.mason.future.ask_only`, `work.mason.future.encourage`, `work.mason.hard`, `work.mason.risk.ask_outlive`, `work.mason.risk.ask_wall`, `work.mason.risk.sympathise`, `work.mason.task.ask_identical`, `work.mason.task.ask_mortar`, `work.mason.task.offer_hands`, `work.mason.value`, `work.mason.village.ask_names`, `work.mason.village.ask_twice`, `work.mason.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.followup.leave   [13 chars]
    en  Solid ground.
    >>  ............................................
    pt  Chão firme.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.leave
WHO    VILLAGER — what the player reads after pressing "Solid ground."
       spoken on: conversations.topic.work.mason.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.left`: the villager accepts. Subject `work.mason.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mason.bad_stone.blocked.respond / leave; conversations.scene.work.mason.bad_stone.succeeded.respond / leave; conversations.scene.work.mason.followup / leave; conversations.scene.work.mason.quick_apprentice.active.respond / leave; conversations.scene.work.mason.quick_apprentice.succeeded.respond / leave; conversations.scene.work.mason.rushed_foundation.blocked.respond / leave; conversations.scene.work.mason.rushed_foundation.succeeded.respond / leave; conversations.topic.work.mason.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.mason.bad_stone.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.mason.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.mason.future` — e.g. "An arch. A real one, over the lane, that holds itself up by argument rather than by mortar."


```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.mason.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.mason.future.respond   [25 chars]
    en  That's what's left to do.
    >>  ............................................
    pt  É o que falta fazer.
    >>  ............................................
```


### Button `ask_arch` — "Why an arch?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mason.future` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mason.future.ask_arch` — accepted phrasings: "why an arch"
  - the message must contain one of: `arch`, `span`
  - scored words: `arch`(1.5), `reason`(0.8), `span`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.future.respond.ask_arch
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.future.respond.ask_arch   [12 chars]
    en  Why an arch?
    >>  ............................................
    pt  Por que um arco?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mason.future.ask_arch`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mason.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which building are you proudest of?" | "Solid ground."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.future.ask_arch
WHO    VILLAGER — what the player reads after pressing "Why an arch?"
       spoken on: conversations.topic.work.mason.future.respond, button `ask_arch`
       leaves the player on: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.future.ask_arch`: the villager explains. Subject `work.mason.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mason.future.ask_arch/1   [96 chars]
    en  Because it's the one thing in the trade where the stone does the thinking and I only arrange it.
    >>  ............................................
    pt  Porque é a única coisa do ofício em que a pedra pensa e eu só arranjo.
    >>  ............................................
  dialogue.conversations.work.prof.mason.future.ask_arch/2   [90 chars]
    en  Because I've wanted one since I was nine and saw one, %1$s, and that is the entire reason.
    >>  ............................................
    pt  Porque eu quero um desde os nove anos, quando vi um, %1$s, e é a razão inteira.
    >>  ............................................
```


### Button `encourage` — "Fifty-one is time enough for both."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.mason.future` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mason.future.encourage` — accepted phrasings: "fifty-one is time enough for both"
  - the message must contain one of: `both`, `fifty`, `enough`
  - scored words: `both`(1.5), `fifty`(1.2), `enough`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.future.respond.encourage   [34 chars]
    en  Fifty-one is time enough for both.
    >>  ............................................
    pt  Cinquenta e um dá tempo pros dois.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.mason.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.mason.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.mason.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which building are you proudest of?" | "Solid ground."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.future.encourage
WHO    VILLAGER — what the player reads after pressing "Fifty-one is time enough for both."
       spoken on: conversations.topic.work.mason.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.future.encourage`: the villager accepts. Subject `work.mason.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mason.future.encourage/1   [86 chars]
    en  ...Both. I'd been treating it as a choice and it might not be one. Thank you for that.
    >>  ............................................
    pt  ...Os dois. Eu vinha tratando como escolha e talvez não seja. Obrigado por isso.
    >>  ............................................
  dialogue.conversations.work.prof.mason.future.encourage/2   [82 chars]
    en  The abutment first. Then the arch, and then whoever comes after can argue with it.
    >>  ............................................
    pt  O encontro primeiro. Depois o arco, e aí quem vier depois que discuta com ele.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both. I made it a choice because a choice is easier to keep postponing.
    >>  ............................................
    pt  ...Os dois. Fiz virar escolha porque escolha é mais fácil de adiar.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.mason.future.encourage/2
    en  The abutment first. Then the arch. Saying the order aloud makes it a thing I've started.
    >>  ............................................
    pt  Contraforte primeiro. Depois o arco. Dizer a ordem em voz alta já é ter começado.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both. Thirty years of walls and I still make choices out of things that aren't.
    >>  ............................................
    pt  ...Os dois. Trinta anos de muros e ainda faço escolhas do que não é escolha.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.mason.future.encourage/2
    en  The abutment first. Then the arch, and it will outlast the argument about it.
    >>  ............................................
    pt  Contraforte primeiro. Depois o arco, e ele vai durar mais que a discussão.
    >>  ............................................
  confident.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both. I'd been treating it as a choice and it might not be one. Thank you.
    >>  ............................................
    pt  ...Os dois. Eu tratava como escolha e talvez não seja. Obrigado.
    >>  ............................................
  confident.dialogue.conversations.work.prof.mason.future.encourage/2
    en  The abutment first. Then the arch, and whoever comes after can argue with it.
    >>  ............................................
    pt  O contraforte primeiro. Depois o arco, e quem vier depois que discuta com ele.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both. I'd been treating it as a choice and it might not be one. Thank you.
    >>  ............................................
    pt  ...Os dois. Eu tratava como escolha e talvez não seja. Obrigado.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.mason.future.encourage/2
    en  The abutment first. Then the arch, and whoever comes after can argue with it.
    >>  ............................................
    pt  O contraforte primeiro. Depois o arco, e quem vier depois que discuta com ele.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both, %1$s. I'd made it a choice and it might not be one. Thank you for that.
    >>  ............................................
    pt  ...Os dois, %1$s. Fiz disso uma escolha e talvez não seja. Obrigado por isso.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.mason.future.encourage/2
    en  The abutment first. Then the arch, and whoever comes after can argue with it.
    >>  ............................................
    pt  Contraforte primeiro. Depois o arco, e quem vier depois que discuta.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both, %1$s. I'd made it a choice and it might not be one. Thank you for that.
    >>  ............................................
    pt  ...Os dois, %1$s. Fiz disso uma escolha e talvez não seja. Obrigado por isso.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.mason.future.encourage/2
    en  The abutment first. Then the arch, and whoever comes after can argue with it.
    >>  ............................................
    pt  Contraforte primeiro. Depois o arco, e quem vier depois que discuta.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both, %1$s. I'd made it a choice and it might not be one. Thank you for that.
    >>  ............................................
    pt  ...Os dois, %1$s. Fiz disso uma escolha e talvez não seja. Obrigado por isso.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.mason.future.encourage/2
    en  The abutment first. Then the arch, and whoever comes after can argue with it.
    >>  ............................................
    pt  Contraforte primeiro. Depois o arco, e quem vier depois que discuta.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both. I made it a choice because a choice is easier to keep postponing.
    >>  ............................................
    pt  ...Os dois. Fiz virar escolha porque escolha é mais fácil de adiar.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.mason.future.encourage/2
    en  The abutment first. Then the arch. Saying the order aloud makes it a thing I've started.
    >>  ............................................
    pt  Contraforte primeiro. Depois o arco. Dizer a ordem em voz alta já é ter começado.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both. I'd been treating it as a choice and it might not be one. Thank you.
    >>  ............................................
    pt  ...Os dois. Eu tratava como escolha e talvez não seja. Obrigado.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.mason.future.encourage/2
    en  The abutment first. Then the arch, and whoever comes after can argue with it.
    >>  ............................................
    pt  O contraforte primeiro. Depois o arco, e quem vier depois que discuta com ele.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both. I'd been treating it as a choice and it might not be one. Thank you.
    >>  ............................................
    pt  ...Os dois. Eu tratava como escolha e talvez não seja. Obrigado.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.mason.future.encourage/2
    en  The abutment first. Then the arch, and whoever comes after can argue with it.
    >>  ............................................
    pt  O contraforte primeiro. Depois o arco, e quem vier depois que discuta com ele.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both. Not a choice after all.
    >>  ............................................
    pt  ...Os dois. Não era escolha, afinal.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.mason.future.encourage/2
    en  Abutment first. Then the arch.
    >>  ............................................
    pt  Contraforte primeiro. Depois o arco.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both. Thirty years of walls and I still make choices out of things that aren't.
    >>  ............................................
    pt  ...Os dois. Trinta anos de muros e ainda faço escolhas do que não é escolha.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.mason.future.encourage/2
    en  The abutment first. Then the arch, and it will outlast the argument about it.
    >>  ............................................
    pt  Contraforte primeiro. Depois o arco, e ele vai durar mais que a discussão.
    >>  ............................................
  odd.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both. Not a choice after all.
    >>  ............................................
    pt  ...Os dois. Não era escolha, afinal.
    >>  ............................................
  odd.dialogue.conversations.work.prof.mason.future.encourage/2
    en  Abutment first. Then the arch.
    >>  ............................................
    pt  Contraforte primeiro. Depois o arco.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both. Thirty years of walls and I still make choices out of things that aren't.
    >>  ............................................
    pt  ...Os dois. Trinta anos de muros e ainda faço escolhas do que não é escolha.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.mason.future.encourage/2
    en  The abutment first. Then the arch, and it will outlast the argument about it.
    >>  ............................................
    pt  Contraforte primeiro. Depois o arco, e ele vai durar mais que a discussão.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both! I'd been treating it as a choice and it might not be one at all.
    >>  ............................................
    pt  ...Os dois! Eu tratava como escolha e talvez não seja escolha nenhuma.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.mason.future.encourage/2
    en  The abutment first, then the arch — and whoever comes after can argue with it.
    >>  ............................................
    pt  Contraforte primeiro, depois o arco — e quem vier depois que discuta.
    >>  ............................................
  playful.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both! I'd been treating it as a choice and it might not be one at all.
    >>  ............................................
    pt  ...Os dois! Eu tratava como escolha e talvez não seja escolha nenhuma.
    >>  ............................................
  playful.dialogue.conversations.work.prof.mason.future.encourage/2
    en  The abutment first, then the arch — and whoever comes after can argue with it.
    >>  ............................................
    pt  Contraforte primeiro, depois o arco — e quem vier depois que discuta.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both. Thirty years of walls and I still make choices out of things that aren't.
    >>  ............................................
    pt  ...Os dois. Trinta anos de muros e ainda faço escolhas do que não é escolha.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.mason.future.encourage/2
    en  The abutment first. Then the arch, and it will outlast the argument about it.
    >>  ............................................
    pt  Contraforte primeiro. Depois o arco, e ele vai durar mais que a discussão.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both. I made it a choice because a choice is easier to keep postponing.
    >>  ............................................
    pt  ...Os dois. Fiz virar escolha porque escolha é mais fácil de adiar.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.mason.future.encourage/2
    en  The abutment first. Then the arch. Saying the order aloud makes it a thing I've started.
    >>  ............................................
    pt  Contraforte primeiro. Depois o arco. Dizer a ordem em voz alta já é ter começado.
    >>  ............................................
  shy.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both. Not a choice after all.
    >>  ............................................
    pt  ...Os dois. Não era escolha, afinal.
    >>  ............................................
  shy.dialogue.conversations.work.prof.mason.future.encourage/2
    en  Abutment first. Then the arch.
    >>  ............................................
    pt  Contraforte primeiro. Depois o arco.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both! I'd been treating it as a choice and it might not be one at all.
    >>  ............................................
    pt  ...Os dois! Eu tratava como escolha e talvez não seja escolha nenhuma.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.mason.future.encourage/2
    en  The abutment first, then the arch — and whoever comes after can argue with it.
    >>  ............................................
    pt  Contraforte primeiro, depois o arco — e quem vier depois que discuta.
    >>  ............................................
  witty.dialogue.conversations.work.prof.mason.future.encourage/1
    en  ...Both! I'd been treating it as a choice and it might not be one at all.
    >>  ............................................
    pt  ...Os dois! Eu tratava como escolha e talvez não seja escolha nenhuma.
    >>  ............................................
  witty.dialogue.conversations.work.prof.mason.future.encourage/2
    en  The abutment first, then the arch — and whoever comes after can argue with it.
    >>  ............................................
    pt  Contraforte primeiro, depois o arco — e quem vier depois que discuta.
    >>  ............................................
```

</details>


### Button `ask_only` — "Is there really nobody else?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mason.future` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mason.future.ask_only` — accepted phrasings: "is there really nobody else"
  - the message must contain one of: `else`, `apprentice`, `successor`
  - scored words: `else`(1.2), `apprentice`(1.5), `successor`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.future.respond.ask_only
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.future.respond.ask_only   [28 chars]
    en  Is there really nobody else?
    >>  ............................................
    pt  Não tem mesmo mais ninguém?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mason.future.ask_only`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mason.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which building are you proudest of?" | "Solid ground."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.future.ask_only
WHO    VILLAGER — what the player reads after pressing "Is there really nobody else?"
       spoken on: conversations.topic.work.mason.future.respond, button `ask_only`
       leaves the player on: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.future.ask_only`: the villager explains. Subject `work.mason.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mason.future.ask_only/1   [92 chars]
    en  Not within four valleys. I've asked. It is a slow, cold, unglamorous trade and I understand.
    >>  ............................................
    pt  Em quatro vales, não. Já perguntei. É um ofício lento, frio e sem glamour e eu entendo.
    >>  ............................................
  dialogue.conversations.work.prof.mason.future.ask_only/2   [84 chars]
    en  Two apprentices in twenty years. One went to the city and one took up fishing, %1$s.
    >>  ............................................
    pt  Dois aprendizes em vinte anos. Um foi pra cidade e o outro virou pescador, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the course."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.mason.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.future.respond.leave   [36 chars]
    en  I'll let you get back to the course.
    >>  ............................................
    pt  Vou deixar você voltar à fiada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the course."
       spoken on: conversations.topic.work.mason.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.left`: the villager accepts. Subject `work.mason.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mason.bad_stone.blocked.respond / leave; conversations.scene.work.mason.bad_stone.succeeded.respond / leave; conversations.scene.work.mason.followup / leave; conversations.scene.work.mason.quick_apprentice.active.respond / leave; conversations.scene.work.mason.quick_apprentice.succeeded.respond / leave; conversations.scene.work.mason.rushed_foundation.blocked.respond / leave; conversations.scene.work.mason.rushed_foundation.succeeded.respond / leave; conversations.topic.work.mason.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.mason.bad_stone.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.mason.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.mason` — e.g. "Stone is patient and so am I. The wall I built at twenty will outlive my grandchildren's grudges."


```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.mason.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.mason.respond   [35 chars]
    en  That's the stone and what I owe it.
    >>  ............................................
    pt  É a pedra e o que eu devo a ela.
    >>  ............................................
```


### Button `ask_hard` — "What do you find when you look at a wall?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mason.identity` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mason.hard` — accepted phrasings: "what do you find when you look at a wall"
  - the message must contain one of: `wall`, `cracks`
  - scored words: `wall`(1.2), `cracks`(1.5), `look`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.respond.ask_hard   [41 chars]
    en  What do you find when you look at a wall?
    >>  ............................................
    pt  O que você vê quando olha pra uma parede?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.mason.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mason.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which building are you proudest of?" | "Solid ground."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.hard
WHO    VILLAGER — what the player reads after pressing "What do you find when you look at a wall?"
       spoken on: conversations.topic.work.mason.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.hard`: the villager explains. Subject `work.mason.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mason.followup / ask_more
```

> Written out in full under **`conversations.scene.work.mason.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "Half these houses are standing because of you."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.mason.identity` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mason.value` — accepted phrasings: "half these houses are standing because of you"
  - the message must contain one of: `standing`, `houses`
  - scored words: `standing`(1.5), `houses`(1.5), `because`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.respond.value   [46 chars]
    en  Half these houses are standing because of you.
    >>  ............................................
    pt  Metade destas casas está de pé por sua causa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.mason.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.mason.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mason.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which building are you proudest of?" | "Solid ground."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.value
WHO    VILLAGER — what the player reads after pressing "Half these houses are standing because of you."
       spoken on: conversations.topic.work.mason.respond, button `value`
       leaves the player on: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.value`: the villager accepts. Subject `work.mason.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mason.value/1   [68 chars]
    en  Standing, aye. The other half is standing because of my grandmother.
    >>  ............................................
    pt  De pé, sim. A outra metade está de pé por causa da minha avó.
    >>  ............................................
  dialogue.conversations.work.prof.mason.value/2   [81 chars]
    en  It is. That's a heavy thing to think about on a bad day and a fine one on a good.
    >>  ............................................
    pt  Está. É uma coisa pesada de pensar num dia ruim e ótima num dia bom.
    >>  ............................................
```


### Button `challenge` — "Stacking rocks isn't a craft."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.mason.identity` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mason.challenge` — accepted phrasings: "stacking rocks isn't a craft"
  - the message must contain one of: `stacking`, `rocks`
  - scored words: `stacking`(1.5), `rocks`(1.5), `craft`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.respond.challenge   [29 chars]
    en  Stacking rocks isn't a craft.
    >>  ............................................
    pt  Empilhar pedra não é ofício.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.mason.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.mason.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mason.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which building are you proudest of?" | "Solid ground."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.challenge.landed
WHO    VILLAGER — what the player reads after pressing "Stacking rocks isn't a craft."
       spoken on: conversations.topic.work.mason.respond, button `challenge`
       leaves the player on: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.challenge.landed`: the villager resists. Subject `work.mason.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mason.challenge.landed/1   [76 chars]
    en  Stacking isn't. Choosing which stone goes where — that took me eleven years.
    >>  ............................................
    pt  Empilhar não é. Escolher qual pedra vai onde — isso me levou onze anos.
    >>  ............................................
  dialogue.conversations.work.prof.mason.challenge.landed/2   [75 chars]
    en  Ha. Stack some, %1$s, and come back when your wall falls over in the frost.
    >>  ............................................
    pt  Ha. Empilhe umas, %1$s, e volte quando sua parede cair na geada.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.mason.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.mason.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mason.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which building are you proudest of?" | "Solid ground."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.challenge.stung
WHO    VILLAGER — what the player reads after pressing "Stacking rocks isn't a craft."
       spoken on: conversations.topic.work.mason.respond, button `challenge`
       leaves the player on: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.challenge.stung`: the villager resists. Subject `work.mason.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mason.challenge.stung/1   [79 chars]
    en  ...The wall I built at twenty is still standing. Say that again in fifty years.
    >>  ............................................
    pt  ...A parede que eu fiz aos vinte ainda está de pé. Repita isso daqui a cinquenta anos.
    >>  ............................................
  dialogue.conversations.work.prof.mason.challenge.stung/2   [66 chars]
    en  Stacking rocks. Right. Sleep under one you stacked yourself, then.
    >>  ............................................
    pt  Empilhar pedra. Certo. Durma embaixo de uma que você empilhou, então.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the course."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.mason.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.respond.leave   [36 chars]
    en  I'll let you get back to the course.
    >>  ............................................
    pt  Vou deixar você voltar à fiada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the course."
       spoken on: conversations.topic.work.mason.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.left`: the villager accepts. Subject `work.mason.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mason.bad_stone.blocked.respond / leave; conversations.scene.work.mason.bad_stone.succeeded.respond / leave; conversations.scene.work.mason.followup / leave; conversations.scene.work.mason.quick_apprentice.active.respond / leave; conversations.scene.work.mason.quick_apprentice.succeeded.respond / leave; conversations.scene.work.mason.rushed_foundation.blocked.respond / leave; conversations.scene.work.mason.rushed_foundation.succeeded.respond / leave; conversations.topic.work.mason.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.mason.bad_stone.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.mason.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.mason.risk` — e.g. "Everything I build outlives me, and so does everything I get wrong. That's not a comfortable pair."


```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.mason.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.mason.risk.respond   [25 chars]
    en  That's what stands in it.
    >>  ............................................
    pt  É o que está de pé nisso.
    >>  ............................................
```


### Button `ask_wall` — "What did you find when you looked?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mason.risk` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mason.risk.ask_wall` — accepted phrasings: "what did you find when you looked"
  - the message must contain one of: `found`, `looked`, `collapse`
  - scored words: `found`(1.5), `looked`(1.2), `collapse`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.risk.respond.ask_wall
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.risk.respond.ask_wall   [34 chars]
    en  What did you find when you looked?
    >>  ............................................
    pt  O que você achou quando olhou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mason.risk.ask_wall`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mason.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which building are you proudest of?" | "Solid ground."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.risk.ask_wall
WHO    VILLAGER — what the player reads after pressing "What did you find when you looked?"
       spoken on: conversations.topic.work.mason.risk.respond, button `ask_wall`
       leaves the player on: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.risk.ask_wall`: the villager explains. Subject `work.mason.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mason.risk.ask_wall/1   [82 chars]
    en  Bad footing on soft ground. Ordinary, avoidable, and it took a woman and her loom.
    >>  ............................................
    pt  Fundação ruim em terreno mole. Comum, evitável, e levou uma mulher e o tear dela.
    >>  ............................................
  dialogue.conversations.work.prof.mason.risk.ask_wall/2   [78 chars]
    en  Exactly what I'd expected and it did not make the walk home any shorter, %1$s.
    >>  ............................................
    pt  Exatamente o que eu esperava e não encurtou nada a volta pra casa, %1$s.
    >>  ............................................
```


### Button `sympathise` — "You went to the next valley though it wasn't yours."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.mason.risk` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mason.risk.sympathise` — accepted phrasings: "you went to the next valley though it wasn't yours"
  - the message must contain one of: `yours`, `valley`, `went`
  - scored words: `yours`(1.5), `valley`(1.2), `went`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.risk.respond.sympathise   [51 chars]
    en  You went to the next valley though it wasn't yours.
    >>  ............................................
    pt  Você foi ao vale vizinho mesmo não sendo sua.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.mason.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.mason.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mason.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which building are you proudest of?" | "Solid ground."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "You went to the next valley though it wasn't yours."
       spoken on: conversations.topic.work.mason.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.risk.sympathise`: the villager accepts. Subject `work.mason.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mason.risk.sympathise/1   [72 chars]
    en  ...It's the only way the trade gets better. Somebody has to go and look.
    >>  ............................................
    pt  ...É o único jeito de o ofício melhorar. Alguém tem que ir olhar.
    >>  ............................................
  dialogue.conversations.work.prof.mason.risk.sympathise/2   [84 chars]
    en  If I don't, I'll build the same footing in ten years and not know why it went, %1$s.
    >>  ............................................
    pt  Se eu não for, faço a mesma fundação em dez anos e não sei por que caiu, %1$s.
    >>  ............................................
```


### Button `ask_outlive` — "Does the outliving weigh on you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mason.risk` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mason.risk.ask_outlive` — accepted phrasings: "does the outliving weigh on you"
  - the message must contain one of: `outlive`, `weigh`, `lasting`
  - scored words: `outlive`(1.5), `weigh`(1.2), `lasting`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.risk.respond.ask_outlive
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.risk.respond.ask_outlive   [32 chars]
    en  Does the outliving weigh on you?
    >>  ............................................
    pt  O sobreviver pesa em você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mason.risk.ask_outlive`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mason.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which building are you proudest of?" | "Solid ground."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.risk.ask_outlive
WHO    VILLAGER — what the player reads after pressing "Does the outliving weigh on you?"
       spoken on: conversations.topic.work.mason.risk.respond, button `ask_outlive`
       leaves the player on: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.risk.ask_outlive`: the villager explains. Subject `work.mason.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mason.risk.ask_outlive/1   [80 chars]
    en  Both ways. The well I cut at twenty is still there and so is the crooked lintel.
    >>  ............................................
    pt  Dos dois jeitos. O poço que eu cortei aos vinte ainda está lá e a verga torta também.
    >>  ............................................
  dialogue.conversations.work.prof.mason.risk.ask_outlive/2   [76 chars]
    en  Some nights. Mostly it makes me slower, which is the correct response to it.
    >>  ............................................
    pt  Em algumas noites. Na maioria me deixa mais lento, que é a resposta certa.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the course."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.mason.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.risk.respond.leave   [36 chars]
    en  I'll let you get back to the course.
    >>  ............................................
    pt  Vou deixar você voltar à fiada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the course."
       spoken on: conversations.topic.work.mason.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.left`: the villager accepts. Subject `work.mason.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mason.bad_stone.blocked.respond / leave; conversations.scene.work.mason.bad_stone.succeeded.respond / leave; conversations.scene.work.mason.followup / leave; conversations.scene.work.mason.quick_apprentice.active.respond / leave; conversations.scene.work.mason.quick_apprentice.succeeded.respond / leave; conversations.scene.work.mason.rushed_foundation.blocked.respond / leave; conversations.scene.work.mason.rushed_foundation.succeeded.respond / leave; conversations.topic.work.mason.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.mason.bad_stone.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.mason.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.mason.task` — e.g. "Cutting sill stones. Six of them, all identical, and identical is harder than beautiful."


```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.mason.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.mason.task.respond   [22 chars]
    en  That's the yard today.
    >>  ............................................
    pt  É o pátio hoje.
    >>  ............................................
```


### Button `ask_identical` — "Why is identical harder?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mason.task` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mason.task.ask_identical` — accepted phrasings: "why is identical harder"
  - the message must contain one of: `identical`, `harder`, `matching`
  - scored words: `identical`(1.5), `harder`(1.2), `matching`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.task.respond.ask_identical
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.task.respond.ask_identical   [24 chars]
    en  Why is identical harder?
    >>  ............................................
    pt  Por que idêntico é mais difícil?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mason.task.ask_identical`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mason.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which building are you proudest of?" | "Solid ground."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.task.ask_identical
WHO    VILLAGER — what the player reads after pressing "Why is identical harder?"
       spoken on: conversations.topic.work.mason.task.respond, button `ask_identical`
       leaves the player on: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.task.ask_identical`: the villager explains. Subject `work.mason.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mason.task.ask_identical/1   [88 chars]
    en  Because beautiful only has to satisfy me. Identical has to satisfy the next five stones.
    >>  ............................................
    pt  Porque bonito só precisa me satisfazer. Idêntico precisa satisfazer as outras cinco pedras.
    >>  ............................................
  dialogue.conversations.work.prof.mason.task.ask_identical/2   [76 chars]
    en  One wrong by a finger and the whole row reads crooked from the street, %1$s.
    >>  ............................................
    pt  Uma errada por um dedo e a fileira inteira parece torta da rua, %1$s.
    >>  ............................................
```


### Button `offer_hands` — "I can haul stone."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.mason.task` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mason.task.offer_hands` — accepted phrasings: "i can haul stone"
  - the message must contain one of: `haul`, `stone`
  - scored words: `haul`(1.5), `stone`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.task.respond.offer_hands   [17 chars]
    en  I can haul stone.
    >>  ............................................
    pt  Eu posso carregar pedra.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.mason.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.mason.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mason.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which building are you proudest of?" | "Solid ground."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I can haul stone."
       spoken on: conversations.topic.work.mason.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.task.offer_hands`: the villager accepts. Subject `work.mason.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mason.task.offer_hands/1   [91 chars]
    en  ...You can. Lift with the legs and never with pride. I've buried men who lifted with pride.
    >>  ............................................
    pt  ...Pode. Levante com as pernas e nunca com orgulho. Já enterrei homens que levantaram com orgulho.
    >>  ............................................
  dialogue.conversations.work.prof.mason.task.offer_hands/2   [86 chars]
    en  Two at a time, no more, and set them down where I point, %1$s. Not near where I point.
    >>  ............................................
    pt  Duas por vez, não mais, e ponha onde eu apontar, %1$s. Não perto de onde eu apontar.
    >>  ............................................
```


### Button `ask_mortar` — "How bad is soft mortar?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mason.task` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mason.task.ask_mortar` — accepted phrasings: "how bad is soft mortar"
  - the message must contain one of: `mortar`, `soft`, `well`
  - scored words: `mortar`(1.5), `soft`(1.2), `well`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.task.respond.ask_mortar
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.task.respond.ask_mortar   [23 chars]
    en  How bad is soft mortar?
    >>  ............................................
    pt  Quão ruim é argamassa mole?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mason.task.ask_mortar`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mason.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which building are you proudest of?" | "Solid ground."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.task.ask_mortar
WHO    VILLAGER — what the player reads after pressing "How bad is soft mortar?"
       spoken on: conversations.topic.work.mason.task.respond, button `ask_mortar`
       leaves the player on: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.task.ask_mortar`: the villager explains. Subject `work.mason.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mason.task.ask_mortar/1   [82 chars]
    en  It gives you two years of warning and then no warning at all. That's a well shaft.
    >>  ............................................
    pt  Dá dois anos de aviso e depois nenhum aviso. Isso num poço.
    >>  ............................................
  dialogue.conversations.work.prof.mason.task.ask_mortar/2   [77 chars]
    en  On a wall, ugly. On a well, it is somebody's child, %1$s. So I'm on the well.
    >>  ............................................
    pt  Numa parede, feio. Num poço, é o filho de alguém, %1$s. Então eu estou no poço.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the course."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.mason.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.task.respond.leave   [36 chars]
    en  I'll let you get back to the course.
    >>  ............................................
    pt  Vou deixar você voltar à fiada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the course."
       spoken on: conversations.topic.work.mason.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.left`: the villager accepts. Subject `work.mason.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mason.bad_stone.blocked.respond / leave; conversations.scene.work.mason.bad_stone.succeeded.respond / leave; conversations.scene.work.mason.followup / leave; conversations.scene.work.mason.quick_apprentice.active.respond / leave; conversations.scene.work.mason.quick_apprentice.succeeded.respond / leave; conversations.scene.work.mason.rushed_foundation.blocked.respond / leave; conversations.scene.work.mason.rushed_foundation.succeeded.respond / leave; conversations.topic.work.mason.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.mason.bad_stone.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.mason.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.mason.village` — e.g. "The well, the church footing, four chimneys and the bridge abutment. That's me, and it'll be me in a hundred years."


```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.mason.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.mason.village.respond   [26 chars]
    en  That's what I've put here.
    >>  ............................................
    pt  É o que eu pus aqui.
    >>  ............................................
```


### Button `ask_twice` — "Twice?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mason.village` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mason.village.ask_twice` — accepted phrasings: "twice"
  - the message must contain one of: `twice`, `graves`
  - scored words: `twice`(1.5), `graves`(1.2), `hands`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.village.respond.ask_twice
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.village.respond.ask_twice   [6 chars]
    en  Twice?
    >>  ............................................
    pt  Duas vezes?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mason.village.ask_twice`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mason.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which building are you proudest of?" | "Solid ground."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.village.ask_twice
WHO    VILLAGER — what the player reads after pressing "Twice?"
       spoken on: conversations.topic.work.mason.village.respond, button `ask_twice`
       leaves the player on: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.village.ask_twice`: the villager explains. Subject `work.mason.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mason.village.ask_twice/1   [75 chars]
    en  Once when I built something they used, once when I cut the stone over them.
    >>  ............................................
    pt  Uma quando eu construí algo que usaram, outra quando cortei a pedra sobre eles.
    >>  ............................................
  dialogue.conversations.work.prof.mason.village.ask_twice/2   [77 chars]
    en  The second is the harder cut, %1$s, and I have never charged for one of them.
    >>  ............................................
    pt  O segundo é o corte mais difícil, %1$s, e eu nunca cobrei por nenhum.
    >>  ............................................
```


### Button `say_thanks` — "You'll be here longer than any of us."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.mason.village` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mason.village.say_thanks` — accepted phrasings: "you'll be here longer than any of us"
  - the message must contain one of: `longer`, `outlast`, `lasting`
  - scored words: `longer`(1.5), `outlast`(1.2), `lasting`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.village.respond.say_thanks   [37 chars]
    en  You'll be here longer than any of us.
    >>  ............................................
    pt  Você vai estar aqui mais tempo que qualquer um de nós.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.mason.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.mason.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mason.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which building are you proudest of?" | "Solid ground."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "You'll be here longer than any of us."
       spoken on: conversations.topic.work.mason.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.village.say_thanks`: the villager accepts. Subject `work.mason.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mason.village.say_thanks/1   [74 chars]
    en  ...The stone will. I'd not confuse the two, though I'll take the sentence.
    >>  ............................................
    pt  ...A pedra vai. Eu não confundiria as duas, mas eu aceito a frase.
    >>  ............................................
  dialogue.conversations.work.prof.mason.village.say_thanks/2   [86 chars]
    en  That's either a great compliment or a very long sentence. I've decided it's the first.
    >>  ............................................
    pt  Ou é um grande elogio ou uma sentença muito longa. Decidi que é o primeiro.
    >>  ............................................
```


### Button `ask_names` — "Is cutting the names hard?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.mason.village` · offered only once the villager has actually said `work:mason`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.mason.village.ask_names` — accepted phrasings: "is cutting the names hard"
  - the message must contain one of: `names`, `cutting`
  - scored words: `names`(1.5), `cutting`(1.2), `graves`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.village.respond.ask_names
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.village.respond.ask_names   [26 chars]
    en  Is cutting the names hard?
    >>  ............................................
    pt  É difícil cortar os nomes?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.mason.village.ask_names`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.mason.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Which building are you proudest of?" | "Solid ground."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.village.ask_names
WHO    VILLAGER — what the player reads after pressing "Is cutting the names hard?"
       spoken on: conversations.topic.work.mason.village.respond, button `ask_names`
       leaves the player on: conversations.topic.work.mason.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.village.ask_names`: the villager explains. Subject `work.mason.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:mason` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.mason.village.ask_names/1   [80 chars]
    en  The letters, no. Getting them right when I knew the person, yes. I've recut two.
    >>  ............................................
    pt  As letras, não. Acertar quando eu conhecia a pessoa, sim. Já recortei duas.
    >>  ............................................
  dialogue.conversations.work.prof.mason.village.ask_names/2   [64 chars]
    en  I do them at night, alone, and I take as long as it takes, %1$s.
    >>  ............................................
    pt  Faço à noite, sozinho, e levo o tempo que levar, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the course."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.mason.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.mason.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.mason.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.mason.village.respond.leave   [36 chars]
    en  I'll let you get back to the course.
    >>  ............................................
    pt  Vou deixar você voltar à fiada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.mason.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the course."
       spoken on: conversations.topic.work.mason.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.mason.left`: the villager accepts. Subject `work.mason.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.mason.bad_stone.blocked.respond / leave; conversations.scene.work.mason.bad_stone.succeeded.respond / leave; conversations.scene.work.mason.followup / leave; conversations.scene.work.mason.quick_apprentice.active.respond / leave; conversations.scene.work.mason.quick_apprentice.succeeded.respond / leave; conversations.scene.work.mason.rushed_foundation.blocked.respond / leave; conversations.scene.work.mason.rushed_foundation.succeeded.respond / leave; conversations.topic.work.mason.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.mason.bad_stone.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

