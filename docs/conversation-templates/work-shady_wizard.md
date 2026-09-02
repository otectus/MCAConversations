# Work talk with a shady wizard

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.shady_wizard.followup`](#conversations-scene-work-shady-wizard-followup)
- [`conversations.scene.work.shady_wizard.inherited_page.active.respond`](#conversations-scene-work-shady-wizard-inherited-page-active-respond)
- [`conversations.scene.work.shady_wizard.inherited_page.succeeded.respond`](#conversations-scene-work-shady-wizard-inherited-page-succeeded-respond)
- [`conversations.scene.work.shady_wizard.misfire.blocked.respond`](#conversations-scene-work-shady-wizard-misfire-blocked-respond)
- [`conversations.scene.work.shady_wizard.misfire.succeeded.respond`](#conversations-scene-work-shady-wizard-misfire-succeeded-respond)
- [`conversations.scene.work.shady_wizard.the_name_they_use.active.respond`](#conversations-scene-work-shady-wizard-the-name-they-use-active-respond)
- [`conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond`](#conversations-scene-work-shady-wizard-the-name-they-use-succeeded-respond)
- [`conversations.topic.work.shady_wizard.craft.respond`](#conversations-topic-work-shady-wizard-craft-respond)
- [`conversations.topic.work.shady_wizard.followup`](#conversations-topic-work-shady-wizard-followup)
- [`conversations.topic.work.shady_wizard.future.respond`](#conversations-topic-work-shady-wizard-future-respond)
- [`conversations.topic.work.shady_wizard.respond`](#conversations-topic-work-shady-wizard-respond)
- [`conversations.topic.work.shady_wizard.risk.respond`](#conversations-topic-work-shady-wizard-risk-respond)
- [`conversations.topic.work.shady_wizard.task.respond`](#conversations-topic-work-shady-wizard-task-respond)
- [`conversations.topic.work.shady_wizard.village.respond`](#conversations-topic-work-shady-wizard-village-respond)

---

## `conversations.scene.work.shady_wizard.followup`

**Reached from 10 route(s):** `conversations.scene.work.shady_wizard.inherited_page.active.respond` / `ask_what_she_thinks`; `conversations.scene.work.shady_wizard.inherited_page.active.respond` / `suggest_asking_someone`; `conversations.scene.work.shady_wizard.inherited_page.succeeded.respond` / `note_the_restraint`; `conversations.scene.work.shady_wizard.misfire.blocked.respond` / `ask_why_stop`; `conversations.scene.work.shady_wizard.misfire.blocked.respond` / `offer_lapis`; `conversations.scene.work.shady_wizard.misfire.blocked.respond` / `back_the_caution`; `conversations.scene.work.shady_wizard.misfire.succeeded.respond` / `ask_about_the_notes`; `conversations.scene.work.shady_wizard.the_name_they_use.active.respond` / `ask_why_the_word`; `conversations.scene.work.shady_wizard.the_name_they_use.active.respond` / `advise_showing_the_notes`; `conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond` / `note_the_word`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.shady_wizard.inherited_page.active.accepted` — e.g. "A copyist would read the letters without knowing what they mean, which is exactly the help I need and I had not thought of it."
- `conversations.scene.work.shady_wizard.inherited_page.active.declined_politely` — e.g. "I am not going to say, and that is not mystery-making. A half-read instruction repeated out loud is how somebody else tries it."
- `conversations.scene.work.shady_wizard.inherited_page.succeeded.acknowledged` — e.g. "Two years of not doing a thing, and the reward is finding out it would have been harmless. That is how it always goes and it proves nothing."
- `conversations.scene.work.shady_wizard.misfire.blocked.accepted` — e.g. "Then I can run it four times at a quarter strength and find out which step is the liar."
- `conversations.scene.work.shady_wizard.misfire.blocked.explained` — e.g. "Because a working I do not understand is one I cannot repeat and cannot avoid, and both halves of that are dangerous."
- `conversations.scene.work.shady_wizard.misfire.blocked.steadied` — e.g. "It is the only rule I have that has never let me down, and it is also the rule that makes me look slow and evasive."
- `conversations.scene.work.shady_wizard.misfire.succeeded.explained` — e.g. "Everything, including the weather and what I had eaten, because the third time something goes wrong the pattern is always in the boring column."
- `conversations.scene.work.shady_wizard.the_name_they_use.active.considered` — e.g. "One person. The cleric, probably, who will not understand a word of it and will understand exactly what it is."
- `conversations.scene.work.shady_wizard.the_name_they_use.active.explained` — e.g. "From the trade, mostly. There is no guild and no examination, so anybody with a shed can call themselves what I call myself."
- `conversations.scene.work.shady_wizard.the_name_they_use.succeeded.acknowledged` — e.g. "It is the only word I have ever wanted and it took a notebook full of failures to get it, which is a joke I enjoy."


```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.shady_wizard.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.shady_wizard.followup   [20 chars]
    en  Anything else, then?
    >>  ............................................
    pt  Mais alguma coisa, então?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of a working that misfires?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.shady_wizard.*` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shady_wizard.followup.ask_more` — accepted phrasings: "whats the hardest part of a working that misfires"; "what is the hardest part of a working that misfires"; "hardest thing about a misfire"
  - the message must contain one of: `hardest`, `misfires`
  - scored words: `hardest`(1.8), `misfires`(1.8), `whats`(0.8), `part`(0.8), `working`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shady_wizard.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shady_wizard.followup.ask_more   [51 chars]
    en  What's the hardest part of a working that misfires?
    >>  ............................................
    pt  Qual é a parte mais difícil de um trabalho que falha?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shady_wizard.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shady_wizard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there anything you won't sell?" | "No refunds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of a working that misfires?"
       spoken on: conversations.scene.work.shady_wizard.followup, button `ask_more`
       leaves the player on: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.hard`: the villager explains. Subject `work.shady_wizard.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.shady_wizard.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.shady_wizard.hard/1   [78 chars]
    en  It doesn't. There is no mechanism by which it could. ...It has happened twice.
    >>  ............................................
    pt  Não dá. Não existe mecanismo pelo qual pudesse. ...Aconteceu duas vezes.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.hard/2   [85 chars]
    en  Then we have a conversation like this one, %1$s, but with more shouting on your side.
    >>  ............................................
    pt  Aí a gente tem uma conversa como esta, %1$s, mas com mais gritaria do seu lado.
    >>  ............................................
```


### Button `leave` — "I'll leave you to your notes."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.shady_wizard.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shady_wizard.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shady_wizard.followup.leave   [29 chars]
    en  I'll leave you to your notes.
    >>  ............................................
    pt  Vou deixar você com suas anotações.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to your notes."
       spoken on: conversations.scene.work.shady_wizard.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.left`: the villager accepts. Subject `work.shady_wizard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shady_wizard.inherited_page.active.respond / leave; conversations.scene.work.shady_wizard.inherited_page.succeeded.respond / leave; conversations.scene.work.shady_wizard.misfire.blocked.respond / leave; conversations.scene.work.shady_wizard.misfire.succeeded.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.active.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond / leave; conversations.topic.work.shady_wizard.craft.respond / leave; conversations.topic.work.shady_wizard.followup / leave …and 5 more
```

```text
  dialogue.conversations.work.prof.shady_wizard.leave/1   [29 chars]
    en  Do. And remember: no refunds.
    >>  ............................................
    pt  Pode ir. E lembre: sem reembolso.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.leave/2   [45 chars]
    en  A pleasure, %1$s. Don't read the small print.
    >>  ............................................
    pt  Um prazer, %1$s. Não leia as letras miúdas.
    >>  ............................................
```

---


## `conversations.scene.work.shady_wizard.inherited_page.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.shady_wizard.inherited_page.active` — e.g. "I have %2$s that came with the shed and I have been looking at it for two years without getting anywhere."


```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.inherited_page.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.shady_wizard.inherited_page.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.shady_wizard.inherited_page.active.respond   [24 chars]
    en  The page you can't read.
    >>  ............................................
    pt  A página que você não lê.
    >>  ............................................
```


### Button `ask_what_she_thinks` — "What do you think it says?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shady_wizard.inherited_page.active` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shady_wizard.inherited_page.active.ask_what_she_thinks` — accepted phrasings: "what do you think it says"; "what do you think it says"; "what is your reading of it"
  - the message must contain one of: `says`, `reading`
  - scored words: `says`(1.8), `reading`(1.8), `think`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.inherited_page.active.respond.ask_what_she_thinks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shady_wizard.inherited_page.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shady_wizard.inherited_page.active.respond.ask_what_she_thinks   [26 chars]
    en  What do you think it says?
    >>  ............................................
    pt  O que você acha que diz?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shady_wizard.notes`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.shady_wizard.inherited_page"}
- Then opens: `conversations.scene.work.shady_wizard.followup`
- …where the player's next choices will be: "What's the hardest part of a working that misfires?" | "I'll leave you to your notes."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.inherited_page.active.declined_politely
WHO    VILLAGER — what the player reads after pressing "What do you think it says?"
       spoken on: conversations.scene.work.shady_wizard.inherited_page.active.respond, button `ask_what_she_thinks`
       leaves the player on: conversations.scene.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.inherited_page.active.declined_politely`: the villager deflects. Subject `work.shady_wizard.notes`, polarity `neutral`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shady_wizard.inherited_page.active.declined_politely/1   [127 chars]
    en  I am not going to say, and that is not mystery-making. A half-read instruction repeated out loud is how somebody else tries it.
    >>  ............................................
    pt  Não vou dizer, e isso não é mistério. Uma instrução lida pela metade e repetida em voz alta é como outra pessoa acaba testando.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.inherited_page.active.declined_politely/2   [113 chars]
    en  Three things, and the three contradict, and saying the most plausible one aloud would make it the one I remember.
    >>  ............................................
    pt  Três coisas, e as três se contradizem, e dizer a mais plausível em voz alta faria dela a que eu lembro.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.inherited_page.active.declined_politely/3   [107 chars]
    en  Ask me when I know. It has been two years and I would rather it were twenty than be the person who guessed.
    >>  ............................................
    pt  Me pergunte quando eu souber. Já são dois anos e eu prefiro que sejam vinte a ser a pessoa que chutou.
    >>  ............................................
```


### Button `suggest_asking_someone` — "Find somebody who reads that hand."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.shady_wizard.inherited_page.active` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shady_wizard.inherited_page.active.suggest_asking_someone` — accepted phrasings: "find somebody who reads that hand"; "find somebody who reads that hand"; "ask a scribe to look at it"
  - the message must contain one of: `hand`, `scribe`
  - scored words: `hand`(1.8), `scribe`(1.8), `find`(0.8), `somebody`(0.8), `who`(0.8), `reads`(0.8), `ask`(0.8), `look`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.inherited_page.active.respond.suggest_asking_someone
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shady_wizard.inherited_page.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shady_wizard.inherited_page.active.respond.suggest_asking_someone   [34 chars]
    en  Find somebody who reads that hand.
    >>  ............................................
    pt  Ache alguém que leia essa letra.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.shady_wizard.notes`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.shady_wizard.inherited_page"}
- Then opens: `conversations.scene.work.shady_wizard.followup`
- …where the player's next choices will be: "What's the hardest part of a working that misfires?" | "I'll leave you to your notes."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.inherited_page.active.accepted
WHO    VILLAGER — what the player reads after pressing "Find somebody who reads that hand."
       spoken on: conversations.scene.work.shady_wizard.inherited_page.active.respond, button `suggest_asking_someone`
       leaves the player on: conversations.scene.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.inherited_page.active.accepted`: the villager accepts. Subject `work.shady_wizard.notes`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shady_wizard.inherited_page.active.accepted/1   [126 chars]
    en  A copyist would read the letters without knowing what they mean, which is exactly the help I need and I had not thought of it.
    >>  ............................................
    pt  Um copista leria as letras sem saber o que significam, que é exatamente a ajuda de que eu preciso e eu não tinha pensado nisso.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.inherited_page.active.accepted/2   [131 chars]
    en  Yes. It means handing my strangest possession to somebody who will tell the village about it, and that is a price I can now afford.
    >>  ............................................
    pt  Sim. Significa entregar meu bem mais estranho a alguém que vai contar à vila, e é um preço que agora eu posso pagar.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.inherited_page.active.accepted/3   [120 chars]
    en  Two years of staring and the answer is somebody with a different skill. I am going to be annoyed about that for a while.
    >>  ............................................
    pt  Dois anos encarando e a resposta é alguém com outra habilidade. Vou ficar irritada com isso por um tempo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your workings."

