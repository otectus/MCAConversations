# Work talk with a guard

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.guard.failing_kit.active.respond`](#conversations-scene-work-guard-failing-kit-active-respond)
- [`conversations.scene.work.guard.failing_kit.succeeded.respond`](#conversations-scene-work-guard-failing-kit-succeeded-respond)
- [`conversations.scene.work.guard.followup`](#conversations-scene-work-guard-followup)
- [`conversations.scene.work.guard.night_sighting.blocked.respond`](#conversations-scene-work-guard-night-sighting-blocked-respond)
- [`conversations.scene.work.guard.night_sighting.succeeded.respond`](#conversations-scene-work-guard-night-sighting-succeeded-respond)
- [`conversations.scene.work.guard.weak_point.blocked.respond`](#conversations-scene-work-guard-weak-point-blocked-respond)
- [`conversations.scene.work.guard.weak_point.failed.respond`](#conversations-scene-work-guard-weak-point-failed-respond)
- [`conversations.scene.work.guard.weak_point.succeeded.respond`](#conversations-scene-work-guard-weak-point-succeeded-respond)
- [`conversations.topic.work.guard.craft.respond`](#conversations-topic-work-guard-craft-respond)
- [`conversations.topic.work.guard.followup`](#conversations-topic-work-guard-followup)
- [`conversations.topic.work.guard.future.respond`](#conversations-topic-work-guard-future-respond)
- [`conversations.topic.work.guard.respond`](#conversations-topic-work-guard-respond)
- [`conversations.topic.work.guard.risk.respond`](#conversations-topic-work-guard-risk-respond)
- [`conversations.topic.work.guard.task.respond`](#conversations-topic-work-guard-task-respond)
- [`conversations.topic.work.guard.village.respond`](#conversations-topic-work-guard-village-respond)

---

## `conversations.scene.work.guard.failing_kit.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.guard.failing_kit.active` — e.g. "%2$s is going. Not gone. Going, in the way that means I will find out exactly when at the least convenient possible moment."


```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.failing_kit.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.guard.failing_kit.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.guard.failing_kit.active.respond   [14 chars]
    en  About the kit.
    >>  ............................................
    pt  Sobre o equipamento.
    >>  ............................................
```


### Button `urge_replace` — "Replace it. Don't wait for it to fail."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.guard.failing_kit.active` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.guard.failing_kit.active.urge_replace` — accepted phrasings: "replace it dont wait for it to fail"; "get a new one now"; "replace it before it breaks"
  - the message must contain one of: `replace`, `new`
  - scored words: `replace`(1.8), `new`(1.8), `dont`(0.8), `wait`(0.8), `fail`(0.8), `get`(0.8), `now`(0.8), `before`(0.8), `breaks`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.failing_kit.active.respond.urge_replace
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.failing_kit.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.failing_kit.active.respond.urge_replace   [38 chars]
    en  Replace it. Don't wait for it to fail.
    >>  ............................................
    pt  Troque. Não espere falhar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2, familiarity +1  _(recorded under topic `work.guard.equipment`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.failing_kit", "state": "succeeded"}
- Does: `conversations_thread` = {"op": "open", "template": "work.guard.failing_kit"}
- Then opens: `conversations.scene.work.guard.followup`
- …where the player's next choices will be: "What's the hardest part of the watch?" | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.failing_kit.active.conceded
WHO    VILLAGER — what the player reads after pressing "Replace it. Don't wait for it to fail."
       spoken on: conversations.scene.work.guard.failing_kit.active.respond, button `urge_replace`
       leaves the player on: conversations.scene.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.failing_kit.active.conceded`: the villager accepts. Subject `work.guard.equipment`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.failing_kit.active.conceded/1   [121 chars]
    en  You are right and I have been treating %2$s as a test of character instead of a piece of equipment. I will see the smith.
    >>  ............................................
    pt  Você tem razão, e eu venho tratando %2$s como teste de caráter em vez de peça de equipamento. Vou procurar o ferreiro.
    >>  ............................................
  dialogue.conversations.scene.work.guard.failing_kit.active.conceded/2   [114 chars]
    en  The reason I have not is that asking means admitting I let it get this far. Said out loud that is a stupid reason.
    >>  ............................................
    pt  O motivo de eu não ter feito é que pedir significa admitir que deixei chegar a esse ponto. Dito em voz alta, é um motivo idiota.
    >>  ............................................
  dialogue.conversations.scene.work.guard.failing_kit.active.conceded/3   [122 chars]
    en  Fine. Tomorrow. And I will ask for the good one rather than the cheap one, which is the harder half of what you just said.
    >>  ............................................
    pt  Está bem. Amanhã. E vou pedir a boa em vez da barata, que é a metade mais difícil do que você acabou de dizer.
    >>  ............................................
```


### Button `ask_why_wait` — "What's stopping you asking for a new one?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.guard.failing_kit.active` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.guard.failing_kit.active.ask_why_wait` — accepted phrasings: "whats stopping you asking for a new one"; "what is stopping you asking for another"; "what holds you back from asking"
  - the message must contain one of: `stopping`, `holds`, `asking`
  - scored words: `stopping`(1.8), `holds`(1.8), `asking`(1.8), `whats`(0.8), `another`(0.8), `back`(0.8), `from`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.failing_kit.active.respond.ask_why_wait
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.failing_kit.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.failing_kit.active.respond.ask_why_wait   [41 chars]
    en  What's stopping you asking for a new one?
    >>  ............................................
    pt  O que impede você de pedir uma nova?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, trust +1  _(recorded under topic `work.guard.equipment`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.guard.failing_kit"}
- Then opens: `conversations.scene.work.guard.followup`
- …where the player's next choices will be: "What's the hardest part of the watch?" | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.failing_kit.active.admitted
WHO    VILLAGER — what the player reads after pressing "What's stopping you asking for a new one?"
       spoken on: conversations.scene.work.guard.failing_kit.active.respond, button `ask_why_wait`
       leaves the player on: conversations.scene.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.failing_kit.active.admitted`: the villager explains. Subject `work.guard.equipment`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.failing_kit.active.admitted/1   [131 chars]
    en  Because the last three things I asked for took a month each, and I have decided to spend my asking on the wall rather than on %2$s.
    >>  ............................................
    pt  Porque as três últimas coisas que pedi levaram um mês cada, e resolvi gastar os meus pedidos com a muralha em vez de com %2$s.
    >>  ............................................
  dialogue.conversations.scene.work.guard.failing_kit.active.admitted/2   [111 chars]
    en  Pride, mostly, dressed up as thrift. It is somebody else's coin and I keep behaving as though it were a favour.
    >>  ............................................
    pt  Orgulho, principalmente, vestido de economia. É dinheiro dos outros e eu insisto em agir como se fosse favor.
    >>  ............................................
  dialogue.conversations.scene.work.guard.failing_kit.active.admitted/3   [133 chars]
    en  %2$s was my father's. That is the whole reason and it is not a good one, and I would rather you had it than a better-sounding answer.
    >>  ............................................
    pt  %2$s era do meu pai. É o motivo inteiro e não é um bom motivo, e prefiro que você tenha ele a ter uma resposta mais bonita.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the wall."

*stance family `exit` · tone `plain` · answers the beat(s) `work.guard.failing_kit.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.failing_kit.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.failing_kit.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.failing_kit.active.respond.leave   [34 chars]
    en  I'll let you get back to the wall.
    >>  ............................................
    pt  Vou deixar você voltar para a muralha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the wall."
       spoken on: conversations.scene.work.guard.failing_kit.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.left`: the villager accepts. Subject `work.guard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.guard.failing_kit.succeeded.respond / leave; conversations.scene.work.guard.followup / leave; conversations.scene.work.guard.night_sighting.blocked.respond / leave; conversations.scene.work.guard.night_sighting.succeeded.respond / leave; conversations.scene.work.guard.weak_point.blocked.respond / leave; conversations.scene.work.guard.weak_point.failed.respond / leave; conversations.scene.work.guard.weak_point.succeeded.respond / leave; conversations.topic.work.guard.craft.respond / leave …and 6 more
```

```text
  dialogue.conversations.work.prof.guard.leave/1   [20 chars]
    en  Aye. Someone has to.
    >>  ............................................
    pt  É. Alguém tem que ficar.
    >>  ............................................
  dialogue.conversations.work.prof.guard.leave/2   [48 chars]
    en  Off you go, %1$s. Mind the east gate, it sticks.
    >>  ............................................
    pt  Pode ir, %1$s. Cuidado com o portão leste, ele emperra.
    >>  ............................................
```

---


## `conversations.scene.work.guard.failing_kit.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.guard.failing_kit.succeeded` — e.g. "%2$s is replaced. It does not fit right yet and it is already better than the thing it replaced, which tells you something."


```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.failing_kit.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.guard.failing_kit.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.guard.failing_kit.succeeded.respond   [19 chars]
    en  You got it seen to.
    >>  ............................................
    pt  Você resolveu.
    >>  ............................................
```


### Button `tease` — "Six weeks, and it took four sentences."

*stance family `humor` · tone `playful` · outcome `appreciated` · answers the beat(s) `work.guard.failing_kit.succeeded` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.guard.failing_kit.succeeded.tease` — accepted phrasings: "six weeks and it took four sentences"; "six weeks for four sentences"; "all that for one short conversation"
  - the message must contain one of: `weeks`, `sentences`, `conversation`
  - scored words: `weeks`(1.8), `sentences`(1.8), `conversation`(1.8), `six`(0.8), `took`(0.8), `four`(0.8), `all`(0.8), `short`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.failing_kit.succeeded.respond.tease
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.failing_kit.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.failing_kit.succeeded.respond.tease   [38 chars]
    en  Six weeks, and it took four sentences.
    >>  ............................................
    pt  Seis semanas, e bastaram quatro frases.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2  _(recorded under topic `work.guard.equipment`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.guard.failing_kit"}
- Then opens: `conversations.scene.work.guard.followup`
- …where the player's next choices will be: "What's the hardest part of the watch?" | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.failing_kit.succeeded.wry
WHO    VILLAGER — what the player reads after pressing "Six weeks, and it took four sentences."
       spoken on: conversations.scene.work.guard.failing_kit.succeeded.respond, button `tease`
       leaves the player on: conversations.scene.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.failing_kit.succeeded.wry`: the villager accepts. Subject `work.guard.equipment`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.failing_kit.succeeded.wry/1   [88 chars]
    en  Do not. I have already had this conversation with myself on the walk back and I lost it.
    >>  ............................................
    pt  Não. Já tive essa conversa comigo mesma na volta e perdi.
    >>  ............................................
  dialogue.conversations.scene.work.guard.failing_kit.succeeded.wry/2   [104 chars]
    en  Four sentences and one of them was 'yes'. I am going to be thinking about that at odd hours for a while.
    >>  ............................................
    pt  Quatro frases, e uma delas foi 'sim'. Vou ficar pensando nisso em horas estranhas por um tempo.
    >>  ............................................
  dialogue.conversations.scene.work.guard.failing_kit.succeeded.wry/3   [120 chars]
    en  I know. Put it in the book. 'Guard delays six weeks, is fine, feels foolish.' It will be good for whoever reads it next.
    >>  ............................................
    pt  Eu sei. Anote no livro. 'Guarda adia seis semanas, dá tudo certo, sente-se boba.' Vai ser útil para quem ler depois.
    >>  ............................................
```


### Button `ask_fit` — "Does the new one suit you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.guard.failing_kit.succeeded` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.guard.failing_kit.succeeded.ask_fit` — accepted phrasings: "does the new one suit you"; "how does the new one feel"; "does it fit properly"
  - the message must contain one of: `suit`, `fit`, `feel`
  - scored words: `suit`(1.8), `fit`(1.8), `feel`(1.8), `does`(0.8), `new`(0.8), `properly`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.failing_kit.succeeded.respond.ask_fit
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.failing_kit.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.failing_kit.succeeded.respond.ask_fit   [26 chars]
    en  Does the new one suit you?
    >>  ............................................
    pt  A nova serve bem em você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.guard.equipment`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.guard.failing_kit"}
