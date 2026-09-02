# Work talk with a archer

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.archer.arrow_shortage.blocked.respond`](#conversations-scene-work-archer-arrow-shortage-blocked-respond)
- [`conversations.scene.work.archer.arrow_shortage.succeeded.respond`](#conversations-scene-work-archer-arrow-shortage-succeeded-respond)
- [`conversations.scene.work.archer.followup`](#conversations-scene-work-archer-followup)
- [`conversations.scene.work.archer.missed_shot.active.respond`](#conversations-scene-work-archer-missed-shot-active-respond)
- [`conversations.scene.work.archer.missed_shot.succeeded.respond`](#conversations-scene-work-archer-missed-shot-succeeded-respond)
- [`conversations.scene.work.archer.teaching_a_child.active.respond`](#conversations-scene-work-archer-teaching-a-child-active-respond)
- [`conversations.scene.work.archer.teaching_a_child.succeeded.respond`](#conversations-scene-work-archer-teaching-a-child-succeeded-respond)
- [`conversations.topic.work.archer.craft.respond`](#conversations-topic-work-archer-craft-respond)
- [`conversations.topic.work.archer.followup`](#conversations-topic-work-archer-followup)
- [`conversations.topic.work.archer.future.respond`](#conversations-topic-work-archer-future-respond)
- [`conversations.topic.work.archer.respond`](#conversations-topic-work-archer-respond)
- [`conversations.topic.work.archer.risk.respond`](#conversations-topic-work-archer-risk-respond)
- [`conversations.topic.work.archer.task.respond`](#conversations-topic-work-archer-task-respond)
- [`conversations.topic.work.archer.village.respond`](#conversations-topic-work-archer-village-respond)

---

## `conversations.scene.work.archer.arrow_shortage.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.archer.arrow_shortage.blocked` — e.g. "I have %2$s, so I have been shooting at %3$s with arrows I would be ashamed to hand anyone."


```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.arrow_shortage.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.archer.arrow_shortage.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.archer.arrow_shortage.blocked.respond   [11 chars]
    en  The quiver.
    >>  ............................................
    pt  A aljava.
    >>  ............................................
```


### Button `ask_how_bad` — "How much difference does that make?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.archer.arrow_shortage.blocked` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.archer.arrow_shortage.blocked.ask_how_bad` — accepted phrasings: "how much difference does that make"; "how much difference does that make"; "does the fletching change the flight much"
  - the message must contain one of: `difference`, `fletching`, `flight`
  - scored words: `difference`(1.8), `fletching`(1.8), `flight`(1.8), `much`(0.8), `does`(0.8), `make`(0.8), `change`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.arrow_shortage.blocked.respond.ask_how_bad
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.archer.arrow_shortage.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.archer.arrow_shortage.blocked.respond.ask_how_bad   [35 chars]
    en  How much difference does that make?
    >>  ............................................
    pt  Quanta diferença isso faz?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.archer.supply`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.archer.arrow_shortage"}
- Then opens: `conversations.scene.work.archer.followup`
- …where the player's next choices will be: "What's the hardest part of a long shot?" | "I'll leave you to the butts."

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.arrow_shortage.blocked.explained
WHO    VILLAGER — what the player reads after pressing "How much difference does that make?"
       spoken on: conversations.scene.work.archer.arrow_shortage.blocked.respond, button `ask_how_bad`
       leaves the player on: conversations.scene.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.arrow_shortage.blocked.explained`: the villager explains. Subject `work.archer.supply`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.archer.arrow_shortage.blocked.explained/1   [130 chars]
    en  At ten paces, none. At the far mark on %2$s, the width of a person. That is the entire difference between a story and an accident.
    >>  ............................................
    pt  A dez passos, nenhuma. Na marca longe de %2$s, a largura de uma pessoa. É essa a diferença inteira entre uma história e um acidente.
    >>  ............................................
  dialogue.conversations.scene.work.archer.arrow_shortage.blocked.explained/2   [136 chars]
    en  It moves the group. Six arrows that would sit in a hand's width sit in a hat's width instead, and one of the six goes wherever it likes.
    >>  ............................................
    pt  Espalha o agrupamento. Seis flechas que caberiam num palmo passam a caber num chapéu, e uma das seis vai para onde bem entender.
    >>  ............................................
  dialogue.conversations.scene.work.archer.arrow_shortage.blocked.explained/3   [119 chars]
    en  Enough that I have started calling my misses honestly. If I blamed the wind every time, I would learn nothing all year.
    >>  ............................................
    pt  O bastante para eu ter passado a admitir meus erros com honestidade. Se eu culpasse o vento toda vez, não aprenderia nada o ano inteiro.
    >>  ............................................
```


### Button `offer_feathers` — "I'll bring you feathers."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.archer.arrow_shortage.blocked` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.archer.arrow_shortage.blocked.offer_feathers` — accepted phrasings: "ill bring you feathers"; "i can bring you feathers"; "let me find some feathers for that"
  - the message must contain one of: `feathers`, `feather`
  - scored words: `feathers`(1.8), `feather`(1.8), `ill`(0.8), `bring`(0.8), `let`(0.8), `find`(0.8), `some`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.arrow_shortage.blocked.respond.offer_feathers
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.archer.arrow_shortage.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.archer.arrow_shortage.blocked.respond.offer_feathers   [24 chars]
    en  I'll bring you feathers.
    >>  ............................................
    pt  Vou te trazer penas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.archer.shortage.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.archer.supply`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.arrow_shortage", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.archer.arrow_shortage", "obligation": "commitment:work.archer.bring_feathers"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.archer.bring_feathers"}
- Then opens: `conversations.scene.work.archer.followup`
- …where the player's next choices will be: "What's the hardest part of a long shot?" | "I'll leave you to the butts."

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.arrow_shortage.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you feathers."
       spoken on: conversations.scene.work.archer.arrow_shortage.blocked.respond, button `offer_feathers`
       leaves the player on: conversations.scene.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.arrow_shortage.blocked.accepted`: the villager accepts. Subject `work.archer.supply`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.archer.arrow_shortage.blocked.accepted/1   [114 chars]
    en  Then I spend an evening cutting and binding and by morning I am an archer again instead of a person holding a bow.
    >>  ............................................
    pt  Então passo uma noite cortando e amarrando e de manhã volto a ser arqueira, em vez de uma pessoa segurando um arco.
    >>  ............................................
  dialogue.conversations.scene.work.archer.arrow_shortage.blocked.accepted/2   [101 chars]
    en  Yes, and bring the grey ones if you have a choice. They are stiffer and they forgive a bad glue line.
    >>  ............................................
    pt  Sim, e traga as cinzentas se puder escolher. São mais firmes e perdoam uma linha de cola mal feita.
    >>  ............................................
  dialogue.conversations.scene.work.archer.arrow_shortage.blocked.accepted/3   [95 chars]
    en  That is four days of work you have just handed me back. I will not pretend it is a small thing.
    >>  ............................................
    pt  São quatro dias de trabalho que você acabou de me devolver. Não vou fingir que é pouca coisa.
    >>  ............................................
