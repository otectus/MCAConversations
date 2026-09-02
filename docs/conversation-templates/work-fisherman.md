# Work talk with a fisherman

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.fisherman.bad_morning.succeeded.respond`](#conversations-scene-work-fisherman-bad-morning-succeeded-respond)
- [`conversations.scene.work.fisherman.empty_water.active.respond`](#conversations-scene-work-fisherman-empty-water-active-respond)
- [`conversations.scene.work.fisherman.empty_water.succeeded.respond`](#conversations-scene-work-fisherman-empty-water-succeeded-respond)
- [`conversations.scene.work.fisherman.followup`](#conversations-scene-work-fisherman-followup)
- [`conversations.scene.work.fisherman.torn_gear.blocked.respond`](#conversations-scene-work-fisherman-torn-gear-blocked-respond)
- [`conversations.scene.work.fisherman.torn_gear.succeeded.respond`](#conversations-scene-work-fisherman-torn-gear-succeeded-respond)
- [`conversations.topic.work.fisherman.craft.respond`](#conversations-topic-work-fisherman-craft-respond)
- [`conversations.topic.work.fisherman.followup`](#conversations-topic-work-fisherman-followup)
- [`conversations.topic.work.fisherman.future.respond`](#conversations-topic-work-fisherman-future-respond)
- [`conversations.topic.work.fisherman.respond`](#conversations-topic-work-fisherman-respond)
- [`conversations.topic.work.fisherman.risk.respond`](#conversations-topic-work-fisherman-risk-respond)
- [`conversations.topic.work.fisherman.task.respond`](#conversations-topic-work-fisherman-task-respond)
- [`conversations.topic.work.fisherman.village.respond`](#conversations-topic-work-fisherman-village-respond)

---

## `conversations.scene.work.fisherman.bad_morning.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.fisherman.bad_morning.succeeded` — e.g. "There was %2$s two springs back and I got home, and I have not talked about it much since."


```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.bad_morning.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.fisherman.bad_morning.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.fisherman.bad_morning.succeeded.respond   [26 chars]
    en  That morning on the water.
    >>  ............................................
    pt  Aquela manhã na água.
    >>  ............................................
```


### Button `ask_if_she_still_goes` — "Do you still go out alone?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.fisherman.bad_morning.succeeded` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fisherman.bad_morning.succeeded.ask_if_she_still_goes` — accepted phrasings: "do you still go out alone"; "do you still go out by yourself"; "are you still going out on your own"
  - the message must contain one of: `still`, `yourself`
  - scored words: `still`(1.8), `yourself`(1.8), `out`(0.8), `alone`(0.8), `going`(0.8), `own`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.bad_morning.succeeded.respond.ask_if_she_still_goes
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fisherman.bad_morning.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fisherman.bad_morning.succeeded.respond.ask_if_she_still_goes   [26 chars]
    en  Do you still go out alone?
    >>  ............................................
    pt  Você ainda sai sozinha?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.fisherman.weather`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.fisherman.bad_morning"}
- Then opens: `conversations.scene.work.fisherman.followup`
- …where the player's next choices will be: "What's the hardest part of an empty net?" | "I'll leave you to the water."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.bad_morning.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "Do you still go out alone?"
       spoken on: conversations.scene.work.fisherman.bad_morning.succeeded.respond, button `ask_if_she_still_goes`
       leaves the player on: conversations.scene.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.bad_morning.succeeded.answered`: the villager explains. Subject `work.fisherman.weather`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fisherman.bad_morning.succeeded.answered/1   [121 chars]
    en  Every morning. I am more careful about the sky and less careful about the hour, and I tell somebody where I am going now.
    >>  ............................................
    pt  Toda manhã. Sou mais cuidadosa com o céu e menos com o horário, e agora eu aviso alguém para onde vou.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.bad_morning.succeeded.answered/2   [102 chars]
    en  Yes, and the first week after was the hardest thing I have done. Not the water. Getting into the boat.
    >>  ............................................
    pt  Sim, e a primeira semana depois foi a coisa mais difícil que eu já fiz. Não a água. Entrar no barco.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.bad_morning.succeeded.answered/3   [128 chars]
    en  I do. There is no other version of my life, and pretending there is would only mean sitting on a bank being frightened for free.
    >>  ............................................
    pt  Saio. Não existe outra versão da minha vida, e fingir que existe só significaria ficar na margem com medo de graça.
    >>  ............................................
```


### Button `say_glad_she_came_back` — "I'm glad you came home."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.fisherman.bad_morning.succeeded` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fisherman.bad_morning.succeeded.say_glad_she_came_back` — accepted phrasings: "im glad you came home"; "i am glad you came home"; "glad you made it back"
  - the message must contain one of: `glad`, `home`
  - scored words: `glad`(1.8), `home`(1.8), `came`(0.8), `made`(0.8), `back`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.bad_morning.succeeded.respond.say_glad_she_came_back
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fisherman.bad_morning.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fisherman.bad_morning.succeeded.respond.say_glad_she_came_back   [23 chars]
    en  I'm glad you came home.
    >>  ............................................
    pt  Que bom que você voltou.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.fisherman.squall.seen`, budget `standard`, replay policy `once`
- Does: disposition — warmth +4, trust +2  _(recorded under topic `work.fisherman.weather`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.fisherman.bad_morning"}
- Then opens: `conversations.scene.work.fisherman.followup`
- …where the player's next choices will be: "What's the hardest part of an empty net?" | "I'll leave you to the water."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.bad_morning.succeeded.softened
WHO    VILLAGER — what the player reads after pressing "I'm glad you came home."
       spoken on: conversations.scene.work.fisherman.bad_morning.succeeded.respond, button `say_glad_she_came_back`
       leaves the player on: conversations.scene.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.bad_morning.succeeded.softened`: the villager accepts. Subject `work.fisherman.weather`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fisherman.bad_morning.succeeded.softened/1   [108 chars]
    en  So am I. It is a plainer thing to say than anyone else managed, and it is the only thing that actually fits.
    >>  ............................................
    pt  Eu também. É uma coisa mais simples do que qualquer um conseguiu dizer, e é a única que de fato serve.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.bad_morning.succeeded.softened/2   [125 chars]
    en  Thank you. Nobody said that at the time. Everybody said the weather had been bad, which is true and is not the same sentence.
    >>  ............................................
    pt  Obrigada. Ninguém disse isso na época. Todo mundo dizia que o tempo tinha estado ruim, o que é verdade e não é a mesma frase.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.bad_morning.succeeded.softened/3   [108 chars]
    en  I nearly did not. I have said that out loud twice, counting now, and it gets very slightly easier each time.
    >>  ............................................
    pt  Eu quase não voltei. Já disse isso em voz alta duas vezes, contando agora, e fica levemente mais fácil a cada vez.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the water."

*stance family `exit` · tone `plain` · answers the beat(s) `work.fisherman.bad_morning.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.bad_morning.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fisherman.bad_morning.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fisherman.bad_morning.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the water.
    >>  ............................................
    pt  Vou deixar você voltar para a água.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the water."
       spoken on: conversations.scene.work.fisherman.bad_morning.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.left`: the villager accepts. Subject `work.fisherman.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fisherman.empty_water.active.respond / leave; conversations.scene.work.fisherman.empty_water.succeeded.respond / leave; conversations.scene.work.fisherman.followup / leave; conversations.scene.work.fisherman.torn_gear.blocked.respond / leave; conversations.scene.work.fisherman.torn_gear.succeeded.respond / leave; conversations.topic.work.fisherman.craft.respond / leave; conversations.topic.work.fisherman.followup / leave; conversations.topic.work.fisherman.future.respond / leave …and 4 more
```

```text
  dialogue.conversations.work.prof.fisherman.leave/1   [46 chars]
    en  The water doesn't mind either way. Off you go.
    >>  ............................................
    pt  A água não liga de um jeito ou de outro. Pode ir.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.leave/2   [60 chars]
    en  Aye. Come back at dawn if you want to see it properly, %1$s.
    >>  ............................................
    pt  É. Volte de madrugada se quiser ver direito, %1$s.
    >>  ............................................
```

---


## `conversations.scene.work.fisherman.empty_water.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.fisherman.empty_water.active` — e.g. "%3$s has given me %2$s, and I have stopped being able to tell whether that is the river or me."


```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.empty_water.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.fisherman.empty_water.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.fisherman.empty_water.active.respond   [11 chars]
    en  The season.
    >>  ............................................
    pt  A temporada.
    >>  ............................................
```


### Button `ask_the_cause` — "What do you think is causing it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fisherman.empty_water.active` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fisherman.empty_water.active.ask_the_cause` — accepted phrasings: "what do you think is causing it"; "what do you think is causing it"; "what is behind the empty water"
  - the message must contain one of: `causing`, `behind`, `empty`
  - scored words: `causing`(1.8), `behind`(1.8), `empty`(1.8), `think`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.empty_water.active.respond.ask_the_cause
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fisherman.empty_water.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fisherman.empty_water.active.respond.ask_the_cause   [32 chars]
    en  What do you think is causing it?
    >>  ............................................
    pt  O que você acha que está causando?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fisherman.the_season`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.fisherman.empty_water"}
- Then opens: `conversations.scene.work.fisherman.followup`
- …where the player's next choices will be: "What's the hardest part of an empty net?" | "I'll leave you to the water."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.empty_water.active.explained
WHO    VILLAGER — what the player reads after pressing "What do you think is causing it?"
       spoken on: conversations.scene.work.fisherman.empty_water.active.respond, button `ask_the_cause`
       leaves the player on: conversations.scene.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.empty_water.active.explained`: the villager explains. Subject `work.fisherman.the_season`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fisherman.empty_water.active.explained/1   [138 chars]
    en  Warm water, most likely. Fish go deep and I fish shallow, and the honest answer is that I should change where I fish rather than complain.
    >>  ............................................
    pt  Água quente, muito provavelmente. O peixe desce e eu pesco raso, e a resposta honesta é que eu deveria mudar de lugar em vez de reclamar.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.empty_water.active.explained/2   [134 chars]
    en  Something upstream. I would need to walk four days to find out, and four days is four days of not fishing, so I keep guessing instead.
    >>  ............................................
    pt  Alguma coisa a montante. Eu precisaria caminhar quatro dias para descobrir, e quatro dias são quatro dias sem pescar, então sigo chutando.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.empty_water.active.explained/3   [115 chars]
    en  I genuinely do not know, and I am suspicious of everybody who does. A river is a large thing to be confident about.
    >>  ............................................
    pt  Sinceramente não sei, e desconfio de todo mundo que sabe. Um rio é uma coisa grande para se ter certeza sobre.
    >>  ............................................
```


### Button `advise_moving` — "Then fish somewhere deeper."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.fisherman.empty_water.active` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fisherman.empty_water.active.advise_moving` — accepted phrasings: "then fish somewhere deeper"; "then fish somewhere deeper"; "try deeper water for a while"
  - the message must contain one of: `deeper`, `deep`
  - scored words: `deeper`(1.8), `deep`(1.8), `fish`(0.8), `somewhere`(0.8), `try`(0.8), `while`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.empty_water.active.respond.advise_moving
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fisherman.empty_water.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fisherman.empty_water.active.respond.advise_moving   [27 chars]
    en  Then fish somewhere deeper.
    >>  ............................................
    pt  Então pesque em lugar mais fundo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.fisherman.the_season`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.fisherman.empty_water"}
