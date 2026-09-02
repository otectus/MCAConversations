# Work talk with a librarian

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.librarian.acquisition.blocked.respond`](#conversations-scene-work-librarian-acquisition-blocked-respond)
- [`conversations.scene.work.librarian.acquisition.succeeded.respond`](#conversations-scene-work-librarian-acquisition-succeeded-respond)
- [`conversations.scene.work.librarian.damaged_volume.active.respond`](#conversations-scene-work-librarian-damaged-volume-active-respond)
- [`conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond`](#conversations-scene-work-librarian-damaged-volume-blocked-clarified-respond)
- [`conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond`](#conversations-scene-work-librarian-damaged-volume-blocked-resisted-respond)
- [`conversations.scene.work.librarian.damaged_volume.blocked.respond`](#conversations-scene-work-librarian-damaged-volume-blocked-respond)
- [`conversations.scene.work.librarian.damaged_volume.failed.respond`](#conversations-scene-work-librarian-damaged-volume-failed-respond)
- [`conversations.scene.work.librarian.damaged_volume.succeeded.respond`](#conversations-scene-work-librarian-damaged-volume-succeeded-respond)
- [`conversations.scene.work.librarian.followup`](#conversations-scene-work-librarian-followup)
- [`conversations.scene.work.librarian.reader_need.active.respond`](#conversations-scene-work-librarian-reader-need-active-respond)
- [`conversations.scene.work.librarian.reader_need.succeeded.respond`](#conversations-scene-work-librarian-reader-need-succeeded-respond)
- [`conversations.topic.work.librarian.craft.respond`](#conversations-topic-work-librarian-craft-respond)
- [`conversations.topic.work.librarian.followup`](#conversations-topic-work-librarian-followup)
- [`conversations.topic.work.librarian.future.respond`](#conversations-topic-work-librarian-future-respond)
- [`conversations.topic.work.librarian.respond`](#conversations-topic-work-librarian-respond)
- [`conversations.topic.work.librarian.risk.respond`](#conversations-topic-work-librarian-risk-respond)
- [`conversations.topic.work.librarian.task.respond`](#conversations-topic-work-librarian-task-respond)
- [`conversations.topic.work.librarian.village.respond`](#conversations-topic-work-librarian-village-respond)

---

## `conversations.scene.work.librarian.acquisition.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.librarian.acquisition.blocked` — e.g. "%2$s has %3$s and wants more for it than the library has had in a year. I have been doing sums that do not work since Tuesday."


```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.acquisition.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.librarian.acquisition.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.librarian.acquisition.blocked.respond   [15 chars]
    en  About the book.
    >>  ............................................
    pt  Sobre o livro.
    >>  ............................................
```


### Button `urge_ask_village` — "Ask the village to go in on it together."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.librarian.acquisition.blocked` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.acquisition.blocked.urge_ask_village` — accepted phrasings: "ask the village to go in on it together"; "ask the village to help buy it"; "get everyone to chip in"
  - the message must contain one of: `village`, `together`, `chip`
  - scored words: `village`(1.8), `together`(1.8), `chip`(1.8), `ask`(0.8), `help`(0.8), `buy`(0.8), `get`(0.8), `everyone`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.acquisition.blocked.respond.urge_ask_village
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.acquisition.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.acquisition.blocked.respond.urge_ask_village   [40 chars]
    en  Ask the village to go in on it together.
    >>  ............................................
    pt  Peça à vila para comprarem juntos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, familiarity +1  _(recorded under topic `work.librarian.acquisition`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.acquisition", "state": "succeeded"}
- Does: `conversations_thread` = {"op": "open", "template": "work.librarian.acquisition"}
- Then opens: `conversations.scene.work.librarian.followup`
- …where the player's next choices will be: "What's the hardest thing you've had to mend?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.acquisition.blocked.agreed
WHO    VILLAGER — what the player reads after pressing "Ask the village to go in on it together."
       spoken on: conversations.scene.work.librarian.acquisition.blocked.respond, button `urge_ask_village`
       leaves the player on: conversations.scene.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.acquisition.blocked.agreed`: the villager accepts. Subject `work.librarian.acquisition`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.acquisition.blocked.agreed/1   [124 chars]
    en  I had not thought of it as theirs to buy. That is my whole problem in one sentence, isn't it. Right. I will ask at the well.
    >>  ............................................
    pt  Não tinha pensado nele como algo que eles pudessem comprar. Meu problema inteiro numa frase, não é. Certo. Vou perguntar no poço.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.acquisition.blocked.agreed/2   [116 chars]
    en  They will say no and then they will say how much, which is how this village says yes. %2$s might actually come home.
    >>  ............................................
    pt  Vão dizer não e depois vão perguntar quanto, que é como esta vila diz sim. %2$s pode acabar vindo para casa.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.acquisition.blocked.agreed/3   [114 chars]
    en  Together. Yes. I have been treating %2$s as my private embarrassment when it is the village's book and always was.
    >>  ............................................
    pt  Juntos. Sim. Venho tratando %2$s como um constrangimento meu quando é o livro da vila, e sempre foi.
    >>  ............................................
```


### Button `ask_why_it_matters` — "Why does that one matter so much?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.librarian.acquisition.blocked` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.acquisition.blocked.ask_why_it_matters` — accepted phrasings: "why does that one matter so much"; "why does it matter to you"; "what makes that book worth it"
  - the message must contain one of: `matter`, `worth`
  - scored words: `matter`(1.8), `worth`(1.8), `why`(0.8), `does`(0.8), `one`(0.8), `much`(0.8), `makes`(0.8), `book`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.acquisition.blocked.respond.ask_why_it_matters
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.acquisition.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.acquisition.blocked.respond.ask_why_it_matters   [33 chars]
    en  Why does that one matter so much?
    >>  ............................................
    pt  Por que justamente esse importa tanto?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, trust +1  _(recorded under topic `work.librarian.acquisition`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.librarian.acquisition"}
- Then opens: `conversations.scene.work.librarian.followup`
- …where the player's next choices will be: "What's the hardest thing you've had to mend?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.acquisition.blocked.explained
WHO    VILLAGER — what the player reads after pressing "Why does that one matter so much?"
       spoken on: conversations.scene.work.librarian.acquisition.blocked.respond, button `ask_why_it_matters`
       leaves the player on: conversations.scene.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.acquisition.blocked.explained`: the villager explains. Subject `work.librarian.acquisition`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.acquisition.blocked.explained/1   [128 chars]
    en  Because there is one copy and I have seen it. Most of what I want does not exist any more; %2$s exists and is thirty miles away.
    >>  ............................................
    pt  Porque existe uma cópia e eu a vi. Quase tudo que eu quero não existe mais; %2$s existe e está a cinquenta quilômetros.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.acquisition.blocked.explained/2   [118 chars]
    en  It is not the book. It is that somebody a hundred years from now will want it and I will be the reason it is not here.
    >>  ............................................
    pt  Não é o livro. É que alguém daqui a cem anos vai querer, e eu serei o motivo de não estar aqui.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.acquisition.blocked.explained/3   [147 chars]
    en  %2$s has the years nobody wrote down anywhere else. Lose it and the village simply has no fifty years, and nobody will notice until they need them.
    >>  ............................................
    pt  %2$s tem os anos que ninguém anotou em outro lugar. Perca isso e a vila simplesmente não tem cinquenta anos, e ninguém vai notar até precisar deles.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the shelves."

*stance family `exit` · tone `plain` · answers the beat(s) `work.librarian.acquisition.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.acquisition.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.acquisition.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.acquisition.blocked.respond.leave   [30 chars]
    en  I'll leave you to the shelves.
    >>  ............................................
    pt  Vou deixar você com as prateleiras.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the shelves."
       spoken on: conversations.scene.work.librarian.acquisition.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.left`: the villager accepts. Subject `work.librarian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.librarian.acquisition.succeeded.respond / leave; conversations.scene.work.librarian.damaged_volume.active.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.respond / leave; conversations.scene.work.librarian.damaged_volume.failed.respond / leave; conversations.scene.work.librarian.damaged_volume.succeeded.respond / leave; conversations.scene.work.librarian.followup / leave …and 9 more
```

```text
  dialogue.conversations.work.prof.librarian.leave/1   [57 chars]
    en  They've waited this long. Another minute won't hurt them.
    >>  ............................................
    pt  Elas esperaram até agora. Mais um minuto não faz mal.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.leave/2   [28 chars]
    en  Quietly, if you would, %1$s.
    >>  ............................................
    pt  Em silêncio, se puder, %1$s.
    >>  ............................................
```

---


## `conversations.scene.work.librarian.acquisition.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.librarian.acquisition.succeeded` — e.g. "%2$s is here. Eleven households and the innkeeper, and the innkeeper gave twice what he could spare, which I am not going to forget."


```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.acquisition.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.librarian.acquisition.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.librarian.acquisition.succeeded.respond   [17 chars]
    en  You got it, then.
    >>  ............................................
    pt  Então conseguiu.
    >>  ............................................
```