- Then opens: `conversations.scene.work.guard.followup`
- …where the player's next choices will be: "What's the hardest part of the watch?" | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.failing_kit.succeeded.assessed
WHO    VILLAGER — what the player reads after pressing "Does the new one suit you?"
       spoken on: conversations.scene.work.guard.failing_kit.succeeded.respond, button `ask_fit`
       leaves the player on: conversations.scene.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.failing_kit.succeeded.assessed`: the villager explains. Subject `work.guard.equipment`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.failing_kit.succeeded.assessed/1   [114 chars]
    en  Not yet. Everything new sits wrong for a fortnight and then one morning you stop noticing it. %2$s will get there.
    >>  ............................................
    pt  Ainda não. Tudo novo assenta errado por quinze dias e aí, numa manhã, você para de reparar. %2$s vai chegar lá.
    >>  ............................................
  dialogue.conversations.scene.work.guard.failing_kit.succeeded.assessed/2   [124 chars]
    en  It is heavier and I am glad of it. I had got used to something too light to do its job and started calling that comfortable.
    >>  ............................................
    pt  É mais pesada e ainda bem. Eu tinha me acostumado com algo leve demais para o serviço e comecei a chamar isso de confortável.
    >>  ............................................
  dialogue.conversations.scene.work.guard.failing_kit.succeeded.assessed/3   [110 chars]
    en  Well enough that I stopped adjusting it halfway through the watch, which is the only test that means anything.
    >>  ............................................
    pt  Bem o bastante para eu parar de ajustar no meio da ronda, que é o único teste que significa alguma coisa.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the wall."

*stance family `exit` · tone `plain` · answers the beat(s) `work.guard.failing_kit.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.failing_kit.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.failing_kit.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.failing_kit.succeeded.respond.leave   [34 chars]
    en  I'll let you get back to the wall.
    >>  ............................................
    pt  Vou deixar você voltar para a muralha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the wall."
       spoken on: conversations.scene.work.guard.failing_kit.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.left`: the villager accepts. Subject `work.guard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.guard.failing_kit.active.respond / leave; conversations.scene.work.guard.followup / leave; conversations.scene.work.guard.night_sighting.blocked.respond / leave; conversations.scene.work.guard.night_sighting.succeeded.respond / leave; conversations.scene.work.guard.weak_point.blocked.respond / leave; conversations.scene.work.guard.weak_point.failed.respond / leave; conversations.scene.work.guard.weak_point.succeeded.respond / leave; conversations.topic.work.guard.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.guard.failing_kit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.guard.followup`

**Reached from 15 route(s):** `conversations.scene.work.guard.failing_kit.active.respond` / `urge_replace`; `conversations.scene.work.guard.failing_kit.active.respond` / `ask_why_wait`; `conversations.scene.work.guard.failing_kit.succeeded.respond` / `tease`; `conversations.scene.work.guard.failing_kit.succeeded.respond` / `ask_fit`; `conversations.scene.work.guard.night_sighting.blocked.respond` / `ask_certainty`; `conversations.scene.work.guard.night_sighting.blocked.respond` / `offer_to_watch`; `conversations.scene.work.guard.night_sighting.succeeded.respond` / `praise_care`; `conversations.scene.work.guard.night_sighting.succeeded.respond` / `ask_rumour`; `conversations.scene.work.guard.weak_point.blocked.respond` / `ask_consequence`; `conversations.scene.work.guard.weak_point.blocked.respond` / `offer_timber`; `conversations.scene.work.guard.weak_point.blocked.respond` / `advise_escalate`; `conversations.scene.work.guard.weak_point.failed.respond` / `sit_with_it` …and 3 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.guard.failing_kit.active.admitted` — e.g. "Because the last three things I asked for took a month each, and I have decided to spend my asking on the wall rather than on %2$s."
- `conversations.scene.work.guard.failing_kit.active.conceded` — e.g. "You are right and I have been treating %2$s as a test of character instead of a piece of equipment. I will see the smith."
- `conversations.scene.work.guard.failing_kit.succeeded.assessed` — e.g. "Not yet. Everything new sits wrong for a fortnight and then one morning you stop noticing it. %2$s will get there."
- `conversations.scene.work.guard.failing_kit.succeeded.wry` — e.g. "Do not. I have already had this conversation with myself on the walk back and I lost it."
- `conversations.scene.work.guard.night_sighting.blocked.accepted` — e.g. "Then we will both look and there will be two accounts instead of one, which is worth more than anything I could add alone."
- `conversations.scene.work.guard.night_sighting.blocked.hedged` — e.g. "Sure that I saw %2$s. Not sure what it means, and the two get run together the moment I stop saying them separately."
- `conversations.scene.work.guard.night_sighting.succeeded.acknowledged` — e.g. "It is the only part of this I am actually proud of. Anyone can raise an alarm. Not raising one badly takes some doing."
- `conversations.scene.work.guard.night_sighting.succeeded.recounted` — e.g. "By the second morning %2$s had become three of something with eyes. By the third it had a direction and a purpose. Nobody lied. It just travelled."
- `conversations.scene.work.guard.weak_point.blocked.accepted` — e.g. "Then it gets done, and it gets done this week, and I will stop being the person who mentions %2$s at every meeting."
- `conversations.scene.work.guard.weak_point.blocked.explained` — e.g. "Nothing, most nights. That is the difficulty. It costs nothing for a hundred nights and everything on the hundred and first, and I cannot show anybody the hundred and first."
- `conversations.scene.work.guard.weak_point.blocked.resisted` — e.g. "And be the guard who cries wolf about %2$s. I get one of those in a career and I would rather spend it on something worse than this."
- `conversations.scene.work.guard.weak_point.failed.coping` — e.g. "Badly. I loop back past %2$s twice a night, which means the far side gets less of me. You cannot cover a hole; you can only choose which hole."
- `conversations.scene.work.guard.weak_point.failed.received` — e.g. "That is true and it will not help me at all on the night it matters, but say it again anyway. It helps now."
- `conversations.scene.work.guard.weak_point.succeeded.credited` — e.g. "I kept asking and I was very close to stopping. A fortnight more and %2$s would still be open, and I would have called that patience."
- …and 1 more pools


```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.guard.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.guard.followup   [14 chars]
    en  Anything else?
    >>  ............................................
    pt  Mais alguma coisa?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of the watch?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.guard.*` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.guard.followup.ask_more` — accepted phrasings: "whats the hardest part of the watch"; "what is the worst part of standing watch"; "hardest thing about the job"
  - the message must contain one of: `hardest`, `watch`
  - scored words: `hardest`(1.8), `watch`(1.8), `whats`(0.8), `part`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.followup.ask_more   [37 chars]
    en  What's the hardest part of the watch?
    >>  ............................................
    pt  Qual é a parte mais difícil da ronda?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.guard.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.guard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where's the village weakest?" | "Quiet shifts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of the watch?"
       spoken on: conversations.scene.work.guard.followup, button `ask_more`
       leaves the player on: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.hard`: the villager explains. Subject `work.guard.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.guard.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.guard.hard/1   [79 chars]
    en  The fourth hour. Nothing has happened and your mind starts inventing something.
    >>  ............................................
    pt  A quarta hora. Nada aconteceu e sua cabeça começa a inventar alguma coisa.
    >>  ............................................
  dialogue.conversations.work.prof.guard.hard/2   [81 chars]
    en  Staying sharp for nothing, %1$s. Boredom is the thing that actually kills guards.
    >>  ............................................
    pt  Ficar alerta por nada, %1$s. Tédio é o que realmente mata guarda.
    >>  ............................................
```


### Button `leave` — "I'll let you get on with it."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.guard.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.followup.leave   [28 chars]
    en  I'll let you get on with it.
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
POOL   dialogue key: dialogue.conversations.work.prof.guard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on with it."
       spoken on: conversations.scene.work.guard.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.left`: the villager accepts. Subject `work.guard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.guard.failing_kit.active.respond / leave; conversations.scene.work.guard.failing_kit.succeeded.respond / leave; conversations.scene.work.guard.night_sighting.blocked.respond / leave; conversations.scene.work.guard.night_sighting.succeeded.respond / leave; conversations.scene.work.guard.weak_point.blocked.respond / leave; conversations.scene.work.guard.weak_point.failed.respond / leave; conversations.scene.work.guard.weak_point.succeeded.respond / leave; conversations.topic.work.guard.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.guard.failing_kit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.guard.night_sighting.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.guard.night_sighting.blocked` — e.g. "%2$s, past %3$s, two nights running. I am not going to tell you what it was, because I do not know what it was."


```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.night_sighting.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.guard.night_sighting.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.guard.night_sighting.blocked.respond   [16 chars]
    en  The night watch.
    >>  ............................................
    pt  A ronda noturna.
    >>  ............................................
```


### Button `ask_certainty` — "How sure are you about what you saw?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.guard.night_sighting.blocked` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.guard.night_sighting.blocked.ask_certainty` — accepted phrasings: "how sure are you about what you saw"; "how certain are you"; "could you have been mistaken"
  - the message must contain one of: `sure`, `certain`, `mistaken`
  - scored words: `sure`(1.8), `certain`(1.8), `mistaken`(1.8), `saw`(0.8), `been`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.night_sighting.blocked.respond.ask_certainty
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.night_sighting.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.night_sighting.blocked.respond.ask_certainty   [36 chars]
    en  How sure are you about what you saw?
    >>  ............................................
    pt  Quão certa você está do que viu?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, familiarity +1  _(recorded under topic `work.guard.recent_threat`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.guard.night_sighting"}
- Then opens: `conversations.scene.work.guard.followup`
- …where the player's next choices will be: "What's the hardest part of the watch?" | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.night_sighting.blocked.hedged
WHO    VILLAGER — what the player reads after pressing "How sure are you about what you saw?"
       spoken on: conversations.scene.work.guard.night_sighting.blocked.respond, button `ask_certainty`
       leaves the player on: conversations.scene.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.night_sighting.blocked.hedged`: the villager qualifys. Subject `work.guard.recent_threat`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.night_sighting.blocked.hedged/1   [116 chars]
    en  Sure that I saw %2$s. Not sure what it means, and the two get run together the moment I stop saying them separately.
    >>  ............................................
    pt  Certa de que vi %2$s. Nada certa do que significa, e as duas coisas se misturam no instante em que eu paro de dizê-las separadas.
    >>  ............................................
  dialogue.conversations.scene.work.guard.night_sighting.blocked.hedged/2   [124 chars]
    en  Certain of the fact, uncertain of everything past it. That is an uncomfortable place to stand and it is the only honest one.
    >>  ............................................
    pt  Certa do fato, incerta de tudo depois dele. É um lugar desconfortável de se ficar e é o único honesto.
    >>  ............................................
  dialogue.conversations.scene.work.guard.night_sighting.blocked.hedged/3   [121 chars]
    en  I have been wrong before at that hour. I have also been right before at that hour, and only one of those gets remembered.
    >>  ............................................
    pt  Já errei nessa hora antes. Também já acertei nessa hora antes, e só uma dessas fica na memória.
    >>  ............................................
```


### Button `offer_to_watch` — "I'll walk it with you tonight."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.guard.night_sighting.blocked` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.guard.night_sighting.blocked.offer_to_watch` — accepted phrasings: "ill walk it with you tonight"; "let me walk the watch with you"; "ill come out with you tonight"
  - the message must contain one of: `walk`, `watch`, `tonight`
  - scored words: `walk`(1.8), `watch`(1.8), `tonight`(1.8), `ill`(0.8), `let`(0.8), `come`(0.8), `out`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.night_sighting.blocked.respond.offer_to_watch
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.night_sighting.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.night_sighting.blocked.respond.offer_to_watch   [30 chars]
    en  I'll walk it with you tonight.
    >>  ............................................
    pt  Faço a ronda com você hoje à noite.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.guard.night_sighting.company`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.guard.recent_threat`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.night_sighting", "state": "succeeded"}
- Does: `conversations_thread` = {"op": "open", "template": "work.guard.night_sighting"}
- Then opens: `conversations.scene.work.guard.followup`
- …where the player's next choices will be: "What's the hardest part of the watch?" | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.night_sighting.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll walk it with you tonight."
       spoken on: conversations.scene.work.guard.night_sighting.blocked.respond, button `offer_to_watch`
       leaves the player on: conversations.scene.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.night_sighting.blocked.accepted`: the villager accepts. Subject `work.guard.recent_threat`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.night_sighting.blocked.accepted/1   [122 chars]
    en  Then we will both look and there will be two accounts instead of one, which is worth more than anything I could add alone.
    >>  ............................................
    pt  Então nós dois olhamos e haverá dois relatos em vez de um, o que vale mais do que qualquer coisa que eu acrescentasse sozinha.
    >>  ............................................
  dialogue.conversations.scene.work.guard.night_sighting.blocked.accepted/2   [122 chars]
    en  Come at full dark and stand where I put you, and do not talk. If %2$s has anything to show us it shows it to quiet people.
    >>  ............................................
    pt  Venha na escuridão total, fique onde eu colocar e não fale. Se %2$s tiver algo a mostrar, mostra a gente calada.
    >>  ............................................
  dialogue.conversations.scene.work.guard.night_sighting.blocked.accepted/3   [135 chars]
    en  Yes. And if we see nothing, I will say we saw nothing, and I will be glad rather than disappointed. I want you to know that in advance.
    >>  ............................................
    pt  Sim. E se não virmos nada, direi que não vimos nada, e ficarei contente em vez de decepcionada. Quero que você saiba disso desde já.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the wall."

*stance family `exit` · tone `plain` · answers the beat(s) `work.guard.night_sighting.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.night_sighting.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.night_sighting.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.night_sighting.blocked.respond.leave   [34 chars]
    en  I'll let you get back to the wall.
    >>  ............................................
    pt  Vou deixar você voltar para a muralha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the wall."
       spoken on: conversations.scene.work.guard.night_sighting.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.left`: the villager accepts. Subject `work.guard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.guard.failing_kit.active.respond / leave; conversations.scene.work.guard.failing_kit.succeeded.respond / leave; conversations.scene.work.guard.followup / leave; conversations.scene.work.guard.night_sighting.succeeded.respond / leave; conversations.scene.work.guard.weak_point.blocked.respond / leave; conversations.scene.work.guard.weak_point.failed.respond / leave; conversations.scene.work.guard.weak_point.succeeded.respond / leave; conversations.topic.work.guard.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.guard.failing_kit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.guard.night_sighting.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.guard.night_sighting.succeeded` — e.g. "%2$s was a stray dog and a badly hung shutter, in that order. I have never been so pleased to have wasted three nights."


```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.night_sighting.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.guard.night_sighting.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.guard.night_sighting.succeeded.respond   [18 chars]
    en  So it was nothing.
    >>  ............................................
    pt  Então não era nada.
    >>  ............................................
```