*stance family `exit` · tone `plain` · answers the beat(s) `work.shady_wizard.inherited_page.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.inherited_page.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shady_wizard.inherited_page.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shady_wizard.inherited_page.active.respond.leave   [39 chars]
    en  I'll let you get back to your workings.
    >>  ............................................
    pt  Vou deixar você voltar aos seus trabalhos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your workings."
       spoken on: conversations.scene.work.shady_wizard.inherited_page.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.left`: the villager accepts. Subject `work.shady_wizard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shady_wizard.followup / leave; conversations.scene.work.shady_wizard.inherited_page.succeeded.respond / leave; conversations.scene.work.shady_wizard.misfire.blocked.respond / leave; conversations.scene.work.shady_wizard.misfire.succeeded.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.active.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond / leave; conversations.topic.work.shady_wizard.craft.respond / leave; conversations.topic.work.shady_wizard.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shady_wizard.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.shady_wizard.inherited_page.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.shady_wizard.inherited_page.succeeded` — e.g. "A copyist read %2$s in an afternoon. It is a shopping list. Two years, and it is a shopping list."


```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.inherited_page.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.shady_wizard.inherited_page.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.shady_wizard.inherited_page.succeeded.respond   [10 chars]
    en  That page.
    >>  ............................................
    pt  Aquela página.
    >>  ............................................
```


### Button `note_the_restraint` — "You held off for two whole years."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.shady_wizard.inherited_page.succeeded` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shady_wizard.inherited_page.succeeded.note_the_restraint` — accepted phrasings: "you held off for two whole years"; "the restraint was the point"; "you held off for two whole years"
  - the message must contain one of: `restraint`, `held`, `years`
  - scored words: `restraint`(1.8), `held`(1.8), `years`(1.8), `off`(0.8), `two`(0.8), `whole`(0.8), `point`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.inherited_page.succeeded.respond.note_the_restraint
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shady_wizard.inherited_page.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shady_wizard.inherited_page.succeeded.respond.note_the_restraint   [33 chars]
    en  You held off for two whole years.
    >>  ............................................
    pt  Você se conteve por dois anos inteiros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.shady_wizard.notes`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.shady_wizard.inherited_page"}
- Then opens: `conversations.scene.work.shady_wizard.followup`
- …where the player's next choices will be: "What's the hardest part of a working that misfires?" | "I'll leave you to your notes."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.inherited_page.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "You held off for two whole years."
       spoken on: conversations.scene.work.shady_wizard.inherited_page.succeeded.respond, button `note_the_restraint`
       leaves the player on: conversations.scene.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.inherited_page.succeeded.acknowledged`: the villager accepts. Subject `work.shady_wizard.notes`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shady_wizard.inherited_page.succeeded.acknowledged/1   [140 chars]
    en  Two years of not doing a thing, and the reward is finding out it would have been harmless. That is how it always goes and it proves nothing.
    >>  ............................................
    pt  Dois anos sem fazer uma coisa, e o prêmio é descobrir que teria sido inofensivo. É sempre assim e não prova nada.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.inherited_page.succeeded.acknowledged/2   [135 chars]
    en  Thank you. The version where it was not a shopping list is the version I was actually preparing for, and it cost me nothing to prepare.
    >>  ............................................
    pt  Obrigada. A versão em que não era lista de compras é a versão para a qual eu estava me preparando, e me preparar não custou nada.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.inherited_page.succeeded.acknowledged/3   [132 chars]
    en  It is the only discipline in this trade and it is invisible. There is no praise anywhere for a working somebody declined to attempt.
    >>  ............................................
    pt  É a única disciplina deste ofício e é invisível. Não existe elogio em lugar nenhum para um trabalho que alguém deixou de tentar.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your workings."

*stance family `exit` · tone `plain` · answers the beat(s) `work.shady_wizard.inherited_page.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.inherited_page.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shady_wizard.inherited_page.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shady_wizard.inherited_page.succeeded.respond.leave   [39 chars]
    en  I'll let you get back to your workings.
    >>  ............................................
    pt  Vou deixar você voltar aos seus trabalhos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your workings."
       spoken on: conversations.scene.work.shady_wizard.inherited_page.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.left`: the villager accepts. Subject `work.shady_wizard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shady_wizard.followup / leave; conversations.scene.work.shady_wizard.inherited_page.active.respond / leave; conversations.scene.work.shady_wizard.misfire.blocked.respond / leave; conversations.scene.work.shady_wizard.misfire.succeeded.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.active.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond / leave; conversations.topic.work.shady_wizard.craft.respond / leave; conversations.topic.work.shady_wizard.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shady_wizard.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.shady_wizard.misfire.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.shady_wizard.misfire.blocked` — e.g. "%2$s went wrong on Tuesday and left me %3$s, and I have stopped work until I understand why."


```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.misfire.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.shady_wizard.misfire.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.shady_wizard.misfire.blocked.respond   [9 chars]
    en  The shed.
    >>  ............................................
    pt  O galpão.
    >>  ............................................
```


### Button `ask_why_stop` — "Why stop instead of trying again?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shady_wizard.misfire.blocked` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shady_wizard.misfire.blocked.ask_why_stop` — accepted phrasings: "why stop instead of trying again"; "why halt instead of trying again"; "what makes you pause the work"
  - the message must contain one of: `halt`, `pause`, `again`
  - scored words: `halt`(1.8), `pause`(1.8), `again`(1.8), `why`(0.8), `stop`(0.8), `instead`(0.8), `trying`(0.8), `makes`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.misfire.blocked.respond.ask_why_stop
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shady_wizard.misfire.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shady_wizard.misfire.blocked.respond.ask_why_stop   [33 chars]
    en  Why stop instead of trying again?
    >>  ............................................
    pt  Por que parar em vez de tentar de novo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.shady_wizard.a_misfire`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.shady_wizard.misfire"}
- Then opens: `conversations.scene.work.shady_wizard.followup`
- …where the player's next choices will be: "What's the hardest part of a working that misfires?" | "I'll leave you to your notes."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.misfire.blocked.explained
WHO    VILLAGER — what the player reads after pressing "Why stop instead of trying again?"
       spoken on: conversations.scene.work.shady_wizard.misfire.blocked.respond, button `ask_why_stop`
       leaves the player on: conversations.scene.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.misfire.blocked.explained`: the villager explains. Subject `work.shady_wizard.a_misfire`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shady_wizard.misfire.blocked.explained/1   [117 chars]
    en  Because a working I do not understand is one I cannot repeat and cannot avoid, and both halves of that are dangerous.
    >>  ............................................
    pt  Porque um trabalho que eu não entendo é um que eu não sei repetir nem evitar, e as duas metades disso são perigosas.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.misfire.blocked.explained/2   [114 chars]
    en  Trying again is how a small surprise becomes a large one. I have watched a man in the next valley do exactly that.
    >>  ............................................
    pt  Tentar de novo é como uma surpresa pequena vira uma grande. Já vi um homem no vale vizinho fazer exatamente isso.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.misfire.blocked.explained/3   [117 chars]
    en  %2$s is not urgent. Nothing I do is urgent. That is the one advantage of an unregulated trade and I intend to use it.
    >>  ............................................
    pt  %2$s não é urgente. Nada do que eu faço é urgente. É a única vantagem de um ofício sem regras e eu pretendo usá-la.
    >>  ............................................
```


### Button `offer_lapis` — "I'll bring you lapis for the work."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.shady_wizard.misfire.blocked` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shady_wizard.misfire.blocked.offer_lapis` — accepted phrasings: "ill bring you lapis for the work"; "i can bring you lapis"; "let me fetch lapis for that"
  - the message must contain one of: `lapis`
  - scored words: `lapis`(1.8), `ill`(0.8), `bring`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.misfire.blocked.respond.offer_lapis
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shady_wizard.misfire.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shady_wizard.misfire.blocked.respond.offer_lapis   [34 chars]
    en  I'll bring you lapis for the work.
    >>  ............................................
    pt  Vou trazer lápis-lazúli para o trabalho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.shady_wizard.misfire.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.shady_wizard.a_misfire`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.misfire", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.shady_wizard.misfire", "obligation": "commitment:work.shady_wizard.bring_lapis"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.shady_wizard.bring_lapis"}
- Then opens: `conversations.scene.work.shady_wizard.followup`
- …where the player's next choices will be: "What's the hardest part of a working that misfires?" | "I'll leave you to your notes."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.misfire.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you lapis for the work."
       spoken on: conversations.scene.work.shady_wizard.misfire.blocked.respond, button `offer_lapis`
       leaves the player on: conversations.scene.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.misfire.blocked.accepted`: the villager accepts. Subject `work.shady_wizard.a_misfire`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shady_wizard.misfire.blocked.accepted/1   [87 chars]
    en  Then I can run it four times at a quarter strength and find out which step is the liar.
    >>  ............................................
    pt  Então eu posso rodar quatro vezes a um quarto da força e descobrir qual passo é o mentiroso.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.misfire.blocked.accepted/2   [128 chars]
    en  Four small tests instead of one large one. That is what the material buys, and it is the difference between method and gambling.
    >>  ............................................
    pt  Quatro testes pequenos em vez de um grande. É isso que o material compra, e é a diferença entre método e aposta.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.misfire.blocked.accepted/3   [111 chars]
    en  Yes. And you may watch, from the doorway, with the door open, which is how I would want to watch somebody else.
    >>  ............................................
    pt  Sim. E você pode assistir, da porta, com a porta aberta, que é como eu gostaria de assistir a outra pessoa.
    >>  ............................................
```