- Then opens: `conversations.scene.work.fisherman.followup`
- …where the player's next choices will be: "What's the hardest part of an empty net?" | "I'll leave you to the water."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.empty_water.active.accepted
WHO    VILLAGER — what the player reads after pressing "Then fish somewhere deeper."
       spoken on: conversations.scene.work.fisherman.empty_water.active.respond, button `advise_moving`
       leaves the player on: conversations.scene.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.empty_water.active.accepted`: the villager accepts. Subject `work.fisherman.the_season`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fisherman.empty_water.active.accepted/1   [97 chars]
    en  I know. I have known for a fortnight. Nineteen years of habit is a heavier anchor than any I own.
    >>  ............................................
    pt  Eu sei. Sei há duas semanas. Dezenove anos de hábito são uma âncora mais pesada do que qualquer uma que eu tenha.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.empty_water.active.accepted/2   [124 chars]
    en  Yes. It means longer lines and a heavier boat and asking a favour, and every one of those is easier than another empty week.
    >>  ............................................
    pt  Sim. Significa linhas mais longas, barco mais pesado e pedir um favor, e todas essas coisas são mais fáceis do que outra semana vazia.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.empty_water.active.accepted/3   [99 chars]
    en  You are right. I have been treating the river as if it owed me the same water it gave me last year.
    >>  ............................................
    pt  Você tem razão. Eu venho tratando o rio como se ele me devesse a mesma água que me deu no ano passado.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the water."

*stance family `exit` · tone `plain` · answers the beat(s) `work.fisherman.empty_water.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.empty_water.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fisherman.empty_water.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fisherman.empty_water.active.respond.leave   [35 chars]
    en  I'll let you get back to the water.
    >>  ............................................
    pt  Vou deixar você voltar para a água.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the water."
       spoken on: conversations.scene.work.fisherman.empty_water.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.left`: the villager accepts. Subject `work.fisherman.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fisherman.bad_morning.succeeded.respond / leave; conversations.scene.work.fisherman.empty_water.succeeded.respond / leave; conversations.scene.work.fisherman.followup / leave; conversations.scene.work.fisherman.torn_gear.blocked.respond / leave; conversations.scene.work.fisherman.torn_gear.succeeded.respond / leave; conversations.topic.work.fisherman.craft.respond / leave; conversations.topic.work.fisherman.followup / leave; conversations.topic.work.fisherman.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.fisherman.bad_morning.succeeded.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.fisherman.empty_water.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.fisherman.empty_water.succeeded` — e.g. "Deeper worked. Nineteen years of fishing one shelf and the fix was forty paces further out."


```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.empty_water.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.fisherman.empty_water.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.fisherman.empty_water.succeeded.respond   [17 chars]
    en  The deeper water.
    >>  ............................................
    pt  A água mais funda.
    >>  ............................................
```


### Button `note_the_change` — "Changing your habit took nerve."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.fisherman.empty_water.succeeded` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fisherman.empty_water.succeeded.note_the_change` — accepted phrasings: "changing your habit took nerve"; "changing your habit took nerve"; "shifting a habit like that is hard"
  - the message must contain one of: `habit`, `nerve`, `shifting`
  - scored words: `habit`(1.8), `nerve`(1.8), `shifting`(1.8), `changing`(0.8), `took`(0.8), `like`(0.8), `hard`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.empty_water.succeeded.respond.note_the_change
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fisherman.empty_water.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fisherman.empty_water.succeeded.respond.note_the_change   [31 chars]
    en  Changing your habit took nerve.
    >>  ............................................
    pt  Mudar seu hábito exigiu coragem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.fisherman.the_season`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.fisherman.empty_water"}
- Then opens: `conversations.scene.work.fisherman.followup`
- …where the player's next choices will be: "What's the hardest part of an empty net?" | "I'll leave you to the water."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.empty_water.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Changing your habit took nerve."
       spoken on: conversations.scene.work.fisherman.empty_water.succeeded.respond, button `note_the_change`
       leaves the player on: conversations.scene.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.empty_water.succeeded.acknowledged`: the villager accepts. Subject `work.fisherman.the_season`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fisherman.empty_water.succeeded.acknowledged/1   [122 chars]
    en  It took an empty fortnight and somebody saying it out loud. I do not think I would have got there alone before the winter.
    >>  ............................................
    pt  Exigiu duas semanas vazias e alguém dizendo em voz alta. Acho que sozinha eu não teria chegado lá antes do inverno.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.empty_water.succeeded.acknowledged/2   [128 chars]
    en  Thank you. A habit that has fed you for nineteen years is not a stupid thing to trust, which is exactly why it is hard to leave.
    >>  ............................................
    pt  Obrigada. Um hábito que te alimentou por dezenove anos não é uma coisa boba de se confiar, e é justamente por isso que é difícil largar.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.empty_water.succeeded.acknowledged/3   [123 chars]
    en  The nerve was the first morning. After that it was just rowing further, and rowing further is not brave, it is only tiring.
    >>  ............................................
    pt  A coragem foi na primeira manhã. Depois disso era só remar mais longe, e remar mais longe não é bravura, é só cansaço.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the water."

*stance family `exit` · tone `plain` · answers the beat(s) `work.fisherman.empty_water.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.empty_water.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fisherman.empty_water.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fisherman.empty_water.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the water.
    >>  ............................................
    pt  Vou deixar você voltar para a água.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the water."
       spoken on: conversations.scene.work.fisherman.empty_water.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.left`: the villager accepts. Subject `work.fisherman.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fisherman.bad_morning.succeeded.respond / leave; conversations.scene.work.fisherman.empty_water.active.respond / leave; conversations.scene.work.fisherman.followup / leave; conversations.scene.work.fisherman.torn_gear.blocked.respond / leave; conversations.scene.work.fisherman.torn_gear.succeeded.respond / leave; conversations.topic.work.fisherman.craft.respond / leave; conversations.topic.work.fisherman.followup / leave; conversations.topic.work.fisherman.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.fisherman.bad_morning.succeeded.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.fisherman.followup`

**Reached from 9 route(s):** `conversations.scene.work.fisherman.bad_morning.succeeded.respond` / `ask_if_she_still_goes`; `conversations.scene.work.fisherman.bad_morning.succeeded.respond` / `say_glad_she_came_back`; `conversations.scene.work.fisherman.empty_water.active.respond` / `ask_the_cause`; `conversations.scene.work.fisherman.empty_water.active.respond` / `advise_moving`; `conversations.scene.work.fisherman.empty_water.succeeded.respond` / `note_the_change`; `conversations.scene.work.fisherman.torn_gear.blocked.respond` / `ask_about_mending`; `conversations.scene.work.fisherman.torn_gear.blocked.respond` / `offer_string`; `conversations.scene.work.fisherman.torn_gear.blocked.respond` / `suggest_borrowing`; `conversations.scene.work.fisherman.torn_gear.succeeded.respond` / `ask_about_the_haul`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.fisherman.bad_morning.succeeded.answered` — e.g. "Every morning. I am more careful about the sky and less careful about the hour, and I tell somebody where I am going now."
- `conversations.scene.work.fisherman.bad_morning.succeeded.softened` — e.g. "So am I. It is a plainer thing to say than anyone else managed, and it is the only thing that actually fits."
- `conversations.scene.work.fisherman.empty_water.active.accepted` — e.g. "I know. I have known for a fortnight. Nineteen years of habit is a heavier anchor than any I own."
- `conversations.scene.work.fisherman.empty_water.active.explained` — e.g. "Warm water, most likely. Fish go deep and I fish shallow, and the honest answer is that I should change where I fish rather than complain."
- `conversations.scene.work.fisherman.empty_water.succeeded.acknowledged` — e.g. "It took an empty fortnight and somebody saying it out loud. I do not think I would have got there alone before the winter."
- `conversations.scene.work.fisherman.torn_gear.blocked.accepted` — e.g. "Then I am at %2$s before light on Thursday, and there will be fish in this village by Thursday evening."
- `conversations.scene.work.fisherman.torn_gear.blocked.explained` — e.g. "%2$s is one evening with the right twine and four evenings with the wrong twine, and a season with none."
- `conversations.scene.work.fisherman.torn_gear.blocked.resisted` — e.g. "A borrowed net is somebody else's living, and if I tear it I have taken their week to save mine."
- `conversations.scene.work.fisherman.torn_gear.succeeded.answered` — e.g. "Good enough that I gave two away on the walk home, which is my whole measure of a good morning."


```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.fisherman.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.fisherman.followup   [25 chars]
    en  Anything else you wanted?
    >>  ............................................
    pt  Queria mais alguma coisa?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of an empty net?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.fisherman.*` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fisherman.followup.ask_more` — accepted phrasings: "whats the hardest part of an empty net"; "what is the hardest part of an empty net"; "hardest thing about hauling an empty net"
  - the message must contain one of: `hardest`, `net`
  - scored words: `hardest`(1.8), `net`(1.8), `whats`(0.8), `part`(0.8), `empty`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fisherman.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fisherman.followup.ask_more   [40 chars]
    en  What's the hardest part of an empty net?
    >>  ............................................
    pt  Qual é a parte mais difícil de uma rede vazia?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fisherman.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fisherman.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the worst the water's done to you?" | "Good fishing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of an empty net?"
       spoken on: conversations.scene.work.fisherman.followup, button `ask_more`
       leaves the player on: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.hard`: the villager explains. Subject `work.fisherman.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.fisherman.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.fisherman.hard/1   [79 chars]
    en  Two in five on a bad week. He's not greedy, he's just better at this than I am.
    >>  ............................................
    pt  Dois em cada cinco numa semana ruim. Ele não é ganancioso, só é melhor nisso que eu.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.hard/2   [80 chars]
    en  Enough that I've named him. You don't name a thing you're winning against, %1$s.
    >>  ............................................
    pt  O bastante pra eu ter dado nome a ele. Não se dá nome a quem você está vencendo, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the water."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.fisherman.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fisherman.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fisherman.followup.leave   [28 chars]
    en  I'll leave you to the water.
    >>  ............................................
    pt  Vou deixar você com a água.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the water."
       spoken on: conversations.scene.work.fisherman.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.left`: the villager accepts. Subject `work.fisherman.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fisherman.bad_morning.succeeded.respond / leave; conversations.scene.work.fisherman.empty_water.active.respond / leave; conversations.scene.work.fisherman.empty_water.succeeded.respond / leave; conversations.scene.work.fisherman.torn_gear.blocked.respond / leave; conversations.scene.work.fisherman.torn_gear.succeeded.respond / leave; conversations.topic.work.fisherman.craft.respond / leave; conversations.topic.work.fisherman.followup / leave; conversations.topic.work.fisherman.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.fisherman.bad_morning.succeeded.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.fisherman.torn_gear.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.fisherman.torn_gear.blocked` — e.g. "I have %2$s, so %3$s might as well be dry land for all the good it does me this week."


```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.torn_gear.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.fisherman.torn_gear.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.fisherman.torn_gear.blocked.respond   [9 chars]
    en  The gear.
    >>  ............................................
    pt  O apetrecho.
    >>  ............................................
```