### Button `praise_care` — "You were careful about it the whole way through."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.guard.night_sighting.succeeded` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.guard.night_sighting.succeeded.praise_care` — accepted phrasings: "you were careful about it the whole way through"; "you were careful with that"; "you stayed measured about it"
  - the message must contain one of: `careful`, `measured`
  - scored words: `careful`(1.8), `measured`(1.8), `whole`(0.8), `way`(0.8), `through`(0.8), `stayed`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.night_sighting.succeeded.respond.praise_care
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.night_sighting.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.night_sighting.succeeded.respond.praise_care   [48 chars]
    en  You were careful about it the whole way through.
    >>  ............................................
    pt  Você teve cuidado com isso do começo ao fim.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.guard.night_sighting.praise`, budget `standard`, replay policy `once`
- Does: disposition — respect +4  _(recorded under topic `work.guard.recent_threat`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.guard.night_sighting"}
- Then opens: `conversations.scene.work.guard.followup`
- …where the player's next choices will be: "What's the hardest part of the watch?" | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.night_sighting.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "You were careful about it the whole way through."
       spoken on: conversations.scene.work.guard.night_sighting.succeeded.respond, button `praise_care`
       leaves the player on: conversations.scene.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.night_sighting.succeeded.acknowledged`: the villager accepts. Subject `work.guard.recent_threat`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.night_sighting.succeeded.acknowledged/1   [118 chars]
    en  It is the only part of this I am actually proud of. Anyone can raise an alarm. Not raising one badly takes some doing.
    >>  ............................................
    pt  É a única parte disso de que eu realmente me orgulho. Qualquer um dá um alarme. Não dar um alarme malfeito custa trabalho.
    >>  ............................................
  dialogue.conversations.scene.work.guard.night_sighting.succeeded.acknowledged/2   [114 chars]
    en  I nearly was not. Second night I very nearly said the word everyone was waiting for, and then I went home instead.
    >>  ............................................
    pt  Quase não tive. Na segunda noite quase disse a palavra que todo mundo esperava, e então fui para casa em vez disso.
    >>  ............................................
  dialogue.conversations.scene.work.guard.night_sighting.succeeded.acknowledged/3   [105 chars]
    en  Thank you. That is the thing I would want written down, and it is never the thing that gets written down.
    >>  ............................................
    pt  Obrigada. É o que eu gostaria que ficasse escrito, e nunca é o que fica escrito.
    >>  ............................................
```


### Button `ask_rumour` — "Did the story get away from you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.guard.night_sighting.succeeded` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.guard.night_sighting.succeeded.ask_rumour` — accepted phrasings: "did the story get away from you"; "did the rumour spread"; "what were people saying"
  - the message must contain one of: `story`, `rumour`, `saying`
  - scored words: `story`(1.8), `rumour`(1.8), `saying`(1.8), `get`(0.8), `away`(0.8), `from`(0.8), `spread`(0.8), `people`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.night_sighting.succeeded.respond.ask_rumour
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.night_sighting.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.night_sighting.succeeded.respond.ask_rumour   [32 chars]
    en  Did the story get away from you?
    >>  ............................................
    pt  A história fugiu do seu controle?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.guard.recent_threat`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.guard.night_sighting"}
- Then opens: `conversations.scene.work.guard.followup`
- …where the player's next choices will be: "What's the hardest part of the watch?" | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.night_sighting.succeeded.recounted
WHO    VILLAGER — what the player reads after pressing "Did the story get away from you?"
       spoken on: conversations.scene.work.guard.night_sighting.succeeded.respond, button `ask_rumour`
       leaves the player on: conversations.scene.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.night_sighting.succeeded.recounted`: the villager explains. Subject `work.guard.recent_threat`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.night_sighting.succeeded.recounted/1   [146 chars]
    en  By the second morning %2$s had become three of something with eyes. By the third it had a direction and a purpose. Nobody lied. It just travelled.
    >>  ............................................
    pt  Na segunda manhã, %2$s já eram três de alguma coisa com olhos. Na terceira, tinha direção e propósito. Ninguém mentiu. Só viajou.
    >>  ............................................
  dialogue.conversations.scene.work.guard.night_sighting.succeeded.recounted/2   [109 chars]
    en  A little. The trick is to say the boring version loudly and often, and to say it to the people who talk most.
    >>  ............................................
    pt  Um pouco. O truque é dizer a versão sem graça bem alto e muitas vezes, e dizer para quem mais fala.
    >>  ............................................
  dialogue.conversations.scene.work.guard.night_sighting.succeeded.recounted/3   [139 chars]
    en  It did, and I let it for half a day because I wanted to see where it went. That was not the responsible choice and it was very instructive.
    >>  ............................................
    pt  Fugiu, e eu deixei por meio dia porque quis ver onde ia parar. Não foi a escolha responsável e foi muito instrutivo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the wall."

*stance family `exit` · tone `plain` · answers the beat(s) `work.guard.night_sighting.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.night_sighting.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.night_sighting.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.night_sighting.succeeded.respond.leave   [34 chars]
    en  I'll let you get back to the wall.
    >>  ............................................
    pt  Vou deixar você voltar para a muralha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the wall."
       spoken on: conversations.scene.work.guard.night_sighting.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.left`: the villager accepts. Subject `work.guard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.guard.failing_kit.active.respond / leave; conversations.scene.work.guard.failing_kit.succeeded.respond / leave; conversations.scene.work.guard.followup / leave; conversations.scene.work.guard.night_sighting.blocked.respond / leave; conversations.scene.work.guard.weak_point.blocked.respond / leave; conversations.scene.work.guard.weak_point.failed.respond / leave; conversations.scene.work.guard.weak_point.succeeded.respond / leave; conversations.topic.work.guard.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.guard.failing_kit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.guard.weak_point.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.guard.weak_point.blocked` — e.g. "There is %3$s at %2$s. I have reported it twice and been told twice that it is on the list."


```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.guard.weak_point.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.guard.weak_point.blocked.respond   [9 chars]
    en  The wall.
    >>  ............................................
    pt  A muralha.
    >>  ............................................
```


### Button `ask_consequence` — "What happens if nobody fixes it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.guard.weak_point.blocked` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.guard.weak_point.blocked.ask_consequence` — accepted phrasings: "what happens if nobody fixes it"; "what is the worst case there"; "what could get through"
  - the message must contain one of: `happens`, `worst`, `through`
  - scored words: `happens`(1.8), `worst`(1.8), `through`(1.8), `nobody`(0.8), `fixes`(0.8), `case`(0.8), `get`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.blocked.respond.ask_consequence
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.weak_point.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.weak_point.blocked.respond.ask_consequence   [32 chars]
    en  What happens if nobody fixes it?
    >>  ............................................
    pt  O que acontece se ninguém consertar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.guard.weak_points`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.guard.weak_point"}
- Then opens: `conversations.scene.work.guard.followup`
- …where the player's next choices will be: "What's the hardest part of the watch?" | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What happens if nobody fixes it?"
       spoken on: conversations.scene.work.guard.weak_point.blocked.respond, button `ask_consequence`
       leaves the player on: conversations.scene.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.weak_point.blocked.explained`: the villager explains. Subject `work.guard.weak_points`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.weak_point.blocked.explained/1   [173 chars]
    en  Nothing, most nights. That is the difficulty. It costs nothing for a hundred nights and everything on the hundred and first, and I cannot show anybody the hundred and first.
    >>  ............................................
    pt  Nada, na maioria das noites. É essa a dificuldade. Não custa nada por cem noites e custa tudo na centésima primeira, e eu não consigo mostrar a centésima primeira a ninguém.
    >>  ............................................
  dialogue.conversations.scene.work.guard.weak_point.blocked.explained/2   [140 chars]
    en  Somebody comes through %2$s and nobody knows until they are inside. Not a battle. A theft, a fright, a child who will not sleep for a month.
    >>  ............................................
    pt  Alguém entra por %2$s e ninguém percebe até já estar dentro. Não uma batalha. Um furto, um susto, uma criança que não dorme por um mês.
    >>  ............................................
  dialogue.conversations.scene.work.guard.weak_point.blocked.explained/3   [155 chars]
    en  I stand where I can see it instead of where I should be. So the answer is that the whole watch is worse, quietly, every night, and nobody can point at why.
    >>  ............................................
    pt  Fico onde consigo ver isso em vez de onde deveria estar. Então a resposta é que a ronda inteira piora, em silêncio, toda noite, e ninguém consegue apontar por quê.
    >>  ............................................
```


### Button `offer_timber` — "Bring me the planks and I'll help you fix it."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.guard.weak_point.blocked` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.guard.weak_point.blocked.offer_timber` — accepted phrasings: "bring me the planks and ill help you fix it"; "i can bring planks for that"; "let me fetch timber"
  - the message must contain one of: `planks`, `timber`
  - scored words: `planks`(1.8), `timber`(1.8), `bring`(0.8), `ill`(0.8), `help`(0.8), `fix`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.blocked.respond.offer_timber
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.weak_point.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.weak_point.blocked.respond.offer_timber   [45 chars]
    en  Bring me the planks and I'll help you fix it.
    >>  ............................................
    pt  Traga as tábuas e eu ajudo a consertar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.guard.weak_point.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.guard.weak_points`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.weak_point", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.guard.weak_point", "obligation": "commitment:work.guard.bring_timber"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.guard.bring_timber"}
- Then opens: `conversations.scene.work.guard.followup`
- …where the player's next choices will be: "What's the hardest part of the watch?" | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "Bring me the planks and I'll help you fix it."
       spoken on: conversations.scene.work.guard.weak_point.blocked.respond, button `offer_timber`
       leaves the player on: conversations.scene.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.weak_point.blocked.accepted`: the villager accepts. Subject `work.guard.weak_points`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.weak_point.blocked.accepted/1   [115 chars]
    en  Then it gets done, and it gets done this week, and I will stop being the person who mentions %2$s at every meeting.
    >>  ............................................
    pt  Então fica feito, e fica feito esta semana, e eu paro de ser a pessoa que menciona %2$s em toda reunião.
    >>  ............................................
  dialogue.conversations.scene.work.guard.weak_point.blocked.accepted/2   [137 chars]
    en  You are offering to solve in an afternoon a thing I have been carrying for a month. I will take it and I will not make a speech about it.
    >>  ............................................
    pt  Você está se oferecendo para resolver numa tarde uma coisa que eu carrego há um mês. Aceito, e não vou fazer discurso.
    >>  ............................................
  dialogue.conversations.scene.work.guard.weak_point.blocked.accepted/3   [132 chars]
    en  Right. Bring them to %2$s at dusk and I will have the tools laid out. I have had the tools laid out for a fortnight, if I am honest.
    >>  ............................................
    pt  Certo. Traga até %2$s ao anoitecer e eu deixo as ferramentas prontas. Deixo as ferramentas prontas há quinze dias, para ser honesta.
    >>  ............................................
