# Work talk with a fletcher

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.fletcher.bad_supplier.active.respond`](#conversations-scene-work-fletcher-bad-supplier-active-respond)
- [`conversations.scene.work.fletcher.bad_supplier.succeeded.respond`](#conversations-scene-work-fletcher-bad-supplier-succeeded-respond)
- [`conversations.scene.work.fletcher.crooked_batch.blocked.respond`](#conversations-scene-work-fletcher-crooked-batch-blocked-respond)
- [`conversations.scene.work.fletcher.crooked_batch.succeeded.respond`](#conversations-scene-work-fletcher-crooked-batch-succeeded-respond)
- [`conversations.scene.work.fletcher.followup`](#conversations-scene-work-fletcher-followup)
- [`conversations.scene.work.fletcher.who_buys.active.respond`](#conversations-scene-work-fletcher-who-buys-active-respond)
- [`conversations.scene.work.fletcher.who_buys.succeeded.respond`](#conversations-scene-work-fletcher-who-buys-succeeded-respond)
- [`conversations.topic.work.fletcher.craft.respond`](#conversations-topic-work-fletcher-craft-respond)
- [`conversations.topic.work.fletcher.followup`](#conversations-topic-work-fletcher-followup)
- [`conversations.topic.work.fletcher.future.respond`](#conversations-topic-work-fletcher-future-respond)
- [`conversations.topic.work.fletcher.respond`](#conversations-topic-work-fletcher-respond)
- [`conversations.topic.work.fletcher.risk.respond`](#conversations-topic-work-fletcher-risk-respond)
- [`conversations.topic.work.fletcher.task.respond`](#conversations-topic-work-fletcher-task-respond)
- [`conversations.topic.work.fletcher.village.respond`](#conversations-topic-work-fletcher-village-respond)

---

## `conversations.scene.work.fletcher.bad_supplier.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.fletcher.bad_supplier.active` — e.g. "Third delivery running with %2$s, and I have paid full price for all three."


```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.bad_supplier.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.fletcher.bad_supplier.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.fletcher.bad_supplier.active.respond   [22 chars]
    en  The wood you get sent.
    >>  ............................................
    pt  A madeira que te mandam.
    >>  ............................................
```


### Button `advise_refusing` — "Send the next load back."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.fletcher.bad_supplier.active` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fletcher.bad_supplier.active.advise_refusing` — accepted phrasings: "send the next load back"; "send the next load back"; "refuse the next delivery"
  - the message must contain one of: `load`, `refuse`, `delivery`
  - scored words: `load`(1.8), `refuse`(1.8), `delivery`(1.8), `next`(0.8), `back`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.bad_supplier.active.respond.advise_refusing
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fletcher.bad_supplier.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fletcher.bad_supplier.active.respond.advise_refusing   [24 chars]
    en  Send the next load back.
    >>  ............................................
    pt  Devolva a próxima carga.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, trust +1  _(recorded under topic `work.fletcher.shafts`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.fletcher.bad_supplier"}
- Then opens: `conversations.scene.work.fletcher.followup`
- …where the player's next choices will be: "What's the hardest part of a straight shaft?" | "I'll leave you to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.bad_supplier.active.accepted
WHO    VILLAGER — what the player reads after pressing "Send the next load back."
       spoken on: conversations.scene.work.fletcher.bad_supplier.active.respond, button `advise_refusing`
       leaves the player on: conversations.scene.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.bad_supplier.active.accepted`: the villager accepts. Subject `work.fletcher.shafts`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fletcher.bad_supplier.active.accepted/1   [104 chars]
    en  Unopened, on the same cart, with a note. He will be furious and he will send better wood, in that order.
    >>  ............................................
    pt  Lacrada, na mesma carroça, com um bilhete. Ele vai ficar furioso e vai mandar madeira melhor, nessa ordem.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.bad_supplier.active.accepted/2   [129 chars]
    en  Yes. The risk is that he stops coming, and then I have no supplier at all. I have decided I would rather have none than this one.
    >>  ............................................
    pt  Sim. O risco é ele parar de vir, e aí eu fico sem fornecedor nenhum. Decidi que prefiro nenhum a este.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.bad_supplier.active.accepted/3   [118 chars]
    en  I have been rehearsing that for two months. Saying it to you has taken most of the difficulty out of it, which is odd.
    >>  ............................................
    pt  Faz dois meses que eu ensaio isso. Dizer a você tirou quase toda a dificuldade, o que é estranho.
    >>  ............................................
```


### Button `ask_why_he_does_it` — "Why does he send you the poor stock?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fletcher.bad_supplier.active` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fletcher.bad_supplier.active.ask_why_he_does_it` — accepted phrasings: "why does he send you the poor stock"; "why does he send you the poor stock"; "what makes him send you the worst wood"
  - the message must contain one of: `stock`, `wood`, `send`
  - scored words: `stock`(1.8), `wood`(1.8), `send`(1.8), `why`(0.8), `does`(0.8), `poor`(0.8), `makes`(0.8), `him`(0.8), `worst`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.bad_supplier.active.respond.ask_why_he_does_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fletcher.bad_supplier.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fletcher.bad_supplier.active.respond.ask_why_he_does_it   [36 chars]
    en  Why does he send you the poor stock?
    >>  ............................................
    pt  Por que ele te manda o material ruim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fletcher.shafts`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.fletcher.bad_supplier"}