### Button `ask_about_mending` — "How long does mending take?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fisherman.torn_gear.blocked` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fisherman.torn_gear.blocked.ask_about_mending` — accepted phrasings: "how long does mending take"; "how long does mending take"; "how long is the repair"
  - the message must contain one of: `mending`, `repair`, `long`
  - scored words: `mending`(1.8), `repair`(1.8), `long`(1.8), `does`(0.8), `take`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.torn_gear.blocked.respond.ask_about_mending
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fisherman.torn_gear.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fisherman.torn_gear.blocked.respond.ask_about_mending   [27 chars]
    en  How long does mending take?
    >>  ............................................
    pt  Quanto tempo leva o conserto?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fisherman.nets`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.fisherman.torn_gear"}
- Then opens: `conversations.scene.work.fisherman.followup`
- …where the player's next choices will be: "What's the hardest part of an empty net?" | "I'll leave you to the water."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.torn_gear.blocked.explained
WHO    VILLAGER — what the player reads after pressing "How long does mending take?"
       spoken on: conversations.scene.work.fisherman.torn_gear.blocked.respond, button `ask_about_mending`
       leaves the player on: conversations.scene.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.torn_gear.blocked.explained`: the villager explains. Subject `work.fisherman.nets`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fisherman.torn_gear.blocked.explained/1   [104 chars]
    en  %2$s is one evening with the right twine and four evenings with the wrong twine, and a season with none.
    >>  ............................................
    pt  %2$s leva uma noite com a linha certa, quatro noites com a linha errada, e uma estação com nenhuma.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.torn_gear.blocked.explained/2   [109 chars]
    en  The mending is quick. The finding of materials is what takes a week, which nobody believes until they try it.
    >>  ............................................
    pt  O conserto é rápido. Achar material é o que leva uma semana, coisa em que ninguém acredita antes de tentar.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.torn_gear.blocked.explained/3   [142 chars]
    en  Longer than it should, because I do it properly. A hurried mend fails in the water, and a net that fails in the water takes the catch with it.
    >>  ............................................
    pt  Mais do que deveria, porque eu faço direito. Remendo apressado falha na água, e rede que falha na água leva a pescaria junto.
    >>  ............................................
```


### Button `offer_string` — "I'll bring you string for it."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.fisherman.torn_gear.blocked` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fisherman.torn_gear.blocked.offer_string` — accepted phrasings: "ill bring you string for it"; "i can bring you string"; "let me fetch string for that"
  - the message must contain one of: `string`, `twine`
  - scored words: `string`(1.8), `twine`(1.8), `ill`(0.8), `bring`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.torn_gear.blocked.respond.offer_string
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fisherman.torn_gear.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fisherman.torn_gear.blocked.respond.offer_string   [29 chars]
    en  I'll bring you string for it.
    >>  ............................................
    pt  Vou te trazer barbante para isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.fisherman.gear.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.fisherman.nets`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.torn_gear", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.fisherman.torn_gear", "obligation": "commitment:work.fisherman.bring_string"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.fisherman.bring_string"}
- Then opens: `conversations.scene.work.fisherman.followup`
- …where the player's next choices will be: "What's the hardest part of an empty net?" | "I'll leave you to the water."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.torn_gear.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you string for it."
       spoken on: conversations.scene.work.fisherman.torn_gear.blocked.respond, button `offer_string`
       leaves the player on: conversations.scene.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.torn_gear.blocked.accepted`: the villager accepts. Subject `work.fisherman.nets`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fisherman.torn_gear.blocked.accepted/1   [103 chars]
    en  Then I am at %2$s before light on Thursday, and there will be fish in this village by Thursday evening.
    >>  ............................................
    pt  Então eu estou em %2$s antes de clarear na quinta, e vai ter peixe nesta vila na quinta à noite.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.torn_gear.blocked.accepted/2   [108 chars]
    en  Bring it and I will mend tonight. I would rather sit up late with a needle than sit on a bank being useless.
    >>  ............................................
    pt  Traga e eu remendo hoje à noite. Prefiro varar a noite com uma agulha a ficar sentada na margem sendo inútil.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.torn_gear.blocked.accepted/3   [109 chars]
    en  Yes. And take two fish for it, and do not tell me it was nothing, because six days of nothing is not nothing.
    >>  ............................................
    pt  Sim. E leve dois peixes por isso, e não me diga que não foi nada, porque seis dias de nada não é nada.
    >>  ............................................
```


### Button `suggest_borrowing` — "Borrow a net from somebody."

*stance family `candor` · tone `plain` · outcome `resisted` · answers the beat(s) `work.fisherman.torn_gear.blocked` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fisherman.torn_gear.blocked.suggest_borrowing` — accepted phrasings: "borrow a net from somebody"; "borrow a net from somebody"; "ask another boat to lend you gear"
  - the message must contain one of: `borrow`, `lend`
  - scored words: `borrow`(1.8), `lend`(1.8), `net`(0.8), `from`(0.8), `somebody`(0.8), `ask`(0.8), `another`(0.8), `boat`(0.8), `gear`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.torn_gear.blocked.respond.suggest_borrowing
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fisherman.torn_gear.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fisherman.torn_gear.blocked.respond.suggest_borrowing   [27 chars]
    en  Borrow a net from somebody.
    >>  ............................................
    pt  Peça uma rede emprestada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +1  _(recorded under topic `work.fisherman.nets`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.fisherman.torn_gear"}
- Then opens: `conversations.scene.work.fisherman.followup`
- …where the player's next choices will be: "What's the hardest part of an empty net?" | "I'll leave you to the water."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.torn_gear.blocked.resisted
WHO    VILLAGER — what the player reads after pressing "Borrow a net from somebody."
       spoken on: conversations.scene.work.fisherman.torn_gear.blocked.respond, button `suggest_borrowing`
       leaves the player on: conversations.scene.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.torn_gear.blocked.resisted`: the villager resists. Subject `work.fisherman.nets`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fisherman.torn_gear.blocked.resisted/1   [96 chars]
    en  A borrowed net is somebody else's living, and if I tear it I have taken their week to save mine.
    >>  ............................................
    pt  Rede emprestada é o sustento de outra pessoa, e se eu rasgar, tomei a semana dela para salvar a minha.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.torn_gear.blocked.resisted/2   [108 chars]
    en  There are two other boats and both are out at dawn. A spare net is a thing nobody on the water actually has.
    >>  ............................................
    pt  Existem dois outros barcos e os dois saem ao amanhecer. Rede sobrando é coisa que ninguém na água de fato tem.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.torn_gear.blocked.resisted/3   [87 chars]
    en  I would rather ask for twine than for a net. Materials are a favour; gear is a hostage.
    >>  ............................................
    pt  Prefiro pedir linha a pedir rede. Material é favor; apetrecho é refém.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the water."

*stance family `exit` · tone `plain` · answers the beat(s) `work.fisherman.torn_gear.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.torn_gear.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fisherman.torn_gear.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fisherman.torn_gear.blocked.respond.leave   [35 chars]
    en  I'll let you get back to the water.
    >>  ............................................
    pt  Vou deixar você voltar para a água.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the water."
       spoken on: conversations.scene.work.fisherman.torn_gear.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.left`: the villager accepts. Subject `work.fisherman.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fisherman.bad_morning.succeeded.respond / leave; conversations.scene.work.fisherman.empty_water.active.respond / leave; conversations.scene.work.fisherman.empty_water.succeeded.respond / leave; conversations.scene.work.fisherman.followup / leave; conversations.scene.work.fisherman.torn_gear.succeeded.respond / leave; conversations.topic.work.fisherman.craft.respond / leave; conversations.topic.work.fisherman.followup / leave; conversations.topic.work.fisherman.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.fisherman.bad_morning.succeeded.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.fisherman.torn_gear.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.fisherman.torn_gear.succeeded` — e.g. "Mended and back at %2$s before dawn, and the first haul was better than it had any right to be."


```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.torn_gear.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.fisherman.torn_gear.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.fisherman.torn_gear.succeeded.respond   [17 chars]
    en  The gear, mended.
    >>  ............................................
    pt  O apetrecho, remendado.
    >>  ............................................
```


### Button `ask_about_the_haul` — "How was the haul?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fisherman.torn_gear.succeeded` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.fisherman.torn_gear.succeeded.ask_about_the_haul` — accepted phrasings: "how was the haul"; "how was the haul"; "did the catch come good"
  - the message must contain one of: `haul`, `catch`
  - scored words: `haul`(1.8), `catch`(1.8), `come`(0.8), `good`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.torn_gear.succeeded.respond.ask_about_the_haul
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fisherman.torn_gear.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fisherman.torn_gear.succeeded.respond.ask_about_the_haul   [17 chars]
    en  How was the haul?
    >>  ............................................
    pt  Como foi a pescaria?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fisherman.nets`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.fisherman.torn_gear"}
- Then opens: `conversations.scene.work.fisherman.followup`
- …where the player's next choices will be: "What's the hardest part of an empty net?" | "I'll leave you to the water."

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.torn_gear.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "How was the haul?"
       spoken on: conversations.scene.work.fisherman.torn_gear.succeeded.respond, button `ask_about_the_haul`
       leaves the player on: conversations.scene.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.torn_gear.succeeded.answered`: the villager explains. Subject `work.fisherman.nets`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.fisherman.torn_gear.succeeded.answered/1   [95 chars]
    en  Good enough that I gave two away on the walk home, which is my whole measure of a good morning.
    >>  ............................................
    pt  Boa o bastante para eu dar dois peixes no caminho de casa, que é a minha medida inteira de uma boa manhã.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.torn_gear.succeeded.answered/2   [140 chars]
    en  Ordinary. I want to be careful here — an ordinary haul after a bad fortnight feels enormous and is not, and I have made that mistake before.
    >>  ............................................
    pt  Comum. Quero ser cuidadosa aqui — uma pescaria comum depois de duas semanas ruins parece enorme e não é, e eu já errei assim.
    >>  ............................................
  dialogue.conversations.scene.work.fisherman.torn_gear.succeeded.answered/3   [102 chars]
    en  Better than the week before and worse than the week before that. The river does not know about my net.
    >>  ............................................
    pt  Melhor que a semana passada e pior que a retrasada. O rio não sabe da minha rede.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the water."

*stance family `exit` · tone `plain` · answers the beat(s) `work.fisherman.torn_gear.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.fisherman.torn_gear.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.fisherman.torn_gear.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.fisherman.torn_gear.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the water.
    >>  ............................................
    pt  Vou deixar você voltar para a água.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the water."
       spoken on: conversations.scene.work.fisherman.torn_gear.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.left`: the villager accepts. Subject `work.fisherman.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fisherman.bad_morning.succeeded.respond / leave; conversations.scene.work.fisherman.empty_water.active.respond / leave; conversations.scene.work.fisherman.empty_water.succeeded.respond / leave; conversations.scene.work.fisherman.followup / leave; conversations.scene.work.fisherman.torn_gear.blocked.respond / leave; conversations.topic.work.fisherman.craft.respond / leave; conversations.topic.work.fisherman.followup / leave; conversations.topic.work.fisherman.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.fisherman.bad_morning.succeeded.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.fisherman.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.fisherman.craft` — e.g. "Nobody teaches patience. You either sit through the first hundred empty mornings or you don't."