### Button `back_the_caution` — "Halting there was the right call."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.shady_wizard.misfire.blocked` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shady_wizard.misfire.blocked.back_the_caution` — accepted phrasings: "halting there was the right call"; "halting there was the right call"; "pausing the work was correct"
  - the message must contain one of: `halting`, `pausing`, `correct`
  - scored words: `halting`(1.8), `pausing`(1.8), `correct`(1.8), `right`(0.8), `call`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.misfire.blocked.respond.back_the_caution
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shady_wizard.misfire.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shady_wizard.misfire.blocked.respond.back_the_caution   [33 chars]
    en  Halting there was the right call.
    >>  ............................................
    pt  Interromper ali foi a decisão certa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +4, trust +1  _(recorded under topic `work.shady_wizard.a_misfire`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.shady_wizard.misfire"}
- Then opens: `conversations.scene.work.shady_wizard.followup`
- …where the player's next choices will be: "What's the hardest part of a working that misfires?" | "I'll leave you to your notes."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.misfire.blocked.steadied
WHO    VILLAGER — what the player reads after pressing "Halting there was the right call."
       spoken on: conversations.scene.work.shady_wizard.misfire.blocked.respond, button `back_the_caution`
       leaves the player on: conversations.scene.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.misfire.blocked.steadied`: the villager accepts. Subject `work.shady_wizard.a_misfire`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shady_wizard.misfire.blocked.steadied/1   [115 chars]
    en  It is the only rule I have that has never let me down, and it is also the rule that makes me look slow and evasive.
    >>  ............................................
    pt  É a única regra minha que nunca me decepcionou, e é também a regra que me faz parecer lenta e evasiva.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.misfire.blocked.steadied/2   [142 chars]
    en  Thank you. Everybody in this village would rather I were reckless and cheerful about it. Reckless and cheerful is what they think a wizard is.
    >>  ............................................
    pt  Obrigada. Todo mundo nesta vila preferiria que eu fosse imprudente e alegre a respeito. Imprudente e alegre é o que acham que uma maga é.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.misfire.blocked.steadied/3   [101 chars]
    en  I have a shelf of workings I stopped and never went back to. It is the most useful shelf in the shed.
    >>  ............................................
    pt  Tenho uma prateleira de trabalhos que eu interrompi e nunca retomei. É a prateleira mais útil do galpão.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your workings."

*stance family `exit` · tone `plain` · answers the beat(s) `work.shady_wizard.misfire.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.misfire.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shady_wizard.misfire.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shady_wizard.misfire.blocked.respond.leave   [39 chars]
    en  I'll let you get back to your workings.
    >>  ............................................
    pt  Vou deixar você voltar aos seus trabalhos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your workings."
       spoken on: conversations.scene.work.shady_wizard.misfire.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.left`: the villager accepts. Subject `work.shady_wizard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shady_wizard.followup / leave; conversations.scene.work.shady_wizard.inherited_page.active.respond / leave; conversations.scene.work.shady_wizard.inherited_page.succeeded.respond / leave; conversations.scene.work.shady_wizard.misfire.succeeded.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.active.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond / leave; conversations.topic.work.shady_wizard.craft.respond / leave; conversations.topic.work.shady_wizard.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shady_wizard.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.shady_wizard.misfire.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.shady_wizard.misfire.succeeded` — e.g. "Four quarter-strength runs and the third one told me. %2$s was fine; the bench under it was damp."


```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.misfire.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.shady_wizard.misfire.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.shady_wizard.misfire.succeeded.respond   [13 chars]
    en  That working.
    >>  ............................................
    pt  Aquele trabalho.
    >>  ............................................
```


### Button `ask_about_the_notes` — "What goes in your notes?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shady_wizard.misfire.succeeded` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shady_wizard.misfire.succeeded.ask_about_the_notes` — accepted phrasings: "what goes in your notes"; "what goes in your notes"; "what do you write down about a working"
  - the message must contain one of: `notes`, `write`
  - scored words: `notes`(1.8), `write`(1.8), `goes`(0.8), `down`(0.8), `working`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.misfire.succeeded.respond.ask_about_the_notes
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shady_wizard.misfire.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shady_wizard.misfire.succeeded.respond.ask_about_the_notes   [24 chars]
    en  What goes in your notes?
    >>  ............................................
    pt  O que vai nas suas anotações?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.shady_wizard.a_misfire`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.shady_wizard.misfire"}
- Then opens: `conversations.scene.work.shady_wizard.followup`
- …where the player's next choices will be: "What's the hardest part of a working that misfires?" | "I'll leave you to your notes."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.misfire.succeeded.explained
WHO    VILLAGER — what the player reads after pressing "What goes in your notes?"
       spoken on: conversations.scene.work.shady_wizard.misfire.succeeded.respond, button `ask_about_the_notes`
       leaves the player on: conversations.scene.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.misfire.succeeded.explained`: the villager explains. Subject `work.shady_wizard.a_misfire`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shady_wizard.misfire.succeeded.explained/1   [143 chars]
    en  Everything, including the weather and what I had eaten, because the third time something goes wrong the pattern is always in the boring column.
    >>  ............................................
    pt  Tudo, inclusive o tempo e o que eu tinha comido, porque na terceira vez que algo dá errado o padrão está sempre na coluna chata.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.misfire.succeeded.explained/2   [119 chars]
    en  Failures at the front, successes at the back. Anybody who reads it will see the failures first, and that is deliberate.
    >>  ............................................
    pt  Fracassos na frente, acertos no fim. Quem ler vai ver os fracassos primeiro, e isso é de propósito.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.misfire.succeeded.explained/3   [133 chars]
    en  The things I do not understand get a mark in the margin. There are nineteen marks. I would be worried about a practitioner with none.
    >>  ............................................
    pt  O que eu não entendo ganha uma marca na margem. São dezenove marcas. Eu me preocuparia com uma praticante que não tivesse nenhuma.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your workings."

*stance family `exit` · tone `plain` · answers the beat(s) `work.shady_wizard.misfire.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.misfire.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shady_wizard.misfire.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shady_wizard.misfire.succeeded.respond.leave   [39 chars]
    en  I'll let you get back to your workings.
    >>  ............................................
    pt  Vou deixar você voltar aos seus trabalhos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your workings."
       spoken on: conversations.scene.work.shady_wizard.misfire.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.left`: the villager accepts. Subject `work.shady_wizard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shady_wizard.followup / leave; conversations.scene.work.shady_wizard.inherited_page.active.respond / leave; conversations.scene.work.shady_wizard.inherited_page.succeeded.respond / leave; conversations.scene.work.shady_wizard.misfire.blocked.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.active.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond / leave; conversations.topic.work.shady_wizard.craft.respond / leave; conversations.topic.work.shady_wizard.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shady_wizard.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.shady_wizard.the_name_they_use.active.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.shady_wizard.the_name_they_use.active` — e.g. "They call me shady at %2$s, and they buy from me anyway, and both of those happen in the same afternoon."


```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.the_name_they_use.active.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.shady_wizard.the_name_they_use.active.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.active.respond   [19 chars]
    en  What they call you.
    >>  ............................................
    pt  Como te chamam.
    >>  ............................................
```


### Button `ask_why_the_word` — "Where did the word come from?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shady_wizard.the_name_they_use.active` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shady_wizard.the_name_they_use.active.ask_why_the_word` — accepted phrasings: "where did the word come from"; "where did the word come from"; "who started calling you that"
  - the message must contain one of: `word`, `calling`
  - scored words: `word`(1.8), `calling`(1.8), `where`(0.8), `come`(0.8), `from`(0.8), `who`(0.8), `started`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.the_name_they_use.active.respond.ask_why_the_word
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shady_wizard.the_name_they_use.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.active.respond.ask_why_the_word   [29 chars]
    en  Where did the word come from?
    >>  ............................................
    pt  De onde veio essa palavra?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shady_wizard.customers`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.shady_wizard.the_name_they_use"}
- Then opens: `conversations.scene.work.shady_wizard.followup`
- …where the player's next choices will be: "What's the hardest part of a working that misfires?" | "I'll leave you to your notes."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.the_name_they_use.active.explained
WHO    VILLAGER — what the player reads after pressing "Where did the word come from?"
       spoken on: conversations.scene.work.shady_wizard.the_name_they_use.active.respond, button `ask_why_the_word`
       leaves the player on: conversations.scene.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.the_name_they_use.active.explained`: the villager explains. Subject `work.shady_wizard.customers`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.active.explained/1   [124 chars]
    en  From the trade, mostly. There is no guild and no examination, so anybody with a shed can call themselves what I call myself.
    >>  ............................................
    pt  Do ofício, principalmente. Não há guilda nem exame, então qualquer um com um galpão pode se chamar do que eu me chamo.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.active.explained/2   [123 chars]
    en  From one man, eleven years ago, in another village, who took money and left. Every one of us has been paying for him since.
    >>  ............................................
    pt  De um homem, onze anos atrás, em outra vila, que pegou dinheiro e sumiu. Todos nós vimos pagando por ele desde então.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.active.explained/3   [122 chars]
    en  It is not unearned by the trade as a whole. That is the part I cannot argue with and the reason I keep such careful notes.
    >>  ............................................
    pt  Não é imerecido pelo ofício como um todo. É a parte que eu não consigo contestar e o motivo de eu manter notas tão cuidadosas.
    >>  ............................................
```


### Button `advise_showing_the_notes` — "Show somebody the notebook."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.shady_wizard.the_name_they_use.active` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shady_wizard.the_name_they_use.active.advise_showing_the_notes` — accepted phrasings: "show somebody the notebook"; "show somebody the notebook"; "let one person read your record"
  - the message must contain one of: `notebook`, `record`
  - scored words: `notebook`(1.8), `record`(1.8), `show`(0.8), `somebody`(0.8), `let`(0.8), `one`(0.8), `person`(0.8), `read`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.the_name_they_use.active.respond.advise_showing_the_notes
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shady_wizard.the_name_they_use.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.active.respond.advise_showing_the_notes   [27 chars]
    en  Show somebody the notebook.
    >>  ............................................
    pt  Mostre o caderno a alguém.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.shady_wizard.name.backed`, budget `standard`, replay policy `once`
- Does: disposition — respect +3, trust +2  _(recorded under topic `work.shady_wizard.customers`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.shady_wizard.the_name_they_use"}
- Then opens: `conversations.scene.work.shady_wizard.followup`
- …where the player's next choices will be: "What's the hardest part of a working that misfires?" | "I'll leave you to your notes."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.the_name_they_use.active.considered
WHO    VILLAGER — what the player reads after pressing "Show somebody the notebook."
       spoken on: conversations.scene.work.shady_wizard.the_name_they_use.active.respond, button `advise_showing_the_notes`
       leaves the player on: conversations.scene.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.the_name_they_use.active.considered`: the villager accepts. Subject `work.shady_wizard.customers`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.active.considered/1   [110 chars]
    en  One person. The cleric, probably, who will not understand a word of it and will understand exactly what it is.
    >>  ............................................
    pt  Uma pessoa. A clériga, provavelmente, que não vai entender uma palavra e vai entender exatamente o que aquilo é.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.active.considered/2   [126 chars]
    en  That had not occurred to me because the notebook is for me. It has never been evidence before and I dislike that it has to be.
    >>  ............................................
    pt  Isso não tinha me ocorrido porque o caderno é para mim. Nunca foi prova antes e eu não gosto que tenha que ser.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.active.considered/3   [142 chars]
    en  Yes. And the failures are at the front, which means the first thing they read is nineteen things I got wrong. That is either brave or foolish.
    >>  ............................................
    pt  Sim. E os fracassos estão na frente, então a primeira coisa que vão ler são dezenove erros meus. Isso é corajoso ou tolo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your workings."

*stance family `exit` · tone `plain` · answers the beat(s) `work.shady_wizard.the_name_they_use.active` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.the_name_they_use.active.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shady_wizard.the_name_they_use.active.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.active.respond.leave   [39 chars]
    en  I'll let you get back to your workings.
    >>  ............................................
    pt  Vou deixar você voltar aos seus trabalhos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your workings."
       spoken on: conversations.scene.work.shady_wizard.the_name_they_use.active.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.left`: the villager accepts. Subject `work.shady_wizard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shady_wizard.followup / leave; conversations.scene.work.shady_wizard.inherited_page.active.respond / leave; conversations.scene.work.shady_wizard.inherited_page.succeeded.respond / leave; conversations.scene.work.shady_wizard.misfire.blocked.respond / leave; conversations.scene.work.shady_wizard.misfire.succeeded.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond / leave; conversations.topic.work.shady_wizard.craft.respond / leave; conversations.topic.work.shady_wizard.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shady_wizard.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.shady_wizard.the_name_they_use.succeeded` — e.g. "The cleric read the notebook and said one sentence about it at the well, and %2$s has been different since."


```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond   [17 chars]
    en  The lane, lately.
    >>  ............................................
    pt  A viela, ultimamente.
    >>  ............................................
```


### Button `note_the_word` — "Careful is the right word for you."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.shady_wizard.the_name_they_use.succeeded` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.shady_wizard.the_name_they_use.succeeded.note_the_word` — accepted phrasings: "careful is the right word for you"; "careful is the right word for you"; "careful describes you accurately"
  - the message must contain one of: `careful`, `describes`
  - scored words: `careful`(1.8), `describes`(1.8), `right`(0.8), `word`(0.8), `accurately`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond.note_the_word
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond.note_the_word   [34 chars]
    en  Careful is the right word for you.
    >>  ............................................
    pt  Cuidadosa é a palavra certa para você.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, respect +3  _(recorded under topic `work.shady_wizard.customers`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.shady_wizard.the_name_they_use"}
- Then opens: `conversations.scene.work.shady_wizard.followup`
- …where the player's next choices will be: "What's the hardest part of a working that misfires?" | "I'll leave you to your notes."

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.the_name_they_use.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Careful is the right word for you."
       spoken on: conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond, button `note_the_word`
       leaves the player on: conversations.scene.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.the_name_they_use.succeeded.acknowledged`: the villager accepts. Subject `work.shady_wizard.customers`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.succeeded.acknowledged/1   [114 chars]
    en  It is the only word I have ever wanted and it took a notebook full of failures to get it, which is a joke I enjoy.
    >>  ............................................
    pt  É a única palavra que eu sempre quis, e precisou de um caderno cheio de fracassos para consegui-la, o que é uma piada de que eu gosto.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.succeeded.acknowledged/2   [104 chars]
    en  Thank you. Careful is a slower reputation than shady and it is the only one that survives being checked.
    >>  ............................................
    pt  Obrigada. Cuidadosa é uma reputação mais lenta que suspeita e é a única que sobrevive a ser conferida.
    >>  ............................................
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.succeeded.acknowledged/3   [108 chars]
    en  One person said it once. I am aware of exactly how thin that is and I have thought about it every day since.
    >>  ............................................
    pt  Uma pessoa disse uma vez. Sei exatamente o quanto isso é pouco e penso nisso todo dia desde então.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to your workings."

*stance family `exit` · tone `plain` · answers the beat(s) `work.shady_wizard.the_name_they_use.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond.leave   [39 chars]
    en  I'll let you get back to your workings.
    >>  ............................................
    pt  Vou deixar você voltar aos seus trabalhos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to your workings."
       spoken on: conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.left`: the villager accepts. Subject `work.shady_wizard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shady_wizard.followup / leave; conversations.scene.work.shady_wizard.inherited_page.active.respond / leave; conversations.scene.work.shady_wizard.inherited_page.succeeded.respond / leave; conversations.scene.work.shady_wizard.misfire.blocked.respond / leave; conversations.scene.work.shady_wizard.misfire.succeeded.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.active.respond / leave; conversations.topic.work.shady_wizard.craft.respond / leave; conversations.topic.work.shady_wizard.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shady_wizard.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.shady_wizard.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.shady_wizard.craft` — e.g. "Nobody trained me. I bought a chest at an estate sale when I was twenty-two and I've been catching up since."


```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.shady_wizard.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.shady_wizard.craft.respond   [24 chars]
    en  That's how I came by it.
    >>  ............................................
    pt  Foi assim que eu cheguei aqui.
    >>  ............................................
```


### Button `ask_chest` — "What was in the chest?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shady_wizard.craft` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shady_wizard.craft.ask_chest` — accepted phrasings: "what was in the chest"
  - the message must contain one of: `chest`, `inside`, `contained`
  - scored words: `chest`(1.5), `inside`(1.2), `contained`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.craft.respond.ask_chest
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.craft.respond.ask_chest   [22 chars]
    en  What was in the chest?
    >>  ............................................
    pt  O que tinha no baú?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shady_wizard.craft.ask_chest`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shady_wizard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there anything you won't sell?" | "No refunds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.craft.ask_chest
WHO    VILLAGER — what the player reads after pressing "What was in the chest?"
       spoken on: conversations.topic.work.shady_wizard.craft.respond, button `ask_chest`
       leaves the player on: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.craft.ask_chest`: the villager explains. Subject `work.shady_wizard.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shady_wizard.craft.ask_chest/1   [82 chars]
    en  Forty jars, nine books and one thing I put back in and have never taken out again.
    >>  ............................................
    pt  Quarenta potes, nove livros e uma coisa que eu pus de volta e nunca mais tirei.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.craft.ask_chest/2   [96 chars]
    en  Somebody's whole life, sold off by relatives who wanted the furniture, %1$s. I think about them.
    >>  ............................................
    pt  A vida inteira de alguém, leiloada por parentes que queriam os móveis, %1$s. Eu penso neles.
    >>  ............................................
```


### Button `admire` — "Knowing which half is theatre is more than most manage."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.shady_wizard.craft` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shady_wizard.craft.admire` — accepted phrasings: "knowing which half is theatre is more than most manage"
  - the message must contain one of: `theatre`, `honest`
  - scored words: `theatre`(1.5), `honest`(1.2), `half`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.craft.respond.admire   [55 chars]
    en  Knowing which half is theatre is more than most manage.
    >>  ............................................
    pt  Saber qual metade é teatro é mais do que a maioria consegue.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.shady_wizard.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.shady_wizard.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shady_wizard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there anything you won't sell?" | "No refunds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.craft.admire
WHO    VILLAGER — what the player reads after pressing "Knowing which half is theatre is more than most manage."
       spoken on: conversations.topic.work.shady_wizard.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.craft.admire`: the villager accepts. Subject `work.shady_wizard.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shady_wizard.craft.admire/1   [81 chars]
    en  It's the only thing that keeps me from becoming the sort of man I buy stock from.
    >>  ............................................
    pt  É a única coisa que me impede de virar o tipo de homem de quem eu compro estoque.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.craft.admire/2   [86 chars]
    en  It's also lonely, %1$s. There's nobody to check my chemistry against, only my theatre.
    >>  ............................................
    pt  Também é solitário, %1$s. Não tem ninguém pra conferir minha química, só meu teatro.
    >>  ............................................
```


### Button `ask_books` — "Have you read all nine books?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shady_wizard.craft` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shady_wizard.craft.ask_books` — accepted phrasings: "have you read all nine books"
  - the message must contain one of: `books`, `read`, `nine`
  - scored words: `books`(1.5), `read`(1.0), `nine`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.craft.respond.ask_books
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.craft.respond.ask_books   [29 chars]
    en  Have you read all nine books?
    >>  ............................................
    pt  Você leu os nove livros?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shady_wizard.craft.ask_books`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shady_wizard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there anything you won't sell?" | "No refunds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.craft.ask_books
WHO    VILLAGER — what the player reads after pressing "Have you read all nine books?"
       spoken on: conversations.topic.work.shady_wizard.craft.respond, button `ask_books`
       leaves the player on: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.craft.ask_books`: the villager explains. Subject `work.shady_wizard.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shady_wizard.craft.ask_books/1   [85 chars]
    en  Seven. The eighth is in a hand I can't read and the ninth I've decided not to finish.
    >>  ............................................
    pt  Sete. O oitavo tem uma letra que eu não leio e o nono eu decidi não terminar.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.craft.ask_books/2   [91 chars]
    en  Four times each, %1$s, and the fourth reading is where I found out how wrong the first was.
    >>  ............................................
    pt  Quatro vezes cada, %1$s, e a quarta leitura foi onde eu descobri o quanto a primeira estava errada.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to business."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.shady_wizard.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.craft.respond.leave   [34 chars]
    en  I'll let you get back to business.
    >>  ............................................
    pt  Vou deixar você voltar aos negócios.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to business."
       spoken on: conversations.topic.work.shady_wizard.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.left`: the villager accepts. Subject `work.shady_wizard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shady_wizard.followup / leave; conversations.scene.work.shady_wizard.inherited_page.active.respond / leave; conversations.scene.work.shady_wizard.inherited_page.succeeded.respond / leave; conversations.scene.work.shady_wizard.misfire.blocked.respond / leave; conversations.scene.work.shady_wizard.misfire.succeeded.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.active.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond / leave; conversations.topic.work.shady_wizard.followup / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shady_wizard.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.shady_wizard.followup`

**Reached from 20 route(s):** `conversations.scene.work.shady_wizard.followup` / `ask_more`; `conversations.topic.work.shady_wizard.craft.respond` / `ask_chest`; `conversations.topic.work.shady_wizard.craft.respond` / `admire`; `conversations.topic.work.shady_wizard.craft.respond` / `ask_books`; `conversations.topic.work.shady_wizard.future.respond` / `ask_dontknow`; `conversations.topic.work.shady_wizard.future.respond` / `encourage`; `conversations.topic.work.shady_wizard.future.respond` / `ask_sensible`; `conversations.topic.work.shady_wizard.respond` / `ask_hard`; `conversations.topic.work.shady_wizard.respond` / `value`; `conversations.topic.work.shady_wizard.respond` / `challenge`; `conversations.topic.work.shady_wizard.respond` / `challenge`; `conversations.topic.work.shady_wizard.risk.respond` / `ask_first_name` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.shady_wizard.challenge.landed` — e.g. "'Sourced creatively' was the agreed phrasing, and I'd thank you to use it."
- `conversations.work.prof.shady_wizard.challenge.stung` — e.g. "...Everything on that shelf has paperwork. Somewhere. In some form."
- `conversations.work.prof.shady_wizard.craft.admire` — e.g. "It's the only thing that keeps me from becoming the sort of man I buy stock from."
- `conversations.work.prof.shady_wizard.craft.ask_books` — e.g. "Seven. The eighth is in a hand I can't read and the ninth I've decided not to finish."
- `conversations.work.prof.shady_wizard.craft.ask_chest` — e.g. "Forty jars, nine books and one thing I put back in and have never taken out again."
- `conversations.work.prof.shady_wizard.future.ask_dontknow` — e.g. "Eleven years of having said the opposite. You can't change your voice with the same customers."
- `conversations.work.prof.shady_wizard.future.ask_sensible` — e.g. "The librarian, who'd catalogue them and never open the eighth. That's the correct instinct."
- `conversations.work.prof.shady_wizard.future.encourage` — e.g. "...She already knows. That's — yes. That's a very small first step and I can actually take it."
- `conversations.work.prof.shady_wizard.hard` — e.g. "It doesn't. There is no mechanism by which it could. ...It has happened twice."
- `conversations.work.prof.shady_wizard.risk.ask_first_name` — e.g. "Twice. Both times it was a well and a wet spring, and both times I was asked to prove otherwise."
- `conversations.work.prof.shady_wizard.risk.ask_shelf` — e.g. "A jar with a hand-written lid and a date on it older than this village. That's what I'll say."
- `conversations.work.prof.shady_wizard.risk.sympathise` — e.g. "...It is. And I can't complain, because I chose a trade that looks exactly like the thing they fear."
- `conversations.work.prof.shady_wizard.task.ask_pretend` — e.g. "Because half of what I sell is confidence and the other half is a jar. That's an ugly sentence and a true one."
- `conversations.work.prof.shady_wizard.task.ask_whom` — e.g. "Anything binding, to anybody angry. That rule has cost me more custom than every other rule combined."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.shady_wizard.followup   [39 chars]
    en  That's the shop, more or less honestly.
    >>  ............................................
    pt  É a loja, mais ou menos honestamente.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.shady_wizard.challenge.landed`, `work.shady_wizard.challenge.stung`, `work.shady_wizard.craft.admire`, `work.shady_wizard.craft.ask_books`, `work.shady_wizard.craft.ask_chest`, `work.shady_wizard.future.ask_dontknow`, `work.shady_wizard.future.ask_sensible`, `work.shady_wizard.future.encourage`, `work.shady_wizard.hard`, `work.shady_wizard.risk.ask_first_name`, `work.shady_wizard.risk.ask_shelf`, `work.shady_wizard.risk.sympathise`, `work.shady_wizard.task.ask_pretend`, `work.shady_wizard.task.ask_whom`, `work.shady_wizard.task.offer_hands`, `work.shady_wizard.value`, `work.shady_wizard.village.ask_birth`, `work.shady_wizard.village.ask_lane`, `work.shady_wizard.village.say_thanks` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.shady_wizard.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `customer`
  - scored words: `thought`(1.2), `customer`(1.5), `question`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.shady_wizard.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.shady_wizard.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.shady_wizard.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.shady_wizard.thanks`: the villager accepts. Subject `work.shady_wizard.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shady_wizard.thanks/1   [65 chars]
    en  Few take the time. Nearly all of them want the item and the exit.
    >>  ............................................
    pt  Poucos param pra pensar. A maioria quer o item e a saída.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.thanks/2   [83 chars]
    en  You're the first customer in a year to ask a question that wasn't 'how much', %1$s.
    >>  ............................................
    pt  Você é o primeiro cliente em um ano a fazer uma pergunta que não era 'quanto custa', %1$s.
    >>  ............................................
```


### Button `ask_more` — "Is there anything you won't sell?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shady_wizard.challenge.landed`, `work.shady_wizard.challenge.stung`, `work.shady_wizard.craft.admire`, `work.shady_wizard.craft.ask_books`, `work.shady_wizard.craft.ask_chest`, `work.shady_wizard.future.ask_dontknow`, `work.shady_wizard.future.ask_sensible`, `work.shady_wizard.future.encourage`, `work.shady_wizard.hard`, `work.shady_wizard.risk.ask_first_name`, `work.shady_wizard.risk.ask_shelf`, `work.shady_wizard.risk.sympathise`, `work.shady_wizard.task.ask_pretend`, `work.shady_wizard.task.ask_whom`, `work.shady_wizard.task.offer_hands`, `work.shady_wizard.value`, `work.shady_wizard.village.ask_birth`, `work.shady_wizard.village.ask_lane`, `work.shady_wizard.village.say_thanks` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.shady_wizard.more` — accepted phrasings: "is there anything you won't sell"
  - the message must contain one of: `sell`, `refuse`, `stock`
  - scored words: `sell`(1.5), `refuse`(1.2), `stock`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.followup.ask_more   [33 chars]
    en  Is there anything you won't sell?
    >>  ............................................
    pt  Tem alguma coisa que você não vende?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.shady_wizard.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.more
WHO    VILLAGER — what the player reads after pressing "Is there anything you won't sell?"
       spoken on: conversations.topic.work.shady_wizard.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.shady_wizard.more`: the villager discloses. Subject `work.shady_wizard.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shady_wizard.more/1   [80 chars]
    en  Three things. I keep them behind the counter so I can say no with the door shut.
    >>  ............................................
    pt  Três coisas. Guardo atrás do balcão pra poder dizer não com a porta fechada.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.more/2   [87 chars]
    en  Yes. And I'd rather not name them out loud, %1$s, in case naming counts as advertising.
    >>  ............................................
    pt  Tem. E prefiro não nomear em voz alta, %1$s, caso nomear conte como propaganda.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things I'd destroy if I knew how. Selling them is out and burying them is worse.
    >>  ............................................
    pt  Três coisas que eu destruiria se soubesse como. Vender está fora e enterrar é pior.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.shady_wizard.more/2
    en  To say 'I don't know'. The first time I do, half of them will decide the last eleven years were invented.
    >>  ............................................
    pt  Dizer 'eu não sei'. Na primeira vez, metade vai decidir que os últimos onze anos foram inventados.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things behind the counter. They've been there years and they can stay there years more.
    >>  ............................................
    pt  Três coisas atrás do balcão. Estão lá há anos e podem ficar mais anos.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.shady_wizard.more/2
    en  To be honest at the counter, one day. It'll take a while to undo eleven years of the other thing.
    >>  ............................................
    pt  Ser honesto no balcão, um dia. Vai levar um tempo pra desfazer onze anos do contrário.
    >>  ............................................
  confident.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things. I keep them behind the counter so I can say no with the door shut.
    >>  ............................................
    pt  Três coisas. Guardo atrás do balcão pra poder dizer não com a porta fechada.
    >>  ............................................
  confident.dialogue.conversations.work.prof.shady_wizard.more/2
    en  To be able to say 'I don't know' at the counter and keep the customer. That is the whole ambition.
    >>  ............................................
    pt  Conseguir dizer 'eu não sei' no balcão e manter o cliente. É toda a ambição.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things. I keep them behind the counter so I can say no with the door shut.
    >>  ............................................
    pt  Três coisas. Guardo atrás do balcão pra poder dizer não com a porta fechada.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.shady_wizard.more/2
    en  To be able to say 'I don't know' at the counter and keep the customer. That is the whole ambition.
    >>  ............................................
    pt  Conseguir dizer 'eu não sei' no balcão e manter o cliente. É toda a ambição.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things behind the counter. I'd show you one, and only one, and only if you asked twice.
    >>  ............................................
    pt  Três coisas atrás do balcão. Eu te mostraria uma, só uma, e só se você pedisse duas vezes.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.shady_wizard.more/2
    en  To be honest at the counter. The cleric already knows I'm guessing; she's never once said so.
    >>  ............................................
    pt  Ser honesto no balcão. A clériga já sabe que eu chuto; ela nunca disse.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things behind the counter. I'd show you one, and only one, and only if you asked twice.
    >>  ............................................
    pt  Três coisas atrás do balcão. Eu te mostraria uma, só uma, e só se você pedisse duas vezes.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.shady_wizard.more/2
    en  To be honest at the counter. The cleric already knows I'm guessing; she's never once said so.
    >>  ............................................
    pt  Ser honesto no balcão. A clériga já sabe que eu chuto; ela nunca disse.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things behind the counter. I'd show you one, and only one, and only if you asked twice.
    >>  ............................................
    pt  Três coisas atrás do balcão. Eu te mostraria uma, só uma, e só se você pedisse duas vezes.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.shady_wizard.more/2
    en  To be honest at the counter. The cleric already knows I'm guessing; she's never once said so.
    >>  ............................................
    pt  Ser honesto no balcão. A clériga já sabe que eu chuto; ela nunca disse.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things I'd destroy if I knew how. Selling them is out and burying them is worse.
    >>  ............................................
    pt  Três coisas que eu destruiria se soubesse como. Vender está fora e enterrar é pior.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.shady_wizard.more/2
    en  To say 'I don't know'. The first time I do, half of them will decide the last eleven years were invented.
    >>  ............................................
    pt  Dizer 'eu não sei'. Na primeira vez, metade vai decidir que os últimos onze anos foram inventados.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things. I keep them behind the counter so I can say no with the door shut.
    >>  ............................................
    pt  Três coisas. Guardo atrás do balcão pra poder dizer não com a porta fechada.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.shady_wizard.more/2
    en  To be able to say 'I don't know' at the counter and keep the customer. That is the whole ambition.
    >>  ............................................
    pt  Conseguir dizer 'eu não sei' no balcão e manter o cliente. É toda a ambição.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things. I keep them behind the counter so I can say no with the door shut.
    >>  ............................................
    pt  Três coisas. Guardo atrás do balcão pra poder dizer não com a porta fechada.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.shady_wizard.more/2
    en  To be able to say 'I don't know' at the counter and keep the customer. That is the whole ambition.
    >>  ............................................
    pt  Conseguir dizer 'eu não sei' no balcão e manter o cliente. É toda a ambição.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things. Behind the counter, where saying no is easier than explaining why.
    >>  ............................................
    pt  Três coisas. Atrás do balcão, onde dizer não é mais fácil que explicar por quê.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.shady_wizard.more/2
    en  To say 'I don't know'. Half of what I sell is confidence, and confidence is a hard habit to put down.
    >>  ............................................
    pt  Dizer 'eu não sei'. Metade do que eu vendo é confiança, e confiança é um hábito difícil de largar.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things behind the counter. They've been there years and they can stay there years more.
    >>  ............................................
    pt  Três coisas atrás do balcão. Estão lá há anos e podem ficar mais anos.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.shady_wizard.more/2
    en  To be honest at the counter, one day. It'll take a while to undo eleven years of the other thing.
    >>  ............................................
    pt  Ser honesto no balcão, um dia. Vai levar um tempo pra desfazer onze anos do contrário.
    >>  ............................................
  odd.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things. Behind the counter, where saying no is easier than explaining why.
    >>  ............................................
    pt  Três coisas. Atrás do balcão, onde dizer não é mais fácil que explicar por quê.
    >>  ............................................
  odd.dialogue.conversations.work.prof.shady_wizard.more/2
    en  To say 'I don't know'. Half of what I sell is confidence, and confidence is a hard habit to put down.
    >>  ............................................
    pt  Dizer 'eu não sei'. Metade do que eu vendo é confiança, e confiança é um hábito difícil de largar.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things behind the counter. They've been there years and they can stay there years more.
    >>  ............................................
    pt  Três coisas atrás do balcão. Estão lá há anos e podem ficar mais anos.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.shady_wizard.more/2
    en  To be honest at the counter, one day. It'll take a while to undo eleven years of the other thing.
    >>  ............................................
    pt  Ser honesto no balcão, um dia. Vai levar um tempo pra desfazer onze anos do contrário.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things! Behind the counter, so I can say no with the door shut. Very civilised.
    >>  ............................................
    pt  Três coisas! Atrás do balcão, pra eu poder dizer não com a porta fechada. Muito civilizado.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.shady_wizard.more/2
    en  I'd like to say 'I don't know' out loud one day. Eleven years of the opposite makes that difficult.
    >>  ............................................
    pt  Eu queria dizer 'eu não sei' em voz alta um dia. Onze anos do contrário dificultam.
    >>  ............................................
  playful.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things! Behind the counter, so I can say no with the door shut. Very civilised.
    >>  ............................................
    pt  Três coisas! Atrás do balcão, pra eu poder dizer não com a porta fechada. Muito civilizado.
    >>  ............................................
  playful.dialogue.conversations.work.prof.shady_wizard.more/2
    en  I'd like to say 'I don't know' out loud one day. Eleven years of the opposite makes that difficult.
    >>  ............................................
    pt  Eu queria dizer 'eu não sei' em voz alta um dia. Onze anos do contrário dificultam.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things behind the counter. They've been there years and they can stay there years more.
    >>  ............................................
    pt  Três coisas atrás do balcão. Estão lá há anos e podem ficar mais anos.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.shady_wizard.more/2
    en  To be honest at the counter, one day. It'll take a while to undo eleven years of the other thing.
    >>  ............................................
    pt  Ser honesto no balcão, um dia. Vai levar um tempo pra desfazer onze anos do contrário.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things I'd destroy if I knew how. Selling them is out and burying them is worse.
    >>  ............................................
    pt  Três coisas que eu destruiria se soubesse como. Vender está fora e enterrar é pior.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.shady_wizard.more/2
    en  To say 'I don't know'. The first time I do, half of them will decide the last eleven years were invented.
    >>  ............................................
    pt  Dizer 'eu não sei'. Na primeira vez, metade vai decidir que os últimos onze anos foram inventados.
    >>  ............................................
  shy.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things. Behind the counter, where saying no is easier than explaining why.
    >>  ............................................
    pt  Três coisas. Atrás do balcão, onde dizer não é mais fácil que explicar por quê.
    >>  ............................................
  shy.dialogue.conversations.work.prof.shady_wizard.more/2
    en  To say 'I don't know'. Half of what I sell is confidence, and confidence is a hard habit to put down.
    >>  ............................................
    pt  Dizer 'eu não sei'. Metade do que eu vendo é confiança, e confiança é um hábito difícil de largar.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things! Behind the counter, so I can say no with the door shut. Very civilised.
    >>  ............................................
    pt  Três coisas! Atrás do balcão, pra eu poder dizer não com a porta fechada. Muito civilizado.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.shady_wizard.more/2
    en  I'd like to say 'I don't know' out loud one day. Eleven years of the opposite makes that difficult.
    >>  ............................................
    pt  Eu queria dizer 'eu não sei' em voz alta um dia. Onze anos do contrário dificultam.
    >>  ............................................
  witty.dialogue.conversations.work.prof.shady_wizard.more/1
    en  Three things! Behind the counter, so I can say no with the door shut. Very civilised.
    >>  ............................................
    pt  Três coisas! Atrás do balcão, pra eu poder dizer não com a porta fechada. Muito civilizado.
    >>  ............................................
  witty.dialogue.conversations.work.prof.shady_wizard.more/2
    en  I'd like to say 'I don't know' out loud one day. Eleven years of the opposite makes that difficult.
    >>  ............................................
    pt  Eu queria dizer 'eu não sei' em voz alta um dia. Onze anos do contrário dificultam.
    >>  ............................................
```

</details>


### Button `leave` — "No refunds."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.shady_wizard.challenge.landed`, `work.shady_wizard.challenge.stung`, `work.shady_wizard.craft.admire`, `work.shady_wizard.craft.ask_books`, `work.shady_wizard.craft.ask_chest`, `work.shady_wizard.future.ask_dontknow`, `work.shady_wizard.future.ask_sensible`, `work.shady_wizard.future.encourage`, `work.shady_wizard.hard`, `work.shady_wizard.risk.ask_first_name`, `work.shady_wizard.risk.ask_shelf`, `work.shady_wizard.risk.sympathise`, `work.shady_wizard.task.ask_pretend`, `work.shady_wizard.task.ask_whom`, `work.shady_wizard.task.offer_hands`, `work.shady_wizard.value`, `work.shady_wizard.village.ask_birth`, `work.shady_wizard.village.ask_lane`, `work.shady_wizard.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.followup.leave   [11 chars]
    en  No refunds.
    >>  ............................................
    pt  Sem reembolso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.leave
WHO    VILLAGER — what the player reads after pressing "No refunds."
       spoken on: conversations.topic.work.shady_wizard.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.left`: the villager accepts. Subject `work.shady_wizard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shady_wizard.followup / leave; conversations.scene.work.shady_wizard.inherited_page.active.respond / leave; conversations.scene.work.shady_wizard.inherited_page.succeeded.respond / leave; conversations.scene.work.shady_wizard.misfire.blocked.respond / leave; conversations.scene.work.shady_wizard.misfire.succeeded.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.active.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond / leave; conversations.topic.work.shady_wizard.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shady_wizard.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.shady_wizard.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.shady_wizard.future` — e.g. "I want to be able to say 'I don't know' at the counter and keep the customer. That's the whole ambition."


```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.shady_wizard.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.shady_wizard.future.respond   [35 chars]
    en  That's what's ahead of the counter.
    >>  ............................................
    pt  É o que está à frente do balcão.
    >>  ............................................
```


### Button `ask_dontknow` — "What's preventing you from saying it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shady_wizard.future` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shady_wizard.future.ask_dontknow` — accepted phrasings: "what's preventing you from saying it"
  - the message must contain one of: `saying`, `preventing`, `admit`
  - scored words: `saying`(1.2), `preventing`(1.5), `admit`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.future.respond.ask_dontknow
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.future.respond.ask_dontknow   [37 chars]
    en  What's preventing you from saying it?
    >>  ............................................
    pt  O que te impede de dizer isso?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shady_wizard.future.ask_dontknow`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shady_wizard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there anything you won't sell?" | "No refunds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.future.ask_dontknow
WHO    VILLAGER — what the player reads after pressing "What's preventing you from saying it?"
       spoken on: conversations.topic.work.shady_wizard.future.respond, button `ask_dontknow`
       leaves the player on: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.future.ask_dontknow`: the villager explains. Subject `work.shady_wizard.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shady_wizard.future.ask_dontknow/1   [94 chars]
    en  Eleven years of having said the opposite. You can't change your voice with the same customers.
    >>  ............................................
    pt  Onze anos tendo dito o contrário. Você não muda de voz com os mesmos clientes.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.future.ask_dontknow/2   [96 chars]
    en  The first time I say it, %1$s, half of them decide the previous eleven years were also invented.
    >>  ............................................
    pt  Na primeira vez que eu disser, %1$s, metade decide que os onze anos anteriores também foram inventados.
    >>  ............................................
```


### Button `encourage` — "Say it once to the cleric. She already knows."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.shady_wizard.future` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shady_wizard.future.encourage` — accepted phrasings: "say it once to the cleric. she already knows"
  - the message must contain one of: `cleric`, `once`, `knows`
  - scored words: `cleric`(1.5), `once`(1.0), `knows`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.future.respond.encourage   [45 chars]
    en  Say it once to the cleric. She already knows.
    >>  ............................................
    pt  Diga uma vez à clériga. Ela já sabe.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.shady_wizard.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.shady_wizard.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.shady_wizard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there anything you won't sell?" | "No refunds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.future.encourage
WHO    VILLAGER — what the player reads after pressing "Say it once to the cleric. She already knows."
       spoken on: conversations.topic.work.shady_wizard.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.future.encourage`: the villager accepts. Subject `work.shady_wizard.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shady_wizard.future.encourage/1   [94 chars]
    en  ...She already knows. That's — yes. That's a very small first step and I can actually take it.
    >>  ............................................
    pt  ...Ela já sabe. Isso é — sim. É um primeiro passo bem pequeno e eu consigo dar.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.future.encourage/2   [101 chars]
    en  She'd not blink. She'd say 'good' and hand me something to grind, %1$s. That's exactly what she'd do.
    >>  ............................................
    pt  Ela nem piscaria. Diria 'ótimo' e me daria algo pra moer, %1$s. É exatamente o que ela faria.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows. Which means the fear was never about her at all.
    >>  ............................................
    pt  ...Ela já sabe. O que significa que o medo nunca foi dela.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. She'd say 'good', and I'd have to sit with being seen.
    >>  ............................................
    pt  Ela nem piscaria. Diria 'bom', e eu teria que aguentar ser visto.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows. People generally do; it's the pretending that ages you.
    >>  ............................................
    pt  ...Ela já sabe. As pessoas geralmente sabem; é o fingimento que envelhece.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. Twenty years behind a counter and the honest ones never blink.
    >>  ............................................
    pt  Ela nem piscaria. Vinte anos atrás de um balcão e os honestos nunca piscam.
    >>  ............................................
  confident.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows. That's — yes. A very small first step and I can take it.
    >>  ............................................
    pt  ...Ela já sabe. Isso é — sim. Um primeiro passo bem pequeno e eu consigo dar.
    >>  ............................................
  confident.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. She'd say 'good' and hand me something to grind.
    >>  ............................................
    pt  Ela nem piscaria. Diria 'bom' e me daria algo pra moer.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows. That's — yes. A very small first step and I can take it.
    >>  ............................................
    pt  ...Ela já sabe. Isso é — sim. Um primeiro passo bem pequeno e eu consigo dar.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. She'd say 'good' and hand me something to grind.
    >>  ............................................
    pt  Ela nem piscaria. Diria 'bom' e me daria algo pra moer.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows, %1$s. That's a very small first step and I can take it.
    >>  ............................................
    pt  ...Ela já sabe, %1$s. É um primeiro passo bem pequeno e eu consigo dar.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. She'd say 'good' and hand me something to grind.
    >>  ............................................
    pt  Ela nem piscaria. Diria 'bom' e me daria algo pra moer.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows, %1$s. That's a very small first step and I can take it.
    >>  ............................................
    pt  ...Ela já sabe, %1$s. É um primeiro passo bem pequeno e eu consigo dar.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. She'd say 'good' and hand me something to grind.
    >>  ............................................
    pt  Ela nem piscaria. Diria 'bom' e me daria algo pra moer.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows, %1$s. That's a very small first step and I can take it.
    >>  ............................................
    pt  ...Ela já sabe, %1$s. É um primeiro passo bem pequeno e eu consigo dar.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. She'd say 'good' and hand me something to grind.
    >>  ............................................
    pt  Ela nem piscaria. Diria 'bom' e me daria algo pra moer.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows. Which means the fear was never about her at all.
    >>  ............................................
    pt  ...Ela já sabe. O que significa que o medo nunca foi dela.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. She'd say 'good', and I'd have to sit with being seen.
    >>  ............................................
    pt  Ela nem piscaria. Diria 'bom', e eu teria que aguentar ser visto.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows. That's — yes. A very small first step and I can take it.
    >>  ............................................
    pt  ...Ela já sabe. Isso é — sim. Um primeiro passo bem pequeno e eu consigo dar.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. She'd say 'good' and hand me something to grind.
    >>  ............................................
    pt  Ela nem piscaria. Diria 'bom' e me daria algo pra moer.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows. That's — yes. A very small first step and I can take it.
    >>  ............................................
    pt  ...Ela já sabe. Isso é — sim. Um primeiro passo bem pequeno e eu consigo dar.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. She'd say 'good' and hand me something to grind.
    >>  ............................................
    pt  Ela nem piscaria. Diria 'bom' e me daria algo pra moer.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows. Small step. I can take it.
    >>  ............................................
    pt  ...Ela já sabe. Passo pequeno. Eu consigo.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. She'd say 'good'.
    >>  ............................................
    pt  Ela nem piscaria. Diria 'bom'.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows. People generally do; it's the pretending that ages you.
    >>  ............................................
    pt  ...Ela já sabe. As pessoas geralmente sabem; é o fingimento que envelhece.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. Twenty years behind a counter and the honest ones never blink.
    >>  ............................................
    pt  Ela nem piscaria. Vinte anos atrás de um balcão e os honestos nunca piscam.
    >>  ............................................
  odd.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows. Small step. I can take it.
    >>  ............................................
    pt  ...Ela já sabe. Passo pequeno. Eu consigo.
    >>  ............................................
  odd.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. She'd say 'good'.
    >>  ............................................
    pt  Ela nem piscaria. Diria 'bom'.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows. People generally do; it's the pretending that ages you.
    >>  ............................................
    pt  ...Ela já sabe. As pessoas geralmente sabem; é o fingimento que envelhece.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. Twenty years behind a counter and the honest ones never blink.
    >>  ............................................
    pt  Ela nem piscaria. Vinte anos atrás de um balcão e os honestos nunca piscam.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows! That's — yes. A very small first step, and I can actually take it.
    >>  ............................................
    pt  ...Ela já sabe! Isso é — sim. Um primeiro passo bem pequeno, e eu consigo dar.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. She'd say 'good' and hand me something to grind, which is exactly her.
    >>  ............................................
    pt  Ela nem piscaria. Diria 'bom' e me daria algo pra moer, que é exatamente ela.
    >>  ............................................
  playful.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows! That's — yes. A very small first step, and I can actually take it.
    >>  ............................................
    pt  ...Ela já sabe! Isso é — sim. Um primeiro passo bem pequeno, e eu consigo dar.
    >>  ............................................
  playful.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. She'd say 'good' and hand me something to grind, which is exactly her.
    >>  ............................................
    pt  Ela nem piscaria. Diria 'bom' e me daria algo pra moer, que é exatamente ela.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows. People generally do; it's the pretending that ages you.
    >>  ............................................
    pt  ...Ela já sabe. As pessoas geralmente sabem; é o fingimento que envelhece.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. Twenty years behind a counter and the honest ones never blink.
    >>  ............................................
    pt  Ela nem piscaria. Vinte anos atrás de um balcão e os honestos nunca piscam.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows. Which means the fear was never about her at all.
    >>  ............................................
    pt  ...Ela já sabe. O que significa que o medo nunca foi dela.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. She'd say 'good', and I'd have to sit with being seen.
    >>  ............................................
    pt  Ela nem piscaria. Diria 'bom', e eu teria que aguentar ser visto.
    >>  ............................................
  shy.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows. Small step. I can take it.
    >>  ............................................
    pt  ...Ela já sabe. Passo pequeno. Eu consigo.
    >>  ............................................
  shy.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. She'd say 'good'.
    >>  ............................................
    pt  Ela nem piscaria. Diria 'bom'.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows! That's — yes. A very small first step, and I can actually take it.
    >>  ............................................
    pt  ...Ela já sabe! Isso é — sim. Um primeiro passo bem pequeno, e eu consigo dar.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. She'd say 'good' and hand me something to grind, which is exactly her.
    >>  ............................................
    pt  Ela nem piscaria. Diria 'bom' e me daria algo pra moer, que é exatamente ela.
    >>  ............................................
  witty.dialogue.conversations.work.prof.shady_wizard.future.encourage/1
    en  ...She already knows! That's — yes. A very small first step, and I can actually take it.
    >>  ............................................
    pt  ...Ela já sabe! Isso é — sim. Um primeiro passo bem pequeno, e eu consigo dar.
    >>  ............................................
  witty.dialogue.conversations.work.prof.shady_wizard.future.encourage/2
    en  She'd not blink. She'd say 'good' and hand me something to grind, which is exactly her.
    >>  ............................................
    pt  Ela nem piscaria. Diria 'bom' e me daria algo pra moer, que é exatamente ela.
    >>  ............................................
```

</details>


### Button `ask_sensible` — "Who's sensible enough for the books?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shady_wizard.future` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shady_wizard.future.ask_sensible` — accepted phrasings: "who's sensible enough for the books"
  - the message must contain one of: `sensible`, `books`, `inherit`
  - scored words: `sensible`(1.5), `books`(1.0), `inherit`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.future.respond.ask_sensible
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.future.respond.ask_sensible   [36 chars]
    en  Who's sensible enough for the books?
    >>  ............................................
    pt  Quem é sensato o bastante pros livros?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shady_wizard.future.ask_sensible`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shady_wizard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there anything you won't sell?" | "No refunds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.future.ask_sensible
WHO    VILLAGER — what the player reads after pressing "Who's sensible enough for the books?"
       spoken on: conversations.topic.work.shady_wizard.future.respond, button `ask_sensible`
       leaves the player on: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.future.ask_sensible`: the villager explains. Subject `work.shady_wizard.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shady_wizard.future.ask_sensible/1   [91 chars]
    en  The librarian, who'd catalogue them and never open the eighth. That's the correct instinct.
    >>  ............................................
    pt  O bibliotecário, que catalogaria e nunca abriria o oitavo. É o instinto certo.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.future.ask_sensible/2   [92 chars]
    en  Nobody who'd want them, %1$s. That's the difficulty and it's the same difficulty every time.
    >>  ............................................
    pt  Ninguém que os queira, %1$s. É essa a dificuldade e é a mesma dificuldade sempre.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to business."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.shady_wizard.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.future.respond.leave   [34 chars]
    en  I'll let you get back to business.
    >>  ............................................
    pt  Vou deixar você voltar aos negócios.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to business."
       spoken on: conversations.topic.work.shady_wizard.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.left`: the villager accepts. Subject `work.shady_wizard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shady_wizard.followup / leave; conversations.scene.work.shady_wizard.inherited_page.active.respond / leave; conversations.scene.work.shady_wizard.inherited_page.succeeded.respond / leave; conversations.scene.work.shady_wizard.misfire.blocked.respond / leave; conversations.scene.work.shady_wizard.misfire.succeeded.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.active.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond / leave; conversations.topic.work.shady_wizard.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shady_wizard.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.shady_wizard.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.shady_wizard` — e.g. "I sell... solutions. Magical ones. No refunds, no questions, and definitely no warranty cards."


```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.shady_wizard.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.shady_wizard.respond   [32 chars]
    en  That's the stock, and the terms.
    >>  ............................................
    pt  É o estoque, e os termos.
    >>  ............................................
```


### Button `ask_hard` — "What happens when a solution goes wrong?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shady_wizard.identity` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shady_wizard.hard` — accepted phrasings: "what happens when a solution goes wrong"
  - the message must contain one of: `wrong`, `solution`, `refund`
  - scored words: `wrong`(1.2), `solution`(1.5), `refund`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.respond.ask_hard   [40 chars]
    en  What happens when a solution goes wrong?
    >>  ............................................
    pt  O que acontece quando uma solução dá errado?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.shady_wizard.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shady_wizard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there anything you won't sell?" | "No refunds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.hard
WHO    VILLAGER — what the player reads after pressing "What happens when a solution goes wrong?"
       spoken on: conversations.topic.work.shady_wizard.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.hard`: the villager explains. Subject `work.shady_wizard.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shady_wizard.followup / ask_more
```

> Written out in full under **`conversations.scene.work.shady_wizard.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "You actually know what you're doing, don't you."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.shady_wizard.identity` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shady_wizard.value` — accepted phrasings: "you actually know what you're doing, don't you"
  - the message must contain one of: `know`, `doing`, `expertise`
  - scored words: `know`(1.0), `doing`(1.2), `expertise`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.respond.value   [47 chars]
    en  You actually know what you're doing, don't you.
    >>  ............................................
    pt  Você realmente sabe o que faz, não é.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.shady_wizard.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.shady_wizard.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shady_wizard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there anything you won't sell?" | "No refunds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.value
WHO    VILLAGER — what the player reads after pressing "You actually know what you're doing, don't you."
       spoken on: conversations.topic.work.shady_wizard.respond, button `value`
       leaves the player on: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.value`: the villager accepts. Subject `work.shady_wizard.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shady_wizard.value/1   [72 chars]
    en  ...Please don't say that where customers can hear. It ruins the pricing.
    >>  ............................................
    pt  ...Por favor não diga isso onde os clientes ouçam. Estraga o preço.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.value/2   [68 chars]
    en  I do. Twenty years of it. The patter is a service, not a substitute.
    >>  ............................................
    pt  Sei. Vinte anos disso. O papo é um serviço, não um substituto.
    >>  ............................................
```


### Button `challenge` — "Half of this is stolen."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.shady_wizard.identity` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shady_wizard.challenge` — accepted phrasings: "half of this is stolen"
  - the message must contain one of: `stolen`, `stole`, `sourced`
  - scored words: `stolen`(1.5), `stole`(1.5), `sourced`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.respond.challenge   [23 chars]
    en  Half of this is stolen.
    >>  ............................................
    pt  Metade disso é roubado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.shady_wizard.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.shady_wizard.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shady_wizard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there anything you won't sell?" | "No refunds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.challenge.landed
WHO    VILLAGER — what the player reads after pressing "Half of this is stolen."
       spoken on: conversations.topic.work.shady_wizard.respond, button `challenge`
       leaves the player on: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.challenge.landed`: the villager resists. Subject `work.shady_wizard.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shady_wizard.challenge.landed/1   [74 chars]
    en  'Sourced creatively' was the agreed phrasing, and I'd thank you to use it.
    >>  ............................................
    pt  'Obtido criativamente' era a expressão combinada, e eu agradeceria se você a usasse.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.challenge.landed/2   [64 chars]
    en  Not half. A third, and only from people who won't miss it, %1$s.
    >>  ............................................
    pt  Metade não. Um terço, e só de gente que não vai sentir falta, %1$s.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.shady_wizard.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.shady_wizard.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shady_wizard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there anything you won't sell?" | "No refunds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.challenge.stung
WHO    VILLAGER — what the player reads after pressing "Half of this is stolen."
       spoken on: conversations.topic.work.shady_wizard.respond, button `challenge`
       leaves the player on: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.challenge.stung`: the villager resists. Subject `work.shady_wizard.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shady_wizard.challenge.stung/1   [67 chars]
    en  ...Everything on that shelf has paperwork. Somewhere. In some form.
    >>  ............................................
    pt  ...Tudo naquela prateleira tem documentação. Em algum lugar. De alguma forma.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.challenge.stung/2   [73 chars]
    en  Stolen. Right. Take your custom to the honest wizard down the road, then.
    >>  ............................................
    pt  Roubado. Certo. Leve seu dinheiro ao mago honesto ali adiante, então.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to business."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.shady_wizard.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.respond.leave   [34 chars]
    en  I'll let you get back to business.
    >>  ............................................
    pt  Vou deixar você voltar aos negócios.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to business."
       spoken on: conversations.topic.work.shady_wizard.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.left`: the villager accepts. Subject `work.shady_wizard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shady_wizard.followup / leave; conversations.scene.work.shady_wizard.inherited_page.active.respond / leave; conversations.scene.work.shady_wizard.inherited_page.succeeded.respond / leave; conversations.scene.work.shady_wizard.misfire.blocked.respond / leave; conversations.scene.work.shady_wizard.misfire.succeeded.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.active.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond / leave; conversations.topic.work.shady_wizard.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shady_wizard.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.shady_wizard.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.shady_wizard.risk` — e.g. "If something goes wrong in this valley, the man with the jars is the first name anybody says."


```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.shady_wizard.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.shady_wizard.risk.respond   [20 chars]
    en  That's what I carry.
    >>  ............................................
    pt  É o que eu carrego.
    >>  ............................................
```


### Button `ask_first_name` — "Has it happened?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shady_wizard.risk` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shady_wizard.risk.ask_first_name` — accepted phrasings: "has it happened"
  - the message must contain one of: `happened`, `blamed`, `accused`
  - scored words: `happened`(1.2), `blamed`(1.5), `accused`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.risk.respond.ask_first_name
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.risk.respond.ask_first_name   [16 chars]
    en  Has it happened?
    >>  ............................................
    pt  Já aconteceu?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shady_wizard.risk.ask_first_name`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shady_wizard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there anything you won't sell?" | "No refunds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.risk.ask_first_name
WHO    VILLAGER — what the player reads after pressing "Has it happened?"
       spoken on: conversations.topic.work.shady_wizard.risk.respond, button `ask_first_name`
       leaves the player on: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.risk.ask_first_name`: the villager explains. Subject `work.shady_wizard.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shady_wizard.risk.ask_first_name/1   [96 chars]
    en  Twice. Both times it was a well and a wet spring, and both times I was asked to prove otherwise.
    >>  ............................................
    pt  Duas vezes. Nas duas foi um poço e uma primavera úmida, e nas duas me pediram pra provar o contrário.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.risk.ask_first_name/2   [91 chars]
    en  Once the cleric stood up and said it wasn't me, %1$s. I've done a great deal for her since.
    >>  ............................................
    pt  Uma vez a clériga levantou e disse que não fui eu, %1$s. Fiz muita coisa por ela desde então.
    >>  ............................................
```


### Button `sympathise` — "Having to prove otherwise every time is exhausting."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.shady_wizard.risk` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shady_wizard.risk.sympathise` — accepted phrasings: "having to prove otherwise every time is exhausting"
  - the message must contain one of: `prove`, `exhausting`, `otherwise`
  - scored words: `prove`(1.5), `exhausting`(1.5), `otherwise`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.risk.respond.sympathise   [51 chars]
    en  Having to prove otherwise every time is exhausting.
    >>  ............................................
    pt  Ter que provar o contrário toda vez é exaustivo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.shady_wizard.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.shady_wizard.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shady_wizard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there anything you won't sell?" | "No refunds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "Having to prove otherwise every time is exhausting."
       spoken on: conversations.topic.work.shady_wizard.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.risk.sympathise`: the villager accepts. Subject `work.shady_wizard.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shady_wizard.risk.sympathise/1   [100 chars]
    en  ...It is. And I can't complain, because I chose a trade that looks exactly like the thing they fear.
    >>  ............................................
    pt  ...É. E eu não posso reclamar, porque escolhi um ofício que parece exatamente com o que eles temem.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.risk.sympathise/2   [93 chars]
    en  It's the price on the label, %1$s, and I read the label before I bought in. It's still heavy.
    >>  ............................................
    pt  É o preço na etiqueta, %1$s, e eu li antes de comprar. Continua pesado.
    >>  ............................................
```


### Button `ask_shelf` — "What's on the top shelf?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shady_wizard.risk` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shady_wizard.risk.ask_shelf` — accepted phrasings: "what's on the top shelf"
  - the message must contain one of: `shelf`, `top`, `jar`
  - scored words: `shelf`(1.5), `top`(1.2), `jar`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.risk.respond.ask_shelf
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.risk.respond.ask_shelf   [24 chars]
    en  What's on the top shelf?
    >>  ............................................
    pt  O que tem na prateleira de cima?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shady_wizard.risk.ask_shelf`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shady_wizard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there anything you won't sell?" | "No refunds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.risk.ask_shelf
WHO    VILLAGER — what the player reads after pressing "What's on the top shelf?"
       spoken on: conversations.topic.work.shady_wizard.risk.respond, button `ask_shelf`
       leaves the player on: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.risk.ask_shelf`: the villager explains. Subject `work.shady_wizard.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shady_wizard.risk.ask_shelf/1   [93 chars]
    en  A jar with a hand-written lid and a date on it older than this village. That's what I'll say.
    >>  ............................................
    pt  Um pote com tampa escrita à mão e uma data mais velha que este vilarejo. É o que eu digo.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.risk.ask_shelf/2   [80 chars]
    en  Something I'd rather nobody inherits, %1$s, which is the entire problem with it.
    >>  ............................................
    pt  Algo que eu preferia que ninguém herdasse, %1$s, que é todo o problema.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to business."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.shady_wizard.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.risk.respond.leave   [34 chars]
    en  I'll let you get back to business.
    >>  ............................................
    pt  Vou deixar você voltar aos negócios.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to business."
       spoken on: conversations.topic.work.shady_wizard.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.left`: the villager accepts. Subject `work.shady_wizard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shady_wizard.followup / leave; conversations.scene.work.shady_wizard.inherited_page.active.respond / leave; conversations.scene.work.shady_wizard.inherited_page.succeeded.respond / leave; conversations.scene.work.shady_wizard.misfire.blocked.respond / leave; conversations.scene.work.shady_wizard.misfire.succeeded.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.active.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond / leave; conversations.topic.work.shady_wizard.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shady_wizard.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.shady_wizard.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.shady_wizard.task` — e.g. "Sorting stock and deciding what I'm prepared to sell to whom. That second part takes the afternoon."


```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.shady_wizard.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.shady_wizard.task.respond   [25 chars]
    en  That's the counter today.
    >>  ............................................
    pt  É o balcão hoje.
    >>  ............................................
```


### Button `ask_whom` — "What wouldn't you sell, and to whom?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shady_wizard.task` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shady_wizard.task.ask_whom` — accepted phrasings: "what wouldn't you sell, and to whom"
  - the message must contain one of: `sell`, `whom`, `refuse`
  - scored words: `sell`(1.2), `whom`(1.5), `refuse`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.task.respond.ask_whom
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.task.respond.ask_whom   [36 chars]
    en  What wouldn't you sell, and to whom?
    >>  ............................................
    pt  O que você não venderia, e a quem?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shady_wizard.task.ask_whom`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shady_wizard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there anything you won't sell?" | "No refunds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.task.ask_whom
WHO    VILLAGER — what the player reads after pressing "What wouldn't you sell, and to whom?"
       spoken on: conversations.topic.work.shady_wizard.task.respond, button `ask_whom`
       leaves the player on: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.task.ask_whom`: the villager explains. Subject `work.shady_wizard.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shady_wizard.task.ask_whom/1   [101 chars]
    en  Anything binding, to anybody angry. That rule has cost me more custom than every other rule combined.
    >>  ............................................
    pt  Nada vinculante, pra ninguém com raiva. Essa regra me custou mais clientela que todas as outras juntas.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.task.ask_whom/2   [94 chars]
    en  The third shelf, to a man who won't say what it's for, %1$s. I've turned two away this season.
    >>  ............................................
    pt  A terceira prateleira, pra um homem que não diz pra quê, %1$s. Recusei dois nesta estação.
    >>  ............................................
```


### Button `offer_hands` — "I can label the jars."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.shady_wizard.task` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shady_wizard.task.offer_hands` — accepted phrasings: "i can label the jars"
  - the message must contain one of: `jars`, `label`, `sort`
  - scored words: `jars`(1.5), `label`(1.5), `sort`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.task.respond.offer_hands   [21 chars]
    en  I can label the jars.
    >>  ............................................
    pt  Eu posso rotular os potes.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.shady_wizard.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.shady_wizard.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shady_wizard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there anything you won't sell?" | "No refunds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I can label the jars."
       spoken on: conversations.topic.work.shady_wizard.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.task.offer_hands`: the villager accepts. Subject `work.shady_wizard.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shady_wizard.task.offer_hands/1   [88 chars]
    en  ...You can. Copy exactly what's on the lid and invent nothing, however obvious it looks.
    >>  ............................................
    pt  ...Pode. Copie exatamente o que está na tampa e não invente nada, por mais óbvio que pareça.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.task.offer_hands/2   [71 chars]
    en  Front row only, %1$s, and don't open anything to check. Ask me instead.
    >>  ............................................
    pt  Só a fileira da frente, %1$s, e não abra nada pra conferir. Pergunte a mim.
    >>  ............................................
```


### Button `ask_pretend` — "Why pretend to understand it?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shady_wizard.task` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shady_wizard.task.ask_pretend` — accepted phrasings: "why pretend to understand it"
  - the message must contain one of: `pretend`, `understand`, `admit`
  - scored words: `pretend`(1.5), `understand`(1.2), `admit`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.task.respond.ask_pretend
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.task.respond.ask_pretend   [29 chars]
    en  Why pretend to understand it?
    >>  ............................................
    pt  Por que fingir que entende?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shady_wizard.task.ask_pretend`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shady_wizard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there anything you won't sell?" | "No refunds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.task.ask_pretend
WHO    VILLAGER — what the player reads after pressing "Why pretend to understand it?"
       spoken on: conversations.topic.work.shady_wizard.task.respond, button `ask_pretend`
       leaves the player on: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.task.ask_pretend`: the villager explains. Subject `work.shady_wizard.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shady_wizard.task.ask_pretend/1   [110 chars]
    en  Because half of what I sell is confidence and the other half is a jar. That's an ugly sentence and a true one.
    >>  ............................................
    pt  Porque metade do que eu vendo é confiança e metade é um pote. É uma frase feia e verdadeira.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.task.ask_pretend/2   [93 chars]
    en  Because a wizard who says 'I'm not sure' gets no second customer, %1$s, and I've tested that.
    >>  ............................................
    pt  Porque um mago que diz 'não tenho certeza' não tem segundo cliente, %1$s, e eu testei isso.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to business."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.shady_wizard.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.task.respond.leave   [34 chars]
    en  I'll let you get back to business.
    >>  ............................................
    pt  Vou deixar você voltar aos negócios.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to business."
       spoken on: conversations.topic.work.shady_wizard.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.left`: the villager accepts. Subject `work.shady_wizard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shady_wizard.followup / leave; conversations.scene.work.shady_wizard.inherited_page.active.respond / leave; conversations.scene.work.shady_wizard.inherited_page.succeeded.respond / leave; conversations.scene.work.shady_wizard.misfire.blocked.respond / leave; conversations.scene.work.shady_wizard.misfire.succeeded.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.active.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond / leave; conversations.topic.work.shady_wizard.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shady_wizard.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.shady_wizard.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.shady_wizard.village` — e.g. "Four fevers, two burns and a birth that went sideways. That's my ledger and I keep it privately."


```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.shady_wizard.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.shady_wizard.village.respond   [19 chars]
    en  That's my standing.
    >>  ............................................
    pt  É a minha posição.
    >>  ............................................
```


### Button `ask_birth` — "The birth that went sideways?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shady_wizard.village` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shady_wizard.village.ask_birth` — accepted phrasings: "the birth that went sideways"
  - the message must contain one of: `birth`, `sideways`, `delivery`
  - scored words: `birth`(1.5), `sideways`(1.2), `delivery`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.village.respond.ask_birth
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.village.respond.ask_birth   [29 chars]
    en  The birth that went sideways?
    >>  ............................................
    pt  O parto que complicou?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shady_wizard.village.ask_birth`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shady_wizard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there anything you won't sell?" | "No refunds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.village.ask_birth
WHO    VILLAGER — what the player reads after pressing "The birth that went sideways?"
       spoken on: conversations.topic.work.shady_wizard.village.respond, button `ask_birth`
       leaves the player on: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.village.ask_birth`: the villager explains. Subject `work.shady_wizard.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shady_wizard.village.ask_birth/1   [103 chars]
    en  The cleric sent for me at two in the morning. She has never mentioned it and neither have I, until now.
    >>  ............................................
    pt  A clériga mandou me chamar às duas da manhã. Ela nunca mencionou e eu também não, até agora.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.village.ask_birth/2   [81 chars]
    en  Both alive. That's all I'll say, %1$s, and it's the sentence I'd have on a stone.
    >>  ............................................
    pt  Os dois vivos. É tudo que eu digo, %1$s, e é a frase que eu queria numa lápide.
    >>  ............................................
```


### Button `say_thanks` — "A private ledger is a ledger nobody can argue with."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.shady_wizard.village` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shady_wizard.village.say_thanks` — accepted phrasings: "a private ledger is a ledger nobody can argue with"
  - the message must contain one of: `ledger`, `private`, `argue`
  - scored words: `ledger`(1.5), `private`(1.2), `argue`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.village.respond.say_thanks   [51 chars]
    en  A private ledger is a ledger nobody can argue with.
    >>  ............................................
    pt  Um registro privado é um registro que ninguém contesta.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.shady_wizard.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.shady_wizard.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shady_wizard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there anything you won't sell?" | "No refunds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "A private ledger is a ledger nobody can argue with."
       spoken on: conversations.topic.work.shady_wizard.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.village.say_thanks`: the villager accepts. Subject `work.shady_wizard.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shady_wizard.village.say_thanks/1   [86 chars]
    en  ...Nor thank me for. I've been telling myself the first half of that for eleven years.
    >>  ............................................
    pt  ...Nem pelo qual me agradecem. Venho me dizendo a primeira metade disso há onze anos.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.village.say_thanks/2   [78 chars]
    en  That's a generous reading of cowardice, %1$s, and I'm going to take it anyway.
    >>  ............................................
    pt  É uma leitura generosa de covardia, %1$s, e eu vou aceitar mesmo assim.
    >>  ............................................
```


### Button `ask_lane` — "Does the crossing the lane bother you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.shady_wizard.village` · offered only once the villager has actually said `work:shady_wizard`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.shady_wizard.village.ask_lane` — accepted phrasings: "does the crossing the lane bother you"
  - the message must contain one of: `lane`, `bother`, `avoid`
  - scored words: `lane`(1.5), `bother`(1.2), `avoid`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.village.respond.ask_lane
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.village.respond.ask_lane   [38 chars]
    en  Does the crossing the lane bother you?
    >>  ............................................
    pt  Atravessar a rua te incomoda?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.shady_wizard.village.ask_lane`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.shady_wizard.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Is there anything you won't sell?" | "No refunds."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.village.ask_lane
WHO    VILLAGER — what the player reads after pressing "Does the crossing the lane bother you?"
       spoken on: conversations.topic.work.shady_wizard.village.respond, button `ask_lane`
       leaves the player on: conversations.topic.work.shady_wizard.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.village.ask_lane`: the villager explains. Subject `work.shady_wizard.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:shady_wizard` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.shady_wizard.village.ask_lane/1   [97 chars]
    en  Not from the adults. From their children, who learn it by watching, and that's a different thing.
    >>  ............................................
    pt  Dos adultos, não. Dos filhos deles, que aprendem olhando, e isso é outra coisa.
    >>  ............................................
  dialogue.conversations.work.prof.shady_wizard.village.ask_lane/2   [83 chars]
    en  Less each year. That's either healing or numbness and I've not decided which, %1$s.
    >>  ............................................
    pt  Menos a cada ano. Ou é cura ou é dormência e eu não decidi qual, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to business."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.shady_wizard.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.shady_wizard.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.shady_wizard.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.shady_wizard.village.respond.leave   [34 chars]
    en  I'll let you get back to business.
    >>  ............................................
    pt  Vou deixar você voltar aos negócios.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.shady_wizard.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to business."
       spoken on: conversations.topic.work.shady_wizard.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.shady_wizard.left`: the villager accepts. Subject `work.shady_wizard.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.shady_wizard.followup / leave; conversations.scene.work.shady_wizard.inherited_page.active.respond / leave; conversations.scene.work.shady_wizard.inherited_page.succeeded.respond / leave; conversations.scene.work.shady_wizard.misfire.blocked.respond / leave; conversations.scene.work.shady_wizard.misfire.succeeded.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.active.respond / leave; conversations.scene.work.shady_wizard.the_name_they_use.succeeded.respond / leave; conversations.topic.work.shady_wizard.craft.respond / leave …and 5 more
```

> Written out in full under **`conversations.scene.work.shady_wizard.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