- Then opens: `conversations.scene.work.fletcher.followup`
- …where the player's next choices will be: "What's the hardest part of a straight shaft?" | "I'll leave you to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.bad_supplier.active.explained
WHO    VILLAGER — what the player reads after pressing "Why does he send you the poor stock?"
       spoken on: conversations.scene.work.fletcher.bad_supplier.active.respond, button `ask_why_he_does_it`
       leaves the player on: conversations.scene.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.bad_supplier.active.explained`: the villager explains. Subject `work.fletcher.shafts`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fletcher.bad_supplier.active.explained/1   [115 chars]
    en  Because I have never once complained, and eleven years of not complaining reads as eleven years of being satisfied.
    >>  ............................................
    pt  Porque eu nunca reclamei uma vez, e onze anos sem reclamar são lidos como onze anos de satisfação.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.bad_supplier.active.explained/2   [108 chars]
    en  The good wood goes to the town, where they argue about it. I get the rest because I am quiet and I am close.
    >>  ............................................
    pt  A madeira boa vai para a cidade, onde discutem. Eu fico com o resto porque sou calada e fico perto.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.bad_supplier.active.explained/3   [115 chars]
    en  I do not think he decides. I think a man loading a cart puts the awkward pieces where he expects the least trouble.
    >>  ............................................
    pt  Não acho que ele decida. Acho que um homem carregando uma carroça põe as peças ruins onde espera menos encrenca.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the bench."

*stance family `exit` · tone `plain` · answers the beat(s) `work.fletcher.bad_supplier.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.bad_supplier.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fletcher.bad_supplier.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fletcher.bad_supplier.active.respond.leave   [35 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the bench."
       spoken on: conversations.scene.work.fletcher.bad_supplier.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.left`: the villager accepts. Subject `work.fletcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fletcher.bad_supplier.succeeded.respond / leave; conversations.scene.work.fletcher.crooked_batch.blocked.respond / leave; conversations.scene.work.fletcher.crooked_batch.succeeded.respond / leave; conversations.scene.work.fletcher.followup / leave; conversations.scene.work.fletcher.who_buys.active.respond / leave; conversations.scene.work.fletcher.who_buys.succeeded.respond / leave; conversations.topic.work.fletcher.craft.respond / leave; conversations.topic.work.fletcher.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.fletcher.leave/1   [34 chars]
    en  The batch is grateful. Off you go.
    >>  ............................................
    pt  O lote agradece. Pode ir.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.leave/2   [29 chars]
    en  Aye. Mind the shavings, %1$s.
    >>  ............................................
    pt  É. Cuidado com as aparas, %1$s.
    >>  ............................................
```

---


## `conversations.scene.work.fletcher.bad_supplier.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.fletcher.bad_supplier.succeeded` — e.g. "I sent it back. He came in person, which he has never done, and the next load was the best I have had in years."


```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.bad_supplier.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.fletcher.bad_supplier.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.fletcher.bad_supplier.succeeded.respond   [14 chars]
    en  That supplier.
    >>  ............................................
    pt  Aquele fornecedor.
    >>  ............................................
```


### Button `note_the_nerve` — "It took nerve to send it back."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.fletcher.bad_supplier.succeeded` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fletcher.bad_supplier.succeeded.note_the_nerve` — accepted phrasings: "it took nerve to send it back"; "it took nerve to send it back"; "sending it back was brave"
  - the message must contain one of: `nerve`, `brave`
  - scored words: `nerve`(1.8), `brave`(1.8), `took`(0.8), `send`(0.8), `back`(0.8), `sending`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.bad_supplier.succeeded.respond.note_the_nerve
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fletcher.bad_supplier.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fletcher.bad_supplier.succeeded.respond.note_the_nerve   [30 chars]
    en  It took nerve to send it back.
    >>  ............................................
    pt  Exigiu coragem devolver.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.fletcher.shafts`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.fletcher.bad_supplier"}
- Then opens: `conversations.scene.work.fletcher.followup`
- …where the player's next choices will be: "What's the hardest part of a straight shaft?" | "I'll leave you to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.bad_supplier.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "It took nerve to send it back."
       spoken on: conversations.scene.work.fletcher.bad_supplier.succeeded.respond, button `note_the_nerve`
       leaves the player on: conversations.scene.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.bad_supplier.succeeded.acknowledged`: the villager accepts. Subject `work.fletcher.shafts`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fletcher.bad_supplier.succeeded.acknowledged/1   [110 chars]
    en  It took a witness. I told somebody I was going to do it, and then I had to, and that is the only trick I know.
    >>  ............................................
    pt  Exigiu uma testemunha. Contei a alguém que eu ia fazer, e aí tive que fazer, e é o único truque que eu conheço.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.bad_supplier.succeeded.acknowledged/2   [106 chars]
    en  Thank you. I would like to claim principle. It was mostly having finally run out of patience on a Tuesday.
    >>  ............................................
    pt  Obrigada. Eu gostaria de alegar princípio. Foi mais ter finalmente esgotado a paciência numa terça.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.bad_supplier.succeeded.acknowledged/3   [126 chars]
    en  The nerve was writing the note. Handing it to the carter was nothing, and the carter agreed with me, which I had not expected.
    >>  ............................................
    pt  A coragem foi escrever o bilhete. Entregar ao carroceiro foi nada, e o carroceiro concordou comigo, o que eu não esperava.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the bench."

*stance family `exit` · tone `plain` · answers the beat(s) `work.fletcher.bad_supplier.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.bad_supplier.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fletcher.bad_supplier.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fletcher.bad_supplier.succeeded.respond.leave   [35 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the bench."
       spoken on: conversations.scene.work.fletcher.bad_supplier.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.left`: the villager accepts. Subject `work.fletcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fletcher.bad_supplier.active.respond / leave; conversations.scene.work.fletcher.crooked_batch.blocked.respond / leave; conversations.scene.work.fletcher.crooked_batch.succeeded.respond / leave; conversations.scene.work.fletcher.followup / leave; conversations.scene.work.fletcher.who_buys.active.respond / leave; conversations.scene.work.fletcher.who_buys.succeeded.respond / leave; conversations.topic.work.fletcher.craft.respond / leave; conversations.topic.work.fletcher.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.fletcher.bad_supplier.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.fletcher.crooked_batch.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.fletcher.crooked_batch.blocked` — e.g. "%2$s has %3$s and I found it at the end, which means I found it forty times over."


```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.fletcher.crooked_batch.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.respond   [10 chars]
    en  The batch.
    >>  ............................................
    pt  O lote.
    >>  ............................................
```


### Button `ask_if_usable` — "Are they usable at all?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fletcher.crooked_batch.blocked` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fletcher.crooked_batch.blocked.ask_if_usable` — accepted phrasings: "are they usable at all"; "are they usable at all"; "can any of them be salvaged"
  - the message must contain one of: `usable`, `salvaged`
  - scored words: `usable`(1.8), `salvaged`(1.8), `all`(0.8), `any`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.respond.ask_if_usable
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fletcher.crooked_batch.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.respond.ask_if_usable   [23 chars]
    en  Are they usable at all?
    >>  ............................................
    pt  Dá para usar de algum jeito?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fletcher.batch_work`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.fletcher.crooked_batch"}
- Then opens: `conversations.scene.work.fletcher.followup`
- …where the player's next choices will be: "What's the hardest part of a straight shaft?" | "I'll leave you to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.explained
WHO    VILLAGER — what the player reads after pressing "Are they usable at all?"
       spoken on: conversations.scene.work.fletcher.crooked_batch.blocked.respond, button `ask_if_usable`
       leaves the player on: conversations.scene.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.crooked_batch.blocked.explained`: the villager explains. Subject `work.fletcher.batch_work`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.explained/1   [85 chars]
    en  For practice at ten paces, yes. For the watch, no, and the watch is who %2$s was for.
    >>  ............................................
    pt  Para treino a dez passos, sim. Para a ronda, não, e a ronda era para quem %2$s ia.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.explained/2   [113 chars]
    en  About a third. The trouble is that they look identical, so a third good and two thirds bad is worse than all bad.
    >>  ............................................
    pt  Um terço, mais ou menos. O problema é que parecem idênticas, então um terço bom e dois terços ruins é pior do que tudo ruim.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.explained/3   [118 chars]
    en  I could sell them to somebody who will never notice. I have sold that way once, years ago, and I still think about it.
    >>  ............................................
    pt  Eu poderia vender para alguém que nunca vai perceber. Vendi assim uma vez, anos atrás, e ainda penso nisso.
    >>  ............................................
```


### Button `offer_shafts` — "I'll bring you sticks for new shafts."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.fletcher.crooked_batch.blocked` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fletcher.crooked_batch.blocked.offer_shafts` — accepted phrasings: "ill bring you sticks for new shafts"; "i can bring you sticks for shafts"; "let me fetch sticks for that"
  - the message must contain one of: `sticks`, `shafts`
  - scored words: `sticks`(1.8), `shafts`(1.8), `ill`(0.8), `bring`(0.8), `new`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.respond.offer_shafts
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fletcher.crooked_batch.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.respond.offer_shafts   [37 chars]
    en  I'll bring you sticks for new shafts.
    >>  ............................................
    pt  Vou trazer gravetos para novas hastes.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.fletcher.batch.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.fletcher.batch_work`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.crooked_batch", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.fletcher.crooked_batch", "obligation": "commitment:work.fletcher.bring_sticks"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.fletcher.bring_sticks"}
- Then opens: `conversations.scene.work.fletcher.followup`
- …where the player's next choices will be: "What's the hardest part of a straight shaft?" | "I'll leave you to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you sticks for new shafts."
       spoken on: conversations.scene.work.fletcher.crooked_batch.blocked.respond, button `offer_shafts`
       leaves the player on: conversations.scene.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.crooked_batch.blocked.accepted`: the villager accepts. Subject `work.fletcher.batch_work`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.accepted/1   [113 chars]
    en  Then I start again tonight and it is finished by Thursday, and nobody has to know there was ever a first attempt.
    >>  ............................................
    pt  Então recomeço hoje à noite e fica pronto até quinta, e ninguém precisa saber que houve uma primeira tentativa.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.accepted/2   [107 chars]
    en  Bring them straight, that is all I ask. I would rather have twenty straight than a hundred that are nearly.
    >>  ............................................
    pt  Traga retos, é só o que peço. Prefiro vinte retos a cem que estão quase.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.accepted/3   [103 chars]
    en  Yes. And I will burn the bad ones in front of you, because otherwise I will find a reason to keep them.
    >>  ............................................
    pt  Sim. E vou queimar os ruins na sua frente, porque senão eu vou achar um motivo para guardar.
    >>  ............................................
```


### Button `advise_burning_them` — "Destroy the bad ones."

*stance family `candor` · tone `blunt` · outcome `appreciated` · answers the beat(s) `work.fletcher.crooked_batch.blocked` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fletcher.crooked_batch.blocked.advise_burning_them` — accepted phrasings: "destroy the bad ones"; "destroy the bad ones"; "burn the faulty batch"
  - the message must contain one of: `destroy`, `burn`, `faulty`
  - scored words: `destroy`(1.8), `burn`(1.8), `faulty`(1.8), `bad`(0.8), `ones`(0.8), `batch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.respond.advise_burning_them
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fletcher.crooked_batch.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.respond.advise_burning_them   [21 chars]
    en  Destroy the bad ones.
    >>  ............................................
    pt  Destrua os ruins.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4  _(recorded under topic `work.fletcher.batch_work`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.fletcher.crooked_batch"}
- Then opens: `conversations.scene.work.fletcher.followup`
- …where the player's next choices will be: "What's the hardest part of a straight shaft?" | "I'll leave you to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.agreed_to_burn
WHO    VILLAGER — what the player reads after pressing "Destroy the bad ones."
       spoken on: conversations.scene.work.fletcher.crooked_batch.blocked.respond, button `advise_burning_them`
       leaves the player on: conversations.scene.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.crooked_batch.blocked.agreed_to_burn`: the villager accepts. Subject `work.fletcher.batch_work`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.agreed_to_burn/1   [126 chars]
    en  Yes. A bad arrow in a good quiver is worse than no arrow, because the archer trusts it right up until the moment they need it.
    >>  ............................................
    pt  Sim. Uma flecha ruim numa aljava boa é pior do que nenhuma, porque o arqueiro confia nela até o instante em que precisa.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.agreed_to_burn/2   [107 chars]
    en  That is the correct answer and it is four days of my work on the fire. I will do it before I lose my nerve.
    >>  ............................................
    pt  É a resposta certa e são quatro dias do meu trabalho no fogo. Vou fazer antes de perder a coragem.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.agreed_to_burn/3   [122 chars]
    en  I keep them in a separate box and tell myself I will sort them properly one day. The box is four years old. You are right.
    >>  ............................................
    pt  Guardo numa caixa separada e digo a mim mesma que um dia vou separar direito. A caixa tem quatro anos. Você tem razão.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the bench."

*stance family `exit` · tone `plain` · answers the beat(s) `work.fletcher.crooked_batch.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fletcher.crooked_batch.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fletcher.crooked_batch.blocked.respond.leave   [35 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the bench."
       spoken on: conversations.scene.work.fletcher.crooked_batch.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.left`: the villager accepts. Subject `work.fletcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fletcher.bad_supplier.active.respond / leave; conversations.scene.work.fletcher.bad_supplier.succeeded.respond / leave; conversations.scene.work.fletcher.crooked_batch.succeeded.respond / leave; conversations.scene.work.fletcher.followup / leave; conversations.scene.work.fletcher.who_buys.active.respond / leave; conversations.scene.work.fletcher.who_buys.succeeded.respond / leave; conversations.topic.work.fletcher.craft.respond / leave; conversations.topic.work.fletcher.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.fletcher.bad_supplier.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.fletcher.crooked_batch.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.fletcher.crooked_batch.succeeded` — e.g. "%2$s went out true. I spun every one on the bench before it left, which took an hour and was worth it."


```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.crooked_batch.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.fletcher.crooked_batch.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.fletcher.crooked_batch.succeeded.respond   [18 chars]
    en  The batch, remade.
    >>  ............................................
    pt  O lote, refeito.
    >>  ............................................
```


### Button `ask_about_spinning` — "What does spinning one tell you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fletcher.crooked_batch.succeeded` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fletcher.crooked_batch.succeeded.ask_about_spinning` — accepted phrasings: "what does spinning one tell you"; "what does spinning one tell you"; "why spin each arrow on the bench"
  - the message must contain one of: `spinning`, `spin`
  - scored words: `spinning`(1.8), `spin`(1.8), `does`(0.8), `one`(0.8), `tell`(0.8), `why`(0.8), `each`(0.8), `arrow`(0.8), `bench`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.crooked_batch.succeeded.respond.ask_about_spinning
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fletcher.crooked_batch.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fletcher.crooked_batch.succeeded.respond.ask_about_spinning   [32 chars]
    en  What does spinning one tell you?
    >>  ............................................
    pt  O que girar uma te diz?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.fletcher.batch_work`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.fletcher.crooked_batch"}
- Then opens: `conversations.scene.work.fletcher.followup`
- …where the player's next choices will be: "What's the hardest part of a straight shaft?" | "I'll leave you to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.crooked_batch.succeeded.explained
WHO    VILLAGER — what the player reads after pressing "What does spinning one tell you?"
       spoken on: conversations.scene.work.fletcher.crooked_batch.succeeded.respond, button `ask_about_spinning`
       leaves the player on: conversations.scene.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.crooked_batch.succeeded.explained`: the villager explains. Subject `work.fletcher.batch_work`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fletcher.crooked_batch.succeeded.explained/1   [116 chars]
    en  Everything. A straight shaft spins quiet and a bent one wobbles, and your eye catches it long before your hand does.
    >>  ............................................
    pt  Tudo. Uma haste reta gira em silêncio e uma torta bamboleia, e o olho pega isso muito antes da mão.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.crooked_batch.succeeded.explained/2   [113 chars]
    en  It is the only honest test I have. You cannot see straightness by looking down a shaft; you can only see it move.
    >>  ............................................
    pt  É o único teste honesto que eu tenho. Não dá para ver retidão olhando pelo eixo; só dá para ver ela se mover.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.crooked_batch.succeeded.explained/3   [122 chars]
    en  Ten thousand arrows and I still do it one at a time. The moment I start trusting the batch is the moment I ship a bad one.
    >>  ............................................
    pt  Dez mil flechas e ainda faço uma por vez. No instante em que eu confiar no lote é o instante em que eu entrego uma ruim.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the bench."

*stance family `exit` · tone `plain` · answers the beat(s) `work.fletcher.crooked_batch.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.crooked_batch.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fletcher.crooked_batch.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fletcher.crooked_batch.succeeded.respond.leave   [35 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the bench."
       spoken on: conversations.scene.work.fletcher.crooked_batch.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.left`: the villager accepts. Subject `work.fletcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fletcher.bad_supplier.active.respond / leave; conversations.scene.work.fletcher.bad_supplier.succeeded.respond / leave; conversations.scene.work.fletcher.crooked_batch.blocked.respond / leave; conversations.scene.work.fletcher.followup / leave; conversations.scene.work.fletcher.who_buys.active.respond / leave; conversations.scene.work.fletcher.who_buys.succeeded.respond / leave; conversations.topic.work.fletcher.craft.respond / leave; conversations.topic.work.fletcher.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.fletcher.bad_supplier.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.fletcher.followup`

**Reached from 10 route(s):** `conversations.scene.work.fletcher.bad_supplier.active.respond` / `advise_refusing`; `conversations.scene.work.fletcher.bad_supplier.active.respond` / `ask_why_he_does_it`; `conversations.scene.work.fletcher.bad_supplier.succeeded.respond` / `note_the_nerve`; `conversations.scene.work.fletcher.crooked_batch.blocked.respond` / `ask_if_usable`; `conversations.scene.work.fletcher.crooked_batch.blocked.respond` / `offer_shafts`; `conversations.scene.work.fletcher.crooked_batch.blocked.respond` / `advise_burning_them`; `conversations.scene.work.fletcher.crooked_batch.succeeded.respond` / `ask_about_spinning`; `conversations.scene.work.fletcher.who_buys.active.respond` / `ask_whether_she_asks`; `conversations.scene.work.fletcher.who_buys.active.respond` / `back_her_rule`; `conversations.scene.work.fletcher.who_buys.succeeded.respond` / `note_the_lesson`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.fletcher.bad_supplier.active.accepted` — e.g. "Unopened, on the same cart, with a note. He will be furious and he will send better wood, in that order."
- `conversations.scene.work.fletcher.bad_supplier.active.explained` — e.g. "Because I have never once complained, and eleven years of not complaining reads as eleven years of being satisfied."
- `conversations.scene.work.fletcher.bad_supplier.succeeded.acknowledged` — e.g. "It took a witness. I told somebody I was going to do it, and then I had to, and that is the only trick I know."
- `conversations.scene.work.fletcher.crooked_batch.blocked.accepted` — e.g. "Then I start again tonight and it is finished by Thursday, and nobody has to know there was ever a first attempt."
- `conversations.scene.work.fletcher.crooked_batch.blocked.agreed_to_burn` — e.g. "Yes. A bad arrow in a good quiver is worse than no arrow, because the archer trusts it right up until the moment they need it."
- `conversations.scene.work.fletcher.crooked_batch.blocked.explained` — e.g. "For practice at ten paces, yes. For the watch, no, and the watch is who %2$s was for."
- `conversations.scene.work.fletcher.crooked_batch.succeeded.explained` — e.g. "Everything. A straight shaft spins quiet and a bent one wobbles, and your eye catches it long before your hand does."
- `conversations.scene.work.fletcher.who_buys.active.explained` — e.g. "Always. Not to judge — to make them say it out loud. Half the trouble in the world is done by people who never had to name it."
- `conversations.scene.work.fletcher.who_buys.active.steadied` — e.g. "It is the only part I control. What happens after the sale is not mine, and pretending otherwise would be a comfortable kind of vanity."
- `conversations.scene.work.fletcher.who_buys.succeeded.acknowledged` — e.g. "It is not, and I know it, and I will do it again the next time somebody is short with me at the counter."


```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.fletcher.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.fletcher.followup   [5 chars]
    en  More?
    >>  ............................................
    pt  Mais alguma?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of a straight shaft?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.fletcher.*` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fletcher.followup.ask_more` — accepted phrasings: "whats the hardest part of a straight shaft"; "what is the hardest part of a straight shaft"; "hardest thing about getting a shaft straight"
  - the message must contain one of: `hardest`, `shaft`
  - scored words: `hardest`(1.8), `shaft`(1.8), `whats`(0.8), `part`(0.8), `straight`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fletcher.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fletcher.followup.ask_more   [44 chars]
    en  What's the hardest part of a straight shaft?
    >>  ............................................
    pt  Qual é a parte mais difícil de uma haste reta?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fletcher.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fletcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you ever get them back?" | "Straight shafts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of a straight shaft?"
       spoken on: conversations.scene.work.fletcher.followup, button `ask_more`
       leaves the player on: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.hard`: the villager explains. Subject `work.fletcher.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.fletcher.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.fletcher.hard/1   [94 chars]
    en  A shaft that dried crooked. You can't see it and you can't feel it until it's fifty paces out.
    >>  ............................................
    pt  Uma haste que secou torta. Você não vê e não sente até ela estar a cinquenta passos.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.hard/2   [76 chars]
    en  Rushing. Every bad arrow I've made was one somebody stood over me for, %1$s.
    >>  ............................................
    pt  Pressa. Toda flecha ruim que eu fiz foi uma que alguém ficou me apressando, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the bench."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.fletcher.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fletcher.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fletcher.followup.leave   [28 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the bench."
       spoken on: conversations.scene.work.fletcher.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.left`: the villager accepts. Subject `work.fletcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fletcher.bad_supplier.active.respond / leave; conversations.scene.work.fletcher.bad_supplier.succeeded.respond / leave; conversations.scene.work.fletcher.crooked_batch.blocked.respond / leave; conversations.scene.work.fletcher.crooked_batch.succeeded.respond / leave; conversations.scene.work.fletcher.who_buys.active.respond / leave; conversations.scene.work.fletcher.who_buys.succeeded.respond / leave; conversations.topic.work.fletcher.craft.respond / leave; conversations.topic.work.fletcher.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.fletcher.bad_supplier.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.fletcher.who_buys.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.fletcher.who_buys.active` — e.g. "%2$s came in yesterday wanting three dozen and would say nothing about what for."


```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.who_buys.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.fletcher.who_buys.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.fletcher.who_buys.active.respond   [18 chars]
    en  Who buys from you.
    >>  ............................................
    pt  Quem compra de você.
    >>  ............................................
```


### Button `ask_whether_she_asks` — "Do you ask what they're for?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fletcher.who_buys.active` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fletcher.who_buys.active.ask_whether_she_asks` — accepted phrasings: "do you ask what theyre for"; "do you ask what they are for"; "do you ask about the purpose"
  - the message must contain one of: `purpose`, `ask`
  - scored words: `purpose`(1.8), `ask`(1.8), `theyre`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.who_buys.active.respond.ask_whether_she_asks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fletcher.who_buys.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fletcher.who_buys.active.respond.ask_whether_she_asks   [28 chars]
    en  Do you ask what they're for?
    >>  ............................................
    pt  Você pergunta para que servem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fletcher.the_bow_trade`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.fletcher.who_buys"}
- Then opens: `conversations.scene.work.fletcher.followup`
- …where the player's next choices will be: "What's the hardest part of a straight shaft?" | "I'll leave you to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.who_buys.active.explained
WHO    VILLAGER — what the player reads after pressing "Do you ask what they're for?"
       spoken on: conversations.scene.work.fletcher.who_buys.active.respond, button `ask_whether_she_asks`
       leaves the player on: conversations.scene.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.who_buys.active.explained`: the villager explains. Subject `work.fletcher.the_bow_trade`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fletcher.who_buys.active.explained/1   [126 chars]
    en  Always. Not to judge — to make them say it out loud. Half the trouble in the world is done by people who never had to name it.
    >>  ............................................
    pt  Sempre. Não para julgar — para fazerem dizer em voz alta. Metade da encrenca do mundo é feita por gente que nunca precisou nomear.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.who_buys.active.explained/2   [133 chars]
    en  I ask, and I sell anyway more often than I would like, because refusing on a feeling is a bad rule and I have not found a better one.
    >>  ............................................
    pt  Pergunto, e vendo assim mesmo mais vezes do que gostaria, porque recusar por pressentimento é uma regra ruim e eu não achei uma melhor.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.who_buys.active.explained/3   [125 chars]
    en  I ask, and if they will not say, I sell them the practice arrows. They are perfectly good and they are also not my best work.
    >>  ............................................
    pt  Pergunto, e se não quiserem dizer, vendo as de treino. São perfeitamente boas e também não são o meu melhor trabalho.
    >>  ............................................
```


### Button `back_her_rule` — "Making them say it out loud is right."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.fletcher.who_buys.active` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fletcher.who_buys.active.back_her_rule` — accepted phrasings: "making them say it out loud is right"; "making them say it out loud is right"; "asking them to name it is the right rule"
  - the message must contain one of: `loud`, `name`, `rule`
  - scored words: `loud`(1.8), `name`(1.8), `rule`(1.8), `making`(0.8), `say`(0.8), `out`(0.8), `right`(0.8), `asking`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.who_buys.active.respond.back_her_rule
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fletcher.who_buys.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fletcher.who_buys.active.respond.back_her_rule   [37 chars]
    en  Making them say it out loud is right.
    >>  ............................................
    pt  Fazer dizerem em voz alta está certo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, trust +1  _(recorded under topic `work.fletcher.the_bow_trade`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.fletcher.who_buys"}
- Then opens: `conversations.scene.work.fletcher.followup`
- …where the player's next choices will be: "What's the hardest part of a straight shaft?" | "I'll leave you to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.who_buys.active.steadied
WHO    VILLAGER — what the player reads after pressing "Making them say it out loud is right."
       spoken on: conversations.scene.work.fletcher.who_buys.active.respond, button `back_her_rule`
       leaves the player on: conversations.scene.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.who_buys.active.steadied`: the villager accepts. Subject `work.fletcher.the_bow_trade`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fletcher.who_buys.active.steadied/1   [135 chars]
    en  It is the only part I control. What happens after the sale is not mine, and pretending otherwise would be a comfortable kind of vanity.
    >>  ............................................
    pt  É a única parte que eu controlo. O que acontece depois da venda não é meu, e fingir o contrário seria uma vaidade confortável.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.who_buys.active.steadied/2   [110 chars]
    en  Thank you. I have been told it is nosy. It is nosy. It has also stopped two sales that I am glad were stopped.
    >>  ............................................
    pt  Obrigada. Já me disseram que é intromissão. É intromissão. Também impediu duas vendas que eu fico feliz de terem sido impedidas.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.who_buys.active.steadied/3   [140 chars]
    en  I will keep doing it. And I will keep selling to people who answer badly, because a bad answer given is still better than no question asked.
    >>  ............................................
    pt  Vou continuar. E vou continuar vendendo para quem responde mal, porque uma resposta ruim ainda é melhor do que nenhuma pergunta feita.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the bench."

*stance family `exit` · tone `plain` · answers the beat(s) `work.fletcher.who_buys.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.who_buys.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fletcher.who_buys.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fletcher.who_buys.active.respond.leave   [35 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the bench."
       spoken on: conversations.scene.work.fletcher.who_buys.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.left`: the villager accepts. Subject `work.fletcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fletcher.bad_supplier.active.respond / leave; conversations.scene.work.fletcher.bad_supplier.succeeded.respond / leave; conversations.scene.work.fletcher.crooked_batch.blocked.respond / leave; conversations.scene.work.fletcher.crooked_batch.succeeded.respond / leave; conversations.scene.work.fletcher.followup / leave; conversations.scene.work.fletcher.who_buys.succeeded.respond / leave; conversations.topic.work.fletcher.craft.respond / leave; conversations.topic.work.fletcher.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.fletcher.bad_supplier.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.fletcher.who_buys.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.fletcher.who_buys.succeeded` — e.g. "He came back and told me. Deer, for a family that had lost a barn, and he had been embarrassed to say so."


```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.who_buys.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.fletcher.who_buys.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.fletcher.who_buys.succeeded.respond   [11 chars]
    en  That order.
    >>  ............................................
    pt  Aquela encomenda.
    >>  ............................................
```


### Button `note_the_lesson` — "A shrug isn't much to build on."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.fletcher.who_buys.succeeded` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fletcher.who_buys.succeeded.note_the_lesson` — accepted phrasings: "a shrug isnt much to build on"; "a shrug is thin evidence to build on"; "you built a lot on very thin evidence"
  - the message must contain one of: `shrug`, `evidence`, `build`
  - scored words: `shrug`(1.8), `evidence`(1.8), `build`(1.8), `isnt`(0.8), `much`(0.8), `thin`(0.8), `built`(0.8), `lot`(0.8), `very`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.who_buys.succeeded.respond.note_the_lesson
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fletcher.who_buys.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fletcher.who_buys.succeeded.respond.note_the_lesson   [31 chars]
    en  A shrug isn't much to build on.
    >>  ............................................
    pt  Um dar de ombros é pouco para construir.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2, respect +2  _(recorded under topic `work.fletcher.the_bow_trade`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.fletcher.who_buys"}
- Then opens: `conversations.scene.work.fletcher.followup`
- …where the player's next choices will be: "What's the hardest part of a straight shaft?" | "I'll leave you to the bench."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.who_buys.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "A shrug isn't much to build on."
       spoken on: conversations.scene.work.fletcher.who_buys.succeeded.respond, button `note_the_lesson`
       leaves the player on: conversations.scene.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.who_buys.succeeded.acknowledged`: the villager accepts. Subject `work.fletcher.the_bow_trade`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fletcher.who_buys.succeeded.acknowledged/1   [104 chars]
    en  It is not, and I know it, and I will do it again the next time somebody is short with me at the counter.
    >>  ............................................
    pt  Não é, e eu sei, e vou fazer de novo na próxima vez que alguém for seco comigo no balcão.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.who_buys.succeeded.acknowledged/2   [130 chars]
    en  Thank you. I spend all day looking for the flaw in forty identical things. It turns out the habit does not switch off at the door.
    >>  ............................................
    pt  Obrigada. Passo o dia procurando o defeito em quarenta coisas idênticas. Parece que o hábito não desliga na porta.
    >>  ............................................
  dialogue.conversations.scene.work.fletcher.who_buys.succeeded.acknowledged/3   [111 chars]
    en  He was embarrassed. That had genuinely not been on my list of explanations, and it should have been at the top.
    >>  ............................................
    pt  Ele estava com vergonha. Isso genuinamente não estava na minha lista de explicações, e deveria estar no topo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the bench."

*stance family `exit` · tone `plain` · answers the beat(s) `work.fletcher.who_buys.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.fletcher.who_buys.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fletcher.who_buys.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fletcher.who_buys.succeeded.respond.leave   [35 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the bench."
       spoken on: conversations.scene.work.fletcher.who_buys.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.left`: the villager accepts. Subject `work.fletcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fletcher.bad_supplier.active.respond / leave; conversations.scene.work.fletcher.bad_supplier.succeeded.respond / leave; conversations.scene.work.fletcher.crooked_batch.blocked.respond / leave; conversations.scene.work.fletcher.crooked_batch.succeeded.respond / leave; conversations.scene.work.fletcher.followup / leave; conversations.scene.work.fletcher.who_buys.active.respond / leave; conversations.topic.work.fletcher.craft.respond / leave; conversations.topic.work.fletcher.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.fletcher.bad_supplier.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.fletcher.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.fletcher.craft` — e.g. "Straightness you learn by eye and then stop trusting your eye. The string tells the truth."


```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.fletcher.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.fletcher.craft.respond   [26 chars]
    en  That's the learning of it.
    >>  ............................................
    pt  É o aprendizado da coisa.
    >>  ............................................
```


### Button `ask_guard` — "What did the guard tell you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fletcher.craft` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fletcher.craft.ask_guard` — accepted phrasings: "what did the guard tell you"
  - the message must contain one of: `guard`, `told`
  - scored words: `guard`(1.5), `told`(1.2), `said`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.craft.respond.ask_guard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.craft.respond.ask_guard   [28 chars]
    en  What did the guard tell you?
    >>  ............................................
    pt  O que o guarda te disse?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fletcher.craft.ask_guard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fletcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you ever get them back?" | "Straight shafts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.craft.ask_guard
WHO    VILLAGER — what the player reads after pressing "What did the guard tell you?"
       spoken on: conversations.topic.work.fletcher.craft.respond, button `ask_guard`
       leaves the player on: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.craft.ask_guard`: the villager explains. Subject `work.fletcher.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fletcher.craft.ask_guard/1   [85 chars]
    en  That mine drifted left in wind. He'd known for a year and hadn't wanted to offend me.
    >>  ............................................
    pt  Que as minhas desviavam pra esquerda no vento. Ele sabia fazia um ano e não quis me ofender.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.craft.ask_guard/2   [76 chars]
    en  That two of his had shattered on impact. He apologised. He apologised, %1$s.
    >>  ............................................
    pt  Que duas dele se estilhaçaram no impacto. Ele pediu desculpa. Ele pediu desculpa, %1$s.
    >>  ............................................
```


### Button `admire` — "Not many trades let you be told that plainly."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.fletcher.craft` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fletcher.craft.admire` — accepted phrasings: "not many trades let you be told that plainly"
  - the message must contain one of: `plainly`, `honest`
  - scored words: `plainly`(1.5), `told`(0.8), `honest`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.craft.respond.admire   [45 chars]
    en  Not many trades let you be told that plainly.
    >>  ............................................
    pt  Poucos ofícios permitem que te digam isso tão direto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.fletcher.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.fletcher.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fletcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you ever get them back?" | "Straight shafts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.craft.admire
WHO    VILLAGER — what the player reads after pressing "Not many trades let you be told that plainly."
       spoken on: conversations.topic.work.fletcher.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.craft.admire`: the villager accepts. Subject `work.fletcher.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fletcher.craft.admire/1   [73 chars]
    en  No. And I've asked him to keep doing it, which was harder than it sounds.
    >>  ............................................
    pt  Não. E eu pedi pra ele continuar, o que foi mais difícil do que parece.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.craft.admire/2   [75 chars]
    en  It's the whole reason I got better. Nobody improves on their own eye alone.
    >>  ............................................
    pt  É a razão inteira de eu ter melhorado. Ninguém melhora só com o próprio olho.
    >>  ............................................
```


### Button `ask_test` — "How do you test them now?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fletcher.craft` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fletcher.craft.ask_test` — accepted phrasings: "how do you test them now"
  - the message must contain one of: `test`, `check`
  - scored words: `test`(1.5), `check`(1.2), `now`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.craft.respond.ask_test
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.craft.respond.ask_test   [25 chars]
    en  How do you test them now?
    >>  ............................................
    pt  Como você testa agora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fletcher.craft.ask_test`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fletcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you ever get them back?" | "Straight shafts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.craft.ask_test
WHO    VILLAGER — what the player reads after pressing "How do you test them now?"
       spoken on: conversations.topic.work.fletcher.craft.respond, button `ask_test`
       leaves the player on: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.craft.ask_test`: the villager explains. Subject `work.fletcher.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fletcher.craft.ask_test/1   [77 chars]
    en  Roll them on the bench and watch the gap. Then shoot ten and believe the ten.
    >>  ............................................
    pt  Rolo na bancada e olho a fresta. Aí atiro dez e acredito nas dez.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.craft.ask_test/2   [83 chars]
    en  The string, the roll, and a wall behind the workshop with a great many holes in it.
    >>  ............................................
    pt  A corda, o rolar, e uma parede atrás da oficina com muitos furos.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with the batch."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.fletcher.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.craft.respond.leave   [35 chars]
    en  I'll let you get on with the batch.
    >>  ............................................
    pt  Vou deixar você seguir com o lote.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with the batch."
       spoken on: conversations.topic.work.fletcher.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.left`: the villager accepts. Subject `work.fletcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fletcher.bad_supplier.active.respond / leave; conversations.scene.work.fletcher.bad_supplier.succeeded.respond / leave; conversations.scene.work.fletcher.crooked_batch.blocked.respond / leave; conversations.scene.work.fletcher.crooked_batch.succeeded.respond / leave; conversations.scene.work.fletcher.followup / leave; conversations.scene.work.fletcher.who_buys.active.respond / leave; conversations.scene.work.fletcher.who_buys.succeeded.respond / leave; conversations.topic.work.fletcher.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.fletcher.bad_supplier.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.fletcher.followup`

**Reached from 20 route(s):** `conversations.scene.work.fletcher.followup` / `ask_more`; `conversations.topic.work.fletcher.craft.respond` / `ask_guard`; `conversations.topic.work.fletcher.craft.respond` / `admire`; `conversations.topic.work.fletcher.craft.respond` / `ask_test`; `conversations.topic.work.fletcher.future.respond` / `ask_bow`; `conversations.topic.work.fletcher.future.respond` / `encourage`; `conversations.topic.work.fletcher.future.respond` / `ask_apprentice`; `conversations.topic.work.fletcher.respond` / `ask_hard`; `conversations.topic.work.fletcher.respond` / `value`; `conversations.topic.work.fletcher.respond` / `challenge`; `conversations.topic.work.fletcher.respond` / `challenge`; `conversations.topic.work.fletcher.risk.respond` / `ask_rushed` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.fletcher.challenge.landed` — e.g. "It is. So is a house, if you're rude about timber."
- `conversations.work.prof.fletcher.challenge.stung` — e.g. "...Then take a bundle and go stand a watch with them."
- `conversations.work.prof.fletcher.craft.admire` — e.g. "No. And I've asked him to keep doing it, which was harder than it sounds."
- `conversations.work.prof.fletcher.craft.ask_guard` — e.g. "That mine drifted left in wind. He'd known for a year and hadn't wanted to offend me."
- `conversations.work.prof.fletcher.craft.ask_test` — e.g. "Roll them on the bench and watch the gap. Then shoot ten and believe the ten."
- `conversations.work.prof.fletcher.future.ask_apprentice` — e.g. "One girl, twice. She got bored at the feathers, which is where everyone gets bored."
- `conversations.work.prof.fletcher.future.ask_bow` — e.g. "Sixty arrows a week. A bow takes a month and nobody's short of arrows for a month."
- `conversations.work.prof.fletcher.future.encourage` — e.g. "...That is annoyingly good advice and I resent how simple it sounds."
- `conversations.work.prof.fletcher.hard` — e.g. "A shaft that dried crooked. You can't see it and you can't feel it until it's fifty paces out."
- `conversations.work.prof.fletcher.risk.ask_refuse` — e.g. "Once. A traveller wanted forty and wouldn't say for what. He got none and left angry."
- `conversations.work.prof.fletcher.risk.ask_rushed` — e.g. "The watch, when there's been a sighting. They're not wrong to. It's still how mistakes happen."
- `conversations.work.prof.fletcher.risk.sympathise` — e.g. "...I do. It's a quiet sort of weight and I've never found a place to set it down."
- `conversations.work.prof.fletcher.task.ask_feathers` — e.g. "It spins. A spinning arrow goes somewhere, just not where you looked."
- `conversations.work.prof.fletcher.task.ask_pace` — e.g. "Sixty will happen. Sixty good ones is a different question and the one I care about."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.fletcher.followup   [27 chars]
    en  That's the bench, in short.
    >>  ............................................
    pt  É a bancada, resumindo.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.fletcher.challenge.landed`, `work.fletcher.challenge.stung`, `work.fletcher.craft.admire`, `work.fletcher.craft.ask_guard`, `work.fletcher.craft.ask_test`, `work.fletcher.future.ask_apprentice`, `work.fletcher.future.ask_bow`, `work.fletcher.future.encourage`, `work.fletcher.hard`, `work.fletcher.risk.ask_refuse`, `work.fletcher.risk.ask_rushed`, `work.fletcher.risk.sympathise`, `work.fletcher.task.ask_feathers`, `work.fletcher.task.ask_pace`, `work.fletcher.task.offer_hands`, `work.fletcher.value`, `work.fletcher.village.ask_archer`, `work.fletcher.village.ask_stopped`, `work.fletcher.village.say_thanks` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.fletcher.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `invisible`
  - scored words: `thought`(1.2), `invisible`(1.5), `craft`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.fletcher.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.fletcher.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.fletcher.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.fletcher.thanks`: the villager accepts. Subject `work.fletcher.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fletcher.thanks/1   [81 chars]
    en  Nobody thinks about an arrow that worked. That's rather the measure of the trade.
    >>  ............................................
    pt  Ninguém pensa numa flecha que funcionou. É mais ou menos a medida do ofício.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.thanks/2   [63 chars]
    en  Good craft is invisible, %1$s. It's a poor career for the vain.
    >>  ............................................
    pt  Bom artesanato é invisível, %1$s. É carreira ruim pra quem é vaidoso.
    >>  ............................................
```


### Button `ask_more` — "Do you ever get them back?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fletcher.challenge.landed`, `work.fletcher.challenge.stung`, `work.fletcher.craft.admire`, `work.fletcher.craft.ask_guard`, `work.fletcher.craft.ask_test`, `work.fletcher.future.ask_apprentice`, `work.fletcher.future.ask_bow`, `work.fletcher.future.encourage`, `work.fletcher.hard`, `work.fletcher.risk.ask_refuse`, `work.fletcher.risk.ask_rushed`, `work.fletcher.risk.sympathise`, `work.fletcher.task.ask_feathers`, `work.fletcher.task.ask_pace`, `work.fletcher.task.offer_hands`, `work.fletcher.value`, `work.fletcher.village.ask_archer`, `work.fletcher.village.ask_stopped`, `work.fletcher.village.say_thanks` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.fletcher.more` — accepted phrasings: "do you ever get them back"
  - the message must contain one of: `back`, `broken`, `returned`
  - scored words: `back`(1.0), `broken`(1.5), `returned`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.followup.ask_more   [26 chars]
    en  Do you ever get them back?
    >>  ............................................
    pt  Alguma vez elas voltam?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.fletcher.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.more
WHO    VILLAGER — what the player reads after pressing "Do you ever get them back?"
       spoken on: conversations.topic.work.fletcher.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.fletcher.more`: the villager discloses. Subject `work.fletcher.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fletcher.more/1   [78 chars]
    en  Broken ones, sometimes. I read them like a letter — where it hit, what it hit.
    >>  ............................................
    pt  Quebradas, às vezes. Eu leio como carta — onde acertou, o que acertou.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.more/2   [81 chars]
    en  The guard brings me a handful every month. Half of them tell me something useful.
    >>  ............................................
    pt  O guarda me traz um punhado todo mês. Metade me diz algo útil.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones. Two shattered on impact once, and the guard apologised to me for it.
    >>  ............................................
    pt  Quebradas. Duas se estilhaçaram no impacto uma vez, e o guarda me pediu desculpa por isso.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.fletcher.more/2
    en  Every arrow goes somewhere I'll never see. That's the part I don't discuss at the inn.
    >>  ............................................
    pt  Toda flecha vai pra um lugar que eu nunca vou ver. É a parte que eu não discuto na estalagem.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones, now and then. They keep. I read them when there's a quiet afternoon.
    >>  ............................................
    pt  Quebradas, de vez em quando. Elas esperam. Eu leio numa tarde calma.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.fletcher.more/2
    en  Sixty a week, twelve years. It's a slow way to matter and it's the way I've got.
    >>  ............................................
    pt  Sessenta por semana, doze anos. É um jeito lento de importar e é o jeito que eu tenho.
    >>  ............................................
  confident.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones, sometimes. I read them like a letter — where it hit and what it hit.
    >>  ............................................
    pt  Quebradas, às vezes. Eu leio como carta — onde bateu e no que bateu.
    >>  ............................................
  confident.dialogue.conversations.work.prof.fletcher.more/2
    en  Sixty a week and not one of them crooked. Twelve years, and the watch has never run short.
    >>  ............................................
    pt  Sessenta por semana e nenhuma torta. Doze anos, e a guarda nunca ficou sem.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones, sometimes. I read them like a letter — where it hit and what it hit.
    >>  ............................................
    pt  Quebradas, às vezes. Eu leio como carta — onde bateu e no que bateu.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.fletcher.more/2
    en  Sixty a week and not one of them crooked. Twelve years, and the watch has never run short.
    >>  ............................................
    pt  Sessenta por semana e nenhuma torta. Doze anos, e a guarda nunca ficou sem.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones. The archer brings them back and tells me what happened to each. Best hour of my week.
    >>  ............................................
    pt  Quebradas. A arqueira traz de volta e conta o que houve com cada uma. Melhor hora da semana.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.fletcher.more/2
    en  Sixty a week. Say that to the archer sometime — half of what she's done is on my bench.
    >>  ............................................
    pt  Sessenta por semana. Diga isso à arqueira um dia — metade do que ela fez está na minha bancada.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones. The archer brings them back and tells me what happened to each. Best hour of my week.
    >>  ............................................
    pt  Quebradas. A arqueira traz de volta e conta o que houve com cada uma. Melhor hora da semana.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.fletcher.more/2
    en  Sixty a week. Say that to the archer sometime — half of what she's done is on my bench.
    >>  ............................................
    pt  Sessenta por semana. Diga isso à arqueira um dia — metade do que ela fez está na minha bancada.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones. The archer brings them back and tells me what happened to each. Best hour of my week.
    >>  ............................................
    pt  Quebradas. A arqueira traz de volta e conta o que houve com cada uma. Melhor hora da semana.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.fletcher.more/2
    en  Sixty a week. Say that to the archer sometime — half of what she's done is on my bench.
    >>  ............................................
    pt  Sessenta por semana. Diga isso à arqueira um dia — metade do que ela fez está na minha bancada.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones. Two shattered on impact once, and the guard apologised to me for it.
    >>  ............................................
    pt  Quebradas. Duas se estilhaçaram no impacto uma vez, e o guarda me pediu desculpa por isso.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.fletcher.more/2
    en  Every arrow goes somewhere I'll never see. That's the part I don't discuss at the inn.
    >>  ............................................
    pt  Toda flecha vai pra um lugar que eu nunca vou ver. É a parte que eu não discuto na estalagem.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones, sometimes. I read them like a letter — where it hit and what it hit.
    >>  ............................................
    pt  Quebradas, às vezes. Eu leio como carta — onde bateu e no que bateu.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.fletcher.more/2
    en  Sixty a week and not one of them crooked. Twelve years, and the watch has never run short.
    >>  ............................................
    pt  Sessenta por semana e nenhuma torta. Doze anos, e a guarda nunca ficou sem.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones, sometimes. I read them like a letter — where it hit and what it hit.
    >>  ............................................
    pt  Quebradas, às vezes. Eu leio como carta — onde bateu e no que bateu.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.fletcher.more/2
    en  Sixty a week and not one of them crooked. Twelve years, and the watch has never run short.
    >>  ............................................
    pt  Sessenta por semana e nenhuma torta. Doze anos, e a guarda nunca ficou sem.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones, sometimes. Where it struck, what it struck, and whether the shaft was mine.
    >>  ............................................
    pt  Quebradas, às vezes. Onde bateu, no que bateu, e se a haste era minha.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.fletcher.more/2
    en  Sixty a week. The last twenty of any batch are the ones that teach me anything.
    >>  ............................................
    pt  Sessenta por semana. As últimas vinte de qualquer lote são as que me ensinam algo.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones, now and then. They keep. I read them when there's a quiet afternoon.
    >>  ............................................
    pt  Quebradas, de vez em quando. Elas esperam. Eu leio numa tarde calma.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.fletcher.more/2
    en  Sixty a week, twelve years. It's a slow way to matter and it's the way I've got.
    >>  ............................................
    pt  Sessenta por semana, doze anos. É um jeito lento de importar e é o jeito que eu tenho.
    >>  ............................................
  odd.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones, sometimes. Where it struck, what it struck, and whether the shaft was mine.
    >>  ............................................
    pt  Quebradas, às vezes. Onde bateu, no que bateu, e se a haste era minha.
    >>  ............................................
  odd.dialogue.conversations.work.prof.fletcher.more/2
    en  Sixty a week. The last twenty of any batch are the ones that teach me anything.
    >>  ............................................
    pt  Sessenta por semana. As últimas vinte de qualquer lote são as que me ensinam algo.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones, now and then. They keep. I read them when there's a quiet afternoon.
    >>  ............................................
    pt  Quebradas, de vez em quando. Elas esperam. Eu leio numa tarde calma.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.fletcher.more/2
    en  Sixty a week, twelve years. It's a slow way to matter and it's the way I've got.
    >>  ............................................
    pt  Sessenta por semana, doze anos. É um jeito lento de importar e é o jeito que eu tenho.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones! I read them like letters. Where it hit, what it hit, and how cross the archer was.
    >>  ............................................
    pt  Quebradas! Eu leio como cartas. Onde bateu, no que bateu, e o quanto a arqueira ficou brava.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.fletcher.more/2
    en  Sixty a week for twelve years. That's — a great many arrows. I've never done that sum before.
    >>  ............................................
    pt  Sessenta por semana por doze anos. Isso é — muitíssimas flechas. Eu nunca tinha feito essa conta.
    >>  ............................................
  playful.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones! I read them like letters. Where it hit, what it hit, and how cross the archer was.
    >>  ............................................
    pt  Quebradas! Eu leio como cartas. Onde bateu, no que bateu, e o quanto a arqueira ficou brava.
    >>  ............................................
  playful.dialogue.conversations.work.prof.fletcher.more/2
    en  Sixty a week for twelve years. That's — a great many arrows. I've never done that sum before.
    >>  ............................................
    pt  Sessenta por semana por doze anos. Isso é — muitíssimas flechas. Eu nunca tinha feito essa conta.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones, now and then. They keep. I read them when there's a quiet afternoon.
    >>  ............................................
    pt  Quebradas, de vez em quando. Elas esperam. Eu leio numa tarde calma.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.fletcher.more/2
    en  Sixty a week, twelve years. It's a slow way to matter and it's the way I've got.
    >>  ............................................
    pt  Sessenta por semana, doze anos. É um jeito lento de importar e é o jeito que eu tenho.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones. Two shattered on impact once, and the guard apologised to me for it.
    >>  ............................................
    pt  Quebradas. Duas se estilhaçaram no impacto uma vez, e o guarda me pediu desculpa por isso.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.fletcher.more/2
    en  Every arrow goes somewhere I'll never see. That's the part I don't discuss at the inn.
    >>  ............................................
    pt  Toda flecha vai pra um lugar que eu nunca vou ver. É a parte que eu não discuto na estalagem.
    >>  ............................................
  shy.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones, sometimes. Where it struck, what it struck, and whether the shaft was mine.
    >>  ............................................
    pt  Quebradas, às vezes. Onde bateu, no que bateu, e se a haste era minha.
    >>  ............................................
  shy.dialogue.conversations.work.prof.fletcher.more/2
    en  Sixty a week. The last twenty of any batch are the ones that teach me anything.
    >>  ............................................
    pt  Sessenta por semana. As últimas vinte de qualquer lote são as que me ensinam algo.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones! I read them like letters. Where it hit, what it hit, and how cross the archer was.
    >>  ............................................
    pt  Quebradas! Eu leio como cartas. Onde bateu, no que bateu, e o quanto a arqueira ficou brava.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.fletcher.more/2
    en  Sixty a week for twelve years. That's — a great many arrows. I've never done that sum before.
    >>  ............................................
    pt  Sessenta por semana por doze anos. Isso é — muitíssimas flechas. Eu nunca tinha feito essa conta.
    >>  ............................................
  witty.dialogue.conversations.work.prof.fletcher.more/1
    en  Broken ones! I read them like letters. Where it hit, what it hit, and how cross the archer was.
    >>  ............................................
    pt  Quebradas! Eu leio como cartas. Onde bateu, no que bateu, e o quanto a arqueira ficou brava.
    >>  ............................................
  witty.dialogue.conversations.work.prof.fletcher.more/2
    en  Sixty a week for twelve years. That's — a great many arrows. I've never done that sum before.
    >>  ............................................
    pt  Sessenta por semana por doze anos. Isso é — muitíssimas flechas. Eu nunca tinha feito essa conta.
    >>  ............................................
```

</details>


### Button `leave` — "Straight shafts to you."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.fletcher.challenge.landed`, `work.fletcher.challenge.stung`, `work.fletcher.craft.admire`, `work.fletcher.craft.ask_guard`, `work.fletcher.craft.ask_test`, `work.fletcher.future.ask_apprentice`, `work.fletcher.future.ask_bow`, `work.fletcher.future.encourage`, `work.fletcher.hard`, `work.fletcher.risk.ask_refuse`, `work.fletcher.risk.ask_rushed`, `work.fletcher.risk.sympathise`, `work.fletcher.task.ask_feathers`, `work.fletcher.task.ask_pace`, `work.fletcher.task.offer_hands`, `work.fletcher.value`, `work.fletcher.village.ask_archer`, `work.fletcher.village.ask_stopped`, `work.fletcher.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.followup.leave   [23 chars]
    en  Straight shafts to you.
    >>  ............................................
    pt  Hastes retas pra você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.leave
WHO    VILLAGER — what the player reads after pressing "Straight shafts to you."
       spoken on: conversations.topic.work.fletcher.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.left`: the villager accepts. Subject `work.fletcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fletcher.bad_supplier.active.respond / leave; conversations.scene.work.fletcher.bad_supplier.succeeded.respond / leave; conversations.scene.work.fletcher.crooked_batch.blocked.respond / leave; conversations.scene.work.fletcher.crooked_batch.succeeded.respond / leave; conversations.scene.work.fletcher.followup / leave; conversations.scene.work.fletcher.who_buys.active.respond / leave; conversations.scene.work.fletcher.who_buys.succeeded.respond / leave; conversations.topic.work.fletcher.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.fletcher.bad_supplier.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.fletcher.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.fletcher.future` — e.g. "I'd like to make one bow. Properly. It's a different trade and I've wanted it for years."


```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.fletcher.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.fletcher.future.respond   [20 chars]
    en  That's what's ahead.
    >>  ............................................
    pt  É o que está à frente.
    >>  ............................................
```


### Button `ask_bow` — "What's in the way of the bow?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fletcher.future` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fletcher.future.ask_bow` — accepted phrasings: "what's in the way of the bow"
  - the message must contain one of: `bow`, `obstacle`
  - scored words: `bow`(1.5), `obstacle`(1.2), `why`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.future.respond.ask_bow
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.future.respond.ask_bow   [29 chars]
    en  What's in the way of the bow?
    >>  ............................................
    pt  O que está no caminho do arco?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fletcher.future.ask_bow`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fletcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you ever get them back?" | "Straight shafts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.future.ask_bow
WHO    VILLAGER — what the player reads after pressing "What's in the way of the bow?"
       spoken on: conversations.topic.work.fletcher.future.respond, button `ask_bow`
       leaves the player on: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.future.ask_bow`: the villager explains. Subject `work.fletcher.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fletcher.future.ask_bow/1   [82 chars]
    en  Sixty arrows a week. A bow takes a month and nobody's short of arrows for a month.
    >>  ............................................
    pt  Sessenta flechas por semana. Um arco leva um mês e ninguém fica sem flecha um mês.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.future.ask_bow/2   [66 chars]
    en  Nerve, mostly. I know exactly how bad my first one would be, %1$s.
    >>  ............................................
    pt  Coragem, principalmente. Eu sei exatamente o quão ruim seria o primeiro, %1$s.
    >>  ............................................
```


### Button `encourage` — "Make the bad one. Then make the second."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.fletcher.future` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fletcher.future.encourage` — accepted phrasings: "make the bad one. then make the second"
  - the message must contain one of: `second`, `bad`, `start`
  - scored words: `second`(1.5), `bad`(1.0), `start`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.future.respond.encourage   [39 chars]
    en  Make the bad one. Then make the second.
    >>  ............................................
    pt  Faça o ruim. Depois faça o segundo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.fletcher.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.fletcher.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.fletcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you ever get them back?" | "Straight shafts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.future.encourage
WHO    VILLAGER — what the player reads after pressing "Make the bad one. Then make the second."
       spoken on: conversations.topic.work.fletcher.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.future.encourage`: the villager accepts. Subject `work.fletcher.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fletcher.future.encourage/1   [68 chars]
    en  ...That is annoyingly good advice and I resent how simple it sounds.
    >>  ............................................
    pt  ...É um conselho irritantemente bom e eu detesto como soa simples.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.future.encourage/2   [54 chars]
    en  The second. Right. Nobody ever puts it that way round.
    >>  ............................................
    pt  O segundo. Certo. Ninguém coloca nessa ordem.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...That's good advice and I resent it, which usually means it's true.
    >>  ............................................
    pt  ...É um bom conselho e eu detesto, o que geralmente quer dizer que é verdade.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Put that way round it stops being a thing I can keep postponing.
    >>  ............................................
    pt  O segundo. Nessa ordem deixa de ser algo que eu posso ficar adiando.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...Annoyingly good advice. I've given worse to apprentices and charged for it.
    >>  ............................................
    pt  ...Conselho irritantemente bom. Já dei pior a aprendizes e cobrei por isso.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Thirty years and nobody has put it that way round to me.
    >>  ............................................
    pt  O segundo. Trinta anos e ninguém pôs nessa ordem pra mim.
    >>  ............................................
  confident.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...That is annoyingly good advice and I resent how simple it sounds.
    >>  ............................................
    pt  ...É um conselho irritantemente bom e eu detesto o quanto soa simples.
    >>  ............................................
  confident.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Right. Nobody ever puts it that way round.
    >>  ............................................
    pt  O segundo. Certo. Ninguém nunca põe nessa ordem.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...That is annoyingly good advice and I resent how simple it sounds.
    >>  ............................................
    pt  ...É um conselho irritantemente bom e eu detesto o quanto soa simples.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Right. Nobody ever puts it that way round.
    >>  ............................................
    pt  O segundo. Certo. Ninguém nunca põe nessa ordem.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...That's annoyingly good advice, %1$s, and I resent how simple it sounds.
    >>  ............................................
    pt  ...É um conselho irritantemente bom, %1$s, e detesto o quanto soa simples.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Nobody puts it that way round. Trust you to.
    >>  ............................................
    pt  O segundo. Ninguém põe nessa ordem. Só podia ser você.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...That's annoyingly good advice, %1$s, and I resent how simple it sounds.
    >>  ............................................
    pt  ...É um conselho irritantemente bom, %1$s, e detesto o quanto soa simples.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Nobody puts it that way round. Trust you to.
    >>  ............................................
    pt  O segundo. Ninguém põe nessa ordem. Só podia ser você.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...That's annoyingly good advice, %1$s, and I resent how simple it sounds.
    >>  ............................................
    pt  ...É um conselho irritantemente bom, %1$s, e detesto o quanto soa simples.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Nobody puts it that way round. Trust you to.
    >>  ............................................
    pt  O segundo. Ninguém põe nessa ordem. Só podia ser você.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...That's good advice and I resent it, which usually means it's true.
    >>  ............................................
    pt  ...É um bom conselho e eu detesto, o que geralmente quer dizer que é verdade.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Put that way round it stops being a thing I can keep postponing.
    >>  ............................................
    pt  O segundo. Nessa ordem deixa de ser algo que eu posso ficar adiando.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...That is annoyingly good advice and I resent how simple it sounds.
    >>  ............................................
    pt  ...É um conselho irritantemente bom e eu detesto o quanto soa simples.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Right. Nobody ever puts it that way round.
    >>  ............................................
    pt  O segundo. Certo. Ninguém nunca põe nessa ordem.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...That is annoyingly good advice and I resent how simple it sounds.
    >>  ............................................
    pt  ...É um conselho irritantemente bom e eu detesto o quanto soa simples.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Right. Nobody ever puts it that way round.
    >>  ............................................
    pt  O segundo. Certo. Ninguém nunca põe nessa ordem.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...Annoyingly good advice.
    >>  ............................................
    pt  ...Conselho irritantemente bom.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Nobody says it that way round.
    >>  ............................................
    pt  O segundo. Ninguém diz nessa ordem.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...Annoyingly good advice. I've given worse to apprentices and charged for it.
    >>  ............................................
    pt  ...Conselho irritantemente bom. Já dei pior a aprendizes e cobrei por isso.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Thirty years and nobody has put it that way round to me.
    >>  ............................................
    pt  O segundo. Trinta anos e ninguém pôs nessa ordem pra mim.
    >>  ............................................
  odd.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...Annoyingly good advice.
    >>  ............................................
    pt  ...Conselho irritantemente bom.
    >>  ............................................
  odd.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Nobody says it that way round.
    >>  ............................................
    pt  O segundo. Ninguém diz nessa ordem.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...Annoyingly good advice. I've given worse to apprentices and charged for it.
    >>  ............................................
    pt  ...Conselho irritantemente bom. Já dei pior a aprendizes e cobrei por isso.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Thirty years and nobody has put it that way round to me.
    >>  ............................................
    pt  O segundo. Trinta anos e ninguém pôs nessa ordem pra mim.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...That is annoyingly good advice! I resent how simple you made it sound.
    >>  ............................................
    pt  ...É um conselho irritantemente bom! Detesto como você fez soar simples.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Right. Nobody puts it that way round, and now I can't unhear it.
    >>  ............................................
    pt  O segundo. Certo. Ninguém põe nessa ordem, e agora não consigo desouvir.
    >>  ............................................
  playful.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...That is annoyingly good advice! I resent how simple you made it sound.
    >>  ............................................
    pt  ...É um conselho irritantemente bom! Detesto como você fez soar simples.
    >>  ............................................
  playful.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Right. Nobody puts it that way round, and now I can't unhear it.
    >>  ............................................
    pt  O segundo. Certo. Ninguém põe nessa ordem, e agora não consigo desouvir.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...Annoyingly good advice. I've given worse to apprentices and charged for it.
    >>  ............................................
    pt  ...Conselho irritantemente bom. Já dei pior a aprendizes e cobrei por isso.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Thirty years and nobody has put it that way round to me.
    >>  ............................................
    pt  O segundo. Trinta anos e ninguém pôs nessa ordem pra mim.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...That's good advice and I resent it, which usually means it's true.
    >>  ............................................
    pt  ...É um bom conselho e eu detesto, o que geralmente quer dizer que é verdade.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Put that way round it stops being a thing I can keep postponing.
    >>  ............................................
    pt  O segundo. Nessa ordem deixa de ser algo que eu posso ficar adiando.
    >>  ............................................
  shy.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...Annoyingly good advice.
    >>  ............................................
    pt  ...Conselho irritantemente bom.
    >>  ............................................
  shy.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Nobody says it that way round.
    >>  ............................................
    pt  O segundo. Ninguém diz nessa ordem.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...That is annoyingly good advice! I resent how simple you made it sound.
    >>  ............................................
    pt  ...É um conselho irritantemente bom! Detesto como você fez soar simples.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Right. Nobody puts it that way round, and now I can't unhear it.
    >>  ............................................
    pt  O segundo. Certo. Ninguém põe nessa ordem, e agora não consigo desouvir.
    >>  ............................................
  witty.dialogue.conversations.work.prof.fletcher.future.encourage/1
    en  ...That is annoyingly good advice! I resent how simple you made it sound.
    >>  ............................................
    pt  ...É um conselho irritantemente bom! Detesto como você fez soar simples.
    >>  ............................................
  witty.dialogue.conversations.work.prof.fletcher.future.encourage/2
    en  The second. Right. Nobody puts it that way round, and now I can't unhear it.
    >>  ............................................
    pt  O segundo. Certo. Ninguém põe nessa ordem, e agora não consigo desouvir.
    >>  ............................................
```

</details>


### Button `ask_apprentice` — "Has anyone sat at the bench?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fletcher.future` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fletcher.future.ask_apprentice` — accepted phrasings: "has anyone sat at the bench"
  - the message must contain one of: `apprentice`, `bench`
  - scored words: `apprentice`(1.5), `bench`(1.2), `anyone`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.future.respond.ask_apprentice
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.future.respond.ask_apprentice   [28 chars]
    en  Has anyone sat at the bench?
    >>  ............................................
    pt  Alguém já sentou na bancada?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fletcher.future.ask_apprentice`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fletcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you ever get them back?" | "Straight shafts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.future.ask_apprentice
WHO    VILLAGER — what the player reads after pressing "Has anyone sat at the bench?"
       spoken on: conversations.topic.work.fletcher.future.respond, button `ask_apprentice`
       leaves the player on: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.future.ask_apprentice`: the villager explains. Subject `work.fletcher.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fletcher.future.ask_apprentice/1   [83 chars]
    en  One girl, twice. She got bored at the feathers, which is where everyone gets bored.
    >>  ............................................
    pt  Uma menina, duas vezes. Ficou entediada nas penas, que é onde todo mundo entedia.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.future.ask_apprentice/2   [71 chars]
    en  Not yet. I'd take anyone with steady hands and no opinions about speed.
    >>  ............................................
    pt  Ainda não. Aceitaria qualquer um de mão firme e sem opinião sobre velocidade.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with the batch."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.fletcher.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.future.respond.leave   [35 chars]
    en  I'll let you get on with the batch.
    >>  ............................................
    pt  Vou deixar você seguir com o lote.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with the batch."
       spoken on: conversations.topic.work.fletcher.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.left`: the villager accepts. Subject `work.fletcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fletcher.bad_supplier.active.respond / leave; conversations.scene.work.fletcher.bad_supplier.succeeded.respond / leave; conversations.scene.work.fletcher.crooked_batch.blocked.respond / leave; conversations.scene.work.fletcher.crooked_batch.succeeded.respond / leave; conversations.scene.work.fletcher.followup / leave; conversations.scene.work.fletcher.who_buys.active.respond / leave; conversations.scene.work.fletcher.who_buys.succeeded.respond / leave; conversations.topic.work.fletcher.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.fletcher.bad_supplier.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.fletcher.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.fletcher` — e.g. "Every arrow that flies true is a small promise kept. I keep hundreds a week."


```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.fletcher.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.fletcher.respond   [42 chars]
    en  Hundreds a week. Each one has to be right.
    >>  ............................................
    pt  Centenas por semana. Cada uma tem que estar certa.
    >>  ............................................
```


### Button `ask_hard` — "What makes one go wrong?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fletcher.identity` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fletcher.hard` — accepted phrasings: "what makes one go wrong"
  - the message must contain one of: `wrong`, `crooked`, `fail`
  - scored words: `wrong`(1.2), `crooked`(1.5), `fail`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.respond.ask_hard   [24 chars]
    en  What makes one go wrong?
    >>  ............................................
    pt  O que faz uma sair errada?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.fletcher.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fletcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you ever get them back?" | "Straight shafts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.hard
WHO    VILLAGER — what the player reads after pressing "What makes one go wrong?"
       spoken on: conversations.topic.work.fletcher.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.hard`: the villager explains. Subject `work.fletcher.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fletcher.followup / ask_more
```

> Written out in full under **`conversations.scene.work.fletcher.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "The guards trust their lives to those."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.fletcher.identity` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fletcher.value` — accepted phrasings: "the guards trust their lives to those"
  - the message must contain one of: `guards`, `quiver`
  - scored words: `guards`(1.5), `quiver`(1.5), `trust`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.respond.value   [38 chars]
    en  The guards trust their lives to those.
    >>  ............................................
    pt  Os guardas confiam a vida nelas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.fletcher.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.fletcher.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fletcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you ever get them back?" | "Straight shafts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.value
WHO    VILLAGER — what the player reads after pressing "The guards trust their lives to those."
       spoken on: conversations.topic.work.fletcher.respond, button `value`
       leaves the player on: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.value`: the villager accepts. Subject `work.fletcher.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fletcher.value/1   [63 chars]
    en  They do, and they'll never once say so. That's the arrangement.
    >>  ............................................
    pt  Confiam, e nunca vão dizer isso. É o combinado.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.value/2   [72 chars]
    en  Aye. Which is why the crooked ones go on the fire and not in the quiver.
    >>  ............................................
    pt  É. Por isso as tortas vão pro fogo e não pra aljava.
    >>  ............................................
```


### Button `challenge` — "It's sticks and feathers."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.fletcher.identity` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fletcher.challenge` — accepted phrasings: "it's sticks and feathers"
  - the message must contain one of: `sticks`, `feathers`
  - scored words: `sticks`(1.5), `feathers`(1.5), `simple`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.respond.challenge   [25 chars]
    en  It's sticks and feathers.
    >>  ............................................
    pt  É graveto e pena.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.fletcher.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.fletcher.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fletcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you ever get them back?" | "Straight shafts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.challenge.landed
WHO    VILLAGER — what the player reads after pressing "It's sticks and feathers."
       spoken on: conversations.topic.work.fletcher.respond, button `challenge`
       leaves the player on: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.challenge.landed`: the villager resists. Subject `work.fletcher.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fletcher.challenge.landed/1   [50 chars]
    en  It is. So is a house, if you're rude about timber.
    >>  ............................................
    pt  É. Uma casa também, se você for grosseiro com madeira.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.challenge.landed/2   [73 chars]
    en  Sticks and feathers and about nine hundred hours of learning which, %1$s.
    >>  ............................................
    pt  Graveto e pena e umas novecentas horas aprendendo qual dos dois, %1$s.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.fletcher.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.fletcher.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fletcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you ever get them back?" | "Straight shafts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.challenge.stung
WHO    VILLAGER — what the player reads after pressing "It's sticks and feathers."
       spoken on: conversations.topic.work.fletcher.respond, button `challenge`
       leaves the player on: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.challenge.stung`: the villager resists. Subject `work.fletcher.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fletcher.challenge.stung/1   [53 chars]
    en  ...Then take a bundle and go stand a watch with them.
    >>  ............................................
    pt  ...Então pega um feixe e vai montar guarda com elas.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.challenge.stung/2   [63 chars]
    en  Sticks and feathers. Right. Tell the guard that when one holds.
    >>  ............................................
    pt  Graveto e pena. Certo. Diga isso ao guarda quando uma segurar.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with the batch."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.fletcher.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.respond.leave   [35 chars]
    en  I'll let you get on with the batch.
    >>  ............................................
    pt  Vou deixar você seguir com o lote.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with the batch."
       spoken on: conversations.topic.work.fletcher.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.left`: the villager accepts. Subject `work.fletcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fletcher.bad_supplier.active.respond / leave; conversations.scene.work.fletcher.bad_supplier.succeeded.respond / leave; conversations.scene.work.fletcher.crooked_batch.blocked.respond / leave; conversations.scene.work.fletcher.crooked_batch.succeeded.respond / leave; conversations.scene.work.fletcher.followup / leave; conversations.scene.work.fletcher.who_buys.active.respond / leave; conversations.scene.work.fletcher.who_buys.succeeded.respond / leave; conversations.topic.work.fletcher.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.fletcher.bad_supplier.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.fletcher.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.fletcher.risk` — e.g. "A rushed batch is the dangerous one. Nobody dies of a slow arrow; they die of a hurried one."


```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.fletcher.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.fletcher.risk.respond   [24 chars]
    en  That's the weight of it.
    >>  ............................................
    pt  É esse o peso.
    >>  ............................................
```


### Button `ask_rushed` — "Who rushes you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fletcher.risk` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fletcher.risk.ask_rushed` — accepted phrasings: "who rushes you"
  - the message must contain one of: `rushes`, `hurry`, `pressure`
  - scored words: `rushes`(1.5), `hurry`(1.5), `pressure`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.risk.respond.ask_rushed
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.risk.respond.ask_rushed   [15 chars]
    en  Who rushes you?
    >>  ............................................
    pt  Quem te apressa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fletcher.risk.ask_rushed`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fletcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you ever get them back?" | "Straight shafts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.risk.ask_rushed
WHO    VILLAGER — what the player reads after pressing "Who rushes you?"
       spoken on: conversations.topic.work.fletcher.risk.respond, button `ask_rushed`
       leaves the player on: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.risk.ask_rushed`: the villager explains. Subject `work.fletcher.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fletcher.risk.ask_rushed/1   [94 chars]
    en  The watch, when there's been a sighting. They're not wrong to. It's still how mistakes happen.
    >>  ............................................
    pt  A guarda, quando tem um avistamento. Não estão errados. Ainda assim é como o erro acontece.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.risk.ask_rushed/2   [71 chars]
    en  Nobody, out loud. It's the sighting itself that does the rushing, %1$s.
    >>  ............................................
    pt  Ninguém, em voz alta. É o próprio avistamento que apressa, %1$s.
    >>  ............................................
```


### Button `sympathise` — "You carry the ones you don't see."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.fletcher.risk` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fletcher.risk.sympathise` — accepted phrasings: "you carry the ones you don't see"
  - the message must contain one of: `carry`, `unseen`, `weight`
  - scored words: `carry`(1.5), `unseen`(1.2), `weight`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.risk.respond.sympathise   [33 chars]
    en  You carry the ones you don't see.
    >>  ............................................
    pt  Você carrega as que não vê.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.fletcher.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.fletcher.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fletcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you ever get them back?" | "Straight shafts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "You carry the ones you don't see."
       spoken on: conversations.topic.work.fletcher.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.risk.sympathise`: the villager accepts. Subject `work.fletcher.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fletcher.risk.sympathise/1   [81 chars]
    en  ...I do. It's a quiet sort of weight and I've never found a place to set it down.
    >>  ............................................
    pt  ...Carrego. É um peso silencioso e eu nunca achei onde largar.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.risk.sympathise/2   [66 chars]
    en  That's it exactly, and I've never had the words for it before now.
    >>  ............................................
    pt  É exatamente isso, e eu nunca tive as palavras antes de agora.
    >>  ............................................
```


### Button `ask_refuse` — "Have you ever refused an order?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fletcher.risk` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fletcher.risk.ask_refuse` — accepted phrasings: "have you ever refused an order"
  - the message must contain one of: `refused`, `order`, `turned`
  - scored words: `refused`(1.5), `order`(1.2), `turned`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.risk.respond.ask_refuse
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.risk.respond.ask_refuse   [31 chars]
    en  Have you ever refused an order?
    >>  ............................................
    pt  Você já recusou uma encomenda?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fletcher.risk.ask_refuse`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fletcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you ever get them back?" | "Straight shafts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.risk.ask_refuse
WHO    VILLAGER — what the player reads after pressing "Have you ever refused an order?"
       spoken on: conversations.topic.work.fletcher.risk.respond, button `ask_refuse`
       leaves the player on: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.risk.ask_refuse`: the villager explains. Subject `work.fletcher.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fletcher.risk.ask_refuse/1   [85 chars]
    en  Once. A traveller wanted forty and wouldn't say for what. He got none and left angry.
    >>  ............................................
    pt  Uma vez. Um viajante queria quarenta e não dizia pra quê. Não levou nenhuma e saiu bravo.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.risk.ask_refuse/2   [80 chars]
    en  I've slowed one down without saying so, which is a coward's version of refusing.
    >>  ............................................
    pt  Já atrasei uma sem dizer, que é a versão covarde de recusar.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with the batch."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.fletcher.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.risk.respond.leave   [35 chars]
    en  I'll let you get on with the batch.
    >>  ............................................
    pt  Vou deixar você seguir com o lote.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with the batch."
       spoken on: conversations.topic.work.fletcher.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.left`: the villager accepts. Subject `work.fletcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fletcher.bad_supplier.active.respond / leave; conversations.scene.work.fletcher.bad_supplier.succeeded.respond / leave; conversations.scene.work.fletcher.crooked_batch.blocked.respond / leave; conversations.scene.work.fletcher.crooked_batch.succeeded.respond / leave; conversations.scene.work.fletcher.followup / leave; conversations.scene.work.fletcher.who_buys.active.respond / leave; conversations.scene.work.fletcher.who_buys.succeeded.respond / leave; conversations.topic.work.fletcher.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.fletcher.bad_supplier.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.fletcher.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.fletcher.task` — e.g. "Sixty for the watch by Thursday. I'm at nineteen and the light's going."


```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.fletcher.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.fletcher.task.respond   [23 chars]
    en  That's the bench today.
    >>  ............................................
    pt  É a bancada hoje.
    >>  ............................................
```


### Button `ask_pace` — "Will sixty happen?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fletcher.task` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fletcher.task.ask_pace` — accepted phrasings: "will sixty happen"
  - the message must contain one of: `sixty`, `happen`, `finish`
  - scored words: `sixty`(1.5), `happen`(1.0), `finish`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.task.respond.ask_pace
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.task.respond.ask_pace   [18 chars]
    en  Will sixty happen?
    >>  ............................................
    pt  As sessenta vão sair?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fletcher.task.ask_pace`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fletcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you ever get them back?" | "Straight shafts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.task.ask_pace
WHO    VILLAGER — what the player reads after pressing "Will sixty happen?"
       spoken on: conversations.topic.work.fletcher.task.respond, button `ask_pace`
       leaves the player on: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.task.ask_pace`: the villager explains. Subject `work.fletcher.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fletcher.task.ask_pace/1   [84 chars]
    en  Sixty will happen. Sixty good ones is a different question and the one I care about.
    >>  ............................................
    pt  Sessenta vão sair. Sessenta boas é outra pergunta e é a que me importa.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.task.ask_pace/2   [60 chars]
    en  It'll happen. I'll be unpleasant company on Wednesday, mind.
    >>  ............................................
    pt  Vão sair. Mas na quarta eu vou ser péssima companhia.
    >>  ............................................
```


### Button `offer_hands` — "I could sort feathers."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.fletcher.task` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fletcher.task.offer_hands` — accepted phrasings: "i could sort feathers"
  - the message must contain one of: `feathers`, `sort`
  - scored words: `feathers`(1.5), `sort`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.task.respond.offer_hands   [22 chars]
    en  I could sort feathers.
    >>  ............................................
    pt  Eu podia separar penas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.fletcher.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.fletcher.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fletcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you ever get them back?" | "Straight shafts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I could sort feathers."
       spoken on: conversations.topic.work.fletcher.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.task.offer_hands`: the villager accepts. Subject `work.fletcher.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fletcher.task.offer_hands/1   [73 chars]
    en  ...You could. Left pile straight, right pile bent, and don't guess — ask.
    >>  ............................................
    pt  ...Podia. Pilha esquerda reta, direita torta, e não chute — pergunte.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.task.offer_hands/2   [80 chars]
    en  Anyone can sort feathers badly. Sit down and I'll show you the difference, %1$s.
    >>  ............................................
    pt  Qualquer um separa pena mal. Senta que eu te mostro a diferença, %1$s.
    >>  ............................................
```


### Button `ask_feathers` — "What's wrong with a bent one?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fletcher.task` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fletcher.task.ask_feathers` — accepted phrasings: "what's wrong with a bent one"
  - the message must contain one of: `bent`, `feather`
  - scored words: `bent`(1.5), `wrong`(0.8), `feather`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.task.respond.ask_feathers
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.task.respond.ask_feathers   [29 chars]
    en  What's wrong with a bent one?
    >>  ............................................
    pt  O que tem de errado com uma torta?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fletcher.task.ask_feathers`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fletcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you ever get them back?" | "Straight shafts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.task.ask_feathers
WHO    VILLAGER — what the player reads after pressing "What's wrong with a bent one?"
       spoken on: conversations.topic.work.fletcher.task.respond, button `ask_feathers`
       leaves the player on: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.task.ask_feathers`: the villager explains. Subject `work.fletcher.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fletcher.task.ask_feathers/1   [69 chars]
    en  It spins. A spinning arrow goes somewhere, just not where you looked.
    >>  ............................................
    pt  Ela gira. Uma flecha que gira vai a algum lugar, só não onde você olhou.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.task.ask_feathers/2   [57 chars]
    en  Nothing, until fifty paces. Then everything, all at once.
    >>  ............................................
    pt  Nada, até cinquenta passos. Aí tudo, de uma vez.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with the batch."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.fletcher.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.task.respond.leave   [35 chars]
    en  I'll let you get on with the batch.
    >>  ............................................
    pt  Vou deixar você seguir com o lote.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with the batch."
       spoken on: conversations.topic.work.fletcher.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.left`: the villager accepts. Subject `work.fletcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fletcher.bad_supplier.active.respond / leave; conversations.scene.work.fletcher.bad_supplier.succeeded.respond / leave; conversations.scene.work.fletcher.crooked_batch.blocked.respond / leave; conversations.scene.work.fletcher.crooked_batch.succeeded.respond / leave; conversations.scene.work.fletcher.followup / leave; conversations.scene.work.fletcher.who_buys.active.respond / leave; conversations.scene.work.fletcher.who_buys.succeeded.respond / leave; conversations.topic.work.fletcher.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.fletcher.bad_supplier.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.fletcher.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.fletcher.village` — e.g. "The watch has never run short. Twelve years. That's the whole of what I've got to show."


```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.fletcher.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.fletcher.village.respond   [25 chars]
    en  That's the account of it.
    >>  ............................................
    pt  É esse o balanço.
    >>  ............................................
```


### Button `ask_archer` — "Does the archer say so?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fletcher.village` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fletcher.village.ask_archer` — accepted phrasings: "does the archer say so"
  - the message must contain one of: `archer`, `thanks`, `acknowledge`
  - scored words: `archer`(1.5), `thanks`(1.0), `acknowledge`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.village.respond.ask_archer
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.village.respond.ask_archer   [23 chars]
    en  Does the archer say so?
    >>  ............................................
    pt  O arqueiro reconhece?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fletcher.village.ask_archer`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fletcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you ever get them back?" | "Straight shafts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.village.ask_archer
WHO    VILLAGER — what the player reads after pressing "Does the archer say so?"
       spoken on: conversations.topic.work.fletcher.village.respond, button `ask_archer`
       leaves the player on: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.village.ask_archer`: the villager explains. Subject `work.fletcher.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fletcher.village.ask_archer/1   [79 chars]
    en  In her way. She brings back the broken ones and tells me what happened to each.
    >>  ............................................
    pt  Do jeito dela. Ela traz as quebradas e me conta o que aconteceu com cada uma.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.village.ask_archer/2   [77 chars]
    en  Never in words. She's stood a shift in the rain to bring me feathers, though.
    >>  ............................................
    pt  Nunca em palavras. Mas já ficou um turno na chuva pra me trazer penas.
    >>  ............................................
```


### Button `say_thanks` — "Twelve years without running short is worth saying."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.fletcher.village` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fletcher.village.say_thanks` — accepted phrasings: "twelve years without running short is worth saying"
  - the message must contain one of: `twelve`, `short`, `record`
  - scored words: `twelve`(1.5), `short`(1.2), `record`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.village.respond.say_thanks   [51 chars]
    en  Twelve years without running short is worth saying.
    >>  ............................................
    pt  Doze anos sem faltar vale ser dito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.fletcher.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.fletcher.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fletcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you ever get them back?" | "Straight shafts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Twelve years without running short is worth saying."
       spoken on: conversations.topic.work.fletcher.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.village.say_thanks`: the villager accepts. Subject `work.fletcher.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fletcher.village.say_thanks/1   [75 chars]
    en  ...It is. I keep the count privately and I'd not have said it aloud myself.
    >>  ............................................
    pt  ...Vale. Eu guardo a conta em silêncio e não teria dito em voz alta.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.village.say_thanks/2   [81 chars]
    en  Then say it where the mayor hears and I'll have a quiet week of being smug, %1$s.
    >>  ............................................
    pt  Então diga onde o prefeito ouça e eu terei uma semana tranquila de me achar, %1$s.
    >>  ............................................
```


### Button `ask_stopped` — "What if you did run short?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fletcher.village` · offered only once the villager has actually said `work:fletcher`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fletcher.village.ask_stopped` — accepted phrasings: "what if you did run short"
  - the message must contain one of: `short`, `ran`, `failed`
  - scored words: `short`(1.2), `ran`(1.2), `failed`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.village.respond.ask_stopped
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.village.respond.ask_stopped   [26 chars]
    en  What if you did run short?
    >>  ............................................
    pt  E se você ficasse sem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fletcher.village.ask_stopped`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fletcher.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Do you ever get them back?" | "Straight shafts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.village.ask_stopped
WHO    VILLAGER — what the player reads after pressing "What if you did run short?"
       spoken on: conversations.topic.work.fletcher.village.respond, button `ask_stopped`
       leaves the player on: conversations.topic.work.fletcher.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.village.ask_stopped`: the villager explains. Subject `work.fletcher.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fletcher` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fletcher.village.ask_stopped/1   [77 chars]
    en  Then the watch carries spears and hopes. It has happened elsewhere. Not here.
    >>  ............................................
    pt  Aí a guarda carrega lança e esperança. Já aconteceu em outros lugares. Aqui não.
    >>  ............................................
  dialogue.conversations.work.prof.fletcher.village.ask_stopped/2   [84 chars]
    en  That's a question I answer at three in the morning sometimes, and never in daylight.
    >>  ............................................
    pt  É uma pergunta que eu respondo às três da manhã, às vezes, e nunca de dia.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with the batch."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.fletcher.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.fletcher.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fletcher.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fletcher.village.respond.leave   [35 chars]
    en  I'll let you get on with the batch.
    >>  ............................................
    pt  Vou deixar você seguir com o lote.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fletcher.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with the batch."
       spoken on: conversations.topic.work.fletcher.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fletcher.left`: the villager accepts. Subject `work.fletcher.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fletcher.bad_supplier.active.respond / leave; conversations.scene.work.fletcher.bad_supplier.succeeded.respond / leave; conversations.scene.work.fletcher.crooked_batch.blocked.respond / leave; conversations.scene.work.fletcher.crooked_batch.succeeded.respond / leave; conversations.scene.work.fletcher.followup / leave; conversations.scene.work.fletcher.who_buys.active.respond / leave; conversations.scene.work.fletcher.who_buys.succeeded.respond / leave; conversations.topic.work.fletcher.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.fletcher.bad_supplier.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