### Button `ask_to_see` — "Can I see it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.librarian.acquisition.succeeded` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.acquisition.succeeded.ask_to_see` — accepted phrasings: "can i see it"; "may i look at it"; "let me see it"
  - the message must contain one of: `see`, `look`
  - scored words: `see`(1.8), `look`(1.8), `may`(0.8), `let`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.acquisition.succeeded.respond.ask_to_see
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.acquisition.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.acquisition.succeeded.respond.ask_to_see   [13 chars]
    en  Can I see it?
    >>  ............................................
    pt  Posso ver?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2, familiarity +1  _(recorded under topic `work.librarian.acquisition`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.librarian.acquisition"}
- Then opens: `conversations.scene.work.librarian.followup`
- …where the player's next choices will be: "What's the hardest thing you've had to mend?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.acquisition.succeeded.shown
WHO    VILLAGER — what the player reads after pressing "Can I see it?"
       spoken on: conversations.scene.work.librarian.acquisition.succeeded.respond, button `ask_to_see`
       leaves the player on: conversations.scene.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.acquisition.succeeded.shown`: the villager accepts. Subject `work.librarian.acquisition`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.acquisition.succeeded.shown/1   [111 chars]
    en  Hands, first. Then yes. I have been waiting three days for somebody to ask instead of nodding politely at %2$s.
    >>  ............................................
    pt  Mãos primeiro. Depois sim. Faz três dias que espero alguém pedir em vez de acenar educadamente para %2$s.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.acquisition.succeeded.shown/2   [138 chars]
    en  You may. Turn the leaves from the top corner, not the edge — and look at the third gathering, that is the part I have been going on about.
    >>  ............................................
    pt  Pode. Vire as folhas pelo canto de cima, não pela borda — e olhe o terceiro caderno, é dessa parte que eu não paro de falar.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.acquisition.succeeded.shown/3   [115 chars]
    en  Yes. Sit down. I will tell you what %2$s is while you look, and you may stop me at any point, and nobody ever does.
    >>  ............................................
    pt  Sim. Sente-se. Conto o que é %2$s enquanto você olha, e pode me interromper a qualquer momento, e ninguém nunca interrompe.
    >>  ............................................
```


### Button `credit_village` — "They gave because you asked properly."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.librarian.acquisition.succeeded` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.acquisition.succeeded.credit_village` — accepted phrasings: "they gave because you asked properly"; "they gave because of how you asked"; "that was you asking well"
  - the message must contain one of: `asked`, `gave`, `asking`
  - scored words: `asked`(1.8), `gave`(1.8), `asking`(1.8), `because`(0.8), `properly`(0.8), `well`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.acquisition.succeeded.respond.credit_village
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.acquisition.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.acquisition.succeeded.respond.credit_village   [37 chars]
    en  They gave because you asked properly.
    >>  ............................................
    pt  Eles deram porque você pediu direito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.librarian.acquisition.credit`, budget `standard`, replay policy `once`
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.librarian.acquisition`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.librarian.acquisition"}
- Then opens: `conversations.scene.work.librarian.followup`
- …where the player's next choices will be: "What's the hardest thing you've had to mend?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.acquisition.succeeded.deflected
WHO    VILLAGER — what the player reads after pressing "They gave because you asked properly."
       spoken on: conversations.scene.work.librarian.acquisition.succeeded.respond, button `credit_village`
       leaves the player on: conversations.scene.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.acquisition.succeeded.deflected`: the villager qualifys. Subject `work.librarian.acquisition`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.acquisition.succeeded.deflected/1   [118 chars]
    en  I asked badly, in fact. I stumbled over the number twice. They gave anyway, which says more about them than my asking.
    >>  ............................................
    pt  Pedi mal, na verdade. Tropecei no número duas vezes. Deram mesmo assim, o que diz mais sobre eles do que sobre o meu pedido.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.acquisition.succeeded.deflected/2   [123 chars]
    en  Perhaps. I had rehearsed a speech about posterity and what came out was 'it has our names in it', and that was what did it.
    >>  ............................................
    pt  Talvez. Eu tinha ensaiado um discurso sobre a posteridade e o que saiu foi 'tem os nossos nomes dentro', e foi isso que funcionou.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.acquisition.succeeded.deflected/3   [133 chars]
    en  You are trying to give me the credit for other people's generosity, and I am going to let you, once, because it has been a good week.
    >>  ............................................
    pt  Você está tentando me dar o crédito pela generosidade dos outros, e eu vou deixar, uma vez, porque foi uma boa semana.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the shelves."

*stance family `exit` · tone `plain` · answers the beat(s) `work.librarian.acquisition.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.acquisition.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.acquisition.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.acquisition.succeeded.respond.leave   [30 chars]
    en  I'll leave you to the shelves.
    >>  ............................................
    pt  Vou deixar você com as prateleiras.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the shelves."
       spoken on: conversations.scene.work.librarian.acquisition.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.left`: the villager accepts. Subject `work.librarian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.librarian.acquisition.blocked.respond / leave; conversations.scene.work.librarian.damaged_volume.active.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.respond / leave; conversations.scene.work.librarian.damaged_volume.failed.respond / leave; conversations.scene.work.librarian.damaged_volume.succeeded.respond / leave; conversations.scene.work.librarian.followup / leave …and 9 more
```

> Written out in full under **`conversations.scene.work.librarian.acquisition.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.librarian.damaged_volume.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.librarian.damaged_volume.active` — e.g. "%2$s is under cloth and weights. I change them twice a day and I look at it more often than that, which helps nobody."


```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.librarian.damaged_volume.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.active.respond   [12 chars]
    en  Under cloth.
    >>  ............................................
    pt  Sob panos.
    >>  ............................................
```


### Button `ask_progress` — "How much of it has come back?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.librarian.damaged_volume.active` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.damaged_volume.active.ask_progress` — accepted phrasings: "how much of it has come back"; "how much has recovered"; "how far along is it"
  - the message must contain one of: `much`, `recovered`, `along`
  - scored words: `much`(1.8), `recovered`(1.8), `along`(1.8), `come`(0.8), `back`(0.8), `far`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.active.respond.ask_progress
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.damaged_volume.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.active.respond.ask_progress   [29 chars]
    en  How much of it has come back?
    >>  ............................................
    pt  Quanto dele já voltou ao normal?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.librarian.damaged_volume`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.librarian.damaged_volume"}
- Then opens: `conversations.scene.work.librarian.followup`
- …where the player's next choices will be: "What's the hardest thing you've had to mend?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.active.answered
WHO    VILLAGER — what the player reads after pressing "How much of it has come back?"
       spoken on: conversations.scene.work.librarian.damaged_volume.active.respond, button `ask_progress`
       leaves the player on: conversations.scene.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.damaged_volume.active.answered`: the villager explains. Subject `work.librarian.damaged_volume`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.active.answered/1   [115 chars]
    en  The outer sheets, mostly. The middle of %2$s is still the middle of a wet book, and there is no hurrying that part.
    >>  ............................................
    pt  As folhas de fora, quase todas. O miolo de %2$s ainda é o miolo de um livro molhado, e essa parte não se apressa.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.active.answered/2   [126 chars]
    en  Enough that I have stopped expecting the worst. Ask me again in three days and I will either be smug about %2$s or very quiet.
    >>  ............................................
    pt  O bastante para eu ter parado de esperar o pior. Pergunte de novo em três dias: ou vou estar convencida por causa de %2$s, ou muito calada.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.active.answered/3   [106 chars]
    en  About two thirds. The trouble with %2$s is that the last third is always the part somebody actually wants.
    >>  ............................................
    pt  Uns dois terços. O problema de %2$s é que o último terço é sempre justamente a parte que alguém quer.
    >>  ............................................
```


### Button `glad` — "I'm glad it's out of the water, at least."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.librarian.damaged_volume.active` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.damaged_volume.active.glad` — accepted phrasings: "im glad its out of the water at least"; "glad its drying at least"; "im glad you got to it"
  - the message must contain one of: `glad`
  - scored words: `glad`(1.8), `its`(0.8), `out`(0.8), `water`(0.8), `least`(0.8), `drying`(0.8), `got`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.active.respond.glad
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.damaged_volume.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.active.respond.glad   [41 chars]
    en  I'm glad it's out of the water, at least.
    >>  ............................................
    pt  Fico feliz que pelo menos esteja fora da água.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.librarian.damaged_volume.glad`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — warmth +2  _(recorded under topic `work.librarian.damaged_volume`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.librarian.damaged_volume"}
- Then opens: `conversations.scene.work.librarian.followup`
- …where the player's next choices will be: "What's the hardest thing you've had to mend?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.active.shared
WHO    VILLAGER — what the player reads after pressing "I'm glad it's out of the water, at least."
       spoken on: conversations.scene.work.librarian.damaged_volume.active.respond, button `glad`
       leaves the player on: conversations.scene.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.damaged_volume.active.shared`: the villager accepts. Subject `work.librarian.damaged_volume`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.active.shared/1   [92 chars]
    en  So am I. I carried %2$s out of that cupboard at arm's length like something that might bite.
    >>  ............................................
    pt  Eu também. Carreguei %2$s para fora daquele armário com o braço estendido, como algo que pudesse morder.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.active.shared/2   [87 chars]
    en  It is out of the water and I am not out of the woods, but yes. Thank you for saying so.
    >>  ............................................
    pt  Está fora da água e eu não estou fora do perigo, mas sim. Obrigada por dizer.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.active.shared/3   [115 chars]
    en  I keep telling myself the same thing at the end of every hour. It helps more when somebody else says it about %2$s.
    >>  ............................................
    pt  Fico repetindo isso para mim a cada hora. Ajuda mais quando outra pessoa diz sobre %2$s.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the shelves."

*stance family `exit` · tone `plain` · answers the beat(s) `work.librarian.damaged_volume.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.damaged_volume.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.active.respond.leave   [30 chars]
    en  I'll leave you to the shelves.
    >>  ............................................
    pt  Vou deixar você com as prateleiras.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the shelves."
       spoken on: conversations.scene.work.librarian.damaged_volume.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.left`: the villager accepts. Subject `work.librarian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.librarian.acquisition.blocked.respond / leave; conversations.scene.work.librarian.acquisition.succeeded.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.respond / leave; conversations.scene.work.librarian.damaged_volume.failed.respond / leave; conversations.scene.work.librarian.damaged_volume.succeeded.respond / leave; conversations.scene.work.librarian.followup / leave …and 9 more
```

> Written out in full under **`conversations.scene.work.librarian.acquisition.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond`

**Reached from 1 route(s):** `conversations.scene.work.librarian.damaged_volume.blocked.respond` / `ask_which`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.librarian.damaged_volume.blocked.clarified` — e.g. "The names. A warped page can be pressed back over a winter; %2$s cannot be un-run. Whoever wrote them down is gone, and so is anyone who could tell me what they said."


```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond   [10 chars]
    en  So: which?
    >>  ............................................
    pt  Então: qual dos dois?
    >>  ............................................
```


### Button `offer_wool` — "Then let me bring you wool before you start."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.librarian.damaged_volume.blocked.clarified` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.damaged_volume.blocked.clarified.offer_wool` — accepted phrasings: "then let me bring you wool before you start"; "let me bring wool first"; "wait bring wool before you begin"
  - the message must contain one of: `wool`
  - scored words: `wool`(1.8), `let`(0.8), `bring`(0.8), `before`(0.8), `start`(0.8), `first`(0.8), `wait`(0.8), `begin`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond.offer_wool
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond.offer_wool   [44 chars]
    en  Then let me bring you wool before you start.
    >>  ............................................
    pt  Então deixe eu trazer lã antes de você começar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.librarian.damaged_volume.offer_informed`, budget `standard`, replay policy `once`
- Does: disposition — trust +3, warmth +2  _(recorded under topic `work.librarian.damaged_volume`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.damaged_volume", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.librarian.damaged_volume", "obligation": "commitment:work.librarian.bring_absorbent"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.librarian.bring_absorbent"}
- Then opens: `conversations.scene.work.librarian.followup`
- …where the player's next choices will be: "What's the hardest thing you've had to mend?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked.clarified.accepted
WHO    VILLAGER — what the player reads after pressing "Then let me bring you wool before you start."
       spoken on: conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond, button `offer_wool`
       leaves the player on: conversations.scene.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.damaged_volume.blocked.clarified.accepted`: the villager accepts. Subject `work.librarian.damaged_volume`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.clarified.accepted/1   [114 chars]
    en  Then I will leave %2$s where it is until you come. Thank you — I mean that, and I am not always good at saying it.
    >>  ............................................
    pt  Então vou deixar %2$s onde está até você voltar. Obrigada — falo sério, e nem sempre sei dizer isso direito.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.clarified.accepted/2   [72 chars]
    en  Wool. Of course it is wool. I will not start on %2$s until you are back.
    >>  ............................................
    pt  Lã. Claro que era lã. Não vou mexer em %2$s até você voltar.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.clarified.accepted/3   [136 chars]
    en  Wool. Yes. Then nothing happens to %2$s until you are standing here again, and I will find something else to fret about in the meantime.
    >>  ............................................
    pt  Lã. Isso. Então nada acontece com %2$s até você estar aqui de novo, e enquanto isso eu arranjo outra coisa para me preocupar.
    >>  ............................................
```


### Button `defer_to_her` — "It's your book and your hands. You'll know."

*stance family `restraint` · tone `plain` · outcome `qualified` · answers the beat(s) `work.librarian.damaged_volume.blocked.clarified` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.damaged_volume.blocked.clarified.defer_to_her` — accepted phrasings: "its your book and your hands youll know"; "your book your call"; "your hands your judgement"
  - the message must contain one of: `book`, `hands`
  - scored words: `book`(1.8), `hands`(1.8), `its`(0.8), `youll`(0.8), `know`(0.8), `call`(0.8), `judgement`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond.defer_to_her
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond.defer_to_her   [43 chars]
    en  It's your book and your hands. You'll know.
    >>  ............................................
    pt  O livro é seu e as mãos são suas. Você vai saber.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, familiarity +1  _(recorded under topic `work.librarian.damaged_volume`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.librarian.damaged_volume"}
- Then opens: `conversations.scene.work.librarian.followup`
- …where the player's next choices will be: "What's the hardest thing you've had to mend?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked.clarified.deferred
WHO    VILLAGER — what the player reads after pressing "It's your book and your hands. You'll know."
       spoken on: conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond, button `defer_to_her`
       leaves the player on: conversations.scene.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.damaged_volume.blocked.clarified.deferred`: the villager accepts. Subject `work.librarian.damaged_volume`, polarity `mixed`, permits followup, outcome `qualified`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.clarified.deferred/1   [149 chars]
    en  That is either great faith or great convenience. I will take it as faith. %2$s gets the blotting cloth, then, and I will live with the crooked pages.
    >>  ............................................
    pt  Isso é ou muita fé ou muita conveniência. Vou tomar como fé. %2$s fica com o pano absorvente, então, e eu convivo com as páginas tortas.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.clarified.deferred/2   [167 chars]
    en  You are the first this week to hand it back to me instead of settling it for me. Everyone has an opinion about %2$s and none of them wants to be the one who was wrong.
    >>  ............................................
    pt  Você é a primeira pessoa esta semana a devolver a decisão em vez de resolvê-la por mim. Todo mundo tem opinião sobre %2$s e nenhuma delas quer ser a que errou.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.clarified.deferred/3   [151 chars]
    en  Then I will decide, and I will be the one who decided. That is worse and better at once. Come back and I will tell you what %2$s looks like afterwards.
    >>  ............................................
    pt  Então eu decido, e eu serei quem decidiu. Isso é pior e melhor ao mesmo tempo. Volte e eu conto como %2$s ficou depois.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the shelves."

*stance family `exit` · tone `plain` · answers the beat(s) `work.librarian.damaged_volume.blocked.clarified` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond.leave   [30 chars]
    en  I'll leave you to the shelves.
    >>  ............................................
    pt  Vou deixar você com as prateleiras.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the shelves."
       spoken on: conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.left`: the villager accepts. Subject `work.librarian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.librarian.acquisition.blocked.respond / leave; conversations.scene.work.librarian.acquisition.succeeded.respond / leave; conversations.scene.work.librarian.damaged_volume.active.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.respond / leave; conversations.scene.work.librarian.damaged_volume.failed.respond / leave; conversations.scene.work.librarian.damaged_volume.succeeded.respond / leave; conversations.scene.work.librarian.followup / leave …and 9 more
```

> Written out in full under **`conversations.scene.work.librarian.acquisition.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond`

**Reached from 1 route(s):** `conversations.scene.work.librarian.damaged_volume.blocked.respond` / `advise_ink`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.librarian.damaged_volume.blocked.resisted` — e.g. "That is easy to say about a book you have not held. Press %2$s wrong once and the binding goes, and then there is nothing to save the ink onto."


```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond   [15 chars]
    en  So we disagree.
    >>  ............................................
    pt  Então discordamos.
    >>  ............................................
```


### Button `hold_position` — "I still think the words matter more than the shape."

*stance family `challenge` · tone `plain` · outcome `qualified` · answers the beat(s) `work.librarian.damaged_volume.blocked.resisted` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.damaged_volume.blocked.resisted.hold_position` — accepted phrasings: "i still think the words matter more than the shape"; "the words matter more than the shape"; "i stand by that words over shape"
  - the message must contain one of: `words`, `shape`
  - scored words: `words`(1.8), `shape`(1.8), `still`(0.8), `think`(0.8), `matter`(0.8), `more`(0.8), `stand`(0.8), `over`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond.hold_position
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond.hold_position   [51 chars]
    en  I still think the words matter more than the shape.
    >>  ............................................
    pt  Continuo achando que as palavras importam mais do que a forma.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +2, tension +1  _(recorded under topic `work.librarian.damaged_volume`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.librarian.damaged_volume"}
- Then opens: `conversations.scene.work.librarian.followup`
- …where the player's next choices will be: "What's the hardest thing you've had to mend?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked.resisted.held
WHO    VILLAGER — what the player reads after pressing "I still think the words matter more than the shape."
       spoken on: conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond, button `hold_position`
       leaves the player on: conversations.scene.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.damaged_volume.blocked.resisted.held`: the villager qualifys. Subject `work.librarian.damaged_volume`, polarity `mixed`, permits followup, outcome `qualified`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.resisted.held/1   [145 chars]
    en  We can disagree. I would rather that than have you agree with me to be pleasant. I am still not taking %2$s apart on a stranger's opinion of ink.
    >>  ............................................
    pt  Podemos discordar. Prefiro isso a você concordar comigo só para ser gentil. Mas continuo não desmontando %2$s por causa da opinião de um estranho sobre tinta.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.resisted.held/2   [84 chars]
    en  Say it again in a month and I may have come round. Not today. Today %2$s stays sewn.
    >>  ............................................
    pt  Repita isso daqui a um mês e talvez eu tenha mudado de ideia. Hoje não. Hoje %2$s continua costurado.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.resisted.held/3   [112 chars]
    en  Noted, and refused, and I am glad you said it. Half the village would have nodded and let me ruin %2$s in peace.
    >>  ............................................
    pt  Anotado, recusado, e fico feliz que você tenha falado. Metade da vila teria concordado com a cabeça e me deixado estragar %2$s em paz.
    >>  ............................................
```


### Button `concede` — "Fair enough. Your hands, your call."

*stance family `restraint` · tone `plain` · outcome `qualified` · answers the beat(s) `work.librarian.damaged_volume.blocked.resisted` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.damaged_volume.blocked.resisted.concede` — accepted phrasings: "fair enough your hands your call"; "fair enough your call"; "you know it better than i do"
  - the message must contain one of: `fair`, `call`, `better`
  - scored words: `fair`(1.8), `call`(1.8), `better`(1.8), `enough`(0.8), `hands`(0.8), `know`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond.concede
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond.concede   [35 chars]
    en  Fair enough. Your hands, your call.
    >>  ............................................
    pt  Justo. Suas mãos, sua decisão.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.librarian.damaged_volume`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.librarian.damaged_volume"}
- Then opens: `conversations.scene.work.librarian.followup`
- …where the player's next choices will be: "What's the hardest thing you've had to mend?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked.resisted.settled
WHO    VILLAGER — what the player reads after pressing "Fair enough. Your hands, your call."
       spoken on: conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond, button `concede`
       leaves the player on: conversations.scene.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.damaged_volume.blocked.resisted.settled`: the villager accepts. Subject `work.librarian.damaged_volume`, polarity `mixed`, permits followup, outcome `qualified`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.resisted.settled/1   [133 chars]
    en  Thank you. And for what it is worth you were half right — I will lift the worst page out and copy it before I press the rest of %2$s.
    >>  ............................................
    pt  Obrigada. E, para constar, você tinha meia razão — vou tirar a pior página e copiá-la antes de prensar o resto de %2$s.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.resisted.settled/2   [83 chars]
    en  Then I will do the slow thing and be quietly sure it was correct. %2$s stays whole.
    >>  ............................................
    pt  Então faço do jeito lento e fico discretamente convencida de que estava certo. %2$s continua inteiro.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.resisted.settled/3   [139 chars]
    en  Good. I argue better when somebody lets me finish. The compromise is the one you suggested, mostly, and I will not be admitting that twice.
    >>  ............................................
    pt  Ótimo. Eu discuto melhor quando me deixam terminar. O meio-termo é o que você sugeriu, quase todo, e não vou admitir isso duas vezes.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the shelves."

*stance family `exit` · tone `plain` · answers the beat(s) `work.librarian.damaged_volume.blocked.resisted` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond.leave   [30 chars]
    en  I'll leave you to the shelves.
    >>  ............................................
    pt  Vou deixar você com as prateleiras.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the shelves."
       spoken on: conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.left`: the villager accepts. Subject `work.librarian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.librarian.acquisition.blocked.respond / leave; conversations.scene.work.librarian.acquisition.succeeded.respond / leave; conversations.scene.work.librarian.damaged_volume.active.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.respond / leave; conversations.scene.work.librarian.damaged_volume.failed.respond / leave; conversations.scene.work.librarian.damaged_volume.succeeded.respond / leave; conversations.scene.work.librarian.followup / leave …and 9 more
```

> Written out in full under **`conversations.scene.work.librarian.acquisition.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.librarian.damaged_volume.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.librarian.damaged_volume.blocked` — e.g. "I took %2$s out of the flood cupboard this morning and %3$s had got right through the gathering. I can press the pages flat or I can save the ink. Not both, not with what I have here."


```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.librarian.damaged_volume.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.respond   [15 chars]
    en  The back table.
    >>  ............................................
    pt  A mesa do fundo.
    >>  ............................................
```


### Button `ask_which` — "What do you lose if you press it flat?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.librarian.damaged_volume.blocked` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.damaged_volume.blocked.ask_which` — accepted phrasings: "what do you lose if you press it flat"; "what would pressing it cost"; "what happens if you flatten it"
  - the message must contain one of: `lose`, `press`, `flat`, `pressing`, `flatten`
  - scored words: `lose`(1.8), `press`(1.8), `flat`(1.8), `pressing`(1.8), `flatten`(1.8), `cost`(0.8), `happens`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked.respond.ask_which
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.damaged_volume.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.respond.ask_which   [38 chars]
    en  What do you lose if you press it flat?
    >>  ............................................
    pt  O que você perde se prensar as páginas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.librarian.damaged_volume`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.librarian.damaged_volume"}
- Then opens: `conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond`
- …where the player's next choices will be: "Then let me bring you wool before you start." | "It's your book and your hands. You'll know." | "I'll leave you to the shelves."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked.clarified
WHO    VILLAGER — what the player reads after pressing "What do you lose if you press it flat?"
       spoken on: conversations.scene.work.librarian.damaged_volume.blocked.respond, button `ask_which`
       leaves the player on: conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.damaged_volume.blocked.clarified`: the villager explains. Subject `work.librarian.damaged_volume`, polarity `mixed`, invites followup, outcome `engaged`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: practical_help, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.clarified/1   [166 chars]
    en  The names. A warped page can be pressed back over a winter; %2$s cannot be un-run. Whoever wrote them down is gone, and so is anyone who could tell me what they said.
    >>  ............................................
    pt  Os nomes. Uma página empenada volta ao lugar depois de um inverno; %2$s não tem volta. Quem os escreveu já se foi, e também quem poderia me dizer o que diziam.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.clarified/2   [89 chars]
    en  Everything and nothing. The paper survives either way. %2$s takes the part that mattered.
    >>  ............................................
    pt  Tudo e nada. O papel sobrevive de qualquer jeito. %2$s leva justamente a parte que importava.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.clarified/3   [145 chars]
    en  The names, and the dates beside them. Paper I can flatten. %2$s is simply gone, and everyone who could have told me what it said is gone with it.
    >>  ............................................
    pt  Os nomes, e as datas ao lado deles. Papel eu consigo aplainar. %2$s simplesmente se foi, e todos que poderiam me dizer o que estava escrito se foram junto.
    >>  ............................................
```


### Button `advise_ink` — "Save the ink. A book nobody can read is only paper."

*stance family `candor` · tone `plain` · outcome `resisted` · answers the beat(s) `work.librarian.damaged_volume.blocked` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.damaged_volume.blocked.advise_ink` — accepted phrasings: "save the ink a book nobody can read is only paper"; "the ink matters more than the pages"; "a book nobody can read is only paper"
  - the message must contain one of: `ink`, `book`
  - scored words: `ink`(1.8), `book`(1.8), `save`(0.8), `nobody`(0.8), `read`(0.8), `paper`(0.8), `matters`(0.8), `more`(0.8), `than`(0.8), `pages`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked.respond.advise_ink
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.damaged_volume.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.respond.advise_ink   [51 chars]
    en  Save the ink. A book nobody can read is only paper.
    >>  ............................................
    pt  Salve a tinta. Um livro que ninguém consegue ler é só papel.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +1, tension +1  _(recorded under topic `work.librarian.damaged_volume`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.librarian.damaged_volume"}
- Then opens: `conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond`
- …where the player's next choices will be: "I still think the words matter more than the shape." | "Fair enough. Your hands, your call." | "I'll leave you to the shelves."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked.resisted
WHO    VILLAGER — what the player reads after pressing "Save the ink. A book nobody can read is only paper."
       spoken on: conversations.scene.work.librarian.damaged_volume.blocked.respond, button `advise_ink`
       leaves the player on: conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.damaged_volume.blocked.resisted`: the villager resists. Subject `work.librarian.damaged_volume`, polarity `mixed`, invites followup, outcome `resisted`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: challenge, restraint, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.resisted/1   [143 chars]
    en  That is easy to say about a book you have not held. Press %2$s wrong once and the binding goes, and then there is nothing to save the ink onto.
    >>  ............................................
    pt  É fácil dizer isso de um livro que você nunca segurou. Prense %2$s errado uma vez e a costura se rompe, e aí não sobra onde a tinta possa ficar.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.resisted/2   [109 chars]
    en  No. The shape is the record too. If %2$s comes apart in my hands nobody will care how sharp the letters were.
    >>  ............................................
    pt  Não. A forma também é o registro. Se %2$s desmanchar nas minhas mãos, ninguém vai se importar com o quanto as letras estavam nítidas.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.resisted/3   [137 chars]
    en  You would not say that holding it. Take %2$s apart to save the ink and you have a box of loose sheets nobody will ever put back in order.
    >>  ............................................
    pt  Você não diria isso se estivesse segurando. Desmonte %2$s para salvar a tinta e você fica com uma caixa de folhas soltas que ninguém vai reordenar.
    >>  ............................................
```


### Button `no_advice` — "That's beyond me. I'd only be guessing."

*stance family `restraint` · tone `plain` · outcome `qualified` · answers the beat(s) `work.librarian.damaged_volume.blocked` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.damaged_volume.blocked.no_advice` — accepted phrasings: "thats beyond me id only be guessing"; "that is beyond me id only be guessing"; "im guessing if i answer that"
  - the message must contain one of: `beyond`, `guessing`
  - scored words: `beyond`(1.8), `guessing`(1.8), `thats`(0.8), `answer`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked.respond.no_advice
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.damaged_volume.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.respond.no_advice   [39 chars]
    en  That's beyond me. I'd only be guessing.
    >>  ............................................
    pt  Isso está além de mim. Eu só estaria chutando.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +1  _(recorded under topic `work.librarian.damaged_volume`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.librarian.damaged_volume"}
- Then opens: `conversations.scene.work.librarian.followup`
- …where the player's next choices will be: "What's the hardest thing you've had to mend?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked.excused
WHO    VILLAGER — what the player reads after pressing "That's beyond me. I'd only be guessing."
       spoken on: conversations.scene.work.librarian.damaged_volume.blocked.respond, button `no_advice`
       leaves the player on: conversations.scene.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.damaged_volume.blocked.excused`: the villager qualifys. Subject `work.librarian.damaged_volume`, polarity `mixed`, permits followup, outcome `qualified`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.excused/1   [110 chars]
    en  That's fair. Hardly anyone does, and they answer anyway. I think I only wanted to hear myself say it out loud.
    >>  ............................................
    pt  É justo. Quase ninguém entende, e respondem mesmo assim. Acho que eu só queria me ouvir dizendo isso em voz alta.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.excused/2   [111 chars]
    en  Nobody has to. I have been arguing with myself all morning and I wanted a second voice in the room, that's all.
    >>  ............................................
    pt  Ninguém precisa entender. Passei a manhã discutindo comigo mesma e queria outra voz na sala, só isso.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.excused/3   [109 chars]
    en  That's fair, and I'd rather you said so. I think I only wanted the sentence out of my head and into the room.
    >>  ............................................
    pt  É justo, e prefiro que você diga. Acho que eu só queria tirar a frase da minha cabeça e colocá-la na sala.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the shelves."

*stance family `exit` · tone `plain` · answers the beat(s) `work.librarian.damaged_volume.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.damaged_volume.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.blocked.respond.leave   [30 chars]
    en  I'll leave you to the shelves.
    >>  ............................................
    pt  Vou deixar você com as prateleiras.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the shelves."
       spoken on: conversations.scene.work.librarian.damaged_volume.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.left`: the villager accepts. Subject `work.librarian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.librarian.acquisition.blocked.respond / leave; conversations.scene.work.librarian.acquisition.succeeded.respond / leave; conversations.scene.work.librarian.damaged_volume.active.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond / leave; conversations.scene.work.librarian.damaged_volume.failed.respond / leave; conversations.scene.work.librarian.damaged_volume.succeeded.respond / leave; conversations.scene.work.librarian.followup / leave …and 9 more
```

> Written out in full under **`conversations.scene.work.librarian.acquisition.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.librarian.damaged_volume.failed.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.librarian.damaged_volume.failed` — e.g. "%2$s did not come back. %3$s took the middle of it, and the middle was the part anyone would have wanted."


```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.failed.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.librarian.damaged_volume.failed.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.failed.respond   [20 chars]
    en  It didn't come back.
    >>  ............................................
    pt  Não voltou.
    >>  ............................................
```


### Button `sit_with_it` — "That's a real loss. I'm sorry."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.librarian.damaged_volume.failed` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.damaged_volume.failed.sit_with_it` — accepted phrasings: "thats a real loss im sorry"; "im sorry thats a genuine loss"; "thats a loss and im sorry for it"
  - the message must contain one of: `loss`, `sorry`
  - scored words: `loss`(1.8), `sorry`(1.8), `thats`(0.8), `real`(0.8), `genuine`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.failed.respond.sit_with_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.damaged_volume.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.failed.respond.sit_with_it   [30 chars]
    en  That's a real loss. I'm sorry.
    >>  ............................................
    pt  É uma perda de verdade. Sinto muito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.librarian.damaged_volume.mourn`, budget `standard`, replay policy `once`
- Does: disposition — warmth +3, trust +2  _(recorded under topic `work.librarian.damaged_volume`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.librarian.damaged_volume"}
- Then opens: `conversations.scene.work.librarian.followup`
- …where the player's next choices will be: "What's the hardest thing you've had to mend?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.failed.received
WHO    VILLAGER — what the player reads after pressing "That's a real loss. I'm sorry."
       spoken on: conversations.scene.work.librarian.damaged_volume.failed.respond, button `sit_with_it`
       leaves the player on: conversations.scene.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.damaged_volume.failed.received`: the villager qualifys. Subject `work.librarian.damaged_volume`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.failed.received/1   [146 chars]
    en  It is. Thank you for calling it that instead of telling me it was only a book. The covers are on my desk. I have not decided what to do with them.
    >>  ............................................
    pt  É mesmo. Obrigada por chamar assim, em vez de me dizer que era só um livro. As capas estão na minha mesa. Ainda não decidi o que fazer com elas.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.failed.received/2   [101 chars]
    en  Four sheets and the index. I copied the index out twice in case my hand is the next thing that fails.
    >>  ............................................
    pt  Quatro folhas e o índice. Copiei o índice duas vezes, caso minha mão seja a próxima coisa a falhar.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.failed.received/3   [129 chars]
    en  The index and four sheets of %2$s. I have copied the index twice over, which changes nothing and made me feel better for an hour.
    >>  ............................................
    pt  O índice e quatro folhas de %2$s. Copiei o índice duas vezes, o que não muda nada e me fez sentir melhor por uma hora.
    >>  ............................................
```


### Button `ask_what_survived` — "Did any of it survive?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.librarian.damaged_volume.failed` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.damaged_volume.failed.ask_what_survived` — accepted phrasings: "did any of it survive"; "was anything left of it"; "did anything come through"
  - the message must contain one of: `survive`, `survived`, `left`, `anything`
  - scored words: `survive`(1.8), `survived`(1.8), `left`(1.8), `anything`(1.8), `any`(0.8), `come`(0.8), `through`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.failed.respond.ask_what_survived
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.damaged_volume.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.failed.respond.ask_what_survived   [22 chars]
    en  Did any of it survive?
    >>  ............................................
    pt  Sobrou alguma coisa dele?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.librarian.damaged_volume`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.librarian.damaged_volume"}
- Then opens: `conversations.scene.work.librarian.followup`
- …where the player's next choices will be: "What's the hardest thing you've had to mend?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.failed.counted
WHO    VILLAGER — what the player reads after pressing "Did any of it survive?"
       spoken on: conversations.scene.work.librarian.damaged_volume.failed.respond, button `ask_what_survived`
       leaves the player on: conversations.scene.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.damaged_volume.failed.counted`: the villager explains. Subject `work.librarian.damaged_volume`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.failed.counted/1   [107 chars]
    en  The binding, which is worth nothing, and the first quire, which is worth everything and is four pages long.
    >>  ............................................
    pt  A costura, que não vale nada, e o primeiro caderno, que vale tudo e tem quatro páginas.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.failed.counted/2   [102 chars]
    en  Enough to prove %2$s existed. Not enough to be it. I will keep the covers on the shelf where it stood.
    >>  ............................................
    pt  O bastante para provar que %2$s existiu. Não o bastante para ser ele. Vou guardar as capas na prateleira onde ele ficava.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.failed.counted/3   [141 chars]
    en  Two names and a date. I have written them into the new ledger with a note saying where they came from, which is the only kind of rescue left.
    >>  ............................................
    pt  Dois nomes e uma data. Passei para o livro novo com uma nota dizendo de onde vieram, que é o único tipo de resgate que restou.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the shelves."

*stance family `exit` · tone `plain` · answers the beat(s) `work.librarian.damaged_volume.failed` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.failed.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.damaged_volume.failed.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.failed.respond.leave   [30 chars]
    en  I'll leave you to the shelves.
    >>  ............................................
    pt  Vou deixar você com as prateleiras.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the shelves."
       spoken on: conversations.scene.work.librarian.damaged_volume.failed.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.left`: the villager accepts. Subject `work.librarian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.librarian.acquisition.blocked.respond / leave; conversations.scene.work.librarian.acquisition.succeeded.respond / leave; conversations.scene.work.librarian.damaged_volume.active.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.respond / leave; conversations.scene.work.librarian.damaged_volume.succeeded.respond / leave; conversations.scene.work.librarian.followup / leave …and 9 more
```

> Written out in full under **`conversations.scene.work.librarian.acquisition.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.librarian.damaged_volume.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.librarian.damaged_volume.succeeded` — e.g. "%2$s held. Not prettily — it will never sit flat again — but every name in it can be read, and that was the whole argument."


```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.librarian.damaged_volume.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.succeeded.respond   [18 chars]
    en  Back on the shelf.
    >>  ............................................
    pt  De volta à prateleira.
    >>  ............................................
```


### Button `credit_her` — "You saved it. I only carried something heavy."

*stance family `candor` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.librarian.damaged_volume.succeeded` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.damaged_volume.succeeded.credit_her` — accepted phrasings: "you saved it i only carried something heavy"; "that was your work not mine"; "you saved it"
  - the message must contain one of: `saved`, `carried`, `work`
  - scored words: `saved`(1.8), `carried`(1.8), `work`(1.8), `only`(0.8), `something`(0.8), `heavy`(0.8), `mine`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.succeeded.respond.credit_her
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.damaged_volume.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.succeeded.respond.credit_her   [45 chars]
    en  You saved it. I only carried something heavy.
    >>  ............................................
    pt  Você salvou. Eu só carreguei uma coisa pesada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.librarian.damaged_volume.credit`, budget `standard`, replay policy `once`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.librarian.damaged_volume`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.librarian.damaged_volume"}
- Then opens: `conversations.scene.work.librarian.followup`
- …where the player's next choices will be: "What's the hardest thing you've had to mend?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.succeeded.credited
WHO    VILLAGER — what the player reads after pressing "You saved it. I only carried something heavy."
       spoken on: conversations.scene.work.librarian.damaged_volume.succeeded.respond, button `credit_her`
       leaves the player on: conversations.scene.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.damaged_volume.succeeded.credited`: the villager qualifys. Subject `work.librarian.damaged_volume`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.succeeded.credited/1   [115 chars]
    en  Carrying something heavy at the right hour is most of it. I would still be arguing with myself over %2$s otherwise.
    >>  ............................................
    pt  Carregar algo pesado na hora certa é quase tudo. Sem isso, eu ainda estaria discutindo comigo mesma por causa de %2$s.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.succeeded.credited/2   [112 chars]
    en  Take the credit. I am going to be insufferable about %2$s for a week and you may as well have earned some of it.
    >>  ............................................
    pt  Aceite o crédito. Vou ser insuportável por causa de %2$s durante uma semana, e é melhor que parte disso tenha sido merecida por você.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.succeeded.credited/3   [128 chars]
    en  I will allow you a share of it. Not the whole thing — I did the sewing — but a share. %2$s would not be on that shelf otherwise.
    >>  ............................................
    pt  Vou permitir que você fique com uma parte. Não com tudo — a costura foi minha — mas uma parte. %2$s não estaria naquela prateleira sem isso.
    >>  ............................................
```


### Button `ask_readable` — "Can the whole of it be read now?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.librarian.damaged_volume.succeeded` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.damaged_volume.succeeded.ask_readable` — accepted phrasings: "can the whole of it be read now"; "is all of it readable"; "can you read it all again"
  - the message must contain one of: `read`, `readable`
  - scored words: `read`(1.8), `readable`(1.8), `whole`(0.8), `now`(0.8), `all`(0.8), `again`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.succeeded.respond.ask_readable
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.damaged_volume.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.succeeded.respond.ask_readable   [32 chars]
    en  Can the whole of it be read now?
    >>  ............................................
    pt  Dá para ler ele inteiro agora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.librarian.damaged_volume`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.librarian.damaged_volume"}
- Then opens: `conversations.scene.work.librarian.followup`
- …where the player's next choices will be: "What's the hardest thing you've had to mend?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.succeeded.assessed
WHO    VILLAGER — what the player reads after pressing "Can the whole of it be read now?"
       spoken on: conversations.scene.work.librarian.damaged_volume.succeeded.respond, button `ask_readable`
       leaves the player on: conversations.scene.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.damaged_volume.succeeded.assessed`: the villager explains. Subject `work.librarian.damaged_volume`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.succeeded.assessed/1   [123 chars]
    en  All but four lines, and I know what three of them said from another copy. The fourth I will have to ask somebody old about.
    >>  ............................................
    pt  Tudo menos quatro linhas, e sei o que três delas diziam por outra cópia. A quarta vou ter de perguntar a alguém idoso.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.succeeded.assessed/2   [86 chars]
    en  Every page of %2$s, if you hold it to a window. That counts. I have decided it counts.
    >>  ............................................
    pt  Toda página de %2$s, se você segurar contra a janela. Isso conta. Decidi que conta.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.damaged_volume.succeeded.assessed/3   [133 chars]
    en  Yes, and the margins are legible too, which I had written off entirely. Somebody's grandmother left notes in %2$s and they came back.
    >>  ............................................
    pt  Sim, e as margens também estão legíveis, o que eu tinha dado por perdido. A avó de alguém deixou notas em %2$s e elas voltaram.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the shelves."

*stance family `exit` · tone `plain` · answers the beat(s) `work.librarian.damaged_volume.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.damaged_volume.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.damaged_volume.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.damaged_volume.succeeded.respond.leave   [30 chars]
    en  I'll leave you to the shelves.
    >>  ............................................
    pt  Vou deixar você com as prateleiras.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the shelves."
       spoken on: conversations.scene.work.librarian.damaged_volume.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.left`: the villager accepts. Subject `work.librarian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.librarian.acquisition.blocked.respond / leave; conversations.scene.work.librarian.acquisition.succeeded.respond / leave; conversations.scene.work.librarian.damaged_volume.active.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.respond / leave; conversations.scene.work.librarian.damaged_volume.failed.respond / leave; conversations.scene.work.librarian.followup / leave …and 9 more
```

> Written out in full under **`conversations.scene.work.librarian.acquisition.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.librarian.followup`

**Reached from 19 route(s):** `conversations.scene.work.librarian.acquisition.blocked.respond` / `urge_ask_village`; `conversations.scene.work.librarian.acquisition.blocked.respond` / `ask_why_it_matters`; `conversations.scene.work.librarian.acquisition.succeeded.respond` / `ask_to_see`; `conversations.scene.work.librarian.acquisition.succeeded.respond` / `credit_village`; `conversations.scene.work.librarian.damaged_volume.active.respond` / `ask_progress`; `conversations.scene.work.librarian.damaged_volume.active.respond` / `glad`; `conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond` / `offer_wool`; `conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond` / `defer_to_her`; `conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond` / `hold_position`; `conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond` / `concede`; `conversations.scene.work.librarian.damaged_volume.blocked.respond` / `no_advice`; `conversations.scene.work.librarian.damaged_volume.failed.respond` / `sit_with_it` …and 7 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.librarian.acquisition.blocked.agreed` — e.g. "I had not thought of it as theirs to buy. That is my whole problem in one sentence, isn't it. Right. I will ask at the well."
- `conversations.scene.work.librarian.acquisition.blocked.explained` — e.g. "Because there is one copy and I have seen it. Most of what I want does not exist any more; %2$s exists and is thirty miles away."
- `conversations.scene.work.librarian.acquisition.succeeded.deflected` — e.g. "I asked badly, in fact. I stumbled over the number twice. They gave anyway, which says more about them than my asking."
- `conversations.scene.work.librarian.acquisition.succeeded.shown` — e.g. "Hands, first. Then yes. I have been waiting three days for somebody to ask instead of nodding politely at %2$s."
- `conversations.scene.work.librarian.damaged_volume.active.answered` — e.g. "The outer sheets, mostly. The middle of %2$s is still the middle of a wet book, and there is no hurrying that part."
- `conversations.scene.work.librarian.damaged_volume.active.shared` — e.g. "So am I. I carried %2$s out of that cupboard at arm's length like something that might bite."
- `conversations.scene.work.librarian.damaged_volume.blocked.clarified.accepted` — e.g. "Then I will leave %2$s where it is until you come. Thank you — I mean that, and I am not always good at saying it."
- `conversations.scene.work.librarian.damaged_volume.blocked.clarified.deferred` — e.g. "That is either great faith or great convenience. I will take it as faith. %2$s gets the blotting cloth, then, and I will live with the crooked pages."
- `conversations.scene.work.librarian.damaged_volume.blocked.excused` — e.g. "That's fair. Hardly anyone does, and they answer anyway. I think I only wanted to hear myself say it out loud."
- `conversations.scene.work.librarian.damaged_volume.blocked.resisted.held` — e.g. "We can disagree. I would rather that than have you agree with me to be pleasant. I am still not taking %2$s apart on a stranger's opinion of ink."
- `conversations.scene.work.librarian.damaged_volume.blocked.resisted.settled` — e.g. "Thank you. And for what it is worth you were half right — I will lift the worst page out and copy it before I press the rest of %2$s."
- `conversations.scene.work.librarian.damaged_volume.failed.counted` — e.g. "The binding, which is worth nothing, and the first quire, which is worth everything and is four pages long."
- `conversations.scene.work.librarian.damaged_volume.failed.received` — e.g. "It is. Thank you for calling it that instead of telling me it was only a book. The covers are on my desk. I have not decided what to do with them."
- `conversations.scene.work.librarian.damaged_volume.succeeded.assessed` — e.g. "All but four lines, and I know what three of them said from another copy. The fourth I will have to ask somebody old about."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.librarian.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.librarian.followup   [14 chars]
    en  Anything else?
    >>  ............................................
    pt  Mais alguma coisa?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest thing you've had to mend?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.librarian.*` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.followup.ask_more` — accepted phrasings: "whats the hardest thing youve had to mend"; "what was the hardest repair youve done"; "whats the worst thing youve had to fix"
  - the message must contain one of: `hardest`, `mend`, `thing`
  - scored words: `hardest`(1.8), `mend`(1.8), `thing`(1.8), `whats`(0.8), `youve`(0.8), `had`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.followup.ask_more   [44 chars]
    en  What's the hardest thing you've had to mend?
    >>  ............................................
    pt  Qual foi a coisa mais difícil que você já teve de restaurar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.librarian.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.librarian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a book you'd save first?" | "Quiet shelves to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest thing you've had to mend?"
       spoken on: conversations.scene.work.librarian.followup, button `ask_more`
       leaves the player on: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.hard`: the villager explains. Subject `work.librarian.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.librarian.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.librarian.hard/1   [80 chars]
    en  Damp. Fire is faster but damp is patient, and patience always wins in a library.
    >>  ............................................
    pt  Umidade. Fogo é mais rápido, mas umidade é paciente, e paciência sempre vence numa biblioteca.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.hard/2   [92 chars]
    en  Being borrowed by somebody kind. They mean no harm and they always leave it face-down, %1$s.
    >>  ............................................
    pt  Ser emprestado a alguém gentil. Não fazem por mal e sempre deixam aberto de bruços, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to it."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.librarian.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.followup.leave   [28 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.librarian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to it."
       spoken on: conversations.scene.work.librarian.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.left`: the villager accepts. Subject `work.librarian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.librarian.acquisition.blocked.respond / leave; conversations.scene.work.librarian.acquisition.succeeded.respond / leave; conversations.scene.work.librarian.damaged_volume.active.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.respond / leave; conversations.scene.work.librarian.damaged_volume.failed.respond / leave; conversations.scene.work.librarian.damaged_volume.succeeded.respond / leave …and 9 more
```

> Written out in full under **`conversations.scene.work.librarian.acquisition.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.librarian.reader_need.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.librarian.reader_need.active` — e.g. "I have been teaching %2$s their letters at the end of the day. It is going slowly and I do not know whether the slowness is theirs or mine."


```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.reader_need.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.librarian.reader_need.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.librarian.reader_need.active.respond   [18 chars]
    en  About the lessons.
    >>  ............................................
    pt  Sobre as aulas.
    >>  ............................................
```


### Button `urge_patience` — "Keep going. Three weeks is early days."

*stance family `encouragement` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.librarian.reader_need.active` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.reader_need.active.urge_patience` — accepted phrasings: "keep going three weeks is early days"; "keep going three weeks is early days"; "give it more weeks it is early yet"
  - the message must contain one of: `keep`, `early`, `weeks`
  - scored words: `keep`(1.8), `early`(1.8), `weeks`(1.8), `going`(0.8), `three`(0.8), `days`(0.8), `give`(0.8), `more`(0.8), `yet`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.reader_need.active.respond.urge_patience
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.reader_need.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.reader_need.active.respond.urge_patience   [38 chars]
    en  Keep going. Three weeks is early days.
    >>  ............................................
    pt  Continue. Três semanas ainda é começo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3  _(recorded under topic `work.librarian.literacy`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.reader_need", "state": "succeeded"}
- Does: `conversations_thread` = {"op": "open", "template": "work.librarian.reader_need"}
- Then opens: `conversations.scene.work.librarian.followup`
- …where the player's next choices will be: "What's the hardest thing you've had to mend?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.reader_need.active.steadied
WHO    VILLAGER — what the player reads after pressing "Keep going. Three weeks is early days."
       spoken on: conversations.scene.work.librarian.reader_need.active.respond, button `urge_patience`
       leaves the player on: conversations.scene.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.reader_need.active.steadied`: the villager accepts. Subject `work.librarian.literacy`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.reader_need.active.steadied/1   [133 chars]
    en  It took me two years and a patient uncle. I keep forgetting that when I am sitting across from %2$s wanting it to be Tuesday already.
    >>  ............................................
    pt  Comigo levou dois anos e um tio paciente. Vivo esquecendo disso quando estou sentada em frente a %2$s querendo que já seja terça.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.reader_need.active.steadied/2   [95 chars]
    en  You are right and I needed to hear it from outside my own head. I will stop counting the weeks.
    >>  ............................................
    pt  Você tem razão e eu precisava ouvir isso de fora da minha cabeça. Vou parar de contar as semanas.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.reader_need.active.steadied/3   [104 chars]
    en  Then I will keep going, and I will stop letting %2$s see me checking the light to know when we can stop.
    >>  ............................................
    pt  Então continuo, e paro de deixar %2$s me ver olhando a luz para saber quando podemos parar.
    >>  ............................................
```


### Button `ask_method` — "How are you teaching it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.librarian.reader_need.active` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.reader_need.active.ask_method` — accepted phrasings: "how are you teaching it"; "what method are you using"; "how do you teach reading"
  - the message must contain one of: `teaching`, `method`, `teach`
  - scored words: `teaching`(1.8), `method`(1.8), `teach`(1.8), `using`(0.8), `reading`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.reader_need.active.respond.ask_method
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.reader_need.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.reader_need.active.respond.ask_method   [24 chars]
    en  How are you teaching it?
    >>  ............................................
    pt  Como você está ensinando?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.librarian.literacy`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.librarian.reader_need"}
- Then opens: `conversations.scene.work.librarian.followup`
- …where the player's next choices will be: "What's the hardest thing you've had to mend?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.reader_need.active.described
WHO    VILLAGER — what the player reads after pressing "How are you teaching it?"
       spoken on: conversations.scene.work.librarian.reader_need.active.respond, button `ask_method`
       leaves the player on: conversations.scene.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.reader_need.active.described`: the villager explains. Subject `work.librarian.literacy`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.reader_need.active.described/1   [164 chars]
    en  Badly, from memory of how I was taught, which was also badly. We start with the names of people %2$s already knows, because a name you know is a word you can guess.
    >>  ............................................
    pt  Mal, pela lembrança de como me ensinaram, que também foi mal. Começamos pelos nomes de pessoas que %2$s já conhece, porque um nome conhecido é uma palavra que se pode adivinhar.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.reader_need.active.described/2   [138 chars]
    en  Out of the ledger, of all things. Real sentences about real fields. %2$s learned 'flood' in one afternoon and has never once forgotten it.
    >>  ............................................
    pt  Pelo livro de registros, veja você. Frases de verdade sobre campos de verdade. %2$s aprendeu 'enchente' numa tarde e nunca mais esqueceu.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.reader_need.active.described/3   [126 chars]
    en  Letters first, then their own name, then anything they ask for. The asking is the part that matters and I try not to steer it.
    >>  ............................................
    pt  Letras primeiro, depois o próprio nome, depois o que pedirem. O pedir é a parte que importa e eu tento não conduzir.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the shelves."

*stance family `exit` · tone `plain` · answers the beat(s) `work.librarian.reader_need.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.reader_need.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.reader_need.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.reader_need.active.respond.leave   [30 chars]
    en  I'll leave you to the shelves.
    >>  ............................................
    pt  Vou deixar você com as prateleiras.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the shelves."
       spoken on: conversations.scene.work.librarian.reader_need.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.left`: the villager accepts. Subject `work.librarian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.librarian.acquisition.blocked.respond / leave; conversations.scene.work.librarian.acquisition.succeeded.respond / leave; conversations.scene.work.librarian.damaged_volume.active.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.respond / leave; conversations.scene.work.librarian.damaged_volume.failed.respond / leave; conversations.scene.work.librarian.damaged_volume.succeeded.respond / leave …and 9 more
```

> Written out in full under **`conversations.scene.work.librarian.acquisition.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.librarian.reader_need.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.librarian.reader_need.succeeded` — e.g. "%2$s read a whole page to me on Thursday without stopping. I had to go and reshelve something that did not need reshelving."


```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.reader_need.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.librarian.reader_need.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.librarian.reader_need.succeeded.respond   [14 chars]
    en  They can read.
    >>  ............................................
    pt  Já conseguem ler.
    >>  ............................................
```


### Button `congratulate` — "That's the best thing I've heard this week."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.librarian.reader_need.succeeded` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.reader_need.succeeded.congratulate` — accepted phrasings: "thats the best thing ive heard this week"; "that is wonderful news"; "best thing ive heard all week"
  - the message must contain one of: `best`, `wonderful`, `week`
  - scored words: `best`(1.8), `wonderful`(1.8), `week`(1.8), `thats`(0.8), `thing`(0.8), `ive`(0.8), `heard`(0.8), `news`(0.8), `all`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.reader_need.succeeded.respond.congratulate
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.reader_need.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.reader_need.succeeded.respond.congratulate   [43 chars]
    en  That's the best thing I've heard this week.
    >>  ............................................
    pt  É a melhor coisa que ouvi esta semana.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.librarian.reader_need.praise`, budget `standard`, replay policy `once`
- Does: disposition — warmth +3, respect +2  _(recorded under topic `work.librarian.literacy`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.librarian.reader_need"}
- Then opens: `conversations.scene.work.librarian.followup`
- …where the player's next choices will be: "What's the hardest thing you've had to mend?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.reader_need.succeeded.glowed
WHO    VILLAGER — what the player reads after pressing "That's the best thing I've heard this week."
       spoken on: conversations.scene.work.librarian.reader_need.succeeded.respond, button `congratulate`
       leaves the player on: conversations.scene.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.reader_need.succeeded.glowed`: the villager accepts. Subject `work.librarian.literacy`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.reader_need.succeeded.glowed/1   [123 chars]
    en  Mine too, and I have been looking for somebody to tell since Thursday without seeming as though I were boasting about %2$s.
    >>  ............................................
    pt  A minha também, e desde quinta procuro alguém para contar sem parecer que estou me gabando de %2$s.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.reader_need.succeeded.glowed/2   [85 chars]
    en  I have said it four times today. Nobody has minded yet. I am going to keep saying it.
    >>  ............................................
    pt  Já disse isso quatro vezes hoje. Ninguém reclamou ainda. Vou continuar dizendo.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.reader_need.succeeded.glowed/3   [98 chars]
    en  It is, isn't it. I have done a great deal of careful work in this room and none of it comes close.
    >>  ............................................
    pt  É mesmo, não é. Já fiz muito trabalho cuidadoso nesta sala e nada disso chega perto.
    >>  ............................................
```


### Button `ask_what_next` — "What will they read next?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.librarian.reader_need.succeeded` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.librarian.reader_need.succeeded.ask_what_next` — accepted phrasings: "what will they read next"; "what comes next for them"; "what will they read now"
  - the message must contain one of: `next`, `now`
  - scored words: `next`(1.8), `now`(1.8), `read`(0.8), `comes`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.reader_need.succeeded.respond.ask_what_next
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.reader_need.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.reader_need.succeeded.respond.ask_what_next   [25 chars]
    en  What will they read next?
    >>  ............................................
    pt  O que vão ler agora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.librarian.literacy`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.librarian.reader_need"}
- Then opens: `conversations.scene.work.librarian.followup`
- …where the player's next choices will be: "What's the hardest thing you've had to mend?" | "I'll let you get back to it."

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.reader_need.succeeded.wondered
WHO    VILLAGER — what the player reads after pressing "What will they read next?"
       spoken on: conversations.scene.work.librarian.reader_need.succeeded.respond, button `ask_what_next`
       leaves the player on: conversations.scene.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.reader_need.succeeded.wondered`: the villager explains. Subject `work.librarian.literacy`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.librarian.reader_need.succeeded.wondered/1   [147 chars]
    en  Whatever they like, and I have had to physically stop myself making a list. %2$s does not need a curriculum. %2$s needs a shelf and no supervision.
    >>  ............................................
    pt  O que quiserem, e eu tive de me segurar fisicamente para não fazer uma lista. %2$s não precisa de currículo. %2$s precisa de uma prateleira e de nenhuma supervisão.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.reader_need.succeeded.wondered/2   [141 chars]
    en  The atlas, I think, because it has pictures and no obligation. After that it stops being any of my business, which took some getting used to.
    >>  ............................................
    pt  O atlas, eu acho, porque tem figuras e nenhuma obrigação. Depois disso deixa de ser assunto meu, o que me custou um pouco aceitar.
    >>  ............................................
  dialogue.conversations.scene.work.librarian.reader_need.succeeded.wondered/3   [145 chars]
    en  They asked for something with a ship in it and I do not have one. So my next job is finding a book about ships, which is a very good job to have.
    >>  ............................................
    pt  Pediram algo com um navio, e eu não tenho. Então meu próximo trabalho é achar um livro sobre navios, que é um trabalho muito bom de se ter.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the shelves."

*stance family `exit` · tone `plain` · answers the beat(s) `work.librarian.reader_need.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.librarian.reader_need.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.librarian.reader_need.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.librarian.reader_need.succeeded.respond.leave   [30 chars]
    en  I'll leave you to the shelves.
    >>  ............................................
    pt  Vou deixar você com as prateleiras.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the shelves."
       spoken on: conversations.scene.work.librarian.reader_need.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.left`: the villager accepts. Subject `work.librarian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.librarian.acquisition.blocked.respond / leave; conversations.scene.work.librarian.acquisition.succeeded.respond / leave; conversations.scene.work.librarian.damaged_volume.active.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.respond / leave; conversations.scene.work.librarian.damaged_volume.failed.respond / leave; conversations.scene.work.librarian.damaged_volume.succeeded.respond / leave …and 9 more
```

> Written out in full under **`conversations.scene.work.librarian.acquisition.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.librarian.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.librarian.craft` — e.g. "Binding I taught myself from a book about binding, which is either clever or ridiculous."


```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.librarian.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.librarian.craft.respond   [18 chars]
    en  That's the method.
    >>  ............................................
    pt  É esse o método.
    >>  ............................................
```


### Button `ask_catalogue` — "How do you decide where a thing goes?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.librarian.craft` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.librarian.craft.ask_catalogue` — accepted phrasings: "how do you decide where a thing goes"
  - the message must contain one of: `catalogue`, `decide`, `shelve`
  - scored words: `catalogue`(1.5), `decide`(1.2), `shelve`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.craft.respond.ask_catalogue
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.craft.respond.ask_catalogue   [37 chars]
    en  How do you decide where a thing goes?
    >>  ............................................
    pt  Como você decide onde uma coisa fica?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.librarian.craft.ask_catalogue`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.librarian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a book you'd save first?" | "Quiet shelves to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.craft.ask_catalogue
WHO    VILLAGER — what the player reads after pressing "How do you decide where a thing goes?"
       spoken on: conversations.topic.work.librarian.craft.respond, button `ask_catalogue`
       leaves the player on: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.craft.ask_catalogue`: the villager explains. Subject `work.librarian.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.librarian.craft.ask_catalogue/1   [85 chars]
    en  I ask who'll want it and put it where they'd look, not where it belongs. They differ.
    >>  ............................................
    pt  Pergunto quem vai querer e ponho onde procurariam, não onde pertence. São coisas diferentes.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.craft.ask_catalogue/2   [98 chars]
    en  Badly, for the first three years. Then somebody asked for a book and I found it, and I understood.
    >>  ............................................
    pt  Mal, nos primeiros três anos. Aí alguém pediu um livro e eu achei, e entendi.
    >>  ............................................
```


### Button `admire` — "Teaching yourself binding from a book is properly clever."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.librarian.craft` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.librarian.craft.admire` — accepted phrasings: "teaching yourself binding from a book is properly clever"
  - the message must contain one of: `clever`, `yourself`
  - scored words: `clever`(1.5), `yourself`(1.2), `taught`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.craft.respond.admire   [57 chars]
    en  Teaching yourself binding from a book is properly clever.
    >>  ............................................
    pt  Aprender encadernação num livro sobre encadernação é genial mesmo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.librarian.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.librarian.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.librarian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a book you'd save first?" | "Quiet shelves to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.craft.admire
WHO    VILLAGER — what the player reads after pressing "Teaching yourself binding from a book is properly clever."
       spoken on: conversations.topic.work.librarian.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.craft.admire`: the villager accepts. Subject `work.librarian.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.librarian.craft.admire/1   [80 chars]
    en  It's the only trade where that's possible, and I have never stopped enjoying it.
    >>  ............................................
    pt  É o único ofício em que isso é possível, e eu nunca parei de achar isso divertido.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.craft.admire/2   [77 chars]
    en  The first six were unspeakable. The book did not warn me about the first six.
    >>  ............................................
    pt  Os seis primeiros foram indescritíveis. O livro não me avisou sobre os seis primeiros.
    >>  ............................................
```


### Button `ask_hardest` — "What's the hardest thing to mend?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.librarian.craft` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.librarian.craft.ask_hardest` — accepted phrasings: "what's the hardest thing to mend"
  - the message must contain one of: `hardest`, `mend`, `repair`
  - scored words: `hardest`(1.5), `mend`(1.2), `repair`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.craft.respond.ask_hardest
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.craft.respond.ask_hardest   [33 chars]
    en  What's the hardest thing to mend?
    >>  ............................................
    pt  O que é mais difícil de consertar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.librarian.craft.ask_hardest`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.librarian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a book you'd save first?" | "Quiet shelves to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.craft.ask_hardest
WHO    VILLAGER — what the player reads after pressing "What's the hardest thing to mend?"
       spoken on: conversations.topic.work.librarian.craft.respond, button `ask_hardest`
       leaves the player on: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.craft.ask_hardest`: the villager explains. Subject `work.librarian.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.librarian.craft.ask_hardest/1   [72 chars]
    en  Water. You can sew a spine and glue a corner. You cannot un-blur a name.
    >>  ............................................
    pt  Água. Dá pra costurar lombada e colar canto. Não dá pra desborrar um nome.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.craft.ask_hardest/2   [86 chars]
    en  Anything somebody loves. Their hands shake while they hand it over and mine start too.
    >>  ............................................
    pt  Qualquer coisa que alguém ama. As mãos deles tremem ao entregar e as minhas começam também.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the shelves."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.librarian.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.craft.respond.leave   [37 chars]
    en  I'll let you get back to the shelves.
    >>  ............................................
    pt  Vou deixar você voltar pras prateleiras.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the shelves."
       spoken on: conversations.topic.work.librarian.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.left`: the villager accepts. Subject `work.librarian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.librarian.acquisition.blocked.respond / leave; conversations.scene.work.librarian.acquisition.succeeded.respond / leave; conversations.scene.work.librarian.damaged_volume.active.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.respond / leave; conversations.scene.work.librarian.damaged_volume.failed.respond / leave; conversations.scene.work.librarian.damaged_volume.succeeded.respond / leave …and 9 more
```

> Written out in full under **`conversations.scene.work.librarian.acquisition.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.librarian.followup`

**Reached from 20 route(s):** `conversations.scene.work.librarian.followup` / `ask_more`; `conversations.topic.work.librarian.craft.respond` / `ask_catalogue`; `conversations.topic.work.librarian.craft.respond` / `admire`; `conversations.topic.work.librarian.craft.respond` / `ask_hardest`; `conversations.topic.work.librarian.future.respond` / `ask_room`; `conversations.topic.work.librarian.future.respond` / `encourage`; `conversations.topic.work.librarian.future.respond` / `ask_successor`; `conversations.topic.work.librarian.respond` / `ask_hard`; `conversations.topic.work.librarian.respond` / `value`; `conversations.topic.work.librarian.respond` / `challenge`; `conversations.topic.work.librarian.respond` / `challenge`; `conversations.topic.work.librarian.risk.respond` / `ask_families` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.librarian.challenge.landed` — e.g. "I do. It's the finest arrangement anyone in this village has managed."
- `conversations.work.prof.librarian.challenge.stung` — e.g. "...I also carry, mend, catalogue and argue with the mayor about the roof."
- `conversations.work.prof.librarian.craft.admire` — e.g. "It's the only trade where that's possible, and I have never stopped enjoying it."
- `conversations.work.prof.librarian.craft.ask_catalogue` — e.g. "I ask who'll want it and put it where they'd look, not where it belongs. They differ."
- `conversations.work.prof.librarian.craft.ask_hardest` — e.g. "Water. You can sew a spine and glue a corner. You cannot un-blur a name."
- `conversations.work.prof.librarian.future.ask_room` — e.g. "Chairs, light, and no requirement to be quiet. The quiet is for the books, not the readers."
- `conversations.work.prof.librarian.future.ask_successor` — e.g. "One. She's nine, she reads under the table, and I've said nothing to anybody."
- `conversations.work.prof.librarian.future.encourage` — e.g. "...He does. And I've never asked him for anything, which is a habit I could break."
- `conversations.work.prof.librarian.hard` — e.g. "Damp. Fire is faster but damp is patient, and patience always wins in a library."
- `conversations.work.prof.librarian.risk.ask_book` — e.g. "A book. That's as much as I'll say standing in a room with windows."
- `conversations.work.prof.librarian.risk.ask_families` — e.g. "The truth, one at a time, in their own kitchens. It took me a week and I'd do it again."
- `conversations.work.prof.librarian.risk.sympathise` — e.g. "...It is. And the roof is the mayor's business and the damp is mine. That's the arrangement."
- `conversations.work.prof.librarian.task.ask_ledger` — e.g. "Older than the church roof. The first name in it belongs to nobody anyone remembers."
- `conversations.work.prof.librarian.task.ask_who` — e.g. "I'll not say. They brought it back, which is the part that matters and the rarer part."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.librarian.followup   [33 chars]
    en  That's the shelves, more or less.
    >>  ............................................
    pt  São as prateleiras, mais ou menos.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.librarian.challenge.landed`, `work.librarian.challenge.stung`, `work.librarian.craft.admire`, `work.librarian.craft.ask_catalogue`, `work.librarian.craft.ask_hardest`, `work.librarian.future.ask_room`, `work.librarian.future.ask_successor`, `work.librarian.future.encourage`, `work.librarian.hard`, `work.librarian.risk.ask_book`, `work.librarian.risk.ask_families`, `work.librarian.risk.sympathise`, `work.librarian.task.ask_ledger`, `work.librarian.task.ask_who`, `work.librarian.task.offer_hands`, `work.librarian.value`, `work.librarian.village.ask_disputes`, `work.librarian.village.ask_readers`, `work.librarian.village.say_thanks` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.librarian.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `important`, `shelves`
  - scored words: `thought`(1.2), `important`(1.0), `shelves`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.librarian.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.librarian.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.librarian.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.librarian.thanks`: the villager accepts. Subject `work.librarian.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.librarian.thanks/1   [75 chars]
    en  Nobody does until they need a date proved. Then I'm suddenly indispensable.
    >>  ............................................
    pt  Ninguém pensa até precisar provar uma data. Aí eu viro indispensável de repente.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.thanks/2   [79 chars]
    en  It's a slow sort of importance, %1$s. Nobody notices until something's missing.
    >>  ............................................
    pt  É uma importância lenta, %1$s. Ninguém repara até faltar alguma coisa.
    >>  ............................................
```


### Button `ask_more` — "Is there a book you'd save first?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.librarian.challenge.landed`, `work.librarian.challenge.stung`, `work.librarian.craft.admire`, `work.librarian.craft.ask_catalogue`, `work.librarian.craft.ask_hardest`, `work.librarian.future.ask_room`, `work.librarian.future.ask_successor`, `work.librarian.future.encourage`, `work.librarian.hard`, `work.librarian.risk.ask_book`, `work.librarian.risk.ask_families`, `work.librarian.risk.sympathise`, `work.librarian.task.ask_ledger`, `work.librarian.task.ask_who`, `work.librarian.task.offer_hands`, `work.librarian.value`, `work.librarian.village.ask_disputes`, `work.librarian.village.ask_readers`, `work.librarian.village.say_thanks` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.librarian.more` — accepted phrasings: "is there a book you'd save first"
  - the message must contain one of: `save`, `book`
  - scored words: `save`(1.5), `first`(0.8), `book`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.followup.ask_more   [33 chars]
    en  Is there a book you'd save first?
    >>  ............................................
    pt  Tem um livro que você salvaria primeiro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.librarian.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.more
WHO    VILLAGER — what the player reads after pressing "Is there a book you'd save first?"
       spoken on: conversations.topic.work.librarian.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.librarian.more`: the villager discloses. Subject `work.librarian.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.librarian.more/1   [72 chars]
    en  The births ledger. Everything else can be written again. That one can't.
    >>  ............................................
    pt  O registro de nascimentos. Todo o resto pode ser reescrito. Esse não.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.more/2   [85 chars]
    en  There's a small one nobody borrows that I'd carry out under my coat. Don't ask which.
    >>  ............................................
    pt  Tem um pequeno que ninguém pega emprestado que eu levaria embaixo do casaco. Não pergunte qual.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger. Damp took four pages last winter and four families lost a date.
    >>  ............................................
    pt  O registro de nascimentos. A umidade levou quatro páginas no inverno e quatro famílias perderam uma data.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.librarian.more/2
    en  A second room. I'd like somewhere warm for the people who read slowly and know they do.
    >>  ............................................
    pt  Uma segunda sala. Queria um lugar quente pra quem lê devagar e sabe disso.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger. It has outlived four librarians and it will outlive a fifth.
    >>  ............................................
    pt  O registro de nascimentos. Sobreviveu a quatro bibliotecários e vai sobreviver a um quinto.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.librarian.more/2
    en  A second room. I've wanted it eleven years and I've never once asked the mason. That's on me.
    >>  ............................................
    pt  Uma segunda sala. Quero há onze anos e nunca pedi ao pedreiro. A culpa é minha.
    >>  ............................................
  confident.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger. Everything else can be written again. That one cannot.
    >>  ............................................
    pt  O registro de nascimentos. Todo o resto pode ser reescrito. Esse não.
    >>  ............................................
  confident.dialogue.conversations.work.prof.librarian.more/2
    en  A second room, for people to sit in with the books. It is not the same thing as shelving.
    >>  ............................................
    pt  Uma segunda sala, pra gente sentar com os livros. Não é a mesma coisa que prateleira.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger. Everything else can be written again. That one cannot.
    >>  ............................................
    pt  O registro de nascimentos. Todo o resto pode ser reescrito. Esse não.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.librarian.more/2
    en  A second room, for people to sit in with the books. It is not the same thing as shelving.
    >>  ............................................
    pt  Uma segunda sala, pra gente sentar com os livros. Não é a mesma coisa que prateleira.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger. Every family here has a line in it, and most of them have never seen it.
    >>  ............................................
    pt  O registro de nascimentos. Toda família daqui tem uma linha nele, e quase nenhuma viu.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.librarian.more/2
    en  A second room, warm, where nobody watches you read badly. That's the whole design.
    >>  ............................................
    pt  Uma segunda sala, quente, onde ninguém te olha ler mal. É todo o projeto.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger. Every family here has a line in it, and most of them have never seen it.
    >>  ............................................
    pt  O registro de nascimentos. Toda família daqui tem uma linha nele, e quase nenhuma viu.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.librarian.more/2
    en  A second room, warm, where nobody watches you read badly. That's the whole design.
    >>  ............................................
    pt  Uma segunda sala, quente, onde ninguém te olha ler mal. É todo o projeto.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger. Every family here has a line in it, and most of them have never seen it.
    >>  ............................................
    pt  O registro de nascimentos. Toda família daqui tem uma linha nele, e quase nenhuma viu.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.librarian.more/2
    en  A second room, warm, where nobody watches you read badly. That's the whole design.
    >>  ............................................
    pt  Uma segunda sala, quente, onde ninguém te olha ler mal. É todo o projeto.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger. Damp took four pages last winter and four families lost a date.
    >>  ............................................
    pt  O registro de nascimentos. A umidade levou quatro páginas no inverno e quatro famílias perderam uma data.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.librarian.more/2
    en  A second room. I'd like somewhere warm for the people who read slowly and know they do.
    >>  ............................................
    pt  Uma segunda sala. Queria um lugar quente pra quem lê devagar e sabe disso.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger. Everything else can be written again. That one cannot.
    >>  ............................................
    pt  O registro de nascimentos. Todo o resto pode ser reescrito. Esse não.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.librarian.more/2
    en  A second room, for people to sit in with the books. It is not the same thing as shelving.
    >>  ............................................
    pt  Uma segunda sala, pra gente sentar com os livros. Não é a mesma coisa que prateleira.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger. Everything else can be written again. That one cannot.
    >>  ............................................
    pt  O registro de nascimentos. Todo o resto pode ser reescrito. Esse não.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.librarian.more/2
    en  A second room, for people to sit in with the books. It is not the same thing as shelving.
    >>  ............................................
    pt  Uma segunda sala, pra gente sentar com os livros. Não é a mesma coisa que prateleira.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger. Older than the church roof, and the first name in it belongs to nobody anyone recalls.
    >>  ............................................
    pt  O registro de nascimentos. Mais velho que o telhado da igreja, e o primeiro nome não é de ninguém que se lembre.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.librarian.more/2
    en  A second room. The quiet is for the books, not for the readers. People forget that.
    >>  ............................................
    pt  Uma segunda sala. O silêncio é pros livros, não pros leitores. As pessoas esquecem.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger. It has outlived four librarians and it will outlive a fifth.
    >>  ............................................
    pt  O registro de nascimentos. Sobreviveu a quatro bibliotecários e vai sobreviver a um quinto.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.librarian.more/2
    en  A second room. I've wanted it eleven years and I've never once asked the mason. That's on me.
    >>  ............................................
    pt  Uma segunda sala. Quero há onze anos e nunca pedi ao pedreiro. A culpa é minha.
    >>  ............................................
  odd.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger. Older than the church roof, and the first name in it belongs to nobody anyone recalls.
    >>  ............................................
    pt  O registro de nascimentos. Mais velho que o telhado da igreja, e o primeiro nome não é de ninguém que se lembre.
    >>  ............................................
  odd.dialogue.conversations.work.prof.librarian.more/2
    en  A second room. The quiet is for the books, not for the readers. People forget that.
    >>  ............................................
    pt  Uma segunda sala. O silêncio é pros livros, não pros leitores. As pessoas esquecem.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger. It has outlived four librarians and it will outlive a fifth.
    >>  ............................................
    pt  O registro de nascimentos. Sobreviveu a quatro bibliotecários e vai sobreviver a um quinto.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.librarian.more/2
    en  A second room. I've wanted it eleven years and I've never once asked the mason. That's on me.
    >>  ............................................
    pt  Uma segunda sala. Quero há onze anos e nunca pedi ao pedreiro. A culpa é minha.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger! Everything else can be written again. That one is ninety years and no copy.
    >>  ............................................
    pt  O registro de nascimentos! Todo o resto se reescreve. Esse tem noventa anos e nenhuma cópia.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.librarian.more/2
    en  A second room. Chairs, light, and absolutely no requirement to be quiet. Radical, I know.
    >>  ............................................
    pt  Uma segunda sala. Cadeiras, luz, e nenhuma obrigação de silêncio. Radical, eu sei.
    >>  ............................................
  playful.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger! Everything else can be written again. That one is ninety years and no copy.
    >>  ............................................
    pt  O registro de nascimentos! Todo o resto se reescreve. Esse tem noventa anos e nenhuma cópia.
    >>  ............................................
  playful.dialogue.conversations.work.prof.librarian.more/2
    en  A second room. Chairs, light, and absolutely no requirement to be quiet. Radical, I know.
    >>  ............................................
    pt  Uma segunda sala. Cadeiras, luz, e nenhuma obrigação de silêncio. Radical, eu sei.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger. It has outlived four librarians and it will outlive a fifth.
    >>  ............................................
    pt  O registro de nascimentos. Sobreviveu a quatro bibliotecários e vai sobreviver a um quinto.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.librarian.more/2
    en  A second room. I've wanted it eleven years and I've never once asked the mason. That's on me.
    >>  ............................................
    pt  Uma segunda sala. Quero há onze anos e nunca pedi ao pedreiro. A culpa é minha.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger. Damp took four pages last winter and four families lost a date.
    >>  ............................................
    pt  O registro de nascimentos. A umidade levou quatro páginas no inverno e quatro famílias perderam uma data.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.librarian.more/2
    en  A second room. I'd like somewhere warm for the people who read slowly and know they do.
    >>  ............................................
    pt  Uma segunda sala. Queria um lugar quente pra quem lê devagar e sabe disso.
    >>  ............................................
  shy.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger. Older than the church roof, and the first name in it belongs to nobody anyone recalls.
    >>  ............................................
    pt  O registro de nascimentos. Mais velho que o telhado da igreja, e o primeiro nome não é de ninguém que se lembre.
    >>  ............................................
  shy.dialogue.conversations.work.prof.librarian.more/2
    en  A second room. The quiet is for the books, not for the readers. People forget that.
    >>  ............................................
    pt  Uma segunda sala. O silêncio é pros livros, não pros leitores. As pessoas esquecem.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger! Everything else can be written again. That one is ninety years and no copy.
    >>  ............................................
    pt  O registro de nascimentos! Todo o resto se reescreve. Esse tem noventa anos e nenhuma cópia.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.librarian.more/2
    en  A second room. Chairs, light, and absolutely no requirement to be quiet. Radical, I know.
    >>  ............................................
    pt  Uma segunda sala. Cadeiras, luz, e nenhuma obrigação de silêncio. Radical, eu sei.
    >>  ............................................
  witty.dialogue.conversations.work.prof.librarian.more/1
    en  The births ledger! Everything else can be written again. That one is ninety years and no copy.
    >>  ............................................
    pt  O registro de nascimentos! Todo o resto se reescreve. Esse tem noventa anos e nenhuma cópia.
    >>  ............................................
  witty.dialogue.conversations.work.prof.librarian.more/2
    en  A second room. Chairs, light, and absolutely no requirement to be quiet. Radical, I know.
    >>  ............................................
    pt  Uma segunda sala. Cadeiras, luz, e nenhuma obrigação de silêncio. Radical, eu sei.
    >>  ............................................
```

</details>


### Button `leave` — "Quiet shelves to you."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.librarian.challenge.landed`, `work.librarian.challenge.stung`, `work.librarian.craft.admire`, `work.librarian.craft.ask_catalogue`, `work.librarian.craft.ask_hardest`, `work.librarian.future.ask_room`, `work.librarian.future.ask_successor`, `work.librarian.future.encourage`, `work.librarian.hard`, `work.librarian.risk.ask_book`, `work.librarian.risk.ask_families`, `work.librarian.risk.sympathise`, `work.librarian.task.ask_ledger`, `work.librarian.task.ask_who`, `work.librarian.task.offer_hands`, `work.librarian.value`, `work.librarian.village.ask_disputes`, `work.librarian.village.ask_readers`, `work.librarian.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.followup.leave   [21 chars]
    en  Quiet shelves to you.
    >>  ............................................
    pt  Prateleiras silenciosas pra você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.leave
WHO    VILLAGER — what the player reads after pressing "Quiet shelves to you."
       spoken on: conversations.topic.work.librarian.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.left`: the villager accepts. Subject `work.librarian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.librarian.acquisition.blocked.respond / leave; conversations.scene.work.librarian.acquisition.succeeded.respond / leave; conversations.scene.work.librarian.damaged_volume.active.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.respond / leave; conversations.scene.work.librarian.damaged_volume.failed.respond / leave; conversations.scene.work.librarian.damaged_volume.succeeded.respond / leave …and 9 more
```

> Written out in full under **`conversations.scene.work.librarian.acquisition.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.librarian.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.librarian.future` — e.g. "I want a second room. Not for books — for people to sit in with them. It isn't the same thing."


```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.librarian.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.librarian.future.respond   [22 chars]
    en  That's what I'd build.
    >>  ............................................
    pt  É o que eu construiria.
    >>  ............................................
```


### Button `ask_room` — "What would the second room be?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.librarian.future` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.librarian.future.ask_room` — accepted phrasings: "what would the second room be"
  - the message must contain one of: `room`, `second`, `build`
  - scored words: `room`(1.5), `second`(1.2), `build`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.future.respond.ask_room
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.future.respond.ask_room   [30 chars]
    en  What would the second room be?
    >>  ............................................
    pt  Como seria a segunda sala?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.librarian.future.ask_room`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.librarian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a book you'd save first?" | "Quiet shelves to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.future.ask_room
WHO    VILLAGER — what the player reads after pressing "What would the second room be?"
       spoken on: conversations.topic.work.librarian.future.respond, button `ask_room`
       leaves the player on: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.future.ask_room`: the villager explains. Subject `work.librarian.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.librarian.future.ask_room/1   [91 chars]
    en  Chairs, light, and no requirement to be quiet. The quiet is for the books, not the readers.
    >>  ............................................
    pt  Cadeiras, luz, e nenhuma obrigação de silêncio. O silêncio é pros livros, não pros leitores.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.future.ask_room/2   [72 chars]
    en  Warm. That's the whole design. Warm, and nobody watching you read badly.
    >>  ............................................
    pt  Quente. É todo o projeto. Quente, e ninguém te olhando ler mal.
    >>  ............................................
```


### Button `encourage` — "Ask the mason. He builds things that outlast people."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.librarian.future` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.librarian.future.encourage` — accepted phrasings: "ask the mason. he builds things that outlast people"
  - the message must contain one of: `mason`, `build`
  - scored words: `mason`(1.5), `ask`(0.8), `build`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.future.respond.encourage   [52 chars]
    en  Ask the mason. He builds things that outlast people.
    >>  ............................................
    pt  Peça ao pedreiro. Ele constrói coisas que sobrevivem às pessoas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.librarian.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.librarian.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.librarian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a book you'd save first?" | "Quiet shelves to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.future.encourage
WHO    VILLAGER — what the player reads after pressing "Ask the mason. He builds things that outlast people."
       spoken on: conversations.topic.work.librarian.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.future.encourage`: the villager accepts. Subject `work.librarian.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.librarian.future.encourage/1   [82 chars]
    en  ...He does. And I've never asked him for anything, which is a habit I could break.
    >>  ............................................
    pt  ...Ele constrói. E eu nunca pedi nada a ele, um hábito que eu poderia quebrar.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.future.encourage/2   [58 chars]
    en  Now there's a thought I'll be turning over all week, %1$s.
    >>  ............................................
    pt  Aí está um pensamento que eu vou remoer a semana toda, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does. I've never asked him for anything, and I'm not sure I know how.
    >>  ............................................
    pt  ...Ele tem. Nunca pedi nada a ele, e não sei se sei como.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  Now there's a thought I'll be turning over all week, and I'll be nervous the whole time.
    >>  ............................................
    pt  Aí está um pensamento que vou remoer a semana toda, e nervosa o tempo todo.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does. Thirty years of not asking anybody for anything is a long habit.
    >>  ............................................
    pt  ...Ele tem. Trinta anos sem pedir nada a ninguém é um hábito longo.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  Now there's a thought. At my age they arrive rarely and stay longer.
    >>  ............................................
    pt  Aí está um pensamento. Na minha idade eles chegam raro e ficam mais tempo.
    >>  ............................................
  confident.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does. And I've never asked him for anything, which is a habit I could break.
    >>  ............................................
    pt  ...Ele tem. E eu nunca pedi nada a ele, o que é um hábito que eu podia quebrar.
    >>  ............................................
  confident.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  Now there's a thought I'll be turning over all week.
    >>  ............................................
    pt  Aí está um pensamento que vou remoer a semana toda.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does. And I've never asked him for anything, which is a habit I could break.
    >>  ............................................
    pt  ...Ele tem. E eu nunca pedi nada a ele, o que é um hábito que eu podia quebrar.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  Now there's a thought I'll be turning over all week.
    >>  ............................................
    pt  Aí está um pensamento que vou remoer a semana toda.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does, %1$s. And I've never asked him for anything, which I could change.
    >>  ............................................
    pt  ...Ele tem, %1$s. E eu nunca pedi nada a ele, o que eu podia mudar.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  Now there's a thought I'll be turning over all week. Thank you for it.
    >>  ............................................
    pt  Aí está um pensamento que vou remoer a semana toda. Obrigada por ele.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does, %1$s. And I've never asked him for anything, which I could change.
    >>  ............................................
    pt  ...Ele tem, %1$s. E eu nunca pedi nada a ele, o que eu podia mudar.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  Now there's a thought I'll be turning over all week. Thank you for it.
    >>  ............................................
    pt  Aí está um pensamento que vou remoer a semana toda. Obrigada por ele.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does, %1$s. And I've never asked him for anything, which I could change.
    >>  ............................................
    pt  ...Ele tem, %1$s. E eu nunca pedi nada a ele, o que eu podia mudar.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  Now there's a thought I'll be turning over all week. Thank you for it.
    >>  ............................................
    pt  Aí está um pensamento que vou remoer a semana toda. Obrigada por ele.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does. I've never asked him for anything, and I'm not sure I know how.
    >>  ............................................
    pt  ...Ele tem. Nunca pedi nada a ele, e não sei se sei como.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  Now there's a thought I'll be turning over all week, and I'll be nervous the whole time.
    >>  ............................................
    pt  Aí está um pensamento que vou remoer a semana toda, e nervosa o tempo todo.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does. And I've never asked him for anything, which is a habit I could break.
    >>  ............................................
    pt  ...Ele tem. E eu nunca pedi nada a ele, o que é um hábito que eu podia quebrar.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  Now there's a thought I'll be turning over all week.
    >>  ............................................
    pt  Aí está um pensamento que vou remoer a semana toda.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does. And I've never asked him for anything, which is a habit I could break.
    >>  ............................................
    pt  ...Ele tem. E eu nunca pedi nada a ele, o que é um hábito que eu podia quebrar.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  Now there's a thought I'll be turning over all week.
    >>  ............................................
    pt  Aí está um pensamento que vou remoer a semana toda.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does. I've never asked him for anything.
    >>  ............................................
    pt  ...Ele tem. Nunca pedi nada a ele.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  A thought to turn over.
    >>  ............................................
    pt  Um pensamento pra remoer.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does. Thirty years of not asking anybody for anything is a long habit.
    >>  ............................................
    pt  ...Ele tem. Trinta anos sem pedir nada a ninguém é um hábito longo.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  Now there's a thought. At my age they arrive rarely and stay longer.
    >>  ............................................
    pt  Aí está um pensamento. Na minha idade eles chegam raro e ficam mais tempo.
    >>  ............................................
  odd.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does. I've never asked him for anything.
    >>  ............................................
    pt  ...Ele tem. Nunca pedi nada a ele.
    >>  ............................................
  odd.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  A thought to turn over.
    >>  ............................................
    pt  Um pensamento pra remoer.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does. Thirty years of not asking anybody for anything is a long habit.
    >>  ............................................
    pt  ...Ele tem. Trinta anos sem pedir nada a ninguém é um hábito longo.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  Now there's a thought. At my age they arrive rarely and stay longer.
    >>  ............................................
    pt  Aí está um pensamento. Na minha idade eles chegam raro e ficam mais tempo.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does! And I've never asked him for a thing, which is a habit I could break today.
    >>  ............................................
    pt  ...Ele tem! E eu nunca pedi nada a ele, hábito que eu podia quebrar hoje.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  Now there's a thought I'll be turning over all week, and probably at inconvenient hours.
    >>  ............................................
    pt  Aí está um pensamento que vou remoer a semana toda, provavelmente em horas ruins.
    >>  ............................................
  playful.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does! And I've never asked him for a thing, which is a habit I could break today.
    >>  ............................................
    pt  ...Ele tem! E eu nunca pedi nada a ele, hábito que eu podia quebrar hoje.
    >>  ............................................
  playful.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  Now there's a thought I'll be turning over all week, and probably at inconvenient hours.
    >>  ............................................
    pt  Aí está um pensamento que vou remoer a semana toda, provavelmente em horas ruins.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does. Thirty years of not asking anybody for anything is a long habit.
    >>  ............................................
    pt  ...Ele tem. Trinta anos sem pedir nada a ninguém é um hábito longo.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  Now there's a thought. At my age they arrive rarely and stay longer.
    >>  ............................................
    pt  Aí está um pensamento. Na minha idade eles chegam raro e ficam mais tempo.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does. I've never asked him for anything, and I'm not sure I know how.
    >>  ............................................
    pt  ...Ele tem. Nunca pedi nada a ele, e não sei se sei como.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  Now there's a thought I'll be turning over all week, and I'll be nervous the whole time.
    >>  ............................................
    pt  Aí está um pensamento que vou remoer a semana toda, e nervosa o tempo todo.
    >>  ............................................
  shy.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does. I've never asked him for anything.
    >>  ............................................
    pt  ...Ele tem. Nunca pedi nada a ele.
    >>  ............................................
  shy.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  A thought to turn over.
    >>  ............................................
    pt  Um pensamento pra remoer.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does! And I've never asked him for a thing, which is a habit I could break today.
    >>  ............................................
    pt  ...Ele tem! E eu nunca pedi nada a ele, hábito que eu podia quebrar hoje.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  Now there's a thought I'll be turning over all week, and probably at inconvenient hours.
    >>  ............................................
    pt  Aí está um pensamento que vou remoer a semana toda, provavelmente em horas ruins.
    >>  ............................................
  witty.dialogue.conversations.work.prof.librarian.future.encourage/1
    en  ...He does! And I've never asked him for a thing, which is a habit I could break today.
    >>  ............................................
    pt  ...Ele tem! E eu nunca pedi nada a ele, hábito que eu podia quebrar hoje.
    >>  ............................................
  witty.dialogue.conversations.work.prof.librarian.future.encourage/2
    en  Now there's a thought I'll be turning over all week, and probably at inconvenient hours.
    >>  ............................................
    pt  Aí está um pensamento que vou remoer a semana toda, provavelmente em horas ruins.
    >>  ............................................
```

</details>


### Button `ask_successor` — "Is there anyone who wants it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.librarian.future` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.librarian.future.ask_successor` — accepted phrasings: "is there anyone who wants it"
  - the message must contain one of: `wants`, `successor`
  - scored words: `wants`(1.5), `successor`(1.5), `anyone`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.future.respond.ask_successor
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.future.respond.ask_successor   [29 chars]
    en  Is there anyone who wants it?
    >>  ............................................
    pt  Tem alguém que queira?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.librarian.future.ask_successor`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.librarian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a book you'd save first?" | "Quiet shelves to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.future.ask_successor
WHO    VILLAGER — what the player reads after pressing "Is there anyone who wants it?"
       spoken on: conversations.topic.work.librarian.future.respond, button `ask_successor`
       leaves the player on: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.future.ask_successor`: the villager explains. Subject `work.librarian.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.librarian.future.ask_successor/1   [77 chars]
    en  One. She's nine, she reads under the table, and I've said nothing to anybody.
    >>  ............................................
    pt  Uma. Ela tem nove anos, lê embaixo da mesa, e eu não disse nada a ninguém.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.future.ask_successor/2   [75 chars]
    en  Not yet. I've stopped looking for able and started watching for interested.
    >>  ............................................
    pt  Ainda não. Parei de procurar capaz e comecei a observar quem se interessa.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the shelves."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.librarian.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.future.respond.leave   [37 chars]
    en  I'll let you get back to the shelves.
    >>  ............................................
    pt  Vou deixar você voltar pras prateleiras.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the shelves."
       spoken on: conversations.topic.work.librarian.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.left`: the villager accepts. Subject `work.librarian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.librarian.acquisition.blocked.respond / leave; conversations.scene.work.librarian.acquisition.succeeded.respond / leave; conversations.scene.work.librarian.damaged_volume.active.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.respond / leave; conversations.scene.work.librarian.damaged_volume.failed.respond / leave; conversations.scene.work.librarian.damaged_volume.succeeded.respond / leave …and 9 more
```

> Written out in full under **`conversations.scene.work.librarian.acquisition.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.librarian.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.librarian` — e.g. "I mind the books. They mind me back. Quieter company than the tavern and twice as opinionated."


```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.librarian.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.librarian.respond   [34 chars]
    en  That's the room and its occupants.
    >>  ............................................
    pt  É a sala e seus ocupantes.
    >>  ............................................
```


### Button `ask_hard` — "What's the worst thing that happens to a book?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.librarian.identity` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.librarian.hard` — accepted phrasings: "what's the worst thing that happens to a book"
  - the message must contain one of: `worst`, `damp`, `ruins`
  - scored words: `worst`(1.0), `damp`(1.5), `ruins`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.respond.ask_hard   [46 chars]
    en  What's the worst thing that happens to a book?
    >>  ............................................
    pt  Qual a pior coisa que acontece com um livro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.librarian.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.librarian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a book you'd save first?" | "Quiet shelves to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.hard
WHO    VILLAGER — what the player reads after pressing "What's the worst thing that happens to a book?"
       spoken on: conversations.topic.work.librarian.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.hard`: the villager explains. Subject `work.librarian.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.librarian.followup / ask_more
```

> Written out in full under **`conversations.scene.work.librarian.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "We would forget ourselves without you."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.librarian.identity` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.librarian.value` — accepted phrasings: "we would forget ourselves without you"
  - the message must contain one of: `forget`, `memory`, `ourselves`
  - scored words: `forget`(1.5), `memory`(1.5), `ourselves`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.respond.value   [38 chars]
    en  We would forget ourselves without you.
    >>  ............................................
    pt  A gente se esqueceria de si mesmo sem você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.librarian.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.librarian.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.librarian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a book you'd save first?" | "Quiet shelves to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.value
WHO    VILLAGER — what the player reads after pressing "We would forget ourselves without you."
       spoken on: conversations.topic.work.librarian.respond, button `value`
       leaves the player on: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.value`: the villager accepts. Subject `work.librarian.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.librarian.value/1   [74 chars]
    en  It would. Half of what I keep is births and deaths nobody else wrote down.
    >>  ............................................
    pt  Esqueceria. Metade do que eu guardo são nascimentos e mortes que mais ninguém anotou.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.value/2   [85 chars]
    en  That's the whole job, put plainly. I'm the village's memory and I have damp problems.
    >>  ............................................
    pt  É o trabalho inteiro, dito sem rodeio. Sou a memória do vilarejo e tenho problema de umidade.
    >>  ............................................
```


### Button `challenge` — "You sit indoors and read."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.librarian.identity` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.librarian.challenge` — accepted phrasings: "you sit indoors and read"
  - the message must contain one of: `indoors`, `read`
  - scored words: `indoors`(1.5), `read`(1.2), `easy`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.respond.challenge   [25 chars]
    en  You sit indoors and read.
    >>  ............................................
    pt  Você fica dentro lendo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.librarian.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.librarian.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.librarian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a book you'd save first?" | "Quiet shelves to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.challenge.landed
WHO    VILLAGER — what the player reads after pressing "You sit indoors and read."
       spoken on: conversations.topic.work.librarian.respond, button `challenge`
       leaves the player on: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.challenge.landed`: the villager resists. Subject `work.librarian.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.librarian.challenge.landed/1   [69 chars]
    en  I do. It's the finest arrangement anyone in this village has managed.
    >>  ............................................
    pt  Fico. É o melhor arranjo que alguém neste vilarejo conseguiu.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.challenge.landed/2   [73 chars]
    en  Sit indoors and read. Yes. I recommend it, %1$s, if you can get the post.
    >>  ............................................
    pt  Ficar dentro lendo. Sim. Eu recomendo, %1$s, se você conseguir o cargo.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.librarian.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.librarian.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.librarian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a book you'd save first?" | "Quiet shelves to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.challenge.stung
WHO    VILLAGER — what the player reads after pressing "You sit indoors and read."
       spoken on: conversations.topic.work.librarian.respond, button `challenge`
       leaves the player on: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.challenge.stung`: the villager resists. Subject `work.librarian.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.librarian.challenge.stung/1   [73 chars]
    en  ...I also carry, mend, catalogue and argue with the mayor about the roof.
    >>  ............................................
    pt  ...Eu também carrego, conserto, cataloguei e discuto com o prefeito sobre o telhado.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.challenge.stung/2   [63 chars]
    en  Read. Right. Come back when you need something looked up, %1$s.
    >>  ............................................
    pt  Ler. Certo. Volte quando precisar procurar alguma coisa, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the shelves."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.librarian.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.respond.leave   [37 chars]
    en  I'll let you get back to the shelves.
    >>  ............................................
    pt  Vou deixar você voltar pras prateleiras.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the shelves."
       spoken on: conversations.topic.work.librarian.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.left`: the villager accepts. Subject `work.librarian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.librarian.acquisition.blocked.respond / leave; conversations.scene.work.librarian.acquisition.succeeded.respond / leave; conversations.scene.work.librarian.damaged_volume.active.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.respond / leave; conversations.scene.work.librarian.damaged_volume.failed.respond / leave; conversations.scene.work.librarian.damaged_volume.succeeded.respond / leave …and 9 more
```

> Written out in full under **`conversations.scene.work.librarian.acquisition.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.librarian.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.librarian.risk` — e.g. "Damp took four pages of the births ledger last winter. Four families lost a date."


```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.librarian.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.librarian.risk.respond   [28 chars]
    en  That's what's at stake here.
    >>  ............................................
    pt  É isso que está em jogo aqui.
    >>  ............................................
```


### Button `ask_families` — "What did you tell the families?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.librarian.risk` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.librarian.risk.ask_families` — accepted phrasings: "what did you tell the families"
  - the message must contain one of: `families`, `told`
  - scored words: `families`(1.5), `told`(1.2), `said`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.risk.respond.ask_families
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.risk.respond.ask_families   [31 chars]
    en  What did you tell the families?
    >>  ............................................
    pt  O que você disse às famílias?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.librarian.risk.ask_families`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.librarian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a book you'd save first?" | "Quiet shelves to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.risk.ask_families
WHO    VILLAGER — what the player reads after pressing "What did you tell the families?"
       spoken on: conversations.topic.work.librarian.risk.respond, button `ask_families`
       leaves the player on: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.risk.ask_families`: the villager explains. Subject `work.librarian.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.librarian.risk.ask_families/1   [87 chars]
    en  The truth, one at a time, in their own kitchens. It took me a week and I'd do it again.
    >>  ............................................
    pt  A verdade, uma por vez, na cozinha de cada uma. Levei uma semana e eu faria de novo.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.risk.ask_families/2   [80 chars]
    en  That I had the names and not the dates. Two of them cried. One thanked me, %1$s.
    >>  ............................................
    pt  Que eu tinha os nomes e não as datas. Duas choraram. Uma me agradeceu, %1$s.
    >>  ............................................
```


### Button `sympathise` — "That's a heavy thing to be the keeper of."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.librarian.risk` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.librarian.risk.sympathise` — accepted phrasings: "that's a heavy thing to be the keeper of"
  - the message must contain one of: `keeper`, `heavy`, `burden`
  - scored words: `keeper`(1.5), `heavy`(1.2), `burden`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.risk.respond.sympathise   [41 chars]
    en  That's a heavy thing to be the keeper of.
    >>  ............................................
    pt  É uma coisa pesada de se guardar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.librarian.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.librarian.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.librarian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a book you'd save first?" | "Quiet shelves to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "That's a heavy thing to be the keeper of."
       spoken on: conversations.topic.work.librarian.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.risk.sympathise`: the villager accepts. Subject `work.librarian.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.librarian.risk.sympathise/1   [92 chars]
    en  ...It is. And the roof is the mayor's business and the damp is mine. That's the arrangement.
    >>  ............................................
    pt  ...É. E o telhado é assunto do prefeito e a umidade é meu. É esse o acordo.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.risk.sympathise/2   [73 chars]
    en  Somebody has to be. I'd rather it were somebody who minds, and I do mind.
    >>  ............................................
    pt  Alguém tem que ser. Prefiro que seja alguém que se importe, e eu me importo.
    >>  ............................................
```


### Button `ask_book` — "What's on the high shelf?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.librarian.risk` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.librarian.risk.ask_book` — accepted phrasings: "what's on the high shelf"
  - the message must contain one of: `shelf`, `book`, `high`
  - scored words: `shelf`(1.5), `book`(1.0), `high`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.risk.respond.ask_book
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.risk.respond.ask_book   [25 chars]
    en  What's on the high shelf?
    >>  ............................................
    pt  O que tem na prateleira alta?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.librarian.risk.ask_book`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.librarian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a book you'd save first?" | "Quiet shelves to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.risk.ask_book
WHO    VILLAGER — what the player reads after pressing "What's on the high shelf?"
       spoken on: conversations.topic.work.librarian.risk.respond, button `ask_book`
       leaves the player on: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.risk.ask_book`: the villager explains. Subject `work.librarian.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.librarian.risk.ask_book/1   [67 chars]
    en  A book. That's as much as I'll say standing in a room with windows.
    >>  ............................................
    pt  Um livro. É tudo que eu digo numa sala com janelas.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.risk.ask_book/2   [84 chars]
    en  Something that shouldn't have been written down. I've told no one where it is, %1$s.
    >>  ............................................
    pt  Algo que não devia ter sido escrito. Não contei a ninguém onde está, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the shelves."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.librarian.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.risk.respond.leave   [37 chars]
    en  I'll let you get back to the shelves.
    >>  ............................................
    pt  Vou deixar você voltar pras prateleiras.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the shelves."
       spoken on: conversations.topic.work.librarian.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.left`: the villager accepts. Subject `work.librarian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.librarian.acquisition.blocked.respond / leave; conversations.scene.work.librarian.acquisition.succeeded.respond / leave; conversations.scene.work.librarian.damaged_volume.active.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.respond / leave; conversations.scene.work.librarian.damaged_volume.failed.respond / leave; conversations.scene.work.librarian.damaged_volume.succeeded.respond / leave …and 9 more
```

> Written out in full under **`conversations.scene.work.librarian.acquisition.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.librarian.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.librarian.task` — e.g. "Re-sewing a spine that gave out in somebody's bag. I've said nothing and I will say nothing."


```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.librarian.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.librarian.task.respond   [22 chars]
    en  That's the desk today.
    >>  ............................................
    pt  É a escrivaninha hoje.
    >>  ............................................
```


### Button `ask_who` — "Whose bag?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.librarian.task` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.librarian.task.ask_who` — accepted phrasings: "whose bag"
  - the message must contain one of: `whose`, `bag`
  - scored words: `whose`(1.5), `bag`(1.2), `who`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.task.respond.ask_who
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.task.respond.ask_who   [10 chars]
    en  Whose bag?
    >>  ............................................
    pt  Bolsa de quem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.librarian.task.ask_who`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.librarian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a book you'd save first?" | "Quiet shelves to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.task.ask_who
WHO    VILLAGER — what the player reads after pressing "Whose bag?"
       spoken on: conversations.topic.work.librarian.task.respond, button `ask_who`
       leaves the player on: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.task.ask_who`: the villager explains. Subject `work.librarian.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.librarian.task.ask_who/1   [86 chars]
    en  I'll not say. They brought it back, which is the part that matters and the rarer part.
    >>  ............................................
    pt  Não vou dizer. Trouxeram de volta, que é a parte que importa e a mais rara.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.task.ask_who/2   [72 chars]
    en  The mason's boy. He looked so stricken I nearly apologised to him, %1$s.
    >>  ............................................
    pt  O filho do pedreiro. Ele ficou tão arrasado que eu quase pedi desculpa a ele, %1$s.
    >>  ............................................
```


### Button `offer_hands` — "I can turn pages while you write."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.librarian.task` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.librarian.task.offer_hands` — accepted phrasings: "i can turn pages while you write"
  - the message must contain one of: `pages`, `turn`
  - scored words: `pages`(1.5), `turn`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.task.respond.offer_hands   [33 chars]
    en  I can turn pages while you write.
    >>  ............................................
    pt  Posso virar páginas enquanto você escreve.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.librarian.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.librarian.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.librarian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a book you'd save first?" | "Quiet shelves to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I can turn pages while you write."
       spoken on: conversations.topic.work.librarian.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.task.offer_hands`: the villager accepts. Subject `work.librarian.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.librarian.task.offer_hands/1   [92 chars]
    en  ...You can. Clean hands, corner only, and don't read ahead — it's births, not entertainment.
    >>  ............................................
    pt  ...Pode. Mãos limpas, só no canto, e não leia à frente — são nascimentos, não diversão.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.task.offer_hands/2   [66 chars]
    en  That would halve it. Sit there and don't breathe on the ink, %1$s.
    >>  ............................................
    pt  Isso cortaria pela metade. Sente aí e não sopre na tinta, %1$s.
    >>  ............................................
```


### Button `ask_ledger` — "How old is the ledger?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.librarian.task` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.librarian.task.ask_ledger` — accepted phrasings: "how old is the ledger"
  - the message must contain one of: `ledger`, `old`, `age`
  - scored words: `ledger`(1.5), `old`(1.0), `age`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.task.respond.ask_ledger
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.task.respond.ask_ledger   [22 chars]
    en  How old is the ledger?
    >>  ............................................
    pt  Quantos anos tem o registro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.librarian.task.ask_ledger`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.librarian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a book you'd save first?" | "Quiet shelves to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.task.ask_ledger
WHO    VILLAGER — what the player reads after pressing "How old is the ledger?"
       spoken on: conversations.topic.work.librarian.task.respond, button `ask_ledger`
       leaves the player on: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.task.ask_ledger`: the villager explains. Subject `work.librarian.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.librarian.task.ask_ledger/1   [84 chars]
    en  Older than the church roof. The first name in it belongs to nobody anyone remembers.
    >>  ............................................
    pt  Mais velho que o telhado da igreja. O primeiro nome não pertence a ninguém que se lembre.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.task.ask_ledger/2   [78 chars]
    en  Ninety-odd years. It has outlived four librarians and it will outlive a fifth.
    >>  ............................................
    pt  Uns noventa anos. Sobreviveu a quatro bibliotecários e vai sobreviver a um quinto.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the shelves."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.librarian.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.task.respond.leave   [37 chars]
    en  I'll let you get back to the shelves.
    >>  ............................................
    pt  Vou deixar você voltar pras prateleiras.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the shelves."
       spoken on: conversations.topic.work.librarian.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.left`: the villager accepts. Subject `work.librarian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.librarian.acquisition.blocked.respond / leave; conversations.scene.work.librarian.acquisition.succeeded.respond / leave; conversations.scene.work.librarian.damaged_volume.active.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.respond / leave; conversations.scene.work.librarian.damaged_volume.failed.respond / leave; conversations.scene.work.librarian.damaged_volume.succeeded.respond / leave …and 9 more
```

> Written out in full under **`conversations.scene.work.librarian.acquisition.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.librarian.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.librarian.village` — e.g. "Half the village can read because I stayed open on winter evenings and pretended it was for me."


```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.librarian.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.librarian.village.respond   [21 chars]
    en  That's what it's for.
    >>  ............................................
    pt  É pra isso que serve.
    >>  ............................................
```


### Button `ask_readers` — "Who did you teach?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.librarian.village` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.librarian.village.ask_readers` — accepted phrasings: "who did you teach"
  - the message must contain one of: `teach`, `readers`
  - scored words: `teach`(1.2), `readers`(1.5), `who`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.village.respond.ask_readers
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.village.respond.ask_readers   [18 chars]
    en  Who did you teach?
    >>  ............................................
    pt  Quem você ensinou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.librarian.village.ask_readers`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.librarian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a book you'd save first?" | "Quiet shelves to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.village.ask_readers
WHO    VILLAGER — what the player reads after pressing "Who did you teach?"
       spoken on: conversations.topic.work.librarian.village.respond, button `ask_readers`
       leaves the player on: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.village.ask_readers`: the villager explains. Subject `work.librarian.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.librarian.village.ask_readers/1   [93 chars]
    en  Eleven, over the years. Two of them read better than I do now and I am insufferable about it.
    >>  ............................................
    pt  Onze, ao longo dos anos. Dois leem melhor que eu agora e eu sou insuportável sobre isso.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.village.ask_readers/2   [75 chars]
    en  Anyone who came in and stayed past the first hour. Most didn't. Eleven did.
    >>  ............................................
    pt  Quem entrasse e ficasse além da primeira hora. A maioria não ficava. Onze ficaram.
    >>  ............................................
```


### Button `say_thanks` — "Half the town reading is not a small thing."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.librarian.village` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.librarian.village.say_thanks` — accepted phrasings: "half the town reading is not a small thing"
  - the message must contain one of: `reading`, `half`, `town`
  - scored words: `reading`(1.5), `half`(1.0), `town`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.village.respond.say_thanks   [43 chars]
    en  Half the town reading is not a small thing.
    >>  ............................................
    pt  Metade da cidade lendo não é pouca coisa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.librarian.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.librarian.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.librarian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a book you'd save first?" | "Quiet shelves to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Half the town reading is not a small thing."
       spoken on: conversations.topic.work.librarian.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.village.say_thanks`: the villager accepts. Subject `work.librarian.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.librarian.village.say_thanks/1   [84 chars]
    en  ...No. Said like that it isn't. I'd been thinking of it as evenings I spent indoors.
    >>  ............................................
    pt  ...Não é. Dito assim, não é. Eu vinha pensando nisso como noites passadas em casa.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.village.say_thanks/2   [80 chars]
    en  It's the only line I'd want on a stone, and now somebody else has said it first.
    >>  ............................................
    pt  É a única frase que eu queria numa lápide, e agora outra pessoa disse primeiro.
    >>  ............................................
```


### Button `ask_disputes` — "Does the ledger always settle it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.librarian.village` · offered only once the villager has actually said `work:librarian`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.librarian.village.ask_disputes` — accepted phrasings: "does the ledger always settle it"
  - the message must contain one of: `settle`, `dispute`
  - scored words: `settle`(1.5), `dispute`(1.5), `ledger`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.village.respond.ask_disputes
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.village.respond.ask_disputes   [33 chars]
    en  Does the ledger always settle it?
    >>  ............................................
    pt  O registro sempre resolve?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.librarian.village.ask_disputes`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.librarian.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there a book you'd save first?" | "Quiet shelves to you."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.village.ask_disputes
WHO    VILLAGER — what the player reads after pressing "Does the ledger always settle it?"
       spoken on: conversations.topic.work.librarian.village.respond, button `ask_disputes`
       leaves the player on: conversations.topic.work.librarian.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.village.ask_disputes`: the villager explains. Subject `work.librarian.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:librarian` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.librarian.village.ask_disputes/1   [80 chars]
    en  When it's been kept properly. That's why I copy pages instead of sleeping, %1$s.
    >>  ............................................
    pt  Quando foi bem mantido. Por isso eu copio páginas em vez de dormir, %1$s.
    >>  ............................................
  dialogue.conversations.work.prof.librarian.village.ask_disputes/2   [64 chars]
    en  Twice it hasn't, and both times the shouting went on for a year.
    >>  ............................................
    pt  Duas vezes não resolveu, e nas duas a gritaria durou um ano.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the shelves."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.librarian.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.librarian.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.librarian.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.librarian.village.respond.leave   [37 chars]
    en  I'll let you get back to the shelves.
    >>  ............................................
    pt  Vou deixar você voltar pras prateleiras.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.librarian.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the shelves."
       spoken on: conversations.topic.work.librarian.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.librarian.left`: the villager accepts. Subject `work.librarian.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.librarian.acquisition.blocked.respond / leave; conversations.scene.work.librarian.acquisition.succeeded.respond / leave; conversations.scene.work.librarian.damaged_volume.active.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.clarified.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.resisted.respond / leave; conversations.scene.work.librarian.damaged_volume.blocked.respond / leave; conversations.scene.work.librarian.damaged_volume.failed.respond / leave; conversations.scene.work.librarian.damaged_volume.succeeded.respond / leave …and 9 more
```

> Written out in full under **`conversations.scene.work.librarian.acquisition.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