```


### Button `suggest_make_do` — "Shoot closer until you're resupplied."

*stance family `candor` · tone `plain` · outcome `resisted` · answers the beat(s) `work.archer.arrow_shortage.blocked` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.archer.arrow_shortage.blocked.suggest_make_do` — accepted phrasings: "shoot closer until youre resupplied"; "shoot closer until you are resupplied"; "practise at short range for now"
  - the message must contain one of: `closer`, `short`, `resupplied`
  - scored words: `closer`(1.8), `short`(1.8), `resupplied`(1.8), `shoot`(0.8), `until`(0.8), `youre`(0.8), `practise`(0.8), `range`(0.8), `now`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.arrow_shortage.blocked.respond.suggest_make_do
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.archer.arrow_shortage.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.archer.arrow_shortage.blocked.respond.suggest_make_do   [37 chars]
    en  Shoot closer until you're resupplied.
    >>  ............................................
    pt  Atire mais perto até se reabastecer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +1  _(recorded under topic `work.archer.supply`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.archer.arrow_shortage"}
- Then opens: `conversations.scene.work.archer.followup`
- …where the player's next choices will be: "What's the hardest part of a long shot?" | "I'll leave you to the butts."

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.arrow_shortage.blocked.resisted
WHO    VILLAGER — what the player reads after pressing "Shoot closer until you're resupplied."
       spoken on: conversations.scene.work.archer.arrow_shortage.blocked.respond, button `suggest_make_do`
       leaves the player on: conversations.scene.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.arrow_shortage.blocked.resisted`: the villager resists. Subject `work.archer.supply`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.archer.arrow_shortage.blocked.resisted/1   [118 chars]
    en  That is sensible and I hate it. Short practice teaches short habits, and the habits are the only thing I actually own.
    >>  ............................................
    pt  É sensato e eu detesto. Prática curta ensina hábitos curtos, e os hábitos são a única coisa que eu de fato tenho.
    >>  ............................................
  dialogue.conversations.scene.work.archer.arrow_shortage.blocked.resisted/2   [84 chars]
    en  I could. Then in a month I would be very good at a distance nobody ever needs me at.
    >>  ............................................
    pt  Poderia. Aí, em um mês, eu seria ótima numa distância em que ninguém nunca precisa de mim.
    >>  ............................................
  dialogue.conversations.scene.work.archer.arrow_shortage.blocked.resisted/3   [119 chars]
    en  Half right. I will shoot close for form and stay off the long mark, and I will still be sour about it on the walk home.
    >>  ............................................
    pt  Meio certo. Vou atirar perto para cuidar da forma e ficar longe da marca longa, e ainda vou voltar para casa emburrada.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the butts."

*stance family `exit` · tone `plain` · answers the beat(s) `work.archer.arrow_shortage.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.arrow_shortage.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.archer.arrow_shortage.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.archer.arrow_shortage.blocked.respond.leave   [35 chars]
    en  I'll let you get back to the butts.
    >>  ............................................
    pt  Vou deixar você voltar ao alvo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the butts."
       spoken on: conversations.scene.work.archer.arrow_shortage.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.left`: the villager accepts. Subject `work.archer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.archer.arrow_shortage.succeeded.respond / leave; conversations.scene.work.archer.followup / leave; conversations.scene.work.archer.missed_shot.active.respond / leave; conversations.scene.work.archer.missed_shot.succeeded.respond / leave; conversations.scene.work.archer.teaching_a_child.active.respond / leave; conversations.scene.work.archer.teaching_a_child.succeeded.respond / leave; conversations.topic.work.archer.craft.respond / leave; conversations.topic.work.archer.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.archer.leave/1   [29 chars]
    en  Aye. It doesn't watch itself.
    >>  ............................................
    pt  É. Ela não se vigia sozinha.
    >>  ............................................
  dialogue.conversations.work.prof.archer.leave/2   [48 chars]
    en  Off you go, %1$s. I'll see you leave, obviously.
    >>  ............................................
    pt  Pode ir, %1$s. Vou ver você indo, obviamente.
    >>  ............................................
```

---


## `conversations.scene.work.archer.arrow_shortage.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.archer.arrow_shortage.succeeded` — e.g. "Forty new arrows, all matched, and the group at %2$s tightened the first afternoon."


```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.arrow_shortage.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.archer.arrow_shortage.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.archer.arrow_shortage.succeeded.respond   [18 chars]
    en  The quiver, since.
    >>  ............................................
    pt  A aljava, depois disso.
    >>  ............................................
```


### Button `ask_the_feeling` — "What does a good group feel like?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.archer.arrow_shortage.succeeded` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.archer.arrow_shortage.succeeded.ask_the_feeling` — accepted phrasings: "what does a good group feel like"; "what does a good group feel like"; "describe a tight group to me"
  - the message must contain one of: `group`, `tight`, `feel`
  - scored words: `group`(1.8), `tight`(1.8), `feel`(1.8), `does`(0.8), `good`(0.8), `like`(0.8), `describe`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.arrow_shortage.succeeded.respond.ask_the_feeling
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.archer.arrow_shortage.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.archer.arrow_shortage.succeeded.respond.ask_the_feeling   [33 chars]
    en  What does a good group feel like?
    >>  ............................................
    pt  Como é a sensação de um bom agrupamento?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3  _(recorded under topic `work.archer.supply`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.archer.arrow_shortage"}
- Then opens: `conversations.scene.work.archer.followup`
- …where the player's next choices will be: "What's the hardest part of a long shot?" | "I'll leave you to the butts."

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.arrow_shortage.succeeded.described
WHO    VILLAGER — what the player reads after pressing "What does a good group feel like?"
       spoken on: conversations.scene.work.archer.arrow_shortage.succeeded.respond, button `ask_the_feeling`
       leaves the player on: conversations.scene.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.arrow_shortage.succeeded.described`: the villager explains. Subject `work.archer.supply`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.archer.arrow_shortage.succeeded.described/1   [130 chars]
    en  Boring. That is the honest word. Nothing surprises you. Six times in a row nothing surprises you, and that is the whole joy of it.
    >>  ............................................
    pt  Entediante. Essa é a palavra honesta. Nada te surpreende. Seis vezes seguidas nada te surpreende, e a alegria toda é essa.
    >>  ............................................
  dialogue.conversations.scene.work.archer.arrow_shortage.succeeded.described/2   [135 chars]
    en  You stop hearing yourself think about it. The arm does it and you are just standing there watching, like a spectator at your own hands.
    >>  ............................................
    pt  Você para de se ouvir pensando nisso. O braço faz e você só assiste, como plateia das próprias mãos.
    >>  ............................................
  dialogue.conversations.scene.work.archer.arrow_shortage.succeeded.described/3   [128 chars]
    en  It feels like the third arrow already knows where the first two went, which is nonsense, and is also exactly what it feels like.
    >>  ............................................
    pt  É como se a terceira flecha já soubesse onde as duas primeiras foram, o que é absurdo, e é exatamente a sensação.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the butts."

*stance family `exit` · tone `plain` · answers the beat(s) `work.archer.arrow_shortage.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.arrow_shortage.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.archer.arrow_shortage.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.archer.arrow_shortage.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the butts.
    >>  ............................................
    pt  Vou deixar você voltar ao alvo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the butts."
       spoken on: conversations.scene.work.archer.arrow_shortage.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.left`: the villager accepts. Subject `work.archer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.archer.arrow_shortage.blocked.respond / leave; conversations.scene.work.archer.followup / leave; conversations.scene.work.archer.missed_shot.active.respond / leave; conversations.scene.work.archer.missed_shot.succeeded.respond / leave; conversations.scene.work.archer.teaching_a_child.active.respond / leave; conversations.scene.work.archer.teaching_a_child.succeeded.respond / leave; conversations.topic.work.archer.craft.respond / leave; conversations.topic.work.archer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.archer.arrow_shortage.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.archer.followup`

**Reached from 11 route(s):** `conversations.scene.work.archer.arrow_shortage.blocked.respond` / `ask_how_bad`; `conversations.scene.work.archer.arrow_shortage.blocked.respond` / `offer_feathers`; `conversations.scene.work.archer.arrow_shortage.blocked.respond` / `suggest_make_do`; `conversations.scene.work.archer.arrow_shortage.succeeded.respond` / `ask_the_feeling`; `conversations.scene.work.archer.missed_shot.active.respond` / `ask_why`; `conversations.scene.work.archer.missed_shot.active.respond` / `let_it_go`; `conversations.scene.work.archer.missed_shot.active.respond` / `urge_practice`; `conversations.scene.work.archer.missed_shot.succeeded.respond` / `note_the_discipline`; `conversations.scene.work.archer.teaching_a_child.active.respond` / `ask_approach`; `conversations.scene.work.archer.teaching_a_child.active.respond` / `advise_patience`; `conversations.scene.work.archer.teaching_a_child.succeeded.respond` / `praise_teaching`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.archer.arrow_shortage.blocked.accepted` — e.g. "Then I spend an evening cutting and binding and by morning I am an archer again instead of a person holding a bow."
- `conversations.scene.work.archer.arrow_shortage.blocked.explained` — e.g. "At ten paces, none. At the far mark on %2$s, the width of a person. That is the entire difference between a story and an accident."
- `conversations.scene.work.archer.arrow_shortage.blocked.resisted` — e.g. "That is sensible and I hate it. Short practice teaches short habits, and the habits are the only thing I actually own."
- `conversations.scene.work.archer.arrow_shortage.succeeded.described` — e.g. "Boring. That is the honest word. Nothing surprises you. Six times in a row nothing surprises you, and that is the whole joy of it."
- `conversations.scene.work.archer.missed_shot.active.agreed` — e.g. "That is the only cure and I resent how simple it is. Six arrows tomorrow, same distance, no audience."
- `conversations.scene.work.archer.missed_shot.active.diagnosed` — e.g. "I hurried the loose. That is all it ever is. Everything else is decoration on hurrying the loose."
- `conversations.scene.work.archer.missed_shot.active.softened` — e.g. "I know. I am better at saying that to other people than at hearing it, which most archers would admit if you asked kindly."
- `conversations.scene.work.archer.missed_shot.succeeded.acknowledged` — e.g. "It took about an hour, which is less than it took to feel bad about it, and I would like somebody to explain that to me."
- `conversations.scene.work.archer.teaching_a_child.active.accepted` — e.g. "A season. Yes. I took three, and somebody was patient with me, and I have been forgetting to pass that on."
- `conversations.scene.work.archer.teaching_a_child.active.explained` — e.g. "Lighter bow, closer mark, and I say one thing per session. One. It is the hardest rule I have ever kept."
- `conversations.scene.work.archer.teaching_a_child.succeeded.deflected` — e.g. "They taught them. I stood nearby and said one useful sentence a week. I have made peace with that being the job."


```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.archer.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.archer.followup   [14 chars]
    en  Anything more?
    >>  ............................................
    pt  Mais alguma?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of a long shot?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.archer.*` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.archer.followup.ask_more` — accepted phrasings: "whats the hardest part of a long shot"; "what is the hardest part of a long shot"; "hardest thing about a long shot"
  - the message must contain one of: `hardest`, `shot`
  - scored words: `hardest`(1.8), `shot`(1.8), `whats`(0.8), `part`(0.8), `long`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.archer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.archer.followup.ask_more   [39 chars]
    en  What's the hardest part of a long shot?
    >>  ............................................
    pt  Qual é a parte mais difícil de um tiro longo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.archer.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.archer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you seen lately?" | "Clear sightlines."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of a long shot?"
       spoken on: conversations.scene.work.archer.followup, button `ask_more`
       leaves the player on: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.hard`: the villager explains. Subject `work.archer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.archer.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.archer.hard/1   [68 chars]
    en  Not being certain. An arrow is a decision you can't take back, %1$s.
    >>  ............................................
    pt  Não ter certeza. Uma flecha é uma decisão que não dá pra desfazer, %1$s.
    >>  ............................................
  dialogue.conversations.work.prof.archer.hard/2   [87 chars]
    en  Doubt. One breath of it and I lower the bow. That breath has been right more than once.
    >>  ............................................
    pt  Dúvida. Um sopro dela e eu baixo o arco. Esse sopro esteve certo mais de uma vez.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the butts."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.archer.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.archer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.archer.followup.leave   [28 chars]
    en  I'll leave you to the butts.
    >>  ............................................
    pt  Vou deixar você com o alvo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the butts."
       spoken on: conversations.scene.work.archer.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.left`: the villager accepts. Subject `work.archer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.archer.arrow_shortage.blocked.respond / leave; conversations.scene.work.archer.arrow_shortage.succeeded.respond / leave; conversations.scene.work.archer.missed_shot.active.respond / leave; conversations.scene.work.archer.missed_shot.succeeded.respond / leave; conversations.scene.work.archer.teaching_a_child.active.respond / leave; conversations.scene.work.archer.teaching_a_child.succeeded.respond / leave; conversations.topic.work.archer.craft.respond / leave; conversations.topic.work.archer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.archer.arrow_shortage.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.archer.missed_shot.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.archer.missed_shot.active` — e.g. "I missed %2$s at forty paces and I have replayed it about nine hundred times since."


```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.missed_shot.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.archer.missed_shot.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.archer.missed_shot.active.respond   [9 chars]
    en  The miss.
    >>  ............................................
    pt  O erro.
    >>  ............................................
```


### Button `ask_why` — "Do you know what went wrong?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.archer.missed_shot.active` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.archer.missed_shot.active.ask_why` — accepted phrasings: "do you know what went wrong"; "do you know what went wrong"; "have you worked out the cause"
  - the message must contain one of: `wrong`, `cause`, `worked`
  - scored words: `wrong`(1.8), `cause`(1.8), `worked`(1.8), `know`(0.8), `went`(0.8), `out`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.missed_shot.active.respond.ask_why
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.archer.missed_shot.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.archer.missed_shot.active.respond.ask_why   [28 chars]
    en  Do you know what went wrong?
    >>  ............................................
    pt  Você sabe o que deu errado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.archer.missed_shot`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.archer.missed_shot"}
- Then opens: `conversations.scene.work.archer.followup`
- …where the player's next choices will be: "What's the hardest part of a long shot?" | "I'll leave you to the butts."

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.missed_shot.active.diagnosed
WHO    VILLAGER — what the player reads after pressing "Do you know what went wrong?"
       spoken on: conversations.scene.work.archer.missed_shot.active.respond, button `ask_why`
       leaves the player on: conversations.scene.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.missed_shot.active.diagnosed`: the villager explains. Subject `work.archer.missed_shot`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.archer.missed_shot.active.diagnosed/1   [97 chars]
    en  I hurried the loose. That is all it ever is. Everything else is decoration on hurrying the loose.
    >>  ............................................
    pt  Apressei a soltura. É sempre só isso. Todo o resto é enfeite em cima de apressar a soltura.
    >>  ............................................
  dialogue.conversations.scene.work.archer.missed_shot.active.diagnosed/2   [108 chars]
    en  My back hand crept forward a finger's width. One finger, forty paces, and it becomes a hand's width of miss.
    >>  ............................................
    pt  Minha mão de trás avançou a largura de um dedo. Um dedo, quarenta passos, e vira um palmo de erro.
    >>  ............................................
  dialogue.conversations.scene.work.archer.missed_shot.active.diagnosed/3   [110 chars]
    en  I have three explanations and I trust the least flattering one, which is that I wanted it too much and rushed.
    >>  ............................................
    pt  Tenho três explicações e confio na menos lisonjeira, que é a de que eu quis demais e me apressei.
    >>  ............................................
```


### Button `let_it_go` — "One arrow isn't your whole record."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.archer.missed_shot.active` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.archer.missed_shot.active.let_it_go` — accepted phrasings: "one arrow isnt your whole record"; "one arrow is not your whole record"; "your record is more than a single shot"
  - the message must contain one of: `record`, `arrow`, `single`
  - scored words: `record`(1.8), `arrow`(1.8), `single`(1.8), `one`(0.8), `isnt`(0.8), `whole`(0.8), `more`(0.8), `than`(0.8), `shot`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.missed_shot.active.respond.let_it_go
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.archer.missed_shot.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.archer.missed_shot.active.respond.let_it_go   [34 chars]
    en  One arrow isn't your whole record.
    >>  ............................................
    pt  Uma flecha não é todo o seu histórico.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `work.archer.missed_shot`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.archer.missed_shot"}
- Then opens: `conversations.scene.work.archer.followup`
- …where the player's next choices will be: "What's the hardest part of a long shot?" | "I'll leave you to the butts."

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.missed_shot.active.softened
WHO    VILLAGER — what the player reads after pressing "One arrow isn't your whole record."
       spoken on: conversations.scene.work.archer.missed_shot.active.respond, button `let_it_go`
       leaves the player on: conversations.scene.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.missed_shot.active.softened`: the villager accepts. Subject `work.archer.missed_shot`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.archer.missed_shot.active.softened/1   [122 chars]
    en  I know. I am better at saying that to other people than at hearing it, which most archers would admit if you asked kindly.
    >>  ............................................
    pt  Eu sei. Sou melhor em dizer isso aos outros do que em ouvir, coisa que quase toda arqueira admitiria se você perguntasse com jeito.
    >>  ............................................
  dialogue.conversations.scene.work.archer.missed_shot.active.softened/2   [112 chars]
    en  True, and I will believe it by the weekend. The first two days after a miss are simply not available for reason.
    >>  ............................................
    pt  Verdade, e vou acreditar até o fim de semana. Os dois primeiros dias depois de um erro simplesmente não aceitam razão.
    >>  ............................................
  dialogue.conversations.scene.work.archer.missed_shot.active.softened/3   [123 chars]
    en  Thank you. I keep a tally, and the tally says you are right, and the tally has never once made me feel better on the night.
    >>  ............................................
    pt  Obrigada. Eu faço a conta, e a conta diz que você tem razão, e a conta nunca me fez sentir melhor na mesma noite.
    >>  ............................................
```


### Button `urge_practice` — "Then go and shoot it again tomorrow."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.archer.missed_shot.active` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.archer.missed_shot.active.urge_practice` — accepted phrasings: "then go and shoot it again tomorrow"; "go and shoot it again tomorrow"; "put another six arrows through it"
  - the message must contain one of: `again`, `another`, `tomorrow`
  - scored words: `again`(1.8), `another`(1.8), `tomorrow`(1.8), `shoot`(0.8), `put`(0.8), `six`(0.8), `arrows`(0.8), `through`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.missed_shot.active.respond.urge_practice
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.archer.missed_shot.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.archer.missed_shot.active.respond.urge_practice   [36 chars]
    en  Then go and shoot it again tomorrow.
    >>  ............................................
    pt  Então vá atirar de novo amanhã.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.archer.missed_shot`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.archer.missed_shot"}
- Then opens: `conversations.scene.work.archer.followup`
- …where the player's next choices will be: "What's the hardest part of a long shot?" | "I'll leave you to the butts."

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.missed_shot.active.agreed
WHO    VILLAGER — what the player reads after pressing "Then go and shoot it again tomorrow."
       spoken on: conversations.scene.work.archer.missed_shot.active.respond, button `urge_practice`
       leaves the player on: conversations.scene.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.missed_shot.active.agreed`: the villager accepts. Subject `work.archer.missed_shot`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.archer.missed_shot.active.agreed/1   [101 chars]
    en  That is the only cure and I resent how simple it is. Six arrows tomorrow, same distance, no audience.
    >>  ............................................
    pt  É a única cura e me irrita como é simples. Seis flechas amanhã, mesma distância, sem plateia.
    >>  ............................................
  dialogue.conversations.scene.work.archer.missed_shot.active.agreed/2   [125 chars]
    en  Yes. The longer I leave it the larger it gets, and by next week it would be a thing I am afraid of rather than a thing I did.
    >>  ............................................
    pt  Sim. Quanto mais eu deixar, maior fica, e na semana que vem viraria uma coisa de que tenho medo em vez de uma coisa que fiz.
    >>  ............................................
  dialogue.conversations.scene.work.archer.missed_shot.active.agreed/3   [94 chars]
    en  Right. And I will do it badly first, which is the part people skip when they give that advice.
    >>  ............................................
    pt  Certo. E vou fazer mal na primeira vez, que é a parte que as pessoas pulam quando dão esse conselho.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the butts."

*stance family `exit` · tone `plain` · answers the beat(s) `work.archer.missed_shot.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.missed_shot.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.archer.missed_shot.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.archer.missed_shot.active.respond.leave   [35 chars]
    en  I'll let you get back to the butts.
    >>  ............................................
    pt  Vou deixar você voltar ao alvo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the butts."
       spoken on: conversations.scene.work.archer.missed_shot.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.left`: the villager accepts. Subject `work.archer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.archer.arrow_shortage.blocked.respond / leave; conversations.scene.work.archer.arrow_shortage.succeeded.respond / leave; conversations.scene.work.archer.followup / leave; conversations.scene.work.archer.missed_shot.succeeded.respond / leave; conversations.scene.work.archer.teaching_a_child.active.respond / leave; conversations.scene.work.archer.teaching_a_child.succeeded.respond / leave; conversations.topic.work.archer.craft.respond / leave; conversations.topic.work.archer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.archer.arrow_shortage.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.archer.missed_shot.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.archer.missed_shot.succeeded` — e.g. "I went back out and put six through the same mark. %2$s is no longer a thing that lives in my head."


```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.missed_shot.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.archer.missed_shot.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.archer.missed_shot.succeeded.respond   [10 chars]
    en  That shot.
    >>  ............................................
    pt  Aquele tiro.
    >>  ............................................
```


### Button `note_the_discipline` — "That took discipline."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.archer.missed_shot.succeeded` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.archer.missed_shot.succeeded.note_the_discipline` — accepted phrasings: "that took discipline"; "that took real discipline"; "going back out took discipline"
  - the message must contain one of: `discipline`, `took`
  - scored words: `discipline`(1.8), `took`(1.8), `real`(0.8), `going`(0.8), `back`(0.8), `out`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.missed_shot.succeeded.respond.note_the_discipline
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.archer.missed_shot.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.archer.missed_shot.succeeded.respond.note_the_discipline   [21 chars]
    en  That took discipline.
    >>  ............................................
    pt  Isso exigiu disciplina.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.archer.missed_shot`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.archer.missed_shot"}
- Then opens: `conversations.scene.work.archer.followup`
- …where the player's next choices will be: "What's the hardest part of a long shot?" | "I'll leave you to the butts."

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.missed_shot.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "That took discipline."
       spoken on: conversations.scene.work.archer.missed_shot.succeeded.respond, button `note_the_discipline`
       leaves the player on: conversations.scene.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.missed_shot.succeeded.acknowledged`: the villager accepts. Subject `work.archer.missed_shot`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.archer.missed_shot.succeeded.acknowledged/1   [120 chars]
    en  It took about an hour, which is less than it took to feel bad about it, and I would like somebody to explain that to me.
    >>  ............................................
    pt  Exigiu cerca de uma hora, menos do que levou para me sentir mal, e eu gostaria que alguém me explicasse isso.
    >>  ............................................
  dialogue.conversations.scene.work.archer.missed_shot.succeeded.acknowledged/2   [79 chars]
    en  Thank you. Discipline is a grand word for walking to a field while still cross.
    >>  ............................................
    pt  Obrigada. Disciplina é uma palavra grandiosa para caminhar até um campo ainda irritada.
    >>  ............................................
  dialogue.conversations.scene.work.archer.missed_shot.succeeded.acknowledged/3   [97 chars]
    en  It is the only skill I am sure I have. Everything else about the bow could be luck and good eyes.
    >>  ............................................
    pt  É a única habilidade que tenho certeza de ter. Todo o resto do arco pode ser sorte e boa vista.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the butts."

*stance family `exit` · tone `plain` · answers the beat(s) `work.archer.missed_shot.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.missed_shot.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.archer.missed_shot.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.archer.missed_shot.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the butts.
    >>  ............................................
    pt  Vou deixar você voltar ao alvo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the butts."
       spoken on: conversations.scene.work.archer.missed_shot.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.left`: the villager accepts. Subject `work.archer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.archer.arrow_shortage.blocked.respond / leave; conversations.scene.work.archer.arrow_shortage.succeeded.respond / leave; conversations.scene.work.archer.followup / leave; conversations.scene.work.archer.missed_shot.active.respond / leave; conversations.scene.work.archer.teaching_a_child.active.respond / leave; conversations.scene.work.archer.teaching_a_child.succeeded.respond / leave; conversations.topic.work.archer.craft.respond / leave; conversations.topic.work.archer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.archer.arrow_shortage.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.archer.teaching_a_child.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.archer.teaching_a_child.active` — e.g. "One of the children wants to learn and has %2$s, and correcting it wrong will put them off for life."


```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.teaching_a_child.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.archer.teaching_a_child.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.archer.teaching_a_child.active.respond   [12 chars]
    en  The lessons.
    >>  ............................................
    pt  As aulas.
    >>  ............................................
```


### Button `ask_approach` — "How are you teaching it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.archer.teaching_a_child.active` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.archer.teaching_a_child.active.ask_approach` — accepted phrasings: "how are you teaching it"; "how are you teaching that"; "what is your method with them"
  - the message must contain one of: `teaching`, `method`
  - scored words: `teaching`(1.8), `method`(1.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.teaching_a_child.active.respond.ask_approach
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.archer.teaching_a_child.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.archer.teaching_a_child.active.respond.ask_approach   [24 chars]
    en  How are you teaching it?
    >>  ............................................
    pt  Como você está ensinando?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.archer.teaching`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.archer.teaching_a_child"}
- Then opens: `conversations.scene.work.archer.followup`
- …where the player's next choices will be: "What's the hardest part of a long shot?" | "I'll leave you to the butts."

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.teaching_a_child.active.explained
WHO    VILLAGER — what the player reads after pressing "How are you teaching it?"
       spoken on: conversations.scene.work.archer.teaching_a_child.active.respond, button `ask_approach`
       leaves the player on: conversations.scene.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.teaching_a_child.active.explained`: the villager explains. Subject `work.archer.teaching`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.archer.teaching_a_child.active.explained/1   [104 chars]
    en  Lighter bow, closer mark, and I say one thing per session. One. It is the hardest rule I have ever kept.
    >>  ............................................
    pt  Arco mais leve, alvo mais perto, e eu digo uma coisa por sessão. Uma. É a regra mais difícil que já cumpri.
    >>  ............................................
  dialogue.conversations.scene.work.archer.teaching_a_child.active.explained/2   [100 chars]
    en  I let them shoot badly for a while first, so that the correction arrives after they already want it.
    >>  ............................................
    pt  Deixo atirarem mal por um tempo primeiro, para que a correção chegue depois de já quererem ela.
    >>  ............................................
  dialogue.conversations.scene.work.archer.teaching_a_child.active.explained/3   [110 chars]
    en  Mostly I stand where they can see my hands and shut up. Children copy what you do and argue with what you say.
    >>  ............................................
    pt  Basicamente fico onde possam ver minhas mãos e me calo. Criança copia o que você faz e discute o que você fala.
    >>  ............................................
```


### Button `advise_patience` — "Give them a whole season before you judge it."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.archer.teaching_a_child.active` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.archer.teaching_a_child.active.advise_patience` — accepted phrasings: "give them a whole season before you judge it"; "give them a whole season first"; "let a season pass before judging"
  - the message must contain one of: `season`, `whole`
  - scored words: `season`(1.8), `whole`(1.8), `give`(0.8), `before`(0.8), `judge`(0.8), `first`(0.8), `let`(0.8), `pass`(0.8), `judging`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.teaching_a_child.active.respond.advise_patience
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.archer.teaching_a_child.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.archer.teaching_a_child.active.respond.advise_patience   [45 chars]
    en  Give them a whole season before you judge it.
    >>  ............................................
    pt  Dê uma estação inteira antes de julgar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.archer.teaching`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.archer.teaching_a_child"}
- Then opens: `conversations.scene.work.archer.followup`
- …where the player's next choices will be: "What's the hardest part of a long shot?" | "I'll leave you to the butts."

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.teaching_a_child.active.accepted
WHO    VILLAGER — what the player reads after pressing "Give them a whole season before you judge it."
       spoken on: conversations.scene.work.archer.teaching_a_child.active.respond, button `advise_patience`
       leaves the player on: conversations.scene.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.teaching_a_child.active.accepted`: the villager accepts. Subject `work.archer.teaching`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.archer.teaching_a_child.active.accepted/1   [106 chars]
    en  A season. Yes. I took three, and somebody was patient with me, and I have been forgetting to pass that on.
    >>  ............................................
    pt  Uma estação. Sim. Eu levei três, e alguém teve paciência comigo, e ando esquecendo de repassar isso.
    >>  ............................................
  dialogue.conversations.scene.work.archer.teaching_a_child.active.accepted/2   [104 chars]
    en  You are right. I am measuring a child in weeks against an adult in years, which is not measuring at all.
    >>  ............................................
    pt  Você tem razão. Estou medindo uma criança em semanas contra uma adulta em anos, o que não é medir nada.
    >>  ............................................
  dialogue.conversations.scene.work.archer.teaching_a_child.active.accepted/3   [105 chars]
    en  Agreed. And I will say that to them out loud, because the waiting is easier when you know it is the plan.
    >>  ............................................
    pt  De acordo. E vou dizer isso a ela em voz alta, porque esperar é mais fácil quando se sabe que esperar é o plano.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the butts."

*stance family `exit` · tone `plain` · answers the beat(s) `work.archer.teaching_a_child.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.teaching_a_child.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.archer.teaching_a_child.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.archer.teaching_a_child.active.respond.leave   [35 chars]
    en  I'll let you get back to the butts.
    >>  ............................................
    pt  Vou deixar você voltar ao alvo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the butts."
       spoken on: conversations.scene.work.archer.teaching_a_child.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.left`: the villager accepts. Subject `work.archer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.archer.arrow_shortage.blocked.respond / leave; conversations.scene.work.archer.arrow_shortage.succeeded.respond / leave; conversations.scene.work.archer.followup / leave; conversations.scene.work.archer.missed_shot.active.respond / leave; conversations.scene.work.archer.missed_shot.succeeded.respond / leave; conversations.scene.work.archer.teaching_a_child.succeeded.respond / leave; conversations.topic.work.archer.craft.respond / leave; conversations.topic.work.archer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.archer.arrow_shortage.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.archer.teaching_a_child.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.archer.teaching_a_child.succeeded` — e.g. "They hit the mark three times running last week and looked at me as if I had done it."


```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.teaching_a_child.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.archer.teaching_a_child.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.archer.teaching_a_child.succeeded.respond   [13 chars]
    en  Your student.
    >>  ............................................
    pt  Sua aluna.
    >>  ............................................
```


### Button `praise_teaching` — "You taught them well."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.archer.teaching_a_child.succeeded` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.archer.teaching_a_child.succeeded.praise_teaching` — accepted phrasings: "you taught them well"; "you taught them well"; "that is good teaching"
  - the message must contain one of: `taught`, `teaching`
  - scored words: `taught`(1.8), `teaching`(1.8), `well`(0.8), `good`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.teaching_a_child.succeeded.respond.praise_teaching
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.archer.teaching_a_child.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.archer.teaching_a_child.succeeded.respond.praise_teaching   [21 chars]
    en  You taught them well.
    >>  ............................................
    pt  Você ensinou bem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +2  _(recorded under topic `work.archer.teaching`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.archer.teaching_a_child"}
- Then opens: `conversations.scene.work.archer.followup`
- …where the player's next choices will be: "What's the hardest part of a long shot?" | "I'll leave you to the butts."

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.teaching_a_child.succeeded.deflected
WHO    VILLAGER — what the player reads after pressing "You taught them well."
       spoken on: conversations.scene.work.archer.teaching_a_child.succeeded.respond, button `praise_teaching`
       leaves the player on: conversations.scene.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.teaching_a_child.succeeded.deflected`: the villager accepts. Subject `work.archer.teaching`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.archer.teaching_a_child.succeeded.deflected/1   [112 chars]
    en  They taught them. I stood nearby and said one useful sentence a week. I have made peace with that being the job.
    >>  ............................................
    pt  Ela ensinou a si mesma. Eu fiquei por perto e disse uma frase útil por semana. Já fiz as pazes com esse ser o trabalho.
    >>  ............................................
  dialogue.conversations.scene.work.archer.teaching_a_child.succeeded.deflected/2   [106 chars]
    en  Thank you. I will take the credit in private and give it to them in public, which is the correct division.
    >>  ............................................
    pt  Obrigada. Vou aceitar o crédito em particular e dar a ela em público, que é a divisão correta.
    >>  ............................................
  dialogue.conversations.scene.work.archer.teaching_a_child.succeeded.deflected/3   [105 chars]
    en  If I did, it was by keeping my mouth shut at the right times, and I would like that written on something.
    >>  ............................................
    pt  Se ensinei, foi ficando calada nas horas certas, e eu gostaria que isso ficasse escrito em algum lugar.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the butts."

*stance family `exit` · tone `plain` · answers the beat(s) `work.archer.teaching_a_child.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.archer.teaching_a_child.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.archer.teaching_a_child.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.archer.teaching_a_child.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the butts.
    >>  ............................................
    pt  Vou deixar você voltar ao alvo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the butts."
       spoken on: conversations.scene.work.archer.teaching_a_child.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.left`: the villager accepts. Subject `work.archer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.archer.arrow_shortage.blocked.respond / leave; conversations.scene.work.archer.arrow_shortage.succeeded.respond / leave; conversations.scene.work.archer.followup / leave; conversations.scene.work.archer.missed_shot.active.respond / leave; conversations.scene.work.archer.missed_shot.succeeded.respond / leave; conversations.scene.work.archer.teaching_a_child.active.respond / leave; conversations.topic.work.archer.craft.respond / leave; conversations.topic.work.archer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.archer.arrow_shortage.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.archer.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.archer.craft` — e.g. "Everyone thinks it's the eye. It's the breath. The eye only tells you how badly you breathed."


```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.archer.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.archer.craft.respond   [27 chars]
    en  That's what it actually is.
    >>  ............................................
    pt  É isso que realmente é.
    >>  ............................................
```


### Button `ask_breath` — "What does the breath do?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.archer.craft` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.archer.craft.ask_breath` — accepted phrasings: "what does the breath do"
  - the message must contain one of: `breath`, `breathing`
  - scored words: `breath`(1.5), `breathing`(1.5), `does`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.craft.respond.ask_breath
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.craft.respond.ask_breath   [24 chars]
    en  What does the breath do?
    >>  ............................................
    pt  O que a respiração faz?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.archer.craft.ask_breath`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.archer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you seen lately?" | "Clear sightlines."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.craft.ask_breath
WHO    VILLAGER — what the player reads after pressing "What does the breath do?"
       spoken on: conversations.topic.work.archer.craft.respond, button `ask_breath`
       leaves the player on: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.craft.ask_breath`: the villager explains. Subject `work.archer.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.archer.craft.ask_breath/1   [93 chars]
    en  Everything moves when you breathe. You loose in the gap, and the gap is small, and it closes.
    >>  ............................................
    pt  Tudo se mexe quando você respira. Você solta na pausa, e a pausa é pequena, e ela fecha.
    >>  ............................................
  dialogue.conversations.work.prof.archer.craft.ask_breath/2   [76 chars]
    en  It decides. The arm only carries out what the lungs already agreed to, %1$s.
    >>  ............................................
    pt  Ela decide. O braço só executa o que os pulmões já combinaram, %1$s.
    >>  ............................................
```


### Button `admire` — "Not being able to explain it is a kind of mastery."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.archer.craft` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.archer.craft.admire` — accepted phrasings: "not being able to explain it is a kind of mastery"
  - the message must contain one of: `explain`, `mastery`
  - scored words: `explain`(1.5), `mastery`(1.5), `able`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.craft.respond.admire   [50 chars]
    en  Not being able to explain it is a kind of mastery.
    >>  ............................................
    pt  Não conseguir explicar é um tipo de maestria.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.archer.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.archer.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.archer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you seen lately?" | "Clear sightlines."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.craft.admire
WHO    VILLAGER — what the player reads after pressing "Not being able to explain it is a kind of mastery."
       spoken on: conversations.topic.work.archer.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.craft.admire`: the villager accepts. Subject `work.archer.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.archer.craft.admire/1   [76 chars]
    en  It's also useless. I've two people to teach and no words to teach them with.
    >>  ............................................
    pt  Também é inútil. Tenho duas pessoas pra ensinar e nenhuma palavra pra ensinar.
    >>  ............................................
  dialogue.conversations.work.prof.archer.craft.admire/2   [81 chars]
    en  The fletcher says the same about feathers. Perhaps we should compare notes, %1$s.
    >>  ............................................
    pt  O flecheiro diz o mesmo das penas. Talvez a gente devesse comparar notas, %1$s.
    >>  ............................................
```


### Button `ask_teach` — "How do you teach it, then?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.archer.craft` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.archer.craft.ask_teach` — accepted phrasings: "how do you teach it, then"
  - the message must contain one of: `teach`, `pupils`
  - scored words: `teach`(1.5), `pupils`(1.2), `then`(0.4)

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.craft.respond.ask_teach
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.craft.respond.ask_teach   [26 chars]
    en  How do you teach it, then?
    >>  ............................................
    pt  Como você ensina, então?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.archer.craft.ask_teach`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.archer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you seen lately?" | "Clear sightlines."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.craft.ask_teach
WHO    VILLAGER — what the player reads after pressing "How do you teach it, then?"
       spoken on: conversations.topic.work.archer.craft.respond, button `ask_teach`
       leaves the player on: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.craft.ask_teach`: the villager explains. Subject `work.archer.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.archer.craft.ask_teach/1   [89 chars]
    en  I make them draw beside me and say nothing. It takes a year and it's the only way I know.
    >>  ............................................
    pt  Faço puxarem ao meu lado e não digo nada. Leva um ano e é o único jeito que eu sei.
    >>  ............................................
  dialogue.conversations.work.prof.archer.craft.ask_teach/2   [83 chars]
    en  Badly. One of the two has it already and doesn't know, and I dare not tell her yet.
    >>  ............................................
    pt  Mal. Uma das duas já tem e não sabe, e eu não ouso contar ainda.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the sightline."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.archer.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.craft.respond.leave   [39 chars]
    en  I'll let you get back to the sightline.
    >>  ............................................
    pt  Vou deixar você voltar pra sua linha de visão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the sightline."
       spoken on: conversations.topic.work.archer.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.left`: the villager accepts. Subject `work.archer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.archer.arrow_shortage.blocked.respond / leave; conversations.scene.work.archer.arrow_shortage.succeeded.respond / leave; conversations.scene.work.archer.followup / leave; conversations.scene.work.archer.missed_shot.active.respond / leave; conversations.scene.work.archer.missed_shot.succeeded.respond / leave; conversations.scene.work.archer.teaching_a_child.active.respond / leave; conversations.scene.work.archer.teaching_a_child.succeeded.respond / leave; conversations.topic.work.archer.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.archer.arrow_shortage.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.archer.followup`

**Reached from 20 route(s):** `conversations.scene.work.archer.followup` / `ask_more`; `conversations.topic.work.archer.craft.respond` / `ask_breath`; `conversations.topic.work.archer.craft.respond` / `admire`; `conversations.topic.work.archer.craft.respond` / `ask_teach`; `conversations.topic.work.archer.future.respond` / `ask_second`; `conversations.topic.work.archer.future.respond` / `encourage`; `conversations.topic.work.archer.future.respond` / `ask_old`; `conversations.topic.work.archer.respond` / `ask_hard`; `conversations.topic.work.archer.respond` / `value`; `conversations.topic.work.archer.respond` / `challenge`; `conversations.topic.work.archer.respond` / `challenge`; `conversations.topic.work.archer.risk.respond` / `ask_decision` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.archer.challenge.landed` — e.g. "I do. Standing where you can see is most of what defending a place means."
- `conversations.work.prof.archer.challenge.stung` — e.g. "...Stand there through a hailstorm and say that again."
- `conversations.work.prof.archer.craft.admire` — e.g. "It's also useless. I've two people to teach and no words to teach them with."
- `conversations.work.prof.archer.craft.ask_breath` — e.g. "Everything moves when you breathe. You loose in the gap, and the gap is small, and it closes."
- `conversations.work.prof.archer.craft.ask_teach` — e.g. "I make them draw beside me and say nothing. It takes a year and it's the only way I know."
- `conversations.work.prof.archer.future.ask_old` — e.g. "For a year of standing beside somebody every morning. It's a year of my knees, not my eyes."
- `conversations.work.prof.archer.future.ask_second` — e.g. "A year of mornings and somebody willing to give them. That's all. That's genuinely all."
- `conversations.work.prof.archer.future.encourage` — e.g. "...Let her decide. Yes. I've been deciding for her, which is not my right."
- `conversations.work.prof.archer.hard` — e.g. "Not being certain. An arrow is a decision you can't take back, %1$s."
- `conversations.work.prof.archer.risk.ask_decision` — e.g. "Before it happens. You decide the rule in daylight so your hands aren't deciding in the dark."
- `conversations.work.prof.archer.risk.ask_forever` — e.g. "Two. Both were correct and both wake me, and I've stopped expecting those to be different things."
- `conversations.work.prof.archer.risk.sympathise` — e.g. "...It's the accurate way. And it's why I'm careful about who stands beside me up there."
- `conversations.work.prof.archer.task.ask_draws` — e.g. "Every morning for nine years. The morning I skip is the evening I'd find out about it."
- `conversations.work.prof.archer.task.ask_four` — e.g. "Behind the smithy, where the wood stack has grown. I've asked twice for it to be shifted."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.archer.followup   [30 chars]
    en  That's the tower's side of it.
    >>  ............................................
    pt  É o lado da torre da coisa.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.archer.challenge.landed`, `work.archer.challenge.stung`, `work.archer.craft.admire`, `work.archer.craft.ask_breath`, `work.archer.craft.ask_teach`, `work.archer.future.ask_old`, `work.archer.future.ask_second`, `work.archer.future.encourage`, `work.archer.hard`, `work.archer.risk.ask_decision`, `work.archer.risk.ask_forever`, `work.archer.risk.sympathise`, `work.archer.task.ask_draws`, `work.archer.task.ask_four`, `work.archer.task.offer_hands`, `work.archer.value`, `work.archer.village.ask_arrangement`, `work.archer.village.ask_name`, `work.archer.village.say_thanks` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.archer.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `thinking`, `tower`
  - scored words: `thought`(1.2), `thinking`(1.2), `tower`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.archer.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.archer.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.archer.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.archer.thanks`: the villager accepts. Subject `work.archer.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.archer.thanks/1   [63 chars]
    en  Few come up here. Fewer stay long enough to see what it's like.
    >>  ............................................
    pt  Poucos sobem aqui. Menos ainda ficam tempo bastante pra ver como é.
    >>  ............................................
  dialogue.conversations.work.prof.archer.thanks/2   [74 chars]
    en  It's a quiet trade full of thinking, %1$s. The thinking is the heavy part.
    >>  ............................................
    pt  É um ofício silencioso e cheio de pensar, %1$s. O pensar é a parte pesada.
    >>  ............................................
```


### Button `ask_more` — "What have you seen lately?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.archer.challenge.landed`, `work.archer.challenge.stung`, `work.archer.craft.admire`, `work.archer.craft.ask_breath`, `work.archer.craft.ask_teach`, `work.archer.future.ask_old`, `work.archer.future.ask_second`, `work.archer.future.encourage`, `work.archer.hard`, `work.archer.risk.ask_decision`, `work.archer.risk.ask_forever`, `work.archer.risk.sympathise`, `work.archer.task.ask_draws`, `work.archer.task.ask_four`, `work.archer.task.offer_hands`, `work.archer.value`, `work.archer.village.ask_arrangement`, `work.archer.village.ask_name`, `work.archer.village.say_thanks` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.archer.more` — accepted phrasings: "what have you seen lately"
  - the message must contain one of: `seen`, `lately`, `spotted`
  - scored words: `seen`(1.5), `lately`(1.5), `spotted`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.followup.ask_more   [26 chars]
    en  What have you seen lately?
    >>  ............................................
    pt  O que você viu ultimamente?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.archer.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.more
WHO    VILLAGER — what the player reads after pressing "What have you seen lately?"
       spoken on: conversations.topic.work.archer.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.archer.more`: the villager discloses. Subject `work.archer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.archer.more/1   [87 chars]
    en  Lights at the treeline, three nights running. I've told the guard. He's told the mayor.
    >>  ............................................
    pt  Luzes na borda da mata, três noites seguidas. Já avisei o guarda. Ele avisou o prefeito.
    >>  ............................................
  dialogue.conversations.work.prof.archer.more/2   [88 chars]
    en  Nothing that needed an arrow. Which is either good news or news that hasn't arrived yet.
    >>  ............................................
    pt  Nada que precisasse de flecha. O que é boa notícia ou notícia que ainda não chegou.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline, three nights. I've told the guard and I've not slept properly since.
    >>  ............................................
    pt  Luzes na linha das árvores, três noites. Falei com o guarda e não durmo direito desde então.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.archer.more/2
    en  Four places where somebody could stand unseen. I count them every evening and I hate that I do.
    >>  ............................................
    pt  Quatro lugares onde alguém poderia ficar sem ser visto. Conto toda noite e odeio que eu conte.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline. Three nights. It'll be a lantern and a lost carter, most likely.
    >>  ............................................
    pt  Luzes na linha das árvores. Três noites. Deve ser uma lanterna e um carroceiro perdido.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.archer.more/2
    en  The wood stack has grown into my line of sight. It'll get moved eventually. These things do.
    >>  ............................................
    pt  A pilha de lenha cresceu na minha linha de visão. Vai ser mudada uma hora. Essas coisas mudam.
    >>  ............................................
  confident.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline, three nights running. I've told the guard. He's told the mayor.
    >>  ............................................
    pt  Luzes na linha das árvores, três noites seguidas. Falei com o guarda. Ele falou com o prefeito.
    >>  ............................................
  confident.dialogue.conversations.work.prof.archer.more/2
    en  The north stretch. Anyone could stand there unseen and I've said so twice.
    >>  ............................................
    pt  O trecho norte. Qualquer um poderia ficar ali sem ser visto e eu já disse duas vezes.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline, three nights running. I've told the guard. He's told the mayor.
    >>  ............................................
    pt  Luzes na linha das árvores, três noites seguidas. Falei com o guarda. Ele falou com o prefeito.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.archer.more/2
    en  The north stretch. Anyone could stand there unseen and I've said so twice.
    >>  ............................................
    pt  O trecho norte. Qualquer um poderia ficar ali sem ser visto e eu já disse duas vezes.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline, three nights. Come up at dusk and see for yourself — I'd welcome the company.
    >>  ............................................
    pt  Luzes na linha das árvores, três noites. Suba ao anoitecer e veja — eu adoraria a companhia.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.archer.more/2
    en  There's a blind spot by the well. Now you know it, %1$s, and that makes three of us.
    >>  ............................................
    pt  Tem um ponto cego perto do poço. Agora você sabe, %1$s, e isso faz três de nós.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline, three nights. Come up at dusk and see for yourself — I'd welcome the company.
    >>  ............................................
    pt  Luzes na linha das árvores, três noites. Suba ao anoitecer e veja — eu adoraria a companhia.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.archer.more/2
    en  There's a blind spot by the well. Now you know it, %1$s, and that makes three of us.
    >>  ............................................
    pt  Tem um ponto cego perto do poço. Agora você sabe, %1$s, e isso faz três de nós.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline, three nights. Come up at dusk and see for yourself — I'd welcome the company.
    >>  ............................................
    pt  Luzes na linha das árvores, três noites. Suba ao anoitecer e veja — eu adoraria a companhia.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.archer.more/2
    en  There's a blind spot by the well. Now you know it, %1$s, and that makes three of us.
    >>  ............................................
    pt  Tem um ponto cego perto do poço. Agora você sabe, %1$s, e isso faz três de nós.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline, three nights. I've told the guard and I've not slept properly since.
    >>  ............................................
    pt  Luzes na linha das árvores, três noites. Falei com o guarda e não durmo direito desde então.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.archer.more/2
    en  Four places where somebody could stand unseen. I count them every evening and I hate that I do.
    >>  ............................................
    pt  Quatro lugares onde alguém poderia ficar sem ser visto. Conto toda noite e odeio que eu conte.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline, three nights running. I've told the guard. He's told the mayor.
    >>  ............................................
    pt  Luzes na linha das árvores, três noites seguidas. Falei com o guarda. Ele falou com o prefeito.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.archer.more/2
    en  The north stretch. Anyone could stand there unseen and I've said so twice.
    >>  ............................................
    pt  O trecho norte. Qualquer um poderia ficar ali sem ser visto e eu já disse duas vezes.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline, three nights running. I've told the guard. He's told the mayor.
    >>  ............................................
    pt  Luzes na linha das árvores, três noites seguidas. Falei com o guarda. Ele falou com o prefeito.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.archer.more/2
    en  The north stretch. Anyone could stand there unseen and I've said so twice.
    >>  ............................................
    pt  O trecho norte. Qualquer um poderia ficar ali sem ser visto e eu já disse duas vezes.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline. Three nights, same hour, same place. That's what makes it worth saying.
    >>  ............................................
    pt  Luzes na linha das árvores. Três noites, mesma hora, mesmo lugar. É isso que faz valer a pena dizer.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.archer.more/2
    en  Behind the smithy, where the wood stack has grown. A man could stand there all evening.
    >>  ............................................
    pt  Atrás da ferraria, onde a pilha de lenha cresceu. Um homem poderia ficar ali a noite toda.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline. Three nights. It'll be a lantern and a lost carter, most likely.
    >>  ............................................
    pt  Luzes na linha das árvores. Três noites. Deve ser uma lanterna e um carroceiro perdido.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.archer.more/2
    en  The wood stack has grown into my line of sight. It'll get moved eventually. These things do.
    >>  ............................................
    pt  A pilha de lenha cresceu na minha linha de visão. Vai ser mudada uma hora. Essas coisas mudam.
    >>  ............................................
  odd.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline. Three nights, same hour, same place. That's what makes it worth saying.
    >>  ............................................
    pt  Luzes na linha das árvores. Três noites, mesma hora, mesmo lugar. É isso que faz valer a pena dizer.
    >>  ............................................
  odd.dialogue.conversations.work.prof.archer.more/2
    en  Behind the smithy, where the wood stack has grown. A man could stand there all evening.
    >>  ............................................
    pt  Atrás da ferraria, onde a pilha de lenha cresceu. Um homem poderia ficar ali a noite toda.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline. Three nights. It'll be a lantern and a lost carter, most likely.
    >>  ............................................
    pt  Luzes na linha das árvores. Três noites. Deve ser uma lanterna e um carroceiro perdido.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.archer.more/2
    en  The wood stack has grown into my line of sight. It'll get moved eventually. These things do.
    >>  ............................................
    pt  A pilha de lenha cresceu na minha linha de visão. Vai ser mudada uma hora. Essas coisas mudam.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline! Three nights. The guard has told the mayor, so that's that solved forever.
    >>  ............................................
    pt  Luzes na linha das árvores! Três noites. O guarda avisou o prefeito, então está resolvido pra sempre.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.archer.more/2
    en  There's a spot on the north wall where a whole cart could hide. I mention it weekly. Weekly!
    >>  ............................................
    pt  Tem um ponto na muralha norte onde uma carroça inteira caberia escondida. Eu menciono toda semana. Toda semana!
    >>  ............................................
  playful.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline! Three nights. The guard has told the mayor, so that's that solved forever.
    >>  ............................................
    pt  Luzes na linha das árvores! Três noites. O guarda avisou o prefeito, então está resolvido pra sempre.
    >>  ............................................
  playful.dialogue.conversations.work.prof.archer.more/2
    en  There's a spot on the north wall where a whole cart could hide. I mention it weekly. Weekly!
    >>  ............................................
    pt  Tem um ponto na muralha norte onde uma carroça inteira caberia escondida. Eu menciono toda semana. Toda semana!
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline. Three nights. It'll be a lantern and a lost carter, most likely.
    >>  ............................................
    pt  Luzes na linha das árvores. Três noites. Deve ser uma lanterna e um carroceiro perdido.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.archer.more/2
    en  The wood stack has grown into my line of sight. It'll get moved eventually. These things do.
    >>  ............................................
    pt  A pilha de lenha cresceu na minha linha de visão. Vai ser mudada uma hora. Essas coisas mudam.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline, three nights. I've told the guard and I've not slept properly since.
    >>  ............................................
    pt  Luzes na linha das árvores, três noites. Falei com o guarda e não durmo direito desde então.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.archer.more/2
    en  Four places where somebody could stand unseen. I count them every evening and I hate that I do.
    >>  ............................................
    pt  Quatro lugares onde alguém poderia ficar sem ser visto. Conto toda noite e odeio que eu conte.
    >>  ............................................
  shy.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline. Three nights, same hour, same place. That's what makes it worth saying.
    >>  ............................................
    pt  Luzes na linha das árvores. Três noites, mesma hora, mesmo lugar. É isso que faz valer a pena dizer.
    >>  ............................................
  shy.dialogue.conversations.work.prof.archer.more/2
    en  Behind the smithy, where the wood stack has grown. A man could stand there all evening.
    >>  ............................................
    pt  Atrás da ferraria, onde a pilha de lenha cresceu. Um homem poderia ficar ali a noite toda.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline! Three nights. The guard has told the mayor, so that's that solved forever.
    >>  ............................................
    pt  Luzes na linha das árvores! Três noites. O guarda avisou o prefeito, então está resolvido pra sempre.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.archer.more/2
    en  There's a spot on the north wall where a whole cart could hide. I mention it weekly. Weekly!
    >>  ............................................
    pt  Tem um ponto na muralha norte onde uma carroça inteira caberia escondida. Eu menciono toda semana. Toda semana!
    >>  ............................................
  witty.dialogue.conversations.work.prof.archer.more/1
    en  Lights at the treeline! Three nights. The guard has told the mayor, so that's that solved forever.
    >>  ............................................
    pt  Luzes na linha das árvores! Três noites. O guarda avisou o prefeito, então está resolvido pra sempre.
    >>  ............................................
  witty.dialogue.conversations.work.prof.archer.more/2
    en  There's a spot on the north wall where a whole cart could hide. I mention it weekly. Weekly!
    >>  ............................................
    pt  Tem um ponto na muralha norte onde uma carroça inteira caberia escondida. Eu menciono toda semana. Toda semana!
    >>  ............................................
```

</details>


### Button `leave` — "Clear sightlines."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.archer.challenge.landed`, `work.archer.challenge.stung`, `work.archer.craft.admire`, `work.archer.craft.ask_breath`, `work.archer.craft.ask_teach`, `work.archer.future.ask_old`, `work.archer.future.ask_second`, `work.archer.future.encourage`, `work.archer.hard`, `work.archer.risk.ask_decision`, `work.archer.risk.ask_forever`, `work.archer.risk.sympathise`, `work.archer.task.ask_draws`, `work.archer.task.ask_four`, `work.archer.task.offer_hands`, `work.archer.value`, `work.archer.village.ask_arrangement`, `work.archer.village.ask_name`, `work.archer.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.followup.leave   [17 chars]
    en  Clear sightlines.
    >>  ............................................
    pt  Boa visibilidade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.leave
WHO    VILLAGER — what the player reads after pressing "Clear sightlines."
       spoken on: conversations.topic.work.archer.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.left`: the villager accepts. Subject `work.archer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.archer.arrow_shortage.blocked.respond / leave; conversations.scene.work.archer.arrow_shortage.succeeded.respond / leave; conversations.scene.work.archer.followup / leave; conversations.scene.work.archer.missed_shot.active.respond / leave; conversations.scene.work.archer.missed_shot.succeeded.respond / leave; conversations.scene.work.archer.teaching_a_child.active.respond / leave; conversations.scene.work.archer.teaching_a_child.succeeded.respond / leave; conversations.topic.work.archer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.archer.arrow_shortage.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.archer.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.archer.future` — e.g. "I want two archers on that wall, not one. Then a night off is a thing that exists."


```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.archer.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.archer.future.respond   [22 chars]
    en  That's what I'd build.
    >>  ............................................
    pt  É o que eu construiria.
    >>  ............................................
```


### Button `ask_second` — "What's needed for a second archer?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.archer.future` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.archer.future.ask_second` — accepted phrasings: "what's needed for a second archer"
  - the message must contain one of: `second`, `archer`, `needed`
  - scored words: `second`(1.5), `archer`(1.2), `needed`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.future.respond.ask_second
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.future.respond.ask_second   [34 chars]
    en  What's needed for a second archer?
    >>  ............................................
    pt  O que é preciso pra uma segunda arqueira?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.archer.future.ask_second`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.archer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you seen lately?" | "Clear sightlines."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.future.ask_second
WHO    VILLAGER — what the player reads after pressing "What's needed for a second archer?"
       spoken on: conversations.topic.work.archer.future.respond, button `ask_second`
       leaves the player on: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.future.ask_second`: the villager explains. Subject `work.archer.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.archer.future.ask_second/1   [87 chars]
    en  A year of mornings and somebody willing to give them. That's all. That's genuinely all.
    >>  ............................................
    pt  Um ano de manhãs e alguém disposto a dá-las. É tudo. É genuinamente tudo.
    >>  ............................................
  dialogue.conversations.work.prof.archer.future.ask_second/2   [77 chars]
    en  Permission, and a bow, and the mayor deciding one archer is not a plan, %1$s.
    >>  ............................................
    pt  Permissão, um arco, e o prefeito decidir que uma arqueira não é um plano, %1$s.
    >>  ............................................
```


### Button `encourage` — "Tell her. Let her decide what to do with it."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.archer.future` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.archer.future.encourage` — accepted phrasings: "tell her. let her decide what to do with it"
  - the message must contain one of: `tell`, `decide`
  - scored words: `tell`(1.2), `her`(0.6), `decide`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.future.respond.encourage   [44 chars]
    en  Tell her. Let her decide what to do with it.
    >>  ............................................
    pt  Conte a ela. Deixe ela decidir o que fazer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.archer.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.archer.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.archer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you seen lately?" | "Clear sightlines."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.future.encourage
WHO    VILLAGER — what the player reads after pressing "Tell her. Let her decide what to do with it."
       spoken on: conversations.topic.work.archer.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.future.encourage`: the villager accepts. Subject `work.archer.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.archer.future.encourage/1   [74 chars]
    en  ...Let her decide. Yes. I've been deciding for her, which is not my right.
    >>  ............................................
    pt  ...Deixar ela decidir. Sim. Eu venho decidindo por ela, o que não é meu direito.
    >>  ............................................
  dialogue.conversations.work.prof.archer.future.encourage/2   [83 chars]
    en  If she says no I lose the only candidate. If I say nothing I lose her anyway, %1$s.
    >>  ............................................
    pt  Se ela disser não eu perco a única candidata. Se eu não disser nada eu perco ela igual, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide. I've been deciding for her because I couldn't bear the no.
    >>  ............................................
    pt  ...Deixe ela decidir. Venho decidindo por ela porque não aguentava o não.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.archer.future.encourage/2
    en  If she says no I lose the candidate, and I'd rather not know which it is.
    >>  ............................................
    pt  Se disser não eu perco a candidata, e prefiro não saber qual dos dois é.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide. Twenty years on the wall and I still forget people are people.
    >>  ............................................
    pt  ...Deixe ela decidir. Vinte anos no muro e ainda esqueço que gente é gente.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.archer.future.encourage/2
    en  If she says no I lose the candidate. I've lost better on worse reasoning.
    >>  ............................................
    pt  Se disser não eu perco a candidata. Já perdi melhores por razões piores.
    >>  ............................................
  confident.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide. Yes. I've been deciding for her, which isn't my right.
    >>  ............................................
    pt  ...Deixe ela decidir. Sim. Venho decidindo por ela, o que não é meu direito.
    >>  ............................................
  confident.dialogue.conversations.work.prof.archer.future.encourage/2
    en  If she says no I lose the candidate. If I say nothing I lose her anyway.
    >>  ............................................
    pt  Se ela disser não eu perco a candidata. Se eu calar, perco do mesmo jeito.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide. Yes. I've been deciding for her, which isn't my right.
    >>  ............................................
    pt  ...Deixe ela decidir. Sim. Venho decidindo por ela, o que não é meu direito.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.archer.future.encourage/2
    en  If she says no I lose the candidate. If I say nothing I lose her anyway.
    >>  ............................................
    pt  Se ela disser não eu perco a candidata. Se eu calar, perco do mesmo jeito.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide, %1$s. I've been deciding for her and calling it protection.
    >>  ............................................
    pt  ...Deixe ela decidir, %1$s. Venho decidindo por ela e chamando de proteção.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.archer.future.encourage/2
    en  If she says no I lose the candidate. If I say nothing I lose her anyway, and you knew that.
    >>  ............................................
    pt  Se disser não eu perco a candidata. Se eu calar, perco igual, e você sabia.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide, %1$s. I've been deciding for her and calling it protection.
    >>  ............................................
    pt  ...Deixe ela decidir, %1$s. Venho decidindo por ela e chamando de proteção.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.archer.future.encourage/2
    en  If she says no I lose the candidate. If I say nothing I lose her anyway, and you knew that.
    >>  ............................................
    pt  Se disser não eu perco a candidata. Se eu calar, perco igual, e você sabia.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide, %1$s. I've been deciding for her and calling it protection.
    >>  ............................................
    pt  ...Deixe ela decidir, %1$s. Venho decidindo por ela e chamando de proteção.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.archer.future.encourage/2
    en  If she says no I lose the candidate. If I say nothing I lose her anyway, and you knew that.
    >>  ............................................
    pt  Se disser não eu perco a candidata. Se eu calar, perco igual, e você sabia.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide. I've been deciding for her because I couldn't bear the no.
    >>  ............................................
    pt  ...Deixe ela decidir. Venho decidindo por ela porque não aguentava o não.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.archer.future.encourage/2
    en  If she says no I lose the candidate, and I'd rather not know which it is.
    >>  ............................................
    pt  Se disser não eu perco a candidata, e prefiro não saber qual dos dois é.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide. Yes. I've been deciding for her, which isn't my right.
    >>  ............................................
    pt  ...Deixe ela decidir. Sim. Venho decidindo por ela, o que não é meu direito.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.archer.future.encourage/2
    en  If she says no I lose the candidate. If I say nothing I lose her anyway.
    >>  ............................................
    pt  Se ela disser não eu perco a candidata. Se eu calar, perco do mesmo jeito.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide. Yes. I've been deciding for her, which isn't my right.
    >>  ............................................
    pt  ...Deixe ela decidir. Sim. Venho decidindo por ela, o que não é meu direito.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.archer.future.encourage/2
    en  If she says no I lose the candidate. If I say nothing I lose her anyway.
    >>  ............................................
    pt  Se ela disser não eu perco a candidata. Se eu calar, perco do mesmo jeito.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide. That's not my choice to make.
    >>  ............................................
    pt  ...Deixe ela decidir. Não é escolha minha.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.archer.future.encourage/2
    en  Either way I lose her. Best it be her doing.
    >>  ............................................
    pt  De um jeito ou de outro eu a perco. Melhor que seja ela.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide. Twenty years on the wall and I still forget people are people.
    >>  ............................................
    pt  ...Deixe ela decidir. Vinte anos no muro e ainda esqueço que gente é gente.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.archer.future.encourage/2
    en  If she says no I lose the candidate. I've lost better on worse reasoning.
    >>  ............................................
    pt  Se disser não eu perco a candidata. Já perdi melhores por razões piores.
    >>  ............................................
  odd.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide. That's not my choice to make.
    >>  ............................................
    pt  ...Deixe ela decidir. Não é escolha minha.
    >>  ............................................
  odd.dialogue.conversations.work.prof.archer.future.encourage/2
    en  Either way I lose her. Best it be her doing.
    >>  ............................................
    pt  De um jeito ou de outro eu a perco. Melhor que seja ela.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide. Twenty years on the wall and I still forget people are people.
    >>  ............................................
    pt  ...Deixe ela decidir. Vinte anos no muro e ainda esqueço que gente é gente.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.archer.future.encourage/2
    en  If she says no I lose the candidate. I've lost better on worse reasoning.
    >>  ............................................
    pt  Se disser não eu perco a candidata. Já perdi melhores por razões piores.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide! Of course. I've been deciding for her like some sort of magistrate.
    >>  ............................................
    pt  ...Deixe ela decidir! Claro. Venho decidindo por ela como se fosse juiz.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.archer.future.encourage/2
    en  If she says no I lose the candidate, and if I say nothing I lose her anyway. Splendid.
    >>  ............................................
    pt  Se disser não eu perco a candidata, e se eu calar perco igual. Esplêndido.
    >>  ............................................
  playful.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide! Of course. I've been deciding for her like some sort of magistrate.
    >>  ............................................
    pt  ...Deixe ela decidir! Claro. Venho decidindo por ela como se fosse juiz.
    >>  ............................................
  playful.dialogue.conversations.work.prof.archer.future.encourage/2
    en  If she says no I lose the candidate, and if I say nothing I lose her anyway. Splendid.
    >>  ............................................
    pt  Se disser não eu perco a candidata, e se eu calar perco igual. Esplêndido.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide. Twenty years on the wall and I still forget people are people.
    >>  ............................................
    pt  ...Deixe ela decidir. Vinte anos no muro e ainda esqueço que gente é gente.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.archer.future.encourage/2
    en  If she says no I lose the candidate. I've lost better on worse reasoning.
    >>  ............................................
    pt  Se disser não eu perco a candidata. Já perdi melhores por razões piores.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide. I've been deciding for her because I couldn't bear the no.
    >>  ............................................
    pt  ...Deixe ela decidir. Venho decidindo por ela porque não aguentava o não.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.archer.future.encourage/2
    en  If she says no I lose the candidate, and I'd rather not know which it is.
    >>  ............................................
    pt  Se disser não eu perco a candidata, e prefiro não saber qual dos dois é.
    >>  ............................................
  shy.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide. That's not my choice to make.
    >>  ............................................
    pt  ...Deixe ela decidir. Não é escolha minha.
    >>  ............................................
  shy.dialogue.conversations.work.prof.archer.future.encourage/2
    en  Either way I lose her. Best it be her doing.
    >>  ............................................
    pt  De um jeito ou de outro eu a perco. Melhor que seja ela.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide! Of course. I've been deciding for her like some sort of magistrate.
    >>  ............................................
    pt  ...Deixe ela decidir! Claro. Venho decidindo por ela como se fosse juiz.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.archer.future.encourage/2
    en  If she says no I lose the candidate, and if I say nothing I lose her anyway. Splendid.
    >>  ............................................
    pt  Se disser não eu perco a candidata, e se eu calar perco igual. Esplêndido.
    >>  ............................................
  witty.dialogue.conversations.work.prof.archer.future.encourage/1
    en  ...Let her decide! Of course. I've been deciding for her like some sort of magistrate.
    >>  ............................................
    pt  ...Deixe ela decidir! Claro. Venho decidindo por ela como se fosse juiz.
    >>  ............................................
  witty.dialogue.conversations.work.prof.archer.future.encourage/2
    en  If she says no I lose the candidate, and if I say nothing I lose her anyway. Splendid.
    >>  ............................................
    pt  Se disser não eu perco a candidata, e se eu calar perco igual. Esplêndido.
    >>  ............................................
```

</details>


### Button `ask_old` — "Too old for what?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.archer.future` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.archer.future.ask_old` — accepted phrasings: "too old for what"
  - the message must contain one of: `old`, `age`
  - scored words: `old`(1.5), `too`(0.5), `age`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.future.respond.ask_old
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.future.respond.ask_old   [17 chars]
    en  Too old for what?
    >>  ............................................
    pt  Velha demais pra quê?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.archer.future.ask_old`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.archer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you seen lately?" | "Clear sightlines."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.future.ask_old
WHO    VILLAGER — what the player reads after pressing "Too old for what?"
       spoken on: conversations.topic.work.archer.future.respond, button `ask_old`
       leaves the player on: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.future.ask_old`: the villager explains. Subject `work.archer.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.archer.future.ask_old/1   [91 chars]
    en  For a year of standing beside somebody every morning. It's a year of my knees, not my eyes.
    >>  ............................................
    pt  Pra um ano ao lado de alguém toda manhã. É um ano dos meus joelhos, não dos olhos.
    >>  ............................................
  dialogue.conversations.work.prof.archer.future.ask_old/2   [104 chars]
    en  For being the one who's there when she gets it wrong the first time, %1$s. That's the part that matters.
    >>  ............................................
    pt  Pra estar lá quando ela errar a primeira vez, %1$s. É essa a parte que importa.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the sightline."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.archer.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.future.respond.leave   [39 chars]
    en  I'll let you get back to the sightline.
    >>  ............................................
    pt  Vou deixar você voltar pra sua linha de visão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the sightline."
       spoken on: conversations.topic.work.archer.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.left`: the villager accepts. Subject `work.archer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.archer.arrow_shortage.blocked.respond / leave; conversations.scene.work.archer.arrow_shortage.succeeded.respond / leave; conversations.scene.work.archer.followup / leave; conversations.scene.work.archer.missed_shot.active.respond / leave; conversations.scene.work.archer.missed_shot.succeeded.respond / leave; conversations.scene.work.archer.teaching_a_child.active.respond / leave; conversations.scene.work.archer.teaching_a_child.succeeded.respond / leave; conversations.topic.work.archer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.archer.arrow_shortage.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.archer.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.archer` — e.g. "From the tower I see everything — who's brave, who's sneaking, whose roof needs mending. Mostly roofs."


```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.archer.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.archer.respond   [38 chars]
    en  That's the tower and the view from it.
    >>  ............................................
    pt  É a torre e a vista dela.
    >>  ............................................
```


### Button `ask_hard` — "What makes you hold the shot?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.archer.identity` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.archer.hard` — accepted phrasings: "what makes you hold the shot"
  - the message must contain one of: `hold`, `shot`
  - scored words: `hold`(1.5), `shot`(1.5), `fire`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.respond.ask_hard   [29 chars]
    en  What makes you hold the shot?
    >>  ............................................
    pt  O que faz você segurar o tiro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.archer.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.archer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you seen lately?" | "Clear sightlines."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.hard
WHO    VILLAGER — what the player reads after pressing "What makes you hold the shot?"
       spoken on: conversations.topic.work.archer.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.hard`: the villager explains. Subject `work.archer.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.archer.followup / ask_more
```

> Written out in full under **`conversations.scene.work.archer.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "You see this place more clearly than anyone."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.archer.identity` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.archer.value` — accepted phrasings: "you see this place more clearly than anyone"
  - the message must contain one of: `clearly`, `sightlines`, `tower`
  - scored words: `clearly`(1.5), `sightlines`(1.5), `tower`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.respond.value   [44 chars]
    en  You see this place more clearly than anyone.
    >>  ............................................
    pt  Você vê este lugar com mais clareza que qualquer um.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.archer.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.archer.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.archer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you seen lately?" | "Clear sightlines."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.value
WHO    VILLAGER — what the player reads after pressing "You see this place more clearly than anyone."
       spoken on: conversations.topic.work.archer.respond, button `value`
       leaves the player on: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.value`: the villager accepts. Subject `work.archer.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.archer.value/1   [64 chars]
    en  I do. It's a strange kind of loneliness, being the one who sees.
    >>  ............................................
    pt  Vejo. É um tipo estranho de solidão, ser quem vê.
    >>  ............................................
  dialogue.conversations.work.prof.archer.value/2   [68 chars]
    en  From up there, aye. I know whose shutters are broken before they do.
    >>  ............................................
    pt  Lá de cima, sim. Eu sei de quem é a veneziana quebrada antes deles.
    >>  ............................................
```


### Button `challenge` — "You just stand up there all day."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.archer.identity` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.archer.challenge` — accepted phrasings: "you just stand up there all day"
  - the message must contain one of: `stand`, `lazy`
  - scored words: `stand`(1.5), `day`(0.8), `lazy`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.respond.challenge   [32 chars]
    en  You just stand up there all day.
    >>  ............................................
    pt  Você só fica lá em cima o dia todo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.archer.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.archer.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.archer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you seen lately?" | "Clear sightlines."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.challenge.landed
WHO    VILLAGER — what the player reads after pressing "You just stand up there all day."
       spoken on: conversations.topic.work.archer.respond, button `challenge`
       leaves the player on: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.challenge.landed`: the villager resists. Subject `work.archer.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.archer.challenge.landed/1   [73 chars]
    en  I do. Standing where you can see is most of what defending a place means.
    >>  ............................................
    pt  Fico. Ficar onde dá pra ver é quase tudo que defender um lugar significa.
    >>  ............................................
  dialogue.conversations.work.prof.archer.challenge.landed/2   [63 chars]
    en  All day, aye. Try it once and see how still you can hold, %1$s.
    >>  ............................................
    pt  O dia todo, sim. Tente uma vez e veja quanto tempo consegue ficar parado, %1$s.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.archer.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.archer.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.archer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you seen lately?" | "Clear sightlines."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.challenge.stung
WHO    VILLAGER — what the player reads after pressing "You just stand up there all day."
       spoken on: conversations.topic.work.archer.respond, button `challenge`
       leaves the player on: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.challenge.stung`: the villager resists. Subject `work.archer.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.archer.challenge.stung/1   [54 chars]
    en  ...Stand there through a hailstorm and say that again.
    >>  ............................................
    pt  ...Fique lá durante uma tempestade de granizo e repita isso.
    >>  ............................................
  dialogue.conversations.work.prof.archer.challenge.stung/2   [71 chars]
    en  Just stand. Right. You've a talent for making things sound small, %1$s.
    >>  ............................................
    pt  Só ficar. Certo. Você tem talento pra fazer as coisas parecerem pequenas, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the sightline."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.archer.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.respond.leave   [39 chars]
    en  I'll let you get back to the sightline.
    >>  ............................................
    pt  Vou deixar você voltar pra sua linha de visão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the sightline."
       spoken on: conversations.topic.work.archer.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.left`: the villager accepts. Subject `work.archer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.archer.arrow_shortage.blocked.respond / leave; conversations.scene.work.archer.arrow_shortage.succeeded.respond / leave; conversations.scene.work.archer.followup / leave; conversations.scene.work.archer.missed_shot.active.respond / leave; conversations.scene.work.archer.missed_shot.succeeded.respond / leave; conversations.scene.work.archer.teaching_a_child.active.respond / leave; conversations.scene.work.archer.teaching_a_child.succeeded.respond / leave; conversations.topic.work.archer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.archer.arrow_shortage.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.archer.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.archer.risk` — e.g. "An arrow doesn't come back. Every one I loose is a decision that stays made forever."


```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.archer.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.archer.risk.respond   [24 chars]
    en  That's what the post is.
    >>  ............................................
    pt  É isso que o posto é.
    >>  ............................................
```


### Button `ask_decision` — "How do you make that decision?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.archer.risk` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.archer.risk.ask_decision` — accepted phrasings: "how do you make that decision"
  - the message must contain one of: `decision`, `rule`, `decide`
  - scored words: `decision`(1.5), `rule`(1.2), `decide`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.risk.respond.ask_decision
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.risk.respond.ask_decision   [30 chars]
    en  How do you make that decision?
    >>  ............................................
    pt  Como você toma essa decisão?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.archer.risk.ask_decision`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.archer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you seen lately?" | "Clear sightlines."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.risk.ask_decision
WHO    VILLAGER — what the player reads after pressing "How do you make that decision?"
       spoken on: conversations.topic.work.archer.risk.respond, button `ask_decision`
       leaves the player on: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.risk.ask_decision`: the villager explains. Subject `work.archer.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.archer.risk.ask_decision/1   [93 chars]
    en  Before it happens. You decide the rule in daylight so your hands aren't deciding in the dark.
    >>  ............................................
    pt  Antes de acontecer. Você decide a regra de dia pras suas mãos não decidirem no escuro.
    >>  ............................................
  dialogue.conversations.work.prof.archer.risk.ask_decision/2   [87 chars]
    en  I've a rule and I'll not recite it, %1$s. Reciting it makes it sound easier than it is.
    >>  ............................................
    pt  Tenho uma regra e não vou recitar, %1$s. Recitar faz parecer mais fácil do que é.
    >>  ............................................
```


### Button `sympathise` — "First thing aimed at is a heavy way to describe your post."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.archer.risk` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.archer.risk.sympathise` — accepted phrasings: "first thing aimed at is a heavy way to describe your post"
  - the message must contain one of: `aimed`, `heavy`
  - scored words: `aimed`(1.5), `heavy`(1.2), `post`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.risk.respond.sympathise   [58 chars]
    en  First thing aimed at is a heavy way to describe your post.
    >>  ............................................
    pt  Primeira coisa mirada é um jeito pesado de descrever seu posto.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.archer.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.archer.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.archer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you seen lately?" | "Clear sightlines."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "First thing aimed at is a heavy way to describe your post."
       spoken on: conversations.topic.work.archer.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.risk.sympathise`: the villager accepts. Subject `work.archer.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.archer.risk.sympathise/1   [87 chars]
    en  ...It's the accurate way. And it's why I'm careful about who stands beside me up there.
    >>  ............................................
    pt  ...É o jeito exato. E é por isso que eu tenho cuidado com quem fica ao meu lado lá em cima.
    >>  ............................................
  dialogue.conversations.work.prof.archer.risk.sympathise/2   [82 chars]
    en  It's what the post is, and I chose it, %1$s, and those two facts don't cancel out.
    >>  ............................................
    pt  É o que o posto é, e eu escolhi, %1$s, e esses dois fatos não se anulam.
    >>  ............................................
```


### Button `ask_forever` — "Does an arrow ever sit badly with you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.archer.risk` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.archer.risk.ask_forever` — accepted phrasings: "does an arrow ever sit badly with you"
  - the message must contain one of: `arrow`, `badly`, `regret`
  - scored words: `arrow`(1.2), `badly`(1.5), `regret`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.risk.respond.ask_forever
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.risk.respond.ask_forever   [38 chars]
    en  Does an arrow ever sit badly with you?
    >>  ............................................
    pt  Alguma flecha já te caiu mal?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.archer.risk.ask_forever`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.archer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you seen lately?" | "Clear sightlines."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.risk.ask_forever
WHO    VILLAGER — what the player reads after pressing "Does an arrow ever sit badly with you?"
       spoken on: conversations.topic.work.archer.risk.respond, button `ask_forever`
       leaves the player on: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.risk.ask_forever`: the villager explains. Subject `work.archer.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.archer.risk.ask_forever/1   [97 chars]
    en  Two. Both were correct and both wake me, and I've stopped expecting those to be different things.
    >>  ............................................
    pt  Duas. As duas foram corretas e as duas me acordam, e eu parei de esperar que fossem coisas diferentes.
    >>  ............................................
  dialogue.conversations.work.prof.archer.risk.ask_forever/2   [95 chars]
    en  One. I'd loose it again and I'd hate it again, %1$s, and that is the whole of my honesty on it.
    >>  ............................................
    pt  Uma. Eu soltaria de novo e odiaria de novo, %1$s, e é toda a minha honestidade sobre isso.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the sightline."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.archer.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.risk.respond.leave   [39 chars]
    en  I'll let you get back to the sightline.
    >>  ............................................
    pt  Vou deixar você voltar pra sua linha de visão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the sightline."
       spoken on: conversations.topic.work.archer.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.left`: the villager accepts. Subject `work.archer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.archer.arrow_shortage.blocked.respond / leave; conversations.scene.work.archer.arrow_shortage.succeeded.respond / leave; conversations.scene.work.archer.followup / leave; conversations.scene.work.archer.missed_shot.active.respond / leave; conversations.scene.work.archer.missed_shot.succeeded.respond / leave; conversations.scene.work.archer.teaching_a_child.active.respond / leave; conversations.scene.work.archer.teaching_a_child.succeeded.respond / leave; conversations.topic.work.archer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.archer.arrow_shortage.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.archer.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.archer.task` — e.g. "Two hundred draws before noon. It's dull and it's the only reason my hand is steady at dusk."


```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.archer.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.archer.task.respond   [19 chars]
    en  That's the morning.
    >>  ............................................
    pt  É a manhã.
    >>  ............................................
```


### Button `ask_four` — "Four places? Show me one."

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.archer.task` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.archer.task.ask_four` — accepted phrasings: "four places? show me one"
  - the message must contain one of: `places`, `four`, `unseen`
  - scored words: `places`(1.5), `four`(1.2), `unseen`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.task.respond.ask_four
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.task.respond.ask_four   [25 chars]
    en  Four places? Show me one.
    >>  ............................................
    pt  Quatro lugares? Me mostre um.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.archer.task.ask_four`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.archer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you seen lately?" | "Clear sightlines."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.task.ask_four
WHO    VILLAGER — what the player reads after pressing "Four places? Show me one."
       spoken on: conversations.topic.work.archer.task.respond, button `ask_four`
       leaves the player on: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.task.ask_four`: the villager explains. Subject `work.archer.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.archer.task.ask_four/1   [89 chars]
    en  Behind the smithy, where the wood stack has grown. I've asked twice for it to be shifted.
    >>  ............................................
    pt  Atrás da ferraria, onde a pilha de lenha cresceu. Já pedi duas vezes pra mudarem.
    >>  ............................................
  dialogue.conversations.work.prof.archer.task.ask_four/2   [80 chars]
    en  The gap by the well. And now that you know it, %1$s, you're one of three who do.
    >>  ............................................
    pt  A brecha perto do poço. E agora que você sabe, %1$s, você é um dos três que sabem.
    >>  ............................................
```


### Button `offer_hands` — "I could shift the wood stack."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.archer.task` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.archer.task.offer_hands` — accepted phrasings: "i could shift the wood stack"
  - the message must contain one of: `stack`, `shift`
  - scored words: `stack`(1.5), `shift`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.task.respond.offer_hands   [29 chars]
    en  I could shift the wood stack.
    >>  ............................................
    pt  Eu podia mudar a pilha de lenha.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.archer.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.archer.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.archer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you seen lately?" | "Clear sightlines."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I could shift the wood stack."
       spoken on: conversations.topic.work.archer.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.task.offer_hands`: the villager accepts. Subject `work.archer.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.archer.task.offer_hands/1   [85 chars]
    en  ...You could, and the smith will object, and I will enjoy watching that conversation.
    >>  ............................................
    pt  ...Podia, e o ferreiro vai reclamar, e eu vou adorar assistir essa conversa.
    >>  ............................................
  dialogue.conversations.work.prof.archer.task.offer_hands/2   [84 chars]
    en  Three carts' worth. If you're serious, %1$s, start at the far end and I'll join you.
    >>  ............................................
    pt  Três carroças. Se você está falando sério, %1$s, comece pelo fim e eu me junto.
    >>  ............................................
```


### Button `ask_draws` — "Two hundred every morning?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.archer.task` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.archer.task.ask_draws` — accepted phrasings: "two hundred every morning"
  - the message must contain one of: `draws`, `hundred`, `morning`
  - scored words: `draws`(1.5), `hundred`(1.2), `morning`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.task.respond.ask_draws
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.task.respond.ask_draws   [26 chars]
    en  Two hundred every morning?
    >>  ............................................
    pt  Duzentos toda manhã?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.archer.task.ask_draws`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.archer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you seen lately?" | "Clear sightlines."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.task.ask_draws
WHO    VILLAGER — what the player reads after pressing "Two hundred every morning?"
       spoken on: conversations.topic.work.archer.task.respond, button `ask_draws`
       leaves the player on: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.task.ask_draws`: the villager explains. Subject `work.archer.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.archer.task.ask_draws/1   [86 chars]
    en  Every morning for nine years. The morning I skip is the evening I'd find out about it.
    >>  ............................................
    pt  Toda manhã por nove anos. A manhã que eu pular é a noite em que eu descobriria.
    >>  ............................................
  dialogue.conversations.work.prof.archer.task.ask_draws/2   [80 chars]
    en  Two hundred, and the last twenty are the only ones that teach me anything, %1$s.
    >>  ............................................
    pt  Duzentos, e os últimos vinte são os únicos que me ensinam algo, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the sightline."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.archer.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.task.respond.leave   [39 chars]
    en  I'll let you get back to the sightline.
    >>  ............................................
    pt  Vou deixar você voltar pra sua linha de visão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the sightline."
       spoken on: conversations.topic.work.archer.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.left`: the villager accepts. Subject `work.archer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.archer.arrow_shortage.blocked.respond / leave; conversations.scene.work.archer.arrow_shortage.succeeded.respond / leave; conversations.scene.work.archer.followup / leave; conversations.scene.work.archer.missed_shot.active.respond / leave; conversations.scene.work.archer.missed_shot.succeeded.respond / leave; conversations.scene.work.archer.teaching_a_child.active.respond / leave; conversations.scene.work.archer.teaching_a_child.succeeded.respond / leave; conversations.topic.work.archer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.archer.arrow_shortage.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.archer.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.archer.village` — e.g. "Nothing has come over that wall in six years. That's the only sentence I'd let anyone write about me."


```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.archer.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.archer.village.respond   [25 chars]
    en  That's the account of it.
    >>  ............................................
    pt  É esse o balanço.
    >>  ............................................
```


### Button `ask_name` — "Does it bother you, the not knowing your name?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.archer.village` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.archer.village.ask_name` — accepted phrasings: "does it bother you, the not knowing your name"
  - the message must contain one of: `name`, `bother`, `anonymous`
  - scored words: `name`(1.5), `bother`(1.2), `anonymous`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.village.respond.ask_name
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.village.respond.ask_name   [46 chars]
    en  Does it bother you, the not knowing your name?
    >>  ............................................
    pt  Te incomoda, não saberem seu nome?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.archer.village.ask_name`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.archer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you seen lately?" | "Clear sightlines."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.village.ask_name
WHO    VILLAGER — what the player reads after pressing "Does it bother you, the not knowing your name?"
       spoken on: conversations.topic.work.archer.village.respond, button `ask_name`
       leaves the player on: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.village.ask_name`: the villager explains. Subject `work.archer.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.archer.village.ask_name/1   [89 chars]
    en  Less than it should. I've decided that's a thing about me rather than a thing about them.
    >>  ............................................
    pt  Menos do que deveria. Decidi que isso é sobre mim e não sobre eles.
    >>  ............................................
  dialogue.conversations.work.prof.archer.village.ask_name/2   [82 chars]
    en  Some evenings. Then something doesn't come over the wall and I'm even again, %1$s.
    >>  ............................................
    pt  Em algumas noites. Aí nada passa a muralha e eu fico quite de novo, %1$s.
    >>  ............................................
```


### Button `say_thanks` — "Six years is the whole reason this place has children in it."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.archer.village` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.archer.village.say_thanks` — accepted phrasings: "six years is the whole reason this place has children in it"
  - the message must contain one of: `six`, `children`, `reason`
  - scored words: `six`(1.2), `children`(1.5), `reason`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.village.respond.say_thanks   [60 chars]
    en  Six years is the whole reason this place has children in it.
    >>  ............................................
    pt  Seis anos é a razão inteira deste lugar ter crianças.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.archer.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.archer.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.archer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you seen lately?" | "Clear sightlines."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Six years is the whole reason this place has children in it."
       spoken on: conversations.topic.work.archer.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.village.say_thanks`: the villager accepts. Subject `work.archer.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.archer.village.say_thanks/1   [83 chars]
    en  ...That's the version I don't let myself say. Hearing it from outside is different.
    >>  ............................................
    pt  ...É a versão que eu não me deixo dizer. Ouvir de fora é diferente.
    >>  ............................................
  dialogue.conversations.work.prof.archer.village.say_thanks/2   [77 chars]
    en  Then say it to the fletcher too, %1$s. Half those six years are his feathers.
    >>  ............................................
    pt  Então diga ao flecheiro também, %1$s. Metade desses seis anos são as penas dele.
    >>  ............................................
```


### Button `ask_arrangement` — "Is that arrangement fair?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.archer.village` · offered only once the villager has actually said `work:archer`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.archer.village.ask_arrangement` — accepted phrasings: "is that arrangement fair"
  - the message must contain one of: `arrangement`, `fair`
  - scored words: `arrangement`(1.5), `fair`(1.5), `trade`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.village.respond.ask_arrangement
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.village.respond.ask_arrangement   [25 chars]
    en  Is that arrangement fair?
    >>  ............................................
    pt  Esse acordo é justo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.archer.village.ask_arrangement`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.archer.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What have you seen lately?" | "Clear sightlines."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.village.ask_arrangement
WHO    VILLAGER — what the player reads after pressing "Is that arrangement fair?"
       spoken on: conversations.topic.work.archer.village.respond, button `ask_arrangement`
       leaves the player on: conversations.topic.work.archer.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.village.ask_arrangement`: the villager explains. Subject `work.archer.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:archer` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.archer.village.ask_arrangement/1   [83 chars]
    en  It's the one I signed. Fair would mean somebody else takes a night, and nobody can.
    >>  ............................................
    pt  É o que eu assinei. Justo seria outra pessoa pegar uma noite, e ninguém pode.
    >>  ............................................
  dialogue.conversations.work.prof.archer.village.ask_arrangement/2   [90 chars]
    en  It's fair to them. Whether it's fair to me is a question I've been avoiding for six years.
    >>  ............................................
    pt  É justo pra eles. Se é justo pra mim é uma pergunta que eu evito há seis anos.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the sightline."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.archer.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.archer.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.archer.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.archer.village.respond.leave   [39 chars]
    en  I'll let you get back to the sightline.
    >>  ............................................
    pt  Vou deixar você voltar pra sua linha de visão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.archer.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the sightline."
       spoken on: conversations.topic.work.archer.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.archer.left`: the villager accepts. Subject `work.archer.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.archer.arrow_shortage.blocked.respond / leave; conversations.scene.work.archer.arrow_shortage.succeeded.respond / leave; conversations.scene.work.archer.followup / leave; conversations.scene.work.archer.missed_shot.active.respond / leave; conversations.scene.work.archer.missed_shot.succeeded.respond / leave; conversations.scene.work.archer.teaching_a_child.active.respond / leave; conversations.scene.work.archer.teaching_a_child.succeeded.respond / leave; conversations.topic.work.archer.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.archer.arrow_shortage.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

