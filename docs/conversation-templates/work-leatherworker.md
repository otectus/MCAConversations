# Work talk with a leatherworker

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.leatherworker.followup`](#conversations-scene-work-leatherworker-followup)
- [`conversations.scene.work.leatherworker.old_repair.succeeded.respond`](#conversations-scene-work-leatherworker-old-repair-succeeded-respond)
- [`conversations.scene.work.leatherworker.stubborn_hide.blocked.respond`](#conversations-scene-work-leatherworker-stubborn-hide-blocked-respond)
- [`conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond`](#conversations-scene-work-leatherworker-stubborn-hide-succeeded-respond)
- [`conversations.scene.work.leatherworker.the_complaint.blocked.respond`](#conversations-scene-work-leatherworker-the-complaint-blocked-respond)
- [`conversations.scene.work.leatherworker.the_complaint.succeeded.respond`](#conversations-scene-work-leatherworker-the-complaint-succeeded-respond)
- [`conversations.topic.work.leatherworker.craft.respond`](#conversations-topic-work-leatherworker-craft-respond)
- [`conversations.topic.work.leatherworker.followup`](#conversations-topic-work-leatherworker-followup)
- [`conversations.topic.work.leatherworker.future.respond`](#conversations-topic-work-leatherworker-future-respond)
- [`conversations.topic.work.leatherworker.respond`](#conversations-topic-work-leatherworker-respond)
- [`conversations.topic.work.leatherworker.risk.respond`](#conversations-topic-work-leatherworker-risk-respond)
- [`conversations.topic.work.leatherworker.task.respond`](#conversations-topic-work-leatherworker-task-respond)
- [`conversations.topic.work.leatherworker.village.respond`](#conversations-topic-work-leatherworker-village-respond)

---

## `conversations.scene.work.leatherworker.followup`

**Reached from 9 route(s):** `conversations.scene.work.leatherworker.old_repair.succeeded.respond` / `ask_how_it_felt`; `conversations.scene.work.leatherworker.old_repair.succeeded.respond` / `note_the_craft`; `conversations.scene.work.leatherworker.stubborn_hide.blocked.respond` / `ask_about_the_time`; `conversations.scene.work.leatherworker.stubborn_hide.blocked.respond` / `offer_leather`; `conversations.scene.work.leatherworker.stubborn_hide.blocked.respond` / `advise_telling_the_customer`; `conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond` / `ask_about_writing_it_down`; `conversations.scene.work.leatherworker.the_complaint.blocked.respond` / `ask_what_could_help`; `conversations.scene.work.leatherworker.the_complaint.blocked.respond` / `advise_doing_it`; `conversations.scene.work.leatherworker.the_complaint.succeeded.respond` / `note_the_arithmetic`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.leatherworker.old_repair.succeeded.acknowledged` — e.g. "It is good leather, mostly. I will take a third of the credit and give the rest to a cow and to whoever oiled it every winter."
- `conversations.scene.work.leatherworker.old_repair.succeeded.answered` — e.g. "Strange. I could see exactly which parts I was unsure of at twenty-six, and every one of them had held."
- `conversations.scene.work.leatherworker.stubborn_hide.blocked.accepted` — e.g. "Then the commission goes out on time and I start a fresh pit tomorrow, which puts me back in the ordinary run of things."
- `conversations.scene.work.leatherworker.stubborn_hide.blocked.agreed_to_warn` — e.g. "Today, then, before I have talked myself into a plan that would nearly work. Nearly working is how I lose customers."
- `conversations.scene.work.leatherworker.stubborn_hide.blocked.explained` — e.g. "Eleven for a good one. You can do it in three and it will crack in a year, and the person who bought it will blame the cow."
- `conversations.scene.work.leatherworker.stubborn_hide.succeeded.explained` — e.g. "Because a season is too long to hold in your head. I have been trusting memory across eleven weeks and calling it experience."
- `conversations.scene.work.leatherworker.the_complaint.blocked.accepted` — e.g. "A week of work to stop two years of resentment. When you put it in those terms I have been being stupid on purpose."
- `conversations.scene.work.leatherworker.the_complaint.blocked.explained` — e.g. "A hedge and a lid. Both would take a week and cost me a hide, and I have been putting it off for two years."
- `conversations.scene.work.leatherworker.the_complaint.succeeded.acknowledged` — e.g. "Cheap and late. I would like the record to show both, because I have watched other trades make the same mistake for longer."


```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.leatherworker.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.leatherworker.followup   [21 chars]
    en  Anything else at all?
    >>  ............................................
    pt  Mais alguma coisa mesmo?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of curing a hide?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.leatherworker.*` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.leatherworker.followup.ask_more` — accepted phrasings: "whats the hardest part of curing a hide"; "what is the hardest part of curing a hide"; "hardest thing about curing hides"
  - the message must contain one of: `hardest`, `curing`
  - scored words: `hardest`(1.8), `curing`(1.8), `whats`(0.8), `part`(0.8), `hide`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.leatherworker.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.leatherworker.followup.ask_more   [41 chars]
    en  What's the hardest part of curing a hide?
    >>  ............................................
    pt  Qual é a parte mais difícil de curtir um couro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.leatherworker.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.leatherworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the best thing you've made?" | "Soft leather."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of curing a hide?"
       spoken on: conversations.scene.work.leatherworker.followup, button `ask_more`
       leaves the player on: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.hard`: the villager explains. Subject `work.leatherworker.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.leatherworker.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.leatherworker.hard/1   [85 chars]
    en  I stopped noticing in my second year. Everyone else has not, and they tell me weekly.
    >>  ............................................
    pt  Parei de sentir no segundo ano. Todo mundo não parou, e me dizem toda semana.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.hard/2   [78 chars]
    en  It's not the smell, %1$s. It's being the only trade the village puts downwind.
    >>  ............................................
    pt  Não é o cheiro, %1$s. É ser o único ofício que o vilarejo põe a favor do vento.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the hides."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.leatherworker.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.leatherworker.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.leatherworker.followup.leave   [28 chars]
    en  I'll leave you to the hides.
    >>  ............................................
    pt  Vou deixar você com os couros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the hides."
       spoken on: conversations.scene.work.leatherworker.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.left`: the villager accepts. Subject `work.leatherworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.leatherworker.old_repair.succeeded.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.blocked.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond / leave; conversations.scene.work.leatherworker.the_complaint.blocked.respond / leave; conversations.scene.work.leatherworker.the_complaint.succeeded.respond / leave; conversations.topic.work.leatherworker.craft.respond / leave; conversations.topic.work.leatherworker.followup / leave; conversations.topic.work.leatherworker.future.respond / leave …and 4 more
```

```text
  dialogue.conversations.work.prof.leatherworker.leave/1   [58 chars]
    en  It waits for nobody and rewards nobody either. Off you go.
    >>  ............................................
    pt  Ele não espera ninguém e não recompensa ninguém. Pode ir.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.leave/2   [34 chars]
    en  Aye. Stand upwind next time, %1$s.
    >>  ............................................
    pt  É. Fique a favor do vento da próxima vez, %1$s.
    >>  ............................................
```

---


## `conversations.scene.work.leatherworker.old_repair.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.leatherworker.old_repair.succeeded` — e.g. "%2$s came back to me last week. I made it twenty years ago and I knew my own stitching before I saw the mark."


```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.old_repair.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.leatherworker.old_repair.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.leatherworker.old_repair.succeeded.respond   [24 chars]
    en  That thing on the bench.
    >>  ............................................
    pt  Aquilo na bancada.
    >>  ............................................
```