```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.fisherman.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.fisherman.craft.respond   [27 chars]
    en  That's how it's come to me.
    >>  ............................................
    pt  Foi assim que aprendi.
    >>  ............................................
```


### Button `ask_which_half` — "Which half was gold?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fisherman.craft` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fisherman.craft.ask_which_half` — accepted phrasings: "which half was gold"
  - the message must contain one of: `gold`, `half`, `useful`
  - scored words: `gold`(1.5), `half`(1.0), `useful`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.craft.respond.ask_which_half
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.craft.respond.ask_which_half   [20 chars]
    en  Which half was gold?
    >>  ............................................
    pt  Qual metade era ouro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fisherman.craft.ask_which_half`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fisherman.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the worst the water's done to you?" | "Good fishing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.craft.ask_which_half
WHO    VILLAGER — what the player reads after pressing "Which half was gold?"
       spoken on: conversations.topic.work.fisherman.craft.respond, button `ask_which_half`
       leaves the player on: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.craft.ask_which_half`: the villager explains. Subject `work.fisherman.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fisherman.craft.ask_which_half/1   [67 chars]
    en  Watch the birds, not the water. I've never once regretted that one.
    >>  ............................................
    pt  Olhe os pássaros, não a água. Nunca me arrependi dessa.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.craft.ask_which_half/2   [72 chars]
    en  Never fish where it's easy to stand. That took me four years to believe.
    >>  ............................................
    pt  Nunca pesque onde é fácil ficar de pé. Levei quatro anos pra acreditar.
    >>  ............................................
```


### Button `admire` — "A hundred empty mornings is its own kind of skill."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.fisherman.craft` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fisherman.craft.admire` — accepted phrasings: "a hundred empty mornings is its own kind of skill"
  - the message must contain one of: `mornings`, `patience`, `empty`
  - scored words: `mornings`(1.5), `patience`(1.5), `empty`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.craft.respond.admire   [50 chars]
    en  A hundred empty mornings is its own kind of skill.
    >>  ............................................
    pt  Cem manhãs vazias são uma habilidade em si.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.fisherman.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.fisherman.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fisherman.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the worst the water's done to you?" | "Good fishing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.craft.admire
WHO    VILLAGER — what the player reads after pressing "A hundred empty mornings is its own kind of skill."
       spoken on: conversations.topic.work.fisherman.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.craft.admire`: the villager accepts. Subject `work.fisherman.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fisherman.craft.admire/1   [71 chars]
    en  It is, and it's the one nobody wants to learn. Everyone wants the fish.
    >>  ............................................
    pt  É, e é a que ninguém quer aprender. Todo mundo quer o peixe.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.craft.admire/2   [79 chars]
    en  Say that to the young one who came down last spring and gave up in a fortnight.
    >>  ............................................
    pt  Diga isso ao jovem que desceu na primavera passada e desistiu em quinze dias.
    >>  ............................................
```


### Button `ask_teach` — "Would you teach it to anyone?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fisherman.craft` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fisherman.craft.ask_teach` — accepted phrasings: "would you teach it to anyone"
  - the message must contain one of: `teach`, `anyone`, `apprentice`
  - scored words: `teach`(1.5), `anyone`(1.0), `apprentice`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.craft.respond.ask_teach
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.craft.respond.ask_teach   [29 chars]
    en  Would you teach it to anyone?
    >>  ............................................
    pt  Você ensinaria a alguém?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fisherman.craft.ask_teach`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fisherman.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the worst the water's done to you?" | "Good fishing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.craft.ask_teach
WHO    VILLAGER — what the player reads after pressing "Would you teach it to anyone?"
       spoken on: conversations.topic.work.fisherman.craft.respond, button `ask_teach`
       leaves the player on: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.craft.ask_teach`: the villager explains. Subject `work.fisherman.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fisherman.craft.ask_teach/1   [68 chars]
    en  I've tried twice. Both of them wanted the trick and there isn't one.
    >>  ............................................
    pt  Já tentei duas vezes. Os dois queriam o truque e não existe truque.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.craft.ask_teach/2   [73 chars]
    en  Aye. Come at dawn and don't bring conversation, and we'll see how you do.
    >>  ............................................
    pt  Ensinaria. Venha ao amanhecer sem conversa e a gente vê como você se sai.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the water."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.fisherman.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.craft.respond.leave   [28 chars]
    en  I'll leave you to the water.
    >>  ............................................
    pt  Vou deixar você com a água.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the water."
       spoken on: conversations.topic.work.fisherman.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.left`: the villager accepts. Subject `work.fisherman.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fisherman.bad_morning.succeeded.respond / leave; conversations.scene.work.fisherman.empty_water.active.respond / leave; conversations.scene.work.fisherman.empty_water.succeeded.respond / leave; conversations.scene.work.fisherman.followup / leave; conversations.scene.work.fisherman.torn_gear.blocked.respond / leave; conversations.scene.work.fisherman.torn_gear.succeeded.respond / leave; conversations.topic.work.fisherman.followup / leave; conversations.topic.work.fisherman.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.fisherman.bad_morning.succeeded.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.fisherman.followup`

**Reached from 20 route(s):** `conversations.scene.work.fisherman.followup` / `ask_more`; `conversations.topic.work.fisherman.craft.respond` / `ask_which_half`; `conversations.topic.work.fisherman.craft.respond` / `admire`; `conversations.topic.work.fisherman.craft.respond` / `ask_teach`; `conversations.topic.work.fisherman.future.respond` / `ask_go`; `conversations.topic.work.fisherman.future.respond` / `encourage`; `conversations.topic.work.fisherman.future.respond` / `ask_hands`; `conversations.topic.work.fisherman.respond` / `ask_hard`; `conversations.topic.work.fisherman.respond` / `value`; `conversations.topic.work.fisherman.respond` / `challenge`; `conversations.topic.work.fisherman.respond` / `challenge`; `conversations.topic.work.fisherman.risk.respond` / `ask_twice` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.fisherman.challenge.landed` — e.g. "Ha! No. Sitting still and being right about where is the trade."
- `conversations.work.prof.fisherman.challenge.stung` — e.g. "...Right. Well. Enjoy your supper, whoever caught it."
- `conversations.work.prof.fisherman.craft.admire` — e.g. "It is, and it's the one nobody wants to learn. Everyone wants the fish."
- `conversations.work.prof.fisherman.craft.ask_teach` — e.g. "I've tried twice. Both of them wanted the trick and there isn't one."
- `conversations.work.prof.fisherman.craft.ask_which_half` — e.g. "Watch the birds, not the water. I've never once regretted that one."
- `conversations.work.prof.fisherman.future.ask_go` — e.g. "Two days there and two back and the village eats on Friday. That's the whole answer."
- `conversations.work.prof.fisherman.future.ask_hands` — e.g. "The knots. When the knots take twice as long, that's the letter arriving."
- `conversations.work.prof.fisherman.future.encourage` — e.g. "...Spring. You've put a season on it, and now it's a plan and not a daydream."
- `conversations.work.prof.fisherman.hard` — e.g. "Two in five on a bad week. He's not greedy, he's just better at this than I am."
- `conversations.work.prof.fisherman.risk.ask_rule` — e.g. "Never past the second bend after rain. I broke it once and that was the second time."
- `conversations.work.prof.fisherman.risk.ask_twice` — e.g. "I got out slower. That's the only detail that matters and I think about it often."
- `conversations.work.prof.fisherman.risk.concern` — e.g. "...I do. I've been told. I've told myself, come to that."
- `conversations.work.prof.fisherman.task.ask_corner` — e.g. "Something with teeth, or something with a hull. I've theories and no proof."
- `conversations.work.prof.fisherman.task.offer_hands` — e.g. "...Alright. Watch once, then do it wrong, then do it right. That's the order."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.fisherman.followup   [38 chars]
    en  That's the river's side of it, anyway.
    >>  ............................................
    pt  É o lado do rio da coisa, enfim.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.fisherman.challenge.landed`, `work.fisherman.challenge.stung`, `work.fisherman.craft.admire`, `work.fisherman.craft.ask_teach`, `work.fisherman.craft.ask_which_half`, `work.fisherman.future.ask_go`, `work.fisherman.future.ask_hands`, `work.fisherman.future.encourage`, `work.fisherman.hard`, `work.fisherman.risk.ask_rule`, `work.fisherman.risk.ask_twice`, `work.fisherman.risk.concern`, `work.fisherman.task.ask_corner`, `work.fisherman.task.offer_hands`, `work.fisherman.task.stay_quiet`, `work.fisherman.value`, `work.fisherman.village.ask_children`, `work.fisherman.village.ask_stopped`, `work.fisherman.village.say_credit` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.fisherman.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `quiet`, `river`
  - scored words: `thought`(1.2), `quiet`(1.0), `river`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.fisherman.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.fisherman.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.fisherman.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.fisherman.thanks`: the villager accepts. Subject `work.fisherman.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fisherman.thanks/1   [68 chars]
    en  Few do. It's a quiet trade and quiet trades don't get thought about.
    >>  ............................................
    pt  Poucos pensam. É um ofício silencioso, e ofício silencioso ninguém pensa nele.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.thanks/2   [74 chars]
    en  That's the dock for you, %1$s. Plenty of time to think and nobody to tell.
    >>  ............................................
    pt  É a doca, %1$s. Tempo de sobra pra pensar e ninguém pra contar.
    >>  ............................................
```


### Button `ask_more` — "What's the worst the water's done to you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fisherman.challenge.landed`, `work.fisherman.challenge.stung`, `work.fisherman.craft.admire`, `work.fisherman.craft.ask_teach`, `work.fisherman.craft.ask_which_half`, `work.fisherman.future.ask_go`, `work.fisherman.future.ask_hands`, `work.fisherman.future.encourage`, `work.fisherman.hard`, `work.fisherman.risk.ask_rule`, `work.fisherman.risk.ask_twice`, `work.fisherman.risk.concern`, `work.fisherman.task.ask_corner`, `work.fisherman.task.offer_hands`, `work.fisherman.task.stay_quiet`, `work.fisherman.value`, `work.fisherman.village.ask_children`, `work.fisherman.village.ask_stopped`, `work.fisherman.village.say_credit` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.fisherman.more` — accepted phrasings: "what's the worst the water's done to you"
  - the message must contain one of: `water`, `storm`
  - scored words: `water`(1.5), `storm`(1.5), `worst`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.followup.ask_more   [41 chars]
    en  What's the worst the water's done to you?
    >>  ............................................
    pt  Qual a pior coisa que a água já te fez?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.fisherman.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.more