```


### Button `advise_escalate` — "Stop asking. Call it a danger and make them hear it."

*stance family `candor` · tone `plain` · outcome `resisted` · answers the beat(s) `work.guard.weak_point.blocked` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.guard.weak_point.blocked.advise_escalate` — accepted phrasings: "stop asking call it a danger and make them hear it"; "call it a danger and make them hear it"; "say danger and make them listen"
  - the message must contain one of: `danger`, `hear`, `listen`
  - scored words: `danger`(1.8), `hear`(1.8), `listen`(1.8), `stop`(0.8), `asking`(0.8), `call`(0.8), `make`(0.8), `say`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.blocked.respond.advise_escalate
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.weak_point.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.weak_point.blocked.respond.advise_escalate   [52 chars]
    en  Stop asking. Call it a danger and make them hear it.
    >>  ............................................
    pt  Pare de pedir. Chame de perigo e faça ouvirem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2, tension +1  _(recorded under topic `work.guard.weak_points`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.guard.weak_point"}
- Then opens: `conversations.scene.work.guard.followup`
- …where the player's next choices will be: "What's the hardest part of the watch?" | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.blocked.resisted
WHO    VILLAGER — what the player reads after pressing "Stop asking. Call it a danger and make them hear it."
       spoken on: conversations.scene.work.guard.weak_point.blocked.respond, button `advise_escalate`
       leaves the player on: conversations.scene.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.weak_point.blocked.resisted`: the villager resists. Subject `work.guard.weak_points`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.weak_point.blocked.resisted/1   [132 chars]
    en  And be the guard who cries wolf about %2$s. I get one of those in a career and I would rather spend it on something worse than this.
    >>  ............................................
    pt  E virar a guarda que grita lobo por causa de %2$s. Tenho direito a uma dessas numa carreira, e prefiro gastar com algo pior que isto.
    >>  ............................................
  dialogue.conversations.scene.work.guard.weak_point.blocked.resisted/2   [138 chars]
    en  If I say unsafe they will post two people there and take them off the river, and then the river is what I am standing here worrying about.
    >>  ............................................
    pt  Se eu disser inseguro, vão pôr duas pessoas ali e tirar do rio, e aí o rio passa a ser o que eu fico aqui temendo.
    >>  ............................................
  dialogue.conversations.scene.work.guard.weak_point.blocked.resisted/3   [132 chars]
    en  That is what you say when you have not had to be believed for a living. I would rather be dull and heard than urgent and discounted.
    >>  ............................................
    pt  Isso é o que se diz quando nunca se precisou ser acreditada para viver. Prefiro ser monótona e ouvida a ser urgente e descontada.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the wall."

*stance family `exit` · tone `plain` · answers the beat(s) `work.guard.weak_point.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.weak_point.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.weak_point.blocked.respond.leave   [34 chars]
    en  I'll let you get back to the wall.
    >>  ............................................
    pt  Vou deixar você voltar para a muralha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the wall."
       spoken on: conversations.scene.work.guard.weak_point.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.left`: the villager accepts. Subject `work.guard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.guard.failing_kit.active.respond / leave; conversations.scene.work.guard.failing_kit.succeeded.respond / leave; conversations.scene.work.guard.followup / leave; conversations.scene.work.guard.night_sighting.blocked.respond / leave; conversations.scene.work.guard.night_sighting.succeeded.respond / leave; conversations.scene.work.guard.weak_point.failed.respond / leave; conversations.scene.work.guard.weak_point.succeeded.respond / leave; conversations.topic.work.guard.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.guard.failing_kit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.guard.weak_point.failed.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.guard.weak_point.failed` — e.g. "I have stopped raising %2$s. %3$s is still there and I have moved my patrol so that I do not have to look at it."


```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.failed.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.guard.weak_point.failed.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.guard.weak_point.failed.respond   [23 chars]
    en  Nothing happened, then.
    >>  ............................................
    pt  Então nada foi feito.
    >>  ............................................
```


### Button `sit_with_it` — "You did your part. They didn't do theirs."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.guard.weak_point.failed` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.guard.weak_point.failed.sit_with_it` — accepted phrasings: "you did your part they didnt do theirs"; "you did what you could"; "that is on them not you"
  - the message must contain one of: `part`, `them`, `could`
  - scored words: `part`(1.8), `them`(1.8), `could`(1.8), `didnt`(0.8), `theirs`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.failed.respond.sit_with_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.weak_point.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.weak_point.failed.respond.sit_with_it   [41 chars]
    en  You did your part. They didn't do theirs.
    >>  ............................................
    pt  Você fez a sua parte. Eles não fizeram a deles.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.guard.weak_point.mourn`, budget `standard`, replay policy `once`
- Does: disposition — warmth +3, trust +2  _(recorded under topic `work.guard.weak_points`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.guard.weak_point"}
- Then opens: `conversations.scene.work.guard.followup`
- …where the player's next choices will be: "What's the hardest part of the watch?" | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.failed.received
WHO    VILLAGER — what the player reads after pressing "You did your part. They didn't do theirs."
       spoken on: conversations.scene.work.guard.weak_point.failed.respond, button `sit_with_it`
       leaves the player on: conversations.scene.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.weak_point.failed.received`: the villager qualifys. Subject `work.guard.weak_points`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.weak_point.failed.received/1   [107 chars]
    en  That is true and it will not help me at all on the night it matters, but say it again anyway. It helps now.
    >>  ............................................
    pt  É verdade, e não vai me ajudar em nada na noite em que importar — mas repita mesmo assim. Ajuda agora.
    >>  ............................................
  dialogue.conversations.scene.work.guard.weak_point.failed.received/2   [79 chars]
    en  I know. I would still rather have been wrong about %2$s than right and ignored.
    >>  ............................................
    pt  Eu sei. Ainda assim, preferia ter estado errada sobre %2$s a estar certa e ignorada.
    >>  ............................................
  dialogue.conversations.scene.work.guard.weak_point.failed.received/3   [124 chars]
    en  Not one person has put it that way. They have all said 'you tried', which is the same sentence with the blame quietly moved.
    >>  ............................................
    pt  Nem uma pessoa colocou assim. Todos disseram 'você tentou', que é a mesma frase com a culpa discretamente deslocada.
    >>  ............................................
```


### Button `ask_workaround` — "How are you covering it in the meantime?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.guard.weak_point.failed` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.guard.weak_point.failed.ask_workaround` — accepted phrasings: "how are you covering it in the meantime"; "how do you cover it now"; "what are you doing instead"
  - the message must contain one of: `covering`, `cover`, `instead`
  - scored words: `covering`(1.8), `cover`(1.8), `instead`(1.8), `meantime`(0.8), `now`(0.8), `doing`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.failed.respond.ask_workaround
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.weak_point.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.weak_point.failed.respond.ask_workaround   [40 chars]
    en  How are you covering it in the meantime?
    >>  ............................................
    pt  Como você está cobrindo isso enquanto isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.guard.weak_points`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.guard.weak_point"}
- Then opens: `conversations.scene.work.guard.followup`
- …where the player's next choices will be: "What's the hardest part of the watch?" | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.failed.coping
WHO    VILLAGER — what the player reads after pressing "How are you covering it in the meantime?"
       spoken on: conversations.scene.work.guard.weak_point.failed.respond, button `ask_workaround`
       leaves the player on: conversations.scene.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.weak_point.failed.coping`: the villager explains. Subject `work.guard.weak_points`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.weak_point.failed.coping/1   [142 chars]
    en  Badly. I loop back past %2$s twice a night, which means the far side gets less of me. You cannot cover a hole; you can only choose which hole.
    >>  ............................................
    pt  Mal. Volto por %2$s duas vezes por noite, o que significa que o outro lado fica com menos de mim. Não se cobre um buraco; só se escolhe qual buraco.
    >>  ............................................
  dialogue.conversations.scene.work.guard.weak_point.failed.coping/2   [115 chars]
    en  A lamp I pay for myself and a habit of listening at the turn. Neither is a repair and both are better than nothing.
    >>  ............................................
    pt  Uma lamparina que pago do meu bolso e o hábito de escutar na curva. Nenhum dos dois é conserto, e ambos são melhores que nada.
    >>  ............................................
  dialogue.conversations.scene.work.guard.weak_point.failed.coping/3   [136 chars]
    en  By being there. That is the whole answer and it is why I am tired all the time, and why I will not be able to keep it up through winter.
    >>  ............................................
    pt  Estando lá. É a resposta inteira, e é por isso que vivo cansada, e por isso que não vou aguentar isso o inverno todo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the wall."

*stance family `exit` · tone `plain` · answers the beat(s) `work.guard.weak_point.failed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.failed.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.weak_point.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.weak_point.failed.respond.leave   [34 chars]
    en  I'll let you get back to the wall.
    >>  ............................................
    pt  Vou deixar você voltar para a muralha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the wall."
       spoken on: conversations.scene.work.guard.weak_point.failed.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.left`: the villager accepts. Subject `work.guard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.guard.failing_kit.active.respond / leave; conversations.scene.work.guard.failing_kit.succeeded.respond / leave; conversations.scene.work.guard.followup / leave; conversations.scene.work.guard.night_sighting.blocked.respond / leave; conversations.scene.work.guard.night_sighting.succeeded.respond / leave; conversations.scene.work.guard.weak_point.blocked.respond / leave; conversations.scene.work.guard.weak_point.succeeded.respond / leave; conversations.topic.work.guard.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.guard.failing_kit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.guard.weak_point.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.guard.weak_point.succeeded` — e.g. "%2$s is sound. I walked it twice last night for no reason except that I could walk past it without thinking."


```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.guard.weak_point.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.guard.weak_point.succeeded.respond   [12 chars]
    en  It's mended.
    >>  ............................................
    pt  Está consertado.
    >>  ............................................
```


### Button `credit_her` — "You kept asking. That's why it got done."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.guard.weak_point.succeeded` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.guard.weak_point.succeeded.credit_her` — accepted phrasings: "you kept asking thats why it got done"; "you are the reason it got done"; "you kept on about it"
  - the message must contain one of: `asking`, `reason`, `kept`
  - scored words: `asking`(1.8), `reason`(1.8), `kept`(1.8), `thats`(0.8), `why`(0.8), `got`(0.8), `done`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.succeeded.respond.credit_her
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.weak_point.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.weak_point.succeeded.respond.credit_her   [40 chars]
    en  You kept asking. That's why it got done.
    >>  ............................................
    pt  Você insistiu. Foi por isso que foi feito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.guard.weak_point.credit`, budget `standard`, replay policy `once`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.guard.weak_points`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.guard.weak_point"}
- Then opens: `conversations.scene.work.guard.followup`
- …where the player's next choices will be: "What's the hardest part of the watch?" | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.succeeded.credited
WHO    VILLAGER — what the player reads after pressing "You kept asking. That's why it got done."
       spoken on: conversations.scene.work.guard.weak_point.succeeded.respond, button `credit_her`
       leaves the player on: conversations.scene.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.weak_point.succeeded.credited`: the villager qualifys. Subject `work.guard.weak_points`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.weak_point.succeeded.credited/1   [133 chars]
    en  I kept asking and I was very close to stopping. A fortnight more and %2$s would still be open, and I would have called that patience.
    >>  ............................................
    pt  Insisti e estive muito perto de desistir. Mais quinze dias e %2$s ainda estaria aberto, e eu teria chamado isso de paciência.
    >>  ............................................
  dialogue.conversations.scene.work.guard.weak_point.succeeded.credited/2   [88 chars]
    en  Being a nuisance is most of this job and nobody ever puts it that way kindly. Thank you.
    >>  ............................................
    pt  Ser inconveniente é metade deste trabalho, e ninguém nunca coloca isso com gentileza. Obrigada.
    >>  ............................................
  dialogue.conversations.scene.work.guard.weak_point.succeeded.credited/3   [93 chars]
    en  That is the part nobody sees, so I will take having it said. Once. Do not make a habit of it.
    >>  ............................................
    pt  Essa é a parte que ninguém vê, então aceito ouvir. Uma vez. Não vire hábito.
    >>  ............................................
```


### Button `ask_next_weak` — "Where's the next weak spot?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.guard.weak_point.succeeded` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.guard.weak_point.succeeded.ask_next_weak` — accepted phrasings: "wheres the next weak spot"; "what is the next problem"; "where else is thin"
  - the message must contain one of: `next`, `else`, `weak`
  - scored words: `next`(1.8), `else`(1.8), `weak`(1.8), `wheres`(0.8), `spot`(0.8), `problem`(0.8), `where`(0.8), `thin`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.succeeded.respond.ask_next_weak
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.weak_point.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.weak_point.succeeded.respond.ask_next_weak   [27 chars]
    en  Where's the next weak spot?
    >>  ............................................
    pt  Onde fica o próximo ponto fraco?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, trust +1  _(recorded under topic `work.guard.weak_points`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.guard.weak_point"}
- Then opens: `conversations.scene.work.guard.followup`
- …where the player's next choices will be: "What's the hardest part of the watch?" | "I'll let you get on with it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.succeeded.listed
WHO    VILLAGER — what the player reads after pressing "Where's the next weak spot?"
       spoken on: conversations.scene.work.guard.weak_point.succeeded.respond, button `ask_next_weak`
       leaves the player on: conversations.scene.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.weak_point.succeeded.listed`: the villager explains. Subject `work.guard.weak_points`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.guard.weak_point.succeeded.listed/1   [131 chars]
    en  There is always a next one. That is not gloom, it is the job — you fix %2$s and the next thinnest place becomes the thinnest place.
    >>  ............................................
    pt  Sempre tem um próximo. Isso não é pessimismo, é o ofício — você conserta %2$s e o próximo ponto mais fino vira o mais fino.
    >>  ............................................
  dialogue.conversations.scene.work.guard.weak_point.succeeded.listed/2   [120 chars]
    en  The river stair, and I am not going to start on it today because I have had one good week and I would like to finish it.
    >>  ............................................
    pt  A escada do rio, e não vou começar com isso hoje porque tive uma boa semana e gostaria de terminá-la.
    >>  ............................................
  dialogue.conversations.scene.work.guard.weak_point.succeeded.listed/3   [112 chars]
    en  Ask me tomorrow. Tonight I am going to walk a wall with nothing wrong with it, which happens about twice a year.
    >>  ............................................
    pt  Me pergunte amanhã. Hoje vou percorrer uma muralha sem nada de errado, o que acontece umas duas vezes por ano.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the wall."

*stance family `exit` · tone `plain` · answers the beat(s) `work.guard.weak_point.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.guard.weak_point.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.guard.weak_point.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.guard.weak_point.succeeded.respond.leave   [34 chars]
    en  I'll let you get back to the wall.
    >>  ............................................
    pt  Vou deixar você voltar para a muralha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the wall."
       spoken on: conversations.scene.work.guard.weak_point.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.left`: the villager accepts. Subject `work.guard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.guard.failing_kit.active.respond / leave; conversations.scene.work.guard.failing_kit.succeeded.respond / leave; conversations.scene.work.guard.followup / leave; conversations.scene.work.guard.night_sighting.blocked.respond / leave; conversations.scene.work.guard.night_sighting.succeeded.respond / leave; conversations.scene.work.guard.weak_point.blocked.respond / leave; conversations.scene.work.guard.weak_point.failed.respond / leave; conversations.topic.work.guard.craft.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.guard.failing_kit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.guard.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.guard.craft` — e.g. "The skill is noticing what's different. Same lane, same faces, every day — until one day something isn't."


```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.guard.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.guard.craft.respond   [26 chars]
    en  That's the training of it.
    >>  ............................................
    pt  É esse o treino.
    >>  ............................................
```


### Button `ask_different` — "When did something last look different?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.guard.craft` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.guard.craft.ask_different` — accepted phrasings: "when did something last look different"
  - the message must contain one of: `different`, `noticed`
  - scored words: `different`(1.5), `noticed`(1.2), `last`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.craft.respond.ask_different
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.craft.respond.ask_different   [39 chars]
    en  When did something last look different?
    >>  ............................................
    pt  Quando algo pareceu diferente pela última vez?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.guard.craft.ask_different`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.guard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where's the village weakest?" | "Quiet shifts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.craft.ask_different
WHO    VILLAGER — what the player reads after pressing "When did something last look different?"
       spoken on: conversations.topic.work.guard.craft.respond, button `ask_different`
       leaves the player on: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.craft.ask_different`: the villager explains. Subject `work.guard.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.guard.craft.ask_different/1   [80 chars]
    en  Four years ago, an hour before the raid. I noticed and I didn't act fast enough.
    >>  ............................................
    pt  Quatro anos atrás, uma hora antes do ataque. Eu reparei e não agi rápido o bastante.
    >>  ............................................
  dialogue.conversations.work.prof.guard.craft.ask_different/2   [87 chars]
    en  Tuesday. A cart with the wrong kind of tired horses, %1$s. It turned out to be nothing.
    >>  ............................................
    pt  Terça. Uma carroça com cavalos cansados do jeito errado, %1$s. Não deu em nada.
    >>  ............................................
```


### Button `admire` — "Describing the square with your eyes shut is a real method."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.guard.craft` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.guard.craft.admire` — accepted phrasings: "describing the square with your eyes shut is a real method"
  - the message must contain one of: `describing`, `method`, `shut`
  - scored words: `describing`(1.5), `method`(1.5), `shut`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.craft.respond.admire   [59 chars]
    en  Describing the square with your eyes shut is a real method.
    >>  ............................................
    pt  Descrever a praça de olhos fechados é um método de verdade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.guard.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.guard.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.guard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where's the village weakest?" | "Quiet shifts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.craft.admire
WHO    VILLAGER — what the player reads after pressing "Describing the square with your eyes shut is a real method."
       spoken on: conversations.topic.work.guard.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.craft.admire`: the villager accepts. Subject `work.guard.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.guard.craft.admire/1   [82 chars]
    en  It is, and I've made two people do it since, and both of them hated me for it too.
    >>  ............................................
    pt  É, e eu já fiz duas pessoas fazerem, e as duas também me odiaram.
    >>  ............................................
  dialogue.conversations.work.prof.guard.craft.admire/2   [90 chars]
    en  He knew exactly what he was doing and never once explained it, %1$s. That's the tradition.
    >>  ............................................
    pt  Ele sabia exatamente o que fazia e nunca explicou, %1$s. É a tradição.
    >>  ............................................
```


### Button `ask_hated` — "Do you hate him now?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.guard.craft` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.guard.craft.ask_hated` — accepted phrasings: "do you hate him now"
  - the message must contain one of: `hate`, `teacher`
  - scored words: `hate`(1.5), `him`(0.5), `teacher`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.craft.respond.ask_hated
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.craft.respond.ask_hated   [20 chars]
    en  Do you hate him now?
    >>  ............................................
    pt  Você o odeia agora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.guard.craft.ask_hated`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.guard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where's the village weakest?" | "Quiet shifts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.craft.ask_hated
WHO    VILLAGER — what the player reads after pressing "Do you hate him now?"
       spoken on: conversations.topic.work.guard.craft.respond, button `ask_hated`
       leaves the player on: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.craft.ask_hated`: the villager explains. Subject `work.guard.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.guard.craft.ask_hated/1   [95 chars]
    en  He died before I could tell him he was right. That's the arrangement with teachers, apparently.
    >>  ............................................
    pt  Ele morreu antes de eu poder dizer que ele tinha razão. É o acordo com mestres, aparentemente.
    >>  ............................................
  dialogue.conversations.work.prof.guard.craft.ask_hated/2   [87 chars]
    en  Not for a long time. I hate that I never said so, %1$s, which is a different complaint.
    >>  ............................................
    pt  Faz tempo que não. Odeio nunca ter dito, %1$s, que é outra queixa.
    >>  ............................................
```


### Button `leave` — "I'll let you keep watch."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.guard.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.craft.respond.leave   [24 chars]
    en  I'll let you keep watch.
    >>  ............................................
    pt  Vou deixar você de vigia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you keep watch."
       spoken on: conversations.topic.work.guard.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.left`: the villager accepts. Subject `work.guard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.guard.failing_kit.active.respond / leave; conversations.scene.work.guard.failing_kit.succeeded.respond / leave; conversations.scene.work.guard.followup / leave; conversations.scene.work.guard.night_sighting.blocked.respond / leave; conversations.scene.work.guard.night_sighting.succeeded.respond / leave; conversations.scene.work.guard.weak_point.blocked.respond / leave; conversations.scene.work.guard.weak_point.failed.respond / leave; conversations.scene.work.guard.weak_point.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.guard.failing_kit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.guard.followup`

**Reached from 20 route(s):** `conversations.scene.work.guard.followup` / `ask_more`; `conversations.topic.work.guard.craft.respond` / `ask_different`; `conversations.topic.work.guard.craft.respond` / `admire`; `conversations.topic.work.guard.craft.respond` / `ask_hated`; `conversations.topic.work.guard.future.respond` / `ask_second_guard`; `conversations.topic.work.guard.future.respond` / `encourage`; `conversations.topic.work.guard.future.respond` / `ask_unnecessary`; `conversations.topic.work.guard.respond` / `ask_hard`; `conversations.topic.work.guard.respond` / `value`; `conversations.topic.work.guard.respond` / `challenge`; `conversations.topic.work.guard.respond` / `challenge`; `conversations.topic.work.guard.risk.respond` / `ask_raid` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.guard.challenge.landed` — e.g. "That is the entire result of my work, %1$s. I'll take it as a compliment."
- `conversations.work.prof.guard.challenge.stung` — e.g. "...Nothing happened last spring either. Ask the mason why he has a new door."
- `conversations.work.prof.guard.craft.admire` — e.g. "It is, and I've made two people do it since, and both of them hated me for it too."
- `conversations.work.prof.guard.craft.ask_different` — e.g. "Four years ago, an hour before the raid. I noticed and I didn't act fast enough."
- `conversations.work.prof.guard.craft.ask_hated` — e.g. "He died before I could tell him he was right. That's the arrangement with teachers, apparently."
- `conversations.work.prof.guard.future.ask_second_guard` — e.g. "He's said 'not this year' four times, which is a different word with the same result."
- `conversations.work.prof.guard.future.ask_unnecessary` — e.g. "Every part of me except the part that wouldn't know what to do on a Tuesday."
- `conversations.work.prof.guard.future.encourage` — e.g. "...In front of them. That is either very clever or very unkind and I need a day on it."
- `conversations.work.prof.guard.hard` — e.g. "The fourth hour. Nothing has happened and your mind starts inventing something."
- `conversations.work.prof.guard.risk.ask_raid` — e.g. "Nothing. That's the point. I stood at a quiet gate while the noise was on the other side."
- `conversations.work.prof.guard.risk.ask_since` — e.g. "I walk instead of stand and I've a second pair of eyes on the far side. It cost me a year of arguing."
- `conversations.work.prof.guard.risk.sympathise` — e.g. "...No. Everyone says that and everyone is right and it has never once helped."
- `conversations.work.prof.guard.task.ask_children` — e.g. "Never. I mend the gap and say nothing and they find another one, and that's how it should go."
- `conversations.work.prof.guard.task.ask_hour` — e.g. "Your eyes start telling you stories. That's when you walk instead of stand."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.guard.followup   [31 chars]
    en  That's the watch, more or less.
    >>  ............................................
    pt  É a vigia, mais ou menos.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.guard.challenge.landed`, `work.guard.challenge.stung`, `work.guard.craft.admire`, `work.guard.craft.ask_different`, `work.guard.craft.ask_hated`, `work.guard.future.ask_second_guard`, `work.guard.future.ask_unnecessary`, `work.guard.future.encourage`, `work.guard.hard`, `work.guard.risk.ask_raid`, `work.guard.risk.ask_since`, `work.guard.risk.sympathise`, `work.guard.task.ask_children`, `work.guard.task.ask_hour`, `work.guard.task.offer_hands`, `work.guard.value`, `work.guard.village.ask_claim`, `work.guard.village.ask_six`, `work.guard.village.say_thanks` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.guard.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `absence`
  - scored words: `thought`(1.2), `absence`(1.2), `watch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.guard.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.guard.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.guard.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.guard.thanks`: the villager accepts. Subject `work.guard.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.guard.thanks/1   [69 chars]
    en  Nobody does. That's the job — being thought about only when it fails.
    >>  ............................................
    pt  Ninguém pensa. É esse o trabalho — só ser lembrado quando falha.
    >>  ............................................
  dialogue.conversations.work.prof.guard.thanks/2   [73 chars]
    en  It's a strange trade to be judged by, %1$s. Absences don't make speeches.
    >>  ............................................
    pt  É um ofício estranho de ser julgado, %1$s. Ausências não fazem discurso.
    >>  ............................................
```


### Button `ask_more` — "Where's the village weakest?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.guard.challenge.landed`, `work.guard.challenge.stung`, `work.guard.craft.admire`, `work.guard.craft.ask_different`, `work.guard.craft.ask_hated`, `work.guard.future.ask_second_guard`, `work.guard.future.ask_unnecessary`, `work.guard.future.encourage`, `work.guard.hard`, `work.guard.risk.ask_raid`, `work.guard.risk.ask_since`, `work.guard.risk.sympathise`, `work.guard.task.ask_children`, `work.guard.task.ask_hour`, `work.guard.task.offer_hands`, `work.guard.value`, `work.guard.village.ask_claim`, `work.guard.village.ask_six`, `work.guard.village.say_thanks` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.guard.more` — accepted phrasings: "where's the village weakest"
  - the message must contain one of: `weakest`, `weak`, `gap`
  - scored words: `weakest`(1.5), `weak`(1.2), `gap`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.followup.ask_more   [28 chars]
    en  Where's the village weakest?
    >>  ............................................
    pt  Onde o vilarejo é mais frágil?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.guard.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.more
WHO    VILLAGER — what the player reads after pressing "Where's the village weakest?"
       spoken on: conversations.topic.work.guard.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.guard.more`: the villager discloses. Subject `work.guard.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.guard.more/1   [76 chars]
    en  The north fence, and the fact that I'm one man. I've said both to the mayor.
    >>  ............................................
    pt  A cerca norte, e o fato de eu ser um homem só. Já disse os dois ao prefeito.
    >>  ............................................
  dialogue.conversations.work.prof.guard.more/2   [70 chars]
    en  Not a place — an hour. Just before dawn, when even I want to sit down.
    >>  ............................................
    pt  Não é um lugar — é uma hora. Pouco antes do amanhecer, quando até eu quero sentar.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.guard.more/1
    en  The north fence, and that I'm one man. Four years ago being one man was the whole problem.
    >>  ............................................
    pt  A cerca norte, e que eu sou um homem só. Quatro anos atrás ser um homem só era todo o problema.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.guard.more/2
    en  A second guard. Not for me — so that the next wrong gate isn't somebody's family.
    >>  ............................................
    pt  Um segundo guarda. Não por mim — pra que o próximo portão errado não seja a família de alguém.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.guard.more/1
    en  The north fence. It's been down a while and it'll go up when somebody has a fortnight.
    >>  ............................................
    pt  A cerca norte. Está caída faz um tempo e vai subir quando alguém tiver quinze dias.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.guard.more/2
    en  A second guard, eventually. 'Not this year' four times is still not a no.
    >>  ............................................
    pt  Um segundo guarda, uma hora. 'Este ano não' quatro vezes ainda não é um não.
    >>  ............................................
  confident.dialogue.conversations.work.prof.guard.more/1
    en  The north fence, and the fact that I'm one man. I've said both to the mayor.
    >>  ............................................
    pt  A cerca norte, e o fato de eu ser um homem só. Já disse os dois ao prefeito.
    >>  ............................................
  confident.dialogue.conversations.work.prof.guard.more/2
    en  A second guard. Then four quiet years is a thing two people did and not a thing I got away with.
    >>  ............................................
    pt  Um segundo guarda. Aí quatro anos calmos é coisa que duas pessoas fizeram e não coisa de que eu escapei.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.guard.more/1
    en  The north fence, and the fact that I'm one man. I've said both to the mayor.
    >>  ............................................
    pt  A cerca norte, e o fato de eu ser um homem só. Já disse os dois ao prefeito.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.guard.more/2
    en  A second guard. Then four quiet years is a thing two people did and not a thing I got away with.
    >>  ............................................
    pt  Um segundo guarda. Aí quatro anos calmos é coisa que duas pessoas fizeram e não coisa de que eu escapei.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.guard.more/1
    en  The north fence, and that I'm one man. Walk it with me and you'll see the gap in a minute.
    >>  ............................................
    pt  A cerca norte, e que eu sou um homem só. Ande comigo e você vê a brecha num minuto.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.guard.more/2
    en  A second guard. Then somebody else would know the six doors that don't lock, and I'd sleep.
    >>  ............................................
    pt  Um segundo guarda. Aí outra pessoa saberia das seis portas que não trancam, e eu dormiria.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.guard.more/1
    en  The north fence, and that I'm one man. Walk it with me and you'll see the gap in a minute.
    >>  ............................................
    pt  A cerca norte, e que eu sou um homem só. Ande comigo e você vê a brecha num minuto.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.guard.more/2
    en  A second guard. Then somebody else would know the six doors that don't lock, and I'd sleep.
    >>  ............................................
    pt  Um segundo guarda. Aí outra pessoa saberia das seis portas que não trancam, e eu dormiria.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.guard.more/1
    en  The north fence, and that I'm one man. Walk it with me and you'll see the gap in a minute.
    >>  ............................................
    pt  A cerca norte, e que eu sou um homem só. Ande comigo e você vê a brecha num minuto.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.guard.more/2
    en  A second guard. Then somebody else would know the six doors that don't lock, and I'd sleep.
    >>  ............................................
    pt  Um segundo guarda. Aí outra pessoa saberia das seis portas que não trancam, e eu dormiria.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.guard.more/1
    en  The north fence, and that I'm one man. Four years ago being one man was the whole problem.
    >>  ............................................
    pt  A cerca norte, e que eu sou um homem só. Quatro anos atrás ser um homem só era todo o problema.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.guard.more/2
    en  A second guard. Not for me — so that the next wrong gate isn't somebody's family.
    >>  ............................................
    pt  Um segundo guarda. Não por mim — pra que o próximo portão errado não seja a família de alguém.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.guard.more/1
    en  The north fence, and the fact that I'm one man. I've said both to the mayor.
    >>  ............................................
    pt  A cerca norte, e o fato de eu ser um homem só. Já disse os dois ao prefeito.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.guard.more/2
    en  A second guard. Then four quiet years is a thing two people did and not a thing I got away with.
    >>  ............................................
    pt  Um segundo guarda. Aí quatro anos calmos é coisa que duas pessoas fizeram e não coisa de que eu escapei.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.guard.more/1
    en  The north fence, and the fact that I'm one man. I've said both to the mayor.
    >>  ............................................
    pt  A cerca norte, e o fato de eu ser um homem só. Já disse os dois ao prefeito.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.guard.more/2
    en  A second guard. Then four quiet years is a thing two people did and not a thing I got away with.
    >>  ............................................
    pt  Um segundo guarda. Aí quatro anos calmos é coisa que duas pessoas fizeram e não coisa de que eu escapei.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.guard.more/1
    en  The north fence. And that there is one of me, which I've said twice and will say again.
    >>  ............................................
    pt  A cerca norte. E que só tem um de mim, o que eu já disse duas vezes e vou dizer de novo.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.guard.more/2
    en  A second guard. It's a year of mornings and somebody willing to give them. That's all it is.
    >>  ............................................
    pt  Um segundo guarda. É um ano de manhãs e alguém disposto a dá-las. É só isso.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.guard.more/1
    en  The north fence. It's been down a while and it'll go up when somebody has a fortnight.
    >>  ............................................
    pt  A cerca norte. Está caída faz um tempo e vai subir quando alguém tiver quinze dias.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.guard.more/2
    en  A second guard, eventually. 'Not this year' four times is still not a no.
    >>  ............................................
    pt  Um segundo guarda, uma hora. 'Este ano não' quatro vezes ainda não é um não.
    >>  ............................................
  odd.dialogue.conversations.work.prof.guard.more/1
    en  The north fence. And that there is one of me, which I've said twice and will say again.
    >>  ............................................
    pt  A cerca norte. E que só tem um de mim, o que eu já disse duas vezes e vou dizer de novo.
    >>  ............................................
  odd.dialogue.conversations.work.prof.guard.more/2
    en  A second guard. It's a year of mornings and somebody willing to give them. That's all it is.
    >>  ............................................
    pt  Um segundo guarda. É um ano de manhãs e alguém disposto a dá-las. É só isso.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.guard.more/1
    en  The north fence. It's been down a while and it'll go up when somebody has a fortnight.
    >>  ............................................
    pt  A cerca norte. Está caída faz um tempo e vai subir quando alguém tiver quinze dias.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.guard.more/2
    en  A second guard, eventually. 'Not this year' four times is still not a no.
    >>  ............................................
    pt  Um segundo guarda, uma hora. 'Este ano não' quatro vezes ainda não é um não.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.guard.more/1
    en  The north fence, and the fact that I'm one man! I've mentioned both. Repeatedly. With diagrams.
    >>  ............................................
    pt  A cerca norte, e o fato de eu ser um homem só! Já mencionei os dois. Repetidamente. Com diagramas.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.guard.more/2
    en  A second guard. The mayor asked what they'd do. I said 'the other gate' and he laughed.
    >>  ............................................
    pt  Um segundo guarda. O prefeito perguntou o que ele faria. Eu disse 'o outro portão' e ele riu.
    >>  ............................................
  playful.dialogue.conversations.work.prof.guard.more/1
    en  The north fence, and the fact that I'm one man! I've mentioned both. Repeatedly. With diagrams.
    >>  ............................................
    pt  A cerca norte, e o fato de eu ser um homem só! Já mencionei os dois. Repetidamente. Com diagramas.
    >>  ............................................
  playful.dialogue.conversations.work.prof.guard.more/2
    en  A second guard. The mayor asked what they'd do. I said 'the other gate' and he laughed.
    >>  ............................................
    pt  Um segundo guarda. O prefeito perguntou o que ele faria. Eu disse 'o outro portão' e ele riu.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.guard.more/1
    en  The north fence. It's been down a while and it'll go up when somebody has a fortnight.
    >>  ............................................
    pt  A cerca norte. Está caída faz um tempo e vai subir quando alguém tiver quinze dias.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.guard.more/2
    en  A second guard, eventually. 'Not this year' four times is still not a no.
    >>  ............................................
    pt  Um segundo guarda, uma hora. 'Este ano não' quatro vezes ainda não é um não.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.guard.more/1
    en  The north fence, and that I'm one man. Four years ago being one man was the whole problem.
    >>  ............................................
    pt  A cerca norte, e que eu sou um homem só. Quatro anos atrás ser um homem só era todo o problema.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.guard.more/2
    en  A second guard. Not for me — so that the next wrong gate isn't somebody's family.
    >>  ............................................
    pt  Um segundo guarda. Não por mim — pra que o próximo portão errado não seja a família de alguém.
    >>  ............................................
  shy.dialogue.conversations.work.prof.guard.more/1
    en  The north fence. And that there is one of me, which I've said twice and will say again.
    >>  ............................................
    pt  A cerca norte. E que só tem um de mim, o que eu já disse duas vezes e vou dizer de novo.
    >>  ............................................
  shy.dialogue.conversations.work.prof.guard.more/2
    en  A second guard. It's a year of mornings and somebody willing to give them. That's all it is.
    >>  ............................................
    pt  Um segundo guarda. É um ano de manhãs e alguém disposto a dá-las. É só isso.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.guard.more/1
    en  The north fence, and the fact that I'm one man! I've mentioned both. Repeatedly. With diagrams.
    >>  ............................................
    pt  A cerca norte, e o fato de eu ser um homem só! Já mencionei os dois. Repetidamente. Com diagramas.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.guard.more/2
    en  A second guard. The mayor asked what they'd do. I said 'the other gate' and he laughed.
    >>  ............................................
    pt  Um segundo guarda. O prefeito perguntou o que ele faria. Eu disse 'o outro portão' e ele riu.
    >>  ............................................
  witty.dialogue.conversations.work.prof.guard.more/1
    en  The north fence, and the fact that I'm one man! I've mentioned both. Repeatedly. With diagrams.
    >>  ............................................
    pt  A cerca norte, e o fato de eu ser um homem só! Já mencionei os dois. Repetidamente. Com diagramas.
    >>  ............................................
  witty.dialogue.conversations.work.prof.guard.more/2
    en  A second guard. The mayor asked what they'd do. I said 'the other gate' and he laughed.
    >>  ............................................
    pt  Um segundo guarda. O prefeito perguntou o que ele faria. Eu disse 'o outro portão' e ele riu.
    >>  ............................................
```

</details>


### Button `leave` — "Quiet shifts to you."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.guard.challenge.landed`, `work.guard.challenge.stung`, `work.guard.craft.admire`, `work.guard.craft.ask_different`, `work.guard.craft.ask_hated`, `work.guard.future.ask_second_guard`, `work.guard.future.ask_unnecessary`, `work.guard.future.encourage`, `work.guard.hard`, `work.guard.risk.ask_raid`, `work.guard.risk.ask_since`, `work.guard.risk.sympathise`, `work.guard.task.ask_children`, `work.guard.task.ask_hour`, `work.guard.task.offer_hands`, `work.guard.value`, `work.guard.village.ask_claim`, `work.guard.village.ask_six`, `work.guard.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.followup.leave   [20 chars]
    en  Quiet shifts to you.
    >>  ............................................
    pt  Turnos tranquilos pra você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.leave
WHO    VILLAGER — what the player reads after pressing "Quiet shifts to you."
       spoken on: conversations.topic.work.guard.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.left`: the villager accepts. Subject `work.guard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.guard.failing_kit.active.respond / leave; conversations.scene.work.guard.failing_kit.succeeded.respond / leave; conversations.scene.work.guard.followup / leave; conversations.scene.work.guard.night_sighting.blocked.respond / leave; conversations.scene.work.guard.night_sighting.succeeded.respond / leave; conversations.scene.work.guard.weak_point.blocked.respond / leave; conversations.scene.work.guard.weak_point.failed.respond / leave; conversations.scene.work.guard.weak_point.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.guard.failing_kit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.guard.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.guard.future` — e.g. "A second guard, so that four years becomes a thing two people did and not a thing I got away with."


```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.guard.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.guard.future.respond   [23 chars]
    en  That's what I'd change.
    >>  ............................................
    pt  É o que eu mudaria.
    >>  ............................................
```


### Button `ask_second_guard` — "Has the mayor said no?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.guard.future` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.guard.future.ask_second_guard` — accepted phrasings: "has the mayor said no"
  - the message must contain one of: `mayor`, `refused`, `second`
  - scored words: `mayor`(1.5), `refused`(1.2), `second`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.future.respond.ask_second_guard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.future.respond.ask_second_guard   [22 chars]
    en  Has the mayor said no?
    >>  ............................................
    pt  O prefeito disse não?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.guard.future.ask_second_guard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.guard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where's the village weakest?" | "Quiet shifts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.future.ask_second_guard
WHO    VILLAGER — what the player reads after pressing "Has the mayor said no?"
       spoken on: conversations.topic.work.guard.future.respond, button `ask_second_guard`
       leaves the player on: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.future.ask_second_guard`: the villager explains. Subject `work.guard.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.guard.future.ask_second_guard/1   [85 chars]
    en  He's said 'not this year' four times, which is a different word with the same result.
    >>  ............................................
    pt  Ele disse 'este ano não' quatro vezes, que é outra palavra com o mesmo resultado.
    >>  ............................................
  dialogue.conversations.work.prof.guard.future.ask_second_guard/2   [87 chars]
    en  He asked what the second guard would do. I said 'the other gate', %1$s, and he laughed.
    >>  ............................................
    pt  Ele perguntou o que o segundo guarda faria. Eu disse 'o outro portão', %1$s, e ele riu.
    >>  ............................................
```


### Button `encourage` — "Then say it in front of the families with the six doors."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.guard.future` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.guard.future.encourage` — accepted phrasings: "then say it in front of the families with the six doors"
  - the message must contain one of: `front`, `families`, `publicly`
  - scored words: `front`(1.2), `families`(1.5), `publicly`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.future.respond.encourage   [56 chars]
    en  Then say it in front of the families with the six doors.
    >>  ............................................
    pt  Então diga na frente das famílias das seis portas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.guard.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.guard.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.guard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where's the village weakest?" | "Quiet shifts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.future.encourage
WHO    VILLAGER — what the player reads after pressing "Then say it in front of the families with the six doors."
       spoken on: conversations.topic.work.guard.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.future.encourage`: the villager accepts. Subject `work.guard.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.guard.future.encourage/1   [86 chars]
    en  ...In front of them. That is either very clever or very unkind and I need a day on it.
    >>  ............................................
    pt  ...Na frente delas. Isso é muito esperto ou muito cruel e eu preciso de um dia pra pensar.
    >>  ............................................
  dialogue.conversations.work.prof.guard.future.encourage/2   [85 chars]
    en  They'd back me and they'd hate that I'd used them, %1$s. You've given me a hard idea.
    >>  ............................................
    pt  Elas me apoiariam e odiariam eu ter usado, %1$s. Você me deu uma ideia difícil.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them. I'd be using people who trust me, and I'd know it.
    >>  ............................................
    pt  ...Na frente deles. Eu estaria usando gente que confia em mim, e eu saberia.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and hate that I'd used them. I don't want to be the man who does that.
    >>  ............................................
    pt  Me apoiariam e odiariam ter sido usados. Não quero ser o homem que faz isso.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them. Clever or unkind — four years on the wall and I still can't tell.
    >>  ............................................
    pt  ...Na frente deles. Esperto ou cruel — quatro anos no muro e ainda não sei dizer.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and hate it afterwards. I've seen that trick work and cost the man his friends.
    >>  ............................................
    pt  Me apoiariam e odiariam depois. Já vi esse truque funcionar e custar os amigos ao homem.
    >>  ............................................
  confident.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them. That's either very clever or very unkind. I need a day on it.
    >>  ............................................
    pt  ...Na frente deles. Ou é muito esperto ou é muito cruel. Preciso de um dia pra pensar.
    >>  ............................................
  confident.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and they'd hate that I'd used them. You've given me a hard idea.
    >>  ............................................
    pt  Me apoiariam e odiariam ter sido usados. Você me deu uma ideia difícil.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them. That's either very clever or very unkind. I need a day on it.
    >>  ............................................
    pt  ...Na frente deles. Ou é muito esperto ou é muito cruel. Preciso de um dia pra pensar.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and they'd hate that I'd used them. You've given me a hard idea.
    >>  ............................................
    pt  Me apoiariam e odiariam ter sido usados. Você me deu uma ideia difícil.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them, %1$s. That's clever or unkind and I can't tell which yet.
    >>  ............................................
    pt  ...Na frente deles, %1$s. É esperto ou cruel e ainda não sei qual.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and they'd hate that I'd used them. That's a hard thing to have been given.
    >>  ............................................
    pt  Me apoiariam e odiariam ter sido usados. É uma coisa difícil de receber.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them, %1$s. That's clever or unkind and I can't tell which yet.
    >>  ............................................
    pt  ...Na frente deles, %1$s. É esperto ou cruel e ainda não sei qual.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and they'd hate that I'd used them. That's a hard thing to have been given.
    >>  ............................................
    pt  Me apoiariam e odiariam ter sido usados. É uma coisa difícil de receber.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them, %1$s. That's clever or unkind and I can't tell which yet.
    >>  ............................................
    pt  ...Na frente deles, %1$s. É esperto ou cruel e ainda não sei qual.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and they'd hate that I'd used them. That's a hard thing to have been given.
    >>  ............................................
    pt  Me apoiariam e odiariam ter sido usados. É uma coisa difícil de receber.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them. I'd be using people who trust me, and I'd know it.
    >>  ............................................
    pt  ...Na frente deles. Eu estaria usando gente que confia em mim, e eu saberia.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and hate that I'd used them. I don't want to be the man who does that.
    >>  ............................................
    pt  Me apoiariam e odiariam ter sido usados. Não quero ser o homem que faz isso.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them. That's either very clever or very unkind. I need a day on it.
    >>  ............................................
    pt  ...Na frente deles. Ou é muito esperto ou é muito cruel. Preciso de um dia pra pensar.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and they'd hate that I'd used them. You've given me a hard idea.
    >>  ............................................
    pt  Me apoiariam e odiariam ter sido usados. Você me deu uma ideia difícil.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them. That's either very clever or very unkind. I need a day on it.
    >>  ............................................
    pt  ...Na frente deles. Ou é muito esperto ou é muito cruel. Preciso de um dia pra pensar.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and they'd hate that I'd used them. You've given me a hard idea.
    >>  ............................................
    pt  Me apoiariam e odiariam ter sido usados. Você me deu uma ideia difícil.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them. Clever or unkind. I need a day.
    >>  ............................................
    pt  ...Na frente deles. Esperto ou cruel. Preciso de um dia.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and hate it. Hard idea.
    >>  ............................................
    pt  Me apoiariam e odiariam. Ideia difícil.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them. Clever or unkind — four years on the wall and I still can't tell.
    >>  ............................................
    pt  ...Na frente deles. Esperto ou cruel — quatro anos no muro e ainda não sei dizer.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and hate it afterwards. I've seen that trick work and cost the man his friends.
    >>  ............................................
    pt  Me apoiariam e odiariam depois. Já vi esse truque funcionar e custar os amigos ao homem.
    >>  ............................................
  odd.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them. Clever or unkind. I need a day.
    >>  ............................................
    pt  ...Na frente deles. Esperto ou cruel. Preciso de um dia.
    >>  ............................................
  odd.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and hate it. Hard idea.
    >>  ............................................
    pt  Me apoiariam e odiariam. Ideia difícil.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them. Clever or unkind — four years on the wall and I still can't tell.
    >>  ............................................
    pt  ...Na frente deles. Esperto ou cruel — quatro anos no muro e ainda não sei dizer.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and hate it afterwards. I've seen that trick work and cost the man his friends.
    >>  ............................................
    pt  Me apoiariam e odiariam depois. Já vi esse truque funcionar e custar os amigos ao homem.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them! That's either very clever or very unkind and I need a day on it.
    >>  ............................................
    pt  ...Na frente deles! Ou é muito esperto ou muito cruel e preciso de um dia pra pensar.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and hate that I'd used them. You've handed me a genuinely hard idea.
    >>  ............................................
    pt  Me apoiariam e odiariam ter sido usados. Você me deu uma ideia genuinamente difícil.
    >>  ............................................
  playful.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them! That's either very clever or very unkind and I need a day on it.
    >>  ............................................
    pt  ...Na frente deles! Ou é muito esperto ou muito cruel e preciso de um dia pra pensar.
    >>  ............................................
  playful.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and hate that I'd used them. You've handed me a genuinely hard idea.
    >>  ............................................
    pt  Me apoiariam e odiariam ter sido usados. Você me deu uma ideia genuinamente difícil.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them. Clever or unkind — four years on the wall and I still can't tell.
    >>  ............................................
    pt  ...Na frente deles. Esperto ou cruel — quatro anos no muro e ainda não sei dizer.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and hate it afterwards. I've seen that trick work and cost the man his friends.
    >>  ............................................
    pt  Me apoiariam e odiariam depois. Já vi esse truque funcionar e custar os amigos ao homem.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them. I'd be using people who trust me, and I'd know it.
    >>  ............................................
    pt  ...Na frente deles. Eu estaria usando gente que confia em mim, e eu saberia.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and hate that I'd used them. I don't want to be the man who does that.
    >>  ............................................
    pt  Me apoiariam e odiariam ter sido usados. Não quero ser o homem que faz isso.
    >>  ............................................
  shy.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them. Clever or unkind. I need a day.
    >>  ............................................
    pt  ...Na frente deles. Esperto ou cruel. Preciso de um dia.
    >>  ............................................
  shy.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and hate it. Hard idea.
    >>  ............................................
    pt  Me apoiariam e odiariam. Ideia difícil.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them! That's either very clever or very unkind and I need a day on it.
    >>  ............................................
    pt  ...Na frente deles! Ou é muito esperto ou muito cruel e preciso de um dia pra pensar.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and hate that I'd used them. You've handed me a genuinely hard idea.
    >>  ............................................
    pt  Me apoiariam e odiariam ter sido usados. Você me deu uma ideia genuinamente difícil.
    >>  ............................................
  witty.dialogue.conversations.work.prof.guard.future.encourage/1
    en  ...In front of them! That's either very clever or very unkind and I need a day on it.
    >>  ............................................
    pt  ...Na frente deles! Ou é muito esperto ou muito cruel e preciso de um dia pra pensar.
    >>  ............................................
  witty.dialogue.conversations.work.prof.guard.future.encourage/2
    en  They'd back me and hate that I'd used them. You've handed me a genuinely hard idea.
    >>  ............................................
    pt  Me apoiariam e odiariam ter sido usados. Você me deu uma ideia genuinamente difícil.
    >>  ............................................
```

</details>


### Button `ask_unnecessary` — "Would you really want to be unnecessary?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.guard.future` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.guard.future.ask_unnecessary` — accepted phrasings: "would you really want to be unnecessary"
  - the message must contain one of: `unnecessary`, `idle`
  - scored words: `unnecessary`(1.5), `want`(0.5), `idle`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.future.respond.ask_unnecessary
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.future.respond.ask_unnecessary   [40 chars]
    en  Would you really want to be unnecessary?
    >>  ............................................
    pt  Você quereria mesmo ser desnecessário?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.guard.future.ask_unnecessary`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.guard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where's the village weakest?" | "Quiet shifts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.future.ask_unnecessary
WHO    VILLAGER — what the player reads after pressing "Would you really want to be unnecessary?"
       spoken on: conversations.topic.work.guard.future.respond, button `ask_unnecessary`
       leaves the player on: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.future.ask_unnecessary`: the villager explains. Subject `work.guard.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.guard.future.ask_unnecessary/1   [76 chars]
    en  Every part of me except the part that wouldn't know what to do on a Tuesday.
    >>  ............................................
    pt  Toda parte de mim, exceto a que não saberia o que fazer numa terça.
    >>  ............................................
  dialogue.conversations.work.prof.guard.future.ask_unnecessary/2   [72 chars]
    en  Yes, and I'd be lost, %1$s, and both of those are true at the same time.
    >>  ............................................
    pt  Sim, e eu ficaria perdido, %1$s, e as duas coisas são verdade ao mesmo tempo.
    >>  ............................................
```


### Button `leave` — "I'll let you keep watch."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.guard.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.future.respond.leave   [24 chars]
    en  I'll let you keep watch.
    >>  ............................................
    pt  Vou deixar você de vigia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you keep watch."
       spoken on: conversations.topic.work.guard.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.left`: the villager accepts. Subject `work.guard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.guard.failing_kit.active.respond / leave; conversations.scene.work.guard.failing_kit.succeeded.respond / leave; conversations.scene.work.guard.followup / leave; conversations.scene.work.guard.night_sighting.blocked.respond / leave; conversations.scene.work.guard.night_sighting.succeeded.respond / leave; conversations.scene.work.guard.weak_point.blocked.respond / leave; conversations.scene.work.guard.weak_point.failed.respond / leave; conversations.scene.work.guard.weak_point.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.guard.failing_kit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.guard.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.guard` — e.g. "I hold the wall so everyone else gets to complain about smaller things. It's good work."


```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.guard.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.guard.respond   [23 chars]
    en  That's the wall and me.
    >>  ............................................
    pt  É o muro e eu.
    >>  ............................................
```


### Button `ask_hard` — "What's the worst part of a quiet shift?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.guard.identity` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.guard.hard` — accepted phrasings: "what's the worst part of a quiet shift"
  - the message must contain one of: `quiet`, `shift`, `boredom`
  - scored words: `quiet`(1.2), `shift`(1.5), `boredom`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.respond.ask_hard   [39 chars]
    en  What's the worst part of a quiet shift?
    >>  ............................................
    pt  Qual a pior parte de um turno tranquilo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.guard.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.guard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where's the village weakest?" | "Quiet shifts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.hard
WHO    VILLAGER — what the player reads after pressing "What's the worst part of a quiet shift?"
       spoken on: conversations.topic.work.guard.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.hard`: the villager explains. Subject `work.guard.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.guard.followup / ask_more
```

> Written out in full under **`conversations.scene.work.guard.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "Everyone here sleeps because you don't."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.guard.identity` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.guard.value` — accepted phrasings: "everyone here sleeps because you don't"
  - the message must contain one of: `sleeps`, `safe`
  - scored words: `sleeps`(1.5), `safe`(1.2), `watch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.respond.value   [39 chars]
    en  Everyone here sleeps because you don't.
    >>  ............................................
    pt  Todo mundo aqui dorme porque você não dorme.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.guard.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.guard.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.guard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where's the village weakest?" | "Quiet shifts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.value
WHO    VILLAGER — what the player reads after pressing "Everyone here sleeps because you don't."
       spoken on: conversations.topic.work.guard.respond, button `value`
       leaves the player on: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.value`: the villager accepts. Subject `work.guard.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.guard.value/1   [68 chars]
    en  That's the arrangement. I'd not swap it, though I'd take the thanks.
    >>  ............................................
    pt  É o combinado. Eu não trocaria, mas aceitaria o agradecimento.
    >>  ............................................
  dialogue.conversations.work.prof.guard.value/2   [79 chars]
    en  Aye. And they'll never know which night it mattered, which is how it should be.
    >>  ............................................
    pt  É. E eles nunca vão saber em qual noite importou, e é assim que tem que ser.
    >>  ............................................
```


### Button `challenge` — "Nothing ever happens here."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.guard.identity` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.guard.challenge` — accepted phrasings: "nothing ever happens here"
  - the message must contain one of: `nothing`, `happens`
  - scored words: `nothing`(1.5), `happens`(1.5), `ever`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.respond.challenge   [26 chars]
    en  Nothing ever happens here.
    >>  ............................................
    pt  Nunca acontece nada aqui.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.guard.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.guard.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.guard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where's the village weakest?" | "Quiet shifts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.challenge.landed
WHO    VILLAGER — what the player reads after pressing "Nothing ever happens here."
       spoken on: conversations.topic.work.guard.respond, button `challenge`
       leaves the player on: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.challenge.landed`: the villager resists. Subject `work.guard.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.guard.challenge.landed/1   [73 chars]
    en  That is the entire result of my work, %1$s. I'll take it as a compliment.
    >>  ............................................
    pt  É esse o resultado inteiro do meu trabalho, %1$s. Vou tomar como elogio.
    >>  ............................................
  dialogue.conversations.work.prof.guard.challenge.landed/2   [64 chars]
    en  Correct. Ask me why nothing happens and we'll be here till dawn.
    >>  ............................................
    pt  Correto. Me pergunte por que nada acontece e a gente fica aqui até o amanhecer.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.guard.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.guard.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.guard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where's the village weakest?" | "Quiet shifts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.challenge.stung
WHO    VILLAGER — what the player reads after pressing "Nothing ever happens here."
       spoken on: conversations.topic.work.guard.respond, button `challenge`
       leaves the player on: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.challenge.stung`: the villager resists. Subject `work.guard.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.guard.challenge.stung/1   [76 chars]
    en  ...Nothing happened last spring either. Ask the mason why he has a new door.
    >>  ............................................
    pt  ...Também não aconteceu nada na primavera passada. Pergunte ao pedreiro por que ele tem porta nova.
    >>  ............................................
  dialogue.conversations.work.prof.guard.challenge.stung/2   [52 chars]
    en  Nothing. Right. Stand a night and then say it, %1$s.
    >>  ............................................
    pt  Nada. Certo. Fique uma noite e depois diga isso, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you keep watch."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.guard.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.respond.leave   [24 chars]
    en  I'll let you keep watch.
    >>  ............................................
    pt  Vou deixar você de vigia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you keep watch."
       spoken on: conversations.topic.work.guard.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.left`: the villager accepts. Subject `work.guard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.guard.failing_kit.active.respond / leave; conversations.scene.work.guard.failing_kit.succeeded.respond / leave; conversations.scene.work.guard.followup / leave; conversations.scene.work.guard.night_sighting.blocked.respond / leave; conversations.scene.work.guard.night_sighting.succeeded.respond / leave; conversations.scene.work.guard.weak_point.blocked.respond / leave; conversations.scene.work.guard.weak_point.failed.respond / leave; conversations.scene.work.guard.weak_point.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.guard.failing_kit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.guard.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.guard.risk` — e.g. "The raid, four years back. I was at the wrong gate and I have never been at the wrong gate since."


```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.guard.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.guard.risk.respond   [30 chars]
    en  That's the weight of the post.
    >>  ............................................
    pt  É o peso do posto.
    >>  ............................................
```


### Button `ask_raid` — "What happened at the wrong gate?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.guard.risk` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.guard.risk.ask_raid` — accepted phrasings: "what happened at the wrong gate"
  - the message must contain one of: `gate`, `raid`
  - scored words: `gate`(1.5), `raid`(1.2), `happened`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.risk.respond.ask_raid
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.risk.respond.ask_raid   [32 chars]
    en  What happened at the wrong gate?
    >>  ............................................
    pt  O que aconteceu no portão errado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.guard.risk.ask_raid`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.guard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where's the village weakest?" | "Quiet shifts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.risk.ask_raid
WHO    VILLAGER — what the player reads after pressing "What happened at the wrong gate?"
       spoken on: conversations.topic.work.guard.risk.respond, button `ask_raid`
       leaves the player on: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.risk.ask_raid`: the villager explains. Subject `work.guard.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.guard.risk.ask_raid/1   [89 chars]
    en  Nothing. That's the point. I stood at a quiet gate while the noise was on the other side.
    >>  ............................................
    pt  Nada. É essa a questão. Fiquei num portão silencioso enquanto o barulho estava do outro lado.
    >>  ............................................
  dialogue.conversations.work.prof.guard.risk.ask_raid/2   [91 chars]
    en  I ran, and running takes ninety seconds, %1$s, and ninety seconds was the whole difference.
    >>  ............................................
    pt  Eu corri, e correr leva noventa segundos, %1$s, e noventa segundos foram toda a diferença.
    >>  ............................................
```


### Button `sympathise` — "You couldn't have been at both gates."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.guard.risk` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.guard.risk.sympathise` — accepted phrasings: "you couldn't have been at both gates"
  - the message must contain one of: `both`, `gates`, `blame`
  - scored words: `both`(1.5), `gates`(1.2), `blame`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.risk.respond.sympathise   [37 chars]
    en  You couldn't have been at both gates.
    >>  ............................................
    pt  Você não podia estar nos dois portões.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.guard.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.guard.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.guard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where's the village weakest?" | "Quiet shifts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "You couldn't have been at both gates."
       spoken on: conversations.topic.work.guard.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.risk.sympathise`: the villager accepts. Subject `work.guard.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.guard.risk.sympathise/1   [77 chars]
    en  ...No. Everyone says that and everyone is right and it has never once helped.
    >>  ............................................
    pt  ...Não. Todo mundo diz e todo mundo tem razão e nunca ajudou nem uma vez.
    >>  ............................................
  dialogue.conversations.work.prof.guard.risk.sympathise/2   [92 chars]
    en  That's true and it is not the same as innocent, %1$s. I've had four years on the difference.
    >>  ............................................
    pt  É verdade e não é a mesma coisa que inocente, %1$s. Tive quatro anos sobre a diferença.
    >>  ............................................
```


### Button `ask_since` — "What changed after?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.guard.risk` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.guard.risk.ask_since` — accepted phrasings: "what changed after"
  - the message must contain one of: `changed`, `after`, `since`
  - scored words: `changed`(1.5), `after`(1.0), `since`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.risk.respond.ask_since
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.risk.respond.ask_since   [19 chars]
    en  What changed after?
    >>  ............................................
    pt  O que mudou depois?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.guard.risk.ask_since`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.guard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where's the village weakest?" | "Quiet shifts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.risk.ask_since
WHO    VILLAGER — what the player reads after pressing "What changed after?"
       spoken on: conversations.topic.work.guard.risk.respond, button `ask_since`
       leaves the player on: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.risk.ask_since`: the villager explains. Subject `work.guard.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.guard.risk.ask_since/1   [101 chars]
    en  I walk instead of stand and I've a second pair of eyes on the far side. It cost me a year of arguing.
    >>  ............................................
    pt  Eu ando em vez de ficar parado e tenho um segundo par de olhos do outro lado. Custou um ano de discussão.
    >>  ............................................
  dialogue.conversations.work.prof.guard.risk.ask_since/2   [93 chars]
    en  The nitwit at the gate knows more than the mayor does, %1$s, and now I ask him. That changed.
    >>  ............................................
    pt  O bobo do portão sabe mais que o prefeito, %1$s, e agora eu pergunto a ele. Isso mudou.
    >>  ............................................
```


### Button `leave` — "I'll let you keep watch."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.guard.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.risk.respond.leave   [24 chars]
    en  I'll let you keep watch.
    >>  ............................................
    pt  Vou deixar você de vigia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you keep watch."
       spoken on: conversations.topic.work.guard.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.left`: the villager accepts. Subject `work.guard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.guard.failing_kit.active.respond / leave; conversations.scene.work.guard.failing_kit.succeeded.respond / leave; conversations.scene.work.guard.followup / leave; conversations.scene.work.guard.night_sighting.blocked.respond / leave; conversations.scene.work.guard.night_sighting.succeeded.respond / leave; conversations.scene.work.guard.weak_point.blocked.respond / leave; conversations.scene.work.guard.weak_point.failed.respond / leave; conversations.scene.work.guard.weak_point.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.guard.failing_kit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.guard.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.guard.task` — e.g. "Standing here. That's the whole of it and it is harder than it looks by about hour six."


```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.guard.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.guard.task.respond   [17 chars]
    en  That's the shift.
    >>  ............................................
    pt  É o turno.
    >>  ............................................
```


### Button `ask_hour` — "What happens at hour six?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.guard.task` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.guard.task.ask_hour` — accepted phrasings: "what happens at hour six"
  - the message must contain one of: `hour`, `six`, `tired`
  - scored words: `hour`(1.5), `six`(1.2), `tired`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.task.respond.ask_hour
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.task.respond.ask_hour   [25 chars]
    en  What happens at hour six?
    >>  ............................................
    pt  O que acontece na sexta hora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.guard.task.ask_hour`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.guard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where's the village weakest?" | "Quiet shifts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.task.ask_hour
WHO    VILLAGER — what the player reads after pressing "What happens at hour six?"
       spoken on: conversations.topic.work.guard.task.respond, button `ask_hour`
       leaves the player on: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.task.ask_hour`: the villager explains. Subject `work.guard.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.guard.task.ask_hour/1   [75 chars]
    en  Your eyes start telling you stories. That's when you walk instead of stand.
    >>  ............................................
    pt  Seus olhos começam a te contar histórias. É a hora de andar em vez de ficar parado.
    >>  ............................................
  dialogue.conversations.work.prof.guard.task.ask_hour/2   [73 chars]
    en  You get comfortable, %1$s. Comfortable is the actual danger of this post.
    >>  ............................................
    pt  Você fica confortável, %1$s. Confortável é o perigo real deste posto.
    >>  ............................................
```


### Button `offer_hands` — "I'll walk the perimeter with you."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.guard.task` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.guard.task.offer_hands` — accepted phrasings: "i'll walk the perimeter with you"
  - the message must contain one of: `perimeter`, `walk`
  - scored words: `perimeter`(1.5), `walk`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.task.respond.offer_hands   [33 chars]
    en  I'll walk the perimeter with you.
    >>  ............................................
    pt  Eu ando o perímetro com você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.guard.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.guard.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.guard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where's the village weakest?" | "Quiet shifts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I'll walk the perimeter with you."
       spoken on: conversations.topic.work.guard.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.task.offer_hands`: the villager accepts. Subject `work.guard.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.guard.task.offer_hands/1   [84 chars]
    en  ...Right. Then you take the outside of the wall and shout if the ground's disturbed.
    >>  ............................................
    pt  ...Certo. Então você fica do lado de fora da muralha e grita se o chão estiver mexido.
    >>  ............................................
  dialogue.conversations.work.prof.guard.task.offer_hands/2   [77 chars]
    en  Company on a perimeter walk is a rare offer, %1$s. Keep up and don't chatter.
    >>  ............................................
    pt  Companhia numa ronda é uma oferta rara, %1$s. Acompanhe e não tagarele.
    >>  ............................................
```


### Button `ask_children` — "Do you tell on the children?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.guard.task` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.guard.task.ask_children` — accepted phrasings: "do you tell on the children"
  - the message must contain one of: `children`, `tell`, `gate`
  - scored words: `children`(1.5), `tell`(1.2), `gate`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.task.respond.ask_children
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.task.respond.ask_children   [28 chars]
    en  Do you tell on the children?
    >>  ............................................
    pt  Você entrega as crianças?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.guard.task.ask_children`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.guard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where's the village weakest?" | "Quiet shifts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.task.ask_children
WHO    VILLAGER — what the player reads after pressing "Do you tell on the children?"
       spoken on: conversations.topic.work.guard.task.respond, button `ask_children`
       leaves the player on: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.task.ask_children`: the villager explains. Subject `work.guard.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.guard.task.ask_children/1   [93 chars]
    en  Never. I mend the gap and say nothing and they find another one, and that's how it should go.
    >>  ............................................
    pt  Nunca. Eu tapo a brecha e não digo nada e elas acham outra, e é assim que tem que ser.
    >>  ............................................
  dialogue.conversations.work.prof.guard.task.ask_children/2   [93 chars]
    en  I told once, when I was new. Their mother thanked me and the child hasn't spoken to me since.
    >>  ............................................
    pt  Entreguei uma vez, quando eu era novo. A mãe agradeceu e a criança não fala comigo desde então.
    >>  ............................................
```


### Button `leave` — "I'll let you keep watch."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.guard.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.task.respond.leave   [24 chars]
    en  I'll let you keep watch.
    >>  ............................................
    pt  Vou deixar você de vigia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you keep watch."
       spoken on: conversations.topic.work.guard.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.left`: the villager accepts. Subject `work.guard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.guard.failing_kit.active.respond / leave; conversations.scene.work.guard.failing_kit.succeeded.respond / leave; conversations.scene.work.guard.followup / leave; conversations.scene.work.guard.night_sighting.blocked.respond / leave; conversations.scene.work.guard.night_sighting.succeeded.respond / leave; conversations.scene.work.guard.weak_point.blocked.respond / leave; conversations.scene.work.guard.weak_point.failed.respond / leave; conversations.scene.work.guard.weak_point.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.guard.failing_kit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.guard.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.guard.village` — e.g. "Four years without a second raid. I don't get to claim that and I count it every single week."


```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.guard.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.guard.village.respond   [18 chars]
    en  That's the ledger.
    >>  ............................................
    pt  É esse o registro.
    >>  ............................................
```


### Button `ask_six` — "Why don't those six lock?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.guard.village` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.guard.village.ask_six` — accepted phrasings: "why don't those six lock"
  - the message must contain one of: `lock`, `six`, `doors`
  - scored words: `lock`(1.5), `six`(1.2), `doors`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.village.respond.ask_six
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.village.respond.ask_six   [25 chars]
    en  Why don't those six lock?
    >>  ............................................
    pt  Por que essas seis não trancam?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.guard.village.ask_six`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.guard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where's the village weakest?" | "Quiet shifts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.village.ask_six
WHO    VILLAGER — what the player reads after pressing "Why don't those six lock?"
       spoken on: conversations.topic.work.guard.village.respond, button `ask_six`
       leaves the player on: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.village.ask_six`: the villager explains. Subject `work.guard.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.guard.village.ask_six/1   [96 chars]
    en  Poverty, mostly, and pride in two cases. I've fixed three quietly and been caught doing it once.
    >>  ............................................
    pt  Pobreza, principalmente, e orgulho em dois casos. Consertei três em silêncio e fui pego uma vez.
    >>  ............................................
  dialogue.conversations.work.prof.guard.village.ask_six/2   [81 chars]
    en  Two are old people who'd be insulted by a new lock, %1$s. So I walk past instead.
    >>  ............................................
    pt  Duas são de idosos que se ofenderiam com fechadura nova, %1$s. Então eu passo por perto.
    >>  ............................................
```


### Button `say_thanks` — "Two rounds a night that nobody assigned you."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.guard.village` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.guard.village.say_thanks` — accepted phrasings: "two rounds a night that nobody assigned you"
  - the message must contain one of: `rounds`, `assigned`, `night`
  - scored words: `rounds`(1.5), `assigned`(1.5), `night`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.village.respond.say_thanks   [44 chars]
    en  Two rounds a night that nobody assigned you.
    >>  ............................................
    pt  Duas rondas por noite que ninguém te mandou fazer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.guard.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.guard.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.guard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where's the village weakest?" | "Quiet shifts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Two rounds a night that nobody assigned you."
       spoken on: conversations.topic.work.guard.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.village.say_thanks`: the villager accepts. Subject `work.guard.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.guard.village.say_thanks/1   [84 chars]
    en  ...It isn't in the description, no. I'd assumed nobody had noticed it was happening.
    >>  ............................................
    pt  ...Não está na descrição, não. Eu supunha que ninguém tinha reparado.
    >>  ............................................
  dialogue.conversations.work.prof.guard.village.say_thanks/2   [74 chars]
    en  Somebody's counted my rounds. That's — give me a moment, %1$s, that's new.
    >>  ............................................
    pt  Alguém contou minhas rondas. Isso é — me dê um momento, %1$s, isso é novo.
    >>  ............................................
```


### Button `ask_claim` — "Why can't you claim the four years?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.guard.village` · offered only once the villager has actually said `work:guard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.guard.village.ask_claim` — accepted phrasings: "why can't you claim the four years"
  - the message must contain one of: `claim`, `four`, `credit`
  - scored words: `claim`(1.5), `four`(1.0), `credit`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.village.respond.ask_claim
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.village.respond.ask_claim   [35 chars]
    en  Why can't you claim the four years?
    >>  ............................................
    pt  Por que você não pode reivindicar os quatro anos?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.guard.village.ask_claim`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.guard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Where's the village weakest?" | "Quiet shifts to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.village.ask_claim
WHO    VILLAGER — what the player reads after pressing "Why can't you claim the four years?"
       spoken on: conversations.topic.work.guard.village.respond, button `ask_claim`
       leaves the player on: conversations.topic.work.guard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.village.ask_claim`: the villager explains. Subject `work.guard.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:guard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.guard.village.ask_claim/1   [85 chars]
    en  Because nothing happening isn't proof of anything. It might just be four quiet years.
    >>  ............................................
    pt  Porque nada acontecer não prova nada. Podem ser só quatro anos calmos.
    >>  ............................................
  dialogue.conversations.work.prof.guard.village.ask_claim/2   [98 chars]
    en  Because the one time it mattered I was at the wrong gate, %1$s, and that arithmetic doesn't clear.
    >>  ............................................
    pt  Porque na única vez que importou eu estava no portão errado, %1$s, e essa conta não zera.
    >>  ............................................
```


### Button `leave` — "I'll let you keep watch."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.guard.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.guard.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.guard.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.guard.village.respond.leave   [24 chars]
    en  I'll let you keep watch.
    >>  ............................................
    pt  Vou deixar você de vigia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.guard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you keep watch."
       spoken on: conversations.topic.work.guard.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.guard.left`: the villager accepts. Subject `work.guard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.guard.failing_kit.active.respond / leave; conversations.scene.work.guard.failing_kit.succeeded.respond / leave; conversations.scene.work.guard.followup / leave; conversations.scene.work.guard.night_sighting.blocked.respond / leave; conversations.scene.work.guard.night_sighting.succeeded.respond / leave; conversations.scene.work.guard.weak_point.blocked.respond / leave; conversations.scene.work.guard.weak_point.failed.respond / leave; conversations.scene.work.guard.weak_point.succeeded.respond / leave …and 6 more
```

> Written out in full under **`conversations.scene.work.guard.failing_kit.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

