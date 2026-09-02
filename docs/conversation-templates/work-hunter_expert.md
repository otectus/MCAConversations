# Work talk with a hunter expert

> Fill in each `NEW en_us` / `NEW pt_br` line. Leave one blank to keep the current wording.
> Read [README.md](README.md) once first — it carries the rules the build enforces.



## Nodes in this file

- [`conversations.scene.work.hunter_expert.failing_lantern.blocked.respond`](#conversations-scene-work-hunter-expert-failing-lantern-blocked-respond)
- [`conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond`](#conversations-scene-work-hunter-expert-failing-lantern-succeeded-respond)
- [`conversations.scene.work.hunter_expert.false_alarm.blocked.respond`](#conversations-scene-work-hunter-expert-false-alarm-blocked-respond)
- [`conversations.scene.work.hunter_expert.false_alarm.succeeded.respond`](#conversations-scene-work-hunter-expert-false-alarm-succeeded-respond)
- [`conversations.scene.work.hunter_expert.followup`](#conversations-scene-work-hunter-expert-followup)
- [`conversations.scene.work.hunter_expert.the_real_night.succeeded.respond`](#conversations-scene-work-hunter-expert-the-real-night-succeeded-respond)
- [`conversations.topic.work.hunter_expert.craft.respond`](#conversations-topic-work-hunter-expert-craft-respond)
- [`conversations.topic.work.hunter_expert.followup`](#conversations-topic-work-hunter-expert-followup)
- [`conversations.topic.work.hunter_expert.future.respond`](#conversations-topic-work-hunter-expert-future-respond)
- [`conversations.topic.work.hunter_expert.respond`](#conversations-topic-work-hunter-expert-respond)
- [`conversations.topic.work.hunter_expert.risk.respond`](#conversations-topic-work-hunter-expert-risk-respond)
- [`conversations.topic.work.hunter_expert.task.respond`](#conversations-topic-work-hunter-expert-task-respond)
- [`conversations.topic.work.hunter_expert.village.respond`](#conversations-topic-work-hunter-expert-village-respond)

---

## `conversations.scene.work.hunter_expert.failing_lantern.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.hunter_expert.failing_lantern.blocked` — e.g. "I have %2$s and I am still walking the round with it, which is exactly the sort of thing I would tell somebody else off for."


```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.failing_lantern.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.hunter_expert.failing_lantern.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.blocked.respond   [8 chars]
    en  The kit.
    >>  ............................................
    pt  O equipamento.
    >>  ............................................
```


### Button `offer_lantern` — "I'll bring you a lantern for the round."

*stance family `practical_help` · tone `gentle` · outcome `accepted` · answers the beat(s) `work.hunter_expert.failing_lantern.blocked` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter_expert.failing_lantern.blocked.offer_lantern` — accepted phrasings: "ill bring you a lantern for the round"; "i can bring you a lantern"; "let me fetch a lantern for the round"
  - the message must contain one of: `lantern`
  - scored words: `lantern`(1.8), `ill`(0.8), `bring`(0.8), `let`(0.8), `fetch`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.failing_lantern.blocked.respond.offer_lantern
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter_expert.failing_lantern.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.blocked.respond.offer_lantern   [39 chars]
    en  I'll bring you a lantern for the round.
    >>  ............................................
    pt  Vou trazer uma lanterna para a ronda.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.hunter_expert.kit.offer`, budget `standard`, replay policy `once`
- Does: disposition — trust +4, warmth +2  _(recorded under topic `work.hunter_expert.the_kit`)_
- Does: session `turn`
- Does: `conversations_episode` = {"op": "advance", "kind": "work.failing_lantern", "state": "active"}
- Does: `conversations_thread` = {"op": "open", "template": "work.hunter_expert.failing_lantern", "obligation": "commitment:work.hunter_expert.bring_lantern"}
- Does: `conversations_commitment` = {"op": "make", "id": "work.hunter_expert.bring_lantern"}
- Then opens: `conversations.scene.work.hunter_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a night patrol?" | "I'll leave you to the watch."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.failing_lantern.blocked.accepted
WHO    VILLAGER — what the player reads after pressing "I'll bring you a lantern for the round."
       spoken on: conversations.scene.work.hunter_expert.failing_lantern.blocked.respond, button `offer_lantern`
       leaves the player on: conversations.scene.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.failing_lantern.blocked.accepted`: the villager accepts. Subject `work.hunter_expert.the_kit`, polarity `positive`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.blocked.accepted/1   [107 chars]
    en  Then the round is a round again rather than a hope, and I will sleep the day before it instead of worrying.
    >>  ............................................
    pt  Então a ronda volta a ser ronda em vez de esperança, e eu vou dormir no dia anterior em vez de me preocupar.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.blocked.accepted/2   [121 chars]
    en  Take it as a loan and let me pay you back over the winter, because if it is a gift I will feel it every night I carry it.
    >>  ............................................
    pt  Aceite como empréstimo e me deixe pagar durante o inverno, porque se for presente eu vou sentir isso toda noite que carregar.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.blocked.accepted/3   [106 chars]
    en  Yes. And I will show you the round, if you want. Everybody has opinions about it and nobody has walked it.
    >>  ............................................
    pt  Sim. E eu te mostro a ronda, se quiser. Todo mundo tem opinião sobre ela e ninguém a percorreu.
    >>  ............................................
```


### Button `ask_about_the_round` — "What does the round involve?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter_expert.failing_lantern.blocked` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter_expert.failing_lantern.blocked.ask_about_the_round` — accepted phrasings: "what does the round involve"; "what does the round involve"; "what do you actually do on the round"
  - the message must contain one of: `round`, `involve`
  - scored words: `round`(1.8), `involve`(1.8), `does`(0.8), `actually`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.failing_lantern.blocked.respond.ask_about_the_round
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter_expert.failing_lantern.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.blocked.respond.ask_about_the_round   [28 chars]
    en  What does the round involve?
    >>  ............................................
    pt  O que a ronda envolve?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter_expert.the_kit`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.hunter_expert.failing_lantern"}
- Then opens: `conversations.scene.work.hunter_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a night patrol?" | "I'll leave you to the watch."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.failing_lantern.blocked.explained
WHO    VILLAGER — what the player reads after pressing "What does the round involve?"
       spoken on: conversations.scene.work.hunter_expert.failing_lantern.blocked.respond, button `ask_about_the_round`
       leaves the player on: conversations.scene.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.failing_lantern.blocked.explained`: the villager explains. Subject `work.hunter_expert.the_kit`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.blocked.explained/1   [143 chars]
    en  Eleven doors, two barns and a stretch of hedge, twice a night, and the point of it is that the village sleeps rather than that I find anything.
    >>  ............................................
    pt  Onze portas, dois celeiros e um trecho de cerca viva, duas vezes por noite, e o objetivo é que a vila durma, não que eu ache alguma coisa.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.blocked.explained/2   [137 chars]
    en  Mostly I check that latches are latched and lamps are lit. It is dull and it is preventative and dull preventative work is the whole job.
    >>  ............................................
    pt  Basicamente eu confiro se as trancas estão trancadas e as lamparinas acesas. É monótono, é preventivo, e trabalho preventivo monótono é o ofício inteiro.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.blocked.explained/3   [126 chars]
    en  I talk to whoever is awake. Half my useful information comes from a woman with a bad hip who cannot sleep and sees everything.
    >>  ............................................
    pt  Converso com quem estiver acordado. Metade da minha informação útil vem de uma mulher com o quadril ruim que não dorme e vê tudo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the watch."

*stance family `exit` · tone `plain` · answers the beat(s) `work.hunter_expert.failing_lantern.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.failing_lantern.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter_expert.failing_lantern.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.blocked.respond.leave   [35 chars]
    en  I'll let you get back to the watch.
    >>  ............................................
    pt  Vou deixar você voltar à ronda.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the watch."
       spoken on: conversations.scene.work.hunter_expert.failing_lantern.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.left`: the villager accepts. Subject `work.hunter_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond / leave; conversations.scene.work.hunter_expert.false_alarm.blocked.respond / leave; conversations.scene.work.hunter_expert.false_alarm.succeeded.respond / leave; conversations.scene.work.hunter_expert.followup / leave; conversations.scene.work.hunter_expert.the_real_night.succeeded.respond / leave; conversations.topic.work.hunter_expert.craft.respond / leave; conversations.topic.work.hunter_expert.followup / leave; conversations.topic.work.hunter_expert.future.respond / leave …and 4 more
```

```text
  dialogue.conversations.work.prof.hunter_expert.leave/1   [35 chars]
    en  Aye. The garlic won't count itself.
    >>  ............................................
    pt  É. O alho não se conta sozinho.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.leave/2   [37 chars]
    en  Off you go, %1$s. Be indoors by dark.
    >>  ............................................
    pt  Pode ir, %1$s. Esteja em casa antes de escurecer.
    >>  ............................................
```

---


## `conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.hunter_expert.failing_lantern.succeeded` — e.g. "The round is done properly again. I had forgotten how much of the worry was about the equipment rather than the dark."


```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond   [15 chars]
    en  The kit, since.
    >>  ............................................
    pt  O equipamento, depois disso.
    >>  ............................................
```


### Button `note_the_others` — "Others followed once you said it."

*stance family `encouragement` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.hunter_expert.failing_lantern.succeeded` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter_expert.failing_lantern.succeeded.note_the_others` — accepted phrasings: "others followed once you said it"; "others followed once you said it"; "the rest of the watch followed you"
  - the message must contain one of: `followed`, `watch`, `others`
  - scored words: `followed`(1.8), `watch`(1.8), `others`(1.8), `once`(0.8), `said`(0.8), `rest`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond.note_the_others
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond.note_the_others   [33 chars]
    en  Others followed once you said it.
    >>  ............................................
    pt  Outros seguiram depois que você falou.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — respect +3, warmth +2  _(recorded under topic `work.hunter_expert.the_kit`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.hunter_expert.failing_lantern"}
- Then opens: `conversations.scene.work.hunter_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a night patrol?" | "I'll leave you to the watch."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.failing_lantern.succeeded.acknowledged
WHO    VILLAGER — what the player reads after pressing "Others followed once you said it."
       spoken on: conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond, button `note_the_others`
       leaves the player on: conversations.scene.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.failing_lantern.succeeded.acknowledged`: the villager accepts. Subject `work.hunter_expert.the_kit`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.succeeded.acknowledged/1   [128 chars]
    en  Everybody was waiting for somebody to admit it first, which is how a watch ends up walking around in the dark with broken lamps.
    >>  ............................................
    pt  Todo mundo esperava alguém admitir primeiro, que é como uma ronda acaba andando no escuro com lamparinas quebradas.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.succeeded.acknowledged/2   [134 chars]
    en  Thank you. I said it out loud because you had already made it a normal thing to say, and normal is most of what makes a thing sayable.
    >>  ............................................
    pt  Obrigada. Eu disse em voz alta porque você já tinha tornado aquilo uma coisa normal de se dizer, e normal é quase tudo o que torna algo dizível.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.succeeded.acknowledged/3   [115 chars]
    en  We check each other's kit at the start of the night now. It takes four minutes and nobody has argued about it once.
    >>  ............................................
    pt  Agora a gente confere o equipamento um do outro no começo da noite. Leva quatro minutos e ninguém reclamou uma vez sequer.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the watch."

*stance family `exit` · tone `plain` · answers the beat(s) `work.hunter_expert.failing_lantern.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the watch.
    >>  ............................................
    pt  Vou deixar você voltar à ronda.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the watch."
       spoken on: conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.left`: the villager accepts. Subject `work.hunter_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter_expert.failing_lantern.blocked.respond / leave; conversations.scene.work.hunter_expert.false_alarm.blocked.respond / leave; conversations.scene.work.hunter_expert.false_alarm.succeeded.respond / leave; conversations.scene.work.hunter_expert.followup / leave; conversations.scene.work.hunter_expert.the_real_night.succeeded.respond / leave; conversations.topic.work.hunter_expert.craft.respond / leave; conversations.topic.work.hunter_expert.followup / leave; conversations.topic.work.hunter_expert.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.hunter_expert.failing_lantern.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.hunter_expert.false_alarm.blocked.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.hunter_expert.false_alarm.blocked` — e.g. "Half the lane has decided about %2$s on the strength of %3$s, and I am the only person expected to say otherwise."


```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.hunter_expert.false_alarm.blocked.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.respond   [23 chars]
    en  The village, this week.
    >>  ............................................
    pt  A vila, esta semana.
    >>  ............................................
```


### Button `ask_how_she_tells` — "How do you tell the difference?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter_expert.false_alarm.blocked` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter_expert.false_alarm.blocked.ask_how_she_tells` — accepted phrasings: "how do you tell the difference"; "how do you tell the difference"; "what separates a real case from a scare"
  - the message must contain one of: `difference`, `separates`, `scare`
  - scored words: `difference`(1.8), `separates`(1.8), `scare`(1.8), `tell`(0.8), `real`(0.8), `case`(0.8), `from`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.respond.ask_how_she_tells
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter_expert.false_alarm.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.respond.ask_how_she_tells   [31 chars]
    en  How do you tell the difference?
    >>  ............................................
    pt  Como você distingue?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, respect +1  _(recorded under topic `work.hunter_expert.false_alarms`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.hunter_expert.false_alarm"}
- Then opens: `conversations.scene.work.hunter_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a night patrol?" | "I'll leave you to the watch."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.explained
WHO    VILLAGER — what the player reads after pressing "How do you tell the difference?"
       spoken on: conversations.scene.work.hunter_expert.false_alarm.blocked.respond, button `ask_how_she_tells`
       leaves the player on: conversations.scene.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.false_alarm.blocked.explained`: the villager explains. Subject `work.hunter_expert.false_alarms`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.explained/1   [134 chars]
    en  Evidence that would still be evidence if I disliked the person less. That is the only test I have and it throws out nine cases in ten.
    >>  ............................................
    pt  Prova que continuaria sendo prova se eu gostasse mais da pessoa. É o único teste que eu tenho e ele descarta nove casos em dez.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.explained/2   [135 chars]
    en  %2$s on its own is nothing. It becomes something when three separate things line up and none of them is about somebody being unpopular.
    >>  ............................................
    pt  %2$s sozinho não é nada. Vira alguma coisa quando três coisas separadas se alinham e nenhuma delas é sobre alguém ser impopular.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.explained/3   [132 chars]
    en  I ask who benefits from the accusation. Four times out of five somebody does, and it is never the person making it who notices that.
    >>  ............................................
    pt  Pergunto quem se beneficia da acusação. Em quatro de cinco vezes alguém se beneficia, e nunca é quem acusa que percebe isso.
    >>  ............................................
```


### Button `back_the_refusal` — "Keep refusing until there's evidence."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.hunter_expert.false_alarm.blocked` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter_expert.false_alarm.blocked.back_the_refusal` — accepted phrasings: "keep refusing until theres evidence"; "keep refusing until there is evidence"; "hold out for actual evidence"
  - the message must contain one of: `refusing`, `evidence`, `hold`
  - scored words: `refusing`(1.8), `evidence`(1.8), `hold`(1.8), `keep`(0.8), `until`(0.8), `theres`(0.8), `out`(0.8), `actual`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.respond.back_the_refusal
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter_expert.false_alarm.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.respond.back_the_refusal   [37 chars]
    en  Keep refusing until there's evidence.
    >>  ............................................
    pt  Continue recusando até haver prova.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +3** — decision id `work.hunter_expert.alarm.backed`, budget `deep`, replay policy `once`
- Does: disposition — respect +4, trust +2  _(recorded under topic `work.hunter_expert.false_alarms`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.hunter_expert.false_alarm"}
- Then opens: `conversations.scene.work.hunter_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a night patrol?" | "I'll leave you to the watch."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.steadied
WHO    VILLAGER — what the player reads after pressing "Keep refusing until there's evidence."
       spoken on: conversations.scene.work.hunter_expert.false_alarm.blocked.respond, button `back_the_refusal`
       leaves the player on: conversations.scene.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.false_alarm.blocked.steadied`: the villager accepts. Subject `work.hunter_expert.false_alarms`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.steadied/1   [115 chars]
    en  I will, and it will cost me the lane's goodwill for a season, and that is a cheaper thing than a family driven out.
    >>  ............................................
    pt  Vou, e isso vai me custar a boa vontade da viela por uma estação, e é coisa mais barata que uma família expulsa.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.steadied/2   [140 chars]
    en  Yes. The moment I go and look, the looking becomes the evidence. That is the part nobody understands and it is the whole reason I stay home.
    >>  ............................................
    pt  Sim. No instante em que eu for olhar, o olhar vira a prova. É a parte que ninguém entende e é o motivo inteiro de eu ficar em casa.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.steadied/3   [96 chars]
    en  Thank you. It is a very lonely no and it helps enormously to hear somebody else say it out loud.
    >>  ............................................
    pt  Obrigada. É um não muito solitário e ajuda enormemente ouvir outra pessoa dizer em voz alta.
    >>  ............................................
```


### Button `worry_about_her` — "That puts you against the whole lane."

*stance family `empathy` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.hunter_expert.false_alarm.blocked` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter_expert.false_alarm.blocked.worry_about_her` — accepted phrasings: "that puts you against the whole lane"; "that puts you against the whole lane"; "you are standing against everybody there"
  - the message must contain one of: `lane`, `against`, `standing`
  - scored words: `lane`(1.8), `against`(1.8), `standing`(1.8), `puts`(0.8), `whole`(0.8), `everybody`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.respond.worry_about_her
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter_expert.false_alarm.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.respond.worry_about_her   [37 chars]
    en  That puts you against the whole lane.
    >>  ............................................
    pt  Isso te coloca contra a viela inteira.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — warmth +3, trust +1  _(recorded under topic `work.hunter_expert.false_alarms`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "open", "template": "work.hunter_expert.false_alarm"}
- Then opens: `conversations.scene.work.hunter_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a night patrol?" | "I'll leave you to the watch."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.acknowledged
WHO    VILLAGER — what the player reads after pressing "That puts you against the whole lane."
       spoken on: conversations.scene.work.hunter_expert.false_alarm.blocked.respond, button `worry_about_her`
       leaves the player on: conversations.scene.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.false_alarm.blocked.acknowledged`: the villager accepts. Subject `work.hunter_expert.false_alarms`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.acknowledged/1   [125 chars]
    en  It does. That is what the position is for. If I agreed with the lane there would be no reason to have somebody like me in it.
    >>  ............................................
    pt  Coloca. É para isso que a posição serve. Se eu concordasse com a viela, não haveria motivo para ter alguém como eu nela.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.acknowledged/2   [137 chars]
    en  For a season. Then the ewe turns out to have been a dog and everybody is quietly relieved and nobody says thank you, and that is correct.
    >>  ............................................
    pt  Por uma estação. Depois a ovelha acaba tendo sido um cachorro e todo mundo fica discretamente aliviado e ninguém agradece, e está certo.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.acknowledged/3   [114 chars]
    en  The frightening version is the one where I go along with it once, because after that I am not a check on anything.
    >>  ............................................
    pt  A versão assustadora é aquela em que eu concordo uma vez, porque depois disso eu não sou controle de nada.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the watch."

*stance family `exit` · tone `plain` · answers the beat(s) `work.hunter_expert.false_alarm.blocked` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter_expert.false_alarm.blocked.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter_expert.false_alarm.blocked.respond.leave   [35 chars]
    en  I'll let you get back to the watch.
    >>  ............................................
    pt  Vou deixar você voltar à ronda.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the watch."
       spoken on: conversations.scene.work.hunter_expert.false_alarm.blocked.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.left`: the villager accepts. Subject `work.hunter_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter_expert.failing_lantern.blocked.respond / leave; conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond / leave; conversations.scene.work.hunter_expert.false_alarm.succeeded.respond / leave; conversations.scene.work.hunter_expert.followup / leave; conversations.scene.work.hunter_expert.the_real_night.succeeded.respond / leave; conversations.topic.work.hunter_expert.craft.respond / leave; conversations.topic.work.hunter_expert.followup / leave; conversations.topic.work.hunter_expert.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.hunter_expert.failing_lantern.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.hunter_expert.false_alarm.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.hunter_expert.false_alarm.succeeded` — e.g. "It was a dog. Two lanes over, somebody's dog, and %2$s is still here and still being looked at sideways."


```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.false_alarm.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.hunter_expert.false_alarm.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.hunter_expert.false_alarm.succeeded.respond   [16 chars]
    en  That accusation.
    >>  ............................................
    pt  Aquela acusação.
    >>  ............................................
```


### Button `ask_about_after` — "What happens to them afterwards?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.hunter_expert.false_alarm.succeeded` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter_expert.false_alarm.succeeded.ask_about_after` — accepted phrasings: "what happens to them afterwards"; "what happens to them afterwards"; "how does a family recover from that"
  - the message must contain one of: `afterwards`, `recover`
  - scored words: `afterwards`(1.8), `recover`(1.8), `happens`(0.8), `does`(0.8), `family`(0.8), `from`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.false_alarm.succeeded.respond.ask_about_after
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter_expert.false_alarm.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter_expert.false_alarm.succeeded.respond.ask_about_after   [32 chars]
    en  What happens to them afterwards?
    >>  ............................................
    pt  O que acontece com eles depois?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2, warmth +1  _(recorded under topic `work.hunter_expert.false_alarms`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.hunter_expert.false_alarm"}
- Then opens: `conversations.scene.work.hunter_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a night patrol?" | "I'll leave you to the watch."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.false_alarm.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "What happens to them afterwards?"
       spoken on: conversations.scene.work.hunter_expert.false_alarm.succeeded.respond, button `ask_about_after`
       leaves the player on: conversations.scene.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.false_alarm.succeeded.answered`: the villager explains. Subject `work.hunter_expert.false_alarms`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter_expert.false_alarm.succeeded.answered/1   [135 chars]
    en  Nothing, officially, which is the problem. There is a procedure for accusing somebody and none at all for having been wrong about them.
    >>  ............................................
    pt  Nada, oficialmente, que é o problema. Existe um procedimento para acusar alguém e nenhum para ter errado sobre a pessoa.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.false_alarm.succeeded.answered/2   [111 chars]
    en  They leave, usually, within two years, and everybody says it was for work. I have watched three families do it.
    >>  ............................................
    pt  Vão embora, normalmente, dentro de dois anos, e todo mundo diz que foi por trabalho. Já vi três famílias fazerem isso.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.false_alarm.succeeded.answered/3   [158 chars]
    en  This one stayed. I think it is because two households went and sat in their kitchen the following week, and I have been trying to work out who organised that.
    >>  ............................................
    pt  Esta ficou. Acho que é porque duas casas foram sentar na cozinha deles na semana seguinte, e eu venho tentando descobrir quem organizou aquilo.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the watch."

*stance family `exit` · tone `plain` · answers the beat(s) `work.hunter_expert.false_alarm.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.false_alarm.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter_expert.false_alarm.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter_expert.false_alarm.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the watch.
    >>  ............................................
    pt  Vou deixar você voltar à ronda.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the watch."
       spoken on: conversations.scene.work.hunter_expert.false_alarm.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.left`: the villager accepts. Subject `work.hunter_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter_expert.failing_lantern.blocked.respond / leave; conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond / leave; conversations.scene.work.hunter_expert.false_alarm.blocked.respond / leave; conversations.scene.work.hunter_expert.followup / leave; conversations.scene.work.hunter_expert.the_real_night.succeeded.respond / leave; conversations.topic.work.hunter_expert.craft.respond / leave; conversations.topic.work.hunter_expert.followup / leave; conversations.topic.work.hunter_expert.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.hunter_expert.failing_lantern.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.hunter_expert.followup`

**Reached from 9 route(s):** `conversations.scene.work.hunter_expert.failing_lantern.blocked.respond` / `offer_lantern`; `conversations.scene.work.hunter_expert.failing_lantern.blocked.respond` / `ask_about_the_round`; `conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond` / `note_the_others`; `conversations.scene.work.hunter_expert.false_alarm.blocked.respond` / `ask_how_she_tells`; `conversations.scene.work.hunter_expert.false_alarm.blocked.respond` / `back_the_refusal`; `conversations.scene.work.hunter_expert.false_alarm.blocked.respond` / `worry_about_her`; `conversations.scene.work.hunter_expert.false_alarm.succeeded.respond` / `ask_about_after`; `conversations.scene.work.hunter_expert.the_real_night.succeeded.respond` / `ask_what_she_learned`; `conversations.scene.work.hunter_expert.the_real_night.succeeded.respond` / `respect_the_silence`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.hunter_expert.failing_lantern.blocked.accepted` — e.g. "Then the round is a round again rather than a hope, and I will sleep the day before it instead of worrying."
- `conversations.scene.work.hunter_expert.failing_lantern.blocked.explained` — e.g. "Eleven doors, two barns and a stretch of hedge, twice a night, and the point of it is that the village sleeps rather than that I find anything."
- `conversations.scene.work.hunter_expert.failing_lantern.succeeded.acknowledged` — e.g. "Everybody was waiting for somebody to admit it first, which is how a watch ends up walking around in the dark with broken lamps."
- `conversations.scene.work.hunter_expert.false_alarm.blocked.acknowledged` — e.g. "It does. That is what the position is for. If I agreed with the lane there would be no reason to have somebody like me in it."
- `conversations.scene.work.hunter_expert.false_alarm.blocked.explained` — e.g. "Evidence that would still be evidence if I disliked the person less. That is the only test I have and it throws out nine cases in ten."
- `conversations.scene.work.hunter_expert.false_alarm.blocked.steadied` — e.g. "I will, and it will cost me the lane's goodwill for a season, and that is a cheaper thing than a family driven out."
- `conversations.scene.work.hunter_expert.false_alarm.succeeded.answered` — e.g. "Nothing, officially, which is the problem. There is a procedure for accusing somebody and none at all for having been wrong about them."
- `conversations.scene.work.hunter_expert.the_real_night.succeeded.answered` — e.g. "That the preparation was what mattered and the preparation was boring, and not one person has asked me about the boring part since."
- `conversations.scene.work.hunter_expert.the_real_night.succeeded.softened` — e.g. "Thank you. You are the first person in six years to hear that I would rather not and then simply stop, and it is a strange relief."


```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.hunter_expert.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.hunter_expert.followup   [25 chars]
    en  Anything more to go over?
    >>  ............................................
    pt  Mais alguma coisa para repassar?
    >>  ............................................
```


### Button `ask_more` — "What's the hardest part of a night patrol?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `subject:work.hunter_expert.*` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter_expert.followup.ask_more` — accepted phrasings: "whats the hardest part of a night patrol"; "what is the hardest part of a night patrol"; "hardest thing about the night patrol"
  - the message must contain one of: `hardest`, `patrol`
  - scored words: `hardest`(1.8), `patrol`(1.8), `whats`(0.8), `part`(0.8), `night`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter_expert.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter_expert.followup.ask_more   [42 chars]
    en  What's the hardest part of a night patrol?
    >>  ............................................
    pt  Qual é a parte mais difícil de uma patrulha noturna?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter_expert.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you seen signs lately?" | "Stay indoors after dark."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.hard
WHO    VILLAGER — what the player reads after pressing "What's the hardest part of a night patrol?"
       spoken on: conversations.scene.work.hunter_expert.followup, button `ask_more`
       leaves the player on: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.hard`: the villager explains. Subject `work.hunter_expert.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.topic.work.hunter_expert.respond / ask_hard
```

```text
  dialogue.conversations.work.prof.hunter_expert.hard/1   [85 chars]
    en  Telling a family what their neighbour has become. That is the whole of the hard part.
    >>  ............................................
    pt  Contar a uma família no que o vizinho se tornou. É toda a parte difícil.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.hard/2   [83 chars]
    en  Sending a novice out, %1$s. I've done it forty times and I have not got used to it.
    >>  ............................................
    pt  Mandar um novato pra fora, %1$s. Já fiz isso quarenta vezes e não me acostumei.
    >>  ............................................
```


### Button `leave` — "I'll leave you to the watch."

*stance family `exit` · tone `plain` · answers the beat(s) `subject:work.hunter_expert.*` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter_expert.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter_expert.followup.leave   [28 chars]
    en  I'll leave you to the watch.
    >>  ............................................
    pt  Vou deixar você com a ronda.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll leave you to the watch."
       spoken on: conversations.scene.work.hunter_expert.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.left`: the villager accepts. Subject `work.hunter_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter_expert.failing_lantern.blocked.respond / leave; conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond / leave; conversations.scene.work.hunter_expert.false_alarm.blocked.respond / leave; conversations.scene.work.hunter_expert.false_alarm.succeeded.respond / leave; conversations.scene.work.hunter_expert.the_real_night.succeeded.respond / leave; conversations.topic.work.hunter_expert.craft.respond / leave; conversations.topic.work.hunter_expert.followup / leave; conversations.topic.work.hunter_expert.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.hunter_expert.failing_lantern.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.scene.work.hunter_expert.the_real_night.succeeded.respond`

**Reached from 1 route(s):** `conversations.cat.profession` / `work`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.scene.work.hunter_expert.the_real_night.succeeded` — e.g. "There was %2$s, six years ago, and it was the one time in nineteen years that any of this was not a false alarm."


```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.the_real_night.succeeded.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.scene.work.hunter_expert.the_real_night.succeeded.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.scene.work.hunter_expert.the_real_night.succeeded.respond   [24 chars]
    en  The night that was real.
    >>  ............................................
    pt  A noite que foi real.
    >>  ............................................
```


### Button `ask_what_she_learned` — "What did you take from it?"

*stance family `curiosity` · tone `gentle` · outcome `engaged` · answers the beat(s) `work.hunter_expert.the_real_night.succeeded` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter_expert.the_real_night.succeeded.ask_what_she_learned` — accepted phrasings: "what did you take from it"; "what did you take from it"; "what did that night teach you"
  - the message must contain one of: `take`, `teach`
  - scored words: `take`(1.8), `teach`(1.8), `from`(0.8), `night`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.the_real_night.succeeded.respond.ask_what_she_learned
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter_expert.the_real_night.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter_expert.the_real_night.succeeded.respond.ask_what_she_learned   [26 chars]
    en  What did you take from it?
    >>  ............................................
    pt  O que você tirou daquilo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.hunter_expert.the_one_real_night`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.hunter_expert.the_real_night"}
- Then opens: `conversations.scene.work.hunter_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a night patrol?" | "I'll leave you to the watch."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.the_real_night.succeeded.answered
WHO    VILLAGER — what the player reads after pressing "What did you take from it?"
       spoken on: conversations.scene.work.hunter_expert.the_real_night.succeeded.respond, button `ask_what_she_learned`
       leaves the player on: conversations.scene.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.the_real_night.succeeded.answered`: the villager explains. Subject `work.hunter_expert.the_one_real_night`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter_expert.the_real_night.succeeded.answered/1   [131 chars]
    en  That the preparation was what mattered and the preparation was boring, and not one person has asked me about the boring part since.
    >>  ............................................
    pt  Que a preparação era o que importava e a preparação era chata, e nenhuma pessoa me perguntou sobre a parte chata desde então.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.the_real_night.succeeded.answered/2   [147 chars]
    en  That being right about one night does not make me right about the next ninety. That is the lesson and it is the one I have to relearn every winter.
    >>  ............................................
    pt  Que estar certa sobre uma noite não me deixa certa sobre as próximas noventa. É a lição e é a que eu preciso reaprender todo inverno.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.the_real_night.succeeded.answered/3   [125 chars]
    en  That I want to be wrong. Every time I go out I am hoping to be wrong, and the one time I was not is the reason I hope harder.
    >>  ............................................
    pt  Que eu quero estar errada. Toda vez que eu saio estou torcendo para estar errada, e a única vez em que não estive é o motivo de eu torcer mais.
    >>  ............................................
```


### Button `respect_the_silence` — "Keeping that private makes sense."

*stance family `restraint` · tone `gentle` · outcome `appreciated` · answers the beat(s) `work.hunter_expert.the_real_night.succeeded` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `scene.work.hunter_expert.the_real_night.succeeded.respect_the_silence` — accepted phrasings: "keeping that private makes sense"; "your reasons for keeping it private are sound"; "keeping that private makes sense"
  - the message must contain one of: `private`, `reasons`, `keeping`
  - scored words: `private`(1.8), `reasons`(1.8), `keeping`(1.8), `makes`(0.8), `sense`(0.8), `sound`(0.8)

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.the_real_night.succeeded.respond.respect_the_silence
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter_expert.the_real_night.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter_expert.the_real_night.succeeded.respond.respect_the_silence   [33 chars]
    en  Keeping that private makes sense.
    >>  ............................................
    pt  Guardar isso em particular faz sentido.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.hunter_expert.night.respected`, budget `standard`, replay policy `once`
- Does: disposition — warmth +3, trust +3  _(recorded under topic `work.hunter_expert.the_one_real_night`)_
- Does: session `turn`
- Does: `conversations_thread` = {"op": "resolve", "template": "work.hunter_expert.the_real_night"}
- Then opens: `conversations.scene.work.hunter_expert.followup`
- …where the player's next choices will be: "What's the hardest part of a night patrol?" | "I'll leave you to the watch."

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.the_real_night.succeeded.softened
WHO    VILLAGER — what the player reads after pressing "Keeping that private makes sense."
       spoken on: conversations.scene.work.hunter_expert.the_real_night.succeeded.respond, button `respect_the_silence`
       leaves the player on: conversations.scene.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   3 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.the_real_night.succeeded.softened`: the villager accepts. Subject `work.hunter_expert.the_one_real_night`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: curiosity, candor, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.scene.work.hunter_expert.the_real_night.succeeded.softened/1   [130 chars]
    en  Thank you. You are the first person in six years to hear that I would rather not and then simply stop, and it is a strange relief.
    >>  ............................................
    pt  Obrigada. Você é a primeira pessoa em seis anos a ouvir que eu prefiro não falar e simplesmente parar, e é um alívio estranho.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.the_real_night.succeeded.softened/2   [93 chars]
    en  The story does harm and the facts do not, and separating those has taken me most of a decade.
    >>  ............................................
    pt  A história faz mal e os fatos não, e separar as duas coisas me levou quase uma década.
    >>  ............................................
  dialogue.conversations.scene.work.hunter_expert.the_real_night.succeeded.softened/3   [114 chars]
    en  I will tell somebody one day, properly, in writing, so that it exists without me having to perform it in a tavern.
    >>  ............................................
    pt  Vou contar a alguém um dia, direito, por escrito, para que aquilo exista sem eu ter que encenar numa taverna.
    >>  ............................................
```


### Button `leave` — "I'll let you get back to the watch."

*stance family `exit` · tone `plain` · answers the beat(s) `work.hunter_expert.the_real_night.succeeded` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.scene.work.hunter_expert.the_real_night.succeeded.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.scene.work.hunter_expert.the_real_night.succeeded.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.scene.work.hunter_expert.the_real_night.succeeded.respond.leave   [35 chars]
    en  I'll let you get back to the watch.
    >>  ............................................
    pt  Vou deixar você voltar à ronda.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get back to the watch."
       spoken on: conversations.scene.work.hunter_expert.the_real_night.succeeded.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.left`: the villager accepts. Subject `work.hunter_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter_expert.failing_lantern.blocked.respond / leave; conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond / leave; conversations.scene.work.hunter_expert.false_alarm.blocked.respond / leave; conversations.scene.work.hunter_expert.false_alarm.succeeded.respond / leave; conversations.scene.work.hunter_expert.followup / leave; conversations.topic.work.hunter_expert.craft.respond / leave; conversations.topic.work.hunter_expert.followup / leave; conversations.topic.work.hunter_expert.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.hunter_expert.failing_lantern.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.hunter_expert.craft.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.hunter_expert.craft` — e.g. "Half the trade is knowing what isn't one. Nine years and I've been right about that far more often than not."


```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.craft.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.hunter_expert.craft.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.hunter_expert.craft.respond   [27 chars]
    en  That's what it actually is.
    >>  ............................................
    pt  É isso que realmente é.
    >>  ............................................
```


### Button `ask_certain` — "What was wrong with being certain?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter_expert.craft` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter_expert.craft.ask_certain` — accepted phrasings: "what was wrong with being certain"
  - the message must contain one of: `certain`, `certainty`
  - scored words: `certain`(1.5), `certainty`(1.5), `wrong`(0.6)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.craft.respond.ask_certain
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.craft.respond.ask_certain   [34 chars]
    en  What was wrong with being certain?
    >>  ............................................
    pt  O que havia de errado com a certeza?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter_expert.craft.ask_certain`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you seen signs lately?" | "Stay indoors after dark."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.craft.ask_certain
WHO    VILLAGER — what the player reads after pressing "What was wrong with being certain?"
       spoken on: conversations.topic.work.hunter_expert.craft.respond, button `ask_certain`
       leaves the player on: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.craft.ask_certain`: the villager explains. Subject `work.hunter_expert.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.craft.ask_certain/1   [96 chars]
    en  Certainty is what puts a name on a neighbour. I've seen that done and I've seen what came after.
    >>  ............................................
    pt  Certeza é o que põe um nome num vizinho. Eu vi isso ser feito e vi o que veio depois.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.craft.ask_certain/2   [100 chars]
    en  It's fast, and fast is right about one night in fifty, %1$s. The other forty-nine it ruins somebody.
    >>  ............................................
    pt  É rápida, e rápida acerta uma noite em cinquenta, %1$s. Nas outras quarenta e nove arruína alguém.
    >>  ............................................
```


### Button `admire` — "Learning to be less certain is not the usual direction."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.hunter_expert.craft` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter_expert.craft.admire` — accepted phrasings: "learning to be less certain is not the usual direction"
  - the message must contain one of: `certain`, `direction`, `learning`
  - scored words: `certain`(1.2), `direction`(1.5), `learning`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.craft.respond.admire
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.craft.respond.admire   [55 chars]
    en  Learning to be less certain is not the usual direction.
    >>  ............................................
    pt  Aprender a ter menos certeza não é a direção usual.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.hunter_expert.craft.admire`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.hunter_expert.craft.admire`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you seen signs lately?" | "Stay indoors after dark."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.craft.admire
WHO    VILLAGER — what the player reads after pressing "Learning to be less certain is not the usual direction."
       spoken on: conversations.topic.work.hunter_expert.craft.respond, button `admire`
       leaves the player on: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.craft.admire`: the villager accepts. Subject `work.hunter_expert.craft`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.craft.admire/1   [86 chars]
    en  No. And it makes me a poor recruiter, because nobody joins a watch to be told to wait.
    >>  ............................................
    pt  Não. E me faz um recrutador ruim, porque ninguém entra numa vigia pra ouvir que espere.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.craft.admire/2   [89 chars]
    en  It's cost me the respect of every teacher I had, %1$s, and I'd make the same trade again.
    >>  ............................................
    pt  Me custou o respeito de todo mestre que eu tive, %1$s, e eu faria a mesma troca de novo.
    >>  ............................................
```


### Button `ask_right` — "How often has it actually been one?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter_expert.craft` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter_expert.craft.ask_right` — accepted phrasings: "how often has it actually been one"
  - the message must contain one of: `often`, `actually`, `twice`
  - scored words: `often`(1.2), `actually`(1.2), `twice`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.craft.respond.ask_right
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.craft.respond.ask_right   [35 chars]
    en  How often has it actually been one?
    >>  ............................................
    pt  Com que frequência foi mesmo um?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter_expert.craft.ask_right`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you seen signs lately?" | "Stay indoors after dark."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.craft.ask_right
WHO    VILLAGER — what the player reads after pressing "How often has it actually been one?"
       spoken on: conversations.topic.work.hunter_expert.craft.respond, button `ask_right`
       leaves the player on: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.craft.ask_right`: the villager explains. Subject `work.hunter_expert.craft`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.craft.ask_right/1   [101 chars]
    en  Twice in nine years. Both times I was slower than my training said and both times slower was correct.
    >>  ............................................
    pt  Duas vezes em nove anos. Nas duas eu fui mais lento que o treino mandava e nas duas o lento estava certo.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.craft.ask_right/2   [82 chars]
    en  Rarely enough that I'd rather talk about the foxes, %1$s. The foxes are the trade.
    >>  ............................................
    pt  Raro o bastante pra eu preferir falar das raposas, %1$s. As raposas são o ofício.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.hunter_expert.craft` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.craft.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.craft.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.craft.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.hunter_expert.craft.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.left`: the villager accepts. Subject `work.hunter_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter_expert.failing_lantern.blocked.respond / leave; conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond / leave; conversations.scene.work.hunter_expert.false_alarm.blocked.respond / leave; conversations.scene.work.hunter_expert.false_alarm.succeeded.respond / leave; conversations.scene.work.hunter_expert.followup / leave; conversations.scene.work.hunter_expert.the_real_night.succeeded.respond / leave; conversations.topic.work.hunter_expert.followup / leave; conversations.topic.work.hunter_expert.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.hunter_expert.failing_lantern.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.hunter_expert.followup`

**Reached from 20 route(s):** `conversations.scene.work.hunter_expert.followup` / `ask_more`; `conversations.topic.work.hunter_expert.craft.respond` / `ask_certain`; `conversations.topic.work.hunter_expert.craft.respond` / `admire`; `conversations.topic.work.hunter_expert.craft.respond` / `ask_right`; `conversations.topic.work.hunter_expert.future.respond` / `ask_rule`; `conversations.topic.work.hunter_expert.future.respond` / `encourage`; `conversations.topic.work.hunter_expert.future.respond` / `ask_hate`; `conversations.topic.work.hunter_expert.respond` / `ask_hard`; `conversations.topic.work.hunter_expert.respond` / `value`; `conversations.topic.work.hunter_expert.respond` / `challenge`; `conversations.topic.work.hunter_expert.respond` / `challenge`; `conversations.topic.work.hunter_expert.risk.respond` / `ask_account` …and 8 more

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.hunter_expert.challenge.landed` — e.g. "I'm teaching them to be sure first. That's the entire curriculum, and it's mostly waiting."
- `conversations.work.prof.hunter_expert.challenge.stung` — e.g. "...Ask what happened the year before I arrived. Then ask me that again."
- `conversations.work.prof.hunter_expert.craft.admire` — e.g. "No. And it makes me a poor recruiter, because nobody joins a watch to be told to wait."
- `conversations.work.prof.hunter_expert.craft.ask_certain` — e.g. "Certainty is what puts a name on a neighbour. I've seen that done and I've seen what came after."
- `conversations.work.prof.hunter_expert.craft.ask_right` — e.g. "Twice in nine years. Both times I was slower than my training said and both times slower was correct."
- `conversations.work.prof.hunter_expert.future.ask_hate` — e.g. "Because they joined to protect people and slowness feels like not protecting anyone."
- `conversations.work.prof.hunter_expert.future.ask_rule` — e.g. "Three nights of observation, two witnesses, and never on the word of somebody who stands to gain."
- `conversations.work.prof.hunter_expert.future.encourage` — e.g. "...He would. And a copy four valleys away, which is how a rule becomes a rule rather than my opinion."
- `conversations.work.prof.hunter_expert.hard` — e.g. "Telling a family what their neighbour has become. That is the whole of the hard part."
- `conversations.work.prof.hunter_expert.risk.ask_account` — e.g. "The scribe has it. He let me read it once and asked me nothing about why I wanted to."
- `conversations.work.prof.hunter_expert.risk.ask_two` — e.g. "Because the story would frighten them into caution and I want them careful, which is different."
- `conversations.work.prof.hunter_expert.risk.sympathise` — e.g. "...I carry the training we shared. That's not the same as his mistake and it's not nothing either."
- `conversations.work.prof.hunter_expert.task.ask_frightened` — e.g. "Almost everything. Nine times in ten it's a fox, and a watch that panics at foxes is no watch at all."
- `conversations.work.prof.hunter_expert.task.ask_same` — e.g. "Nine years of it. It's the repetition that lets me notice the one evening something is different."
- …and 5 more pools


```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.followup
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.hunter_expert.followup   [38 chars]
    en  That's the work, night shift included.
    >>  ............................................
    pt  É o trabalho, turno da noite incluído.
    >>  ............................................
```


### Button `thanks` — "I'd not thought about it that way."

*stance family `candor` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.hunter_expert.challenge.landed`, `work.hunter_expert.challenge.stung`, `work.hunter_expert.craft.admire`, `work.hunter_expert.craft.ask_certain`, `work.hunter_expert.craft.ask_right`, `work.hunter_expert.future.ask_hate`, `work.hunter_expert.future.ask_rule`, `work.hunter_expert.future.encourage`, `work.hunter_expert.hard`, `work.hunter_expert.risk.ask_account`, `work.hunter_expert.risk.ask_two`, `work.hunter_expert.risk.sympathise`, `work.hunter_expert.task.ask_frightened`, `work.hunter_expert.task.ask_same`, `work.hunter_expert.task.offer_hands`, `work.hunter_expert.value`, `work.hunter_expert.village.ask_never`, `work.hunter_expert.village.ask_priest`, `work.hunter_expert.village.say_thanks` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.hunter_expert.thanks` — accepted phrasings: "i'd not thought about it that way"; "i had not thought of it like that"; "that is a new way to see it"
  - the message must contain one of: `thought`, `teaching`, `door`
  - scored words: `thought`(1.2), `teaching`(1.0), `door`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.followup.thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.followup.thanks   [34 chars]
    en  I'd not thought about it that way.
    >>  ............................................
    pt  Eu não tinha pensado por esse lado.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.hunter_expert.thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +2, warmth +2  _(recorded under topic `work.prof.hunter_expert.thanks`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.thanks
WHO    VILLAGER — what the player reads after pressing "I'd not thought about it that way."
       spoken on: conversations.topic.work.hunter_expert.followup, button `thanks`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.hunter_expert.thanks`: the villager accepts. Subject `work.hunter_expert.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.thanks/1   [84 chars]
    en  Nobody does until it's their door. Then everybody has thought about it a great deal.
    >>  ............................................
    pt  Ninguém pensa até ser a própria porta. Aí todo mundo já pensou muito.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.thanks/2   [72 chars]
    en  It's teaching, mostly, %1$s. People imagine it's all stakes and running.
    >>  ............................................
    pt  É ensinar, principalmente, %1$s. As pessoas imaginam que é tudo estaca e corrida.
    >>  ............................................
```


### Button `ask_more` — "Have you seen signs lately?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter_expert.challenge.landed`, `work.hunter_expert.challenge.stung`, `work.hunter_expert.craft.admire`, `work.hunter_expert.craft.ask_certain`, `work.hunter_expert.craft.ask_right`, `work.hunter_expert.future.ask_hate`, `work.hunter_expert.future.ask_rule`, `work.hunter_expert.future.encourage`, `work.hunter_expert.hard`, `work.hunter_expert.risk.ask_account`, `work.hunter_expert.risk.ask_two`, `work.hunter_expert.risk.sympathise`, `work.hunter_expert.task.ask_frightened`, `work.hunter_expert.task.ask_same`, `work.hunter_expert.task.offer_hands`, `work.hunter_expert.value`, `work.hunter_expert.village.ask_never`, `work.hunter_expert.village.ask_priest`, `work.hunter_expert.village.say_thanks` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.prof.hunter_expert.more` — accepted phrasings: "have you seen signs lately"
  - the message must contain one of: `signs`, `lately`, `threat`
  - scored words: `signs`(1.5), `lately`(1.2), `threat`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.followup.ask_more
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.followup.ask_more   [27 chars]
    en  Have you seen signs lately?
    >>  ............................................
    pt  Você viu sinais ultimamente?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.prof.hunter_expert.more`)_
- Does: session `turn`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.more
WHO    VILLAGER — what the player reads after pressing "Have you seen signs lately?"
       spoken on: conversations.topic.work.hunter_expert.followup, button `ask_more`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.prof.hunter_expert.more`: the villager discloses. Subject `work.hunter_expert.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.more/1   [65 chars]
    en  Two. Neither confirmed. I'd rather be wrong twice than late once.
    >>  ............................................
    pt  Dois. Nenhum confirmado. Prefiro errar duas vezes a chegar tarde uma.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.more/2   [93 chars]
    en  Nothing I'd act on. I'd tell the guard before I told you, %1$s, and I haven't told the guard.
    >>  ............................................
    pt  Nada em que eu agiria. Eu avisaria o guarda antes de você, %1$s, e não avisei o guarda.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two, neither confirmed. Being wrong about a person is the thing I'm most afraid of.
    >>  ............................................
    pt  Dois, nenhum confirmado. Errar sobre uma pessoa é o que mais me assusta.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Nine years without a name. You can't show anybody the family that wasn't destroyed.
    >>  ............................................
    pt  Nove anos sem um nome. Você não consegue mostrar a família que não foi destruída.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two. Neither confirmed, and there's no hurry to confirm them badly.
    >>  ............................................
    pt  Dois. Nenhum confirmado, e não há pressa de confirmar mal.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Nine years, no names. Slowness is the whole method and it has held so far.
    >>  ............................................
    pt  Nove anos, nenhum nome. Lentidão é todo o método e se manteve até agora.
    >>  ............................................
  confident.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two. Neither confirmed. I would rather be wrong twice than late once.
    >>  ............................................
    pt  Dois. Nenhum confirmado. Prefiro errar duas vezes a chegar tarde uma.
    >>  ............................................
  confident.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Nine years and I have never put a name to anybody in this valley. That is the achievement.
    >>  ............................................
    pt  Nove anos e eu nunca pus um nome em ninguém deste vale. É essa a conquista.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two. Neither confirmed. I would rather be wrong twice than late once.
    >>  ............................................
    pt  Dois. Nenhum confirmado. Prefiro errar duas vezes a chegar tarde uma.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Nine years and I have never put a name to anybody in this valley. That is the achievement.
    >>  ............................................
    pt  Nove anos e eu nunca pus um nome em ninguém deste vale. É essa a conquista.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two, neither confirmed. Walk the boundary with me and I'll show you where and why.
    >>  ............................................
    pt  Dois, nenhum confirmado. Ande a divisa comigo e eu mostro onde e por quê.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Nine years and no names. Say that to the priest sometime — half of it is his doing.
    >>  ............................................
    pt  Nove anos e nenhum nome. Diga isso ao padre um dia — metade é obra dele.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two, neither confirmed. Walk the boundary with me and I'll show you where and why.
    >>  ............................................
    pt  Dois, nenhum confirmado. Ande a divisa comigo e eu mostro onde e por quê.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Nine years and no names. Say that to the priest sometime — half of it is his doing.
    >>  ............................................
    pt  Nove anos e nenhum nome. Diga isso ao padre um dia — metade é obra dele.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two, neither confirmed. Walk the boundary with me and I'll show you where and why.
    >>  ............................................
    pt  Dois, nenhum confirmado. Ande a divisa comigo e eu mostro onde e por quê.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Nine years and no names. Say that to the priest sometime — half of it is his doing.
    >>  ............................................
    pt  Nove anos e nenhum nome. Diga isso ao padre um dia — metade é obra dele.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two, neither confirmed. Being wrong about a person is the thing I'm most afraid of.
    >>  ............................................
    pt  Dois, nenhum confirmado. Errar sobre uma pessoa é o que mais me assusta.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Nine years without a name. You can't show anybody the family that wasn't destroyed.
    >>  ............................................
    pt  Nove anos sem um nome. Você não consegue mostrar a família que não foi destruída.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two. Neither confirmed. I would rather be wrong twice than late once.
    >>  ............................................
    pt  Dois. Nenhum confirmado. Prefiro errar duas vezes a chegar tarde uma.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Nine years and I have never put a name to anybody in this valley. That is the achievement.
    >>  ............................................
    pt  Nove anos e eu nunca pus um nome em ninguém deste vale. É essa a conquista.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two. Neither confirmed. I would rather be wrong twice than late once.
    >>  ............................................
    pt  Dois. Nenhum confirmado. Prefiro errar duas vezes a chegar tarde uma.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Nine years and I have never put a name to anybody in this valley. That is the achievement.
    >>  ............................................
    pt  Nove anos e eu nunca pus um nome em ninguém deste vale. É essa a conquista.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two. Neither confirmed, and neither will be until three nights and two witnesses say so.
    >>  ............................................
    pt  Dois. Nenhum confirmado, e nenhum será até três noites e duas testemunhas dizerem.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Three times I've been asked to name somebody. Three times I said I wasn't sure.
    >>  ............................................
    pt  Três vezes me pediram pra nomear alguém. Três vezes eu disse que não tinha certeza.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two. Neither confirmed, and there's no hurry to confirm them badly.
    >>  ............................................
    pt  Dois. Nenhum confirmado, e não há pressa de confirmar mal.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Nine years, no names. Slowness is the whole method and it has held so far.
    >>  ............................................
    pt  Nove anos, nenhum nome. Lentidão é todo o método e se manteve até agora.
    >>  ............................................
  odd.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two. Neither confirmed, and neither will be until three nights and two witnesses say so.
    >>  ............................................
    pt  Dois. Nenhum confirmado, e nenhum será até três noites e duas testemunhas dizerem.
    >>  ............................................
  odd.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Three times I've been asked to name somebody. Three times I said I wasn't sure.
    >>  ............................................
    pt  Três vezes me pediram pra nomear alguém. Três vezes eu disse que não tinha certeza.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two. Neither confirmed, and there's no hurry to confirm them badly.
    >>  ............................................
    pt  Dois. Nenhum confirmado, e não há pressa de confirmar mal.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Nine years, no names. Slowness is the whole method and it has held so far.
    >>  ............................................
    pt  Nove anos, nenhum nome. Lentidão é todo o método e se manteve até agora.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two! Neither confirmed. I'd rather be wrong twice than late once, and I say that a lot.
    >>  ............................................
    pt  Dois! Nenhum confirmado. Prefiro errar duas vezes a chegar tarde uma, e eu digo isso bastante.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Nine years, no names. It's a record of things that didn't happen and I'm quietly smug about it.
    >>  ............................................
    pt  Nove anos, nenhum nome. É um registro do que não aconteceu e eu me acho um pouco por isso.
    >>  ............................................
  playful.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two! Neither confirmed. I'd rather be wrong twice than late once, and I say that a lot.
    >>  ............................................
    pt  Dois! Nenhum confirmado. Prefiro errar duas vezes a chegar tarde uma, e eu digo isso bastante.
    >>  ............................................
  playful.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Nine years, no names. It's a record of things that didn't happen and I'm quietly smug about it.
    >>  ............................................
    pt  Nove anos, nenhum nome. É um registro do que não aconteceu e eu me acho um pouco por isso.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two. Neither confirmed, and there's no hurry to confirm them badly.
    >>  ............................................
    pt  Dois. Nenhum confirmado, e não há pressa de confirmar mal.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Nine years, no names. Slowness is the whole method and it has held so far.
    >>  ............................................
    pt  Nove anos, nenhum nome. Lentidão é todo o método e se manteve até agora.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two, neither confirmed. Being wrong about a person is the thing I'm most afraid of.
    >>  ............................................
    pt  Dois, nenhum confirmado. Errar sobre uma pessoa é o que mais me assusta.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Nine years without a name. You can't show anybody the family that wasn't destroyed.
    >>  ............................................
    pt  Nove anos sem um nome. Você não consegue mostrar a família que não foi destruída.
    >>  ............................................
  shy.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two. Neither confirmed, and neither will be until three nights and two witnesses say so.
    >>  ............................................
    pt  Dois. Nenhum confirmado, e nenhum será até três noites e duas testemunhas dizerem.
    >>  ............................................
  shy.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Three times I've been asked to name somebody. Three times I said I wasn't sure.
    >>  ............................................
    pt  Três vezes me pediram pra nomear alguém. Três vezes eu disse que não tinha certeza.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two! Neither confirmed. I'd rather be wrong twice than late once, and I say that a lot.
    >>  ............................................
    pt  Dois! Nenhum confirmado. Prefiro errar duas vezes a chegar tarde uma, e eu digo isso bastante.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Nine years, no names. It's a record of things that didn't happen and I'm quietly smug about it.
    >>  ............................................
    pt  Nove anos, nenhum nome. É um registro do que não aconteceu e eu me acho um pouco por isso.
    >>  ............................................
  witty.dialogue.conversations.work.prof.hunter_expert.more/1
    en  Two! Neither confirmed. I'd rather be wrong twice than late once, and I say that a lot.
    >>  ............................................
    pt  Dois! Nenhum confirmado. Prefiro errar duas vezes a chegar tarde uma, e eu digo isso bastante.
    >>  ............................................
  witty.dialogue.conversations.work.prof.hunter_expert.more/2
    en  Nine years, no names. It's a record of things that didn't happen and I'm quietly smug about it.
    >>  ............................................
    pt  Nove anos, nenhum nome. É um registro do que não aconteceu e eu me acho um pouco por isso.
    >>  ............................................
```

</details>


### Button `leave` — "Stay indoors after dark."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.hunter_expert.challenge.landed`, `work.hunter_expert.challenge.stung`, `work.hunter_expert.craft.admire`, `work.hunter_expert.craft.ask_certain`, `work.hunter_expert.craft.ask_right`, `work.hunter_expert.future.ask_hate`, `work.hunter_expert.future.ask_rule`, `work.hunter_expert.future.encourage`, `work.hunter_expert.hard`, `work.hunter_expert.risk.ask_account`, `work.hunter_expert.risk.ask_two`, `work.hunter_expert.risk.sympathise`, `work.hunter_expert.task.ask_frightened`, `work.hunter_expert.task.ask_same`, `work.hunter_expert.task.offer_hands`, `work.hunter_expert.value`, `work.hunter_expert.village.ask_never`, `work.hunter_expert.village.ask_priest`, `work.hunter_expert.village.say_thanks` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.followup.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.followup
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.followup.leave   [24 chars]
    en  Stay indoors after dark.
    >>  ............................................
    pt  Fique em casa depois de escurecer.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: session `end`
- Then opens: `conversations.cat.profession`
- …where the player's next choices will be: "Do you actually like your work?" | "Anything you need doing?" | "Something else."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.leave
WHO    VILLAGER — what the player reads after pressing "Stay indoors after dark."
       spoken on: conversations.topic.work.hunter_expert.followup, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.left`: the villager accepts. Subject `work.hunter_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter_expert.failing_lantern.blocked.respond / leave; conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond / leave; conversations.scene.work.hunter_expert.false_alarm.blocked.respond / leave; conversations.scene.work.hunter_expert.false_alarm.succeeded.respond / leave; conversations.scene.work.hunter_expert.followup / leave; conversations.scene.work.hunter_expert.the_real_night.succeeded.respond / leave; conversations.topic.work.hunter_expert.craft.respond / leave; conversations.topic.work.hunter_expert.future.respond / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.hunter_expert.failing_lantern.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.hunter_expert.future.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.hunter_expert.future` — e.g. "I want my two to be better than me, which means being slower than me, which they will hate."


```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.future.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.hunter_expert.future.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.hunter_expert.future.respond   [25 chars]
    en  That's what's left to do.
    >>  ............................................
    pt  É o que falta fazer.
    >>  ............................................
```


### Button `ask_rule` — "What would the rule say?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter_expert.future` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter_expert.future.ask_rule` — accepted phrasings: "what would the rule say"
  - the message must contain one of: `rule`, `written`
  - scored words: `rule`(1.5), `written`(1.2), `say`(0.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.future.respond.ask_rule
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.future.respond.ask_rule   [24 chars]
    en  What would the rule say?
    >>  ............................................
    pt  O que a regra diria?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter_expert.future.ask_rule`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you seen signs lately?" | "Stay indoors after dark."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.future.ask_rule
WHO    VILLAGER — what the player reads after pressing "What would the rule say?"
       spoken on: conversations.topic.work.hunter_expert.future.respond, button `ask_rule`
       leaves the player on: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.future.ask_rule`: the villager explains. Subject `work.hunter_expert.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.future.ask_rule/1   [97 chars]
    en  Three nights of observation, two witnesses, and never on the word of somebody who stands to gain.
    >>  ............................................
    pt  Três noites de observação, duas testemunhas, e nunca pela palavra de quem tem a ganhar.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.future.ask_rule/2   [99 chars]
    en  Mostly it would say 'not yet', %1$s, in nine different ways, which is why nobody wants to write it.
    >>  ............................................
    pt  Diria principalmente 'ainda não', %1$s, de nove jeitos diferentes, e por isso ninguém quer escrever.
    >>  ............................................
```


### Button `encourage` — "Then write it. The scribe would keep a copy."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.hunter_expert.future` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter_expert.future.encourage` — accepted phrasings: "then write it. the scribe would keep a copy"
  - the message must contain one of: `write`, `scribe`, `copy`
  - scored words: `write`(1.5), `scribe`(1.2), `copy`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.future.respond.encourage
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.future.respond.encourage   [44 chars]
    en  Then write it. The scribe would keep a copy.
    >>  ............................................
    pt  Então escreva. O escriba guardaria uma cópia.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.hunter_expert.future.encourage`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.hunter_expert.future.encourage`)_
- Does: arc `work` — advance
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you seen signs lately?" | "Stay indoors after dark."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.future.encourage
WHO    VILLAGER — what the player reads after pressing "Then write it. The scribe would keep a copy."
       spoken on: conversations.topic.work.hunter_expert.future.respond, button `encourage`
       leaves the player on: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.future.encourage`: the villager accepts. Subject `work.hunter_expert.future`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.future.encourage/1   [101 chars]
    en  ...He would. And a copy four valleys away, which is how a rule becomes a rule rather than my opinion.
    >>  ............................................
    pt  ...Ele guardaria. E uma cópia a quatro vales, que é como uma regra vira regra e não minha opinião.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.future.encourage/2   [106 chars]
    en  Write it and be argued with. That's better than what I have, %1$s, which is being agreed with and ignored.
    >>  ............................................
    pt  Escrever e ser contestado. É melhor que o que eu tenho, %1$s, que é ser concordado e ignorado.
    >>  ............................................
```

<details><summary><b>Per-personality versions &mdash; 21 of 21 personalities override this pool. The <code>&lt;personality&gt;.</code> key prefix is mandatory.</b></summary>

```text
  anxious.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would. And a copy elsewhere, in case I'm not here to argue for it.
    >>  ............................................
    pt  ...Ele faria. E uma cópia longe, caso eu não esteja aqui pra defender.
    >>  ............................................
  anxious.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Write it and be argued with. Being agreed with and ignored has worn me down.
    >>  ............................................
    pt  Escrever e ser contestado. Ser concordado e ignorado me desgastou.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would. A copy four valleys off is how anything outlives the person who wrote it.
    >>  ............................................
    pt  ...Ele faria. Uma cópia a quatro vales é como algo sobrevive a quem escreveu.
    >>  ............................................
  athletic.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Write it and be argued with. Thirty years of polite agreement has taught me nothing.
    >>  ............................................
    pt  Escrever e ser contestado. Trinta anos de concordância educada não me ensinaram nada.
    >>  ............................................
  confident.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would. And a copy four valleys away, which is how a rule becomes a rule.
    >>  ............................................
    pt  ...Ele faria. E uma cópia a quatro vales daqui, que é como uma regra vira regra.
    >>  ............................................
  confident.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Write it and be argued with. That's better than being agreed with and ignored.
    >>  ............................................
    pt  Escrever e ser contestado. É melhor que ser concordado e ignorado.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would. And a copy four valleys away, which is how a rule becomes a rule.
    >>  ............................................
    pt  ...Ele faria. E uma cópia a quatro vales daqui, que é como uma regra vira regra.
    >>  ............................................
  crabby.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Write it and be argued with. That's better than being agreed with and ignored.
    >>  ............................................
    pt  Escrever e ser contestado. É melhor que ser concordado e ignorado.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would, %1$s. And a copy four valleys off, which is how a rule becomes a rule.
    >>  ............................................
    pt  ...Ele faria, %1$s. E uma cópia a quatro vales, que é como uma regra vira regra.
    >>  ............................................
  extroverted.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Write it and be argued with. That's better than what I have, which is polite agreement.
    >>  ............................................
    pt  Escrever e ser contestado. Melhor que o que tenho, que é concordância educada.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would, %1$s. And a copy four valleys off, which is how a rule becomes a rule.
    >>  ............................................
    pt  ...Ele faria, %1$s. E uma cópia a quatro vales, que é como uma regra vira regra.
    >>  ............................................
  flirty.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Write it and be argued with. That's better than what I have, which is polite agreement.
    >>  ............................................
    pt  Escrever e ser contestado. Melhor que o que tenho, que é concordância educada.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would, %1$s. And a copy four valleys off, which is how a rule becomes a rule.
    >>  ............................................
    pt  ...Ele faria, %1$s. E uma cópia a quatro vales, que é como uma regra vira regra.
    >>  ............................................
  friendly.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Write it and be argued with. That's better than what I have, which is polite agreement.
    >>  ............................................
    pt  Escrever e ser contestado. Melhor que o que tenho, que é concordância educada.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would. And a copy elsewhere, in case I'm not here to argue for it.
    >>  ............................................
    pt  ...Ele faria. E uma cópia longe, caso eu não esteja aqui pra defender.
    >>  ............................................
  gloomy.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Write it and be argued with. Being agreed with and ignored has worn me down.
    >>  ............................................
    pt  Escrever e ser contestado. Ser concordado e ignorado me desgastou.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would. And a copy four valleys away, which is how a rule becomes a rule.
    >>  ............................................
    pt  ...Ele faria. E uma cópia a quatro vales daqui, que é como uma regra vira regra.
    >>  ............................................
  greedy.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Write it and be argued with. That's better than being agreed with and ignored.
    >>  ............................................
    pt  Escrever e ser contestado. É melhor que ser concordado e ignorado.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would. And a copy four valleys away, which is how a rule becomes a rule.
    >>  ............................................
    pt  ...Ele faria. E uma cópia a quatro vales daqui, que é como uma regra vira regra.
    >>  ............................................
  grumpy.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Write it and be argued with. That's better than being agreed with and ignored.
    >>  ............................................
    pt  Escrever e ser contestado. É melhor que ser concordado e ignorado.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would. A copy four valleys off.
    >>  ............................................
    pt  ...Ele faria. Uma cópia a quatro vales.
    >>  ............................................
  introverted.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Written and argued with. Better than ignored.
    >>  ............................................
    pt  Escrita e contestada. Melhor que ignorada.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would. A copy four valleys off is how anything outlives the person who wrote it.
    >>  ............................................
    pt  ...Ele faria. Uma cópia a quatro vales é como algo sobrevive a quem escreveu.
    >>  ............................................
  lazy.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Write it and be argued with. Thirty years of polite agreement has taught me nothing.
    >>  ............................................
    pt  Escrever e ser contestado. Trinta anos de concordância educada não me ensinaram nada.
    >>  ............................................
  odd.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would. A copy four valleys off.
    >>  ............................................
    pt  ...Ele faria. Uma cópia a quatro vales.
    >>  ............................................
  odd.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Written and argued with. Better than ignored.
    >>  ............................................
    pt  Escrita e contestada. Melhor que ignorada.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would. A copy four valleys off is how anything outlives the person who wrote it.
    >>  ............................................
    pt  ...Ele faria. Uma cópia a quatro vales é como algo sobrevive a quem escreveu.
    >>  ............................................
  peaceful.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Write it and be argued with. Thirty years of polite agreement has taught me nothing.
    >>  ............................................
    pt  Escrever e ser contestado. Trinta anos de concordância educada não me ensinaram nada.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would! And a copy four valleys away — that's how a rule becomes a rule.
    >>  ............................................
    pt  ...Ele faria! E uma cópia a quatro vales — é assim que uma regra vira regra.
    >>  ............................................
  peppy.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Write it and be argued with. Far better than being agreed with and ignored.
    >>  ............................................
    pt  Escrever e ser contestado. Bem melhor que ser concordado e ignorado.
    >>  ............................................
  playful.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would! And a copy four valleys away — that's how a rule becomes a rule.
    >>  ............................................
    pt  ...Ele faria! E uma cópia a quatro vales — é assim que uma regra vira regra.
    >>  ............................................
  playful.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Write it and be argued with. Far better than being agreed with and ignored.
    >>  ............................................
    pt  Escrever e ser contestado. Bem melhor que ser concordado e ignorado.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would. A copy four valleys off is how anything outlives the person who wrote it.
    >>  ............................................
    pt  ...Ele faria. Uma cópia a quatro vales é como algo sobrevive a quem escreveu.
    >>  ............................................
  relaxed.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Write it and be argued with. Thirty years of polite agreement has taught me nothing.
    >>  ............................................
    pt  Escrever e ser contestado. Trinta anos de concordância educada não me ensinaram nada.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would. And a copy elsewhere, in case I'm not here to argue for it.
    >>  ............................................
    pt  ...Ele faria. E uma cópia longe, caso eu não esteja aqui pra defender.
    >>  ............................................
  sensitive.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Write it and be argued with. Being agreed with and ignored has worn me down.
    >>  ............................................
    pt  Escrever e ser contestado. Ser concordado e ignorado me desgastou.
    >>  ............................................
  shy.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would. A copy four valleys off.
    >>  ............................................
    pt  ...Ele faria. Uma cópia a quatro vales.
    >>  ............................................
  shy.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Written and argued with. Better than ignored.
    >>  ............................................
    pt  Escrita e contestada. Melhor que ignorada.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would! And a copy four valleys away — that's how a rule becomes a rule.
    >>  ............................................
    pt  ...Ele faria! E uma cópia a quatro vales — é assim que uma regra vira regra.
    >>  ............................................
  upbeat.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Write it and be argued with. Far better than being agreed with and ignored.
    >>  ............................................
    pt  Escrever e ser contestado. Bem melhor que ser concordado e ignorado.
    >>  ............................................
  witty.dialogue.conversations.work.prof.hunter_expert.future.encourage/1
    en  ...He would! And a copy four valleys away — that's how a rule becomes a rule.
    >>  ............................................
    pt  ...Ele faria! E uma cópia a quatro vales — é assim que uma regra vira regra.
    >>  ............................................
  witty.dialogue.conversations.work.prof.hunter_expert.future.encourage/2
    en  Write it and be argued with. Far better than being agreed with and ignored.
    >>  ............................................
    pt  Escrever e ser contestado. Bem melhor que ser concordado e ignorado.
    >>  ............................................
```

</details>


### Button `ask_hate` — "Why will they hate being slower?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter_expert.future` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter_expert.future.ask_hate` — accepted phrasings: "why will they hate being slower"
  - the message must contain one of: `hate`, `slower`, `students`
  - scored words: `hate`(1.5), `slower`(1.2), `students`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.future.respond.ask_hate
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.future.respond.ask_hate   [32 chars]
    en  Why will they hate being slower?
    >>  ............................................
    pt  Por que vão detestar ser mais lentos?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter_expert.future.ask_hate`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you seen signs lately?" | "Stay indoors after dark."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.future.ask_hate
WHO    VILLAGER — what the player reads after pressing "Why will they hate being slower?"
       spoken on: conversations.topic.work.hunter_expert.future.respond, button `ask_hate`
       leaves the player on: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.future.ask_hate`: the villager explains. Subject `work.hunter_expert.future`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.future.ask_hate/1   [84 chars]
    en  Because they joined to protect people and slowness feels like not protecting anyone.
    >>  ............................................
    pt  Porque entraram pra proteger e lentidão parece não proteger ninguém.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.future.ask_hate/2   [100 chars]
    en  Because they're nineteen and twenty-two, %1$s, and at that age waiting feels exactly like cowardice.
    >>  ............................................
    pt  Porque têm dezenove e vinte e dois anos, %1$s, e nessa idade esperar parece exatamente covardia.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.hunter_expert.future` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.future.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.future.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.future.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.hunter_expert.future.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.left`: the villager accepts. Subject `work.hunter_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter_expert.failing_lantern.blocked.respond / leave; conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond / leave; conversations.scene.work.hunter_expert.false_alarm.blocked.respond / leave; conversations.scene.work.hunter_expert.false_alarm.succeeded.respond / leave; conversations.scene.work.hunter_expert.followup / leave; conversations.scene.work.hunter_expert.the_real_night.succeeded.respond / leave; conversations.topic.work.hunter_expert.craft.respond / leave; conversations.topic.work.hunter_expert.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.hunter_expert.failing_lantern.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.hunter_expert.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.hunter_expert` — e.g. "I train those who stand against the night-drinkers. Garlic inventory alone is a full day's work."


```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.hunter_expert.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.hunter_expert.respond   [38 chars]
    en  That's the training and the inventory.
    >>  ............................................
    pt  É o treinamento e o inventário.
    >>  ............................................
```


### Button `ask_hard` — "What's the part you dread?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter_expert.identity` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter_expert.hard` — accepted phrasings: "what's the part you dread"
  - the message must contain one of: `dread`, `worst`, `fear`
  - scored words: `dread`(1.5), `worst`(1.0), `fear`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.respond.ask_hard
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.respond.ask_hard   [26 chars]
    en  What's the part you dread?
    >>  ............................................
    pt  Qual é a parte que você teme?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +3, trust +1  _(recorded under topic `work.hunter_expert.hard`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you seen signs lately?" | "Stay indoors after dark."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.hard
WHO    VILLAGER — what the player reads after pressing "What's the part you dread?"
       spoken on: conversations.topic.work.hunter_expert.respond, button `ask_hard`
       leaves the player on: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.hard`: the villager explains. Subject `work.hunter_expert.identity`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter_expert.followup / ask_more
```

> Written out in full under **`conversations.scene.work.hunter_expert.followup` / button `ask_more`** earlier in this file. Fill it in there, once.


### Button `value` — "Nobody here knows how close it has been."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.hunter_expert.identity` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter_expert.value` — accepted phrasings: "nobody here knows how close it has been"
  - the message must contain one of: `close`, `protected`
  - scored words: `close`(1.2), `nobody`(0.8), `protected`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.respond.value
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.respond.value   [40 chars]
    en  Nobody here knows how close it has been.
    >>  ............................................
    pt  Ninguém aqui sabe o quão perto já esteve.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.hunter_expert.value`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4, warmth +2  _(recorded under topic `work.hunter_expert.value`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you seen signs lately?" | "Stay indoors after dark."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.value
WHO    VILLAGER — what the player reads after pressing "Nobody here knows how close it has been."
       spoken on: conversations.topic.work.hunter_expert.respond, button `value`
       leaves the player on: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.value`: the villager accepts. Subject `work.hunter_expert.identity`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.value/1   [92 chars]
    en  No. And it should stay that way — frightened people make worse decisions than ignorant ones.
    >>  ............................................
    pt  Não. E deve continuar assim — gente com medo decide pior que gente ignorante.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.value/2   [71 chars]
    en  Twice. Both times a novice did the work and nobody thanked them either.
    >>  ............................................
    pt  Duas vezes. Nas duas um novato fez o trabalho e ninguém agradeceu a ele também.
    >>  ............................................
```


### Button `challenge` — "You're teaching people to kill their neighbours."

*stance family `challenge` · tone `blunt` · outcome `resisted` · answers the beat(s) `work.hunter_expert.identity` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter_expert.challenge` — accepted phrasings: "you're teaching people to kill their neighbours"
  - the message must contain one of: `teaching`, `neighbours`, `kill`
  - scored words: `teaching`(1.5), `neighbours`(1.5), `kill`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.respond.challenge
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.respond.challenge   [48 chars]
    en  You're teaching people to kill their neighbours.
    >>  ............................................
    pt  Você está ensinando gente a matar os vizinhos.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 2** — base weight `0`

- Fires when: weighted +100 when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`
- Does: **hearts +1** — decision id `work.hunter_expert.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +4  _(recorded under topic `work.hunter_expert.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you seen signs lately?" | "Stay indoors after dark."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.challenge.landed
WHO    VILLAGER — what the player reads after pressing "You're teaching people to kill their neighbours."
       spoken on: conversations.topic.work.hunter_expert.respond, button `challenge`
       leaves the player on: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.challenge.landed`: the villager resists. Subject `work.hunter_expert.identity`, polarity `mixed`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.challenge.landed/1   [90 chars]
    en  I'm teaching them to be sure first. That's the entire curriculum, and it's mostly waiting.
    >>  ............................................
    pt  Estou ensinando a ter certeza primeiro. É o currículo inteiro, e é quase todo espera.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.challenge.landed/2   [81 chars]
    en  That's the accusation, %1$s, and it's the one I check myself against every month.
    >>  ............................................
    pt  É a acusação, %1$s, e é aquela contra a qual eu me avalio todo mês.
    >>  ............................................
```


**Outcome 2 of 2** — base weight `1`  ·  _last in the list, so this is the safety net MCA falls back to when nothing else scores_

- Fires when: RULED OUT when the personality is `confident`, `greedy`, `crabby`, `peaceful`, `relaxed`, `upbeat`  _(chance -2000)_
- Does: **hearts -1** — decision id `work.hunter_expert.challenge`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect -1, tension +4  _(recorded under topic `work.hunter_expert.challenge`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you seen signs lately?" | "Stay indoors after dark."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.challenge.stung
WHO    VILLAGER — what the player reads after pressing "You're teaching people to kill their neighbours."
       spoken on: conversations.topic.work.hunter_expert.respond, button `challenge`
       leaves the player on: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.challenge.stung`: the villager resists. Subject `work.hunter_expert.identity`, polarity `negative`, permits followup, outcome `resisted`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.challenge.stung/1   [71 chars]
    en  ...Ask what happened the year before I arrived. Then ask me that again.
    >>  ............................................
    pt  ...Pergunte o que aconteceu no ano antes de eu chegar. Aí me pergunte isso de novo.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.challenge.stung/2   [74 chars]
    en  Their neighbours. Right. I'll be sure to mention that at the next funeral.
    >>  ............................................
    pt  Os vizinhos. Certo. Vou lembrar de mencionar isso no próximo funeral.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.hunter_expert.identity` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.hunter_expert.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.left`: the villager accepts. Subject `work.hunter_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter_expert.failing_lantern.blocked.respond / leave; conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond / leave; conversations.scene.work.hunter_expert.false_alarm.blocked.respond / leave; conversations.scene.work.hunter_expert.false_alarm.succeeded.respond / leave; conversations.scene.work.hunter_expert.followup / leave; conversations.scene.work.hunter_expert.the_real_night.succeeded.respond / leave; conversations.topic.work.hunter_expert.craft.respond / leave; conversations.topic.work.hunter_expert.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.hunter_expert.failing_lantern.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.hunter_expert.risk.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.hunter_expert.risk` — e.g. "The danger in my trade is my trade. A hunter who's wrong is worse than the thing he was wrong about."


```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.risk.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.hunter_expert.risk.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.hunter_expert.risk.respond   [25 chars]
    en  That's what I hold on to.
    >>  ............................................
    pt  É a isso que eu me agarro.
    >>  ............................................
```


### Button `ask_account` — "Where did you read the account?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter_expert.risk` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter_expert.risk.ask_account` — accepted phrasings: "where did you read the account"
  - the message must contain one of: `account`, `read`, `scribe`
  - scored words: `account`(1.5), `read`(1.0), `scribe`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.risk.respond.ask_account
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.risk.respond.ask_account   [31 chars]
    en  Where did you read the account?
    >>  ............................................
    pt  Onde você leu o relato?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter_expert.risk.ask_account`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you seen signs lately?" | "Stay indoors after dark."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.risk.ask_account
WHO    VILLAGER — what the player reads after pressing "Where did you read the account?"
       spoken on: conversations.topic.work.hunter_expert.risk.respond, button `ask_account`
       leaves the player on: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.risk.ask_account`: the villager explains. Subject `work.hunter_expert.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.risk.ask_account/1   [85 chars]
    en  The scribe has it. He let me read it once and asked me nothing about why I wanted to.
    >>  ............................................
    pt  O escriba tem. Ele me deixou ler uma vez e não perguntou nada sobre por que eu queria.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.risk.ask_account/2   [85 chars]
    en  It's the reason I walk the boundary slowly, %1$s, and I've never told my own two why.
    >>  ............................................
    pt  É a razão de eu andar a divisa devagar, %1$s, e eu nunca contei aos meus dois por quê.
    >>  ............................................
```


### Button `sympathise` — "You carry another man's mistake as if it were yours."

*stance family `empathy` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.hunter_expert.risk` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter_expert.risk.sympathise` — accepted phrasings: "you carry another man's mistake as if it were yours"
  - the message must contain one of: `carry`, `mistake`, `another`
  - scored words: `carry`(1.5), `mistake`(1.2), `another`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.risk.respond.sympathise
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.risk.respond.sympathise   [52 chars]
    en  You carry another man's mistake as if it were yours.
    >>  ............................................
    pt  Você carrega o erro de outro homem como se fosse seu.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.hunter_expert.risk.sympathise`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.hunter_expert.risk.sympathise`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you seen signs lately?" | "Stay indoors after dark."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.risk.sympathise
WHO    VILLAGER — what the player reads after pressing "You carry another man's mistake as if it were yours."
       spoken on: conversations.topic.work.hunter_expert.risk.respond, button `sympathise`
       leaves the player on: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.risk.sympathise`: the villager accepts. Subject `work.hunter_expert.risk`, polarity `mixed`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.risk.sympathise/1   [98 chars]
    en  ...I carry the training we shared. That's not the same as his mistake and it's not nothing either.
    >>  ............................................
    pt  ...Eu carrego o treino que compartilhamos. Não é o mesmo que o erro dele e também não é nada.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.risk.sympathise/2   [91 chars]
    en  If I don't carry it, %1$s, then nobody in this trade does, and that's how it happens again.
    >>  ............................................
    pt  Se eu não carregar, %1$s, ninguém neste ofício carrega, e é assim que acontece de novo.
    >>  ............................................
```


### Button `ask_two` — "Why not tell your two why you're slow?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter_expert.risk` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter_expert.risk.ask_two` — accepted phrasings: "why not tell your two why you're slow"
  - the message must contain one of: `tell`, `slow`, `students`
  - scored words: `tell`(1.2), `slow`(1.5), `students`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.risk.respond.ask_two
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.risk.respond.ask_two   [38 chars]
    en  Why not tell your two why you're slow?
    >>  ............................................
    pt  Por que não contar aos seus dois por que você é lento?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter_expert.risk.ask_two`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you seen signs lately?" | "Stay indoors after dark."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.risk.ask_two
WHO    VILLAGER — what the player reads after pressing "Why not tell your two why you're slow?"
       spoken on: conversations.topic.work.hunter_expert.risk.respond, button `ask_two`
       leaves the player on: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.risk.ask_two`: the villager explains. Subject `work.hunter_expert.risk`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.risk.ask_two/1   [95 chars]
    en  Because the story would frighten them into caution and I want them careful, which is different.
    >>  ............................................
    pt  Porque a história os assustaria até a cautela e eu os quero cuidadosos, que é diferente.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.risk.ask_two/2   [99 chars]
    en  I've been meaning to for two years, %1$s. Saying it aloud just now has rather settled the argument.
    >>  ............................................
    pt  Venho pretendendo há dois anos, %1$s. Dizer em voz alta agora meio que encerrou a discussão.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.hunter_expert.risk` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.risk.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.risk.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.risk.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.hunter_expert.risk.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.left`: the villager accepts. Subject `work.hunter_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter_expert.failing_lantern.blocked.respond / leave; conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond / leave; conversations.scene.work.hunter_expert.false_alarm.blocked.respond / leave; conversations.scene.work.hunter_expert.false_alarm.succeeded.respond / leave; conversations.scene.work.hunter_expert.followup / leave; conversations.scene.work.hunter_expert.the_real_night.succeeded.respond / leave; conversations.topic.work.hunter_expert.craft.respond / leave; conversations.topic.work.hunter_expert.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.hunter_expert.failing_lantern.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.hunter_expert.task.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.hunter_expert.task` — e.g. "Teaching two people to keep watch properly. Most of that is teaching them what not to be frightened of."


```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.task.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.hunter_expert.task.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.hunter_expert.task.respond   [19 chars]
    en  That's the evening.
    >>  ............................................
    pt  É a noite.
    >>  ............................................
```


### Button `ask_frightened` — "What shouldn't they be frightened of?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter_expert.task` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter_expert.task.ask_frightened` — accepted phrasings: "what shouldn't they be frightened of"
  - the message must contain one of: `frightened`, `fear`, `watch`
  - scored words: `frightened`(1.5), `fear`(1.2), `watch`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.task.respond.ask_frightened
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.task.respond.ask_frightened   [37 chars]
    en  What shouldn't they be frightened of?
    >>  ............................................
    pt  Do que não deviam ter medo?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter_expert.task.ask_frightened`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you seen signs lately?" | "Stay indoors after dark."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.task.ask_frightened
WHO    VILLAGER — what the player reads after pressing "What shouldn't they be frightened of?"
       spoken on: conversations.topic.work.hunter_expert.task.respond, button `ask_frightened`
       leaves the player on: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.task.ask_frightened`: the villager explains. Subject `work.hunter_expert.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.task.ask_frightened/1   [101 chars]
    en  Almost everything. Nine times in ten it's a fox, and a watch that panics at foxes is no watch at all.
    >>  ............................................
    pt  De quase tudo. Nove em dez vezes é uma raposa, e uma vigia que entra em pânico com raposa não é vigia.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.task.ask_frightened/2   [94 chars]
    en  Their neighbours, %1$s. That's the one I spend the most time on and it's the one that matters.
    >>  ............................................
    pt  Dos vizinhos, %1$s. É nisso que eu gasto mais tempo e é o que importa.
    >>  ............................................
```


### Button `offer_hands` — "I'll take the boundary with you tonight."

*stance family `practical_help` · tone `plain` · outcome `accepted` · answers the beat(s) `work.hunter_expert.task` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter_expert.task.offer_hands` — accepted phrasings: "i'll take the boundary with you tonight"
  - the message must contain one of: `boundary`, `tonight`, `walk`
  - scored words: `boundary`(1.5), `tonight`(1.2), `walk`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.task.respond.offer_hands
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.task.respond.offer_hands   [40 chars]
    en  I'll take the boundary with you tonight.
    >>  ............................................
    pt  Eu ando a divisa com você hoje.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +1** — decision id `work.hunter_expert.task.offer_hands`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.hunter_expert.task.offer_hands`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you seen signs lately?" | "Stay indoors after dark."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.task.offer_hands
WHO    VILLAGER — what the player reads after pressing "I'll take the boundary with you tonight."
       spoken on: conversations.topic.work.hunter_expert.task.respond, button `offer_hands`
       leaves the player on: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.task.offer_hands`: the villager accepts. Subject `work.hunter_expert.task`, polarity `mixed`, permits followup, outcome `accepted`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.task.offer_hands/1   [101 chars]
    en  ...Then walk on my left and say what you see before you decide what it is. That's the whole training.
    >>  ............................................
    pt  ...Então ande à minha esquerda e diga o que vê antes de decidir o que é. É todo o treino.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.task.offer_hands/2   [97 chars]
    en  Bring nothing sharp, %1$s. If you need something sharp on that walk, I've already made a mistake.
    >>  ............................................
    pt  Não traga nada afiado, %1$s. Se precisar de algo afiado nessa caminhada, eu já errei antes.
    >>  ............................................
```


### Button `ask_same` — "The same walk every evening?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter_expert.task` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter_expert.task.ask_same` — accepted phrasings: "the same walk every evening"
  - the message must contain one of: `same`, `evening`, `routine`
  - scored words: `same`(1.2), `evening`(1.2), `routine`(1.5)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.task.respond.ask_same
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.task.respond.ask_same   [28 chars]
    en  The same walk every evening?
    >>  ............................................
    pt  A mesma caminhada toda noite?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter_expert.task.ask_same`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you seen signs lately?" | "Stay indoors after dark."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.task.ask_same
WHO    VILLAGER — what the player reads after pressing "The same walk every evening?"
       spoken on: conversations.topic.work.hunter_expert.task.respond, button `ask_same`
       leaves the player on: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.task.ask_same`: the villager explains. Subject `work.hunter_expert.task`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.task.ask_same/1   [97 chars]
    en  Nine years of it. It's the repetition that lets me notice the one evening something is different.
    >>  ............................................
    pt  Nove anos disso. É a repetição que me deixa notar a noite em que algo está diferente.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.task.ask_same/2   [91 chars]
    en  Same route, same hour, same order, %1$s. Vary it and you lose the only instrument you have.
    >>  ............................................
    pt  Mesma rota, mesma hora, mesma ordem, %1$s. Varie e você perde o único instrumento que tem.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.hunter_expert.task` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.task.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.task.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.task.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.hunter_expert.task.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.left`: the villager accepts. Subject `work.hunter_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter_expert.failing_lantern.blocked.respond / leave; conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond / leave; conversations.scene.work.hunter_expert.false_alarm.blocked.respond / leave; conversations.scene.work.hunter_expert.false_alarm.succeeded.respond / leave; conversations.scene.work.hunter_expert.followup / leave; conversations.scene.work.hunter_expert.the_real_night.succeeded.respond / leave; conversations.topic.work.hunter_expert.craft.respond / leave; conversations.topic.work.hunter_expert.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.hunter_expert.failing_lantern.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---


## `conversations.topic.work.hunter_expert.village.respond`

**Reached from 2 route(s):** `conversations.work` / `(auto)`; `conversations.work` / `(auto)`

The lines that can open it (write every button below so it answers *all* of them):
- `conversations.work.prof.hunter_expert.village` — e.g. "Nine years and I've never put a name to anybody in this valley. That's the achievement, not the two nights."


```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.village.respond
WHO    VILLAGER — the node's own prompt. **Fallback only:** the player sees it when they arrive here on a result that carried no line of its own.
       node: conversations.topic.work.hunter_expert.village.respond
ARGS   %1$s = the player's name
SIZE   1 line in this pool
NOTE   Write it so it reads correctly cold, with nothing before it.
```

```text
  dialogue.conversations.topic.work.hunter_expert.village.respond   [25 chars]
    en  That's the account of it.
    >>  ............................................
    pt  É esse o balanço.
    >>  ............................................
```


### Button `ask_never` — "Not one name in nine years?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter_expert.village` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter_expert.village.ask_never` — accepted phrasings: "not one name in nine years"
  - the message must contain one of: `name`, `nine`, `none`
  - scored words: `name`(1.5), `nine`(1.2), `none`(1.2)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.village.respond.ask_never
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.village.respond.ask_never   [27 chars]
    en  Not one name in nine years?
    >>  ............................................
    pt  Nem um nome em nove anos?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter_expert.village.ask_never`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you seen signs lately?" | "Stay indoors after dark."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.village.ask_never
WHO    VILLAGER — what the player reads after pressing "Not one name in nine years?"
       spoken on: conversations.topic.work.hunter_expert.village.respond, button `ask_never`
       leaves the player on: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.village.ask_never`: the villager explains. Subject `work.hunter_expert.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.village.ask_never/1   [96 chars]
    en  Not one. Three times I was asked to and three times I said I wasn't sure, which is what I'm for.
    >>  ............................................
    pt  Nenhum. Três vezes me pediram e três vezes eu disse que não tinha certeza, que é pra isso que eu sirvo.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.village.ask_never/2   [74 chars]
    en  It's the only line on my ledger and it's the line I'd want read out, %1$s.
    >>  ............................................
    pt  É a única linha do meu registro e é a linha que eu queria lida, %1$s.
    >>  ............................................
```


### Button `say_thanks` — "Three refusals is a harder record than three catches."

*stance family `encouragement` · tone `plain` · outcome `appreciated` · answers the beat(s) `work.hunter_expert.village` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter_expert.village.say_thanks` — accepted phrasings: "three refusals is a harder record than three catches"
  - the message must contain one of: `refusals`, `record`
  - scored words: `refusals`(1.5), `record`(1.2), `three`(0.8)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.village.respond.say_thanks
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.village.respond.say_thanks   [53 chars]
    en  Three refusals is a harder record than three catches.
    >>  ............................................
    pt  Três recusas é um recorde mais difícil que três capturas.
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: **hearts +2** — decision id `work.hunter_expert.village.say_thanks`, budget `standard`, replay policy `daily_repeat`
- Does: disposition — respect +3  _(recorded under topic `work.hunter_expert.village.say_thanks`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you seen signs lately?" | "Stay indoors after dark."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.village.say_thanks
WHO    VILLAGER — what the player reads after pressing "Three refusals is a harder record than three catches."
       spoken on: conversations.topic.work.hunter_expert.village.respond, button `say_thanks`
       leaves the player on: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.village.say_thanks`: the villager accepts. Subject `work.hunter_expert.village`, polarity `positive`, permits followup, outcome `appreciated`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.village.say_thanks/1   [82 chars]
    en  ...Harder, and invisible. You can't show anybody the family that wasn't destroyed.
    >>  ............................................
    pt  ...Mais difícil, e invisível. Você não consegue mostrar a família que não foi destruída.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.village.say_thanks/2   [88 chars]
    en  Nobody frames it as a record at all, %1$s. They frame it as me not having done anything.
    >>  ............................................
    pt  Ninguém chama isso de recorde, %1$s. Chamam de eu não ter feito nada.
    >>  ............................................
```


### Button `ask_priest` — "Why never disagree in public?"

*stance family `curiosity` · tone `plain` · outcome `engaged` · answers the beat(s) `work.hunter_expert.village` · offered only once the villager has actually said `work:hunter_expert`*

In chat mode the player can type this instead of clicking. Rewrite the button and these phrasings together, or typing it stops working:
- intent `work.hunter_expert.village.ask_priest` — accepted phrasings: "why never disagree in public"
  - the message must contain one of: `priest`, `public`, `disagree`
  - scored words: `priest`(1.5), `public`(1.5), `disagree`(1.0)

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.village.respond.ask_priest
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.village.respond.ask_priest   [29 chars]
    en  Why never disagree in public?
    >>  ............................................
    pt  Por que nunca discordar em público?
    >>  ............................................
```

**What pressing it does, and what the villager says back:**


**Outcome 1 of 1** — base weight `1`

- Fires when: always eligible (no conditions on it)
- Does: disposition — familiarity +2  _(recorded under topic `work.hunter_expert.village.ask_priest`)_
- Does: session `turn`
- Then opens: `conversations.topic.work.hunter_expert.followup`
- …where the player's next choices will be: "I'd not thought about it that way." | "Have you seen signs lately?" | "Stay indoors after dark."

```text
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.village.ask_priest
WHO    VILLAGER — what the player reads after pressing "Why never disagree in public?"
       spoken on: conversations.topic.work.hunter_expert.village.respond, button `ask_priest`
       leaves the player on: conversations.topic.work.hunter_expert.followup
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.village.ask_priest`: the villager explains. Subject `work.hunter_expert.village`, polarity `mixed`, permits followup, outcome `engaged`.
NOTE   this is the line that establishes `work:hunter_expert` — later lines read it back, so the replacement must still say it
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: candor, curiosity, exit
NOTE   only ever spoken by a adult
```

```text
  dialogue.conversations.work.prof.hunter_expert.village.ask_priest/1   [110 chars]
    en  Because the moment we do, everybody picks a side, and a valley with sides in it is a valley that names people.
    >>  ............................................
    pt  Porque no momento em que discordarmos, todos escolhem um lado, e um vale com lados é um vale que nomeia gente.
    >>  ............................................
  dialogue.conversations.work.prof.hunter_expert.village.ask_priest/2   [90 chars]
    en  We agreed it in the first month, %1$s, and it's the only agreement between us that's held.
    >>  ............................................
    pt  Combinamos no primeiro mês, %1$s, e é o único acordo entre nós que se manteve.
    >>  ............................................
```


### Button `leave` — "I'll let you get on."

*stance family `exit` · tone `plain` · outcome `conversation_ended` · answers the beat(s) `work.hunter_expert.village` · **this is the graceful way out of the node***

```text
POOL   dialogue key: dialogue.conversations.topic.work.hunter_expert.village.respond.leave
WHO    PLAYER — the words printed on the button the player presses
       on the node: conversations.topic.work.hunter_expert.village.respond
ARGS   none — button labels take no substitutions; write plain text
SIZE   1 line in this pool
NOTE   Write it as something a person would actually say, in the player's voice. Never name the mechanic ("Persuade", "Gain trust") and never show a number.
NOTE   It must make sense as a reply to every line that can open this node (listed above).
```

```text
  dialogue.conversations.topic.work.hunter_expert.village.respond.leave   [20 chars]
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
POOL   dialogue key: dialogue.conversations.work.prof.hunter_expert.leave
WHO    VILLAGER — what the player reads after pressing "I'll let you get on."
       spoken on: conversations.topic.work.hunter_expert.village.respond, button `leave`
       leaves the player on: conversations.cat.profession
ARGS   %1$s = the player's name (MCA prepends it to every line; using it is optional)
SIZE   2 lines in this pool — the game picks one at random each time, so they must be interchangeable
NOTE   beat `work.hunter_expert.left`: the villager accepts. Subject `work.hunter_expert.future`, polarity `neutral`, permits followup, outcome `conversation_ended`.
NOTE   the buttons that answer it may only take these stances, so leave room for exactly them: exit
NOTE   only ever spoken by a adult
NOTE   the same pool is also spoken at: conversations.scene.work.hunter_expert.failing_lantern.blocked.respond / leave; conversations.scene.work.hunter_expert.failing_lantern.succeeded.respond / leave; conversations.scene.work.hunter_expert.false_alarm.blocked.respond / leave; conversations.scene.work.hunter_expert.false_alarm.succeeded.respond / leave; conversations.scene.work.hunter_expert.followup / leave; conversations.scene.work.hunter_expert.the_real_night.succeeded.respond / leave; conversations.topic.work.hunter_expert.craft.respond / leave; conversations.topic.work.hunter_expert.followup / leave …and 4 more
```

> Written out in full under **`conversations.scene.work.hunter_expert.failing_lantern.blocked.respond` / button `leave`** earlier in this file. Fill it in there, once.

---

