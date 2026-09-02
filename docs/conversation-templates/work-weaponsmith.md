# Work talk with a weaponsmith

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.weaponsmith.followup`](#conversations-scene-work-weaponsmith-followup)
- [`conversations.scene.work.weaponsmith.returned_blade.active.respond`](#conversations-scene-work-weaponsmith-returned-blade-active-respond)
- [`conversations.scene.work.weaponsmith.returned_blade.succeeded.respond`](#conversations-scene-work-weaponsmith-returned-blade-succeeded-respond)
- [`conversations.scene.work.weaponsmith.trade_argument.active.respond`](#conversations-scene-work-weaponsmith-trade-argument-active-respond)
- [`conversations.scene.work.weaponsmith.trade_argument.succeeded.respond`](#conversations-scene-work-weaponsmith-trade-argument-succeeded-respond)
- [`conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond`](#conversations-scene-work-weaponsmith-uneasy-commission-blocked-respond)
- [`conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond`](#conversations-scene-work-weaponsmith-uneasy-commission-succeeded-respond)
- [`conversations.topic.work.weaponsmith.craft.respond`](#conversations-topic-work-weaponsmith-craft-respond)
- [`conversations.topic.work.weaponsmith.followup`](#conversations-topic-work-weaponsmith-followup)
- [`conversations.topic.work.weaponsmith.future.respond`](#conversations-topic-work-weaponsmith-future-respond)
- [`conversations.topic.work.weaponsmith.respond`](#conversations-topic-work-weaponsmith-respond)
- [`conversations.topic.work.weaponsmith.risk.respond`](#conversations-topic-work-weaponsmith-risk-respond)
- [`conversations.topic.work.weaponsmith.task.respond`](#conversations-topic-work-weaponsmith-task-respond)
- [`conversations.topic.work.weaponsmith.village.respond`](#conversations-topic-work-weaponsmith-village-respond)

---

## `conversations.scene.work.weaponsmith.followup`

**Reached from 10 route(s):** `conversations.scene.work.weaponsmith.returned_blade.active.respond` / `ask_if_she_wants_to_know`; `conversations.scene.work.weaponsmith.returned_blade.active.respond` / `offer_iron`; `conversations.scene.work.weaponsmith.returned_blade.succeeded.respond` / `note_the_discretion`; `conversations.scene.work.weaponsmith.trade_argument.active.respond` / `ask_who_is_right`; `conversations.scene.work.weaponsmith.trade_argument.active.respond` / `advise_the_test`; `conversations.scene.work.weaponsmith.trade_argument.succeeded.respond` / `note_the_method`; `conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond` / `ask_her_rule`; `conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond` / `advise_refusing`; `conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond` / `say_it_is_hard`; `conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond` / `ask_about_not_knowing`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.weaponsmith.returned_blade.active.accepted` — e.g. "Then she has it back before her next watch, which is the only thing about this that I can actually do something about."
- `conversations.scene.work.weaponsmith.returned_blade.active.explained` — e.g. "No, and I dislike that answer about myself. Knowing would change how I make the next one, and it should not."
- `conversations.scene.work.weaponsmith.returned_blade.succeeded.acknowledged` — e.g. "It cost me nothing and it is the only thing I had to give, so I would not call it much of a virtue."
- `conversations.scene.work.weaponsmith.trade_argument.active.accepted` — e.g. "A month each and then ask them separately, so that nobody is agreeing with the room. I will propose it on Thursday."
- `conversations.scene.work.weaponsmith.trade_argument.active.explained` — e.g. "Let the watch decide after a month of carrying both. %2$s will hate that and it is the only fair test there is."
- `conversations.scene.work.weaponsmith.trade_argument.succeeded.acknowledged` — e.g. "Obviously right, and it took an outsider to say it, which is the humbling part of the whole business."
- `conversations.scene.work.weaponsmith.uneasy_commission.blocked.explained` — e.g. "Anything made to be hidden. A blade that has to be concealed is a blade for somebody who is not going to be challenged first."
- `conversations.scene.work.weaponsmith.uneasy_commission.blocked.resolved` — e.g. "I will return the payment tomorrow and say the reason plainly, which will be worse for me than saying nothing."
- `conversations.scene.work.weaponsmith.uneasy_commission.blocked.steadied` — e.g. "Every commission. Most of them take a second and are obvious, and about one a year takes a week and does not become obvious."
- `conversations.scene.work.weaponsmith.uneasy_commission.succeeded.answered` — e.g. "Almost never. That is the discipline of it — you refuse, and then you live without the ending, and you do it again next time."


```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.weaponsmith.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.weaponsmith.followup   [21 chars]
    en  Anything more to ask?
    >>  ............................................
    pt  Mais alguma pergunta?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of arming someone?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.weaponsmith.*` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.weaponsmith.followup.ask_more` — accepted phrasings: "whats the hardest part of arming someone"; "what is the hardest part of arming someone"; "hardest thing about arming people"
  - the message must contain one of: `hardest`, `arming`
  - scored words: `hardest`(1.8), `arming`(1.8), `whats`(0.8), `part`(0.8), `someone`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.weaponsmith.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.weaponsmith.followup.ask_more   [42 chars]
    en  What's the hardest part of arming someone?
    >>  ............................................
    pt  Qual é a parte mais difícil de armar alguém?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.weaponsmith.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.weaponsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you ever refused a sale?" | "Keep the edge."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of arming someone?"
       spoken on: conversations.scene.work.weaponsmith.followup, button `ask_more`
       leaves the player on: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.hard`: the villager explains. Subject `work.weaponsmith.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.weaponsmith.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.weaponsmith.hard/1   [83 chars]
    en  Badly, sometimes. You can't read a man across a counter, and I've been wrong twice.
    >>  ............................................
    pt  Mal, às vezes. Não dá pra ler alguém do outro lado do balcão, e eu errei duas vezes.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.hard/2   [71 chars]
    en  I ask what it's for and I listen to how long they take to answer, %1$s.
    >>  ............................................
    pt  Eu pergunto pra que serve e escuto quanto tempo levam pra responder, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the anvil."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.weaponsmith.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.weaponsmith.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.weaponsmith.followup.leave   [28 chars]
    en  I'll leave you to the anvil.
    >>  ............................................
    pt  Vou deixar você com a bigorna.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the anvil."
       spoken on: conversations.scene.work.weaponsmith.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.left`: the villager accepts. Subject `work.weaponsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.weaponsmith.returned_blade.active.respond / leave; conversations.scene.work.weaponsmith.returned_blade.succeeded.respond / leave; conversations.scene.work.weaponsmith.trade_argument.active.respond / leave; conversations.scene.work.weaponsmith.trade_argument.succeeded.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond / leave; conversations.topic.work.weaponsmith.craft.respond / leave; conversations.topic.work.weaponsmith.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.weaponsmith.leave/1   [53 chars]
    en  It's patient. Unlike the customer waiting behind you.
    >>  ............................................
    pt  Ela é paciente. Diferente do cliente atrás de você.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.leave/2   [27 chars]
    en  Aye. Mind the sparks, %1$s.
    >>  ............................................
    pt  É. Cuidado com as fagulhas, %1$s.
    >>  ............................................
```

---


## `conversations.scene.work.weaponsmith.returned_blade.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.weaponsmith.returned_blade.active` — e.g. "One of the watch brought a blade back with %2$s, which means it was used, which means somebody was on the other end of it."


```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.returned_blade.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.weaponsmith.returned_blade.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.weaponsmith.returned_blade.active.respond   [25 chars]
    en  The blade that came back.
    >>  ............................................
    pt  A lâmina que voltou.
    >>  ............................................
```


### Button `ask_if_she_wants_to_know` — "Would you rather know?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.weaponsmith.returned_blade.active` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.weaponsmith.returned_blade.active.ask_if_she_wants_to_know` — accepted phrasings: "would you rather know"; "would you rather know"; "do you want to know what happened"
  - the message must contain one of: `rather`, `know`, `happened`
  - scored words: `rather`(1.8), `know`(1.8), `happened`(1.8), `want`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.returned_blade.active.respond.ask_if_she_wants_to_know
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.weaponsmith.returned_blade.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.weaponsmith.returned_blade.active.respond.ask_if_she_wants_to_know   [22 chars]
    en  Would you rather know?
    >>  ............................................
    pt  Você preferiria saber?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.weaponsmith.edges`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.weaponsmith.returned_blade"}
- Then opens: `conversations.scene.work.weaponsmith.followup`
- …where the player's next choices will be: "What's the hardest part of arming someone?" | "I'll leave you to the anvil."

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.returned_blade.active.explained
WHO    VILLAGER — what the player reads after pressing "Would you rather know?"
       spoken on: conversations.scene.work.weaponsmith.returned_blade.active.respond, button `ask_if_she_wants_to_know`
       leaves the player on: conversations.scene.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.returned_blade.active.explained`: the villager explains. Subject `work.weaponsmith.edges`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.weaponsmith.returned_blade.active.explained/1   [108 chars]
    en  No, and I dislike that answer about myself. Knowing would change how I make the next one, and it should not.
    >>  ............................................
    pt  Não, e não gosto dessa resposta a meu respeito. Saber mudaria como eu faço a próxima, e não deveria.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.returned_blade.active.explained/2   [116 chars]
    en  It is her night, not mine. If she wants to tell me she will, and if she does I will listen and I will not repeat it.
    >>  ............................................
    pt  A noite é dela, não minha. Se ela quiser contar, conta, e se contar eu escuto e não repito.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.returned_blade.active.explained/3   [134 chars]
    en  I know enough. A notch that deep is a hard stop against something that was not wood, and there is no version where I need the details.
    >>  ............................................
    pt  Eu sei o bastante. Uma lasca fundo assim é uma parada dura contra algo que não era madeira, e não existe versão em que eu precise dos detalhes.
    >>  ............................................
```


### Button `offer_iron` — "I'll bring iron for the repair."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.weaponsmith.returned_blade.active` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.weaponsmith.returned_blade.active.offer_iron` — accepted phrasings: "ill bring iron for the repair"; "i can bring iron for the repair"; "let me fetch iron for that blade"
  - the message must contain one of: `iron`, `repair`
  - scored words: `iron`(1.8), `repair`(1.8), `ill`(0.8), `bring`(0.8), `let`(0.8), `fetch`(0.8), `blade`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.returned_blade.active.respond.offer_iron
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.weaponsmith.returned_blade.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.weaponsmith.returned_blade.active.respond.offer_iron   [31 chars]
    en  I'll bring iron for the repair.
    >>  ............................................
    pt  Vou trazer ferro para o conserto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.weaponsmith.blade.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.weaponsmith.edges`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.returned_blade", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.weaponsmith.returned_blade", "obligation": "commitment:work.weaponsmith.bring_iron"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.weaponsmith.bring_iron"}
