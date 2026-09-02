# Work talk with a engineer

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.engineer.doubted_idea.blocked.respond`](#conversations-scene-work-engineer-doubted-idea-blocked-respond)
- [`conversations.scene.work.engineer.doubted_idea.succeeded.respond`](#conversations-scene-work-engineer-doubted-idea-succeeded-respond)
- [`conversations.scene.work.engineer.followup`](#conversations-scene-work-engineer-followup)
- [`conversations.scene.work.engineer.maintenance_round.active.respond`](#conversations-scene-work-engineer-maintenance-round-active-respond)
- [`conversations.scene.work.engineer.maintenance_round.succeeded.respond`](#conversations-scene-work-engineer-maintenance-round-succeeded-respond)
- [`conversations.scene.work.engineer.prototype_fault.blocked.respond`](#conversations-scene-work-engineer-prototype-fault-blocked-respond)
- [`conversations.scene.work.engineer.prototype_fault.failed.respond`](#conversations-scene-work-engineer-prototype-fault-failed-respond)
- [`conversations.scene.work.engineer.prototype_fault.succeeded.respond`](#conversations-scene-work-engineer-prototype-fault-succeeded-respond)
- [`conversations.topic.work.engineer.craft.respond`](#conversations-topic-work-engineer-craft-respond)
- [`conversations.topic.work.engineer.followup`](#conversations-topic-work-engineer-followup)
- [`conversations.topic.work.engineer.future.respond`](#conversations-topic-work-engineer-future-respond)
- [`conversations.topic.work.engineer.respond`](#conversations-topic-work-engineer-respond)
- [`conversations.topic.work.engineer.risk.respond`](#conversations-topic-work-engineer-risk-respond)
- [`conversations.topic.work.engineer.task.respond`](#conversations-topic-work-engineer-task-respond)
- [`conversations.topic.work.engineer.village.respond`](#conversations-topic-work-engineer-village-respond)

---

## `conversations.scene.work.engineer.doubted_idea.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.engineer.doubted_idea.blocked` — e.g. "Half the village thinks %2$s is a toy. They are polite about it, which is worse than if they said so."


```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.doubted_idea.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.engineer.doubted_idea.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.engineer.doubted_idea.blocked.respond   [19 chars]
    en  About the doubters.
    >>  ............................................
    pt  Sobre os que duvidam.
    >>  ............................................
