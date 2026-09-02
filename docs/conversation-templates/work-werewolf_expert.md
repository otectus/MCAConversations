# Work talk with a werewolf expert

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.werewolf_expert.a_confidence.active.respond`](#conversations-scene-work-werewolf-expert-a-confidence-active-respond)
- [`conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond`](#conversations-scene-work-werewolf-expert-a-confidence-succeeded-respond)
- [`conversations.scene.work.werewolf_expert.followup`](#conversations-scene-work-werewolf-expert-followup)
- [`conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond`](#conversations-scene-work-werewolf-expert-pressed-for-a-name-blocked-respond)
- [`conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond`](#conversations-scene-work-werewolf-expert-pressed-for-a-name-succeeded-respond)
- [`conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond`](#conversations-scene-work-werewolf-expert-unfunded-precautions-blocked-respond)
- [`conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond`](#conversations-scene-work-werewolf-expert-unfunded-precautions-succeeded-respond)
- [`conversations.topic.work.werewolf_expert.craft.respond`](#conversations-topic-work-werewolf-expert-craft-respond)
- [`conversations.topic.work.werewolf_expert.followup`](#conversations-topic-work-werewolf-expert-followup)
- [`conversations.topic.work.werewolf_expert.future.respond`](#conversations-topic-work-werewolf-expert-future-respond)
- [`conversations.topic.work.werewolf_expert.respond`](#conversations-topic-work-werewolf-expert-respond)
- [`conversations.topic.work.werewolf_expert.risk.respond`](#conversations-topic-work-werewolf-expert-risk-respond)
- [`conversations.topic.work.werewolf_expert.task.respond`](#conversations-topic-work-werewolf-expert-task-respond)
- [`conversations.topic.work.werewolf_expert.village.respond`](#conversations-topic-work-werewolf-expert-village-respond)

---

## `conversations.scene.work.werewolf_expert.a_confidence.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.werewolf_expert.a_confidence.active` — e.g. "There is %2$s, and that is the whole of what I am going to tell you or anybody, ever."


```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.a_confidence.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.werewolf_expert.a_confidence.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.active.respond   [21 chars]
    en  Somebody came to you.
    >>  ............................................
    pt  Alguém te procurou.
    >>  ............................................
```


### Button `ask_about_the_help` — "What does the help look like?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.werewolf_expert.a_confidence.active` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.werewolf_expert.a_confidence.active.ask_about_the_help` — accepted phrasings: "what does the help look like"; "what does the help look like"; "what kind of help do you give"
  - the message must contain one of: `help`, `kind`
  - scored words: `help`(1.8), `kind`(1.8), `does`(0.8), `look`(0.8), `like`(0.8), `give`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.a_confidence.active.respond.ask_about_the_help
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.werewolf_expert.a_confidence.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.active.respond.ask_about_the_help   [29 chars]
    en  What does the help look like?
    >>  ............................................
    pt  Como é essa ajuda?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.werewolf_expert.confidence`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.werewolf_expert.a_confidence"}
- Then opens: `conversations.scene.work.werewolf_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a bad moon?" | "I'll leave you to the calendar."

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.a_confidence.active.explained
WHO    VILLAGER — what the player reads after pressing "What does the help look like?"
       spoken on: conversations.scene.work.werewolf_expert.a_confidence.active.respond, button `ask_about_the_help`
       leaves the player on: conversations.scene.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.a_confidence.active.explained`: the villager explains. Subject `work.werewolf_expert.confidence`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.active.explained/1   [131 chars]
    en  Dates, a room with a good door, and somebody who knows where they are. That is the entire arrangement and it has never once failed.
    >>  ............................................
    pt  Datas, um quarto com uma boa porta, e alguém que saiba onde estão. É o arranjo inteiro e nunca falhou uma vez.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.active.explained/2   [131 chars]
    en  Mostly it is logistics. Three nights a month, arranged four years in advance, and the arranging is what stops it being frightening.
    >>  ............................................
    pt  Na maior parte é logística. Três noites por mês, combinadas com quatro anos de antecedência, e é o combinar que impede de ser assustador.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.active.explained/3   [113 chars]
    en  And somebody to talk to on the fourth day, which is the part that is not logistics and is the part they came for.
    >>  ............................................
    pt  E alguém para conversar no quarto dia, que é a parte que não é logística e é a parte pela qual vieram.
    >>  ............................................
```


### Button `respect_the_confidence` — "That name is yours to keep."

*stance family `restraint` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.werewolf_expert.a_confidence.active` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.werewolf_expert.a_confidence.active.respect_the_confidence` — accepted phrasings: "that name is yours to keep"; "the name is yours to keep"; "keep the name to yourself"
  - the message must contain one of: `name`, `yours`, `yourself`
  - scored words: `name`(1.8), `yours`(1.8), `yourself`(1.8), `keep`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.a_confidence.active.respond.respect_the_confidence
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.werewolf_expert.a_confidence.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.active.respond.respect_the_confidence   [27 chars]
    en  That name is yours to keep.
    >>  ............................................
    pt  Esse nome é seu para guardar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `work.werewolf_expert.confidence.respected`, budget `deep`, replay policy `once`
- Does: disposition — trust +5, warmth +3  _(recorded under topic `work.werewolf_expert.confidence`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.werewolf_expert.a_confidence"}
- Then opens: `conversations.scene.work.werewolf_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a bad moon?" | "I'll leave you to the calendar."

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.a_confidence.active.steadied
WHO    VILLAGER — what the player reads after pressing "That name is yours to keep."
       spoken on: conversations.scene.work.werewolf_expert.a_confidence.active.respond, button `respect_the_confidence`
       leaves the player on: conversations.scene.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.a_confidence.active.steadied`: the villager accepts. Subject `work.werewolf_expert.confidence`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.active.steadied/1   [126 chars]
    en  Thank you. You are the fourth person this month to be told there is somebody, and the first not to spend ten minutes guessing.
    >>  ............................................
    pt  Obrigada. Você é a quarta pessoa neste mês a saber que existe alguém, e a primeira a não passar dez minutos adivinhando.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.active.steadied/2   [155 chars]
    en  It matters more than you think. If the guessing ever lands, that person leaves the village, and the arrangement that keeps everybody safe leaves with them.
    >>  ............................................
    pt  Importa mais do que você imagina. Se o palpite acertar, essa pessoa vai embora da vila, e o arranjo que mantém todo mundo seguro vai junto.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.active.steadied/3   [136 chars]
    en  I will remember that. Not warmly — usefully. It tells me what I can say in front of you, and that is a short list and you are now on it.
    >>  ............................................
    pt  Vou lembrar disso. Não com carinho — com utilidade. Me diz o que eu posso falar na sua frente, e essa é uma lista curta e você agora está nela.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the calendar."

*stance family `exit` · tone `plain` · answers the beat(s) `work.werewolf_expert.a_confidence.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.a_confidence.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.werewolf_expert.a_confidence.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.active.respond.leave   [38 chars]
    en  I'll let you get back to the calendar.
    >>  ............................................
    pt  Vou deixar você voltar ao calendário.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the calendar."
       spoken on: conversations.scene.work.werewolf_expert.a_confidence.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.left`: the villager accepts. Subject `work.werewolf_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond / leave; conversations.scene.work.werewolf_expert.followup / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond / leave; conversations.topic.work.werewolf_expert.craft.respond / leave; conversations.topic.work.werewolf_expert.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.werewolf_expert.leave/1   [47 chars]
    en  It moves whether I watch it or not. Off you go.
    >>  ............................................
    pt  Ele anda eu olhando ou não. Pode ir.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.leave/2   [44 chars]
    en  Aye. Ask me again before the full one, %1$s.
    >>  ............................................
    pt  É. Me pergunte de novo antes da cheia, %1$s.
    >>  ............................................
```

---


## `conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.werewolf_expert.a_confidence.succeeded` — e.g. "Two years of it now, and it has never once been a problem for anybody, and nobody in this village knows it exists."


```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond   [17 chars]
    en  That arrangement.
    >>  ............................................
    pt  Aquele arranjo.
    >>  ............................................