- Then opens: `conversations.scene.work.weaponsmith.followup`
- …where the player's next choices will be: "What's the hardest part of arming someone?" | "I'll leave you to the anvil."

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.returned_blade.active.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring iron for the repair."
       spoken on: conversations.scene.work.weaponsmith.returned_blade.active.respond, button `offer_iron`
       leaves the player on: conversations.scene.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.returned_blade.active.accepted`: the villager accepts. Subject `work.weaponsmith.edges`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.weaponsmith.returned_blade.active.accepted/1   [118 chars]
    en  Then she has it back before her next watch, which is the only thing about this that I can actually do something about.
    >>  ............................................
    pt  Então ela recebe de volta antes da próxima ronda, que é a única coisa nisso tudo sobre a qual eu posso de fato fazer algo.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.returned_blade.active.accepted/2   [125 chars]
    en  Bring it and I will work through the morning. She should not have to stand a night with a borrowed blade she has not learned.
    >>  ............................................
    pt  Traga e eu trabalho a manhã inteira. Ela não deveria passar uma noite com uma lâmina emprestada que não conhece.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.returned_blade.active.accepted/3   [119 chars]
    en  Yes. And I will put a new grip on it while I am there, because she has been holding it wrong for a year and never said.
    >>  ............................................
    pt  Sim. E vou pôr um punho novo enquanto estou nisso, porque ela segura errado há um ano e nunca disse.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the hammer."

*stance family `exit` · tone `plain` · answers the beat(s) `work.weaponsmith.returned_blade.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.returned_blade.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.weaponsmith.returned_blade.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.weaponsmith.returned_blade.active.respond.leave   [36 chars]
    en  I'll let you get back to the hammer.
    >>  ............................................
    pt  Vou deixar você voltar ao martelo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the hammer."
       spoken on: conversations.scene.work.weaponsmith.returned_blade.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.left`: the villager accepts. Subject `work.weaponsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.weaponsmith.followup / leave; conversations.scene.work.weaponsmith.returned_blade.succeeded.respond / leave; conversations.scene.work.weaponsmith.trade_argument.active.respond / leave; conversations.scene.work.weaponsmith.trade_argument.succeeded.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond / leave; conversations.topic.work.weaponsmith.craft.respond / leave; conversations.topic.work.weaponsmith.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.weaponsmith.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.weaponsmith.returned_blade.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.weaponsmith.returned_blade.succeeded` — e.g. "Repaired and handed back before her watch. She weighed it, nodded, and that was the entire conversation."


```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.returned_blade.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.weaponsmith.returned_blade.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.weaponsmith.returned_blade.succeeded.respond   [11 chars]
    en  That blade.
    >>  ............................................
    pt  Aquela lâmina.
    >>  ............................................
```


### Button `note_the_discretion` — "Your discretion was the kind part."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.weaponsmith.returned_blade.succeeded` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.weaponsmith.returned_blade.succeeded.note_the_discretion` — accepted phrasings: "your discretion was the kind part"; "leaving the question alone was the kindness"; "your discretion was the kind part"
  - the message must contain one of: `discretion`, `kindness`, `question`
  - scored words: `discretion`(1.8), `kindness`(1.8), `question`(1.8), `kind`(0.8), `part`(0.8), `leaving`(0.8), `alone`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.returned_blade.succeeded.respond.note_the_discretion
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.weaponsmith.returned_blade.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.weaponsmith.returned_blade.succeeded.respond.note_the_discretion   [34 chars]
    en  Your discretion was the kind part.
    >>  ............................................
    pt  Sua discrição foi a parte gentil.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +3  _(recorded under topic `work.weaponsmith.edges`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.weaponsmith.returned_blade"}
- Then opens: `conversations.scene.work.weaponsmith.followup`
- …where the player's next choices will be: "What's the hardest part of arming someone?" | "I'll leave you to the anvil."

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.returned_blade.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Your discretion was the kind part."
       spoken on: conversations.scene.work.weaponsmith.returned_blade.succeeded.respond, button `note_the_discretion`
       leaves the player on: conversations.scene.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.returned_blade.succeeded.acknowledged`: the villager accepts. Subject `work.weaponsmith.edges`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.weaponsmith.returned_blade.succeeded.acknowledged/1   [99 chars]
    en  It cost me nothing and it is the only thing I had to give, so I would not call it much of a virtue.
    >>  ............................................
    pt  Não me custou nada e era a única coisa que eu tinha para dar, então eu não chamaria de grande virtude.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.returned_blade.succeeded.acknowledged/2   [128 chars]
    en  Thank you. I have watched people be asked at the well the morning after, by three separate neighbours, all of them meaning well.
    >>  ............................................
    pt  Obrigada. Já vi gente ser interrogada no poço na manhã seguinte, por três vizinhos diferentes, todos bem-intencionados.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.returned_blade.succeeded.acknowledged/3   [118 chars]
    en  She will tell somebody, eventually. It should be somebody she chose, not the woman who happens to have made the thing.
    >>  ............................................
    pt  Ela vai contar a alguém, um dia. Deve ser alguém que ela escolheu, não a mulher que por acaso fabricou a coisa.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the hammer."

*stance family `exit` · tone `plain` · answers the beat(s) `work.weaponsmith.returned_blade.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.returned_blade.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.weaponsmith.returned_blade.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.weaponsmith.returned_blade.succeeded.respond.leave   [36 chars]
    en  I'll let you get back to the hammer.
    >>  ............................................
    pt  Vou deixar você voltar ao martelo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the hammer."
       spoken on: conversations.scene.work.weaponsmith.returned_blade.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.left`: the villager accepts. Subject `work.weaponsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.weaponsmith.followup / leave; conversations.scene.work.weaponsmith.returned_blade.active.respond / leave; conversations.scene.work.weaponsmith.trade_argument.active.respond / leave; conversations.scene.work.weaponsmith.trade_argument.succeeded.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond / leave; conversations.topic.work.weaponsmith.craft.respond / leave; conversations.topic.work.weaponsmith.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.weaponsmith.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.weaponsmith.trade_argument.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.weaponsmith.trade_argument.active` — e.g. "%2$s and I disagree about weight, and we have been disagreeing about it, politely, for four years."


```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.trade_argument.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.weaponsmith.trade_argument.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.weaponsmith.trade_argument.active.respond   [13 chars]
    en  The argument.
    >>  ............................................
    pt  A discussão.
    >>  ............................................
```