WHO    VILLAGER — what the player reads after pressing "What's the worst the water's done to you?"
       spoken on: conversations.topic.work.fisherman.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.fisherman.more`: the villager discloses. Subject `work.fisherman.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fisherman.more/1   [69 chars]
    en  Took a boat and gave it back in pieces. I was in it for part of that.
    >>  ............................................
    pt  Levou um barco e devolveu em pedaços. Eu estava nele em parte disso.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.more/2   [67 chars]
    en  A storm in my second year. I've respected the sky ever since, %1$s.
    >>  ............................................
    pt  Uma tempestade no meu segundo ano. Respeito o céu desde então, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.fisherman.more/1
    en  It took a boat with me in it. I don't tell that one often and I've not told it well now.
    >>  ............................................
    pt  Levou um barco comigo dentro. Não conto essa sempre e não contei bem agora.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.fisherman.more/2
    en  The river's lower every year. I don't say so, because people worry, and worry doesn't raise a river.
    >>  ............................................
    pt  O rio está mais baixo todo ano. Eu não digo, porque as pessoas se preocupam, e preocupação não levanta rio.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.fisherman.more/1
    en  Took a boat once. Gave most of it back. Rivers do as they like and always have.
    >>  ............................................
    pt  Levou um barco uma vez. Devolveu quase todo. Rios fazem o que querem e sempre fizeram.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.fisherman.more/2
    en  Lower than it was. Rivers rise and fall over lifetimes; I've only had the one to watch it in.
    >>  ............................................
    pt  Mais baixo do que era. Rios sobem e descem ao longo de vidas; eu só tive uma pra observar.
    >>  ............................................
  confident.dialogue.conversations.work.prof.fisherman.more/1
    en  Took a boat and gave it back in pieces. I was in it for part of that.
    >>  ............................................
    pt  Levou um barco e devolveu em pedaços. Eu estava dentro em parte disso.
    >>  ............................................
  confident.dialogue.conversations.work.prof.fisherman.more/2
    en  The river's lower than when I was young. I'm the only one counting and nobody has asked me the number.
    >>  ............................................
    pt  O rio está mais baixo do que quando eu era jovem. Sou o único contando e ninguém me perguntou o número.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.fisherman.more/1
    en  Took a boat and gave it back in pieces. I was in it for part of that.
    >>  ............................................
    pt  Levou um barco e devolveu em pedaços. Eu estava dentro em parte disso.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.fisherman.more/2
    en  The river's lower than when I was young. I'm the only one counting and nobody has asked me the number.
    >>  ............................................
    pt  O rio está mais baixo do que quando eu era jovem. Sou o único contando e ninguém me perguntou o número.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.fisherman.more/1
    en  Took a boat and gave it back in pieces, with me in it. Come out with me and I'll show you where.
    >>  ............................................
    pt  Levou um barco e devolveu em pedaços, comigo dentro. Venha comigo e eu te mostro onde.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.fisherman.more/2
    en  The river's lower. I'd rather tell you than the mayor — you'd actually listen to the number.
    >>  ............................................
    pt  O rio está mais baixo. Prefiro contar a você que ao prefeito — você escutaria o número.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.fisherman.more/1
    en  Took a boat and gave it back in pieces, with me in it. Come out with me and I'll show you where.
    >>  ............................................
    pt  Levou um barco e devolveu em pedaços, comigo dentro. Venha comigo e eu te mostro onde.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.fisherman.more/2
    en  The river's lower. I'd rather tell you than the mayor — you'd actually listen to the number.
    >>  ............................................
    pt  O rio está mais baixo. Prefiro contar a você que ao prefeito — você escutaria o número.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.fisherman.more/1
    en  Took a boat and gave it back in pieces, with me in it. Come out with me and I'll show you where.
    >>  ............................................
    pt  Levou um barco e devolveu em pedaços, comigo dentro. Venha comigo e eu te mostro onde.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.fisherman.more/2
    en  The river's lower. I'd rather tell you than the mayor — you'd actually listen to the number.
    >>  ............................................
    pt  O rio está mais baixo. Prefiro contar a você que ao prefeito — você escutaria o número.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.fisherman.more/1
    en  It took a boat with me in it. I don't tell that one often and I've not told it well now.
    >>  ............................................
    pt  Levou um barco comigo dentro. Não conto essa sempre e não contei bem agora.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.fisherman.more/2
    en  The river's lower every year. I don't say so, because people worry, and worry doesn't raise a river.
    >>  ............................................
    pt  O rio está mais baixo todo ano. Eu não digo, porque as pessoas se preocupam, e preocupação não levanta rio.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.fisherman.more/1
    en  Took a boat and gave it back in pieces. I was in it for part of that.
    >>  ............................................
    pt  Levou um barco e devolveu em pedaços. Eu estava dentro em parte disso.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.fisherman.more/2
    en  The river's lower than when I was young. I'm the only one counting and nobody has asked me the number.
    >>  ............................................
    pt  O rio está mais baixo do que quando eu era jovem. Sou o único contando e ninguém me perguntou o número.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.fisherman.more/1
    en  Took a boat and gave it back in pieces. I was in it for part of that.
    >>  ............................................
    pt  Levou um barco e devolveu em pedaços. Eu estava dentro em parte disso.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.fisherman.more/2
    en  The river's lower than when I was young. I'm the only one counting and nobody has asked me the number.
    >>  ............................................
    pt  O rio está mais baixo do que quando eu era jovem. Sou o único contando e ninguém me perguntou o número.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.fisherman.more/1
    en  It took a boat. Gave it back in pieces over about three days. I was in it for the first hour.
    >>  ............................................
    pt  Levou um barco. Devolveu em pedaços ao longo de uns três dias. Eu estava dentro na primeira hora.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.fisherman.more/2
    en  Lower than when I was young. About a hand's width a decade. I've marked the stone.
    >>  ............................................
    pt  Mais baixo do que quando eu era jovem. Uns quatro dedos por década. Eu marquei a pedra.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.fisherman.more/1
    en  Took a boat once. Gave most of it back. Rivers do as they like and always have.
    >>  ............................................
    pt  Levou um barco uma vez. Devolveu quase todo. Rios fazem o que querem e sempre fizeram.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.fisherman.more/2
    en  Lower than it was. Rivers rise and fall over lifetimes; I've only had the one to watch it in.
    >>  ............................................
    pt  Mais baixo do que era. Rios sobem e descem ao longo de vidas; eu só tive uma pra observar.
    >>  ............................................
  odd.dialogue.conversations.work.prof.fisherman.more/1
    en  It took a boat. Gave it back in pieces over about three days. I was in it for the first hour.
    >>  ............................................
    pt  Levou um barco. Devolveu em pedaços ao longo de uns três dias. Eu estava dentro na primeira hora.
    >>  ............................................
  odd.dialogue.conversations.work.prof.fisherman.more/2
    en  Lower than when I was young. About a hand's width a decade. I've marked the stone.
    >>  ............................................
    pt  Mais baixo do que quando eu era jovem. Uns quatro dedos por década. Eu marquei a pedra.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.fisherman.more/1
    en  Took a boat once. Gave most of it back. Rivers do as they like and always have.
    >>  ............................................
    pt  Levou um barco uma vez. Devolveu quase todo. Rios fazem o que querem e sempre fizeram.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.fisherman.more/2
    en  Lower than it was. Rivers rise and fall over lifetimes; I've only had the one to watch it in.
    >>  ............................................
    pt  Mais baixo do que era. Rios sobem e descem ao longo de vidas; eu só tive uma pra observar.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.fisherman.more/1
    en  It took a boat and gave it back in pieces! I was in it for part of that, which I do mention often.
    >>  ............................................
    pt  Levou um barco e devolveu em pedaços! Eu estava dentro em parte disso, o que eu menciono bastante.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.fisherman.more/2
    en  The river's lower than it was. Nobody wants to hear it, so I say it to the fish instead.
    >>  ............................................
    pt  O rio está mais baixo. Ninguém quer ouvir, então eu digo pros peixes.
    >>  ............................................
  playful.dialogue.conversations.work.prof.fisherman.more/1
    en  It took a boat and gave it back in pieces! I was in it for part of that, which I do mention often.
    >>  ............................................
    pt  Levou um barco e devolveu em pedaços! Eu estava dentro em parte disso, o que eu menciono bastante.
    >>  ............................................
  playful.dialogue.conversations.work.prof.fisherman.more/2
    en  The river's lower than it was. Nobody wants to hear it, so I say it to the fish instead.
    >>  ............................................
    pt  O rio está mais baixo. Ninguém quer ouvir, então eu digo pros peixes.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.fisherman.more/1
    en  Took a boat once. Gave most of it back. Rivers do as they like and always have.
    >>  ............................................
    pt  Levou um barco uma vez. Devolveu quase todo. Rios fazem o que querem e sempre fizeram.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.fisherman.more/2
    en  Lower than it was. Rivers rise and fall over lifetimes; I've only had the one to watch it in.
    >>  ............................................
    pt  Mais baixo do que era. Rios sobem e descem ao longo de vidas; eu só tive uma pra observar.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.fisherman.more/1
    en  It took a boat with me in it. I don't tell that one often and I've not told it well now.
    >>  ............................................
    pt  Levou um barco comigo dentro. Não conto essa sempre e não contei bem agora.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.fisherman.more/2
    en  The river's lower every year. I don't say so, because people worry, and worry doesn't raise a river.
    >>  ............................................
    pt  O rio está mais baixo todo ano. Eu não digo, porque as pessoas se preocupam, e preocupação não levanta rio.
    >>  ............................................
  shy.dialogue.conversations.work.prof.fisherman.more/1
    en  It took a boat. Gave it back in pieces over about three days. I was in it for the first hour.
    >>  ............................................
    pt  Levou um barco. Devolveu em pedaços ao longo de uns três dias. Eu estava dentro na primeira hora.
    >>  ............................................
  shy.dialogue.conversations.work.prof.fisherman.more/2
    en  Lower than when I was young. About a hand's width a decade. I've marked the stone.
    >>  ............................................
    pt  Mais baixo do que quando eu era jovem. Uns quatro dedos por década. Eu marquei a pedra.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.fisherman.more/1
    en  It took a boat and gave it back in pieces! I was in it for part of that, which I do mention often.
    >>  ............................................
    pt  Levou um barco e devolveu em pedaços! Eu estava dentro em parte disso, o que eu menciono bastante.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.fisherman.more/2
    en  The river's lower than it was. Nobody wants to hear it, so I say it to the fish instead.
    >>  ............................................
    pt  O rio está mais baixo. Ninguém quer ouvir, então eu digo pros peixes.
    >>  ............................................
  witty.dialogue.conversations.work.prof.fisherman.more/1
    en  It took a boat and gave it back in pieces! I was in it for part of that, which I do mention often.
    >>  ............................................
    pt  Levou um barco e devolveu em pedaços! Eu estava dentro em parte disso, o que eu menciono bastante.
    >>  ............................................
  witty.dialogue.conversations.work.prof.fisherman.more/2
    en  The river's lower than it was. Nobody wants to hear it, so I say it to the fish instead.
    >>  ............................................
    pt  O rio está mais baixo. Ninguém quer ouvir, então eu digo pros peixes.
    >>  ............................................
```

</details>


### Button `leave` — "Good fishing."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.fisherman.challenge.landed`, `work.fisherman.challenge.stung`, `work.fisherman.craft.admire`, `work.fisherman.craft.ask_teach`, `work.fisherman.craft.ask_which_half`, `work.fisherman.future.ask_go`, `work.fisherman.future.ask_hands`, `work.fisherman.future.encourage`, `work.fisherman.hard`, `work.fisherman.risk.ask_rule`, `work.fisherman.risk.ask_twice`, `work.fisherman.risk.concern`, `work.fisherman.task.ask_corner`, `work.fisherman.task.offer_hands`, `work.fisherman.task.stay_quiet`, `work.fisherman.value`, `work.fisherman.village.ask_children`, `work.fisherman.village.ask_stopped`, `work.fisherman.village.say_credit` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.followup.leave   [13 chars]
    en  Good fishing.
    >>  ............................................
    pt  Boa pescaria.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.leave