```


### Button `offer_to_try_it` — "Show me. I'll use it and tell you what's wrong."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.engineer.doubted_idea.blocked` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.engineer.doubted_idea.blocked.offer_to_try_it` — accepted phrasings: "show me ill use it and tell you whats wrong"; "show me and ill use it"; "let me try it and report back"
  - the message must contain one of: `show`, `use`, `try`
  - scored words: `show`(1.8), `use`(1.8), `try`(1.8), `ill`(0.8), `tell`(0.8), `whats`(0.8), `wrong`(0.8), `let`(0.8), `report`(0.8), `back`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.doubted_idea.blocked.respond.offer_to_try_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.doubted_idea.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.doubted_idea.blocked.respond.offer_to_try_it   [47 chars]
    en  Show me. I'll use it and tell you what's wrong.
    >>  ............................................
    pt  Me mostre. Eu uso e digo o que está errado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.engineer.doubted_idea.trial`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +3  _(recorded under topic `work.engineer.skepticism`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.doubted_idea", "state": "succeeded"}
- Does: `conversations_thread` = {"op": "open", "template": "work.engineer.doubted_idea"}
- Then opens: `conversations.scene.work.engineer.followup`
- …where the player's next choices will be: "What's the hardest part of building something new?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.doubted_idea.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "Show me. I'll use it and tell you what's wrong."
       spoken on: conversations.scene.work.engineer.doubted_idea.blocked.respond, button `offer_to_try_it`
       leaves the player on: conversations.scene.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.doubted_idea.blocked.accepted`: the villager accepts. Subject `work.engineer.skepticism`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.doubted_idea.blocked.accepted/1   [139 chars]
    en  Then %2$s has a user, and a thing with a user is not a toy. That is not flattery, it is the actual definition I have been failing to reach.
    >>  ............................................
    pt  Então %2$s tem um usuário, e uma coisa com usuário não é brinquedo. Não é lisonja, é a definição que eu venho falhando em alcançar.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.doubted_idea.blocked.accepted/2   [116 chars]
    en  Come at noon and I will not hover. Use it wrong. Please use it wrong — that is worth more to me than using it right.
    >>  ............................................
    pt  Venha ao meio-dia e eu não fico em cima. Use errado. Por favor, use errado — vale mais para mim do que usar certo.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.doubted_idea.blocked.accepted/3   [114 chars]
    en  You will find three things I have stopped being able to see. Everybody does, in the first ten minutes, every time.
    >>  ............................................
    pt  Você vai achar três coisas que eu já não consigo enxergar. Todo mundo acha, nos primeiros dez minutos, todas as vezes.
    >>  ............................................
```


### Button `ask_what_convinces` — "What would convince them?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.engineer.doubted_idea.blocked` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.engineer.doubted_idea.blocked.ask_what_convinces` — accepted phrasings: "what would convince them"; "what would change their minds"; "what convinces people around here"
  - the message must contain one of: `convince`, `convinces`, `minds`
  - scored words: `convince`(1.8), `convinces`(1.8), `minds`(1.8), `change`(0.8), `their`(0.8), `people`(0.8), `around`(0.8), `here`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.doubted_idea.blocked.respond.ask_what_convinces
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.doubted_idea.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.doubted_idea.blocked.respond.ask_what_convinces   [25 chars]
    en  What would convince them?
    >>  ............................................
    pt  O que os convenceria?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.engineer.skepticism`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.engineer.doubted_idea"}
- Then opens: `conversations.scene.work.engineer.followup`
- …where the player's next choices will be: "What's the hardest part of building something new?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.doubted_idea.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What would convince them?"
       spoken on: conversations.scene.work.engineer.doubted_idea.blocked.respond, button `ask_what_convinces`
       leaves the player on: conversations.scene.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.doubted_idea.blocked.explained`: the villager explains. Subject `work.engineer.skepticism`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.doubted_idea.blocked.explained/1   [128 chars]
    en  One harvest where %2$s saves somebody a day. Not an argument, not a drawing. One person with a day back who tells somebody else.
    >>  ............................................
    pt  Uma colheita em que %2$s poupe um dia de alguém. Não um argumento, não um desenho. Uma pessoa com um dia a mais que conte para outra.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.doubted_idea.blocked.explained/2   [114 chars]
    en  Nothing I say. That is the lesson of four years and I keep having to learn it again every time I have a good idea.
    >>  ............................................
    pt  Nada que eu diga. É a lição de quatro anos e eu preciso reaprender toda vez que tenho uma boa ideia.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.doubted_idea.blocked.explained/3   [140 chars]
    en  Being wrong loudly, once, and admitting it. They do not distrust the machine. They distrust somebody who has never said 'that did not work'.
    >>  ............................................
    pt  Errar em voz alta, uma vez, e admitir. Eles não desconfiam da máquina. Desconfiam de quem nunca disse 'aquilo não deu certo'.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the bench."

*stance family `exit` · tone `plain` · answers the beat(s) `work.engineer.doubted_idea.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.doubted_idea.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.doubted_idea.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.doubted_idea.blocked.respond.leave   [28 chars]
    en  I'll leave you to the bench.
    >>  ............................................
    pt  Vou deixar você com a bancada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the bench."
       spoken on: conversations.scene.work.engineer.doubted_idea.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.left`: the villager accepts. Subject `work.engineer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.engineer.doubted_idea.succeeded.respond / leave; conversations.scene.work.engineer.followup / leave; conversations.scene.work.engineer.maintenance_round.active.respond / leave; conversations.scene.work.engineer.maintenance_round.succeeded.respond / leave; conversations.scene.work.engineer.prototype_fault.blocked.respond / leave; conversations.scene.work.engineer.prototype_fault.failed.respond / leave; conversations.scene.work.engineer.prototype_fault.succeeded.respond / leave; conversations.topic.work.engineer.craft.respond / leave …and 6 more
```

```text
  dialogue.conversations.work.prof.engineer.leave/1   [46 chars]
    en  Stand back a little further than that, though.
    >>  ............................................
    pt  Mas fique um pouco mais longe que isso.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.leave/2   [33 chars]
    en  Off you go, %1$s. Mind the lever.
    >>  ............................................
    pt  Pode ir, %1$s. Cuidado com a alavanca.
    >>  ............................................
```

---


## `conversations.scene.work.engineer.doubted_idea.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.engineer.doubted_idea.succeeded` — e.g. "The miller used %2$s twice this week without being asked. He has not said anything about it, which is how you know it has worked."


```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.doubted_idea.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.engineer.doubted_idea.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.engineer.doubted_idea.succeeded.respond   [16 chars]
    en  They came round.
    >>  ............................................
    pt  Eles mudaram de ideia.
    >>  ............................................
```


### Button `congratulate` — "You were right and you waited it out."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.engineer.doubted_idea.succeeded` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.engineer.doubted_idea.succeeded.congratulate` — accepted phrasings: "you were right and you waited it out"; "you were right all along"; "you waited them out and were right"
  - the message must contain one of: `right`, `waited`
  - scored words: `right`(1.8), `waited`(1.8), `were`(0.8), `out`(0.8), `all`(0.8), `along`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.doubted_idea.succeeded.respond.congratulate
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.doubted_idea.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.doubted_idea.succeeded.respond.congratulate   [37 chars]
    en  You were right and you waited it out.
    >>  ............................................
    pt  Você estava certa e soube esperar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.engineer.doubted_idea.praise`, budget `standard`, replay policy `once`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.engineer.skepticism`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.engineer.doubted_idea"}
- Then opens: `conversations.scene.work.engineer.followup`
- …where the player's next choices will be: "What's the hardest part of building something new?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.doubted_idea.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "You were right and you waited it out."
       spoken on: conversations.scene.work.engineer.doubted_idea.succeeded.respond, button `congratulate`
       leaves the player on: conversations.scene.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.doubted_idea.succeeded.acknowledged`: the villager accepts. Subject `work.engineer.skepticism`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.doubted_idea.succeeded.acknowledged/1   [130 chars]
    en  I was right about the machine. I was wrong about how to convince anybody, for four years, and that cost more than the machine did.
    >>  ............................................
    pt  Eu estava certa sobre a máquina. Estava errada sobre como convencer alguém, por quatro anos, e isso custou mais que a máquina.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.doubted_idea.succeeded.acknowledged/2   [111 chars]
    en  Waiting it out is a generous word for what I did, which was sulk in a workshop and occasionally file something.
    >>  ............................................
    pt  Esperar é uma palavra generosa para o que eu fiz, que foi emburrar numa oficina e vez ou outra limar alguma coisa.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.doubted_idea.succeeded.acknowledged/3   [109 chars]
    en  Say it once more and then let us never mention it, because I would like to still be liveable-with next month.
    >>  ............................................
    pt  Diga mais uma vez e depois nunca mais falemos disso, porque eu gostaria de continuar suportável mês que vem.
    >>  ............................................
```


### Button `ask_next_build` — "What will you build next?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.engineer.doubted_idea.succeeded` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.engineer.doubted_idea.succeeded.ask_next_build` — accepted phrasings: "what will you build next"; "what is the next build"; "what comes next on the bench"
  - the message must contain one of: `build`, `next`
  - scored words: `build`(1.8), `next`(1.8), `comes`(0.8), `bench`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.doubted_idea.succeeded.respond.ask_next_build
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.doubted_idea.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.doubted_idea.succeeded.respond.ask_next_build   [25 chars]
    en  What will you build next?
    >>  ............................................
    pt  O que você vai construir agora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.engineer.skepticism`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.engineer.doubted_idea"}
- Then opens: `conversations.scene.work.engineer.followup`
- …where the player's next choices will be: "What's the hardest part of building something new?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.doubted_idea.succeeded.planned
WHO    VILLAGER — what the player reads after pressing "What will you build next?"
       spoken on: conversations.scene.work.engineer.doubted_idea.succeeded.respond, button `ask_next_build`
       leaves the player on: conversations.scene.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.doubted_idea.succeeded.planned`: the villager explains. Subject `work.engineer.skepticism`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.doubted_idea.succeeded.planned/1   [107 chars]
    en  Something small and dull that four people need, on purpose, before I am allowed anything interesting again.
    >>  ............................................
    pt  Algo pequeno e sem graça de que quatro pessoas precisem, de propósito, antes de eu ter direito a algo interessante de novo.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.doubted_idea.succeeded.planned/2   [121 chars]
    en  Nothing, for a month. I have a bench full of half-things and a reputation I would like to keep for more than a fortnight.
    >>  ............................................
    pt  Nada, por um mês. Tenho uma bancada cheia de meias-coisas e uma reputação que eu gostaria de manter por mais de quinze dias.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.doubted_idea.succeeded.planned/3   [130 chars]
    en  There is a thing about water and winter that I am refusing to think about, and I have been refusing for six days, and I will lose.
    >>  ............................................
    pt  Tem uma coisa sobre água e inverno que eu me recuso a pensar, e me recuso há seis dias, e eu vou perder.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the bench."

*stance family `exit` · tone `plain` · answers the beat(s) `work.engineer.doubted_idea.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.doubted_idea.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.doubted_idea.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.doubted_idea.succeeded.respond.leave   [28 chars]
    en  I'll leave you to the bench.
    >>  ............................................
    pt  Vou deixar você com a bancada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the bench."
       spoken on: conversations.scene.work.engineer.doubted_idea.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.left`: the villager accepts. Subject `work.engineer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.engineer.doubted_idea.blocked.respond / leave; conversations.scene.work.engineer.followup / leave; conversations.scene.work.engineer.maintenance_round.active.respond / leave; conversations.scene.work.engineer.maintenance_round.succeeded.respond / leave; conversations.scene.work.engineer.prototype_fault.blocked.respond / leave; conversations.scene.work.engineer.prototype_fault.failed.respond / leave; conversations.scene.work.engineer.prototype_fault.succeeded.respond / leave; conversations.topic.work.engineer.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.engineer.doubted_idea.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.engineer.followup`

**Reached from 15 route(s):** `conversations.scene.work.engineer.doubted_idea.blocked.respond` / `offer_to_try_it`; `conversations.scene.work.engineer.doubted_idea.blocked.respond` / `ask_what_convinces`; `conversations.scene.work.engineer.doubted_idea.succeeded.respond` / `congratulate`; `conversations.scene.work.engineer.doubted_idea.succeeded.respond` / `ask_next_build`; `conversations.scene.work.engineer.maintenance_round.active.respond` / `urge_schedule`; `conversations.scene.work.engineer.maintenance_round.active.respond` / `ask_what_breaks`; `conversations.scene.work.engineer.maintenance_round.succeeded.respond` / `note_invisibility`; `conversations.scene.work.engineer.maintenance_round.succeeded.respond` / `ask_add`; `conversations.scene.work.engineer.prototype_fault.blocked.respond` / `ask_reproduce`; `conversations.scene.work.engineer.prototype_fault.blocked.respond` / `offer_iron`; `conversations.scene.work.engineer.prototype_fault.blocked.respond` / `advise_simpler`; `conversations.scene.work.engineer.prototype_fault.failed.respond` / `ask_what_kept` …and 3 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.engineer.doubted_idea.blocked.accepted` — e.g. "Then %2$s has a user, and a thing with a user is not a toy. That is not flattery, it is the actual definition I have been failing to reach."
- `conversations.scene.work.engineer.doubted_idea.blocked.explained` — e.g. "One harvest where %2$s saves somebody a day. Not an argument, not a drawing. One person with a day back who tells somebody else."
- `conversations.scene.work.engineer.doubted_idea.succeeded.acknowledged` — e.g. "I was right about the machine. I was wrong about how to convince anybody, for four years, and that cost more than the machine did."
- `conversations.scene.work.engineer.doubted_idea.succeeded.planned` — e.g. "Something small and dull that four people need, on purpose, before I am allowed anything interesting again."
- `conversations.scene.work.engineer.maintenance_round.active.adopted` — e.g. "Announced, so that it is theirs and not a favour. Yes. I have been treating %2$s as something I do quietly and that is why it gets forgotten."
- `conversations.scene.work.engineer.maintenance_round.active.explained` — e.g. "Nothing, and then everything. %2$s runs dry for a week without complaint and then eats a bearing in an afternoon."
- `conversations.scene.work.engineer.maintenance_round.succeeded.listed` — e.g. "Everything with a hinge, eventually. I am adding one thing a month so that the round stays a round rather than becoming my whole life."
- `conversations.scene.work.engineer.maintenance_round.succeeded.wry` — e.g. "Not one. The better I am at this the less evidence there is that I exist, and I chose the trade knowing that, and it still stings on a Thursday."
- `conversations.scene.work.engineer.prototype_fault.blocked.accepted` — e.g. "Then I can cut the part properly instead of shimming it, and %2$s stops being a thing I apologise for."
- `conversations.scene.work.engineer.prototype_fault.blocked.explained` — e.g. "Every time, which is the only good news I have. A fault you can summon is a fault you can corner. %2$s only hides from people who are not looking properly."
- `conversations.scene.work.engineer.prototype_fault.blocked.resisted` — e.g. "The simple one exists. It is a rope and two people, and it costs the village two people every day for ever. %2$s costs me a fortnight once."
- `conversations.scene.work.engineer.prototype_fault.failed.received` — e.g. "It is, and it is the one I am worst at. I have been stopping this for six weeks and only actually stopped on Tuesday."
- `conversations.scene.work.engineer.prototype_fault.failed.salvaged` — e.g. "A way of holding two shafts true that I will use for the rest of my life, and a very clear sense of what I cannot do with wood."
- `conversations.scene.work.engineer.prototype_fault.succeeded.credited` — e.g. "That is the whole trade and it is never what anybody praises. They praise the idea. The idea took an afternoon."
- …and 1 more pools


```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.engineer.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.engineer.followup   [14 chars]
    en  Anything else?
    >>  ............................................
    pt  Mais alguma coisa?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of building something new?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.engineer.*` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.engineer.followup.ask_more` — accepted phrasings: "whats the hardest part of building something new"; "hardest part of building something"; "what is difficult about making new things"
  - the message must contain one of: `hardest`, `building`, `difficult`
  - scored words: `hardest`(1.8), `building`(1.8), `difficult`(1.8), `whats`(0.8), `part`(0.8), `something`(0.8), `new`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.followup.ask_more   [50 chars]
    en  What's the hardest part of building something new?
    >>  ............................................
    pt  Qual é a parte mais difícil de construir algo novo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.engineer.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.engineer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What are you building now?" | "Mind the sparks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of building something new?"
       spoken on: conversations.scene.work.engineer.followup, button `ask_more`
       leaves the player on: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.hard`: the villager explains. Subject `work.engineer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.engineer.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.engineer.hard/1   [76 chars]
    en  One in six. That is a very good rate and nobody in this village believes me.
    >>  ............................................
    pt  Uma em seis. É uma taxa muito boa e ninguém neste vilarejo acredita em mim.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.hard/2   [76 chars]
    en  The ones you've seen do. You've not seen the yard behind the workshop, %1$s.
    >>  ............................................
    pt  As que você viu, funcionam. Você não viu o quintal atrás da oficina, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.engineer.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.followup.leave   [28 chars]
    en  I'll let you get back to it.
    >>  ............................................
    pt  Vou deixar você voltar ao trabalho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.scene.work.engineer.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.left`: the villager accepts. Subject `work.engineer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.engineer.doubted_idea.blocked.respond / leave; conversations.scene.work.engineer.doubted_idea.succeeded.respond / leave; conversations.scene.work.engineer.maintenance_round.active.respond / leave; conversations.scene.work.engineer.maintenance_round.succeeded.respond / leave; conversations.scene.work.engineer.prototype_fault.blocked.respond / leave; conversations.scene.work.engineer.prototype_fault.failed.respond / leave; conversations.scene.work.engineer.prototype_fault.succeeded.respond / leave; conversations.topic.work.engineer.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.engineer.doubted_idea.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.engineer.maintenance_round.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.engineer.maintenance_round.active` — e.g. "%2$s wants greasing every eleven days and gets it every twenty, because nobody sends for me until it squeals."


```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.maintenance_round.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.engineer.maintenance_round.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.engineer.maintenance_round.active.respond   [17 chars]
    en  About the rounds.
    >>  ............................................
    pt  Sobre as rondas.
    >>  ............................................
```


### Button `urge_schedule` — "Set a fixed round and tell people it's fixed."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.engineer.maintenance_round.active` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.engineer.maintenance_round.active.urge_schedule` — accepted phrasings: "set a fixed round and tell people its fixed"; "set a regular round"; "make it a fixed schedule people know about"
  - the message must contain one of: `round`, `fixed`, `schedule`
  - scored words: `round`(1.8), `fixed`(1.8), `schedule`(1.8), `set`(0.8), `tell`(0.8), `people`(0.8), `its`(0.8), `regular`(0.8), `make`(0.8), `know`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.maintenance_round.active.respond.urge_schedule
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.maintenance_round.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.maintenance_round.active.respond.urge_schedule   [45 chars]
    en  Set a fixed round and tell people it's fixed.
    >>  ............................................
    pt  Faça uma ronda fixa e avise que é fixa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.engineer.maintenance`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.maintenance_round", "state": "succeeded"}
- Does: `conversations_thread` = {"op": "open", "template": "work.engineer.maintenance_round"}
- Then opens: `conversations.scene.work.engineer.followup`
- …where the player's next choices will be: "What's the hardest part of building something new?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.maintenance_round.active.adopted
WHO    VILLAGER — what the player reads after pressing "Set a fixed round and tell people it's fixed."
       spoken on: conversations.scene.work.engineer.maintenance_round.active.respond, button `urge_schedule`
       leaves the player on: conversations.scene.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.maintenance_round.active.adopted`: the villager accepts. Subject `work.engineer.maintenance`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.maintenance_round.active.adopted/1   [141 chars]
    en  Announced, so that it is theirs and not a favour. Yes. I have been treating %2$s as something I do quietly and that is why it gets forgotten.
    >>  ............................................
    pt  Anunciada, para ser deles e não um favor. Sim. Venho tratando %2$s como coisa que faço em silêncio, e é por isso que esquecem.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.maintenance_round.active.adopted/2   [142 chars]
    en  Every eleven days, on the same day, whether or not anything squeals. Written up at the well. I will feel ridiculous doing it and it will work.
    >>  ............................................
    pt  A cada onze dias, no mesmo dia, guinche ou não. Afixado no poço. Vou me sentir ridícula fazendo isso e vai funcionar.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.maintenance_round.active.adopted/3   [83 chars]
    en  That takes the deciding out of it, which is the part that actually costs me. Right.
    >>  ............................................
    pt  Isso tira o decidir do meio, que é a parte que de fato me custa. Certo.
    >>  ............................................
```


### Button `ask_what_breaks` — "What breaks first when you're late?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.engineer.maintenance_round.active` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.engineer.maintenance_round.active.ask_what_breaks` — accepted phrasings: "what breaks first when youre late"; "what fails first if you are late"; "what goes wrong when it is overdue"
  - the message must contain one of: `breaks`, `fails`, `late`, `overdue`
  - scored words: `breaks`(1.8), `fails`(1.8), `late`(1.8), `overdue`(1.8), `first`(0.8), `when`(0.8), `youre`(0.8), `goes`(0.8), `wrong`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.maintenance_round.active.respond.ask_what_breaks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.maintenance_round.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.maintenance_round.active.respond.ask_what_breaks   [35 chars]
    en  What breaks first when you're late?
    >>  ............................................
    pt  O que quebra primeiro quando você atrasa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.engineer.maintenance`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.engineer.maintenance_round"}
- Then opens: `conversations.scene.work.engineer.followup`
- …where the player's next choices will be: "What's the hardest part of building something new?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.maintenance_round.active.explained
WHO    VILLAGER — what the player reads after pressing "What breaks first when you're late?"
       spoken on: conversations.scene.work.engineer.maintenance_round.active.respond, button `ask_what_breaks`
       leaves the player on: conversations.scene.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.maintenance_round.active.explained`: the villager explains. Subject `work.engineer.maintenance`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.maintenance_round.active.explained/1   [113 chars]
    en  Nothing, and then everything. %2$s runs dry for a week without complaint and then eats a bearing in an afternoon.
    >>  ............................................
    pt  Nada, e depois tudo. %2$s roda seco uma semana sem reclamar e aí come um mancal numa tarde.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.maintenance_round.active.explained/2   [122 chars]
    en  The quiet parts go first — the ones nobody hears fail. By the time something squeals, the squealing is the second problem.
    >>  ............................................
    pt  As partes silenciosas vão primeiro — aquelas que ninguém ouve falhar. Quando algo guincha, o guincho já é o segundo problema.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.maintenance_round.active.explained/3   [123 chars]
    en  Whatever is carrying the most load and getting the least attention, which in this village is almost always the same object.
    >>  ............................................
    pt  O que estiver carregando mais carga e recebendo menos atenção, que nesta vila é quase sempre o mesmo objeto.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the bench."

*stance family `exit` · tone `plain` · answers the beat(s) `work.engineer.maintenance_round.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.maintenance_round.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.maintenance_round.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.maintenance_round.active.respond.leave   [28 chars]
    en  I'll leave you to the bench.
    >>  ............................................
    pt  Vou deixar você com a bancada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the bench."
       spoken on: conversations.scene.work.engineer.maintenance_round.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.left`: the villager accepts. Subject `work.engineer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.engineer.doubted_idea.blocked.respond / leave; conversations.scene.work.engineer.doubted_idea.succeeded.respond / leave; conversations.scene.work.engineer.followup / leave; conversations.scene.work.engineer.maintenance_round.succeeded.respond / leave; conversations.scene.work.engineer.prototype_fault.blocked.respond / leave; conversations.scene.work.engineer.prototype_fault.failed.respond / leave; conversations.scene.work.engineer.prototype_fault.succeeded.respond / leave; conversations.topic.work.engineer.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.engineer.doubted_idea.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.engineer.maintenance_round.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.engineer.maintenance_round.succeeded` — e.g. "Three rounds in and nothing has squealed once. %2$s got its grease on the day, and I have started to enjoy the boredom of it."


```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.maintenance_round.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.engineer.maintenance_round.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.engineer.maintenance_round.succeeded.respond   [21 chars]
    en  The round is running.
    >>  ............................................
    pt  A ronda está funcionando.
    >>  ............................................
```


### Button `note_invisibility` — "The better it works, the less anyone sees you."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.engineer.maintenance_round.succeeded` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.engineer.maintenance_round.succeeded.note_invisibility` — accepted phrasings: "the better it works the less anyone sees you"; "the better it works the less anyone sees"; "good maintenance stays invisible"
  - the message must contain one of: `works`, `sees`, `invisible`, `maintenance`
  - scored words: `works`(1.8), `sees`(1.8), `invisible`(1.8), `maintenance`(1.8), `better`(0.8), `less`(0.8), `anyone`(0.8), `good`(0.8), `stays`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.maintenance_round.succeeded.respond.note_invisibility
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.maintenance_round.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.maintenance_round.succeeded.respond.note_invisibility   [46 chars]
    en  The better it works, the less anyone sees you.
    >>  ............................................
    pt  Quanto melhor funciona, menos te enxergam.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2, respect +2  _(recorded under topic `work.engineer.maintenance`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.engineer.maintenance_round"}
- Then opens: `conversations.scene.work.engineer.followup`
- …where the player's next choices will be: "What's the hardest part of building something new?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.maintenance_round.succeeded.wry
WHO    VILLAGER — what the player reads after pressing "The better it works, the less anyone sees you."
       spoken on: conversations.scene.work.engineer.maintenance_round.succeeded.respond, button `note_invisibility`
       leaves the player on: conversations.scene.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.maintenance_round.succeeded.wry`: the villager qualifys. Subject `work.engineer.maintenance`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.maintenance_round.succeeded.wry/1   [144 chars]
    en  Not one. The better I am at this the less evidence there is that I exist, and I chose the trade knowing that, and it still stings on a Thursday.
    >>  ............................................
    pt  Nem uma. Quanto melhor eu sou nisso, menos prova existe de que eu existo, e escolhi o ofício sabendo disso, e ainda arde numa quinta-feira.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.maintenance_round.succeeded.wry/2   [123 chars]
    en  That is the arrangement. I get a village that works and they get to never think about me. On balance I would take it again.
    >>  ............................................
    pt  É o acordo. Eu fico com uma vila que funciona e eles ficam com nunca pensar em mim. No fim das contas, eu escolheria de novo.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.maintenance_round.succeeded.wry/3   [100 chars]
    en  You noticed, which is annoying of you, because I had a whole speech about being unappreciated ready.
    >>  ............................................
    pt  Você reparou, o que é chato da sua parte, porque eu tinha um discurso inteiro sobre não ser reconhecida pronto.
    >>  ............................................
```


### Button `ask_add` — "What else should be on the round?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.engineer.maintenance_round.succeeded` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.engineer.maintenance_round.succeeded.ask_add` — accepted phrasings: "what else should be on the round"; "what else belongs on the list"; "what should you add to it"
  - the message must contain one of: `else`, `list`, `add`
  - scored words: `else`(1.8), `list`(1.8), `add`(1.8), `should`(0.8), `round`(0.8), `belongs`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.maintenance_round.succeeded.respond.ask_add
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.maintenance_round.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.maintenance_round.succeeded.respond.ask_add   [33 chars]
    en  What else should be on the round?
    >>  ............................................
    pt  O que mais deveria estar na ronda?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.engineer.maintenance`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.engineer.maintenance_round"}
- Then opens: `conversations.scene.work.engineer.followup`
- …where the player's next choices will be: "What's the hardest part of building something new?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.maintenance_round.succeeded.listed
WHO    VILLAGER — what the player reads after pressing "What else should be on the round?"
       spoken on: conversations.scene.work.engineer.maintenance_round.succeeded.respond, button `ask_add`
       leaves the player on: conversations.scene.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.maintenance_round.succeeded.listed`: the villager explains. Subject `work.engineer.maintenance`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.maintenance_round.succeeded.listed/1   [134 chars]
    en  Everything with a hinge, eventually. I am adding one thing a month so that the round stays a round rather than becoming my whole life.
    >>  ............................................
    pt  Tudo que tem dobradiça, com o tempo. Estou acrescentando uma coisa por mês para que a ronda continue uma ronda e não vire a minha vida inteira.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.maintenance_round.succeeded.listed/2   [94 chars]
    en  The bell frame, which nobody has looked at since it was hung and which I think about at night.
    >>  ............................................
    pt  A estrutura do sino, que ninguém olha desde que foi pendurada e na qual eu penso à noite.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.maintenance_round.succeeded.listed/3   [106 chars]
    en  Ask me what should not be on it — that is the shorter list, and keeping it short is the actual discipline.
    >>  ............................................
    pt  Me pergunte o que não deveria estar nela — essa lista é mais curta, e mantê-la curta é a disciplina de verdade.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the bench."

*stance family `exit` · tone `plain` · answers the beat(s) `work.engineer.maintenance_round.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.maintenance_round.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.maintenance_round.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.maintenance_round.succeeded.respond.leave   [28 chars]
    en  I'll leave you to the bench.
    >>  ............................................
    pt  Vou deixar você com a bancada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the bench."
       spoken on: conversations.scene.work.engineer.maintenance_round.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.left`: the villager accepts. Subject `work.engineer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.engineer.doubted_idea.blocked.respond / leave; conversations.scene.work.engineer.doubted_idea.succeeded.respond / leave; conversations.scene.work.engineer.followup / leave; conversations.scene.work.engineer.maintenance_round.active.respond / leave; conversations.scene.work.engineer.prototype_fault.blocked.respond / leave; conversations.scene.work.engineer.prototype_fault.failed.respond / leave; conversations.scene.work.engineer.prototype_fault.succeeded.respond / leave; conversations.topic.work.engineer.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.engineer.doubted_idea.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.engineer.prototype_fault.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.engineer.prototype_fault.blocked` — e.g. "%2$s works four times out of five and I have not found the fifth. It is %3$s, and I can make it happen, and I cannot make it stop."


```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.engineer.prototype_fault.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.blocked.respond   [10 chars]
    en  The bench.
    >>  ............................................
    pt  A bancada.
    >>  ............................................
```


### Button `ask_reproduce` — "Can you make it fail on purpose?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.engineer.prototype_fault.blocked` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.engineer.prototype_fault.blocked.ask_reproduce` — accepted phrasings: "can you make it fail on purpose"; "can you reproduce the fault"; "does it fail when you want it to"
  - the message must contain one of: `purpose`, `reproduce`, `fail`
  - scored words: `purpose`(1.8), `reproduce`(1.8), `fail`(1.8), `fault`(0.8), `does`(0.8), `when`(0.8), `want`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.blocked.respond.ask_reproduce
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.prototype_fault.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.blocked.respond.ask_reproduce   [32 chars]
    en  Can you make it fail on purpose?
    >>  ............................................
    pt  Você consegue fazer falhar de propósito?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, familiarity +1  _(recorded under topic `work.engineer.prototypes`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.engineer.prototype_fault"}
- Then opens: `conversations.scene.work.engineer.followup`
- …where the player's next choices will be: "What's the hardest part of building something new?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.blocked.explained
WHO    VILLAGER — what the player reads after pressing "Can you make it fail on purpose?"
       spoken on: conversations.scene.work.engineer.prototype_fault.blocked.respond, button `ask_reproduce`
       leaves the player on: conversations.scene.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.prototype_fault.blocked.explained`: the villager explains. Subject `work.engineer.prototypes`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.blocked.explained/1   [155 chars]
    en  Every time, which is the only good news I have. A fault you can summon is a fault you can corner. %2$s only hides from people who are not looking properly.
    >>  ............................................
    pt  Toda vez, que é a única boa notícia que eu tenho. Falha que se consegue invocar é falha que se consegue encurralar. %2$s só se esconde de quem não olha direito.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.prototype_fault.blocked.explained/2   [119 chars]
    en  Now I can. It took two days to get there and everybody thinks those two days were wasted, and they were the entire job.
    >>  ............................................
    pt  Agora consigo. Levei dois dias para chegar aí, e todo mundo acha que esses dois dias foram desperdiçados, e eram o trabalho inteiro.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.prototype_fault.blocked.explained/3   [144 chars]
    en  That is the right question and almost nobody asks it. Yes — six times running this morning, same conditions, same failure. Now it is arithmetic.
    >>  ............................................
    pt  Essa é a pergunta certa e quase ninguém faz. Sim — seis vezes seguidas esta manhã, mesmas condições, mesma falha. Agora é aritmética.
    >>  ............................................
```


### Button `offer_iron` — "I can bring you iron for the part."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.engineer.prototype_fault.blocked` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.engineer.prototype_fault.blocked.offer_iron` — accepted phrasings: "i can bring you iron for the part"; "let me bring you iron"; "i will fetch iron for it"
  - the message must contain one of: `iron`
  - scored words: `iron`(1.8), `bring`(0.8), `part`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.blocked.respond.offer_iron
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.prototype_fault.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.blocked.respond.offer_iron   [34 chars]
    en  I can bring you iron for the part.
    >>  ............................................
    pt  Posso trazer ferro para a peça.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.engineer.prototype_fault.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +3, warmth +2  _(recorded under topic `work.engineer.prototypes`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.prototype_fault", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.engineer.prototype_fault", "obligation": "commitment:work.engineer.bring_iron"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.engineer.bring_iron"}
- Then opens: `conversations.scene.work.engineer.followup`
- …where the player's next choices will be: "What's the hardest part of building something new?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I can bring you iron for the part."
       spoken on: conversations.scene.work.engineer.prototype_fault.blocked.respond, button `offer_iron`
       leaves the player on: conversations.scene.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.prototype_fault.blocked.accepted`: the villager accepts. Subject `work.engineer.prototypes`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.blocked.accepted/1   [102 chars]
    en  Then I can cut the part properly instead of shimming it, and %2$s stops being a thing I apologise for.
    >>  ............................................
    pt  Então posso usinar a peça direito em vez de calçar, e %2$s deixa de ser uma coisa pela qual eu peço desculpas.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.prototype_fault.blocked.accepted/2   [110 chars]
    en  You have just moved this from next month to this week. I am going to be insufferable about %2$s when it works.
    >>  ............................................
    pt  Você acabou de trazer isso do mês que vem para esta semana. Vou ser insuportável sobre %2$s quando funcionar.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.prototype_fault.blocked.accepted/3   [117 chars]
    en  Yes. Bring it and stay ten minutes — I would like somebody to watch the first run who will tell me if it looks wrong.
    >>  ............................................
    pt  Sim. Traga e fique dez minutos — quero alguém assistindo à primeira volta que me diga se parecer errado.
    >>  ............................................
```


### Button `advise_simpler` — "Build a simpler one that always works."

*stance family `candor` · tone `plain` · outcome `resisted` · answers the beat(s) `work.engineer.prototype_fault.blocked` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.engineer.prototype_fault.blocked.advise_simpler` — accepted phrasings: "build a simpler one that always works"; "make something simpler instead"; "a simple version that always works"
  - the message must contain one of: `simpler`, `simple`
  - scored words: `simpler`(1.8), `simple`(1.8), `build`(0.8), `one`(0.8), `always`(0.8), `works`(0.8), `something`(0.8), `instead`(0.8), `version`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.blocked.respond.advise_simpler
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.prototype_fault.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.blocked.respond.advise_simpler   [38 chars]
    en  Build a simpler one that always works.
    >>  ............................................
    pt  Faça um mais simples que sempre funcione.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2, tension +1  _(recorded under topic `work.engineer.prototypes`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.engineer.prototype_fault"}
- Then opens: `conversations.scene.work.engineer.followup`
- …where the player's next choices will be: "What's the hardest part of building something new?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.blocked.resisted
WHO    VILLAGER — what the player reads after pressing "Build a simpler one that always works."
       spoken on: conversations.scene.work.engineer.prototype_fault.blocked.respond, button `advise_simpler`
       leaves the player on: conversations.scene.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.prototype_fault.blocked.resisted`: the villager resists. Subject `work.engineer.prototypes`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.blocked.resisted/1   [139 chars]
    en  The simple one exists. It is a rope and two people, and it costs the village two people every day for ever. %2$s costs me a fortnight once.
    >>  ............................................
    pt  O simples existe. É uma corda e duas pessoas, e custa à vila duas pessoas por dia para sempre. %2$s me custa quinze dias uma vez.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.prototype_fault.blocked.resisted/2   [119 chars]
    en  You are describing giving up in a way that sounds like sense, and I have talked myself into it twice already this year.
    >>  ............................................
    pt  Você está descrevendo desistir de um jeito que soa como bom senso, e eu já me convenci disso duas vezes este ano.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.prototype_fault.blocked.resisted/3   [144 chars]
    en  I will grant that the simple one would be working now. I will not grant that it would be better in five years, and I am building for five years.
    >>  ............................................
    pt  Admito que o simples estaria funcionando agora. Não admito que seria melhor em cinco anos, e eu construo para cinco anos.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the bench."

*stance family `exit` · tone `plain` · answers the beat(s) `work.engineer.prototype_fault.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.prototype_fault.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.blocked.respond.leave   [28 chars]
    en  I'll leave you to the bench.
    >>  ............................................
    pt  Vou deixar você com a bancada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the bench."
       spoken on: conversations.scene.work.engineer.prototype_fault.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.left`: the villager accepts. Subject `work.engineer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.engineer.doubted_idea.blocked.respond / leave; conversations.scene.work.engineer.doubted_idea.succeeded.respond / leave; conversations.scene.work.engineer.followup / leave; conversations.scene.work.engineer.maintenance_round.active.respond / leave; conversations.scene.work.engineer.maintenance_round.succeeded.respond / leave; conversations.scene.work.engineer.prototype_fault.failed.respond / leave; conversations.scene.work.engineer.prototype_fault.succeeded.respond / leave; conversations.topic.work.engineer.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.engineer.doubted_idea.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.engineer.prototype_fault.failed.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.engineer.prototype_fault.failed` — e.g. "I have taken %2$s apart. %3$s was never going to be solved with what I have, and pretending otherwise was costing the village a working bench."


```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.failed.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.engineer.prototype_fault.failed.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.failed.respond   [15 chars]
    en  You gave it up.
    >>  ............................................
    pt  Você desistiu.
    >>  ............................................
```


### Button `ask_what_kept` — "What did you keep out of it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.engineer.prototype_fault.failed` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.engineer.prototype_fault.failed.ask_what_kept` — accepted phrasings: "what did you keep out of it"; "what came out of it"; "what did you salvage"
  - the message must contain one of: `keep`, `salvage`, `came`
  - scored words: `keep`(1.8), `salvage`(1.8), `came`(1.8), `out`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.failed.respond.ask_what_kept
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.prototype_fault.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.failed.respond.ask_what_kept   [28 chars]
    en  What did you keep out of it?
    >>  ............................................
    pt  O que você aproveitou disso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +2  _(recorded under topic `work.engineer.prototypes`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.engineer.prototype_fault"}
- Then opens: `conversations.scene.work.engineer.followup`
- …where the player's next choices will be: "What's the hardest part of building something new?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.failed.salvaged
WHO    VILLAGER — what the player reads after pressing "What did you keep out of it?"
       spoken on: conversations.scene.work.engineer.prototype_fault.failed.respond, button `ask_what_kept`
       leaves the player on: conversations.scene.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.prototype_fault.failed.salvaged`: the villager explains. Subject `work.engineer.prototypes`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.failed.salvaged/1   [127 chars]
    en  A way of holding two shafts true that I will use for the rest of my life, and a very clear sense of what I cannot do with wood.
    >>  ............................................
    pt  Um jeito de manter dois eixos alinhados que vou usar pelo resto da vida, e uma noção bem clara do que eu não consigo fazer com madeira.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.prototype_fault.failed.salvaged/2   [92 chars]
    en  The knowledge that I will not try that again, which sounds like nothing and is worth a year.
    >>  ............................................
    pt  A certeza de que não vou tentar aquilo de novo, o que parece nada e vale um ano.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.prototype_fault.failed.salvaged/3   [81 chars]
    en  Two bearings and an apology I owe the miller. In that order of usefulness, sadly.
    >>  ............................................
    pt  Dois mancais e um pedido de desculpas que devo ao moleiro. Nessa ordem de utilidade, infelizmente.
    >>  ............................................
```


### Button `respect_stopping` — "Knowing when to walk away is a skill too."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.engineer.prototype_fault.failed` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.engineer.prototype_fault.failed.respect_stopping` — accepted phrasings: "knowing when to walk away is a skill too"; "walking away is a skill as well"; "knowing when to abandon a thing takes judgement"
  - the message must contain one of: `walking`, `abandon`, `skill`
  - scored words: `walking`(1.8), `abandon`(1.8), `skill`(1.8), `knowing`(0.8), `when`(0.8), `walk`(0.8), `away`(0.8), `too`(0.8), `well`(0.8), `thing`(0.8), `takes`(0.8), `judgement`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.failed.respond.respect_stopping
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.prototype_fault.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.failed.respond.respect_stopping   [41 chars]
    en  Knowing when to walk away is a skill too.
    >>  ............................................
    pt  Saber a hora de largar também é habilidade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.engineer.prototype_fault.stopping`, budget `standard`, replay policy `once`
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.engineer.prototypes`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.engineer.prototype_fault"}
- Then opens: `conversations.scene.work.engineer.followup`
- …where the player's next choices will be: "What's the hardest part of building something new?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.failed.received
WHO    VILLAGER — what the player reads after pressing "Knowing when to walk away is a skill too."
       spoken on: conversations.scene.work.engineer.prototype_fault.failed.respond, button `respect_stopping`
       leaves the player on: conversations.scene.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.prototype_fault.failed.received`: the villager qualifys. Subject `work.engineer.prototypes`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.failed.received/1   [117 chars]
    en  It is, and it is the one I am worst at. I have been stopping this for six weeks and only actually stopped on Tuesday.
    >>  ............................................
    pt  É, e é a que eu faço pior. Venho parando isso há seis semanas e só parei de verdade na terça.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.prototype_fault.failed.received/2   [90 chars]
    en  I would like to believe that. Some days I do. Today I mostly notice the bench being empty.
    >>  ............................................
    pt  Eu gostaria de acreditar nisso. Em alguns dias acredito. Hoje eu reparo principalmente na bancada vazia.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.prototype_fault.failed.received/3   [126 chars]
    en  The difficult bit is that stopping and giving up look identical from outside, and about eighty per cent identical from inside.
    >>  ............................................
    pt  A parte difícil é que parar e desistir são idênticos vistos de fora, e uns oitenta por cento idênticos vistos de dentro.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the bench."

*stance family `exit` · tone `plain` · answers the beat(s) `work.engineer.prototype_fault.failed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.failed.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.prototype_fault.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.failed.respond.leave   [28 chars]
    en  I'll leave you to the bench.
    >>  ............................................
    pt  Vou deixar você com a bancada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the bench."
       spoken on: conversations.scene.work.engineer.prototype_fault.failed.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.left`: the villager accepts. Subject `work.engineer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.engineer.doubted_idea.blocked.respond / leave; conversations.scene.work.engineer.doubted_idea.succeeded.respond / leave; conversations.scene.work.engineer.followup / leave; conversations.scene.work.engineer.maintenance_round.active.respond / leave; conversations.scene.work.engineer.maintenance_round.succeeded.respond / leave; conversations.scene.work.engineer.prototype_fault.blocked.respond / leave; conversations.scene.work.engineer.prototype_fault.succeeded.respond / leave; conversations.topic.work.engineer.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.engineer.doubted_idea.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.engineer.prototype_fault.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.engineer.prototype_fault.succeeded` — e.g. "%2$s has run forty times without stopping. I counted all forty. I am aware of what that says about me."


```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.engineer.prototype_fault.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.succeeded.respond   [8 chars]
    en  It runs.
    >>  ............................................
    pt  Está rodando.
    >>  ............................................
```


### Button `ask_the_fix` — "What was actually wrong with it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.engineer.prototype_fault.succeeded` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.engineer.prototype_fault.succeeded.ask_the_fix` — accepted phrasings: "what was actually wrong with it"; "what was the real fault"; "what turned out to be wrong"
  - the message must contain one of: `wrong`, `fault`
  - scored words: `wrong`(1.8), `fault`(1.8), `actually`(0.8), `real`(0.8), `turned`(0.8), `out`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.succeeded.respond.ask_the_fix
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.prototype_fault.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.succeeded.respond.ask_the_fix   [32 chars]
    en  What was actually wrong with it?
    >>  ............................................
    pt  O que estava errado de verdade?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, respect +1  _(recorded under topic `work.engineer.prototypes`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.engineer.prototype_fault"}
- Then opens: `conversations.scene.work.engineer.followup`
- …where the player's next choices will be: "What's the hardest part of building something new?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.succeeded.taught
WHO    VILLAGER — what the player reads after pressing "What was actually wrong with it?"
       spoken on: conversations.scene.work.engineer.prototype_fault.succeeded.respond, button `ask_the_fix`
       leaves the player on: conversations.scene.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.prototype_fault.succeeded.taught`: the villager explains. Subject `work.engineer.prototypes`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.succeeded.taught/1   [149 chars]
    en  A gap the width of a hair that only opened when the wood swelled. So %2$s was fine in the workshop and wrong in the rain, and I test in the workshop.
    >>  ............................................
    pt  Uma folga da largura de um fio de cabelo que só abria quando a madeira inchava. Então %2$s estava bom na oficina e errado na chuva, e eu testo na oficina.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.prototype_fault.succeeded.taught/2   [117 chars]
    en  I had the load in the wrong place by about a thumb. Everything downstream of that was me solving problems I had made.
    >>  ............................................
    pt  Eu tinha posto a carga fora de lugar por uns dois centímetros. Tudo depois disso era eu resolvendo problemas que eu mesma criei.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.prototype_fault.succeeded.taught/3   [125 chars]
    en  Nothing clever. It was assembled in the wrong order, and it had been assembled in the wrong order since the first day, by me.
    >>  ............................................
    pt  Nada engenhoso. Estava montado na ordem errada, e estava na ordem errada desde o primeiro dia, por minha causa.
    >>  ............................................
```


### Button `credit_persistence` — "You stayed with it after it stopped being interesting."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.engineer.prototype_fault.succeeded` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.engineer.prototype_fault.succeeded.credit_persistence` — accepted phrasings: "you stayed with it after it stopped being interesting"; "you kept at it past the interesting part"; "staying with it was the skill"
  - the message must contain one of: `stayed`, `kept`, `staying`
  - scored words: `stayed`(1.8), `kept`(1.8), `staying`(1.8), `after`(0.8), `stopped`(0.8), `being`(0.8), `interesting`(0.8), `past`(0.8), `part`(0.8), `skill`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.succeeded.respond.credit_persistence
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.prototype_fault.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.succeeded.respond.credit_persistence   [54 chars]
    en  You stayed with it after it stopped being interesting.
    >>  ............................................
    pt  Você continuou depois de deixar de ser interessante.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.engineer.prototype_fault.credit`, budget `standard`, replay policy `once`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.engineer.prototypes`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.engineer.prototype_fault"}
- Then opens: `conversations.scene.work.engineer.followup`
- …where the player's next choices will be: "What's the hardest part of building something new?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.succeeded.credited
WHO    VILLAGER — what the player reads after pressing "You stayed with it after it stopped being interesting."
       spoken on: conversations.scene.work.engineer.prototype_fault.succeeded.respond, button `credit_persistence`
       leaves the player on: conversations.scene.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.prototype_fault.succeeded.credited`: the villager qualifys. Subject `work.engineer.prototypes`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.succeeded.credited/1   [111 chars]
    en  That is the whole trade and it is never what anybody praises. They praise the idea. The idea took an afternoon.
    >>  ............................................
    pt  Isso é o ofício inteiro e nunca é o que elogiam. Elogiam a ideia. A ideia levou uma tarde.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.prototype_fault.succeeded.credited/2   [91 chars]
    en  I nearly did not. Day nine I put the tools down and went for a walk that lasted until dark.
    >>  ............................................
    pt  Quase não continuei. No nono dia larguei as ferramentas e fui caminhar até escurecer.
    >>  ............................................
  dialogue.conversations.scene.work.engineer.prototype_fault.succeeded.credited/3   [81 chars]
    en  Thank you. Write that on my headstone rather than anything about being inventive.
    >>  ............................................
    pt  Obrigada. Escreva isso na minha lápide, em vez de qualquer coisa sobre ser inventiva.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the bench."

*stance family `exit` · tone `plain` · answers the beat(s) `work.engineer.prototype_fault.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.engineer.prototype_fault.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.engineer.prototype_fault.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.engineer.prototype_fault.succeeded.respond.leave   [28 chars]
    en  I'll leave you to the bench.
    >>  ............................................
    pt  Vou deixar você com a bancada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the bench."
       spoken on: conversations.scene.work.engineer.prototype_fault.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.left`: the villager accepts. Subject `work.engineer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.engineer.doubted_idea.blocked.respond / leave; conversations.scene.work.engineer.doubted_idea.succeeded.respond / leave; conversations.scene.work.engineer.followup / leave; conversations.scene.work.engineer.maintenance_round.active.respond / leave; conversations.scene.work.engineer.maintenance_round.succeeded.respond / leave; conversations.scene.work.engineer.prototype_fault.blocked.respond / leave; conversations.scene.work.engineer.prototype_fault.failed.respond / leave; conversations.topic.work.engineer.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.engineer.doubted_idea.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.engineer.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.engineer.craft` — e.g. "I take things apart. That's the entire method and I've been doing it since I was six and in trouble for it."


```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.engineer.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.engineer.craft.respond   [27 chars]
    en  That's how I know anything.
    >>  ............................................
    pt  É assim que eu sei alguma coisa.
    >>  ............................................
```


### Button `ask_apart` — "Does it always go back together?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.engineer.craft` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.engineer.craft.ask_apart` — accepted phrasings: "does it always go back together"
  - the message must contain one of: `apart`, `together`, `reassemble`
  - scored words: `apart`(1.2), `together`(1.5), `reassemble`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.craft.respond.ask_apart
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.craft.respond.ask_apart   [32 chars]
    en  Does it always go back together?
    >>  ............................................
    pt  Sempre volta a montar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.engineer.craft.ask_apart`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.engineer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What are you building now?" | "Mind the sparks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.craft.ask_apart
WHO    VILLAGER — what the player reads after pressing "Does it always go back together?"
       spoken on: conversations.topic.work.engineer.craft.respond, button `ask_apart`
       leaves the player on: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.craft.ask_apart`: the villager explains. Subject `work.engineer.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.engineer.craft.ask_apart/1   [94 chars]
    en  Now. Not when I was six. There's a clock in this valley that has been wrong since I was seven.
    >>  ............................................
    pt  Agora sim. Aos seis, não. Tem um relógio neste vale errado desde os meus sete anos.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.craft.ask_apart/2   [101 chars]
    en  With three more parts than it should have, sometimes, %1$s, and it runs better. I don't explain that.
    >>  ............................................
    pt  Com três peças a mais do que devia, às vezes, %1$s, e roda melhor. Eu não explico isso.
    >>  ............................................
```


### Button `admire` — "Three machines self-taught is not a small claim."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.engineer.craft` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.engineer.craft.admire` — accepted phrasings: "three machines self-taught is not a small claim"
  - the message must contain one of: `machines`, `taught`
  - scored words: `machines`(1.5), `taught`(1.2), `three`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.craft.respond.admire   [48 chars]
    en  Three machines self-taught is not a small claim.
    >>  ............................................
    pt  Três máquinas por conta própria não é pouca coisa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.engineer.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.engineer.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.engineer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What are you building now?" | "Mind the sparks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.craft.admire
WHO    VILLAGER — what the player reads after pressing "Three machines self-taught is not a small claim."
       spoken on: conversations.topic.work.engineer.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.craft.admire`: the villager accepts. Subject `work.engineer.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.engineer.craft.admire/1   [95 chars]
    en  It's three machines and about nine failures nobody counts. I'd rather the count included those.
    >>  ............................................
    pt  São três máquinas e umas nove falhas que ninguém conta. Eu preferia que a conta as incluísse.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.craft.admire/2   [86 chars]
    en  Say it to the mayor, who has described me as the person who fiddles with things, %1$s.
    >>  ............................................
    pt  Diga isso ao prefeito, que me descreveu como a pessoa que mexe nas coisas, %1$s.
    >>  ............................................
```


### Button `ask_clock` — "Whose clock did you break?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.engineer.craft` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.engineer.craft.ask_clock` — accepted phrasings: "whose clock did you break"
  - the message must contain one of: `clock`, `broke`, `whose`
  - scored words: `clock`(1.5), `broke`(1.2), `whose`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.craft.respond.ask_clock
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.craft.respond.ask_clock   [26 chars]
    en  Whose clock did you break?
    >>  ............................................
    pt  De quem era o relógio?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.engineer.craft.ask_clock`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.engineer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What are you building now?" | "Mind the sparks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.craft.ask_clock
WHO    VILLAGER — what the player reads after pressing "Whose clock did you break?"
       spoken on: conversations.topic.work.engineer.craft.respond, button `ask_clock`
       leaves the player on: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.craft.ask_clock`: the villager explains. Subject `work.engineer.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.engineer.craft.ask_clock/1   [86 chars]
    en  The church's. It's four minutes fast and the whole village sets its day by my mistake.
    >>  ............................................
    pt  Da igreja. Adianta quatro minutos e o vilarejo inteiro acerta o dia pelo meu erro.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.craft.ask_clock/2   [95 chars]
    en  My grandmother's, and she left it to me anyway, %1$s, which I've never entirely recovered from.
    >>  ............................................
    pt  Da minha avó, e ela me deixou de herança mesmo assim, %1$s, e eu nunca me recuperei disso.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.engineer.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.craft.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.engineer.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.left`: the villager accepts. Subject `work.engineer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.engineer.doubted_idea.blocked.respond / leave; conversations.scene.work.engineer.doubted_idea.succeeded.respond / leave; conversations.scene.work.engineer.followup / leave; conversations.scene.work.engineer.maintenance_round.active.respond / leave; conversations.scene.work.engineer.maintenance_round.succeeded.respond / leave; conversations.scene.work.engineer.prototype_fault.blocked.respond / leave; conversations.scene.work.engineer.prototype_fault.failed.respond / leave; conversations.scene.work.engineer.prototype_fault.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.engineer.doubted_idea.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.engineer.followup`

**Reached from 20 route(s):** `conversations.scene.work.engineer.followup` / `ask_more`; `conversations.topic.work.engineer.craft.respond` / `ask_apart`; `conversations.topic.work.engineer.craft.respond` / `admire`; `conversations.topic.work.engineer.craft.respond` / `ask_clock`; `conversations.topic.work.engineer.future.respond` / `ask_august`; `conversations.topic.work.engineer.future.respond` / `encourage`; `conversations.topic.work.engineer.future.respond` / `ask_careful`; `conversations.topic.work.engineer.respond` / `ask_hard`; `conversations.topic.work.engineer.respond` / `value`; `conversations.topic.work.engineer.respond` / `challenge`; `conversations.topic.work.engineer.respond` / `challenge`; `conversations.topic.work.engineer.risk.respond` / `ask_before` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.engineer.challenge.landed` — e.g. "Some of it explodes. That's data, %1$s, and it's the fastest kind."
- `conversations.work.prof.engineer.challenge.stung` — e.g. "...Once. It exploded once, and the whole village has kept it for four years."
- `conversations.work.prof.engineer.craft.admire` — e.g. "It's three machines and about nine failures nobody counts. I'd rather the count included those."
- `conversations.work.prof.engineer.craft.ask_apart` — e.g. "Now. Not when I was six. There's a clock in this valley that has been wrong since I was seven."
- `conversations.work.prof.engineer.craft.ask_clock` — e.g. "The church's. It's four minutes fast and the whole village sets its day by my mistake."
- `conversations.work.prof.engineer.future.ask_august` — e.g. "Three weeks of it, most years. Everybody accepts that as weather. It is not weather; it's a ditch."
- `conversations.work.prof.engineer.future.ask_careful` — e.g. "Because they won't open a working machine, and everything I know came from opening working machines."
- `conversations.work.prof.engineer.future.encourage` — e.g. "...Three weeks of flour. Not 'a ditch'. That is the entire reason I've been refused four times."
- `conversations.work.prof.engineer.hard` — e.g. "One in six. That is a very good rate and nobody in this village believes me."
- `conversations.work.prof.engineer.risk.ask_before` — e.g. "Four visits a year and a list. It would cost less than one failure and I cannot get anyone to agree."
- `conversations.work.prof.engineer.risk.ask_winch` — e.g. "Not mine. The one before mine did, in my father's time, and there's a name on a stone about it."
- `conversations.work.prof.engineer.risk.sympathise` — e.g. "...It is, and it makes me look like a man who charges a lot to fix emergencies."
- `conversations.work.prof.engineer.task.ask_race` — e.g. "It loses a third of the water it should deliver. Ninety years of a third, every day."
- `conversations.work.prof.engineer.task.ask_tooth` — e.g. "The wheel. One tooth means I'm back in four months and the mill is shut either way."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.engineer.followup   [29 chars]
    en  That's the workshop, roughly.
    >>  ............................................
    pt  É a oficina, mais ou menos.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.engineer.challenge.landed`, `work.engineer.challenge.stung`, `work.engineer.craft.admire`, `work.engineer.craft.ask_apart`, `work.engineer.craft.ask_clock`, `work.engineer.future.ask_august`, `work.engineer.future.ask_careful`, `work.engineer.future.encourage`, `work.engineer.hard`, `work.engineer.risk.ask_before`, `work.engineer.risk.ask_winch`, `work.engineer.risk.sympathise`, `work.engineer.task.ask_race`, `work.engineer.task.ask_tooth`, `work.engineer.task.offer_hands`, `work.engineer.value`, `work.engineer.village.ask_flood`, `work.engineer.village.ask_three`, `work.engineer.village.say_thanks` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.engineer.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `failure`
  - scored words: `thought`(1.2), `failure`(1.5), `step`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.engineer.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.engineer.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.engineer.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.engineer.thanks`: the villager accepts. Subject `work.engineer.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.engineer.thanks/1   [72 chars]
    en  Nobody does. They see the twitching and not the six that came before it.
    >>  ............................................
    pt  Ninguém pensa. Veem o troço espasmódico e não as seis tentativas antes dele.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.thanks/2   [61 chars]
    en  A failure is a step, %1$s. I wish I could get that on a sign.
    >>  ............................................
    pt  Uma falha é um passo, %1$s. Queria conseguir pôr isso numa placa.
    >>  ............................................
```


### Button `ask_more` — "What are you building now?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.engineer.challenge.landed`, `work.engineer.challenge.stung`, `work.engineer.craft.admire`, `work.engineer.craft.ask_apart`, `work.engineer.craft.ask_clock`, `work.engineer.future.ask_august`, `work.engineer.future.ask_careful`, `work.engineer.future.encourage`, `work.engineer.hard`, `work.engineer.risk.ask_before`, `work.engineer.risk.ask_winch`, `work.engineer.risk.sympathise`, `work.engineer.task.ask_race`, `work.engineer.task.ask_tooth`, `work.engineer.task.offer_hands`, `work.engineer.value`, `work.engineer.village.ask_flood`, `work.engineer.village.ask_three`, `work.engineer.village.say_thanks` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.engineer.more` — accepted phrasings: "what are you building now"
  - the message must contain one of: `building`, `working`
  - scored words: `building`(1.5), `now`(0.8), `working`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.followup.ask_more   [26 chars]
    en  What are you building now?
    >>  ............................................
    pt  O que você está construindo agora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.engineer.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.more
WHO    VILLAGER — what the player reads after pressing "What are you building now?"
       spoken on: conversations.topic.work.engineer.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.engineer.more`: the villager discloses. Subject `work.engineer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.engineer.more/1   [91 chars]
    en  A gate that opens when the cart is close and not when the sheep are. That is the hard half.
    >>  ............................................
    pt  Um portão que abre quando a carroça chega e não quando as ovelhas chegam. Essa é a metade difícil.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.more/2   [85 chars]
    en  Something for the well. I'll not describe it until it works — I've learned that much.
    >>  ............................................
    pt  Algo pro poço. Não vou descrever até funcionar — pelo menos isso eu aprendi.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.engineer.more/1
    en  A gate that opens for the right thing. Nobody has asked for it and I think about it constantly.
    >>  ............................................
    pt  Um portão que abre pra coisa certa. Ninguém pediu e eu penso nisso o tempo todo.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.engineer.more/2
    en  The race. I've asked four times and been refused four times, and I made the wrong argument each time.
    >>  ............................................
    pt  O canal. Pedi quatro vezes e fui recusado quatro vezes, e fiz o argumento errado todas as vezes.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.engineer.more/1
    en  A gate for carts and not sheep. It'll take a winter of thinking and I've winters to spare.
    >>  ............................................
    pt  Um portão pra carroça e não pra ovelha. Vai levar um inverno de reflexão e eu tenho invernos de sobra.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.engineer.more/2
    en  The race. It's waited ninety years; another year of asking won't hurt it.
    >>  ............................................
    pt  O canal. Esperou noventa anos; mais um ano de pedidos não vai doer.
    >>  ............................................
  confident.dialogue.conversations.work.prof.engineer.more/1
    en  A gate that opens when the cart is close and not when the sheep are. That is the hard half.
    >>  ............................................
    pt  Um portão que abre quando a carroça chega e não quando as ovelhas chegam. É a metade difícil.
    >>  ............................................
  confident.dialogue.conversations.work.prof.engineer.more/2
    en  The race, dug properly. A third more water for the same river, and the mill would run in August.
    >>  ............................................
    pt  O canal, cavado direito. Um terço a mais de água pro mesmo rio, e o moinho rodaria em agosto.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.engineer.more/1
    en  A gate that opens when the cart is close and not when the sheep are. That is the hard half.
    >>  ............................................
    pt  Um portão que abre quando a carroça chega e não quando as ovelhas chegam. É a metade difícil.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.engineer.more/2
    en  The race, dug properly. A third more water for the same river, and the mill would run in August.
    >>  ............................................
    pt  O canal, cavado direito. Um terço a mais de água pro mesmo rio, e o moinho rodaria em agosto.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.engineer.more/1
    en  A gate that opens for carts and not sheep. Come and hold the measure and I'll talk you through it.
    >>  ............................................
    pt  Um portão que abre pra carroça e não pra ovelha. Venha segurar a medida e eu te explico.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.engineer.more/2
    en  The race, properly dug. Three weeks of flour a year, for everyone — that's how I should have put it.
    >>  ............................................
    pt  O canal, bem cavado. Três semanas de farinha por ano, pra todos — era assim que eu devia ter dito.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.engineer.more/1
    en  A gate that opens for carts and not sheep. Come and hold the measure and I'll talk you through it.
    >>  ............................................
    pt  Um portão que abre pra carroça e não pra ovelha. Venha segurar a medida e eu te explico.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.engineer.more/2
    en  The race, properly dug. Three weeks of flour a year, for everyone — that's how I should have put it.
    >>  ............................................
    pt  O canal, bem cavado. Três semanas de farinha por ano, pra todos — era assim que eu devia ter dito.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.engineer.more/1
    en  A gate that opens for carts and not sheep. Come and hold the measure and I'll talk you through it.
    >>  ............................................
    pt  Um portão que abre pra carroça e não pra ovelha. Venha segurar a medida e eu te explico.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.engineer.more/2
    en  The race, properly dug. Three weeks of flour a year, for everyone — that's how I should have put it.
    >>  ............................................
    pt  O canal, bem cavado. Três semanas de farinha por ano, pra todos — era assim que eu devia ter dito.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.engineer.more/1
    en  A gate that opens for the right thing. Nobody has asked for it and I think about it constantly.
    >>  ............................................
    pt  Um portão que abre pra coisa certa. Ninguém pediu e eu penso nisso o tempo todo.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.engineer.more/2
    en  The race. I've asked four times and been refused four times, and I made the wrong argument each time.
    >>  ............................................
    pt  O canal. Pedi quatro vezes e fui recusado quatro vezes, e fiz o argumento errado todas as vezes.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.engineer.more/1
    en  A gate that opens when the cart is close and not when the sheep are. That is the hard half.
    >>  ............................................
    pt  Um portão que abre quando a carroça chega e não quando as ovelhas chegam. É a metade difícil.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.engineer.more/2
    en  The race, dug properly. A third more water for the same river, and the mill would run in August.
    >>  ............................................
    pt  O canal, cavado direito. Um terço a mais de água pro mesmo rio, e o moinho rodaria em agosto.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.engineer.more/1
    en  A gate that opens when the cart is close and not when the sheep are. That is the hard half.
    >>  ............................................
    pt  Um portão que abre quando a carroça chega e não quando as ovelhas chegam. É a metade difícil.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.engineer.more/2
    en  The race, dug properly. A third more water for the same river, and the mill would run in August.
    >>  ............................................
    pt  O canal, cavado direito. Um terço a mais de água pro mesmo rio, e o moinho rodaria em agosto.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.engineer.more/1
    en  A gate that tells a cart from a sheep. Everything else about it is already solved.
    >>  ............................................
    pt  Um portão que distingue carroça de ovelha. Todo o resto já está resolvido.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.engineer.more/2
    en  The race loses a third of what it should deliver. Ninety years of a third, every day.
    >>  ............................................
    pt  O canal perde um terço do que deveria entregar. Noventa anos de um terço, todo dia.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.engineer.more/1
    en  A gate for carts and not sheep. It'll take a winter of thinking and I've winters to spare.
    >>  ............................................
    pt  Um portão pra carroça e não pra ovelha. Vai levar um inverno de reflexão e eu tenho invernos de sobra.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.engineer.more/2
    en  The race. It's waited ninety years; another year of asking won't hurt it.
    >>  ............................................
    pt  O canal. Esperou noventa anos; mais um ano de pedidos não vai doer.
    >>  ............................................
  odd.dialogue.conversations.work.prof.engineer.more/1
    en  A gate that tells a cart from a sheep. Everything else about it is already solved.
    >>  ............................................
    pt  Um portão que distingue carroça de ovelha. Todo o resto já está resolvido.
    >>  ............................................
  odd.dialogue.conversations.work.prof.engineer.more/2
    en  The race loses a third of what it should deliver. Ninety years of a third, every day.
    >>  ............................................
    pt  O canal perde um terço do que deveria entregar. Noventa anos de um terço, todo dia.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.engineer.more/1
    en  A gate for carts and not sheep. It'll take a winter of thinking and I've winters to spare.
    >>  ............................................
    pt  Um portão pra carroça e não pra ovelha. Vai levar um inverno de reflexão e eu tenho invernos de sobra.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.engineer.more/2
    en  The race. It's waited ninety years; another year of asking won't hurt it.
    >>  ............................................
    pt  O canal. Esperou noventa anos; mais um ano de pedidos não vai doer.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.engineer.more/1
    en  A gate that knows the difference between a cart and a sheep! That is the whole engineering problem.
    >>  ............................................
    pt  Um portão que saiba a diferença entre uma carroça e uma ovelha! É todo o problema de engenharia.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.engineer.more/2
    en  The race! Ninety years of losing a third of the water and everybody calls it weather. It's a ditch!
    >>  ............................................
    pt  O canal! Noventa anos perdendo um terço da água e todos chamam de clima. É uma vala!
    >>  ............................................
  playful.dialogue.conversations.work.prof.engineer.more/1
    en  A gate that knows the difference between a cart and a sheep! That is the whole engineering problem.
    >>  ............................................
    pt  Um portão que saiba a diferença entre uma carroça e uma ovelha! É todo o problema de engenharia.
    >>  ............................................
  playful.dialogue.conversations.work.prof.engineer.more/2
    en  The race! Ninety years of losing a third of the water and everybody calls it weather. It's a ditch!
    >>  ............................................
    pt  O canal! Noventa anos perdendo um terço da água e todos chamam de clima. É uma vala!
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.engineer.more/1
    en  A gate for carts and not sheep. It'll take a winter of thinking and I've winters to spare.
    >>  ............................................
    pt  Um portão pra carroça e não pra ovelha. Vai levar um inverno de reflexão e eu tenho invernos de sobra.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.engineer.more/2
    en  The race. It's waited ninety years; another year of asking won't hurt it.
    >>  ............................................
    pt  O canal. Esperou noventa anos; mais um ano de pedidos não vai doer.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.engineer.more/1
    en  A gate that opens for the right thing. Nobody has asked for it and I think about it constantly.
    >>  ............................................
    pt  Um portão que abre pra coisa certa. Ninguém pediu e eu penso nisso o tempo todo.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.engineer.more/2
    en  The race. I've asked four times and been refused four times, and I made the wrong argument each time.
    >>  ............................................
    pt  O canal. Pedi quatro vezes e fui recusado quatro vezes, e fiz o argumento errado todas as vezes.
    >>  ............................................
  shy.dialogue.conversations.work.prof.engineer.more/1
    en  A gate that tells a cart from a sheep. Everything else about it is already solved.
    >>  ............................................
    pt  Um portão que distingue carroça de ovelha. Todo o resto já está resolvido.
    >>  ............................................
  shy.dialogue.conversations.work.prof.engineer.more/2
    en  The race loses a third of what it should deliver. Ninety years of a third, every day.
    >>  ............................................
    pt  O canal perde um terço do que deveria entregar. Noventa anos de um terço, todo dia.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.engineer.more/1
    en  A gate that knows the difference between a cart and a sheep! That is the whole engineering problem.
    >>  ............................................
    pt  Um portão que saiba a diferença entre uma carroça e uma ovelha! É todo o problema de engenharia.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.engineer.more/2
    en  The race! Ninety years of losing a third of the water and everybody calls it weather. It's a ditch!
    >>  ............................................
    pt  O canal! Noventa anos perdendo um terço da água e todos chamam de clima. É uma vala!
    >>  ............................................
  witty.dialogue.conversations.work.prof.engineer.more/1
    en  A gate that knows the difference between a cart and a sheep! That is the whole engineering problem.
    >>  ............................................
    pt  Um portão que saiba a diferença entre uma carroça e uma ovelha! É todo o problema de engenharia.
    >>  ............................................
  witty.dialogue.conversations.work.prof.engineer.more/2
    en  The race! Ninety years of losing a third of the water and everybody calls it weather. It's a ditch!
    >>  ............................................
    pt  O canal! Noventa anos perdendo um terço da água e todos chamam de clima. É uma vala!
    >>  ............................................
```

</details>


### Button `leave` — "Mind the sparks."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.engineer.challenge.landed`, `work.engineer.challenge.stung`, `work.engineer.craft.admire`, `work.engineer.craft.ask_apart`, `work.engineer.craft.ask_clock`, `work.engineer.future.ask_august`, `work.engineer.future.ask_careful`, `work.engineer.future.encourage`, `work.engineer.hard`, `work.engineer.risk.ask_before`, `work.engineer.risk.ask_winch`, `work.engineer.risk.sympathise`, `work.engineer.task.ask_race`, `work.engineer.task.ask_tooth`, `work.engineer.task.offer_hands`, `work.engineer.value`, `work.engineer.village.ask_flood`, `work.engineer.village.ask_three`, `work.engineer.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.followup.leave   [16 chars]
    en  Mind the sparks.
    >>  ............................................
    pt  Cuidado com as fagulhas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.leave
WHO    VILLAGER — what the player reads after pressing "Mind the sparks."
       spoken on: conversations.topic.work.engineer.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.left`: the villager accepts. Subject `work.engineer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.engineer.doubted_idea.blocked.respond / leave; conversations.scene.work.engineer.doubted_idea.succeeded.respond / leave; conversations.scene.work.engineer.followup / leave; conversations.scene.work.engineer.maintenance_round.active.respond / leave; conversations.scene.work.engineer.maintenance_round.succeeded.respond / leave; conversations.scene.work.engineer.prototype_fault.blocked.respond / leave; conversations.scene.work.engineer.prototype_fault.failed.respond / leave; conversations.scene.work.engineer.prototype_fault.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.engineer.doubted_idea.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.engineer.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.engineer.future` — e.g. "The race, dug properly. A third more water for the same river, and the mill would run in August."


```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.engineer.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.engineer.future.respond   [27 chars]
    en  That's what I'd build next.
    >>  ............................................
    pt  É o que eu construiria em seguida.
    >>  ............................................
```


### Button `ask_august` — "The mill doesn't run in August?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.engineer.future` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.engineer.future.ask_august` — accepted phrasings: "the mill doesn't run in august"
  - the message must contain one of: `august`, `mill`, `idle`
  - scored words: `august`(1.5), `mill`(1.0), `idle`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.future.respond.ask_august
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.future.respond.ask_august   [31 chars]
    en  The mill doesn't run in August?
    >>  ............................................
    pt  O moinho não roda em agosto?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.engineer.future.ask_august`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.engineer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What are you building now?" | "Mind the sparks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.future.ask_august
WHO    VILLAGER — what the player reads after pressing "The mill doesn't run in August?"
       spoken on: conversations.topic.work.engineer.future.respond, button `ask_august`
       leaves the player on: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.future.ask_august`: the villager explains. Subject `work.engineer.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.engineer.future.ask_august/1   [98 chars]
    en  Three weeks of it, most years. Everybody accepts that as weather. It is not weather; it's a ditch.
    >>  ............................................
    pt  Três semanas, quase todo ano. Todos aceitam como clima. Não é clima; é uma vala.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.future.ask_august/2   [76 chars]
    en  It stands idle exactly when the harvest arrives, %1$s. Ninety years of that.
    >>  ............................................
    pt  Fica parado exatamente quando a colheita chega, %1$s. Noventa anos disso.
    >>  ............................................
```


### Button `encourage` — "Put it to the miller as three weeks of flour."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.engineer.future` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.engineer.future.encourage` — accepted phrasings: "put it to the miller as three weeks of flour"
  - the message must contain one of: `flour`, `miller`, `weeks`
  - scored words: `flour`(1.5), `miller`(1.2), `weeks`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.future.respond.encourage   [45 chars]
    en  Put it to the miller as three weeks of flour.
    >>  ............................................
    pt  Coloque pro moleiro como três semanas de farinha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.engineer.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.engineer.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.engineer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What are you building now?" | "Mind the sparks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.future.encourage
WHO    VILLAGER — what the player reads after pressing "Put it to the miller as three weeks of flour."
       spoken on: conversations.topic.work.engineer.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.future.encourage`: the villager accepts. Subject `work.engineer.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.engineer.future.encourage/1   [95 chars]
    en  ...Three weeks of flour. Not 'a ditch'. That is the entire reason I've been refused four times.
    >>  ............................................
    pt  ...Três semanas de farinha. Não 'uma vala'. É a razão inteira de eu ter sido recusado quatro vezes.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.future.encourage/2   [102 chars]
    en  You've just rewritten my proposal, %1$s, and I have been making the wrong argument since I was twenty.
    >>  ............................................
    pt  Você acabou de reescrever minha proposta, %1$s, e eu venho fazendo o argumento errado desde os vinte.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour. Four refusals, and each one I took as being about me.
    >>  ............................................
    pt  ...Três semanas de farinha. Quatro recusas, e tomei cada uma como sendo sobre mim.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten my proposal, and I'm not sure whether to thank you or sit down.
    >>  ............................................
    pt  Você reescreveu minha proposta, e não sei se agradeço ou se sento.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour. Not 'a ditch'. Four refusals is a slow way to learn that.
    >>  ............................................
    pt  ...Três semanas de farinha. Não 'uma vala'. Quatro recusas é jeito lento de aprender.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten my proposal. Twenty years of the wrong argument, put right in a breath.
    >>  ............................................
    pt  Você reescreveu minha proposta. Vinte anos de argumento errado, corrigidos num fôlego.
    >>  ............................................
  confident.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour. Not 'a ditch'. That's why I've been refused four times.
    >>  ............................................
    pt  ...Três semanas de farinha. Não 'uma vala'. É por isso que me recusaram quatro vezes.
    >>  ............................................
  confident.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten my proposal, and I've made the wrong argument since I was twenty.
    >>  ............................................
    pt  Você reescreveu minha proposta, e eu venho argumentando errado desde os vinte.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour. Not 'a ditch'. That's why I've been refused four times.
    >>  ............................................
    pt  ...Três semanas de farinha. Não 'uma vala'. É por isso que me recusaram quatro vezes.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten my proposal, and I've made the wrong argument since I was twenty.
    >>  ............................................
    pt  Você reescreveu minha proposta, e eu venho argumentando errado desde os vinte.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour, %1$s. Not 'a ditch'. That's the whole of my four refusals.
    >>  ............................................
    pt  ...Três semanas de farinha, %1$s. Não 'uma vala'. É a razão das minhas quatro recusas.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten my proposal. I've been making the wrong argument since I was twenty.
    >>  ............................................
    pt  Você reescreveu minha proposta. Argumentei errado desde os vinte anos.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour, %1$s. Not 'a ditch'. That's the whole of my four refusals.
    >>  ............................................
    pt  ...Três semanas de farinha, %1$s. Não 'uma vala'. É a razão das minhas quatro recusas.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten my proposal. I've been making the wrong argument since I was twenty.
    >>  ............................................
    pt  Você reescreveu minha proposta. Argumentei errado desde os vinte anos.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour, %1$s. Not 'a ditch'. That's the whole of my four refusals.
    >>  ............................................
    pt  ...Três semanas de farinha, %1$s. Não 'uma vala'. É a razão das minhas quatro recusas.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten my proposal. I've been making the wrong argument since I was twenty.
    >>  ............................................
    pt  Você reescreveu minha proposta. Argumentei errado desde os vinte anos.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour. Four refusals, and each one I took as being about me.
    >>  ............................................
    pt  ...Três semanas de farinha. Quatro recusas, e tomei cada uma como sendo sobre mim.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten my proposal, and I'm not sure whether to thank you or sit down.
    >>  ............................................
    pt  Você reescreveu minha proposta, e não sei se agradeço ou se sento.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour. Not 'a ditch'. That's why I've been refused four times.
    >>  ............................................
    pt  ...Três semanas de farinha. Não 'uma vala'. É por isso que me recusaram quatro vezes.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten my proposal, and I've made the wrong argument since I was twenty.
    >>  ............................................
    pt  Você reescreveu minha proposta, e eu venho argumentando errado desde os vinte.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour. Not 'a ditch'. That's why I've been refused four times.
    >>  ............................................
    pt  ...Três semanas de farinha. Não 'uma vala'. É por isso que me recusaram quatro vezes.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten my proposal, and I've made the wrong argument since I was twenty.
    >>  ............................................
    pt  Você reescreveu minha proposta, e eu venho argumentando errado desde os vinte.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour. Not a ditch.
    >>  ............................................
    pt  ...Três semanas de farinha. Não uma vala.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten it. Twenty years of the wrong argument.
    >>  ............................................
    pt  Você reescreveu. Vinte anos de argumento errado.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour. Not 'a ditch'. Four refusals is a slow way to learn that.
    >>  ............................................
    pt  ...Três semanas de farinha. Não 'uma vala'. Quatro recusas é jeito lento de aprender.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten my proposal. Twenty years of the wrong argument, put right in a breath.
    >>  ............................................
    pt  Você reescreveu minha proposta. Vinte anos de argumento errado, corrigidos num fôlego.
    >>  ............................................
  odd.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour. Not a ditch.
    >>  ............................................
    pt  ...Três semanas de farinha. Não uma vala.
    >>  ............................................
  odd.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten it. Twenty years of the wrong argument.
    >>  ............................................
    pt  Você reescreveu. Vinte anos de argumento errado.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour. Not 'a ditch'. Four refusals is a slow way to learn that.
    >>  ............................................
    pt  ...Três semanas de farinha. Não 'uma vala'. Quatro recusas é jeito lento de aprender.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten my proposal. Twenty years of the wrong argument, put right in a breath.
    >>  ............................................
    pt  Você reescreveu minha proposta. Vinte anos de argumento errado, corrigidos num fôlego.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour! Not 'a ditch'. Four refusals and it was one word all along.
    >>  ............................................
    pt  ...Três semanas de farinha! Não 'uma vala'. Quatro recusas e era uma palavra só.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten my proposal in a sentence, and I've argued it wrong since I was twenty.
    >>  ............................................
    pt  Você reescreveu minha proposta numa frase, e eu argumentei errado desde os vinte.
    >>  ............................................
  playful.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour! Not 'a ditch'. Four refusals and it was one word all along.
    >>  ............................................
    pt  ...Três semanas de farinha! Não 'uma vala'. Quatro recusas e era uma palavra só.
    >>  ............................................
  playful.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten my proposal in a sentence, and I've argued it wrong since I was twenty.
    >>  ............................................
    pt  Você reescreveu minha proposta numa frase, e eu argumentei errado desde os vinte.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour. Not 'a ditch'. Four refusals is a slow way to learn that.
    >>  ............................................
    pt  ...Três semanas de farinha. Não 'uma vala'. Quatro recusas é jeito lento de aprender.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten my proposal. Twenty years of the wrong argument, put right in a breath.
    >>  ............................................
    pt  Você reescreveu minha proposta. Vinte anos de argumento errado, corrigidos num fôlego.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour. Four refusals, and each one I took as being about me.
    >>  ............................................
    pt  ...Três semanas de farinha. Quatro recusas, e tomei cada uma como sendo sobre mim.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten my proposal, and I'm not sure whether to thank you or sit down.
    >>  ............................................
    pt  Você reescreveu minha proposta, e não sei se agradeço ou se sento.
    >>  ............................................
  shy.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour. Not a ditch.
    >>  ............................................
    pt  ...Três semanas de farinha. Não uma vala.
    >>  ............................................
  shy.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten it. Twenty years of the wrong argument.
    >>  ............................................
    pt  Você reescreveu. Vinte anos de argumento errado.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour! Not 'a ditch'. Four refusals and it was one word all along.
    >>  ............................................
    pt  ...Três semanas de farinha! Não 'uma vala'. Quatro recusas e era uma palavra só.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten my proposal in a sentence, and I've argued it wrong since I was twenty.
    >>  ............................................
    pt  Você reescreveu minha proposta numa frase, e eu argumentei errado desde os vinte.
    >>  ............................................
  witty.dialogue.conversations.work.prof.engineer.future.encourage/1
    en  ...Three weeks of flour! Not 'a ditch'. Four refusals and it was one word all along.
    >>  ............................................
    pt  ...Três semanas de farinha! Não 'uma vala'. Quatro recusas e era uma palavra só.
    >>  ............................................
  witty.dialogue.conversations.work.prof.engineer.future.encourage/2
    en  You've rewritten my proposal in a sentence, and I've argued it wrong since I was twenty.
    >>  ............................................
    pt  Você reescreveu minha proposta numa frase, e eu argumentei errado desde os vinte.
    >>  ............................................
```

</details>


### Button `ask_careful` — "Why are the careful ones useless?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.engineer.future` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.engineer.future.ask_careful` — accepted phrasings: "why are the careful ones useless"
  - the message must contain one of: `careful`, `useless`, `curious`
  - scored words: `careful`(1.5), `useless`(1.2), `curious`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.future.respond.ask_careful
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.future.respond.ask_careful   [33 chars]
    en  Why are the careful ones useless?
    >>  ............................................
    pt  Por que os cuidadosos não servem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.engineer.future.ask_careful`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.engineer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What are you building now?" | "Mind the sparks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.future.ask_careful
WHO    VILLAGER — what the player reads after pressing "Why are the careful ones useless?"
       spoken on: conversations.topic.work.engineer.future.respond, button `ask_careful`
       leaves the player on: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.future.ask_careful`: the villager explains. Subject `work.engineer.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.engineer.future.ask_careful/1   [100 chars]
    en  Because they won't open a working machine, and everything I know came from opening working machines.
    >>  ............................................
    pt  Porque não abrem uma máquina que funciona, e tudo que eu sei veio de abrir máquinas que funcionam.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.future.ask_careful/2   [105 chars]
    en  Because you can teach care to somebody curious, %1$s, and you cannot teach curiosity to somebody careful.
    >>  ............................................
    pt  Porque dá pra ensinar cuidado a um curioso, %1$s, e não dá pra ensinar curiosidade a um cuidadoso.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.engineer.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.future.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.engineer.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.left`: the villager accepts. Subject `work.engineer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.engineer.doubted_idea.blocked.respond / leave; conversations.scene.work.engineer.doubted_idea.succeeded.respond / leave; conversations.scene.work.engineer.followup / leave; conversations.scene.work.engineer.maintenance_round.active.respond / leave; conversations.scene.work.engineer.maintenance_round.succeeded.respond / leave; conversations.scene.work.engineer.prototype_fault.blocked.respond / leave; conversations.scene.work.engineer.prototype_fault.failed.respond / leave; conversations.scene.work.engineer.prototype_fault.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.engineer.doubted_idea.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.engineer.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.engineer` — e.g. "Redstone, pistons, the occasional explosion — I prefer 'rapid disassembly'. Progress has a sound."


```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.engineer.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.engineer.respond   [31 chars]
    en  That's progress, and its sound.
    >>  ............................................
    pt  É o progresso, e o som dele.
    >>  ............................................
```


### Button `ask_hard` — "How many of them actually work?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.engineer.identity` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.engineer.hard` — accepted phrasings: "how many of them actually work"
  - the message must contain one of: `many`, `succeed`
  - scored words: `work`(0.5), `many`(1.2), `succeed`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.respond.ask_hard   [31 chars]
    en  How many of them actually work?
    >>  ............................................
    pt  Quantas delas realmente funcionam?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.engineer.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.engineer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What are you building now?" | "Mind the sparks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.hard
WHO    VILLAGER — what the player reads after pressing "How many of them actually work?"
       spoken on: conversations.topic.work.engineer.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.hard`: the villager explains. Subject `work.engineer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.engineer.followup / ask_more
```

> Written out in full under **`conversations.scene.work.engineer.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "The farmer's crops water themselves now."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.engineer.identity` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.engineer.value` — accepted phrasings: "the farmer's crops water themselves now"
  - the message must contain one of: `crops`, `water`, `themselves`
  - scored words: `crops`(1.5), `water`(1.2), `themselves`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.respond.value   [40 chars]
    en  The farmer's crops water themselves now.
    >>  ............................................
    pt  As plantações do fazendeiro se regam sozinhas agora.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.engineer.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.engineer.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.engineer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What are you building now?" | "Mind the sparks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.value
WHO    VILLAGER — what the player reads after pressing "The farmer's crops water themselves now."
       spoken on: conversations.topic.work.engineer.respond, button `value`
       leaves the player on: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.value`: the villager accepts. Subject `work.engineer.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.engineer.value/1   [74 chars]
    en  They do! And he still calls it 'the thing'. I have stopped correcting him.
    >>  ............................................
    pt  Regam! E ele ainda chama de 'a coisa'. Parei de corrigir.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.value/2   [60 chars]
    en  That took nine attempts and one small fire. Worth every one.
    >>  ............................................
    pt  Isso levou nove tentativas e um incêndio pequeno. Valeu cada uma.
    >>  ............................................
```


### Button `challenge` — "Most of it just explodes."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.engineer.identity` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.engineer.challenge` — accepted phrasings: "most of it just explodes"
  - the message must contain one of: `explodes`, `blow`, `fail`
  - scored words: `explodes`(1.5), `blow`(1.2), `fail`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.respond.challenge   [25 chars]
    en  Most of it just explodes.
    >>  ............................................
    pt  Boa parte disso só explode.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.engineer.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.engineer.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.engineer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What are you building now?" | "Mind the sparks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.challenge.landed
WHO    VILLAGER — what the player reads after pressing "Most of it just explodes."
       spoken on: conversations.topic.work.engineer.respond, button `challenge`
       leaves the player on: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.challenge.landed`: the villager resists. Subject `work.engineer.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.engineer.challenge.landed/1   [66 chars]
    en  Some of it explodes. That's data, %1$s, and it's the fastest kind.
    >>  ............................................
    pt  Parte explode. Isso é dado, %1$s, e é do tipo mais rápido.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.challenge.landed/2   [61 chars]
    en  It does. Then it doesn't, and then it waters a field forever.
    >>  ............................................
    pt  Explode. Aí para de explodir, e aí rega um campo pra sempre.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.engineer.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.engineer.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.engineer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What are you building now?" | "Mind the sparks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.challenge.stung
WHO    VILLAGER — what the player reads after pressing "Most of it just explodes."
       spoken on: conversations.topic.work.engineer.respond, button `challenge`
       leaves the player on: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.challenge.stung`: the villager resists. Subject `work.engineer.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.engineer.challenge.stung/1   [76 chars]
    en  ...Once. It exploded once, and the whole village has kept it for four years.
    >>  ............................................
    pt  ...Uma vez. Explodiu uma vez, e o vilarejo inteiro guarda isso faz quatro anos.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.challenge.stung/2   [52 chars]
    en  Just explodes. Right. Enjoy your hand-carried water.
    >>  ............................................
    pt  Só explode. Certo. Aproveite sua água carregada na mão.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.engineer.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.engineer.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.left`: the villager accepts. Subject `work.engineer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.engineer.doubted_idea.blocked.respond / leave; conversations.scene.work.engineer.doubted_idea.succeeded.respond / leave; conversations.scene.work.engineer.followup / leave; conversations.scene.work.engineer.maintenance_round.active.respond / leave; conversations.scene.work.engineer.maintenance_round.succeeded.respond / leave; conversations.scene.work.engineer.prototype_fault.blocked.respond / leave; conversations.scene.work.engineer.prototype_fault.failed.respond / leave; conversations.scene.work.engineer.prototype_fault.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.engineer.doubted_idea.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.engineer.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.engineer.risk` — e.g. "A winch that fails drops whatever it was holding onto whoever was underneath. I check mine weekly."


```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.engineer.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.engineer.risk.respond   [23 chars]
    en  That's what worries me.
    >>  ............................................
    pt  É isso que me preocupa.
    >>  ............................................
```


### Button `ask_before` — "What would calling you before look like?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.engineer.risk` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.engineer.risk.ask_before` — accepted phrasings: "what would calling you before look like"
  - the message must contain one of: `before`, `prevent`, `calling`
  - scored words: `before`(1.2), `prevent`(1.5), `calling`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.risk.respond.ask_before
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.risk.respond.ask_before   [40 chars]
    en  What would calling you before look like?
    >>  ............................................
    pt  Como seria te chamar antes?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.engineer.risk.ask_before`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.engineer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What are you building now?" | "Mind the sparks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.risk.ask_before
WHO    VILLAGER — what the player reads after pressing "What would calling you before look like?"
       spoken on: conversations.topic.work.engineer.risk.respond, button `ask_before`
       leaves the player on: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.risk.ask_before`: the villager explains. Subject `work.engineer.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.engineer.risk.ask_before/1   [100 chars]
    en  Four visits a year and a list. It would cost less than one failure and I cannot get anyone to agree.
    >>  ............................................
    pt  Quatro visitas por ano e uma lista. Custaria menos que uma falha e eu não convenço ninguém.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.risk.ask_before/2   [89 chars]
    en  Somebody saying 'it sounds different this week', %1$s. That sentence would save the mill.
    >>  ............................................
    pt  Alguém dizer 'o som está diferente esta semana', %1$s. Essa frase salvaria o moinho.
    >>  ............................................
```


### Button `sympathise` — "Being the person called only after is a thankless place."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.engineer.risk` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.engineer.risk.sympathise` — accepted phrasings: "being the person called only after is a thankless place"
  - the message must contain one of: `after`, `thankless`, `called`
  - scored words: `after`(1.2), `thankless`(1.5), `called`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.risk.respond.sympathise   [56 chars]
    en  Being the person called only after is a thankless place.
    >>  ............................................
    pt  Ser chamado só depois é um lugar ingrato.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.engineer.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.engineer.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.engineer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What are you building now?" | "Mind the sparks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "Being the person called only after is a thankless place."
       spoken on: conversations.topic.work.engineer.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.risk.sympathise`: the villager accepts. Subject `work.engineer.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.engineer.risk.sympathise/1   [79 chars]
    en  ...It is, and it makes me look like a man who charges a lot to fix emergencies.
    >>  ............................................
    pt  ...É, e me faz parecer um homem que cobra caro pra consertar emergências.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.risk.sympathise/2   [81 chars]
    en  It's worse than thankless, %1$s. It makes prevention look like me inventing work.
    >>  ............................................
    pt  É pior que ingrato, %1$s. Faz prevenção parecer eu inventando serviço.
    >>  ............................................
```


### Button `ask_winch` — "Has the winch ever failed?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.engineer.risk` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.engineer.risk.ask_winch` — accepted phrasings: "has the winch ever failed"
  - the message must contain one of: `winch`, `failed`
  - scored words: `winch`(1.5), `failed`(1.2), `ever`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.risk.respond.ask_winch
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.risk.respond.ask_winch   [26 chars]
    en  Has the winch ever failed?
    >>  ............................................
    pt  O guincho já falhou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.engineer.risk.ask_winch`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.engineer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What are you building now?" | "Mind the sparks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.risk.ask_winch
WHO    VILLAGER — what the player reads after pressing "Has the winch ever failed?"
       spoken on: conversations.topic.work.engineer.risk.respond, button `ask_winch`
       leaves the player on: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.risk.ask_winch`: the villager explains. Subject `work.engineer.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.engineer.risk.ask_winch/1   [95 chars]
    en  Not mine. The one before mine did, in my father's time, and there's a name on a stone about it.
    >>  ............................................
    pt  O meu não. O anterior ao meu falhou, no tempo do meu pai, e tem um nome numa pedra sobre isso.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.risk.ask_winch/2   [87 chars]
    en  Once, at the second abutment, with nobody under it. I've checked it weekly since, %1$s.
    >>  ............................................
    pt  Uma vez, no segundo encontro, sem ninguém embaixo. Confiro toda semana desde então, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.engineer.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.risk.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.engineer.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.left`: the villager accepts. Subject `work.engineer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.engineer.doubted_idea.blocked.respond / leave; conversations.scene.work.engineer.doubted_idea.succeeded.respond / leave; conversations.scene.work.engineer.followup / leave; conversations.scene.work.engineer.maintenance_round.active.respond / leave; conversations.scene.work.engineer.maintenance_round.succeeded.respond / leave; conversations.scene.work.engineer.prototype_fault.blocked.respond / leave; conversations.scene.work.engineer.prototype_fault.failed.respond / leave; conversations.scene.work.engineer.prototype_fault.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.engineer.doubted_idea.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.engineer.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.engineer.task` — e.g. "The mill gear has a tooth going and I'm deciding whether to replace one tooth or the whole wheel."


```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.engineer.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.engineer.task.respond   [32 chars]
    en  That's the problem on the bench.
    >>  ............................................
    pt  É o problema na bancada.
    >>  ............................................
```


### Button `ask_tooth` — "Which will you choose?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.engineer.task` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.engineer.task.ask_tooth` — accepted phrasings: "which will you choose"
  - the message must contain one of: `tooth`, `wheel`, `choose`
  - scored words: `tooth`(1.5), `wheel`(1.2), `choose`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.task.respond.ask_tooth
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.task.respond.ask_tooth   [22 chars]
    en  Which will you choose?
    >>  ............................................
    pt  Qual você vai escolher?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.engineer.task.ask_tooth`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.engineer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What are you building now?" | "Mind the sparks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.task.ask_tooth
WHO    VILLAGER — what the player reads after pressing "Which will you choose?"
       spoken on: conversations.topic.work.engineer.task.respond, button `ask_tooth`
       leaves the player on: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.task.ask_tooth`: the villager explains. Subject `work.engineer.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.engineer.task.ask_tooth/1   [83 chars]
    en  The wheel. One tooth means I'm back in four months and the mill is shut either way.
    >>  ............................................
    pt  A roda. Um dente significa eu voltar em quatro meses e o moinho parado do mesmo jeito.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.task.ask_tooth/2   [72 chars]
    en  One tooth, this year. The wheel when the harvest can spare a week, %1$s.
    >>  ............................................
    pt  Um dente, este ano. A roda quando a colheita puder ceder uma semana, %1$s.
    >>  ............................................
```


### Button `offer_hands` — "I can hold the measure at the far end."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.engineer.task` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.engineer.task.offer_hands` — accepted phrasings: "i can hold the measure at the far end"
  - the message must contain one of: `measure`, `level`, `hold`
  - scored words: `measure`(1.5), `level`(1.2), `hold`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.task.respond.offer_hands   [38 chars]
    en  I can hold the measure at the far end.
    >>  ............................................
    pt  Eu posso segurar a medida na outra ponta.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.engineer.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.engineer.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.engineer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What are you building now?" | "Mind the sparks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I can hold the measure at the far end."
       spoken on: conversations.topic.work.engineer.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.task.offer_hands`: the villager accepts. Subject `work.engineer.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.engineer.task.offer_hands/1   [87 chars]
    en  ...You can. Hold it level and say the number out loud even when you think it's obvious.
    >>  ............................................
    pt  ...Pode. Segure no nível e diga o número em voz alta mesmo quando parecer óbvio.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.task.offer_hands/2   [71 chars]
    en  Then we do the whole race before noon, %1$s, and I get an evening back.
    >>  ............................................
    pt  Aí a gente faz o canal inteiro antes do meio-dia, %1$s, e eu ganho uma noite.
    >>  ............................................
```


### Button `ask_race` — "Nearly, you said?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.engineer.task` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.engineer.task.ask_race` — accepted phrasings: "nearly, you said"
  - the message must contain one of: `race`, `nearly`, `water`
  - scored words: `race`(1.5), `nearly`(1.2), `water`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.task.respond.ask_race
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.task.respond.ask_race   [17 chars]
    en  Nearly, you said?
    >>  ............................................
    pt  Quase, você disse?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.engineer.task.ask_race`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.engineer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What are you building now?" | "Mind the sparks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.task.ask_race
WHO    VILLAGER — what the player reads after pressing "Nearly, you said?"
       spoken on: conversations.topic.work.engineer.task.respond, button `ask_race`
       leaves the player on: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.task.ask_race`: the villager explains. Subject `work.engineer.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.engineer.task.ask_race/1   [84 chars]
    en  It loses a third of the water it should deliver. Ninety years of a third, every day.
    >>  ............................................
    pt  Perde um terço da água que deveria entregar. Noventa anos de um terço, todo dia.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.task.ask_race/2   [102 chars]
    en  It works well enough that nobody has ever fixed it, %1$s, which is the most expensive kind of working.
    >>  ............................................
    pt  Funciona bem o bastante pra ninguém nunca ter consertado, %1$s, o tipo mais caro de funcionar.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.engineer.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.task.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.engineer.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.left`: the villager accepts. Subject `work.engineer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.engineer.doubted_idea.blocked.respond / leave; conversations.scene.work.engineer.doubted_idea.succeeded.respond / leave; conversations.scene.work.engineer.followup / leave; conversations.scene.work.engineer.maintenance_round.active.respond / leave; conversations.scene.work.engineer.maintenance_round.succeeded.respond / leave; conversations.scene.work.engineer.prototype_fault.blocked.respond / leave; conversations.scene.work.engineer.prototype_fault.failed.respond / leave; conversations.scene.work.engineer.prototype_fault.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.engineer.doubted_idea.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.engineer.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.engineer.village` — e.g. "The mill turns, the bridge lifts, the well draws. Three machines and this place stops without any of them."


```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.engineer.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.engineer.village.respond   [22 chars]
    en  That's what I hold up.
    >>  ............................................
    pt  É o que eu sustento.
    >>  ............................................
```


### Button `ask_flood` — "You shut it without permission?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.engineer.village` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.engineer.village.ask_flood` — accepted phrasings: "you shut it without permission"
  - the message must contain one of: `permission`, `flood`, `shut`
  - scored words: `permission`(1.5), `flood`(1.2), `shut`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.village.respond.ask_flood
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.village.respond.ask_flood   [31 chars]
    en  You shut it without permission?
    >>  ............................................
    pt  Você fechou sem permissão?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.engineer.village.ask_flood`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.engineer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What are you building now?" | "Mind the sparks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.village.ask_flood
WHO    VILLAGER — what the player reads after pressing "You shut it without permission?"
       spoken on: conversations.topic.work.engineer.village.respond, button `ask_flood`
       leaves the player on: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.village.ask_flood`: the villager explains. Subject `work.engineer.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.engineer.village.ask_flood/1   [104 chars]
    en  I had about nine minutes and the mayor was two fields away. I've never been thanked and I'd do it again.
    >>  ............................................
    pt  Eu tinha uns nove minutos e o prefeito estava a dois campos. Nunca fui agradecido e faria de novo.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.village.ask_flood/2   [97 chars]
    en  The miller shouted at me for an hour and brought me flour every week for a year afterwards, %1$s.
    >>  ............................................
    pt  O moleiro gritou comigo por uma hora e me trouxe farinha toda semana por um ano depois, %1$s.
    >>  ............................................
```


### Button `say_thanks` — "Nine minutes and you got it right. That deserves saying."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.engineer.village` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.engineer.village.say_thanks` — accepted phrasings: "nine minutes and you got it right. that deserves saying"
  - the message must contain one of: `minutes`, `nine`, `deserves`
  - scored words: `minutes`(1.5), `nine`(1.2), `deserves`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.village.respond.say_thanks   [56 chars]
    en  Nine minutes and you got it right. That deserves saying.
    >>  ............................................
    pt  Nove minutos e você acertou. Isso merece ser dito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.engineer.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.engineer.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.engineer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What are you building now?" | "Mind the sparks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Nine minutes and you got it right. That deserves saying."
       spoken on: conversations.topic.work.engineer.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.village.say_thanks`: the villager accepts. Subject `work.engineer.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.engineer.village.say_thanks/1   [72 chars]
    en  ...Nine minutes. No one asks how long I had. They ask why I didn't wait.
    >>  ............................................
    pt  ...Nove minutos. Ninguém pergunta quanto tempo eu tinha. Perguntam por que eu não esperei.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.village.say_thanks/2   [83 chars]
    en  It deserves saying to the mayor, %1$s, who to this day describes it as an incident.
    >>  ............................................
    pt  Merece ser dito ao prefeito, %1$s, que até hoje descreve como um incidente.
    >>  ............................................
```


### Button `ask_three` — "What happens if one of the three goes?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.engineer.village` · offered only once the villager has actually said `work:engineer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.engineer.village.ask_three` — accepted phrasings: "what happens if one of the three goes"
  - the message must contain one of: `three`, `goes`, `fails`
  - scored words: `three`(1.2), `goes`(1.0), `fails`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.village.respond.ask_three
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.village.respond.ask_three   [38 chars]
    en  What happens if one of the three goes?
    >>  ............................................
    pt  O que acontece se uma das três for?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.engineer.village.ask_three`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.engineer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What are you building now?" | "Mind the sparks."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.village.ask_three
WHO    VILLAGER — what the player reads after pressing "What happens if one of the three goes?"
       spoken on: conversations.topic.work.engineer.village.respond, button `ask_three`
       leaves the player on: conversations.topic.work.engineer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.village.ask_three`: the villager explains. Subject `work.engineer.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:engineer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.engineer.village.ask_three/1   [103 chars]
    en  The mill: no flour, four days. The winch: no stone in or out. The well: everybody carries water uphill.
    >>  ............................................
    pt  O moinho: sem farinha, quatro dias. O guincho: sem pedra entrando ou saindo. O poço: todos carregam água morro acima.
    >>  ............................................
  dialogue.conversations.work.prof.engineer.village.ask_three/2   [77 chars]
    en  The well is the one that frightens me, %1$s. The other two are inconvenience.
    >>  ............................................
    pt  O poço é o que me assusta, %1$s. Os outros dois são inconveniência.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.engineer.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.engineer.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.engineer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.engineer.village.respond.leave   [20 chars]
    en  I'll let you get on.
    >>  ............................................
    pt  Vou deixar você seguir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.engineer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.engineer.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.engineer.left`: the villager accepts. Subject `work.engineer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.engineer.doubted_idea.blocked.respond / leave; conversations.scene.work.engineer.doubted_idea.succeeded.respond / leave; conversations.scene.work.engineer.followup / leave; conversations.scene.work.engineer.maintenance_round.active.respond / leave; conversations.scene.work.engineer.maintenance_round.succeeded.respond / leave; conversations.scene.work.engineer.prototype_fault.blocked.respond / leave; conversations.scene.work.engineer.prototype_fault.failed.respond / leave; conversations.scene.work.engineer.prototype_fault.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.engineer.doubted_idea.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