### Button `ask_who_is_right` — "How would you settle it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.weaponsmith.trade_argument.active` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.weaponsmith.trade_argument.active.ask_who_is_right` — accepted phrasings: "how would you settle it"; "how would you settle it"; "what would settle the argument"
  - the message must contain one of: `settle`, `argument`
  - scored words: `settle`(1.8), `argument`(1.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.trade_argument.active.respond.ask_who_is_right
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.weaponsmith.trade_argument.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.weaponsmith.trade_argument.active.respond.ask_who_is_right   [24 chars]
    en  How would you settle it?
    >>  ............................................
    pt  Como você resolveria isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.weaponsmith.balance`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.weaponsmith.trade_argument"}
- Then opens: `conversations.scene.work.weaponsmith.followup`
- …where the player's next choices will be: "What's the hardest part of arming someone?" | "I'll leave you to the anvil."

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.trade_argument.active.explained
WHO    VILLAGER — what the player reads after pressing "How would you settle it?"
       spoken on: conversations.scene.work.weaponsmith.trade_argument.active.respond, button `ask_who_is_right`
       leaves the player on: conversations.scene.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.trade_argument.active.explained`: the villager explains. Subject `work.weaponsmith.balance`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.weaponsmith.trade_argument.active.explained/1   [111 chars]
    en  Let the watch decide after a month of carrying both. %2$s will hate that and it is the only fair test there is.
    >>  ............................................
    pt  Deixar a ronda decidir depois de um mês carregando as duas. %2$s vai detestar, e é o único teste justo que existe.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.trade_argument.active.explained/2   [112 chars]
    en  Neither of us can. That is the honest position. Both of us are arguing from four good years and no measurements.
    >>  ............................................
    pt  Nenhum de nós dois pode. Essa é a posição honesta. Os dois argumentam a partir de quatro bons anos e nenhuma medição.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.trade_argument.active.explained/3   [107 chars]
    en  I would rather be shown wrong by a guard than agreed with by %2$s, and I would like that quoted accurately.
    >>  ............................................
    pt  Prefiro ser corrigida por um guarda a receber a concordância de %2$s, e eu gostaria que isso fosse citado com exatidão.
    >>  ............................................
```


### Button `advise_the_test` — "Let the watch carry both and choose."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.weaponsmith.trade_argument.active` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.weaponsmith.trade_argument.active.advise_the_test` — accepted phrasings: "let the watch carry both and choose"; "let the watch carry both and choose"; "put both to the people who use them"
  - the message must contain one of: `carry`, `choose`, `both`
  - scored words: `carry`(1.8), `choose`(1.8), `both`(1.8), `let`(0.8), `watch`(0.8), `put`(0.8), `people`(0.8), `who`(0.8), `use`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.trade_argument.active.respond.advise_the_test
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.weaponsmith.trade_argument.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.weaponsmith.trade_argument.active.respond.advise_the_test   [36 chars]
    en  Let the watch carry both and choose.
    >>  ............................................
    pt  Deixe a ronda carregar as duas e escolher.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.weaponsmith.balance`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.weaponsmith.trade_argument"}
- Then opens: `conversations.scene.work.weaponsmith.followup`
- …where the player's next choices will be: "What's the hardest part of arming someone?" | "I'll leave you to the anvil."

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.trade_argument.active.accepted
WHO    VILLAGER — what the player reads after pressing "Let the watch carry both and choose."
       spoken on: conversations.scene.work.weaponsmith.trade_argument.active.respond, button `advise_the_test`
       leaves the player on: conversations.scene.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.trade_argument.active.accepted`: the villager accepts. Subject `work.weaponsmith.balance`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.weaponsmith.trade_argument.active.accepted/1   [115 chars]
    en  A month each and then ask them separately, so that nobody is agreeing with the room. I will propose it on Thursday.
    >>  ............................................
    pt  Um mês cada e depois perguntar a cada um em separado, para ninguém concordar com a sala. Vou propor na quinta.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.trade_argument.active.accepted/2   [114 chars]
    en  Yes. And I have to accept the answer if it goes against me, which is the part I have been avoiding for four years.
    >>  ............................................
    pt  Sim. E eu tenho que aceitar a resposta se for contra mim, que é a parte que eu venho evitando há quatro anos.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.trade_argument.active.accepted/3   [126 chars]
    en  It costs me two blades and my pride. Both of those are cheaper than the watch carrying the wrong thing for another four years.
    >>  ............................................
    pt  Me custa duas lâminas e o orgulho. As duas coisas são mais baratas do que a ronda carregar a coisa errada por mais quatro anos.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the hammer."

*stance family `exit` · tone `plain` · answers the beat(s) `work.weaponsmith.trade_argument.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.trade_argument.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.weaponsmith.trade_argument.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.weaponsmith.trade_argument.active.respond.leave   [36 chars]
    en  I'll let you get back to the hammer.
    >>  ............................................
    pt  Vou deixar você voltar ao martelo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the hammer."
       spoken on: conversations.scene.work.weaponsmith.trade_argument.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.left`: the villager accepts. Subject `work.weaponsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.weaponsmith.followup / leave; conversations.scene.work.weaponsmith.returned_blade.active.respond / leave; conversations.scene.work.weaponsmith.returned_blade.succeeded.respond / leave; conversations.scene.work.weaponsmith.trade_argument.succeeded.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond / leave; conversations.topic.work.weaponsmith.craft.respond / leave; conversations.topic.work.weaponsmith.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.weaponsmith.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.weaponsmith.trade_argument.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.weaponsmith.trade_argument.succeeded` — e.g. "The watch chose lighter, five to two, and %2$s took it better than I would have."


```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.trade_argument.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.weaponsmith.trade_argument.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.weaponsmith.trade_argument.succeeded.respond   [14 chars]
    en  That argument.
    >>  ............................................
    pt  Aquela discussão.
    >>  ............................................
```


### Button `note_the_method` — "Asking the people who carry them was right."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.weaponsmith.trade_argument.succeeded` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.weaponsmith.trade_argument.succeeded.note_the_method` — accepted phrasings: "asking the people who carry them was right"; "asking the people who carry them was right"; "the users were the right judges"
  - the message must contain one of: `carry`, `users`, `judges`
  - scored words: `carry`(1.8), `users`(1.8), `judges`(1.8), `asking`(0.8), `people`(0.8), `who`(0.8), `right`(0.8), `were`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.trade_argument.succeeded.respond.note_the_method
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.weaponsmith.trade_argument.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.weaponsmith.trade_argument.succeeded.respond.note_the_method   [43 chars]
    en  Asking the people who carry them was right.
    >>  ............................................
    pt  Perguntar a quem carrega estava certo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.weaponsmith.balance`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.weaponsmith.trade_argument"}
- Then opens: `conversations.scene.work.weaponsmith.followup`
- …where the player's next choices will be: "What's the hardest part of arming someone?" | "I'll leave you to the anvil."

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.trade_argument.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Asking the people who carry them was right."
       spoken on: conversations.scene.work.weaponsmith.trade_argument.succeeded.respond, button `note_the_method`
       leaves the player on: conversations.scene.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.trade_argument.succeeded.acknowledged`: the villager accepts. Subject `work.weaponsmith.balance`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.weaponsmith.trade_argument.succeeded.acknowledged/1   [101 chars]
    en  Obviously right, and it took an outsider to say it, which is the humbling part of the whole business.
    >>  ............................................
    pt  Obviamente certo, e precisou de alguém de fora dizer, que é a parte humilhante da história toda.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.trade_argument.succeeded.acknowledged/2   [116 chars]
    en  Thank you. Two craftspeople arguing will argue for a decade. Two craftspeople asking will have an answer in a month.
    >>  ............................................
    pt  Obrigada. Dois artesãos discutindo discutem por uma década. Dois artesãos perguntando têm resposta em um mês.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.trade_argument.succeeded.acknowledged/3   [115 chars]
    en  %2$s and I have started doing it for everything now. We disagree twice as often and it takes a quarter of the time.
    >>  ............................................
    pt  %2$s e eu passamos a fazer isso para tudo agora. Discordamos o dobro das vezes e leva um quarto do tempo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the hammer."

*stance family `exit` · tone `plain` · answers the beat(s) `work.weaponsmith.trade_argument.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.trade_argument.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.weaponsmith.trade_argument.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.weaponsmith.trade_argument.succeeded.respond.leave   [36 chars]
    en  I'll let you get back to the hammer.
    >>  ............................................
    pt  Vou deixar você voltar ao martelo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the hammer."
       spoken on: conversations.scene.work.weaponsmith.trade_argument.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.left`: the villager accepts. Subject `work.weaponsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.weaponsmith.followup / leave; conversations.scene.work.weaponsmith.returned_blade.active.respond / leave; conversations.scene.work.weaponsmith.returned_blade.succeeded.respond / leave; conversations.scene.work.weaponsmith.trade_argument.active.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond / leave; conversations.topic.work.weaponsmith.craft.respond / leave; conversations.topic.work.weaponsmith.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.weaponsmith.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.weaponsmith.uneasy_commission.blocked` — e.g. "%3$s came in and asked for %2$s and would give me no reason, and I have not started it."


```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond   [15 chars]
    en  The commission.
    >>  ............................................
    pt  A encomenda.
    >>  ............................................
```


### Button `ask_her_rule` — "What do you refuse to make?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.weaponsmith.uneasy_commission.blocked` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.weaponsmith.uneasy_commission.blocked.ask_her_rule` — accepted phrasings: "what do you refuse to make"; "what do you refuse to make"; "which pieces are off your bench"
  - the message must contain one of: `refuse`, `pieces`, `bench`
  - scored words: `refuse`(1.8), `pieces`(1.8), `bench`(1.8), `make`(0.8), `which`(0.8), `off`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond.ask_her_rule
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond.ask_her_rule   [27 chars]
    en  What do you refuse to make?
    >>  ............................................
    pt  O que você se recusa a fazer?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.weaponsmith.who_buys`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.weaponsmith.uneasy_commission"}
- Then opens: `conversations.scene.work.weaponsmith.followup`
- …where the player's next choices will be: "What's the hardest part of arming someone?" | "I'll leave you to the anvil."

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What do you refuse to make?"
       spoken on: conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond, button `ask_her_rule`
       leaves the player on: conversations.scene.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.uneasy_commission.blocked.explained`: the villager explains. Subject `work.weaponsmith.who_buys`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.explained/1   [125 chars]
    en  Anything made to be hidden. A blade that has to be concealed is a blade for somebody who is not going to be challenged first.
    >>  ............................................
    pt  Qualquer coisa feita para ser escondida. Uma lâmina que precisa ser ocultada é uma lâmina para quem não vai ser desafiado antes.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.explained/2   [106 chars]
    en  Nothing for a child, and nothing where the buyer will not say the word for what it is out loud in my shop.
    >>  ............................................
    pt  Nada para criança, e nada em que o comprador se recuse a dizer em voz alta, na minha oficina, o nome do que é.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.explained/3   [100 chars]
    en  I have one rule and it is short, and I have broken it once, and that piece is why the rule is short.
    >>  ............................................
    pt  Tenho uma regra e ela é curta, e eu quebrei uma vez, e é por causa daquela peça que a regra é curta.
    >>  ............................................
```


### Button `advise_refusing` — "Turn that commission down."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.weaponsmith.uneasy_commission.blocked` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.weaponsmith.uneasy_commission.blocked.advise_refusing` — accepted phrasings: "turn that commission down"; "turn that commission down"; "let that order go"
  - the message must contain one of: `commission`, `order`, `down`
  - scored words: `commission`(1.8), `order`(1.8), `down`(1.8), `turn`(0.8), `let`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond.advise_refusing
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond.advise_refusing   [26 chars]
    en  Turn that commission down.
    >>  ............................................
    pt  Recuse essa encomenda.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.weaponsmith.commission.backed`, budget `standard`, replay policy `once`
- Does: disposition — respect +4, trust +1  _(recorded under topic `work.weaponsmith.who_buys`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.weaponsmith.uneasy_commission"}
- Then opens: `conversations.scene.work.weaponsmith.followup`
- …where the player's next choices will be: "What's the hardest part of arming someone?" | "I'll leave you to the anvil."

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.resolved
WHO    VILLAGER — what the player reads after pressing "Turn that commission down."
       spoken on: conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond, button `advise_refusing`
       leaves the player on: conversations.scene.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.uneasy_commission.blocked.resolved`: the villager accepts. Subject `work.weaponsmith.who_buys`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.resolved/1   [110 chars]
    en  I will return the payment tomorrow and say the reason plainly, which will be worse for me than saying nothing.
    >>  ............................................
    pt  Vou devolver o pagamento amanhã e dizer o motivo com todas as letras, o que vai ser pior para mim do que não dizer nada.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.resolved/2   [115 chars]
    en  Yes. And they will find somebody in the town who asks fewer questions, and I have to be able to live with that too.
    >>  ............................................
    pt  Sim. E vão encontrar alguém na cidade que pergunta menos, e eu também preciso conseguir conviver com isso.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.resolved/3   [116 chars]
    en  That is what I have known since Tuesday. I needed to hear somebody else say it before I would act on my own stomach.
    >>  ............................................
    pt  É o que eu sei desde terça. Eu precisava ouvir outra pessoa dizer antes de agir com base no meu próprio estômago.
    >>  ............................................
```


### Button `say_it_is_hard` — "That's a lot to weigh on your own."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.weaponsmith.uneasy_commission.blocked` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.weaponsmith.uneasy_commission.blocked.say_it_is_hard` — accepted phrasings: "thats a lot to weigh on your own"; "that is a lot to weigh on your own"; "you carry that judgement alone"
  - the message must contain one of: `weigh`, `judgement`, `carry`
  - scored words: `weigh`(1.8), `judgement`(1.8), `carry`(1.8), `thats`(0.8), `lot`(0.8), `own`(0.8), `alone`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond.say_it_is_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond.say_it_is_hard   [34 chars]
    en  That's a lot to weigh on your own.
    >>  ............................................
    pt  É muita coisa para pesar sozinha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, trust +1  _(recorded under topic `work.weaponsmith.who_buys`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.weaponsmith.uneasy_commission"}
- Then opens: `conversations.scene.work.weaponsmith.followup`
- …where the player's next choices will be: "What's the hardest part of arming someone?" | "I'll leave you to the anvil."

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.steadied
WHO    VILLAGER — what the player reads after pressing "That's a lot to weigh on your own."
       spoken on: conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond, button `say_it_is_hard`
       leaves the player on: conversations.scene.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.uneasy_commission.blocked.steadied`: the villager accepts. Subject `work.weaponsmith.who_buys`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.steadied/1   [124 chars]
    en  Every commission. Most of them take a second and are obvious, and about one a year takes a week and does not become obvious.
    >>  ............................................
    pt  Toda encomenda. A maioria leva um segundo e é óbvia, e umas uma por ano leva uma semana e nunca fica óbvia.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.steadied/2   [131 chars]
    en  Thank you. People either think I should refuse everything or that I should think about none of it, and neither of those is a trade.
    >>  ............................................
    pt  Obrigada. As pessoas acham que eu deveria recusar tudo ou que não deveria pensar em nada, e nenhuma das duas é um ofício.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.steadied/3   [108 chars]
    en  It is the whole job, actually. The metal is the easy half. I could teach the metal to anyone in three years.
    >>  ............................................
    pt  É o trabalho inteiro, na verdade. O metal é a metade fácil. Eu ensinaria o metal a qualquer um em três anos.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the hammer."

*stance family `exit` · tone `plain` · answers the beat(s) `work.weaponsmith.uneasy_commission.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond.leave   [36 chars]
    en  I'll let you get back to the hammer.
    >>  ............................................
    pt  Vou deixar você voltar ao martelo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the hammer."
       spoken on: conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.left`: the villager accepts. Subject `work.weaponsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.weaponsmith.followup / leave; conversations.scene.work.weaponsmith.returned_blade.active.respond / leave; conversations.scene.work.weaponsmith.returned_blade.succeeded.respond / leave; conversations.scene.work.weaponsmith.trade_argument.active.respond / leave; conversations.scene.work.weaponsmith.trade_argument.succeeded.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond / leave; conversations.topic.work.weaponsmith.craft.respond / leave; conversations.topic.work.weaponsmith.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.weaponsmith.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.weaponsmith.uneasy_commission.succeeded` — e.g. "I gave %2$s the money back and said why. They were angry for about a minute and then they were something else."


```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond   [16 chars]
    en  That commission.
    >>  ............................................
    pt  Aquela encomenda.
    >>  ............................................
```


### Button `ask_about_not_knowing` — "Do you ever find out if you were right?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.weaponsmith.uneasy_commission.succeeded` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.weaponsmith.uneasy_commission.succeeded.ask_about_not_knowing` — accepted phrasings: "do you ever find out if you were right"; "do you ever find out if you were right"; "how do you know your judgement was right"
  - the message must contain one of: `right`, `judgement`, `find`
  - scored words: `right`(1.8), `judgement`(1.8), `find`(1.8), `ever`(0.8), `out`(0.8), `were`(0.8), `know`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond.ask_about_not_knowing
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond.ask_about_not_knowing   [39 chars]
    en  Do you ever find out if you were right?
    >>  ............................................
    pt  Você chega a saber se estava certa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3  _(recorded under topic `work.weaponsmith.who_buys`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.weaponsmith.uneasy_commission"}
- Then opens: `conversations.scene.work.weaponsmith.followup`
- …where the player's next choices will be: "What's the hardest part of arming someone?" | "I'll leave you to the anvil."

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.uneasy_commission.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "Do you ever find out if you were right?"
       spoken on: conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond, button `ask_about_not_knowing`
       leaves the player on: conversations.scene.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.uneasy_commission.succeeded.answered`: the villager explains. Subject `work.weaponsmith.who_buys`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.succeeded.answered/1   [125 chars]
    en  Almost never. That is the discipline of it — you refuse, and then you live without the ending, and you do it again next time.
    >>  ............................................
    pt  Quase nunca. É essa a disciplina — você recusa, e depois vive sem o final, e faz de novo na próxima vez.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.succeeded.answered/2   [117 chars]
    en  Once. Four years ago. I would rather not have found out, and I would make the same refusal again knowing what I know.
    >>  ............................................
    pt  Uma vez. Quatro anos atrás. Eu preferiria não ter descoberto, e faria a mesma recusa de novo sabendo o que sei.
    >>  ............................................
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.succeeded.answered/3   [130 chars]
    en  The one time I said yes against my own judgement, I found out within the year. That is the only evidence I have and it was enough.
    >>  ............................................
    pt  A única vez em que eu disse sim contra o meu próprio julgamento, descobri dentro do ano. É a única prova que tenho e bastou.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the hammer."

*stance family `exit` · tone `plain` · answers the beat(s) `work.weaponsmith.uneasy_commission.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond.leave   [36 chars]
    en  I'll let you get back to the hammer.
    >>  ............................................
    pt  Vou deixar você voltar ao martelo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the hammer."
       spoken on: conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.left`: the villager accepts. Subject `work.weaponsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.weaponsmith.followup / leave; conversations.scene.work.weaponsmith.returned_blade.active.respond / leave; conversations.scene.work.weaponsmith.returned_blade.succeeded.respond / leave; conversations.scene.work.weaponsmith.trade_argument.active.respond / leave; conversations.scene.work.weaponsmith.trade_argument.succeeded.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond / leave; conversations.topic.work.weaponsmith.craft.respond / leave; conversations.topic.work.weaponsmith.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.weaponsmith.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.weaponsmith.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.weaponsmith.craft` — e.g. "Temper is the whole of it. Anyone can shape steel; almost nobody can persuade it to stay shaped."


```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.weaponsmith.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.weaponsmith.craft.respond   [22 chars]
    en  That's how it was got.
    >>  ............................................
    pt  Foi assim que se obteve.
    >>  ............................................
```


### Button `ask_watching` — "Four years of just watching?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.weaponsmith.craft` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.weaponsmith.craft.ask_watching` — accepted phrasings: "four years of just watching"
  - the message must contain one of: `watching`, `silent`
  - scored words: `watching`(1.5), `years`(0.8), `silent`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.craft.respond.ask_watching
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.craft.respond.ask_watching   [28 chars]
    en  Four years of just watching?
    >>  ............................................
    pt  Quatro anos só observando?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.weaponsmith.craft.ask_watching`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.weaponsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you ever refused a sale?" | "Keep the edge."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.craft.ask_watching
WHO    VILLAGER — what the player reads after pressing "Four years of just watching?"
       spoken on: conversations.topic.work.weaponsmith.craft.respond, button `ask_watching`
       leaves the player on: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.craft.ask_watching`: the villager explains. Subject `work.weaponsmith.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.weaponsmith.craft.ask_watching/1   [89 chars]
    en  Four years of watching and being shouted at when I looked away. It worked, unfortunately.
    >>  ............................................
    pt  Quatro anos observando e levando grito quando eu desviava o olhar. Funcionou, infelizmente.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.craft.ask_watching/2   [74 chars]
    en  He'd hand me a ruined piece and say 'why'. I got very good at 'why', %1$s.
    >>  ............................................
    pt  Ele me entregava uma peça estragada e dizia 'por quê'. Fiquei muito bom em 'por quê', %1$s.
    >>  ............................................
```


### Button `admire` — "Persuading steel to stay is a good way to put it."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.weaponsmith.craft` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.weaponsmith.craft.admire` — accepted phrasings: "persuading steel to stay is a good way to put it"
  - the message must contain one of: `persuading`, `steel`, `phrase`
  - scored words: `persuading`(1.5), `steel`(1.2), `phrase`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.craft.respond.admire   [49 chars]
    en  Persuading steel to stay is a good way to put it.
    >>  ............................................
    pt  Convencer o aço a ficar é um bom jeito de dizer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.weaponsmith.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.weaponsmith.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.weaponsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you ever refused a sale?" | "Keep the edge."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.craft.admire
WHO    VILLAGER — what the player reads after pressing "Persuading steel to stay is a good way to put it."
       spoken on: conversations.topic.work.weaponsmith.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.craft.admire`: the villager accepts. Subject `work.weaponsmith.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.weaponsmith.craft.admire/1   [83 chars]
    en  It's the only honest way. Steel has opinions and the fire changes them temporarily.
    >>  ............................................
    pt  É o único jeito honesto. Aço tem opinião e o fogo muda ela temporariamente.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.craft.admire/2   [70 chars]
    en  I've said that for years and you're the first to repeat it back, %1$s.
    >>  ............................................
    pt  Digo isso há anos e você é o primeiro a repetir de volta, %1$s.
    >>  ............................................
```


### Button `ask_teach_differently` — "Would you teach it the same way?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.weaponsmith.craft` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.weaponsmith.craft.ask_teach_differently` — accepted phrasings: "would you teach it the same way"
  - the message must contain one of: `teach`, `same`, `differently`
  - scored words: `teach`(1.5), `same`(1.0), `differently`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.craft.respond.ask_teach_differently
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.craft.respond.ask_teach_differently   [32 chars]
    en  Would you teach it the same way?
    >>  ............................................
    pt  Você ensinaria do mesmo jeito?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.weaponsmith.craft.ask_teach_differently`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.weaponsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you ever refused a sale?" | "Keep the edge."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.craft.ask_teach_differently
WHO    VILLAGER — what the player reads after pressing "Would you teach it the same way?"
       spoken on: conversations.topic.work.weaponsmith.craft.respond, button `ask_teach_differently`
       leaves the player on: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.craft.ask_teach_differently`: the villager explains. Subject `work.weaponsmith.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.weaponsmith.craft.ask_teach_differently/1   [91 chars]
    en  No. I'd explain. Watching taught me the trade and it also taught me four unnecessary years.
    >>  ............................................
    pt  Não. Eu explicaria. Observar me ensinou o ofício e também me custou quatro anos desnecessários.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.craft.ask_teach_differently/2   [77 chars]
    en  The shouting, no. The watching, yes. There is no substitute for the watching.
    >>  ............................................
    pt  O grito, não. A observação, sim. Não há substituto pra observação.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the anvil."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.weaponsmith.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.craft.respond.leave   [35 chars]
    en  I'll let you get back to the anvil.
    >>  ............................................
    pt  Vou deixar você voltar à bigorna.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the anvil."
       spoken on: conversations.topic.work.weaponsmith.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.left`: the villager accepts. Subject `work.weaponsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.weaponsmith.followup / leave; conversations.scene.work.weaponsmith.returned_blade.active.respond / leave; conversations.scene.work.weaponsmith.returned_blade.succeeded.respond / leave; conversations.scene.work.weaponsmith.trade_argument.active.respond / leave; conversations.scene.work.weaponsmith.trade_argument.succeeded.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond / leave; conversations.topic.work.weaponsmith.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.weaponsmith.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.weaponsmith.followup`

**Reached from 20 route(s):** `conversations.scene.work.weaponsmith.followup` / `ask_more`; `conversations.topic.work.weaponsmith.craft.respond` / `ask_watching`; `conversations.topic.work.weaponsmith.craft.respond` / `admire`; `conversations.topic.work.weaponsmith.craft.respond` / `ask_teach_differently`; `conversations.topic.work.weaponsmith.future.respond` / `ask_ploughs`; `conversations.topic.work.weaponsmith.future.respond` / `encourage`; `conversations.topic.work.weaponsmith.future.respond` / `ask_nerve`; `conversations.topic.work.weaponsmith.respond` / `ask_hard`; `conversations.topic.work.weaponsmith.respond` / `value`; `conversations.topic.work.weaponsmith.respond` / `challenge`; `conversations.topic.work.weaponsmith.respond` / `challenge`; `conversations.topic.work.weaponsmith.risk.respond` / `ask_blade` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.weaponsmith.challenge.landed` — e.g. "I do. I've never once pretended otherwise, and I sleep on that, not around it."
- `conversations.work.prof.weaponsmith.challenge.stung` — e.g. "...And the axe that splits your firewood. Same forge, same hands."
- `conversations.work.prof.weaponsmith.craft.admire` — e.g. "It's the only honest way. Steel has opinions and the fire changes them temporarily."
- `conversations.work.prof.weaponsmith.craft.ask_teach_differently` — e.g. "No. I'd explain. Watching taught me the trade and it also taught me four unnecessary years."
- `conversations.work.prof.weaponsmith.craft.ask_watching` — e.g. "Four years of watching and being shouted at when I looked away. It worked, unfortunately."
- `conversations.work.prof.weaponsmith.future.ask_nerve` — e.g. "Because if I make it and it's ordinary, then it was always going to be ordinary."
- `conversations.work.prof.weaponsmith.future.ask_ploughs` — e.g. "The watch. And a reputation that took twenty years and would take one season to lose."
- `conversations.work.prof.weaponsmith.future.encourage` — e.g. "...One selfish thing. I've not thought of it in those terms and it changes the arithmetic."
- `conversations.work.prof.weaponsmith.hard` — e.g. "Badly, sometimes. You can't read a man across a counter, and I've been wrong twice."
- `conversations.work.prof.weaponsmith.risk.ask_blade` — e.g. "I don't know. That is the exact problem and it has been the problem for six years."
- `conversations.work.prof.weaponsmith.risk.ask_refuse` — e.g. "More than I used to. It costs me custom and I have made my peace with that."
- `conversations.work.prof.weaponsmith.risk.sympathise` — e.g. "...I know that. Knowing it and believing it are two separate pieces of work."
- `conversations.work.prof.weaponsmith.task.ask_commission` — e.g. "Nothing, on paper. The man who ordered it would not look at me while he described it."
- `conversations.work.prof.weaponsmith.task.ask_damp` — e.g. "It eats a blade from the inside where you can't see it. Six months and it's a decorative bar."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.weaponsmith.followup   [36 chars]
    en  That's the trade, sharp end and all.
    >>  ............................................
    pt  É o ofício, ponta afiada e tudo.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.weaponsmith.challenge.landed`, `work.weaponsmith.challenge.stung`, `work.weaponsmith.craft.admire`, `work.weaponsmith.craft.ask_teach_differently`, `work.weaponsmith.craft.ask_watching`, `work.weaponsmith.future.ask_nerve`, `work.weaponsmith.future.ask_ploughs`, `work.weaponsmith.future.encourage`, `work.weaponsmith.hard`, `work.weaponsmith.risk.ask_blade`, `work.weaponsmith.risk.ask_refuse`, `work.weaponsmith.risk.sympathise`, `work.weaponsmith.task.ask_commission`, `work.weaponsmith.task.ask_damp`, `work.weaponsmith.task.offer_hands`, `work.weaponsmith.value`, `work.weaponsmith.village.ask_coin`, `work.weaponsmith.village.ask_raid`, `work.weaponsmith.village.say_thanks` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.weaponsmith.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `blade`, `clearly`
  - scored words: `thought`(1.2), `blade`(1.2), `clearly`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.weaponsmith.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.weaponsmith.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.weaponsmith.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.weaponsmith.thanks`: the villager accepts. Subject `work.weaponsmith.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.weaponsmith.thanks/1   [71 chars]
    en  Most don't want to. It's easier to buy a blade than to think about one.
    >>  ............................................
    pt  A maioria não quer pensar. É mais fácil comprar uma lâmina do que pensar nela.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.thanks/2   [68 chars]
    en  It's not a comfortable trade to think about clearly, %1$s. I'd know.
    >>  ............................................
    pt  Não é um ofício confortável de se pensar com clareza, %1$s. Eu sei bem.
    >>  ............................................
```


### Button `ask_more` — "Have you ever refused a sale?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.weaponsmith.challenge.landed`, `work.weaponsmith.challenge.stung`, `work.weaponsmith.craft.admire`, `work.weaponsmith.craft.ask_teach_differently`, `work.weaponsmith.craft.ask_watching`, `work.weaponsmith.future.ask_nerve`, `work.weaponsmith.future.ask_ploughs`, `work.weaponsmith.future.encourage`, `work.weaponsmith.hard`, `work.weaponsmith.risk.ask_blade`, `work.weaponsmith.risk.ask_refuse`, `work.weaponsmith.risk.sympathise`, `work.weaponsmith.task.ask_commission`, `work.weaponsmith.task.ask_damp`, `work.weaponsmith.task.offer_hands`, `work.weaponsmith.value`, `work.weaponsmith.village.ask_coin`, `work.weaponsmith.village.ask_raid`, `work.weaponsmith.village.say_thanks` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.weaponsmith.more` — accepted phrasings: "have you ever refused a sale"
  - the message must contain one of: `refused`, `sale`, `turned`
  - scored words: `refused`(1.5), `sale`(1.5), `turned`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.followup.ask_more   [29 chars]
    en  Have you ever refused a sale?
    >>  ............................................
    pt  Você já recusou uma venda?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.weaponsmith.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.more
WHO    VILLAGER — what the player reads after pressing "Have you ever refused a sale?"
       spoken on: conversations.topic.work.weaponsmith.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.weaponsmith.more`: the villager discloses. Subject `work.weaponsmith.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.weaponsmith.more/1   [76 chars]
    en  Three times. Two of them left angry and one of them thanked me a year later.
    >>  ............................................
    pt  Três vezes. Duas saíram com raiva e uma me agradeceu um ano depois.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.more/2   [85 chars]
    en  Once. He came back with someone else's blade, which taught me what refusing is worth.
    >>  ............................................
    pt  Uma. Ele voltou com a lâmina de outro, o que me ensinou quanto vale recusar.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times, and there's a fourth I should have refused and didn't. I think about that one.
    >>  ............................................
    pt  Três vezes, e tem uma quarta que eu devia ter recusado e não recusei. Eu penso nessa.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of ploughs. Nobody's family gets a visit because of a plough, and that is the whole appeal.
    >>  ............................................
    pt  Um ano de arados. Nenhuma família recebe visita por causa de um arado, e é todo o apelo.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times. It costs me custom each time and I've made my peace with that.
    >>  ............................................
    pt  Três vezes. Me custa clientela toda vez e eu fiz as pazes com isso.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of ploughs, one day. The watch will still want blades, and the year will still be there.
    >>  ............................................
    pt  Um ano de arados, um dia. A guarda ainda vai querer lâminas, e o ano ainda vai estar lá.
    >>  ............................................
  confident.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times. Two of them left angry and one of them thanked me a year later.
    >>  ............................................
    pt  Três vezes. Dois saíram bravos e um me agradeceu um ano depois.
    >>  ............................................
  confident.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of making ploughs. Same fire, same steel, and nobody asks what it's for.
    >>  ............................................
    pt  Um ano fazendo arados. Mesmo fogo, mesmo aço, e ninguém pergunta pra que serve.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times. Two of them left angry and one of them thanked me a year later.
    >>  ............................................
    pt  Três vezes. Dois saíram bravos e um me agradeceu um ano depois.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of making ploughs. Same fire, same steel, and nobody asks what it's for.
    >>  ............................................
    pt  Um ano fazendo arados. Mesmo fogo, mesmo aço, e ninguém pergunta pra que serve.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times. The one who thanked me came back specially, and I've thought about that ever since.
    >>  ............................................
    pt  Três vezes. O que agradeceu voltou de propósito, e eu penso nisso desde então.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of ploughs. I'd still be at the same anvil; you'd not notice the difference from the lane.
    >>  ............................................
    pt  Um ano de arados. Eu estaria na mesma bigorna; você não notaria a diferença da viela.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times. The one who thanked me came back specially, and I've thought about that ever since.
    >>  ............................................
    pt  Três vezes. O que agradeceu voltou de propósito, e eu penso nisso desde então.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of ploughs. I'd still be at the same anvil; you'd not notice the difference from the lane.
    >>  ............................................
    pt  Um ano de arados. Eu estaria na mesma bigorna; você não notaria a diferença da viela.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times. The one who thanked me came back specially, and I've thought about that ever since.
    >>  ............................................
    pt  Três vezes. O que agradeceu voltou de propósito, e eu penso nisso desde então.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of ploughs. I'd still be at the same anvil; you'd not notice the difference from the lane.
    >>  ............................................
    pt  Um ano de arados. Eu estaria na mesma bigorna; você não notaria a diferença da viela.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times, and there's a fourth I should have refused and didn't. I think about that one.
    >>  ............................................
    pt  Três vezes, e tem uma quarta que eu devia ter recusado e não recusei. Eu penso nessa.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of ploughs. Nobody's family gets a visit because of a plough, and that is the whole appeal.
    >>  ............................................
    pt  Um ano de arados. Nenhuma família recebe visita por causa de um arado, e é todo o apelo.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times. Two of them left angry and one of them thanked me a year later.
    >>  ............................................
    pt  Três vezes. Dois saíram bravos e um me agradeceu um ano depois.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of making ploughs. Same fire, same steel, and nobody asks what it's for.
    >>  ............................................
    pt  Um ano fazendo arados. Mesmo fogo, mesmo aço, e ninguém pergunta pra que serve.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times. Two of them left angry and one of them thanked me a year later.
    >>  ............................................
    pt  Três vezes. Dois saíram bravos e um me agradeceu um ano depois.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of making ploughs. Same fire, same steel, and nobody asks what it's for.
    >>  ............................................
    pt  Um ano fazendo arados. Mesmo fogo, mesmo aço, e ninguém pergunta pra que serve.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times. I ask what it's for and I watch how long they take to answer.
    >>  ............................................
    pt  Três vezes. Eu pergunto pra que serve e observo quanto tempo levam pra responder.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of ploughs. It would cost me a reputation that took twenty years to build.
    >>  ............................................
    pt  Um ano de arados. Custaria uma reputação que levou vinte anos pra construir.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times. It costs me custom each time and I've made my peace with that.
    >>  ............................................
    pt  Três vezes. Me custa clientela toda vez e eu fiz as pazes com isso.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of ploughs, one day. The watch will still want blades, and the year will still be there.
    >>  ............................................
    pt  Um ano de arados, um dia. A guarda ainda vai querer lâminas, e o ano ainda vai estar lá.
    >>  ............................................
  odd.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times. I ask what it's for and I watch how long they take to answer.
    >>  ............................................
    pt  Três vezes. Eu pergunto pra que serve e observo quanto tempo levam pra responder.
    >>  ............................................
  odd.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of ploughs. It would cost me a reputation that took twenty years to build.
    >>  ............................................
    pt  Um ano de arados. Custaria uma reputação que levou vinte anos pra construir.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times. It costs me custom each time and I've made my peace with that.
    >>  ............................................
    pt  Três vezes. Me custa clientela toda vez e eu fiz as pazes com isso.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of ploughs, one day. The watch will still want blades, and the year will still be there.
    >>  ............................................
    pt  Um ano de arados, um dia. A guarda ainda vai querer lâminas, e o ano ainda vai estar lá.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times! Two left angry, one thanked me a year later. That's a better rate than I expected.
    >>  ............................................
    pt  Três vezes! Dois saíram bravos, um me agradeceu um ano depois. Melhor taxa do que eu esperava.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of ploughs. Same fire, same steel, no awkward questions. It sounds like a holiday.
    >>  ............................................
    pt  Um ano de arados. Mesmo fogo, mesmo aço, sem perguntas incômodas. Parece férias.
    >>  ............................................
  playful.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times! Two left angry, one thanked me a year later. That's a better rate than I expected.
    >>  ............................................
    pt  Três vezes! Dois saíram bravos, um me agradeceu um ano depois. Melhor taxa do que eu esperava.
    >>  ............................................
  playful.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of ploughs. Same fire, same steel, no awkward questions. It sounds like a holiday.
    >>  ............................................
    pt  Um ano de arados. Mesmo fogo, mesmo aço, sem perguntas incômodas. Parece férias.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times. It costs me custom each time and I've made my peace with that.
    >>  ............................................
    pt  Três vezes. Me custa clientela toda vez e eu fiz as pazes com isso.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of ploughs, one day. The watch will still want blades, and the year will still be there.
    >>  ............................................
    pt  Um ano de arados, um dia. A guarda ainda vai querer lâminas, e o ano ainda vai estar lá.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times, and there's a fourth I should have refused and didn't. I think about that one.
    >>  ............................................
    pt  Três vezes, e tem uma quarta que eu devia ter recusado e não recusei. Eu penso nessa.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of ploughs. Nobody's family gets a visit because of a plough, and that is the whole appeal.
    >>  ............................................
    pt  Um ano de arados. Nenhuma família recebe visita por causa de um arado, e é todo o apelo.
    >>  ............................................
  shy.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times. I ask what it's for and I watch how long they take to answer.
    >>  ............................................
    pt  Três vezes. Eu pergunto pra que serve e observo quanto tempo levam pra responder.
    >>  ............................................
  shy.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of ploughs. It would cost me a reputation that took twenty years to build.
    >>  ............................................
    pt  Um ano de arados. Custaria uma reputação que levou vinte anos pra construir.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times! Two left angry, one thanked me a year later. That's a better rate than I expected.
    >>  ............................................
    pt  Três vezes! Dois saíram bravos, um me agradeceu um ano depois. Melhor taxa do que eu esperava.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of ploughs. Same fire, same steel, no awkward questions. It sounds like a holiday.
    >>  ............................................
    pt  Um ano de arados. Mesmo fogo, mesmo aço, sem perguntas incômodas. Parece férias.
    >>  ............................................
  witty.dialogue.conversations.work.prof.weaponsmith.more/1
    en  Three times! Two left angry, one thanked me a year later. That's a better rate than I expected.
    >>  ............................................
    pt  Três vezes! Dois saíram bravos, um me agradeceu um ano depois. Melhor taxa do que eu esperava.
    >>  ............................................
  witty.dialogue.conversations.work.prof.weaponsmith.more/2
    en  A year of ploughs. Same fire, same steel, no awkward questions. It sounds like a holiday.
    >>  ............................................
    pt  Um ano de arados. Mesmo fogo, mesmo aço, sem perguntas incômodas. Parece férias.
    >>  ............................................
```

</details>


### Button `leave` — "Keep the edge."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.weaponsmith.challenge.landed`, `work.weaponsmith.challenge.stung`, `work.weaponsmith.craft.admire`, `work.weaponsmith.craft.ask_teach_differently`, `work.weaponsmith.craft.ask_watching`, `work.weaponsmith.future.ask_nerve`, `work.weaponsmith.future.ask_ploughs`, `work.weaponsmith.future.encourage`, `work.weaponsmith.hard`, `work.weaponsmith.risk.ask_blade`, `work.weaponsmith.risk.ask_refuse`, `work.weaponsmith.risk.sympathise`, `work.weaponsmith.task.ask_commission`, `work.weaponsmith.task.ask_damp`, `work.weaponsmith.task.offer_hands`, `work.weaponsmith.value`, `work.weaponsmith.village.ask_coin`, `work.weaponsmith.village.ask_raid`, `work.weaponsmith.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.followup.leave   [14 chars]
    en  Keep the edge.
    >>  ............................................
    pt  Mantenha o fio.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.leave
WHO    VILLAGER — what the player reads after pressing "Keep the edge."
       spoken on: conversations.topic.work.weaponsmith.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.left`: the villager accepts. Subject `work.weaponsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.weaponsmith.followup / leave; conversations.scene.work.weaponsmith.returned_blade.active.respond / leave; conversations.scene.work.weaponsmith.returned_blade.succeeded.respond / leave; conversations.scene.work.weaponsmith.trade_argument.active.respond / leave; conversations.scene.work.weaponsmith.trade_argument.succeeded.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond / leave; conversations.topic.work.weaponsmith.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.weaponsmith.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.weaponsmith.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.weaponsmith.future` — e.g. "I'd like to make ploughs for a year. Same fire, same steel, and nobody asks what it's for."


```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.weaponsmith.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.weaponsmith.future.respond   [25 chars]
    en  That's the far end of it.
    >>  ............................................
    pt  É o extremo da coisa.
    >>  ............................................
```


### Button `ask_ploughs` — "What's keeping you from ploughs?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.weaponsmith.future` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.weaponsmith.future.ask_ploughs` — accepted phrasings: "what's keeping you from ploughs"
  - the message must contain one of: `ploughs`, `keeping`, `farming`
  - scored words: `ploughs`(1.5), `keeping`(1.2), `farming`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.future.respond.ask_ploughs
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.future.respond.ask_ploughs   [32 chars]
    en  What's keeping you from ploughs?
    >>  ............................................
    pt  O que te afasta dos arados?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.weaponsmith.future.ask_ploughs`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.weaponsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you ever refused a sale?" | "Keep the edge."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.future.ask_ploughs
WHO    VILLAGER — what the player reads after pressing "What's keeping you from ploughs?"
       spoken on: conversations.topic.work.weaponsmith.future.respond, button `ask_ploughs`
       leaves the player on: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.future.ask_ploughs`: the villager explains. Subject `work.weaponsmith.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.weaponsmith.future.ask_ploughs/1   [85 chars]
    en  The watch. And a reputation that took twenty years and would take one season to lose.
    >>  ............................................
    pt  A guarda. E uma reputação que levou vinte anos e levaria uma estação pra perder.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.future.ask_ploughs/2   [92 chars]
    en  Nothing but habit, if I'm honest with you, %1$s. That's a worse answer than a real obstacle.
    >>  ............................................
    pt  Nada além de hábito, se for honesto com você, %1$s. É uma resposta pior que um obstáculo real.
    >>  ............................................
```


### Button `encourage` — "Make the blade. You've earned one selfish thing."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.weaponsmith.future` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.weaponsmith.future.encourage` — accepted phrasings: "make the blade. you've earned one selfish thing"
  - the message must contain one of: `blade`, `selfish`, `earned`
  - scored words: `blade`(1.2), `selfish`(1.5), `earned`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.future.respond.encourage   [48 chars]
    en  Make the blade. You've earned one selfish thing.
    >>  ............................................
    pt  Faça a lâmina. Você merece uma coisa egoísta.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.weaponsmith.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.weaponsmith.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.weaponsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you ever refused a sale?" | "Keep the edge."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.future.encourage
WHO    VILLAGER — what the player reads after pressing "Make the blade. You've earned one selfish thing."
       spoken on: conversations.topic.work.weaponsmith.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.future.encourage`: the villager accepts. Subject `work.weaponsmith.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.weaponsmith.future.encourage/1   [90 chars]
    en  ...One selfish thing. I've not thought of it in those terms and it changes the arithmetic.
    >>  ............................................
    pt  ...Uma coisa egoísta. Não tinha pensado nesses termos e isso muda a conta.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.future.encourage/2   [80 chars]
    en  Earned. Ha. Say that again in front of the mayor and see what it costs me, %1$s.
    >>  ............................................
    pt  Merecer. Ha. Diga isso de novo na frente do prefeito e veja o que me custa, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing. I've never allowed myself the phrase, let alone the year.
    >>  ............................................
    pt  ...Uma coisa egoísta. Nunca me permiti a frase, quanto mais o ano.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned. I'd like to believe that and I'd need somebody to keep saying it.
    >>  ............................................
    pt  Merecido. Eu gostaria de acreditar e precisaria de alguém repetindo.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing. Forty years at the fire and I've not taken one yet.
    >>  ............................................
    pt  ...Uma coisa egoísta. Quarenta anos na forja e ainda não tirei uma.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned. Say that in front of the mayor and we'll both learn something.
    >>  ............................................
    pt  Merecido. Diga isso na frente do prefeito e nós dois aprendemos algo.
    >>  ............................................
  confident.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing. I've not thought of it in those terms and it changes the arithmetic.
    >>  ............................................
    pt  ...Uma coisa egoísta. Não pensei nesses termos e isso muda a conta.
    >>  ............................................
  confident.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned. Ha. Say that again in front of the mayor and see what it costs me.
    >>  ............................................
    pt  Merecido. Ha. Diga de novo na frente do prefeito e veja quanto me custa.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing. I've not thought of it in those terms and it changes the arithmetic.
    >>  ............................................
    pt  ...Uma coisa egoísta. Não pensei nesses termos e isso muda a conta.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned. Ha. Say that again in front of the mayor and see what it costs me.
    >>  ............................................
    pt  Merecido. Ha. Diga de novo na frente do prefeito e veja quanto me custa.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing, %1$s. I'd not thought of it that way and it changes the arithmetic.
    >>  ............................................
    pt  ...Uma coisa egoísta, %1$s. Não pensei assim e isso muda a conta.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned. Say that again in front of the mayor and see what it costs me.
    >>  ............................................
    pt  Merecido. Diga de novo na frente do prefeito e veja quanto me custa.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing, %1$s. I'd not thought of it that way and it changes the arithmetic.
    >>  ............................................
    pt  ...Uma coisa egoísta, %1$s. Não pensei assim e isso muda a conta.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned. Say that again in front of the mayor and see what it costs me.
    >>  ............................................
    pt  Merecido. Diga de novo na frente do prefeito e veja quanto me custa.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing, %1$s. I'd not thought of it that way and it changes the arithmetic.
    >>  ............................................
    pt  ...Uma coisa egoísta, %1$s. Não pensei assim e isso muda a conta.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned. Say that again in front of the mayor and see what it costs me.
    >>  ............................................
    pt  Merecido. Diga de novo na frente do prefeito e veja quanto me custa.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing. I've never allowed myself the phrase, let alone the year.
    >>  ............................................
    pt  ...Uma coisa egoísta. Nunca me permiti a frase, quanto mais o ano.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned. I'd like to believe that and I'd need somebody to keep saying it.
    >>  ............................................
    pt  Merecido. Eu gostaria de acreditar e precisaria de alguém repetindo.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing. I've not thought of it in those terms and it changes the arithmetic.
    >>  ............................................
    pt  ...Uma coisa egoísta. Não pensei nesses termos e isso muda a conta.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned. Ha. Say that again in front of the mayor and see what it costs me.
    >>  ............................................
    pt  Merecido. Ha. Diga de novo na frente do prefeito e veja quanto me custa.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing. I've not thought of it in those terms and it changes the arithmetic.
    >>  ............................................
    pt  ...Uma coisa egoísta. Não pensei nesses termos e isso muda a conta.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned. Ha. Say that again in front of the mayor and see what it costs me.
    >>  ............................................
    pt  Merecido. Ha. Diga de novo na frente do prefeito e veja quanto me custa.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing. That changes the arithmetic.
    >>  ............................................
    pt  ...Uma coisa egoísta. Isso muda a conta.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned. Say that in front of the mayor.
    >>  ............................................
    pt  Merecido. Diga isso na frente do prefeito.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing. Forty years at the fire and I've not taken one yet.
    >>  ............................................
    pt  ...Uma coisa egoísta. Quarenta anos na forja e ainda não tirei uma.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned. Say that in front of the mayor and we'll both learn something.
    >>  ............................................
    pt  Merecido. Diga isso na frente do prefeito e nós dois aprendemos algo.
    >>  ............................................
  odd.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing. That changes the arithmetic.
    >>  ............................................
    pt  ...Uma coisa egoísta. Isso muda a conta.
    >>  ............................................
  odd.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned. Say that in front of the mayor.
    >>  ............................................
    pt  Merecido. Diga isso na frente do prefeito.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing. Forty years at the fire and I've not taken one yet.
    >>  ............................................
    pt  ...Uma coisa egoísta. Quarenta anos na forja e ainda não tirei uma.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned. Say that in front of the mayor and we'll both learn something.
    >>  ............................................
    pt  Merecido. Diga isso na frente do prefeito e nós dois aprendemos algo.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing! I've never thought of it in those terms and it changes everything.
    >>  ............................................
    pt  ...Uma coisa egoísta! Nunca pensei nesses termos e isso muda tudo.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned — ha! Say that again in front of the mayor and see what it costs me.
    >>  ............................................
    pt  Merecido — ha! Diga de novo na frente do prefeito e veja quanto me custa.
    >>  ............................................
  playful.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing! I've never thought of it in those terms and it changes everything.
    >>  ............................................
    pt  ...Uma coisa egoísta! Nunca pensei nesses termos e isso muda tudo.
    >>  ............................................
  playful.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned — ha! Say that again in front of the mayor and see what it costs me.
    >>  ............................................
    pt  Merecido — ha! Diga de novo na frente do prefeito e veja quanto me custa.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing. Forty years at the fire and I've not taken one yet.
    >>  ............................................
    pt  ...Uma coisa egoísta. Quarenta anos na forja e ainda não tirei uma.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned. Say that in front of the mayor and we'll both learn something.
    >>  ............................................
    pt  Merecido. Diga isso na frente do prefeito e nós dois aprendemos algo.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing. I've never allowed myself the phrase, let alone the year.
    >>  ............................................
    pt  ...Uma coisa egoísta. Nunca me permiti a frase, quanto mais o ano.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned. I'd like to believe that and I'd need somebody to keep saying it.
    >>  ............................................
    pt  Merecido. Eu gostaria de acreditar e precisaria de alguém repetindo.
    >>  ............................................
  shy.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing. That changes the arithmetic.
    >>  ............................................
    pt  ...Uma coisa egoísta. Isso muda a conta.
    >>  ............................................
  shy.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned. Say that in front of the mayor.
    >>  ............................................
    pt  Merecido. Diga isso na frente do prefeito.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing! I've never thought of it in those terms and it changes everything.
    >>  ............................................
    pt  ...Uma coisa egoísta! Nunca pensei nesses termos e isso muda tudo.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned — ha! Say that again in front of the mayor and see what it costs me.
    >>  ............................................
    pt  Merecido — ha! Diga de novo na frente do prefeito e veja quanto me custa.
    >>  ............................................
  witty.dialogue.conversations.work.prof.weaponsmith.future.encourage/1
    en  ...One selfish thing! I've never thought of it in those terms and it changes everything.
    >>  ............................................
    pt  ...Uma coisa egoísta! Nunca pensei nesses termos e isso muda tudo.
    >>  ............................................
  witty.dialogue.conversations.work.prof.weaponsmith.future.encourage/2
    en  Earned — ha! Say that again in front of the mayor and see what it costs me.
    >>  ............................................
    pt  Merecido — ha! Diga de novo na frente do prefeito e veja quanto me custa.
    >>  ............................................
```

</details>


### Button `ask_nerve` — "Why does it take nerve?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.weaponsmith.future` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.weaponsmith.future.ask_nerve` — accepted phrasings: "why does it take nerve"
  - the message must contain one of: `nerve`, `afraid`, `start`
  - scored words: `nerve`(1.5), `afraid`(1.2), `start`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.future.respond.ask_nerve
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.future.respond.ask_nerve   [23 chars]
    en  Why does it take nerve?
    >>  ............................................
    pt  Por que exige coragem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.weaponsmith.future.ask_nerve`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.weaponsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you ever refused a sale?" | "Keep the edge."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.future.ask_nerve
WHO    VILLAGER — what the player reads after pressing "Why does it take nerve?"
       spoken on: conversations.topic.work.weaponsmith.future.respond, button `ask_nerve`
       leaves the player on: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.future.ask_nerve`: the villager explains. Subject `work.weaponsmith.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.weaponsmith.future.ask_nerve/1   [80 chars]
    en  Because if I make it and it's ordinary, then it was always going to be ordinary.
    >>  ............................................
    pt  Porque se eu fizer e for comum, então sempre ia ser comum.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.future.ask_nerve/2   [96 chars]
    en  Because it has been perfect in my head for thirty years, %1$s, and it will not survive the fire.
    >>  ............................................
    pt  Porque está perfeita na minha cabeça há trinta anos, %1$s, e não vai sobreviver ao fogo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the anvil."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.weaponsmith.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.future.respond.leave   [35 chars]
    en  I'll let you get back to the anvil.
    >>  ............................................
    pt  Vou deixar você voltar à bigorna.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the anvil."
       spoken on: conversations.topic.work.weaponsmith.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.left`: the villager accepts. Subject `work.weaponsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.weaponsmith.followup / leave; conversations.scene.work.weaponsmith.returned_blade.active.respond / leave; conversations.scene.work.weaponsmith.returned_blade.succeeded.respond / leave; conversations.scene.work.weaponsmith.trade_argument.active.respond / leave; conversations.scene.work.weaponsmith.trade_argument.succeeded.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond / leave; conversations.topic.work.weaponsmith.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.weaponsmith.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.weaponsmith.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.weaponsmith` — e.g. "Blades are honest tools — they only ever do what the hand asks. I try to like the hands I sell to."


```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.weaponsmith.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.weaponsmith.respond   [36 chars]
    en  That's the forge, and who I sell to.
    >>  ............................................
    pt  É a forja, e pra quem eu vendo.
    >>  ............................................
```


### Button `ask_hard` — "How do you choose the hands?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.weaponsmith.identity` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.weaponsmith.hard` — accepted phrasings: "how do you choose the hands"
  - the message must contain one of: `hands`, `choose`, `customers`
  - scored words: `hands`(1.5), `choose`(1.2), `customers`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.respond.ask_hard   [28 chars]
    en  How do you choose the hands?
    >>  ............................................
    pt  Como você escolhe as mãos?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.weaponsmith.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.weaponsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you ever refused a sale?" | "Keep the edge."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.hard
WHO    VILLAGER — what the player reads after pressing "How do you choose the hands?"
       spoken on: conversations.topic.work.weaponsmith.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.hard`: the villager explains. Subject `work.weaponsmith.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.weaponsmith.followup / ask_more
```

> Written out in full under **`conversations.scene.work.weaponsmith.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "The guards would be holding sticks without you."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.weaponsmith.identity` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.weaponsmith.value` — accepted phrasings: "the guards would be holding sticks without you"
  - the message must contain one of: `guards`, `sticks`, `armed`
  - scored words: `guards`(1.2), `sticks`(1.5), `armed`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.respond.value   [47 chars]
    en  The guards would be holding sticks without you.
    >>  ............................................
    pt  Os guardas estariam segurando gravetos sem você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.weaponsmith.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.weaponsmith.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.weaponsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you ever refused a sale?" | "Keep the edge."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.value
WHO    VILLAGER — what the player reads after pressing "The guards would be holding sticks without you."
       spoken on: conversations.topic.work.weaponsmith.respond, button `value`
       leaves the player on: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.value`: the villager accepts. Subject `work.weaponsmith.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.weaponsmith.value/1   [56 chars]
    en  They would, and they'd still complain about the balance.
    >>  ............................................
    pt  Estariam, e ainda assim reclamariam do balanço.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.value/2   [68 chars]
    en  Aye. Though I'd rather be remembered for the ploughs, if I'm honest.
    >>  ............................................
    pt  É. Embora eu preferisse ser lembrado pelos arados, se for honesto.
    >>  ............................................
```


### Button `challenge` — "You make things for hurting people."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.weaponsmith.identity` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.weaponsmith.challenge` — accepted phrasings: "you make things for hurting people"
  - the message must contain one of: `hurting`, `weapons`, `kill`
  - scored words: `hurting`(1.5), `weapons`(1.2), `kill`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.respond.challenge   [35 chars]
    en  You make things for hurting people.
    >>  ............................................
    pt  Você faz coisas pra machucar gente.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.weaponsmith.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.weaponsmith.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.weaponsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you ever refused a sale?" | "Keep the edge."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.challenge.landed
WHO    VILLAGER — what the player reads after pressing "You make things for hurting people."
       spoken on: conversations.topic.work.weaponsmith.respond, button `challenge`
       leaves the player on: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.challenge.landed`: the villager resists. Subject `work.weaponsmith.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.weaponsmith.challenge.landed/1   [78 chars]
    en  I do. I've never once pretended otherwise, and I sleep on that, not around it.
    >>  ............................................
    pt  Faço. Nunca fingi o contrário, e eu durmo com isso, não em volta disso.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.challenge.landed/2   [76 chars]
    en  That's the honest description, %1$s. I'd think less of you for softening it.
    >>  ............................................
    pt  É a descrição honesta, %1$s. Eu te acharia pior se você suavizasse.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.weaponsmith.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.weaponsmith.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.weaponsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you ever refused a sale?" | "Keep the edge."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.challenge.stung
WHO    VILLAGER — what the player reads after pressing "You make things for hurting people."
       spoken on: conversations.topic.work.weaponsmith.respond, button `challenge`
       leaves the player on: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.challenge.stung`: the villager resists. Subject `work.weaponsmith.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.weaponsmith.challenge.stung/1   [65 chars]
    en  ...And the axe that splits your firewood. Same forge, same hands.
    >>  ............................................
    pt  ...E o machado que racha sua lenha. Mesma forja, mesmas mãos.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.challenge.stung/2   [75 chars]
    en  Hurting people. Aye. Say that to the guard who came home last winter, %1$s.
    >>  ............................................
    pt  Machucar gente. É. Diga isso ao guarda que voltou vivo no inverno passado, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the anvil."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.weaponsmith.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.respond.leave   [35 chars]
    en  I'll let you get back to the anvil.
    >>  ............................................
    pt  Vou deixar você voltar à bigorna.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the anvil."
       spoken on: conversations.topic.work.weaponsmith.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.left`: the villager accepts. Subject `work.weaponsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.weaponsmith.followup / leave; conversations.scene.work.weaponsmith.returned_blade.active.respond / leave; conversations.scene.work.weaponsmith.returned_blade.succeeded.respond / leave; conversations.scene.work.weaponsmith.trade_argument.active.respond / leave; conversations.scene.work.weaponsmith.trade_argument.succeeded.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond / leave; conversations.topic.work.weaponsmith.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.weaponsmith.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.weaponsmith.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.weaponsmith.risk` — e.g. "Everything I sell can be used badly and I have no say in it after the coin changes hands."


```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.weaponsmith.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.weaponsmith.risk.respond   [29 chars]
    en  That's the shape of the risk.
    >>  ............................................
    pt  É esse o formato do risco.
    >>  ............................................
```


### Button `ask_blade` — "What happened with that one?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.weaponsmith.risk` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.weaponsmith.risk.ask_blade` — accepted phrasings: "what happened with that one"
  - the message must contain one of: `blade`, `happened`, `sold`
  - scored words: `blade`(1.5), `happened`(1.0), `sold`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.risk.respond.ask_blade
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.risk.respond.ask_blade   [28 chars]
    en  What happened with that one?
    >>  ............................................
    pt  O que aconteceu com aquela?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.weaponsmith.risk.ask_blade`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.weaponsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you ever refused a sale?" | "Keep the edge."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.risk.ask_blade
WHO    VILLAGER — what the player reads after pressing "What happened with that one?"
       spoken on: conversations.topic.work.weaponsmith.risk.respond, button `ask_blade`
       leaves the player on: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.risk.ask_blade`: the villager explains. Subject `work.weaponsmith.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.weaponsmith.risk.ask_blade/1   [82 chars]
    en  I don't know. That is the exact problem and it has been the problem for six years.
    >>  ............................................
    pt  Não sei. É exatamente esse o problema e é o problema há seis anos.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.risk.ask_blade/2   [64 chars]
    en  Nothing that reached me. Which is not the same as nothing, %1$s.
    >>  ............................................
    pt  Nada que tenha chegado a mim. O que não é o mesmo que nada, %1$s.
    >>  ............................................
```


### Button `sympathise` — "You can't be responsible for every hand."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.weaponsmith.risk` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.weaponsmith.risk.sympathise` — accepted phrasings: "you can't be responsible for every hand"
  - the message must contain one of: `responsible`, `hand`, `blame`
  - scored words: `responsible`(1.5), `hand`(1.0), `blame`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.risk.respond.sympathise   [40 chars]
    en  You can't be responsible for every hand.
    >>  ............................................
    pt  Você não pode ser responsável por toda mão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.weaponsmith.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.weaponsmith.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.weaponsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you ever refused a sale?" | "Keep the edge."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "You can't be responsible for every hand."
       spoken on: conversations.topic.work.weaponsmith.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.risk.sympathise`: the villager accepts. Subject `work.weaponsmith.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.weaponsmith.risk.sympathise/1   [76 chars]
    en  ...I know that. Knowing it and believing it are two separate pieces of work.
    >>  ............................................
    pt  ...Eu sei disso. Saber e acreditar são dois trabalhos separados.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.risk.sympathise/2   [69 chars]
    en  That's what the innkeeper says. He's right and it doesn't help, %1$s.
    >>  ............................................
    pt  É o que o estalajadeiro diz. Ele tem razão e não ajuda, %1$s.
    >>  ............................................
```


### Button `ask_refuse` — "Do you turn people away now?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.weaponsmith.risk` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.weaponsmith.risk.ask_refuse` — accepted phrasings: "do you turn people away now"
  - the message must contain one of: `refuse`, `away`, `decline`
  - scored words: `refuse`(1.5), `away`(1.0), `decline`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.risk.respond.ask_refuse
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.risk.respond.ask_refuse   [28 chars]
    en  Do you turn people away now?
    >>  ............................................
    pt  Você recusa gente agora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.weaponsmith.risk.ask_refuse`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.weaponsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you ever refused a sale?" | "Keep the edge."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.risk.ask_refuse
WHO    VILLAGER — what the player reads after pressing "Do you turn people away now?"
       spoken on: conversations.topic.work.weaponsmith.risk.respond, button `ask_refuse`
       leaves the player on: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.risk.ask_refuse`: the villager explains. Subject `work.weaponsmith.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.weaponsmith.risk.ask_refuse/1   [75 chars]
    en  More than I used to. It costs me custom and I have made my peace with that.
    >>  ............................................
    pt  Mais do que antes. Me custa clientela e eu fiz as pazes com isso.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.risk.ask_refuse/2   [85 chars]
    en  I ask what it's for and I watch how long they take to answer. That's my whole method.
    >>  ............................................
    pt  Pergunto pra que serve e observo quanto tempo levam pra responder. É todo o meu método.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the anvil."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.weaponsmith.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.risk.respond.leave   [35 chars]
    en  I'll let you get back to the anvil.
    >>  ............................................
    pt  Vou deixar você voltar à bigorna.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the anvil."
       spoken on: conversations.topic.work.weaponsmith.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.left`: the villager accepts. Subject `work.weaponsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.weaponsmith.followup / leave; conversations.scene.work.weaponsmith.returned_blade.active.respond / leave; conversations.scene.work.weaponsmith.returned_blade.succeeded.respond / leave; conversations.scene.work.weaponsmith.trade_argument.active.respond / leave; conversations.scene.work.weaponsmith.trade_argument.succeeded.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond / leave; conversations.topic.work.weaponsmith.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.weaponsmith.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.weaponsmith.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.weaponsmith.task` — e.g. "Re-tempering a blade that somebody left in a damp scabbard. It is salvageable. Barely."


```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.weaponsmith.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.weaponsmith.task.respond   [17 chars]
    en  That's the bench.
    >>  ............................................
    pt  É a bancada.
    >>  ............................................
```


### Button `ask_commission` — "What's wrong with the commission?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.weaponsmith.task` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.weaponsmith.task.ask_commission` — accepted phrasings: "what's wrong with the commission"
  - the message must contain one of: `commission`, `wrong`, `order`
  - scored words: `commission`(1.5), `wrong`(1.0), `order`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.task.respond.ask_commission
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.task.respond.ask_commission   [33 chars]
    en  What's wrong with the commission?
    >>  ............................................
    pt  O que tem de errado com a encomenda?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.weaponsmith.task.ask_commission`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.weaponsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you ever refused a sale?" | "Keep the edge."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.task.ask_commission
WHO    VILLAGER — what the player reads after pressing "What's wrong with the commission?"
       spoken on: conversations.topic.work.weaponsmith.task.respond, button `ask_commission`
       leaves the player on: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.task.ask_commission`: the villager explains. Subject `work.weaponsmith.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.weaponsmith.task.ask_commission/1   [85 chars]
    en  Nothing, on paper. The man who ordered it would not look at me while he described it.
    >>  ............................................
    pt  Nada, no papel. O homem que encomendou não me olhava enquanto descrevia.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.task.ask_commission/2   [86 chars]
    en  The specification. Nobody needs a blade that shape for anything I would call a reason.
    >>  ............................................
    pt  A especificação. Ninguém precisa de uma lâmina desse formato por nada que eu chame de motivo.
    >>  ............................................
```


### Button `offer_hands` — "I can pump the bellows."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.weaponsmith.task` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.weaponsmith.task.offer_hands` — accepted phrasings: "i can pump the bellows"
  - the message must contain one of: `bellows`, `pump`
  - scored words: `bellows`(1.5), `pump`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.task.respond.offer_hands   [23 chars]
    en  I can pump the bellows.
    >>  ............................................
    pt  Eu posso bombear o fole.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.weaponsmith.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.weaponsmith.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.weaponsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you ever refused a sale?" | "Keep the edge."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I can pump the bellows."
       spoken on: conversations.topic.work.weaponsmith.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.task.offer_hands`: the villager accepts. Subject `work.weaponsmith.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.weaponsmith.task.offer_hands/1   [70 chars]
    en  ...Steady and slow. Everyone pumps too fast and cooks the edge off it.
    >>  ............................................
    pt  ...Firme e devagar. Todo mundo bombeia rápido demais e queima o fio.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.task.offer_hands/2   [72 chars]
    en  Then keep it even. The temper is in the rhythm more than the heat, %1$s.
    >>  ............................................
    pt  Então mantenha o ritmo. O têmpera está mais no ritmo que no calor, %1$s.
    >>  ............................................
```


### Button `ask_damp` — "How bad is a damp scabbard?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.weaponsmith.task` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.weaponsmith.task.ask_damp` — accepted phrasings: "how bad is a damp scabbard"
  - the message must contain one of: `damp`, `scabbard`, `rust`
  - scored words: `damp`(1.5), `scabbard`(1.5), `rust`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.task.respond.ask_damp
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.task.respond.ask_damp   [27 chars]
    en  How bad is a damp scabbard?
    >>  ............................................
    pt  Quão ruim é uma bainha úmida?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.weaponsmith.task.ask_damp`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.weaponsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you ever refused a sale?" | "Keep the edge."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.task.ask_damp
WHO    VILLAGER — what the player reads after pressing "How bad is a damp scabbard?"
       spoken on: conversations.topic.work.weaponsmith.task.respond, button `ask_damp`
       leaves the player on: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.task.ask_damp`: the villager explains. Subject `work.weaponsmith.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.weaponsmith.task.ask_damp/1   [93 chars]
    en  It eats a blade from the inside where you can't see it. Six months and it's a decorative bar.
    >>  ............................................
    pt  Come a lâmina por dentro onde você não vê. Seis meses e vira uma barra decorativa.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.task.ask_damp/2   [78 chars]
    en  Bad enough that I say it twice at the sale and they forget it twice by winter.
    >>  ............................................
    pt  Ruim o bastante pra eu avisar duas vezes na venda e eles esquecerem duas vezes até o inverno.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the anvil."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.weaponsmith.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.task.respond.leave   [35 chars]
    en  I'll let you get back to the anvil.
    >>  ............................................
    pt  Vou deixar você voltar à bigorna.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the anvil."
       spoken on: conversations.topic.work.weaponsmith.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.left`: the villager accepts. Subject `work.weaponsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.weaponsmith.followup / leave; conversations.scene.work.weaponsmith.returned_blade.active.respond / leave; conversations.scene.work.weaponsmith.returned_blade.succeeded.respond / leave; conversations.scene.work.weaponsmith.trade_argument.active.respond / leave; conversations.scene.work.weaponsmith.trade_argument.succeeded.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond / leave; conversations.topic.work.weaponsmith.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.weaponsmith.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.weaponsmith.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.weaponsmith.village` — e.g. "The watch is armed because of this forge. So is anyone who walked in with coin, and that's the trouble."


```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.weaponsmith.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.weaponsmith.village.respond   [24 chars]
    en  That's what it comes to.
    >>  ............................................
    pt  É no que dá.
    >>  ............................................
```


### Button `ask_raid` — "Would you want it mentioned?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.weaponsmith.village` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.weaponsmith.village.ask_raid` — accepted phrasings: "would you want it mentioned"
  - the message must contain one of: `mentioned`, `raid`, `acknowledge`
  - scored words: `mentioned`(1.5), `raid`(1.2), `acknowledge`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.village.respond.ask_raid
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.village.respond.ask_raid   [28 chars]
    en  Would you want it mentioned?
    >>  ............................................
    pt  Você gostaria que mencionassem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.weaponsmith.village.ask_raid`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.weaponsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you ever refused a sale?" | "Keep the edge."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.village.ask_raid
WHO    VILLAGER — what the player reads after pressing "Would you want it mentioned?"
       spoken on: conversations.topic.work.weaponsmith.village.respond, button `ask_raid`
       leaves the player on: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.village.ask_raid`: the villager explains. Subject `work.weaponsmith.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.weaponsmith.village.ask_raid/1   [60 chars]
    en  ...Once. By him. Not in the square and not with drink in it.
    >>  ............................................
    pt  ...Uma vez. Por ele. Não na praça e não com bebida.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.village.ask_raid/2   [76 chars]
    en  No. And I would think about it for a year afterwards, %1$s, which is why no.
    >>  ............................................
    pt  Não. E eu pensaria nisso por um ano depois, %1$s, e é por isso que não.
    >>  ............................................
```


### Button `say_thanks` — "Then I'll mention it. That night mattered."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.weaponsmith.village` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.weaponsmith.village.say_thanks` — accepted phrasings: "then i'll mention it. that night mattered"
  - the message must contain one of: `mention`, `mattered`, `night`
  - scored words: `mention`(1.2), `mattered`(1.5), `night`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.village.respond.say_thanks   [42 chars]
    en  Then I'll mention it. That night mattered.
    >>  ............................................
    pt  Então eu menciono. Aquela noite importou.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.weaponsmith.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.weaponsmith.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.weaponsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you ever refused a sale?" | "Keep the edge."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Then I'll mention it. That night mattered."
       spoken on: conversations.topic.work.weaponsmith.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.village.say_thanks`: the villager accepts. Subject `work.weaponsmith.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.weaponsmith.village.say_thanks/1   [70 chars]
    en  ...Right. Well. That's said now and I'll have to do something with it.
    >>  ............................................
    pt  ...Certo. Bom. Foi dito e agora eu tenho que fazer algo com isso.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.village.say_thanks/2   [60 chars]
    en  It did matter. Thank you for saying the word out loud, %1$s.
    >>  ............................................
    pt  Importou mesmo. Obrigado por dizer a palavra em voz alta, %1$s.
    >>  ............................................
```


### Button `ask_coin` — "Could you sell only to the watch?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.weaponsmith.village` · offered only once the villager has actually said `work:weaponsmith`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.weaponsmith.village.ask_coin` — accepted phrasings: "could you sell only to the watch"
  - the message must contain one of: `sell`, `watch`, `only`
  - scored words: `sell`(1.5), `watch`(1.0), `only`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.village.respond.ask_coin
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.village.respond.ask_coin   [33 chars]
    en  Could you sell only to the watch?
    >>  ............................................
    pt  Você poderia vender só pra guarda?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.weaponsmith.village.ask_coin`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.weaponsmith.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you ever refused a sale?" | "Keep the edge."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.village.ask_coin
WHO    VILLAGER — what the player reads after pressing "Could you sell only to the watch?"
       spoken on: conversations.topic.work.weaponsmith.village.respond, button `ask_coin`
       leaves the player on: conversations.topic.work.weaponsmith.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.village.ask_coin`: the villager explains. Subject `work.weaponsmith.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:weaponsmith` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.weaponsmith.village.ask_coin/1   [90 chars]
    en  Then I'd starve, and the next smith along would sell to everyone with no questions at all.
    >>  ............................................
    pt  Aí eu passaria fome, e o próximo ferreiro venderia pra todos sem pergunta nenhuma.
    >>  ............................................
  dialogue.conversations.work.prof.weaponsmith.village.ask_coin/2   [80 chars]
    en  I've done the arithmetic four times. It comes out the same way every time, %1$s.
    >>  ............................................
    pt  Já fiz essa conta quatro vezes. Dá o mesmo resultado toda vez, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the anvil."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.weaponsmith.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.weaponsmith.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.weaponsmith.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.weaponsmith.village.respond.leave   [35 chars]
    en  I'll let you get back to the anvil.
    >>  ............................................
    pt  Vou deixar você voltar à bigorna.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.weaponsmith.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the anvil."
       spoken on: conversations.topic.work.weaponsmith.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.weaponsmith.left`: the villager accepts. Subject `work.weaponsmith.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.weaponsmith.followup / leave; conversations.scene.work.weaponsmith.returned_blade.active.respond / leave; conversations.scene.work.weaponsmith.returned_blade.succeeded.respond / leave; conversations.scene.work.weaponsmith.trade_argument.active.respond / leave; conversations.scene.work.weaponsmith.trade_argument.succeeded.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.blocked.respond / leave; conversations.scene.work.weaponsmith.uneasy_commission.succeeded.respond / leave; conversations.topic.work.weaponsmith.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.weaponsmith.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