WHO    VILLAGER — what the player reads after pressing "Good fishing."
       spoken on: conversations.topic.work.fisherman.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.left`: the villager accepts. Subject `work.fisherman.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fisherman.bad_morning.succeeded.respond / leave; conversations.scene.work.fisherman.empty_water.active.respond / leave; conversations.scene.work.fisherman.empty_water.succeeded.respond / leave; conversations.scene.work.fisherman.followup / leave; conversations.scene.work.fisherman.torn_gear.blocked.respond / leave; conversations.scene.work.fisherman.torn_gear.succeeded.respond / leave; conversations.topic.work.fisherman.craft.respond / leave; conversations.topic.work.fisherman.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.fisherman.bad_morning.succeeded.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.fisherman.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.fisherman.future` — e.g. "There's a stretch of coast two days north I've never fished. I think about it more than is healthy."


```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.fisherman.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.fisherman.future.respond   [21 chars]
    en  That's the far water.
    >>  ............................................
    pt  É a água distante.
    >>  ............................................
```


### Button `ask_go` — "Why haven't you gone?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fisherman.future` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fisherman.future.ask_go` — accepted phrasings: "why haven't you gone"
  - the message must contain one of: `gone`, `travel`
  - scored words: `gone`(1.5), `why`(0.5), `travel`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.future.respond.ask_go
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.future.respond.ask_go   [21 chars]
    en  Why haven't you gone?
    >>  ............................................
    pt  Por que você não foi?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fisherman.future.ask_go`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fisherman.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the worst the water's done to you?" | "Good fishing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.future.ask_go
WHO    VILLAGER — what the player reads after pressing "Why haven't you gone?"
       spoken on: conversations.topic.work.fisherman.future.respond, button `ask_go`
       leaves the player on: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.future.ask_go`: the villager explains. Subject `work.fisherman.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fisherman.future.ask_go/1   [84 chars]
    en  Two days there and two back and the village eats on Friday. That's the whole answer.
    >>  ............................................
    pt  Dois dias pra ir e dois pra voltar e o vilarejo come na sexta. É toda a resposta.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.future.ask_go/2   [85 chars]
    en  Because I'd have to want it more than I want the dock, and I've not managed that yet.
    >>  ............................................
    pt  Porque eu teria que querer mais que a doca, e ainda não consegui.
    >>  ............................................
```


### Button `encourage` — "Go in the spring. Friday will survive one week."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.fisherman.future` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fisherman.future.encourage` — accepted phrasings: "go in the spring. friday will survive one week"
  - the message must contain one of: `spring`, `survive`
  - scored words: `spring`(1.5), `survive`(1.2), `go`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.future.respond.encourage   [47 chars]
    en  Go in the spring. Friday will survive one week.
    >>  ............................................
    pt  Vá na primavera. A sexta sobrevive uma semana.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.fisherman.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.fisherman.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.fisherman.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the worst the water's done to you?" | "Good fishing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.future.encourage
WHO    VILLAGER — what the player reads after pressing "Go in the spring. Friday will survive one week."
       spoken on: conversations.topic.work.fisherman.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.future.encourage`: the villager accepts. Subject `work.fisherman.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fisherman.future.encourage/1   [77 chars]
    en  ...Spring. You've put a season on it, and now it's a plan and not a daydream.
    >>  ............................................
    pt  ...Primavera. Você pôs uma estação nisso, e agora é plano e não devaneio.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.future.encourage/2   [64 chars]
    en  One week. Ha. Say that again where the innkeeper can hear, %1$s.
    >>  ............................................
    pt  Uma semana. Ha. Diga isso de novo onde o estalajadeiro ouça, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring. A season on it makes it real, and real things can be taken away.
    >>  ............................................
    pt  ...Primavera. Uma estação torna real, e coisas reais podem ser tiradas.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week. I've wanted it so long that a week sounds like a trick.
    >>  ............................................
    pt  Uma semana. Quis isso por tanto tempo que uma semana soa como truque.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring. Nineteen years of daydream and it takes a season to make it a plan.
    >>  ............................................
    pt  ...Primavera. Dezenove anos de devaneio e basta uma estação pra virar plano.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week. I've lost more than a week to worse and never noticed.
    >>  ............................................
    pt  Uma semana. Já perdi mais que uma semana com coisa pior sem notar.
    >>  ............................................
  confident.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring. You've put a season on it, and now it's a plan and not a daydream.
    >>  ............................................
    pt  ...Primavera. Você pôs uma estação nisso, e agora é plano e não devaneio.
    >>  ............................................
  confident.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week. Ha. Say that again where the innkeeper can hear.
    >>  ............................................
    pt  Uma semana. Ha. Diga de novo onde o estalajadeiro possa ouvir.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring. You've put a season on it, and now it's a plan and not a daydream.
    >>  ............................................
    pt  ...Primavera. Você pôs uma estação nisso, e agora é plano e não devaneio.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week. Ha. Say that again where the innkeeper can hear.
    >>  ............................................
    pt  Uma semana. Ha. Diga de novo onde o estalajadeiro possa ouvir.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring, %1$s. You've put a season on it and it stopped being a daydream.
    >>  ............................................
    pt  ...Primavera, %1$s. Você pôs uma estação e deixou de ser devaneio.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week. Say that again where the innkeeper can hear, and I'll owe you.
    >>  ............................................
    pt  Uma semana. Diga de novo onde o estalajadeiro ouça, e eu fico te devendo.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring, %1$s. You've put a season on it and it stopped being a daydream.
    >>  ............................................
    pt  ...Primavera, %1$s. Você pôs uma estação e deixou de ser devaneio.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week. Say that again where the innkeeper can hear, and I'll owe you.
    >>  ............................................
    pt  Uma semana. Diga de novo onde o estalajadeiro ouça, e eu fico te devendo.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring, %1$s. You've put a season on it and it stopped being a daydream.
    >>  ............................................
    pt  ...Primavera, %1$s. Você pôs uma estação e deixou de ser devaneio.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week. Say that again where the innkeeper can hear, and I'll owe you.
    >>  ............................................
    pt  Uma semana. Diga de novo onde o estalajadeiro ouça, e eu fico te devendo.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring. A season on it makes it real, and real things can be taken away.
    >>  ............................................
    pt  ...Primavera. Uma estação torna real, e coisas reais podem ser tiradas.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week. I've wanted it so long that a week sounds like a trick.
    >>  ............................................
    pt  Uma semana. Quis isso por tanto tempo que uma semana soa como truque.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring. You've put a season on it, and now it's a plan and not a daydream.
    >>  ............................................
    pt  ...Primavera. Você pôs uma estação nisso, e agora é plano e não devaneio.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week. Ha. Say that again where the innkeeper can hear.
    >>  ............................................
    pt  Uma semana. Ha. Diga de novo onde o estalajadeiro possa ouvir.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring. You've put a season on it, and now it's a plan and not a daydream.
    >>  ............................................
    pt  ...Primavera. Você pôs uma estação nisso, e agora é plano e não devaneio.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week. Ha. Say that again where the innkeeper can hear.
    >>  ............................................
    pt  Uma semana. Ha. Diga de novo onde o estalajadeiro possa ouvir.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring. A season makes it a plan.
    >>  ............................................
    pt  ...Primavera. Uma estação faz virar plano.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week. Say that near the innkeeper.
    >>  ............................................
    pt  Uma semana. Diga isso perto do estalajadeiro.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring. Nineteen years of daydream and it takes a season to make it a plan.
    >>  ............................................
    pt  ...Primavera. Dezenove anos de devaneio e basta uma estação pra virar plano.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week. I've lost more than a week to worse and never noticed.
    >>  ............................................
    pt  Uma semana. Já perdi mais que uma semana com coisa pior sem notar.
    >>  ............................................
  odd.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring. A season makes it a plan.
    >>  ............................................
    pt  ...Primavera. Uma estação faz virar plano.
    >>  ............................................
  odd.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week. Say that near the innkeeper.
    >>  ............................................
    pt  Uma semana. Diga isso perto do estalajadeiro.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring. Nineteen years of daydream and it takes a season to make it a plan.
    >>  ............................................
    pt  ...Primavera. Dezenove anos de devaneio e basta uma estação pra virar plano.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week. I've lost more than a week to worse and never noticed.
    >>  ............................................
    pt  Uma semana. Já perdi mais que uma semana com coisa pior sem notar.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring! You've put a season on it and turned a daydream into a plan.
    >>  ............................................
    pt  ...Primavera! Você pôs uma estação e virou devaneio em plano.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week — ha! Say that again where the innkeeper can hear you.
    >>  ............................................
    pt  Uma semana — ha! Diga de novo onde o estalajadeiro possa ouvir.
    >>  ............................................
  playful.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring! You've put a season on it and turned a daydream into a plan.
    >>  ............................................
    pt  ...Primavera! Você pôs uma estação e virou devaneio em plano.
    >>  ............................................
  playful.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week — ha! Say that again where the innkeeper can hear you.
    >>  ............................................
    pt  Uma semana — ha! Diga de novo onde o estalajadeiro possa ouvir.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring. Nineteen years of daydream and it takes a season to make it a plan.
    >>  ............................................
    pt  ...Primavera. Dezenove anos de devaneio e basta uma estação pra virar plano.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week. I've lost more than a week to worse and never noticed.
    >>  ............................................
    pt  Uma semana. Já perdi mais que uma semana com coisa pior sem notar.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring. A season on it makes it real, and real things can be taken away.
    >>  ............................................
    pt  ...Primavera. Uma estação torna real, e coisas reais podem ser tiradas.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week. I've wanted it so long that a week sounds like a trick.
    >>  ............................................
    pt  Uma semana. Quis isso por tanto tempo que uma semana soa como truque.
    >>  ............................................
  shy.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring. A season makes it a plan.
    >>  ............................................
    pt  ...Primavera. Uma estação faz virar plano.
    >>  ............................................
  shy.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week. Say that near the innkeeper.
    >>  ............................................
    pt  Uma semana. Diga isso perto do estalajadeiro.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring! You've put a season on it and turned a daydream into a plan.
    >>  ............................................
    pt  ...Primavera! Você pôs uma estação e virou devaneio em plano.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week — ha! Say that again where the innkeeper can hear you.
    >>  ............................................
    pt  Uma semana — ha! Diga de novo onde o estalajadeiro possa ouvir.
    >>  ............................................
  witty.dialogue.conversations.work.prof.fisherman.future.encourage/1
    en  ...Spring! You've put a season on it and turned a daydream into a plan.
    >>  ............................................
    pt  ...Primavera! Você pôs uma estação e virou devaneio em plano.
    >>  ............................................
  witty.dialogue.conversations.work.prof.fisherman.future.encourage/2
    en  One week — ha! Say that again where the innkeeper can hear you.
    >>  ............................................
    pt  Uma semana — ha! Diga de novo onde o estalajadeiro possa ouvir.
    >>  ............................................
```

</details>


### Button `ask_hands` — "How will you know when it's time?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fisherman.future` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fisherman.future.ask_hands` — accepted phrasings: "how will you know when it's time"
  - the message must contain one of: `time`, `know`, `hands`
  - scored words: `time`(1.0), `know`(1.0), `hands`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.future.respond.ask_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.future.respond.ask_hands   [33 chars]
    en  How will you know when it's time?
    >>  ............................................
    pt  Como você vai saber que chegou a hora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fisherman.future.ask_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fisherman.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the worst the water's done to you?" | "Good fishing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.future.ask_hands