### Button `ask_how_it_felt` — "What was that like to see?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.leatherworker.old_repair.succeeded` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.leatherworker.old_repair.succeeded.ask_how_it_felt` — accepted phrasings: "what was that like to see"; "what was that like to see"; "how did it feel seeing it again"
  - the message must contain one of: `see`, `feel`, `again`
  - scored words: `see`(1.8), `feel`(1.8), `again`(1.8), `like`(0.8), `seeing`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.old_repair.succeeded.respond.ask_how_it_felt
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.leatherworker.old_repair.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.leatherworker.old_repair.succeeded.respond.ask_how_it_felt   [26 chars]
    en  What was that like to see?
    >>  ............................................
    pt  Como foi ver aquilo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, warmth +1  _(recorded under topic `work.leatherworker.old_repairs`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.leatherworker.old_repair"}
- Then opens: `conversations.scene.work.leatherworker.followup`
- …where the player's next choices will be: "What's the hardest part of curing a hide?" | "I'll leave you to the hides."

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.old_repair.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "What was that like to see?"
       spoken on: conversations.scene.work.leatherworker.old_repair.succeeded.respond, button `ask_how_it_felt`
       leaves the player on: conversations.scene.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.old_repair.succeeded.answered`: the villager explains. Subject `work.leatherworker.old_repairs`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.leatherworker.old_repair.succeeded.answered/1   [103 chars]
    en  Strange. I could see exactly which parts I was unsure of at twenty-six, and every one of them had held.
    >>  ............................................
    pt  Estranho. Dava para ver exatamente de quais partes eu tinha dúvida aos vinte e seis, e todas tinham aguentado.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.old_repair.succeeded.answered/2   [118 chars]
    en  Like meeting somebody who has done well. I did not make it to last twenty years. I made it to last five and be mended.
    >>  ............................................
    pt  Como encontrar alguém que se deu bem. Não fiz para durar vinte anos. Fiz para durar cinco e ser remendado.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.old_repair.succeeded.answered/3   [138 chars]
    en  I sat with it for an hour before I started. That is not sentimentality — the wear tells you what it needs, and I wanted to read all of it.
    >>  ............................................
    pt  Fiquei uma hora com aquilo antes de começar. Não é sentimentalismo — o desgaste conta o que a peça precisa, e eu queria ler tudo.
    >>  ............................................
```


### Button `note_the_craft` — "Twenty years is good work."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.leatherworker.old_repair.succeeded` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.leatherworker.old_repair.succeeded.note_the_craft` — accepted phrasings: "twenty years is good work"; "twenty years is good work"; "lasting twenty years says a lot"
  - the message must contain one of: `twenty`, `lasting`
  - scored words: `twenty`(1.8), `lasting`(1.8), `years`(0.8), `good`(0.8), `work`(0.8), `says`(0.8), `lot`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.old_repair.succeeded.respond.note_the_craft
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.leatherworker.old_repair.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.leatherworker.old_repair.succeeded.respond.note_the_craft   [26 chars]
    en  Twenty years is good work.
    >>  ............................................
    pt  Vinte anos é bom trabalho.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.leatherworker.old_repairs`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.leatherworker.old_repair"}
- Then opens: `conversations.scene.work.leatherworker.followup`
- …where the player's next choices will be: "What's the hardest part of curing a hide?" | "I'll leave you to the hides."

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.old_repair.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Twenty years is good work."
       spoken on: conversations.scene.work.leatherworker.old_repair.succeeded.respond, button `note_the_craft`
       leaves the player on: conversations.scene.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.old_repair.succeeded.acknowledged`: the villager accepts. Subject `work.leatherworker.old_repairs`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.leatherworker.old_repair.succeeded.acknowledged/1   [126 chars]
    en  It is good leather, mostly. I will take a third of the credit and give the rest to a cow and to whoever oiled it every winter.
    >>  ............................................
    pt  É couro bom, principalmente. Fico com um terço do crédito e dou o resto a uma vaca e a quem passou óleo todo inverno.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.old_repair.succeeded.acknowledged/2   [126 chars]
    en  Thank you. Nobody praises a thing that works. You only ever hear about the ones that failed, so this has been an unusual week.
    >>  ............................................
    pt  Obrigada. Ninguém elogia uma coisa que funciona. Só se ouve falar das que falharam, então esta foi uma semana incomum.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.old_repair.succeeded.acknowledged/3   [128 chars]
    en  It is the only kind of praise this trade can get, and it arrives twenty years late, and you have to still be here to receive it.
    >>  ............................................
    pt  É o único tipo de elogio que este ofício recebe, e chega vinte anos atrasado, e você precisa ainda estar aqui para receber.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pits."

*stance family `exit` · tone `plain` · answers the beat(s) `work.leatherworker.old_repair.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.old_repair.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.leatherworker.old_repair.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.leatherworker.old_repair.succeeded.respond.leave   [34 chars]
    en  I'll let you get back to the pits.
    >>  ............................................
    pt  Vou deixar você voltar aos tanques.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pits."
       spoken on: conversations.scene.work.leatherworker.old_repair.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.left`: the villager accepts. Subject `work.leatherworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.leatherworker.followup / leave; conversations.scene.work.leatherworker.stubborn_hide.blocked.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond / leave; conversations.scene.work.leatherworker.the_complaint.blocked.respond / leave; conversations.scene.work.leatherworker.the_complaint.succeeded.respond / leave; conversations.topic.work.leatherworker.craft.respond / leave; conversations.topic.work.leatherworker.followup / leave; conversations.topic.work.leatherworker.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.leatherworker.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.leatherworker.stubborn_hide.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.leatherworker.stubborn_hide.blocked` — e.g. "%2$s came out with %3$s after eleven weeks, and eleven weeks is not a thing you get back."


```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.leatherworker.stubborn_hide.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.respond   [9 chars]
    en  The pits.
    >>  ............................................
    pt  Os tanques.
    >>  ............................................
```


### Button `ask_about_the_time` — "Eleven weeks for one hide?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.leatherworker.stubborn_hide.blocked` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.leatherworker.stubborn_hide.blocked.ask_about_the_time` — accepted phrasings: "eleven weeks for one hide"; "eleven weeks for one hide"; "how long does the tanning take"
  - the message must contain one of: `weeks`, `tanning`, `hide`
  - scored words: `weeks`(1.8), `tanning`(1.8), `hide`(1.8), `eleven`(0.8), `one`(0.8), `long`(0.8), `does`(0.8), `take`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.respond.ask_about_the_time
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.leatherworker.stubborn_hide.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.respond.ask_about_the_time   [26 chars]
    en  Eleven weeks for one hide?
    >>  ............................................
    pt  Onze semanas por um couro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.leatherworker.hides`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.leatherworker.stubborn_hide"}
- Then opens: `conversations.scene.work.leatherworker.followup`
- …where the player's next choices will be: "What's the hardest part of curing a hide?" | "I'll leave you to the hides."

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.explained
WHO    VILLAGER — what the player reads after pressing "Eleven weeks for one hide?"
       spoken on: conversations.scene.work.leatherworker.stubborn_hide.blocked.respond, button `ask_about_the_time`
       leaves the player on: conversations.scene.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.stubborn_hide.blocked.explained`: the villager explains. Subject `work.leatherworker.hides`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.explained/1   [123 chars]
    en  Eleven for a good one. You can do it in three and it will crack in a year, and the person who bought it will blame the cow.
    >>  ............................................
    pt  Onze para um bom. Dá para fazer em três e ele racha em um ano, e quem comprou vai culpar a vaca.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.explained/2   [126 chars]
    en  The work is about four days. The rest is standing still, which is the part nobody will pay for and the only part that matters.
    >>  ............................................
    pt  O trabalho leva uns quatro dias. O resto é ficar parado, que é a parte que ninguém paga e a única que importa.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.explained/3   [115 chars]
    en  Time is the ingredient. I cannot buy more of it, I cannot hurry it, and I cannot get it back when a pit goes wrong.
    >>  ............................................
    pt  O tempo é o ingrediente. Não posso comprar mais, não posso apressar, e não posso recuperar quando um tanque dá errado.
    >>  ............................................
```


### Button `offer_leather` — "I'll bring you leather to cover it."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.leatherworker.stubborn_hide.blocked` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.leatherworker.stubborn_hide.blocked.offer_leather` — accepted phrasings: "ill bring you leather to cover it"; "i can bring you leather"; "let me fetch leather for that"
  - the message must contain one of: `leather`
  - scored words: `leather`(1.8), `ill`(0.8), `bring`(0.8), `cover`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.respond.offer_leather
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.leatherworker.stubborn_hide.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.respond.offer_leather   [35 chars]
    en  I'll bring you leather to cover it.
    >>  ............................................
    pt  Vou trazer couro para cobrir isso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.leatherworker.hide.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.leatherworker.hides`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.stubborn_hide", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.leatherworker.stubborn_hide", "obligation": "commitment:work.leatherworker.bring_hide"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.leatherworker.bring_hide"}
- Then opens: `conversations.scene.work.leatherworker.followup`
- …where the player's next choices will be: "What's the hardest part of curing a hide?" | "I'll leave you to the hides."

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you leather to cover it."
       spoken on: conversations.scene.work.leatherworker.stubborn_hide.blocked.respond, button `offer_leather`
       leaves the player on: conversations.scene.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.stubborn_hide.blocked.accepted`: the villager accepts. Subject `work.leatherworker.hides`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.accepted/1   [120 chars]
    en  Then the commission goes out on time and I start a fresh pit tomorrow, which puts me back in the ordinary run of things.
    >>  ............................................
    pt  Então a encomenda sai no prazo e eu começo um tanque novo amanhã, o que me devolve ao ritmo normal.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.accepted/2   [119 chars]
    en  That saves me a season. I want you to understand the size of it, because leather looks like a small thing to hand over.
    >>  ............................................
    pt  Isso me poupa uma estação. Quero que você entenda o tamanho disso, porque couro parece pouca coisa de se entregar.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.accepted/3   [114 chars]
    en  Yes. And I will make you something out of the offcuts, and you can tell me in three months what you want it to be.
    >>  ............................................
    pt  Sim. E vou fazer algo para você com as sobras, e você me diz em três meses o que quer que seja.
    >>  ............................................
```


### Button `advise_telling_the_customer` — "Tell the customer it will be late."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.leatherworker.stubborn_hide.blocked` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.leatherworker.stubborn_hide.blocked.advise_telling_the_customer` — accepted phrasings: "tell the customer it will be late"; "tell the customer it will be late"; "warn them about the delay now"
  - the message must contain one of: `customer`, `delay`, `late`
  - scored words: `customer`(1.8), `delay`(1.8), `late`(1.8), `tell`(0.8), `warn`(0.8), `now`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.respond.advise_telling_the_customer
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.leatherworker.stubborn_hide.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.respond.advise_telling_the_customer   [34 chars]
    en  Tell the customer it will be late.
    >>  ............................................
    pt  Avise o cliente do atraso.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3  _(recorded under topic `work.leatherworker.hides`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.leatherworker.stubborn_hide"}
- Then opens: `conversations.scene.work.leatherworker.followup`
- …where the player's next choices will be: "What's the hardest part of curing a hide?" | "I'll leave you to the hides."

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.agreed_to_warn
WHO    VILLAGER — what the player reads after pressing "Tell the customer it will be late."
       spoken on: conversations.scene.work.leatherworker.stubborn_hide.blocked.respond, button `advise_telling_the_customer`
       leaves the player on: conversations.scene.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.stubborn_hide.blocked.agreed_to_warn`: the villager accepts. Subject `work.leatherworker.hides`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.agreed_to_warn/1   [116 chars]
    en  Today, then, before I have talked myself into a plan that would nearly work. Nearly working is how I lose customers.
    >>  ............................................
    pt  Hoje, então, antes de eu me convencer de um plano que quase funcionaria. Quase funcionar é como eu perco clientes.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.agreed_to_warn/2   [97 chars]
    en  You are right. A season is a long time to wait and a very short time to be told about in advance.
    >>  ............................................
    pt  Você tem razão. Uma estação é muito tempo para esperar e pouquíssimo tempo para se avisar com antecedência.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.agreed_to_warn/3   [122 chars]
    en  I will, and I will offer them the choice between waiting and taking a thinner hide. Given the choice, people usually wait.
    >>  ............................................
    pt  Vou, e vou oferecer a escolha entre esperar e levar um couro mais fino. Dada a escolha, as pessoas costumam esperar.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pits."

*stance family `exit` · tone `plain` · answers the beat(s) `work.leatherworker.stubborn_hide.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.leatherworker.stubborn_hide.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.blocked.respond.leave   [34 chars]
    en  I'll let you get back to the pits.
    >>  ............................................
    pt  Vou deixar você voltar aos tanques.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pits."
       spoken on: conversations.scene.work.leatherworker.stubborn_hide.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.left`: the villager accepts. Subject `work.leatherworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.leatherworker.followup / leave; conversations.scene.work.leatherworker.old_repair.succeeded.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond / leave; conversations.scene.work.leatherworker.the_complaint.blocked.respond / leave; conversations.scene.work.leatherworker.the_complaint.succeeded.respond / leave; conversations.topic.work.leatherworker.craft.respond / leave; conversations.topic.work.leatherworker.followup / leave; conversations.topic.work.leatherworker.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.leatherworker.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.leatherworker.stubborn_hide.succeeded` — e.g. "The new pit came out clean. I changed one thing — I moved it out of the afternoon sun — and that was apparently the whole answer."


```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond   [16 chars]
    en  The pits, since.
    >>  ............................................
    pt  Os tanques, depois disso.
    >>  ............................................
```


### Button `ask_about_writing_it_down` — "Why keep a record now?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.leatherworker.stubborn_hide.succeeded` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.leatherworker.stubborn_hide.succeeded.ask_about_writing_it_down` — accepted phrasings: "why keep a record now"; "why keep a record now"; "what made you start writing it down"
  - the message must contain one of: `record`, `writing`
  - scored words: `record`(1.8), `writing`(1.8), `why`(0.8), `keep`(0.8), `now`(0.8), `made`(0.8), `start`(0.8), `down`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond.ask_about_writing_it_down
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond.ask_about_writing_it_down   [22 chars]
    en  Why keep a record now?
    >>  ............................................
    pt  Por que registrar agora?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.leatherworker.hides`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.leatherworker.stubborn_hide"}
- Then opens: `conversations.scene.work.leatherworker.followup`
- …where the player's next choices will be: "What's the hardest part of curing a hide?" | "I'll leave you to the hides."

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.stubborn_hide.succeeded.explained
WHO    VILLAGER — what the player reads after pressing "Why keep a record now?"
       spoken on: conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond, button `ask_about_writing_it_down`
       leaves the player on: conversations.scene.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.stubborn_hide.succeeded.explained`: the villager explains. Subject `work.leatherworker.hides`, polarity `positive`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.succeeded.explained/1   [125 chars]
    en  Because a season is too long to hold in your head. I have been trusting memory across eleven weeks and calling it experience.
    >>  ............................................
    pt  Porque uma estação é longa demais para caber na cabeça. Eu vinha confiando na memória por onze semanas e chamando isso de experiência.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.succeeded.explained/2   [119 chars]
    en  The bad pit taught me. I could not say what had been different, and that was the frightening part, not the ruined hide.
    >>  ............................................
    pt  O tanque ruim me ensinou. Eu não sabia dizer o que tinha sido diferente, e essa foi a parte assustadora, não o couro perdido.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.succeeded.explained/3   [122 chars]
    en  My mother never wrote anything down and knew everything. I am not my mother, and it has taken me twenty years to admit it.
    >>  ............................................
    pt  Minha mãe nunca anotou nada e sabia tudo. Eu não sou minha mãe, e levei vinte anos para admitir.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pits."

*stance family `exit` · tone `plain` · answers the beat(s) `work.leatherworker.stubborn_hide.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond.leave   [34 chars]
    en  I'll let you get back to the pits.
    >>  ............................................
    pt  Vou deixar você voltar aos tanques.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pits."
       spoken on: conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.left`: the villager accepts. Subject `work.leatherworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.leatherworker.followup / leave; conversations.scene.work.leatherworker.old_repair.succeeded.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.blocked.respond / leave; conversations.scene.work.leatherworker.the_complaint.blocked.respond / leave; conversations.scene.work.leatherworker.the_complaint.succeeded.respond / leave; conversations.topic.work.leatherworker.craft.respond / leave; conversations.topic.work.leatherworker.followup / leave; conversations.topic.work.leatherworker.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.leatherworker.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.leatherworker.the_complaint.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.leatherworker.the_complaint.blocked` — e.g. "%2$s has complained about the pits again, and they are right, and I have nowhere else to put them."


```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.the_complaint.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.leatherworker.the_complaint.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.leatherworker.the_complaint.blocked.respond   [15 chars]
    en  The neighbours.
    >>  ............................................
    pt  Os vizinhos.
    >>  ............................................
```


### Button `ask_what_could_help` — "Is there anything that would help?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.leatherworker.the_complaint.blocked` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.leatherworker.the_complaint.blocked.ask_what_could_help` — accepted phrasings: "is there anything that would help"; "is there anything that would help"; "what would make it easier for them"
  - the message must contain one of: `help`, `easier`
  - scored words: `help`(1.8), `easier`(1.8), `anything`(0.8), `make`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.the_complaint.blocked.respond.ask_what_could_help
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.leatherworker.the_complaint.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.leatherworker.the_complaint.blocked.respond.ask_what_could_help   [34 chars]
    en  Is there anything that would help?
    >>  ............................................
    pt  Tem alguma coisa que ajudaria?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.leatherworker.the_smell`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.leatherworker.the_complaint"}
- Then opens: `conversations.scene.work.leatherworker.followup`
- …where the player's next choices will be: "What's the hardest part of curing a hide?" | "I'll leave you to the hides."

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.the_complaint.blocked.explained
WHO    VILLAGER — what the player reads after pressing "Is there anything that would help?"
       spoken on: conversations.scene.work.leatherworker.the_complaint.blocked.respond, button `ask_what_could_help`
       leaves the player on: conversations.scene.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.the_complaint.blocked.explained`: the villager explains. Subject `work.leatherworker.the_smell`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.leatherworker.the_complaint.blocked.explained/1   [107 chars]
    en  A hedge and a lid. Both would take a week and cost me a hide, and I have been putting it off for two years.
    >>  ............................................
    pt  Uma cerca viva e uma tampa. As duas levariam uma semana e me custariam um couro, e faz dois anos que eu adio.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.the_complaint.blocked.explained/2   [138 chars]
    en  Doing the worst of it on the days the wind is off the river. I know which days those are and I have been ignoring them out of convenience.
    >>  ............................................
    pt  Fazer a parte pior nos dias em que o vento sopra do rio. Eu sei quais são e venho ignorando por conveniência.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.the_complaint.blocked.explained/3   [116 chars]
    en  Telling them when. Half of a nuisance is not knowing how long it will last, and I have never once given them a date.
    >>  ............................................
    pt  Avisar quando. Metade de um incômodo é não saber quanto tempo vai durar, e eu nunca dei uma data a eles.
    >>  ............................................
```


### Button `advise_doing_it` — "Build the hedge and give them a date."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.leatherworker.the_complaint.blocked` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.leatherworker.the_complaint.blocked.advise_doing_it` — accepted phrasings: "build the hedge and give them a date"; "build the hedge and give them a date"; "put up a screen and tell them when"
  - the message must contain one of: `hedge`, `screen`, `date`
  - scored words: `hedge`(1.8), `screen`(1.8), `date`(1.8), `build`(0.8), `give`(0.8), `put`(0.8), `tell`(0.8), `when`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.the_complaint.blocked.respond.advise_doing_it
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.leatherworker.the_complaint.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.leatherworker.the_complaint.blocked.respond.advise_doing_it   [37 chars]
    en  Build the hedge and give them a date.
    >>  ............................................
    pt  Faça a cerca e dê uma data a eles.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +1  _(recorded under topic `work.leatherworker.the_smell`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.leatherworker.the_complaint"}
- Then opens: `conversations.scene.work.leatherworker.followup`
- …where the player's next choices will be: "What's the hardest part of curing a hide?" | "I'll leave you to the hides."

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.the_complaint.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "Build the hedge and give them a date."
       spoken on: conversations.scene.work.leatherworker.the_complaint.blocked.respond, button `advise_doing_it`
       leaves the player on: conversations.scene.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.the_complaint.blocked.accepted`: the villager accepts. Subject `work.leatherworker.the_smell`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.leatherworker.the_complaint.blocked.accepted/1   [115 chars]
    en  A week of work to stop two years of resentment. When you put it in those terms I have been being stupid on purpose.
    >>  ............................................
    pt  Uma semana de trabalho para encerrar dois anos de ressentimento. Colocado assim, eu venho sendo burra de propósito.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.the_complaint.blocked.accepted/2   [130 chars]
    en  Yes. And I will tell %2$s the date to their face rather than letting it be discovered, because being told is the whole difference.
    >>  ............................................
    pt  Sim. E vou dizer a data a %2$s na cara, em vez de deixar que descubram, porque ser avisado é a diferença inteira.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.the_complaint.blocked.accepted/3   [126 chars]
    en  I have been treating their complaint as an attack on the trade. It is a complaint about a smell, and a smell can be worked on.
    >>  ............................................
    pt  Eu vinha tratando a reclamação como ataque ao ofício. É uma reclamação sobre cheiro, e cheiro dá para trabalhar.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pits."

*stance family `exit` · tone `plain` · answers the beat(s) `work.leatherworker.the_complaint.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.the_complaint.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.leatherworker.the_complaint.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.leatherworker.the_complaint.blocked.respond.leave   [34 chars]
    en  I'll let you get back to the pits.
    >>  ............................................
    pt  Vou deixar você voltar aos tanques.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pits."
       spoken on: conversations.scene.work.leatherworker.the_complaint.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.left`: the villager accepts. Subject `work.leatherworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.leatherworker.followup / leave; conversations.scene.work.leatherworker.old_repair.succeeded.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.blocked.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond / leave; conversations.scene.work.leatherworker.the_complaint.succeeded.respond / leave; conversations.topic.work.leatherworker.craft.respond / leave; conversations.topic.work.leatherworker.followup / leave; conversations.topic.work.leatherworker.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.leatherworker.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.leatherworker.the_complaint.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.leatherworker.the_complaint.succeeded` — e.g. "The hedge is in and %2$s has said nothing since, which from that quarter is a standing ovation."


```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.the_complaint.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.leatherworker.the_complaint.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.leatherworker.the_complaint.succeeded.respond   [22 chars]
    en  The neighbours, since.
    >>  ............................................
    pt  Os vizinhos, depois disso.
    >>  ............................................
```


### Button `note_the_arithmetic` — "Cheap at the price."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.leatherworker.the_complaint.succeeded` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.leatherworker.the_complaint.succeeded.note_the_arithmetic` — accepted phrasings: "cheap at the price"; "cheap at the price"; "that was cheap for what it fixed"
  - the message must contain one of: `cheap`, `price`
  - scored words: `cheap`(1.8), `price`(1.8), `fixed`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.the_complaint.succeeded.respond.note_the_arithmetic
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.leatherworker.the_complaint.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.leatherworker.the_complaint.succeeded.respond.note_the_arithmetic   [19 chars]
    en  Cheap at the price.
    >>  ............................................
    pt  Barato pelo que resolveu.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +2, respect +2  _(recorded under topic `work.leatherworker.the_smell`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.leatherworker.the_complaint"}
- Then opens: `conversations.scene.work.leatherworker.followup`
- …where the player's next choices will be: "What's the hardest part of curing a hide?" | "I'll leave you to the hides."

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.the_complaint.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Cheap at the price."
       spoken on: conversations.scene.work.leatherworker.the_complaint.succeeded.respond, button `note_the_arithmetic`
       leaves the player on: conversations.scene.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.the_complaint.succeeded.acknowledged`: the villager accepts. Subject `work.leatherworker.the_smell`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.leatherworker.the_complaint.succeeded.acknowledged/1   [123 chars]
    en  Cheap and late. I would like the record to show both, because I have watched other trades make the same mistake for longer.
    >>  ............................................
    pt  Barato e tardio. Quero as duas coisas registradas, porque já vi outros ofícios cometerem o mesmo erro por mais tempo.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.the_complaint.succeeded.acknowledged/2   [91 chars]
    en  It was. What made it expensive was pride, and pride does not appear anywhere in the ledger.
    >>  ............................................
    pt  Foi. O que encareceu foi o orgulho, e orgulho não aparece em lugar nenhum do livro-caixa.
    >>  ............................................
  dialogue.conversations.scene.work.leatherworker.the_complaint.succeeded.acknowledged/3   [114 chars]
    en  Thank you. I will remember it the next time somebody complains about something I have stopped being able to smell.
    >>  ............................................
    pt  Obrigada. Vou lembrar disso na próxima vez que alguém reclamar de algo que eu já parei de conseguir sentir.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the pits."

*stance family `exit` · tone `plain` · answers the beat(s) `work.leatherworker.the_complaint.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.leatherworker.the_complaint.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.leatherworker.the_complaint.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.leatherworker.the_complaint.succeeded.respond.leave   [34 chars]
    en  I'll let you get back to the pits.
    >>  ............................................
    pt  Vou deixar você voltar aos tanques.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the pits."
       spoken on: conversations.scene.work.leatherworker.the_complaint.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.left`: the villager accepts. Subject `work.leatherworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.leatherworker.followup / leave; conversations.scene.work.leatherworker.old_repair.succeeded.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.blocked.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond / leave; conversations.scene.work.leatherworker.the_complaint.blocked.respond / leave; conversations.topic.work.leatherworker.craft.respond / leave; conversations.topic.work.leatherworker.followup / leave; conversations.topic.work.leatherworker.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.leatherworker.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.leatherworker.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.leatherworker.craft` — e.g. "Tanning is chemistry you learn by ruining things. I ruined nine hides before I ruined none."


```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.leatherworker.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.leatherworker.craft.respond   [23 chars]
    en  That's the craft of it.
    >>  ............................................
    pt  É esse o ofício.
    >>  ............................................
```


### Button `ask_nine` — "Who paid for the nine hides?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.leatherworker.craft` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.leatherworker.craft.ask_nine` — accepted phrasings: "who paid for the nine hides"
  - the message must contain one of: `nine`, `paid`, `hides`
  - scored words: `nine`(1.5), `paid`(1.2), `hides`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.craft.respond.ask_nine
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.craft.respond.ask_nine   [28 chars]
    en  Who paid for the nine hides?
    >>  ............................................
    pt  Quem pagou os nove couros?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.leatherworker.craft.ask_nine`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.leatherworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the best thing you've made?" | "Soft leather."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.craft.ask_nine
WHO    VILLAGER — what the player reads after pressing "Who paid for the nine hides?"
       spoken on: conversations.topic.work.leatherworker.craft.respond, button `ask_nine`
       leaves the player on: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.craft.ask_nine`: the villager explains. Subject `work.leatherworker.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.leatherworker.craft.ask_nine/1   [79 chars]
    en  My master, and he never once mentioned it, which I only understood years later.
    >>  ............................................
    pt  Meu mestre, e ele nunca mencionou, o que eu só entendi anos depois.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.craft.ask_nine/2   [64 chars]
    en  I did, eventually, out of my first three years of takings, %1$s.
    >>  ............................................
    pt  Eu, no fim, com os ganhos dos meus três primeiros anos, %1$s.
    >>  ............................................
```


### Button `admire` — "A bad hole is forever. That's a whole philosophy."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.leatherworker.craft` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.leatherworker.craft.admire` — accepted phrasings: "a bad hole is forever. that's a whole philosophy"
  - the message must contain one of: `forever`, `philosophy`, `permanent`
  - scored words: `forever`(1.5), `philosophy`(1.5), `permanent`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.craft.respond.admire   [49 chars]
    en  A bad hole is forever. That's a whole philosophy.
    >>  ............................................
    pt  Um furo ruim é pra sempre. É uma filosofia inteira.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.leatherworker.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.leatherworker.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.leatherworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the best thing you've made?" | "Soft leather."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.craft.admire
WHO    VILLAGER — what the player reads after pressing "A bad hole is forever. That's a whole philosophy."
       spoken on: conversations.topic.work.leatherworker.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.craft.admire`: the villager accepts. Subject `work.leatherworker.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.leatherworker.craft.admire/1   [88 chars]
    en  It's leather. Everything else you've built a philosophy out of was probably leather too.
    >>  ............................................
    pt  É couro. Todo o resto de que você fez filosofia provavelmente também era couro.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.craft.admire/2   [79 chars]
    en  It's why I go slowly and why people think I'm slow. Those are different things.
    >>  ............................................
    pt  É por isso que eu vou devagar e por isso me acham lento. São coisas diferentes.
    >>  ............................................
```


### Button `ask_awl` — "How do you make a good hole?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.leatherworker.craft` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.leatherworker.craft.ask_awl` — accepted phrasings: "how do you make a good hole"
  - the message must contain one of: `awl`, `hole`, `punch`
  - scored words: `awl`(1.5), `hole`(1.5), `punch`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.craft.respond.ask_awl
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.craft.respond.ask_awl   [28 chars]
    en  How do you make a good hole?
    >>  ............................................
    pt  Como se faz um furo bom?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.leatherworker.craft.ask_awl`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.leatherworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the best thing you've made?" | "Soft leather."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.craft.ask_awl
WHO    VILLAGER — what the player reads after pressing "How do you make a good hole?"
       spoken on: conversations.topic.work.leatherworker.craft.respond, button `ask_awl`
       leaves the player on: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.craft.ask_awl`: the villager explains. Subject `work.leatherworker.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.leatherworker.craft.ask_awl/1   [88 chars]
    en  Mark it, then breathe, then push straight. Most bad holes are made by a hand in a hurry.
    >>  ............................................
    pt  Marque, respire, empurre reto. A maioria dos furos ruins vem de mão apressada.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.craft.ask_awl/2   [99 chars]
    en  The awl does it. Your part is to hold steady and not help, which is the hardest instruction I give.
    >>  ............................................
    pt  A sovela faz. Sua parte é ficar firme e não ajudar, a instrução mais difícil que eu dou.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the vat."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.leatherworker.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.craft.respond.leave   [33 chars]
    en  I'll let you get back to the vat.
    >>  ............................................
    pt  Vou deixar você voltar ao tanque.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the vat."
       spoken on: conversations.topic.work.leatherworker.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.left`: the villager accepts. Subject `work.leatherworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.leatherworker.followup / leave; conversations.scene.work.leatherworker.old_repair.succeeded.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.blocked.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond / leave; conversations.scene.work.leatherworker.the_complaint.blocked.respond / leave; conversations.scene.work.leatherworker.the_complaint.succeeded.respond / leave; conversations.topic.work.leatherworker.followup / leave; conversations.topic.work.leatherworker.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.leatherworker.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.leatherworker.followup`

**Reached from 20 route(s):** `conversations.scene.work.leatherworker.followup` / `ask_more`; `conversations.topic.work.leatherworker.craft.respond` / `ask_nine`; `conversations.topic.work.leatherworker.craft.respond` / `admire`; `conversations.topic.work.leatherworker.craft.respond` / `ask_awl`; `conversations.topic.work.leatherworker.future.respond` / `ask_eleven`; `conversations.topic.work.leatherworker.future.respond` / `encourage`; `conversations.topic.work.leatherworker.future.respond` / `ask_saddle_dream`; `conversations.topic.work.leatherworker.respond` / `ask_hard`; `conversations.topic.work.leatherworker.respond` / `value`; `conversations.topic.work.leatherworker.respond` / `challenge`; `conversations.topic.work.leatherworker.respond` / `challenge`; `conversations.topic.work.leatherworker.risk.respond` / `ask_pits` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.leatherworker.challenge.landed` — e.g. "It is. So is birth and so is bread. Filthy and necessary keep close company."
- `conversations.work.prof.leatherworker.challenge.stung` — e.g. "...Then walk barefoot and we'll see how the argument holds."
- `conversations.work.prof.leatherworker.craft.admire` — e.g. "It's leather. Everything else you've built a philosophy out of was probably leather too."
- `conversations.work.prof.leatherworker.craft.ask_awl` — e.g. "Mark it, then breathe, then push straight. Most bad holes are made by a hand in a hurry."
- `conversations.work.prof.leatherworker.craft.ask_nine` — e.g. "My master, and he never once mentioned it, which I only understood years later."
- `conversations.work.prof.leatherworker.future.ask_eleven` — e.g. "Eleven. I stand in the same spot in the same hall and say the same four sentences."
- `conversations.work.prof.leatherworker.future.ask_saddle_dream` — e.g. "Tree of ash, four months seasoning, and every stitch by hand. It would outlive the horse."
- `conversations.work.prof.leatherworker.future.encourage` — e.g. "...Somebody else in the room. That has genuinely never occurred to me in eleven years."
- `conversations.work.prof.leatherworker.hard` — e.g. "I stopped noticing in my second year. Everyone else has not, and they tell me weekly."
- `conversations.work.prof.leatherworker.risk.ask_pits` — e.g. "A dog, once. That was enough for me to rebuild the fence twice as high, at my own cost."
- `conversations.work.prof.leatherworker.risk.ask_saddle` — e.g. "Rot in the tree, under the leather, where nobody looks. That's the one that kills."
- `conversations.work.prof.leatherworker.risk.sympathise` — e.g. "...I do. Nobody else counts them and somebody ought to, so it may as well be me."
- `conversations.work.prof.leatherworker.task.ask_broader` — e.g. "Because he'd hold his breath through the fitting and I'd cut it to a man who wasn't there."
- `conversations.work.prof.leatherworker.task.ask_smell` — e.g. "Not from me. My daughter says she can find me in the dark, which she means kindly."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.leatherworker.followup   [29 chars]
    en  That's the vat and the bench.
    >>  ............................................
    pt  É o tanque e a bancada.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.leatherworker.challenge.landed`, `work.leatherworker.challenge.stung`, `work.leatherworker.craft.admire`, `work.leatherworker.craft.ask_awl`, `work.leatherworker.craft.ask_nine`, `work.leatherworker.future.ask_eleven`, `work.leatherworker.future.ask_saddle_dream`, `work.leatherworker.future.encourage`, `work.leatherworker.hard`, `work.leatherworker.risk.ask_pits`, `work.leatherworker.risk.ask_saddle`, `work.leatherworker.risk.sympathise`, `work.leatherworker.task.ask_broader`, `work.leatherworker.task.ask_smell`, `work.leatherworker.task.offer_hands`, `work.leatherworker.value`, `work.leatherworker.village.ask_boots`, `work.leatherworker.village.ask_butcher`, `work.leatherworker.village.say_thanks` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.leatherworker.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `strap`, `leather`
  - scored words: `thought`(1.2), `strap`(1.2), `leather`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.leatherworker.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.leatherworker.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.leatherworker.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.leatherworker.thanks`: the villager accepts. Subject `work.leatherworker.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.leatherworker.thanks/1   [78 chars]
    en  Nobody thinks about leather until a strap goes. Then I'm everyone's favourite.
    >>  ............................................
    pt  Ninguém pensa em couro até uma tira arrebentar. Aí eu sou o favorito de todo mundo.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.thanks/2   [72 chars]
    en  It's an invisible trade until it fails, %1$s. Most of the good ones are.
    >>  ............................................
    pt  É um ofício invisível até falhar, %1$s. Os bons quase sempre são.
    >>  ............................................
```


### Button `ask_more` — "What's the best thing you've made?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.leatherworker.challenge.landed`, `work.leatherworker.challenge.stung`, `work.leatherworker.craft.admire`, `work.leatherworker.craft.ask_awl`, `work.leatherworker.craft.ask_nine`, `work.leatherworker.future.ask_eleven`, `work.leatherworker.future.ask_saddle_dream`, `work.leatherworker.future.encourage`, `work.leatherworker.hard`, `work.leatherworker.risk.ask_pits`, `work.leatherworker.risk.ask_saddle`, `work.leatherworker.risk.sympathise`, `work.leatherworker.task.ask_broader`, `work.leatherworker.task.ask_smell`, `work.leatherworker.task.offer_hands`, `work.leatherworker.value`, `work.leatherworker.village.ask_boots`, `work.leatherworker.village.ask_butcher`, `work.leatherworker.village.say_thanks` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.leatherworker.more` — accepted phrasings: "what's the best thing you've made"
  - the message must contain one of: `best`, `made`, `proud`
  - scored words: `best`(1.2), `made`(1.0), `proud`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.followup.ask_more   [34 chars]
    en  What's the best thing you've made?
    >>  ............................................
    pt  Qual a melhor coisa que você fez?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.leatherworker.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.more
WHO    VILLAGER — what the player reads after pressing "What's the best thing you've made?"
       spoken on: conversations.topic.work.leatherworker.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.leatherworker.more`: the villager discloses. Subject `work.leatherworker.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.leatherworker.more/1   [81 chars]
    en  A pair of boots for the old shepherd. Twelve years and they're still on his feet.
    >>  ............................................
    pt  Um par de botas pro velho pastor. Doze anos e ainda estão nos pés dele.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.more/2   [80 chars]
    en  A book binding for the library. It'll outlast me by a century if the roof holds.
    >>  ............................................
    pt  Uma encadernação pra biblioteca. Vai me sobreviver por um século se o telhado aguentar.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.leatherworker.more/1
    en  Boots for the old shepherd. Twelve years on his feet, and he's the only one who ever said thank you.
    >>  ............................................
    pt  Botas pro velho pastor. Doze anos nos pés dele, e ele é o único que já agradeceu.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.leatherworker.more/2
    en  The pits are poison and everyone's children walk past them. That's why I keep asking.
    >>  ............................................
    pt  Os tanques são veneno e as crianças de todos passam por perto. É por isso que eu continuo pedindo.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.leatherworker.more/1
    en  Boots for the old shepherd. Twelve years. Good leather outlasts the argument about good leather.
    >>  ............................................
    pt  Botas pro velho pastor. Doze anos. Couro bom sobrevive à discussão sobre couro bom.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.leatherworker.more/2
    en  The pits will move eventually. Eleven years of asking is not so long for a thing that heavy.
    >>  ............................................
    pt  Os tanques vão mudar uma hora. Onze anos pedindo não é tanto pra uma coisa tão pesada.
    >>  ............................................
  confident.dialogue.conversations.work.prof.leatherworker.more/1
    en  A pair of boots for the old shepherd. Twelve years and they are still on his feet.
    >>  ............................................
    pt  Um par de botas pro velho pastor. Doze anos e ainda estão nos pés dele.
    >>  ............................................
  confident.dialogue.conversations.work.prof.leatherworker.more/2
    en  The pits moved downwind and downstream. It's the same request I've made for eleven years.
    >>  ............................................
    pt  Os tanques a favor do vento e rio abaixo. É o mesmo pedido que eu faço há onze anos.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.leatherworker.more/1
    en  A pair of boots for the old shepherd. Twelve years and they are still on his feet.
    >>  ............................................
    pt  Um par de botas pro velho pastor. Doze anos e ainda estão nos pés dele.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.leatherworker.more/2
    en  The pits moved downwind and downstream. It's the same request I've made for eleven years.
    >>  ............................................
    pt  Os tanques a favor do vento e rio abaixo. É o mesmo pedido que eu faço há onze anos.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.leatherworker.more/1
    en  A pair of boots for the old shepherd. Ask him — he'll show you them and tell you the year.
    >>  ............................................
    pt  Um par de botas pro velho pastor. Pergunte a ele — vai te mostrar e dizer o ano.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.leatherworker.more/2
    en  The pits moved. I've asked alone eleven times; it never occurred to me to bring somebody.
    >>  ............................................
    pt  Mudar os tanques. Pedi sozinho onze vezes; nunca me ocorreu levar alguém.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.leatherworker.more/1
    en  A pair of boots for the old shepherd. Ask him — he'll show you them and tell you the year.
    >>  ............................................
    pt  Um par de botas pro velho pastor. Pergunte a ele — vai te mostrar e dizer o ano.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.leatherworker.more/2
    en  The pits moved. I've asked alone eleven times; it never occurred to me to bring somebody.
    >>  ............................................
    pt  Mudar os tanques. Pedi sozinho onze vezes; nunca me ocorreu levar alguém.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.leatherworker.more/1
    en  A pair of boots for the old shepherd. Ask him — he'll show you them and tell you the year.
    >>  ............................................
    pt  Um par de botas pro velho pastor. Pergunte a ele — vai te mostrar e dizer o ano.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.leatherworker.more/2
    en  The pits moved. I've asked alone eleven times; it never occurred to me to bring somebody.
    >>  ............................................
    pt  Mudar os tanques. Pedi sozinho onze vezes; nunca me ocorreu levar alguém.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.leatherworker.more/1
    en  Boots for the old shepherd. Twelve years on his feet, and he's the only one who ever said thank you.
    >>  ............................................
    pt  Botas pro velho pastor. Doze anos nos pés dele, e ele é o único que já agradeceu.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.leatherworker.more/2
    en  The pits are poison and everyone's children walk past them. That's why I keep asking.
    >>  ............................................
    pt  Os tanques são veneno e as crianças de todos passam por perto. É por isso que eu continuo pedindo.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.leatherworker.more/1
    en  A pair of boots for the old shepherd. Twelve years and they are still on his feet.
    >>  ............................................
    pt  Um par de botas pro velho pastor. Doze anos e ainda estão nos pés dele.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.leatherworker.more/2
    en  The pits moved downwind and downstream. It's the same request I've made for eleven years.
    >>  ............................................
    pt  Os tanques a favor do vento e rio abaixo. É o mesmo pedido que eu faço há onze anos.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.leatherworker.more/1
    en  A pair of boots for the old shepherd. Twelve years and they are still on his feet.
    >>  ............................................
    pt  Um par de botas pro velho pastor. Doze anos e ainda estão nos pés dele.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.leatherworker.more/2
    en  The pits moved downwind and downstream. It's the same request I've made for eleven years.
    >>  ............................................
    pt  Os tanques a favor do vento e rio abaixo. É o mesmo pedido que eu faço há onze anos.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.leatherworker.more/1
    en  Boots for the old shepherd. Twelve years. Ash last, hand-stitched, and no varnish anywhere.
    >>  ............................................
    pt  Botas pro velho pastor. Doze anos. Forma de freixo, costura à mão, e nada de verniz.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.leatherworker.more/2
    en  The pits, moved downwind. Same spot, same hall, same four sentences, eleven years running.
    >>  ............................................
    pt  Os tanques, a favor do vento. Mesmo lugar, mesmo salão, mesmas quatro frases, onze anos seguidos.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.leatherworker.more/1
    en  Boots for the old shepherd. Twelve years. Good leather outlasts the argument about good leather.
    >>  ............................................
    pt  Botas pro velho pastor. Doze anos. Couro bom sobrevive à discussão sobre couro bom.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.leatherworker.more/2
    en  The pits will move eventually. Eleven years of asking is not so long for a thing that heavy.
    >>  ............................................
    pt  Os tanques vão mudar uma hora. Onze anos pedindo não é tanto pra uma coisa tão pesada.
    >>  ............................................
  odd.dialogue.conversations.work.prof.leatherworker.more/1
    en  Boots for the old shepherd. Twelve years. Ash last, hand-stitched, and no varnish anywhere.
    >>  ............................................
    pt  Botas pro velho pastor. Doze anos. Forma de freixo, costura à mão, e nada de verniz.
    >>  ............................................
  odd.dialogue.conversations.work.prof.leatherworker.more/2
    en  The pits, moved downwind. Same spot, same hall, same four sentences, eleven years running.
    >>  ............................................
    pt  Os tanques, a favor do vento. Mesmo lugar, mesmo salão, mesmas quatro frases, onze anos seguidos.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.leatherworker.more/1
    en  Boots for the old shepherd. Twelve years. Good leather outlasts the argument about good leather.
    >>  ............................................
    pt  Botas pro velho pastor. Doze anos. Couro bom sobrevive à discussão sobre couro bom.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.leatherworker.more/2
    en  The pits will move eventually. Eleven years of asking is not so long for a thing that heavy.
    >>  ............................................
    pt  Os tanques vão mudar uma hora. Onze anos pedindo não é tanto pra uma coisa tão pesada.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.leatherworker.more/1
    en  Boots for the old shepherd! Twelve years and still on his feet. I mention it at every opportunity.
    >>  ............................................
    pt  Botas pro velho pastor! Doze anos e ainda nos pés dele. Menciono em toda oportunidade.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.leatherworker.more/2
    en  Move the pits downwind. Eleven years of asking. Eleven! I could recite my own speech.
    >>  ............................................
    pt  Mudar os tanques a favor do vento. Onze anos pedindo. Onze! Eu recitaria meu próprio discurso.
    >>  ............................................
  playful.dialogue.conversations.work.prof.leatherworker.more/1
    en  Boots for the old shepherd! Twelve years and still on his feet. I mention it at every opportunity.
    >>  ............................................
    pt  Botas pro velho pastor! Doze anos e ainda nos pés dele. Menciono em toda oportunidade.
    >>  ............................................
  playful.dialogue.conversations.work.prof.leatherworker.more/2
    en  Move the pits downwind. Eleven years of asking. Eleven! I could recite my own speech.
    >>  ............................................
    pt  Mudar os tanques a favor do vento. Onze anos pedindo. Onze! Eu recitaria meu próprio discurso.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.leatherworker.more/1
    en  Boots for the old shepherd. Twelve years. Good leather outlasts the argument about good leather.
    >>  ............................................
    pt  Botas pro velho pastor. Doze anos. Couro bom sobrevive à discussão sobre couro bom.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.leatherworker.more/2
    en  The pits will move eventually. Eleven years of asking is not so long for a thing that heavy.
    >>  ............................................
    pt  Os tanques vão mudar uma hora. Onze anos pedindo não é tanto pra uma coisa tão pesada.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.leatherworker.more/1
    en  Boots for the old shepherd. Twelve years on his feet, and he's the only one who ever said thank you.
    >>  ............................................
    pt  Botas pro velho pastor. Doze anos nos pés dele, e ele é o único que já agradeceu.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.leatherworker.more/2
    en  The pits are poison and everyone's children walk past them. That's why I keep asking.
    >>  ............................................
    pt  Os tanques são veneno e as crianças de todos passam por perto. É por isso que eu continuo pedindo.
    >>  ............................................
  shy.dialogue.conversations.work.prof.leatherworker.more/1
    en  Boots for the old shepherd. Twelve years. Ash last, hand-stitched, and no varnish anywhere.
    >>  ............................................
    pt  Botas pro velho pastor. Doze anos. Forma de freixo, costura à mão, e nada de verniz.
    >>  ............................................
  shy.dialogue.conversations.work.prof.leatherworker.more/2
    en  The pits, moved downwind. Same spot, same hall, same four sentences, eleven years running.
    >>  ............................................
    pt  Os tanques, a favor do vento. Mesmo lugar, mesmo salão, mesmas quatro frases, onze anos seguidos.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.leatherworker.more/1
    en  Boots for the old shepherd! Twelve years and still on his feet. I mention it at every opportunity.
    >>  ............................................
    pt  Botas pro velho pastor! Doze anos e ainda nos pés dele. Menciono em toda oportunidade.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.leatherworker.more/2
    en  Move the pits downwind. Eleven years of asking. Eleven! I could recite my own speech.
    >>  ............................................
    pt  Mudar os tanques a favor do vento. Onze anos pedindo. Onze! Eu recitaria meu próprio discurso.
    >>  ............................................
  witty.dialogue.conversations.work.prof.leatherworker.more/1
    en  Boots for the old shepherd! Twelve years and still on his feet. I mention it at every opportunity.
    >>  ............................................
    pt  Botas pro velho pastor! Doze anos e ainda nos pés dele. Menciono em toda oportunidade.
    >>  ............................................
  witty.dialogue.conversations.work.prof.leatherworker.more/2
    en  Move the pits downwind. Eleven years of asking. Eleven! I could recite my own speech.
    >>  ............................................
    pt  Mudar os tanques a favor do vento. Onze anos pedindo. Onze! Eu recitaria meu próprio discurso.
    >>  ............................................
```

</details>


### Button `leave` — "Soft leather."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.leatherworker.challenge.landed`, `work.leatherworker.challenge.stung`, `work.leatherworker.craft.admire`, `work.leatherworker.craft.ask_awl`, `work.leatherworker.craft.ask_nine`, `work.leatherworker.future.ask_eleven`, `work.leatherworker.future.ask_saddle_dream`, `work.leatherworker.future.encourage`, `work.leatherworker.hard`, `work.leatherworker.risk.ask_pits`, `work.leatherworker.risk.ask_saddle`, `work.leatherworker.risk.sympathise`, `work.leatherworker.task.ask_broader`, `work.leatherworker.task.ask_smell`, `work.leatherworker.task.offer_hands`, `work.leatherworker.value`, `work.leatherworker.village.ask_boots`, `work.leatherworker.village.ask_butcher`, `work.leatherworker.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.followup.leave   [13 chars]
    en  Soft leather.
    >>  ............................................
    pt  Couro macio.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.leave
WHO    VILLAGER — what the player reads after pressing "Soft leather."
       spoken on: conversations.topic.work.leatherworker.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.left`: the villager accepts. Subject `work.leatherworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.leatherworker.followup / leave; conversations.scene.work.leatherworker.old_repair.succeeded.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.blocked.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond / leave; conversations.scene.work.leatherworker.the_complaint.blocked.respond / leave; conversations.scene.work.leatherworker.the_complaint.succeeded.respond / leave; conversations.topic.work.leatherworker.craft.respond / leave; conversations.topic.work.leatherworker.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.leatherworker.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.leatherworker.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.leatherworker.future` — e.g. "I want the pits moved downwind and downstream. It's the same request I've made for eleven years."


```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.leatherworker.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.leatherworker.future.respond   [23 chars]
    en  That's what I'd change.
    >>  ............................................
    pt  É o que eu mudaria.
    >>  ............................................
```


### Button `ask_eleven` — "Eleven years of the same request?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.leatherworker.future` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.leatherworker.future.ask_eleven` — accepted phrasings: "eleven years of the same request"
  - the message must contain one of: `eleven`, `request`, `repeated`
  - scored words: `eleven`(1.5), `request`(1.2), `repeated`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.future.respond.ask_eleven
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.future.respond.ask_eleven   [33 chars]
    en  Eleven years of the same request?
    >>  ............................................
    pt  Onze anos do mesmo pedido?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.leatherworker.future.ask_eleven`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.leatherworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the best thing you've made?" | "Soft leather."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.future.ask_eleven
WHO    VILLAGER — what the player reads after pressing "Eleven years of the same request?"
       spoken on: conversations.topic.work.leatherworker.future.respond, button `ask_eleven`
       leaves the player on: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.future.ask_eleven`: the villager explains. Subject `work.leatherworker.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.leatherworker.future.ask_eleven/1   [82 chars]
    en  Eleven. I stand in the same spot in the same hall and say the same four sentences.
    >>  ............................................
    pt  Onze. Fico no mesmo lugar do mesmo salão e digo as mesmas quatro frases.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.future.ask_eleven/2   [68 chars]
    en  And every year they agree with me and then it is autumn again, %1$s.
    >>  ............................................
    pt  E todo ano concordam comigo e aí é outono de novo, %1$s.
    >>  ............................................
```


### Button `encourage` — "Take somebody with you next time."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.leatherworker.future` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.leatherworker.future.encourage` — accepted phrasings: "take somebody with you next time"
  - the message must contain one of: `somebody`, `together`
  - scored words: `somebody`(1.2), `together`(1.5), `next`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.future.respond.encourage   [33 chars]
    en  Take somebody with you next time.
    >>  ............................................
    pt  Leve alguém com você da próxima.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.leatherworker.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.leatherworker.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.leatherworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the best thing you've made?" | "Soft leather."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.future.encourage
WHO    VILLAGER — what the player reads after pressing "Take somebody with you next time."
       spoken on: conversations.topic.work.leatherworker.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.future.encourage`: the villager accepts. Subject `work.leatherworker.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.leatherworker.future.encourage/1   [86 chars]
    en  ...Somebody else in the room. That has genuinely never occurred to me in eleven years.
    >>  ............................................
    pt  ...Outra pessoa na sala. Isso genuinamente nunca me ocorreu em onze anos.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.future.encourage/2   [80 chars]
    en  The butcher would come. He'd have to walk in with me, which is rather the point.
    >>  ............................................
    pt  O açougueiro viria. Ele teria que entrar comigo, que é justamente a questão.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room. I've gone in alone eleven times and come out alone.
    >>  ............................................
    pt  ...Outra pessoa na sala. Entrei sozinho onze vezes e saí sozinho.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. Asking him is the part I'll have to work myself up to.
    >>  ............................................
    pt  O açougueiro viria. Pedir a ele é a parte pra qual vou ter que criar coragem.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room. Eleven years of walking in alone taught me nothing.
    >>  ............................................
    pt  ...Outra pessoa na sala. Onze anos entrando sozinho não me ensinaram nada.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. He's argued that room down before, and won.
    >>  ............................................
    pt  O açougueiro viria. Ele já venceu discussão naquela sala antes.
    >>  ............................................
  confident.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room. That has never once occurred to me in eleven years.
    >>  ............................................
    pt  ...Outra pessoa na sala. Isso nunca me ocorreu em onze anos.
    >>  ............................................
  confident.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. He'd have to walk in with me, which is rather the point.
    >>  ............................................
    pt  O açougueiro viria. Ele teria que entrar comigo, que é justamente o ponto.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room. That has never once occurred to me in eleven years.
    >>  ............................................
    pt  ...Outra pessoa na sala. Isso nunca me ocorreu em onze anos.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. He'd have to walk in with me, which is rather the point.
    >>  ............................................
    pt  O açougueiro viria. Ele teria que entrar comigo, que é justamente o ponto.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room, %1$s. Eleven years and I never thought of it.
    >>  ............................................
    pt  ...Outra pessoa na sala, %1$s. Onze anos e nunca pensei nisso.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. He'd have to walk in with me, which is rather the point.
    >>  ............................................
    pt  O açougueiro viria. Teria que entrar comigo, que é justamente o ponto.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room, %1$s. Eleven years and I never thought of it.
    >>  ............................................
    pt  ...Outra pessoa na sala, %1$s. Onze anos e nunca pensei nisso.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. He'd have to walk in with me, which is rather the point.
    >>  ............................................
    pt  O açougueiro viria. Teria que entrar comigo, que é justamente o ponto.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room, %1$s. Eleven years and I never thought of it.
    >>  ............................................
    pt  ...Outra pessoa na sala, %1$s. Onze anos e nunca pensei nisso.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. He'd have to walk in with me, which is rather the point.
    >>  ............................................
    pt  O açougueiro viria. Teria que entrar comigo, que é justamente o ponto.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room. I've gone in alone eleven times and come out alone.
    >>  ............................................
    pt  ...Outra pessoa na sala. Entrei sozinho onze vezes e saí sozinho.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. Asking him is the part I'll have to work myself up to.
    >>  ............................................
    pt  O açougueiro viria. Pedir a ele é a parte pra qual vou ter que criar coragem.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room. That has never once occurred to me in eleven years.
    >>  ............................................
    pt  ...Outra pessoa na sala. Isso nunca me ocorreu em onze anos.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. He'd have to walk in with me, which is rather the point.
    >>  ............................................
    pt  O açougueiro viria. Ele teria que entrar comigo, que é justamente o ponto.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room. That has never once occurred to me in eleven years.
    >>  ............................................
    pt  ...Outra pessoa na sala. Isso nunca me ocorreu em onze anos.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. He'd have to walk in with me, which is rather the point.
    >>  ............................................
    pt  O açougueiro viria. Ele teria que entrar comigo, que é justamente o ponto.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room. Eleven years and I never thought of it.
    >>  ............................................
    pt  ...Outra pessoa na sala. Onze anos e nunca pensei nisso.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. In with me.
    >>  ............................................
    pt  O açougueiro viria. Comigo.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room. Eleven years of walking in alone taught me nothing.
    >>  ............................................
    pt  ...Outra pessoa na sala. Onze anos entrando sozinho não me ensinaram nada.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. He's argued that room down before, and won.
    >>  ............................................
    pt  O açougueiro viria. Ele já venceu discussão naquela sala antes.
    >>  ............................................
  odd.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room. Eleven years and I never thought of it.
    >>  ............................................
    pt  ...Outra pessoa na sala. Onze anos e nunca pensei nisso.
    >>  ............................................
  odd.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. In with me.
    >>  ............................................
    pt  O açougueiro viria. Comigo.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room. Eleven years of walking in alone taught me nothing.
    >>  ............................................
    pt  ...Outra pessoa na sala. Onze anos entrando sozinho não me ensinaram nada.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. He's argued that room down before, and won.
    >>  ............................................
    pt  O açougueiro viria. Ele já venceu discussão naquela sala antes.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room! Eleven years and that has never once occurred to me.
    >>  ............................................
    pt  ...Outra pessoa na sala! Onze anos e isso nunca me ocorreu.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. He'd have to walk in with me, which is exactly the point.
    >>  ............................................
    pt  O açougueiro viria. Teria que entrar comigo, que é exatamente o ponto.
    >>  ............................................
  playful.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room! Eleven years and that has never once occurred to me.
    >>  ............................................
    pt  ...Outra pessoa na sala! Onze anos e isso nunca me ocorreu.
    >>  ............................................
  playful.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. He'd have to walk in with me, which is exactly the point.
    >>  ............................................
    pt  O açougueiro viria. Teria que entrar comigo, que é exatamente o ponto.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room. Eleven years of walking in alone taught me nothing.
    >>  ............................................
    pt  ...Outra pessoa na sala. Onze anos entrando sozinho não me ensinaram nada.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. He's argued that room down before, and won.
    >>  ............................................
    pt  O açougueiro viria. Ele já venceu discussão naquela sala antes.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room. I've gone in alone eleven times and come out alone.
    >>  ............................................
    pt  ...Outra pessoa na sala. Entrei sozinho onze vezes e saí sozinho.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. Asking him is the part I'll have to work myself up to.
    >>  ............................................
    pt  O açougueiro viria. Pedir a ele é a parte pra qual vou ter que criar coragem.
    >>  ............................................
  shy.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room. Eleven years and I never thought of it.
    >>  ............................................
    pt  ...Outra pessoa na sala. Onze anos e nunca pensei nisso.
    >>  ............................................
  shy.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. In with me.
    >>  ............................................
    pt  O açougueiro viria. Comigo.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room! Eleven years and that has never once occurred to me.
    >>  ............................................
    pt  ...Outra pessoa na sala! Onze anos e isso nunca me ocorreu.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. He'd have to walk in with me, which is exactly the point.
    >>  ............................................
    pt  O açougueiro viria. Teria que entrar comigo, que é exatamente o ponto.
    >>  ............................................
  witty.dialogue.conversations.work.prof.leatherworker.future.encourage/1
    en  ...Somebody else in the room! Eleven years and that has never once occurred to me.
    >>  ............................................
    pt  ...Outra pessoa na sala! Onze anos e isso nunca me ocorreu.
    >>  ............................................
  witty.dialogue.conversations.work.prof.leatherworker.future.encourage/2
    en  The butcher would come. He'd have to walk in with me, which is exactly the point.
    >>  ............................................
    pt  O açougueiro viria. Teria que entrar comigo, que é exatamente o ponto.
    >>  ............................................
```

</details>


### Button `ask_saddle_dream` — "What would the proper saddle be?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.leatherworker.future` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.leatherworker.future.ask_saddle_dream` — accepted phrasings: "what would the proper saddle be"
  - the message must contain one of: `saddle`, `proper`, `design`
  - scored words: `saddle`(1.5), `proper`(1.2), `design`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.future.respond.ask_saddle_dream
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.future.respond.ask_saddle_dream   [32 chars]
    en  What would the proper saddle be?
    >>  ............................................
    pt  Como seria a sela direito?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.leatherworker.future.ask_saddle_dream`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.leatherworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the best thing you've made?" | "Soft leather."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.future.ask_saddle_dream
WHO    VILLAGER — what the player reads after pressing "What would the proper saddle be?"
       spoken on: conversations.topic.work.leatherworker.future.respond, button `ask_saddle_dream`
       leaves the player on: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.future.ask_saddle_dream`: the villager explains. Subject `work.leatherworker.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.leatherworker.future.ask_saddle_dream/1   [89 chars]
    en  Tree of ash, four months seasoning, and every stitch by hand. It would outlive the horse.
    >>  ............................................
    pt  Armação de freixo, quatro meses curando, e cada ponto à mão. Sobreviveria ao cavalo.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.future.ask_saddle_dream/2   [82 chars]
    en  One that nobody has to check. That's the whole design, %1$s, and it takes a month.
    >>  ............................................
    pt  Uma que ninguém precise conferir. É todo o projeto, %1$s, e leva um mês.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the vat."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.leatherworker.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.future.respond.leave   [33 chars]
    en  I'll let you get back to the vat.
    >>  ............................................
    pt  Vou deixar você voltar ao tanque.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the vat."
       spoken on: conversations.topic.work.leatherworker.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.left`: the villager accepts. Subject `work.leatherworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.leatherworker.followup / leave; conversations.scene.work.leatherworker.old_repair.succeeded.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.blocked.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond / leave; conversations.scene.work.leatherworker.the_complaint.blocked.respond / leave; conversations.scene.work.leatherworker.the_complaint.succeeded.respond / leave; conversations.topic.work.leatherworker.craft.respond / leave; conversations.topic.work.leatherworker.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.leatherworker.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.leatherworker.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.leatherworker` — e.g. "Hides come in stiff and ornery, leave soft and useful. There's a sermon in that if you want one."


```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.leatherworker.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.leatherworker.respond   [43 chars]
    en  That's the tanning and everything after it.
    >>  ............................................
    pt  É o curtume e tudo que vem depois.
    >>  ............................................
```


### Button `ask_hard` — "Does the smell ever get to you?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.leatherworker.identity` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.leatherworker.hard` — accepted phrasings: "does the smell ever get to you"
  - the message must contain one of: `smell`, `stink`
  - scored words: `smell`(1.5), `stink`(1.5), `bother`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.respond.ask_hard   [31 chars]
    en  Does the smell ever get to you?
    >>  ............................................
    pt  O cheiro nunca te incomoda?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.leatherworker.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.leatherworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the best thing you've made?" | "Soft leather."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.hard
WHO    VILLAGER — what the player reads after pressing "Does the smell ever get to you?"
       spoken on: conversations.topic.work.leatherworker.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.hard`: the villager explains. Subject `work.leatherworker.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.leatherworker.followup / ask_more
```

> Written out in full under **`conversations.scene.work.leatherworker.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "Half of what people own passed through your hands."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.leatherworker.identity` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.leatherworker.value` — accepted phrasings: "half of what people own passed through your hands"
  - the message must contain one of: `boots`, `belts`
  - scored words: `boots`(1.5), `belts`(1.2), `own`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.respond.value   [50 chars]
    en  Half of what people own passed through your hands.
    >>  ............................................
    pt  Metade do que as pessoas têm passou pelas suas mãos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.leatherworker.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.leatherworker.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.leatherworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the best thing you've made?" | "Soft leather."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.value
WHO    VILLAGER — what the player reads after pressing "Half of what people own passed through your hands."
       spoken on: conversations.topic.work.leatherworker.respond, button `value`
       leaves the player on: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.value`: the villager accepts. Subject `work.leatherworker.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.leatherworker.value/1   [72 chars]
    en  Boots, belts, bindings, bellows. Aye. And a saddle I'm quietly proud of.
    >>  ............................................
    pt  Botas, cintos, encadernações, foles. É. E uma sela da qual eu tenho orgulho calado.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.value/2   [69 chars]
    en  It did, and not one of them thinks about where the leather came from.
    >>  ............................................
    pt  Passou, e nenhum deles pensa de onde veio o couro.
    >>  ............................................
```


### Button `challenge` — "It's a filthy job."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.leatherworker.identity` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.leatherworker.challenge` — accepted phrasings: "it's a filthy job"
  - the message must contain one of: `filthy`, `dirty`
  - scored words: `filthy`(1.5), `dirty`(1.5), `job`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.respond.challenge   [18 chars]
    en  It's a filthy job.
    >>  ............................................
    pt  É um trabalho imundo.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.leatherworker.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.leatherworker.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.leatherworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the best thing you've made?" | "Soft leather."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.challenge.landed
WHO    VILLAGER — what the player reads after pressing "It's a filthy job."
       spoken on: conversations.topic.work.leatherworker.respond, button `challenge`
       leaves the player on: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.challenge.landed`: the villager resists. Subject `work.leatherworker.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.leatherworker.challenge.landed/1   [76 chars]
    en  It is. So is birth and so is bread. Filthy and necessary keep close company.
    >>  ............................................
    pt  É. Nascimento e pão também são. Imundo e necessário andam juntos.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.challenge.landed/2   [59 chars]
    en  Filthy, yes. Also the reason your boots aren't paper, %1$s.
    >>  ............................................
    pt  Imundo, sim. Também o motivo das suas botas não serem papel, %1$s.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.leatherworker.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.leatherworker.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.leatherworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the best thing you've made?" | "Soft leather."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.challenge.stung
WHO    VILLAGER — what the player reads after pressing "It's a filthy job."
       spoken on: conversations.topic.work.leatherworker.respond, button `challenge`
       leaves the player on: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.challenge.stung`: the villager resists. Subject `work.leatherworker.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.leatherworker.challenge.stung/1   [59 chars]
    en  ...Then walk barefoot and we'll see how the argument holds.
    >>  ............................................
    pt  ...Então ande descalço e a gente vê como o argumento se sustenta.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.challenge.stung/2   [62 chars]
    en  Filthy. Right. It's a fine thing to say to a man's face, %1$s.
    >>  ............................................
    pt  Imundo. Certo. É uma bela coisa de se dizer na cara de alguém, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the vat."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.leatherworker.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.respond.leave   [33 chars]
    en  I'll let you get back to the vat.
    >>  ............................................
    pt  Vou deixar você voltar ao tanque.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the vat."
       spoken on: conversations.topic.work.leatherworker.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.left`: the villager accepts. Subject `work.leatherworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.leatherworker.followup / leave; conversations.scene.work.leatherworker.old_repair.succeeded.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.blocked.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond / leave; conversations.scene.work.leatherworker.the_complaint.blocked.respond / leave; conversations.scene.work.leatherworker.the_complaint.succeeded.respond / leave; conversations.topic.work.leatherworker.craft.respond / leave; conversations.topic.work.leatherworker.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.leatherworker.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.leatherworker.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.leatherworker.risk` — e.g. "The pits are poison and everyone's boots walk past them. I've a fence and I check it daily."


```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.leatherworker.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.leatherworker.risk.respond   [24 chars]
    en  That's the danger of it.
    >>  ............................................
    pt  É esse o perigo.
    >>  ............................................
```


### Button `ask_pits` — "Has anyone ever gone in?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.leatherworker.risk` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.leatherworker.risk.ask_pits` — accepted phrasings: "has anyone ever gone in"
  - the message must contain one of: `pits`, `fallen`, `poison`
  - scored words: `pits`(1.5), `fallen`(1.2), `poison`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.risk.respond.ask_pits
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.risk.respond.ask_pits   [24 chars]
    en  Has anyone ever gone in?
    >>  ............................................
    pt  Alguém já caiu dentro?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.leatherworker.risk.ask_pits`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.leatherworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the best thing you've made?" | "Soft leather."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.risk.ask_pits
WHO    VILLAGER — what the player reads after pressing "Has anyone ever gone in?"
       spoken on: conversations.topic.work.leatherworker.risk.respond, button `ask_pits`
       leaves the player on: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.risk.ask_pits`: the villager explains. Subject `work.leatherworker.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.leatherworker.risk.ask_pits/1   [87 chars]
    en  A dog, once. That was enough for me to rebuild the fence twice as high, at my own cost.
    >>  ............................................
    pt  Um cachorro, uma vez. Bastou pra eu refazer a cerca duas vezes mais alta, do meu bolso.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.risk.ask_pits/2   [75 chars]
    en  No, and I intend to keep that sentence true for another twenty years, %1$s.
    >>  ............................................
    pt  Não, e eu pretendo manter essa frase verdadeira por mais vinte anos, %1$s.
    >>  ............................................
```


### Button `sympathise` — "Two saddles a year and you lose sleep over both."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.leatherworker.risk` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.leatherworker.risk.sympathise` — accepted phrasings: "two saddles a year and you lose sleep over both"
  - the message must contain one of: `saddles`, `sleep`
  - scored words: `saddles`(1.5), `sleep`(1.2), `both`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.risk.respond.sympathise   [48 chars]
    en  Two saddles a year and you lose sleep over both.
    >>  ............................................
    pt  Duas selas por ano e você perde sono com as duas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.leatherworker.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.leatherworker.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.leatherworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the best thing you've made?" | "Soft leather."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "Two saddles a year and you lose sleep over both."
       spoken on: conversations.topic.work.leatherworker.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.risk.sympathise`: the villager accepts. Subject `work.leatherworker.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.leatherworker.risk.sympathise/1   [80 chars]
    en  ...I do. Nobody else counts them and somebody ought to, so it may as well be me.
    >>  ............................................
    pt  ...Perco. Mais ninguém conta e alguém deveria, então que seja eu.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.risk.sympathise/2   [88 chars]
    en  Both. Every year. And I check the girth stitching four times, %1$s, which helps nothing.
    >>  ............................................
    pt  As duas. Todo ano. E eu confiro a costura da cilha quatro vezes, %1$s, o que não ajuda nada.
    >>  ............................................
```


### Button `ask_saddle` — "What makes a saddle fail?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.leatherworker.risk` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.leatherworker.risk.ask_saddle` — accepted phrasings: "what makes a saddle fail"
  - the message must contain one of: `saddle`, `fail`, `girth`
  - scored words: `saddle`(1.5), `fail`(1.2), `girth`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.risk.respond.ask_saddle
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.risk.respond.ask_saddle   [25 chars]
    en  What makes a saddle fail?
    >>  ............................................
    pt  O que faz uma sela falhar?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.leatherworker.risk.ask_saddle`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.leatherworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the best thing you've made?" | "Soft leather."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.risk.ask_saddle
WHO    VILLAGER — what the player reads after pressing "What makes a saddle fail?"
       spoken on: conversations.topic.work.leatherworker.risk.respond, button `ask_saddle`
       leaves the player on: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.risk.ask_saddle`: the villager explains. Subject `work.leatherworker.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.leatherworker.risk.ask_saddle/1   [82 chars]
    en  Rot in the tree, under the leather, where nobody looks. That's the one that kills.
    >>  ............................................
    pt  Podridão na armação, sob o couro, onde ninguém olha. É essa que mata.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.risk.ask_saddle/2   [85 chars]
    en  The girth, always the girth. And the girth is the part the rider stares at every day.
    >>  ............................................
    pt  A cilha, sempre a cilha. E a cilha é a parte que o cavaleiro encara todo dia.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the vat."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.leatherworker.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.risk.respond.leave   [33 chars]
    en  I'll let you get back to the vat.
    >>  ............................................
    pt  Vou deixar você voltar ao tanque.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the vat."
       spoken on: conversations.topic.work.leatherworker.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.left`: the villager accepts. Subject `work.leatherworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.leatherworker.followup / leave; conversations.scene.work.leatherworker.old_repair.succeeded.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.blocked.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond / leave; conversations.scene.work.leatherworker.the_complaint.blocked.respond / leave; conversations.scene.work.leatherworker.the_complaint.succeeded.respond / leave; conversations.topic.work.leatherworker.craft.respond / leave; conversations.topic.work.leatherworker.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.leatherworker.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.leatherworker.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.leatherworker.task` — e.g. "Turning hides in the pits. It smells like a decision I made twenty years ago and can't unmake."


```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.leatherworker.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.leatherworker.task.respond   [22 chars]
    en  That's the pits today.
    >>  ............................................
    pt  São os tanques hoje.
    >>  ............................................
```


### Button `ask_smell` — "Does the smell ever leave?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.leatherworker.task` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.leatherworker.task.ask_smell` — accepted phrasings: "does the smell ever leave"
  - the message must contain one of: `smell`, `leave`, `pits`
  - scored words: `smell`(1.5), `leave`(1.0), `pits`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.task.respond.ask_smell
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.task.respond.ask_smell   [26 chars]
    en  Does the smell ever leave?
    >>  ............................................
    pt  O cheiro vai embora algum dia?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.leatherworker.task.ask_smell`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.leatherworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the best thing you've made?" | "Soft leather."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.task.ask_smell
WHO    VILLAGER — what the player reads after pressing "Does the smell ever leave?"
       spoken on: conversations.topic.work.leatherworker.task.respond, button `ask_smell`
       leaves the player on: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.task.ask_smell`: the villager explains. Subject `work.leatherworker.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.leatherworker.task.ask_smell/1   [82 chars]
    en  Not from me. My daughter says she can find me in the dark, which she means kindly.
    >>  ............................................
    pt  De mim, não. Minha filha diz que me acha no escuro, e ela diz com carinho.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.task.ask_smell/2   [74 chars]
    en  It left my nose in the first year. It has never left anybody else's, %1$s.
    >>  ............................................
    pt  Saiu do meu nariz no primeiro ano. Nunca saiu do de mais ninguém, %1$s.
    >>  ............................................
```


### Button `offer_hands` — "I could turn hides."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.leatherworker.task` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.leatherworker.task.offer_hands` — accepted phrasings: "i could turn hides"
  - the message must contain one of: `hides`, `turn`
  - scored words: `hides`(1.5), `turn`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.task.respond.offer_hands   [19 chars]
    en  I could turn hides.
    >>  ............................................
    pt  Eu podia virar os couros.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.leatherworker.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.leatherworker.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.leatherworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the best thing you've made?" | "Soft leather."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I could turn hides."
       spoken on: conversations.topic.work.leatherworker.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.task.offer_hands`: the villager accepts. Subject `work.leatherworker.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.leatherworker.task.offer_hands/1   [80 chars]
    en  ...You could, and you'll wear those clothes for a week afterwards. Fair warning.
    >>  ............................................
    pt  ...Podia, e vai usar essa roupa por uma semana depois. Aviso dado.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.task.offer_hands/2   [88 chars]
    en  Every hide, corner to corner, and count them. If the count is wrong we both start again.
    >>  ............................................
    pt  Cada couro, canto a canto, e conte. Se a conta der errado a gente recomeça os dois.
    >>  ............................................
```


### Button `ask_broader` — "Why not tell him he's broader?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.leatherworker.task` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.leatherworker.task.ask_broader` — accepted phrasings: "why not tell him he's broader"
  - the message must contain one of: `broader`, `measure`, `fitting`
  - scored words: `broader`(1.5), `measure`(1.2), `fitting`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.task.respond.ask_broader
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.task.respond.ask_broader   [30 chars]
    en  Why not tell him he's broader?
    >>  ............................................
    pt  Por que não dizer que ele é mais largo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.leatherworker.task.ask_broader`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.leatherworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the best thing you've made?" | "Soft leather."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.task.ask_broader
WHO    VILLAGER — what the player reads after pressing "Why not tell him he's broader?"
       spoken on: conversations.topic.work.leatherworker.task.respond, button `ask_broader`
       leaves the player on: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.task.ask_broader`: the villager explains. Subject `work.leatherworker.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.leatherworker.task.ask_broader/1   [90 chars]
    en  Because he'd hold his breath through the fitting and I'd cut it to a man who wasn't there.
    >>  ............................................
    pt  Porque ele prenderia a respiração no ajuste e eu cortaria pra um homem que não existe.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.task.ask_broader/2   [77 chars]
    en  Because he'd take it as an insult and I'd take a week to make it right, %1$s.
    >>  ............................................
    pt  Porque ele tomaria como insulto e eu levaria uma semana pra consertar, %1$s.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the vat."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.leatherworker.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.task.respond.leave   [33 chars]
    en  I'll let you get back to the vat.
    >>  ............................................
    pt  Vou deixar você voltar ao tanque.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the vat."
       spoken on: conversations.topic.work.leatherworker.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.left`: the villager accepts. Subject `work.leatherworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.leatherworker.followup / leave; conversations.scene.work.leatherworker.old_repair.succeeded.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.blocked.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond / leave; conversations.scene.work.leatherworker.the_complaint.blocked.respond / leave; conversations.scene.work.leatherworker.the_complaint.succeeded.respond / leave; conversations.topic.work.leatherworker.craft.respond / leave; conversations.topic.work.leatherworker.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.leatherworker.followup` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.leatherworker.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.leatherworker.village` — e.g. "Boots. That's the whole of my case. Everyone in this place walks on something I made."


```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.leatherworker.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.leatherworker.village.respond   [21 chars]
    en  That's my part of it.
    >>  ............................................
    pt  É a minha parte.
    >>  ............................................
```


### Button `ask_boots` — "How many pairs a year?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.leatherworker.village` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.leatherworker.village.ask_boots` — accepted phrasings: "how many pairs a year"
  - the message must contain one of: `pairs`, `boots`
  - scored words: `pairs`(1.5), `boots`(1.0), `many`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.village.respond.ask_boots
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.village.respond.ask_boots   [22 chars]
    en  How many pairs a year?
    >>  ............................................
    pt  Quantos pares por ano?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.leatherworker.village.ask_boots`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.leatherworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the best thing you've made?" | "Soft leather."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.village.ask_boots
WHO    VILLAGER — what the player reads after pressing "How many pairs a year?"
       spoken on: conversations.topic.work.leatherworker.village.respond, button `ask_boots`
       leaves the player on: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.village.ask_boots`: the villager explains. Subject `work.leatherworker.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.leatherworker.village.ask_boots/1   [87 chars]
    en  Thirty new, ninety mended. The mending is the trade; the new pairs are the showing off.
    >>  ............................................
    pt  Trinta novos, noventa consertados. O conserto é o ofício; os novos são exibição.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.village.ask_boots/2   [70 chars]
    en  Enough that I know every foot in this place by its wear pattern, %1$s.
    >>  ............................................
    pt  O bastante pra eu conhecer cada pé daqui pelo padrão de desgaste, %1$s.
    >>  ............................................
```


### Button `say_thanks` — "Everyone walking on something you made is not nothing."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.leatherworker.village` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.leatherworker.village.say_thanks` — accepted phrasings: "everyone walking on something you made is not nothing"
  - the message must contain one of: `walking`
  - scored words: `walking`(1.5), `made`(0.8), `everyone`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.village.respond.say_thanks   [54 chars]
    en  Everyone walking on something you made is not nothing.
    >>  ............................................
    pt  Todo mundo pisando em algo que você fez não é pouca coisa.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.leatherworker.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.leatherworker.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.leatherworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the best thing you've made?" | "Soft leather."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Everyone walking on something you made is not nothing."
       spoken on: conversations.topic.work.leatherworker.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.village.say_thanks`: the villager accepts. Subject `work.leatherworker.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.leatherworker.village.say_thanks/1   [67 chars]
    en  ...No. It's the argument I make to myself on the days the pits win.
    >>  ............................................
    pt  ...Não é. É o argumento que eu faço comigo nos dias em que os tanques ganham.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.village.say_thanks/2   [77 chars]
    en  It's the one thing about the smell I can put on the other side of the ledger.
    >>  ............................................
    pt  É a única coisa que eu ponho do outro lado da balança contra o cheiro.
    >>  ............................................
```


### Button `ask_butcher` — "Does the butcher see it that way?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.leatherworker.village` · offered only once the villager has actually said `work:leatherworker`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.leatherworker.village.ask_butcher` — accepted phrasings: "does the butcher see it that way"
  - the message must contain one of: `butcher`, `hides`
  - scored words: `butcher`(1.5), `hides`(1.0), `see`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.village.respond.ask_butcher
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.village.respond.ask_butcher   [33 chars]
    en  Does the butcher see it that way?
    >>  ............................................
    pt  O açougueiro vê assim?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.leatherworker.village.ask_butcher`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.leatherworker.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "What's the best thing you've made?" | "Soft leather."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.village.ask_butcher
WHO    VILLAGER — what the player reads after pressing "Does the butcher see it that way?"
       spoken on: conversations.topic.work.leatherworker.village.respond, button `ask_butcher`
       leaves the player on: conversations.topic.work.leatherworker.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.village.ask_butcher`: the villager explains. Subject `work.leatherworker.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:leatherworker` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.leatherworker.village.ask_butcher/1   [103 chars]
    en  He brings the hides at dawn so nobody watches him come to my end of the lane. Draw your own conclusion.
    >>  ............................................
    pt  Ele traz os couros ao amanhecer pra ninguém ver ele vindo ao meu lado da viela. Tire sua conclusão.
    >>  ............................................
  dialogue.conversations.work.prof.leatherworker.village.ask_butcher/2   [70 chars]
    en  He does, and he says so, and he says it where nobody else can hear it.
    >>  ............................................
    pt  Ele vê, e ele diz, e diz onde mais ninguém escuta.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the vat."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.leatherworker.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.leatherworker.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.leatherworker.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.leatherworker.village.respond.leave   [33 chars]
    en  I'll let you get back to the vat.
    >>  ............................................
    pt  Vou deixar você voltar ao tanque.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.leatherworker.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the vat."
       spoken on: conversations.topic.work.leatherworker.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.leatherworker.left`: the villager accepts. Subject `work.leatherworker.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.leatherworker.followup / leave; conversations.scene.work.leatherworker.old_repair.succeeded.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.blocked.respond / leave; conversations.scene.work.leatherworker.stubborn_hide.succeeded.respond / leave; conversations.scene.work.leatherworker.the_complaint.blocked.respond / leave; conversations.scene.work.leatherworker.the_complaint.succeeded.respond / leave; conversations.topic.work.leatherworker.craft.respond / leave; conversations.topic.work.leatherworker.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.leatherworker.followup` / button `leave`** earlier in this file. Fill it in there, once.

---