```


### Button `note_the_quiet_success` — "Two uneventful years is the whole point."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.werewolf_expert.a_confidence.succeeded` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.werewolf_expert.a_confidence.succeeded.note_the_quiet_success` — accepted phrasings: "two uneventful years is the whole point"; "two uneventful years is the whole point"; "the uneventful stretch is the achievement"
  - the message must contain one of: `uneventful`, `achievement`, `years`
  - scored words: `uneventful`(1.8), `achievement`(1.8), `years`(1.8), `two`(0.8), `whole`(0.8), `point`(0.8), `stretch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond.note_the_quiet_success
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond.note_the_quiet_success   [40 chars]
    en  Two uneventful years is the whole point.
    >>  ............................................
    pt  Dois anos sem incidente são o objetivo inteiro.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +4  _(recorded under topic `work.werewolf_expert.confidence`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.werewolf_expert.a_confidence"}
- Then opens: `conversations.scene.work.werewolf_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a bad moon?" | "I'll leave you to the calendar."

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.a_confidence.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Two uneventful years is the whole point."
       spoken on: conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond, button `note_the_quiet_success`
       leaves the player on: conversations.scene.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.a_confidence.succeeded.acknowledged`: the villager accepts. Subject `work.werewolf_expert.confidence`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.succeeded.acknowledged/1   [137 chars]
    en  It is, and it means my trade looks from outside like a woman with a calendar being fussy about shutters, and I have made peace with that.
    >>  ............................................
    pt  É, e significa que o meu ofício parece de fora uma mulher com um calendário implicando com venezianas, e eu fiz as pazes com isso.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.succeeded.acknowledged/2   [137 chars]
    en  Thank you. The version of this work that gets talked about is the version where somebody is dramatic and late. This is the other version.
    >>  ............................................
    pt  Obrigada. A versão deste trabalho de que se fala é a versão em que alguém é dramático e chega tarde. Esta é a outra versão.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.succeeded.acknowledged/3   [143 chars]
    en  Two years, thirty-six arrangements, and the only record is a column of dates with no names against them. That is deliberate to the last detail.
    >>  ............................................
    pt  Dois anos, trinta e seis arranjos, e o único registro é uma coluna de datas sem nomes ao lado. É deliberado até o último detalhe.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the calendar."

*stance family `exit` · tone `plain` · answers the beat(s) `work.werewolf_expert.a_confidence.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond.leave   [38 chars]
    en  I'll let you get back to the calendar.
    >>  ............................................
    pt  Vou deixar você voltar ao calendário.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the calendar."
       spoken on: conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.left`: the villager accepts. Subject `work.werewolf_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.werewolf_expert.a_confidence.active.respond / leave; conversations.scene.work.werewolf_expert.followup / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond / leave; conversations.topic.work.werewolf_expert.craft.respond / leave; conversations.topic.work.werewolf_expert.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.werewolf_expert.a_confidence.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.werewolf_expert.followup`

**Reached from 11 route(s):** `conversations.scene.work.werewolf_expert.a_confidence.active.respond` / `ask_about_the_help`; `conversations.scene.work.werewolf_expert.a_confidence.active.respond` / `respect_the_confidence`; `conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond` / `note_the_quiet_success`; `conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond` / `ask_why_not`; `conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond` / `back_the_refusal`; `conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond` / `acknowledge_the_pressure`; `conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond` / `note_the_written_page`; `conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond` / `ask_what_it_costs`; `conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond` / `offer_bars`; `conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond` / `advise_the_comparison`; `conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond` / `ask_about_the_sheet`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.werewolf_expert.a_confidence.active.explained` — e.g. "Dates, a room with a good door, and somebody who knows where they are. That is the entire arrangement and it has never once failed."
- `conversations.scene.work.werewolf_expert.a_confidence.active.steadied` — e.g. "Thank you. You are the fourth person this month to be told there is somebody, and the first not to spend ten minutes guessing."
- `conversations.scene.work.werewolf_expert.a_confidence.succeeded.acknowledged` — e.g. "It is, and it means my trade looks from outside like a woman with a calendar being fussy about shutters, and I have made peace with that."
- `conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.acknowledged` — e.g. "It is, and it is the reason the position exists. If it were easy the village would not need somebody to hold it."
- `conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.explained` — e.g. "Because the arrangement only exists while people come to me. Give one name and nobody comes again, and then there is no calendar and no shutters and no warning."
- `conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.steadied` — e.g. "I will, and it may cost me the position, and the person who takes it after me will give them a name inside a year."
- `conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.acknowledged` — e.g. "That is why I did it. Whoever holds this position in twenty years will inherit the reasoning instead of having to find it under pressure."
- `conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.accepted` — e.g. "Then %2$s is done this week and the argument is over, and I will never get to find out whether I would have won it."
- `conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.agreed_to_say_it` — e.g. "Out loud, at the meeting, where the supper is being planned. It is slightly cruel and it is entirely true."
- `conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.explained` — e.g. "A day's iron and two afternoons. %2$s would be finished before the week is out and the whole argument is about the day's iron."
- `conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.explained` — e.g. "Eleven items, four years of dates, and a column for who agreed to each one, which is the column that actually does the work."


```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.werewolf_expert.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.werewolf_expert.followup   [30 chars]
    en  Anything else before the moon?
    >>  ............................................
    pt  Mais alguma coisa antes da lua?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of a bad moon?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.werewolf_expert.*` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.werewolf_expert.followup.ask_more` — accepted phrasings: "whats the hardest part of a bad moon"; "what is the hardest part of a bad moon"; "hardest thing about those nights"
  - the message must contain one of: `hardest`, `moon`
  - scored words: `hardest`(1.8), `moon`(1.8), `whats`(0.8), `part`(0.8), `bad`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.werewolf_expert.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.werewolf_expert.followup.ask_more   [38 chars]
    en  What's the hardest part of a bad moon?
    >>  ............................................
    pt  Qual é a parte mais difícil de uma lua ruim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.werewolf_expert.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.werewolf_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "When's the next one?" | "Mind the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of a bad moon?"
       spoken on: conversations.scene.work.werewolf_expert.followup, button `ask_more`
       leaves the player on: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.hard`: the villager explains. Subject `work.werewolf_expert.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.werewolf_expert.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.werewolf_expert.hard/1   [94 chars]
    en  A broken door, a frightened family and a person who has to live with what they don't remember.
    >>  ............................................
    pt  Uma porta arrebentada, uma família apavorada e uma pessoa que precisa viver com o que não lembra.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.hard/2   [95 chars]
    en  It looks like a story people tell for years, %1$s. That's the cost — not the injury, the story.
    >>  ............................................
    pt  Parece uma história que se conta por anos, %1$s. É esse o custo — não o ferimento, a história.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the calendar."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.werewolf_expert.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.werewolf_expert.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.werewolf_expert.followup.leave   [31 chars]
    en  I'll leave you to the calendar.
    >>  ............................................
    pt  Vou deixar você com o calendário.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the calendar."
       spoken on: conversations.scene.work.werewolf_expert.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.left`: the villager accepts. Subject `work.werewolf_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.werewolf_expert.a_confidence.active.respond / leave; conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond / leave; conversations.topic.work.werewolf_expert.craft.respond / leave; conversations.topic.work.werewolf_expert.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.werewolf_expert.a_confidence.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked` — e.g. "%2$s has asked me for a name three times this month, in increasingly reasonable voices."


```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond   [24 chars]
    en  What you're being asked.
    >>  ............................................
    pt  O que estão te perguntando.
    >>  ............................................
```


### Button `ask_why_not` — "Why is a name the wrong answer?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.werewolf_expert.pressed_for_a_name.blocked` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.werewolf_expert.pressed_for_a_name.blocked.ask_why_not` — accepted phrasings: "why is a name the wrong answer"; "why is a name the wrong answer"; "what would giving a name actually do"
  - the message must contain one of: `name`, `giving`
  - scored words: `name`(1.8), `giving`(1.8), `why`(0.8), `wrong`(0.8), `answer`(0.8), `actually`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond.ask_why_not
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond.ask_why_not   [31 chars]
    en  Why is a name the wrong answer?
    >>  ............................................
    pt  Por que um nome é a resposta errada?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.werewolf_expert.the_village_fear`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.werewolf_expert.pressed_for_a_name"}
- Then opens: `conversations.scene.work.werewolf_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a bad moon?" | "I'll leave you to the calendar."

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.explained
WHO    VILLAGER — what the player reads after pressing "Why is a name the wrong answer?"
       spoken on: conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond, button `ask_why_not`
       leaves the player on: conversations.scene.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.pressed_for_a_name.blocked.explained`: the villager explains. Subject `work.werewolf_expert.the_village_fear`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.explained/1   [160 chars]
    en  Because the arrangement only exists while people come to me. Give one name and nobody comes again, and then there is no calendar and no shutters and no warning.
    >>  ............................................
    pt  Porque o arranjo só existe enquanto as pessoas me procuram. Dê um nome e ninguém procura mais, e aí não há calendário, nem venezianas, nem aviso.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.explained/2   [140 chars]
    en  Because a village with a name in it stops doing precautions and starts doing surveillance, and surveillance has never once protected a barn.
    >>  ............................................
    pt  Porque uma vila com um nome dentro para de fazer precaução e começa a fazer vigilância, e vigilância nunca protegeu um celeiro.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.explained/3   [110 chars]
    en  And because I would be wrong sometimes. Not often. Sometimes. And a wrong name is a family gone by the spring.
    >>  ............................................
    pt  E porque eu erraria às vezes. Não muito. Às vezes. E um nome errado é uma família que some até a primavera.
    >>  ............................................
```


### Button `back_the_refusal` — "Hold to that, however they ask."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.werewolf_expert.pressed_for_a_name.blocked` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.werewolf_expert.pressed_for_a_name.blocked.back_the_refusal` — accepted phrasings: "hold to that however they ask"; "hold to that however they ask"; "keep refusing however reasonable they sound"
  - the message must contain one of: `hold`, `refusing`, `reasonable`
  - scored words: `hold`(1.8), `refusing`(1.8), `reasonable`(1.8), `however`(0.8), `ask`(0.8), `keep`(0.8), `sound`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond.back_the_refusal
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond.back_the_refusal   [31 chars]
    en  Hold to that, however they ask.
    >>  ............................................
    pt  Mantenha isso, peçam como pedirem.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `work.werewolf_expert.name.backed`, budget `deep`, replay policy `once`
- Does: disposition — respect +4, trust +3  _(recorded under topic `work.werewolf_expert.the_village_fear`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.werewolf_expert.pressed_for_a_name"}
- Then opens: `conversations.scene.work.werewolf_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a bad moon?" | "I'll leave you to the calendar."

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.steadied
WHO    VILLAGER — what the player reads after pressing "Hold to that, however they ask."
       spoken on: conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond, button `back_the_refusal`
       leaves the player on: conversations.scene.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.pressed_for_a_name.blocked.steadied`: the villager accepts. Subject `work.werewolf_expert.the_village_fear`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.steadied/1   [114 chars]
    en  I will, and it may cost me the position, and the person who takes it after me will give them a name inside a year.
    >>  ............................................
    pt  Vou, e pode me custar o cargo, e quem assumir depois de mim vai dar um nome a eles dentro de um ano.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.steadied/2   [126 chars]
    en  Yes. And I am going to write down why, and give it to two people, so that the reasoning survives whatever happens to my nerve.
    >>  ............................................
    pt  Sim. E vou escrever o motivo e entregar a duas pessoas, para que o raciocínio sobreviva ao que acontecer com a minha coragem.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.steadied/3   [150 chars]
    en  Thank you. Reasonable voices are the hard ones. Anybody can refuse a mob; refusing a decent man asking politely for the fourth time is the actual job.
    >>  ............................................
    pt  Obrigada. Vozes razoáveis são as difíceis. Qualquer um recusa uma multidão; recusar um homem decente pedindo com educação pela quarta vez é o trabalho de verdade.
    >>  ............................................
```


### Button `acknowledge_the_pressure` — "That's a lot of pressure to stand under."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.werewolf_expert.pressed_for_a_name.blocked` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.werewolf_expert.pressed_for_a_name.blocked.acknowledge_the_pressure` — accepted phrasings: "thats a lot of pressure to stand under"; "that is a lot of pressure to stand under"; "you are under a great deal of pressure"
  - the message must contain one of: `pressure`, `stand`
  - scored words: `pressure`(1.8), `stand`(1.8), `thats`(0.8), `lot`(0.8), `under`(0.8), `great`(0.8), `deal`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond.acknowledge_the_pressure
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond.acknowledge_the_pressure   [40 chars]
    en  That's a lot of pressure to stand under.
    >>  ............................................
    pt  É muita pressão para suportar.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +4  _(recorded under topic `work.werewolf_expert.the_village_fear`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.werewolf_expert.pressed_for_a_name"}
- Then opens: `conversations.scene.work.werewolf_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a bad moon?" | "I'll leave you to the calendar."

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.acknowledged
WHO    VILLAGER — what the player reads after pressing "That's a lot of pressure to stand under."
       spoken on: conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond, button `acknowledge_the_pressure`
       leaves the player on: conversations.scene.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.pressed_for_a_name.blocked.acknowledged`: the villager accepts. Subject `work.werewolf_expert.the_village_fear`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.acknowledged/1   [112 chars]
    en  It is, and it is the reason the position exists. If it were easy the village would not need somebody to hold it.
    >>  ............................................
    pt  É, e é o motivo de o cargo existir. Se fosse fácil, a vila não precisaria de alguém para sustentar isso.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.acknowledged/2   [124 chars]
    en  Thank you. I would like to be able to say I find it straightforward. I lie awake about it and I still say no in the morning.
    >>  ............................................
    pt  Obrigada. Eu gostaria de poder dizer que acho simples. Fico acordada por causa disso e continuo dizendo não de manhã.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.acknowledged/3   [153 chars]
    en  The hardest part is that they are right about being frightened. I am not refusing because their fear is silly. I am refusing because the remedy is worse.
    >>  ............................................
    pt  A parte mais difícil é que eles têm razão em estar com medo. Não estou recusando porque o medo deles é bobo. Estou recusando porque o remédio é pior.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the calendar."

*stance family `exit` · tone `plain` · answers the beat(s) `work.werewolf_expert.pressed_for_a_name.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond.leave   [38 chars]
    en  I'll let you get back to the calendar.
    >>  ............................................
    pt  Vou deixar você voltar ao calendário.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the calendar."
       spoken on: conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.left`: the villager accepts. Subject `work.werewolf_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.werewolf_expert.a_confidence.active.respond / leave; conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond / leave; conversations.scene.work.werewolf_expert.followup / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond / leave; conversations.topic.work.werewolf_expert.craft.respond / leave; conversations.topic.work.werewolf_expert.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.werewolf_expert.a_confidence.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded` — e.g. "I wrote down why and gave it to two people, and %2$s read it and has not asked since."


```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond   [21 chars]
    en  That pressure, since.
    >>  ............................................
    pt  Aquela pressão, depois disso.
    >>  ............................................
```


### Button `note_the_written_page` — "Writing it down outlasts you."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.werewolf_expert.pressed_for_a_name.succeeded` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.werewolf_expert.pressed_for_a_name.succeeded.note_the_written_page` — accepted phrasings: "writing it down outlasts you"; "writing it down outlasts you"; "the written page will outlast you"
  - the message must contain one of: `writing`, `written`, `outlasts`
  - scored words: `writing`(1.8), `written`(1.8), `outlasts`(1.8), `down`(0.8), `page`(0.8), `outlast`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond.note_the_written_page
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond.note_the_written_page   [29 chars]
    en  Writing it down outlasts you.
    >>  ............................................
    pt  Escrever sobrevive a você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.werewolf_expert.the_village_fear`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.werewolf_expert.pressed_for_a_name"}
- Then opens: `conversations.scene.work.werewolf_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a bad moon?" | "I'll leave you to the calendar."

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Writing it down outlasts you."
       spoken on: conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond, button `note_the_written_page`
       leaves the player on: conversations.scene.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.pressed_for_a_name.succeeded.acknowledged`: the villager accepts. Subject `work.werewolf_expert.the_village_fear`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.acknowledged/1   [137 chars]
    en  That is why I did it. Whoever holds this position in twenty years will inherit the reasoning instead of having to find it under pressure.
    >>  ............................................
    pt  Foi por isso que eu fiz. Quem ocupar este cargo daqui a vinte anos vai herdar o raciocínio em vez de ter que descobrir sob pressão.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.acknowledged/2   [138 chars]
    en  Thank you. A refusal in a doorway is a mood. A refusal on a page with reasons is a policy, and policies survive people losing their nerve.
    >>  ............................................
    pt  Obrigada. Uma recusa numa porta é um humor. Uma recusa numa página com motivos é uma política, e políticas sobrevivem a gente perder a coragem.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.acknowledged/3   [140 chars]
    en  I have added a line about what to do if I am ever the one being asked about. It was the least comfortable sentence I have ever written down.
    >>  ............................................
    pt  Acrescentei uma linha sobre o que fazer se um dia eu for a pessoa sobre quem perguntam. Foi a frase mais desconfortável que eu já escrevi.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the calendar."

*stance family `exit` · tone `plain` · answers the beat(s) `work.werewolf_expert.pressed_for_a_name.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond.leave   [38 chars]
    en  I'll let you get back to the calendar.
    >>  ............................................
    pt  Vou deixar você voltar ao calendário.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the calendar."
       spoken on: conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.left`: the villager accepts. Subject `work.werewolf_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.werewolf_expert.a_confidence.active.respond / leave; conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond / leave; conversations.scene.work.werewolf_expert.followup / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond / leave; conversations.topic.work.werewolf_expert.craft.respond / leave; conversations.topic.work.werewolf_expert.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.werewolf_expert.a_confidence.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.werewolf_expert.unfunded_precautions.blocked` — e.g. "%2$s wants doing before %3$s, and the village will not pay for it because nothing has happened yet."


```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond   [16 chars]
    en  The precautions.
    >>  ............................................
    pt  As precauções.
    >>  ............................................
```


### Button `ask_what_it_costs` — "What would it cost to do?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.werewolf_expert.unfunded_precautions.blocked` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.werewolf_expert.unfunded_precautions.blocked.ask_what_it_costs` — accepted phrasings: "what would it cost to do"; "what would it cost to do"; "how much would the work come to"
  - the message must contain one of: `cost`, `much`
  - scored words: `cost`(1.8), `much`(1.8), `work`(0.8), `come`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond.ask_what_it_costs
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond.ask_what_it_costs   [25 chars]
    en  What would it cost to do?
    >>  ............................................
    pt  Quanto custaria fazer?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.werewolf_expert.precautions`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.werewolf_expert.unfunded_precautions"}
- Then opens: `conversations.scene.work.werewolf_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a bad moon?" | "I'll leave you to the calendar."

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What would it cost to do?"
       spoken on: conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond, button `ask_what_it_costs`
       leaves the player on: conversations.scene.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.unfunded_precautions.blocked.explained`: the villager explains. Subject `work.werewolf_expert.precautions`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.explained/1   [126 chars]
    en  A day's iron and two afternoons. %2$s would be finished before the week is out and the whole argument is about the day's iron.
    >>  ............................................
    pt  Um dia de ferro e duas tardes. %2$s ficaria pronto antes de a semana acabar, e a discussão inteira é sobre o dia de ferro.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.explained/2   [126 chars]
    en  Less than the village spends on the harvest supper. I have written that comparison down four times and never said it out loud.
    >>  ............................................
    pt  Menos do que a vila gasta na ceia da colheita. Já escrevi essa comparação quatro vezes e nunca disse em voz alta.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.explained/3   [105 chars]
    en  Nothing, if we do it before something happens. Everything, and a family blamed for it, if we do it after.
    >>  ............................................
    pt  Nada, se fizermos antes de acontecer algo. Tudo, e uma família culpada por isso, se fizermos depois.
    >>  ............................................
```


### Button `offer_bars` — "I'll bring you iron bars for the shutters."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.werewolf_expert.unfunded_precautions.blocked` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.werewolf_expert.unfunded_precautions.blocked.offer_bars` — accepted phrasings: "ill bring you iron bars for the shutters"; "i can bring you iron bars for the shutters"; "let me fetch bars for that"
  - the message must contain one of: `bars`, `shutters`
  - scored words: `bars`(1.8), `shutters`(1.8), `ill`(0.8), `bring`(0.8), `iron`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond.offer_bars
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond.offer_bars   [42 chars]
    en  I'll bring you iron bars for the shutters.
    >>  ............................................
    pt  Vou trazer grades de ferro para as venezianas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.werewolf_expert.precautions.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.werewolf_expert.precautions`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.unfunded_precautions", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.werewolf_expert.unfunded_precautions", "obligation": "commitment:work.werewolf_expert.bring_bars"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.werewolf_expert.bring_bars"}
- Then opens: `conversations.scene.work.werewolf_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a bad moon?" | "I'll leave you to the calendar."

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you iron bars for the shutters."
       spoken on: conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond, button `offer_bars`
       leaves the player on: conversations.scene.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.unfunded_precautions.blocked.accepted`: the villager accepts. Subject `work.werewolf_expert.precautions`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.accepted/1   [115 chars]
    en  Then %2$s is done this week and the argument is over, and I will never get to find out whether I would have won it.
    >>  ............................................
    pt  Então %2$s fica pronto esta semana e a discussão acaba, e eu nunca vou descobrir se teria ganhado.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.accepted/2   [131 chars]
    en  You should know that if this works nothing will happen and nobody will thank either of us, and that is the outcome I am asking for.
    >>  ............................................
    pt  Você deve saber que, se isso funcionar, nada vai acontecer e ninguém vai agradecer a nenhum de nós dois, e é esse o resultado que eu peço.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.accepted/3   [134 chars]
    en  Yes. And I will put it on the sheet as done, with the date, because the sheet is the only thing that will outlast the current headman.
    >>  ............................................
    pt  Sim. E vou marcar na folha como feito, com a data, porque a folha é a única coisa que vai sobreviver ao chefe atual.
    >>  ............................................
```


### Button `advise_the_comparison` — "Say it costs less than the harvest supper."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.werewolf_expert.unfunded_precautions.blocked` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.werewolf_expert.unfunded_precautions.blocked.advise_the_comparison` — accepted phrasings: "say it costs less than the harvest supper"; "say it costs less than the harvest supper"; "make the comparison out loud at the meeting"
  - the message must contain one of: `supper`, `comparison`, `meeting`
  - scored words: `supper`(1.8), `comparison`(1.8), `meeting`(1.8), `say`(0.8), `costs`(0.8), `less`(0.8), `than`(0.8), `harvest`(0.8), `make`(0.8), `out`(0.8), `loud`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond.advise_the_comparison
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond.advise_the_comparison   [42 chars]
    en  Say it costs less than the harvest supper.
    >>  ............................................
    pt  Diga que custa menos que a ceia da colheita.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.werewolf_expert.precautions`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.werewolf_expert.unfunded_precautions"}
- Then opens: `conversations.scene.work.werewolf_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a bad moon?" | "I'll leave you to the calendar."

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.agreed_to_say_it
WHO    VILLAGER — what the player reads after pressing "Say it costs less than the harvest supper."
       spoken on: conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond, button `advise_the_comparison`
       leaves the player on: conversations.scene.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.unfunded_precautions.blocked.agreed_to_say_it`: the villager accepts. Subject `work.werewolf_expert.precautions`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.agreed_to_say_it/1   [106 chars]
    en  Out loud, at the meeting, where the supper is being planned. It is slightly cruel and it is entirely true.
    >>  ............................................
    pt  Em voz alta, na reunião, onde a ceia está sendo planejada. É um pouco cruel e é inteiramente verdade.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.agreed_to_say_it/2   [154 chars]
    en  I have been avoiding it because it makes me the person who begrudges everybody a supper. That is a small price and I have been treating it as a large one.
    >>  ............................................
    pt  Eu venho evitando porque isso me torna a pessoa que inveja a ceia de todo mundo. É um preço pequeno e eu venho tratando como grande.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.agreed_to_say_it/3   [105 chars]
    en  Yes. A number nobody can picture is an argument nobody can have. A number next to a supper is a decision.
    >>  ............................................
    pt  Sim. Um número que ninguém consegue imaginar é uma discussão que ninguém consegue ter. Um número ao lado de uma ceia é uma decisão.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the calendar."

*stance family `exit` · tone `plain` · answers the beat(s) `work.werewolf_expert.unfunded_precautions.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond.leave   [38 chars]
    en  I'll let you get back to the calendar.
    >>  ............................................
    pt  Vou deixar você voltar ao calendário.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the calendar."
       spoken on: conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.left`: the villager accepts. Subject `work.werewolf_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.werewolf_expert.a_confidence.active.respond / leave; conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond / leave; conversations.scene.work.werewolf_expert.followup / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond / leave; conversations.topic.work.werewolf_expert.craft.respond / leave; conversations.topic.work.werewolf_expert.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.werewolf_expert.a_confidence.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded` — e.g. "%2$s is done. Two afternoons, and the sheet has its first tick on it in three years."


```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond   [23 chars]
    en  The precautions, since.
    >>  ............................................
    pt  As precauções, depois disso.
    >>  ............................................
```


### Button `ask_about_the_sheet` — "What else is on that sheet?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.werewolf_expert.unfunded_precautions.succeeded` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.werewolf_expert.unfunded_precautions.succeeded.ask_about_the_sheet` — accepted phrasings: "what else is on that sheet"; "what else is on that sheet"; "what does the rest of the sheet say"
  - the message must contain one of: `sheet`
  - scored words: `sheet`(1.8), `else`(0.8), `does`(0.8), `rest`(0.8), `say`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond.ask_about_the_sheet
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond.ask_about_the_sheet   [27 chars]
    en  What else is on that sheet?
    >>  ............................................
    pt  O que mais tem nessa folha?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.werewolf_expert.precautions`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.werewolf_expert.unfunded_precautions"}
- Then opens: `conversations.scene.work.werewolf_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a bad moon?" | "I'll leave you to the calendar."

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.explained
WHO    VILLAGER — what the player reads after pressing "What else is on that sheet?"
       spoken on: conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond, button `ask_about_the_sheet`
       leaves the player on: conversations.scene.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.unfunded_precautions.succeeded.explained`: the villager explains. Subject `work.werewolf_expert.precautions`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.explained/1   [124 chars]
    en  Eleven items, four years of dates, and a column for who agreed to each one, which is the column that actually does the work.
    >>  ............................................
    pt  Onze itens, quatro anos de datas, e uma coluna para quem concordou com cada um, que é a coluna que de fato faz o trabalho.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.explained/2   [138 chars]
    en  Two of the eleven are about lighting a lane, which has nothing to do with my field and everything to do with people being less frightened.
    >>  ............................................
    pt  Dois dos onze são sobre iluminar uma viela, o que não tem nada a ver com a minha área e tudo a ver com as pessoas ficarem menos assustadas.
    >>  ............................................
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.explained/3   [109 chars]
    en  The last line says what to do if I am not here. That took me longer to write than the other ten put together.
    >>  ............................................
    pt  A última linha diz o que fazer se eu não estiver aqui. Levei mais tempo para escrever isso do que as outras dez juntas.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the calendar."

*stance family `exit` · tone `plain` · answers the beat(s) `work.werewolf_expert.unfunded_precautions.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond.leave   [38 chars]
    en  I'll let you get back to the calendar.
    >>  ............................................
    pt  Vou deixar você voltar ao calendário.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the calendar."
       spoken on: conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.left`: the villager accepts. Subject `work.werewolf_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.werewolf_expert.a_confidence.active.respond / leave; conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond / leave; conversations.scene.work.werewolf_expert.followup / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond / leave; conversations.topic.work.werewolf_expert.craft.respond / leave; conversations.topic.work.werewolf_expert.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.werewolf_expert.a_confidence.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.werewolf_expert.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.werewolf_expert.craft` — e.g. "What I actually know is arrangements. Doors, dates, who to tell, what to say. Nobody writes any of that down."


```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.werewolf_expert.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.werewolf_expert.craft.respond   [33 chars]
    en  That's the whole of my expertise.
    >>  ............................................
    pt  É toda a minha perícia.
    >>  ............................................
```


### Button `ask_tell` — "Who do you tell?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.werewolf_expert.craft` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.werewolf_expert.craft.ask_tell` — accepted phrasings: "who do you tell"
  - the message must contain one of: `tell`, `whom`
  - scored words: `tell`(1.5), `whom`(1.2), `three`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.craft.respond.ask_tell
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.craft.respond.ask_tell   [16 chars]
    en  Who do you tell?
    >>  ............................................
    pt  Quem você avisa?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.werewolf_expert.craft.ask_tell`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.werewolf_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "When's the next one?" | "Mind the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.craft.ask_tell
WHO    VILLAGER — what the player reads after pressing "Who do you tell?"
       spoken on: conversations.topic.work.werewolf_expert.craft.respond, button `ask_tell`
       leaves the player on: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.craft.ask_tell`: the villager explains. Subject `work.werewolf_expert.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.craft.ask_tell/1   [99 chars]
    en  Three people. Not the ones you'd guess, and each of them for a different and very practical reason.
    >>  ............................................
    pt  Três pessoas. Não as que você imagina, e cada uma por um motivo diferente e muito prático.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.craft.ask_tell/2   [95 chars]
    en  The priest, the mason and the cook, %1$s. Doors, dates and the fact that I'd not be at the pot.
    >>  ............................................
    pt  O padre, o pedreiro e o cozinheiro, %1$s. Portas, datas e o fato de eu não estar no caldeirão.
    >>  ............................................
```


### Button `admire` — "Arrangements are the practical knowledge nobody records."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.werewolf_expert.craft` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.werewolf_expert.craft.admire` — accepted phrasings: "arrangements are the practical knowledge nobody records"
  - the message must contain one of: `arrangements`, `practical`, `records`
  - scored words: `arrangements`(1.5), `practical`(1.2), `records`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.craft.respond.admire   [56 chars]
    en  Arrangements are the practical knowledge nobody records.
    >>  ............................................
    pt  Arranjos são o conhecimento prático que ninguém registra.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.werewolf_expert.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.werewolf_expert.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.werewolf_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "When's the next one?" | "Mind the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.craft.admire
WHO    VILLAGER — what the player reads after pressing "Arrangements are the practical knowledge nobody records."
       spoken on: conversations.topic.work.werewolf_expert.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.craft.admire`: the villager accepts. Subject `work.werewolf_expert.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.craft.admire/1   [96 chars]
    en  Exactly. Everything written about people like me is about what we are. Nothing is about Tuesday.
    >>  ............................................
    pt  Exatamente. Tudo que se escreve sobre gente como eu é sobre o que somos. Nada é sobre a terça-feira.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.craft.admire/2   [84 chars]
    en  Say that to the scribe. He'd write it down and I would be enormously grateful, %1$s.
    >>  ............................................
    pt  Diga isso ao escriba. Ele anotaria e eu ficaria imensamente grato, %1$s.
    >>  ............................................
```


### Button `ask_alone` — "Eleven years of solving it alone?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.werewolf_expert.craft` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.werewolf_expert.craft.ask_alone` — accepted phrasings: "eleven years of solving it alone"
  - the message must contain one of: `alone`, `eleven`, `solved`
  - scored words: `alone`(1.5), `eleven`(1.2), `solved`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.craft.respond.ask_alone
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.craft.respond.ask_alone   [33 chars]
    en  Eleven years of solving it alone?
    >>  ............................................
    pt  Onze anos resolvendo sozinho?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.werewolf_expert.craft.ask_alone`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.werewolf_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "When's the next one?" | "Mind the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.craft.ask_alone
WHO    VILLAGER — what the player reads after pressing "Eleven years of solving it alone?"
       spoken on: conversations.topic.work.werewolf_expert.craft.respond, button `ask_alone`
       leaves the player on: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.craft.ask_alone`: the villager explains. Subject `work.werewolf_expert.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.craft.ask_alone/1   [94 chars]
    en  The first three were bad and the fourth was the cellar and after the cellar it was arithmetic.
    >>  ............................................
    pt  Os três primeiros foram ruins e o quarto foi o porão e depois do porão virou aritmética.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.craft.ask_alone/2   [96 chars]
    en  Alone is the word, %1$s. There's no one within four valleys who's had to solve the same Tuesday.
    >>  ............................................
    pt  Sozinho é a palavra, %1$s. Não tem ninguém em quatro vales que teve que resolver a mesma terça.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the calendar."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.werewolf_expert.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.craft.respond.leave   [38 chars]
    en  I'll let you get back to the calendar.
    >>  ............................................
    pt  Vou deixar você voltar ao calendário.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the calendar."
       spoken on: conversations.topic.work.werewolf_expert.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.left`: the villager accepts. Subject `work.werewolf_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.werewolf_expert.a_confidence.active.respond / leave; conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond / leave; conversations.scene.work.werewolf_expert.followup / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond / leave; conversations.topic.work.werewolf_expert.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.werewolf_expert.a_confidence.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.werewolf_expert.followup`

**Reached from 20 route(s):** `conversations.scene.work.werewolf_expert.followup` / `ask_more`; `conversations.topic.work.werewolf_expert.craft.respond` / `ask_tell`; `conversations.topic.work.werewolf_expert.craft.respond` / `admire`; `conversations.topic.work.werewolf_expert.craft.respond` / `ask_alone`; `conversations.topic.work.werewolf_expert.future.respond` / `ask_written`; `conversations.topic.work.werewolf_expert.future.respond` / `encourage`; `conversations.topic.work.werewolf_expert.future.respond` / `ask_next`; `conversations.topic.work.werewolf_expert.respond` / `ask_hard`; `conversations.topic.work.werewolf_expert.respond` / `value`; `conversations.topic.work.werewolf_expert.respond` / `challenge`; `conversations.topic.work.werewolf_expert.respond` / `challenge`; `conversations.topic.work.werewolf_expert.risk.respond` / `ask_bolts` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.werewolf_expert.challenge.landed` — e.g. "Someone, not something. And yes — safely, on a schedule, with their agreement."
- `conversations.work.prof.werewolf_expert.challenge.stung` — e.g. "...Say 'something' again and see whether I keep helping this village."
- `conversations.work.prof.werewolf_expert.craft.admire` — e.g. "Exactly. Everything written about people like me is about what we are. Nothing is about Tuesday."
- `conversations.work.prof.werewolf_expert.craft.ask_alone` — e.g. "The first three were bad and the fourth was the cellar and after the cellar it was arithmetic."
- `conversations.work.prof.werewolf_expert.craft.ask_tell` — e.g. "Three people. Not the ones you'd guess, and each of them for a different and very practical reason."
- `conversations.work.prof.werewolf_expert.future.ask_next` — e.g. "Somewhere. Not here, necessarily, and not soon — but somebody, and they'll have the first three bad years."
- `conversations.work.prof.werewolf_expert.future.ask_written` — e.g. "The scribe. He'd copy exactly and add nothing, and adding nothing is the entire requirement."
- `conversations.work.prof.werewolf_expert.future.encourage` — e.g. "...Name him in it. He couldn't refuse that and he'd hate it and he would read it forty times."
- `conversations.work.prof.werewolf_expert.hard` — e.g. "A broken door, a frightened family and a person who has to live with what they don't remember."
- `conversations.work.prof.werewolf_expert.risk.ask_bolts` — e.g. "It's eleven years of margin and it has never been tested, and I check it nine times on the twenty-eighth."
- `conversations.work.prof.werewolf_expert.risk.ask_three_know` — e.g. "Because each of them needs to know one thing and none of them needs to know all three."
- `conversations.work.prof.werewolf_expert.risk.sympathise` — e.g. "...Every morning, before anything else. It's the first thing I do and I've never described it aloud."
- `conversations.work.prof.werewolf_expert.task.ask_often` — e.g. "Four times a day for twenty-seven days and about nine times on the twenty-eighth."
- `conversations.work.prof.werewolf_expert.task.ask_three_days` — e.g. "Better than I expected and worse than I'd like. Nobody asks and everybody counts."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.werewolf_expert.followup   [29 chars]
    en  That's the moon's half of it.
    >>  ............................................
    pt  É a metade da lua.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.werewolf_expert.challenge.landed`, `work.werewolf_expert.challenge.stung`, `work.werewolf_expert.craft.admire`, `work.werewolf_expert.craft.ask_alone`, `work.werewolf_expert.craft.ask_tell`, `work.werewolf_expert.future.ask_next`, `work.werewolf_expert.future.ask_written`, `work.werewolf_expert.future.encourage`, `work.werewolf_expert.hard`, `work.werewolf_expert.risk.ask_bolts`, `work.werewolf_expert.risk.ask_three_know`, `work.werewolf_expert.risk.sympathise`, `work.werewolf_expert.task.ask_often`, `work.werewolf_expert.task.ask_three_days`, `work.werewolf_expert.task.offer_hands`, `work.werewolf_expert.value`, `work.werewolf_expert.village.ask_pot`, `work.werewolf_expert.village.ask_unpaid`, `work.werewolf_expert.village.say_thanks` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.werewolf_expert.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `scheduling`, `monsters`
  - scored words: `thought`(1.2), `scheduling`(1.5), `monsters`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.werewolf_expert.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.werewolf_expert.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.werewolf_expert.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.werewolf_expert.thanks`: the villager accepts. Subject `work.werewolf_expert.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.thanks/1   [83 chars]
    en  Few do. It's easier to think in monsters than in neighbours with a difficult month.
    >>  ............................................
    pt  Poucos pensam. É mais fácil pensar em monstros que em vizinhos com um mês difícil.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.thanks/2   [81 chars]
    en  It is mostly scheduling and chain maintenance, %1$s. Nobody puts that in a story.
    >>  ............................................
    pt  É quase tudo agendamento e manutenção de corrente, %1$s. Ninguém põe isso numa história.
    >>  ............................................
```


### Button `ask_more` — "When's the next one?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.werewolf_expert.challenge.landed`, `work.werewolf_expert.challenge.stung`, `work.werewolf_expert.craft.admire`, `work.werewolf_expert.craft.ask_alone`, `work.werewolf_expert.craft.ask_tell`, `work.werewolf_expert.future.ask_next`, `work.werewolf_expert.future.ask_written`, `work.werewolf_expert.future.encourage`, `work.werewolf_expert.hard`, `work.werewolf_expert.risk.ask_bolts`, `work.werewolf_expert.risk.ask_three_know`, `work.werewolf_expert.risk.sympathise`, `work.werewolf_expert.task.ask_often`, `work.werewolf_expert.task.ask_three_days`, `work.werewolf_expert.task.offer_hands`, `work.werewolf_expert.value`, `work.werewolf_expert.village.ask_pot`, `work.werewolf_expert.village.ask_unpaid`, `work.werewolf_expert.village.say_thanks` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.werewolf_expert.more` — accepted phrasings: "when's the next one"
  - the message must contain one of: `next`, `moon`, `full`
  - scored words: `next`(1.2), `moon`(1.5), `full`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.followup.ask_more   [20 chars]
    en  When's the next one?
    >>  ............................................
    pt  Quando é a próxima?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.werewolf_expert.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.more
WHO    VILLAGER — what the player reads after pressing "When's the next one?"
       spoken on: conversations.topic.work.werewolf_expert.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.werewolf_expert.more`: the villager discloses. Subject `work.werewolf_expert.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.more/1   [81 chars]
    en  Eleven days. Everything I do between now and then is preparation for three hours.
    >>  ............................................
    pt  Onze dias. Tudo que eu faço daqui até lá é preparação pra três horas.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.more/2   [92 chars]
    en  Sooner than the village thinks. I'll have the arrangements made before anyone notices, %1$s.
    >>  ............................................
    pt  Antes do que o vilarejo pensa. Vou ter tudo arranjado antes que alguém repare, %1$s.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days. Two bolts and a stone cellar are all that stand between me and being a story forever.
    >>  ............................................
    pt  Onze dias. Dois ferrolhos e um porão de pedra é tudo entre mim e virar história pra sempre.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements written down. The first three years went wrong and nobody should have to repeat them.
    >>  ............................................
    pt  Os arranjos escritos. Os três primeiros anos deram errado e ninguém devia repetir.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days. It comes round the same way every month and there's a comfort in that.
    >>  ............................................
    pt  Onze dias. Vem do mesmo jeito todo mês e tem um conforto nisso.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements, written down eventually. Doors first, then dates, then people, in that order.
    >>  ............................................
    pt  Os arranjos, escritos uma hora. Portas primeiro, depois datas, depois pessoas, nessa ordem.
    >>  ............................................
  confident.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days. Everything I do between now and then is preparation for three hours.
    >>  ............................................
    pt  Onze dias. Tudo que eu faço daqui até lá é preparação pra três horas.
    >>  ............................................
  confident.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements written down for whoever comes next. Somebody always comes next.
    >>  ............................................
    pt  Os arranjos escritos pra quem vier depois. Sempre vem alguém depois.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days. Everything I do between now and then is preparation for three hours.
    >>  ............................................
    pt  Onze dias. Tudo que eu faço daqui até lá é preparação pra três horas.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements written down for whoever comes next. Somebody always comes next.
    >>  ............................................
    pt  Os arranjos escritos pra quem vier depois. Sempre vem alguém depois.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days. Three people know, and none of them was ever asked to help, and all three did.
    >>  ............................................
    pt  Onze dias. Três pessoas sabem, e nenhuma foi convidada a ajudar, e as três ajudaram.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements written down. The scribe would copy exactly and add nothing, which is the whole requirement.
    >>  ............................................
    pt  Os arranjos escritos. O escriba copiaria exatamente e não acrescentaria nada, que é todo o requisito.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days. Three people know, and none of them was ever asked to help, and all three did.
    >>  ............................................
    pt  Onze dias. Três pessoas sabem, e nenhuma foi convidada a ajudar, e as três ajudaram.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements written down. The scribe would copy exactly and add nothing, which is the whole requirement.
    >>  ............................................
    pt  Os arranjos escritos. O escriba copiaria exatamente e não acrescentaria nada, que é todo o requisito.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days. Three people know, and none of them was ever asked to help, and all three did.
    >>  ............................................
    pt  Onze dias. Três pessoas sabem, e nenhuma foi convidada a ajudar, e as três ajudaram.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements written down. The scribe would copy exactly and add nothing, which is the whole requirement.
    >>  ............................................
    pt  Os arranjos escritos. O escriba copiaria exatamente e não acrescentaria nada, que é todo o requisito.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days. Two bolts and a stone cellar are all that stand between me and being a story forever.
    >>  ............................................
    pt  Onze dias. Dois ferrolhos e um porão de pedra é tudo entre mim e virar história pra sempre.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements written down. The first three years went wrong and nobody should have to repeat them.
    >>  ............................................
    pt  Os arranjos escritos. Os três primeiros anos deram errado e ninguém devia repetir.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days. Everything I do between now and then is preparation for three hours.
    >>  ............................................
    pt  Onze dias. Tudo que eu faço daqui até lá é preparação pra três horas.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements written down for whoever comes next. Somebody always comes next.
    >>  ............................................
    pt  Os arranjos escritos pra quem vier depois. Sempre vem alguém depois.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days. Everything I do between now and then is preparation for three hours.
    >>  ............................................
    pt  Onze dias. Tudo que eu faço daqui até lá é preparação pra três horas.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements written down for whoever comes next. Somebody always comes next.
    >>  ............................................
    pt  Os arranjos escritos pra quem vier depois. Sempre vem alguém depois.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days. Four checks of the door a day for twenty-seven of them, and nine on the twenty-eighth.
    >>  ............................................
    pt  Onze dias. Quatro conferidas na porta por dia em vinte e sete deles, e nove no vigésimo oitavo.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements. Doors, dates, who to tell, what to say. Nobody writes any of that down.
    >>  ............................................
    pt  Os arranjos. Portas, datas, quem avisar, o que dizer. Ninguém anota nada disso.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days. It comes round the same way every month and there's a comfort in that.
    >>  ............................................
    pt  Onze dias. Vem do mesmo jeito todo mês e tem um conforto nisso.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements, written down eventually. Doors first, then dates, then people, in that order.
    >>  ............................................
    pt  Os arranjos, escritos uma hora. Portas primeiro, depois datas, depois pessoas, nessa ordem.
    >>  ............................................
  odd.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days. Four checks of the door a day for twenty-seven of them, and nine on the twenty-eighth.
    >>  ............................................
    pt  Onze dias. Quatro conferidas na porta por dia em vinte e sete deles, e nove no vigésimo oitavo.
    >>  ............................................
  odd.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements. Doors, dates, who to tell, what to say. Nobody writes any of that down.
    >>  ............................................
    pt  Os arranjos. Portas, datas, quem avisar, o que dizer. Ninguém anota nada disso.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days. It comes round the same way every month and there's a comfort in that.
    >>  ............................................
    pt  Onze dias. Vem do mesmo jeito todo mês e tem um conforto nisso.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements, written down eventually. Doors first, then dates, then people, in that order.
    >>  ............................................
    pt  Os arranjos, escritos uma hora. Portas primeiro, depois datas, depois pessoas, nessa ordem.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days! Everything between now and then is preparation for three hours. Excellent ratio.
    >>  ............................................
    pt  Onze dias! Tudo daqui até lá é preparação pra três horas. Proporção excelente.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements, written down. Everything published about people like me is about what we are, not about Tuesday.
    >>  ............................................
    pt  Os arranjos, escritos. Tudo publicado sobre gente como eu é sobre o que somos, não sobre a terça.
    >>  ............................................
  playful.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days! Everything between now and then is preparation for three hours. Excellent ratio.
    >>  ............................................
    pt  Onze dias! Tudo daqui até lá é preparação pra três horas. Proporção excelente.
    >>  ............................................
  playful.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements, written down. Everything published about people like me is about what we are, not about Tuesday.
    >>  ............................................
    pt  Os arranjos, escritos. Tudo publicado sobre gente como eu é sobre o que somos, não sobre a terça.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days. It comes round the same way every month and there's a comfort in that.
    >>  ............................................
    pt  Onze dias. Vem do mesmo jeito todo mês e tem um conforto nisso.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements, written down eventually. Doors first, then dates, then people, in that order.
    >>  ............................................
    pt  Os arranjos, escritos uma hora. Portas primeiro, depois datas, depois pessoas, nessa ordem.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days. Two bolts and a stone cellar are all that stand between me and being a story forever.
    >>  ............................................
    pt  Onze dias. Dois ferrolhos e um porão de pedra é tudo entre mim e virar história pra sempre.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements written down. The first three years went wrong and nobody should have to repeat them.
    >>  ............................................
    pt  Os arranjos escritos. Os três primeiros anos deram errado e ninguém devia repetir.
    >>  ............................................
  shy.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days. Four checks of the door a day for twenty-seven of them, and nine on the twenty-eighth.
    >>  ............................................
    pt  Onze dias. Quatro conferidas na porta por dia em vinte e sete deles, e nove no vigésimo oitavo.
    >>  ............................................
  shy.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements. Doors, dates, who to tell, what to say. Nobody writes any of that down.
    >>  ............................................
    pt  Os arranjos. Portas, datas, quem avisar, o que dizer. Ninguém anota nada disso.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days! Everything between now and then is preparation for three hours. Excellent ratio.
    >>  ............................................
    pt  Onze dias! Tudo daqui até lá é preparação pra três horas. Proporção excelente.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements, written down. Everything published about people like me is about what we are, not about Tuesday.
    >>  ............................................
    pt  Os arranjos, escritos. Tudo publicado sobre gente como eu é sobre o que somos, não sobre a terça.
    >>  ............................................
  witty.dialogue.conversations.work.prof.werewolf_expert.more/1
    en  Eleven days! Everything between now and then is preparation for three hours. Excellent ratio.
    >>  ............................................
    pt  Onze dias! Tudo daqui até lá é preparação pra três horas. Proporção excelente.
    >>  ............................................
  witty.dialogue.conversations.work.prof.werewolf_expert.more/2
    en  The arrangements, written down. Everything published about people like me is about what we are, not about Tuesday.
    >>  ............................................
    pt  Os arranjos, escritos. Tudo publicado sobre gente como eu é sobre o que somos, não sobre a terça.
    >>  ............................................
```

</details>


### Button `leave` — "Mind the calendar."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.werewolf_expert.challenge.landed`, `work.werewolf_expert.challenge.stung`, `work.werewolf_expert.craft.admire`, `work.werewolf_expert.craft.ask_alone`, `work.werewolf_expert.craft.ask_tell`, `work.werewolf_expert.future.ask_next`, `work.werewolf_expert.future.ask_written`, `work.werewolf_expert.future.encourage`, `work.werewolf_expert.hard`, `work.werewolf_expert.risk.ask_bolts`, `work.werewolf_expert.risk.ask_three_know`, `work.werewolf_expert.risk.sympathise`, `work.werewolf_expert.task.ask_often`, `work.werewolf_expert.task.ask_three_days`, `work.werewolf_expert.task.offer_hands`, `work.werewolf_expert.value`, `work.werewolf_expert.village.ask_pot`, `work.werewolf_expert.village.ask_unpaid`, `work.werewolf_expert.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.followup.leave   [18 chars]
    en  Mind the calendar.
    >>  ............................................
    pt  Fique de olho no calendário.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.leave
WHO    VILLAGER — what the player reads after pressing "Mind the calendar."
       spoken on: conversations.topic.work.werewolf_expert.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.left`: the villager accepts. Subject `work.werewolf_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.werewolf_expert.a_confidence.active.respond / leave; conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond / leave; conversations.scene.work.werewolf_expert.followup / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond / leave; conversations.topic.work.werewolf_expert.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.werewolf_expert.a_confidence.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.werewolf_expert.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.werewolf_expert.future` — e.g. "I'd like the arrangements written down for whoever comes next. Somebody always comes next and they start from nothing."


```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.werewolf_expert.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.werewolf_expert.future.respond   [30 chars]
    en  That's what's left to arrange.
    >>  ............................................
    pt  É o que falta arranjar.
    >>  ............................................
```


### Button `ask_written` — "Who would you trust to write them?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.werewolf_expert.future` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.werewolf_expert.future.ask_written` — accepted phrasings: "who would you trust to write them"
  - the message must contain one of: `written`, `trust`, `scribe`
  - scored words: `written`(1.5), `trust`(1.2), `scribe`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.future.respond.ask_written
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.future.respond.ask_written   [34 chars]
    en  Who would you trust to write them?
    >>  ............................................
    pt  Em quem você confiaria pra escrever?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.werewolf_expert.future.ask_written`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.werewolf_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "When's the next one?" | "Mind the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.future.ask_written
WHO    VILLAGER — what the player reads after pressing "Who would you trust to write them?"
       spoken on: conversations.topic.work.werewolf_expert.future.respond, button `ask_written`
       leaves the player on: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.future.ask_written`: the villager explains. Subject `work.werewolf_expert.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.future.ask_written/1   [92 chars]
    en  The scribe. He'd copy exactly and add nothing, and adding nothing is the entire requirement.
    >>  ............................................
    pt  No escriba. Ele copiaria exatamente e não acrescentaria nada, e não acrescentar é todo o requisito.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.future.ask_written/2   [99 chars]
    en  Nobody, until this conversation, %1$s. And now I've said the scribe out loud and it sounds obvious.
    >>  ............................................
    pt  Ninguém, até esta conversa, %1$s. E agora eu disse o escriba em voz alta e soa óbvio.
    >>  ............................................
```


### Button `encourage` — "Pay the mason by naming the cellar in what's written."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.werewolf_expert.future` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.werewolf_expert.future.encourage` — accepted phrasings: "pay the mason by naming the cellar in what's written"
  - the message must contain one of: `mason`, `naming`, `payment`
  - scored words: `mason`(1.5), `naming`(1.5), `payment`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.future.respond.encourage   [53 chars]
    en  Pay the mason by naming the cellar in what's written.
    >>  ............................................
    pt  Pague o pedreiro nomeando o porão no que for escrito.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.werewolf_expert.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.werewolf_expert.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.werewolf_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "When's the next one?" | "Mind the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.future.encourage
WHO    VILLAGER — what the player reads after pressing "Pay the mason by naming the cellar in what's written."
       spoken on: conversations.topic.work.werewolf_expert.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.future.encourage`: the villager accepts. Subject `work.werewolf_expert.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.future.encourage/1   [93 chars]
    en  ...Name him in it. He couldn't refuse that and he'd hate it and he would read it forty times.
    >>  ............................................
    pt  ...Nomeá-lo. Ele não poderia recusar e odiaria e leria quarenta vezes.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.future.encourage/2   [107 chars]
    en  That's payment he can't hand back, %1$s. Eleven years and I never thought of a thing he couldn't hand back.
    >>  ............................................
    pt  É um pagamento que ele não pode devolver, %1$s. Onze anos e eu nunca pensei em algo que ele não pudesse devolver.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it. He'd hate it and he'd keep it, and I'd not have to say the words.
    >>  ............................................
    pt  ...Nomeie ele nisso. Ele odiaria e guardaria, e eu não teria que dizer as palavras.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  That's payment he can't hand back. Eleven years of owing him and no way to say so.
    >>  ............................................
    pt  É um pagamento que não pode devolver. Onze anos devendo e sem jeito de dizer.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it. Men like him refuse everything except being written down.
    >>  ............................................
    pt  ...Nomeie ele nisso. Homens como ele recusam tudo, menos serem escritos.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  That's payment he can't hand back. Eleven years, and it needed somebody younger to see it.
    >>  ............................................
    pt  É um pagamento que não pode devolver. Onze anos, e precisou de alguém mais novo pra ver.
    >>  ............................................
  confident.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it. He couldn't refuse that, he'd hate it, and he'd read it forty times.
    >>  ............................................
    pt  ...Nomeie ele nisso. Ele não poderia recusar, odiaria, e leria quarenta vezes.
    >>  ............................................
  confident.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  That's payment he can't hand back. Eleven years and I never thought of one.
    >>  ............................................
    pt  É um pagamento que ele não pode devolver. Onze anos e eu nunca pensei em um.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it. He couldn't refuse that, he'd hate it, and he'd read it forty times.
    >>  ............................................
    pt  ...Nomeie ele nisso. Ele não poderia recusar, odiaria, e leria quarenta vezes.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  That's payment he can't hand back. Eleven years and I never thought of one.
    >>  ............................................
    pt  É um pagamento que ele não pode devolver. Onze anos e eu nunca pensei em um.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it, %1$s. He couldn't refuse, he'd hate it, and he'd read it forty times.
    >>  ............................................
    pt  ...Nomeie ele nisso, %1$s. Não poderia recusar, odiaria, e leria quarenta vezes.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  That's payment he can't hand back. Eleven years and it took you a sentence.
    >>  ............................................
    pt  É um pagamento que ele não pode devolver. Onze anos e você levou uma frase.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it, %1$s. He couldn't refuse, he'd hate it, and he'd read it forty times.
    >>  ............................................
    pt  ...Nomeie ele nisso, %1$s. Não poderia recusar, odiaria, e leria quarenta vezes.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  That's payment he can't hand back. Eleven years and it took you a sentence.
    >>  ............................................
    pt  É um pagamento que ele não pode devolver. Onze anos e você levou uma frase.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it, %1$s. He couldn't refuse, he'd hate it, and he'd read it forty times.
    >>  ............................................
    pt  ...Nomeie ele nisso, %1$s. Não poderia recusar, odiaria, e leria quarenta vezes.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  That's payment he can't hand back. Eleven years and it took you a sentence.
    >>  ............................................
    pt  É um pagamento que ele não pode devolver. Onze anos e você levou uma frase.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it. He'd hate it and he'd keep it, and I'd not have to say the words.
    >>  ............................................
    pt  ...Nomeie ele nisso. Ele odiaria e guardaria, e eu não teria que dizer as palavras.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  That's payment he can't hand back. Eleven years of owing him and no way to say so.
    >>  ............................................
    pt  É um pagamento que não pode devolver. Onze anos devendo e sem jeito de dizer.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it. He couldn't refuse that, he'd hate it, and he'd read it forty times.
    >>  ............................................
    pt  ...Nomeie ele nisso. Ele não poderia recusar, odiaria, e leria quarenta vezes.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  That's payment he can't hand back. Eleven years and I never thought of one.
    >>  ............................................
    pt  É um pagamento que ele não pode devolver. Onze anos e eu nunca pensei em um.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it. He couldn't refuse that, he'd hate it, and he'd read it forty times.
    >>  ............................................
    pt  ...Nomeie ele nisso. Ele não poderia recusar, odiaria, e leria quarenta vezes.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  That's payment he can't hand back. Eleven years and I never thought of one.
    >>  ............................................
    pt  É um pagamento que ele não pode devolver. Onze anos e eu nunca pensei em um.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it. He couldn't refuse.
    >>  ............................................
    pt  ...Nomeie ele nisso. Não poderia recusar.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  Payment he can't hand back. Eleven years.
    >>  ............................................
    pt  Pagamento que não pode devolver. Onze anos.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it. Men like him refuse everything except being written down.
    >>  ............................................
    pt  ...Nomeie ele nisso. Homens como ele recusam tudo, menos serem escritos.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  That's payment he can't hand back. Eleven years, and it needed somebody younger to see it.
    >>  ............................................
    pt  É um pagamento que não pode devolver. Onze anos, e precisou de alguém mais novo pra ver.
    >>  ............................................
  odd.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it. He couldn't refuse.
    >>  ............................................
    pt  ...Nomeie ele nisso. Não poderia recusar.
    >>  ............................................
  odd.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  Payment he can't hand back. Eleven years.
    >>  ............................................
    pt  Pagamento que não pode devolver. Onze anos.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it. Men like him refuse everything except being written down.
    >>  ............................................
    pt  ...Nomeie ele nisso. Homens como ele recusam tudo, menos serem escritos.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  That's payment he can't hand back. Eleven years, and it needed somebody younger to see it.
    >>  ............................................
    pt  É um pagamento que não pode devolver. Onze anos, e precisou de alguém mais novo pra ver.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it! He couldn't refuse, he'd hate it, and he'd read it forty times.
    >>  ............................................
    pt  ...Nomeie ele nisso! Não poderia recusar, odiaria, e leria quarenta vezes.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  That's payment he can't hand back. Eleven years and I never once thought of one.
    >>  ............................................
    pt  É um pagamento que ele não pode devolver. Onze anos e nunca pensei em um.
    >>  ............................................
  playful.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it! He couldn't refuse, he'd hate it, and he'd read it forty times.
    >>  ............................................
    pt  ...Nomeie ele nisso! Não poderia recusar, odiaria, e leria quarenta vezes.
    >>  ............................................
  playful.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  That's payment he can't hand back. Eleven years and I never once thought of one.
    >>  ............................................
    pt  É um pagamento que ele não pode devolver. Onze anos e nunca pensei em um.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it. Men like him refuse everything except being written down.
    >>  ............................................
    pt  ...Nomeie ele nisso. Homens como ele recusam tudo, menos serem escritos.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  That's payment he can't hand back. Eleven years, and it needed somebody younger to see it.
    >>  ............................................
    pt  É um pagamento que não pode devolver. Onze anos, e precisou de alguém mais novo pra ver.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it. He'd hate it and he'd keep it, and I'd not have to say the words.
    >>  ............................................
    pt  ...Nomeie ele nisso. Ele odiaria e guardaria, e eu não teria que dizer as palavras.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  That's payment he can't hand back. Eleven years of owing him and no way to say so.
    >>  ............................................
    pt  É um pagamento que não pode devolver. Onze anos devendo e sem jeito de dizer.
    >>  ............................................
  shy.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it. He couldn't refuse.
    >>  ............................................
    pt  ...Nomeie ele nisso. Não poderia recusar.
    >>  ............................................
  shy.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  Payment he can't hand back. Eleven years.
    >>  ............................................
    pt  Pagamento que não pode devolver. Onze anos.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it! He couldn't refuse, he'd hate it, and he'd read it forty times.
    >>  ............................................
    pt  ...Nomeie ele nisso! Não poderia recusar, odiaria, e leria quarenta vezes.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  That's payment he can't hand back. Eleven years and I never once thought of one.
    >>  ............................................
    pt  É um pagamento que ele não pode devolver. Onze anos e nunca pensei em um.
    >>  ............................................
  witty.dialogue.conversations.work.prof.werewolf_expert.future.encourage/1
    en  ...Name him in it! He couldn't refuse, he'd hate it, and he'd read it forty times.
    >>  ............................................
    pt  ...Nomeie ele nisso! Não poderia recusar, odiaria, e leria quarenta vezes.
    >>  ............................................
  witty.dialogue.conversations.work.prof.werewolf_expert.future.encourage/2
    en  That's payment he can't hand back. Eleven years and I never once thought of one.
    >>  ............................................
    pt  É um pagamento que ele não pode devolver. Onze anos e nunca pensei em um.
    >>  ............................................
```

</details>


### Button `ask_next` — "Does somebody always come next?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.werewolf_expert.future` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.werewolf_expert.future.ask_next` — accepted phrasings: "does somebody always come next"
  - the message must contain one of: `next`, `always`, `successor`
  - scored words: `next`(1.5), `always`(1.0), `successor`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.future.respond.ask_next
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.future.respond.ask_next   [31 chars]
    en  Does somebody always come next?
    >>  ............................................
    pt  Sempre vem alguém depois?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.werewolf_expert.future.ask_next`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.werewolf_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "When's the next one?" | "Mind the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.future.ask_next
WHO    VILLAGER — what the player reads after pressing "Does somebody always come next?"
       spoken on: conversations.topic.work.werewolf_expert.future.respond, button `ask_next`
       leaves the player on: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.future.ask_next`: the villager explains. Subject `work.werewolf_expert.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.future.ask_next/1   [106 chars]
    en  Somewhere. Not here, necessarily, and not soon — but somebody, and they'll have the first three bad years.
    >>  ............................................
    pt  Em algum lugar. Não aqui, necessariamente, e não logo — mas alguém, e vão ter os três primeiros anos ruins.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.future.ask_next/2   [97 chars]
    en  There was somebody before me, four valleys east, %1$s, and I found out too late to write to them.
    >>  ............................................
    pt  Havia alguém antes de mim, a quatro vales a leste, %1$s, e eu descobri tarde demais pra escrever.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the calendar."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.werewolf_expert.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.future.respond.leave   [38 chars]
    en  I'll let you get back to the calendar.
    >>  ............................................
    pt  Vou deixar você voltar ao calendário.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the calendar."
       spoken on: conversations.topic.work.werewolf_expert.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.left`: the villager accepts. Subject `work.werewolf_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.werewolf_expert.a_confidence.active.respond / leave; conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond / leave; conversations.scene.work.werewolf_expert.followup / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond / leave; conversations.topic.work.werewolf_expert.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.werewolf_expert.a_confidence.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.werewolf_expert.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.werewolf_expert` — e.g. "I consult on the moon-touched. The trick is scheduling around the calendar. THEIR calendar."


```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.werewolf_expert.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.werewolf_expert.respond   [37 chars]
    en  That's the calendar and who keeps it.
    >>  ............................................
    pt  É o calendário e quem cuida dele.
    >>  ............................................
```


### Button `ask_hard` — "What does an unprepared month look like?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.werewolf_expert.identity` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.werewolf_expert.hard` — accepted phrasings: "what does an unprepared month look like"
  - the message must contain one of: `unprepared`, `month`
  - scored words: `unprepared`(1.5), `month`(1.2), `goes`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.respond.ask_hard   [40 chars]
    en  What does an unprepared month look like?
    >>  ............................................
    pt  Como é um mês despreparado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.werewolf_expert.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.werewolf_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "When's the next one?" | "Mind the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.hard
WHO    VILLAGER — what the player reads after pressing "What does an unprepared month look like?"
       spoken on: conversations.topic.work.werewolf_expert.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.hard`: the villager explains. Subject `work.werewolf_expert.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.werewolf_expert.followup / ask_more
```

> Written out in full under **`conversations.scene.work.werewolf_expert.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "You treat them like people, not problems."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.werewolf_expert.identity` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.werewolf_expert.value` — accepted phrasings: "you treat them like people, not problems"
  - the message must contain one of: `people`, `problems`, `treat`
  - scored words: `people`(1.2), `problems`(1.5), `treat`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.respond.value   [41 chars]
    en  You treat them like people, not problems.
    >>  ............................................
    pt  Você trata eles como pessoas, não como problemas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.werewolf_expert.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.werewolf_expert.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.werewolf_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "When's the next one?" | "Mind the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.value
WHO    VILLAGER — what the player reads after pressing "You treat them like people, not problems."
       spoken on: conversations.topic.work.werewolf_expert.respond, button `value`
       leaves the player on: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.value`: the villager accepts. Subject `work.werewolf_expert.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.value/1   [87 chars]
    en  They are people. The condition is a Tuesday every month; the person is every other day.
    >>  ............................................
    pt  São pessoas. A condição é uma terça-feira por mês; a pessoa é todos os outros dias.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.value/2   [89 chars]
    en  ...That's the thing I'd want said about the work, if anybody ever said anything about it.
    >>  ............................................
    pt  ...É a coisa que eu gostaria que dissessem do trabalho, se alguém dissesse alguma coisa.
    >>  ............................................
```


### Button `challenge` — "You're keeping something dangerous in the village."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.werewolf_expert.identity` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.werewolf_expert.challenge` — accepted phrasings: "you're keeping something dangerous in the village"
  - the message must contain one of: `dangerous`, `keeping`
  - scored words: `dangerous`(1.5), `keeping`(1.2), `village`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.respond.challenge   [50 chars]
    en  You're keeping something dangerous in the village.
    >>  ............................................
    pt  Você está mantendo algo perigoso no vilarejo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.werewolf_expert.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.werewolf_expert.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.werewolf_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "When's the next one?" | "Mind the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.challenge.landed
WHO    VILLAGER — what the player reads after pressing "You're keeping something dangerous in the village."
       spoken on: conversations.topic.work.werewolf_expert.respond, button `challenge`
       leaves the player on: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.challenge.landed`: the villager resists. Subject `work.werewolf_expert.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.challenge.landed/1   [78 chars]
    en  Someone, not something. And yes — safely, on a schedule, with their agreement.
    >>  ............................................
    pt  Alguém, não algo. E sim — com segurança, num cronograma, com o consentimento deles.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.challenge.landed/2   [89 chars]
    en  Dangerous three nights a year and my neighbour the rest, %1$s. Which do you want managed?
    >>  ............................................
    pt  Perigoso três noites por ano e meu vizinho o resto, %1$s. Qual você quer administrado?
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.werewolf_expert.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.werewolf_expert.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.werewolf_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "When's the next one?" | "Mind the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.challenge.stung
WHO    VILLAGER — what the player reads after pressing "You're keeping something dangerous in the village."
       spoken on: conversations.topic.work.werewolf_expert.respond, button `challenge`
       leaves the player on: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.challenge.stung`: the villager resists. Subject `work.werewolf_expert.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.challenge.stung/1   [69 chars]
    en  ...Say 'something' again and see whether I keep helping this village.
    >>  ............................................
    pt  ...Diga 'algo' de novo e veja se eu continuo ajudando este vilarejo.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.challenge.stung/2   [71 chars]
    en  Something. Right. That word is exactly why they hide instead of asking.
    >>  ............................................
    pt  Algo. Certo. É exatamente essa palavra que faz eles se esconderem em vez de pedir ajuda.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the calendar."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.werewolf_expert.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.respond.leave   [38 chars]
    en  I'll let you get back to the calendar.
    >>  ............................................
    pt  Vou deixar você voltar ao calendário.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the calendar."
       spoken on: conversations.topic.work.werewolf_expert.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.left`: the villager accepts. Subject `work.werewolf_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.werewolf_expert.a_confidence.active.respond / leave; conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond / leave; conversations.scene.work.werewolf_expert.followup / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond / leave; conversations.topic.work.werewolf_expert.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.werewolf_expert.a_confidence.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.werewolf_expert.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.werewolf_expert.risk` — e.g. "Two bolts and a stone cellar are all that stand between me and being the story this valley tells forever."


```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.werewolf_expert.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.werewolf_expert.risk.respond   [24 chars]
    en  That's what it rests on.
    >>  ............................................
    pt  É nisso que se apoia.
    >>  ............................................
```


### Button `ask_bolts` — "Two bolts is a thin margin."

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.werewolf_expert.risk` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.werewolf_expert.risk.ask_bolts` — accepted phrasings: "two bolts is a thin margin"
  - the message must contain one of: `bolts`, `margin`, `thin`
  - scored words: `bolts`(1.5), `margin`(1.2), `thin`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.risk.respond.ask_bolts
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.risk.respond.ask_bolts   [27 chars]
    en  Two bolts is a thin margin.
    >>  ............................................
    pt  Dois ferrolhos é uma margem fina.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.werewolf_expert.risk.ask_bolts`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.werewolf_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "When's the next one?" | "Mind the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.risk.ask_bolts
WHO    VILLAGER — what the player reads after pressing "Two bolts is a thin margin."
       spoken on: conversations.topic.work.werewolf_expert.risk.respond, button `ask_bolts`
       leaves the player on: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.risk.ask_bolts`: the villager explains. Subject `work.werewolf_expert.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.risk.ask_bolts/1   [105 chars]
    en  It's eleven years of margin and it has never been tested, and I check it nine times on the twenty-eighth.
    >>  ............................................
    pt  São onze anos de margem e nunca foi testada, e eu confiro nove vezes no vigésimo oitavo.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.risk.ask_bolts/2   [105 chars]
    en  The mason set the frame himself and asked me no questions about the load, %1$s. That's the actual margin.
    >>  ............................................
    pt  O pedreiro montou o batente e não me fez pergunta nenhuma sobre a carga, %1$s. É essa a margem real.
    >>  ............................................
```


### Button `sympathise` — "Living as though they won't is a decision you make every day."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.werewolf_expert.risk` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.werewolf_expert.risk.sympathise` — accepted phrasings: "living as though they won't is a decision you make every day"
  - the message must contain one of: `decision`, `living`
  - scored words: `decision`(1.5), `living`(1.5), `every`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.risk.respond.sympathise   [61 chars]
    en  Living as though they won't is a decision you make every day.
    >>  ............................................
    pt  Viver como se não fossem é uma decisão que você toma todo dia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.werewolf_expert.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.werewolf_expert.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.werewolf_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "When's the next one?" | "Mind the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "Living as though they won't is a decision you make every day."
       spoken on: conversations.topic.work.werewolf_expert.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.risk.sympathise`: the villager accepts. Subject `work.werewolf_expert.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.risk.sympathise/1   [100 chars]
    en  ...Every morning, before anything else. It's the first thing I do and I've never described it aloud.
    >>  ............................................
    pt  ...Toda manhã, antes de tudo. É a primeira coisa que eu faço e eu nunca descrevi em voz alta.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.risk.sympathise/2   [97 chars]
    en  Every day for eleven years, %1$s, and it has not once got easier and it has also not once failed.
    >>  ............................................
    pt  Todo dia por onze anos, %1$s, e nunca ficou mais fácil e também nunca falhou.
    >>  ............................................
```


### Button `ask_three_know` — "Why those three and nobody else?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.werewolf_expert.risk` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.werewolf_expert.risk.ask_three_know` — accepted phrasings: "why those three and nobody else"
  - the message must contain one of: `three`, `else`, `chosen`
  - scored words: `three`(1.2), `else`(1.2), `chosen`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.risk.respond.ask_three_know
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.risk.respond.ask_three_know   [32 chars]
    en  Why those three and nobody else?
    >>  ............................................
    pt  Por que esses três e mais ninguém?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.werewolf_expert.risk.ask_three_know`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.werewolf_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "When's the next one?" | "Mind the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.risk.ask_three_know
WHO    VILLAGER — what the player reads after pressing "Why those three and nobody else?"
       spoken on: conversations.topic.work.werewolf_expert.risk.respond, button `ask_three_know`
       leaves the player on: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.risk.ask_three_know`: the villager explains. Subject `work.werewolf_expert.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.risk.ask_three_know/1   [86 chars]
    en  Because each of them needs to know one thing and none of them needs to know all three.
    >>  ............................................
    pt  Porque cada um precisa saber uma coisa e nenhum precisa saber as três.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.risk.ask_three_know/2   [89 chars]
    en  Because three is the largest number I could hold in my head at four in the morning, %1$s.
    >>  ............................................
    pt  Porque três é o maior número que eu consigo segurar na cabeça às quatro da manhã, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the calendar."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.werewolf_expert.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.risk.respond.leave   [38 chars]
    en  I'll let you get back to the calendar.
    >>  ............................................
    pt  Vou deixar você voltar ao calendário.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the calendar."
       spoken on: conversations.topic.work.werewolf_expert.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.left`: the villager accepts. Subject `work.werewolf_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.werewolf_expert.a_confidence.active.respond / leave; conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond / leave; conversations.scene.work.werewolf_expert.followup / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond / leave; conversations.topic.work.werewolf_expert.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.werewolf_expert.a_confidence.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.werewolf_expert.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.werewolf_expert.task` — e.g. "Marking the calendar. Three days a month I'm not available and everybody has learned not to ask why."


```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.werewolf_expert.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.werewolf_expert.task.respond   [27 chars]
    en  That's the month, arranged.
    >>  ............................................
    pt  É o mês, arranjado.
    >>  ............................................
```


### Button `ask_three_days` — "How do people take the three days?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.werewolf_expert.task` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.werewolf_expert.task.ask_three_days` — accepted phrasings: "how do people take the three days"
  - the message must contain one of: `days`, `three`
  - scored words: `days`(1.2), `three`(1.2), `people`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.task.respond.ask_three_days
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.task.respond.ask_three_days   [34 chars]
    en  How do people take the three days?
    >>  ............................................
    pt  Como as pessoas lidam com os três dias?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.werewolf_expert.task.ask_three_days`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.werewolf_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "When's the next one?" | "Mind the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.task.ask_three_days
WHO    VILLAGER — what the player reads after pressing "How do people take the three days?"
       spoken on: conversations.topic.work.werewolf_expert.task.respond, button `ask_three_days`
       leaves the player on: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.task.ask_three_days`: the villager explains. Subject `work.werewolf_expert.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.task.ask_three_days/1   [81 chars]
    en  Better than I expected and worse than I'd like. Nobody asks and everybody counts.
    >>  ............................................
    pt  Melhor do que eu esperava e pior do que eu gostaria. Ninguém pergunta e todos contam.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.task.ask_three_days/2   [101 chars]
    en  The mason schedules around it without ever mentioning it, %1$s. That's the kindest thing anyone does.
    >>  ............................................
    pt  O pedreiro agenda em volta sem nunca mencionar, %1$s. É a coisa mais gentil que alguém faz.
    >>  ............................................
```


### Button `offer_hands` — "I could check the cellar door with you."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.werewolf_expert.task` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.werewolf_expert.task.offer_hands` — accepted phrasings: "i could check the cellar door with you"
  - the message must contain one of: `cellar`, `door`, `bolts`
  - scored words: `cellar`(1.5), `door`(1.2), `bolts`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.task.respond.offer_hands   [39 chars]
    en  I could check the cellar door with you.
    >>  ............................................
    pt  Eu podia conferir a porta do porão com você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.werewolf_expert.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.werewolf_expert.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.werewolf_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "When's the next one?" | "Mind the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I could check the cellar door with you."
       spoken on: conversations.topic.work.werewolf_expert.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.task.offer_hands`: the villager accepts. Subject `work.werewolf_expert.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.task.offer_hands/1   [103 chars]
    en  ...From the outside. Both bolts, top and bottom, and tell me the number out loud. Two. It's always two.
    >>  ............................................
    pt  ...Por fora. Os dois ferrolhos, cima e baixo, e me diga o número em voz alta. Dois. É sempre dois.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.task.offer_hands/2   [97 chars]
    en  You'd be the first person ever to look at it with me, %1$s. That's a larger offer than you meant.
    >>  ............................................
    pt  Você seria a primeira pessoa a olhar isso comigo, %1$s. É uma oferta maior do que você quis fazer.
    >>  ............................................
```


### Button `ask_often` — "How often do you check it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.werewolf_expert.task` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.werewolf_expert.task.ask_often` — accepted phrasings: "how often do you check it"
  - the message must contain one of: `often`, `check`, `times`
  - scored words: `often`(1.5), `check`(1.2), `times`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.task.respond.ask_often
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.task.respond.ask_often   [26 chars]
    en  How often do you check it?
    >>  ............................................
    pt  Com que frequência você confere?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.werewolf_expert.task.ask_often`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.werewolf_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "When's the next one?" | "Mind the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.task.ask_often
WHO    VILLAGER — what the player reads after pressing "How often do you check it?"
       spoken on: conversations.topic.work.werewolf_expert.task.respond, button `ask_often`
       leaves the player on: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.task.ask_often`: the villager explains. Subject `work.werewolf_expert.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.task.ask_often/1   [81 chars]
    en  Four times a day for twenty-seven days and about nine times on the twenty-eighth.
    >>  ............................................
    pt  Quatro vezes por dia por vinte e sete dias e umas nove vezes no vigésimo oitavo.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.task.ask_often/2   [97 chars]
    en  More than is sensible and less than I want to, %1$s. That's where I've settled and it took years.
    >>  ............................................
    pt  Mais do que é sensato e menos do que eu queria, %1$s. É onde eu me assentei e levou anos.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the calendar."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.werewolf_expert.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.task.respond.leave   [38 chars]
    en  I'll let you get back to the calendar.
    >>  ............................................
    pt  Vou deixar você voltar ao calendário.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the calendar."
       spoken on: conversations.topic.work.werewolf_expert.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.left`: the villager accepts. Subject `work.werewolf_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.werewolf_expert.a_confidence.active.respond / leave; conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond / leave; conversations.scene.work.werewolf_expert.followup / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond / leave; conversations.topic.work.werewolf_expert.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.werewolf_expert.a_confidence.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.werewolf_expert.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.werewolf_expert.village` — e.g. "Eleven years and this valley has never had a night because of me. That's the only number I keep."


```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.werewolf_expert.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.werewolf_expert.village.respond   [25 chars]
    en  That's the balance of it.
    >>  ............................................
    pt  É esse o equilíbrio.
    >>  ............................................
```


### Button `ask_unpaid` — "Have you tried to pay them?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.werewolf_expert.village` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.werewolf_expert.village.ask_unpaid` — accepted phrasings: "have you tried to pay them"
  - the message must contain one of: `paid`, `refused`
  - scored words: `paid`(1.5), `refused`(1.2), `tried`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.village.respond.ask_unpaid
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.village.respond.ask_unpaid   [27 chars]
    en  Have you tried to pay them?
    >>  ............................................
    pt  Você tentou pagá-los?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.werewolf_expert.village.ask_unpaid`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.werewolf_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "When's the next one?" | "Mind the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.village.ask_unpaid
WHO    VILLAGER — what the player reads after pressing "Have you tried to pay them?"
       spoken on: conversations.topic.work.werewolf_expert.village.respond, button `ask_unpaid`
       leaves the player on: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.village.ask_unpaid`: the villager explains. Subject `work.werewolf_expert.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.village.ask_unpaid/1   [91 chars]
    en  The mason refused twice and the second refusal was rude enough that I've never tried again.
    >>  ............................................
    pt  O pedreiro recusou duas vezes e a segunda recusa foi rude o bastante pra eu nunca mais tentar.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.village.ask_unpaid/2   [97 chars]
    en  The cook took a sack of flour once, %1$s, and I think she took it so that I'd have paid somebody.
    >>  ............................................
    pt  O cozinheiro aceitou um saco de farinha uma vez, %1$s, e eu acho que aceitou pra eu ter pago alguém.
    >>  ............................................
```


### Button `say_thanks` — "Eleven years of nothing happening is entirely your doing."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.werewolf_expert.village` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.werewolf_expert.village.say_thanks` — accepted phrasings: "eleven years of nothing happening is entirely your doing"
  - the message must contain one of: `eleven`, `doing`, `record`
  - scored words: `eleven`(1.2), `doing`(1.5), `record`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.village.respond.say_thanks   [57 chars]
    en  Eleven years of nothing happening is entirely your doing.
    >>  ............................................
    pt  Onze anos sem nada acontecer é inteiramente obra sua.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.werewolf_expert.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.werewolf_expert.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.werewolf_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "When's the next one?" | "Mind the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Eleven years of nothing happening is entirely your doing."
       spoken on: conversations.topic.work.werewolf_expert.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.village.say_thanks`: the villager accepts. Subject `work.werewolf_expert.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.village.say_thanks/1   [96 chars]
    en  ...Mine and two bolts and three people. I'd not take the whole of it and I'll take the sentence.
    >>  ............................................
    pt  ...Minha e de dois ferrolhos e três pessoas. Eu não pegaria tudo e eu aceito a frase.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.village.say_thanks/2   [93 chars]
    en  It's a record of an absence, %1$s. You're the second person ever to call an absence a record.
    >>  ............................................
    pt  É um registro de uma ausência, %1$s. Você é a segunda pessoa a chamar uma ausência de registro.
    >>  ............................................
```


### Button `ask_pot` — "The cook covers the pot for you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.werewolf_expert.village` · offered only once the villager has actually said `work:werewolf_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.werewolf_expert.village.ask_pot` — accepted phrasings: "the cook covers the pot for you"
  - the message must contain one of: `pot`, `covers`, `cook`
  - scored words: `pot`(1.5), `covers`(1.2), `cook`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.village.respond.ask_pot
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.village.respond.ask_pot   [32 chars]
    en  The cook covers the pot for you?
    >>  ............................................
    pt  O cozinheiro cobre o caldeirão por você?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.werewolf_expert.village.ask_pot`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.werewolf_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "When's the next one?" | "Mind the calendar."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.village.ask_pot
WHO    VILLAGER — what the player reads after pressing "The cook covers the pot for you?"
       spoken on: conversations.topic.work.werewolf_expert.village.respond, button `ask_pot`
       leaves the player on: conversations.topic.work.werewolf_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.village.ask_pot`: the villager explains. Subject `work.werewolf_expert.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:werewolf_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.werewolf_expert.village.ask_pot/1   [112 chars]
    en  Three days a month, without ever asking a question, for eleven years. That is a month a year of somebody's life.
    >>  ............................................
    pt  Três dias por mês, sem nunca fazer uma pergunta, por onze anos. É um mês por ano da vida de alguém.
    >>  ............................................
  dialogue.conversations.work.prof.werewolf_expert.village.ask_pot/2   [75 chars]
    en  She calls it the rota, %1$s. There is no rota. There has never been a rota.
    >>  ............................................
    pt  Ele chama de escala, %1$s. Não existe escala. Nunca existiu escala.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the calendar."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.werewolf_expert.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.werewolf_expert.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.werewolf_expert.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.werewolf_expert.village.respond.leave   [38 chars]
    en  I'll let you get back to the calendar.
    >>  ............................................
    pt  Vou deixar você voltar ao calendário.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.werewolf_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the calendar."
       spoken on: conversations.topic.work.werewolf_expert.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.werewolf_expert.left`: the villager accepts. Subject `work.werewolf_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.werewolf_expert.a_confidence.active.respond / leave; conversations.scene.work.werewolf_expert.a_confidence.succeeded.respond / leave; conversations.scene.work.werewolf_expert.followup / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.blocked.respond / leave; conversations.scene.work.werewolf_expert.pressed_for_a_name.succeeded.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.blocked.respond / leave; conversations.scene.work.werewolf_expert.unfunded_precautions.succeeded.respond / leave; conversations.topic.work.werewolf_expert.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.werewolf_expert.a_confidence.active.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