WHO    VILLAGER — what the player reads after pressing "How will you know when it's time?"
       spoken on: conversations.topic.work.fisherman.future.respond, button `ask_hands`
       leaves the player on: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.future.ask_hands`: the villager explains. Subject `work.fisherman.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fisherman.future.ask_hands/1   [73 chars]
    en  The knots. When the knots take twice as long, that's the letter arriving.
    >>  ............................................
    pt  Os nós. Quando os nós levarem o dobro do tempo, é a carta chegando.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.future.ask_hands/2   [70 chars]
    en  I won't. That's what frightens me, and I'd thank you not to repeat it.
    >>  ............................................
    pt  Não vou saber. É isso que me assusta, e eu agradeceria se você não repetisse.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the water."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.fisherman.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.future.respond.leave   [28 chars]
    en  I'll leave you to the water.
    >>  ............................................
    pt  Vou deixar você com a água.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the water."
       spoken on: conversations.topic.work.fisherman.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.left`: the villager accepts. Subject `work.fisherman.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fisherman.bad_morning.succeeded.respond / leave; conversations.scene.work.fisherman.empty_water.active.respond / leave; conversations.scene.work.fisherman.empty_water.succeeded.respond / leave; conversations.scene.work.fisherman.followup / leave; conversations.scene.work.fisherman.torn_gear.blocked.respond / leave; conversations.scene.work.fisherman.torn_gear.succeeded.respond / leave; conversations.topic.work.fisherman.craft.respond / leave; conversations.topic.work.fisherman.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.fisherman.bad_morning.succeeded.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.fisherman.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.fisherman` — e.g. "The fish and I have an understanding: I wait, they mock me, occasionally one apologizes into the net."


```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.fisherman.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.fisherman.respond   [34 chars]
    en  That's the dock and me, most days.
    >>  ............................................
    pt  É a doca e eu, quase todo dia.
    >>  ............................................
```


### Button `ask_hard` — "What does the heron actually take?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fisherman.identity` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fisherman.hard` — accepted phrasings: "what does the heron actually take"
  - the message must contain one of: `heron`, `take`, `steal`
  - scored words: `heron`(1.5), `take`(1.0), `steal`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.respond.ask_hard   [34 chars]
    en  What does the heron actually take?
    >>  ............................................
    pt  O que a garça leva de verdade?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.fisherman.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fisherman.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the worst the water's done to you?" | "Good fishing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.hard
WHO    VILLAGER — what the player reads after pressing "What does the heron actually take?"
       spoken on: conversations.topic.work.fisherman.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.hard`: the villager explains. Subject `work.fisherman.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fisherman.followup / ask_more
```

> Written out in full under **`conversations.scene.work.fisherman.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "Half the village's supper comes off that dock."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.fisherman.identity` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fisherman.value` — accepted phrasings: "half the village's supper comes off that dock"
  - the message must contain one of: `supper`, `dock`, `catch`
  - scored words: `supper`(1.5), `dock`(1.2), `catch`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.respond.value   [46 chars]
    en  Half the village's supper comes off that dock.
    >>  ............................................
    pt  Metade do jantar do vilarejo sai daquela doca.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.fisherman.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.fisherman.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fisherman.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the worst the water's done to you?" | "Good fishing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.value
WHO    VILLAGER — what the player reads after pressing "Half the village's supper comes off that dock."
       spoken on: conversations.topic.work.fisherman.respond, button `value`
       leaves the player on: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.value`: the villager accepts. Subject `work.fisherman.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fisherman.value/1   [54 chars]
    en  Aye, and none of them are up at four to see it happen.
    >>  ............................................
    pt  É, e nenhum deles está de pé às quatro pra ver isso acontecer.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.value/2   [81 chars]
    en  It does. And on the days it doesn't, they notice me for the first time all month.
    >>  ............................................
    pt  Sai. E nos dias que não sai, eles reparam em mim pela primeira vez no mês.
    >>  ............................................
```


### Button `challenge` — "Sitting still isn't a trade."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.fisherman.identity` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fisherman.challenge` — accepted phrasings: "sitting still isn't a trade"
  - the message must contain one of: `sitting`, `still`
  - scored words: `sitting`(1.5), `still`(1.2), `trade`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.respond.challenge   [28 chars]
    en  Sitting still isn't a trade.
    >>  ............................................
    pt  Ficar sentado não é ofício.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.fisherman.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.fisherman.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fisherman.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the worst the water's done to you?" | "Good fishing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.challenge.landed
WHO    VILLAGER — what the player reads after pressing "Sitting still isn't a trade."
       spoken on: conversations.topic.work.fisherman.respond, button `challenge`
       leaves the player on: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.challenge.landed`: the villager resists. Subject `work.fisherman.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fisherman.challenge.landed/1   [63 chars]
    en  Ha! No. Sitting still and being right about where is the trade.
    >>  ............................................
    pt  Ha! Não. Ficar sentado e estar certo sobre o lugar é que é o ofício.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.challenge.landed/2   [75 chars]
    en  Try it for a dawn and tell me it isn't work, %1$s. Bring your own patience.
    >>  ............................................
    pt  Tente por um amanhecer e me diga que não é trabalho, %1$s. Traga sua própria paciência.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.fisherman.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.fisherman.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fisherman.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the worst the water's done to you?" | "Good fishing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.challenge.stung
WHO    VILLAGER — what the player reads after pressing "Sitting still isn't a trade."
       spoken on: conversations.topic.work.fisherman.respond, button `challenge`
       leaves the player on: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.challenge.stung`: the villager resists. Subject `work.fisherman.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fisherman.challenge.stung/1   [53 chars]
    en  ...Right. Well. Enjoy your supper, whoever caught it.
    >>  ............................................
    pt  ...Certo. Bom. Aproveite seu jantar, seja quem for que pescou.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.challenge.stung/2   [66 chars]
    en  Sitting still. Aye. That's what it looks like from the road, %1$s.
    >>  ............................................
    pt  Ficar sentado. É. É o que parece da estrada, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the water."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.fisherman.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.respond.leave   [28 chars]
    en  I'll leave you to the water.
    >>  ............................................
    pt  Vou deixar você com a água.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the water."
       spoken on: conversations.topic.work.fisherman.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.left`: the villager accepts. Subject `work.fisherman.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fisherman.bad_morning.succeeded.respond / leave; conversations.scene.work.fisherman.empty_water.active.respond / leave; conversations.scene.work.fisherman.empty_water.succeeded.respond / leave; conversations.scene.work.fisherman.followup / leave; conversations.scene.work.fisherman.torn_gear.blocked.respond / leave; conversations.scene.work.fisherman.torn_gear.succeeded.respond / leave; conversations.topic.work.fisherman.craft.respond / leave; conversations.topic.work.fisherman.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.fisherman.bad_morning.succeeded.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.fisherman.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.fisherman.risk` — e.g. "The river's shallow and friendly right up until it decides otherwise. I've been in it twice."


```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.fisherman.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.fisherman.risk.respond   [23 chars]
    en  That's what's under it.
    >>  ............................................
    pt  É o que está por baixo.
    >>  ............................................
```


### Button `ask_twice` — "What happened the second time?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fisherman.risk` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fisherman.risk.ask_twice` — accepted phrasings: "what happened the second time"
  - the message must contain one of: `second`, `happened`
  - scored words: `second`(1.5), `happened`(1.0), `time`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.risk.respond.ask_twice
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.risk.respond.ask_twice   [30 chars]
    en  What happened the second time?
    >>  ............................................
    pt  O que aconteceu na segunda vez?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fisherman.risk.ask_twice`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fisherman.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the worst the water's done to you?" | "Good fishing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.risk.ask_twice
WHO    VILLAGER — what the player reads after pressing "What happened the second time?"
       spoken on: conversations.topic.work.fisherman.risk.respond, button `ask_twice`
       leaves the player on: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.risk.ask_twice`: the villager explains. Subject `work.fisherman.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fisherman.risk.ask_twice/1   [81 chars]
    en  I got out slower. That's the only detail that matters and I think about it often.
    >>  ............................................
    pt  Saí mais devagar. É o único detalhe que importa e eu penso nisso com frequência.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.risk.ask_twice/2   [74 chars]
    en  Nothing worth telling. That's a lie, but it's the version I've settled on.
    >>  ............................................
    pt  Nada que valha contar. É mentira, mas é a versão com que eu fiquei.
    >>  ............................................
```


### Button `concern` — "You go out alone, though."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.fisherman.risk` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fisherman.risk.concern` — accepted phrasings: "you go out alone, though"
  - the message must contain one of: `alone`, `yourself`, `nobody`
  - scored words: `alone`(1.5), `yourself`(1.2), `nobody`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.risk.respond.concern
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.risk.respond.concern   [25 chars]
    en  You go out alone, though.
    >>  ............................................
    pt  Mas você vai sozinho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.fisherman.risk.concern`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.fisherman.risk.concern`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fisherman.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the worst the water's done to you?" | "Good fishing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.risk.concern
WHO    VILLAGER — what the player reads after pressing "You go out alone, though."
       spoken on: conversations.topic.work.fisherman.risk.respond, button `concern`
       leaves the player on: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.risk.concern`: the villager accepts. Subject `work.fisherman.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fisherman.risk.concern/1   [56 chars]
    en  ...I do. I've been told. I've told myself, come to that.
    >>  ............................................
    pt  ...Vou. Já me disseram. E eu já disse a mim mesmo.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.risk.concern/2   [80 chars]
    en  Alone, aye. It's the good part and the bad part and they're the same part, %1$s.
    >>  ............................................
    pt  Sozinho, sim. É a parte boa e a parte ruim e são a mesma parte, %1$s.
    >>  ............................................
```


### Button `ask_rule` — "Do you have a rule about it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fisherman.risk` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fisherman.risk.ask_rule` — accepted phrasings: "do you have a rule about it"
  - the message must contain one of: `rule`, `safety`, `careful`
  - scored words: `rule`(1.5), `safety`(1.2), `careful`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.risk.respond.ask_rule
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.risk.respond.ask_rule   [28 chars]
    en  Do you have a rule about it?
    >>  ............................................
    pt  Você tem uma regra pra isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fisherman.risk.ask_rule`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fisherman.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the worst the water's done to you?" | "Good fishing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.risk.ask_rule
WHO    VILLAGER — what the player reads after pressing "Do you have a rule about it?"
       spoken on: conversations.topic.work.fisherman.risk.respond, button `ask_rule`
       leaves the player on: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.risk.ask_rule`: the villager explains. Subject `work.fisherman.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fisherman.risk.ask_rule/1   [84 chars]
    en  Never past the second bend after rain. I broke it once and that was the second time.
    >>  ............................................
    pt  Nunca depois da segunda curva após chuva. Quebrei uma vez e foi a segunda vez.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.risk.ask_rule/2   [59 chars]
    en  Dry clothes on the bank, always. It sounds small. It isn't.
    >>  ............................................
    pt  Roupa seca na margem, sempre. Parece pouco. Não é.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the water."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.fisherman.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.risk.respond.leave   [28 chars]
    en  I'll leave you to the water.
    >>  ............................................
    pt  Vou deixar você com a água.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the water."
       spoken on: conversations.topic.work.fisherman.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.left`: the villager accepts. Subject `work.fisherman.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fisherman.bad_morning.succeeded.respond / leave; conversations.scene.work.fisherman.empty_water.active.respond / leave; conversations.scene.work.fisherman.empty_water.succeeded.respond / leave; conversations.scene.work.fisherman.followup / leave; conversations.scene.work.fisherman.torn_gear.blocked.respond / leave; conversations.scene.work.fisherman.torn_gear.succeeded.respond / leave; conversations.topic.work.fisherman.craft.respond / leave; conversations.topic.work.fisherman.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.fisherman.bad_morning.succeeded.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.fisherman.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.fisherman.task` — e.g. "Mending net today. Third time this month, same corner, and I've started taking it personally."


```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.fisherman.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.fisherman.task.respond   [21 chars]
    en  That's the afternoon.
    >>  ............................................
    pt  É a tarde.
    >>  ............................................
```


### Button `ask_corner` — "Why always that corner?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fisherman.task` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fisherman.task.ask_corner` — accepted phrasings: "why always that corner"
  - the message must contain one of: `corner`, `always`
  - scored words: `corner`(1.5), `always`(1.0), `why`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.task.respond.ask_corner
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.task.respond.ask_corner   [23 chars]
    en  Why always that corner?
    >>  ............................................
    pt  Por que sempre aquele canto?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fisherman.task.ask_corner`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fisherman.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the worst the water's done to you?" | "Good fishing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.task.ask_corner
WHO    VILLAGER — what the player reads after pressing "Why always that corner?"
       spoken on: conversations.topic.work.fisherman.task.respond, button `ask_corner`
       leaves the player on: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.task.ask_corner`: the villager explains. Subject `work.fisherman.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fisherman.task.ask_corner/1   [75 chars]
    en  Something with teeth, or something with a hull. I've theories and no proof.
    >>  ............................................
    pt  Algo com dentes, ou algo com casco. Tenho teorias e nenhuma prova.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.task.ask_corner/2   [84 chars]
    en  Because that's where the weight goes when the net's full. It's my own fault, really.
    >>  ............................................
    pt  Porque é onde o peso vai quando a rede está cheia. A culpa é minha, na verdade.
    >>  ............................................
```


### Button `offer_hands` — "Show me the knot and I'll do half."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.fisherman.task` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fisherman.task.offer_hands` — accepted phrasings: "show me the knot and i'll do half"
  - the message must contain one of: `knot`, `half`, `show`
  - scored words: `knot`(1.5), `half`(1.0), `show`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.task.respond.offer_hands   [34 chars]
    en  Show me the knot and I'll do half.
    >>  ............................................
    pt  Me mostre o nó e eu faço metade.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.fisherman.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.fisherman.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fisherman.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the worst the water's done to you?" | "Good fishing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "Show me the knot and I'll do half."
       spoken on: conversations.topic.work.fisherman.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.task.offer_hands`: the villager accepts. Subject `work.fisherman.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fisherman.task.offer_hands/1   [77 chars]
    en  ...Alright. Watch once, then do it wrong, then do it right. That's the order.
    >>  ............................................
    pt  ...Está bem. Olhe uma vez, erre, depois acerte. É essa a ordem.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.task.offer_hands/2   [73 chars]
    en  Half. Ha. You'll do three and hand it back, %1$s, and that's still three.
    >>  ............................................
    pt  Metade. Ha. Você vai fazer três e devolver, %1$s, e três já é três.
    >>  ............................................
```


### Button `stay_quiet` — "Then I'll stand here and say nothing."

*stance family `restraint` · tone `plain` · outcome `accepted` · answers the beat(s) `work.fisherman.task` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fisherman.task.stay_quiet` — accepted phrasings: "then i'll stand here and say nothing"
  - the message must contain one of: `nothing`, `quiet`
  - scored words: `nothing`(1.2), `quiet`(1.5), `stand`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.task.respond.stay_quiet
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.task.respond.stay_quiet   [37 chars]
    en  Then I'll stand here and say nothing.
    >>  ............................................
    pt  Então eu fico aqui e não digo nada.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.fisherman.task.stay_quiet`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fisherman.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the worst the water's done to you?" | "Good fishing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.task.stay_quiet
WHO    VILLAGER — what the player reads after pressing "Then I'll stand here and say nothing."
       spoken on: conversations.topic.work.fisherman.task.respond, button `stay_quiet`
       leaves the player on: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.task.stay_quiet`: the villager accepts. Subject `work.fisherman.task`, polarity `neutral`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fisherman.task.stay_quiet/1   [70 chars]
    en  Good. You're the first person all week to take that as an instruction.
    >>  ............................................
    pt  Bom. Você é a primeira pessoa na semana a levar isso como instrução.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.task.stay_quiet/2   [63 chars]
    en  That's the correct answer. Sit on the crate, mind, not the net.
    >>  ............................................
    pt  É a resposta certa. Mas sente no caixote, não na rede.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the water."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.fisherman.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.task.respond.leave   [28 chars]
    en  I'll leave you to the water.
    >>  ............................................
    pt  Vou deixar você com a água.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the water."
       spoken on: conversations.topic.work.fisherman.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.left`: the villager accepts. Subject `work.fisherman.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fisherman.bad_morning.succeeded.respond / leave; conversations.scene.work.fisherman.empty_water.active.respond / leave; conversations.scene.work.fisherman.empty_water.succeeded.respond / leave; conversations.scene.work.fisherman.followup / leave; conversations.scene.work.fisherman.torn_gear.blocked.respond / leave; conversations.scene.work.fisherman.torn_gear.succeeded.respond / leave; conversations.topic.work.fisherman.craft.respond / leave; conversations.topic.work.fisherman.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.fisherman.bad_morning.succeeded.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.fisherman.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.fisherman.village` — e.g. "Friday's stew has been my catch for nine years. The innkeeper takes the credit and I let him."


```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.fisherman.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.fisherman.village.respond   [21 chars]
    en  That's what it feeds.
    >>  ............................................
    pt  É o que isso alimenta.
    >>  ............................................
```


### Button `ask_children` — "Are you teaching them, then?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fisherman.village` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fisherman.village.ask_children` — accepted phrasings: "are you teaching them, then"
  - the message must contain one of: `children`, `teaching`
  - scored words: `children`(1.5), `teaching`(1.2), `them`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.village.respond.ask_children
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.village.respond.ask_children   [28 chars]
    en  Are you teaching them, then?
    >>  ............................................
    pt  Então você está ensinando a elas?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fisherman.village.ask_children`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fisherman.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the worst the water's done to you?" | "Good fishing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.village.ask_children
WHO    VILLAGER — what the player reads after pressing "Are you teaching them, then?"
       spoken on: conversations.topic.work.fisherman.village.respond, button `ask_children`
       leaves the player on: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.village.ask_children`: the villager explains. Subject `work.fisherman.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fisherman.village.ask_children/1   [87 chars]
    en  I am not. I'm standing near them while they learn, which is different and works better.
    >>  ............................................
    pt  Não estou. Estou ao lado enquanto elas aprendem, que é diferente e funciona melhor.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.village.ask_children/2   [70 chars]
    en  One of them'll have this dock in twenty years. I've not told her that.
    >>  ............................................
    pt  Uma delas vai ter esta doca em vinte anos. Não contei a ela.
    >>  ............................................
```


### Button `say_credit` — "You should take the credit for Friday."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.fisherman.village` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fisherman.village.say_credit` — accepted phrasings: "you should take the credit for friday"
  - the message must contain one of: `credit`, `friday`, `deserve`
  - scored words: `credit`(1.5), `friday`(1.2), `deserve`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.village.respond.say_credit
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.village.respond.say_credit   [38 chars]
    en  You should take the credit for Friday.
    >>  ............................................
    pt  Você devia levar o crédito pela sexta.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.fisherman.village.say_credit`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.fisherman.village.say_credit`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fisherman.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the worst the water's done to you?" | "Good fishing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.village.say_credit
WHO    VILLAGER — what the player reads after pressing "You should take the credit for Friday."
       spoken on: conversations.topic.work.fisherman.village.respond, button `say_credit`
       leaves the player on: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.village.say_credit`: the villager accepts. Subject `work.fisherman.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fisherman.village.say_credit/1   [64 chars]
    en  And say what? 'I sat down and waited'? It doesn't make a speech.
    >>  ............................................
    pt  E dizer o quê? 'Sentei e esperei'? Não vira discurso.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.village.say_credit/2   [54 chars]
    en  ...Maybe once. Just to see the innkeeper's face, %1$s.
    >>  ............................................
    pt  ...Talvez uma vez. Só pra ver a cara do estalajadeiro, %1$s.
    >>  ............................................
```


### Button `ask_stopped` — "What happens on a week you catch nothing?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.fisherman.village` · offered only once the villager has actually said `work:fisherman`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.fisherman.village.ask_stopped` — accepted phrasings: "what happens on a week you catch nothing"
  - the message must contain one of: `nothing`, `week`, `fail`
  - scored words: `nothing`(1.5), `week`(1.0), `fail`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.village.respond.ask_stopped
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.village.respond.ask_stopped   [41 chars]
    en  What happens on a week you catch nothing?
    >>  ............................................
    pt  O que acontece numa semana sem pesca?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.fisherman.village.ask_stopped`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.fisherman.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the worst the water's done to you?" | "Good fishing."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.village.ask_stopped
WHO    VILLAGER — what the player reads after pressing "What happens on a week you catch nothing?"
       spoken on: conversations.topic.work.fisherman.village.respond, button `ask_stopped`
       leaves the player on: conversations.topic.work.fisherman.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.village.ask_stopped`: the villager explains. Subject `work.fisherman.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:fisherman` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.fisherman.village.ask_stopped/1   [76 chars]
    en  Then it's bread and turnips at the inn and everyone is very polite about it.
    >>  ............................................
    pt  Aí é pão e nabo na estalagem e todo mundo é muito educado sobre isso.
    >>  ............................................
  dialogue.conversations.work.prof.fisherman.village.ask_stopped/2   [73 chars]
    en  It's happened three times. Each one I remember better than any good week.
    >>  ............................................
    pt  Aconteceu três vezes. Lembro de cada uma melhor que de qualquer semana boa.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the water."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.fisherman.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.fisherman.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.fisherman.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.fisherman.village.respond.leave   [28 chars]
    en  I'll leave you to the water.
    >>  ............................................
    pt  Vou deixar você com a água.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.fisherman.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the water."
       spoken on: conversations.topic.work.fisherman.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.fisherman.left`: the villager accepts. Subject `work.fisherman.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.fisherman.bad_morning.succeeded.respond / leave; conversations.scene.work.fisherman.empty_water.active.respond / leave; conversations.scene.work.fisherman.empty_water.succeeded.respond / leave; conversations.scene.work.fisherman.followup / leave; conversations.scene.work.fisherman.torn_gear.blocked.respond / leave; conversations.scene.work.fisherman.torn_gear.succeeded.respond / leave; conversations.topic.work.fisherman.craft.respond / leave; conversations.topic.work.fisherman.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.fisherman.bad_morning.succeeded.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

